package com.webmd.wbmdomnituremanager;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WBMDOmnitureModule implements Parcelable {
    public static final Parcelable.Creator<WBMDOmnitureModule> CREATOR = new Parcelable.Creator<WBMDOmnitureModule>() {
        public WBMDOmnitureModule createFromParcel(Parcel parcel) {
            return new WBMDOmnitureModule(parcel);
        }

        public WBMDOmnitureModule[] newArray(int i) {
            return new WBMDOmnitureModule[i];
        }
    };
    String linkId;
    String moduleId;
    Map<String, String> properties;
    String referrer;

    public int describeContents() {
        return 0;
    }

    public WBMDOmnitureModule(String str, String str2, String str3) {
        this.moduleId = str;
        this.linkId = str2;
        this.referrer = str3;
        this.properties = new HashMap();
    }

    public WBMDOmnitureModule(String str, String str2, String str3, Map<String, String> map) {
        this.moduleId = str;
        this.linkId = str2;
        this.referrer = str3;
        this.properties = map;
    }

    public String toString() {
        return this.moduleId;
    }

    public void setProperties(Map<String, String> map) {
        String str;
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (!next.getKey().toString().equalsIgnoreCase("wapp.busref") || (!next.getValue().toString().equalsIgnoreCase("symptom checker") && !next.getValue().toString().equalsIgnoreCase("tool - rmq"))) {
                str = next.getValue().toString().toLowerCase().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-");
            } else {
                str = next.getValue().toString().toLowerCase();
            }
            this.properties.put(next.getKey().toString(), str);
            it.remove();
        }
    }

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public String getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(String str) {
        this.moduleId = str;
    }

    public String getLinkId() {
        return this.linkId;
    }

    public void setLinkId(String str) {
        this.linkId = str;
    }

    public String getRefferer() {
        return this.referrer;
    }

    public void setRefferer(String str) {
        this.referrer = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.moduleId);
        parcel.writeString(this.linkId);
        parcel.writeString(this.referrer);
        parcel.writeInt(this.properties.size());
        for (Map.Entry next : this.properties.entrySet()) {
            parcel.writeString((String) next.getKey());
            parcel.writeString((String) next.getValue());
        }
    }

    protected WBMDOmnitureModule(Parcel parcel) {
        this.moduleId = parcel.readString();
        this.linkId = parcel.readString();
        this.referrer = parcel.readString();
        int readInt = parcel.readInt();
        this.properties = new HashMap(readInt);
        for (int i = 0; i < readInt; i++) {
            this.properties.put(parcel.readString(), parcel.readString());
        }
    }
}
