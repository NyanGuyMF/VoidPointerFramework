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

import java.net.URI;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** @author VoidPointer aka NyanGuyMF */
@Getter
@RequiredArgsConstructor
enum StandardDriver implements DatabaseDriver {
    H2(
        URI.create("https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar"),
        "f7533fe7cb8e99c87a43d325a77b4b678ad9031a",
        "org.h2.Driver"
    ),
    MYSQL(
        URI.create("https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.19/mysql-connector-java-8.0.19.jar"),
        "9af69ef0f68eae737351ff55c0ba6e23a06826a5",
        "com.mysql.jdbc.Driver"
    )
    ;

    public static final StandardDriver DEFAULT = H2;

    @NonNull private final URI downloadUri;
    @NonNull private final String sha1;
    @NonNull private final String className;

    @Override public String getName() {
        return toString();
    }

    @Override public String toString() {
        return super.toString().toLowerCase();
    }
}
