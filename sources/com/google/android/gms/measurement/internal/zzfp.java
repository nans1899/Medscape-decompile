package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzbv;
import com.google.android.gms.internal.measurement.zzca;
import com.google.android.gms.internal.measurement.zzhz;
import com.google.android.gms.internal.measurement.zzih;
import com.google.android.gms.internal.measurement.zzlq;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
public final class zzfp extends zzkj implements zzaa {
    private static int zzb = 65535;
    private static int zzc = 2;
    private final Map<String, Map<String, String>> zzd = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zze = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzf = new ArrayMap();
    private final Map<String, zzca.zzb> zzg = new ArrayMap();
    private final Map<String, Map<String, Integer>> zzh = new ArrayMap();
    private final Map<String, String> zzi = new ArrayMap();

    zzfp(zzki zzki) {
        super(zzki);
    }

    /* access modifiers changed from: protected */
    public final boolean zzd() {
        return false;
    }

    private final void zzi(String str) {
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        if (this.zzg.get(str) == null) {
            byte[] zzd2 = zzi().zzd(str);
            if (zzd2 == null) {
                this.zzd.put(str, (Object) null);
                this.zze.put(str, (Object) null);
                this.zzf.put(str, (Object) null);
                this.zzg.put(str, (Object) null);
                this.zzi.put(str, (Object) null);
                this.zzh.put(str, (Object) null);
                return;
            }
            zzca.zzb.zza zza = (zzca.zzb.zza) zza(str, zzd2).zzbn();
            zza(str, zza);
            this.zzd.put(str, zza((zzca.zzb) ((zzhz) zza.zzz())));
            this.zzg.put(str, (zzca.zzb) ((zzhz) zza.zzz()));
            this.zzi.put(str, (Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public final zzca.zzb zza(String str) {
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        zzi(str);
        return this.zzg.get(str);
    }

    /* access modifiers changed from: protected */
    public final String zzb(String str) {
        zzc();
        return this.zzi.get(str);
    }

    /* access modifiers changed from: protected */
    public final void zzc(String str) {
        zzc();
        this.zzi.put(str, (Object) null);
    }

    /* access modifiers changed from: package-private */
    public final void zzd(String str) {
        zzc();
        this.zzg.remove(str);
    }

    /* access modifiers changed from: package-private */
    public final boolean zze(String str) {
        zzc();
        zzca.zzb zza = zza(str);
        if (zza == null) {
            return false;
        }
        return zza.zzh();
    }

    public final String zza(String str, String str2) {
        zzc();
        zzi(str);
        Map map = this.zzd.get(str);
        if (map != null) {
            return (String) map.get(str2);
        }
        return null;
    }

    private static Map<String, String> zza(zzca.zzb zzb2) {
        ArrayMap arrayMap = new ArrayMap();
        if (zzb2 != null) {
            for (zzca.zzc next : zzb2.zze()) {
                arrayMap.put(next.zza(), next.zzb());
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zzca.zzb.zza zza) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        if (zza != null) {
            for (int i = 0; i < zza.zza(); i++) {
                zzca.zza.C0036zza zza2 = (zzca.zza.C0036zza) zza.zza(i).zzbn();
                if (TextUtils.isEmpty(zza2.zza())) {
                    zzq().zzh().zza("EventConfig contained null event name");
                } else {
                    String zza3 = zza2.zza();
                    String zzb2 = zzgs.zzb(zza2.zza());
                    if (!TextUtils.isEmpty(zzb2)) {
                        zza2 = zza2.zza(zzb2);
                        zza.zza(i, zza2);
                    }
                    if (!zzlq.zzb() || !zzs().zza(zzat.zzcm)) {
                        arrayMap.put(zza2.zza(), Boolean.valueOf(zza2.zzb()));
                    } else {
                        arrayMap.put(zza3, Boolean.valueOf(zza2.zzb()));
                    }
                    arrayMap2.put(zza2.zza(), Boolean.valueOf(zza2.zzc()));
                    if (zza2.zzd()) {
                        if (zza2.zze() < zzc || zza2.zze() > zzb) {
                            zzq().zzh().zza("Invalid sampling rate. Event name, sample rate", zza2.zza(), Integer.valueOf(zza2.zze()));
                        } else {
                            arrayMap3.put(zza2.zza(), Integer.valueOf(zza2.zze()));
                        }
                    }
                }
            }
        }
        this.zze.put(str, arrayMap);
        this.zzf.put(str, arrayMap2);
        this.zzh.put(str, arrayMap3);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(String str, byte[] bArr, String str2) {
        zzaj();
        zzc();
        Preconditions.checkNotEmpty(str);
        zzca.zzb.zza zza = (zzca.zzb.zza) zza(str, bArr).zzbn();
        if (zza == null) {
            return false;
        }
        zza(str, zza);
        this.zzg.put(str, (zzca.zzb) ((zzhz) zza.zzz()));
        this.zzi.put(str, str2);
        this.zzd.put(str, zza((zzca.zzb) ((zzhz) zza.zzz())));
        zzi().zza(str, (List<zzbv.zza>) new ArrayList(zza.zzb()));
        try {
            zza.zzc();
            bArr = ((zzca.zzb) ((zzhz) zza.zzz())).zzbk();
        } catch (RuntimeException e) {
            zzq().zzh().zza("Unable to serialize reduced-size config. Storing full config instead. appId", zzer.zza(str), e);
        }
        zzac zzi2 = zzi();
        Preconditions.checkNotEmpty(str);
        zzi2.zzc();
        zzi2.zzaj();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) zzi2.c_().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzi2.zzq().zze().zza("Failed to update remote config (got 0). appId", zzer.zza(str));
            }
        } catch (SQLiteException e2) {
            zzi2.zzq().zze().zza("Error storing remote config. appId", zzer.zza(str), e2);
        }
        this.zzg.put(str, (zzca.zzb) ((zzhz) zza.zzz()));
        return true;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzb(String str, String str2) {
        Boolean bool;
        zzc();
        zzi(str);
        if (zzg(str) && zzkw.zzd(str2)) {
            return true;
        }
        if (zzh(str) && zzkw.zza(str2)) {
            return true;
        }
        Map map = this.zze.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final boolean zzc(String str, String str2) {
        Boolean bool;
        zzc();
        zzi(str);
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(str2) || FirebaseAnalytics.Event.PURCHASE.equals(str2) || FirebaseAnalytics.Event.REFUND.equals(str2)) {
            return true;
        }
        Map map = this.zzf.get(str);
        if (map == null || (bool = (Boolean) map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: package-private */
    public final int zzd(String str, String str2) {
        Integer num;
        zzc();
        zzi(str);
        Map map = this.zzh.get(str);
        if (map == null || (num = (Integer) map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    /* access modifiers changed from: package-private */
    public final long zzf(String str) {
        String zza = zza(str, "measurement.account.time_zone_offset_minutes");
        if (TextUtils.isEmpty(zza)) {
            return 0;
        }
        try {
            return Long.parseLong(zza);
        } catch (NumberFormatException e) {
            zzq().zzh().zza("Unable to parse timezone offset. appId", zzer.zza(str), e);
            return 0;
        }
    }

    private final zzca.zzb zza(String str, byte[] bArr) {
        if (bArr == null) {
            return zzca.zzb.zzj();
        }
        try {
            zzca.zzb zzb2 = (zzca.zzb) ((zzhz) ((zzca.zzb.zza) zzks.zza(zzca.zzb.zzi(), bArr)).zzz());
            zzet zzw = zzq().zzw();
            String str2 = null;
            Long valueOf = zzb2.zza() ? Long.valueOf(zzb2.zzb()) : null;
            if (zzb2.zzc()) {
                str2 = zzb2.zzd();
            }
            zzw.zza("Parsed config. version, gmp_app_id", valueOf, str2);
            return zzb2;
        } catch (zzih e) {
            zzq().zzh().zza("Unable to merge remote config. appId", zzer.zza(str), e);
            return zzca.zzb.zzj();
        } catch (RuntimeException e2) {
            zzq().zzh().zza("Unable to merge remote config. appId", zzer.zza(str), e2);
            return zzca.zzb.zzj();
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean zzg(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(zza(str, "measurement.upload.blacklist_internal"));
    }

    /* access modifiers changed from: package-private */
    public final boolean zzh(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(zza(str, "measurement.upload.blacklist_public"));
    }

    public final /* bridge */ /* synthetic */ zzjo zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzks f_() {
        return super.f_();
    }

    public final /* bridge */ /* synthetic */ zzo zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zzac zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzfp zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzep zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzkw zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzfo zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzer zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzfd zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }
}
