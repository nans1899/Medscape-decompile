package com.wbmd.qxcalculator.model.db.managers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.wbmd.qxcalculator.model.db.DaoMaster;

public class DaoUpgradeHelper extends DaoMaster.OpenHelper {
    private static final String TAG = DaoUpgradeHelper.class.getSimpleName();

    public DaoUpgradeHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
        super(context, str, cursorFactory);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0062, code lost:
        new com.wbmd.qxcalculator.model.db.managers.MigrateV9ToV10().applyMigration(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x006a, code lost:
        new com.wbmd.qxcalculator.model.db.managers.MigrateV10ToV11().applyMigration(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x002a, code lost:
        new com.wbmd.qxcalculator.model.db.managers.MigrateV2ToV3().applyMigration(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0032, code lost:
        new com.wbmd.qxcalculator.model.db.managers.MigrateV3ToV4().applyMigration(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x003a, code lost:
        new com.wbmd.qxcalculator.model.db.managers.MigrateV4ToV5().applyMigration(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0042, code lost:
        new com.wbmd.qxcalculator.model.db.managers.MigrateV5ToV6().applyMigration(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x004a, code lost:
        new com.wbmd.qxcalculator.model.db.managers.MigrateV6ToV7().applyMigration(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0052, code lost:
        new com.wbmd.qxcalculator.model.db.managers.MigrateV7ToV8().applyMigration(r4, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x005a, code lost:
        new com.wbmd.qxcalculator.model.db.managers.MigrateV8ToV9().applyMigration(r4, r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r4, int r5, int r6) {
        /*
            r3 = this;
            java.lang.String r0 = TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Migrating database from version "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r2 = " to version "
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            android.util.Log.i(r0, r1)
            switch(r6) {
                case 2: goto L_0x0022;
                case 3: goto L_0x002a;
                case 4: goto L_0x0032;
                case 5: goto L_0x003a;
                case 6: goto L_0x0042;
                case 7: goto L_0x004a;
                case 8: goto L_0x0052;
                case 9: goto L_0x005a;
                case 10: goto L_0x0062;
                case 11: goto L_0x006a;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x0072
        L_0x0022:
            com.wbmd.qxcalculator.model.db.managers.MigrateV1ToV2 r6 = new com.wbmd.qxcalculator.model.db.managers.MigrateV1ToV2
            r6.<init>()
            r6.applyMigration(r4, r5)
        L_0x002a:
            com.wbmd.qxcalculator.model.db.managers.MigrateV2ToV3 r6 = new com.wbmd.qxcalculator.model.db.managers.MigrateV2ToV3
            r6.<init>()
            r6.applyMigration(r4, r5)
        L_0x0032:
            com.wbmd.qxcalculator.model.db.managers.MigrateV3ToV4 r6 = new com.wbmd.qxcalculator.model.db.managers.MigrateV3ToV4
            r6.<init>()
            r6.applyMigration(r4, r5)
        L_0x003a:
            com.wbmd.qxcalculator.model.db.managers.MigrateV4ToV5 r6 = new com.wbmd.qxcalculator.model.db.managers.MigrateV4ToV5
            r6.<init>()
            r6.applyMigration(r4, r5)
        L_0x0042:
            com.wbmd.qxcalculator.model.db.managers.MigrateV5ToV6 r6 = new com.wbmd.qxcalculator.model.db.managers.MigrateV5ToV6
            r6.<init>()
            r6.applyMigration(r4, r5)
        L_0x004a:
            com.wbmd.qxcalculator.model.db.managers.MigrateV6ToV7 r6 = new com.wbmd.qxcalculator.model.db.managers.MigrateV6ToV7
            r6.<init>()
            r6.applyMigration(r4, r5)
        L_0x0052:
            com.wbmd.qxcalculator.model.db.managers.MigrateV7ToV8 r6 = new com.wbmd.qxcalculator.model.db.managers.MigrateV7ToV8
            r6.<init>()
            r6.applyMigration(r4, r5)
        L_0x005a:
            com.wbmd.qxcalculator.model.db.managers.MigrateV8ToV9 r6 = new com.wbmd.qxcalculator.model.db.managers.MigrateV8ToV9
            r6.<init>()
            r6.applyMigration(r4, r5)
        L_0x0062:
            com.wbmd.qxcalculator.model.db.managers.MigrateV9ToV10 r6 = new com.wbmd.qxcalculator.model.db.managers.MigrateV9ToV10
            r6.<init>()
            r6.applyMigration(r4, r5)
        L_0x006a:
            com.wbmd.qxcalculator.model.db.managers.MigrateV10ToV11 r6 = new com.wbmd.qxcalculator.model.db.managers.MigrateV10ToV11
            r6.<init>()
            r6.applyMigration(r4, r5)
        L_0x0072:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.managers.DaoUpgradeHelper.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }
}
