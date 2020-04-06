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

import java.io.File;

import org.bukkit.plugin.Plugin;

import voidpointer.bukkit.framework.config.PluginConfig;

/** @author VoidPointer aka NyanGuyMF */
public class PluginYamlLocale extends PluginConfig implements Locale {
    public static final String DEFAULT_FILENAME = "messages.yml";
    private static final String EMPTY_STRING = "";

    public PluginYamlLocale(final Plugin pluginOwner) {
        this(pluginOwner, pluginOwner.getDataFolder(), DEFAULT_FILENAME);
    }

    public PluginYamlLocale(final Plugin pluginOwner, final String messagesFilename) {
        this(pluginOwner, pluginOwner.getDataFolder(), messagesFilename);
    }

    public PluginYamlLocale(final Plugin pluginOwner, final File configFolder, final String messagesFilename) {
        super(pluginOwner, configFolder, messagesFilename);
    }

    @Override public LocalizedMessage getLocalized(final Message message) {
        return getLocalized(message.getPath(), message.getDefaultValue());
    }

    @Override public LocalizedMessage getLocalized(final String messagePath) {
        return getLocalized(messagePath, EMPTY_STRING);
    }

    @Override public LocalizedMessage getLocalized(final String messagePath, final String defaultValue) {
        final String message = super.getConfig().getString(messagePath, defaultValue);
        LocalizedMessage localizedMessage = new BaseLocalizedMessage(message);
        return localizedMessage;
    }
}
