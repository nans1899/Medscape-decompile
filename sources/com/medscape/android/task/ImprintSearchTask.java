package com.medscape.android.task;

import android.content.Context;
import com.medscape.android.util.JSONParser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImprintSearchTask {
    private static final String[] COUNTRIES = {"Belgium", "France", "Italy", "Germany", "Spain", "Belgium", "France", "Italy", "Germany", "Spain", "Belgium", "France", "Italy", "Germany", "Spain", "Belgium", "France", "Italy", "Germany", "Spain", "Belgium", "France", "Italy", "Germany", "Spain"};
    static final String imprintSearchQueryEndpoint = "https://www.medscape.com/api/quickreflookup/LookupService.ashx?sz=50&s=3&format=json&json=imprint_lookup&q=%s";
    Context mContext;
    String mQuery;
    boolean mTestMode;

    public ImprintSearchTask(Context context, String str, boolean z) {
        this.mContext = context;
        this.mQuery = str;
        this.mTestMode = z;
    }

    public List<String> execute() {
        ArrayList arrayList = new ArrayList();
        try {
            if (this.mQuery != null && !this.mQuery.isEmpty()) {
                String replace = imprintSearchQueryEndpoint.replace("%s", this.mQuery);
                JSONParser jSONParser = new JSONParser();
                new JSONObject();
                int i = 0;
                if (!this.mTestMode) {
                    JSONObject jSONFromUrl = jSONParser.getJSONFromUrl(replace, false, this.mContext);
                    if (jSONFromUrl != null) {
                        JSONArray jSONArray = jSONFromUrl.getJSONArray("types").getJSONObject(0).getJSONArray("references");
                        while (i < jSONArray.length()) {
                            arrayList.add(jSONArray.getJSONObject(i).getString("val"));
                            i++;
                        }
                    }
                } else {
                    String[] strArr = COUNTRIES;
                    int length = strArr.length;
                    while (i < length) {
                        arrayList.add(strArr[i]);
                        i++;
                    }
                }
            }
        } catch (JSONException unused) {
        }
        return arrayList;
    }
}
