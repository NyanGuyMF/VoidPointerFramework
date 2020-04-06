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

import voidpointer.bukkit.framework.dependency.DataFolderDependencyManager;
import voidpointer.bukkit.framework.dependency.DependencyLoader;
import voidpointer.bukkit.framework.dependency.DependencyManager;
import voidpointer.bukkit.framework.dependency.PluginDependencyLoader;

/** @author VoidPointer aka NyanGuyMF */
public final class BaseVoidPointerFarmework implements VoidPointerFramework {
    @Override public DependencyManager getDependencyManager(final Plugin plugin) {
        return getDependencyManager(plugin, DEFAULT_DEPENDENCY_FOLDER);
    }

    @Override public DependencyManager getDependencyManager(
            final Plugin plugin, final String dependencyFolderName
    ) {
        File dependencyFolder = new File(plugin.getDataFolder(), dependencyFolderName);
        return getDependencyManager(plugin, dependencyFolder);
    }

    @Override public DependencyManager getDependencyManager(
            final Plugin plugin, final File dependencyFolder
    ) {
        DependencyLoader dependencyLoader = PluginDependencyLoader.forPlugin(plugin);
        if (dependencyLoader == null)
            return null;
        if (!dependencyFolder.exists())
            dependencyFolder.mkdirs();

        return new DataFolderDependencyManager(dependencyLoader, dependencyFolder);
    }
}
