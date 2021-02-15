package com.medscape.android.activity.interactions;

import com.medscape.android.db.model.Drug;
import com.medscape.android.db.model.Interaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class InteractionsDataManager {
    public static ArrayList<Interaction> removeInteractionsForDrug(ArrayList<Interaction> arrayList, Drug drug) {
        if (!(arrayList == null || arrayList.size() == 0)) {
            try {
                ArrayList arrayList2 = new ArrayList();
                Iterator<Interaction> it = arrayList.iterator();
                while (it.hasNext()) {
                    Interaction next = it.next();
                    if (next.getSubjectName().equalsIgnoreCase(drug.getDrugName()) || next.getObjectName().equalsIgnoreCase(drug.getDrugName())) {
                        arrayList2.add(next);
                    }
                }
                arrayList.removeAll(arrayList2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x012d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<com.medscape.android.db.model.Interaction> interactionsWithDrugs(java.util.ArrayList<com.medscape.android.db.model.Drug> r12, android.content.Context r13) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            com.medscape.android.db.DatabaseHelper r3 = new com.medscape.android.db.DatabaseHelper
            r3.<init>(r13)
            android.database.sqlite.SQLiteDatabase r13 = r3.getDatabase()     // Catch:{ Exception -> 0x0126 }
            if (r12 != 0) goto L_0x001b
            return r2
        L_0x001b:
            java.util.Iterator r3 = r12.iterator()     // Catch:{ Exception -> 0x0124 }
        L_0x001f:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x0124 }
            r5 = 2
            r6 = 0
            r7 = 1
            if (r4 == 0) goto L_0x0068
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x0124 }
            com.medscape.android.db.model.Drug r4 = (com.medscape.android.db.model.Drug) r4     // Catch:{ Exception -> 0x0124 }
            int r8 = r4.getComboId()     // Catch:{ Exception -> 0x0124 }
            if (r8 == 0) goto L_0x005c
            java.lang.String r8 = "select DrugID,DrugName,G.GenericID from tblDrugs G JOIN tblComboDrugs C ON G.DrugID=C.GenericID Where C.ComboID = ?"
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ Exception -> 0x0124 }
            int r4 = r4.getComboId()     // Catch:{ Exception -> 0x0124 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Exception -> 0x0124 }
            r7[r6] = r4     // Catch:{ Exception -> 0x0124 }
            android.database.Cursor r4 = r13.rawQuery(r8, r7)     // Catch:{ Exception -> 0x0124 }
        L_0x0046:
            boolean r6 = r4.moveToNext()     // Catch:{ Exception -> 0x0124 }
            if (r6 == 0) goto L_0x0058
            int r6 = r4.getInt(r5)     // Catch:{ Exception -> 0x0124 }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x0124 }
            r1.add(r6)     // Catch:{ Exception -> 0x0124 }
            goto L_0x0046
        L_0x0058:
            r4.close()     // Catch:{ Exception -> 0x0124 }
            goto L_0x001f
        L_0x005c:
            int r4 = r4.getGenericId()     // Catch:{ Exception -> 0x0124 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Exception -> 0x0124 }
            r1.add(r4)     // Catch:{ Exception -> 0x0124 }
            goto L_0x001f
        L_0x0068:
            r0.addAll(r1)     // Catch:{ Exception -> 0x0124 }
            java.util.Iterator r1 = r0.iterator()     // Catch:{ Exception -> 0x0124 }
        L_0x006f:
            boolean r3 = r1.hasNext()     // Catch:{ Exception -> 0x0124 }
            if (r3 == 0) goto L_0x012b
            java.lang.Object r3 = r1.next()     // Catch:{ Exception -> 0x0124 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ Exception -> 0x0124 }
            java.util.Iterator r4 = r0.iterator()     // Catch:{ Exception -> 0x0124 }
        L_0x007f:
            boolean r8 = r4.hasNext()     // Catch:{ Exception -> 0x0124 }
            if (r8 == 0) goto L_0x006f
            java.lang.Object r8 = r4.next()     // Catch:{ Exception -> 0x0124 }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ Exception -> 0x0124 }
            int r9 = r3.intValue()     // Catch:{ Exception -> 0x0124 }
            int r8 = r8.intValue()     // Catch:{ Exception -> 0x0124 }
            if (r9 != r8) goto L_0x0096
            goto L_0x007f
        L_0x0096:
            java.lang.String r10 = "SELECT InteractionID,SubjectID,ObjectID,InteractionType,MechID,Direction,Effect,Strength,Comment,D1.DrugName AS SubjectName, D2.DrugName AS ObjectName FROM tblInteractions LEFT JOIN tblDrugs D1 ON SubjectID = D1.DrugID LEFT JOIN tblDrugs D2 ON ObjectID = D2.DrugID WHERE SubjectID = ? AND ObjectID = ?"
            java.lang.String[] r11 = new java.lang.String[r5]     // Catch:{ Exception -> 0x0124 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x0124 }
            r11[r6] = r9     // Catch:{ Exception -> 0x0124 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x0124 }
            r11[r7] = r8     // Catch:{ Exception -> 0x0124 }
            android.database.Cursor r8 = r13.rawQuery(r10, r11)     // Catch:{ Exception -> 0x0124 }
        L_0x00aa:
            boolean r9 = r8.moveToNext()     // Catch:{ Exception -> 0x0124 }
            if (r9 == 0) goto L_0x011f
            com.medscape.android.db.model.Interaction r9 = new com.medscape.android.db.model.Interaction     // Catch:{ Exception -> 0x0124 }
            r9.<init>()     // Catch:{ Exception -> 0x0124 }
            int r10 = r8.getInt(r6)     // Catch:{ Exception -> 0x0124 }
            r9.setInteractionId(r10)     // Catch:{ Exception -> 0x0124 }
            int r10 = r8.getInt(r7)     // Catch:{ Exception -> 0x0124 }
            r9.setSubjectId(r10)     // Catch:{ Exception -> 0x0124 }
            int r10 = r8.getInt(r5)     // Catch:{ Exception -> 0x0124 }
            r9.setObjectId(r10)     // Catch:{ Exception -> 0x0124 }
            r10 = 3
            int r10 = r8.getInt(r10)     // Catch:{ Exception -> 0x0124 }
            r9.setInteractionType(r10)     // Catch:{ Exception -> 0x0124 }
            r10 = 4
            int r10 = r8.getInt(r10)     // Catch:{ Exception -> 0x0124 }
            r9.setMechId(r10)     // Catch:{ Exception -> 0x0124 }
            r10 = 5
            java.lang.String r10 = r8.getString(r10)     // Catch:{ Exception -> 0x0124 }
            r9.setDirection(r10)     // Catch:{ Exception -> 0x0124 }
            r10 = 6
            java.lang.String r10 = r8.getString(r10)     // Catch:{ Exception -> 0x0124 }
            r9.setEffect(r10)     // Catch:{ Exception -> 0x0124 }
            r10 = 7
            int r10 = r8.getInt(r10)     // Catch:{ Exception -> 0x0124 }
            r9.setStrengthId(r10)     // Catch:{ Exception -> 0x0124 }
            r10 = 8
            java.lang.String r10 = r8.getString(r10)     // Catch:{ Exception -> 0x0124 }
            r9.setComment(r10)     // Catch:{ Exception -> 0x0124 }
            r10 = 9
            java.lang.String r10 = r8.getString(r10)     // Catch:{ Exception -> 0x0124 }
            r9.setSubjectName(r10)     // Catch:{ Exception -> 0x0124 }
            r10 = 10
            java.lang.String r10 = r8.getString(r10)     // Catch:{ Exception -> 0x0124 }
            r9.setObjectName(r10)     // Catch:{ Exception -> 0x0124 }
            java.lang.String r10 = r9.getSubjectName()     // Catch:{ Exception -> 0x0124 }
            java.lang.String r11 = r9.getObjectName()     // Catch:{ Exception -> 0x0124 }
            boolean r10 = isFromSameDrug(r10, r11, r12)     // Catch:{ Exception -> 0x0124 }
            if (r10 == 0) goto L_0x00aa
            r2.add(r9)     // Catch:{ Exception -> 0x0124 }
            goto L_0x00aa
        L_0x011f:
            r8.close()     // Catch:{ Exception -> 0x0124 }
            goto L_0x007f
        L_0x0124:
            r12 = move-exception
            goto L_0x0128
        L_0x0126:
            r12 = move-exception
            r13 = 0
        L_0x0128:
            r12.printStackTrace()
        L_0x012b:
            if (r13 == 0) goto L_0x0130
            r13.close()
        L_0x0130:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.interactions.InteractionsDataManager.interactionsWithDrugs(java.util.ArrayList, android.content.Context):java.util.ArrayList");
    }

    private static boolean isFromSameDrug(String str, String str2, ArrayList<Drug> arrayList) {
        if (str == null || str2 == null) {
            return false;
        }
        Iterator<Drug> it = arrayList.iterator();
        while (it.hasNext()) {
            Drug next = it.next();
            if (next.getDrugName() != null) {
                if (!next.getDrugName().equalsIgnoreCase(str) && !next.getDrugName().equalsIgnoreCase(str2)) {
                    String[] split = next.getDrugName().toLowerCase().split("/");
                    if (Arrays.asList(split).contains(str2.toLowerCase()) ^ Arrays.asList(split).contains(str.toLowerCase())) {
                    }
                }
                return true;
            }
        }
        Iterator<Drug> it2 = arrayList.iterator();
        int i = 0;
        while (it2.hasNext()) {
            Drug next2 = it2.next();
            if (next2.getDrugName() != null) {
                String lowerCase = next2.getDrugName().toLowerCase();
                if (lowerCase.contains(str.toLowerCase()) || lowerCase.contains(str2.toLowerCase())) {
                    i++;
                }
            }
        }
        if (i >= 2) {
            return true;
        }
        return false;
    }
}
