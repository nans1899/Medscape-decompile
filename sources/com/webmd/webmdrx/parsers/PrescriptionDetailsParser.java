package com.webmd.webmdrx.parsers;

import android.util.Log;
import com.webmd.webmdrx.models.PackageSize;
import com.webmd.webmdrx.models.Quantity;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class PrescriptionDetailsParser {
    private static final String TAG = PrescriptionDetailsParser.class.getSimpleName();

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        r0 = (r1 = r1.optJSONObject("rxform")).optJSONArray("data");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.webmd.webmdrx.models.RxForm parsePrescriptionDetails(java.lang.String r3) {
        /*
            java.lang.String r0 = "data"
            boolean r1 = com.webmd.webmdrx.util.StringUtil.isNotEmpty(r3)
            r2 = 0
            if (r1 == 0) goto L_0x003c
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ Exception -> 0x0035 }
            r1.<init>(r3)     // Catch:{ Exception -> 0x0035 }
            com.webmd.webmdrx.models.RxForm r3 = new com.webmd.webmdrx.models.RxForm     // Catch:{ Exception -> 0x0035 }
            r3.<init>()     // Catch:{ Exception -> 0x0035 }
            org.json.JSONObject r1 = r1.optJSONObject(r0)     // Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x0032
            java.lang.String r2 = "rxform"
            org.json.JSONObject r1 = r1.optJSONObject(r2)     // Catch:{ Exception -> 0x0034 }
            if (r1 == 0) goto L_0x0032
            org.json.JSONArray r0 = r1.optJSONArray(r0)     // Catch:{ Exception -> 0x0034 }
            if (r0 == 0) goto L_0x0032
            int r1 = r0.length()     // Catch:{ Exception -> 0x0034 }
            if (r1 <= 0) goto L_0x0032
            com.webmd.webmdrx.models.RxForm r3 = getDrugFromObject(r0, r3)     // Catch:{ Exception -> 0x0034 }
            return r3
        L_0x0032:
            r2 = r3
            goto L_0x003c
        L_0x0034:
            r2 = r3
        L_0x0035:
            java.lang.String r3 = TAG
            java.lang.String r0 = "Failed to parse prescription details"
            com.webmd.webmdrx.util.Trace.w(r3, r0)
        L_0x003c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.parsers.PrescriptionDetailsParser.parsePrescriptionDetails(java.lang.String):com.webmd.webmdrx.models.RxForm");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:43|44) */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        com.webmd.webmdrx.util.Trace.w(TAG, "Failed to parse quantity for drug");
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x0118 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.webmd.webmdrx.models.RxForm getDrugFromObject(org.json.JSONArray r13, com.webmd.webmdrx.models.RxForm r14) {
        /*
            r0 = 0
            r1 = 0
        L_0x0002:
            int r2 = r13.length()
            if (r1 >= r2) goto L_0x0132
            java.lang.Object r2 = r13.get(r1)     // Catch:{ Exception -> 0x012a }
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch:{ Exception -> 0x012a }
            java.lang.String r3 = "drug"
            java.lang.String r3 = r2.getString(r3)     // Catch:{ Exception -> 0x012a }
            java.lang.String r4 = "forms"
            org.json.JSONArray r2 = r2.getJSONArray(r4)     // Catch:{ Exception -> 0x012a }
            r4 = 0
        L_0x001b:
            int r5 = r2.length()     // Catch:{ Exception -> 0x012a }
            if (r4 >= r5) goto L_0x012e
            java.lang.Object r5 = r2.get(r4)     // Catch:{ Exception -> 0x012a }
            org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch:{ Exception -> 0x012a }
            java.lang.String r6 = "form"
            java.lang.String r6 = r5.getString(r6)     // Catch:{ Exception -> 0x012a }
            java.lang.String r7 = "strengths"
            org.json.JSONArray r5 = r5.getJSONArray(r7)     // Catch:{ Exception -> 0x012a }
            r7 = 0
        L_0x0034:
            int r8 = r5.length()     // Catch:{ Exception -> 0x012a }
            if (r7 >= r8) goto L_0x0126
            java.lang.Object r8 = r5.get(r7)     // Catch:{ Exception -> 0x012a }
            org.json.JSONObject r8 = (org.json.JSONObject) r8     // Catch:{ Exception -> 0x012a }
            java.lang.String r9 = "strength"
            java.lang.String r9 = r8.getString(r9)     // Catch:{ Exception -> 0x012a }
            com.webmd.webmdrx.models.Drug r10 = new com.webmd.webmdrx.models.Drug     // Catch:{ Exception -> 0x012a }
            r10.<init>()     // Catch:{ Exception -> 0x012a }
            r10.setValue(r3)     // Catch:{ Exception -> 0x012a }
            r10.setForm(r6)     // Catch:{ Exception -> 0x012a }
            r10.setStrength(r9)     // Catch:{ Exception -> 0x012a }
            java.lang.String r9 = "GPI"
            java.lang.String r9 = r8.getString(r9)     // Catch:{ Exception -> 0x012a }
            r10.setGPI(r9)     // Catch:{ Exception -> 0x012a }
            java.lang.String r9 = "is_generic"
            boolean r9 = r8.getBoolean(r9)     // Catch:{ Exception -> 0x012a }
            r10.setIsGeneric(r9)     // Catch:{ Exception -> 0x012a }
            java.lang.String r9 = "AutoPackageSelect"
            boolean r9 = r8.getBoolean(r9)     // Catch:{ Exception -> 0x012a }
            r10.setIsAutoPackageSelect(r9)     // Catch:{ Exception -> 0x012a }
            java.lang.String r9 = "PackageDescription"
            java.lang.String r9 = r8.getString(r9)     // Catch:{ Exception -> 0x012a }
            r10.setPackageDescription(r9)     // Catch:{ Exception -> 0x012a }
            java.lang.String r9 = "DeaClassCode"
            java.lang.String r9 = r8.getString(r9)     // Catch:{ Exception -> 0x012a }
            r10.setDeaClassCode(r9)     // Catch:{ Exception -> 0x012a }
            java.lang.String r9 = "NDC"
            org.json.JSONArray r9 = r8.getJSONArray(r9)     // Catch:{ Exception -> 0x012a }
            if (r9 == 0) goto L_0x00a0
            int r11 = r9.length()     // Catch:{ Exception -> 0x012a }
            if (r11 <= 0) goto L_0x00a0
            r11 = 0
        L_0x0090:
            int r12 = r9.length()     // Catch:{ Exception -> 0x012a }
            if (r11 >= r12) goto L_0x00a0
            java.lang.String r12 = r9.getString(r11)     // Catch:{ Exception -> 0x012a }
            r10.addToNDCList(r12)     // Catch:{ Exception -> 0x012a }
            int r11 = r11 + 1
            goto L_0x0090
        L_0x00a0:
            java.util.List r9 = r10.getNDCList()     // Catch:{ Exception -> 0x012a }
            if (r9 == 0) goto L_0x00bd
            java.util.List r9 = r10.getNDCList()     // Catch:{ Exception -> 0x012a }
            boolean r9 = r9.isEmpty()     // Catch:{ Exception -> 0x012a }
            if (r9 != 0) goto L_0x00bd
            java.util.List r9 = r10.getNDCList()     // Catch:{ Exception -> 0x012a }
            java.lang.Object r9 = r9.get(r0)     // Catch:{ Exception -> 0x012a }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ Exception -> 0x012a }
            r10.setDrugId(r9)     // Catch:{ Exception -> 0x012a }
        L_0x00bd:
            boolean r9 = r10.isAutoPackageSelect()     // Catch:{ Exception -> 0x0118 }
            if (r9 == 0) goto L_0x010a
            java.lang.String r9 = "Quantity"
            org.json.JSONArray r8 = r8.getJSONArray(r9)     // Catch:{ Exception -> 0x0118 }
            if (r8 == 0) goto L_0x011f
            r9 = 0
        L_0x00cc:
            int r11 = r8.length()     // Catch:{ Exception -> 0x0118 }
            if (r9 >= r11) goto L_0x00e8
            java.lang.Object r11 = r8.get(r9)     // Catch:{ Exception -> 0x0118 }
            com.webmd.webmdrx.models.Quantity r11 = getQuantiyForObject(r11)     // Catch:{ Exception -> 0x0118 }
            if (r11 == 0) goto L_0x00e5
            int r12 = r11.getQuantity()     // Catch:{ Exception -> 0x0118 }
            if (r12 <= 0) goto L_0x00e5
            r10.addQuantiyToList(r11)     // Catch:{ Exception -> 0x0118 }
        L_0x00e5:
            int r9 = r9 + 1
            goto L_0x00cc
        L_0x00e8:
            java.util.List r8 = r10.getQuantityList()     // Catch:{ Exception -> 0x0118 }
            if (r8 == 0) goto L_0x011f
            java.util.List r8 = r10.getQuantityList()     // Catch:{ Exception -> 0x0118 }
            int r8 = r8.size()     // Catch:{ Exception -> 0x0118 }
            if (r8 <= 0) goto L_0x011f
            java.util.List r8 = r10.getQuantityList()     // Catch:{ Exception -> 0x0118 }
            java.lang.Object r8 = r8.get(r0)     // Catch:{ Exception -> 0x0118 }
            com.webmd.webmdrx.models.Quantity r8 = (com.webmd.webmdrx.models.Quantity) r8     // Catch:{ Exception -> 0x0118 }
            java.lang.String r8 = r8.getPackageUnit()     // Catch:{ Exception -> 0x0118 }
            r10.setPackageUnit(r8)     // Catch:{ Exception -> 0x0118 }
            goto L_0x011f
        L_0x010a:
            java.lang.String r9 = "PackageSize"
            org.json.JSONArray r8 = r8.getJSONArray(r9)     // Catch:{ Exception -> 0x0118 }
            java.util.List r8 = getPackageSizeList(r8)     // Catch:{ Exception -> 0x0118 }
            r10.setPackageSizeList(r8)     // Catch:{ Exception -> 0x0118 }
            goto L_0x011f
        L_0x0118:
            java.lang.String r8 = TAG     // Catch:{ Exception -> 0x012a }
            java.lang.String r9 = "Failed to parse quantity for drug"
            com.webmd.webmdrx.util.Trace.w(r8, r9)     // Catch:{ Exception -> 0x012a }
        L_0x011f:
            r14.addDrugToList(r10)     // Catch:{ Exception -> 0x012a }
            int r7 = r7 + 1
            goto L_0x0034
        L_0x0126:
            int r4 = r4 + 1
            goto L_0x001b
        L_0x012a:
            r2 = move-exception
            r2.printStackTrace()
        L_0x012e:
            int r1 = r1 + 1
            goto L_0x0002
        L_0x0132:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.parsers.PrescriptionDetailsParser.getDrugFromObject(org.json.JSONArray, com.webmd.webmdrx.models.RxForm):com.webmd.webmdrx.models.RxForm");
    }

    private static List<PackageSize> getPackageSizeList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                        PackageSize packageSize = new PackageSize();
                        packageSize.setPackageSize(jSONObject.optString("PackageSize"));
                        packageSize.setDisplay(jSONObject.optString("display").toLowerCase());
                        if (packageSize.getDisplay() != null && !packageSize.getDisplay().isEmpty() && packageSize.getPackageSize() != null && !packageSize.getPackageSize().isEmpty()) {
                            JSONArray jSONArray2 = jSONObject.getJSONArray("Quantity");
                            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                                Quantity quantity = new Quantity();
                                quantity.setRank(jSONObject2.optInt("rank"));
                                quantity.setQuantity(jSONObject2.optInt("value"));
                                quantity.setDisplay(jSONObject2.getString("display").toLowerCase());
                                packageSize.addToQuantityList(quantity);
                            }
                            arrayList.add(packageSize);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.i("_TAG", "getPackageSizeList: ");
        return arrayList;
    }

    private static Quantity getQuantiyForObject(Object obj) {
        if (!(obj instanceof JSONObject)) {
            return null;
        }
        JSONObject jSONObject = (JSONObject) obj;
        Quantity quantity = new Quantity();
        quantity.setQuantity(jSONObject.optInt("value"));
        quantity.setRank(jSONObject.optInt("rank"));
        quantity.setDisplay(jSONObject.optString("display").toLowerCase());
        quantity.setPackageSize(jSONObject.optString("PackageSize"));
        quantity.setPackageUnit(jSONObject.optString("PackageUnit"));
        quantity.setPriceQuantity(Double.valueOf(jSONObject.optDouble("priceQuantity")));
        return quantity;
    }
}
