package com.medscape.android.activity.formulary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.helper.FileHelper;
import java.io.File;

public class FormularyDatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "formulary.sqlite";
    private static volatile FormularyDatabaseHelper INSTANCE = null;
    public static final String PARSE_CLASS_QUERY = "SELECT N.ClassID as classId, N.ClassName as className FROM tblClassMapping M, tblClassNames N WHERE M.ClassID = N.ClassID AND (N.ParentID IS NOT NULL OR N.SingleLevel = 1) AND ContentID = ?";
    public static final String PARSE_FORMULARY_CLASS_QUERY = "SELECT F.tierName, F.restrictionCodes FROM tblFormulary F WHERE F.brandId = ? AND F.planId = ?";
    public static final String PARSE_FORMULARY_QUERY = "SELECT P.planName, P.planId, P.isMedicare, P.stateAbbreviation, F.tierName, F.restrictionCodes FROM tblFormulary F JOIN tblUserPlan P ON F.planId = P.planId WHERE F.brandId = ?";
    public static final String SELECT_PLAN = "select planId, stateId from tblUserPlan";
    private SQLiteDatabase mSQLiteDatabase;

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    private FormularyDatabaseHelper(Context context) {
        super(context, DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    public static FormularyDatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (FormularyDatabaseHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FormularyDatabaseHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public String getDbPath() {
        return FileHelper.getDataDirectory(MedscapeApplication.get());
    }

    public boolean isDatabaseExists() {
        return new File(getDbPath() + "/Medscape/" + DB_NAME).exists();
    }

    private SQLiteDatabase openDataBase() throws Exception {
        SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(getDbPath() + "/Medscape/" + DB_NAME, (SQLiteDatabase.CursorFactory) null, 16);
        this.mSQLiteDatabase = openDatabase;
        return openDatabase;
    }

    public SQLiteDatabase getDatabase() {
        try {
            if (!isDatabaseExists()) {
                return null;
            }
            if (this.mSQLiteDatabase == null || !this.mSQLiteDatabase.isOpen()) {
                return openDataBase();
            }
            return this.mSQLiteDatabase;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close() {
        SQLiteDatabase sQLiteDatabase;
        if (isDatabaseExists() && (sQLiteDatabase = this.mSQLiteDatabase) != null && sQLiteDatabase.isOpen()) {
            this.mSQLiteDatabase.close();
        }
    }

    public boolean isValidDatabse(Context context) {
        Boolean bool = false;
        if (isDatabaseExists()) {
            try {
                SQLiteDatabase database = getInstance(context).getDatabase();
                if (database != null) {
                    Cursor rawQuery = database.rawQuery("select planId from tblUserPlan", (String[]) null);
                    if (rawQuery != null) {
                        bool = true;
                        rawQuery.close();
                    }
                    database.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bool.booleanValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x004c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getUserPlans(android.content.Context r5) {
        /*
            r4 = this;
            boolean r0 = r4.isDatabaseExists()
            r1 = 0
            if (r0 == 0) goto L_0x0050
            r0 = 0
            com.medscape.android.activity.formulary.FormularyDatabaseHelper r5 = getInstance(r5)     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            android.database.sqlite.SQLiteDatabase r5 = r5.getDatabase()     // Catch:{ Exception -> 0x0034, all -> 0x0031 }
            java.lang.String r2 = "SELECT planId, stateId FROM tblUserPlan"
            android.database.Cursor r0 = r5.rawQuery(r2, r0)     // Catch:{ Exception -> 0x002c, all -> 0x0027 }
            if (r0 == 0) goto L_0x001c
            int r1 = r0.getCount()     // Catch:{ Exception -> 0x002c, all -> 0x0027 }
        L_0x001c:
            if (r5 == 0) goto L_0x0021
            r5.close()
        L_0x0021:
            if (r0 == 0) goto L_0x0050
            r0.close()
            goto L_0x0050
        L_0x0027:
            r1 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L_0x0045
        L_0x002c:
            r2 = move-exception
            r3 = r0
            r0 = r5
            r5 = r3
            goto L_0x0036
        L_0x0031:
            r1 = move-exception
            r5 = r0
            goto L_0x0045
        L_0x0034:
            r2 = move-exception
            r5 = r0
        L_0x0036:
            r2.printStackTrace()     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x003e
            r0.close()
        L_0x003e:
            if (r5 == 0) goto L_0x0050
            r5.close()
            goto L_0x0050
        L_0x0044:
            r1 = move-exception
        L_0x0045:
            if (r0 == 0) goto L_0x004a
            r0.close()
        L_0x004a:
            if (r5 == 0) goto L_0x004f
            r5.close()
        L_0x004f:
            throw r1
        L_0x0050:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.formulary.FormularyDatabaseHelper.getUserPlans(android.content.Context):int");
    }
}
