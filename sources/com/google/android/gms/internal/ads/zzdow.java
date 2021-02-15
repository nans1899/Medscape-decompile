package com.google.android.gms.internal.ads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdow {
    private final Pattern zzhfo;

    public zzdow() {
        Pattern pattern;
        try {
            pattern = Pattern.compile((String) zzwm.zzpx().zzd(zzabb.zzcxo));
        } catch (PatternSyntaxException unused) {
            pattern = null;
        }
        this.zzhfo = pattern;
    }

    public final String zzgu(String str) {
        Pattern pattern = this.zzhfo;
        if (!(pattern == null || str == null)) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                return matcher.group();
            }
        }
        return null;
    }
}
