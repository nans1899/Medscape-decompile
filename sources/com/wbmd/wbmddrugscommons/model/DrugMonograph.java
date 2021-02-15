package com.wbmd.wbmddrugscommons.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DrugMonograph implements Serializable {
    public String FDB_id;
    public ArrayList<DrugDosage> dosages;
    public String drugDescription;
    public LinkedHashMap<String, DrugMonographSection> drugMonographSectionsMap;
    public String genericDrugId;
    public String gp10;
    public List<String> gp14;
    public String id;
    public DrugIdType idType;
    public ArrayList<Slide> images;
    public Boolean isActive;
    public Boolean isTop;
    public String monographId;
    public String ndc;
    public ArrayList<String> ndcList;
    public String primaryTopicId;
    public String professionalContentID;
    public ArrayList<DrugMonographSection> sections;
    public String tBrandNames;
    public String tDrugName;
    public String tGenericName;
    public String tMonographTitle;
    public ArrayList<Tug> tugs;
    public String urlSuffix;
    public ArrayList<String> urls;

    public DrugMonograph addSection(String str, DrugMonographSection drugMonographSection) {
        if (this.drugMonographSectionsMap == null) {
            this.drugMonographSectionsMap = new LinkedHashMap<>();
        }
        this.drugMonographSectionsMap.put(str, drugMonographSection);
        return this;
    }

    public DrugMonograph addImage(Slide slide) {
        if (this.images == null) {
            this.images = new ArrayList<>();
        }
        this.images.add(slide);
        return this;
    }

    public ArrayList<Tug> getTugs() {
        return this.tugs;
    }

    public void setTugs(ArrayList<Tug> arrayList) {
        this.tugs = arrayList;
    }
}
