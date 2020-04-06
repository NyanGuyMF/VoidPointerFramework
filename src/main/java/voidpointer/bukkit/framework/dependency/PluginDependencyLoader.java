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

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URLClassLoader;

import org.bukkit.plugin.Plugin;

/** @author VoidPointer aka NyanGuyMF */
public final class PluginDependencyLoader implements DependencyLoader {
    private static final String ADD_URL_METHOD_NAME = "addURL";

    /**
     * Returns {@link DependencyLoader} instance for specified plugin or
     *      <tt>null</tt> if it cannot be created.
     */
    public static DependencyLoader forPlugin(final Plugin plugin) {
        DependencyLoader dependencyLoader = null;
        try {
            dependencyLoader = new PluginDependencyLoader(plugin);
        } catch (IllegalArgumentException ignore) {}
        return dependencyLoader;
    }

    private final URLClassLoader pluginClassLoader;

    private PluginDependencyLoader(final Plugin plugin) {
        ClassLoader pluginClassLoader = plugin.getClass().getClassLoader();
        if (pluginClassLoader instanceof URLClassLoader)
            this.pluginClassLoader = (URLClassLoader) pluginClassLoader;
        else
            throw new IllegalArgumentException("Plugin class loader is not instance of URLClassLoader");
    }

    /**
     * Load given dependency .jar archive to current JVM process
     *      using plugin {@link URLClassLoader}.
     */
    @Override public boolean load(final File dependencyFile) {
        final Method addUrlMethod = getAddUrlMethod();
        if (addUrlMethod == null)
            return false;

        try {
            addUrlMethod.invoke(pluginClassLoader, dependencyFile.toPath().toUri().toURL());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | MalformedURLException ingore) {
            return false;
        }
        return true;
    }

    /**
     * Returns protected {@link URLClassLoader#addURL(java.net.URL)} method
     *      or <tt>null</tt> if it doesn't exist (should never happen).
     */
    private Method getAddUrlMethod() {
        Method addUrlMethod = null;
        try {
            addUrlMethod = URLClassLoader.class.getDeclaredMethod(
                ADD_URL_METHOD_NAME, java.net.URL.class
            );
            addUrlMethod.setAccessible(true);
        } catch (Exception ignore) {}
        return addUrlMethod;
    }
}
