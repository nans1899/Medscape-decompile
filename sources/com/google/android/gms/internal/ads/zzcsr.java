package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcsr implements Runnable {
    private final String zzdgm;
    private final SQLiteDatabase zzgnj;
    private final zzbbu zzgnk;

    zzcsr(SQLiteDatabase sQLiteDatabase, String str, zzbbu zzbbu) {
        this.zzgnj = sQLiteDatabase;
        this.zzdgm = str;
        this.zzgnk = zzbbu;
    }

    public final void run() {
        SQLiteDatabase sQLiteDatabase = this.zzgnj;
        String str = this.zzdgm;
        zzbbu zzbbu = this.zzgnk;
        ContentValues contentValues = new ContentValues();
        contentValues.put("event_state", 1);
        sQLiteDatabase.update("offline_buffered_pings", contentValues, "gws_query_id = ?", new String[]{str});
        sQLiteDatabase.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(25);
            sb.append("event_state = 1");
            Cursor query = sQLiteDatabase.query("offline_buffered_pings", new String[]{"url"}, sb.toString(), (String[]) null, (String) null, (String) null, "timestamp ASC", (String) null);
            int count = query.getCount();
            String[] strArr = new String[count];
            int i = 0;
            while (query.moveToNext()) {
                int columnIndex = query.getColumnIndex("url");
                if (columnIndex != -1) {
                    strArr[i] = query.getString(columnIndex);
                }
                i++;
            }
            query.close();
            sQLiteDatabase.delete("offline_buffered_pings", "event_state = ?", new String[]{Integer.toString(1)});
            sQLiteDatabase.setTransactionSuccessful();
            for (int i2 = 0; i2 < count; i2++) {
                zzbbu.zzes(strArr[i2]);
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }
}
