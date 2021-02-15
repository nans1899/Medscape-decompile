package de.greenrobot.dao;

import android.database.Cursor;
import de.greenrobot.dao.internal.TableStatements;
import java.util.List;

public final class InternalQueryDaoAccess<T> {
    private final AbstractDao<T, ?> dao;

    public InternalQueryDaoAccess(AbstractDao<T, ?> abstractDao) {
        this.dao = abstractDao;
    }

    public T loadCurrent(Cursor cursor, int i, boolean z) {
        return this.dao.loadCurrent(cursor, i, z);
    }

    public List<T> loadAllAndCloseCursor(Cursor cursor) {
        return this.dao.loadAllAndCloseCursor(cursor);
    }

    public T loadUniqueAndCloseCursor(Cursor cursor) {
        return this.dao.loadUniqueAndCloseCursor(cursor);
    }

    public TableStatements getStatements() {
        return this.dao.getStatements();
    }

    public static <T2> TableStatements getStatements(AbstractDao<T2, ?> abstractDao) {
        return abstractDao.getStatements();
    }
}
