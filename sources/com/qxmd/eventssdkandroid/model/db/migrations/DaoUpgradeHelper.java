package com.qxmd.eventssdkandroid.model.db.migrations;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.qxmd.eventssdkandroid.model.db.DaoMaster;
import com.qxmd.eventssdkandroid.util.Log;

public class DaoUpgradeHelper extends DaoMaster.OpenHelper {
    private static final String TAG = DaoUpgradeHelper.class.getSimpleName();

    public DaoUpgradeHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
        super(context, str, cursorFactory);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str = TAG;
        Log.i(str, "Migrating database from version " + i + " to version " + i2);
        if (i2 == 2) {
            new MigrateV1ToV2().applyMigration(sQLiteDatabase, i);
        } else if (i2 != 3) {
            return;
        }
        new MigrateV2ToV3().applyMigration(sQLiteDatabase, i);
    }
}
