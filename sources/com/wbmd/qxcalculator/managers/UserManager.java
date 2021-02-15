package com.wbmd.qxcalculator.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.wbmd.qxcalculator.AppConfiguration;
import com.wbmd.qxcalculator.model.api.APIResponse;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.model.db.DBUserDao;
import com.wbmd.qxcalculator.model.db.managers.DatabaseManager;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.CrashLogger;
import com.wbmd.qxcalculator.util.ObscuredSharedPreferences;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.joda.time.DateTime;

public class UserManager {
    private static final String KEY_ACTIVE_ACCOUNT_TYPE = "SharedPreferenceHelper.KEY_ACTIVE_ACCOUNT_TYPE";
    private static final String KEY_ACTIVE_MEDSCAPE_USER_ID = "SharedPreferenceHelper.KEY_ACTIVE_MEDSCAPE_USER_ID";
    private static final String KEY_ACTIVE_USER_AUTH_KEY = "UserSettingType_AUTH_KEY";
    private static final String KEY_ACTIVE_USER_DEVICE_ID = "KEY_ACTIVE_USER_DEVICE_ID";
    private static final String KEY_ACTIVE_USER_EMAIL = "UserSettingType_EMAIL";
    private static final String KEY_ACTIVE_USER_FACEBOOK_ID = "KEY_ACTIVE_USER_FACEBOOK_ID";
    private static final String KEY_ACTIVE_USER_FB_TOKEN = "UserSettingType_FACEBOOK_ACCESS_TOKEN";
    private static final String KEY_ACTIVE_USER_ID = "SharedPreferenceHelper.KEY_ACTIVE_USER_ID";
    private static final String KEY_ACTIVE_USER_PASSWORD = "UserSettingType_PASSWORD";
    private static final String KEY_ACTIVE_USER_SESSION_ID = "KEY_ACTIVE_USER_SESSION_ID";
    private static final String PREFERENCE_KEY = "SharedPreferenceHelper.KEY_STORE_NAME";
    private static final String TRIAL_ACCOUNT_DB_NAME = "trial-db";
    private static final long activeUserIdDeepLink = -1;
    public static final Long defaultUniversalAccountUpdateDeferralLimit = 3L;
    private static UserManager mInstance;
    public AccountType accountType;
    private Long activeUserId;
    private String authKey;
    private Context context;
    private DBUser currentUser;
    private String deviceId;
    private String facebookId;
    public boolean hasShownAccountUpdatePrompt;
    private String sessionId;
    private Boolean shouldShowAccountUpdatePromptCache;

