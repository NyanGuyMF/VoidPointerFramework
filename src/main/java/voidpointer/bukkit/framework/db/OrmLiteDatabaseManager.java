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

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import voidpointer.bukkit.framework.config.db.DatabaseConfig;
import voidpointer.bukkit.framework.dependency.Dependency;
import voidpointer.bukkit.framework.dependency.DependencyManager;

/** @author VoidPointer aka NyanGuyMF */
@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public abstract class OrmLiteDatabaseManager implements DatabaseManager {
    @NonNull private final DatabaseConfig config;
    @NonNull private final DependencyManager dependencyManager;

    private ConnectionSource connectionSource;

    @Override public CompletableFuture<Boolean> connect() {
        return CompletableFuture.supplyAsync(() -> connect0());
    }

    private boolean connect0() {
        if (!installConnector())
            return false;
        boolean isConnected = false;
        ConnectionSource connectionSource = null;
        try {
            connectionSource = new JdbcConnectionSource(config.getConnectionUrl());
            onConnectionEstablished(connectionSource);
            isConnected = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            this.connectionSource = connectionSource;
        }
        return isConnected;
    }

    protected abstract void onConnectionEstablished(ConnectionSource connectionSource);

    private boolean installConnector() {
        return installDependency(config.getConnectorDependency());
    }

    private boolean installDependency(final Dependency dependency) {
        if (!dependencyManager.isDependencyInstalled(dependency))
            return dependencyManager.installDependency(dependency).join();
        return false;
    }

    @Override public boolean isConnected() {
        /* how to detect if connection closed, but source isn't null? */
        return (connectionSource != null);
    }

    @Override public void close() {
        if (!(connectionSource instanceof ConnectionSource))
            return;
        connectionSource.closeQuietly();
    }
}
