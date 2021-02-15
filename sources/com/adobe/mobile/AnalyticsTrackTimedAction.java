package com.adobe.mobile;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteStatement;
import com.adobe.mobile.Analytics;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

final class AnalyticsTrackTimedAction extends AbstractDatabaseBacking {
    private static final int NUMBER_OF_SECONDS_IN_A_YEAR = 31536000;
    private static final String TIMED_ACTION_IN_APP_TIME_KEY = "a.action.time.inapp";
    private static final String TIMED_ACTION_TOTAL_TIME_KEY = "a.action.time.total";
    private static AnalyticsTrackTimedAction _instance;
    private static final Object _instanceMutex = new Object();
    private SQLiteStatement sqlDeleteAction;
    private SQLiteStatement sqlDeleteContextDataForAction;
    private String sqlExistsAction;
    private String sqlExistsContextDataByActionAndKey;
    private SQLiteStatement sqlInsertAction;
    private SQLiteStatement sqlInsertContextData;
    private String sqlSelectAction;
    private String sqlSelectContextDataForAction;
    private SQLiteStatement sqlUpdateAction;
    private SQLiteStatement sqlUpdateActionsClearAdjustedTime;
    private SQLiteStatement sqlUpdateContextData;

    /* access modifiers changed from: protected */
    public void postMigrate() {
    }

    /* access modifiers changed from: protected */
    public void postReset() {
    }

    public static AnalyticsTrackTimedAction sharedInstance() {
        AnalyticsTrackTimedAction analyticsTrackTimedAction;
        synchronized (_instanceMutex) {
            if (_instance == null) {
                _instance = new AnalyticsTrackTimedAction();
            }
            analyticsTrackTimedAction = _instance;
        }
        return analyticsTrackTimedAction;
    }

    private AnalyticsTrackTimedAction() {
        this.fileName = "ADBMobileTimedActionsCache.sqlite";
        this.logPrefix = "Analytics";
        initDatabaseBacking(new File(StaticMethods.getCacheDirectory(), this.fileName));
    }

    /* access modifiers changed from: protected */
    public void trackTimedActionStart(String str, Map<String, Object> map) {
        if (str == null || str.trim().length() == 0) {
            StaticMethods.logWarningFormat("%s - trackTimedActionStart() failed(the required parameter actionName was null or empty.)", this.logPrefix);
            return;
        }
        long timeSince1970 = StaticMethods.getTimeSince1970();
        AnalyticsTimedAction timedAction = getTimedAction(str);
        if (timedAction != null) {
            deleteTimedAction(timedAction.databaseID);
        }
        insertTimedAction(str, map, timeSince1970);
    }

    /* access modifiers changed from: protected */
    public void trackTimedActionUpdate(String str, Map<String, Object> map) {
        if (str == null || str.trim().length() == 0) {
            StaticMethods.logWarningFormat("%s - Unable to update the timed action (timed action name was null or empty)", this.logPrefix);
        } else if (map == null || map.isEmpty()) {
            StaticMethods.logWarningFormat("%s - Unable to update the timed action (context data was null or empty)", this.logPrefix);
        } else {
            saveContextDataForTimedAction(str, map);
        }
    }

