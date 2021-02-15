package com.google.android.gms.ads.nonagon.transaction.omid;

import com.facebook.share.internal.MessengerShareContentUtility;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public class OmidSettings {
    private final JSONObject zzhfl;

    public OmidSettings(JSONObject jSONObject) {
        this.zzhfl = jSONObject;
    }

    public OmidMediaType getMediaType() {
        int optInt = this.zzhfl.optInt(MessengerShareContentUtility.MEDIA_TYPE, -1);
        if (optInt == 0) {
            return OmidMediaType.DISPLAY;
        }
        if (optInt != 1) {
            return OmidMediaType.UNKNOWN;
        }
        return OmidMediaType.VIDEO;
    }

    public String getVideoEventsOwner() {
        if (zza.zzhfn[getMediaType().ordinal()] != 1) {
            return "javascript";
        }
        return null;
    }
}
