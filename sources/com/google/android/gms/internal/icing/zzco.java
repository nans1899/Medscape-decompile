package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.zzcm;
import com.google.android.gms.internal.icing.zzco;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public abstract class zzco<MessageType extends zzcm<MessageType, BuilderType>, BuilderType extends zzco<MessageType, BuilderType>> implements zzfg {
    /* access modifiers changed from: protected */
    public abstract BuilderType zza(MessageType messagetype);

    /* renamed from: zzag */
    public abstract BuilderType clone();

    public final /* synthetic */ zzfg zza(zzfh zzfh) {
        if (zzbr().getClass().isInstance(zzfh)) {
            return zza((zzcm) zzfh);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
