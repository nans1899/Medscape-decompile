package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcd;
import com.google.android.gms.internal.measurement.zzhz;
import com.google.android.gms.internal.measurement.zzmj;
import com.google.android.gms.internal.measurement.zznt;
import com.google.android.gms.internal.measurement.zzny;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.DebugKt;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
final class zzie extends zzkj {
    public zzie(zzki zzki) {
        super(zzki);
    }

    /* access modifiers changed from: protected */
    public final boolean zzd() {
        return false;
    }

    public final byte[] zza(zzar zzar, String str) {
        zzkt zzkt;
        Bundle bundle;
        zzcd.zzf.zza zza;
        zzcd.zzg.zza zza2;
        zzf zzf;
        byte[] bArr;
        Bundle bundle2;
        zzan zzan;
        long j;
        zzar zzar2 = zzar;
        String str2 = str;
        zzc();
        this.zzy.zzad();
        Preconditions.checkNotNull(zzar);
        Preconditions.checkNotEmpty(str);
        if (!zzs().zze(str2, zzat.zzav)) {
            zzq().zzv().zza("Generating ScionPayload disabled. packageName", str2);
            return new byte[0];
        } else if ("_iap".equals(zzar2.zza) || "_iapx".equals(zzar2.zza)) {
            zzcd.zzf.zza zzb = zzcd.zzf.zzb();
            zzi().zze();
            zzf zzb2 = zzi().zzb(str2);
            if (zzb2 == null) {
                zzq().zzv().zza("Log and bundle not available. package_name", str2);
                byte[] bArr2 = new byte[0];
                zzi().zzg();
                return bArr2;
            } else if (!zzb2.zzr()) {
                zzq().zzv().zza("Log and bundle disabled. package_name", str2);
                byte[] bArr3 = new byte[0];
                zzi().zzg();
                return bArr3;
            } else {
                zzcd.zzg.zza zza3 = zzcd.zzg.zzbh().zza(1).zza("android");
                if (!TextUtils.isEmpty(zzb2.zzc())) {
                    zza3.zzf(zzb2.zzc());
                }
                if (!TextUtils.isEmpty(zzb2.zzn())) {
                    zza3.zze(zzb2.zzn());
                }
                if (!TextUtils.isEmpty(zzb2.zzl())) {
                    zza3.zzg(zzb2.zzl());
                }
                if (zzb2.zzm() != -2147483648L) {
                    zza3.zzh((int) zzb2.zzm());
                }
                zza3.zzf(zzb2.zzo()).zzk(zzb2.zzq());
                if (!zznt.zzb() || !zzs().zze(zzb2.zzc(), zzat.zzbi)) {
                    if (!TextUtils.isEmpty(zzb2.zze())) {
                        zza3.zzk(zzb2.zze());
                    } else if (!TextUtils.isEmpty(zzb2.zzf())) {
                        zza3.zzo(zzb2.zzf());
                    }
                } else if (!TextUtils.isEmpty(zzb2.zze())) {
                    zza3.zzk(zzb2.zze());
                } else if (!TextUtils.isEmpty(zzb2.zzg())) {
                    zza3.zzp(zzb2.zzg());
                } else if (!TextUtils.isEmpty(zzb2.zzf())) {
                    zza3.zzo(zzb2.zzf());
                }
                zzad zza4 = this.zza.zza(str2);
                zza3.zzh(zzb2.zzp());
                if (this.zzy.zzaa() && zzs().zzh(zza3.zzj())) {
                    if (!zzmj.zzb() || !zzs().zza(zzat.zzci)) {
                        zza3.zzj();
                        if (!TextUtils.isEmpty((CharSequence) null)) {
                            zza3.zzn((String) null);
                        }
                    } else if (zza4.zzc() && !TextUtils.isEmpty((CharSequence) null)) {
                        zza3.zzn((String) null);
                    }
                }
                if (zzmj.zzb() && zzs().zza(zzat.zzci)) {
                    zza3.zzq(zza4.zza());
                }
                if (!zzmj.zzb() || !zzs().zza(zzat.zzci) || zza4.zzc()) {
                    Pair<String, Boolean> zza5 = zzf().zza(zzb2.zzc(), zza4);
                    if (zzb2.zzaf() && zza5 != null && !TextUtils.isEmpty((CharSequence) zza5.first)) {
                        try {
                            zza3.zzh(zza((String) zza5.first, Long.toString(zzar2.zzd)));
                            if (zza5.second != null) {
                                zza3.zza(((Boolean) zza5.second).booleanValue());
                            }
                        } catch (SecurityException e) {
                            zzq().zzv().zza("Resettable device id encryption failed", e.getMessage());
                            return new byte[0];
                        } finally {
                            zzi().zzg();
                        }
                    }
                }
                zzk().zzaa();
                zzcd.zzg.zza zzc = zza3.zzc(Build.MODEL);
                zzk().zzaa();
                zzc.zzb(Build.VERSION.RELEASE).zzf((int) zzk().zze()).zzd(zzk().zzf());
                try {
                    if (!zzmj.zzb() || !zzs().zza(zzat.zzci) || zza4.zze()) {
                        zza3.zzi(zza(zzb2.zzd(), Long.toString(zzar2.zzd)));
                    }
                    if (!TextUtils.isEmpty(zzb2.zzi())) {
                        zza3.zzl(zzb2.zzi());
                    }
                    String zzc2 = zzb2.zzc();
                    List<zzkt> zza6 = zzi().zza(zzc2);
                    Iterator<zzkt> it = zza6.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            zzkt = null;
                            break;
                        }
                        zzkt = it.next();
                        if ("_lte".equals(zzkt.zzc)) {
                            break;
                        }
                    }
                    if (zzkt == null || zzkt.zze == null) {
                        zzkt zzkt2 = new zzkt(zzc2, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_lte", zzl().currentTimeMillis(), 0L);
                        zza6.add(zzkt2);
                        zzi().zza(zzkt2);
                    }
                    zzks f_ = f_();
                    f_.zzq().zzw().zza("Checking account type status for ad personalization signals");
                    if (f_.zzk().zzi()) {
                        String zzc3 = zzb2.zzc();
                        if (zzb2.zzaf() && f_.zzj().zze(zzc3)) {
                            f_.zzq().zzv().zza("Turning off ad personalization due to account type");
                            Iterator<zzkt> it2 = zza6.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                } else if ("_npa".equals(it2.next().zzc)) {
                                    it2.remove();
                                    break;
                                }
                            }
                            zza6.add(new zzkt(zzc3, DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_npa", f_.zzl().currentTimeMillis(), 1L));
                        }
                    }
                    zzcd.zzk[] zzkArr = new zzcd.zzk[zza6.size()];
                    for (int i = 0; i < zza6.size(); i++) {
                        zzcd.zzk.zza zza7 = zzcd.zzk.zzj().zza(zza6.get(i).zzc).zza(zza6.get(i).zzd);
                        f_().zza(zza7, zza6.get(i).zze);
                        zzkArr[i] = (zzcd.zzk) ((zzhz) zza7.zzz());
                    }
                    zza3.zzb((Iterable<? extends zzcd.zzk>) Arrays.asList(zzkArr));
                    if (!zzny.zzb() || !zzs().zza(zzat.zzbz) || !zzs().zza(zzat.zzca)) {
                        bundle = zzar2.zzb.zzb();
                    } else {
                        zzev zza8 = zzev.zza(zzar);
                        zzo().zza(zza8.zzb, zzi().zzi(str2));
                        zzo().zza(zza8, zzs().zza(str2));
                        bundle = zza8.zzb;
                    }
                    Bundle bundle3 = bundle;
                    bundle3.putLong("_c", 1);
                    zzq().zzv().zza("Marking in-app purchase as real-time");
                    bundle3.putLong("_r", 1);
                    bundle3.putString("_o", zzar2.zzc);
                    if (zzo().zze(zza3.zzj())) {
                        zzo().zza(bundle3, "_dbg", (Object) 1L);
                        zzo().zza(bundle3, "_r", (Object) 1L);
                    }
                    zzan zza9 = zzi().zza(str2, zzar2.zza);
                    if (zza9 == null) {
                        zzf = zzb2;
                        zza2 = zza3;
                        zza = zzb;
                        bundle2 = bundle3;
                        bArr = null;
                        zzan = new zzan(str, zzar2.zza, 0, 0, zzar2.zzd, 0, (Long) null, (Long) null, (Long) null, (Boolean) null);
                        j = 0;
                    } else {
                        zzf = zzb2;
                        zza2 = zza3;
                        zza = zzb;
                        bundle2 = bundle3;
                        bArr = null;
                        j = zza9.zzf;
                        zzan = zza9.zza(zzar2.zzd);
                    }
                    zzi().zza(zzan);
                    zzak zzak = new zzak(this.zzy, zzar2.zzc, str, zzar2.zza, zzar2.zzd, j, bundle2);
                    zzcd.zzc.zza zzb3 = zzcd.zzc.zzj().zza(zzak.zzc).zza(zzak.zzb).zzb(zzak.zzd);
                    Iterator<String> it3 = zzak.zze.iterator();
                    while (it3.hasNext()) {
                        String next = it3.next();
                        zzcd.zze.zza zza10 = zzcd.zze.zzm().zza(next);
                        f_().zza(zza10, zzak.zze.zza(next));
                        zzb3.zza(zza10);
                    }
                    zzcd.zzg.zza zza11 = zza2;
                    zza11.zza(zzb3).zza(zzcd.zzh.zza().zza(zzcd.zzd.zza().zza(zzan.zzc).zza(zzar2.zza)));
                    zza11.zzc((Iterable<? extends zzcd.zza>) zzh().zza(zzf.zzc(), Collections.emptyList(), zza11.zzd(), Long.valueOf(zzb3.zzf()), Long.valueOf(zzb3.zzf())));
                    if (zzb3.zze()) {
                        zza11.zzb(zzb3.zzf()).zzc(zzb3.zzf());
                    }
                    long zzk = zzf.zzk();
                    int i2 = (zzk > 0 ? 1 : (zzk == 0 ? 0 : -1));
                    if (i2 != 0) {
                        zza11.zze(zzk);
                    }
                    long zzj = zzf.zzj();
                    if (zzj != 0) {
                        zza11.zzd(zzj);
                    } else if (i2 != 0) {
                        zza11.zzd(zzk);
                    }
                    zzf.zzv();
                    zza11.zzg((int) zzf.zzs()).zzg(32053).zza(zzl().currentTimeMillis()).zzb(Boolean.TRUE.booleanValue());
                    zzcd.zzf.zza zza12 = zza;
                    zza12.zza(zza11);
                    zzf zzf2 = zzf;
                    zzf2.zza(zza11.zzf());
                    zzf2.zzb(zza11.zzg());
                    zzi().zza(zzf2);
                    zzi().b_();
                    try {
                        return f_().zzc(((zzcd.zzf) ((zzhz) zza12.zzz())).zzbk());
                    } catch (IOException e2) {
                        zzq().zze().zza("Data loss. Failed to bundle and serialize. appId", zzer.zza(str), e2);
                        return bArr;
                    }
                } catch (SecurityException e3) {
                    zzq().zzv().zza("app instance id encryption failed", e3.getMessage());
                    byte[] bArr4 = new byte[0];
                    zzi().zzg();
                    return bArr4;
                }
            }
        } else {
            zzq().zzv().zza("Generating a payload for this event is not available. package_name, event_name", str2, zzar2.zza);
            return null;
        }
    }

    private static String zza(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }
}
