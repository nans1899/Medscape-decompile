package com.medscape.android.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import com.medscape.android.cache.CacheManager;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.util.LogUtil;

public class MedscapeContentProvider extends ContentProvider {
    public static final String AUTHORITY = "com.android.medscape.providers.MedscapeContentProvider";
    private static final int CACHES = 1;
    private static final String CACHES_TABLE_NAME = "caches";
    private static final String CALCULATORES_TABLE_NAME = "calculatorcaches";
    private static final int CALCULATORS = 6;
    private static final int CONDITIONS = 4;
    private static final String CONDITIONS_TABLE_NAME = "conditionscaches";
    private static final int DRUGS = 2;
    private static final String DRUG_CACHES_TABLE_NAME = "drugcaches";
    private static final int FEEDS = 5;
    private static final String FEEDS_TABLE_NAME = "feedscaches";
    private static final int INTERACTIONS_DRUG = 3;
    private static final String INTERACTIONS_DRUG_TABLE_NAME = "interactionsdrugcaches";
    private static final String TAG = "MedscapeContentProvider";
    private static final int VOTEHINT = 7;
    private static final String VOTE_HINT_USERS_TABLE_NAME = "votehintaccepetedusers";
    private static final UriMatcher sUriMatcher;
    private CacheManager dbHelper;

    public String getType(Uri uri) {
        return null;
    }

    public boolean onCreate() {
        this.dbHelper = new CacheManager(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        switch (sUriMatcher.match(uri)) {
            case 1:
                sQLiteQueryBuilder.setTables(CACHES_TABLE_NAME);
                break;
            case 2:
                sQLiteQueryBuilder.setTables(DRUG_CACHES_TABLE_NAME);
                break;
            case 3:
                sQLiteQueryBuilder.setTables(INTERACTIONS_DRUG_TABLE_NAME);
                break;
            case 4:
                sQLiteQueryBuilder.setTables(CONDITIONS_TABLE_NAME);
                break;
            case 5:
                sQLiteQueryBuilder.setTables(FEEDS_TABLE_NAME);
                break;
            case 6:
                sQLiteQueryBuilder.setTables(CALCULATORES_TABLE_NAME);
                break;
            case 7:
                sQLiteQueryBuilder.setTables(VOTE_HINT_USERS_TABLE_NAME);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        Cursor query = sQLiteQueryBuilder.query(this.dbHelper.getReadableDatabase(), strArr, str, strArr2, (String) null, (String) null, str2);
        query.setNotificationUri(getContext().getContentResolver(), uri);
        return query;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        int i;
        SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case 1:
                i = writableDatabase.delete(CACHES_TABLE_NAME, str, strArr);
                break;
            case 2:
                i = writableDatabase.delete(DRUG_CACHES_TABLE_NAME, str, strArr);
                break;
            case 3:
                i = writableDatabase.delete(INTERACTIONS_DRUG_TABLE_NAME, str, strArr);
                break;
            case 4:
                i = writableDatabase.delete(CONDITIONS_TABLE_NAME, str, strArr);
                break;
            case 5:
                i = writableDatabase.delete(FEEDS_TABLE_NAME, str, strArr);
                break;
            case 6:
                i = writableDatabase.delete(CALCULATORES_TABLE_NAME, str, strArr);
                break;
            case 7:
                i = writableDatabase.delete(VOTE_HINT_USERS_TABLE_NAME, str, strArr);
                break;
            default:
                throw new IllegalArgumentException("Unknown URL " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
        return i;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int i;
        SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case 1:
                i = writableDatabase.update(CACHES_TABLE_NAME, contentValues, str, strArr);
                break;
            case 2:
                i = writableDatabase.update(DRUG_CACHES_TABLE_NAME, contentValues, str, strArr);
                break;
            case 3:
                i = writableDatabase.update(INTERACTIONS_DRUG_TABLE_NAME, contentValues, str, strArr);
                break;
            case 4:
                i = writableDatabase.update(CONDITIONS_TABLE_NAME, contentValues, str, strArr);
                break;
            case 5:
                i = writableDatabase.update(FEEDS_TABLE_NAME, contentValues, str, strArr);
                break;
            case 6:
                i = writableDatabase.update(CALCULATORES_TABLE_NAME, contentValues, str, strArr);
                break;
            case 7:
                i = writableDatabase.update(VOTE_HINT_USERS_TABLE_NAME, contentValues, str, strArr);
                break;
            default:
                throw new IllegalArgumentException("Unknown URL " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, (ContentObserver) null);
        return i;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        ContentValues contentValues2;
        long j;
        if (contentValues != null) {
            contentValues2 = new ContentValues(contentValues);
        } else {
            contentValues2 = new ContentValues();
        }
        SQLiteDatabase writableDatabase = this.dbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case 1:
                j = writableDatabase.insert(CACHES_TABLE_NAME, "body", contentValues2);
                break;
            case 2:
                j = writableDatabase.insert(DRUG_CACHES_TABLE_NAME, "body", contentValues2);
                break;
            case 3:
                j = writableDatabase.insert(INTERACTIONS_DRUG_TABLE_NAME, "body", contentValues2);
                break;
            case 4:
                j = writableDatabase.insert(CONDITIONS_TABLE_NAME, "body", contentValues2);
                break;
            case 5:
                j = writableDatabase.insert(FEEDS_TABLE_NAME, "body", contentValues2);
                break;
            case 6:
                j = writableDatabase.insert(CALCULATORES_TABLE_NAME, "body", contentValues2);
                LogUtil.e(TAG, "Save crArticle.rowID = %s", "" + j);
                break;
            case 7:
                j = writableDatabase.insert(VOTE_HINT_USERS_TABLE_NAME, "body", contentValues2);
                LogUtil.e(TAG, "Save crArticle.rowID = %s", "" + j);
                break;
            default:
                throw new IllegalArgumentException("Unknown URL " + uri);
        }
        if (j > 0) {
            Uri withAppendedPath = Uri.withAppendedPath(ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI, uri.getLastPathSegment());
            getContext().getContentResolver().notifyChange(withAppendedPath, (ContentObserver) null);
            return withAppendedPath;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        sUriMatcher = uriMatcher;
        uriMatcher.addURI(AUTHORITY, CONDITIONS_TABLE_NAME, 4);
        sUriMatcher.addURI(AUTHORITY, DRUG_CACHES_TABLE_NAME, 2);
        sUriMatcher.addURI(AUTHORITY, CACHES_TABLE_NAME, 1);
        sUriMatcher.addURI(AUTHORITY, INTERACTIONS_DRUG_TABLE_NAME, 3);
        sUriMatcher.addURI(AUTHORITY, FEEDS_TABLE_NAME, 5);
        sUriMatcher.addURI(AUTHORITY, CALCULATORES_TABLE_NAME, 6);
        sUriMatcher.addURI(AUTHORITY, VOTE_HINT_USERS_TABLE_NAME, 7);
    }
}
