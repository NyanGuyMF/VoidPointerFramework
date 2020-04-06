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
package voidpointer.bukkit.framework.config;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.NonNull;

/** @author VoidPointer aka NyanGuyMF */
public final class FilenamePattern {
    private static final Pattern FILENAME_PATTERN = Pattern.compile(
            "^((.*)[\\/\\\\])?(.+?)(\\..*)?$"
            , Pattern.MULTILINE
    );
    /** Full file path (including last {@link File#separatorChar}). */
    private static final int FULL_FILE_PATH_INDEX = 1;
    /** Extension without last {@link File#separatorChar}. */
    private static final int FILE_PATH_INDEX = 2;
    /** Filename without extension. */
    private static final int FILE_NAME_INDEX = 3;
    /** File extension with dot. */
    private static final int FILE_EXTENSION_INDEX = 4;

    @NonNull private final Matcher matcher;

    public static FilenamePattern match(final String target) {
        return new FilenamePattern(target);
    }

    private FilenamePattern(final String target) {
        matcher = FILENAME_PATTERN.matcher(target);
        if (!matcher.matches()) /* should neven happen */
            throw new IllegalArgumentException("Unable to match target string");
    }

    /*
     * Bad documentation:
     *      programmer shouldn't know about any inthernals.
     */

    /** @see #FULL_FILE_PATH_INDEX */
    public String getFullFilePath() {
        return matcher.group(FULL_FILE_PATH_INDEX);
    }

    /** @see #FILE_PATH_INDEX */
    public String getFilePath() {
        return matcher.group(FILE_PATH_INDEX);
    }

    /** @see #FILE_NAME_INDEX */
    public String getFilename() {
        return matcher.group(FILE_NAME_INDEX);
    }

    /** @see #FILE_EXTENSION_INDEX */
    public String getFileExtension() {
        return matcher.group(FILE_EXTENSION_INDEX);
    }
}
