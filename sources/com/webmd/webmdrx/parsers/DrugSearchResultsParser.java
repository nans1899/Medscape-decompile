package com.webmd.webmdrx.parsers;

import com.wbmd.wbmddrugscommons.constants.Constants;
import com.webmd.webmdrx.models.DrugSearchResult;
import com.webmd.webmdrx.util.StringUtil;
import com.webmd.webmdrx.util.Trace;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class DrugSearchResultsParser {
    private static final String TAG = DrugSearchResultsParser.class.getSimpleName();

    public static List<DrugSearchResult> parseDrugSearchResults(String str) {
        JSONObject optJSONObject;
        JSONArray optJSONArray;
        ArrayList arrayList = null;
        if (!StringUtil.isNotEmpty(str)) {
            return null;
        }
        try {
            JSONObject optJSONObject2 = new JSONObject(str).optJSONObject("data");
            if (optJSONObject2 == null || (optJSONObject = optJSONObject2.optJSONObject("rxsearch")) == null || (optJSONArray = optJSONObject.optJSONArray("data")) == null) {
                return null;
            }
            ArrayList arrayList2 = new ArrayList();
            int i = 0;
            while (i < optJSONArray.length()) {
                try {
                    DrugSearchResult drugSearchResultFromObject = getDrugSearchResultFromObject(optJSONArray.get(i));
                    if (drugSearchResultFromObject != null) {
                        arrayList2.add(drugSearchResultFromObject);
                    }
                    i++;
                } catch (Exception unused) {
                    arrayList = arrayList2;
                    Trace.w(TAG, "Failed to parse server response for searched drug");
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (Exception unused2) {
            Trace.w(TAG, "Failed to parse server response for searched drug");
            return arrayList;
        }
    }

    private static DrugSearchResult getDrugSearchResultFromObject(Object obj) {
        if (!(obj instanceof JSONObject)) {
            return null;
        }
        JSONObject jSONObject = (JSONObject) obj;
        DrugSearchResult drugSearchResult = new DrugSearchResult();
        drugSearchResult.setDrugId(jSONObject.optString("id"));
        drugSearchResult.setDrugName(jSONObject.optString(Constants.WBMDDrugResponseKeyDrugName));
        drugSearchResult.setIsGeneric(jSONObject.optBoolean("is_generic"));
        JSONArray optJSONArray = jSONObject.optJSONArray("other_name");
        if (optJSONArray == null) {
            return drugSearchResult;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                Object obj2 = optJSONArray.get(i);
                if (obj2 instanceof JSONObject) {
                    DrugSearchResult drugSearchResult2 = new DrugSearchResult();
                    drugSearchResult2.setIsGeneric(drugSearchResult.isGeneric());
                    DrugSearchResult drugSearchResultFromJson = getDrugSearchResultFromJson(drugSearchResult2, (JSONObject) obj2);
                    if (drugSearchResultFromJson != null) {
                        List otherNames = drugSearchResult.getOtherNames();
                        if (otherNames == null) {
                            otherNames = new ArrayList();
                        }
                        otherNames.add(drugSearchResultFromJson);
                        drugSearchResult.setOtherNames(otherNames);
                    }
                }
            } catch (Exception unused) {
                Trace.w(TAG, "Failed to parse other names");
            }
        }
        return drugSearchResult;
    }

    private static DrugSearchResult getDrugSearchResultFromJson(DrugSearchResult drugSearchResult, JSONObject jSONObject) {
        if (jSONObject != null) {
            drugSearchResult.setDrugId(jSONObject.optString("id"));
            drugSearchResult.setDrugName(jSONObject.optString("name"));
        }
        return drugSearchResult;
    }
}
