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
package voidpointer.bukkit.framework.config.db;

import java.io.File;

import org.bukkit.configuration.ConfigurationSection;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** @author VoidPointer aka NyanGuyMF */
@RequiredArgsConstructor
public final class H2Connection implements DatabaseConnection {
    private static final String DEFAULT_DATABASE_PATH = "h2/database.h2";
    private static final String CONNECTION_URL_FORMAT = "jdbc:h2:%s";

    @NonNull private final File dataFolder;
    @NonNull private final String databasePath;

    public H2Connection(final File dataFolder) {
        this(dataFolder, DEFAULT_DATABASE_PATH);
    }

    public H2Connection(final File dataFolder, final ConfigurationSection connectionConfig) {
        this(
            dataFolder,
            connectionConfig.getString(
                "database-file-path", DEFAULT_DATABASE_PATH
            )
        );
    }

    @Override public DatabaseDriver getDriver() {
        return StandardDriver.H2;
    }

    @Override public String getConnectionUrl() {
        return String.format(
            CONNECTION_URL_FORMAT,
            new File(dataFolder, databasePath).getAbsolutePath()
        );
    }
}
