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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** @author VoidPointer aka NyanGuyMF */
public final class YamlPathFormatter {
    private static final Pattern nonWordCharPattern = Pattern.compile("(\\W+)\\w+");
    private static final int nonWordCharGroup = 1;
    private static final String yamlPathSeparator = ".";

    /**
     * Format target string into valid YAML path.
     * <p>
     * This method will replace any non-word characters in target string
     *      with {@link #yamlPathSeparator}.
     * <p>
     * Examples:
     * <pre>
     * " /c  add "      into  ".c.add"
     * " /c rank add  " into  ".c.rank.add"
     * "help me pls"    into  "help.me.pls"
     * </pre>
     */
    public static String toPath(final String target) {
        /**
         * While the code is not as readable as I expected to write this,
         *      I have to comment the implementation.
         */
        StringBuffer pathBuilder = new StringBuffer(target.trim());

        Matcher occurence = nonWordCharPattern.matcher(pathBuilder);
        int occurenceStart, occurenceEnd;
        int lastOccurenceOffset = 0;
        while (occurence.find()) {
            occurenceStart = occurence.start(nonWordCharGroup);
            occurenceEnd = occurence.end(nonWordCharGroup);
            /**
             * while we're changing the target every loop iteration
             *      (setting it as sub sequence of path builder), the
             *      current occurence start and end aren't related to current
             *      builder, so we have to store last occurence end and add
             *      it to current as the offset.
             */
            pathBuilder.replace(
                    occurenceStart + lastOccurenceOffset,
                    occurenceEnd + lastOccurenceOffset,
                    yamlPathSeparator
            );
            lastOccurenceOffset += occurenceEnd;
            /**
             * may optimize this line, but then we'll need to store somehow
             *      replaced characters count and do some magic with offset,
             *      but code is already no as readable as I expected, so
             *      I just left this.
             */
            occurence = nonWordCharPattern.matcher(pathBuilder.subSequence(
                    lastOccurenceOffset,
                    pathBuilder.length()
            ));
        }
        return pathBuilder.toString();
    }
}
