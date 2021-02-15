package de.greenrobot.dao.query;

import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;

public class DeleteQuery<T> extends AbstractQuery<T> {
    private final QueryData<T> queryData;

    public /* bridge */ /* synthetic */ void setParameter(int i, Object obj) {
        super.setParameter(i, obj);
    }

    private static final class QueryData<T2> extends AbstractQueryData<T2, DeleteQuery<T2>> {
        private QueryData(AbstractDao<T2, ?> abstractDao, String str, String[] strArr) {
            super(abstractDao, str, strArr);
        }

        /* access modifiers changed from: protected */
        public DeleteQuery<T2> createQuery() {
            return new DeleteQuery(this, this.dao, this.sql, (String[]) this.initialValues.clone());
        }
    }

    static <T2> DeleteQuery<T2> create(AbstractDao<T2, ?> abstractDao, String str, Object[] objArr) {
        return (DeleteQuery) new QueryData(abstractDao, str, toStringArray(objArr)).forCurrentThread();
    }

    private DeleteQuery(QueryData<T> queryData2, AbstractDao<T, ?> abstractDao, String str, String[] strArr) {
        super(abstractDao, str, strArr);
        this.queryData = queryData2;
    }

    public DeleteQuery<T> forCurrentThread() {
        return (DeleteQuery) this.queryData.forCurrentThread(this);
    }

    public void executeDeleteWithoutDetachingEntities() {
        checkThread();
        SQLiteDatabase database = this.dao.getDatabase();
        if (database.isDbLockedByCurrentThread()) {
            this.dao.getDatabase().execSQL(this.sql, this.parameters);
            return;
        }
        database.beginTransaction();
        try {
            this.dao.getDatabase().execSQL(this.sql, this.parameters);
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }
}
