package com.adobe.mobile;

import android.database.SQLException;
import android.database.sqlite.SQLiteStatement;
import android.os.Process;
import com.adobe.mobile.AbstractDatabaseBacking;
import com.adobe.mobile.AbstractHitDatabase;
import com.google.common.net.HttpHeaders;
import com.medscape.android.db.FeedDetail;
import com.wbmd.wbmddrugscommons.constants.Constants;
import java.io.File;
import java.util.HashMap;

class ThirdPartyQueue extends AbstractHitDatabase {
    protected static final String THIRDPARTY_DB_CREATE_STATEMENT = "CREATE TABLE IF NOT EXISTS HITS (ID INTEGER PRIMARY KEY AUTOINCREMENT, URL TEXT, POSTBODY TEXT, POSTTYPE TEXT, TIMESTAMP INTEGER, TIMEOUT INTEGER)";
    protected static final String THIRDPARTY_FILENAME = "ADBMobile3rdPartyDataCache.sqlite";
    protected static final String THIRDPARTY_LOG_PREFIX = "External Callback";
    protected static final String THIRDPARTY_THREAD_SUFFIX = "third-party";
    private static final int THIRDPARTY_TIMEOUT_COOLDOWN_TIMER = 30;
    private static final int THIRDPARTY_TIMESTAMP_DISABLED_WAIT_THRESHOLD = 60;
    private static final String _hitsNumberOfRowsToReturn = "1";
    private static final String _hitsOrderBy = "ID ASC";
    private static final String[] _hitsSelectedColumns = {Constants.WBMDTugStringID, FeedDetail.F_URL, "POSTBODY", "POSTTYPE", "TIMESTAMP", "TIMEOUT"};
    private static final String _hitsTableName = "HITS";
    private static ThirdPartyQueue _instance = null;
    private static final Object _instanceMutex = new Object();
    private SQLiteStatement _preparedInsertStatement = null;

    /* access modifiers changed from: protected */
    public String fileName() {
        return THIRDPARTY_FILENAME;
    }

    /* access modifiers changed from: protected */
    public String logPrefix() {
        return THIRDPARTY_LOG_PREFIX;
    }

    /* access modifiers changed from: protected */
    public String threadSuffix() {
        return THIRDPARTY_THREAD_SUFFIX;
    }

    protected static ThirdPartyQueue sharedInstance() {
        ThirdPartyQueue thirdPartyQueue;
        synchronized (_instanceMutex) {
            if (_instance == null) {
                _instance = new ThirdPartyQueue();
            }
            thirdPartyQueue = _instance;
        }
        return thirdPartyQueue;
    }

    protected ThirdPartyQueue() {
        this.fileName = fileName();
        this.logPrefix = logPrefix();
        this.dbCreateStatement = THIRDPARTY_DB_CREATE_STATEMENT;
        this.lastHitTimestamp = 0;
        initDatabaseBacking(new File(StaticMethods.getCacheDirectory(), this.fileName));
        this.numberOfUnsentHits = getTrackingQueueSize();
    }

    /* access modifiers changed from: protected */
    public void queue(String str, String str2, String str3, long j, long j2) {
        MobileConfig instance = MobileConfig.getInstance();
        if (instance == null) {
            StaticMethods.logErrorFormat("%s - Cannot send hit, MobileConfig is null (this really shouldn't happen)", this.logPrefix);
        } else if (instance.getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_OUT) {
            StaticMethods.logDebugFormat("%s - Ignoring hit due to privacy status being opted out", this.logPrefix);
        } else {
            synchronized (this.dbMutex) {
                try {
                    this._preparedInsertStatement.bindString(1, str);
                    if (str2 == null || str2.length() <= 0) {
                        this._preparedInsertStatement.bindNull(2);
                    } else {
                        this._preparedInsertStatement.bindString(2, str2);
                    }
                    if (str3 == null || str3.length() <= 0) {
                        this._preparedInsertStatement.bindNull(3);
                    } else {
                        this._preparedInsertStatement.bindString(3, str3);
                    }
                    this._preparedInsertStatement.bindLong(4, j);
                    this._preparedInsertStatement.bindLong(5, j2);
                    this._preparedInsertStatement.execute();
                    this.numberOfUnsentHits++;
                    this._preparedInsertStatement.clearBindings();
                } catch (SQLException e) {
                    StaticMethods.logErrorFormat("%s - Unable to insert url (%s)", this.logPrefix, str);
                    resetDatabase(e);
                } catch (Exception e2) {
                    StaticMethods.logErrorFormat("%s - Unknown error while inserting url (%s)", this.logPrefix, str);
                    resetDatabase(e2);
                }
            }
            kick(false);
        }
    }

