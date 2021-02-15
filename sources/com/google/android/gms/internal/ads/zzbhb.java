package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbhb implements zzahq<zzbgj> {
    private final /* synthetic */ zzbgz zzepx;

    zzbhb(zzbgz zzbgz) {
        this.zzepx = zzbgz;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgj zzbgj = (zzbgj) obj;
        if (map != null) {
            String str = (String) map.get(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT);
            if (!TextUtils.isEmpty(str)) {
                try {
                    int parseInt = Integer.parseInt(str);
                    synchronized (this.zzepx) {
                        if (this.zzepx.zzepo != parseInt) {
                            int unused = this.zzepx.zzepo = parseInt;
                            this.zzepx.requestLayout();
                        }
                    }
                } catch (Exception e) {
                    zzayp.zzd("Exception occurred while getting webview content height", e);
                }
            }
        }
    }
}
