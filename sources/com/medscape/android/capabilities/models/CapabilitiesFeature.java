package com.medscape.android.capabilities.models;

import com.medscape.android.Constants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CapabilitiesFeature implements Serializable {
    private Map<String, String> mCapabilities = new HashMap();
    private int mCapabilitiesStatus = 2001;
    private int mEULAVersion = -1;
    private String mEulaUrl;
    private int mLastAcceptedEulaVersion = -1;

    public int getCapabilitiesStatus(String str) {
        return -1;
    }

    public void setEulaVersion(int i) {
        this.mEULAVersion = i;
    }

    public int getEulaVersion() {
        return this.mEULAVersion;
    }

    public void setLastAcceptedEulaVersion(int i) {
        this.mLastAcceptedEulaVersion = i;
    }

    public int getLastAcceptedEulaVersion() {
        return this.mLastAcceptedEulaVersion;
    }

    public void setCapabilitiesStatus(int i) {
        this.mCapabilitiesStatus = i;
    }

    public int getCapabilitiesStatus() {
        return this.mCapabilitiesStatus;
    }

    public void setEulaUrl(String str) {
        this.mEulaUrl = str;
    }

    public String getEulaUrl() {
        return this.mEulaUrl;
    }

    public void addToCapabilities(String str, String str2) {
        Map<String, String> map = this.mCapabilities;
        if (map != null) {
            map.put(str, str2);
        }
    }

    public Map<String, String> getCapabilities() {
        return this.mCapabilities;
    }

    public void updateFeatureWithServerFeature(CapabilitiesFeature capabilitiesFeature) {
        if (capabilitiesFeature != null) {
            if (capabilitiesFeature.getCapabilitiesStatus() == 2001 || capabilitiesFeature.getCapabilitiesStatus() == 2002) {
                setCapabilitiesStatus(capabilitiesFeature.getCapabilitiesStatus());
            }
            Map<String, String> capabilities = capabilitiesFeature.getCapabilities();
            if (capabilities != null) {
                for (String next : capabilities.keySet()) {
                    String str = capabilities.get(next);
                    if (str.equalsIgnoreCase(Constants.CAPABILITIES_FEATURE_DISABLED)) {
                        getCapabilities().put(next, Constants.CAPABILITIES_FEATURE_DISABLED);
                    } else if (str.equalsIgnoreCase("Enabled")) {
                        getCapabilities().put(next, "Enabled");
                    }
                }
            }
            if (capabilitiesFeature.getEulaVersion() > 0) {
                setEulaVersion(capabilitiesFeature.getEulaVersion());
            }
            if (capabilitiesFeature.getEulaUrl() != null && !capabilitiesFeature.getEulaUrl().equalsIgnoreCase("")) {
                setEulaUrl(capabilitiesFeature.getEulaUrl());
            }
            if (capabilitiesFeature.getLastAcceptedEulaVersion() > 0) {
                setLastAcceptedEulaVersion(capabilitiesFeature.getLastAcceptedEulaVersion());
            }
        }
    }
}
