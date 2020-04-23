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
package voidpointer.bukkit.framework.event;

import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** @author VoidPointer aka NyanGuyMF */
@RequiredArgsConstructor
public class PluginEventManager implements EventManager {
    @NonNull private final Plugin plugin;
    @NonNull private final BukkitScheduler scheduler;
    @NonNull private final PluginManager pluginManager;

    public PluginEventManager(final Plugin plugin) {
        this(plugin, plugin.getServer().getScheduler(), plugin.getServer().getPluginManager());
    }

    @Override public void callEvent(final Event event) {
        scheduler.runTask(plugin, () -> fireEvent(event));
    }

    @Override public void callEventAsync(final Event event) {
        scheduler.runTaskAsynchronously(plugin, () -> fireEvent(event));
    }

    protected final void fireEvent(final Event event) {
        pluginManager.callEvent(event);
    }
}
