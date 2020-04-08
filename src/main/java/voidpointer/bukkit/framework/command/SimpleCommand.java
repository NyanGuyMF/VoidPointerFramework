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

import java.util.Arrays;

import org.bukkit.command.CommandSender;

/** @author VoidPointer aka NyanGuyMF */
public abstract class SimpleCommand extends AbstractCommand<CommandArgs> {
    public SimpleCommand(final String name) {
        super(name);
    }

    @Override public String getDisplayName() {
        return getName();
    }

    @Override protected CommandArgs newCommandArgs(final CommandSender sender, final String label, final String[] args) {
        return new SimpleCommandArgs(sender, label, Arrays.asList(args));
    }
}
