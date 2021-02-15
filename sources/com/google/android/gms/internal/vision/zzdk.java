package com.google.android.gms.internal.vision;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
final class zzdk<T> implements zzdf<T> {
    @NullableDecl
    private T value;
    private volatile zzdf<T> zzmb;
    private volatile boolean zzmc;

    zzdk(zzdf<T> zzdf) {
        this.zzmb = (zzdf) zzde.checkNotNull(zzdf);
    }

    public final T get() {
        if (!this.zzmc) {
            synchronized (this) {
                if (!this.zzmc) {
                    T t = this.zzmb.get();
                    this.value = t;
                    this.zzmc = true;
                    this.zzmb = null;
                    return t;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        Object obj = this.zzmb;
        if (obj == null) {
            String valueOf = String.valueOf(this.value);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("<supplier that returned ");
            sb.append(valueOf);
            sb.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
            obj = sb.toString();
        }
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(valueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
