package com.medscape.android.ads.proclivity;

import com.appboy.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProclivityParser {
    public static List<ProclivityDataModel> parseProclivityData(JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONArray jSONArray;
        ArrayList arrayList = new ArrayList();
        if (jSONObject != null && jSONObject.length() > 0) {
            try {
                JSONObject jSONObject3 = jSONObject.getJSONObject("data");
                if (jSONObject3 != null && jSONObject3.length() > 0 && (jSONObject2 = jSONObject3.getJSONObject("userSegVars")) != null && jSONObject2.length() > 0 && (jSONArray = jSONObject2.getJSONArray("pbr")) != null && jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        if (optJSONObject != null && optJSONObject.length() > 0) {
                            ProclivityDataModel proclivityDataModel = new ProclivityDataModel();
                            proclivityDataModel.setWidth(optJSONObject.optInt("w"));
                            proclivityDataModel.setHeight(optJSONObject.optInt("h"));
                            proclivityDataModel.setAsid(optJSONObject.optString(Constants.APPBOY_PUSH_CONTENT_KEY));
                            Iterator<String> keys = optJSONObject.keys();
                            HashMap hashMap = new HashMap();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                String str = "";
                                if (!next.equals("w") && !next.equals("h") && !next.equals(Constants.APPBOY_PUSH_CONTENT_KEY) && !next.equals("p")) {
                                    if (!optJSONObject.isNull(next)) {
                                        str = optJSONObject.optString(next);
                                    }
                                    hashMap.put(next, str);
                                }
                            }
                            proclivityDataModel.setOtherData(hashMap);
                            arrayList.add(proclivityDataModel);
                        }
                    }
                }
            } catch (JSONException unused) {
            }
        }
        return arrayList;
    }
}
