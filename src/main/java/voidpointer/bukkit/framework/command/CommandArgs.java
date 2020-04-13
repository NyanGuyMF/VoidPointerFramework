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
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Abstraction for {@link CommandExecutor} method with useful methods.
 *
 * @author VoidPointer aka NyanGuyMF
 */
public interface CommandArgs {
    /**
     * Returns <tt>true</tt> if the {@link CommandSender} is
     *      {@link Player} instance and <tt>false</tt> otherwise;
     *
     * @see #getPlayer()
     */
    boolean isPlayer();

    /**
     * Returns {@link Player} if the {@link CommandSender} is
     *      {@link Player} instance and <tt>null</tt> otherwise.
     *
     * @see #isPlayer()
     */
    Player getPlayer();

    CommandSender getSender();

    String getLabel();

    List<String> getArgs();

    /**
     * Get argument from arguments list on specified index.
     *
     * @throws IndexOutOfBoundsException {@link List#get(int)}
     */
    String get(int index) throws IndexOutOfBoundsException;

    /** Get arguments list length. */
    int length();
}
