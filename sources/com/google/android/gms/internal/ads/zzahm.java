package com.google.android.gms.internal.ads;

import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzahm implements zzahq<zzbgj> {
    zzahm() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzbgj zzbgj = (zzbgj) obj;
        String str = (String) map.get("action");
        if (OmnitureData.LINK_NAME_VIDEO_PAUSE.equals(str)) {
            zzbgj.zzkn();
        } else if ("resume".equals(str)) {
            zzbgj.zzko();
        }
    }
}
