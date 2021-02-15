package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcsu implements zzdrp {
    private final zzcsv zzgnm;

    zzcsu(zzcsv zzcsv) {
        this.zzgnm = zzcsv;
    }

    public final Object apply(Object obj) {
        zzcsv zzcsv = this.zzgnm;
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", Long.valueOf(zzcsv.timestamp));
        contentValues.put("gws_query_id", zzcsv.zzdtb);
        contentValues.put("url", zzcsv.url);
        contentValues.put("event_state", Integer.valueOf(zzcsv.zzgnn - 1));
        ((SQLiteDatabase) obj).insert("offline_buffered_pings", (String) null, contentValues);
        return null;
    }
}
