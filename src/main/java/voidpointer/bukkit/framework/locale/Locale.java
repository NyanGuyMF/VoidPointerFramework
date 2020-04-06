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

import lombok.NonNull;

/**
 * This interface is used for i18n.
 * <p>
 * {@link Locale} operates with {@link Message} objects, resolves their
 *      paths and gets appropriate {@link LocalizedMessage} instances.
 *
 * @author VoidPointer aka NyanGuyMF
 */
public interface Locale {
    /**
     * Finds localized version for specified {@link Message} object
     *      in locale storage and returns it.
     * <p>
     * If there is no localized version for {@link Message}'s path,
     *      locale will use default value of {@link Message}.
     */
    LocalizedMessage getLocalized(@NonNull Message message);

    /**
     * Finds localized message for specified path.
     * <p>
     * If there is no message for specified path, the method will
     *      use empty string as a default value.
     *
     * @deprecated This method is not recommended to use, you should
     *      create your {@link Message} implementation (enums are
     *      very useful for such purposes) and use {@link #getLocalized(Message)}
     *      method.
     */
    @Deprecated
    LocalizedMessage getLocalized(@NonNull String messagePath);

    /**
     * Finds localized message for specified path.
     * <p>
     * If there is noe message for specified path, the method will
     *      use specified default value as localized one.
     *
     * @deprecated This method is not recommended to use, you should
     *      create your {@link Message} implementation (enums are
     *      very useful for such purposes) and use {@link #getLocalized(Message)}
     *      method.
     */
    @Deprecated
    LocalizedMessage getLocalized(@NonNull String messagePath, @NonNull String defaultValue);
}
