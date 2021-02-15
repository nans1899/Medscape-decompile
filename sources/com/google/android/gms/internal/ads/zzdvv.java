package com.google.android.gms.internal.ads;

import com.dd.plist.ASCIIPropertyListParser;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzdvv {
    private final String className;
    private final zzdvy zzhoy;
    private zzdvy zzhoz;
    private boolean zzhpa;

    private zzdvv(String str) {
        zzdvy zzdvy = new zzdvy();
        this.zzhoy = zzdvy;
        this.zzhoz = zzdvy;
        this.zzhpa = false;
        this.className = (String) zzdwd.checkNotNull(str);
    }

    public final zzdvv zzy(@NullableDecl Object obj) {
        zzdvy zzdvy = new zzdvy();
        this.zzhoz.zzhpb = zzdvy;
        this.zzhoz = zzdvy;
        zzdvy.value = obj;
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.className);
        sb.append(ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN);
        zzdvy zzdvy = this.zzhoy.zzhpb;
        String str = "";
        while (zzdvy != null) {
            Object obj = zzdvy.value;
            sb.append(str);
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                String deepToString = Arrays.deepToString(new Object[]{obj});
                sb.append(deepToString, 1, deepToString.length() - 1);
            }
            zzdvy = zzdvy.zzhpb;
            str = ", ";
        }
        sb.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
        return sb.toString();
    }
}
