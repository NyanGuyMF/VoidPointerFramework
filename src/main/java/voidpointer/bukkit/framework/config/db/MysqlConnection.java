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

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** @author VoidPointer aka NyanGuyMF */
@Builder
@NoArgsConstructor(access=AccessLevel.PRIVATE)
@RequiredArgsConstructor
final class MysqlConnection implements DatabaseConnection {
    private static final String CONNECTION_URL_FORMAT = "jdbc:mysql://%s:%s@%s:%d/%s";

    @Getter
    @NonNull
    private String connectionUrl;

    public static DatabaseConnection forCredentials(final MysqlCredentials credentials) {
        return new MysqlConnectionBuilder()
                .connectionUrl(formatUrl(credentials))
                .build();
    }

    private static String formatUrl(final MysqlCredentials credentials) {
        return String.format(
            CONNECTION_URL_FORMAT,
            credentials.getUser(),
            credentials.getPassword(),
            credentials.getHost(),
            credentials.getPort(),
            credentials.getDatabase()
        );
    }

    @Override public DatabaseDriver getDriver() {
        return StandardDriver.MYSQL;
    }
}
