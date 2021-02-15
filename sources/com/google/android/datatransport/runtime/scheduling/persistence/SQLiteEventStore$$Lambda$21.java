package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* compiled from: SQLiteEventStore */
final /* synthetic */ class SQLiteEventStore$$Lambda$21 implements SQLiteEventStore.Function {
    private static final SQLiteEventStore$$Lambda$21 instance = new SQLiteEventStore$$Lambda$21();

    private SQLiteEventStore$$Lambda$21() {
    }

    public static SQLiteEventStore.Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Boolean.valueOf(((Cursor) obj).moveToNext());
    }
}
