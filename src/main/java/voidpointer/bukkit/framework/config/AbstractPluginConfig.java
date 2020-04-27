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
package voidpointer.bukkit.framework.config;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/** @author VoidPointer aka NyanGuyMF */
@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
abstract class AbstractPluginConfig implements Config {
    protected static final boolean REAPLCE = true;
    protected static final String LOCALE_SEPARATOR = "_";

    @NonNull private final Plugin pluginOwner;
    @NonNull private File configFolder;
    @NonNull private String configFilename;
    private String currentLocale;
    private YamlConfiguration config;

    protected final boolean load(final String configFilename) {
        final File configFile = new File(configFolder, configFilename);
        saveDefaultIfNotExists(configFile);
        setConfig(YamlConfiguration.loadConfiguration(configFile));
        return true;
    }

    protected final void saveDefaultIfNotExists(final File file) {
        try {
            if (!file.exists())
                pluginOwner.saveResource(configFilename, REAPLCE);
        } catch (IllegalArgumentException ignore) {
            /** should be pointing to nonexisting resource. */
        }
    }

    protected final String formatLocalizedFilename(final String configFilename) {
        FilenamePattern pattern = FilenamePattern.match(configFilename);
        int localizedFilenameLength = configFilename.length() + currentLocale.length()
                                        + LOCALE_SEPARATOR.length();
        StringBuffer localizedFilename = new StringBuffer(localizedFilenameLength);
        localizedFilename.append(pattern.getFullFilePath())
                .append(pattern.getFilename())
                .append(LOCALE_SEPARATOR)
                .append(pattern.getFileExtension());
        return localizedFilename.toString();
    }

    protected final boolean isLocalized() {
        return getCurrentLocale() != null;
    }

    protected final boolean saveYamlConfiguration() {
        boolean isSaved = false;
        try {
            getConfig().save(getLocalizedOrDefaultConfig());
            isSaved = true;
        } catch (IOException ignore) {}
        return isSaved;
    }

    protected final boolean reloadYamlConfiguration() {
        setConfig(YamlConfiguration.loadConfiguration(getLocalizedOrDefaultConfig()));
        return true;
    }

    private File getLocalizedOrDefaultConfig() {
        final String filename;
        if (isLocalized())
            filename = formatLocalizedFilename(getConfigFilename());
        else
            filename = getConfigFilename();
        return new File(getConfigFolder(), filename);
    }
}
