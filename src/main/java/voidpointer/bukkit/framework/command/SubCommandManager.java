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

import java.util.ArrayList;
import java.util.List;

/** @author VoidPointer aka NyanGuyMF */
public abstract class SubCommandManager<T extends CommandArgs> extends AbstractCommand<T> {
    private static final int FIRST_ARG = 0;
    private final List<Command<T>> subCommands = new ArrayList<>();

    public SubCommandManager(final String name) {
        super(name);
    }

    @Override public boolean execute(final T args) {
        final String specifiedSubCommandName = args.getArgs().get(FIRST_ARG);
        Command<T> specifiedSubCommand = null;
        for (final Command<T> subCommand : subCommands) {
            if (!subCommand.getName().equalsIgnoreCase(specifiedSubCommandName))
                continue;
            specifiedSubCommand = subCommand;
            break;
        }

        if (specifiedSubCommand == null) {
            onSubCommandNotFound(args);
            return true;
        }

        return specifiedSubCommand.execute(args);
    }

    /** Called when specified sub command not found. */
    protected void onSubCommandNotFound(final T args) {
        /* sub classes should override this method. */
    }
}
