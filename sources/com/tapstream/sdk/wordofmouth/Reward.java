package com.tapstream.sdk.wordofmouth;

import com.tapstream.sdk.DelegatedJSONObject;
import org.json.JSONObject;

public class Reward extends DelegatedJSONObject {
    public static Reward fromApiResponse(JSONObject jSONObject) {
        return new Reward(jSONObject);
    }

    private Reward(JSONObject jSONObject) {
        super(jSONObject);
    }

    public int getOfferId() {
        return getOrDefault("offer_id", -1);
    }

    public int getInstallCount() {
        return getOrDefault("installs", 0);
    }

    public String getInsertionPoint() {
        return getOrDefault("insertion_point", "");
    }

    public int getMinimumInstalls() {
        return getOrDefault("reward_minimum_installs", 1);
    }

    public String getRewardSku() {
        return getOrDefault("reward_sku", "");
    }

    public String toString() {
        return "Reward('" + getRewardSku() + "', " + getInstallCount() + "/" + getMinimumInstalls() + " installs)";
    }
}
