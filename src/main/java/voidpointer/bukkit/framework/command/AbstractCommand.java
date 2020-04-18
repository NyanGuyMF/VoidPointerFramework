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

import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** @author VoidPointer aka NyanGuyMF */
@RequiredArgsConstructor
public abstract class AbstractCommand<T extends CommandArgs> implements Command<T> {
    @Getter
    @NonNull
    private final String name;

    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String permission = null;

    private List<CommandArgsValidator<T>> validators = new ArrayList<>();

    @Override public final boolean onCommand(
            final CommandSender sender,
            final org.bukkit.command.Command command,
            final String label,
            final String[] rawArgs
    ) {
        T args = newCommandArgs(sender, label, rawArgs);
        for (CommandArgsValidator<T> commandArgsValidator : validators) {
            if (!commandArgsValidator.areValid(this, args))
                return true; /** invalid args are handled by validators */
        }
        /*
         * If command execution fails, then we should send usage/help
         *      message to CommandSender.
         * TODO:
         *  - create another class, which represents the command,
         *      which allows this mechanic with abstract method
         *      onWrongUsage().
         */
        return execute(args);
    }

    protected abstract T newCommandArgs(CommandSender sender, String label, String[] args);

    @Override public final List<String> onTabComplete(
            final CommandSender sender,
            final org.bukkit.command.Command command,
            final String alias,
            final String[] args
    ) {
        return complete(newCommandArgs(sender, alias, args));
    }

    @Override public final void addValidator(final CommandArgsValidator<T> validator) {
        validators.add(validator);
    }

    @Override public final void register(final JavaPlugin plugin) {
        PluginCommand pluginCommand = plugin.getCommand(getName());
        if (pluginCommand != null)
            pluginCommand.setExecutor(this);
        else
            onCommandNotFound(plugin);
    }

    /** Called when command doesn't exists in plugin.yml. */
    protected void onCommandNotFound(final JavaPlugin plugin) {
        /** sub classes should override it */
    }
}
