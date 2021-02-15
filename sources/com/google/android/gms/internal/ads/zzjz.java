package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzmc;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzjz {
    private static final zzmk zzaox = new zzkc();
    private static final Pattern zzaoy = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    public int zzahp = -1;
    public int zzahq = -1;

    public final boolean zzb(zzmc zzmc) {
        for (int i = 0; i < zzmc.length(); i++) {
            zzmc.zza zzba = zzmc.zzba(i);
            if (zzba instanceof zzmi) {
                zzmi zzmi = (zzmi) zzba;
                if (zzb(zzmi.description, zzmi.text)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean zzb(String str, String str2) {
        if (!"iTunSMPB".equals(str)) {
            return false;
        }
        Matcher matcher = zzaoy.matcher(str2);
        if (matcher.find()) {
            try {
                int parseInt = Integer.parseInt(matcher.group(1), 16);
                int parseInt2 = Integer.parseInt(matcher.group(2), 16);
                if (parseInt > 0 || parseInt2 > 0) {
                    this.zzahp = parseInt;
                    this.zzahq = parseInt2;
                    return true;
                }
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    public final boolean zzgs() {
        return (this.zzahp == -1 || this.zzahq == -1) ? false : true;
    }
}
