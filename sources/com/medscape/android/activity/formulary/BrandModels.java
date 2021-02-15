package com.medscape.android.activity.formulary;

import java.io.Serializable;
import java.util.List;

public class BrandModels implements Serializable {
    List<BrandModel> brandModels;

    public List<BrandModel> getBrandModels() {
        return this.brandModels;
    }

    public void setBrandModels(List<BrandModel> list) {
        this.brandModels = list;
    }
}
