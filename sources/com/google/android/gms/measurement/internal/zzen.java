package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.util.Clock;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.6.0 */
public final class zzen extends zzg {
    private final zzem zza = new zzem(this, zzm(), "google_app_measurement_local.db");
    private boolean zzb;

    zzen(zzfv zzfv) {
        super(zzfv);
    }

    /* access modifiers changed from: protected */
    public final boolean zzy() {
        return false;
    }

    public final void zzaa() {
        zzc();
        try {
            int delete = zzad().delete("messages", (String) null, (String[]) null) + 0;
            if (delete > 0) {
                zzq().zzw().zza("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzq().zze().zza("Error resetting local analytics data. error", e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v6, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [int, boolean] */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r7v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v4, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c1 A[SYNTHETIC, Splitter:B:47:0x00c1] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x010f  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0117 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0117 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int r17, byte[] r18) {
        /*
            r16 = this;
            r1 = r16
            r16.zzc()
            boolean r0 = r1.zzb
            r2 = 0
            if (r0 == 0) goto L_0x000b
            return r2
        L_0x000b:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r17)
            java.lang.String r4 = "type"
            r3.put(r4, r0)
            java.lang.String r0 = "entry"
            r4 = r18
            r3.put(r0, r4)
            r4 = 5
            r5 = 0
            r6 = 5
        L_0x0023:
            if (r5 >= r4) goto L_0x0129
            r7 = 0
            r8 = 1
            android.database.sqlite.SQLiteDatabase r9 = r16.zzad()     // Catch:{ SQLiteFullException -> 0x00fb, SQLiteDatabaseLockedException -> 0x00e9, SQLiteException -> 0x00bd, all -> 0x00ba }
            if (r9 != 0) goto L_0x0035
            r1.zzb = r8     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteDatabaseLockedException -> 0x00ea, SQLiteException -> 0x00b4 }
            if (r9 == 0) goto L_0x0034
            r9.close()
        L_0x0034:
            return r2
        L_0x0035:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteDatabaseLockedException -> 0x00ea, SQLiteException -> 0x00b4 }
            r10 = 0
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r12 = r9.rawQuery(r0, r7)     // Catch:{ SQLiteFullException -> 0x00b8, SQLiteDatabaseLockedException -> 0x00ea, SQLiteException -> 0x00b4 }
            if (r12 == 0) goto L_0x0056
            boolean r0 = r12.moveToFirst()     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            if (r0 == 0) goto L_0x0056
            long r10 = r12.getLong(r2)     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            goto L_0x0056
        L_0x004d:
            r0 = move-exception
            goto L_0x00e7
        L_0x0050:
            r0 = move-exception
            goto L_0x00b6
        L_0x0052:
            r0 = move-exception
            r7 = r12
            goto L_0x00fd
        L_0x0056:
            java.lang.String r0 = "messages"
            r13 = 100000(0x186a0, double:4.94066E-319)
            int r15 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r15 < 0) goto L_0x009d
            com.google.android.gms.measurement.internal.zzer r15 = r16.zzq()     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            com.google.android.gms.measurement.internal.zzet r15 = r15.zze()     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            java.lang.String r4 = "Data loss, local db full"
            r15.zza(r4)     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            long r13 = r13 - r10
            r10 = 1
            long r13 = r13 + r10
            java.lang.String r4 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String[] r10 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            java.lang.String r11 = java.lang.Long.toString(r13)     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            r10[r2] = r11     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            int r4 = r9.delete(r0, r4, r10)     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            long r10 = (long) r4     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            int r4 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r4 == 0) goto L_0x009d
            com.google.android.gms.measurement.internal.zzer r4 = r16.zzq()     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            com.google.android.gms.measurement.internal.zzet r4 = r4.zze()     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            java.lang.String r15 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r2 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            java.lang.Long r8 = java.lang.Long.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            long r13 = r13 - r10
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            r4.zza(r15, r2, r8, r10)     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
        L_0x009d:
            r9.insertOrThrow(r0, r7, r3)     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x0052, SQLiteDatabaseLockedException -> 0x00b2, SQLiteException -> 0x0050, all -> 0x004d }
            if (r12 == 0) goto L_0x00ab
            r12.close()
        L_0x00ab:
            if (r9 == 0) goto L_0x00b0
            r9.close()
        L_0x00b0:
            r2 = 1
            return r2
        L_0x00b2:
            r7 = r12
            goto L_0x00ea
        L_0x00b4:
            r0 = move-exception
            r12 = r7
        L_0x00b6:
            r7 = r9
            goto L_0x00bf
        L_0x00b8:
            r0 = move-exception
            goto L_0x00fd
        L_0x00ba:
            r0 = move-exception
            r9 = r7
            goto L_0x011e
        L_0x00bd:
            r0 = move-exception
            r12 = r7
        L_0x00bf:
            if (r7 == 0) goto L_0x00ca
            boolean r2 = r7.inTransaction()     // Catch:{ all -> 0x00e5 }
            if (r2 == 0) goto L_0x00ca
            r7.endTransaction()     // Catch:{ all -> 0x00e5 }
        L_0x00ca:
            com.google.android.gms.measurement.internal.zzer r2 = r16.zzq()     // Catch:{ all -> 0x00e5 }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zze()     // Catch:{ all -> 0x00e5 }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zza(r4, r0)     // Catch:{ all -> 0x00e5 }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x00e5 }
            if (r12 == 0) goto L_0x00df
            r12.close()
        L_0x00df:
            if (r7 == 0) goto L_0x0117
            r7.close()
            goto L_0x0117
        L_0x00e5:
            r0 = move-exception
            r9 = r7
        L_0x00e7:
            r7 = r12
            goto L_0x011e
        L_0x00e9:
            r9 = r7
        L_0x00ea:
            long r10 = (long) r6
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x011d }
            int r6 = r6 + 20
            if (r7 == 0) goto L_0x00f5
            r7.close()
        L_0x00f5:
            if (r9 == 0) goto L_0x0117
            r9.close()
            goto L_0x0117
        L_0x00fb:
            r0 = move-exception
            r9 = r7
        L_0x00fd:
            com.google.android.gms.measurement.internal.zzer r2 = r16.zzq()     // Catch:{ all -> 0x011d }
            com.google.android.gms.measurement.internal.zzet r2 = r2.zze()     // Catch:{ all -> 0x011d }
            java.lang.String r4 = "Error writing entry; local database full"
            r2.zza(r4, r0)     // Catch:{ all -> 0x011d }
            r2 = 1
            r1.zzb = r2     // Catch:{ all -> 0x011d }
            if (r7 == 0) goto L_0x0112
            r7.close()
        L_0x0112:
            if (r9 == 0) goto L_0x0117
            r9.close()
        L_0x0117:
            int r5 = r5 + 1
            r2 = 0
            r4 = 5
            goto L_0x0023
        L_0x011d:
            r0 = move-exception
        L_0x011e:
            if (r7 == 0) goto L_0x0123
            r7.close()
        L_0x0123:
            if (r9 == 0) goto L_0x0128
            r9.close()
        L_0x0128:
            throw r0
        L_0x0129:
            com.google.android.gms.measurement.internal.zzer r0 = r16.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzw()
            java.lang.String r2 = "Failed to write entry to local database"
            r0.zza(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzen.zza(int, byte[]):boolean");
    }

    public final boolean zza(zzar zzar) {
        Parcel obtain = Parcel.obtain();
        zzar.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzq().zzf().zza("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzkr zzkr) {
        Parcel obtain = Parcel.obtain();
        zzkr.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzq().zzf().zza("User property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zza(zzw zzw) {
        zzo();
        byte[] zza2 = zzkw.zza((Parcelable) zzw);
        if (zza2.length <= 131072) {
            return zza(2, zza2);
        }
        zzq().zzf().zza("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:60|61|62|63) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:75|76|77|78) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:47|48|49|50|175) */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x018c, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x0193, code lost:
        r5 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        zzq().zze().zza("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r11.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        zzq().zze().zza("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        r11.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:?, code lost:
        zzq().zze().zza("Failed to load conditional user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r11.recycle();
        r0 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00a1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:60:0x00d1 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:75:0x0107 */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x018c A[ExcHandler: all (th java.lang.Throwable), Splitter:B:12:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:112:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x0026] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01a3 A[SYNTHETIC, Splitter:B:125:0x01a3] */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x01ee  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x01fb  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x01f1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x01f1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x01f1 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zza(int r22) {
        /*
            r21 = this;
            r1 = r21
            java.lang.String r2 = "Error reading entries from local database"
            r21.zzc()
            boolean r0 = r1.zzb
            r3 = 0
            if (r0 == 0) goto L_0x000d
            return r3
        L_0x000d:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            boolean r0 = r21.zzae()
            if (r0 != 0) goto L_0x0019
            return r4
        L_0x0019:
            r5 = 5
            r6 = 0
            r7 = 0
            r8 = 5
        L_0x001d:
            if (r7 >= r5) goto L_0x0204
            r9 = 1
            android.database.sqlite.SQLiteDatabase r15 = r21.zzad()     // Catch:{ SQLiteFullException -> 0x01d7, SQLiteDatabaseLockedException -> 0x01c4, SQLiteException -> 0x019e, all -> 0x019b }
            if (r15 != 0) goto L_0x0034
            r1.zzb = r9     // Catch:{ SQLiteFullException -> 0x0031, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x002e, all -> 0x018c }
            if (r15 == 0) goto L_0x002d
            r15.close()
        L_0x002d:
            return r3
        L_0x002e:
            r0 = move-exception
            goto L_0x0191
        L_0x0031:
            r0 = move-exception
            goto L_0x0199
        L_0x0034:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0197, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x018f, all -> 0x018c }
            long r10 = zza((android.database.sqlite.SQLiteDatabase) r15)     // Catch:{ SQLiteFullException -> 0x0197, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x018f, all -> 0x018c }
            r19 = -1
            int r0 = (r10 > r19 ? 1 : (r10 == r19 ? 0 : -1))
            if (r0 == 0) goto L_0x004e
            java.lang.String r0 = "rowid<?"
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x0031, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x002e, all -> 0x018c }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x0031, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x002e, all -> 0x018c }
            r12[r6] = r10     // Catch:{ SQLiteFullException -> 0x0031, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x002e, all -> 0x018c }
            r13 = r0
            r14 = r12
            goto L_0x0050
        L_0x004e:
            r13 = r3
            r14 = r13
        L_0x0050:
            java.lang.String r11 = "messages"
            java.lang.String r0 = "rowid"
            java.lang.String r10 = "type"
            java.lang.String r12 = "entry"
            java.lang.String[] r12 = new java.lang.String[]{r0, r10, r12}     // Catch:{ SQLiteFullException -> 0x0197, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x018f, all -> 0x018c }
            r0 = 0
            r16 = 0
            java.lang.String r17 = "rowid asc"
            r10 = 100
            java.lang.String r18 = java.lang.Integer.toString(r10)     // Catch:{ SQLiteFullException -> 0x0197, SQLiteDatabaseLockedException -> 0x0193, SQLiteException -> 0x018f, all -> 0x018c }
            r10 = r15
            r5 = r15
            r15 = r0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ SQLiteFullException -> 0x0187, SQLiteDatabaseLockedException -> 0x0194, SQLiteException -> 0x0183, all -> 0x0180 }
        L_0x006e:
            boolean r0 = r10.moveToNext()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            if (r0 == 0) goto L_0x0144
            long r19 = r10.getLong(r6)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r0 = r10.getInt(r9)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            r11 = 2
            byte[] r12 = r10.getBlob(r11)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            if (r0 != 0) goto L_0x00b6
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x00a1 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x00a1 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x00a1 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzar> r0 = com.google.android.gms.measurement.internal.zzar.CREATOR     // Catch:{ ParseException -> 0x00a1 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x00a1 }
            com.google.android.gms.measurement.internal.zzar r0 = (com.google.android.gms.measurement.internal.zzar) r0     // Catch:{ ParseException -> 0x00a1 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            if (r0 == 0) goto L_0x006e
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x009f:
            r0 = move-exception
            goto L_0x00b2
        L_0x00a1:
            com.google.android.gms.measurement.internal.zzer r0 = r21.zzq()     // Catch:{ all -> 0x009f }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zze()     // Catch:{ all -> 0x009f }
            java.lang.String r12 = "Failed to load event from local database"
            r0.zza(r12)     // Catch:{ all -> 0x009f }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x00b2:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            throw r0     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
        L_0x00b6:
            if (r0 != r9) goto L_0x00ec
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x00d1 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x00d1 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x00d1 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzkr> r0 = com.google.android.gms.measurement.internal.zzkr.CREATOR     // Catch:{ ParseException -> 0x00d1 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x00d1 }
            com.google.android.gms.measurement.internal.zzkr r0 = (com.google.android.gms.measurement.internal.zzkr) r0     // Catch:{ ParseException -> 0x00d1 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x00e2
        L_0x00cf:
            r0 = move-exception
            goto L_0x00e8
        L_0x00d1:
            com.google.android.gms.measurement.internal.zzer r0 = r21.zzq()     // Catch:{ all -> 0x00cf }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zze()     // Catch:{ all -> 0x00cf }
            java.lang.String r12 = "Failed to load user property from local database"
            r0.zza(r12)     // Catch:{ all -> 0x00cf }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            r0 = r3
        L_0x00e2:
            if (r0 == 0) goto L_0x006e
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x00e8:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            throw r0     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
        L_0x00ec:
            if (r0 != r11) goto L_0x0123
            android.os.Parcel r11 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x0107 }
            r11.unmarshall(r12, r6, r0)     // Catch:{ ParseException -> 0x0107 }
            r11.setDataPosition(r6)     // Catch:{ ParseException -> 0x0107 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzw> r0 = com.google.android.gms.measurement.internal.zzw.CREATOR     // Catch:{ ParseException -> 0x0107 }
            java.lang.Object r0 = r0.createFromParcel(r11)     // Catch:{ ParseException -> 0x0107 }
            com.google.android.gms.measurement.internal.zzw r0 = (com.google.android.gms.measurement.internal.zzw) r0     // Catch:{ ParseException -> 0x0107 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x0118
        L_0x0105:
            r0 = move-exception
            goto L_0x011f
        L_0x0107:
            com.google.android.gms.measurement.internal.zzer r0 = r21.zzq()     // Catch:{ all -> 0x0105 }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zze()     // Catch:{ all -> 0x0105 }
            java.lang.String r12 = "Failed to load conditional user property from local database"
            r0.zza(r12)     // Catch:{ all -> 0x0105 }
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            r0 = r3
        L_0x0118:
            if (r0 == 0) goto L_0x006e
            r4.add(r0)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x011f:
            r11.recycle()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            throw r0     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
        L_0x0123:
            r11 = 3
            if (r0 != r11) goto L_0x0135
            com.google.android.gms.measurement.internal.zzer r0 = r21.zzq()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzh()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            java.lang.String r11 = "Skipping app launch break"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x0135:
            com.google.android.gms.measurement.internal.zzer r0 = r21.zzq()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zze()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            java.lang.String r11 = "Unknown record type in local database"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            goto L_0x006e
        L_0x0144:
            java.lang.String r0 = "messages"
            java.lang.String r11 = "rowid <= ?"
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            java.lang.String r13 = java.lang.Long.toString(r19)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            r12[r6] = r13     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r0 = r5.delete(r0, r11, r12)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            int r11 = r4.size()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            if (r0 >= r11) goto L_0x0167
            com.google.android.gms.measurement.internal.zzer r0 = r21.zzq()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            com.google.android.gms.measurement.internal.zzet r0 = r0.zze()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            java.lang.String r11 = "Fewer entries removed from local database than expected"
            r0.zza(r11)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
        L_0x0167:
            r5.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            r5.endTransaction()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x0195, SQLiteException -> 0x017c, all -> 0x0178 }
            if (r10 == 0) goto L_0x0172
            r10.close()
        L_0x0172:
            if (r5 == 0) goto L_0x0177
            r5.close()
        L_0x0177:
            return r4
        L_0x0178:
            r0 = move-exception
            r3 = r10
            goto L_0x01f9
        L_0x017c:
            r0 = move-exception
            goto L_0x0185
        L_0x017e:
            r0 = move-exception
            goto L_0x0189
        L_0x0180:
            r0 = move-exception
            goto L_0x01f9
        L_0x0183:
            r0 = move-exception
            r10 = r3
        L_0x0185:
            r15 = r5
            goto L_0x01a1
        L_0x0187:
            r0 = move-exception
            r10 = r3
        L_0x0189:
            r15 = r5
            goto L_0x01da
        L_0x018c:
            r0 = move-exception
            goto L_0x01f8
        L_0x018f:
            r0 = move-exception
            r5 = r15
        L_0x0191:
            r10 = r3
            goto L_0x01a1
        L_0x0193:
            r5 = r15
        L_0x0194:
            r10 = r3
        L_0x0195:
            r15 = r5
            goto L_0x01c6
        L_0x0197:
            r0 = move-exception
            r5 = r15
        L_0x0199:
            r10 = r3
            goto L_0x01da
        L_0x019b:
            r0 = move-exception
            r5 = r3
            goto L_0x01f9
        L_0x019e:
            r0 = move-exception
            r10 = r3
            r15 = r10
        L_0x01a1:
            if (r15 == 0) goto L_0x01ac
            boolean r5 = r15.inTransaction()     // Catch:{ all -> 0x01f6 }
            if (r5 == 0) goto L_0x01ac
            r15.endTransaction()     // Catch:{ all -> 0x01f6 }
        L_0x01ac:
            com.google.android.gms.measurement.internal.zzer r5 = r21.zzq()     // Catch:{ all -> 0x01f6 }
            com.google.android.gms.measurement.internal.zzet r5 = r5.zze()     // Catch:{ all -> 0x01f6 }
            r5.zza(r2, r0)     // Catch:{ all -> 0x01f6 }
            r1.zzb = r9     // Catch:{ all -> 0x01f6 }
            if (r10 == 0) goto L_0x01be
            r10.close()
        L_0x01be:
            if (r15 == 0) goto L_0x01f1
            r15.close()
            goto L_0x01f1
        L_0x01c4:
            r10 = r3
            r15 = r10
        L_0x01c6:
            long r11 = (long) r8
            android.os.SystemClock.sleep(r11)     // Catch:{ all -> 0x01f6 }
            int r8 = r8 + 20
            if (r10 == 0) goto L_0x01d1
            r10.close()
        L_0x01d1:
            if (r15 == 0) goto L_0x01f1
            r15.close()
            goto L_0x01f1
        L_0x01d7:
            r0 = move-exception
            r10 = r3
            r15 = r10
        L_0x01da:
            com.google.android.gms.measurement.internal.zzer r5 = r21.zzq()     // Catch:{ all -> 0x01f6 }
            com.google.android.gms.measurement.internal.zzet r5 = r5.zze()     // Catch:{ all -> 0x01f6 }
            r5.zza(r2, r0)     // Catch:{ all -> 0x01f6 }
            r1.zzb = r9     // Catch:{ all -> 0x01f6 }
            if (r10 == 0) goto L_0x01ec
            r10.close()
        L_0x01ec:
            if (r15 == 0) goto L_0x01f1
            r15.close()
        L_0x01f1:
            int r7 = r7 + 1
            r5 = 5
            goto L_0x001d
        L_0x01f6:
            r0 = move-exception
            r3 = r10
        L_0x01f8:
            r5 = r15
        L_0x01f9:
            if (r3 == 0) goto L_0x01fe
            r3.close()
        L_0x01fe:
            if (r5 == 0) goto L_0x0203
            r5.close()
        L_0x0203:
            throw r0
        L_0x0204:
            com.google.android.gms.measurement.internal.zzer r0 = r21.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzh()
            java.lang.String r2 = "Failed to read events from database in reasonable time"
            r0.zza(r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzen.zza(int):java.util.List");
    }

    public final boolean zzab() {
        return zza(3, new byte[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0086, code lost:
        r3 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzac() {
        /*
            r11 = this;
            java.lang.String r0 = "Error deleting app launch break from local database"
            r11.zzc()
            boolean r1 = r11.zzb
            r2 = 0
            if (r1 == 0) goto L_0x000b
            return r2
        L_0x000b:
            boolean r1 = r11.zzae()
            if (r1 != 0) goto L_0x0012
            return r2
        L_0x0012:
            r1 = 5
            r3 = 0
            r4 = 5
        L_0x0015:
            if (r3 >= r1) goto L_0x008f
            r5 = 0
            r6 = 1
            android.database.sqlite.SQLiteDatabase r5 = r11.zzad()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            if (r5 != 0) goto L_0x0027
            r11.zzb = r6     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            if (r5 == 0) goto L_0x0026
            r5.close()
        L_0x0026:
            return r2
        L_0x0027:
            r5.beginTransaction()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            java.lang.String r7 = "messages"
            java.lang.String r8 = "type == ?"
            java.lang.String[] r9 = new java.lang.String[r6]     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r10 = 3
            java.lang.String r10 = java.lang.Integer.toString(r10)     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r9[r2] = r10     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r5.delete(r7, r8, r9)     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r5.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            r5.endTransaction()     // Catch:{ SQLiteFullException -> 0x0073, SQLiteDatabaseLockedException -> 0x0067, SQLiteException -> 0x0048 }
            if (r5 == 0) goto L_0x0045
            r5.close()
        L_0x0045:
            return r6
        L_0x0046:
            r0 = move-exception
            goto L_0x0089
        L_0x0048:
            r7 = move-exception
            if (r5 == 0) goto L_0x0054
            boolean r8 = r5.inTransaction()     // Catch:{ all -> 0x0046 }
            if (r8 == 0) goto L_0x0054
            r5.endTransaction()     // Catch:{ all -> 0x0046 }
        L_0x0054:
            com.google.android.gms.measurement.internal.zzer r8 = r11.zzq()     // Catch:{ all -> 0x0046 }
            com.google.android.gms.measurement.internal.zzet r8 = r8.zze()     // Catch:{ all -> 0x0046 }
            r8.zza(r0, r7)     // Catch:{ all -> 0x0046 }
            r11.zzb = r6     // Catch:{ all -> 0x0046 }
            if (r5 == 0) goto L_0x0086
            r5.close()
            goto L_0x0086
        L_0x0067:
            long r6 = (long) r4
            android.os.SystemClock.sleep(r6)     // Catch:{ all -> 0x0046 }
            int r4 = r4 + 20
            if (r5 == 0) goto L_0x0086
            r5.close()
            goto L_0x0086
        L_0x0073:
            r7 = move-exception
            com.google.android.gms.measurement.internal.zzer r8 = r11.zzq()     // Catch:{ all -> 0x0046 }
            com.google.android.gms.measurement.internal.zzet r8 = r8.zze()     // Catch:{ all -> 0x0046 }
            r8.zza(r0, r7)     // Catch:{ all -> 0x0046 }
            r11.zzb = r6     // Catch:{ all -> 0x0046 }
            if (r5 == 0) goto L_0x0086
            r5.close()
        L_0x0086:
            int r3 = r3 + 1
            goto L_0x0015
        L_0x0089:
            if (r5 == 0) goto L_0x008e
            r5.close()
        L_0x008e:
            throw r0
        L_0x008f:
            com.google.android.gms.measurement.internal.zzer r0 = r11.zzq()
            com.google.android.gms.measurement.internal.zzet r0 = r0.zzh()
            java.lang.String r1 = "Error deleting app launch break from local database in reasonable time"
            r0.zza(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzen.zzac():boolean");
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{ExifInterface.GPS_MEASUREMENT_3D}, (String) null, (String) null, "rowid desc", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            if (cursor.moveToFirst()) {
                return cursor.getLong(0);
            }
            if (cursor == null) {
                return -1;
            }
            cursor.close();
            return -1;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private final SQLiteDatabase zzad() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    private final boolean zzae() {
        return zzm().getDatabasePath("google_app_measurement_local.db").exists();
    }

    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    public final /* bridge */ /* synthetic */ zza zzd() {
        return super.zzd();
    }

    public final /* bridge */ /* synthetic */ zzgy zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzek zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzio zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzij zzh() {
        return super.zzh();
    }

    public final /* bridge */ /* synthetic */ zzen zzi() {
        return super.zzi();
    }

    public final /* bridge */ /* synthetic */ zzju zzj() {
        return super.zzj();
    }

    public final /* bridge */ /* synthetic */ zzal zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ Clock zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ Context zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzep zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzkw zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzfo zzp() {
        return super.zzp();
    }

    public final /* bridge */ /* synthetic */ zzer zzq() {
        return super.zzq();
    }

    public final /* bridge */ /* synthetic */ zzfd zzr() {
        return super.zzr();
    }

    public final /* bridge */ /* synthetic */ zzy zzs() {
        return super.zzs();
    }

    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }
}
