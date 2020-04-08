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

import java.io.Closeable;
import java.nio.file.NoSuchFileException;
import java.util.concurrent.CompletableFuture;

/**
 * Simple configuration interface for managing different
 *      program configuration files.
 * <p>
 * Class assumes that all configurations are stored in
 *      some files with some extensions.
 * <p>
 * Filename format for configuration is defined by implementation.
 *
 * @author VoidPointer aka NyanGuyMF
 */
public interface Config extends Closeable {
    CompletableFuture<Boolean> load();

    /**
     * Load localized configuration.
     * <p>
     * The file which is used for l10n is defined by implementation,
     *      but should contain locale String in it's name.
     */
    CompletableFuture<Boolean> loadLocalized(String locale);

    /**
     * Save configuration to localized file (if it's possible) or
     *      or default.
     */
    CompletableFuture<Boolean> save();

    /** Reload configuraiton using current file. */
    CompletableFuture<Boolean> reload() throws NoSuchFileException;

    /** Free all used resources if any. */
    CompletableFuture<Boolean> unload();

    /** Checks whether the configuration is loaded or not. */
    boolean isLoaded();
}
