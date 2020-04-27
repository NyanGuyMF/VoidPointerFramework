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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.NonNull;
import voidpointer.bukkit.framework.locale.Locale;

/** @author VoidPointer aka NyanGuyMF */
public class SimpleSubCommandManager extends AbstractSubCommandManager<CommandArgs> {
    private static final int SUB_COMMAND_NAME_INDEX = 0;
    @NonNull private final Locale locale;

    public SimpleSubCommandManager(final String name, final Locale locale) {
        super(name);
        this.locale = locale;
    }

    @Override public String getDisplayName() {
        return super.getName();
    }

    @Override protected void onCommandNotFound(final JavaPlugin plugin) {
        plugin.getLogger().severe(
            locale.getLocalized(CommandErrorMessage.REGISTER_UNKNOWN_COMMAND)
                    .colorize()
                    .set("command-name", getDisplayName())
                    .getValue()
        );
    }

    @Override protected void onSubCommandNotFound(final String subAlias, final CommandArgs args) {
        locale.getLocalized(CommandErrorMessage.UNKNOWN_SUB_COMMAND)
                .colorize()
                .set("command-name", getDisplayName())
                .set("sub-alias", subAlias)
                .send(args.getSender());
    }

    @Override protected void onSubCommandNotSpecified(final CommandArgs args) {
        locale.getLocalized(CommandErrorMessage.SUB_COMMAND_NOT_SPECIFIED)
                .colorize()
                .set("command-name", getDisplayName())
                .send(args.getSender());
    }

    @Override public List<String> complete(final CommandArgs args) {
        if (args.length() == 0)
            return completeZeroArgs(args);
        else if (args.length() == 1)
            return completeSubCommandName(args.get(SUB_COMMAND_NAME_INDEX), args);
        else
            return completeSubCommand(args.get(SUB_COMMAND_NAME_INDEX), args);
    }

    protected List<String> completeZeroArgs(final CommandArgs args) {
        return super.getSubCommands().parallelStream()
                .map(Command::getName)
                .collect(Collectors.toList());
    }

    protected List<String> completeSubCommandName(final String alias, final CommandArgs args) {
        return super.getSubCommands().parallelStream()
                .map(Command::getName)
                .filter(commandName -> commandName.startsWith(alias))
                .collect(Collectors.toList());
    }

    protected List<String> completeSubCommand(final String subCommandAlias, final CommandArgs args) {
        Command<CommandArgs> specifiedSubCommand = null;
        for (Command<CommandArgs> subCommand : super.getSubCommands()) {
            if (subCommand.getName().equals(subCommandAlias))
                specifiedSubCommand = subCommand;
        }
        if (specifiedSubCommand == null)
            return Arrays.asList(""); /* nothing to complete */
        args.getArgs().remove(SUB_COMMAND_NAME_INDEX);
        return specifiedSubCommand.complete(args);
    }

    @Override protected CommandArgs newCommandArgs(final CommandSender sender, final String label, final String[] args) {
        return new SimpleCommandArgs(sender, label, new ArrayList<>(Arrays.asList(args)));
    }
}
