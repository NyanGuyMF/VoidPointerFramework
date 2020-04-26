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

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import lombok.NonNull;
import voidpointer.bukkit.framework.db.OrmLiteDependency;
import voidpointer.bukkit.framework.dependency.DataFolderDependencyManager;
import voidpointer.bukkit.framework.dependency.Dependency;
import voidpointer.bukkit.framework.dependency.DependencyLoader;
import voidpointer.bukkit.framework.dependency.DependencyManager;
import voidpointer.bukkit.framework.dependency.PluginDependencyLoader;
import voidpointer.bukkit.framework.event.EventManager;
import voidpointer.bukkit.framework.event.PluginEventManager;

/** @author VoidPointer aka NyanGuyMF */
public final class BaseVoidPointerFarmework implements VoidPointerFramework {
    @NonNull private final Cache<Plugin, DependencyManager> dependencyManagersCache;

    public BaseVoidPointerFarmework() {
        dependencyManagersCache = CacheBuilder.newBuilder().build();
    }

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
        if (dependencyManagersCache.asMap().containsKey(plugin))
            return dependencyManagersCache.getIfPresent(plugin);

        DependencyLoader dependencyLoader = PluginDependencyLoader.forPlugin(plugin);
        if (dependencyLoader == null)
            return null;
        if (!dependencyFolder.exists())
            dependencyFolder.mkdirs();

        DependencyManager dependencyManager = new DataFolderDependencyManager(
            dependencyLoader, dependencyFolder
        );
        dependencyManagersCache.put(plugin, dependencyManager);
        return dependencyManager;
    }

    @Override public EventManager getEventManager(@NonNull final Plugin plugin) {
        return new PluginEventManager(plugin);
    }

    @Override public boolean requireOrmLite(final Plugin plugin) {
        return installOrmLite(dependencyManagersCache.getIfPresent(plugin));
    }

    private boolean installOrmLite(@NonNull final DependencyManager dependencyManager) {
        for (Dependency ormLiteDependency : OrmLiteDependency.values()) {
            if (dependencyManager.isDependencyInstalled(ormLiteDependency))
                continue;
            if (!dependencyManager.installDependency(ormLiteDependency).join())
                return false;
        }
        return true;
    }
}
