package com.medscape.android.drugs;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.drugs.helper.SearchHelper;
import com.wbmd.wbmddrugscommons.constants.Constants;

public class InteractionsDataUtil {
    private static String commentScript;
    private static String direction;
    private static String effect;
    private static int mechID;
    private static String mechScript;
    private static int objectID;
    private static String objectName;
    private static String strengthScript;
    private static int subjectID;
    private static String subjectName;
    private static String tempEffect;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00aa A[SYNTHETIC, Splitter:B:32:0x00aa] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00bd A[SYNTHETIC, Splitter:B:40:0x00bd] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:49:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getInteractionDetailText(int r3) {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
            r1.<init>()     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
            java.lang.String r2 = "SELECT I.InteractionID, I.SubjectID, D1.DrugName AS SubjectName, I.ObjectID, D2.DrugName AS ObjectName, M.MechScript, I.Effect, I.Strength, S.StrengthDesc, I.Comment,I.Direction,M.MechID FROM tblInteractions I LEFT JOIN tblDrugs D1 ON I.SubjectID = D1.DrugID LEFT JOIN tblDrugs D2 ON I.ObjectID = D2.DrugID LEFT JOIN tblMechanisms M ON I.MechID = M.MechID LEFT JOIN tblStrengths S ON I.Strength = S.StrengthID WHERE InteractionID = "
            r1.append(r2)     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
            r1.append(r3)     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
            java.lang.String r3 = " LIMIT 1"
            r1.append(r3)     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
            java.lang.String r3 = r1.toString()     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
            com.medscape.android.db.DatabaseHelper r1 = new com.medscape.android.db.DatabaseHelper     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
            com.medscape.android.MedscapeApplication r2 = com.medscape.android.MedscapeApplication.get()     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x00a3, all -> 0x00a0 }
            android.database.sqlite.SQLiteDatabase r2 = r1.getDatabase()     // Catch:{ Exception -> 0x009e }
            android.database.Cursor r0 = r2.rawQuery(r3, r0)     // Catch:{ Exception -> 0x009e }
            if (r0 == 0) goto L_0x0093
            int r3 = r0.getCount()     // Catch:{ Exception -> 0x009e }
            if (r3 <= 0) goto L_0x0093
        L_0x0030:
            boolean r3 = r0.moveToNext()     // Catch:{ Exception -> 0x009e }
            if (r3 == 0) goto L_0x0081
            r3 = 1
            int r3 = r0.getInt(r3)     // Catch:{ Exception -> 0x009e }
            subjectID = r3     // Catch:{ Exception -> 0x009e }
            r3 = 2
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x009e }
            subjectName = r3     // Catch:{ Exception -> 0x009e }
            r3 = 3
            int r3 = r0.getInt(r3)     // Catch:{ Exception -> 0x009e }
            objectID = r3     // Catch:{ Exception -> 0x009e }
            r3 = 4
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x009e }
            objectName = r3     // Catch:{ Exception -> 0x009e }
            r3 = 5
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x009e }
            mechScript = r3     // Catch:{ Exception -> 0x009e }
            r3 = 6
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x009e }
            effect = r3     // Catch:{ Exception -> 0x009e }
            r3 = 8
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x009e }
            strengthScript = r3     // Catch:{ Exception -> 0x009e }
            r3 = 9
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x009e }
            commentScript = r3     // Catch:{ Exception -> 0x009e }
            r3 = 10
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x009e }
            direction = r3     // Catch:{ Exception -> 0x009e }
            r3 = 11
            int r3 = r0.getInt(r3)     // Catch:{ Exception -> 0x009e }
            mechID = r3     // Catch:{ Exception -> 0x009e }
            goto L_0x0030
        L_0x0081:
            java.lang.String r3 = makeInteractionText()     // Catch:{ Exception -> 0x009e }
            if (r0 == 0) goto L_0x008f
            r0.close()     // Catch:{ Exception -> 0x008b }
            goto L_0x008f
        L_0x008b:
            r0 = move-exception
            r0.printStackTrace()
        L_0x008f:
            r1.close()
            return r3
        L_0x0093:
            if (r0 == 0) goto L_0x00b4
            r0.close()     // Catch:{ Exception -> 0x0099 }
            goto L_0x00b4
        L_0x0099:
            r3 = move-exception
            r3.printStackTrace()
            goto L_0x00b4
        L_0x009e:
            r3 = move-exception
            goto L_0x00a5
        L_0x00a0:
            r3 = move-exception
            r1 = r0
            goto L_0x00bb
        L_0x00a3:
            r3 = move-exception
            r1 = r0
        L_0x00a5:
            r3.printStackTrace()     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x00b2
            r0.close()     // Catch:{ Exception -> 0x00ae }
            goto L_0x00b2
        L_0x00ae:
            r3 = move-exception
            r3.printStackTrace()
        L_0x00b2:
            if (r1 == 0) goto L_0x00b7
        L_0x00b4:
            r1.close()
        L_0x00b7:
            java.lang.String r3 = ""
            return r3
        L_0x00ba:
            r3 = move-exception
        L_0x00bb:
            if (r0 == 0) goto L_0x00c5
            r0.close()     // Catch:{ Exception -> 0x00c1 }
            goto L_0x00c5
        L_0x00c1:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00c5:
            if (r1 == 0) goto L_0x00ca
            r1.close()
        L_0x00ca:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.InteractionsDataUtil.getInteractionDetailText(int):java.lang.String");
    }

    public static String makeInteractionText() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = effect;
        stringBuffer.append(subjectName);
        if (effect.equals("de")) {
            effect = " decreases effects of ";
        } else if (effect.equals("dl")) {
            effect = " decreases levels of ";
        } else if (effect.equals("dt")) {
            effect = " decreases toxicity of ";
        } else if (effect.equals("ie")) {
            effect = " increases effects of ";
        } else if (effect.equals("il")) {
            effect = " increases levels of ";
        } else if (effect.equals("it")) {
            effect = " increases toxicity of ";
        } else if (effect.equals("o")) {
            effect = ", ";
        } else if (effect.equals("")) {
            effect = " and ";
        }
        if (effect.equals(" and ")) {
            if (direction.equals(SearchHelper.TYPE_DRUG)) {
                stringBuffer.append(" and " + objectName + " both decrease");
            } else if (direction.equals("EI")) {
                stringBuffer.append(" will increase the level or effect of" + objectName + " by");
            } else if (direction.equals("I")) {
                stringBuffer.append(" and " + objectName + " both increase");
            } else if (direction.equals(Constants.WBMDTugStringID)) {
                stringBuffer.append(" increases and " + objectName + " decreases");
            } else if (direction.equals("OD")) {
                stringBuffer.append(" will decrease the level or effect of " + objectName + " by");
            } else if (direction.equals("OI")) {
                stringBuffer.append(" will increase the level or effect of " + objectName + " by");
            } else if (direction.equals("SD")) {
                stringBuffer.append(" will decrease the level or effect of " + objectName + " by");
            } else if (direction.equals("SI")) {
                stringBuffer.append(" will increase the level or effect of " + objectName + " by");
            }
            if (!direction.equals(Constants.WBMDTugStringID)) {
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + mechScript + ".");
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + strengthScript + "");
                if (!commentScript.equals("")) {
                    stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + commentScript + "");
                }
            } else {
                stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + mechScript + ". Effect of interaction is not clear, use caution. " + strengthScript + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + commentScript + "");
            }
        } else {
            if (direction.equals(ExifInterface.LONGITUDE_EAST)) {
                stringBuffer.append(", " + objectName + ".\n");
                if (!str.equals("o")) {
                    stringBuffer.append("Either" + effect + "the other");
                }
            } else {
                stringBuffer.append("" + effect + "");
                stringBuffer.append("" + objectName + "");
            }
            if (!str.equals("o")) {
                stringBuffer.append(" by " + mechScript + ".");
            } else if (direction.equals(ExifInterface.LONGITUDE_EAST)) {
                stringBuffer.append("" + mechScript + ".");
            } else {
                stringBuffer.append(". " + mechScript + ".");
            }
            stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + strengthScript + "");
            if (!commentScript.equals("")) {
                if (mechID == 45) {
                    stringBuffer.append(" \nComment: " + commentScript + "");
                } else {
                    stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + commentScript + "");
                }
            }
        }
        return stringBuffer.toString();
    }
}
