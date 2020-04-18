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
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import lombok.NonNull;
import voidpointer.bukkit.framework.config.PluginConfig;
import voidpointer.bukkit.framework.dependency.Dependency;

/** @author VoidPointer aka NyanGuyMF */
public final class PluginDatabaseConfig extends PluginConfig implements DatabaseConfig {
    protected static final String DATABASE_CONFIG_FILENAME = "database.yml";
    private static final String DRIVER_PATH = "driver";
    private static final String DEFAULT_DRIVER = "h2";

    @NonNull private DatabaseConnection connection;

    public PluginDatabaseConfig(final Plugin pluginOwner) {
        this(pluginOwner, DATABASE_CONFIG_FILENAME);
    }

    public PluginDatabaseConfig(final Plugin pluginOwner, final String configFilename) {
        super(pluginOwner, configFilename);
    }

    public PluginDatabaseConfig(final Plugin pluginOwner, final File configFolder, final String configFilename) {
        super(pluginOwner, configFolder, configFilename);
    }

    @Override protected void onLoad() {
        YamlConfiguration databaseConfig = super.getConfig();
        String driverName = databaseConfig.getString(DRIVER_PATH, DEFAULT_DRIVER);
        ConfigurationSection connectionConfig;
        if (databaseConfig.isSet(driverName)) {
            connectionConfig = databaseConfig.getConfigurationSection(driverName);
        } else {
            onConnectionConfigNotFound(driverName);
            driverName = DEFAULT_DRIVER;
            connectionConfig = loadDefaultConnectionConfig(databaseConfig);
        }
        ConnectionFactory connectionFactory = new DatabaseConnectionFactory(getPluginOwner());
        connection = connectionFactory.getConnection(driverName, connectionConfig);
    }

    @Override protected void onReload() {
        onLoad();
    }

    private ConfigurationSection loadDefaultConnectionConfig(final YamlConfiguration databaseConfig) {
        ConfigurationSection connectionConfig;
        if (databaseConfig.isSet(DEFAULT_DRIVER)) {
            connectionConfig = databaseConfig.getConfigurationSection(DEFAULT_DRIVER);
        } else {
            onConnectionConfigNotFound(DEFAULT_DRIVER);
            connectionConfig = new YamlConfiguration();
        }
        return connectionConfig;
    }

    private void onConnectionConfigNotFound(final String driverName) {
        super.getPluginOwner().getLogger().warning(String.format(
            "[DatabaseConfig] Connection settings for «%s» driver not found,"
            + "using default",
            driverName
        ));
    }

    @Override public Dependency getConnectorDependency() {
        if (!isLoaded())
            throw new IllegalStateException("Configuration isn't loaded.");
        return connection.getDriver();
    }

    @Override public String getConnectionUrl() {
        if (!isLoaded())
            throw new IllegalStateException("Configuration isn't loaded.");
        return connection.getConnectionUrl();
    }
}
