package com.medscape.android.consult.parsers;

import com.medscape.android.Constants;
import com.medscape.android.consult.models.ZimbraConfigResponse;
import org.json.JSONObject;

public class ZimbraConfigParser {
    static String TAG = ZimbraConfigParser.class.getSimpleName();

    public static ZimbraConfigResponse parseZimbraConfigResponse(String str) {
        if (str == null || str.trim().equalsIgnoreCase("")) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            ZimbraConfigResponse zimbraConfigResponse = new ZimbraConfigResponse();
            try {
                zimbraConfigResponse.setForumId(jSONObject.optString(Constants.ZIMBRA_FORUM_ID));
                zimbraConfigResponse.setGroupId(jSONObject.optString(Constants.ZIMBRA_GROUP_ID));
                zimbraConfigResponse.setMediaGalleryId(jSONObject.optString(Constants.ZIMBRA_MEDIA_GALLERY_ID));
                zimbraConfigResponse.setHiddenForumId(jSONObject.optString(Constants.ZIMBRA_MEDICAL_ARTICLES_FORUM_ID));
            } catch (Exception unused) {
            }
            return zimbraConfigResponse;
        } catch (Exception unused2) {
            return null;
        }
    }
}
