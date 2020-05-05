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
package voidpointer.bukkit.framework.command.validator;

import voidpointer.bukkit.framework.command.Command;
import voidpointer.bukkit.framework.command.CommandArgs;
import voidpointer.bukkit.framework.command.CommandArgsValidator;

/** @author VoidPointer aka NyanGuyMF */
public abstract class BaseValidator<T extends CommandArgs> implements CommandArgsValidator<T> {
    @Override public final boolean areValid(final Command<T> cmd, final T args) {
        final boolean areValid = areValid0(cmd, args);
        if (!areValid)
            onNotValid(cmd, args);
        return areValid;
    }

    protected abstract boolean areValid0(final Command<T> cmd, final T args);

    protected abstract void onNotValid(final Command<T> cmd, final T args);
}
