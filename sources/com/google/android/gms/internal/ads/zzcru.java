package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcru extends SQLiteOpenHelper {
    public zzcru(Context context) {
        super(context, "OfflineUpload.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE offline_signal_contents (timestamp INTEGER PRIMARY_KEY, serialized_proto_data BLOB)");
        sQLiteDatabase.execSQL("CREATE TABLE offline_signal_statistics (statistic_name TEXT PRIMARY_KEY, value INTEGER)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("statistic_name", "failed_requests");
        contentValues.put("value", 0);
        sQLiteDatabase.insert("offline_signal_statistics", (String) null, contentValues);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("statistic_name", "total_requests");
        contentValues2.put("value", 0);
        sQLiteDatabase.insert("offline_signal_statistics", (String) null, contentValues2);
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put("statistic_name", "last_successful_request_time");
        contentValues3.put("value", 0L);
        sQLiteDatabase.insert("offline_signal_statistics", (String) null, contentValues3);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS offline_signal_contents");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS offline_signal_statistics");
    }
}
