package com.medscape.android.db;

import android.database.Cursor;
import com.medscape.android.db.model.FeedMasters;
import java.util.ArrayList;
import java.util.List;

public class FeedMaster {
    public static final String[] ALL_COLUMN_NAMES = {"specialtyID", F_SPECIALTY_NAME};
    private static final String COUNT_STATEMENT = "SELECT COUNT(*) FROM tblSpecialtyMaster";
    public static final String F_SPECIALTY_ID = "specialtyID";
    public static final String F_SPECIALTY_NAME = "specialtyName";
    public static final String TABLE_NAME = "tblSpecialtyMaster";
    private static final String TAG = "FeedMaster";

    public static List<FeedMasters> getAllSpecialtyByNameForCME(DatabaseHelper databaseHelper) {
        try {
            List<FeedMasters> allFeedMasters = getAllFeedMasters(databaseHelper.getDatabase().rawQuery("select * from tblSpecialtyMaster t1, tblSpecialtyPillar t2 WHERE t1.specialtyID = t2.specialtyID AND t2.pillar = 1  order by specialtyName", (String[]) null));
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            return allFeedMasters;
        } catch (Exception e) {
            e.printStackTrace();
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            return null;
        } catch (Throwable th) {
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            throw th;
        }
    }

    public static List<FeedMasters> getDefaultSpecialtyForCME(DatabaseHelper databaseHelper) {
        try {
            List<FeedMasters> allFeedMasters = getAllFeedMasters(databaseHelper.getDatabase().rawQuery("select * from tblSpecialtyMaster t1, tblSpecialtyPillar t2  WHERE t1.specialtyID = t2.specialtyID   AND t2.pillar = 1  AND t2.isDefault = 1 ", (String[]) null));
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            return allFeedMasters;
        } catch (Exception e) {
            e.printStackTrace();
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            return null;
        } catch (Throwable th) {
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            throw th;
        }
    }

    public static List<FeedMasters> getDefaultSpecialtyForNEWS(DatabaseHelper databaseHelper) {
        try {
            List<FeedMasters> allFeedMasters = getAllFeedMasters(databaseHelper.getDatabase().rawQuery("select * from tblSpecialtyMaster t1, tblSpecialtyPillar t2  WHERE t1.specialtyID = t2.specialtyID   AND t2.pillar = 2  AND t2.isDefault = 1 ", (String[]) null));
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            return allFeedMasters;
        } catch (Exception e) {
            e.printStackTrace();
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            return null;
        } catch (Throwable th) {
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            throw th;
        }
    }

