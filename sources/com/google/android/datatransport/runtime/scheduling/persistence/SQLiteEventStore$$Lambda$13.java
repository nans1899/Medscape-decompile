package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;

/* compiled from: SQLiteEventStore */
final /* synthetic */ class SQLiteEventStore$$Lambda$13 implements SQLiteEventStore.Function {
    private final long arg$1;

    private SQLiteEventStore$$Lambda$13(long j) {
        this.arg$1 = j;
    }

    public static SQLiteEventStore.Function lambdaFactory$(long j) {
        return new SQLiteEventStore$$Lambda$13(j);
    }

    public Object apply(Object obj) {
        return Integer.valueOf(((SQLiteDatabase) obj).delete(OmnitureConstants.CHANNEL, "timestamp_ms < ?", new String[]{String.valueOf(this.arg$1)}));
    }
}
