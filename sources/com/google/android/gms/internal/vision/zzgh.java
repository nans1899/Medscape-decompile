package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzge;
import com.google.android.gms.internal.vision.zzgh;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzgh<MessageType extends zzge<MessageType, BuilderType>, BuilderType extends zzgh<MessageType, BuilderType>> implements zzjm {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    public abstract BuilderType zza(zzhe zzhe, zzho zzho) throws IOException;

    /* renamed from: zzeh */
    public abstract BuilderType clone();

    public BuilderType zza(byte[] bArr, int i, int i2, zzho zzho) throws zzin {
        try {
            zzhe zza = zzhe.zza(bArr, 0, i2, false);
            zza(zza, zzho);
            zza.zzax(0);
            return this;
        } catch (zzin e) {
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

    public final /* synthetic */ zzjm zza(zzjn zzjn) {
        if (zzgx().getClass().isInstance(zzjn)) {
            return zza((zzge) zzjn);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
