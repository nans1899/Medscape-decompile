package com.adobe.mobile;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Process;
import com.adobe.mobile.AbstractDatabaseBacking;
import com.adobe.mobile.AbstractHitDatabase;
import com.google.common.net.HttpHeaders;
import com.medscape.android.db.FeedDetail;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final class AnalyticsWorker extends AbstractHitDatabase {
    protected static final String ANALYTICS_DB_CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS HITS (ID INTEGER PRIMARY KEY AUTOINCREMENT, URL TEXT, TIMESTAMP INTEGER)";
    protected static final String ANALYTICS_FILENAME = "ADBMobileDataCache.sqlite";
    private static final int CONNECTION_TIMEOUT_MSEC = 5000;
    private static final int TIMESTAMP_DISABLED_WAIT_THRESHOLD = 60;
    private static AnalyticsWorker _instance = null;
    private static final Object _instanceMutex = new Object();
    private static volatile boolean analyticsGetBaseURL_pred = true;
    private static String baseURL;
    /* access modifiers changed from: private */
    public static final SecureRandom randomGen = new SecureRandom();
    protected SQLiteStatement _preparedInsertStatement = null;

    public static AnalyticsWorker sharedInstance() {
        AnalyticsWorker analyticsWorker;
        synchronized (_instanceMutex) {
            if (_instance == null) {
                _instance = new AnalyticsWorker();
            }
            analyticsWorker = _instance;
        }
        return analyticsWorker;
    }

    protected AnalyticsWorker() {
        this.fileName = ANALYTICS_FILENAME;
        this.logPrefix = "Analytics";
        this.dbCreateStatement = ANALYTICS_DB_CREATE_STATEMENT;
        this.lastHitTimestamp = 0;
        initDatabaseBacking(new File(StaticMethods.getCacheDirectory(), this.fileName));
        this.numberOfUnsentHits = getTrackingQueueSize();
    }

    /* access modifiers changed from: protected */
    public void queue(String str, long j) {
        MobileConfig instance = MobileConfig.getInstance();
        if (instance == null) {
            StaticMethods.logErrorFormat("Analytics - Cannot send hit, MobileConfig is null (this really shouldn't happen)", new Object[0]);
        } else if (MobileConfig.getInstance().mobileUsingAnalytics()) {
            if (instance.getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT) {
                StaticMethods.logDebugFormat("Analytics - Ignoring hit due to privacy status being opted out", new Object[0]);
            } else if (this.databaseStatus == AbstractDatabaseBacking.DatabaseStatus.FATALERROR) {
                StaticMethods.logErrorFormat("Analytics - Ignoring hit due to database error", new Object[0]);
            } else {
                synchronized (this.dbMutex) {
                    try {
                        this._preparedInsertStatement.bindString(1, str);
                        this._preparedInsertStatement.bindLong(2, j);
                        this._preparedInsertStatement.execute();
                        StaticMethods.updateLastKnownTimestamp(Long.valueOf(j));
                        this.numberOfUnsentHits++;
                        this._preparedInsertStatement.clearBindings();
                    } catch (SQLException e) {
                        StaticMethods.logErrorFormat("Analytics - Unable to insert url (%s)", str);
                        resetDatabase(e);
                    } catch (Exception e2) {
                        StaticMethods.logErrorFormat("Analytics - Unknown error while inserting url (%s)", str);
                        resetDatabase(e2);
                    }
                }
                kick(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public static String getBaseURL() {
        if (analyticsGetBaseURL_pred) {
            analyticsGetBaseURL_pred = false;
            StringBuilder sb = new StringBuilder();
            sb.append(MobileConfig.getInstance().getSSL() ? "https://" : "http://");
            sb.append(MobileConfig.getInstance().getTrackingServer());
            sb.append("/b/ss/");
            sb.append(StaticMethods.URLEncode(MobileConfig.getInstance().getReportSuiteIds()));
            sb.append("/");
            sb.append(MobileConfig.getInstance().getAnalyticsResponseType());
            sb.append("/JAVA-");
            sb.append("4.17.5-AN");
            sb.append("/s");
            String sb2 = sb.toString();
            baseURL = sb2;
            StaticMethods.logDebugFormat("Analytics - Setting base request URL(%s)", sb2);
        }
        return baseURL;
    }

    /* access modifiers changed from: protected */
    public void preMigrate() {
        File file = new File(StaticMethods.getCacheDirectory() + this.fileName);
        File file2 = new File(StaticMethods.getCacheDirectory(), this.fileName);
        if (file.exists() && !file2.exists()) {
            try {
                if (!file.renameTo(file2)) {
                    StaticMethods.logWarningFormat("Analytics - Unable to migrate old hits db, creating new hits db (move file returned false)", new Object[0]);
                }
            } catch (Exception e) {
                StaticMethods.logWarningFormat("Analytics - Unable to migrate old hits db, creating new hits db (%s)", e.getLocalizedMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void prepareStatements() {
        try {
            this._preparedInsertStatement = this.database.compileStatement("INSERT INTO HITS (URL, TIMESTAMP) VALUES (?, ?)");
        } catch (NullPointerException e) {
            StaticMethods.logErrorFormat("Analytics - Unable to create database due to an invalid path (%s)", e.getLocalizedMessage());
        } catch (SQLException e2) {
            StaticMethods.logErrorFormat("Analytics - Unable to create database due to a sql error (%s)", e2.getLocalizedMessage());
        } catch (Exception e3) {
            StaticMethods.logErrorFormat("Analytics - Unable to create database due to an unexpected error (%s)", e3.getLocalizedMessage());
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007d, code lost:
        if (r4 == null) goto L_0x0082;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b A[Catch:{ SQLException -> 0x006c, Exception -> 0x0058, all -> 0x0054, all -> 0x0085 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0088  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adobe.mobile.AbstractHitDatabase.Hit selectOldestHit() {
        /*
            r14 = this;
            java.lang.Object r0 = r14.dbMutex
            monitor-enter(r0)
            r1 = 0
            r2 = 0
            r3 = 1
            android.database.sqlite.SQLiteDatabase r4 = r14.database     // Catch:{ SQLException -> 0x006c, Exception -> 0x0058, all -> 0x0054 }
            java.lang.String r5 = "HITS"
            java.lang.String r6 = "ID"
            java.lang.String r7 = "URL"
            java.lang.String r8 = "TIMESTAMP"
            java.lang.String[] r6 = new java.lang.String[]{r6, r7, r8}     // Catch:{ SQLException -> 0x006c, Exception -> 0x0058, all -> 0x0054 }
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            java.lang.String r11 = "ID ASC"
            java.lang.String r12 = "1"
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ SQLException -> 0x006c, Exception -> 0x0058, all -> 0x0054 }
            boolean r5 = r4.moveToFirst()     // Catch:{ SQLException -> 0x004f, Exception -> 0x004a }
            if (r5 == 0) goto L_0x0044
            com.adobe.mobile.AbstractHitDatabase$Hit r5 = new com.adobe.mobile.AbstractHitDatabase$Hit     // Catch:{ SQLException -> 0x004f, Exception -> 0x004a }
            r5.<init>()     // Catch:{ SQLException -> 0x004f, Exception -> 0x004a }
            java.lang.String r1 = r4.getString(r2)     // Catch:{ SQLException -> 0x0042, Exception -> 0x0040 }
            r5.identifier = r1     // Catch:{ SQLException -> 0x0042, Exception -> 0x0040 }
            java.lang.String r1 = r4.getString(r3)     // Catch:{ SQLException -> 0x0042, Exception -> 0x0040 }
            r5.urlFragment = r1     // Catch:{ SQLException -> 0x0042, Exception -> 0x0040 }
            r1 = 2
            long r6 = r4.getLong(r1)     // Catch:{ SQLException -> 0x0042, Exception -> 0x0040 }
            r5.timestamp = r6     // Catch:{ SQLException -> 0x0042, Exception -> 0x0040 }
            r1 = r5
            goto L_0x0044
        L_0x0040:
            r1 = move-exception
            goto L_0x005c
        L_0x0042:
            r1 = move-exception
            goto L_0x0070
        L_0x0044:
            if (r4 == 0) goto L_0x0083
            r4.close()     // Catch:{ all -> 0x008c }
            goto L_0x0083
        L_0x004a:
            r5 = move-exception
            r13 = r5
            r5 = r1
            r1 = r13
            goto L_0x005c
        L_0x004f:
            r5 = move-exception
            r13 = r5
            r5 = r1
            r1 = r13
            goto L_0x0070
        L_0x0054:
            r2 = move-exception
            r4 = r1
            r1 = r2
            goto L_0x0086
        L_0x0058:
            r4 = move-exception
            r5 = r1
            r1 = r4
            r4 = r5
        L_0x005c:
            java.lang.String r6 = "Analytics - Unknown error reading from database (%s)"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0085 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0085 }
            r3[r2] = r1     // Catch:{ all -> 0x0085 }
            com.adobe.mobile.StaticMethods.logErrorFormat(r6, r3)     // Catch:{ all -> 0x0085 }
            if (r4 == 0) goto L_0x0082
            goto L_0x007f
        L_0x006c:
            r4 = move-exception
            r5 = r1
            r1 = r4
            r4 = r5
        L_0x0070:
            java.lang.String r6 = "Analytics - Unable to read from database (%s)"
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x0085 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0085 }
            r3[r2] = r1     // Catch:{ all -> 0x0085 }
            com.adobe.mobile.StaticMethods.logErrorFormat(r6, r3)     // Catch:{ all -> 0x0085 }
            if (r4 == 0) goto L_0x0082
        L_0x007f:
            r4.close()     // Catch:{ all -> 0x008c }
        L_0x0082:
            r1 = r5
        L_0x0083:
            monitor-exit(r0)     // Catch:{ all -> 0x008c }
            return r1
        L_0x0085:
            r1 = move-exception
        L_0x0086:
            if (r4 == 0) goto L_0x008b
            r4.close()     // Catch:{ all -> 0x008c }
        L_0x008b:
            throw r1     // Catch:{ all -> 0x008c }
        L_0x008c:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x008c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.AnalyticsWorker.selectOldestHit():com.adobe.mobile.AbstractHitDatabase$Hit");
    }

    /* access modifiers changed from: protected */
    public final Runnable workerThread() {
        return new Runnable() {
            public void run() {
                AbstractHitDatabase.Hit selectOldestHit;
                AnalyticsWorker sharedInstance = AnalyticsWorker.sharedInstance();
                Process.setThreadPriority(10);
                HashMap hashMap = new HashMap();
                hashMap.put(HttpHeaders.ACCEPT_LANGUAGE, StaticMethods.getDefaultAcceptLanguage());
                hashMap.put("User-Agent", StaticMethods.getDefaultUserAgent());
                while (MobileConfig.getInstance().getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN && ((!MobileConfig.getInstance().reachabilityChecksEnabled() || MobileConfig.getInstance().networkConnectivity()) && sharedInstance.databaseStatus == AbstractDatabaseBacking.DatabaseStatus.OK && (selectOldestHit = sharedInstance.selectOldestHit()) != null)) {
                    if (MobileConfig.getInstance().getOfflineTrackingEnabled()) {
                        if (selectOldestHit.timestamp - sharedInstance.lastHitTimestamp < 0) {
                            long j = sharedInstance.lastHitTimestamp + 1;
                            selectOldestHit.urlFragment = selectOldestHit.urlFragment.replaceFirst("&ts=" + Long.toString(selectOldestHit.timestamp), "&ts=" + Long.toString(j));
                            StaticMethods.logDebugFormat("Analytics - Adjusting out of order hit timestamp(%d->%d)", Long.valueOf(selectOldestHit.timestamp), Long.valueOf(j));
                            selectOldestHit.timestamp = j;
                        }
                    } else if (selectOldestHit.timestamp < StaticMethods.getTimeSince1970() - 60) {
                        try {
                            sharedInstance.deleteHit(selectOldestHit.identifier);
                        } catch (AbstractDatabaseBacking.CorruptedDatabaseException e) {
                            AnalyticsWorker.sharedInstance().resetDatabase(e);
                        }
                    }
                    byte[] retrieveAnalyticsRequestData = RequestHandler.retrieveAnalyticsRequestData(AnalyticsWorker.getBaseURL() + AnalyticsWorker.randomGen.nextInt(100000000), selectOldestHit.urlFragment.startsWith("ndh") ? selectOldestHit.urlFragment : selectOldestHit.urlFragment.substring(selectOldestHit.urlFragment.indexOf(63) + 1), hashMap, 5000, AnalyticsWorker.this.logPrefix);
                    if (retrieveAnalyticsRequestData == null) {
                        int i = 0;
                        while (((long) i) < 30) {
                            try {
                                if (MobileConfig.getInstance().reachabilityChecksEnabled() && !MobileConfig.getInstance().networkConnectivity()) {
                                    break;
                                }
                                Thread.sleep(1000);
                                i++;
                            } catch (Exception e2) {
                                StaticMethods.logWarningFormat("Analytics - Background Thread Interrupted(%s)", e2.getMessage());
                            }
                        }
                    } else if (retrieveAnalyticsRequestData.length > 1) {
                        try {
                            sharedInstance.deleteHit(selectOldestHit.identifier);
                            sharedInstance.lastHitTimestamp = selectOldestHit.timestamp;
                            final JSONObject jSONObject = new JSONObject(new String(retrieveAnalyticsRequestData, "UTF-8"));
                            StaticMethods.getAudienceExecutor().execute(new Runnable() {
                                public void run() {
                                    AudienceManagerWorker.processJsonResponse(jSONObject);
                                }
                            });
                        } catch (AbstractDatabaseBacking.CorruptedDatabaseException e3) {
                            AnalyticsWorker.sharedInstance().resetDatabase(e3);
                        } catch (UnsupportedEncodingException e4) {
                            StaticMethods.logWarningFormat("Audience Manager - Unable to decode server response (%s)", e4.getLocalizedMessage());
                        } catch (JSONException e5) {
                            StaticMethods.logWarningFormat("Audience Manager - Unable to parse JSON data (%s)", e5.getLocalizedMessage());
                        }
                    } else {
                        try {
                            sharedInstance.deleteHit(selectOldestHit.identifier);
                            sharedInstance.lastHitTimestamp = selectOldestHit.timestamp;
                        } catch (AbstractDatabaseBacking.CorruptedDatabaseException e6) {
                            AnalyticsWorker.sharedInstance().resetDatabase(e6);
                        }
                    }
                }
                sharedInstance.bgThreadActive = false;
            }
        };
    }

    /* access modifiers changed from: protected */
    public void kickWithReferrerData(Map<String, Object> map) {
        if (map == null || map.size() <= 0) {
            ReferrerHandler.setReferrerProcessed(true);
            kick(false);
            return;
        }
        AbstractHitDatabase.Hit selectOldestHit = selectOldestHit();
        if (!(selectOldestHit == null || selectOldestHit.urlFragment == null)) {
            selectOldestHit.urlFragment = StaticMethods.appendContextData(map, selectOldestHit.urlFragment);
            updateHitInDatabase(selectOldestHit);
            ReferrerHandler.setReferrerProcessed(true);
        }
        kick(false);
    }

    /* access modifiers changed from: protected */
    public void updateHitInDatabase(AbstractHitDatabase.Hit hit) {
        synchronized (this.dbMutex) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(FeedDetail.F_URL, hit.urlFragment);
                SQLiteDatabase sQLiteDatabase = this.database;
                sQLiteDatabase.update("HITS", contentValues, "id=" + hit.identifier, (String[]) null);
            } catch (SQLException e) {
                StaticMethods.logErrorFormat("Analytics - Unable to update url in database (%s)", e.getMessage());
            } catch (Exception e2) {
                StaticMethods.logErrorFormat("Analytics - Unknown error updating url in database (%s)", e2.getMessage());
            }
        }
    }
}
