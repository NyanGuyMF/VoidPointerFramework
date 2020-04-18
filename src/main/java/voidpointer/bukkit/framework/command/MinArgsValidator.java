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
public class MinArgsValidator extends LocalizedValidator<CommandArgs> {
    private final int minArgs;

    /**
     * Creates {@link MinArgsValidator} instance with
     *      {@link CommandErrorMessage#NOT_ENOUGH_ARGS} error message.
     *
     * @throws IllegalArgumentException if minArgs is negative integer.
     * @see MinArgsValidator#MinArgsValidator(Locale, Message, int)
     */
    public MinArgsValidator(final Locale locale, final int minArgs) {
        this(locale, CommandErrorMessage.NOT_ENOUGH_ARGS, minArgs);
    }

    /**
     * Creates {@link MinArgsValidator} instance with specified
     *      error message.
     *
     * @throws IllegalArgumentException if minArgs is negative integer.
     */
    public MinArgsValidator(final Locale locale, final Message errorMessage, final int minArgs) {
        super(locale, errorMessage);
        if (minArgs < 0)
            throw new IllegalArgumentException("Minimum args cannot be negative integer.");
        this.minArgs = minArgs;
    }

    @Override protected boolean areValid0(final Command<CommandArgs> cmd, final CommandArgs args) {
        return args.length() >= minArgs;
    }
}
