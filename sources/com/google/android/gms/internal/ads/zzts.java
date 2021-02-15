package com.google.android.gms.internal.ads;

import android.os.Environment;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.ads.zztu;
import com.google.android.gms.internal.ads.zzua;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzts {
    private final zztx zzbvw;
    private final zzua.zzi.zza zzbvx;
    private final boolean zzbvy;

    public static zzts zzna() {
        return new zzts();
    }

    public zzts(zztx zztx) {
        this.zzbvx = zzua.zzi.zzob();
        this.zzbvw = zztx;
        this.zzbvy = ((Boolean) zzwm.zzpx().zzd(zzabb.zzctw)).booleanValue();
    }

    private zzts() {
        this.zzbvx = zzua.zzi.zzob();
        this.zzbvy = false;
        this.zzbvw = new zztx();
    }

    public final synchronized void zza(zztu.zza.zzb zzb) {
        if (this.zzbvy) {
            if (((Boolean) zzwm.zzpx().zzd(zzabb.zzctx)).booleanValue()) {
                zzc(zzb);
            } else {
                zzb(zzb);
            }
        }
    }

    private final synchronized void zzb(zztu.zza.zzb zzb) {
        this.zzbvx.zznx().zza((Iterable<? extends Long>) zznb());
        this.zzbvw.zzf(((zzua.zzi) ((zzejz) this.zzbvx.zzbgt())).toByteArray()).zzby(zzb.zzv()).zzdx();
        String valueOf = String.valueOf(Integer.toString(zzb.zzv(), 10));
        zzayp.zzei(valueOf.length() != 0 ? "Logging Event with event code : ".concat(valueOf) : new String("Logging Event with event code : "));
    }

    private final synchronized void zzc(zztu.zza.zzb zzb) {
        FileOutputStream fileOutputStream;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            try {
                fileOutputStream = new FileOutputStream(new File(externalStorageDirectory, "clearcut_events.txt"), true);
                try {
                    fileOutputStream.write(zzd(zzb).getBytes());
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused) {
                        zzayp.zzei("Could not close Clearcut output stream.");
                    }
                } catch (IOException unused2) {
                    zzayp.zzei("Could not write Clearcut to file.");
                    try {
                        fileOutputStream.close();
                    } catch (IOException unused3) {
                        zzayp.zzei("Could not close Clearcut output stream.");
                    }
                }
            } catch (FileNotFoundException unused4) {
                zzayp.zzei("Could not find file for Clearcut");
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused5) {
                    zzayp.zzei("Could not close Clearcut output stream.");
                }
                throw th;
            }
        }
    }

    private final synchronized String zzd(zztu.zza.zzb zzb) {
        return String.format("id=%s,timestamp=%s,event=%s,data=%s\n", new Object[]{this.zzbvx.zznw(), Long.valueOf(zzp.zzky().elapsedRealtime()), Integer.valueOf(zzb.zzv()), Base64.encodeToString(((zzua.zzi) ((zzejz) this.zzbvx.zzbgt())).toByteArray(), 3)});
    }

    public final synchronized void zza(zztr zztr) {
        if (this.zzbvy) {
            try {
                zztr.zza(this.zzbvx);
            } catch (NullPointerException e) {
                zzp.zzkv().zza(e, "AdMobClearcutLogger.modify");
            }
        }
    }

    private static List<Long> zznb() {
        List<String> zzrh = zzabb.zzrh();
        ArrayList arrayList = new ArrayList();
        for (String split : zzrh) {
            for (String valueOf : split.split(",")) {
                try {
                    arrayList.add(Long.valueOf(valueOf));
                } catch (NumberFormatException unused) {
                    zzayp.zzei("Experiment ID is not a number");
                }
            }
        }
        return arrayList;
    }
}
