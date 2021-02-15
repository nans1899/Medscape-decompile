package io.branch.referral;

import android.text.TextUtils;
import io.branch.referral.Defines;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class BranchLinkData extends JSONObject {
    private String alias;
    private String campaign;
    private String channel;
    private int duration;
    private String feature;
    private JSONObject params;
    private String stage;
    private Collection<String> tags;
    private int type;

    public void putTags(Collection<String> collection) throws JSONException {
        if (collection != null) {
            this.tags = collection;
            JSONArray jSONArray = new JSONArray();
            for (String put : collection) {
                jSONArray.put(put);
            }
            put(Defines.LinkParam.Tags.getKey(), jSONArray);
        }
    }

    public Collection<String> getTags() {
        return this.tags;
    }

    public void putAlias(String str) throws JSONException {
        if (str != null) {
            this.alias = str;
            put(Defines.LinkParam.Alias.getKey(), str);
        }
    }

    public String getAlias() {
        return this.alias;
    }

    public void putType(int i) throws JSONException {
        if (i != 0) {
            this.type = i;
            put(Defines.LinkParam.Type.getKey(), i);
        }
    }

    public int getType() {
        return this.type;
    }

    public void putDuration(int i) throws JSONException {
        if (i > 0) {
            this.duration = i;
            put(Defines.LinkParam.Duration.getKey(), i);
        }
    }

    public int getDuration() {
        return this.duration;
    }

    public void putChannel(String str) throws JSONException {
        if (str != null) {
            this.channel = str;
            put(Defines.LinkParam.Channel.getKey(), str);
        }
    }

    public String getChannel() {
        return this.channel;
    }

    public void putFeature(String str) throws JSONException {
        if (str != null) {
            this.feature = str;
            put(Defines.LinkParam.Feature.getKey(), str);
        }
    }

    public String getFeature() {
        return this.feature;
    }

    public void putStage(String str) throws JSONException {
        if (str != null) {
            this.stage = str;
            put(Defines.LinkParam.Stage.getKey(), str);
        }
    }

    public String getStage() {
        return this.stage;
    }

    public void putCampaign(String str) throws JSONException {
        if (str != null) {
            this.campaign = str;
            put(Defines.LinkParam.Campaign.getKey(), str);
        }
    }

    public String getCampaign() {
        return this.campaign;
    }

    public void putParams(JSONObject jSONObject) throws JSONException {
        this.params = jSONObject;
        put(Defines.LinkParam.Data.getKey(), jSONObject);
    }

    public JSONObject getParams() {
        return this.params;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BranchLinkData branchLinkData = (BranchLinkData) obj;
        String str = this.alias;
        if (str == null) {
            if (branchLinkData.alias != null) {
                return false;
            }
        } else if (!str.equals(branchLinkData.alias)) {
            return false;
        }
        String str2 = this.channel;
        if (str2 == null) {
            if (branchLinkData.channel != null) {
                return false;
            }
        } else if (!str2.equals(branchLinkData.channel)) {
            return false;
        }
        String str3 = this.feature;
        if (str3 == null) {
            if (branchLinkData.feature != null) {
                return false;
            }
        } else if (!str3.equals(branchLinkData.feature)) {
            return false;
        }
        JSONObject jSONObject = this.params;
        if (jSONObject == null) {
            if (branchLinkData.params != null) {
                return false;
            }
        } else if (!jSONObject.equals(branchLinkData.params)) {
            return false;
        }
        String str4 = this.stage;
        if (str4 == null) {
            if (branchLinkData.stage != null) {
                return false;
            }
        } else if (!str4.equals(branchLinkData.stage)) {
            return false;
        }
        String str5 = this.campaign;
        if (str5 == null) {
            if (branchLinkData.campaign != null) {
                return false;
            }
        } else if (!str5.equals(branchLinkData.campaign)) {
            return false;
        }
        if (this.type != branchLinkData.type || this.duration != branchLinkData.duration) {
            return false;
        }
        Collection<String> collection = this.tags;
        if (collection == null) {
            if (branchLinkData.tags != null) {
                return false;
            }
        } else if (!collection.toString().equals(branchLinkData.tags.toString())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = (this.type + 19) * 19;
        String str = this.alias;
        int i7 = 0;
        if (str == null) {
            i = 0;
        } else {
            i = str.toLowerCase().hashCode();
        }
        int i8 = (i6 + i) * 19;
        String str2 = this.channel;
        if (str2 == null) {
            i2 = 0;
        } else {
            i2 = str2.toLowerCase().hashCode();
        }
        int i9 = (i8 + i2) * 19;
        String str3 = this.feature;
        if (str3 == null) {
            i3 = 0;
        } else {
            i3 = str3.toLowerCase().hashCode();
        }
        int i10 = (i9 + i3) * 19;
        String str4 = this.stage;
        if (str4 == null) {
            i4 = 0;
        } else {
            i4 = str4.toLowerCase().hashCode();
        }
        int i11 = (i10 + i4) * 19;
        String str5 = this.campaign;
        if (str5 == null) {
            i5 = 0;
        } else {
            i5 = str5.toLowerCase().hashCode();
        }
        int i12 = (i11 + i5) * 19;
        JSONObject jSONObject = this.params;
        if (jSONObject != null) {
            i7 = jSONObject.toString().toLowerCase().hashCode();
        }
        int i13 = ((i12 + i7) * 19) + this.duration;
        Collection<String> collection = this.tags;
        if (collection != null) {
            for (String lowerCase : collection) {
                i13 = (i13 * 19) + lowerCase.toLowerCase().hashCode();
            }
        }
        return i13;
    }

    public JSONObject getLinkDataJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!TextUtils.isEmpty(this.channel)) {
                jSONObject.put("~" + Defines.LinkParam.Channel.getKey(), this.channel);
            }
            if (!TextUtils.isEmpty(this.alias)) {
                jSONObject.put("~" + Defines.LinkParam.Alias.getKey(), this.alias);
            }
            if (!TextUtils.isEmpty(this.feature)) {
                jSONObject.put("~" + Defines.LinkParam.Feature.getKey(), this.feature);
            }
            if (!TextUtils.isEmpty(this.stage)) {
                jSONObject.put("~" + Defines.LinkParam.Stage.getKey(), this.stage);
            }
            if (!TextUtils.isEmpty(this.campaign)) {
                jSONObject.put("~" + Defines.LinkParam.Campaign.getKey(), this.campaign);
            }
            if (has(Defines.LinkParam.Tags.getKey())) {
                jSONObject.put(Defines.LinkParam.Tags.getKey(), getJSONArray(Defines.LinkParam.Tags.getKey()));
            }
            jSONObject.put("~" + Defines.LinkParam.Type.getKey(), this.type);
            jSONObject.put("~" + Defines.LinkParam.Duration.getKey(), this.duration);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
