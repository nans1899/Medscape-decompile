package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* compiled from: SQLiteEventStore */
final /* synthetic */ class SQLiteEventStore$$Lambda$20 implements SQLiteEventStore.Function {
    private static final SQLiteEventStore$$Lambda$20 instance = new SQLiteEventStore$$Lambda$20();

    private SQLiteEventStore$$Lambda$20() {
    }

    public static SQLiteEventStore.Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$loadActiveContexts$8((Cursor) obj);
    }
}
