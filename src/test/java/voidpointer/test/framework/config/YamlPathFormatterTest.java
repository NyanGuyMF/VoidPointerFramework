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
package voidpointer.test.framework.config;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import lombok.NonNull;
import voidpointer.bukkit.framework.config.YamlPathFormatter;

/** @author VoidPointer aka NyanGuyMF */
public class YamlPathFormatterTest {
    @NonNull private static final Map<String, String> validPaths = new HashMap<String, String>() {
        private static final long serialVersionUID = -4378946843617215753L;
    {
        put("  /c  add player ", ".c.add.player");
        put("help me pls", "help.me.pls");
        put(" help me pls", "help.me.pls");
        put(".help me pls", ".help.me.pls");
    }};

    @Test public void test() {
        for (String traget : validPaths.keySet())
            assertEquals(YamlPathFormatter.toPath(traget), validPaths.get(traget));
    }
}
