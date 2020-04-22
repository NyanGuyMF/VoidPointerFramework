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

import lombok.AccessLevel;
import lombok.Getter;

/** @author VoidPointer aka NyanGuyMF */
public abstract class AbstractSubCommandManager<T extends CommandArgs> extends AbstractCommand<T> {
    private static final int FIRST_ARG = 0;
    @Getter(AccessLevel.PROTECTED)
    private final List<Command<T>> subCommands = new ArrayList<>();

    public AbstractSubCommandManager(final String name) {
        super(name);
    }

    @Override public boolean execute(final T args) {
        if (args.getArgs().size() < 1) {
            onSubCommandNotSpecified(args);
            return true;
        }

        final String subAlias = args.getArgs().get(FIRST_ARG);
        Command<T> specifiedSubCommand = null;
        for (final Command<T> subCommand : subCommands) {
            if (!subCommand.getName().equalsIgnoreCase(subAlias))
                continue;
            specifiedSubCommand = subCommand;
            break;
        }

        if (specifiedSubCommand == null) {
            onSubCommandNotFound(subAlias, args);
            return true;
        }

        /* no longer needed, executing sub command */
        args.getArgs().remove(FIRST_ARG);

        return specifiedSubCommand.execute(args);
    }

    protected void addSubCommand(final Command<T> subCommand) {
        subCommands.add(subCommand);
    }

    // should I refactor this method as executeSelf(args)?
    /** Called when the sub command is not specified. */
    protected void onSubCommandNotSpecified(final T args) {
        /* sub classes should override this method. */
    }

    /** Called when specified sub command not found. */
    protected void onSubCommandNotFound(final String subAlias, final T args) {
        /* sub classes should override this method. */
    }
}
