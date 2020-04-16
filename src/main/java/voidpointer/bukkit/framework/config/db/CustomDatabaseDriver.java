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
import java.net.URISyntaxException;

import org.bukkit.configuration.ConfigurationSection;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/** @author VoidPointer aka NyanGuyMF */
@Data
@Builder
@NoArgsConstructor(access=AccessLevel.PRIVATE)
@AllArgsConstructor
final class CustomDatabaseDriver implements DatabaseDriver {
    private static final String DOWNLOAD_URI_PATH = "download-uri";
    private static final String SHA1_PATH = "sha1-checksum";
    private static final String CLASS_NAME_PATH = "driver-classpath";
    private static final String NAME_PATH = "connector-name";

    @NonNull private URI downloadUri;
    @NonNull private String sha1;
    @NonNull private String className;
    @NonNull private String name;

    /**
     * Construct new custom {@link DatabaseDriver} from specified configuration.
     * <p>
     * Returns <tt>null</tt> if conifguration isn't driver config
     *      or has invalid download uri.
     */
    public static DatabaseDriver forConfig(final ConfigurationSection customConfig) {
        if (!isDriverConfig(customConfig))
            return null;
        final URI downloadUri;
        try {
            downloadUri = new URI(customConfig.getString(DOWNLOAD_URI_PATH));
        } catch (URISyntaxException ex) {
            System.err.printf(
                "Unable to load custom database driver config: %s\n",
                ex.getLocalizedMessage()
            );
            return null;
        }
        return new CustomDatabaseDriverBuilder()
                .downloadUri(downloadUri)
                .sha1(customConfig.getString(SHA1_PATH))
                .className(customConfig.getString(CLASS_NAME_PATH))
                .name(customConfig.getString(SHA1_PATH))
                .build();
    }

    private static boolean isDriverConfig(final ConfigurationSection config) {
        if (!config.isSet(DOWNLOAD_URI_PATH))
            return false;
        else if (!config.isSet(SHA1_PATH))
            return false;
        else if (!config.isSet(CLASS_NAME_PATH))
            return false;
        else if (!config.isSet(NAME_PATH))
            return false;
        else
            return true;
    }

    @Override public URI getDownloadUri() {
        return null;
    }

    @Override public String getSha1() {
        return null;
    }

    @Override public String getClassName() {
        return null;
    }

    @Override public String getName() {
        return null;
    }
}
