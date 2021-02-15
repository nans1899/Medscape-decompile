package com.google.android.gms.internal.icing;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.appindexing.AppIndexApi;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.icing.zzal;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.zip.CRC32;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class zzw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzw> CREATOR = new zzy();
    private final zzi zzaj;
    private final long zzak;
    private int zzal;
    private final String zzam;
    private final zzh zzan;
    private final boolean zzao;
    private int zzap;
    private int zzaq;
    private final String zzar;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public zzw(String str, Intent intent, String str2, Uri uri, String str3, List<AppIndexApi.AppIndexingLink> list, int i) {
        this(zza(str, intent), System.currentTimeMillis(), 0, (String) null, zza(intent, str2, uri, (String) null, list).zzb(), false, -1, 1, (String) null);
        Intent intent2 = intent;
        String str4 = str2;
        Uri uri2 = uri;
    }

    zzw(zzi zzi, long j, int i, String str, zzh zzh, boolean z, int i2, int i3, String str2) {
        this.zzaj = zzi;
        this.zzak = j;
        this.zzal = i;
        this.zzam = str;
        this.zzan = zzh;
        this.zzao = z;
        this.zzap = i2;
        this.zzaq = i3;
        this.zzar = str2;
    }

    public static zzi zza(String str, Intent intent) {
        return new zzi(str, "", zza(intent));
    }

    private static zzk zza(String str, String str2) {
        return new zzk(str2, new zzs(str).zzb(true).zzc(), str);
    }

    private static String zza(Intent intent) {
        String uri = intent.toUri(1);
        CRC32 crc32 = new CRC32();
        try {
            crc32.update(uri.getBytes("UTF-8"));
            return Long.toHexString(crc32.getValue());
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static zzg zza(Intent intent, String str, Uri uri, String str2, List<AppIndexApi.AppIndexingLink> list) {
        String string;
        zzg zzg = new zzg();
        zzg.zza(new zzk(str, new zzs("title").zzc(true).zzd("name").zzc(), "text1"));
        if (uri != null) {
            zzg.zza(new zzk(uri.toString(), new zzs(MessengerShareContentUtility.BUTTON_URL_TYPE).zzb(true).zzd("url").zzc()));
        }
        if (list != null) {
            zzal.zza.C0031zza zzf = zzal.zza.zzf();
            int size = list.size();
            zzal.zza.zzb[] zzbArr = new zzal.zza.zzb[size];
            for (int i = 0; i < size; i++) {
                zzal.zza.zzb.C0032zza zzh = zzal.zza.zzb.zzh();
                AppIndexApi.AppIndexingLink appIndexingLink = list.get(i);
                zzh.zze(appIndexingLink.appIndexingUrl.toString()).zzd(appIndexingLink.viewId);
                if (appIndexingLink.webUrl != null) {
                    zzh.zzf(appIndexingLink.webUrl.toString());
                }
                zzbArr[i] = (zzal.zza.zzb) ((zzdx) zzh.zzbx());
            }
            zzf.zza(Arrays.asList(zzbArr));
            zzg.zza(new zzk(((zzal.zza) ((zzdx) zzf.zzbx())).toByteArray(), new zzs("outlinks").zzb(true).zzd(".private:outLinks").zzc("blob").zzc()));
        }
        String action = intent.getAction();
        if (action != null) {
            zzg.zza(zza("intent_action", action));
        }
        String dataString = intent.getDataString();
        if (dataString != null) {
            zzg.zza(zza("intent_data", dataString));
        }
        ComponentName component = intent.getComponent();
        if (component != null) {
            zzg.zza(zza("intent_activity", component.getClassName()));
        }
        Bundle extras = intent.getExtras();
        if (!(extras == null || (string = extras.getString("intent_extra_data_key")) == null)) {
            zzg.zza(zza("intent_extra_data", string));
        }
        return zzg.zza(str2).zza(true);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzaj, i, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzak);
        SafeParcelWriter.writeInt(parcel, 3, this.zzal);
        SafeParcelWriter.writeString(parcel, 4, this.zzam, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzan, i, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzao);
        SafeParcelWriter.writeInt(parcel, 7, this.zzap);
        SafeParcelWriter.writeInt(parcel, 8, this.zzaq);
        SafeParcelWriter.writeString(parcel, 9, this.zzar, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String toString() {
        return String.format(Locale.US, "UsageInfo[documentId=%s, timestamp=%d, usageType=%d, status=%d]", new Object[]{this.zzaj, Long.valueOf(this.zzak), Integer.valueOf(this.zzal), Integer.valueOf(this.zzaq)});
    }
}
