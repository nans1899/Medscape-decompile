package com.wbmd.wbmddrugscommons.data;

import com.wbmd.wbmddrugscommons.model.DrugMonograph;

public class DrugMonographCache {
    private static DrugMonographCache mInstance;
    private DrugMonograph drugMonograph = new DrugMonograph();

    private DrugMonographCache() {
    }

    public static DrugMonographCache get() {
        if (mInstance == null) {
            mInstance = new DrugMonographCache();
        }
        return mInstance;
    }

    public DrugMonograph getDrugMonograph() {
        return this.drugMonograph;
    }

    public void setDrugMonograph(DrugMonograph drugMonograph2) {
        this.drugMonograph = drugMonograph2;
    }
}
