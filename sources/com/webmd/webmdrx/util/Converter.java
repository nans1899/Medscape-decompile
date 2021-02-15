package com.webmd.webmdrx.util;

import com.webmd.webmdrx.models.Drug;
import com.webmd.webmdrx.models.RxData;
import com.webmd.webmdrx.models.SavedDrugData;
import com.webmd.webmdrx.models.SavedRxDosage;

public class Converter {
    public static SavedDrugData drugToPapixSchema(Drug drug, int i, boolean z) {
        SavedDrugData savedDrugData = new SavedDrugData();
        if (drug != null) {
            savedDrugData.setType("drugs");
            if (z) {
                savedDrugData.setDeleted(1);
            } else {
                savedDrugData.setDeleted(0);
            }
            savedDrugData.setItem_id(drug.getFdbId());
            savedDrugData.setTitle(drug.getTitle());
            savedDrugData.setSecondaryID(drug.getMonoId());
            RxData rxData = new RxData();
            SavedRxDosage savedRxDosage = new SavedRxDosage();
            savedRxDosage.setGpi(drug.getGPI());
            savedRxDosage.setNdc(drug.getDrugId());
            savedRxDosage.setForm(drug.getForm());
            savedRxDosage.setStrength(drug.getStrength());
            savedRxDosage.setDrugName(drug.getValue());
            savedRxDosage.setQuantity(i);
            savedRxDosage.setOtherName(drug.getOtherName());
            savedRxDosage.setIsGeneric(drug.isGeneric() ? 1 : 0);
            if (drug.getPackageSize() != null) {
                savedRxDosage.setPkg_size(Double.parseDouble(drug.getPackageSize()));
            }
            rxData.addToDosagesList(savedRxDosage);
            savedDrugData.setDrugDetail(rxData);
        }
        return savedDrugData;
    }
}
