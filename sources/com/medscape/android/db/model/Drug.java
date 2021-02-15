package com.medscape.android.db.model;

import android.net.Uri;
import android.provider.BaseColumns;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Drug implements Serializable, Comparable<Drug> {
    private static final long serialVersionUID = 5132993178149720070L;
    private int comboId;
    private int contentId;
    private List<String> dnames;
    private int drugId;
    private String drugName;
    private int genericId;

    public static final class Drugs implements BaseColumns {
        public static final String COMBO_ID = "comboId";
        public static final String CONTENT_ID = "contentId";
        public static final Uri CONTENT_URI = Uri.parse("content://com.android.medscape.providers.MedscapeContentProvider/interactionsdrugcaches");
        public static final String DNAMES = "dName";
        public static final String DRUG_ID = "drugId";
        public static final String GENERIC_ID = "genericId";
        public static final String NAME = "drugName";

        private Drugs() {
        }
    }

    public int getContentId() {
        return this.contentId;
    }

    public void setContentId(int i) {
        this.contentId = i;
    }

    public String getDrugName() {
        return this.drugName;
    }

    public void setDrugName(String str) {
        this.drugName = str;
    }

    public void setComboId(int i) {
        this.comboId = i;
    }

    public int getComboId() {
        return this.comboId;
    }

    public void setGenericId(int i) {
        this.genericId = i;
    }

    public int getGenericId() {
        return this.genericId;
    }

    public int compareTo(Drug drug) {
        if (drug == null) {
            return -1;
        }
        if (this.contentId == 0) {
            if (drug.getDrugId() == this.drugId) {
                return 0;
            }
            return -1;
        } else if (this.comboId != 0) {
            if (drug.getComboId() == this.comboId) {
                return 0;
            }
            return -1;
        } else if (drug.getContentId() == this.contentId) {
            return 0;
        } else {
            return -1;
        }
    }

    public void setDnames(List<String> list) {
        this.dnames = list;
    }

    public boolean hasOtherNames() {
        List<String> list = this.dnames;
        return list != null && list.size() > 0;
    }

    public List<String> getDnames() {
        List<String> list = this.dnames;
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.dnames = arrayList;
        return arrayList;
    }

    public void setDrugId(int i) {
        this.drugId = i;
    }

    public int getDrugId() {
        return this.drugId;
    }

    public boolean isGenericDrug() {
        return this.drugId == this.genericId;
    }

    public boolean isComboDrug() {
        return this.comboId > 0;
    }

    public void addNameToOtherNames(String str) {
        if (str != null && !str.isEmpty() && !isNameInOtherNames(str)) {
            List<String> list = this.dnames;
            if (list != null) {
                list.add(str);
                return;
            }
            ArrayList arrayList = new ArrayList();
            this.dnames = arrayList;
            arrayList.add(str);
        }
    }

    public boolean isNameInOtherNames(String str) {
        List<String> list;
        if (str == null || str.isEmpty() || (list = this.dnames) == null || list.isEmpty()) {
            return false;
        }
        for (String equals : this.dnames) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean sameAs(Drug drug) {
        return drug != null && this.comboId == drug.getComboId() && this.drugId == drug.getDrugId() && this.genericId == drug.getGenericId();
    }

    public void mergeOtherNames(Drug drug) {
        if (drug != null && drug.getDnames() != null && !drug.getDnames().isEmpty()) {
            if (this.dnames == null) {
                this.dnames = new ArrayList();
            }
            for (String next : drug.getDnames()) {
                if (!isNameInOtherNames(next)) {
                    this.dnames.add(next);
                }
            }
        }
    }

    public String otherNameToString() {
        StringBuilder sb = new StringBuilder();
        List<String> list = this.dnames;
        if (list != null && !list.isEmpty()) {
            if (this.dnames.size() == 1) {
                return this.dnames.get(0);
            }
            for (int i = 0; i < this.dnames.size(); i++) {
                if (i < this.dnames.size() - 1) {
                    sb.append(this.dnames.get(i));
                    sb.append(";");
                } else {
                    sb.append(this.dnames.get(i));
                }
            }
        }
        return sb.toString();
    }
}
