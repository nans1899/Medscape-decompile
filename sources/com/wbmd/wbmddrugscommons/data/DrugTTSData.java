package com.wbmd.wbmddrugscommons.data;

import com.wbmd.wbmddrugscommons.model.Tug;
import java.util.HashMap;

public class DrugTTSData {
    private static DrugTTSData mInstance;
    private HashMap<String, Tug> tugTTSData = new HashMap<>();

    private DrugTTSData() {
    }

    public static DrugTTSData get() {
        if (mInstance == null) {
            mInstance = new DrugTTSData();
        }
        return mInstance;
    }

    public HashMap<String, Tug> getTTSData() {
        return this.tugTTSData;
    }

    public void setTTSData(HashMap<String, Tug> hashMap) {
        this.tugTTSData = hashMap;
    }

    public void clearTTSData() {
        this.tugTTSData.clear();
    }
}
