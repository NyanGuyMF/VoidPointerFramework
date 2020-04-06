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
import lombok.val;

/** @author VoidPointer aka NyanGuyMF */
@RequiredArgsConstructor
final class BaseLocalizedMessage implements LocalizedMessage {
    private static final int NOT_FOUND = -1;
    private static final String COLORS = "0123456789AaBbCcDdEeFfKkLlMmNnOoRr";

    @Getter
    @NonNull
    private String value;

    @Override public LocalizedMessage set(final String placeholder, final String value) {
        return null;
    }

    @Override public LocalizedMessage colorize() {
        return colorize(LocalizedMessage.CUSTOM_COLOR_CODE);
    }

    @Override public LocalizedMessage colorize(final char customColorCode) {
        value = ChatColor.translateAlternateColorCodes(customColorCode, value);
        return this;
    }

    public static void main(final String[] args) {
        val msg = new BaseLocalizedMessage("%^a%^b%^g%^");
        System.out.println(msg.colorize("%^").getValue());
    }

    @Override public LocalizedMessage colorize(final String customColorCode) {
        StringBuffer message = new StringBuffer(value);

        final String bukkitColorChar = String.valueOf(ChatColor.COLOR_CHAR);
        int occurenceStartIndex = 0;
        int occurenceEndIndex = 0;
        int expectedColorIndex;
        // iterate through every customColorCode occurence.
        while ((occurenceStartIndex = message.indexOf(customColorCode, occurenceEndIndex)) != NOT_FOUND) {
            /* expected color is the char after occurence. */
            expectedColorIndex = occurenceStartIndex + customColorCode.length();
            /* occurence ends before expected color. */
            occurenceEndIndex = expectedColorIndex;

            if (expectedColorIndex >= message.length())
                break; /* reached the end of message. */

            char expectedColor = message.charAt(expectedColorIndex);
            if (COLORS.indexOf(expectedColor) == NOT_FOUND)
                continue; /* the char after color code isn't color. */

            message.setCharAt(expectedColorIndex, toLowerCase(expectedColor));
            message.replace(occurenceStartIndex, occurenceEndIndex, bukkitColorChar);
        }
        value = message.toString();
        return this;
    }
}
