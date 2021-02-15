package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.internal.measurement.zzmj;
import com.google.android.gms.internal.measurement.zzny;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/* compiled from: com.google.android.gms:play-services-measurement@@17.6.0 */
public final class zzfw extends zzei {
    /* access modifiers changed from: private */
    public final zzki zza;
    private Boolean zzb;
    private String zzc;

    public zzfw(zzki zzki) {
        this(zzki, (String) null);
    }

    private zzfw(zzki zzki, String str) {
        Preconditions.checkNotNull(zzki);
        this.zza = zzki;
        this.zzc = null;
    }

    public final void zzb(zzn zzn) {
        zzb(zzn, false);
        zza((Runnable) new zzfy(this, zzn));
    }

    public final void zze(zzn zzn) {
        if (zzmj.zzb() && this.zza.zzb().zza(zzat.zzci)) {
            Preconditions.checkNotEmpty(zzn.zza);
            Preconditions.checkNotNull(zzn.zzw);
            zzgg zzgg = new zzgg(this, zzn);
            Preconditions.checkNotNull(zzgg);
            if (this.zza.zzp().zzf()) {
                zzgg.run();
            } else {
                this.zza.zzp().zzb((Runnable) zzgg);
            }
        }
    }

    public final void zza(zzar zzar, zzn zzn) {
        Preconditions.checkNotNull(zzar);
        zzb(zzn, false);
        zza((Runnable) new zzgj(this, zzar, zzn));
    }

    /* access modifiers changed from: package-private */
    public final zzar zzb(zzar zzar, zzn zzn) {
        boolean z = false;
        if (!(!Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN.equals(zzar.zza) || zzar.zzb == null || zzar.zzb.zza() == 0)) {
            String zzd = zzar.zzb.zzd("_cis");
            if ("referrer broadcast".equals(zzd) || "referrer API".equals(zzd)) {
                z = true;
            }
        }
        if (!z) {
            return zzar;
        }
        this.zza.zzq().zzu().zza("Event has been filtered ", zzar.toString());
        return new zzar("_cmpx", zzar.zzb, zzar.zzc, zzar.zzd);
    }

    public final void zza(zzar zzar, String str, String str2) {
        Preconditions.checkNotNull(zzar);
        Preconditions.checkNotEmpty(str);
        zza(str, true);
        zza((Runnable) new zzgi(this, zzar, str));
    }

