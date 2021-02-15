package io.branch.referral.util;

import android.os.Parcel;
import android.os.Parcelable;
import io.branch.referral.Branch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class LinkProperties implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public LinkProperties createFromParcel(Parcel parcel) {
            return new LinkProperties(parcel);
        }

        public LinkProperties[] newArray(int i) {
            return new LinkProperties[i];
        }
    };
    private String alias_;
    private String campaign_;
    private String channel_;
    private final HashMap<String, String> controlParams_;
    private String feature_;
    private int matchDuration_;
    private String stage_;
    private final ArrayList<String> tags_;

    public int describeContents() {
        return 0;
    }

    public LinkProperties() {
        this.tags_ = new ArrayList<>();
        this.feature_ = "Share";
        this.controlParams_ = new HashMap<>();
        this.alias_ = "";
        this.stage_ = "";
        this.matchDuration_ = 0;
        this.channel_ = "";
        this.campaign_ = "";
    }

    public LinkProperties setAlias(String str) {
        this.alias_ = str;
        return this;
    }

    public LinkProperties addTag(String str) {
        this.tags_.add(str);
        return this;
    }

    public LinkProperties addControlParameter(String str, String str2) {
        this.controlParams_.put(str, str2);
        return this;
    }

    public LinkProperties setFeature(String str) {
        this.feature_ = str;
        return this;
    }

    public LinkProperties setDuration(int i) {
        this.matchDuration_ = i;
        return this;
    }

    public LinkProperties setStage(String str) {
        this.stage_ = str;
        return this;
    }

    public LinkProperties setChannel(String str) {
        this.channel_ = str;
        return this;
    }

    public LinkProperties setCampaign(String str) {
        this.campaign_ = str;
        return this;
    }

    public ArrayList<String> getTags() {
        return this.tags_;
    }

    public HashMap<String, String> getControlParams() {
        return this.controlParams_;
    }

    public int getMatchDuration() {
        return this.matchDuration_;
    }

    public String getAlias() {
        return this.alias_;
    }

    public String getFeature() {
        return this.feature_;
    }

    public String getStage() {
        return this.stage_;
    }

    public String getChannel() {
        return this.channel_;
    }

    public String getCampaign() {
        return this.campaign_;
    }

    public static LinkProperties getReferredLinkProperties() {
        Branch instance = Branch.getInstance();
        if (instance == null || instance.getLatestReferringParams() == null) {
            return null;
        }
        JSONObject latestReferringParams = instance.getLatestReferringParams();
        try {
            if (!latestReferringParams.has("+clicked_branch_link") || !latestReferringParams.getBoolean("+clicked_branch_link")) {
                return null;
            }
            LinkProperties linkProperties = new LinkProperties();
            try {
                if (latestReferringParams.has("~channel")) {
                    linkProperties.setChannel(latestReferringParams.getString("~channel"));
                }
                if (latestReferringParams.has("~feature")) {
                    linkProperties.setFeature(latestReferringParams.getString("~feature"));
                }
                if (latestReferringParams.has("~stage")) {
                    linkProperties.setStage(latestReferringParams.getString("~stage"));
                }
                if (latestReferringParams.has("~campaign")) {
                    linkProperties.setCampaign(latestReferringParams.getString("~campaign"));
                }
                if (latestReferringParams.has("~duration")) {
                    linkProperties.setDuration(latestReferringParams.getInt("~duration"));
                }
                if (latestReferringParams.has("$match_duration")) {
                    linkProperties.setDuration(latestReferringParams.getInt("$match_duration"));
                }
                if (latestReferringParams.has("~tags")) {
                    JSONArray jSONArray = latestReferringParams.getJSONArray("~tags");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        linkProperties.addTag(jSONArray.getString(i));
                    }
                }
                Iterator<String> keys = latestReferringParams.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next.startsWith("$")) {
                        linkProperties.addControlParameter(next, latestReferringParams.getString(next));
                    }
                }
            } catch (Exception unused) {
            }
            return linkProperties;
        } catch (Exception unused2) {
            return null;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.feature_);
        parcel.writeString(this.alias_);
        parcel.writeString(this.stage_);
        parcel.writeString(this.channel_);
        parcel.writeString(this.campaign_);
        parcel.writeInt(this.matchDuration_);
        parcel.writeSerializable(this.tags_);
        parcel.writeInt(this.controlParams_.size());
        for (Map.Entry next : this.controlParams_.entrySet()) {
            parcel.writeString((String) next.getKey());
            parcel.writeString((String) next.getValue());
        }
    }

    private LinkProperties(Parcel parcel) {
        this();
        this.feature_ = parcel.readString();
        this.alias_ = parcel.readString();
        this.stage_ = parcel.readString();
        this.channel_ = parcel.readString();
        this.campaign_ = parcel.readString();
        this.matchDuration_ = parcel.readInt();
        this.tags_.addAll((ArrayList) parcel.readSerializable());
        int readInt = parcel.readInt();
        for (int i = 0; i < readInt; i++) {
            this.controlParams_.put(parcel.readString(), parcel.readString());
        }
    }
}
