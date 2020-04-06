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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.codec.digest.DigestUtils;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class is base and final implementation of {@link DependencyManager}
 *      which assumes, that all the dependencies are stored in some
 *      data folder in file with name "{@link Dependency#getName()}.jar".
 * <p>
 * Not build to be thread safe.
 *
 * @author VoidPointer aka NyanGuyMF
 */
@Slf4j
@RequiredArgsConstructor
public final class DataFolderDependencyManager implements DependencyManager {
    private static final String DEPENDENCY_EXTENSION = ".jar";

    @NonNull private final DependencyLoader dependencyLoader;
    @NonNull private final File dependencyDataFolder;

    @Override public boolean isDependencyInstalled(final Dependency dependency) {
        return isClassLoaded(dependency.getClassName());
    }

    private boolean isClassLoaded(final String className) {
        boolean isClassLoaded = false;

        try {
            Class.forName(className);
            isClassLoaded = true;
        } catch (ClassNotFoundException ignore) {/* means that class isn't loaded */}

        return isClassLoaded;
    }

    @Override public CompletableFuture<Boolean> installDependency(final Dependency dependency) {
        return CompletableFuture.supplyAsync(() -> installDependency0(dependency));
    }

    private boolean installDependency0(final Dependency dependency) {
        if (isDependencyInstalled(dependency))
            return true;

        File dependencyFile = getDependencyFile(dependency);
        if (!dependencyFile.exists()) {
            boolean isDownloaded = download(dependency.getDownloadUri(), dependencyFile);
            if (!isDownloaded)
                return isDownloaded;
        }

        if (!sha1HashMatches(dependencyFile, dependency.getSha1())) {
            dependencyFile.delete();
            return false;
        }

        return dependencyLoader.load(dependencyFile);
    }

    private File getDependencyFile(final Dependency dependency) {
        final String dependencyFilename = dependency.getName() + DEPENDENCY_EXTENSION;
        return new File(dependencyDataFolder, dependencyFilename);
    }

    private boolean download(final URI source, final File destination) {
        InputStream sourceIn = null;
        try {
             sourceIn = source.toURL().openConnection().getInputStream();
        } catch (IOException ex) {
            log.debug("Unable to open connection with dependency download url", ex);
            return false;
        }

        try {
            Files.copy(sourceIn, destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            log.debug("Unable to download depenceny", ex);
            if (destination.exists())
                destination.delete();
            return false;
        }

        return destination.exists();
    }

    private boolean sha1HashMatches(final File target, final String expected) {
        String actual;
        try (InputStream targetIn = new FileInputStream(target)) {
            actual = DigestUtils.sha1Hex(targetIn);
        } catch (Exception ex) {
            actual = null;
        }
        if (actual == null)
            return false;
        return actual.equals(expected);
    }
}