    public final byte[] zza(zzar zzar, String str) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzar);
        zza(str, true);
        this.zza.zzq().zzv().zza("Log and bundle. event", this.zza.zzj().zza(zzar.zza));
        long nanoTime = this.zza.zzl().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.zza.zzp().zzb(new zzgl(this, zzar, str)).get();
            if (bArr == null) {
                this.zza.zzq().zze().zza("Log and bundle returned null. appId", zzer.zza(str));
                bArr = new byte[0];
            }
            this.zza.zzq().zzv().zza("Log and bundle processed. event, size, time_ms", this.zza.zzj().zza(zzar.zza), Integer.valueOf(bArr.length), Long.valueOf((this.zza.zzl().nanoTime() / 1000000) - nanoTime));
            return bArr;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzq().zze().zza("Failed to log and bundle. appId, event, error", zzer.zza(str), this.zza.zzj().zza(zzar.zza), e);
            return null;
        }
    }

    public final void zza(zzkr zzkr, zzn zzn) {
        Preconditions.checkNotNull(zzkr);
        zzb(zzn, false);
        zza((Runnable) new zzgk(this, zzkr, zzn));
    }

    public final List<zzkr> zza(zzn zzn, boolean z) {
        zzb(zzn, false);
        try {
            List<zzkt> list = (List) this.zza.zzp().zza(new zzgn(this, zzn)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkt zzkt : list) {
                if (z || !zzkw.zzd(zzkt.zzc)) {
                    arrayList.add(new zzkr(zzkt));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzq().zze().zza("Failed to get user properties. appId", zzer.zza(zzn.zza), e);
            return null;
        }
    }

    public final void zza(zzn zzn) {
        zzb(zzn, false);
        zza((Runnable) new zzgm(this, zzn));
    }

    private final void zzb(zzn zzn, boolean z) {
        Preconditions.checkNotNull(zzn);
        zza(zzn.zza, false);
        this.zza.zzk().zza(zzn.zzb, zzn.zzr, zzn.zzv);
    }

    private final void zza(String str, boolean z) {
        boolean z2;
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                try {
                    if (this.zzb == null) {
                        if (!"com.google.android.gms".equals(this.zzc) && !UidVerifier.isGooglePlayServicesUid(this.zza.zzm(), Binder.getCallingUid())) {
                            if (!GoogleSignatureVerifier.getInstance(this.zza.zzm()).isUidGoogleSigned(Binder.getCallingUid())) {
                                z2 = false;
                                this.zzb = Boolean.valueOf(z2);
                            }
                        }
                        z2 = true;
                        this.zzb = Boolean.valueOf(z2);
                    }
                    if (this.zzb.booleanValue()) {
                        return;
                    }
                } catch (SecurityException e) {
                    this.zza.zzq().zze().zza("Measurement Service called with invalid calling package. appId", zzer.zza(str));
                    throw e;
                }
            }
            if (this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zzm(), Binder.getCallingUid(), str)) {
                this.zzc = str;
            }
            if (!str.equals(this.zzc)) {
                throw new SecurityException(String.format("Unknown calling package name '%s'.", new Object[]{str}));
            }
            return;
        }
        this.zza.zzq().zze().zza("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }

    public final void zza(long j, String str, String str2, String str3) {
        zza((Runnable) new zzgp(this, str2, str3, str, j));
    }

    public final void zza(Bundle bundle, zzn zzn) {
        if (zzny.zzb() && this.zza.zzb().zza(zzat.zzbz)) {
            zzb(zzn, false);
            zza((Runnable) new zzfz(this, zzn, bundle));
        }
    }

    public final String zzc(zzn zzn) {
        zzb(zzn, false);
        return this.zza.zzd(zzn);
    }

    public final void zza(zzw zzw, zzn zzn) {
        Preconditions.checkNotNull(zzw);
        Preconditions.checkNotNull(zzw.zzc);
        zzb(zzn, false);
        zzw zzw2 = new zzw(zzw);
        zzw2.zza = zzn.zza;
        zza((Runnable) new zzgb(this, zzw2, zzn));
    }

    public final void zza(zzw zzw) {
        Preconditions.checkNotNull(zzw);
        Preconditions.checkNotNull(zzw.zzc);
        zza(zzw.zza, true);
        zza((Runnable) new zzga(this, new zzw(zzw)));
    }

    public final List<zzkr> zza(String str, String str2, boolean z, zzn zzn) {
        zzb(zzn, false);
        try {
            List<zzkt> list = (List) this.zza.zzp().zza(new zzgd(this, zzn, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkt zzkt : list) {
                if (z || !zzkw.zzd(zzkt.zzc)) {
                    arrayList.add(new zzkr(zzkt));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzq().zze().zza("Failed to query user properties. appId", zzer.zza(zzn.zza), e);
            return Collections.emptyList();
        }
    }

    public final List<zzkr> zza(String str, String str2, String str3, boolean z) {
        zza(str, true);
        try {
            List<zzkt> list = (List) this.zza.zzp().zza(new zzgc(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (zzkt zzkt : list) {
                if (z || !zzkw.zzd(zzkt.zzc)) {
                    arrayList.add(new zzkr(zzkt));
                }
            }
            return arrayList;
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzq().zze().zza("Failed to get user properties as. appId", zzer.zza(str), e);
            return Collections.emptyList();
        }
    }

    public final List<zzw> zza(String str, String str2, zzn zzn) {
        zzb(zzn, false);
        try {
            return (List) this.zza.zzp().zza(new zzgf(this, zzn, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzq().zze().zza("Failed to get conditional user properties", e);
            return Collections.emptyList();
        }
    }

    public final List<zzw> zza(String str, String str2, String str3) {
        zza(str, true);
        try {
            return (List) this.zza.zzp().zza(new zzge(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.zza.zzq().zze().zza("Failed to get conditional user properties as", e);
            return Collections.emptyList();
        }
    }

    public final void zzd(zzn zzn) {
        zza(zzn.zza, false);
        zza((Runnable) new zzgh(this, zzn));
    }

    private final void zza(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        if (this.zza.zzp().zzf()) {
            runnable.run();
        } else {
            this.zza.zzp().zza(runnable);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzn zzn, Bundle bundle) {
        this.zza.zze().zza(zzn.zza, bundle);
    }
}
