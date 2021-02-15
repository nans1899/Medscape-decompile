package com.medscape.android.db;

import android.content.Context;
import android.database.Cursor;
import com.medscape.android.drugs.model.DrugsContract;

public class DrugDataHelper {
    public static int findContentIdFromUniqueId(Context context, String str) {
        int i = -1;
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT ContentID FROM tblDrugs WHERE UniqueID = ? LIMIT 1", new String[]{String.valueOf(str)});
            while (rawQuery.moveToNext()) {
                i = rawQuery.getInt(rawQuery.getColumnIndex(rawQuery.getColumnName(0)));
            }
            rawQuery.close();
            databaseHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static int findUniqueIdFromContentId(Context context, int i, String str) {
        int i2 = -1;
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT UniqueID FROM tblDrugs WHERE ContentID = ? AND DrugName = ? LIMIT 1", new String[]{String.valueOf(i), String.valueOf(str)});
            while (rawQuery.moveToNext()) {
                i2 = rawQuery.getInt(rawQuery.getColumnIndex(rawQuery.getColumnName(0)));
            }
            rawQuery.close();
            databaseHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i2;
    }

    public static int findGenericIdFromContentId(Context context, int i, String str) {
        int i2 = -1;
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(context);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT GenericID FROM tblDrugs WHERE ContentID = ? AND DrugName = ? LIMIT 1", new String[]{String.valueOf(i), String.valueOf(str)});
            while (rawQuery.moveToNext()) {
                i2 = rawQuery.getInt(rawQuery.getColumnIndex(DrugsContract.Drug.GENERIC_ID));
            }
            rawQuery.close();
            databaseHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i2;
    }
}
