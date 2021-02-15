package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.dd.plist.ASCIIPropertyListParser;
import com.google.android.gms.ads.internal.zzp;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzabs {
    private final Object lock = new Object();
    boolean zzczm = true;
    private final List<zzabq> zzczn = new LinkedList();
    private final Map<String, String> zzczo = new LinkedHashMap();
    private zzabs zzczp;

    public zzabs(boolean z, String str, String str2) {
        this.zzczo.put("action", str);
        this.zzczo.put(FirebaseAnalytics.Param.AD_FORMAT, str2);
    }

    public final void zzc(zzabs zzabs) {
        synchronized (this.lock) {
            this.zzczp = zzabs;
        }
    }

    public final zzabq zzex(long j) {
        if (!this.zzczm) {
            return null;
        }
        return new zzabq(j, (String) null, (zzabq) null);
    }

    public final boolean zza(zzabq zzabq, long j, String... strArr) {
        synchronized (this.lock) {
            for (String zzabq2 : strArr) {
                this.zzczn.add(new zzabq(j, zzabq2, zzabq));
            }
        }
        return true;
    }

    public final String zzrt() {
        String sb;
        StringBuilder sb2 = new StringBuilder();
        synchronized (this.lock) {
            for (zzabq next : this.zzczn) {
                long time = next.getTime();
                String zzrq = next.zzrq();
                zzabq zzrr = next.zzrr();
                if (zzrr != null && time > 0) {
                    sb2.append(zzrq);
                    sb2.append('.');
                    sb2.append(time - zzrr.getTime());
                    sb2.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
                }
            }
            this.zzczn.clear();
            if (!TextUtils.isEmpty((CharSequence) null)) {
                sb2.append((String) null);
            } else if (sb2.length() > 0) {
                sb2.setLength(sb2.length() - 1);
            }
            sb = sb2.toString();
        }
        return sb;
    }

    public final void zzh(String str, String str2) {
        zzabi zzwn;
        if (this.zzczm && !TextUtils.isEmpty(str2) && (zzwn = zzp.zzkv().zzwn()) != null) {
            synchronized (this.lock) {
                zzabm zzcu = zzwn.zzcu(str);
                Map<String, String> map = this.zzczo;
                map.put(str, zzcu.zzg(map.get(str), str2));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Map<String, String> zzru() {
        synchronized (this.lock) {
            zzabi zzwn = zzp.zzkv().zzwn();
            if (zzwn != null) {
                if (this.zzczp != null) {
                    Map<String, String> zza = zzwn.zza(this.zzczo, this.zzczp.zzru());
                    return zza;
                }
            }
            Map<String, String> map = this.zzczo;
            return map;
        }
    }
}
