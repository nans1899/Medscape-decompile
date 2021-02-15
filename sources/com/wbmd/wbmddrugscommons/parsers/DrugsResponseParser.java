package com.wbmd.wbmddrugscommons.parsers;

import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmddrugscommons.constants.Constants;
import com.wbmd.wbmddrugscommons.model.Drug;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DrugsResponseParser {
    private JSONObject mJsonObject;

    public DrugsResponseParser(JSONObject jSONObject) {
        this.mJsonObject = jSONObject;
    }

    public List<Drug> parse() throws JSONException {
        if (this.mJsonObject == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        if (!this.mJsonObject.has("data")) {
            return null;
        }
        JSONObject jSONObject = this.mJsonObject.getJSONObject("data");
        if (!jSONObject.has("docs")) {
            return null;
        }
        JSONArray jSONArray = jSONObject.getJSONArray("docs");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            Drug drug = new Drug();
            String str = "";
            drug.setId(!StringExtensions.isNullOrEmpty(jSONObject2.getString(Constants.WBMDDrugResponseKeyAZDrugId)) ? jSONObject2.getString(Constants.WBMDDrugResponseKeyAZDrugId) : str);
            drug.setDrugName(!StringExtensions.isNullOrEmpty(jSONObject2.getString("title")) ? jSONObject2.getString("title") : str);
            drug.setMonoId(!StringExtensions.isNullOrEmpty(jSONObject2.getString(Constants.WBMDDrugResponseKeyAZDrugMonoId)) ? jSONObject2.getString(Constants.WBMDDrugResponseKeyAZDrugMonoId) : str);
            drug.setUrl(!StringExtensions.isNullOrEmpty(jSONObject2.getString(Constants.WBMDDrugResponseKeyAZUrlSuffix)) ? jSONObject2.getString(Constants.WBMDDrugResponseKeyAZUrlSuffix) : str);
            if (!StringExtensions.isNullOrEmpty(jSONObject2.getString(Constants.WBMDDrugResponseKeyAZStatusCode))) {
                str = jSONObject2.getString(Constants.WBMDDrugResponseKeyAZStatusCode);
            }
            drug.setStatus(str);
            drug.setIsTop(Boolean.valueOf(jSONObject2.getBoolean(Constants.WBMDDrugResponseKeyAZIsTop)));
            arrayList.add(drug);
        }
        return arrayList;
    }
}
