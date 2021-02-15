package com.ib.foreceup.model;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;

public class RemoteUpdate implements Comparable<RemoteUpdate> {
    public static String TYPE_MANDATORY = "Mandatory";
    public static String TYPE_OPTIONAL = "Optional";
    public static String TYPE_RECOMMENDED = "Recommended";
    public static String TYPE_SILENT = "Silent";
    HashMap<String, ArrayList<UserConditions>> additionalCriteria;
    private String minimumOsVersion;
    private RemoteUpdateMessaging remoteUpdateMessaging;
    private String remoteUpdateType;
    private String targetVersionMax;
    private String targetVersionMin;

    public String getTargetVersionMax() {
        return this.targetVersionMax;
    }

    public void setTargetVersionMax(String str) {
        this.targetVersionMax = str;
    }

    public String getTargetVersionMin() {
        return this.targetVersionMin;
    }

    public void setTargetVersionMin(String str) {
        this.targetVersionMin = str;
    }

    public String getMinimumOsVersion() {
        return this.minimumOsVersion;
    }

    public void setMinimumOsVersion(String str) {
        this.minimumOsVersion = str;
    }

    public String getRemoteUpdateType() {
        return this.remoteUpdateType;
    }

    public void setRemoteUpdateType(String str) {
        this.remoteUpdateType = str;
    }

    public RemoteUpdateMessaging getRemoteUpdateMessaging() {
        return this.remoteUpdateMessaging;
    }

    public void setRemoteUpdateMessaging(RemoteUpdateMessaging remoteUpdateMessaging2) {
        this.remoteUpdateMessaging = remoteUpdateMessaging2;
    }

    public int getTargetVersionMinNumber() {
        if (TextUtils.isEmpty(this.targetVersionMin)) {
            return 0;
        }
        return versionStringToNumber(this.targetVersionMin);
    }

    public int getTargetVersionMaxNumber() {
        if (TextUtils.isEmpty(this.targetVersionMax)) {
            return 0;
        }
        return versionStringToNumber(this.targetVersionMax);
    }

    public HashMap<String, ArrayList<UserConditions>> getAdditionalCriteria() {
        return this.additionalCriteria;
    }

    public void setAdditionalCriteria(HashMap<String, ArrayList<UserConditions>> hashMap) {
        this.additionalCriteria = hashMap;
    }

    public static int versionStringToNumber(String str) {
        if (str == null) {
            return 0;
        }
        try {
            String[] split = (str + ".0.0").split("\\.");
            return (Integer.parseInt(split[0]) * 100000) + (Integer.parseInt(split[1]) * 1000) + Integer.parseInt(split[2]);
        } catch (Exception unused) {
            return 0;
        }
    }

    public int compareTo(RemoteUpdate remoteUpdate) {
        return Integer.compare(getTypeOrder(), remoteUpdate.getTypeOrder());
    }

    public int getTypeOrder() {
        if (TYPE_MANDATORY.equalsIgnoreCase(this.remoteUpdateType)) {
            return 0;
        }
        if (TYPE_RECOMMENDED.equalsIgnoreCase(this.remoteUpdateType)) {
            return 1;
        }
        return TYPE_OPTIONAL.equalsIgnoreCase(this.remoteUpdateType) ? 2 : 3;
    }
}
