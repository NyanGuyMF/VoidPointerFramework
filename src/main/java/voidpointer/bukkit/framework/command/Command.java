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

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Simplified bukkit command representation using {@link CommandArgs}
 *      interface instead of raw {@link CommandExecutor} arguments in
 *      order to make the command execution lifecycle more flexible and
 *      readable.
 * <p>
 * The framework allows you to use command both as single Bukkit command
 *      just like /tp {Player} or /sethome and as a sub command manager
 *      /clan kick {Player}, /clan invite {Player}.
 * <p>
 * The {@link AbstractCommand} class allows you to implement single Bukkit command.
 * <p>
 * The {@link AbstractSubCommandManager} class allows you to implement single command
 *      with sub commands.
 *
 * @author VoidPointer aka NyanGuyMF
 */
public interface Command<T extends CommandArgs> extends CommandExecutor, TabCompleter {
    /**
     * Get the command name.
     * <p>
     * The name for single Bukkit command is <i>/</i><b>clan</b>
     *      <i>arg1 .. argn</i>.
     * <p>
     * The name for sub command in manager is i.e. <i>/clan</i>
     *      <b>kick</b> <i>arg1 .. argn</i>.
     */
    String getName();

    /**
     * Get the name, which will be displayed for player.
     * <p>
     * I.e. if command is sub command of /clan, the sub command
     *      name is <b>kick</b>, then display name should be
     *      <b>clan kick</b>.
     */
    String getDisplayName();

    /**
     * Get the permission required for this command.
     * <p>
     * If there is no permission required for this command,
     *      method returns the empty string or null.
     *
     * @return The permission required for this command or
     *      <tt>null</tt> and empty string if there is no
     *      permission required for command.
     */
    String getPermission();

    /**
     * Add a validator for command.
     * <p>
     * The validators are used to validate command arguments during execution.
     *      When you add a validator, it means that now the command is validated
     *      by the validator and it will filter command calls by {@link CommandExecutor}.
     * <p>
     * I.e. if you want only players to be able to execute your command,
     *      then you could implement this using {@link CommandArgsValidator}
     *      interface or use some ready to use class.
     *
     * @see CommandArgsValidator
     */
    void addValidator(CommandArgsValidator<T> validator);

    /** @see CommandExecutor */
    boolean execute(T args);

    /** @see TabCompleter */
    List<String> complete(T args);

    void register(JavaPlugin plugin);
}
