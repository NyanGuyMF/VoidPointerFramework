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
package voidpointer.bukkit.framework.api;

import java.io.File;

import org.bukkit.plugin.Plugin;

import voidpointer.bukkit.framework.dependency.DependencyManager;
import voidpointer.bukkit.framework.event.EventManager;

/** @author VoidPointer aka NyanGuyMF */
public interface VoidPointerFramework {
    String DEFAULT_DEPENDENCY_FOLDER = "libs";

    /**
     * Creates new {@link DependencyManager} for specified plugin.
     * <p>
     * Uses {@link VoidPointerFramework#DEFAULT_DEPENDENCY_FOLDER} as
     *      dependency folder name in plugin's data folder.
     *
     * @see Plugin#getDataFolder()
     */
    DependencyManager getDependencyManager(Plugin plugin);

    /**
     * Creates new {@link DependencyManager} for specified plugin
     *      in dependencyFolder in plugin's data folder.
     *
     * @see Plugin#getDataFolder()
     */
    DependencyManager getDependencyManager(Plugin plugin, String dependencyFolderName);

    /**
     * Creates new {@link DependencyManager} for specified plugin
     *      in specified dependencyFolder.
     */
    DependencyManager getDependencyManager(Plugin plugin, File dependencyFolder);

    /**
     * Create a new {@link EventManager} instance which will manage
     *      events for specified plugin.
     */
    EventManager getEventManager(Plugin plugin);

    boolean requireOrmLite(Plugin plugin);
}
