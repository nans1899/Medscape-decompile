package com.medscape.android.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.Settings;
import com.medscape.android.activity.interactions.models.DrugList;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.model.Drug;
import com.medscape.android.drugs.model.DrugsContract;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CacheManager extends SQLiteOpenHelper {
    private static final String CALCULATORS_TABLE_NAME = "calculatorcaches";
    private static final String CONDITIONS_TABLE_NAME = "conditionscaches";
    private static final String DATABASE_NAME = "MedscapeCache.db";
    private static final int DATABASE_VERSION = 13;
    private static final String DRUGS_LISTS_JUNCTION_TABLE = "drugsliststable";
    private static final String DRUG_TABLE_NAME = "drugcaches";
    private static final String FEEDS_TABLE_NAME = "feedscaches";
    private static final String INTERACTIONS_DRUGS_TABLE_NAME = "interactionsdrugcaches";
    private static final String LISTS_TABLE = "liststable";
    private static final String TABLE_NAME = "caches";
    private static final String TAG = "CacheManager";
    private static final String VOTE_HINT_USERS_TABLE_NAME = "votehintaccepetedusers";
    private static SQLiteDatabase database = null;
    private static SQLiteDatabase globalDatabase = null;
    private static boolean isUpgraded = false;
    private Context context;

    public CacheManager(Context context2) {
        super(context2, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 13);
        this.context = context2;
        try {
            database = getWritableDatabase();
            globalDatabase = new DatabaseHelper(context2).getDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDatabase() {
        SQLiteDatabase sQLiteDatabase = database;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
        SQLiteDatabase sQLiteDatabase2 = globalDatabase;
        if (sQLiteDatabase2 != null) {
            sQLiteDatabase2.close();
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE caches (id INTEGER, type INTEGER, url TEXT, content TEXT,  title TEXT, isSaved INTEGER,time TEXT, userGUID TEXT, byline TEXT, imageURL TEXT, PRIMARY KEY (id, userGUID) );");
        sQLiteDatabase.execSQL("CREATE TABLE drugcaches (id INTEGER, type INTEGER, contentID INTEGER, drugName TEXT, isSaved INTEGER, userGUID TEXT, PRIMARY KEY (contentID, userGUID));");
        sQLiteDatabase.execSQL("CREATE TABLE interactionsdrugcaches (id INTEGER, contentID INTEGER, drugName TEXT, drugID INTEGER, comboId INTEGER,genericId INTEGER,dName TEXT, userGUID TEXT, PRIMARY KEY (id, userGUID));");
        sQLiteDatabase.execSQL("CREATE TABLE conditionscaches (id INTEGER PRIMARY KEY,  uniqueId INTEGER , title TEXT, articleId INTEGER,type INTEGER,isSaved INTEGER, userGuid TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE feedscaches (id INTEGER PRIMARY KEY, feedUrl TEXT, specialtyId INTEGER , feedTitle TEXT, isSpecial INTEGER,type INTEGER,articleUrl TEXT,feedDate TEXT,feedCategory TEXT,feedImageUrl TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE calculatorcaches (id INTEGER PRIMARY KEY , title TEXT, calcId TEXT,type INTEGER,isSaved INTEGER, userGuid TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE votehintaccepetedusers (id INTEGER PRIMARY KEY,  userGUID TEXT );");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS liststable (id INTEGER PRIMARY KEY, listName TEXT, userGUID TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS drugsliststable (listId INTEGER, contentId INTEGER, drugName TEXT, drugId INTEGER, comboId INTEGER, genericId INTEGER, otherNames TEXT);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i < 8) {
            sQLiteDatabase.execSQL("ALTER TABLE feedscaches ADD feedImageUrl TEXT");
        }
        if (i < 10) {
            upgradeCachesTables(sQLiteDatabase);
            upgradeDrugCachesTables(sQLiteDatabase);
            upgradeInteractionCachesTables(sQLiteDatabase);
        }
        if (i < 12) {
            createListTable(sQLiteDatabase);
            createInteractionDrugTable(sQLiteDatabase);
            sQLiteDatabase.execSQL("ALTER TABLE caches ADD byline TEXT");
            sQLiteDatabase.execSQL("ALTER TABLE caches ADD imageURL TEXT");
        }
        LogUtil.e(TAG, "onUpgrade newVersion = %s", "" + i2);
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS calculatorcaches (id INTEGER PRIMARY KEY , title TEXT, calcId TEXT UNIQUE,type INTEGER,isSaved INTEGER);");
        if (i < 13) {
            sQLiteDatabase.execSQL("CREATE TABLE tempCalculatorTable (id INTEGER PRIMARY KEY , title TEXT, calcId TEXT,type INTEGER,isSaved INTEGER, userGuid TEXT);");
            sQLiteDatabase.execSQL("INSERT INTO tempCalculatorTable (id, title, calcId, type, isSaved, userGuid) SELECT id, title, calcId, type, isSaved, '' FROM calculatorcaches");
            sQLiteDatabase.execSQL("DROP TABLE calculatorcaches");
            sQLiteDatabase.execSQL("ALTER TABLE tempCalculatorTable RENAME TO calculatorcaches");
            sQLiteDatabase.execSQL("CREATE TABLE tempConditionsTable (id INTEGER PRIMARY KEY,  uniqueId INTEGER , title TEXT, articleId INTEGER,type INTEGER,isSaved INTEGER, userGuid TEXT);");
            sQLiteDatabase.execSQL("INSERT INTO tempConditionsTable (id, uniqueId, title, articleId, type, isSaved,userGuid) SELECT id, uniqueId, title, articleId, type, isSaved, '' FROM conditionscaches");
            sQLiteDatabase.execSQL("DROP TABLE conditionscaches");
            sQLiteDatabase.execSQL("ALTER TABLE tempConditionsTable RENAME TO conditionscaches");
            Settings.singleton(this.context).saveSetting(Constants.PREF_DATABASE_UPDATE, true);
            isUpgraded = true;
        }
    }

    public boolean addCache(Cache cache) {
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        if (StringUtil.isNotEmpty(maskedGuid)) {
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                String[] strArr = new String[9];
                strArr[0] = String.valueOf(cache.getType());
                strArr[1] = cache.getUrl().trim();
                strArr[2] = cache.getContent();
                strArr[3] = cache.getTitle();
                strArr[4] = String.valueOf(cache.isSaved() ? 1 : 0);
                strArr[5] = cache.getTime();
                strArr[6] = maskedGuid;
                strArr[7] = cache.getByline();
                strArr[8] = cache.getImageUrl();
                writableDatabase.execSQL("INSERT INTO caches(type, url, content,title, isSaved,time, userGUID, byline, imageURL) VALUES(?,?,?,?,?,?,?,?,?)", strArr);
                close();
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public Cache getCache(String str) {
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        Cache cache = null;
        if (StringUtil.isNotEmpty(maskedGuid)) {
            Cursor rawQuery = getWritableDatabase().rawQuery("SELECT id, type, url, content,title, isSaved,time,byline,imageURL FROM caches WHERE url = ? AND userGUID = ?", new String[]{str, maskedGuid});
            while (rawQuery.moveToNext()) {
                cache = new Cache();
                cache.setId(rawQuery.getInt(0));
                cache.setType(rawQuery.getInt(1));
                cache.setUrl(rawQuery.getString(2));
                cache.setContent(rawQuery.getString(3));
                cache.setTitle(rawQuery.getString(4));
                cache.setSaved(rawQuery.getInt(5) != 0);
                cache.setTime(rawQuery.getString(6));
                cache.setByline(rawQuery.getString(7));
                cache.setImageUrl(rawQuery.getString(8));
            }
            close();
            rawQuery.close();
        }
        return cache;
    }

    private List<String> getOnUpdateSavedCache() {
        ArrayList arrayList = new ArrayList();
        Cursor rawQuery = getWritableDatabase().rawQuery("SELECT url FROM caches WHERE isSaved = 1 AND (type = 2 OR type = 3)", (String[]) null);
        while (rawQuery.moveToNext()) {
            arrayList.add(rawQuery.getString(0).trim());
        }
        close();
        rawQuery.close();
        return arrayList;
    }

    public List<Cache> getNewsSavedCache() {
        ArrayList arrayList = new ArrayList();
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        if (StringUtil.isNotEmpty(maskedGuid)) {
            Cursor rawQuery = getWritableDatabase().rawQuery("SELECT   id, type,  url, content,title,time,byline,imageURL FROM caches WHERE isSaved = 1 AND type = 2 AND userGUID = ?", new String[]{maskedGuid});
            while (rawQuery.moveToNext()) {
                Cache cache = new Cache();
                cache.setId(rawQuery.getInt(0));
                cache.setType(rawQuery.getInt(1));
                cache.setUrl(rawQuery.getString(2).trim());
                cache.setContent(rawQuery.getString(3));
                cache.setTitle(rawQuery.getString(4));
                cache.setTime(rawQuery.getString(5));
                cache.setSaved(true);
                cache.setByline(rawQuery.getString(6));
                cache.setImageUrl(rawQuery.getString(7));
                arrayList.add(cache);
            }
            close();
            rawQuery.close();
        }
        return arrayList;
    }

    public List<Cache> getCMESavedCache() {
        ArrayList arrayList = new ArrayList();
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        if (StringUtil.isNotEmpty(maskedGuid)) {
            Cursor rawQuery = getWritableDatabase().rawQuery("SELECT   id, type,  url, content,title,time FROM caches WHERE isSaved = 1  AND type = 3 AND userGUID = ?", new String[]{maskedGuid});
            while (rawQuery.moveToNext()) {
                Cache cache = new Cache();
                cache.setId(rawQuery.getInt(0));
                cache.setType(rawQuery.getInt(1));
                cache.setUrl(rawQuery.getString(2).trim());
                cache.setContent(rawQuery.getString(3));
                cache.setTitle(rawQuery.getString(4));
                cache.setTime(rawQuery.getString(5));
                cache.setSaved(true);
                arrayList.add(cache);
            }
            close();
            rawQuery.close();
        }
        return arrayList;
    }

    public boolean updateCache(ContentValues contentValues, String str, String[] strArr) {
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        try {
            if (StringUtil.isNotEmpty(maskedGuid)) {
                str = addUserGUIDInWhereClause(str);
                strArr = addUserGUIDInWhereArgs(strArr, maskedGuid);
            }
            if (getWritableDatabase().update(TABLE_NAME, contentValues, str, strArr) <= 0) {
                return false;
            }
            close();
            return true;
        } catch (Exception unused) {
            return false;
        } finally {
            close();
        }
    }

    private String[] addUserGUIDInWhereArgs(String[] strArr, String str) {
        int i = 0;
        if (strArr == null) {
            return new String[]{str};
        }
        String[] strArr2 = new String[(strArr.length + 1)];
        int length = strArr.length;
        int i2 = 0;
        while (i < length) {
            strArr2[i2] = strArr[i];
            i++;
            i2++;
        }
        strArr2[i2] = str;
        return strArr2;
    }

    private String addUserGUIDInWhereClause(String str) {
        if (!StringUtil.isNotEmpty(str)) {
            return "userGUID = ?";
        }
        if (str.toLowerCase().indexOf("and") >= 0) {
            return str.toLowerCase().replace("and", ",") + " AND userGUID = ?";
        }
        return str + " AND userGUID = ?";
    }

    public boolean updateDrugCache(ContentValues contentValues, String str, String[] strArr) {
        boolean z;
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        try {
            if (StringUtil.isNotEmpty(maskedGuid)) {
                str = addUserGUIDInWhereClause(str);
                strArr = addUserGUIDInWhereArgs(strArr, maskedGuid);
            }
            getWritableDatabase().update(DRUG_TABLE_NAME, contentValues, str, strArr);
            z = true;
        } catch (Exception unused) {
            z = false;
        } catch (Throwable th) {
            close();
            throw th;
        }
        close();
        return z;
    }

    public boolean addCache(DrugCache drugCache) {
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        if (!StringUtil.isNotEmpty(maskedGuid)) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String[] strArr = new String[5];
            strArr[0] = String.valueOf(drugCache.getType());
            strArr[1] = String.valueOf(drugCache.getContentId());
            strArr[2] = drugCache.getDrugName();
            strArr[3] = String.valueOf(drugCache.isSaved() ? 1 : 0);
            strArr[4] = maskedGuid;
            writableDatabase.execSQL("INSERT INTO drugcaches(type, contentID,drugName, isSaved, userGUID) VALUES(?,?,?,?,?)", strArr);
            close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            ContentValues contentValues = new ContentValues();
            contentValues.put("isSAved", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            return updateDrugCache(contentValues, "contentID = ?", new String[]{String.valueOf(drugCache.getContentId())});
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: com.medscape.android.cache.DrugCache} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r2v15 */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
        r8 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006b, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x006d, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006a A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x002a] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0076 A[SYNTHETIC, Splitter:B:28:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0080 A[SYNTHETIC, Splitter:B:36:0x0080] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.medscape.android.cache.DrugCache getCache(int r8) {
        /*
            r7 = this;
            com.medscape.android.MedscapeApplication r0 = com.medscape.android.MedscapeApplication.get()
            com.medscape.android.auth.AuthenticationManager r0 = com.medscape.android.auth.AuthenticationManager.getInstance(r0)
            java.lang.String r0 = r0.getMaskedGuid()
            boolean r1 = com.medscape.android.util.StringUtil.isNotEmpty(r0)
            r2 = 0
            if (r1 == 0) goto L_0x0084
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()     // Catch:{ Exception -> 0x007a, all -> 0x0070 }
            java.lang.String r3 = "SELECT id, type, contentID,drugName, isSaved FROM drugcaches WHERE contentID = ? AND userGUID = ?"
            r4 = 2
            java.lang.String[] r5 = new java.lang.String[r4]     // Catch:{ Exception -> 0x007a, all -> 0x0070 }
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x007a, all -> 0x0070 }
            r6 = 0
            r5[r6] = r8     // Catch:{ Exception -> 0x007a, all -> 0x0070 }
            r8 = 1
            r5[r8] = r0     // Catch:{ Exception -> 0x007a, all -> 0x0070 }
            android.database.Cursor r0 = r1.rawQuery(r3, r5)     // Catch:{ Exception -> 0x007a, all -> 0x0070 }
        L_0x002a:
            boolean r1 = r0.moveToNext()     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            if (r1 == 0) goto L_0x0061
            com.medscape.android.cache.DrugCache r1 = new com.medscape.android.cache.DrugCache     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            r1.<init>()     // Catch:{ Exception -> 0x006d, all -> 0x006a }
            int r2 = r0.getInt(r6)     // Catch:{ Exception -> 0x006e, all -> 0x006a }
            r1.setId(r2)     // Catch:{ Exception -> 0x006e, all -> 0x006a }
            int r2 = r0.getInt(r8)     // Catch:{ Exception -> 0x006e, all -> 0x006a }
            r1.setType(r2)     // Catch:{ Exception -> 0x006e, all -> 0x006a }
            int r2 = r0.getInt(r4)     // Catch:{ Exception -> 0x006e, all -> 0x006a }
            r1.setContentId(r2)     // Catch:{ Exception -> 0x006e, all -> 0x006a }
            r2 = 3
            java.lang.String r2 = r0.getString(r2)     // Catch:{ Exception -> 0x006e, all -> 0x006a }
            r1.setDrugName(r2)     // Catch:{ Exception -> 0x006e, all -> 0x006a }
            r2 = 4
            int r2 = r0.getInt(r2)     // Catch:{ Exception -> 0x006e, all -> 0x006a }
            if (r2 != 0) goto L_0x005b
            r2 = 0
            goto L_0x005c
        L_0x005b:
            r2 = 1
        L_0x005c:
            r1.setSaved(r2)     // Catch:{ Exception -> 0x006e, all -> 0x006a }
            r2 = r1
            goto L_0x002a
        L_0x0061:
            r7.close()
            if (r0 == 0) goto L_0x0084
            r0.close()     // Catch:{ Exception -> 0x0084 }
            goto L_0x0084
        L_0x006a:
            r8 = move-exception
            r2 = r0
            goto L_0x0071
        L_0x006d:
            r1 = r2
        L_0x006e:
            r2 = r0
            goto L_0x007b
        L_0x0070:
            r8 = move-exception
        L_0x0071:
            r7.close()
            if (r2 == 0) goto L_0x0079
            r2.close()     // Catch:{ Exception -> 0x0079 }
        L_0x0079:
            throw r8
        L_0x007a:
            r1 = r2
        L_0x007b:
            r7.close()
            if (r2 == 0) goto L_0x0083
            r2.close()     // Catch:{ Exception -> 0x0083 }
        L_0x0083:
            r2 = r1
        L_0x0084:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.cache.CacheManager.getCache(int):com.medscape.android.cache.DrugCache");
    }

    public List<DrugCache> getSavedDrugCache() {
        ArrayList arrayList = new ArrayList();
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        if (StringUtil.isNotEmpty(maskedGuid)) {
            Cursor rawQuery = getWritableDatabase().rawQuery("SELECT  type, contentID,drugName FROM drugcaches WHERE isSaved = 1 AND userGUID = ?", new String[]{maskedGuid});
            while (rawQuery.moveToNext()) {
                DrugCache drugCache = new DrugCache();
                drugCache.setType(rawQuery.getInt(0));
                drugCache.setContentId(rawQuery.getInt(1));
                drugCache.setDrugName(rawQuery.getString(2));
                drugCache.setSaved(true);
                arrayList.add(drugCache);
            }
            close();
            rawQuery.close();
        }
        return arrayList;
    }

    public boolean deleteSavedDrug(String str) {
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        if (StringUtil.isNotEmpty(maskedGuid)) {
            try {
                getWritableDatabase().delete(DRUG_TABLE_NAME, "contentID = ? AND userGUID = ?", new String[]{str, maskedGuid});
                return true;
            } catch (Exception unused) {
            } finally {
                close();
            }
        }
        return false;
    }

    public boolean deleteSavedCache(String str, String[] strArr) {
        if (StringUtil.isNotEmpty(AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid())) {
            try {
                if (getWritableDatabase().delete(TABLE_NAME, str, strArr) <= 0) {
                    return false;
                }
                close();
                return true;
            } catch (Exception unused) {
            } finally {
                close();
            }
        }
        return false;
    }

    public boolean deleteSavedCaches() {
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        if (!StringUtil.isNotEmpty(maskedGuid)) {
            return false;
        }
        if (getWritableDatabase().delete(TABLE_NAME, "userGUID = ?", new String[]{maskedGuid}) > 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.List<com.medscape.android.db.model.Drug>, android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x008e, code lost:
        if (r2 != null) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0091, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0092, code lost:
        if (r2 != 0) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0098, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0099, code lost:
        if (r2 != 0) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0091 A[ExcHandler: all (r0v4 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:5:0x0021] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.medscape.android.db.model.Drug> getInteractionDrugs() {
        /*
            r8 = this;
            com.medscape.android.MedscapeApplication r0 = com.medscape.android.MedscapeApplication.get()
            com.medscape.android.auth.AuthenticationManager r0 = com.medscape.android.auth.AuthenticationManager.getInstance(r0)
            java.lang.String r0 = r0.getMaskedGuid()
            boolean r1 = com.medscape.android.util.StringUtil.isNullOrEmpty(r0)
            if (r1 == 0) goto L_0x001a
            com.medscape.android.MedscapeApplication r0 = com.medscape.android.MedscapeApplication.get()
            java.lang.String r0 = com.medscape.android.activity.login.LoginManager.getStoredGUID(r0)
        L_0x001a:
            boolean r1 = com.medscape.android.util.StringUtil.isNotEmpty(r0)
            r2 = 0
            if (r1 == 0) goto L_0x009f
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Exception -> 0x0098, all -> 0x0091 }
            r1.<init>()     // Catch:{ Exception -> 0x0098, all -> 0x0091 }
            android.database.sqlite.SQLiteDatabase r3 = r8.getWritableDatabase()     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            java.lang.String r4 = "SELECT id, contentId, drugName,comboId, genericId,dName,drugId  FROM interactionsdrugcaches where userGUID = ?"
            r5 = 1
            java.lang.String[] r6 = new java.lang.String[r5]     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r7 = 0
            r6[r7] = r0     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            android.database.Cursor r2 = r3.rawQuery(r4, r6)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
        L_0x0036:
            boolean r0 = r2.moveToNext()     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            if (r0 == 0) goto L_0x008e
            com.medscape.android.db.model.Drug r0 = new com.medscape.android.db.model.Drug     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r0.<init>()     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            int r3 = r2.getInt(r5)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r0.setContentId(r3)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r3 = 2
            java.lang.String r3 = r2.getString(r3)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r0.setDrugName(r3)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r3 = 3
            int r3 = r2.getInt(r3)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r0.setComboId(r3)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r3 = 4
            int r3 = r2.getInt(r3)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r0.setGenericId(r3)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r3.<init>()     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r4 = 5
            java.lang.String r6 = r2.getString(r4)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            if (r6 == 0) goto L_0x0082
            java.lang.String r6 = r2.getString(r4)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            java.lang.String r7 = ""
            boolean r6 = r6.equals(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            if (r6 != 0) goto L_0x0082
            java.lang.String r4 = r2.getString(r4)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r3.add(r4)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r0.setDnames(r3)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
        L_0x0082:
            r3 = 6
            int r3 = r2.getInt(r3)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r0.setDrugId(r3)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            r1.add(r0)     // Catch:{ Exception -> 0x0099, all -> 0x0091 }
            goto L_0x0036
        L_0x008e:
            if (r2 == 0) goto L_0x009e
            goto L_0x009b
        L_0x0091:
            r0 = move-exception
            if (r2 == 0) goto L_0x0097
            r2.close()     // Catch:{ Exception -> 0x0097 }
        L_0x0097:
            throw r0
        L_0x0098:
            r1 = r2
        L_0x0099:
            if (r2 == 0) goto L_0x009e
        L_0x009b:
            r2.close()     // Catch:{ Exception -> 0x009e }
        L_0x009e:
            r2 = r1
        L_0x009f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.cache.CacheManager.getInteractionDrugs():java.util.List");
    }

    public boolean deleteAllIntetractionsDrugs() {
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        if (!StringUtil.isNotEmpty(maskedGuid)) {
            return false;
        }
        if (getWritableDatabase().delete(INTERACTIONS_DRUGS_TABLE_NAME, "userGUID = ?", new String[]{maskedGuid}) > 0) {
            return true;
        }
        return false;
    }

    private boolean upgradeCachesTables(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE caches_COPY (id INTEGER, type INTEGER, url TEXT, content TEXT,  title TEXT, isSaved INTEGER,time TEXT, userGUID TEXT, PRIMARY KEY (id, userGUID) ); ");
            sQLiteDatabase.execSQL("INSERT INTO caches_COPY(id, type , url , content ,  title , isSaved ,time, userGUID ) SELECT id, type , url , content ,  title , isSaved ,time , '" + AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid() + "' from " + TABLE_NAME + "; ");
            sQLiteDatabase.execSQL("DROP TABLE caches; ");
            sQLiteDatabase.execSQL("ALTER TABLE caches_COPY RENAME TO caches; ");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean upgradeDrugCachesTables(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE drugcaches_COPY (id INTEGER, type INTEGER, contentID INTEGER, drugName TEXT, isSaved INTEGER, userGUID TEXT, PRIMARY KEY (contentID, userGUID)); ");
            sQLiteDatabase.execSQL("INSERT INTO drugcaches_COPY(id , type , contentID , drugName , isSaved , userGUID) SELECT id , type , contentID , drugName , isSaved , '" + AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid() + "' from " + DRUG_TABLE_NAME + "; ");
            sQLiteDatabase.execSQL("DROP TABLE drugcaches; ");
            sQLiteDatabase.execSQL("ALTER TABLE drugcaches_COPY RENAME TO drugcaches; ");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean upgradeInteractionCachesTables(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE interactionsdrugcaches_COPY (id INTEGER, contentID INTEGER, drugName TEXT, drugID INTEGER, comboId INTEGER,genericId INTEGER,dName TEXT, userGUID TEXT, PRIMARY KEY (id, userGUID)); ");
            sQLiteDatabase.execSQL("INSERT INTO interactionsdrugcaches_COPY(id , contentID , drugName , drugID , comboId ,genericId ,dName , userGUID) SELECT id , contentID , drugName , drugID , comboId ,genericId ,dName , '" + AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid() + "' from " + INTERACTIONS_DRUGS_TABLE_NAME + "; ");
            sQLiteDatabase.execSQL("DROP TABLE interactionsdrugcaches; ");
            sQLiteDatabase.execSQL("ALTER TABLE interactionsdrugcaches_COPY RENAME TO interactionsdrugcaches; ");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void createVoteHintTable() {
        getWritableDatabase().execSQL("CREATE TABLE votehintaccepetedusers (id INTEGER PRIMARY KEY,  userGUID TEXT );");
    }

    public boolean createListTable(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE liststable (id INTEGER PRIMARY KEY, listName TEXT, userGUID TEXT);");
        } catch (Throwable unused) {
        }
        return true;
    }

    public void createInteractionDrugTable(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS drugsliststable (listId INTEGER, contentId INTEGER, drugName TEXT, drugId INTEGER, comboId INTEGER, genericId INTEGER, otherNames TEXT);");
            checkAndAddColumn(DRUGS_LISTS_JUNCTION_TABLE, TableEntry.COLUMN_DRUG_OTHER_NAMES);
        } catch (Throwable unused) {
            sQLiteDatabase.execSQL("ALTER TABLE drugsliststable ADD otherNames TEXT");
        }
    }

    public long addDefaultListRow() {
        return createNewList(TableEntry.DEFAULT_LIST_NAME);
    }

    public long getDefaultListId(String str) {
        long j = -1;
        if (str != null) {
            checkDataBase();
            String[] strArr = {TableEntry.DEFAULT_LIST_NAME, str};
            Cursor query = database.query(LISTS_TABLE, new String[]{"id"}, "listName = ? AND userGUID = ?", strArr, (String) null, (String) null, (String) null);
            if (query != null) {
                while (query.moveToNext()) {
                    j = query.getLong(0);
                }
                query.close();
            }
        }
        return j;
    }

    public long createNewList(String str) {
        if (str == null || str.isEmpty()) {
            return -1;
        }
        checkDataBase();
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableEntry.COLUMN_LIST_NAME, str);
        contentValues.put("userGUID", maskedGuid);
        return database.insert(LISTS_TABLE, (String) null, contentValues);
    }

    public void removeList(long j) {
        database.delete(LISTS_TABLE, "id = ?", new String[]{j + ""});
    }

    public String getListNameById(long j) {
        String[] strArr = {TableEntry.COLUMN_LIST_NAME};
        Cursor query = database.query(LISTS_TABLE, strArr, "id = ?", new String[]{j + ""}, (String) null, (String) null, (String) null);
        if (query != null && query.moveToNext()) {
            return query.getString(0);
        }
        query.close();
        return "";
    }

    public int moveDrugsToNewList(long j, String str) {
        if (str == null) {
            return -1;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableEntry.COLUMN_LIST_ID, j + "");
        return database.update(DRUGS_LISTS_JUNCTION_TABLE, contentValues, "listId = ?", new String[]{getDefaultListId(str) + ""});
    }

    public void addDrugToList(long j, Drug drug) {
        if (drug != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TableEntry.COLUMN_LIST_ID, Long.valueOf(j));
            contentValues.put("contentId", Integer.valueOf(drug.getContentId()));
            contentValues.put("drugName", drug.getDrugName());
            contentValues.put("drugId", Integer.valueOf(drug.getDrugId()));
            contentValues.put("comboId", Integer.valueOf(drug.getComboId()));
            contentValues.put("genericId", Integer.valueOf(drug.getGenericId()));
            contentValues.put(TableEntry.COLUMN_DRUG_OTHER_NAMES, drug.otherNameToString());
            database.insert(DRUGS_LISTS_JUNCTION_TABLE, (String) null, contentValues);
        }
    }

    public void updateOldDrugs(SQLiteDatabase sQLiteDatabase) {
        Cursor query = sQLiteDatabase.query(DRUGS_LISTS_JUNCTION_TABLE, new String[]{"*"}, (String) null, (String[]) null, (String) null, (String) null, (String) null);
        if (query != null) {
            while (query.moveToNext()) {
                int i = query.getInt(0);
                int i2 = query.getInt(1);
                String string = query.getString(2);
                int i3 = query.getInt(3);
                int i4 = query.getInt(4);
                int i5 = query.getInt(5);
                Drug drug = new Drug();
                drug.setContentId(i2);
                drug.setDrugName(string);
                drug.setDrugId(i3);
                drug.setComboId(i4);
                drug.setGenericId(i5);
                if (!drug.isGenericDrug()) {
                    drug.setDrugName(getBrandDrugName(drug.getGenericId(), drug.getDrugId(), drug.getContentId()));
                    drug = getGenericDrug(drug);
                }
                if (drug != null) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("contentId", Integer.valueOf(drug.getContentId()));
                    contentValues.put("drugName", drug.getDrugName());
                    contentValues.put("drugId", Integer.valueOf(drug.getDrugId()));
                    contentValues.put("comboId", Integer.valueOf(drug.getComboId()));
                    contentValues.put("genericId", Integer.valueOf(drug.getGenericId()));
                    contentValues.put(TableEntry.COLUMN_DRUG_OTHER_NAMES, drug.otherNameToString());
                    sQLiteDatabase.update(DRUGS_LISTS_JUNCTION_TABLE, contentValues, "listId = ? AND drugId = ? AND genericId = ? AND comboId = ? AND contentId = ?", new String[]{i + "", i3 + "", i5 + "", i4 + "", i2 + ""});
                } else {
                    SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
                }
            }
            query.close();
        }
    }

    public void updateDrugInList(long j, Drug drug) {
        if (drug != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TableEntry.COLUMN_DRUG_OTHER_NAMES, drug.otherNameToString());
            database.update(DRUGS_LISTS_JUNCTION_TABLE, contentValues, "listId = ? AND drugId = ? AND genericId = ? AND comboId = ? AND contentId = ?", new String[]{j + "", drug.getDrugId() + "", drug.getGenericId() + "", drug.getComboId() + "", drug.getContentId() + ""});
        }
    }

    public void removeDrugFromList(long j, Drug drug) {
        if (drug != null) {
            checkDataBase();
            database.delete(DRUGS_LISTS_JUNCTION_TABLE, "listId = ? AND contentId = ? AND drugId = ? AND comboId = ? AND genericId = ?", new String[]{j + "", drug.getContentId() + "", drug.getDrugId() + "", drug.getComboId() + "", drug.getGenericId() + ""});
        }
    }

    public void removeAllDrugsForList(long j) {
        database.delete(DRUGS_LISTS_JUNCTION_TABLE, "listId = ?", new String[]{j + ""});
    }

    public List<Drug> getDrugsForList(Context context2, Long l) {
        Context context3 = context2;
        Long l2 = l;
        ArrayList arrayList = new ArrayList();
        if (context3 != null) {
            try {
                checkDataBase();
                String[] strArr = {"contentId", "drugName", "drugId", "comboId", "genericId", TableEntry.COLUMN_DRUG_OTHER_NAMES};
                checkAndAddColumn(DRUGS_LISTS_JUNCTION_TABLE, TableEntry.COLUMN_DRUG_OTHER_NAMES);
                Cursor query = database.query(DRUGS_LISTS_JUNCTION_TABLE, strArr, "listId = ?", new String[]{l2 + ""}, (String) null, (String) null, (String) null);
                if (query != null) {
                    while (query.moveToNext()) {
                        Drug drug = new Drug();
                        drug.setContentId(query.getInt(0));
                        drug.setDrugName(query.getString(1));
                        drug.setDrugId(query.getInt(2));
                        drug.setComboId(query.getInt(3));
                        drug.setGenericId(query.getInt(4));
                        drug.setDnames(convertOtherNamesToList(query.getString(5)));
                        arrayList.add(drug);
                    }
                    query.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                AccountProvider.logCrashlyticEvent(context3, e, e.getMessage(), "SELECT contentId, drugName, drugId, comboId, genericId, otherNames FROM drugsliststable WHERE listId = " + l2, true);
            }
        }
        return arrayList;
    }

    public boolean checkAndAddColumn(String str, String str2) {
        try {
            Cursor query = database.query(str, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            if (query == null) {
                return false;
            }
            if (query.getColumnIndex(str2) == -1) {
                SQLiteDatabase sQLiteDatabase = database;
                sQLiteDatabase.execSQL("ALTER TABLE " + str + " ADD " + str2 + " TEXT");
                query.isAfterLast();
                query.close();
                return true;
            }
            query.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> convertOtherNamesToList(String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null && !str.isEmpty()) {
            String[] split = str.split(";");
            if (split.length > 0) {
                arrayList.addAll(Arrays.asList(split));
            } else {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public Drug getDrugIdFromContentId(Context context2, int i, String str) {
        Cursor cursor;
        try {
            cursor = globalDatabase.query(DrugsContract.Drug.TABLE, new String[]{DrugsContract.Drug.CONTENT_ID, DrugsContract.Drug.DRUG_NAME, DrugsContract.Drug.DRUG_ID, "ComboID", DrugsContract.Drug.GENERIC_ID}, "ContentID = ? AND DrugName = ?", new String[]{i + "", str}, (String) null, (String) null, (String) null);
        } catch (Exception e) {
            e.printStackTrace();
            cursor = null;
        }
        if (cursor != null) {
            if (cursor.moveToNext()) {
                Drug drug = new Drug();
                drug.setContentId(cursor.getInt(0));
                drug.setDrugName(cursor.getString(1));
                drug.setDrugId(cursor.getInt(2));
                drug.setComboId(cursor.getInt(3));
                drug.setGenericId(cursor.getInt(4));
                cursor.close();
                return drug;
            }
            cursor.close();
        }
        return null;
    }

    public String getGenericDrugName(Context context2, int i, int i2) {
        if (context2 != null) {
            String[] strArr = {DrugsContract.Drug.DRUG_NAME};
            String[] strArr2 = {i + "", i + "", i2 + ""};
            Cursor cursor = null;
            try {
                cursor = globalDatabase.query(DrugsContract.Drug.TABLE, strArr, "DrugID = ? AND GenericID = ? AND ContentID = ?", strArr2, (String) null, (String) null, (String) null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    return cursor.getString(0);
                }
                cursor.close();
            }
        }
        return "";
    }

    public String getBrandDrugName(int i, int i2, int i3) {
        Cursor cursor;
        String[] strArr = {DrugsContract.Drug.DRUG_NAME};
        try {
            cursor = globalDatabase.query(DrugsContract.Drug.TABLE, strArr, "DrugID = ? AND GenericID = ? AND ContentID = ?", new String[]{i2 + "", i + "", i3 + ""}, (String) null, (String) null, (String) null);
        } catch (Exception e) {
            e.printStackTrace();
            cursor = null;
        }
        if (cursor != null) {
            if (cursor.moveToNext()) {
                return cursor.getString(0);
            }
            cursor.close();
        }
        return "";
    }

    public void addGenericName(Context context2, Drug drug) {
        String genericDrugName;
        if (context2 != null && drug != null && (genericDrugName = getGenericDrugName(context2, drug.getGenericId(), drug.getContentId())) != null && genericDrugName.length() > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(drug.getDrugName());
            drug.setDrugName(genericDrugName);
            drug.setDnames(arrayList);
        }
    }

    public void addBrandName(Drug drug) {
        String brandDrugName = getBrandDrugName(drug.getGenericId(), drug.getDrugId(), drug.getContentId());
        if (brandDrugName != null && !brandDrugName.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(brandDrugName);
            drug.setDnames(arrayList);
        }
    }

    public List<DrugList> getDrugListsForUser(long j) {
        String str;
        ArrayList arrayList = new ArrayList();
        String maskedGuid = AuthenticationManager.getInstance(MedscapeApplication.get()).getMaskedGuid();
        if (StringUtil.isNotEmpty(maskedGuid)) {
            String[] strArr = {"id", TableEntry.COLUMN_LIST_NAME};
            if (j != -1) {
                str = "userGUID = ? AND listName != ?" + " AND id != " + j;
            } else {
                str = "userGUID = ? AND listName != ?";
            }
            Cursor query = getWritableDatabase().query(LISTS_TABLE, strArr, str, new String[]{maskedGuid, TableEntry.DEFAULT_LIST_NAME}, (String) null, (String) null, "id DESC");
            if (query != null) {
                while (query.moveToNext()) {
                    arrayList.add(new DrugList(Long.valueOf((long) query.getInt(0)).longValue(), query.getString(1)));
                }
                query.close();
            }
        }
        return arrayList;
    }

    public Drug getGenericDrug(Drug drug) {
        Cursor cursor;
        int i;
        if (drug != null) {
            String[] strArr = {"contentId", "drugName", "drugId", "comboId", "genericId"};
            String[] strArr2 = {drug.getComboId() + "", drug.getGenericId() + "", drug.getGenericId() + ""};
            SQLiteDatabase sQLiteDatabase = globalDatabase;
            if (sQLiteDatabase != null) {
                i = 2;
                cursor = sQLiteDatabase.query(DrugsContract.Drug.TABLE, strArr, "comboId = ? AND drugId = ? AND genericId = ?", strArr2, (String) null, (String) null, (String) null);
            } else {
                i = 2;
                cursor = null;
            }
            if (cursor != null && cursor.getCount() == 0 && drug.isComboDrug()) {
                String[] strArr3 = new String[3];
                strArr3[0] = drug.getComboId() + "";
                strArr3[1] = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                strArr3[i] = AppEventsConstants.EVENT_PARAM_VALUE_NO;
                cursor = globalDatabase.query(DrugsContract.Drug.TABLE, strArr, "comboId = ? AND drugId = ? AND genericId = ?", strArr3, (String) null, (String) null, (String) null);
            }
            if (cursor != null) {
                Drug drug2 = null;
                while (cursor.moveToNext()) {
                    Drug drug3 = new Drug();
                    drug3.setContentId(cursor.getInt(0));
                    drug3.setDrugName(cursor.getString(1));
                    drug3.setDrugId(cursor.getInt(i));
                    drug3.setComboId(cursor.getInt(3));
                    drug3.setGenericId(cursor.getInt(4));
                    if (!drug.isGenericDrug()) {
                        drug3.addNameToOtherNames(drug.getDrugName());
                    }
                    drug2 = drug3;
                }
                cursor.close();
                return drug2;
            }
        }
        return null;
    }

    public boolean isUpgraded() {
        return isUpgraded;
    }

    public void setIsUpgraded(boolean z) {
        isUpgraded = z;
    }

    public void updateSavedAfterUpgrade() {
        ArrayList arrayList = new ArrayList(getOnUpdateSavedCache());
        if (arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("url", Util.attachHttpsToUrl((String) arrayList.get(i)));
                updateCache(contentValues, "url = ?", new String[]{(String) arrayList.get(i)});
            }
        }
    }

    private void checkDataBase() {
        SQLiteDatabase sQLiteDatabase = database;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            database = getWritableDatabase();
        }
    }
}
