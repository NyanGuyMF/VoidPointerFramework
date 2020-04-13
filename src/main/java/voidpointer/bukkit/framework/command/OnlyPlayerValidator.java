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

import voidpointer.bukkit.framework.locale.Locale;
import voidpointer.bukkit.framework.locale.Message;

/** @author VoidPointer aka NyanGuyMF */
public class OnlyPlayerValidator extends LocalizedValidator<CommandArgs> {
    public OnlyPlayerValidator(final Locale locale) {
        this(locale, CommandErrorMessage.ONLY_PLAYER_ALLOWED);
    }

    public OnlyPlayerValidator(final Locale locale, final Message errorMessage) {
        super(locale, errorMessage);
    }

    @Override protected boolean areValid0(final Command<CommandArgs> cmd, final CommandArgs args) {
        return args.isPlayer();
    }
}
