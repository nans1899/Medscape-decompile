package com.wbmd.qxcalculator.managers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.appevents.AppEventsConstants;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.qxmd.eventssdkandroid.managers.QXEEventsManager;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.AuthorizationProvider;
import com.wbmd.qxcalculator.model.QxError;
import com.wbmd.qxcalculator.model.api.APIRequest;
import com.wbmd.qxcalculator.model.api.APIResponse;
import com.wbmd.qxcalculator.model.api.APITask;
import com.wbmd.qxcalculator.model.api.ApiResultType;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.common.Favorite;
import com.wbmd.qxcalculator.model.contentItems.common.Recent;
import com.wbmd.qxcalculator.model.db.DBCategory;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.model.db.DBResourceFile;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.model.db.DaoSession;
import com.wbmd.qxcalculator.model.db.managers.DatabaseManager;
import com.wbmd.qxcalculator.model.parsedObjects.User;
import com.wbmd.qxcalculator.model.rowItems.LeafItemRowItem;
import com.wbmd.qxcalculator.util.CrashLogger;
import com.wbmd.qxcalculator.util.RowItemBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class ContentDataManager {
    protected static final long HOUR_IN_MS = 3600000;
    public static final String KEY_NEW_CONTENT_ITEM_IDENTIFIERS = "ApiCommManager.KEY_NEW_CONTENT_ITEM_IDENTIFIERS";
    public static final String KEY_NEW_MENU_ITEMS_IDENTIFIERS = "ApiCommManager.KEY_NEW_MENU_ITEMS_IDENTIFIERS";
    public static final String TASK_ID_FETCH_UPDATES = "DataManager.TASK_ID_FETCH_UPDATES";
    public static final String TASK_ID_REFRESH_ALL = "DataManager.TASK_ID_REFRESH_ALL";
    public static final String TASK_ID_REFRESH_CALCULATORS = "DataManager.TASK_ID_REFRESH_CALCULATORS";
    protected static Context context;
    private static ContentDataManager mInstance;
    public List<QxError> accountRefreshErrors;
    public AuthorizationProvider authorizationProvider;
    /* access modifiers changed from: private */
    public List<String> deletedContentItemIds;
    /* access modifiers changed from: private */
    public List<String> deletedFavoritesIdentifiers;
    /* access modifiers changed from: private */
    public HashMap<String, Long> deletedRecentsMap;
    public CountDownLatch fetchItemsLatch;
    public List<QxError> fetchResourcesErrors;
    protected boolean isFetchingUpdates = false;
    protected boolean isRefreshing = false;
    /* access modifiers changed from: private */
    public OnContentUpdateListener onContentUpdateListener;
    /* access modifiers changed from: private */
    public OnRefreshListener onRefreshListener;
    public List<QxError> refreshAllErrors;
    public boolean shouldRefresh = true;
    /* access modifiers changed from: private */
    public List<Favorite> unprocessedParsedFavorites;
    /* access modifiers changed from: private */
    public List<Recent> unprocessedParsedRecents;

    public interface OnContentUpdateListener {
        void onContentUpdateCompleted(Bundle bundle);

        void onMenuItemUpdateCompleted();
    }

    public interface OnRefreshListener {
        void onRefreshCompleted(Bundle bundle);
    }

    private ContentDataManager() {
    }

    public static ContentDataManager getInstance() {
        if (mInstance == null) {
            mInstance = new ContentDataManager();
        }
        return mInstance;
    }

    public void setContext(Context context2) {
        context = context2;
    }

    public DaoSession getDaoSession() {
        return DatabaseManager.getInstance().getDaoSession();
    }

    public void setRecentlyUsed(final String str, final Date date, String str2) {
        TaskManager.getInstance().addTask(new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
            public APIRequest prepareRequest() {
                APIRequest initAddRecents = APIRequest.initAddRecents();
                initAddRecents.addRecent(str, date);
                return initAddRecents;
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
                UserManager.getInstance().getDbUser().markContentItemUsed(ContentDataManager.this.getContentItemForIdentifier(str), date.getTime(), ContentDataManager.context);
                return null;
            }
        }, str2));
    }

    public void setFavoriteStatus(final boolean z, final String str, String str2) {
        TaskManager.getInstance().addTask(new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
            public APIRequest prepareRequest() {
                if (z) {
                    APIRequest initAddFavorites = APIRequest.initAddFavorites();
                    initAddFavorites.addFavorite(str, new Date());
                    return initAddFavorites;
                }
                APIRequest initRemoveFavorites = APIRequest.initRemoveFavorites();
                initRemoveFavorites.removeFavorite(str);
                return initRemoveFavorites;
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
                DBContentItem contentItemForIdentifier = ContentDataManager.getInstance().getContentItemForIdentifier(str);
                if (z) {
                    dbUser.addContentItemToFavorites(contentItemForIdentifier, ContentDataManager.context);
                    return null;
                }
                dbUser.removeContentItemToFavorites(contentItemForIdentifier, ContentDataManager.context);
                return null;
            }
        }, str2));
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener2) {
        this.onRefreshListener = onRefreshListener2;
    }

    public OnRefreshListener getOnRefreshListener() {
        return this.onRefreshListener;
    }

    public void setOnContentUpdateListener(OnContentUpdateListener onContentUpdateListener2) {
        this.onContentUpdateListener = onContentUpdateListener2;
    }

    public OnContentUpdateListener getOnContentUpdateListener() {
        return this.onContentUpdateListener;
    }

    public boolean getIsRefreshing() {
        return this.isRefreshing;
    }

    public boolean getIsFetchingUpdates() {
        return this.isFetchingUpdates;
    }

    public int getWeeksSinceLastRefresh() {
        long j;
        Long lastRefreshTime = UserManager.getInstance().getDbUser().getLastRefreshTime();
        if (lastRefreshTime == null) {
            j = 0;
        } else {
            j = lastRefreshTime.longValue();
        }
        if (j == 0) {
            return -1;
        }
        return (int) ((j - new Date().getTime()) / 604800000);
    }

    /* access modifiers changed from: private */
    public void updateUsersLastRefreshTime() {
        DBUser dbUser = UserManager.getInstance().getDbUser();
        dbUser.setLastRefreshTime(Long.valueOf(new Date().getTime()));
        dbUser.update();
    }

    public boolean shouldRefresh() {
        long j;
        DBUser dbUser = UserManager.getInstance().getDbUser();
        if (dbUser == null) {
            return false;
        }
        Long lastRefreshTime = dbUser.getLastRefreshTime();
        if (lastRefreshTime == null) {
            j = 0;
        } else {
            j = lastRefreshTime.longValue();
        }
        long time = new Date().getTime();
        if (this.shouldRefresh || time - j > 3600000) {
            return true;
        }
        return false;
    }

    public void setShouldRefresh(boolean z) {
        this.shouldRefresh = z;
    }

    public void refreshContent(final boolean z) {
        if (!this.isRefreshing) {
            this.deletedRecentsMap = new HashMap<>();
            this.deletedFavoritesIdentifiers = new ArrayList();
            this.deletedContentItemIds = new ArrayList();
            CrashLogger.getInstance().leaveBreadcrumb("DataManager: refreshContent");
            if (!InternetConnectivityManager.getInstance().isConnectedToInternet()) {
                CrashLogger.getInstance().leaveBreadcrumb("DataManager: refreshContent no internet");
            }
            this.isRefreshing = true;
            final DBUser dbUser = UserManager.getInstance().getDbUser();
            new Date().getTime();
            final Bundle bundle = new Bundle();
            this.refreshAllErrors = null;
            this.fetchResourcesErrors = null;
            APITask aPITask = new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
                public APIRequest prepareRequest() {
                    FileHelper.getInstance().clearCachedFiles();
                    APIRequest refreshAll = APIRequest.refreshAll();
                    if (z) {
                        refreshAll.setLongRefreshTimeout();
                    }
                    ArrayList<Integer> debugUserGroup = dbUser.getDebugUserGroup();
                    if (debugUserGroup != null && !debugUserGroup.isEmpty()) {
                        StringBuilder sb = new StringBuilder(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                        Iterator<Integer> it = debugUserGroup.iterator();
                        while (it.hasNext()) {
                            sb.append("|");
                            sb.append(it.next());
                        }
                        refreshAll.setDebugType(sb.toString());
                        refreshAll.setLongRefreshTimeout();
                    }
                    if (!UserManager.getInstance().hasFinishedUpgradingToUniversalAccounts()) {
                        if (dbUser.getLocation() != null) {
                            refreshAll.setLocationId(dbUser.getLocation().getIdentifier());
                        }
                        if (dbUser.getSpecialty() != null) {
                            refreshAll.setSpecialtyId(dbUser.getSpecialty().getIdentifier());
                        }
                        if (dbUser.getProfession() != null) {
                            refreshAll.setProfessionId(dbUser.getProfession().getIdentifier());
                        }
                    }
                    refreshAll.setMinifyEnabled(true);
                    return refreshAll;
                }
            }, (APITask.OnProcessResponseBlock) new APITask.OnProcessResponseBlock() {
                public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, APITask aPITask) {
                    if (z) {
                        ContentDataManager.this.accountRefreshErrors = null;
                    } else if (aPIResponse.errors == null || aPIResponse.errors.isEmpty()) {
                        QxError qxError = new QxError(QxError.ErrorType.API, 0, "Error", "Cannot refresh app. Please try again.");
                        ContentDataManager.this.accountRefreshErrors = new ArrayList();
                        ContentDataManager.this.accountRefreshErrors.add(qxError);
                    } else {
                        ContentDataManager.this.accountRefreshErrors = aPIResponse.errors;
                    }
                    if (z || aPIResponse.accountsErrorOnRefreshAll) {
                        boolean hasFinishedUpgradingToUniversalAccounts = UserManager.getInstance().hasFinishedUpgradingToUniversalAccounts();
                        DBUser dbUser = UserManager.getInstance().getDbUser();
                        if (aPIResponse.user != null) {
                            User user = aPIResponse.user;
                            dbUser.setProfession(user.profession, false);
                            dbUser.setSpecialty(user.specialty, false);
                            dbUser.setLocation(user.location, false);
                            if (hasFinishedUpgradingToUniversalAccounts) {
                                dbUser.setFirstName(user.nameFirst);
                                dbUser.setLastName(user.nameLast);
                                dbUser.setDescription(user.userDescription);
                                dbUser.setZipCode(user.zip);
                                UserManager.getInstance().setUserEmail(user.email);
                                if (user.defaultUnitsString != null) {
                                    dbUser.setDefaultUnits(user.defaultUnitsString);
                                }
                                if (dbUser.getDefaultUnits() == null) {
                                    dbUser.setDefaultUnits(Unit.UnitType.convertTypeToString(Unit.UnitType.US_UNITS));
                                }
                                if (user.showItemDescriptions != null) {
                                    dbUser.setShowItemDescription(user.showItemDescriptions);
                                }
                                if (user.autoOpenFirstQuestion != null) {
                                    dbUser.setAutoEnterFirstQuestion(user.autoOpenFirstQuestion);
                                    if (user.autoOpenFirstQuestion.booleanValue()) {
                                        dbUser.setShouldShowPromptForAutoEnterFirstQuestion(false);
                                    }
                                }
                                if (user.pnEnabled != null) {
                                    dbUser.setPnEnabled(user.pnEnabled);
                                }
                                dbUser.setShouldVerifyProfile(user.shouldVerify);
                                dbUser.setAccessPrivileges(user.accessPrivileges);
                                dbUser.setConsents(user.currentConsents);
                                dbUser.setRequiredConsents(user.consentsRequired);
                                dbUser.setConsentsToRequest(user.consentsToRequest);
                                if (!dbUser.canEditDebugGroup()) {
                                    dbUser.setDebugGroups(user.debugGroups);
                                }
                            }
                        }
                        if (aPIResponse.bannerAdsEnabled != null) {
                            dbUser.setBannerAdsEnabled(aPIResponse.bannerAdsEnabled);
                        }
                        dbUser.update();
                        List<ContentItem> list2 = aPIResponse.contentItems;
                        if (z && hasFinishedUpgradingToUniversalAccounts) {
                            List unused = ContentDataManager.this.unprocessedParsedRecents = aPIResponse.recents;
                            List unused2 = ContentDataManager.this.unprocessedParsedFavorites = aPIResponse.favorites;
                            if ((ContentDataManager.this.unprocessedParsedRecents != null && !ContentDataManager.this.unprocessedParsedRecents.isEmpty()) || (ContentDataManager.this.unprocessedParsedFavorites != null && !ContentDataManager.this.unprocessedParsedFavorites.isEmpty())) {
                                dbUser.setShouldShowPromptForAutoEnterFirstQuestion(false);
                                dbUser.update();
                            }
                            List<DBContentItem> recentItems = dbUser.getRecentItems();
                            if (recentItems != null) {
                                for (DBContentItem isRecent : recentItems) {
                                    isRecent.setIsRecent(false);
                                }
                                ContentDataManager.this.getDaoSession().getDBContentItemDao().updateInTx(recentItems);
                            }
                            List<DBContentItem> favoriteItems = dbUser.getFavoriteItems();
                            if (favoriteItems != null) {
                                for (DBContentItem isFavorite : favoriteItems) {
                                    isFavorite.setIsFavorite(false);
                                }
                                ContentDataManager.this.getDaoSession().getDBContentItemDao().updateInTx(favoriteItems);
                            }
                            ContentDataManager.this.processUnprocessedFavoritesAndRecents();
                        }
                        ArrayList arrayList = new ArrayList();
                        ArrayList<DBContentItem> arrayList2 = new ArrayList<>();
                        ArrayList<DBContentItem> arrayList3 = new ArrayList<>();
                        ContentHelper.getInstance().checkForUpdatesToContentItems(dbUser.getContentItemsAndFetchRelations(), list2, arrayList, arrayList2, arrayList3, ContentDataManager.this.getDaoSession());
                        if (!arrayList3.isEmpty()) {
                            for (DBContentItem dBContentItem : arrayList3) {
                                ContentDataManager.this.deletedContentItemIds.add(dBContentItem.getIdentifier());
                                if (dBContentItem.getIsFavorite() != null && dBContentItem.getIsFavorite().booleanValue()) {
                                    ContentDataManager.this.deletedFavoritesIdentifiers.add(dBContentItem.getIdentifier());
                                }
                                if (dBContentItem.getLastUsedDate() != null) {
                                    ContentDataManager.this.deletedRecentsMap.put(dBContentItem.getIdentifier(), dBContentItem.getLastUsedDate());
                                }
                            }
                            DBContentItem.deleteContentItems(ContentDataManager.this.getDaoSession(), arrayList3);
                        }
                        ArrayList arrayList4 = new ArrayList();
                        for (DBContentItem dBContentItem2 : arrayList2) {
                            if (dBContentItem2.getResourcesRequireUpdate() != null && dBContentItem2.getResourcesRequireUpdate().booleanValue()) {
                                arrayList4.add(dBContentItem2);
                            }
                        }
                        new Date().getTime();
                        RowItemBuilder.getInstance().rebuildCache();
                        int size = arrayList2.size();
                        long[] jArr = new long[size];
                        Iterator it = arrayList2.iterator();
                        for (int i = 0; i < size; i++) {
                            jArr[i] = ((DBContentItem) it.next()).getId().longValue();
                        }
                        if (!arrayList.isEmpty()) {
                            bundle.putStringArrayList(ApiResultType.CONTENT_ID_NEW_TO_FETCH, arrayList);
                        }
                        if (size > 0) {
                            bundle.putLongArray(ApiResultType.CONTENT_ITEM_ID_TO_UPDATE, jArr);
                        }
                    } else if (!RowItemBuilder.getInstance().getRowItemCacheBuilt()) {
                        RowItemBuilder.getInstance().rebuildCache();
                    }
                    return aPIResponse;
                }
            }, (APITask.OnCompletionBlock) new APITask.OnCompletionBlock() {
                public Bundle onCompletion(APIResponse aPIResponse, boolean z, List<QxError> list) {
                    CrashLogger.getInstance().leaveBreadcrumb("DataManager: refreshContent completed");
                    boolean z2 = false;
                    if (z || aPIResponse.accountsErrorOnRefreshAll) {
                        ArrayList<String> stringArrayList = bundle.getStringArrayList(ApiResultType.CONTENT_ID_NEW_TO_FETCH);
                        long[] longArray = bundle.getLongArray(ApiResultType.CONTENT_ITEM_ID_TO_UPDATE);
                        if ((stringArrayList == null || stringArrayList.isEmpty()) && (longArray == null || longArray.length == 0)) {
                            ContentDataManager.this.shouldRefresh = false;
                            DataManager.getInstance().registerPnTokenIfNeeded();
                            DataManager.getInstance().removePnDeviceTokenIfNeeded();
                        }
                        ContentDataManager.this.updateUsersLastRefreshTime();
                    } else {
                        ContentDataManager.this.refreshAllErrors = list;
                        DataManager.getInstance().registerPnTokenIfNeeded();
                    }
                    if (z) {
                        DBUser dbUser = UserManager.getInstance().getDbUser();
                        if (dbUser.getProfession() != null) {
                            QXEEventsManager.getInstance().setProfessionId(dbUser.getProfession().getIdentifier());
                        }
                        if (dbUser.getSpecialty() != null) {
                            QXEEventsManager.getInstance().setSpecialtyId(dbUser.getSpecialty().getIdentifier());
                        }
                        if (dbUser.getLocation() != null) {
                            QXEEventsManager.getInstance().setLocationId(dbUser.getLocation().getIdentifier());
                        }
                        QXEEventsManager.getInstance().setUserEmailAddress(UserManager.getInstance().getUserEmail());
                    }
                    ContentDataManager.this.isRefreshing = false;
                    if (ContentDataManager.this.onRefreshListener == null) {
                        return null;
                    }
                    Bundle bundle = bundle;
                    if (z || aPIResponse.accountsErrorOnRefreshAll) {
                        z2 = true;
                    }
                    bundle.putBoolean(ApiResultType.SUCCESS, z2);
                    ContentDataManager.this.onRefreshListener.onRefreshCompleted(bundle);
                    return null;
                }
            }, TASK_ID_REFRESH_ALL);
            aPITask.shouldAutoReauth = true;
            aPITask.shouldAutoRetryTimeouts = false;
            TaskManager.getInstance().addTask(aPITask);
        }
    }

    public void refreshCalculatorsContent(final boolean z) {
        if (!this.isRefreshing) {
            this.deletedRecentsMap = new HashMap<>();
            this.deletedFavoritesIdentifiers = new ArrayList();
            this.deletedContentItemIds = new ArrayList();
            CrashLogger.getInstance().leaveBreadcrumb("DataManager: refreshContent");
            if (!InternetConnectivityManager.getInstance().isConnectedToInternet()) {
                CrashLogger.getInstance().leaveBreadcrumb("DataManager: refreshContent no internet");
            }
            this.isRefreshing = true;
            new Date().getTime();
            final Bundle bundle = new Bundle();
            this.refreshAllErrors = null;
            this.fetchResourcesErrors = null;
            APITask aPITask = new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
                public APIRequest prepareRequest() {
                    FileHelper.getInstance().clearCachedFiles();
                    APIRequest refreshCalculators = APIRequest.refreshCalculators();
                    if (z) {
                        refreshCalculators.setLongRefreshTimeout();
                    }
                    refreshCalculators.setMinifyEnabled(true);
                    return refreshCalculators;
                }
            }, (APITask.OnProcessResponseBlock) new APITask.OnProcessResponseBlock() {
                public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, APITask aPITask) {
                    if (z) {
                        DBUser dbUser = UserManager.getInstance().getDbUser();
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        long[] jArr = new long[0];
                        if (dbUser != null) {
                            List<ContentItem> list2 = aPIResponse.contentItems;
                            ArrayList<DBContentItem> arrayList3 = new ArrayList<>();
                            ContentHelper.getInstance().checkForUpdatesToContentItems(dbUser.getContentItemsAndFetchRelations(), list2, arrayList, arrayList2, arrayList3, ContentDataManager.this.getDaoSession());
                            if (!arrayList3.isEmpty()) {
                                for (DBContentItem dBContentItem : arrayList3) {
                                    ContentDataManager.this.deletedContentItemIds.add(dBContentItem.getIdentifier());
                                    if (dBContentItem.getIsFavorite() != null && dBContentItem.getIsFavorite().booleanValue()) {
                                        ContentDataManager.this.deletedFavoritesIdentifiers.add(dBContentItem.getIdentifier());
                                    }
                                    if (dBContentItem.getLastUsedDate() != null) {
                                        ContentDataManager.this.deletedRecentsMap.put(dBContentItem.getIdentifier(), dBContentItem.getLastUsedDate());
                                    }
                                }
                                DBContentItem.deleteContentItems(ContentDataManager.this.getDaoSession(), arrayList3);
                            }
                            new Date().getTime();
                            RowItemBuilder.getInstance().rebuildCache();
                            int size = arrayList2.size();
                            jArr = new long[size];
                            Iterator it = arrayList2.iterator();
                            for (int i = 0; i < size; i++) {
                                jArr[i] = ((DBContentItem) it.next()).getId().longValue();
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            bundle.putStringArrayList(ApiResultType.CONTENT_ID_NEW_TO_FETCH, arrayList);
                        }
                        if (jArr.length > 0) {
                            bundle.putLongArray(ApiResultType.CONTENT_ITEM_ID_TO_UPDATE, jArr);
                        }
                    } else if (!RowItemBuilder.getInstance().getRowItemCacheBuilt()) {
                        RowItemBuilder.getInstance().rebuildCache();
                    }
                    return aPIResponse;
                }
            }, (APITask.OnCompletionBlock) new APITask.OnCompletionBlock() {
                public Bundle onCompletion(APIResponse aPIResponse, boolean z, List<QxError> list) {
                    CrashLogger.getInstance().leaveBreadcrumb("DataManager: refreshContent completed");
                    if (z) {
                        ArrayList<String> stringArrayList = bundle.getStringArrayList(ApiResultType.CONTENT_ID_NEW_TO_FETCH);
                        long[] longArray = bundle.getLongArray(ApiResultType.CONTENT_ITEM_ID_TO_UPDATE);
                        if ((stringArrayList == null || stringArrayList.isEmpty()) && (longArray == null || longArray.length == 0)) {
                            ContentDataManager.this.shouldRefresh = false;
                        }
                        ContentDataManager.this.updateUsersLastRefreshTime();
                    } else {
                        ContentDataManager.this.refreshAllErrors = list;
                    }
                    ContentDataManager.this.isRefreshing = false;
                    if (ContentDataManager.this.onRefreshListener == null) {
                        return null;
                    }
                    bundle.putBoolean(ApiResultType.SUCCESS, z);
                    ContentDataManager.this.onRefreshListener.onRefreshCompleted(bundle);
                    return null;
                }
            }, TASK_ID_REFRESH_CALCULATORS);
            aPITask.shouldAutoReauth = true;
            aPITask.shouldAutoRetryTimeouts = false;
            TaskManager.getInstance().addTask(aPITask);
        }
    }

    public void fetchItemsThatNeedUpdating() {
        List<DBContentItem> contentItemsThatNeedUpdating = DBContentItem.getContentItemsThatNeedUpdating(getDaoSession());
        if (contentItemsThatNeedUpdating != null && !contentItemsThatNeedUpdating.isEmpty()) {
            ArrayList arrayList = new ArrayList(contentItemsThatNeedUpdating.size());
            for (DBContentItem id : contentItemsThatNeedUpdating) {
                arrayList.add(id.getId());
            }
            fetchContentItems((List<String>) null, arrayList);
        }
    }

    public void fetchContentItems(List<String> list, List<Long> list2) {
        if (!this.isFetchingUpdates) {
            this.fetchItemsLatch = new CountDownLatch(1);
            CrashLogger.getInstance().leaveBreadcrumb("DataManager: fetchContentItems");
            this.isFetchingUpdates = true;
            List<DBContentItem> contentItemsForIds = DBContentItem.getContentItemsForIds(getDaoSession(), list2);
            if (contentItemsForIds != null) {
                for (DBContentItem isUpdating : contentItemsForIds) {
                    isUpdating.setIsUpdating(true);
                }
            }
            Handler handler = new Handler(context.getMainLooper());
            final ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            final ArrayList arrayList5 = new ArrayList();
            final HashMap hashMap = new HashMap();
            final Bundle bundle = new Bundle();
            this.fetchResourcesErrors = null;
            TaskManager instance = TaskManager.getInstance();
            final List<String> list3 = list;
            final List<Long> list4 = list2;
            final ArrayList arrayList6 = arrayList2;
            final Handler handler2 = handler;
            final ArrayList arrayList7 = arrayList2;
            final List<String> list5 = list;
            List asList = Arrays.asList(new APITask.OnPrepareRequestBlock[]{new APITask.OnPrepareRequestBlock() {
                public APIRequest prepareRequest() {
                    List list = list3;
                    if (list != null) {
                        arrayList.addAll(list);
                    }
                    if (list4 != null) {
                        arrayList6.addAll(DBContentItem.getContentItemsForIds(ContentDataManager.this.getDaoSession(), list4));
                        for (DBContentItem identifier : arrayList6) {
                            arrayList.add(identifier.getIdentifier());
                        }
                    }
                    APIRequest fetchItems = APIRequest.fetchItems();
                    ArrayList<Integer> debugUserGroup = UserManager.getInstance().getDbUser().getDebugUserGroup();
                    if (debugUserGroup != null && !debugUserGroup.isEmpty()) {
                        StringBuilder sb = new StringBuilder(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                        Iterator<Integer> it = debugUserGroup.iterator();
                        while (it.hasNext()) {
                            sb.append("|");
                            sb.append(it.next());
                        }
                        fetchItems.setDebugType(sb.toString());
                    }
                    for (String addItemToFetch : arrayList) {
                        fetchItems.addItemToFetch(addItemToFetch);
                    }
                    return fetchItems;
                }
            }, new APITask.OnPrepareRequestBlock() {
                public APIRequest prepareRequest() {
                    if (arrayList5.isEmpty()) {
                        return null;
                    }
                    APIRequest fetchResources = APIRequest.fetchResources();
                    ArrayList<Integer> debugUserGroup = UserManager.getInstance().getDbUser().getDebugUserGroup();
                    if (debugUserGroup != null && !debugUserGroup.isEmpty()) {
                        StringBuilder sb = new StringBuilder(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                        Iterator<Integer> it = debugUserGroup.iterator();
                        while (it.hasNext()) {
                            sb.append("|");
                            sb.append(it.next());
                        }
                        fetchResources.setDebugType(sb.toString());
                    }
                    for (Map.Entry entry : hashMap.entrySet()) {
                        String str = (String) entry.getKey();
                        String str2 = (String) entry.getValue();
                        fetchResources.addItemToFetchResources(str, str2);
                        Log.d("API", "add resource last mod date to fetch from - id: " + str + "; lastModDateTimeStamp: " + str2);
                    }
                    return fetchResources;
                }
            }});
            final ArrayList arrayList8 = arrayList3;
            TaskManager taskManager = instance;
            final ArrayList arrayList9 = arrayList4;
            final ArrayList arrayList10 = arrayList2;
            final Handler handler3 = handler;
            final ArrayList arrayList11 = arrayList4;
            final Bundle bundle2 = bundle;
            APITask.OnProcessResponseBlock[] onProcessResponseBlockArr = {new APITask.OnProcessResponseBlock() {
                public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, APITask aPITask) {
                    if (!z) {
                        handler2.post(new Runnable() {
                            public void run() {
                                for (DBContentItem isUpdating : arrayList7) {
                                    isUpdating.setIsUpdating(false);
                                }
                            }
                        });
                        return aPIResponse;
                    }
                    for (ContentItem next : aPIResponse.contentItems) {
                        List list2 = list5;
                        if (list2 == null || !list2.contains(next.identifier)) {
                            arrayList9.add(next);
                        } else {
                            arrayList8.add(next);
                        }
                    }
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    HashMap hashMap = new HashMap();
                    ArrayList arrayList3 = new ArrayList();
                    Iterator it = arrayList7.iterator();
                    while (it.hasNext()) {
                        DBContentItem dBContentItem = (DBContentItem) it.next();
                        if (dBContentItem.getResourcesRequireUpdate() == null || !dBContentItem.getResourcesRequireUpdate().booleanValue()) {
                            Iterator it2 = arrayList9.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                ContentItem contentItem = (ContentItem) it2.next();
                                if (contentItem.identifier.equals(dBContentItem.getIdentifier())) {
                                    hashMap.put(contentItem, dBContentItem);
                                    arrayList3.add(contentItem);
                                    it2.remove();
                                    it.remove();
                                    if (DBContentItem.shouldIncludeItem(dBContentItem)) {
                                        arrayList2.add(dBContentItem);
                                    } else {
                                        arrayList.add(dBContentItem);
                                    }
                                }
                            }
                        }
                    }
                    if (aPITask.isCancelled()) {
                        return aPIResponse;
                    }
                    List<DBResourceFile> findResourceFilesToDelete = ContentHelper.getInstance().findResourceFilesToDelete(hashMap);
                    if (!findResourceFilesToDelete.isEmpty()) {
                        DBResourceFile.deleteResourceFiles(ContentDataManager.this.getDaoSession(), findResourceFilesToDelete);
                    }
                    if (aPITask.isCancelled()) {
                        return aPIResponse;
                    }
                    final List<DBContentItem> insertAndRetrieveDbEntities = DBContentItem.insertAndRetrieveDbEntities(ContentDataManager.this.getDaoSession(), arrayList3);
                    ArrayList<DBContentItem> arrayList4 = new ArrayList<>();
                    ArrayList<DBContentItem> arrayList5 = new ArrayList<>();
                    for (DBContentItem next2 : insertAndRetrieveDbEntities) {
                        if (DBContentItem.shouldIncludeItem(next2)) {
                            arrayList5.add(next2);
                        } else {
                            arrayList4.add(next2);
                        }
                    }
                    final ArrayList arrayList6 = new ArrayList(arrayList4.size());
                    final ArrayList arrayList7 = new ArrayList(arrayList5.size());
                    for (DBContentItem dBContentItem2 : arrayList4) {
                        if (dBContentItem2.getListType() == ContentItem.ContentItemListType.DEFAULT && !arrayList.contains(dBContentItem2)) {
                            arrayList6.add(dBContentItem2);
                        }
                    }
                    for (DBContentItem dBContentItem3 : arrayList5) {
                        if (dBContentItem3.getListType() == ContentItem.ContentItemListType.DEFAULT && !arrayList2.contains(dBContentItem3)) {
                            arrayList7.add(dBContentItem3);
                        }
                    }
                    if (!insertAndRetrieveDbEntities.isEmpty()) {
                        handler2.post(new Runnable() {
                            public void run() {
                                ArrayList arrayList = new ArrayList(insertAndRetrieveDbEntities.size());
                                boolean z = false;
                                for (DBContentItem dBContentItem : insertAndRetrieveDbEntities) {
                                    dBContentItem.setIsUpdating(false);
                                    if (dBContentItem.isMenuContentItem()) {
                                        z = true;
                                    } else {
                                        arrayList.add(dBContentItem);
                                    }
                                }
                                if (!arrayList6.isEmpty()) {
                                    RowItemBuilder.getInstance().deleteContentItems(arrayList6);
                                }
                                if (!arrayList7.isEmpty()) {
                                    RowItemBuilder.getInstance().insertContentItems(arrayList6);
                                }
                                if (!arrayList.isEmpty()) {
                                    RowItemBuilder.getInstance().updateContentItems(arrayList);
                                }
                                if (z && ContentDataManager.this.onContentUpdateListener != null) {
                                    ContentDataManager.this.onContentUpdateListener.onMenuItemUpdateCompleted();
                                }
                            }
                        });
                    }
                    if (!arrayList7.isEmpty()) {
                        for (DBContentItem dBContentItem4 : arrayList7) {
                            arrayList5.add(dBContentItem4.getIdentifier());
                            hashMap.put(dBContentItem4.getIdentifier(), Long.valueOf(dBContentItem4.getLastModifiedDate().longValue() / 1000).toString());
                        }
                    }
                    if (!arrayList8.isEmpty()) {
                        Iterator it3 = arrayList8.iterator();
                        while (it3.hasNext()) {
                            ContentItem contentItem2 = (ContentItem) it3.next();
                            arrayList5.add(contentItem2.identifier);
                            hashMap.put(contentItem2.identifier, AppEventsConstants.EVENT_PARAM_VALUE_NO);
                        }
                    }
                    return aPIResponse;
                }
            }, new APITask.OnProcessResponseBlock() {
                public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list, final APITask aPITask) {
                    if (!z) {
                        if (!arrayList10.isEmpty()) {
                            handler3.post(new Runnable() {
                                public void run() {
                                    for (DBContentItem isUpdating : arrayList10) {
                                        isUpdating.setIsUpdating(false);
                                    }
                                }
                            });
                        }
                        FileHelper.getInstance().deleteMasterZip();
                        return aPIResponse;
                    }
                    boolean z2 = true;
                    if (aPIResponse.isNonCalledResponse) {
                        aPIResponse.forceSuccess = true;
                        return aPIResponse;
                    }
                    if (aPIResponse.downloadedFile != null && !FileHelper.getInstance().unzipMasterFileZip()) {
                        z2 = false;
                    }
                    FileHelper.getInstance().deleteMasterZip();
                    if (z2) {
                        if (!arrayList11.isEmpty()) {
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            for (DBContentItem dBContentItem : arrayList10) {
                                if (DBContentItem.shouldIncludeItem(dBContentItem)) {
                                    arrayList2.add(dBContentItem);
                                } else {
                                    arrayList.add(dBContentItem);
                                }
                            }
                            HashMap hashMap = new HashMap();
                            Iterator it = arrayList11.iterator();
                            while (it.hasNext()) {
                                ContentItem contentItem = (ContentItem) it.next();
                                Iterator it2 = arrayList10.iterator();
                                while (true) {
                                    if (!it2.hasNext()) {
                                        break;
                                    }
                                    DBContentItem dBContentItem2 = (DBContentItem) it2.next();
                                    if (dBContentItem2.getIdentifier().equals(contentItem.identifier)) {
                                        hashMap.put(contentItem, dBContentItem2);
                                        break;
                                    }
                                }
                            }
                            if (aPITask.isCancelled()) {
                                return aPIResponse;
                            }
                            List<DBResourceFile> findResourceFilesToDelete = ContentHelper.getInstance().findResourceFilesToDelete(hashMap);
                            if (!findResourceFilesToDelete.isEmpty()) {
                                DBResourceFile.deleteResourceFiles(ContentDataManager.this.getDaoSession(), findResourceFilesToDelete);
                            }
                            if (aPITask.isCancelled()) {
                                return aPIResponse;
                            }
                            List<DBContentItem> insertAndRetrieveDbEntities = DBContentItem.insertAndRetrieveDbEntities(ContentDataManager.this.getDaoSession(), arrayList11);
                            ArrayList<DBContentItem> arrayList3 = new ArrayList<>();
                            ArrayList<DBContentItem> arrayList4 = new ArrayList<>();
                            for (DBContentItem next : insertAndRetrieveDbEntities) {
                                if (DBContentItem.shouldIncludeItem(next)) {
                                    arrayList4.add(next);
                                } else {
                                    arrayList3.add(next);
                                }
                            }
                            final ArrayList arrayList5 = new ArrayList(arrayList3.size());
                            final ArrayList arrayList6 = new ArrayList(arrayList4.size());
                            for (DBContentItem dBContentItem3 : arrayList3) {
                                if (dBContentItem3.getListType() == ContentItem.ContentItemListType.DEFAULT && !arrayList.contains(dBContentItem3)) {
                                    arrayList5.add(dBContentItem3);
                                }
                            }
                            for (DBContentItem dBContentItem4 : arrayList4) {
                                if (dBContentItem4.getListType() == ContentItem.ContentItemListType.DEFAULT && !arrayList2.contains(dBContentItem4)) {
                                    arrayList6.add(dBContentItem4);
                                }
                            }
                            handler3.post(new Runnable() {
                                public void run() {
                                    if (!aPITask.isCancelled()) {
                                        ArrayList arrayList = new ArrayList();
                                        boolean z = false;
                                        for (DBContentItem dBContentItem : arrayList10) {
                                            dBContentItem.setIsUpdating(false);
                                            ContentItem.ContentItemListType listType = dBContentItem.getListType();
                                            if (listType == ContentItem.ContentItemListType.MENU_PRIVACY_POLICY) {
                                                DBContentItem.shouldIncludeItem(dBContentItem);
                                            } else if (listType == ContentItem.ContentItemListType.MENU_TERMS_OF_USE) {
                                                DBContentItem.shouldIncludeItem(dBContentItem);
                                            } else if (listType != ContentItem.ContentItemListType.MENU) {
                                                arrayList.add(dBContentItem);
                                            }
                                            z = true;
                                        }
                                        if (!arrayList5.isEmpty()) {
                                            RowItemBuilder.getInstance().deleteContentItems(arrayList5);
                                        }
                                        if (!arrayList6.isEmpty()) {
                                            RowItemBuilder.getInstance().insertContentItems(arrayList6);
                                        }
                                        if (!arrayList.isEmpty()) {
                                            RowItemBuilder.getInstance().updateContentItems(arrayList);
                                        }
                                        if (z && ContentDataManager.this.onContentUpdateListener != null) {
                                            ContentDataManager.this.onContentUpdateListener.onMenuItemUpdateCompleted();
                                        }
                                    }
                                }
                            });
                        }
                        if (!arrayList8.isEmpty()) {
                            DBUser dbUser = UserManager.getInstance().getDbUser();
                            if (aPITask.isCancelled()) {
                                return aPIResponse;
                            }
                            List<DBContentItem> insertAndRetrieveDbEntities2 = DBContentItem.insertAndRetrieveDbEntities(ContentDataManager.this.getDaoSession(), arrayList8);
                            dbUser.insertContentItems(insertAndRetrieveDbEntities2);
                            ContentDataManager.this.getDaoSession().getDBContentItemDao().updateInTx(insertAndRetrieveDbEntities2);
                            if ((ContentDataManager.this.unprocessedParsedRecents != null && !ContentDataManager.this.unprocessedParsedRecents.isEmpty()) || (ContentDataManager.this.unprocessedParsedFavorites != null && !ContentDataManager.this.unprocessedParsedFavorites.isEmpty())) {
                                ContentDataManager.this.processUnprocessedFavoritesAndRecents();
                            }
                            final ArrayList arrayList7 = new ArrayList(insertAndRetrieveDbEntities2.size());
                            final ArrayList arrayList8 = new ArrayList(insertAndRetrieveDbEntities2.size());
                            ArrayList arrayList9 = new ArrayList(insertAndRetrieveDbEntities2.size());
                            final ArrayList arrayList10 = new ArrayList(insertAndRetrieveDbEntities2.size());
                            for (DBContentItem next2 : insertAndRetrieveDbEntities2) {
                                if (DBContentItem.shouldIncludeItem(next2)) {
                                    if (next2.isMenuContentItem()) {
                                        arrayList10.add(next2.getIdentifier());
                                        arrayList8.add(next2);
                                    } else if (next2.getListType() == ContentItem.ContentItemListType.DEFAULT) {
                                        arrayList9.add(next2.getIdentifier());
                                        arrayList7.add(next2);
                                    }
                                }
                            }
                            bundle2.putStringArrayList(ContentDataManager.KEY_NEW_CONTENT_ITEM_IDENTIFIERS, arrayList9);
                            bundle2.putStringArrayList(ContentDataManager.KEY_NEW_MENU_ITEMS_IDENTIFIERS, arrayList10);
                            final APITask aPITask2 = aPITask;
                            handler3.post(new Runnable() {
                                public void run() {
                                    if (!aPITask2.isCancelled()) {
                                        ArrayList arrayList = new ArrayList(arrayList7.size());
                                        for (DBContentItem dBContentItem : arrayList7) {
                                            if (dBContentItem.getCopiedFromContentItemId() != null) {
                                                if (ContentDataManager.this.deletedContentItemIds.contains(dBContentItem.getCopiedFromContentItemId())) {
                                                    dBContentItem.setIsNewlyAdded(false);
                                                } else {
                                                    dBContentItem.setIsNewlyAdded(true);
                                                }
                                                if (ContentDataManager.this.deletedFavoritesIdentifiers != null && ContentDataManager.this.deletedFavoritesIdentifiers.contains(dBContentItem.getCopiedFromContentItemId())) {
                                                    dBContentItem.setIsFavorite(true);
                                                }
                                                if (!(ContentDataManager.this.deletedRecentsMap == null || ContentDataManager.this.deletedRecentsMap.get(dBContentItem.getCopiedFromContentItemId()) == null)) {
                                                    dBContentItem.setLastUsedDate((Long) ContentDataManager.this.deletedRecentsMap.get(dBContentItem.getCopiedFromContentItemId()));
                                                }
                                            } else {
                                                dBContentItem.setIsNewlyAdded(true);
                                            }
                                            arrayList.add(dBContentItem);
                                        }
                                        if (!arrayList.isEmpty()) {
                                            ContentDataManager.this.getDaoSession().getDBContentItemDao().updateInTx(arrayList);
                                        }
                                        RowItemBuilder.getInstance().insertContentItems(arrayList7);
                                        if (ContentDataManager.this.onContentUpdateListener != null) {
                                            for (DBContentItem listType : arrayList8) {
                                                if (listType.getListType() != ContentItem.ContentItemListType.MENU_PRIVACY_POLICY) {
                                                    ContentItem.ContentItemListType contentItemListType = ContentItem.ContentItemListType.MENU_TERMS_OF_USE;
                                                }
                                            }
                                            if (!arrayList10.isEmpty()) {
                                                ContentDataManager.this.onContentUpdateListener.onMenuItemUpdateCompleted();
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                    return aPIResponse;
                }
            }};
            taskManager.addTask(new APITask((List<APITask.OnPrepareRequestBlock>) asList, (List<APITask.OnProcessResponseBlock>) Arrays.asList(onProcessResponseBlockArr), (APITask.MultiRequestOnCompletionBlock) new APITask.MultiRequestOnCompletionBlock() {
                public Bundle onCompletion(List<APIResponse> list, boolean z, List<QxError> list2) {
                    CrashLogger.getInstance().leaveBreadcrumb("DataManager: fetchContentItems completed");
                    if (z) {
                        ContentDataManager.this.shouldRefresh = false;
                        ContentDataManager.this.updateUsersLastRefreshTime();
                    } else {
                        ContentDataManager.this.fetchResourcesErrors = list2;
                    }
                    ContentDataManager.this.isFetchingUpdates = false;
                    if (ContentDataManager.this.onContentUpdateListener != null) {
                        if (list.get(list.size() - 1) != null) {
                            bundle.putBoolean(ApiResultType.SUCCESS, z);
                        } else {
                            bundle.putBoolean(ApiResultType.SUCCESS, false);
                        }
                        ContentDataManager.this.onContentUpdateListener.onContentUpdateCompleted(bundle);
                    } else {
                        ContentDataManager.this.getContentItemsThatWeShouldNotifyAreNowAvailable(RowItemBuilder.getInstance().getAllCalcRowItems());
                    }
                    HashMap unused = ContentDataManager.this.deletedRecentsMap = null;
                    List unused2 = ContentDataManager.this.deletedFavoritesIdentifiers = null;
                    List unused3 = ContentDataManager.this.deletedContentItemIds = null;
                    if (ContentDataManager.this.authorizationProvider != null && !ContentDataManager.this.authorizationProvider.isLoggingIn() && !ContentDataManager.this.authorizationProvider.isRegistering()) {
                        DataManager.getInstance().registerPnTokenIfNeeded();
                        DataManager.getInstance().removePnDeviceTokenIfNeeded();
                    }
                    ContentDataManager.this.fetchItemsLatch.countDown();
                    return null;
                }
            }, TASK_ID_FETCH_UPDATES));
        }
    }

    /* access modifiers changed from: private */
    public void processUnprocessedFavoritesAndRecents() {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (Recent next : this.unprocessedParsedRecents) {
            hashMap.put(next.identifier, next.lastUsedDate);
            arrayList.add(next.identifier);
        }
        List<DBContentItem> contentItemsForIdentifiers = getContentItemsForIdentifiers(arrayList);
        ArrayList arrayList2 = new ArrayList();
        if (contentItemsForIdentifiers != null) {
            for (DBContentItem identifier : contentItemsForIdentifiers) {
                arrayList2.add(identifier.getIdentifier());
            }
        }
        Iterator it = new ArrayList(this.unprocessedParsedRecents).iterator();
        while (it.hasNext()) {
            Recent recent = (Recent) it.next();
            if (arrayList2.contains(recent.identifier)) {
                this.unprocessedParsedRecents.remove(recent);
            }
        }
        if (contentItemsForIdentifiers != null) {
            for (DBContentItem next2 : contentItemsForIdentifiers) {
                next2.setIsRecent(true);
                next2.setIsNewlyAdded(false);
                next2.setShouldNotifiedBecomeAvailable(false);
                next2.setLastUsedDate((Long) hashMap.get(next2.getIdentifier()));
            }
            getDaoSession().getDBContentItemDao().updateInTx(contentItemsForIdentifiers);
            UserManager.getInstance().getDbUser().resetRecentItems();
        }
        ArrayList arrayList3 = new ArrayList();
        for (Favorite favorite : this.unprocessedParsedFavorites) {
            arrayList3.add(favorite.identifier);
        }
        List<DBContentItem> contentItemsForIdentifiers2 = getContentItemsForIdentifiers(arrayList3);
        ArrayList arrayList4 = new ArrayList();
        if (contentItemsForIdentifiers2 != null) {
            for (DBContentItem identifier2 : contentItemsForIdentifiers2) {
                arrayList4.add(identifier2.getIdentifier());
            }
        }
        Iterator it2 = new ArrayList(this.unprocessedParsedFavorites).iterator();
        while (it2.hasNext()) {
            Favorite favorite2 = (Favorite) it2.next();
            if (arrayList4.contains(favorite2.identifier)) {
                this.unprocessedParsedFavorites.remove(favorite2);
            }
        }
        if (contentItemsForIdentifiers2 != null) {
            for (DBContentItem next3 : contentItemsForIdentifiers2) {
                next3.setIsFavorite(true);
                next3.setIsNewlyAdded(false);
                next3.setShouldNotifiedBecomeAvailable(false);
            }
            getDaoSession().getDBContentItemDao().updateInTx(contentItemsForIdentifiers2);
            UserManager.getInstance().getDbUser().resetFavoriteItems();
        }
        new Handler(context.getMainLooper()).post(new Runnable() {
            public void run() {
                LocalBroadcastManager.getInstance(ContentDataManager.context).sendBroadcast(new Intent(DBUser.RECENTS_CHANGED));
                LocalBroadcastManager.getInstance(ContentDataManager.context).sendBroadcast(new Intent(DBUser.FAVORITES_CHANGED));
            }
        });
    }

    public List<DBContentItem> getMenuItemContentItems() {
        List<DBContentItem> contentItemsAndFetchRelations = UserManager.getInstance().getDbUser().getContentItemsAndFetchRelations();
        ArrayList arrayList = new ArrayList();
        for (DBContentItem next : contentItemsAndFetchRelations) {
            if (next.getCategories() != null) {
                Iterator<DBCategory> it = next.getCategories().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getName().toLowerCase().contains(Category.K_MENU_CATEGORY) && DBContentItem.shouldIncludeItem(next)) {
                            arrayList.add(next);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        Iterator it2 = new ArrayList(arrayList).iterator();
        while (it2.hasNext()) {
            DBContentItem dBContentItem = (DBContentItem) it2.next();
            ContentItem.ContentItemListType listType = dBContentItem.getListType();
            if (listType == ContentItem.ContentItemListType.MENU_PRIVACY_POLICY) {
                arrayList.remove(dBContentItem);
            } else if (listType == ContentItem.ContentItemListType.MENU_TERMS_OF_USE) {
                arrayList.remove(dBContentItem);
            }
        }
        return arrayList;
    }

    public DBContentItem getContentItemForIdentifier(String str) {
        return DBContentItem.getContentItemForIdentifier(getDaoSession(), str);
    }

    public List<DBContentItem> getContentItemsForIdentifiers(List<String> list) {
        return DBContentItem.getContentItemsForIdentifiers(getDaoSession(), list);
    }

    public boolean findAndMarkMissingFilesForContentItem(DBContentItem dBContentItem) {
        long j;
        File file = new File(FileHelper.getInstance().getResourceFolderPathForContentItemIdentifier(dBContentItem.getIdentifier()));
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File name : listFiles) {
                arrayList.add(name.getName());
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (DBResourceFile next : dBContentItem.getResourceFiles()) {
            if (!arrayList.contains(next.getName())) {
                arrayList2.add(next);
            }
        }
        if (arrayList2.isEmpty()) {
            return false;
        }
        Iterator it = arrayList2.iterator();
        long j2 = Long.MAX_VALUE;
        while (true) {
            j = 0;
            if (!it.hasNext()) {
                break;
            }
            DBResourceFile dBResourceFile = (DBResourceFile) it.next();
            if (dBResourceFile.getLastModifiedDate() != null) {
                j = dBResourceFile.getLastModifiedDate().longValue();
            }
            j2 = Math.min(j2, j);
        }
        if (j2 != Long.MAX_VALUE) {
            j = (j2 / 1000) - 1;
        }
        try {
            dBContentItem.setLastModifiedDate(Long.valueOf(j));
            dBContentItem.setRequiresUpdate(true);
            dBContentItem.setResourcesRequireUpdate(true);
            dBContentItem.update();
        } catch (Throwable th) {
            th.printStackTrace();
            FirebaseCrashlytics.getInstance().recordException(th);
        }
        return true;
    }

    public boolean getIsContentItemWithIdentifierAvailableAndReady(String str) {
        DBContentItem contentItemForIdentifier;
        if (str == null || str.isEmpty() || (contentItemForIdentifier = getContentItemForIdentifier(str)) == null) {
            return false;
        }
        if (contentItemForIdentifier.getResourcesRequireUpdate() != null && contentItemForIdentifier.getResourcesRequireUpdate().booleanValue()) {
            return false;
        }
        if (contentItemForIdentifier.getRequiresUpdate() == null || !contentItemForIdentifier.getRequiresUpdate().booleanValue()) {
            return true;
        }
        return false;
    }

    public void clearNewItemFlag(List<String> list) {
        List<DBContentItem> contentItemsForIdentifiers = getContentItemsForIdentifiers(list);
        for (DBContentItem isNewlyAdded : contentItemsForIdentifiers) {
            isNewlyAdded.setIsNewlyAdded(false);
        }
        getDaoSession().getDBContentItemDao().updateInTx(contentItemsForIdentifiers);
    }

    public int getNewItemCount() {
        return DBContentItem.getNewlyAddedContentItems(getDaoSession()).size();
    }

    public List<DBContentItem> getNewlyAddedContentItems() {
        return DBContentItem.getNewlyAddedContentItems(getDaoSession());
    }

    public void clearAllNewFlags() {
        List<DBContentItem> newlyAddedContentItems = getNewlyAddedContentItems();
        for (DBContentItem isNewlyAdded : newlyAddedContentItems) {
            isNewlyAdded.setIsNewlyAdded(false);
        }
        getDaoSession().getDBContentItemDao().updateInTx(newlyAddedContentItems);
    }

    public List<DBContentItem> getContentItemsThatWeShouldNotifyAreNowAvailable(List<QxRecyclerViewRowItem> list) {
        ArrayList<DBContentItem> arrayList = new ArrayList<>(list.size());
        Iterator<QxRecyclerViewRowItem> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((LeafItemRowItem) it.next()).contentItem);
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (DBContentItem dBContentItem : arrayList) {
            if (dBContentItem.getShouldNotifiedBecomeAvailable() != null && dBContentItem.getShouldNotifiedBecomeAvailable().booleanValue()) {
                arrayList2.add(dBContentItem);
                dBContentItem.setShouldNotifiedBecomeAvailable(false);
                dBContentItem.setIsNewlyAdded(true);
            }
        }
        if (!arrayList2.isEmpty()) {
            getDaoSession().getDBContentItemDao().updateInTx(arrayList2);
        }
        return arrayList2;
    }
}
