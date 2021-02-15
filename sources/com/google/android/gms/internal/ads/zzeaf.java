package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzelj;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public abstract class zzeaf<KeyFormatProtoT extends zzelj, KeyT> {
    private final Class<KeyFormatProtoT> zzhuf;

    public zzeaf(Class<KeyFormatProtoT> cls) {
        this.zzhuf = cls;
    }

    public abstract void zzc(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;

    public abstract KeyT zzd(KeyFormatProtoT keyformatprotot) throws GeneralSecurityException;

    public abstract KeyFormatProtoT zzq(zzeip zzeip) throws zzekj;

    public final Class<KeyFormatProtoT> zzaym() {
        return this.zzhuf;
    }
}
