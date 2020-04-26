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
package voidpointer.bukkit.framework.db;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.j256.ormlite.dao.Dao;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/** @author VoidPointer aka NyanGuyMF */
@Getter(AccessLevel.PROTECTED)
@RequiredArgsConstructor
public abstract class OrmLiteDao<Model, Id> {
    private final List<Model> emptyResult = Arrays.asList();

    @NonNull private final Dao<Model, Id> dao;

    protected final boolean create(final Model model) {
        boolean isCreated = false;
        try {
            isCreated = dao.create(model) != 0;
        } catch (SQLException ex) {

        }
        return isCreated;
    }

    protected void onCreateException(final SQLException exception) {
        exception.printStackTrace();
    }

    protected final boolean update(final Model model) {
        boolean isUpdated = false;
        try {
            isUpdated = dao.update(model) != 0;
        } catch (SQLException ex) {
            onUpdateException(ex);
        }
        return isUpdated;
    }

    protected void onUpdateException(final SQLException exception) {
        exception.printStackTrace();
    }

    protected final boolean delete(final Model model) {
        boolean isDeleted = false;
        try {
            isDeleted = dao.delete(model) != 0;
        } catch (SQLException ex) {
            onDeleteException(ex);
        }
        return isDeleted;
    }

    protected void onDeleteException(final SQLException exception) {
        exception.printStackTrace();
    }

    protected final List<Model> findAll() {
        List<Model> all = null;
        try {
            all = dao.queryForAll();
        } catch (SQLException ex) {
            onFindAllException(ex);
        }
        return (all != null) ? all : emptyResult;
    }

    protected void onFindAllException(final SQLException exception) {
        exception.printStackTrace();
    }

    protected final List<Model> findMatching(final Model target) {
        List<Model> parametrizedResult = null;
        try {
            parametrizedResult = dao.queryForMatching(target);
        } catch (SQLException ex) {
            onFindMatchingException(ex);
        }
        return (parametrizedResult != null) ? parametrizedResult : emptyResult;
    }

    protected void onFindMatchingException(final SQLException exception) {
        exception.printStackTrace();
    }
}
