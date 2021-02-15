package com.google.android.gms.internal.icing;

import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzcd<T> implements zzcc<T> {
    @NullableDecl
    private T value;
    private volatile zzcc<T> zzdw;
    private volatile boolean zzdx;

    zzcd(zzcc<T> zzcc) {
        this.zzdw = (zzcc) zzca.checkNotNull(zzcc);
    }

    public final T get() {
        if (!this.zzdx) {
            synchronized (this) {
                if (!this.zzdx) {
                    T t = this.zzdw.get();
                    this.value = t;
                    this.zzdx = true;
                    this.zzdw = null;
                    return t;
                }
            }
        }
        return this.value;
    }

    public final String toString() {
        Object obj = this.zzdw;
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