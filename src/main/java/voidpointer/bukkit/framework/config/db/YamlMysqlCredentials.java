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

import org.bukkit.configuration.ConfigurationSection;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** @author VoidPointer aka NyanGuyMF */
@Data
@Builder
@RequiredArgsConstructor(access=AccessLevel.PRIVATE)
public final class YamlMysqlCredentials implements MysqlCredentials {
    private static final String USER_PATH = "user";
    private static final String PASSWORD_PATH = "password";
    private static final String DATABASE_PATH = "database";
    private static final String HOST_PATH = "host";
    private static final String PORT_PATH = "port";

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 3306;

    public static MysqlCredentials fromYaml(final ConfigurationSection config) {
        if (!isCredentialsConfig(config))
            return null;
        return new YamlMysqlCredentialsBuilder()
                .user(config.getString(USER_PATH))
                .password(config.getString(PASSWORD_PATH))
                .database(config.getString(DATABASE_PATH))
                .host(config.getString(HOST_PATH, DEFAULT_HOST))
                .port(config.getInt(PORT_PATH, DEFAULT_PORT))
                .build();
    }

    private static boolean isCredentialsConfig(final ConfigurationSection config) {
        return config.isSet(USER_PATH) && config.isSet(PASSWORD_PATH)
                && config.isSet(DATABASE_PATH);
    }

    @NonNull private final String user;
    @NonNull private final String password;
    @NonNull private final String database;
    @NonNull private final String host;
    private final int port;
}
