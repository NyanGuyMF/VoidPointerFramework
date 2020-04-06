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
package voidpointer.bukkit.framework.locale;

/**
 * This interface is responsible for getting the right
 *      {@link LocalizedMessage} instance from {@link Locale}.
 * <p>
 * This interface assumes, that {@link Locale} resolves messages
 *      paths to find right {@link LocalizedMessage}. And if it
 *      cannot find the one, it will use default {@link Message}
 *      value. {@link #getDefaultValue()}
 * <p>
 * If specific {@link Locale} implementation doesn't provide
 *      localized version for specific {@link Message} object,
 *      {@link Locale} will use default value.
 *
 * @author VoidPointer aka NyanGuyMF
 */
public interface Message {
    /**
     * Gets thet path for current {@link Message} object.
     * <p>
     * The messages paths are used in {@link Locale} in order to
     *      get localized version of {@link Message}.
     */
    String getPath();

    /**
     * Gets default message associated with {@link Message}'s path.
     * @return
     */
    String getDefaultValue();
}
