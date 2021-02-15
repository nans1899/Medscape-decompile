package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzeai<PrimitiveT, KeyT> {
    private final Class<PrimitiveT> zzhuf;

    public zzeai(Class<PrimitiveT> cls) {
        this.zzhuf = cls;
    }

    public abstract PrimitiveT zzag(KeyT keyt) throws GeneralSecurityException;

    /* access modifiers changed from: package-private */
    public final Class<PrimitiveT> zzayk() {
        return this.zzhuf;
    }
}
