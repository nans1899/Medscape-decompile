package com.webmd.wbmdsimullytics.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Platform {
    @SerializedName("platforms")
    private List<String> mPlatforms;

    public List<String> getPlatforms() {
        return this.mPlatforms;
    }

    public void setPlatforms(List<String> list) {
        this.mPlatforms = list;
    }
}
