package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzelj;
import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzead<KeyFormatProtoT extends zzelj, KeyProtoT extends zzelj> {
    private final zzeaf<KeyFormatProtoT, KeyProtoT> zzhuc;

    zzead(zzeaf<KeyFormatProtoT, KeyProtoT> zzeaf) {
        this.zzhuc = zzeaf;
    }

    /* access modifiers changed from: package-private */
    public final KeyProtoT zzp(zzeip zzeip) throws GeneralSecurityException, zzekj {
        KeyFormatProtoT zzq = this.zzhuc.zzq(zzeip);
        this.zzhuc.zzc(zzq);
        return (zzelj) this.zzhuc.zzd(zzq);
    }
}