    public static List<FeedMasters> getAllSpecialtyByNameforNEWS(DatabaseHelper databaseHelper) {
        try {
            List<FeedMasters> allFeedMasters = getAllFeedMasters(databaseHelper.getDatabase().rawQuery("select * from tblSpecialtyMaster t1, tblSpecialtyPillar t2  WHERE t1.specialtyID = t2.specialtyID AND t2.pillar = 2  order by specialtyName", (String[]) null));
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            return allFeedMasters;
        } catch (Exception e) {
            e.printStackTrace();
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            return null;
        } catch (Throwable th) {
            if (databaseHelper != null) {
                databaseHelper.close();
            }
            throw th;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.medscape.android.db.model.FeedMasters} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        if (r4 == null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        r4.close();
        r1 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x004b, code lost:
        if (r4 == null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0054, code lost:
        if (r4 == null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0057, code lost:
        if (r1 != 0) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0062, code lost:
        if (r5[1].equalsIgnoreCase(androidx.exifinterface.media.ExifInterface.GPS_MEASUREMENT_2D) == false) goto L_0x0069;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0064, code lost:
        r4 = getDefaultSpecialtyForNEWS(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0069, code lost:
        r4 = getDefaultSpecialtyForCME(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        return r4.get(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return r1;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.medscape.android.db.model.FeedMasters getOneOrDefaultBySpecialtyId(com.medscape.android.db.DatabaseHelper r4, java.lang.String... r5) {
        /*
            java.lang.String r0 = "select * from tblSpecialtyMaster t1, tblSpecialtyPillar t2  WHERE t1.specialtyID = t2.specialtyID   AND t1.specialtyID = ?  AND t2.pillar = ? "
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r4.getDatabase()     // Catch:{ SQLiteException -> 0x004e, Exception -> 0x0045, all -> 0x0039 }
            android.database.Cursor r2 = r2.rawQuery(r0, r5)     // Catch:{ SQLiteException -> 0x004e, Exception -> 0x0045, all -> 0x0039 }
            if (r2 == 0) goto L_0x001f
            int r3 = r2.getCount()     // Catch:{ SQLiteException -> 0x0037, Exception -> 0x0035, all -> 0x0032 }
            if (r3 <= 0) goto L_0x001f
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0037, Exception -> 0x0035, all -> 0x0032 }
            if (r0 == 0) goto L_0x0027
            com.medscape.android.db.model.FeedMasters r0 = getFeedMasters(r2)     // Catch:{ SQLiteException -> 0x0037, Exception -> 0x0035, all -> 0x0032 }
            r1 = r0
            goto L_0x0027
        L_0x001f:
            android.database.sqlite.SQLiteDatabase r3 = r4.getDatabase()     // Catch:{ SQLiteException -> 0x0037, Exception -> 0x0035, all -> 0x0032 }
            android.database.Cursor r2 = r3.rawQuery(r0, r5)     // Catch:{ SQLiteException -> 0x0037, Exception -> 0x0035, all -> 0x0032 }
        L_0x0027:
            if (r2 == 0) goto L_0x002c
            r2.close()
        L_0x002c:
            if (r4 == 0) goto L_0x0057
        L_0x002e:
            r4.close()
            goto L_0x0057
        L_0x0032:
            r5 = move-exception
            r1 = r2
            goto L_0x003a
        L_0x0035:
            goto L_0x0046
        L_0x0037:
            goto L_0x004f
        L_0x0039:
            r5 = move-exception
        L_0x003a:
            if (r1 == 0) goto L_0x003f
            r1.close()
        L_0x003f:
            if (r4 == 0) goto L_0x0044
            r4.close()
        L_0x0044:
            throw r5
        L_0x0045:
            r2 = r1
        L_0x0046:
            if (r2 == 0) goto L_0x004b
            r2.close()
        L_0x004b:
            if (r4 == 0) goto L_0x0057
            goto L_0x002e
        L_0x004e:
            r2 = r1
        L_0x004f:
            if (r2 == 0) goto L_0x0054
            r2.close()
        L_0x0054:
            if (r4 == 0) goto L_0x0057
            goto L_0x002e
        L_0x0057:
            if (r1 != 0) goto L_0x0075
            r0 = 1
            r5 = r5[r0]     // Catch:{ Exception -> 0x0075 }
            java.lang.String r0 = "2"
            boolean r5 = r5.equalsIgnoreCase(r0)     // Catch:{ Exception -> 0x0075 }
            if (r5 == 0) goto L_0x0069
            java.util.List r4 = getDefaultSpecialtyForNEWS(r4)     // Catch:{ Exception -> 0x0075 }
            goto L_0x006d
        L_0x0069:
            java.util.List r4 = getDefaultSpecialtyForCME(r4)     // Catch:{ Exception -> 0x0075 }
        L_0x006d:
            r5 = 0
            java.lang.Object r4 = r4.get(r5)     // Catch:{ Exception -> 0x0075 }
            com.medscape.android.db.model.FeedMasters r4 = (com.medscape.android.db.model.FeedMasters) r4     // Catch:{ Exception -> 0x0075 }
            r1 = r4
        L_0x0075:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.db.FeedMaster.getOneOrDefaultBySpecialtyId(com.medscape.android.db.DatabaseHelper, java.lang.String[]):com.medscape.android.db.model.FeedMasters");
    }

    public static String getSpecialtyNameOrDefaultBySpecialtyId(DatabaseHelper databaseHelper, String... strArr) {
        FeedMasters oneOrDefaultBySpecialtyId = getOneOrDefaultBySpecialtyId(databaseHelper, strArr);
        if (oneOrDefaultBySpecialtyId == null) {
            return "";
        }
        return oneOrDefaultBySpecialtyId.getSpecilatyName();
    }

    public static int getSpecialtyIdOrDefaultBySpecialtyId(DatabaseHelper databaseHelper, String... strArr) {
        FeedMasters oneOrDefaultBySpecialtyId = getOneOrDefaultBySpecialtyId(databaseHelper, strArr);
        if (oneOrDefaultBySpecialtyId == null) {
            return -1;
        }
        return oneOrDefaultBySpecialtyId.getSpecilatyId();
    }

    public static FeedMasters getFeedMasters(Cursor cursor) {
        FeedMasters feedMasters = new FeedMasters();
        feedMasters.setSpecilatyId(cursor.getInt(cursor.getColumnIndex("specialtyID")));
        feedMasters.setSpecilatyName(cursor.getString(cursor.getColumnIndex(F_SPECIALTY_NAME)));
        return feedMasters;
    }

    public static List<FeedMasters> getAllFeedMasters(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                FeedMasters feedMasters = new FeedMasters();
                feedMasters.setSpecilatyId(cursor.getInt(cursor.getColumnIndex("specialtyID")));
                feedMasters.setSpecilatyName(cursor.getString(cursor.getColumnIndex(F_SPECIALTY_NAME)));
                cursor.moveToNext();
                arrayList.add(feedMasters);
            }
        }
        cursor.close();
        return arrayList;
    }
}
