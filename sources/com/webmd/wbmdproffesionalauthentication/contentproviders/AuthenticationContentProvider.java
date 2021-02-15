package com.webmd.wbmdproffesionalauthentication.contentproviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Binder;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.webmd.wbmdproffesionalauthentication.constants.SharedPreferenceConstants;
import com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider;

public class AuthenticationContentProvider extends ContentProvider {
    private static String AUTHORITY = null;
    private static final String BASE_PATH = "global";
    public static final String CONTENT_ITEM_TYPE = String.format("%s/%s", new Object[]{"vnd.android.cursor.item", BASE_PATH});
    public static final String CONTENT_TYPE = String.format("%s/%s", new Object[]{"vnd.android.cursor.dir", BASE_PATH});
    public static final Uri CONTENT_URI = Uri.parse(String.format("content://%s/%s", new Object[]{AUTHORITY, BASE_PATH}));
    private static final int GET_KEYCHAIN_CODE = 1001;
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public String getType(Uri uri) {
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    public boolean onCreate() {
        String packageName = getContext().getPackageName();
        AUTHORITY = packageName;
        URI_MATCHER.addURI(packageName, BASE_PATH, 1001);
        return false;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Binder.getCallingUid();
        if (URI_MATCHER.match(uri) == 1001) {
            if (strArr == null || strArr.length <= 0) {
                MatrixCursor matrixCursor = new MatrixCursor(new String[]{"_id", "username", "password"});
                SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
                String string = sharedPreferenceProvider.getString(SharedPreferenceConstants.USER_NAME_KEYCHAIN_KEY, StringExtensions.EMPTY, getContext());
                String string2 = sharedPreferenceProvider.getString(SharedPreferenceConstants.PASSWORD_KEYCHAIN_KEY, StringExtensions.EMPTY, getContext());
                if (!StringExtensions.isNullOrEmpty(string) && !StringExtensions.isNullOrEmpty(string2)) {
                    matrixCursor.addRow(new Object[]{1, string, string2});
                    return matrixCursor;
                }
            } else {
                String str3 = strArr[0];
                MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{str3});
                matrixCursor2.addRow(new Object[]{SharedPreferenceProvider.get().getString(str3, StringExtensions.EMPTY, getContext())});
                return matrixCursor2;
            }
        }
        return null;
    }
}
