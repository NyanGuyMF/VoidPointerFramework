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
package voidpointer.bukkit.framework.command;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import voidpointer.bukkit.framework.locale.Message;

/** @author VoidPointer aka NyanGuyMF */
@Getter
@RequiredArgsConstructor
public enum CommandErrorMessage implements Message {
    REGISTER_UNKNOWN_COMMAND("&cTrying to register &6{command-name} &ccommand, which isn't in plugin.yml.&r"),
    NO_PERMISSION("&cYou have not enough permission for &6{command-name}&c command.&r"),
    PLAYER_COMMAND("&cOnly player can execute &6{command-name}&c command.&r"),
    NOT_ENOUGH_ARGS("&cNot enough arguments for &6{command-name}&c command.&r"),
    ONLY_PLAYER_ALLOWED("&cOnly player allowed to execute &6{command-name}&c command.&r"),
    UNKNOWN_SUB_COMMAND("&cUnknown sub command &6&o«{sub-alias}» for &6/{command-name}&c.&r"),
    SUB_COMMAND_NOT_SPECIFIED("&cYou should specify sub command for &6/{command-name}&c.&r"),
    ;

    public static final String ERROR_PATH_PREFIX = "error";

    @NonNull private final String defaultValue;

    @Override public final String getPath() {
        return String.format("%s.%s", ERROR_PATH_PREFIX, toString().replace('_', '-').toLowerCase());
    }
}
