/*
 * This file is part of TemporalWhitelist Bukkit plug-in.
 *
 * TemporalWhitelist is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TemporalWhitelist is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TemporalWhitelist. If not, see <https://www.gnu.org/licenses/>.
 */
package voidpointer.bukkit.framework.db;

import java.net.URI;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import voidpointer.bukkit.framework.dependency.Dependency;

/** @author VoidPointer aka NyanGuyMF */
@Getter
@RequiredArgsConstructor
public enum OrmLiteDependency implements Dependency {
    CORE(
        URI.create("https://repo1.maven.org/maven2/com/j256/ormlite/ormlite-core/5.1/ormlite-core-5.1.jar"),
        "bf7a747016c99ac01577d3268a4ac9c24e94d5de",
        "com.j256.ormlite.dao.Dao",
        "ORMLite-Core"
    ),
    JDBC(
        URI.create("https://repo1.maven.org/maven2/com/j256/ormlite/ormlite-jdbc/5.1/ormlite-jdbc-5.1.jar"),
        "8c5fac7d12ee7d3c82a4eb01534779fc66f75d4a",
        "com.j256.ormlite.jdbc.JdbcDatabaseConnection",
        "ORMLite-JDBC"
    ),
    ;

    @NonNull private final URI downloadUri;
    @NonNull private final String sha1;
    @NonNull private final String className;
    @NonNull private final String name;
}
