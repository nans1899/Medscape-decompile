package com.medscape.android.drugs;

import android.util.Log;
import com.medscape.android.drugs.details.viewmodels.DrugSectionViewModel;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.wbmd.wbmddrugscommons.constants.Constants;

public class OmnitureValues {
    public static String getBiSectionNameFromPageNumber(int i) {
        if (i == 0) {
            return "adult-dosing";
        }
        if (i == 3) {
            return "interactions";
        }
        if (i == 4) {
            return "adverse-effects";
        }
        if (i == 5) {
            return "warnings";
        }
        if (i == 6) {
            return "pregnancy";
        }
        switch (i) {
            case 9:
                return Constants.WBMDDrugResponseKeyDrugImages;
            case 10:
                return "pharmacology";
            case 11:
                return "administration";
            default:
                return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getBiSectionNameFromTitle(java.lang.String r3) {
        /*
            int r0 = r3.hashCode()
            r1 = -1276985633(0xffffffffb3e2bedf, float:-1.0558664E-7)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 63123866(0x3c3319a, float:1.1472458E-36)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "Adult"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0024
            r3 = 0
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "Pediatric"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0024
            r3 = 1
            goto L_0x0025
        L_0x0024:
            r3 = -1
        L_0x0025:
            if (r3 == 0) goto L_0x002e
            if (r3 == r2) goto L_0x002b
            r3 = 0
            return r3
        L_0x002b:
            java.lang.String r3 = "1-pediatric-dosing"
            return r3
        L_0x002e:
            java.lang.String r3 = "0-adult-dosing"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.OmnitureValues.getBiSectionNameFromTitle(java.lang.String):java.lang.String");
    }

    public static String generateChronicleId(DrugSectionViewModel drugSectionViewModel, String str, String str2) {
        String valueOf = String.valueOf(drugSectionViewModel.drugSectionContentRepo.getContentId());
        if (str2 == null) {
            str2 = "";
        }
        String generateAssetId = new ChronicleIDUtil().generateAssetId(valueOf, str, String.format("/drug/view/%s%s", new Object[]{valueOf, str2}));
        Log.d("OMNITURE_DATA", generateAssetId);
        return generateAssetId;
    }
}