    /* access modifiers changed from: protected */
    public void prepareStatements() {
        try {
            this._preparedInsertStatement = this.database.compileStatement("INSERT INTO HITS (URL, POSTBODY, POSTTYPE, TIMESTAMP, TIMEOUT) VALUES (?, ?, ?, ?, ?)");
        } catch (NullPointerException e) {
            StaticMethods.logErrorFormat("%s - Unable to create database due to an invalid path (%s)", this.logPrefix, e.getLocalizedMessage());
        } catch (SQLException e2) {
            StaticMethods.logErrorFormat("%s - Unable to create database due to a sql error (%s)", this.logPrefix, e2.getLocalizedMessage());
        } catch (Exception e3) {
            StaticMethods.logErrorFormat("%s - Unable to create database due to an unexpected error (%s)", this.logPrefix, e3.getLocalizedMessage());
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0092, code lost:
        if (r5 == null) goto L_0x0097;
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007c A[Catch:{ SQLException -> 0x007d, Exception -> 0x0065, all -> 0x0061, all -> 0x009a }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x009d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.adobe.mobile.AbstractHitDatabase.Hit selectOldestHit() {
        /*
            r15 = this;
            java.lang.Object r0 = r15.dbMutex
            monitor-enter(r0)
            r1 = 0
            r2 = 1
            r3 = 0
            r4 = 2
            android.database.sqlite.SQLiteDatabase r5 = r15.database     // Catch:{ SQLException -> 0x007d, Exception -> 0x0065, all -> 0x0061 }
            java.lang.String r6 = "HITS"
            java.lang.String[] r7 = _hitsSelectedColumns     // Catch:{ SQLException -> 0x007d, Exception -> 0x0065, all -> 0x0061 }
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            java.lang.String r12 = "ID ASC"
            java.lang.String r13 = "1"
            android.database.Cursor r5 = r5.query(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLException -> 0x007d, Exception -> 0x0065, all -> 0x0061 }
            boolean r6 = r5.moveToFirst()     // Catch:{ SQLException -> 0x005c, Exception -> 0x0057 }
            if (r6 == 0) goto L_0x0051
            com.adobe.mobile.AbstractHitDatabase$Hit r6 = new com.adobe.mobile.AbstractHitDatabase$Hit     // Catch:{ SQLException -> 0x005c, Exception -> 0x0057 }
            r6.<init>()     // Catch:{ SQLException -> 0x005c, Exception -> 0x0057 }
            java.lang.String r1 = r5.getString(r3)     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            r6.identifier = r1     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            java.lang.String r1 = r5.getString(r2)     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            r6.urlFragment = r1     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            java.lang.String r1 = r5.getString(r4)     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            r6.postBody = r1     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            r1 = 3
            java.lang.String r1 = r5.getString(r1)     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            r6.postType = r1     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            r1 = 4
            long r7 = r5.getLong(r1)     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            r6.timestamp = r7     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            r1 = 5
            int r1 = r5.getInt(r1)     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            r6.timeout = r1     // Catch:{ SQLException -> 0x004f, Exception -> 0x004d }
            r1 = r6
            goto L_0x0051
        L_0x004d:
            r1 = move-exception
            goto L_0x0069
        L_0x004f:
            r1 = move-exception
            goto L_0x0081
        L_0x0051:
            if (r5 == 0) goto L_0x0098
            r5.close()     // Catch:{ all -> 0x00a1 }
            goto L_0x0098
        L_0x0057:
            r6 = move-exception
            r14 = r6
            r6 = r1
            r1 = r14
            goto L_0x0069
        L_0x005c:
            r6 = move-exception
            r14 = r6
            r6 = r1
            r1 = r14
            goto L_0x0081
        L_0x0061:
            r2 = move-exception
            r5 = r1
            r1 = r2
            goto L_0x009b
        L_0x0065:
            r5 = move-exception
            r6 = r1
            r1 = r5
            r5 = r6
        L_0x0069:
            java.lang.String r7 = "%s - Unknown error reading from database (%s)"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x009a }
            java.lang.String r8 = r15.logPrefix     // Catch:{ all -> 0x009a }
            r4[r3] = r8     // Catch:{ all -> 0x009a }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x009a }
            r4[r2] = r1     // Catch:{ all -> 0x009a }
            com.adobe.mobile.StaticMethods.logErrorFormat(r7, r4)     // Catch:{ all -> 0x009a }
            if (r5 == 0) goto L_0x0097
            goto L_0x0094
        L_0x007d:
            r5 = move-exception
            r6 = r1
            r1 = r5
            r5 = r6
        L_0x0081:
            java.lang.String r7 = "%s - Unable to read from database (%s)"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x009a }
            java.lang.String r8 = r15.logPrefix     // Catch:{ all -> 0x009a }
            r4[r3] = r8     // Catch:{ all -> 0x009a }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x009a }
            r4[r2] = r1     // Catch:{ all -> 0x009a }
            com.adobe.mobile.StaticMethods.logErrorFormat(r7, r4)     // Catch:{ all -> 0x009a }
            if (r5 == 0) goto L_0x0097
        L_0x0094:
            r5.close()     // Catch:{ all -> 0x00a1 }
        L_0x0097:
            r1 = r6
        L_0x0098:
            monitor-exit(r0)     // Catch:{ all -> 0x00a1 }
            return r1
        L_0x009a:
            r1 = move-exception
        L_0x009b:
            if (r5 == 0) goto L_0x00a0
            r5.close()     // Catch:{ all -> 0x00a1 }
        L_0x00a0:
            throw r1     // Catch:{ all -> 0x00a1 }
        L_0x00a1:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00a1 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.ThirdPartyQueue.selectOldestHit():com.adobe.mobile.AbstractHitDatabase$Hit");
    }

    /* access modifiers changed from: protected */
    public ThirdPartyQueue getWorker() {
        return sharedInstance();
    }

    /* access modifiers changed from: protected */
    public Runnable workerThread() {
        final ThirdPartyQueue worker = getWorker();
        return new Runnable() {
            public void run() {
                AbstractHitDatabase.Hit selectOldestHit;
                Process.setThreadPriority(10);
                boolean offlineTrackingEnabled = MobileConfig.getInstance().getOfflineTrackingEnabled();
                HashMap hashMap = new HashMap();
                hashMap.put(HttpHeaders.ACCEPT_LANGUAGE, StaticMethods.getDefaultAcceptLanguage());
                hashMap.put("User-Agent", StaticMethods.getDefaultUserAgent());
                while (MobileConfig.getInstance().getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN && ((!MobileConfig.getInstance().reachabilityChecksEnabled() || MobileConfig.getInstance().networkConnectivity()) && (selectOldestHit = worker.selectOldestHit()) != null && selectOldestHit.urlFragment != null)) {
                    if (offlineTrackingEnabled || selectOldestHit.timestamp >= StaticMethods.getTimeSince1970() - 60) {
                        String str = "";
                        selectOldestHit.postBody = selectOldestHit.postBody != null ? selectOldestHit.postBody : str;
                        if (selectOldestHit.postType != null) {
                            str = selectOldestHit.postType;
                        }
                        selectOldestHit.postType = str;
                        selectOldestHit.timeout = selectOldestHit.timeout < 2 ? 2000 : selectOldestHit.timeout * 1000;
                        if (RequestHandler.sendThirdPartyRequest(selectOldestHit.urlFragment, selectOldestHit.postBody, hashMap, selectOldestHit.timeout, selectOldestHit.postType, ThirdPartyQueue.this.logPrefix)) {
                            try {
                                worker.deleteHit(selectOldestHit.identifier);
                                worker.lastHitTimestamp = selectOldestHit.timestamp;
                            } catch (AbstractDatabaseBacking.CorruptedDatabaseException e) {
                                worker.resetDatabase(e);
                            }
                        } else {
                            StaticMethods.logWarningFormat("%s - Unable to forward hit (%s)", ThirdPartyQueue.this.logPrefix, selectOldestHit.urlFragment);
                            if (MobileConfig.getInstance().getOfflineTrackingEnabled()) {
                                StaticMethods.logDebugFormat("%s - Network error, imposing internal cooldown (%d seconds)", ThirdPartyQueue.this.logPrefix, 30L);
                                try {
                                    MobileConfig instance = MobileConfig.getInstance();
                                    for (int i = 0; ((long) i) < 30 && (!instance.reachabilityChecksEnabled() || instance.networkConnectivity()); i++) {
                                        Thread.sleep(1000);
                                    }
                                } catch (Exception e2) {
                                    StaticMethods.logWarningFormat("%s - Background Thread Interrupted (%s)", ThirdPartyQueue.this.logPrefix, e2.getMessage());
                                }
                            } else {
                                try {
                                    worker.deleteHit(selectOldestHit.identifier);
                                } catch (AbstractDatabaseBacking.CorruptedDatabaseException e3) {
                                    worker.resetDatabase(e3);
                                }
                            }
                        }
                    } else {
                        try {
                            worker.deleteHit(selectOldestHit.identifier);
                        } catch (AbstractDatabaseBacking.CorruptedDatabaseException e4) {
                            worker.resetDatabase(e4);
                        }
                    }
                }
                worker.bgThreadActive = false;
            }
        };
    }
}
