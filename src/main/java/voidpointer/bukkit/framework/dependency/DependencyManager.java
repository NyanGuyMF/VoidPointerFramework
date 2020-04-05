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
package voidpointer.bukkit.framework.dependency;

import java.util.concurrent.CompletableFuture;

/**
 * The main goal of this interface is to create anabstraction
 *      for all the internals of dependency management:
 *      file management, downloading, loading dependency
 *      .jar archive to current JVM session etc.
 *
 * @author VoidPointer aka NyanGuyMF
 */
public interface DependencyManager {
    /**
     * Check whether the dependency is installed.
     * <p>
     * Usually uses the {@link Dependency#getName()} in order
     *      to identify specified {@link Dependency}.
     *
     * @return <tt>true</tt> if the {@link Dependency} is already
     *      installed in current program session and <tt>false</tt>
     *      otherwise.
     */
    boolean isDependencyInstalled(Dependency dependency);

    /**
     * Installs dependency if it isn't installed yet.
     * <p>
     * The execution of this method can consume lots of
     *      time, so it uses {@link CompletableFuture}
     *      class to be able to get results of installation
     *      later.
     * <p>
     * The {@link CompletableFuture} completes with <tt>true</tt>
     *      if the installation completed without any error and
     *      <tt>false</tt> otherwise.
     */
    CompletableFuture<Boolean> installDependency(Dependency dependency);
}
