package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzelj;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzeas<KeyProtoT extends zzelj, PublicKeyProtoT extends zzelj> extends zzeag<KeyProtoT> {
    private final Class<PublicKeyProtoT> zzhuy;

    @SafeVarargs
    protected zzeas(Class<KeyProtoT> cls, Class<PublicKeyProtoT> cls2, zzeai<?, KeyProtoT>... zzeaiArr) {
        super(cls, zzeaiArr);
        this.zzhuy = cls2;
    }
}
