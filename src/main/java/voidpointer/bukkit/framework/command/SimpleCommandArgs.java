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

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** @author VoidPointer aka NyanGuyMF */
@Data
@RequiredArgsConstructor
public class SimpleCommandArgs implements CommandArgs {
    @NonNull private final CommandSender sender;
    @NonNull private final String label;
    @NonNull private final List<String> args;

    @Override public boolean isPlayer() {
        return sender instanceof Player;
    }

    @Override public Player getPlayer() {
        if (isPlayer())
            return (Player) sender;
        return null;
    }

    @Override public int length() {
        return args.size();
    }

    @Override public String get(final int index) throws IndexOutOfBoundsException {
        return args.get(index);
    }
}
