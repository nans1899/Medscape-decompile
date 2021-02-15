package com.medscape.android.db;

import android.database.Cursor;
import com.medscape.android.db.model.FeedDetails;

public class FeedDetail {
    public static final String[] ALL_COLUMN_NAMES = {"specialtyID", F_URL, F_FEED_TYPE, F_FUTUREUSE01};
    public static final String F_FEED_TYPE = "FeedType";
    public static final String F_FUTUREUSE01 = "FutureUse01";
    public static final String F_SPECIALTY_ID = "specialtyID";
    public static final String F_URL = "URL";
    public static final String TABLE_NAME = "tblFeedDetail";

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005a, code lost:
        if (r14 != null) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005c, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0071, code lost:
        if (r14 == null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0074, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.medscape.android.db.model.FeedDetails findOneBySpecialtyAndFeedType(com.medscape.android.db.DatabaseHelper r14, java.lang.String... r15) {
        /*
            r0 = 0
            if (r14 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 1
            r2 = r15[r1]
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            int r2 = r2.intValue()
            r3 = 2
            int r2 = r2 % r3
            if (r2 == 0) goto L_0x0021
            r2 = r15[r1]
            java.lang.String r4 = "7"
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x001e
            goto L_0x0021
        L_0x001e:
            java.lang.String r2 = "1"
            goto L_0x0023
        L_0x0021:
            java.lang.String r2 = "2"
        L_0x0023:
            java.lang.String[] r3 = new java.lang.String[r3]
            r4 = 0
            r5 = r15[r4]
            r3[r4] = r5
            r3[r1] = r2
            int r1 = com.medscape.android.db.FeedMaster.getSpecialtyIdOrDefaultBySpecialtyId(r14, r3)
            r2 = -1
            if (r1 == r2) goto L_0x0039
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r15[r4] = r1
        L_0x0039:
            android.database.sqlite.SQLiteDatabase r5 = r14.getDatabase()     // Catch:{ Exception -> 0x0067, all -> 0x0062 }
            java.lang.String r6 = "tblFeedDetail"
            java.lang.String[] r7 = ALL_COLUMN_NAMES     // Catch:{ Exception -> 0x0067, all -> 0x0062 }
            java.lang.String r8 = "specialtyID = ? and FeedType = ? "
            r10 = 0
            r11 = 0
            r12 = 0
            r9 = r15
            android.database.Cursor r15 = r5.query(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ Exception -> 0x0067, all -> 0x0062 }
            boolean r1 = r15.moveToFirst()     // Catch:{ Exception -> 0x0060 }
            if (r1 == 0) goto L_0x0055
            com.medscape.android.db.model.FeedDetails r0 = getFeedDetail(r15)     // Catch:{ Exception -> 0x0060 }
        L_0x0055:
            if (r15 == 0) goto L_0x005a
            r15.close()
        L_0x005a:
            if (r14 == 0) goto L_0x0074
        L_0x005c:
            r14.close()
            goto L_0x0074
        L_0x0060:
            r1 = move-exception
            goto L_0x0069
        L_0x0062:
            r15 = move-exception
            r13 = r0
            r0 = r15
            r15 = r13
            goto L_0x0076
        L_0x0067:
            r1 = move-exception
            r15 = r0
        L_0x0069:
            r1.printStackTrace()     // Catch:{ all -> 0x0075 }
            if (r15 == 0) goto L_0x0071
            r15.close()
        L_0x0071:
            if (r14 == 0) goto L_0x0074
            goto L_0x005c
        L_0x0074:
            return r0
        L_0x0075:
            r0 = move-exception
        L_0x0076:
            if (r15 == 0) goto L_0x007b
            r15.close()
        L_0x007b:
            if (r14 == 0) goto L_0x0080
            r14.close()
        L_0x0080:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.db.FeedDetail.findOneBySpecialtyAndFeedType(com.medscape.android.db.DatabaseHelper, java.lang.String[]):com.medscape.android.db.model.FeedDetails");
    }

    public static FeedDetails getFeedDetail(Cursor cursor) {
        FeedDetails feedDetails = new FeedDetails();
        feedDetails.setFeedType(cursor.getInt(cursor.getColumnIndex(F_FEED_TYPE)));
        feedDetails.setUrl(cursor.getString(cursor.getColumnIndex(F_URL)));
        feedDetails.setSpecilatyId(cursor.getInt(cursor.getColumnIndex("specialtyID")));
        return feedDetails;
    }
}
