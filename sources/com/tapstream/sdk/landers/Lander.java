package com.tapstream.sdk.landers;

import com.tapstream.sdk.DelegatedJSONObject;
import org.json.JSONObject;

public class Lander extends DelegatedJSONObject {
    public static Lander fromApiResponse(JSONObject jSONObject) {
        return new Lander(jSONObject);
    }

    private Lander(JSONObject jSONObject) {
        super(jSONObject);
    }

    public int getId() {
        return getOrDefault("id", -1);
    }

    public String getMarkup() {
        return getOrDefault("markup", "");
    }

    public String getUrl() {
        return getOrDefault("url", (String) null);
    }
}
