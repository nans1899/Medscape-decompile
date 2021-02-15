package com.google.android.gms.internal.ads;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcsp extends SQLiteOpenHelper {
    private final zzdzb zzglx;

    public zzcsp(Context context, zzdzb zzdzb) {
        super(context, "OfflineBufferedPings.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.zzglx = zzdzb;
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE offline_buffered_pings (timestamp INTEGER PRIMARY_KEY, gws_query_id TEXT, url TEXT, event_state INTEGER)");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS offline_buffered_pings");
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(sQLiteDatabase, i, i2);
    }

    /* access modifiers changed from: package-private */
    public final void zza(SQLiteDatabase sQLiteDatabase, zzbbu zzbbu, String str) {
        this.zzglx.execute(new zzcsr(sQLiteDatabase, str, zzbbu));
    }

    public final void zza(zzcsv zzcsv) {
        zzdyq.zza(this.zzglx.zze(new zzcss(this)), new zzcst(this, new zzcsu(zzcsv)), this.zzglx);
    }
}
