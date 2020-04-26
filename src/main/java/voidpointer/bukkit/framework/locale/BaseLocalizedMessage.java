/*
 * This file is part of VoidPointerFramework Bukkit plug-in.
 *
 * VoidPointerFramework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VoidPointerFramework is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VoidPointerFramework. If not, see <https://www.gnu.org/licenses/>.
 */
package voidpointer.bukkit.framework.locale;

import static java.lang.Character.toLowerCase;

import org.bukkit.ChatColor;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** @author VoidPointer aka NyanGuyMF */
@RequiredArgsConstructor
final class BaseLocalizedMessage implements LocalizedMessage {
    private static final int NOT_FOUND = -1;
    private static final String COLORS = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";
    private static final String ESCAPED_NEWLINE = "\\n";

    @Getter
    @NonNull
    private String value;

    @Override public LocalizedMessage set(String placeholder, final String replacement) {
        placeholder = PLACEHOLDER_START + placeholder + PLACEHOLDER_END;
        StringBuffer buffer = new StringBuffer(value);

        int occurrenceStart, occurrenceEnd;
        /* replace all placeholder occurrences with appropriate replacement */
        while ((occurrenceStart = buffer.indexOf(placeholder)) != -1) {
            occurrenceEnd = occurrenceStart + placeholder.length();
            buffer.replace(occurrenceStart, occurrenceEnd, replacement);
        }
        value = buffer.toString();
        return this;
    }

    @Override public LocalizedMessage set(final String placeholder, final LocalizedMessage replacement) {
        return set(placeholder, replacement.getValue());
    }

    @Override public LocalizedMessage colorize() {
        return colorize(LocalizedMessage.CUSTOM_COLOR_CODE);
    }

    @Override public LocalizedMessage colorize(final char customColorCode) {
        value = ChatColor.translateAlternateColorCodes(customColorCode, value);
        return this;
    }

    @Override public LocalizedMessage colorize(final String customColorCode) {
        StringBuffer message = new StringBuffer(value);

        final String bukkitColorChar = String.valueOf(ChatColor.COLOR_CHAR);
        int occurenceStartIndex = 0;
        int occurrenceEndIndex = 0;
        int expectedColorIndex;
        // iterate through every customColorCode occurence.
        while ((occurenceStartIndex = message.indexOf(customColorCode, occurrenceEndIndex)) != NOT_FOUND) {
            /* expected color is the char after occurence. */
            expectedColorIndex = occurenceStartIndex + customColorCode.length();
            /* occurence ends before expected color. */
            occurrenceEndIndex = expectedColorIndex;

            if (expectedColorIndex >= message.length())
                break; /* reached the end of message. */

            char expectedColor = message.charAt(expectedColorIndex);
            if (COLORS.indexOf(expectedColor) == NOT_FOUND)
                continue; /* the char after color code isn't color. */

            message.setCharAt(expectedColorIndex, toLowerCase(expectedColor));
            message.replace(occurenceStartIndex, occurrenceEndIndex, bukkitColorChar);
        }
        value = message.toString();
        return this;
    }

    @Override public LocalizedMessage multiline() {
        final StringBuffer message = new StringBuffer(value);
        final String target = ESCAPED_NEWLINE;
        final String replacement = System.lineSeparator();

        int occurenceStartIndex = 0;
        int occurrenceEndIndex = 0;
        while ((occurenceStartIndex = message.indexOf(target, occurrenceEndIndex)) != NOT_FOUND) {
            occurrenceEndIndex = occurenceStartIndex + target.length();
            message.replace(occurenceStartIndex, occurrenceEndIndex, replacement);
        }
        value = message.toString();
        return this;
    }

}
