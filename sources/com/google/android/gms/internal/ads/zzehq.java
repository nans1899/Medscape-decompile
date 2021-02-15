package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Mac;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzehq extends ThreadLocal<Mac> {
    private final /* synthetic */ zzehn zzidq;

    zzehq(zzehn zzehn) {
        this.zzidq = zzehn;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzbdq */
    public final Mac initialValue() {
        try {
            Mac zzhs = zzehb.zzicy.zzhs(this.zzidq.zzidl);
            zzhs.init(this.zzidq.zzidm);
            return zzhs;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }
}