    /* renamed from: com.wbmd.qxcalculator.managers.UserManager$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$managers$UserManager$AccountType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.wbmd.qxcalculator.managers.UserManager$AccountType[] r0 = com.wbmd.qxcalculator.managers.UserManager.AccountType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$managers$UserManager$AccountType = r0
                com.wbmd.qxcalculator.managers.UserManager$AccountType r1 = com.wbmd.qxcalculator.managers.UserManager.AccountType.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$managers$UserManager$AccountType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.managers.UserManager$AccountType r1 = com.wbmd.qxcalculator.managers.UserManager.AccountType.TRIAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$managers$UserManager$AccountType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.managers.UserManager$AccountType r1 = com.wbmd.qxcalculator.managers.UserManager.AccountType.REGULAR     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$managers$UserManager$AccountType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.qxcalculator.managers.UserManager$AccountType r1 = com.wbmd.qxcalculator.managers.UserManager.AccountType.DEEP_LINKED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.managers.UserManager.AnonymousClass1.<clinit>():void");
        }
    }

    public enum AccountType {
        NONE,
        TRIAL,
        REGULAR,
        DEEP_LINKED;

        public String toString() {
            int i = AnonymousClass1.$SwitchMap$com$wbmd$qxcalculator$managers$UserManager$AccountType[ordinal()];
            if (i == 1) {
                return "none";
            }
            if (i == 2) {
                return "trial";
            }
            if (i == 3) {
                return "regular";
            }
            if (i != 4) {
                return null;
            }
            return "deep_linked";
        }

        public static AccountType accountTypeFromString(String str) {
            if (str == null) {
                return NONE;
            }
            if (str.equals("trial")) {
                return TRIAL;
            }
            if (str.equals("regular")) {
                return REGULAR;
            }
            if (str.equals("deep_linked")) {
                return DEEP_LINKED;
            }
            return NONE;
        }
    }

    public static UserManager getInstance() {
        return mInstance;
    }

    public UserManager(Context context2) {
        this.context = context2;
    }

    public static synchronized void initializeInstance(Context context2) {
        synchronized (UserManager.class) {
            if (mInstance == null) {
                mInstance = new UserManager(context2);
            }
        }
    }

    public DBUser getDbUser() {
        if (getActiveUserId() == null) {
            FirebaseCrashlytics.getInstance().recordException(new Exception("getActiveUserId() is null"));
            return null;
        }
        if (DatabaseManager.getInstance().getDaoSession() == null) {
            loadDatabaseForCurrentUser();
        }
        if (this.currentUser == null && DatabaseManager.getInstance().getDaoSession() != null) {
            if (!DatabaseManager.getInstance().hasOpenedSession()) {
                loadDatabaseForCurrentUser();
            }
            DBUserDao dBUserDao = DatabaseManager.getInstance().getDaoSession().getDBUserDao();
            if (!DatabaseManager.getInstance().hasOpenedSession()) {
                FirebaseCrashlytics.getInstance().recordException(new Exception("DatabaseManager.getInstance().hasOpenedSession() is false"));
                return null;
            }
            List loadAll = dBUserDao.loadAll();
            if (this.activeUserId == null || (loadAll != null && !loadAll.isEmpty())) {
                this.currentUser = (DBUser) loadAll.get(0);
            } else {
                DBUser dBUser = new DBUser();
                this.currentUser = dBUser;
                dBUser.setIdentifier(this.activeUserId.toString());
                this.currentUser.setUsageCount(1L);
                this.currentUser.setShowAppReviewAtUsageCount(3L);
                this.currentUser.setShouldShowPromptForAutoEnterFirstQuestion(true);
                this.currentUser.setAutoEnterFirstQuestion(false);
                this.currentUser.setPnEnabled(true);
                this.currentUser.setBannerAdsEnabled(true);
                this.currentUser.setShowItemDescription(true);
                dBUserDao.insert(this.currentUser);
            }
            AnalyticsHandler.getInstance().setUserDetails();
        }
        return this.currentUser;
    }

    public boolean needsUpgradeToUniversalAccounts() {
        Long l = this.activeUserId;
        return l != null && l.equals(0L);
    }

    public boolean needsToSyncWithCalculateServer() {
        DBUser dbUser = getDbUser();
        if (dbUser.getNeedsToSyncWithCalculateServer() == null) {
            return false;
        }
        return dbUser.getNeedsToSyncWithCalculateServer().booleanValue();
    }

    public boolean hasFinishedUpgradingToUniversalAccounts() {
        return !needsUpgradeToUniversalAccounts() && !needsToSyncWithCalculateServer();
    }

    public boolean didLinkAccount() {
        DBUser dbUser = getDbUser();
        if (dbUser.getDidLinkAccount() == null) {
            return false;
        }
        return dbUser.getDidLinkAccount().booleanValue();
    }

    public boolean shouldShowAccountUpdatePrompt() {
        if (!needsUpgradeToUniversalAccounts() || this.hasShownAccountUpdatePrompt) {
            return false;
        }
        if (this.shouldShowAccountUpdatePromptCache == null) {
            DBUser dbUser = getDbUser();
            if (dbUser.getDateOfNextAccountUpgradeRequest() == null) {
                dbUser.setDateOfNextAccountUpgradeRequest(Long.valueOf(new Date().getTime() - 1000));
                dbUser.update();
            }
            this.shouldShowAccountUpdatePromptCache = Boolean.valueOf(new DateTime((Object) dbUser.getDateOfNextAccountUpgradeRequest()).isBeforeNow());
        }
        return this.shouldShowAccountUpdatePromptCache.booleanValue();
    }

    public void incrementNextUniversalAccountUpdatePromptDate(int i) {
        DBUser dbUser = getDbUser();
        dbUser.setDateOfNextAccountUpgradeRequest(Long.valueOf(DateTime.now().withTimeAtStartOfDay().plusDays(i).getMillis()));
        dbUser.update();
    }

    public void incrementAccountUpgradeDeferralCount() {
        DBUser dbUser = getDbUser();
        Long nbAccountUpgradeDeferrals = dbUser.getNbAccountUpgradeDeferrals();
        if (nbAccountUpgradeDeferrals == null) {
            nbAccountUpgradeDeferrals = 0L;
        }
        dbUser.setNbAccountUpgradeDeferrals(Long.valueOf(nbAccountUpgradeDeferrals.longValue() + 1));
        dbUser.update();
    }

    public boolean isFacebookLoginActive() {
        return getActiveUserFacebookAccessToken() != null && !getActiveUserFacebookAccessToken().isEmpty();
    }

    public void initializeAccount(Long l) {
        if (getActiveUserId() == null || !getActiveUserId().equals(l)) {
            if (getActiveUserId() != null) {
                logout();
            }
            setActiveUserAccountType(AccountType.REGULAR);
            setActiveUserId(l);
            loadDatabaseForCurrentUser();
        }
    }

    public boolean accountInitialized() {
        return getActiveUserAccountType() != AccountType.NONE;
    }

    public String getDatabaseNameForUserId(Long l) {
        return AppConfiguration.getInstance().databaseFileName() + l + "-db";
    }

    public void loadDatabaseForCurrentUser() {
        if (!DatabaseManager.getInstance().hasOpenedSession()) {
            getActiveUserAccountType();
            if (this.accountType == AccountType.TRIAL) {
                DatabaseManager.getInstance().loadDatabase(this.context, TRIAL_ACCOUNT_DB_NAME);
            } else if (this.accountType == AccountType.REGULAR || this.accountType == AccountType.DEEP_LINKED) {
                DatabaseManager.getInstance().loadDatabase(this.context, getDatabaseNameForUserId(getActiveUserId()));
            }
        }
    }

    public boolean doesUserDbFileExist(Long l) {
        return this.context.getDatabasePath(getInstance().getDatabaseNameForUserId(l)).exists();
    }

    public void copyDefaultDbToUserFolder(Long l) throws IOException {
        DatabaseManager.getInstance().unloadDatabase();
        File databasePath = this.context.getDatabasePath(getInstance().getDatabaseNameForUserId(l));
        if (databasePath.exists()) {
            Log.d("API", "delete prev db");
            databasePath.delete();
        }
        FileHelper.getInstance().copyRawResourceItemToDisk("calculate_data/user0-db", databasePath);
    }

    public boolean renameDefaultDbForUserId(Long l) {
        logout();
        return this.context.getDatabasePath(getInstance().getDatabaseNameForUserId(0L)).renameTo(this.context.getDatabasePath(getInstance().getDatabaseNameForUserId(l)));
    }

    public void logout() {
        DatabaseManager.getInstance().unloadDatabase();
        setActiveUserId((Long) null);
        setActiveUserAccountType((AccountType) null);
        setPassword((String) null);
        setUserEmail((String) null);
        setAuthKey((String) null);
        setActiveUserFacebookAccessToken((String) null);
    }

    public void setActiveUserAccountType(AccountType accountType2) {
        SharedPreferences.Editor edit = this.context.getSharedPreferences(PREFERENCE_KEY, 0).edit();
        if (accountType2 == null) {
            edit.remove(KEY_ACTIVE_ACCOUNT_TYPE);
        } else {
            edit.putString(KEY_ACTIVE_ACCOUNT_TYPE, accountType2.toString());
        }
        edit.commit();
        this.accountType = accountType2;
    }

    public AccountType getActiveUserAccountType() {
        if (this.accountType == null) {
            if (getActiveUserId() == null) {
                this.accountType = AccountType.NONE;
            } else {
                String string = this.context.getSharedPreferences(PREFERENCE_KEY, 0).getString(KEY_ACTIVE_ACCOUNT_TYPE, (String) null);
                if (string == null) {
                    setActiveUserAccountType(AccountType.REGULAR);
                } else {
                    this.accountType = AccountType.accountTypeFromString(string);
                }
            }
        }
        return this.accountType;
    }

    public void setActiveUserId(Long l) {
        CrashLogger instance = CrashLogger.getInstance();
        instance.leaveBreadcrumb("set active user id " + l);
        this.activeUserId = l;
        this.currentUser = null;
        this.authKey = null;
        SharedPreferences.Editor edit = this.context.getSharedPreferences(PREFERENCE_KEY, 0).edit();
        if (l == null) {
            edit.remove(KEY_ACTIVE_USER_ID);
        } else {
            edit.putString(KEY_ACTIVE_USER_ID, l.toString());
        }
        edit.commit();
    }

    public Long getActiveUserId() {
        long j;
        Long l;
        if (this.activeUserId == null) {
            try {
                j = Long.valueOf(this.context.getSharedPreferences(PREFERENCE_KEY, 0).getString(KEY_ACTIVE_USER_ID, "-2")).longValue();
            } catch (Exception unused) {
                j = -2;
            }
            if (j < -1) {
                l = null;
            } else {
                l = Long.valueOf(j);
            }
            this.activeUserId = l;
        }
        return this.activeUserId;
    }

    public void setUserEmail(String str) {
        DBUser dbUser = getDbUser();
        if (str == null || (dbUser.getHasSuccessfullyInitialized() != null && dbUser.getHasSuccessfullyInitialized().booleanValue())) {
            Context context2 = this.context;
            ObscuredSharedPreferences.Editor edit = new ObscuredSharedPreferences(context2, context2.getSharedPreferences(PREFERENCE_KEY, 0)).edit();
            if (str == null) {
                edit.remove(KEY_ACTIVE_USER_EMAIL);
            } else {
                edit.putString(KEY_ACTIVE_USER_EMAIL, str);
            }
            edit.commit();
            return;
        }
        dbUser.setEmail(str);
        dbUser.update();
    }

    public String getUserEmail() {
        DBUser dbUser = getDbUser();
        if (dbUser == null) {
            return null;
        }
        if (dbUser.getHasSuccessfullyInitialized() == null || !dbUser.getHasSuccessfullyInitialized().booleanValue()) {
            return dbUser.getEmail();
        }
        Context context2 = this.context;
        return new ObscuredSharedPreferences(context2, context2.getSharedPreferences(PREFERENCE_KEY, 0)).getString(KEY_ACTIVE_USER_EMAIL, (String) null);
    }

    public void setPassword(String str) {
        Context context2 = this.context;
        ObscuredSharedPreferences.Editor edit = new ObscuredSharedPreferences(context2, context2.getSharedPreferences(PREFERENCE_KEY, 0)).edit();
        if (str == null) {
            edit.remove(KEY_ACTIVE_USER_PASSWORD);
        } else {
            edit.putString(KEY_ACTIVE_USER_PASSWORD, str);
        }
        edit.commit();
    }

    public String getPassword() {
        Context context2 = this.context;
        return new ObscuredSharedPreferences(context2, context2.getSharedPreferences(PREFERENCE_KEY, 0)).getString(KEY_ACTIVE_USER_PASSWORD, (String) null);
    }

    public void setActiveUserFacebookAccessToken(String str) {
        Context context2 = this.context;
        ObscuredSharedPreferences.Editor edit = new ObscuredSharedPreferences(context2, context2.getSharedPreferences(PREFERENCE_KEY, 0)).edit();
        if (str == null) {
            edit.remove(KEY_ACTIVE_USER_FB_TOKEN);
        } else {
            edit.putString(KEY_ACTIVE_USER_FB_TOKEN, str);
        }
        edit.commit();
    }

    public String getActiveUserFacebookAccessToken() {
        Context context2 = this.context;
        return new ObscuredSharedPreferences(context2, context2.getSharedPreferences(PREFERENCE_KEY, 0)).getString(KEY_ACTIVE_USER_FB_TOKEN, (String) null);
    }

    public void setAuthKey(String str) {
        this.authKey = str;
        SharedPreferences.Editor edit = this.context.getSharedPreferences(PREFERENCE_KEY, 0).edit();
        if (str == null) {
            edit.remove(KEY_ACTIVE_USER_AUTH_KEY);
        } else {
            edit.putString(KEY_ACTIVE_USER_AUTH_KEY, str);
        }
        edit.commit();
        this.authKey = str;
    }

    public String getAuthKey() {
        if (this.authKey == null) {
            this.authKey = this.context.getSharedPreferences(PREFERENCE_KEY, 0).getString(KEY_ACTIVE_USER_AUTH_KEY, (String) null);
        }
        return this.authKey;
    }

    public void setDeviceId(String str) {
        SharedPreferences.Editor edit = this.context.getSharedPreferences(PREFERENCE_KEY, 0).edit();
        if (str == null) {
            edit.remove(KEY_ACTIVE_USER_DEVICE_ID);
        } else {
            edit.putString(KEY_ACTIVE_USER_DEVICE_ID, str);
        }
        edit.commit();
        this.deviceId = str;
    }

    public String getDeviceId() {
        if (this.deviceId == null) {
            this.deviceId = this.context.getSharedPreferences(PREFERENCE_KEY, 0).getString(KEY_ACTIVE_USER_DEVICE_ID, (String) null);
        }
        return this.deviceId;
    }

    public void setFacebookId(String str) {
        SharedPreferences.Editor edit = this.context.getSharedPreferences(PREFERENCE_KEY, 0).edit();
        if (str == null) {
            edit.remove(KEY_ACTIVE_USER_FACEBOOK_ID);
        } else {
            edit.putString(KEY_ACTIVE_USER_FACEBOOK_ID, str);
        }
        edit.commit();
        this.facebookId = str;
    }

    public String getFacebookId() {
        if (this.facebookId == null) {
            this.facebookId = this.context.getSharedPreferences(PREFERENCE_KEY, 0).getString(KEY_ACTIVE_USER_FACEBOOK_ID, (String) null);
        }
        return this.facebookId;
    }

    public void setSessionId(String str) {
        SharedPreferences.Editor edit = this.context.getSharedPreferences(PREFERENCE_KEY, 0).edit();
        if (str == null) {
            edit.remove(KEY_ACTIVE_USER_SESSION_ID);
        } else {
            edit.putString(KEY_ACTIVE_USER_SESSION_ID, str);
        }
        edit.commit();
        this.sessionId = str;
    }

    public String getSessionId() {
        if (this.sessionId == null) {
            this.sessionId = this.context.getSharedPreferences(PREFERENCE_KEY, 0).getString(KEY_ACTIVE_USER_SESSION_ID, (String) null);
        }
        return this.sessionId;
    }

    public static void internalCreateSeededDB() {
        getInstance().activeUserId = 0L;
        DBUser dbUser = getInstance().getDbUser();
        Log.d("Reg", "Mark c");
        APIResponse loadHardCodedApiResponse = FileHelper.getInstance().loadHardCodedApiResponse();
        Log.d("API", "content items hard coded loaded into ram, size " + loadHardCodedApiResponse.contentItems.size());
        Log.d("Reg", "Mark d");
        List<ContentItem> list = loadHardCodedApiResponse.contentItems;
        ArrayList<List> arrayList = new ArrayList<>();
        int size = list.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            int i3 = i2 + 50;
            arrayList.add(new ArrayList(list.subList(i2, Math.min(size, i3))));
            i2 = i3;
        }
        ArrayList arrayList2 = new ArrayList(list.size());
        for (List insertAndRetrieveDbEntities : arrayList) {
            Log.d("Reg", "Mark d." + i);
            arrayList2.addAll(DBContentItem.insertAndRetrieveDbEntities(DatabaseManager.getInstance().getDaoSession(), insertAndRetrieveDbEntities));
            i++;
        }
        Log.d("Reg", "Mark e");
        dbUser.setContentItems(arrayList2);
        dbUser.update();
        DatabaseManager.getInstance().unloadDatabase();
        Log.d("Reg", "Mark f");
    }
}
