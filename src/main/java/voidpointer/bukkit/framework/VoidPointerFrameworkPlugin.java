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
package voidpointer.bukkit.framework;

import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import lombok.NonNull;
import voidpointer.bukkit.framework.api.BaseVoidPointerFarmework;
import voidpointer.bukkit.framework.api.VoidPointerFramework;

/** @author VoidPointer aka NyanGuyMF */
public class VoidPointerFrameworkPlugin extends JavaPlugin {
    @Getter
    @NonNull
    private VoidPointerFramework framework;

    @Override public void onLoad() {
        framework = new BaseVoidPointerFarmework();
    }

    @Override public void onEnable() {

    }

    @Override public void onDisable() {

    }
}
