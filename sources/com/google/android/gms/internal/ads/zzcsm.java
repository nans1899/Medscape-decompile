package com.google.android.gms.internal.ads;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.ads.zztu;
import com.google.android.gms.internal.ads.zzua;
import java.util.ArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzcsm {
    private zzbbx zzdqm;
    private zzcrw zzgmo;
    private zzts zzgna;
    private Context zzvr;

    public zzcsm(Context context, zzbbx zzbbx, zzts zzts, zzcrw zzcrw) {
        this.zzvr = context;
        this.zzdqm = zzbbx;
        this.zzgna = zzts;
        this.zzgmo = zzcrw;
    }

    public final void zzaqh() {
        try {
            this.zzgmo.zza(new zzcsl(this));
        } catch (Exception e) {
            String valueOf = String.valueOf(e.getMessage());
            zzayp.zzfc(valueOf.length() != 0 ? "Error in offline signals database startup: ".concat(valueOf) : new String("Error in offline signals database startup: "));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzb(SQLiteDatabase sQLiteDatabase) throws Exception {
        SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
        ArrayList<zzua.zzo.zza> zza = zzcsj.zza(sQLiteDatabase);
        int i = 0;
        zzua.zzo zzo = (zzua.zzo) ((zzejz) zzua.zzo.zzom().zzce(this.zzvr.getPackageName()).zzcf(Build.MODEL).zzco(zzcsj.zza(sQLiteDatabase2, 0)).zzf(zza).zzcp(zzcsj.zza(sQLiteDatabase2, 1)).zzev(zzp.zzky().currentTimeMillis()).zzew(zzcsj.zzb(sQLiteDatabase2, 2)).zzbgt());
        ArrayList arrayList = zza;
        int size = arrayList.size();
        long j = 0;
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList.get(i2);
            i2++;
            zzua.zzo.zza zza2 = (zzua.zzo.zza) obj;
            if (zza2.zzoj() == zzuk.ENUM_TRUE && zza2.getTimestamp() > j) {
                j = zza2.getTimestamp();
            }
        }
        if (j != 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("value", Long.valueOf(j));
            sQLiteDatabase2.update("offline_signal_statistics", contentValues, "statistic_name = 'last_successful_request_time'", (String[]) null);
        }
        this.zzgna.zza((zztr) new zzcso(zzo));
        zzua.zzu.zza zzcr = zzua.zzu.zzot().zzcq(this.zzdqm.zzeel).zzcr(this.zzdqm.zzeem);
        if (!this.zzdqm.zzeen) {
            i = 2;
        }
        this.zzgna.zza((zztr) new zzcsn((zzua.zzu) ((zzejz) zzcr.zzcs(i).zzbgt())));
        this.zzgna.zza(zztu.zza.zzb.OFFLINE_UPLOAD);
        sQLiteDatabase2.delete("offline_signal_contents", (String) null, (String[]) null);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("value", 0);
        sQLiteDatabase2.update("offline_signal_statistics", contentValues2, "statistic_name = ?", new String[]{"failed_requests"});
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put("value", 0);
        sQLiteDatabase2.update("offline_signal_statistics", contentValues3, "statistic_name = ?", new String[]{"total_requests"});
        return null;
    }
}
