package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzd;
import com.google.android.gms.internal.measurement.zzna;
import com.google.firebase.messaging.Constants;
import kotlinx.coroutines.DebugKt;
import net.bytebuddy.description.type.TypeDescription;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
final class zzfk implements Runnable {
    private final /* synthetic */ zzd zza;
    private final /* synthetic */ ServiceConnection zzb;
    private final /* synthetic */ zzfl zzc;

    zzfk(zzfl zzfl, zzd zzd, ServiceConnection serviceConnection) {
        this.zzc = zzfl;
        this.zza = zzd;
        this.zzb = serviceConnection;
    }

    public final void run() {
        zzfi zzfi = this.zzc.zza;
        String zza2 = this.zzc.zzb;
        zzd zzd = this.zza;
        ServiceConnection serviceConnection = this.zzb;
        Bundle zza3 = zzfi.zza(zza2, zzd);
        zzfi.zza.zzp().zzc();
        if (zza3 != null) {
            long j = zza3.getLong("install_begin_timestamp_seconds", 0) * 1000;
            if (j == 0) {
                zzfi.zza.zzq().zzh().zza("Service response is missing Install Referrer install timestamp");
            } else {
                String string = zza3.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzfi.zza.zzq().zze().zza("No referrer defined in Install Referrer response");
                } else {
                    zzfi.zza.zzq().zzw().zza("InstallReferrer API result", string);
                    zzkw zzh = zzfi.zza.zzh();
                    String valueOf = String.valueOf(string);
                    Bundle zza4 = zzh.zza(Uri.parse(valueOf.length() != 0 ? TypeDescription.Generic.OfWildcardType.SYMBOL.concat(valueOf) : new String(TypeDescription.Generic.OfWildcardType.SYMBOL)));
                    if (zza4 == null) {
                        zzfi.zza.zzq().zze().zza("No campaign params defined in Install Referrer result");
                    } else {
                        String string2 = zza4.getString("medium");
                        if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                            long j2 = zza3.getLong("referrer_click_timestamp_seconds", 0) * 1000;
                            if (j2 == 0) {
                                zzfi.zza.zzq().zze().zza("Install Referrer is missing click timestamp for ad campaign");
                            } else {
                                zza4.putLong("click_timestamp", j2);
                            }
                        }
                        if (j == zzfi.zza.zzb().zzi.zza()) {
                            zzfi.zza.zzq().zzw().zza("Install Referrer campaign has already been logged");
                        } else if (!zzna.zzb() || !zzfi.zza.zza().zza(zzat.zzbs) || zzfi.zza.zzaa()) {
                            zzfi.zza.zzb().zzi.zza(j);
                            zzfi.zza.zzq().zzw().zza("Logging Install Referrer campaign from sdk with ", "referrer API");
                            zza4.putString("_cis", "referrer API");
                            zzfi.zza.zzg().zza(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, zza4);
                        }
                    }
                }
            }
        }
        if (serviceConnection != null) {
            ConnectionTracker.getInstance().unbindService(zzfi.zza.zzm(), serviceConnection);
        }
    }
}
