package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzbii implements zzahq<zzbgj> {
    private final /* synthetic */ zzbig zzeqt;

    zzbii(zzbig zzbig) {
        this.zzeqt = zzbig;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgj zzbgj = (zzbgj) obj;
        if (map != null) {
            String str = (String) map.get(GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT);
            if (!TextUtils.isEmpty(str)) {
                try {
                    int parseInt = Integer.parseInt(str);
                    synchronized (this.zzeqt) {
                        if (this.zzeqt.zzepo != parseInt) {
                            int unused = this.zzeqt.zzepo = parseInt;
                            this.zzeqt.requestLayout();
                        }
                    }
                } catch (Exception e) {
                    zzayp.zzd("Exception occurred while getting webview content height", e);
                }
            }
        }
    }
}
