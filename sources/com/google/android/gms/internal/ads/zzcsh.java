package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.ads.zzua;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final /* synthetic */ class zzcsh implements zzdrp {
    private final boolean zzeha;
    private final zzcsi zzgms;
    private final ArrayList zzgmt;
    private final zzua.zzm zzgmu;
    private final zzua.zzo.zzb zzgmv;

    zzcsh(zzcsi zzcsi, boolean z, ArrayList arrayList, zzua.zzm zzm, zzua.zzo.zzb zzb) {
        this.zzgms = zzcsi;
        this.zzeha = z;
        this.zzgmt = arrayList;
        this.zzgmu = zzm;
        this.zzgmv = zzb;
    }

    public final Object apply(Object obj) {
        zzcsi zzcsi = this.zzgms;
        boolean z = this.zzeha;
        SQLiteDatabase sQLiteDatabase = (SQLiteDatabase) obj;
        byte[] zza = zzcsi.zzgmx.zza(z, this.zzgmt, this.zzgmu, this.zzgmv);
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", Long.valueOf(zzp.zzky().currentTimeMillis()));
        contentValues.put("serialized_proto_data", zza);
        sQLiteDatabase.insert("offline_signal_contents", (String) null, contentValues);
        sQLiteDatabase.execSQL(String.format("UPDATE offline_signal_statistics SET value = value+1 WHERE statistic_name = '%s'", new Object[]{"total_requests"}));
        if (!z) {
            sQLiteDatabase.execSQL(String.format("UPDATE offline_signal_statistics SET value = value+1 WHERE statistic_name = '%s'", new Object[]{"failed_requests"}));
        }
        return null;
    }
}
