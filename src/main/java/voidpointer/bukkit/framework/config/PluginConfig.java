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
package voidpointer.bukkit.framework.config;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * Abstract implementation of {@link Config} interface using Bukkit
 *      {@link YamlConfiguration} class.
 * <p>
 * This implementaiton will try to save configuraiton file from
 *      plugin resources if it does not exits.
 *
 * @author VoidPointer aka NyanGuyMF
 */
public abstract class PluginConfig extends AbstractPluginConfig {
    /**
     * Create new {@link PluginConfig} instance for configuration
     *      file with specified name in plugin's data folder.
     */
    public PluginConfig(final Plugin pluginOwner, final String configFilename) {
        this(pluginOwner, pluginOwner.getDataFolder(), configFilename);
    }

    /**
     * Create new {@link PluginConfig} instance for configuration file
     *      with specified name in specified configuration folder.
     */
    public PluginConfig(
            final Plugin pluginOwner,
            final File configFolder,
            final String configFilename
    ) {
        super(pluginOwner, configFolder, configFilename);
    }

    @Override public CompletableFuture<Boolean> load() {
        return CompletableFuture.supplyAsync(() -> {
            boolean isLoaded = load(super.getConfigFilename());
            if (isLoaded)
                onLoad();
            return isLoaded;
        });
    }

    /** Override this method if you want to listen this action. */
    protected void onLoad() {
        // empty implementation
    }

    @Override public CompletableFuture<Boolean> loadLocalized(final String locale) {
        return CompletableFuture.supplyAsync(() -> {
            setCurrentLocale(locale);
            final String filename = formatLocalizedFilename(locale);
            boolean isLoaded = load(filename);
            if (isLoaded)
                onLoadLocalized();
            return isLoaded;
        });
    }

    /** Override this method if you want to listen this action. */
    protected void onLoadLocalized() {
        // empty implementation
    }

    @Override public CompletableFuture<Boolean> save() {
        return CompletableFuture.supplyAsync(() -> {
            boolean isSaved = super.saveYamlConfiguration();
            if (isSaved)
                onSave();
            return isSaved;
        });
    }

    /** Override this method if you want to listen this action. */
    protected void onSave() {
        // empty implementation
    }

    @Override public CompletableFuture<Boolean> reload() {
        return CompletableFuture.supplyAsync(() -> {
            boolean isReloaded = super.reloadYamlConfiguration();
            if (isReloaded)
                onReload();
            return isReloaded;
        });
    }

    /** Override this method if you want to listen this action. */
    protected void onReload() {
        // empty implementation
    }

    @Override public CompletableFuture<Boolean> unload() {
        return CompletableFuture.supplyAsync(() -> {
            setConfig(null);
            onUnload();
            return true;
        });
    }

    /** Override this method if you want to listen this action. */
    protected void onUnload() {
        // empty implementation
    }

    @Override public boolean isLoaded() {
        return getConfig() != null;
    }

    @Override public void close() throws IOException {
        /* nothing to close */
    }
}
