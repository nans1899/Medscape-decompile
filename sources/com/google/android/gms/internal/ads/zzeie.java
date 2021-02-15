package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzeie;
import com.google.android.gms.internal.ads.zzeif;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzeie<MessageType extends zzeif<MessageType, BuilderType>, BuilderType extends zzeie<MessageType, BuilderType>> implements zzelm {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    public abstract BuilderType zza(zzeja zzeja, zzejm zzejm) throws IOException;

    /* renamed from: zzbdv */
    public abstract BuilderType clone();

    public BuilderType zza(byte[] bArr, int i, int i2, zzejm zzejm) throws zzekj {
        try {
            zzeja zzb = zzeja.zzb(bArr, 0, i2, false);
            zza(zzb, zzejm);
            zzb.zzfy(0);
            return this;
        } catch (zzekj e) {
            throw e;
        } catch (IOException e2) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 60 + "byte array".length());
            sb.append("Reading ");
            sb.append(name);
            sb.append(" from a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }

    public final /* synthetic */ zzelm zzf(zzelj zzelj) {
        if (zzbgn().getClass().isInstance(zzelj)) {
            return zza((zzeif) zzelj);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
