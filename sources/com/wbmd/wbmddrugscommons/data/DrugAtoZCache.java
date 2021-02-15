package com.wbmd.wbmddrugscommons.data;

import com.wbmd.wbmddrugscommons.model.Drug;
import java.util.ArrayList;
import java.util.List;

public class DrugAtoZCache {
    private static DrugAtoZCache mInstance;
    private List<Drug> OffMarketDrugsList = new ArrayList();
    private List<Drug> allDrugsList = new ArrayList();
    private List<Drug> atozDrugsList = new ArrayList();
    private Boolean isDrugsReadFromResourceFile;
    private List<Drug> topSearchesDrugsList = new ArrayList();

    private DrugAtoZCache() {
    }

    public static DrugAtoZCache getInstance() {
        if (mInstance == null) {
            mInstance = new DrugAtoZCache();
        }
        return mInstance;
    }

    public List<Drug> getAllDrugs() {
        return this.allDrugsList;
    }

    public void setAllDrugs(List<Drug> list) {
        this.allDrugsList = list;
    }

    public List<Drug> getAtoZDrugsList() {
        return this.atozDrugsList;
    }

    public void setAtoZDrugsList(List<Drug> list) {
        this.atozDrugsList = list;
    }

    public List<Drug> getTopSearchesDrugsList() {
        return this.topSearchesDrugsList;
    }

    public void setTopSearchesDrugsList(List<Drug> list) {
        this.topSearchesDrugsList = list;
    }

    public List<Drug> getOffMarketDrugsList() {
        return this.OffMarketDrugsList;
    }

    public void setOffMarketDrugsList(List<Drug> list) {
        this.OffMarketDrugsList = list;
    }

    public Boolean getIsDrugsReadFromResourceFile() {
        return this.isDrugsReadFromResourceFile;
    }

    public void setIsDrugsReadFromResourceFile(Boolean bool) {
        this.isDrugsReadFromResourceFile = bool;
    }
}
