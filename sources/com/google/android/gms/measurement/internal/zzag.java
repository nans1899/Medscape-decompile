package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzag {
    private static Set<String> zza(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
        sb.append("SELECT * FROM ");
        sb.append(str);
        sb.append(" LIMIT 0");
        Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), (String[]) null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    static void zza(zzer zzer, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        if (zzer != null) {
            if (!zza(zzer, sQLiteDatabase, str)) {
                sQLiteDatabase.execSQL(str2);
            }
            if (zzer != null) {
                try {
                    Set<String> zza = zza(sQLiteDatabase, str);
                    String[] split = str3.split(",");
                    int length = split.length;
                    int i = 0;
                    while (i < length) {
                        String str4 = split[i];
                        if (zza.remove(str4)) {
                            i++;
                        } else {
                            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 35 + String.valueOf(str4).length());
                            sb.append("Table ");
                            sb.append(str);
                            sb.append(" is missing required column: ");
                            sb.append(str4);
                            throw new SQLiteException(sb.toString());
                        }
                    }
                    if (strArr != null) {
                        for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                            if (!zza.remove(strArr[i2])) {
                                sQLiteDatabase.execSQL(strArr[i2 + 1]);
                            }
                        }
                    }
                    if (!zza.isEmpty()) {
                        zzer.zzh().zza("Table has extra columns. table, columns", str, TextUtils.join(", ", zza));
                    }
                } catch (SQLiteException e) {
                    zzer.zze().zza("Failed to verify columns on table that was just created", str);
                    throw e;
                }
            } else {
                throw new IllegalArgumentException("Monitor must not be null");
            }
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }

    private static boolean zza(zzer zzer, SQLiteDatabase sQLiteDatabase, String str) {
        if (zzer != null) {
            Cursor cursor = null;
            try {
                SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                Cursor query = sQLiteDatabase2.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{str}, (String) null, (String) null, (String) null);
                boolean moveToFirst = query.moveToFirst();
                if (query != null) {
                    query.close();
                }
                return moveToFirst;
            } catch (SQLiteException e) {
                zzer.zzh().zza("Error querying for table", str, e);
                if (cursor != null) {
                    cursor.close();
                }
                return false;
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }

    static void zza(zzer zzer, SQLiteDatabase sQLiteDatabase) {
        if (zzer != null) {
            File file = new File(sQLiteDatabase.getPath());
            if (!file.setReadable(false, false)) {
                zzer.zzh().zza("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzer.zzh().zza("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzer.zzh().zza("Failed to turn on database read permission for owner");
            }
            if (!file.setWritable(true, true)) {
                zzer.zzh().zza("Failed to turn on database write permission for owner");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }
}
