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

import java.util.Arrays;

import org.bukkit.configuration.ConfigurationSection;

/** @author VoidPointer aka NyanGuyMF */
class DatabaseConnectionFactory {
    public DatabaseConnection getConnection(
            final String driverName,
            final ConfigurationSection connectionConfig
    ) {
        StandardDriver standardDriver = Arrays.stream(StandardDriver.values())
                .filter(driver -> driver.getName().equals(driverName))
                .findFirst().orElse(null);
        if (standardDriver != null)
            return getStandardConnection(standardDriver, connectionConfig);
        return getCustomConnection(standardDriver, connectionConfig);
    }

    private DatabaseConnection getStandardConnection(
            final StandardDriver driver,
            final ConfigurationSection connectionConfiguration
    ) {
        switch (driver) {
        case H2:
            break;
        case MYSQL:
            break;

        default:
            /**
             * Should never happen, change this method to be up to date with
             *      {@link StandardDriver}
             */
            throw new IllegalStateException("dafaq?");
        }
        return null;
    }

    private DatabaseConnection getCustomConnection(
            final DatabaseDriver driver,
            final ConfigurationSection connectionConfiguration
    ) {
        return null;
    }
}
