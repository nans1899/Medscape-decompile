package com.wbmd.qxcalculator.model.db.v9;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.medscape.android.cache.FeedCache;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import java.util.ArrayList;
import java.util.List;

public class DBUserDao extends AbstractDao<DBUser, Long> {
    public static final String TABLENAME = "DBUSER";
    private DaoSession daoSession;
    private String selectDeep;

    public static class Properties {
        public static final Property AccessPrivileges = new Property(42, Long.class, "accessPrivileges", false, "ACCESS_PRIVILEGES");
        public static final Property AdSettingId = new Property(47, Long.class, "adSettingId", false, "AD_SETTING_ID");
        public static final Property AppVersion = new Property(1, String.class, RemoteConfigConstants.RequestFieldKey.APP_VERSION, false, "APP_VERSION");
        public static final Property AutoEnterFirstQuestion = new Property(27, Boolean.class, "autoEnterFirstQuestion", false, "AUTO_ENTER_FIRST_QUESTION");
        public static final Property BannerAdsEnabled = new Property(31, Boolean.class, "bannerAdsEnabled", false, "BANNER_ADS_ENABLED");
        public static final Property DateOfNextAccountUpgradeRequest = new Property(38, Long.class, "dateOfNextAccountUpgradeRequest", false, "DATE_OF_NEXT_ACCOUNT_UPGRADE_REQUEST");
        public static final Property DebugGroups = new Property(41, String.class, "debugGroups", false, "DEBUG_GROUPS");
        public static final Property DefaultUnits = new Property(15, String.class, "defaultUnits", false, "DEFAULT_UNITS");
        public static final Property DefaultUnitsLength = new Property(17, String.class, "defaultUnitsLength", false, "DEFAULT_UNITS_LENGTH");
        public static final Property DefaultUnitsTemp = new Property(18, String.class, "defaultUnitsTemp", false, "DEFAULT_UNITS_TEMP");
        public static final Property DefaultUnitsWeight = new Property(16, String.class, "defaultUnitsWeight", false, "DEFAULT_UNITS_WEIGHT");
        public static final Property Description = new Property(11, String.class, "description", false, ShareConstants.DESCRIPTION);
        public static final Property DidLinkAccount = new Property(39, Boolean.class, "didLinkAccount", false, "DID_LINK_ACCOUNT");
        public static final Property Email = new Property(3, String.class, "email", false, "EMAIL");
        public static final Property EmailEncoded = new Property(4, String.class, "emailEncoded", false, "EMAIL_ENCODED");
        public static final Property FirstName = new Property(5, String.class, Constants.LOGIN_PREF_FIRSTNAMEKEY, false, "FIRST_NAME");
        public static final Property HasSuccessfullyInitialized = new Property(35, Boolean.class, "hasSuccessfullyInitialized", false, "HAS_SUCCESSFULLY_INITIALIZED");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(6, String.class, "identifier", false, "IDENTIFIER");
        public static final Property InstitutionId = new Property(43, Long.class, "institutionId", false, "INSTITUTION_ID");
        public static final Property IsValidated = new Property(7, Boolean.class, "isValidated", false, "IS_VALIDATED");
        public static final Property IsVerified = new Property(8, Boolean.class, "isVerified", false, "IS_VERIFIED");
        public static final Property LastName = new Property(9, String.class, Constants.LOGIN_PREF_LASTNAMEKEY, false, "LAST_NAME");
        public static final Property LastRefreshTime = new Property(14, Long.class, "lastRefreshTime", false, "LAST_REFRESH_TIME");
        public static final Property LastUsedTabId = new Property(23, String.class, "lastUsedTabId", false, "LAST_USED_TAB_ID");
        public static final Property LocationId = new Property(44, Long.class, "locationId", false, "LOCATION_ID");
        public static final Property NbAccountUpgradeDeferrals = new Property(37, Long.class, "nbAccountUpgradeDeferrals", false, "NB_ACCOUNT_UPGRADE_DEFERRALS");
        public static final Property NeedsToSyncWithCalculateServer = new Property(40, Boolean.class, "needsToSyncWithCalculateServer", false, "NEEDS_TO_SYNC_WITH_CALCULATE_SERVER");
        public static final Property Nickname = new Property(10, String.class, "nickname", false, "NICKNAME");
        public static final Property Npi = new Property(12, String.class, "npi", false, "NPI");
        public static final Property OldEmail = new Property(2, String.class, "oldEmail", false, "OLD_EMAIL");
        public static final Property PnEnabled = new Property(29, Boolean.class, "pnEnabled", false, "PN_ENABLED");
        public static final Property PnTokenSentToCalculateServer = new Property(33, String.class, "pnTokenSentToCalculateServer", false, "PN_TOKEN_SENT_TO_CALCULATE_SERVER");
        public static final Property PnTokenSentToListServer = new Property(34, String.class, "pnTokenSentToListServer", false, "PN_TOKEN_SENT_TO_LIST_SERVER");
        public static final Property ProfessionId = new Property(45, Long.class, "professionId", false, "PROFESSION_ID");
        public static final Property ShouldDispatchUpdatesToServer = new Property(25, Boolean.class, "shouldDispatchUpdatesToServer", false, "SHOULD_DISPATCH_UPDATES_TO_SERVER");
        public static final Property ShouldRegisterUserWithServer = new Property(24, Boolean.class, "shouldRegisterUserWithServer", false, "SHOULD_REGISTER_USER_WITH_SERVER");
        public static final Property ShouldSendRemovePnTokenRequest = new Property(32, Boolean.class, "shouldSendRemovePnTokenRequest", false, "SHOULD_SEND_REMOVE_PN_TOKEN_REQUEST");
        public static final Property ShouldShowPromptForAutoEnterFirstQuestion = new Property(28, Boolean.class, "shouldShowPromptForAutoEnterFirstQuestion", false, "SHOULD_SHOW_PROMPT_FOR_AUTO_ENTER_FIRST_QUESTION");
        public static final Property ShouldVerifyProfile = new Property(36, Boolean.class, "shouldVerifyProfile", false, "SHOULD_VERIFY_PROFILE");
        public static final Property ShowAppReviewAtUsageCount = new Property(22, Long.class, "showAppReviewAtUsageCount", false, "SHOW_APP_REVIEW_AT_USAGE_COUNT");
        public static final Property ShowItemDescription = new Property(30, Boolean.class, "showItemDescription", false, "SHOW_ITEM_DESCRIPTION");
        public static final Property ShowUpdatedPrivacyDialog = new Property(20, Boolean.class, "showUpdatedPrivacyDialog", false, "SHOW_UPDATED_PRIVACY_DIALOG");
        public static final Property ShowUpdatedTermsDialog = new Property(19, Boolean.class, "showUpdatedTermsDialog", false, "SHOW_UPDATED_TERMS_DIALOG");
        public static final Property SpecialtyId = new Property(46, Long.class, FeedCache.FeedCaches.SPECIALITY_ID, false, "SPECIALTY_ID");
        public static final Property UpdateProfilePromptTime = new Property(26, Long.class, "updateProfilePromptTime", false, "UPDATE_PROFILE_PROMPT_TIME");
        public static final Property UsageCount = new Property(21, Long.class, "usageCount", false, "USAGE_COUNT");
        public static final Property ZipCode = new Property(13, String.class, "zipCode", false, "ZIP_CODE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBUserDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBUserDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBUSER' ('_id' INTEGER PRIMARY KEY ,'APP_VERSION' TEXT,'OLD_EMAIL' TEXT,'EMAIL' TEXT,'EMAIL_ENCODED' TEXT,'FIRST_NAME' TEXT,'IDENTIFIER' TEXT,'IS_VALIDATED' INTEGER,'IS_VERIFIED' INTEGER,'LAST_NAME' TEXT,'NICKNAME' TEXT,'DESCRIPTION' TEXT,'NPI' TEXT,'ZIP_CODE' TEXT,'LAST_REFRESH_TIME' INTEGER,'DEFAULT_UNITS' TEXT,'DEFAULT_UNITS_WEIGHT' TEXT,'DEFAULT_UNITS_LENGTH' TEXT,'DEFAULT_UNITS_TEMP' TEXT,'SHOW_UPDATED_TERMS_DIALOG' INTEGER,'SHOW_UPDATED_PRIVACY_DIALOG' INTEGER,'USAGE_COUNT' INTEGER,'SHOW_APP_REVIEW_AT_USAGE_COUNT' INTEGER,'LAST_USED_TAB_ID' TEXT,'SHOULD_REGISTER_USER_WITH_SERVER' INTEGER,'SHOULD_DISPATCH_UPDATES_TO_SERVER' INTEGER,'UPDATE_PROFILE_PROMPT_TIME' INTEGER,'AUTO_ENTER_FIRST_QUESTION' INTEGER,'SHOULD_SHOW_PROMPT_FOR_AUTO_ENTER_FIRST_QUESTION' INTEGER,'PN_ENABLED' INTEGER,'SHOW_ITEM_DESCRIPTION' INTEGER,'BANNER_ADS_ENABLED' INTEGER,'SHOULD_SEND_REMOVE_PN_TOKEN_REQUEST' INTEGER,'PN_TOKEN_SENT_TO_CALCULATE_SERVER' TEXT,'PN_TOKEN_SENT_TO_LIST_SERVER' TEXT,'HAS_SUCCESSFULLY_INITIALIZED' INTEGER,'SHOULD_VERIFY_PROFILE' INTEGER,'NB_ACCOUNT_UPGRADE_DEFERRALS' INTEGER,'DATE_OF_NEXT_ACCOUNT_UPGRADE_REQUEST' INTEGER,'DID_LINK_ACCOUNT' INTEGER,'NEEDS_TO_SYNC_WITH_CALCULATE_SERVER' INTEGER,'DEBUG_GROUPS' TEXT,'ACCESS_PRIVILEGES' INTEGER,'INSTITUTION_ID' INTEGER,'LOCATION_ID' INTEGER,'PROFESSION_ID' INTEGER,'SPECIALTY_ID' INTEGER,'AD_SETTING_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBUSER'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBUser dBUser) {
        sQLiteStatement.clearBindings();
        Long id = dBUser.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String appVersion = dBUser.getAppVersion();
        if (appVersion != null) {
            sQLiteStatement.bindString(2, appVersion);
        }
        String oldEmail = dBUser.getOldEmail();
        if (oldEmail != null) {
            sQLiteStatement.bindString(3, oldEmail);
        }
        String email = dBUser.getEmail();
        if (email != null) {
            sQLiteStatement.bindString(4, email);
        }
        String emailEncoded = dBUser.getEmailEncoded();
        if (emailEncoded != null) {
            sQLiteStatement.bindString(5, emailEncoded);
        }
        String firstName = dBUser.getFirstName();
        if (firstName != null) {
            sQLiteStatement.bindString(6, firstName);
        }
        String identifier = dBUser.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(7, identifier);
        }
        Boolean isValidated = dBUser.getIsValidated();
        long j = 1;
        if (isValidated != null) {
            sQLiteStatement.bindLong(8, isValidated.booleanValue() ? 1 : 0);
        }
        Boolean isVerified = dBUser.getIsVerified();
        if (isVerified != null) {
            sQLiteStatement.bindLong(9, isVerified.booleanValue() ? 1 : 0);
        }
        String lastName = dBUser.getLastName();
        if (lastName != null) {
            sQLiteStatement.bindString(10, lastName);
        }
        String nickname = dBUser.getNickname();
        if (nickname != null) {
            sQLiteStatement.bindString(11, nickname);
        }
        String description = dBUser.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(12, description);
        }
        String npi = dBUser.getNpi();
        if (npi != null) {
            sQLiteStatement.bindString(13, npi);
        }
        String zipCode = dBUser.getZipCode();
        if (zipCode != null) {
            sQLiteStatement.bindString(14, zipCode);
        }
        Long lastRefreshTime = dBUser.getLastRefreshTime();
        if (lastRefreshTime != null) {
            sQLiteStatement.bindLong(15, lastRefreshTime.longValue());
        }
        String defaultUnits = dBUser.getDefaultUnits();
        if (defaultUnits != null) {
            sQLiteStatement.bindString(16, defaultUnits);
        }
        String defaultUnitsWeight = dBUser.getDefaultUnitsWeight();
        if (defaultUnitsWeight != null) {
            sQLiteStatement.bindString(17, defaultUnitsWeight);
        }
        String defaultUnitsLength = dBUser.getDefaultUnitsLength();
        if (defaultUnitsLength != null) {
            sQLiteStatement.bindString(18, defaultUnitsLength);
        }
        String defaultUnitsTemp = dBUser.getDefaultUnitsTemp();
        if (defaultUnitsTemp != null) {
            sQLiteStatement.bindString(19, defaultUnitsTemp);
        }
        Boolean showUpdatedTermsDialog = dBUser.getShowUpdatedTermsDialog();
        if (showUpdatedTermsDialog != null) {
            sQLiteStatement.bindLong(20, showUpdatedTermsDialog.booleanValue() ? 1 : 0);
        }
        Boolean showUpdatedPrivacyDialog = dBUser.getShowUpdatedPrivacyDialog();
        if (showUpdatedPrivacyDialog != null) {
            sQLiteStatement.bindLong(21, showUpdatedPrivacyDialog.booleanValue() ? 1 : 0);
        }
        Long usageCount = dBUser.getUsageCount();
        if (usageCount != null) {
            sQLiteStatement.bindLong(22, usageCount.longValue());
        }
        Long showAppReviewAtUsageCount = dBUser.getShowAppReviewAtUsageCount();
        if (showAppReviewAtUsageCount != null) {
            sQLiteStatement.bindLong(23, showAppReviewAtUsageCount.longValue());
        }
        String lastUsedTabId = dBUser.getLastUsedTabId();
        if (lastUsedTabId != null) {
            sQLiteStatement.bindString(24, lastUsedTabId);
        }
        Boolean shouldRegisterUserWithServer = dBUser.getShouldRegisterUserWithServer();
        if (shouldRegisterUserWithServer != null) {
            sQLiteStatement.bindLong(25, shouldRegisterUserWithServer.booleanValue() ? 1 : 0);
        }
        Boolean shouldDispatchUpdatesToServer = dBUser.getShouldDispatchUpdatesToServer();
        if (shouldDispatchUpdatesToServer != null) {
            sQLiteStatement.bindLong(26, shouldDispatchUpdatesToServer.booleanValue() ? 1 : 0);
        }
        Long updateProfilePromptTime = dBUser.getUpdateProfilePromptTime();
        if (updateProfilePromptTime != null) {
            sQLiteStatement.bindLong(27, updateProfilePromptTime.longValue());
        }
        Boolean autoEnterFirstQuestion = dBUser.getAutoEnterFirstQuestion();
        if (autoEnterFirstQuestion != null) {
            sQLiteStatement.bindLong(28, autoEnterFirstQuestion.booleanValue() ? 1 : 0);
        }
        Boolean shouldShowPromptForAutoEnterFirstQuestion = dBUser.getShouldShowPromptForAutoEnterFirstQuestion();
        if (shouldShowPromptForAutoEnterFirstQuestion != null) {
            sQLiteStatement.bindLong(29, shouldShowPromptForAutoEnterFirstQuestion.booleanValue() ? 1 : 0);
        }
        Boolean pnEnabled = dBUser.getPnEnabled();
        if (pnEnabled != null) {
            sQLiteStatement.bindLong(30, pnEnabled.booleanValue() ? 1 : 0);
        }
        Boolean showItemDescription = dBUser.getShowItemDescription();
        if (showItemDescription != null) {
            sQLiteStatement.bindLong(31, showItemDescription.booleanValue() ? 1 : 0);
        }
        Boolean bannerAdsEnabled = dBUser.getBannerAdsEnabled();
        if (bannerAdsEnabled != null) {
            sQLiteStatement.bindLong(32, bannerAdsEnabled.booleanValue() ? 1 : 0);
        }
        Boolean shouldSendRemovePnTokenRequest = dBUser.getShouldSendRemovePnTokenRequest();
        if (shouldSendRemovePnTokenRequest != null) {
            sQLiteStatement.bindLong(33, shouldSendRemovePnTokenRequest.booleanValue() ? 1 : 0);
        }
        String pnTokenSentToCalculateServer = dBUser.getPnTokenSentToCalculateServer();
        if (pnTokenSentToCalculateServer != null) {
            sQLiteStatement.bindString(34, pnTokenSentToCalculateServer);
        }
        String pnTokenSentToListServer = dBUser.getPnTokenSentToListServer();
        if (pnTokenSentToListServer != null) {
            sQLiteStatement.bindString(35, pnTokenSentToListServer);
        }
        Boolean hasSuccessfullyInitialized = dBUser.getHasSuccessfullyInitialized();
        if (hasSuccessfullyInitialized != null) {
            sQLiteStatement.bindLong(36, hasSuccessfullyInitialized.booleanValue() ? 1 : 0);
        }
        Boolean shouldVerifyProfile = dBUser.getShouldVerifyProfile();
        if (shouldVerifyProfile != null) {
            sQLiteStatement.bindLong(37, shouldVerifyProfile.booleanValue() ? 1 : 0);
        }
        Long nbAccountUpgradeDeferrals = dBUser.getNbAccountUpgradeDeferrals();
        if (nbAccountUpgradeDeferrals != null) {
            sQLiteStatement.bindLong(38, nbAccountUpgradeDeferrals.longValue());
        }
        Long dateOfNextAccountUpgradeRequest = dBUser.getDateOfNextAccountUpgradeRequest();
        if (dateOfNextAccountUpgradeRequest != null) {
            sQLiteStatement.bindLong(39, dateOfNextAccountUpgradeRequest.longValue());
        }
        Boolean didLinkAccount = dBUser.getDidLinkAccount();
        if (didLinkAccount != null) {
            sQLiteStatement.bindLong(40, didLinkAccount.booleanValue() ? 1 : 0);
        }
        Boolean needsToSyncWithCalculateServer = dBUser.getNeedsToSyncWithCalculateServer();
        if (needsToSyncWithCalculateServer != null) {
            if (!needsToSyncWithCalculateServer.booleanValue()) {
                j = 0;
            }
            sQLiteStatement.bindLong(41, j);
        }
        String debugGroups = dBUser.getDebugGroups();
        if (debugGroups != null) {
            sQLiteStatement.bindString(42, debugGroups);
        }
        Long accessPrivileges = dBUser.getAccessPrivileges();
        if (accessPrivileges != null) {
            sQLiteStatement.bindLong(43, accessPrivileges.longValue());
        }
        Long institutionId = dBUser.getInstitutionId();
        if (institutionId != null) {
            sQLiteStatement.bindLong(44, institutionId.longValue());
        }
        Long locationId = dBUser.getLocationId();
        if (locationId != null) {
            sQLiteStatement.bindLong(45, locationId.longValue());
        }
        Long professionId = dBUser.getProfessionId();
        if (professionId != null) {
            sQLiteStatement.bindLong(46, professionId.longValue());
        }
        Long specialtyId = dBUser.getSpecialtyId();
        if (specialtyId != null) {
            sQLiteStatement.bindLong(47, specialtyId.longValue());
        }
        Long adSettingId = dBUser.getAdSettingId();
        if (adSettingId != null) {
            sQLiteStatement.bindLong(48, adSettingId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBUser dBUser) {
        super.attachEntity(dBUser);
        dBUser.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBUser readEntity(Cursor cursor, int i) {
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        Boolean bool4;
        Boolean bool5;
        Boolean bool6;
        Boolean bool7;
        Boolean bool8;
        Boolean bool9;
        Boolean bool10;
        Boolean bool11;
        Boolean bool12;
        Boolean bool13;
        Boolean bool14;
        Boolean bool15;
        Boolean bool16;
        Cursor cursor2 = cursor;
        int i2 = i + 0;
        Long valueOf = cursor2.isNull(i2) ? null : Long.valueOf(cursor2.getLong(i2));
        int i3 = i + 1;
        String string = cursor2.isNull(i3) ? null : cursor2.getString(i3);
        int i4 = i + 2;
        String string2 = cursor2.isNull(i4) ? null : cursor2.getString(i4);
        int i5 = i + 3;
        String string3 = cursor2.isNull(i5) ? null : cursor2.getString(i5);
        int i6 = i + 4;
        String string4 = cursor2.isNull(i6) ? null : cursor2.getString(i6);
        int i7 = i + 5;
        String string5 = cursor2.isNull(i7) ? null : cursor2.getString(i7);
        int i8 = i + 6;
        String string6 = cursor2.isNull(i8) ? null : cursor2.getString(i8);
        int i9 = i + 7;
        boolean z = true;
        if (cursor2.isNull(i9)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor2.getShort(i9) != 0);
        }
        int i10 = i + 8;
        if (cursor2.isNull(i10)) {
            bool2 = null;
        } else {
            bool2 = Boolean.valueOf(cursor2.getShort(i10) != 0);
        }
        int i11 = i + 9;
        String string7 = cursor2.isNull(i11) ? null : cursor2.getString(i11);
        int i12 = i + 10;
        String string8 = cursor2.isNull(i12) ? null : cursor2.getString(i12);
        int i13 = i + 11;
        String string9 = cursor2.isNull(i13) ? null : cursor2.getString(i13);
        int i14 = i + 12;
        String string10 = cursor2.isNull(i14) ? null : cursor2.getString(i14);
        int i15 = i + 13;
        String string11 = cursor2.isNull(i15) ? null : cursor2.getString(i15);
        int i16 = i + 14;
        Long valueOf2 = cursor2.isNull(i16) ? null : Long.valueOf(cursor2.getLong(i16));
        int i17 = i + 15;
        String string12 = cursor2.isNull(i17) ? null : cursor2.getString(i17);
        int i18 = i + 16;
        String string13 = cursor2.isNull(i18) ? null : cursor2.getString(i18);
        int i19 = i + 17;
        String string14 = cursor2.isNull(i19) ? null : cursor2.getString(i19);
        int i20 = i + 18;
        String string15 = cursor2.isNull(i20) ? null : cursor2.getString(i20);
        int i21 = i + 19;
        if (cursor2.isNull(i21)) {
            bool3 = null;
        } else {
            bool3 = Boolean.valueOf(cursor2.getShort(i21) != 0);
        }
        int i22 = i + 20;
        if (cursor2.isNull(i22)) {
            bool4 = null;
        } else {
            bool4 = Boolean.valueOf(cursor2.getShort(i22) != 0);
        }
        int i23 = i + 21;
        Long valueOf3 = cursor2.isNull(i23) ? null : Long.valueOf(cursor2.getLong(i23));
        int i24 = i + 22;
        Long valueOf4 = cursor2.isNull(i24) ? null : Long.valueOf(cursor2.getLong(i24));
        int i25 = i + 23;
        String string16 = cursor2.isNull(i25) ? null : cursor2.getString(i25);
        int i26 = i + 24;
        if (cursor2.isNull(i26)) {
            bool5 = null;
        } else {
            bool5 = Boolean.valueOf(cursor2.getShort(i26) != 0);
        }
        int i27 = i + 25;
        if (cursor2.isNull(i27)) {
            bool6 = null;
        } else {
            bool6 = Boolean.valueOf(cursor2.getShort(i27) != 0);
        }
        int i28 = i + 26;
        Long valueOf5 = cursor2.isNull(i28) ? null : Long.valueOf(cursor2.getLong(i28));
        int i29 = i + 27;
        if (cursor2.isNull(i29)) {
            bool7 = null;
        } else {
            bool7 = Boolean.valueOf(cursor2.getShort(i29) != 0);
        }
        int i30 = i + 28;
        if (cursor2.isNull(i30)) {
            bool8 = null;
        } else {
            bool8 = Boolean.valueOf(cursor2.getShort(i30) != 0);
        }
        int i31 = i + 29;
        if (cursor2.isNull(i31)) {
            bool9 = null;
        } else {
            bool9 = Boolean.valueOf(cursor2.getShort(i31) != 0);
        }
        int i32 = i + 30;
        if (cursor2.isNull(i32)) {
            bool10 = null;
        } else {
            bool10 = Boolean.valueOf(cursor2.getShort(i32) != 0);
        }
        int i33 = i + 31;
        if (cursor2.isNull(i33)) {
            bool11 = null;
        } else {
            bool11 = Boolean.valueOf(cursor2.getShort(i33) != 0);
        }
        int i34 = i + 32;
        if (cursor2.isNull(i34)) {
            bool12 = null;
        } else {
            bool12 = Boolean.valueOf(cursor2.getShort(i34) != 0);
        }
        int i35 = i + 33;
        String string17 = cursor2.isNull(i35) ? null : cursor2.getString(i35);
        int i36 = i + 34;
        String string18 = cursor2.isNull(i36) ? null : cursor2.getString(i36);
        int i37 = i + 35;
        if (cursor2.isNull(i37)) {
            bool13 = null;
        } else {
            bool13 = Boolean.valueOf(cursor2.getShort(i37) != 0);
        }
        int i38 = i + 36;
        if (cursor2.isNull(i38)) {
            bool14 = null;
        } else {
            bool14 = Boolean.valueOf(cursor2.getShort(i38) != 0);
        }
        int i39 = i + 37;
        Long valueOf6 = cursor2.isNull(i39) ? null : Long.valueOf(cursor2.getLong(i39));
        int i40 = i + 38;
        Long valueOf7 = cursor2.isNull(i40) ? null : Long.valueOf(cursor2.getLong(i40));
        int i41 = i + 39;
        if (cursor2.isNull(i41)) {
            bool15 = null;
        } else {
            bool15 = Boolean.valueOf(cursor2.getShort(i41) != 0);
        }
        int i42 = i + 40;
        if (cursor2.isNull(i42)) {
            bool16 = null;
        } else {
            if (cursor2.getShort(i42) == 0) {
                z = false;
            }
            bool16 = Boolean.valueOf(z);
        }
        int i43 = i + 41;
        String string19 = cursor2.isNull(i43) ? null : cursor2.getString(i43);
        int i44 = i + 42;
        Long valueOf8 = cursor2.isNull(i44) ? null : Long.valueOf(cursor2.getLong(i44));
        int i45 = i + 43;
        Long valueOf9 = cursor2.isNull(i45) ? null : Long.valueOf(cursor2.getLong(i45));
        int i46 = i + 44;
        Long valueOf10 = cursor2.isNull(i46) ? null : Long.valueOf(cursor2.getLong(i46));
        int i47 = i + 45;
        Long valueOf11 = cursor2.isNull(i47) ? null : Long.valueOf(cursor2.getLong(i47));
        int i48 = i + 46;
        Long valueOf12 = cursor2.isNull(i48) ? null : Long.valueOf(cursor2.getLong(i48));
        int i49 = i + 47;
        return new DBUser(valueOf, string, string2, string3, string4, string5, string6, bool, bool2, string7, string8, string9, string10, string11, valueOf2, string12, string13, string14, string15, bool3, bool4, valueOf3, valueOf4, string16, bool5, bool6, valueOf5, bool7, bool8, bool9, bool10, bool11, bool12, string17, string18, bool13, bool14, valueOf6, valueOf7, bool15, bool16, string19, valueOf8, valueOf9, valueOf10, valueOf11, valueOf12, cursor2.isNull(i49) ? null : Long.valueOf(cursor2.getLong(i49)));
    }

    public void readEntity(Cursor cursor, DBUser dBUser, int i) {
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        Boolean bool4;
        Boolean bool5;
        Boolean bool6;
        Boolean bool7;
        Boolean bool8;
        Boolean bool9;
        Boolean bool10;
        Boolean bool11;
        Boolean bool12;
        Boolean bool13;
        Boolean bool14;
        Boolean bool15;
        Boolean bool16;
        int i2 = i + 0;
        Long l = null;
        dBUser.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBUser.setAppVersion(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBUser.setOldEmail(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBUser.setEmail(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBUser.setEmailEncoded(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBUser.setFirstName(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBUser.setIdentifier(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        boolean z = true;
        if (cursor.isNull(i9)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i9) != 0);
        }
        dBUser.setIsValidated(bool);
        int i10 = i + 8;
        if (cursor.isNull(i10)) {
            bool2 = null;
        } else {
            bool2 = Boolean.valueOf(cursor.getShort(i10) != 0);
        }
        dBUser.setIsVerified(bool2);
        int i11 = i + 9;
        dBUser.setLastName(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 10;
        dBUser.setNickname(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 11;
        dBUser.setDescription(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 12;
        dBUser.setNpi(cursor.isNull(i14) ? null : cursor.getString(i14));
        int i15 = i + 13;
        dBUser.setZipCode(cursor.isNull(i15) ? null : cursor.getString(i15));
        int i16 = i + 14;
        dBUser.setLastRefreshTime(cursor.isNull(i16) ? null : Long.valueOf(cursor.getLong(i16)));
        int i17 = i + 15;
        dBUser.setDefaultUnits(cursor.isNull(i17) ? null : cursor.getString(i17));
        int i18 = i + 16;
        dBUser.setDefaultUnitsWeight(cursor.isNull(i18) ? null : cursor.getString(i18));
        int i19 = i + 17;
        dBUser.setDefaultUnitsLength(cursor.isNull(i19) ? null : cursor.getString(i19));
        int i20 = i + 18;
        dBUser.setDefaultUnitsTemp(cursor.isNull(i20) ? null : cursor.getString(i20));
        int i21 = i + 19;
        if (cursor.isNull(i21)) {
            bool3 = null;
        } else {
            bool3 = Boolean.valueOf(cursor.getShort(i21) != 0);
        }
        dBUser.setShowUpdatedTermsDialog(bool3);
        int i22 = i + 20;
        if (cursor.isNull(i22)) {
            bool4 = null;
        } else {
            bool4 = Boolean.valueOf(cursor.getShort(i22) != 0);
        }
        dBUser.setShowUpdatedPrivacyDialog(bool4);
        int i23 = i + 21;
        dBUser.setUsageCount(cursor.isNull(i23) ? null : Long.valueOf(cursor.getLong(i23)));
        int i24 = i + 22;
        dBUser.setShowAppReviewAtUsageCount(cursor.isNull(i24) ? null : Long.valueOf(cursor.getLong(i24)));
        int i25 = i + 23;
        dBUser.setLastUsedTabId(cursor.isNull(i25) ? null : cursor.getString(i25));
        int i26 = i + 24;
        if (cursor.isNull(i26)) {
            bool5 = null;
        } else {
            bool5 = Boolean.valueOf(cursor.getShort(i26) != 0);
        }
        dBUser.setShouldRegisterUserWithServer(bool5);
        int i27 = i + 25;
        if (cursor.isNull(i27)) {
            bool6 = null;
        } else {
            bool6 = Boolean.valueOf(cursor.getShort(i27) != 0);
        }
        dBUser.setShouldDispatchUpdatesToServer(bool6);
        int i28 = i + 26;
        dBUser.setUpdateProfilePromptTime(cursor.isNull(i28) ? null : Long.valueOf(cursor.getLong(i28)));
        int i29 = i + 27;
        if (cursor.isNull(i29)) {
            bool7 = null;
        } else {
            bool7 = Boolean.valueOf(cursor.getShort(i29) != 0);
        }
        dBUser.setAutoEnterFirstQuestion(bool7);
        int i30 = i + 28;
        if (cursor.isNull(i30)) {
            bool8 = null;
        } else {
            bool8 = Boolean.valueOf(cursor.getShort(i30) != 0);
        }
        dBUser.setShouldShowPromptForAutoEnterFirstQuestion(bool8);
        int i31 = i + 29;
        if (cursor.isNull(i31)) {
            bool9 = null;
        } else {
            bool9 = Boolean.valueOf(cursor.getShort(i31) != 0);
        }
        dBUser.setPnEnabled(bool9);
        int i32 = i + 30;
        if (cursor.isNull(i32)) {
            bool10 = null;
        } else {
            bool10 = Boolean.valueOf(cursor.getShort(i32) != 0);
        }
        dBUser.setShowItemDescription(bool10);
        int i33 = i + 31;
        if (cursor.isNull(i33)) {
            bool11 = null;
        } else {
            bool11 = Boolean.valueOf(cursor.getShort(i33) != 0);
        }
        dBUser.setBannerAdsEnabled(bool11);
        int i34 = i + 32;
        if (cursor.isNull(i34)) {
            bool12 = null;
        } else {
            bool12 = Boolean.valueOf(cursor.getShort(i34) != 0);
        }
        dBUser.setShouldSendRemovePnTokenRequest(bool12);
        int i35 = i + 33;
        dBUser.setPnTokenSentToCalculateServer(cursor.isNull(i35) ? null : cursor.getString(i35));
        int i36 = i + 34;
        dBUser.setPnTokenSentToListServer(cursor.isNull(i36) ? null : cursor.getString(i36));
        int i37 = i + 35;
        if (cursor.isNull(i37)) {
            bool13 = null;
        } else {
            bool13 = Boolean.valueOf(cursor.getShort(i37) != 0);
        }
        dBUser.setHasSuccessfullyInitialized(bool13);
        int i38 = i + 36;
        if (cursor.isNull(i38)) {
            bool14 = null;
        } else {
            bool14 = Boolean.valueOf(cursor.getShort(i38) != 0);
        }
        dBUser.setShouldVerifyProfile(bool14);
        int i39 = i + 37;
        dBUser.setNbAccountUpgradeDeferrals(cursor.isNull(i39) ? null : Long.valueOf(cursor.getLong(i39)));
        int i40 = i + 38;
        dBUser.setDateOfNextAccountUpgradeRequest(cursor.isNull(i40) ? null : Long.valueOf(cursor.getLong(i40)));
        int i41 = i + 39;
        if (cursor.isNull(i41)) {
            bool15 = null;
        } else {
            bool15 = Boolean.valueOf(cursor.getShort(i41) != 0);
        }
        dBUser.setDidLinkAccount(bool15);
        int i42 = i + 40;
        if (cursor.isNull(i42)) {
            bool16 = null;
        } else {
            if (cursor.getShort(i42) == 0) {
                z = false;
            }
            bool16 = Boolean.valueOf(z);
        }
        dBUser.setNeedsToSyncWithCalculateServer(bool16);
        int i43 = i + 41;
        dBUser.setDebugGroups(cursor.isNull(i43) ? null : cursor.getString(i43));
        int i44 = i + 42;
        dBUser.setAccessPrivileges(cursor.isNull(i44) ? null : Long.valueOf(cursor.getLong(i44)));
        int i45 = i + 43;
        dBUser.setInstitutionId(cursor.isNull(i45) ? null : Long.valueOf(cursor.getLong(i45)));
        int i46 = i + 44;
        dBUser.setLocationId(cursor.isNull(i46) ? null : Long.valueOf(cursor.getLong(i46)));
        int i47 = i + 45;
        dBUser.setProfessionId(cursor.isNull(i47) ? null : Long.valueOf(cursor.getLong(i47)));
        int i48 = i + 46;
        dBUser.setSpecialtyId(cursor.isNull(i48) ? null : Long.valueOf(cursor.getLong(i48)));
        int i49 = i + 47;
        if (!cursor.isNull(i49)) {
            l = Long.valueOf(cursor.getLong(i49));
        }
        dBUser.setAdSettingId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBUser dBUser, long j) {
        dBUser.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBUser dBUser) {
        if (dBUser != null) {
            return dBUser.getId();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String getSelectDeep() {
        if (this.selectDeep == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(sb, "T", getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T0", this.daoSession.getDBInstitutionDao().getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T1", this.daoSession.getDBLocationDao().getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T2", this.daoSession.getDBProfessionDao().getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T3", this.daoSession.getDBSpecialtyDao().getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T4", this.daoSession.getDBAdSettingDao().getAllColumns());
            sb.append(" FROM DBUSER T");
            sb.append(" LEFT JOIN DBINSTITUTION T0 ON T.'INSTITUTION_ID'=T0.'_id'");
            sb.append(" LEFT JOIN DBLOCATION T1 ON T.'LOCATION_ID'=T1.'_id'");
            sb.append(" LEFT JOIN DBPROFESSION T2 ON T.'PROFESSION_ID'=T2.'_id'");
            sb.append(" LEFT JOIN DBSPECIALTY T3 ON T.'SPECIALTY_ID'=T3.'_id'");
            sb.append(" LEFT JOIN DBAD_SETTING T4 ON T.'AD_SETTING_ID'=T4.'_id'");
            sb.append(' ');
            this.selectDeep = sb.toString();
        }
        return this.selectDeep;
    }

    /* access modifiers changed from: protected */
    public DBUser loadCurrentDeep(Cursor cursor, boolean z) {
        DBUser dBUser = (DBUser) loadCurrent(cursor, 0, z);
        int length = getAllColumns().length;
        dBUser.setInstitution((DBInstitution) loadCurrentOther(this.daoSession.getDBInstitutionDao(), cursor, length));
        int length2 = length + this.daoSession.getDBInstitutionDao().getAllColumns().length;
        dBUser.setLocation((DBLocation) loadCurrentOther(this.daoSession.getDBLocationDao(), cursor, length2));
        int length3 = length2 + this.daoSession.getDBLocationDao().getAllColumns().length;
        dBUser.setProfession((DBProfession) loadCurrentOther(this.daoSession.getDBProfessionDao(), cursor, length3));
        int length4 = length3 + this.daoSession.getDBProfessionDao().getAllColumns().length;
        dBUser.setSpecialty((DBSpecialty) loadCurrentOther(this.daoSession.getDBSpecialtyDao(), cursor, length4));
        dBUser.setAdSetting((DBAdSetting) loadCurrentOther(this.daoSession.getDBAdSettingDao(), cursor, length4 + this.daoSession.getDBSpecialtyDao().getAllColumns().length));
        return dBUser;
    }

    public DBUser loadDeep(Long l) {
        assertSinglePk();
        if (l == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(getSelectDeep());
        sb.append("WHERE ");
        SqlUtils.appendColumnsEqValue(sb, "T", getPkColumns());
        Cursor rawQuery = this.db.rawQuery(sb.toString(), new String[]{l.toString()});
        try {
            if (!rawQuery.moveToFirst()) {
                return null;
            }
            if (rawQuery.isLast()) {
                DBUser loadCurrentDeep = loadCurrentDeep(rawQuery, true);
                rawQuery.close();
                return loadCurrentDeep;
            }
            throw new IllegalStateException("Expected unique result, but count was " + rawQuery.getCount());
        } finally {
            rawQuery.close();
        }
    }

    public List<DBUser> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList(count);
        if (cursor.moveToFirst()) {
            if (this.identityScope != null) {
                this.identityScope.lock();
                this.identityScope.reserveRoom(count);
            }
            do {
                try {
                    arrayList.add(loadCurrentDeep(cursor, false));
                } finally {
                    if (this.identityScope != null) {
                        this.identityScope.unlock();
                    }
                }
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List<DBUser> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public List<DBUser> queryDeep(String str, String... strArr) {
        SQLiteDatabase sQLiteDatabase = this.db;
        return loadDeepAllAndCloseCursor(sQLiteDatabase.rawQuery(getSelectDeep() + str, strArr));
    }
}
