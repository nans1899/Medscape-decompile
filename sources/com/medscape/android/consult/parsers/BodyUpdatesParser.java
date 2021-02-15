package com.medscape.android.consult.parsers;

import com.medscape.android.consult.models.BodyUpdates;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, d2 = {"Lcom/medscape/android/consult/parsers/BodyUpdatesParser;", "", "()V", "getConsultBodyUpdateFromCommunityJson", "Lcom/medscape/android/consult/models/BodyUpdates;", "consultBodyUpdateJson", "Lorg/json/JSONObject;", "parseConsultBodyUpdate", "", "jsonArray", "Lorg/json/JSONArray;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BodyUpdatesParser.kt */
public final class BodyUpdatesParser {
    public static final BodyUpdatesParser INSTANCE = new BodyUpdatesParser();

    private BodyUpdatesParser() {
    }

    public final List<BodyUpdates> parseConsultBodyUpdate(JSONArray jSONArray) {
        BodyUpdates consultBodyUpdateFromCommunityJson;
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() > 0) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    Object obj = jSONArray.get(i);
                    if ((obj instanceof JSONObject) && (consultBodyUpdateFromCommunityJson = getConsultBodyUpdateFromCommunityJson((JSONObject) obj)) != null) {
                        arrayList.add(consultBodyUpdateFromCommunityJson);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    private final BodyUpdates getConsultBodyUpdateFromCommunityJson(JSONObject jSONObject) {
        BodyUpdates bodyUpdates = null;
        if (jSONObject == null) {
            return bodyUpdates;
        }
        BodyUpdates bodyUpdates2 = new BodyUpdates();
        bodyUpdates2.setId(jSONObject.optString("Id"));
        bodyUpdates2.setCreateDate(jSONObject.optString("CreateDate"));
        bodyUpdates2.setUpdateDate(jSONObject.optString("UpdateDate"));
        bodyUpdates2.setDisplayDate(jSONObject.optString("DisplayDate"));
        bodyUpdates2.setTitle(jSONObject.optString("Title"));
        bodyUpdates2.setBody(jSONObject.optString("Body"));
        return bodyUpdates2;
    }
}