    /* access modifiers changed from: protected */
    public void trackTimedActionEnd(String str, Analytics.TimedActionBlock<Boolean> timedActionBlock) {
        final String str2 = str;
        if (str2 == null || str.trim().length() == 0) {
            StaticMethods.logWarningFormat("%s - Unable to end the timed action (timed action name was null or empty)", this.logPrefix);
            return;
        }
        AnalyticsTimedAction timedAction = getTimedAction(str);
        if (timedAction != null) {
            Long valueOf = Long.valueOf(StaticMethods.getTimeSince1970());
            long j = -1;
            long longValue = timedAction.adjustedStartTime == -1 ? -1 : valueOf.longValue() - timedAction.adjustedStartTime;
            long longValue2 = valueOf.longValue() - timedAction.startTime;
            if (longValue < 0 || longValue > 31536000) {
                longValue = -1;
            }
            if (longValue2 >= 0 && longValue2 <= 31536000) {
                j = longValue2;
            }
            final HashMap hashMap = timedAction.contextData != null ? new HashMap(timedAction.contextData) : new HashMap();
            if (timedActionBlock == null || timedActionBlock.call(longValue, j, hashMap).booleanValue()) {
                if (j >= 0) {
                    hashMap.put(TIMED_ACTION_TOTAL_TIME_KEY, String.valueOf(j));
                }
                if (longValue >= 0) {
                    hashMap.put(TIMED_ACTION_IN_APP_TIME_KEY, String.valueOf(longValue));
                }
                StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
                    public void run() {
                        AnalyticsTrackAction.trackAction(str2, hashMap);
                    }
                });
            } else {
                StaticMethods.logDebugFormat("%s - Not sending hit for timed action due to block cancellation (%s)", this.logPrefix, str2);
            }
            deleteTimedAction(timedAction.databaseID);
            return;
        }
        StaticMethods.logWarningFormat("%s - Unable to end a timed action that has not yet begun (no timed action was found matching the name %s)", this.logPrefix, str2);
    }

    /* access modifiers changed from: protected */
    public void trackTimedActionUpdateAdjustedStartTime(long j) {
        synchronized (this.dbMutex) {
            try {
                this.sqlUpdateAction.bindLong(1, j);
                this.sqlUpdateAction.execute();
                this.sqlUpdateAction.clearBindings();
            } catch (SQLException e) {
                StaticMethods.logErrorFormat("%s - Unable to bind prepared statement values for updating the adjusted start time for timed action (%s)", this.logPrefix, e.getLocalizedMessage());
                resetDatabase(e);
            } catch (Exception e2) {
                StaticMethods.logErrorFormat("%s - Unable to adjust start times for timed actions (%s)", this.logPrefix, e2.getMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void trackTimedActionUpdateActionsClearAdjustedStartTime() {
        synchronized (this.dbMutex) {
            try {
                this.sqlUpdateActionsClearAdjustedTime.execute();
                this.sqlUpdateActionsClearAdjustedTime.clearBindings();
            } catch (SQLException e) {
                StaticMethods.logErrorFormat("%s - Unable to update adjusted time for timed actions after crash (%s)", this.logPrefix, e.getMessage());
                resetDatabase(e);
            } catch (Exception e2) {
                StaticMethods.logErrorFormat("%s - Unknown error clearing adjusted start times for timed actions (%s)", this.logPrefix, e2.getMessage());
                resetDatabase(e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean trackTimedActionExists(String str) {
        boolean z;
        if (str == null || str.trim().length() == 0) {
            StaticMethods.logWarningFormat("%s - Unable to verify the existence of timed action (timed action name was null or empty)", this.logPrefix);
            return false;
        }
        synchronized (this.dbMutex) {
            if (this.database == null) {
                return false;
            }
            try {
                Cursor rawQuery = this.database.rawQuery(this.sqlExistsAction, new String[]{str});
                z = rawQuery.moveToFirst() && rawQuery.getInt(0) > 0;
                try {
                    rawQuery.close();
                } catch (SQLException e) {
                    e = e;
                } catch (Exception e2) {
                    e = e2;
                    StaticMethods.logErrorFormat("%s - Unknown error checking for timed action (%s)", this.logPrefix, e.getMessage());
                    return z;
                }
            } catch (SQLException e3) {
                e = e3;
                z = false;
                StaticMethods.logErrorFormat("%s - Unable to query timed actions database (%s)", this.logPrefix, e.getMessage());
                return z;
            } catch (Exception e4) {
                e = e4;
                z = false;
                StaticMethods.logErrorFormat("%s - Unknown error checking for timed action (%s)", this.logPrefix, e.getMessage());
                return z;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void preMigrate() {
        File file = new File(StaticMethods.getCacheDirectory() + "ADBMobileDataCache.sqlite" + this.fileName);
        File file2 = new File(StaticMethods.getCacheDirectory(), this.fileName);
        if (file.exists() && !file2.exists()) {
            try {
                if (!file.renameTo(file2)) {
                    StaticMethods.logWarningFormat("%s - Unable to migrate old Timed Actions db, creating new Timed Actions db (move file returned false)", this.logPrefix);
                }
            } catch (Exception e) {
                StaticMethods.logWarningFormat("%s - Unable to migrate old Timed Actions db, creating new Timed Actions db (%s)", this.logPrefix, e.getLocalizedMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initializeDatabase() {
        try {
            this.database.execSQL("CREATE TABLE IF NOT EXISTS TIMEDACTIONS (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, STARTTIME INTEGER, ADJSTARTTIME INTEGER)");
            this.database.execSQL("CREATE TABLE IF NOT EXISTS CONTEXTDATA (ID INTEGER PRIMARY KEY AUTOINCREMENT, ACTIONID INTEGER, KEY TEXT, VALUE TEXT, FOREIGN KEY(ACTIONID) REFERENCES TIMEDACTIONS(ID))");
        } catch (SQLException e) {
            StaticMethods.logErrorFormat("%s - Unable to open or create timed actions database (%s)", this.logPrefix, e.getMessage());
        } catch (Exception e2) {
            StaticMethods.logErrorFormat("%s - Uknown error creating timed actions database (%s)", this.logPrefix, e2.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void prepareStatements() {
        this.sqlSelectAction = "SELECT ID, STARTTIME, ADJSTARTTIME FROM TIMEDACTIONS WHERE NAME=?";
        this.sqlExistsAction = "SELECT COUNT(*) FROM TIMEDACTIONS WHERE NAME=?";
        this.sqlSelectContextDataForAction = "SELECT KEY, VALUE FROM CONTEXTDATA WHERE ACTIONID=?";
        this.sqlExistsContextDataByActionAndKey = "SELECT COUNT(*) FROM CONTEXTDATA WHERE ACTIONID=? AND KEY=?";
        try {
            this.sqlInsertAction = this.database.compileStatement("INSERT INTO TIMEDACTIONS (NAME, STARTTIME, ADJSTARTTIME) VALUES (@NAME, @START, @START)");
            this.sqlUpdateAction = this.database.compileStatement("UPDATE TIMEDACTIONS SET ADJSTARTTIME=ADJSTARTTIME+@DELTA WHERE ADJSTARTTIME!=-1");
            this.sqlUpdateActionsClearAdjustedTime = this.database.compileStatement("UPDATE TIMEDACTIONS SET ADJSTARTTIME=-1");
            this.sqlDeleteAction = this.database.compileStatement("DELETE FROM TIMEDACTIONS WHERE ID=@ID");
            this.sqlInsertContextData = this.database.compileStatement("INSERT INTO CONTEXTDATA(ACTIONID, KEY, VALUE) VALUES (@ACTIONID, @KEY, @VALUE)");
            this.sqlUpdateContextData = this.database.compileStatement("UPDATE CONTEXTDATA SET VALUE=@VALUE WHERE ACTIONID=@ACTIONID AND KEY=@KEY");
            this.sqlDeleteContextDataForAction = this.database.compileStatement("DELETE FROM CONTEXTDATA WHERE ACTIONID=@ACTIONID");
        } catch (SQLException e) {
            StaticMethods.logErrorFormat("Analytics - unable to prepare the needed SQL statements for interacting with the timed actions database (%s)", e.getMessage());
        } catch (Exception e2) {
            StaticMethods.logErrorFormat("Analytics - Unknown error preparing sql statements (%s)", e2.getMessage());
        }
    }

    private void insertTimedAction(String str, Map<String, Object> map, long j) {
        if (str == null || str.trim().length() == 0) {
            StaticMethods.logWarningFormat("Analytics - Unable to insert timed action (timed action name was null or empty)", new Object[0]);
            return;
        }
        synchronized (this.dbMutex) {
            try {
                this.sqlInsertAction.bindString(1, str);
                this.sqlInsertAction.bindLong(2, j);
                if (this.sqlInsertAction.executeInsert() == -1) {
                    StaticMethods.logWarningFormat("Analytics - Unable to insert the timed action (%s)", str);
                }
                this.sqlInsertAction.clearBindings();
            } catch (SQLException e) {
                StaticMethods.logErrorFormat("Analytics - Unable to bind prepared statement values for inserting the timed action (%s)", str);
                resetDatabase(e);
            } catch (Exception e2) {
                StaticMethods.logErrorFormat("Analyitcs - Unknown error when inserting timed action (%s)", e2.getMessage());
            }
        }
        saveContextDataForTimedAction(str, map);
    }

    private void saveContextDataForTimedAction(String str, Map<String, Object> map) {
        String str2;
        if (str == null || str.trim().length() == 0) {
            StaticMethods.logWarningFormat("Analytics - Unable to save context data (timed action name was null or empty)", new Object[0]);
        } else if (map == null || map.isEmpty()) {
            StaticMethods.logWarningFormat("Analytics - Unable to save context data (context data was null or empty)", new Object[0]);
        } else {
            synchronized (this.dbMutex) {
                try {
                    if (this.database == null) {
                        StaticMethods.logErrorFormat("Analytics - Null Database Object, unable to save context data for timed action", new Object[0]);
                        return;
                    }
                    Cursor rawQuery = this.database.rawQuery(this.sqlSelectAction, new String[]{str});
                    if (rawQuery.moveToFirst()) {
                        int i = rawQuery.getInt(0);
                        rawQuery.close();
                        for (Map.Entry next : map.entrySet()) {
                            String str3 = (String) next.getKey();
                            Object value = next.getValue();
                            if (str3 != null) {
                                if (str3.length() > 0) {
                                    Cursor rawQuery2 = this.database.rawQuery(this.sqlExistsContextDataByActionAndKey, new String[]{String.valueOf(i), (String) next.getKey()});
                                    if (value == null) {
                                        str2 = "";
                                    } else {
                                        str2 = value.toString();
                                    }
                                    if (!rawQuery2.moveToFirst() || rawQuery2.getInt(0) <= 0) {
                                        this.sqlInsertContextData.bindLong(1, (long) i);
                                        this.sqlInsertContextData.bindString(2, (String) next.getKey());
                                        this.sqlInsertContextData.bindString(3, str2);
                                        if (this.sqlInsertContextData.executeInsert() == -1) {
                                            StaticMethods.logWarningFormat("Analytics - Unable to insert the timed action's context data (%s)", str);
                                        }
                                        this.sqlInsertContextData.clearBindings();
                                    } else {
                                        this.sqlUpdateContextData.bindString(1, str2);
                                        this.sqlUpdateContextData.bindLong(2, (long) i);
                                        this.sqlUpdateContextData.bindString(3, (String) next.getKey());
                                        this.sqlUpdateContextData.execute();
                                        this.sqlUpdateContextData.clearBindings();
                                    }
                                    rawQuery2.close();
                                }
                            }
                        }
                    } else {
                        rawQuery.close();
                        StaticMethods.logWarningFormat("Analytics - Unable to save context data (no timed action was found matching the name %s)", str);
                    }
                } catch (SQLException e) {
                    StaticMethods.logErrorFormat("Analytics - SQL exception when attempting to update context data for timed action (%s)", e.getMessage());
                } catch (Exception e2) {
                    StaticMethods.logErrorFormat("Analytics - Unexpected exception when attempting to update context data for timed action (%s)", e2.getMessage());
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private AnalyticsTimedAction getTimedAction(String str) {
        AnalyticsTimedAction analyticsTimedAction = null;
        if (str == null || str.trim().length() == 0) {
            StaticMethods.logWarningFormat("Analytics - Unable to get timed action (timed action name was null or empty)", new Object[0]);
            return null;
        }
        synchronized (this.dbMutex) {
            if (this.database == null) {
                return null;
            }
            try {
                Cursor rawQuery = this.database.rawQuery(this.sqlSelectAction, new String[]{str});
                if (rawQuery.moveToFirst()) {
                    AnalyticsTimedAction analyticsTimedAction2 = new AnalyticsTimedAction((Map<String, Object>) null, rawQuery.getLong(1), rawQuery.getLong(2), rawQuery.getInt(0));
                    try {
                        Cursor rawQuery2 = this.database.rawQuery(this.sqlSelectContextDataForAction, new String[]{String.valueOf(analyticsTimedAction2.databaseID)});
                        if (rawQuery2.moveToFirst()) {
                            analyticsTimedAction2.contextData = new HashMap();
                            do {
                                analyticsTimedAction2.contextData.put(rawQuery2.getString(0), rawQuery2.getString(1));
                            } while (rawQuery2.moveToNext());
                        }
                        rawQuery2.close();
                        analyticsTimedAction = analyticsTimedAction2;
                    } catch (SQLException e) {
                        e = e;
                        analyticsTimedAction = analyticsTimedAction2;
                        StaticMethods.logErrorFormat("Analytics - Unable to read from timed actions database (%s)", e.getMessage());
                        resetDatabase(e);
                        return analyticsTimedAction;
                    } catch (Exception e2) {
                        e = e2;
                        analyticsTimedAction = analyticsTimedAction2;
                        StaticMethods.logErrorFormat("Analytics - Unknown error reading from timed actions database (%s)", e.getMessage());
                        return analyticsTimedAction;
                    }
                }
                rawQuery.close();
            } catch (SQLException e3) {
                e = e3;
            } catch (Exception e4) {
                e = e4;
                StaticMethods.logErrorFormat("Analytics - Unknown error reading from timed actions database (%s)", e.getMessage());
                return analyticsTimedAction;
            }
        }
    }

    private void deleteTimedAction(int i) {
        synchronized (this.dbMutex) {
            try {
                long j = (long) i;
                this.sqlDeleteContextDataForAction.bindLong(1, j);
                this.sqlDeleteContextDataForAction.execute();
                this.sqlDeleteAction.bindLong(1, j);
                this.sqlDeleteAction.execute();
                this.sqlDeleteContextDataForAction.clearBindings();
                this.sqlDeleteAction.clearBindings();
            } catch (SQLException e) {
                StaticMethods.logErrorFormat("Analytics - Unable to delete the timed action (ID = %d, Exception: %s)", Integer.valueOf(i), e.getMessage());
                resetDatabase(e);
            } catch (Exception e2) {
                StaticMethods.logErrorFormat("Analytics - Unknown error deleting timed action (%s)", e2.getMessage());
            }
        }
    }
}
