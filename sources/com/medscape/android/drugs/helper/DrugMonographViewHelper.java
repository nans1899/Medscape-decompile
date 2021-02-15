package com.medscape.android.drugs.helper;

import com.medscape.android.R;
import com.medscape.android.db.model.DrugClass;
import com.medscape.android.drugs.model.DrugMonograph;
import com.medscape.android.drugs.model.DrugMonographHeader;

public class DrugMonographViewHelper {
    DrugMonograph mDrugMonograph;
    DrugMonographHeader mDrugMonographHeader;
    StringBuilder sb = new StringBuilder();

    public static int getSectionIndexResource(int i, boolean z) {
        if (i == 10) {
            return R.string.drug_monograph_sections_pharmacology;
        }
        switch (i) {
            case 0:
                return R.string.drug_monograph_sections_dosage_tab_adult;
            case 1:
                return R.string.drug_monograph_sections_dosage_tab_pediatric;
            case 2:
                return R.string.drug_monograph_sections_dosage_tab_geriatric;
            case 3:
                return R.string.drug_monograph_sections_interactions;
            case 4:
                return R.string.drug_monograph_sections_effects;
            case 5:
                return R.string.drug_monograph_sections_warnings;
            case 6:
                return R.string.drug_monograph_sections_pregnancy;
            default:
                return R.string.drug_monograph_sections_default;
        }
    }

    public DrugMonographViewHelper(DrugMonograph drugMonograph) {
        this.mDrugMonograph = drugMonograph;
        this.mDrugMonographHeader = drugMonograph.getHeader();
    }

    public String getGenericNameHeader() {
        StringBuilder sb2 = new StringBuilder();
        this.sb = sb2;
        sb2.append(this.mDrugMonographHeader.getGc());
        sb2.append(" (");
        sb2.append(this.mDrugMonographHeader.getAv());
        sb2.append(")");
        return this.sb.toString();
    }

    public String getClassNamesHeader() {
        this.sb = new StringBuilder("Classes: ");
        int i = 0;
        for (DrugClass next : this.mDrugMonographHeader.getClasses()) {
            int i2 = i + 1;
            if (i != 0) {
                this.sb.append(", ");
            }
            this.sb.append(next.getClassName());
            i = i2;
        }
        return this.sb.toString();
    }
}
