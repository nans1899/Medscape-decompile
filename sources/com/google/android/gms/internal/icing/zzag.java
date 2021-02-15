package com.google.android.gms.internal.icing;

import android.accounts.Account;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.common.internal.AccountType;
import com.google.android.gms.internal.icing.zzhm;
import com.medscape.android.slideshow.SlideshowPageFragment;
import java.util.List;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzag {
    public static zzw zza(Action action, long j, String str, int i) {
        int i2;
        Bundle bundle = new Bundle();
        bundle.putAll(action.zze());
        Bundle bundle2 = bundle.getBundle(SlideshowPageFragment.ARG_OBJECT);
        Uri parse = bundle2.containsKey("id") ? Uri.parse(bundle2.getString("id")) : null;
        String string = bundle2.getString("name");
        String string2 = bundle2.getString("type");
        Intent zza = zzaj.zza(str, Uri.parse(bundle2.getString("url")));
        zzg zza2 = zzw.zza(zza, string, parse, string2, (List<AppIndexApi.AppIndexingLink>) null);
        if (bundle.containsKey(".private:ssbContext")) {
            zza2.zza(zzk.zza(bundle.getByteArray(".private:ssbContext")));
            bundle.remove(".private:ssbContext");
        }
        if (bundle.containsKey(".private:accountName")) {
            zza2.zza(new Account(bundle.getString(".private:accountName"), AccountType.GOOGLE));
            bundle.remove(".private:accountName");
        }
        boolean z = false;
        if (!bundle.containsKey(".private:isContextOnly") || !bundle.getBoolean(".private:isContextOnly")) {
            i2 = 0;
        } else {
            i2 = 4;
            bundle.remove(".private:isContextOnly");
        }
        if (bundle.containsKey(".private:isDeviceOnly")) {
            z = bundle.getBoolean(".private:isDeviceOnly", false);
            bundle.remove(".private:isDeviceOnly");
        }
        zza2.zza(new zzk(zza(bundle).toByteArray(), new zzs(".private:action").zzb(true).zzd(".private:action").zzc("blob").zzc()));
        return new zzz().zza(zzw.zza(str, zza)).zza(j).zzb(i2).zza(zza2.zzb()).zzd(z).zzc(i).zzd();
    }

    private static zzhm.zzb zza(Bundle bundle) {
        zzhm.zzb.zza zzee = zzhm.zzb.zzee();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof String) {
                zzee.zzb((zzhm.zza) ((zzdx) zzhm.zza.zzec().zzu(str).zzb((zzhm.zzc) ((zzdx) zzhm.zzc.zzeg().zzx((String) obj).zzbx())).zzbx()));
            } else if (obj instanceof Bundle) {
                zzee.zzb((zzhm.zza) ((zzdx) zzhm.zza.zzec().zzu(str).zzb((zzhm.zzc) ((zzdx) zzhm.zzc.zzeg().zzb(zza((Bundle) obj)).zzbx())).zzbx()));
            } else {
                int i = 0;
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    int length = strArr.length;
                    while (i < length) {
                        String str2 = strArr[i];
                        if (str2 != null) {
                            zzee.zzb((zzhm.zza) ((zzdx) zzhm.zza.zzec().zzu(str).zzb((zzhm.zzc) ((zzdx) zzhm.zzc.zzeg().zzx(str2).zzbx())).zzbx()));
                        }
                        i++;
                    }
                } else if (obj instanceof Bundle[]) {
                    Bundle[] bundleArr = (Bundle[]) obj;
                    int length2 = bundleArr.length;
                    while (i < length2) {
                        Bundle bundle2 = bundleArr[i];
                        if (bundle2 != null) {
                            zzee.zzb((zzhm.zza) ((zzdx) zzhm.zza.zzec().zzu(str).zzb((zzhm.zzc) ((zzdx) zzhm.zzc.zzeg().zzb(zza(bundle2)).zzbx())).zzbx()));
                        }
                        i++;
                    }
                } else if (obj instanceof Boolean) {
                    zzee.zzb((zzhm.zza) ((zzdx) zzhm.zza.zzec().zzu(str).zzb((zzhm.zzc) ((zzdx) zzhm.zzc.zzeg().zzj(((Boolean) obj).booleanValue()).zzbx())).zzbx()));
                } else {
                    String valueOf = String.valueOf(obj);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 19);
                    sb.append("Unsupported value: ");
                    sb.append(valueOf);
                    Log.e("SearchIndex", sb.toString());
                }
            }
        }
        if (bundle.containsKey("type")) {
            zzee.zzw(bundle.getString("type"));
        }
        return (zzhm.zzb) ((zzdx) zzee.zzbx());
    }
}
