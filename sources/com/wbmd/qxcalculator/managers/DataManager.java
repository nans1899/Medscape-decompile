package com.wbmd.qxcalculator.managers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import androidx.appcompat.app.AlertDialog;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.qxmd.eventssdkandroid.managers.QXEEventsManager;
import com.wbmd.qxcalculator.AppConfiguration;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.QxError;
import com.wbmd.qxcalculator.model.api.APIRequest;
import com.wbmd.qxcalculator.model.api.APIResponse;
import com.wbmd.qxcalculator.model.api.APITask;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.common.ResourceFile;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBLocation;
import com.wbmd.qxcalculator.model.db.DBProfession;
import com.wbmd.qxcalculator.model.db.DBPushNotification;
import com.wbmd.qxcalculator.model.db.DBQuestion;
import com.wbmd.qxcalculator.model.db.DBSpecialty;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.model.db.DaoSession;
import com.wbmd.qxcalculator.model.db.managers.DatabaseManager;
import com.wbmd.qxcalculator.model.parsedObjects.User;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.CrashLogger;
import com.wbmd.qxcalculator.util.SharedPreferenceHelper;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataManager {
    public static final String KEY_DID_LOGOUT = "DataManager.KEY_DID_LOGOUT";
    public static final String KEY_SHOW_DESCRIPTIONS_CHANGED = "DataManager.KEY_SHOW_DESCRIPTIONS_CHANGED";
    public static final String K_PRIVACY_POLICY_IDENTIFIER = "file_source_4";
    public static final String K_TERMS_OF_USE_IDENTIFIER = "file_source_5";
    public static final String TAG = DataManager.class.getSimpleName();
    public static final String TASK_ID_LOGOUT = "DataManager.LOGOUT";
    public static final String TASK_ID_REGISTER_PN_WITH_CALCULATE_SERVER = "DataManager.TASK_ID_REGISTER_PN_WITH_CALCULATE_SERVER";
    public static final String TASK_ID_REGISTER_PN_WITH_LIST_SERVER = "DataManager.TASK_ID_REGISTER_PN_WITH_LIST_SERVER";
    public static final String TASK_ID_REMOVE_PN_WITH_CALCULATE_SERVER = "DataManager.TASK_ID_REMOVE_PN_WITH_CALCULATE_SERVER";
    public static final String TASK_ID_REMOVE_PN_WITH_LIST_SERVER = "DataManager.TASK_ID_REMOVE_PN_WITH_LIST_SERVER";
    protected static Context context;
    private static DataManager mInstance;
    public boolean appHasStarted;
    protected DaoSession daoSession;
    protected HashMap<String, Set<DataManagerListener>> dataManagerListenerHashMap = new HashMap<>();
    public boolean isRefreshServiceRunning;

    public interface DataManagerListener {
        boolean dataManagerMethodFinished(String str, boolean z, List<QxError> list, Bundle bundle);

        void dataManagerMethodQueued(String str);

        void dataManagerMethodStarted(String str);
    }

    protected DataManager() {
    }

    public static DataManager getInstance() {
        if (mInstance == null) {
            mInstance = new DataManager();
        }
        return mInstance;
    }

    public void setContext(Context context2) {
        context = context2;
    }

    public DaoSession getDaoSession() {
        if (this.daoSession == null) {
            CrashLogger.getInstance().leaveBreadcrumb("DataManager: getDaoSession NULL so load user");
            if (DatabaseManager.getInstance().getDaoSession() == null) {
                UserManager.getInstance().getDbUser();
            }
            this.daoSession = DatabaseManager.getInstance().getDaoSession();
        }
        return this.daoSession;
    }

    public void registerDataManagerListener(DataManagerListener dataManagerListener, String str) {
        Set set = this.dataManagerListenerHashMap.get(str);
        if (set == null) {
            set = new HashSet();
            this.dataManagerListenerHashMap.put(str, set);
        }
        set.add(dataManagerListener);
    }

    public void deRegisterDataManagerListener(DataManagerListener dataManagerListener, String str) {
        Set set = this.dataManagerListenerHashMap.get(str);
        if (set != null) {
            set.remove(dataManagerListener);
        }
    }

    public Set<DataManagerListener> getListenersForTaskId(String str) {
        return this.dataManagerListenerHashMap.get(str);
    }

    public Exception initializeUserAccount(User user) {
        return initializeUserAccount(user, false);
    }

    public Exception initializeUserAccount(User user, boolean z) {
        String str;
        boolean needsUpgradeToUniversalAccounts = UserManager.getInstance().needsUpgradeToUniversalAccounts();
        if (needsUpgradeToUniversalAccounts) {
            if (ContentDataManager.getInstance().fetchItemsLatch != null && !z) {
                try {
                    ContentDataManager.getInstance().fetchItemsLatch.await();
                } catch (Exception unused) {
                }
            }
            str = FileHelper.getInstance().getResourceFolderPath();
            DBUser dbUser = UserManager.getInstance().getDbUser();
            dbUser.setHasSuccessfullyInitialized(true);
            dbUser.update();
            UserManager.getInstance().logout();
            this.daoSession = null;
            UserManager.getInstance().renameDefaultDbForUserId(user.identifier);
        } else {
            str = null;
        }
        if (!UserManager.getInstance().doesUserDbFileExist(user.identifier)) {
            try {
                UserManager.getInstance().copyDefaultDbToUserFolder(user.identifier);
            } catch (IOException e) {
                return e;
            }
        }
        UserManager.getInstance().initializeAccount(user.identifier);
        UserManager.getInstance().setPassword(user.password);
        UserManager.getInstance().setActiveUserFacebookAccessToken(user.facebookAccessToken);
        UserManager.getInstance().setAuthKey(user.authToken);
        if (user.device != null) {
            UserManager.getInstance().setDeviceId(user.device.identifier);
        }
        UserManager.getInstance().setSessionId(user.sessionId);
        UserManager.getInstance().setFacebookId(user.facebookId);
        DBUser dbUser2 = UserManager.getInstance().getDbUser();
        if (needsUpgradeToUniversalAccounts) {
            FileHelper.getInstance().renameOldResourceFolderPath(str);
        } else {
            FileHelper.getInstance().reinitUserDataFolders();
            try {
                FileHelper.getInstance().copyHardCodedResourcesFromAssetsToUserFolder();
            } catch (IOException e2) {
                UserManager.getInstance().logout();
                return e2;
            }
        }
        dbUser2.setIdentifier(user.identifier.toString());
        dbUser2.setProfession(user.profession, false);
        dbUser2.setSpecialty(user.specialty, false);
        dbUser2.setLocation(user.location, false);
        dbUser2.setFirstName(user.nameFirst);
        dbUser2.setLastName(user.nameLast);
        dbUser2.setDescription(user.userDescription);
        dbUser2.setZipCode(user.zip);
        dbUser2.setNpi(user.npi);
        dbUser2.updateNextProfileUpdateTime(false);
        dbUser2.setHasSuccessfullyInitialized(true);
        dbUser2.setNeedsToSyncWithCalculateServer(true);
        dbUser2.setConsents(user.currentConsents);
        dbUser2.setRequiredConsents(user.consentsRequired);
        dbUser2.setConsentsToRequest(user.consentsToRequest);
        if (user.unitType != null) {
            dbUser2.setDefaultUnits(Unit.UnitType.convertTypeToString(user.unitType));
        }
        if (dbUser2.getDefaultUnits() == null) {
            dbUser2.setDefaultUnits(Unit.UnitType.convertTypeToString(Unit.UnitType.US_UNITS));
        }
        if (needsUpgradeToUniversalAccounts) {
            dbUser2.setDidLinkAccount(true);
        }
        Log.d("Reg", "Mark f");
        dbUser2.update();
        UserManager.getInstance().setUserEmail(user.email);
        return null;
    }

    public void updateUserSettingsLocally(User user) {
        DBUser dbUser = UserManager.getInstance().getDbUser();
        dbUser.setDefaultUnits(user.defaultUnitsString);
        dbUser.setAutoEnterFirstQuestion(user.autoOpenFirstQuestion);
        dbUser.setShowItemDescription(user.showItemDescriptions);
        dbUser.setPnEnabled(user.pnEnabled);
        dbUser.update();
        registerPnTokenIfNeeded();
        removePnDeviceTokenIfNeeded();
    }

    public void updateUserSettings(final User user, String str) {
        TaskManager.getInstance().addTask(new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
            public APIRequest prepareRequest() {
                APIRequest initSyncWithCalculateAccount = APIRequest.initSyncWithCalculateAccount();
                initSyncWithCalculateAccount.setAutoOpenFirstQuestion(user.autoOpenFirstQuestion.booleanValue());
                initSyncWithCalculateAccount.setShowItemDescriptions(user.showItemDescriptions.booleanValue());
                initSyncWithCalculateAccount.setDefaultUnits(user.defaultUnitsString);
                initSyncWithCalculateAccount.setPnEnabled(user.pnEnabled.booleanValue());
                return initSyncWithCalculateAccount;
            }
        }, (APITask.OnProcessResponseBlock) new APITask.OnProcessResponseBlock() {
            public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, APITask aPITask) {
                return aPIResponse;
            }
        }, (APITask.OnCompletionBlock) new APITask.OnCompletionBlock() {
            public Bundle onCompletion(APIResponse aPIResponse, boolean z, List<QxError> list) {
                if (!z) {
                    return null;
                }
                DBUser dbUser = UserManager.getInstance().getDbUser();
                boolean z2 = false;
                boolean booleanValue = dbUser.getShowItemDescription() == null ? false : dbUser.getShowItemDescription().booleanValue();
                boolean booleanValue2 = user.showItemDescriptions == null ? false : user.showItemDescriptions.booleanValue();
                DataManager.this.updateUserSettingsLocally(user);
                Bundle bundle = new Bundle();
                if (booleanValue != booleanValue2) {
                    z2 = true;
                }
                bundle.putBoolean(DataManager.KEY_SHOW_DESCRIPTIONS_CHANGED, z2);
                return bundle;
            }
        }, str));
    }

    public void updateUserLocally(User user) {
        DBUser dbUser = UserManager.getInstance().getDbUser();
        dbUser.setIdentifier(user.identifier.toString());
        dbUser.setProfession(user.profession, false);
        dbUser.setSpecialty(user.specialty, false);
        dbUser.setLocation(user.location, false);
        dbUser.setFirstName(user.nameFirst);
        dbUser.setLastName(user.nameLast);
        dbUser.setDescription(user.userDescription);
        dbUser.setDescription(user.userDescription);
        dbUser.setZipCode(user.zip);
        dbUser.setNpi(user.npi);
        dbUser.setShouldDispatchUpdatesToServer(true);
        dbUser.setConsents(user.currentConsents);
        dbUser.setRequiredConsents(user.consentsRequired);
        dbUser.setConsentsToRequest(user.consentsToRequest);
        if (user.defaultUnitsString != null) {
            dbUser.setDefaultUnits(user.defaultUnitsString);
        }
        String userEmail = UserManager.getInstance().getUserEmail();
        if (!user.email.equalsIgnoreCase(userEmail)) {
            dbUser.setOldEmail(userEmail);
        }
        dbUser.update();
        UserManager.getInstance().setUserEmail(user.email);
        UserManager.getInstance().setPassword(user.password);
        UserManager.getInstance().setActiveUserFacebookAccessToken(user.facebookAccessToken);
        UserManager.getInstance().setFacebookId(user.facebookId);
        if (user.device != null) {
            UserManager.getInstance().setDeviceId(user.device.identifier);
        }
        if (user.authToken != null && !user.authToken.isEmpty()) {
            UserManager.getInstance().setAuthKey(user.authToken);
        }
    }

    public void update(final User user, String str) {
        TaskManager.getInstance().addTask(new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
            public APIRequest prepareRequest() {
                return APIRequest.updateUserWithAccounts(user);
            }
        }, (APITask.OnProcessResponseBlock) new APITask.OnProcessResponseBlock() {
            public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, APITask aPITask) {
                return aPIResponse;
            }
        }, (APITask.OnCompletionBlock) new APITask.OnCompletionBlock() {
            public Bundle onCompletion(APIResponse aPIResponse, boolean z, List<QxError> list) {
                if (!z) {
                    return null;
                }
                if (user.password == null && user.facebookAccessToken == null) {
                    aPIResponse.user.password = UserManager.getInstance().getPassword();
                    aPIResponse.user.facebookAccessToken = UserManager.getInstance().getActiveUserFacebookAccessToken();
                    aPIResponse.user.facebookId = UserManager.getInstance().getFacebookId();
                } else {
                    aPIResponse.user.password = user.password;
                    aPIResponse.user.facebookAccessToken = user.facebookAccessToken;
                    aPIResponse.user.facebookId = user.facebookId;
                }
                DataManager.this.updateUserLocally(aPIResponse.user);
                DataManager.this.registerPnTokenIfNeeded();
                DataManager.this.removePnDeviceTokenIfNeeded();
                if (user.profession != null) {
                    QXEEventsManager.getInstance().setProfessionId(user.profession.identifier);
                }
                if (user.specialty != null) {
                    QXEEventsManager.getInstance().setSpecialtyId(user.specialty.identifier);
                }
                if (user.location != null) {
                    QXEEventsManager.getInstance().setLocationId(user.location.identifier);
                }
                QXEEventsManager.getInstance().setUserEmailAddress(UserManager.getInstance().getUserEmail());
                return null;
            }
        }, str));
    }

    public void updateRegistrationDataWithListServer() {
        Log.d(TAG, "updateRegistrationDataWithListServer");
        new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
            public APIRequest prepareRequest() {
                DBUser dbUser = UserManager.getInstance().getDbUser();
                String oldEmail = dbUser.getOldEmail();
                String str = DataManager.TAG;
                Log.d(str, "updateRegistrationDataWithListServer - oldEmail " + oldEmail);
                if (oldEmail == null || oldEmail.isEmpty()) {
                    oldEmail = UserManager.getInstance().getUserEmail();
                }
                String userEmail = UserManager.getInstance().getUserEmail();
                APIRequest updateUser = APIRequest.updateUser(oldEmail);
                updateUser.setRegistrationOldEmail(oldEmail);
                if (!userEmail.equals(oldEmail)) {
                    updateUser.setRegistrationUpdatedEmail(userEmail);
                }
                String str2 = DataManager.TAG;
                Log.d(str2, "updateRegistrationDataWithListServer - currentEmail " + userEmail);
                if (dbUser.getFirstName() != null) {
                    updateUser.setRegistrationFirstName(dbUser.getFirstName());
                }
                if (dbUser.getLastName() != null) {
                    updateUser.setRegistrationLastName(dbUser.getLastName());
                }
                if (dbUser.getLocation() != null) {
                    updateUser.setRegistrationLocation(dbUser.getLocation().getName());
                }
                if (dbUser.getZipCode() != null) {
                    updateUser.setRegistrationZip(dbUser.getZipCode());
                }
                if (dbUser.getProfession() != null) {
                    updateUser.setRegistrationProfession(dbUser.getProfession().getName());
                }
                if (dbUser.getSpecialty() != null) {
                    updateUser.setRegistrationSpecialty(dbUser.getSpecialty().getName());
                }
                updateUser.setRegistrationAppId("13");
                updateUser.setRegistrationAppVersion(AppConfiguration.getInstance().getAppBuildVersion());
                updateUser.setRegistrationOsVersion(Build.VERSION.RELEASE);
                updateUser.setRegistrationPlatform(Build.MODEL);
                updateUser.setHasUpdatedToUniversalAccounts(!UserManager.getInstance().needsUpgradeToUniversalAccounts());
                return updateUser;
            }
        }, (APITask.OnProcessResponseBlock) new APITask.OnProcessResponseBlock() {
            public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, APITask aPITask) {
                return aPIResponse;
            }
        }, (APITask.OnCompletionBlock) new APITask.OnCompletionBlock() {
            public Bundle onCompletion(APIResponse aPIResponse, boolean z, List<QxError> list) {
                if (z) {
                    DBUser dbUser = UserManager.getInstance().getDbUser();
                    dbUser.setShouldDispatchUpdatesToServer(false);
                    dbUser.setOldEmail((String) null);
                    dbUser.update();
                    if (dbUser.getPnEnabled() == null || !dbUser.getPnEnabled().booleanValue()) {
                        DataManager.this.removePnDeviceTokenIfNeeded();
                    } else {
                        DataManager.this.registerPnTokenIfNeeded();
                    }
                }
                return null;
            }
        }, "").startTask();
    }

    public void fetchIdentifierForLegacyUser(String str, String str2) {
        TaskManager.getInstance().addTask(new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
            public APIRequest prepareRequest() {
                return APIRequest.initFetchUserId(UserManager.getInstance().getUserEmail());
            }
        }, (APITask.OnProcessResponseBlock) new APITask.OnProcessResponseBlock() {
            public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, APITask aPITask) {
                return aPIResponse;
            }
        }, (APITask.OnCompletionBlock) new APITask.OnCompletionBlock() {
            public Bundle onCompletion(APIResponse aPIResponse, boolean z, List<QxError> list) {
                if (!z) {
                    return null;
                }
                DBUser dbUser = UserManager.getInstance().getDbUser();
                dbUser.setIdentifierForLegacyUser(aPIResponse.user.identifier);
                dbUser.update();
                return null;
            }
        }, str2));
    }

    public void reportPnReceived(String str) {
        DBPushNotification pushNotification;
        if (str != null && !str.isEmpty() && (pushNotification = DBPushNotification.getPushNotification(getDaoSession(), str)) != null) {
            pushNotification.updateReceivedDate(getDaoSession(), new Date());
        }
    }

    public void reportPnRead(String str) {
        if (str != null && !str.isEmpty()) {
            DBPushNotification pushNotification = DBPushNotification.getPushNotification(getDaoSession(), str);
            Date date = new Date();
            if (pushNotification.getReceivedDate() == null) {
                pushNotification.updateReceivedDate(getDaoSession(), date);
            }
            pushNotification.updateReadDate(getDaoSession(), date);
            getDaoSession().getDBPushNotificationDao().delete(pushNotification);
        }
    }

    public Drawable getSplashPageSponsorImage(Context context2) {
        String splashPageSponsorImagePath = SharedPreferenceHelper.getInstance().getSplashPageSponsorImagePath();
        if (splashPageSponsorImagePath == null || splashPageSponsorImagePath.isEmpty()) {
            return null;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(splashPageSponsorImagePath);
        if (decodeFile == null) {
            CrashLogger.getInstance().leaveBreadcrumb("a no bitmap created splashpage");
            File file = new File(splashPageSponsorImagePath);
            file.exists();
            CrashLogger.getInstance().leaveBreadcrumb("does file exist? " + file.exists());
            return null;
        }
        Double valueOf = Double.valueOf((double) SharedPreferenceHelper.getInstance().getSplashPageSponsorImageScale().floatValue());
        Double valueOf2 = Double.valueOf((double) SharedPreferenceHelper.getInstance().getSplashPageSponsorImageHeightDip().floatValue());
        Double valueOf3 = Double.valueOf((double) SharedPreferenceHelper.getInstance().getSplashPageSponsorImageWidthDip().floatValue());
        SharedPreferenceHelper.getInstance().getSplashScreenTrackerId();
        SharedPreferenceHelper.getInstance().getSplashScreenSponsorId();
        Long splashScreenCampaignAdId = SharedPreferenceHelper.getInstance().getSplashScreenCampaignAdId();
        String str = "Splashpage View";
        String splashScreenSponsorId = SharedPreferenceHelper.getInstance().getSplashScreenSponsorId();
        if (splashScreenSponsorId != null && !splashScreenSponsorId.isEmpty()) {
            str = splashScreenSponsorId + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str;
        }
        AnalyticsHandler.getInstance().trackPageView((Activity) null, str);
        if (splashScreenCampaignAdId != null) {
            EventsManager.getInstance().trackSplashpageView(splashScreenSponsorId, splashScreenCampaignAdId);
        }
        if (valueOf3 == null || valueOf2 == null) {
            return new BitmapDrawable(context2.getResources(), Bitmap.createScaledBitmap(decodeFile, (int) TypedValue.applyDimension(1, (float) (((double) decodeFile.getWidth()) / valueOf.doubleValue()), context2.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, (float) (((double) decodeFile.getHeight()) / valueOf.doubleValue()), context2.getResources().getDisplayMetrics()), true));
        }
        return new BitmapDrawable(context2.getResources(), Bitmap.createScaledBitmap(decodeFile, (int) TypedValue.applyDimension(1, valueOf3.floatValue(), context2.getResources().getDisplayMetrics()), (int) TypedValue.applyDimension(1, valueOf2.floatValue(), context2.getResources().getDisplayMetrics()), true));
    }

    public void setSplashPageSponsorImage(DBContentItem dBContentItem) {
        float f;
        float f2;
        Long l = null;
        float f3 = 0.0f;
        Float valueOf = Float.valueOf(0.0f);
        if (dBContentItem == null) {
            SharedPreferenceHelper.getInstance().setSplashPageSponsorImageHeightDip(valueOf);
            SharedPreferenceHelper.getInstance().setSplashPageSponsorImageWidthDip(valueOf);
            SharedPreferenceHelper.getInstance().setSplashPageSponsorImageScale(valueOf);
            SharedPreferenceHelper.getInstance().setSplashPageSponsorImagePath((String) null);
            SharedPreferenceHelper.getInstance().setSplashScreenTrackerId((String) null);
            SharedPreferenceHelper.getInstance().setSplashScreenSponsorId((String) null);
            return;
        }
        ContentItem contentItem = new ContentItem(dBContentItem);
        ResourceFile resourceFileForScaledDrawable = FileHelper.getInstance().getResourceFileForScaledDrawable(contentItem.identifier, contentItem.resourceFiles, contentItem.splashPage.source, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        if (resourceFileForScaledDrawable != null) {
            SharedPreferenceHelper instance = SharedPreferenceHelper.getInstance();
            if (resourceFileForScaledDrawable.dipHeight == null) {
                f = 0.0f;
            } else {
                f = resourceFileForScaledDrawable.dipHeight.floatValue();
            }
            instance.setSplashPageSponsorImageHeightDip(Float.valueOf(f));
            SharedPreferenceHelper instance2 = SharedPreferenceHelper.getInstance();
            if (resourceFileForScaledDrawable.dipWidth != null) {
                f3 = resourceFileForScaledDrawable.dipWidth.floatValue();
            }
            instance2.setSplashPageSponsorImageWidthDip(Float.valueOf(f3));
            SharedPreferenceHelper instance3 = SharedPreferenceHelper.getInstance();
            if (resourceFileForScaledDrawable.scaleFactor == null) {
                f2 = 1.0f;
            } else {
                f2 = resourceFileForScaledDrawable.scaleFactor.floatValue();
            }
            instance3.setSplashPageSponsorImageScale(Float.valueOf(f2));
            SharedPreferenceHelper instance4 = SharedPreferenceHelper.getInstance();
            instance4.setSplashPageSponsorImagePath(FileHelper.getInstance().getResourceFolderPathForContentItemIdentifier(contentItem.identifier) + resourceFileForScaledDrawable.name);
            SharedPreferenceHelper.getInstance().setSplashScreenTrackerId(contentItem.trackerId);
            SharedPreferenceHelper.getInstance().setSplashScreenSponsorId(contentItem.identifier);
            SharedPreferenceHelper instance5 = SharedPreferenceHelper.getInstance();
            if (contentItem.promotionToUse != null) {
                l = contentItem.promotionToUse.campaignAdId;
            }
            instance5.setSplashScreenCampaignAdId(l);
            return;
        }
        SharedPreferenceHelper.getInstance().setSplashPageSponsorImageHeightDip(valueOf);
        SharedPreferenceHelper.getInstance().setSplashPageSponsorImageWidthDip(valueOf);
        SharedPreferenceHelper.getInstance().setSplashPageSponsorImageScale(valueOf);
        SharedPreferenceHelper.getInstance().setSplashPageSponsorImagePath((String) null);
        SharedPreferenceHelper.getInstance().setSplashScreenTrackerId((String) null);
        SharedPreferenceHelper.getInstance().setSplashScreenSponsorId((String) null);
    }

    public void incrementUsageCount() {
        DBUser dbUser = UserManager.getInstance().getDbUser();
        if (dbUser != null) {
            Long usageCount = dbUser.getUsageCount();
            if (usageCount == null) {
                usageCount = 0L;
            }
            Long valueOf = Long.valueOf(usageCount.longValue() + 1);
            dbUser.setUsageCount(valueOf);
            if (valueOf.longValue() % 5 == 0) {
                dbUser.setShouldDispatchUpdatesToServer(true);
            }
            dbUser.update();
        }
    }

    public void setLastUsedUnits(Question question, String str) {
        DBQuestion.setLastUsedUnits(getDaoSession(), question, str);
    }

    public void setAutoEnterFirstQuestion(boolean z) {
        DBUser dbUser = UserManager.getInstance().getDbUser();
        dbUser.setAutoEnterFirstQuestion(Boolean.valueOf(z));
        dbUser.update();
    }

    public void setShouldShowAutoEnterFirstQuestionPrompt(boolean z) {
        DBUser dbUser = UserManager.getInstance().getDbUser();
        dbUser.setShouldShowPromptForAutoEnterFirstQuestion(Boolean.valueOf(z));
        dbUser.update();
    }

    public static boolean checkIfValidEmail(String str) {
        return Patterns.EMAIL_ADDRESS.matcher(str).matches();
    }

    public static boolean checkIfValidPassword(String str) {
        return str != null && str.length() > 5;
    }

    public static void showInvalidEmailDialog(Context context2) {
        new AlertDialog.Builder(context2).setTitle(R.string.dialog_bad_email_title).setMessage(R.string.dialog_bad_email_message).setNegativeButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
    }

    public static void showInvalidPasswordDialog(Context context2) {
        new AlertDialog.Builder(context2).setTitle(R.string.dialog_bad_pw_title).setMessage(R.string.dialog_bad_pw_message).setNegativeButton(R.string.dismiss, (DialogInterface.OnClickListener) null).create().show();
    }

    public boolean isTaskCurrentlyRunning(String str) {
        return TaskManager.getInstance().isTaskCurrentlyInProgress(str);
    }

    public void startSessionWithQxEvents() {
        Long l;
        Long l2;
        if (UserManager.getInstance().getActiveUserAccountType() == UserManager.AccountType.REGULAR) {
            DBUser dbUser = UserManager.getInstance().getDbUser();
            DBProfession profession = dbUser.getProfession();
            DBSpecialty specialty = dbUser.getSpecialty();
            DBLocation location = dbUser.getLocation();
            Long l3 = null;
            if (profession == null) {
                l = null;
            } else {
                l = profession.getIdentifier();
            }
            if (specialty == null) {
                l2 = null;
            } else {
                l2 = specialty.getIdentifier();
            }
            if (location != null) {
                l3 = location.getIdentifier();
            }
            Long l4 = l3;
            String userEmail = UserManager.getInstance().getUserEmail();
            if (userEmail == null || !userEmail.contains("@qxmd.com")) {
                QXEEventsManager.getInstance().setDebugMode(false);
            } else {
                QXEEventsManager.getInstance().setDebugMode(true);
            }
            QXEEventsManager.getInstance().startCalculateSession(dbUser.getIdentifier(), l, l2, l4, userEmail);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        r1 = com.google.firebase.iid.FirebaseInstanceId.getInstance().getToken();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void registerPnTokenIfNeeded() {
        /*
            r7 = this;
            com.wbmd.qxcalculator.managers.UserManager r0 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            com.wbmd.qxcalculator.model.db.DBUser r0 = r0.getDbUser()
            if (r0 != 0) goto L_0x000b
            return
        L_0x000b:
            java.lang.Boolean r1 = r0.getPnEnabled()
            if (r1 == 0) goto L_0x007b
            java.lang.Boolean r1 = r0.getPnEnabled()
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x001c
            goto L_0x007b
        L_0x001c:
            com.google.firebase.iid.FirebaseInstanceId r1 = com.google.firebase.iid.FirebaseInstanceId.getInstance()
            java.lang.String r1 = r1.getToken()
            if (r1 != 0) goto L_0x0027
            return
        L_0x0027:
            com.wbmd.qxcalculator.managers.UserManager r2 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            java.lang.String r2 = r2.getUserEmail()
            if (r2 != 0) goto L_0x0032
            return
        L_0x0032:
            java.lang.Boolean r2 = r0.getShouldRegisterUserWithServer()
            if (r2 == 0) goto L_0x0042
            java.lang.Boolean r2 = r0.getShouldRegisterUserWithServer()
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L_0x007b
        L_0x0042:
            java.lang.Boolean r2 = r0.getShouldDispatchUpdatesToServer()
            if (r2 == 0) goto L_0x0052
            java.lang.Boolean r2 = r0.getShouldDispatchUpdatesToServer()
            boolean r2 = r2.booleanValue()
            if (r2 != 0) goto L_0x007b
        L_0x0052:
            java.lang.String r2 = r0.getPnTokenSentToListServer()
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L_0x007b
            java.lang.String r2 = "DataManager.TASK_ID_REGISTER_PN_WITH_LIST_SERVER"
            boolean r3 = r7.isTaskCurrentlyRunning(r2)
            if (r3 != 0) goto L_0x007b
            com.wbmd.qxcalculator.model.api.APITask r3 = new com.wbmd.qxcalculator.model.api.APITask
            com.wbmd.qxcalculator.managers.DataManager$13 r4 = new com.wbmd.qxcalculator.managers.DataManager$13
            r4.<init>(r1)
            com.wbmd.qxcalculator.managers.DataManager$14 r5 = new com.wbmd.qxcalculator.managers.DataManager$14
            r5.<init>()
            com.wbmd.qxcalculator.managers.DataManager$15 r6 = new com.wbmd.qxcalculator.managers.DataManager$15
            r6.<init>(r0, r1)
            r3.<init>((com.wbmd.qxcalculator.model.api.APITask.OnPrepareRequestBlock) r4, (com.wbmd.qxcalculator.model.api.APITask.OnProcessResponseBlock) r5, (com.wbmd.qxcalculator.model.api.APITask.OnCompletionBlock) r6, (java.lang.String) r2)
            r3.startTask()
        L_0x007b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.managers.DataManager.registerPnTokenIfNeeded():void");
    }

    public void removePnDeviceTokenIfNeeded() {
        final DBUser dbUser = UserManager.getInstance().getDbUser();
        if (dbUser != null) {
            if ((dbUser.getPnEnabled() != null && dbUser.getPnEnabled().booleanValue()) || UserManager.getInstance().getUserEmail() == null) {
                return;
            }
            if (dbUser.getShouldRegisterUserWithServer() != null && dbUser.getShouldRegisterUserWithServer().booleanValue()) {
                return;
            }
            if (dbUser.getShouldDispatchUpdatesToServer() != null && dbUser.getShouldDispatchUpdatesToServer().booleanValue()) {
                return;
            }
            if (((dbUser.getPnTokenSentToListServer() != null && !dbUser.getPnTokenSentToListServer().isEmpty()) || (dbUser.getShouldSendRemovePnTokenRequest() != null && dbUser.getShouldSendRemovePnTokenRequest().booleanValue())) && !isTaskCurrentlyRunning(TASK_ID_REMOVE_PN_WITH_LIST_SERVER)) {
                new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
                    public APIRequest prepareRequest() {
                        APIRequest removeDeviceIdToken = APIRequest.removeDeviceIdToken(UserManager.getInstance().getUserEmail());
                        removeDeviceIdToken.setRegistrationAppId("13");
                        removeDeviceIdToken.setRegistrationPlatform(Build.MODEL);
                        return removeDeviceIdToken;
                    }
                }, (APITask.OnProcessResponseBlock) new APITask.OnProcessResponseBlock() {
                    public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, APITask aPITask) {
                        return aPIResponse;
                    }
                }, (APITask.OnCompletionBlock) new APITask.OnCompletionBlock() {
                    public Bundle onCompletion(APIResponse aPIResponse, boolean z, List<QxError> list) {
                        if (z) {
                            dbUser.setPnTokenSentToListServer((String) null);
                            dbUser.setShouldSendRemovePnTokenRequest(false);
                            dbUser.update();
                        }
                        return null;
                    }
                }, TASK_ID_REMOVE_PN_WITH_LIST_SERVER).startTask();
            }
        }
    }
}
