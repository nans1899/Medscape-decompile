package com.medscape.android.activity.formulary;

import java.io.Serializable;

public class BrandModel implements Serializable {
    private String mBrandId;
    private String mBrandName;

    public BrandModel(String str, String str2) {
        this.mBrandName = str;
        this.mBrandId = str2;
    }

    public String getBrandName() {
        return this.mBrandName;
    }

    public void setBrandName(String str) {
        this.mBrandName = str;
    }

    public String getBrandId() {
        return this.mBrandId;
    }

    public void setBrandId(String str) {
        this.mBrandId = str;
    }

    public String toString() {
        return "BrandId : " + this.mBrandId + " BrandName : " + this.mBrandName;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: com.medscape.android.activity.formulary.FormularyDatabaseHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: com.medscape.android.activity.formulary.FormularyDatabaseHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: com.medscape.android.activity.formulary.FormularyDatabaseHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: com.medscape.android.activity.formulary.FormularyDatabaseHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: com.medscape.android.activity.formulary.FormularyDatabaseHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: com.medscape.android.activity.formulary.FormularyDatabaseHelper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: android.database.Cursor} */
    /* JADX WARNING: type inference failed for: r7v7 */
    /*  JADX ERROR: JadxRuntimeException in pass: ProcessVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r7v7 ?
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.collectCodeVars(ProcessVariables.java:122)
        	at jadx.core.dex.visitors.regions.variables.ProcessVariables.visit(ProcessVariables.java:45)
        */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003f, code lost:
        if (r7 != 0) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0052, code lost:
        if (r7 == 0) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0055, code lost:
        return r0;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x005e  */
    public static java.util.List<com.medscape.android.activity.formulary.BrandModel> getBrandListFromContentId(android.content.Context r7, java.lang.String r8) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r8 != 0) goto L_0x0008
            return r0
        L_0x0008:
            r1 = 0
            com.medscape.android.activity.formulary.FormularyDatabaseHelper r7 = com.medscape.android.activity.formulary.FormularyDatabaseHelper.getInstance(r7)     // Catch:{ Exception -> 0x0048, all -> 0x0045 }
            if (r7 == 0) goto L_0x003a
            java.lang.String r2 = "SELECT drugId, drugName FROM tblMappedDrugs WHERE ContentId = ? ORDER BY GenericIndicator DESC"
            android.database.sqlite.SQLiteDatabase r3 = r7.getDatabase()     // Catch:{ Exception -> 0x0038 }
            r4 = 1
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch:{ Exception -> 0x0038 }
            r6 = 0
            r5[r6] = r8     // Catch:{ Exception -> 0x0038 }
            android.database.Cursor r1 = r3.rawQuery(r2, r5)     // Catch:{ Exception -> 0x0038 }
            if (r1 == 0) goto L_0x003a
        L_0x0021:
            boolean r8 = r1.moveToNext()     // Catch:{ Exception -> 0x0038 }
            if (r8 == 0) goto L_0x003a
            java.lang.String r8 = r1.getString(r4)     // Catch:{ Exception -> 0x0038 }
            java.lang.String r2 = r1.getString(r6)     // Catch:{ Exception -> 0x0038 }
            com.medscape.android.activity.formulary.BrandModel r3 = new com.medscape.android.activity.formulary.BrandModel     // Catch:{ Exception -> 0x0038 }
            r3.<init>(r8, r2)     // Catch:{ Exception -> 0x0038 }
            r0.add(r3)     // Catch:{ Exception -> 0x0038 }
            goto L_0x0021
        L_0x0038:
            r8 = move-exception
            goto L_0x004a
        L_0x003a:
            if (r1 == 0) goto L_0x003f
            r1.close()
        L_0x003f:
            if (r7 == 0) goto L_0x0055
        L_0x0041:
            r7.close()
            goto L_0x0055
        L_0x0045:
            r8 = move-exception
            r7 = r1
            goto L_0x0057
        L_0x0048:
            r8 = move-exception
            r7 = r1
        L_0x004a:
            r8.printStackTrace()     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x0052
            r1.close()
        L_0x0052:
            if (r7 == 0) goto L_0x0055
            goto L_0x0041
        L_0x0055:
            return r0
        L_0x0056:
            r8 = move-exception
        L_0x0057:
            if (r1 == 0) goto L_0x005c
            r1.close()
        L_0x005c:
            if (r7 == 0) goto L_0x0061
            r7.close()
        L_0x0061:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.BrandModel.getBrandListFromContentId(android.content.Context, java.lang.String):java.util.List");
    }
}
