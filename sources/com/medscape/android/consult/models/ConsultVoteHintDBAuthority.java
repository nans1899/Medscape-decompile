package com.medscape.android.consult.models;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import com.medscape.android.activity.login.LoginManager;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.util.StringUtil;

public class ConsultVoteHintDBAuthority implements BaseColumns {
    static final Uri CONTENT_URI = Uri.parse("content://com.android.medscape.providers.MedscapeContentProvider/votehintaccepetedusers");
    static final String GUID = "userGUID";

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
        if (r1.isClosed() == false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005b, code lost:
        if (r1.isClosed() == false) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0073, code lost:
        if (r1.isClosed() == false) goto L_0x0046;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:22:0x0060 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isVoteHintShown(android.content.Context r10) {
        /*
            com.medscape.android.auth.AuthenticationManager r0 = com.medscape.android.auth.AuthenticationManager.getInstance(r10)
            java.lang.String r0 = r0.getMaskedGuid()
            boolean r1 = com.medscape.android.util.StringUtil.isNullOrEmpty(r0)
            if (r1 == 0) goto L_0x0012
            java.lang.String r0 = com.medscape.android.activity.login.LoginManager.getStoredGUID(r10)
        L_0x0012:
            r1 = 0
            r2 = 0
            android.content.ContentResolver r3 = r10.getContentResolver()     // Catch:{ SQLException -> 0x0060, all -> 0x004a }
            android.net.Uri r4 = CONTENT_URI     // Catch:{ SQLException -> 0x0060, all -> 0x004a }
            r5 = 0
            java.lang.String r6 = "userGUID = ?"
            r9 = 1
            java.lang.String[] r7 = new java.lang.String[r9]     // Catch:{ SQLException -> 0x0060, all -> 0x004a }
            r7[r2] = r0     // Catch:{ SQLException -> 0x0060, all -> 0x004a }
            r8 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ SQLException -> 0x0060, all -> 0x004a }
            java.lang.String r3 = "userGUID"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ SQLException -> 0x0060, all -> 0x004a }
        L_0x002d:
            boolean r4 = r1.moveToNext()     // Catch:{ SQLException -> 0x0060, all -> 0x004a }
            if (r4 == 0) goto L_0x003e
            java.lang.String r4 = r1.getString(r3)     // Catch:{ SQLException -> 0x0060, all -> 0x004a }
            boolean r4 = r0.equals(r4)     // Catch:{ SQLException -> 0x0060, all -> 0x004a }
            if (r4 == 0) goto L_0x002d
            r2 = 1
        L_0x003e:
            if (r1 == 0) goto L_0x0076
            boolean r10 = r1.isClosed()
            if (r10 != 0) goto L_0x0076
        L_0x0046:
            r1.close()
            goto L_0x0076
        L_0x004a:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ all -> 0x005e }
            java.lang.String r10 = "VoteHintDB"
            java.lang.String r0 = "Ignoring VoteHint DB error"
            android.util.Log.w(r10, r0)     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0076
            boolean r10 = r1.isClosed()
            if (r10 != 0) goto L_0x0076
            goto L_0x0046
        L_0x005e:
            r10 = move-exception
            goto L_0x0077
        L_0x0060:
            com.medscape.android.cache.CacheManager r0 = new com.medscape.android.cache.CacheManager     // Catch:{ all -> 0x0069 }
            r0.<init>(r10)     // Catch:{ all -> 0x0069 }
            r0.createVoteHintTable()     // Catch:{ all -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ all -> 0x005e }
        L_0x006d:
            if (r1 == 0) goto L_0x0076
            boolean r10 = r1.isClosed()
            if (r10 != 0) goto L_0x0076
            goto L_0x0046
        L_0x0076:
            return r2
        L_0x0077:
            if (r1 == 0) goto L_0x0082
            boolean r0 = r1.isClosed()
            if (r0 != 0) goto L_0x0082
            r1.close()
        L_0x0082:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.consult.models.ConsultVoteHintDBAuthority.isVoteHintShown(android.content.Context):boolean");
    }

    public static void uponVoteHintShown(Context context) {
        try {
            String maskedGuid = AuthenticationManager.getInstance(context).getMaskedGuid();
            if (StringUtil.isNullOrEmpty(maskedGuid)) {
                maskedGuid = LoginManager.getStoredGUID(context);
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("userGUID", maskedGuid);
            context.getContentResolver().insert(CONTENT_URI, contentValues);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
