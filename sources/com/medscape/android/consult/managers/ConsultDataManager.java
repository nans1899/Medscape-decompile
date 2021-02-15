package com.medscape.android.consult.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import androidx.exifinterface.media.ExifInterface;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.volley.AuthFailureError;
import com.android.volley.ParseError;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.actions.SearchIntents;
import com.google.common.net.HttpHeaders;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.gson.Gson;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.auth.OAuthResponseParser;
import com.medscape.android.consult.ConsultUrls;
import com.medscape.android.consult.interfaces.ICommentUpdateListener;
import com.medscape.android.consult.interfaces.ICommunityArrayReceivedListener;
import com.medscape.android.consult.interfaces.ICommunityDataReceivedListener;
import com.medscape.android.consult.interfaces.ICommunityRequestCompleteListener;
import com.medscape.android.consult.interfaces.ICommunityUserReceivedListener;
import com.medscape.android.consult.interfaces.IFeedReceivedListener;
import com.medscape.android.consult.interfaces.IGetNotificationPreferencesListener;
import com.medscape.android.consult.interfaces.IInitialConsultInfoReceivedListener;
import com.medscape.android.consult.interfaces.IMentionTokenListenerByID;
import com.medscape.android.consult.interfaces.IPostBodyUpdatedWithMentionsListener;
import com.medscape.android.consult.interfaces.IPostReceivedListener;
import com.medscape.android.consult.interfaces.IPostUploadedListener;
import com.medscape.android.consult.interfaces.ITagsReceivedListener;
import com.medscape.android.consult.interfaces.IUpdateNotificationPreferencesListener;
import com.medscape.android.consult.interfaces.IUploadMediaListener;
import com.medscape.android.consult.models.ConsultComment;
import com.medscape.android.consult.models.ConsultFeed;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultTag;
import com.medscape.android.consult.models.ConsultTimeLineFilterItem;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.models.InitialConsultData;
import com.medscape.android.consult.models.NotificationPreference;
import com.medscape.android.consult.parsers.CommunityResponseParser;
import com.medscape.android.consult.parsers.CommunityUserParser;
import com.medscape.android.consult.parsers.NotificationPreferencesParser;
import com.medscape.android.consult.tasks.GetAdditionalConfigTask;
import com.medscape.android.consult.tasks.GetMedscapeCommunityResponseTask;
import com.medscape.android.consult.tasks.GetMentionTokenByUserID;
import com.medscape.android.consult.tasks.GetNecessaryConsultInfoTask;
import com.medscape.android.consult.tasks.GetTagsTask;
import com.medscape.android.consult.tasks.UpdatePostBodyWithMentionsTask;
import com.medscape.android.consult.tasks.UploadMediaTask;
import com.medscape.android.consult.tasks.UploadProfileImageTask;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.interfaces.IAuthFlowCompletedListener;
import com.medscape.android.interfaces.IHttpRequestCompleteListener;
import com.medscape.android.notifications.INotificationProvider;
import com.medscape.android.util.HttpUtils;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.SerializerUtils;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.welcome.WelcomeActivity;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.wbmd.wbmdcommons.caching.CacheProvider;
import com.wbmd.wbmdcommons.caching.ICacheProvider;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsultDataManager {
    static String TAG = ConsultDataManager.class.getSimpleName();
    private static final String TAG_LIST_CACHE_KEY = "consult_tag_list";
    /* access modifiers changed from: private */
    public static JSONObject consultJsonObject = null;
    public static List<ConsultTimeLineFilterItem> mAdditionalTopLevelFeeds = null;
    /* access modifiers changed from: private */
    public static boolean mAuthUnderProgress = false;
    private static final int mCommentPageSize = 10;
    /* access modifiers changed from: private */
    public static String mComposeDisclaimerText = null;
    /* access modifiers changed from: private */
    public static ConsultUser mCurrentConsultUser = null;
    public static boolean mForceUpdateConfig = true;
    /* access modifiers changed from: private */
    public static List<String> mFullTagList = null;
    /* access modifiers changed from: private */
    public static Calendar mLastTagUpdate = null;
    private static ConsultDataManager mManager = null;
    private static final int mMentionPageSize = 20;
    private static final int mPostsPageSize = 10;
    /* access modifiers changed from: private */
    public static List<String> mSpecialtyTagList = null;
    private static final int mUserPageSize = 10;
    /* access modifiers changed from: private */
    public static String mZimbraForumId;
    /* access modifiers changed from: private */
    public static String mZimbraGroupId;
    /* access modifiers changed from: private */
    public static String mZimbraHiddenForumId;
    /* access modifiers changed from: private */
    public static String mZimbraMediaGalleryId;
    /* access modifiers changed from: private */
    public Activity activity;
    /* access modifiers changed from: private */
    public ConcurrentLinkedQueue<IHttpRequestCompleteListener> mAdditionalConfigCompletedListeners = new ConcurrentLinkedQueue<>();
    /* access modifiers changed from: private */
    public ConcurrentLinkedQueue<IAuthFlowCompletedListener> mAuthCompletedListeners = new ConcurrentLinkedQueue<>();
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public GetAdditionalConfigTask mGetAdditionalConfigTask;
    /* access modifiers changed from: private */
    public GetNecessaryConsultInfoTask mGetNecessaryConsultInfoTask;
    private GetTagsTask mGetTagsTask;
    /* access modifiers changed from: private */
    public ConcurrentLinkedQueue<ITagsReceivedListener> mTagsReceivedListeners = new ConcurrentLinkedQueue<>();

    public static ConsultDataManager getInstance(Context context, Activity activity2) {
        if (mManager == null) {
            mManager = new ConsultDataManager();
            mAuthUnderProgress = false;
        }
        ConsultDataManager consultDataManager = mManager;
        consultDataManager.activity = activity2;
        consultDataManager.setContext(context);
        return mManager;
    }

    private void setContext(Context context) {
        if (context != null) {
            this.mContext = context;
            return;
        }
        Activity activity2 = mManager.activity;
        if (activity2 != null) {
            this.mContext = activity2;
        }
    }

    public void waitForInit(final IAuthFlowCompletedListener iAuthFlowCompletedListener) {
        Trace.i(TAG, "Waiting for init");
        waitForAuth(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultDataManager.this.waitForAdditionalConfig(new IHttpRequestCompleteListener() {
                    public void onHttpRequestFailed(MedscapeException medscapeException) {
                        iAuthFlowCompletedListener.onSuccess();
                    }

                    public void onHttpRequestSucceeded(HttpResponseObject httpResponseObject) {
                        iAuthFlowCompletedListener.onSuccess();
                    }
                });
            }

            public void onFailure(MedscapeException medscapeException) {
                iAuthFlowCompletedListener.onFailure(medscapeException);
            }
        });
    }

    /* access modifiers changed from: private */
    public void waitForAdditionalConfig(IHttpRequestCompleteListener iHttpRequestCompleteListener) {
        Trace.i(TAG, "Waiting for additional config");
        if (mForceUpdateConfig || mAdditionalTopLevelFeeds == null) {
            makeAdditionalConfigCall(iHttpRequestCompleteListener);
        } else {
            iHttpRequestCompleteListener.onHttpRequestSucceeded((HttpResponseObject) null);
        }
    }

    public void makeAdditionalConfigCall(final IHttpRequestCompleteListener iHttpRequestCompleteListener) {
        GetAdditionalConfigTask getAdditionalConfigTask = this.mGetAdditionalConfigTask;
        if (getAdditionalConfigTask == null || getAdditionalConfigTask.getStatus() != AsyncTask.Status.RUNNING) {
            this.mAdditionalConfigCompletedListeners.clear();
            mForceUpdateConfig = false;
            GetAdditionalConfigTask getAdditionalConfigTask2 = new GetAdditionalConfigTask(this.mContext, new IHttpRequestCompleteListener() {
                public void onHttpRequestFailed(MedscapeException medscapeException) {
                    ConsultDataManager.this.mGetAdditionalConfigTask.cancel(true);
                    GetAdditionalConfigTask unused = ConsultDataManager.this.mGetAdditionalConfigTask = null;
                    iHttpRequestCompleteListener.onHttpRequestSucceeded((HttpResponseObject) null);
                    while (!ConsultDataManager.this.mAdditionalConfigCompletedListeners.isEmpty()) {
                        IHttpRequestCompleteListener iHttpRequestCompleteListener = (IHttpRequestCompleteListener) ConsultDataManager.this.mAdditionalConfigCompletedListeners.poll();
                        if (iHttpRequestCompleteListener != null) {
                            Trace.i(ConsultDataManager.TAG, "Additional config request complete, returning to other listeners");
                            iHttpRequestCompleteListener.onHttpRequestSucceeded((HttpResponseObject) null);
                        }
                    }
                }

                public void onHttpRequestSucceeded(HttpResponseObject httpResponseObject) {
                    ConsultDataManager.this.mGetAdditionalConfigTask.cancel(true);
                    GetAdditionalConfigTask unused = ConsultDataManager.this.mGetAdditionalConfigTask = null;
                    if (httpResponseObject != null && StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
                        try {
                            JSONObject jSONObject = new JSONObject(httpResponseObject.getResponseData());
                            String unused2 = ConsultDataManager.mComposeDisclaimerText = jSONObject.optString("composeDisclaimer");
                            ConsultDataManager.mAdditionalTopLevelFeeds = ConsultDataManager.this.getFeedsFromJSON(jSONObject.optJSONArray("topLevelFeeds"), false);
                            JSONArray optJSONArray = jSONObject.optJSONArray("specialtyTags");
                            if (optJSONArray != null) {
                                List unused3 = ConsultDataManager.mSpecialtyTagList = new ArrayList();
                                for (int i = 0; i < optJSONArray.length(); i++) {
                                    ConsultDataManager.mSpecialtyTagList.add(optJSONArray.getString(i));
                                }
                            }
                            LocalBroadcastManager.getInstance(ConsultDataManager.this.mContext).sendBroadcast(new Intent(Constants.CONSULT_ADDITIONAL_CONFIG_UPDATEACTION));
                        } catch (Exception unused4) {
                            Trace.w(ConsultDataManager.TAG, "Failed to parse additional config response");
                        }
                    }
                    iHttpRequestCompleteListener.onHttpRequestSucceeded(httpResponseObject);
                    while (!ConsultDataManager.this.mAdditionalConfigCompletedListeners.isEmpty()) {
                        IHttpRequestCompleteListener iHttpRequestCompleteListener = (IHttpRequestCompleteListener) ConsultDataManager.this.mAdditionalConfigCompletedListeners.poll();
                        if (iHttpRequestCompleteListener != null) {
                            Trace.i(ConsultDataManager.TAG, "Additional config request complete, returning to other listeners");
                            iHttpRequestCompleteListener.onHttpRequestSucceeded(httpResponseObject);
                        }
                    }
                }
            });
            this.mGetAdditionalConfigTask = getAdditionalConfigTask2;
            getAdditionalConfigTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        this.mAdditionalConfigCompletedListeners.add(iHttpRequestCompleteListener);
        Trace.w(TAG, "Added listener for additional config");
    }

    /* access modifiers changed from: private */
    public List<ConsultTimeLineFilterItem> getFeedsFromJSON(JSONArray jSONArray, boolean z) throws Exception {
        JSONArray optJSONArray;
        List<ConsultTimeLineFilterItem> feedsFromJSON;
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String optString = jSONObject.optString("title");
                if (!StringUtil.isNullOrEmpty(optString)) {
                    String optString2 = jSONObject.optString("type");
                    if (!StringUtil.isNullOrEmpty(optString2)) {
                        if (!jSONObject.has("children") || jSONObject.isNull("children")) {
                            String optString3 = jSONObject.optString(com.wbmd.wbmddrugscommons.constants.Constants.WBMDDrugKeyTag);
                            ConsultTimeLineFilterItem consultTimeLineFilterItem = new ConsultTimeLineFilterItem();
                            consultTimeLineFilterItem.setTitle(optString);
                            consultTimeLineFilterItem.setItemType(Constants.CONSULT_FILTERTYPE_TAG);
                            consultTimeLineFilterItem.setTag(optString3);
                            consultTimeLineFilterItem.setFilterType(optString2);
                            arrayList.add(consultTimeLineFilterItem);
                        } else if ("folder".equalsIgnoreCase(optString2) && !z && (optJSONArray = jSONObject.optJSONArray("children")) != null && optJSONArray.length() > 0 && (feedsFromJSON = getFeedsFromJSON(optJSONArray, true)) != null && feedsFromJSON.size() > 0) {
                            ConsultTimeLineFilterItem consultTimeLineFilterItem2 = new ConsultTimeLineFilterItem();
                            consultTimeLineFilterItem2.setTitle(optString);
                            consultTimeLineFilterItem2.setItemType(Constants.CONSULT_FILTERTYPE_FOLDER);
                            consultTimeLineFilterItem2.setChildFilters(feedsFromJSON);
                            arrayList.add(consultTimeLineFilterItem2);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private void waitForAuth(final IAuthFlowCompletedListener iAuthFlowCompletedListener) {
        Trace.i(TAG, "Waiting for initial data");
        boolean z = AuthenticationManager.getInstance(this.mContext).getAuthStatus() != 3011;
        if (mCurrentConsultUser == null || z) {
            GetNecessaryConsultInfoTask getNecessaryConsultInfoTask = this.mGetNecessaryConsultInfoTask;
            if ((getNecessaryConsultInfoTask == null || getNecessaryConsultInfoTask.getStatus() != AsyncTask.Status.RUNNING) && !mAuthUnderProgress) {
                mCurrentConsultUser = null;
                this.mAuthCompletedListeners.clear();
                GetNecessaryConsultInfoTask getNecessaryConsultInfoTask2 = new GetNecessaryConsultInfoTask(this.mContext, new IInitialConsultInfoReceivedListener() {
                    public void onInitialConsultInfoReceived(InitialConsultData initialConsultData) {
                        ConsultDataManager.this.mGetNecessaryConsultInfoTask.cancel(true);
                        GetNecessaryConsultInfoTask unused = ConsultDataManager.this.mGetNecessaryConsultInfoTask = null;
                        if (initialConsultData != null) {
                            ConsultUser unused2 = ConsultDataManager.mCurrentConsultUser = initialConsultData.getConsultUser();
                            if (ConsultDataManager.mCurrentConsultUser != null) {
                                LocalBroadcastManager.getInstance(ConsultDataManager.this.mContext).sendBroadcast(new Intent(Constants.CONSULT_CURRENT_USER_UPDATEACTION));
                            }
                            if (initialConsultData.getZimbraConfigResponse() != null) {
                                String unused3 = ConsultDataManager.mZimbraGroupId = initialConsultData.getZimbraConfigResponse().getGroupId();
                                String unused4 = ConsultDataManager.mZimbraForumId = initialConsultData.getZimbraConfigResponse().getForumId();
                                String unused5 = ConsultDataManager.mZimbraMediaGalleryId = initialConsultData.getZimbraConfigResponse().getMediaGalleryId();
                                String unused6 = ConsultDataManager.mZimbraHiddenForumId = initialConsultData.getZimbraConfigResponse().getHiddenForumId();
                            } else {
                                Trace.w(ConsultDataManager.TAG, "Initial content returned null data from zimbra");
                            }
                            Trace.i(ConsultDataManager.TAG, "Received initial data, returning");
                            iAuthFlowCompletedListener.onSuccess();
                            while (!ConsultDataManager.this.mAuthCompletedListeners.isEmpty()) {
                                IAuthFlowCompletedListener iAuthFlowCompletedListener = (IAuthFlowCompletedListener) ConsultDataManager.this.mAuthCompletedListeners.poll();
                                if (iAuthFlowCompletedListener != null) {
                                    Trace.i(ConsultDataManager.TAG, "Received initial data, returning");
                                    iAuthFlowCompletedListener.onSuccess();
                                }
                            }
                            return;
                        }
                        Trace.w(ConsultDataManager.TAG, "Initial data returned null");
                    }

                    public void onFailedToReceiveInitialConsultInfo(MedscapeException medscapeException) {
                        ConsultDataManager.this.mGetNecessaryConsultInfoTask.cancel(true);
                        ConsultDataManager.this.waitforAuthFailed(iAuthFlowCompletedListener, medscapeException);
                    }
                });
                this.mGetNecessaryConsultInfoTask = getNecessaryConsultInfoTask2;
                if (!z) {
                    getNecessaryConsultInfoTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    return;
                }
                mAuthUnderProgress = true;
                AccountProvider.signIn(this.mContext, new ICallbackEvent<Object, Exception>() {
                    public void onError(Exception exc) {
                        if ((exc.getCause() instanceof AuthFailureError) || (exc.getCause() instanceof ParseError)) {
                            ConsultDataManager.this.waitforAuthFailed(iAuthFlowCompletedListener, new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), "Authentication Failed"));
                        } else if (AccountProvider.isUserLoggedIn(ConsultDataManager.this.mContext)) {
                            ConsultDataManager.this.mGetNecessaryConsultInfoTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                        } else {
                            Intent intent = new Intent(ConsultDataManager.this.mContext, WelcomeActivity.class);
                            intent.putExtra(Constants.EXTRA_FORCE_UP, true);
                            ConsultDataManager.this.mContext.startActivity(intent);
                        }
                        boolean unused = ConsultDataManager.mAuthUnderProgress = false;
                    }

                    public void onCompleted(Object obj) {
                        int parseResponse = OAuthResponseParser.parseResponse(ConsultDataManager.this.mContext, obj, ConsultDataManager.this.activity);
                        AuthenticationManager.getInstance(ConsultDataManager.this.mContext).setAuthStatus(parseResponse);
                        if (parseResponse != 3011) {
                            ConsultDataManager.this.waitforAuthFailed(iAuthFlowCompletedListener, new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), "Authentication Failed"));
                        } else {
                            ConsultDataManager.this.mGetNecessaryConsultInfoTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                        }
                        boolean unused = ConsultDataManager.mAuthUnderProgress = false;
                    }
                });
                return;
            }
            this.mAuthCompletedListeners.add(iAuthFlowCompletedListener);
            Trace.w(TAG, "Added listener for additional config");
            return;
        }
        Trace.i(TAG, "Already had initial data. Returning");
        iAuthFlowCompletedListener.onSuccess();
    }

    public void getFullProfileForUser(final Context context, final String str, final ICommunityUserReceivedListener iCommunityUserReceivedListener) {
        if (StringUtil.isNotEmpty(str)) {
            waitForInit(new IAuthFlowCompletedListener() {
                public void onSuccess() {
                    String profileUrl = ConsultUrls.getProfileUrl(context);
                    HashMap hashMap = new HashMap();
                    hashMap.put(com.webmd.wbmdcmepulse.models.utils.Constants.USER_TAG_KEY_USER_ID, str);
                    hashMap.put("groupId", ConsultDataManager.mZimbraGroupId);
                    hashMap.put("forumId", ConsultDataManager.mZimbraForumId);
                    String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(profileUrl, hashMap);
                    HttpRequestObject httpRequestObject = new HttpRequestObject();
                    httpRequestObject.setRequestMethod("GET");
                    httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                    httpRequestObject.setUrl(urlFromUrlWithMap);
                    httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
                    ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                        public void onCommunityDataReceived(JSONObject jSONObject) {
                            if (iCommunityUserReceivedListener == null) {
                                Trace.w(ConsultDataManager.TAG, "Listener for full profile was null!");
                            } else if (jSONObject != null) {
                                Trace.i(ConsultDataManager.TAG, "Received community response from server for full profile");
                                ConsultUser parseCommunityUserResponseFromJson = CommunityUserParser.parseCommunityUserResponseFromJson(jSONObject);
                                if (parseCommunityUserResponseFromJson != null) {
                                    Trace.i(ConsultDataManager.TAG, "Calling back with user data");
                                    iCommunityUserReceivedListener.onCurrentUserReceived(parseCommunityUserResponseFromJson);
                                    return;
                                }
                                iCommunityUserReceivedListener.onFailedToReceiveCurrentUser(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                            } else {
                                iCommunityUserReceivedListener.onFailedToReceiveCurrentUser(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_full_profile_fail)));
                            }
                        }

                        public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                            if (iCommunityUserReceivedListener != null) {
                                iCommunityUserReceivedListener.onFailedToReceiveCurrentUser(medscapeException);
                            } else {
                                Trace.w(ConsultDataManager.TAG, "Listener for full profile was null!");
                            }
                        }
                    }, false);
                }

                public void onFailure(MedscapeException medscapeException) {
                    ICommunityUserReceivedListener iCommunityUserReceivedListener = iCommunityUserReceivedListener;
                    if (iCommunityUserReceivedListener != null) {
                        iCommunityUserReceivedListener.onFailedToReceiveCurrentUser(medscapeException);
                    }
                }
            });
        } else if (iCommunityUserReceivedListener != null) {
            iCommunityUserReceivedListener.onFailedToReceiveCurrentUser(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_missing_userid)));
        }
    }

    public void getMyNetworkPostswithCurrentFeed(ConsultFeed consultFeed, IFeedReceivedListener iFeedReceivedListener) {
        HashMap hashMap;
        Calendar instance = Calendar.getInstance();
        if (!(consultFeed == null || consultFeed.getFeedItems() == null || consultFeed.getFeedItems().size() <= 0)) {
            ConsultFeedItem consultFeedItem = consultFeed.getFeedItems().get(consultFeed.getFeedItems().size() - 1);
            if ((consultFeedItem instanceof ConsultPost) && (instance = ((ConsultPost) consultFeedItem).getTimestamp()) == null) {
                instance = Calendar.getInstance();
            }
        }
        String zimbraTimeStampFromCalendar = getZimbraTimeStampFromCalendar(instance);
        if (StringUtil.isNotEmpty(zimbraTimeStampFromCalendar)) {
            hashMap = new HashMap();
            hashMap.put("EndDate", zimbraTimeStampFromCalendar);
        } else {
            hashMap = null;
        }
        getMyNetworkPostsFeedsWithMap(hashMap, consultFeed, iFeedReceivedListener);
    }

    private void getMyNetworkPostsFeedsWithMap(final Map<String, String> map, final ConsultFeed consultFeed, final IFeedReceivedListener iFeedReceivedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultDataManager.this.performMedscapeCommunityRequest(ConsultUrls.getMyNetworkFeedUrl(ConsultDataManager.this.mContext), map, consultFeed, iFeedReceivedListener, 3000, (String) null);
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, 3000, (String) null);
            }
        });
    }

    public String getISOTime() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'").format(new Date());
    }

    public String getZimbraTimeStampFromCalendar(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            String format = simpleDateFormat.format(calendar.getTime());
            if (!StringUtil.isNotEmpty(format)) {
                return format;
            }
            if (format.contains("Z")) {
                int lastIndexOf = format.lastIndexOf("Z");
                format = format.substring(0, lastIndexOf) + "-00:00" + format.substring(lastIndexOf + 1);
            }
            if (format.contains("+")) {
                return format.replace("+", "-");
            }
            return format;
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to parse date for server request");
            return null;
        }
    }

    public void getPostswithCurrentFeedForUser(final ConsultFeed consultFeed, final IFeedReceivedListener iFeedReceivedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                if (ConsultDataManager.mCurrentConsultUser == null || !StringUtil.isNotEmpty(ConsultDataManager.mCurrentConsultUser.getUserId())) {
                    Trace.w(ConsultDataManager.TAG, "Missing user id");
                    iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_userid)), 3002, (String) null);
                    return;
                }
                HashMap hashMap = new HashMap();
                hashMap.put(INotificationProvider.USER_TAG_USER_ID, ConsultDataManager.mCurrentConsultUser.getUserId());
                hashMap.put("SortBy", HttpHeaders.DATE);
                hashMap.put("SortOrder", "Descending");
                ConsultDataManager.this.performMedscapeCommunityRequest(ConsultUrls.getMyPostsFeedUrl(ConsultDataManager.this.mContext), hashMap, consultFeed, iFeedReceivedListener, 3002, (String) null);
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, 3002, (String) null);
            }
        });
    }

    public void getFollowedPostsWithCurrentFeed(ConsultFeed consultFeed, IFeedReceivedListener iFeedReceivedListener) {
        getFollowedPostsFeedsWithMap((Map<String, String>) null, consultFeed, iFeedReceivedListener);
    }

    private void getFollowedPostsFeedsWithMap(final Map<String, String> map, final ConsultFeed consultFeed, final IFeedReceivedListener iFeedReceivedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultDataManager.this.performMedscapeCommunityRequest(ConsultUrls.getFollowedPostsUrl(ConsultDataManager.this.mContext), map, consultFeed, iFeedReceivedListener, Constants.CONSULT_FEEDTYPE_FOLLOWED_POSTS, (String) null);
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, Constants.CONSULT_FEEDTYPE_FOLLOWED_POSTS, (String) null);
            }
        });
    }

    public boolean isSponsorPostOmnitureEligible(ConsultUser consultUser, int i) {
        return (i == 3001 || i == 3006) && ConsultUtils.isSponsoredUser(consultUser);
    }

    public void getAllPostsWithCurrentFeed(ConsultFeed consultFeed, String str, IFeedReceivedListener iFeedReceivedListener) {
        HashMap hashMap = new HashMap();
        hashMap.put("SortBy", HttpHeaders.DATE);
        hashMap.put("SortOrder", "Descending");
        if (str != null) {
            hashMap.put(ContentParser.TAGS, str);
        }
        getTopFeedsWithMap(hashMap, consultFeed, iFeedReceivedListener, Constants.CONSULT_FEEDTYPE_ALL, (String) null);
    }

    public void getTopPostsWithCurrentFeed(ConsultFeed consultFeed, IFeedReceivedListener iFeedReceivedListener) {
        HashMap hashMap = new HashMap();
        hashMap.put("SortBy", "HotRankingScore");
        hashMap.put("SortOrder", "Descending");
        getTopFeedsWithMap(hashMap, consultFeed, iFeedReceivedListener, Constants.CONSULT_FEEDTYPE_TOP_POSTS, (String) null);
    }

    public void getPostsForTag(String str, String str2, ConsultFeed consultFeed, int i, IFeedReceivedListener iFeedReceivedListener) {
        try {
            String encode = URLEncoder.encode(str, "UTF-8");
            if (str2 != null && !str2.isEmpty()) {
                encode = encode + "," + str2;
            }
            HashMap hashMap = new HashMap();
            hashMap.put(ContentParser.TAGS, encode);
            getTopFeedsWithMap(hashMap, consultFeed, iFeedReceivedListener, i, str);
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to get posts for tag");
            iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_failed_requesting)), i, str);
        }
    }

    private void getTopFeedsWithMap(Map<String, String> map, ConsultFeed consultFeed, IFeedReceivedListener iFeedReceivedListener, int i, String str) {
        final Map<String, String> map2 = map;
        final ConsultFeed consultFeed2 = consultFeed;
        final IFeedReceivedListener iFeedReceivedListener2 = iFeedReceivedListener;
        final int i2 = i;
        final String str2 = str;
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultDataManager.this.performMedscapeCommunityRequest(ConsultUrls.getTopFeedsUrl(ConsultDataManager.this.mContext), map2, consultFeed2, iFeedReceivedListener2, i2, str2);
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener2.onFailedToReceiveFeed(medscapeException, i2, str2);
            }
        });
    }

    /* access modifiers changed from: private */
    public void performMedscapeCommunityRequest(String str, Map<String, String> map, ConsultFeed consultFeed, IFeedReceivedListener iFeedReceivedListener, int i, String str2) {
        final int currentPageIndex = consultFeed != null ? consultFeed.getCurrentPageIndex() + 1 : 0;
        HashMap hashMap = new HashMap();
        hashMap.put(Constants.ZIMBRA_GROUP_ID, mZimbraGroupId);
        hashMap.put(Constants.ZIMBRA_FORUM_ID, mZimbraForumId);
        hashMap.put("PageSize", UserProfile.PHYSICIAN_ID);
        hashMap.put("PageIndex", "" + currentPageIndex);
        String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(str, hashMap);
        if (map != null) {
            urlFromUrlWithMap = Util.getUrlFromUrlWithMap(urlFromUrlWithMap, map);
        }
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setRequestMethod("GET");
        httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
        httpRequestObject.setUrl(urlFromUrlWithMap);
        httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
        final ConsultFeed consultFeed2 = consultFeed;
        final int i2 = i;
        final IFeedReceivedListener iFeedReceivedListener2 = iFeedReceivedListener;
        final String str3 = str2;
        getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
            public void onCommunityDataReceived(JSONObject jSONObject) {
                ConsultFeed access$1900 = ConsultDataManager.this.getPostsFeedFromServerResponse(jSONObject, consultFeed2, currentPageIndex, i2);
                if (access$1900 != null) {
                    iFeedReceivedListener2.onFeedReceived(access$1900, i2, str3);
                    return;
                }
                iFeedReceivedListener2.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_request_failed)), i2, str3);
            }

            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                iFeedReceivedListener2.onFailedToReceiveFeed(medscapeException, i2, str3);
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void getMapFromMedscapeCommunityRequest(HttpRequestObject httpRequestObject, final ICommunityDataReceivedListener iCommunityDataReceivedListener, boolean z) {
        Trace.i(TAG, "Calling get raw json");
        getRawJsonMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityRequestCompleteListener() {
            public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                Trace.i(ConsultDataManager.TAG, "Community request complete for raw json");
                if (httpResponseObject != null && StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
                    try {
                        iCommunityDataReceivedListener.onCommunityDataReceived(new JSONObject(httpResponseObject.getResponseData()).optJSONObject("data"));
                    } catch (Exception unused) {
                        if (iCommunityDataReceivedListener != null) {
                            iCommunityDataReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                        }
                    }
                } else if (iCommunityDataReceivedListener != null) {
                    iCommunityDataReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                }
            }

            public void onCommunityRequestFailed(MedscapeException medscapeException) {
                iCommunityDataReceivedListener.onFailedToReceiveCommunityData(medscapeException);
            }
        }, z);
    }

    /* access modifiers changed from: private */
    public void getDataFromMedscapeCommunityRequest(HttpRequestObject httpRequestObject, final ICommunityDataReceivedListener iCommunityDataReceivedListener, boolean z) {
        Trace.i(TAG, "Calling get raw json");
        getRawJsonMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityRequestCompleteListener() {
            public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                Trace.i(ConsultDataManager.TAG, "Community request complete for raw json");
                if (httpResponseObject != null && StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
                    try {
                        iCommunityDataReceivedListener.onCommunityDataReceived(new JSONObject(httpResponseObject.getResponseData()));
                    } catch (Exception unused) {
                        if (iCommunityDataReceivedListener != null) {
                            iCommunityDataReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                        }
                    }
                } else if (iCommunityDataReceivedListener != null) {
                    iCommunityDataReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                }
            }

            public void onCommunityRequestFailed(MedscapeException medscapeException) {
                iCommunityDataReceivedListener.onFailedToReceiveCommunityData(medscapeException);
            }
        }, z);
    }

    /* access modifiers changed from: private */
    public void getArrayFromMedscapeCommunityRequest(final HttpRequestObject httpRequestObject, final ICommunityArrayReceivedListener iCommunityArrayReceivedListener, boolean z) {
        getRawJsonMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityRequestCompleteListener() {
            public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                if (httpRequestObject != null) {
                    String responseData = httpResponseObject.getResponseData();
                    if (StringUtil.isNotEmpty(responseData)) {
                        try {
                            JSONArray optJSONArray = new JSONObject(responseData).optJSONArray("data");
                            if (optJSONArray != null) {
                                iCommunityArrayReceivedListener.onCommunityDataReceived(optJSONArray);
                            }
                        } catch (Exception unused) {
                            Trace.w(ConsultDataManager.TAG, "Failed to parse response data");
                            iCommunityArrayReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                        }
                    } else {
                        iCommunityArrayReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                    }
                }
            }

            public void onCommunityRequestFailed(MedscapeException medscapeException) {
                iCommunityArrayReceivedListener.onFailedToReceiveCommunityData(medscapeException);
            }
        }, z);
    }

    /* access modifiers changed from: private */
    public void getRawJsonMapFromMedscapeCommunityRequest(final HttpRequestObject httpRequestObject, final ICommunityRequestCompleteListener iCommunityRequestCompleteListener, boolean z) {
        Trace.i(TAG, "Calling get http response request");
        getHttpResponseFromMedscapeCommunityRequest(httpRequestObject, new ICommunityRequestCompleteListener() {
            public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                Trace.i(ConsultDataManager.TAG, "Community request complete for http response request");
                ConsultDataManager.this.handleResponseFromMedscapeCommunityRequest(httpRequestObject, httpResponseObject, iCommunityRequestCompleteListener);
            }

            public void onCommunityRequestFailed(MedscapeException medscapeException) {
                iCommunityRequestCompleteListener.onCommunityRequestFailed(medscapeException);
            }
        }, z);
    }

    /* access modifiers changed from: private */
    public void handleResponseFromMedscapeCommunityRequest(HttpRequestObject httpRequestObject, HttpResponseObject httpResponseObject, ICommunityRequestCompleteListener iCommunityRequestCompleteListener) {
        AuthenticationManager instance;
        try {
            Trace.i(TAG, "Handling response for community request");
            if (StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
                JSONObject jSONObject = new JSONObject(httpResponseObject.getResponseData());
                int optInt = jSONObject.optInt("StatusCode");
                if (jSONObject.optInt("code") == -1205 && (instance = AuthenticationManager.getInstance(this.mContext)) != null) {
                    if (instance.refreshAuthToken(this.mContext)) {
                        httpRequestObject.setAuthorization(instance.getAuthenticationToken());
                        httpResponseObject = HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
                        if (StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
                            Trace.i(TAG, "Updating status code and code");
                            JSONObject jSONObject2 = new JSONObject(httpResponseObject.getResponseData());
                            optInt = jSONObject2.optInt("StatusCode");
                            jSONObject2.optInt("code");
                        }
                    } else {
                        iCommunityRequestCompleteListener.onCommunityRequestFailed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_session_expired)));
                        instance.resetAuthenticationStatus();
                        return;
                    }
                }
                if (optInt != 200) {
                    Trace.i(TAG, "Community error");
                    if (!StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
                        httpResponseObject.setResponseErrorMsg(this.mContext.getString(R.string.service_unavailable));
                    }
                    iCommunityRequestCompleteListener.onCommunityRequestFailed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), httpResponseObject.getResponseErrorMsg()));
                    return;
                }
                Trace.i(TAG, "Community success");
                iCommunityRequestCompleteListener.onCommunityRequestComplete(httpResponseObject);
                return;
            }
            Trace.i(TAG, "Community response was empty");
            if (!StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
                httpResponseObject.setResponseErrorMsg(this.mContext.getString(R.string.consult_error_failed_parsing));
            }
            iCommunityRequestCompleteListener.onCommunityRequestFailed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), httpResponseObject.getResponseErrorMsg()));
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to parse server response from community");
            if (!StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
                httpResponseObject.setResponseErrorMsg(this.mContext.getString(R.string.consult_error_failed_parsing));
            }
            iCommunityRequestCompleteListener.onCommunityRequestFailed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), httpResponseObject.getResponseErrorMsg()));
        }
    }

    /* access modifiers changed from: private */
    public void getHttpResponseFromMedscapeCommunityRequest(final HttpRequestObject httpRequestObject, final ICommunityRequestCompleteListener iCommunityRequestCompleteListener, final boolean z) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                Trace.i(ConsultDataManager.TAG, "Getting medscape community response from the server");
                String str = ConsultDataManager.TAG;
                Trace.i(str, "Making call to " + httpRequestObject.getUrl());
                GetMedscapeCommunityResponseTask getMedscapeCommunityResponseTask = new GetMedscapeCommunityResponseTask(ConsultDataManager.this.mContext, new ICommunityRequestCompleteListener() {
                    public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                        String str = ConsultDataManager.TAG;
                        Trace.i(str, "Community request complete for " + httpRequestObject.getUrl());
                        iCommunityRequestCompleteListener.onCommunityRequestComplete(httpResponseObject);
                    }

                    public void onCommunityRequestFailed(MedscapeException medscapeException) {
                        Trace.i(ConsultDataManager.TAG, "Exception during community request");
                        iCommunityRequestCompleteListener.onCommunityRequestFailed(medscapeException);
                    }
                }, httpRequestObject);
                if (z) {
                    getMedscapeCommunityResponseTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } else {
                    getMedscapeCommunityResponseTask.execute(new Void[0]);
                }
            }

            public void onFailure(MedscapeException medscapeException) {
                iCommunityRequestCompleteListener.onCommunityRequestFailed(medscapeException);
            }
        });
    }

    public void updateProfileImage(Context context, Bitmap bitmap, IHttpRequestCompleteListener iHttpRequestCompleteListener) {
        new UploadProfileImageTask(context, bitmap, iHttpRequestCompleteListener).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void getPostsForUser(final ConsultUser consultUser, final ConsultFeed consultFeed, final IFeedReceivedListener iFeedReceivedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultUser consultUser = consultUser;
                if (consultUser == null || !StringUtil.isNotEmpty(consultUser.getUserId())) {
                    Trace.w(ConsultDataManager.TAG, "Missing user id");
                    iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_userid)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                    return;
                }
                ConsultFeed consultFeed = consultFeed;
                final int currentPageIndex = consultFeed != null ? consultFeed.getCurrentPageIndex() + 1 : 0;
                String postsByUserUrl = ConsultUrls.getPostsByUserUrl(ConsultDataManager.this.mContext);
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.ZIMBRA_GROUP_ID, ConsultDataManager.mZimbraGroupId);
                hashMap.put(Constants.ZIMBRA_FORUM_ID, ConsultDataManager.mZimbraForumId);
                hashMap.put("PageSize", UserProfile.PHYSICIAN_ID);
                hashMap.put("PageIndex", "" + currentPageIndex);
                hashMap.put(com.webmd.wbmdcmepulse.models.utils.Constants.USER_TAG_KEY_USER_ID, consultUser.getUserId());
                hashMap.put("SortBy", HttpHeaders.DATE);
                hashMap.put("SortOrder", "Descending");
                String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(postsByUserUrl, hashMap);
                HttpRequestObject httpRequestObject = new HttpRequestObject();
                httpRequestObject.setRequestMethod("GET");
                httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                httpRequestObject.setUrl(urlFromUrlWithMap);
                ConsultDataManager.this.getHttpResponseFromMedscapeCommunityRequest(httpRequestObject, new ICommunityRequestCompleteListener() {
                    public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                        if (httpResponseObject == null || !StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
                            iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                            return;
                        }
                        try {
                            ConsultFeed access$1900 = ConsultDataManager.this.getPostsFeedFromServerResponse(new JSONObject(httpResponseObject.getResponseData()).optJSONObject("data"), consultFeed, currentPageIndex, -1);
                            if (access$1900 != null) {
                                iFeedReceivedListener.onFeedReceived(access$1900, Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                                return;
                            }
                            Trace.w(ConsultDataManager.TAG, "New feed was null");
                            iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                        } catch (Exception unused) {
                            Trace.w(ConsultDataManager.TAG, "Failed to parse posts response");
                            iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                        }
                    }

                    public void onCommunityRequestFailed(MedscapeException medscapeException) {
                        iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                    }
                }, false);
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, -1, (String) null);
            }
        });
    }

    public void getUsersFollowedByUser(ConsultUser consultUser, ConsultFeed consultFeed, IFeedReceivedListener iFeedReceivedListener) {
        getUsersForProfileRequest(consultUser, consultFeed, ConsultUrls.getFollowingUrl(this.mContext), iFeedReceivedListener);
    }

    public void getUsersFollowingUser(ConsultUser consultUser, ConsultFeed consultFeed, IFeedReceivedListener iFeedReceivedListener) {
        getUsersForProfileRequest(consultUser, consultFeed, ConsultUrls.getFollowersUrl(this.mContext), iFeedReceivedListener);
    }

    public void getResponsesForUser(ConsultUser consultUser, ConsultFeed consultFeed, IFeedReceivedListener iFeedReceivedListener) {
        ConsultUrls.getResponsesUrl(this.mContext);
        getUserResponsesRequest(consultUser, consultFeed, iFeedReceivedListener);
    }

    public void getUserResponsesRequest(final ConsultUser consultUser, final ConsultFeed consultFeed, final IFeedReceivedListener iFeedReceivedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultUser consultUser = consultUser;
                if (consultUser == null || !StringUtil.isNotEmpty(consultUser.getUserId())) {
                    Trace.w(ConsultDataManager.TAG, "Missing user id");
                    iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_userid)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                    return;
                }
                ConsultFeed consultFeed = consultFeed;
                final int currentPageIndex = consultFeed != null ? consultFeed.getCurrentPageIndex() + 1 : 0;
                String responsesUrl = ConsultUrls.getResponsesUrl(ConsultDataManager.this.mContext);
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.ZIMBRA_GROUP_ID, ConsultDataManager.mZimbraGroupId);
                hashMap.put(Constants.ZIMBRA_FORUM_ID, ConsultDataManager.mZimbraForumId);
                hashMap.put("PageSize", UserProfile.PHYSICIAN_ID);
                hashMap.put("PageIndex", "" + currentPageIndex);
                hashMap.put("userName", consultUser.getUserId());
                String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(responsesUrl, hashMap);
                HttpRequestObject httpRequestObject = new HttpRequestObject();
                httpRequestObject.setRequestMethod("GET");
                httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                httpRequestObject.setUrl(urlFromUrlWithMap);
                ConsultDataManager.this.getHttpResponseFromMedscapeCommunityRequest(httpRequestObject, new ICommunityRequestCompleteListener() {
                    public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                        if (httpResponseObject == null || !StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
                            iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                            return;
                        }
                        try {
                            ConsultFeed access$2200 = ConsultDataManager.this.getUsersResponsesFeedFromServerResponse(new JSONObject(httpResponseObject.getResponseData()).optJSONObject("data"), "Items", consultFeed, currentPageIndex);
                            if (access$2200 != null) {
                                iFeedReceivedListener.onFeedReceived(access$2200, Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                                return;
                            }
                            Trace.w(ConsultDataManager.TAG, "New feed was null");
                            iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                        } catch (Exception unused) {
                            Trace.w(ConsultDataManager.TAG, "Failed to parse posts response");
                            iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                        }
                    }

                    public void onCommunityRequestFailed(MedscapeException medscapeException) {
                        iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                    }
                }, false);
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, -1, (String) null);
            }
        });
    }

    private void getUsersForProfileRequest(ConsultUser consultUser, final ConsultFeed consultFeed, String str, final IFeedReceivedListener iFeedReceivedListener) {
        if (consultUser == null || !StringUtil.isNotEmpty(consultUser.getUserId())) {
            Trace.w(TAG, "Missing user id");
            iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_missing_userid)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
            return;
        }
        final int currentPageIndex = consultFeed != null ? consultFeed.getCurrentPageIndex() + 1 : 0;
        HashMap hashMap = new HashMap();
        hashMap.put("Username", consultUser.getUserId());
        hashMap.put("SortBy", Constants.CONSULT_USER_DISPLAY_NAME);
        hashMap.put("SortOrder", "Descending");
        hashMap.put("PageSize", UserProfile.PHYSICIAN_ID);
        hashMap.put("PageIndex", "" + currentPageIndex);
        String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(str, hashMap);
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setRequestMethod("GET");
        httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
        httpRequestObject.setUrl(urlFromUrlWithMap);
        getHttpResponseFromMedscapeCommunityRequest(httpRequestObject, new ICommunityRequestCompleteListener() {
            public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                if (httpResponseObject == null || !StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
                    iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                    return;
                }
                try {
                    ConsultFeed access$2300 = ConsultDataManager.this.getUsersFeedFromServerResponse(new JSONObject(httpResponseObject.getResponseData()).optJSONObject("data"), "Users", consultFeed, currentPageIndex);
                    if (access$2300 != null) {
                        iFeedReceivedListener.onFeedReceived(access$2300, Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                        return;
                    }
                    Trace.w(ConsultDataManager.TAG, "New feed was null");
                    iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                } catch (Exception unused) {
                    Trace.w(ConsultDataManager.TAG, "Failed to parse posts response");
                    iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
                }
            }

            public void onCommunityRequestFailed(MedscapeException medscapeException) {
                iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, Constants.CONSULT_FEEDTYPE_PROFILE, (String) null);
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public ConsultFeed getPostsFeedFromServerResponse(JSONObject jSONObject, ConsultFeed consultFeed, int i, int i2) {
        if (jSONObject == null) {
            return null;
        }
        int optInt = jSONObject.optInt("TotalCount");
        JSONArray optJSONArray = jSONObject.optJSONArray("Items");
        if (optJSONArray == null) {
            return new ConsultFeed();
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
            ConsultPost parseCommunityResponse = CommunityResponseParser.parseCommunityResponse(optJSONArray.optJSONObject(i3), mZimbraHiddenForumId);
            if (parseCommunityResponse != null) {
                ConsultUser poster = parseCommunityResponse.getPoster();
                if (poster != null) {
                    poster.setCanShowSponsoredLabel(true);
                    if (isSponsorPostOmnitureEligible(poster, i2)) {
                        OmnitureManager.get().trackModule(this.activity, "consult", "consult-spsr", "fdimp", (Map<String, Object>) null);
                    }
                }
                arrayList.add(parseCommunityResponse);
            }
        }
        if (consultFeed == null) {
            consultFeed = new ConsultFeed();
        }
        consultFeed.addItemsToEnd(arrayList);
        consultFeed.setTotalItems(optInt);
        consultFeed.setCurrentPageIndex(i);
        return consultFeed;
    }

    /* access modifiers changed from: private */
    public ConsultFeed getUsersFeedFromServerResponse(JSONObject jSONObject, String str, ConsultFeed consultFeed, int i) {
        if (jSONObject == null) {
            return null;
        }
        int optInt = jSONObject.optInt("TotalCount");
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return new ConsultFeed();
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            ConsultUser parseCommunityUserResponseFromJson = CommunityUserParser.parseCommunityUserResponseFromJson(optJSONArray.optJSONObject(i2));
            if (parseCommunityUserResponseFromJson != null) {
                arrayList.add(parseCommunityUserResponseFromJson);
            }
        }
        if (consultFeed == null) {
            consultFeed = new ConsultFeed();
        }
        consultFeed.addItemsToEnd(arrayList);
        consultFeed.setTotalItems(optInt);
        consultFeed.setCurrentPageIndex(i);
        return consultFeed;
    }

    /* access modifiers changed from: private */
    public ConsultFeed getUsersResponsesFeedFromServerResponse(JSONObject jSONObject, String str, ConsultFeed consultFeed, int i) {
        if (jSONObject == null) {
            return null;
        }
        int optInt = jSONObject.optInt("TotalCount");
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return new ConsultFeed();
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            ConsultPost parseCommunityResponse = CommunityResponseParser.parseCommunityResponse(optJSONArray.optJSONObject(i2), "");
            if (parseCommunityResponse != null) {
                parseCommunityResponse.setIsRepliesByUser(true);
                arrayList.add(parseCommunityResponse);
            }
        }
        if (consultFeed == null) {
            consultFeed = new ConsultFeed();
        }
        consultFeed.addItemsToEnd(arrayList);
        consultFeed.setTotalItems(optInt);
        consultFeed.setCurrentPageIndex(i);
        return consultFeed;
    }

    public void updateProfile(final Context context, String str, final IHttpRequestCompleteListener iHttpRequestCompleteListener) {
        String uploadProfileUrl = ConsultUrls.getUploadProfileUrl(context);
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setUrl(uploadProfileUrl);
        httpRequestObject.setAuthorization(AuthenticationManager.getInstance(context).getAuthenticationToken());
        httpRequestObject.setRequestMethod("POST");
        if (str != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(Constants.CONSULT_USER_BIO, str);
            String postDataFromMap = getPostDataFromMap(hashMap);
            if (StringUtil.isNotEmpty(postDataFromMap)) {
                httpRequestObject.setRequestBody(postDataFromMap);
            }
        }
        getHttpResponseFromMedscapeCommunityRequest(httpRequestObject, new ICommunityRequestCompleteListener() {
            public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                if (httpResponseObject != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(httpResponseObject.getResponseData());
                        int optInt = jSONObject.optInt("StatusCode");
                        int optInt2 = jSONObject.optInt("code");
                        if (optInt == 200 && optInt2 == 1) {
                            iHttpRequestCompleteListener.onHttpRequestSucceeded(httpResponseObject);
                        } else {
                            ConsultDataManager.this.returnUpdateProfileError(context, iHttpRequestCompleteListener);
                        }
                    } catch (Exception unused) {
                        Trace.w(ConsultDataManager.TAG, "Failed to parse response after updating profile");
                        ConsultDataManager.this.returnUpdateProfileError(context, iHttpRequestCompleteListener);
                    }
                } else {
                    iHttpRequestCompleteListener.onHttpRequestFailed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), context.getString(R.string.consult_error_failed_parsing)));
                }
            }

            public void onCommunityRequestFailed(MedscapeException medscapeException) {
                iHttpRequestCompleteListener.onHttpRequestFailed(medscapeException);
            }
        }, false);
    }

    /* access modifiers changed from: private */
    public void returnUpdateProfileError(Context context, IHttpRequestCompleteListener iHttpRequestCompleteListener) {
        iHttpRequestCompleteListener.onHttpRequestFailed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), context.getString(R.string.error_updating_profile)));
    }

    public String getPostDataFromMap(Map<String, String> map) {
        String str;
        if (map == null || map.keySet().size() <= 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String next : map.keySet()) {
            if (StringUtil.isNotEmpty(next) && (str = map.get(next)) != null) {
                try {
                    String encode = URLEncoder.encode(next, "UTF-8");
                    String encode2 = URLEncoder.encode(str, "UTF-8");
                    if (!sb.toString().equalsIgnoreCase("")) {
                        sb.append("&");
                    }
                    sb.append(encode);
                    sb.append("=");
                    sb.append(encode2);
                } catch (Exception unused) {
                    Trace.w(TAG, "Failed to encode values for post");
                }
            }
        }
        return sb.toString();
    }

    public void followUser(ConsultUser consultUser, final com.medscape.android.interfaces.ICallbackEvent<Boolean, MedscapeException> iCallbackEvent) {
        if (consultUser == null || !StringUtil.isNotEmpty(consultUser.getUserId())) {
            Trace.w(TAG, "Missing user id");
            iCallbackEvent.onError(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_missing_userid)));
            return;
        }
        try {
            HttpRequestObject httpRequestObject = new HttpRequestObject();
            httpRequestObject.setUrl(ConsultUrls.getFollowUrl(this.mContext));
            httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("FollowingUserName", consultUser.getUserId());
            httpRequestObject.setRequestBody(jSONObject.toString());
            httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
            httpRequestObject.setRequestMethod("POST");
            getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                public void onCommunityDataReceived(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        iCallbackEvent.onCompleted(true);
                        return;
                    }
                    iCallbackEvent.onError(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                }

                public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                    iCallbackEvent.onError(medscapeException);
                }
            }, false);
        } catch (Exception unused) {
            iCallbackEvent.onError(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_failed_parsing)));
        }
    }

    public void unFollowUser(ConsultUser consultUser, final com.medscape.android.interfaces.ICallbackEvent<Boolean, MedscapeException> iCallbackEvent) {
        if (consultUser == null || !StringUtil.isNotEmpty(consultUser.getUserId())) {
            Trace.w(TAG, "Missing user id");
            iCallbackEvent.onError(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_missing_userid)));
            return;
        }
        try {
            HttpRequestObject httpRequestObject = new HttpRequestObject();
            httpRequestObject.setUrl(ConsultUrls.getUnFollowUrl(this.mContext));
            httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("FollowingUserName", consultUser.getUserId());
            httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
            httpRequestObject.setRequestBody(jSONObject.toString());
            httpRequestObject.setRequestMethod("POST");
            getRawJsonMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityRequestCompleteListener() {
                public void onCommunityRequestComplete(HttpResponseObject httpResponseObject) {
                    if (httpResponseObject == null || httpResponseObject.getResponseCode() != 200) {
                        iCallbackEvent.onError(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                        return;
                    }
                    iCallbackEvent.onCompleted(true);
                }

                public void onCommunityRequestFailed(MedscapeException medscapeException) {
                    iCallbackEvent.onError(medscapeException);
                }
            }, false);
        } catch (Exception unused) {
            iCallbackEvent.onError(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_failed_parsing)));
        }
    }

    public void searchContentForText(String str, ConsultFeed consultFeed, IFeedReceivedListener iFeedReceivedListener, int i) {
        final String str2 = str;
        final IFeedReceivedListener iFeedReceivedListener2 = iFeedReceivedListener;
        final int i2 = i;
        final ConsultFeed consultFeed2 = consultFeed;
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                if (!StringUtil.isNotEmpty(str2)) {
                    Trace.w(ConsultDataManager.TAG, "Missing query text");
                    iFeedReceivedListener2.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_query)), i2, (String) null);
                    return;
                }
                try {
                    final int currentPageIndex = consultFeed2 != null ? consultFeed2.getCurrentPageIndex() + 1 : 0;
                    String searchContentUrl = ConsultUrls.getSearchContentUrl(ConsultDataManager.this.mContext);
                    HashMap hashMap = new HashMap();
                    hashMap.put(Constants.ZIMBRA_GROUP_ID, ConsultDataManager.mZimbraGroupId);
                    hashMap.put(SearchIntents.EXTRA_QUERY, URLEncoder.encode(str2, "UTF-8"));
                    hashMap.put("PageSize", UserProfile.PHYSICIAN_ID);
                    hashMap.put("PageIndex", "" + currentPageIndex);
                    String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(searchContentUrl, hashMap);
                    HttpRequestObject httpRequestObject = new HttpRequestObject();
                    httpRequestObject.setUrl(urlFromUrlWithMap);
                    httpRequestObject.setRequestMethod("GET");
                    httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                    ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                        public void onCommunityDataReceived(JSONObject jSONObject) {
                            iFeedReceivedListener2.onFeedReceived(ConsultDataManager.this.getPostsFeedFromServerResponse(jSONObject, consultFeed2, currentPageIndex, -1), i2, str2);
                        }

                        public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                            iFeedReceivedListener2.onFailedToReceiveFeed(medscapeException, i2, str2);
                        }
                    }, false);
                } catch (Exception unused) {
                    Trace.w(ConsultDataManager.TAG, "Failed to search for content");
                    iFeedReceivedListener2.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), i2, (String) null);
                }
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener2.onFailedToReceiveFeed(medscapeException, i2, (String) null);
            }
        });
    }

    public void searchUsersForText(String str, ConsultFeed consultFeed, IFeedReceivedListener iFeedReceivedListener, int i) {
        final String str2 = str;
        final IFeedReceivedListener iFeedReceivedListener2 = iFeedReceivedListener;
        final int i2 = i;
        final ConsultFeed consultFeed2 = consultFeed;
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                if (!StringUtil.isNotEmpty(str2)) {
                    Trace.w(ConsultDataManager.TAG, "Missing query text");
                    iFeedReceivedListener2.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_query)), i2, (String) null);
                    return;
                }
                try {
                    final int currentPageIndex = consultFeed2 != null ? consultFeed2.getCurrentPageIndex() + 1 : 0;
                    String searchUserUrl = ConsultUrls.getSearchUserUrl(ConsultDataManager.this.mContext);
                    HashMap hashMap = new HashMap();
                    hashMap.put(Constants.ZIMBRA_GROUP_ID, ConsultDataManager.mZimbraGroupId);
                    hashMap.put(Constants.ZIMBRA_FORUM_ID, ConsultDataManager.mZimbraForumId);
                    hashMap.put(SearchIntents.EXTRA_QUERY, URLEncoder.encode(str2, "UTF-8"));
                    hashMap.put("PageSize", UserProfile.PHYSICIAN_ID);
                    hashMap.put("PageIndex", "" + currentPageIndex);
                    String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(searchUserUrl, hashMap);
                    HttpRequestObject httpRequestObject = new HttpRequestObject();
                    httpRequestObject.setUrl(urlFromUrlWithMap);
                    httpRequestObject.setRequestMethod("GET");
                    httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                    ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                        public void onCommunityDataReceived(JSONObject jSONObject) {
                            iFeedReceivedListener2.onFeedReceived(ConsultDataManager.this.getUsersFeedFromServerResponse(jSONObject, "Items", consultFeed2, currentPageIndex), i2, str2);
                        }

                        public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                            iFeedReceivedListener2.onFailedToReceiveFeed(medscapeException, i2, str2);
                        }
                    }, false);
                } catch (Exception unused) {
                    Trace.w(ConsultDataManager.TAG, "Failed to search for content");
                    iFeedReceivedListener2.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), i2, (String) null);
                }
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener2.onFailedToReceiveFeed(medscapeException, i2, (String) null);
            }
        });
    }

    public void getTagSearchResultsForText(final String str, final IFeedReceivedListener iFeedReceivedListener, final int i) {
        loadTagList(new ITagsReceivedListener() {
            public void onTagsReceived(List<String> list) {
                ArrayList arrayList = new ArrayList();
                if (list != null) {
                    if (StringUtil.isNotEmpty(str)) {
                        for (String next : list) {
                            if (next.toLowerCase().contains(str.toLowerCase())) {
                                ConsultTag consultTag = new ConsultTag();
                                consultTag.setUniqueIdentifier(next);
                                arrayList.add(consultTag);
                            }
                        }
                    }
                    ConsultFeed consultFeed = new ConsultFeed();
                    consultFeed.addItemsToEnd(arrayList);
                    consultFeed.setTotalItems(consultFeed.getFeedItems().size());
                    consultFeed.setCurrentPageIndex(0);
                    IFeedReceivedListener iFeedReceivedListener = iFeedReceivedListener;
                    if (iFeedReceivedListener != null) {
                        iFeedReceivedListener.onFeedReceived(consultFeed, i, str);
                        return;
                    }
                    return;
                }
                Trace.w(ConsultDataManager.TAG, "Failed to get tags");
                MedscapeException medscapeException = new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_get_tags));
                IFeedReceivedListener iFeedReceivedListener2 = iFeedReceivedListener;
                if (iFeedReceivedListener2 != null) {
                    iFeedReceivedListener2.onFailedToReceiveFeed(medscapeException, i, str);
                }
            }
        });
    }

    public void loadTagList(final ITagsReceivedListener iTagsReceivedListener) {
        GetTagsTask getTagsTask = this.mGetTagsTask;
        if (getTagsTask == null || getTagsTask.getStatus() != AsyncTask.Status.RUNNING) {
            Trace.i(TAG, "Calling get tags task");
            this.mTagsReceivedListeners.clear();
            GetTagsTask getTagsTask2 = new GetTagsTask(this.mContext, mLastTagUpdate, new ITagsReceivedListener() {
                public void onTagsReceived(List<String> list) {
                    CacheProvider.Entry entry;
                    Trace.i(ConsultDataManager.TAG, "Tags received");
                    List unused = ConsultDataManager.mFullTagList = list;
                    ICacheProvider cacheProvider = MedscapeApplication.getCacheProvider();
                    if (ConsultDataManager.mFullTagList != null && !ConsultDataManager.mFullTagList.isEmpty()) {
                        Collections.sort(ConsultDataManager.mFullTagList, String.CASE_INSENSITIVE_ORDER);
                        if (cacheProvider != null) {
                            CacheProvider.Entry entry2 = new CacheProvider.Entry();
                            entry2.data = SerializerUtils.serialize(ConsultDataManager.mFullTagList);
                            entry2.key = ConsultDataManager.TAG_LIST_CACHE_KEY;
                            cacheProvider.put(entry2);
                        }
                    }
                    if (ConsultDataManager.mFullTagList != null && !ConsultDataManager.mFullTagList.isEmpty()) {
                        Calendar unused2 = ConsultDataManager.mLastTagUpdate = Calendar.getInstance();
                    } else if (!(cacheProvider == null || (entry = cacheProvider.get(ConsultDataManager.TAG_LIST_CACHE_KEY, true)) == null)) {
                        Object deserialize = SerializerUtils.deserialize(entry.data);
                        if (deserialize instanceof List) {
                            Trace.i(ConsultDataManager.TAG, "Found tags in the cache");
                            List unused3 = ConsultDataManager.mFullTagList = (List) deserialize;
                        }
                    }
                    iTagsReceivedListener.onTagsReceived(ConsultDataManager.mFullTagList);
                    while (!ConsultDataManager.this.mTagsReceivedListeners.isEmpty()) {
                        ITagsReceivedListener iTagsReceivedListener = (ITagsReceivedListener) ConsultDataManager.this.mTagsReceivedListeners.poll();
                        if (iTagsReceivedListener != null) {
                            Trace.i(ConsultDataManager.TAG, "Get Tags request complete, returning to other listeners");
                            iTagsReceivedListener.onTagsReceived(ConsultDataManager.mFullTagList);
                        }
                    }
                }
            });
            this.mGetTagsTask = getTagsTask2;
            getTagsTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
        }
        this.mTagsReceivedListeners.add(iTagsReceivedListener);
    }

    public void getCommentsForPost(ConsultPost consultPost, ConsultFeed consultFeed, int i, IFeedReceivedListener iFeedReceivedListener) {
        final ConsultPost consultPost2 = consultPost;
        final ConsultFeed consultFeed2 = consultFeed;
        final int i2 = i;
        final IFeedReceivedListener iFeedReceivedListener2 = iFeedReceivedListener;
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                String str;
                ConsultPost consultPost = consultPost2;
                if (consultPost == null || !StringUtil.isNotEmpty(consultPost.getPostId())) {
                    Trace.w(ConsultDataManager.TAG, "Missing post ID needed to get comments");
                    iFeedReceivedListener2.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_query)), i2, (String) null);
                    return;
                }
                ConsultFeed consultFeed = consultFeed2;
                final int currentPageIndex = consultFeed != null ? consultFeed.getCurrentPageIndex() + 1 : 0;
                String repliesUrl = ConsultUrls.getRepliesUrl(ConsultDataManager.this.mContext);
                String access$1000 = ConsultDataManager.mZimbraForumId;
                if (consultPost2.isHidden()) {
                    access$1000 = ConsultDataManager.mZimbraHiddenForumId;
                }
                int i = i2;
                String str2 = "Descending";
                if (i != 3028) {
                    if (i == 3029) {
                        str = "TotalReplies";
                    } else if (i == 30281) {
                        str = "hotrankingscore";
                    } else {
                        str2 = "Ascending";
                    }
                    HashMap hashMap = new HashMap();
                    hashMap.put(Constants.ZIMBRA_FORUM_ID, access$1000);
                    hashMap.put("ThreadId", consultPost2.getPostId());
                    hashMap.put("SortBy", str);
                    hashMap.put("SortOrder", str2);
                    hashMap.put("PageSize", UserProfile.PHYSICIAN_ID);
                    hashMap.put("PageIndex", "" + currentPageIndex);
                    hashMap.put("LoadChildReplies", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                    hashMap.put("ChildRepliesSortBy", "PostDate");
                    hashMap.put("ChildRepliesSortOrder", "Ascending");
                    hashMap.put("ChildRepliesPageSize", ExifInterface.GPS_MEASUREMENT_2D);
                    String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(repliesUrl, hashMap);
                    HttpRequestObject httpRequestObject = new HttpRequestObject();
                    httpRequestObject.setUrl(urlFromUrlWithMap);
                    httpRequestObject.setRequestMethod("GET");
                    httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                    ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                        public void onCommunityDataReceived(JSONObject jSONObject) {
                            if (jSONObject != null) {
                                List<ConsultFeedItem> consultCommentsForJsonArray = CommunityResponseParser.getConsultCommentsForJsonArray(jSONObject.optJSONArray("Items"), ConsultDataManager.mZimbraHiddenForumId, Constants.CONSULT_COMMENT_TYPE_POST, true);
                                ConsultFeed consultFeed = consultFeed2;
                                if (consultFeed == null) {
                                    consultFeed = new ConsultFeed();
                                }
                                consultFeed.addItemsToEnd(consultCommentsForJsonArray);
                                consultFeed.setTotalItems(jSONObject.optInt("TotalCount"));
                                consultFeed.setCurrentPageIndex(currentPageIndex);
                                iFeedReceivedListener2.onFeedReceived(consultFeed, i2, (String) null);
                                return;
                            }
                            iFeedReceivedListener2.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), i2, (String) null);
                        }

                        public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                            iFeedReceivedListener2.onFailedToReceiveFeed(medscapeException, i2, (String) null);
                        }
                    }, false);
                }
                str = "PostDate";
                HashMap hashMap2 = new HashMap();
                hashMap2.put(Constants.ZIMBRA_FORUM_ID, access$1000);
                hashMap2.put("ThreadId", consultPost2.getPostId());
                hashMap2.put("SortBy", str);
                hashMap2.put("SortOrder", str2);
                hashMap2.put("PageSize", UserProfile.PHYSICIAN_ID);
                hashMap2.put("PageIndex", "" + currentPageIndex);
                hashMap2.put("LoadChildReplies", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                hashMap2.put("ChildRepliesSortBy", "PostDate");
                hashMap2.put("ChildRepliesSortOrder", "Ascending");
                hashMap2.put("ChildRepliesPageSize", ExifInterface.GPS_MEASUREMENT_2D);
                String urlFromUrlWithMap2 = Util.getUrlFromUrlWithMap(repliesUrl, hashMap2);
                HttpRequestObject httpRequestObject2 = new HttpRequestObject();
                httpRequestObject2.setUrl(urlFromUrlWithMap2);
                httpRequestObject2.setRequestMethod("GET");
                httpRequestObject2.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject2, new ICommunityDataReceivedListener() {
                    public void onCommunityDataReceived(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            List<ConsultFeedItem> consultCommentsForJsonArray = CommunityResponseParser.getConsultCommentsForJsonArray(jSONObject.optJSONArray("Items"), ConsultDataManager.mZimbraHiddenForumId, Constants.CONSULT_COMMENT_TYPE_POST, true);
                            ConsultFeed consultFeed = consultFeed2;
                            if (consultFeed == null) {
                                consultFeed = new ConsultFeed();
                            }
                            consultFeed.addItemsToEnd(consultCommentsForJsonArray);
                            consultFeed.setTotalItems(jSONObject.optInt("TotalCount"));
                            consultFeed.setCurrentPageIndex(currentPageIndex);
                            iFeedReceivedListener2.onFeedReceived(consultFeed, i2, (String) null);
                            return;
                        }
                        iFeedReceivedListener2.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), i2, (String) null);
                    }

                    public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                        iFeedReceivedListener2.onFailedToReceiveFeed(medscapeException, i2, (String) null);
                    }
                }, false);
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener2.onFailedToReceiveFeed(medscapeException, i2, (String) null);
            }
        });
    }

    public void getRepliesForComment(final ConsultComment consultComment, final ConsultFeed consultFeed, final IFeedReceivedListener iFeedReceivedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultComment consultComment = consultComment;
                if (consultComment == null) {
                    iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_comment)), -1, (String) null);
                } else if (!StringUtil.isNotEmpty(consultComment.getParentThreadId())) {
                    iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_comment_threadid)), -1, (String) null);
                } else if (!StringUtil.isNotEmpty(consultComment.getCommentId())) {
                    iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_commentid)), -1, (String) null);
                } else {
                    ConsultFeed consultFeed = consultFeed;
                    final int currentPageIndex = consultFeed != null ? consultFeed.getCurrentPageIndex() + 1 : 0;
                    String repliesUrl = ConsultUrls.getRepliesUrl(ConsultDataManager.this.mContext);
                    HashMap hashMap = new HashMap();
                    hashMap.put(Constants.ZIMBRA_FORUM_ID, ConsultDataManager.mZimbraForumId);
                    hashMap.put("ThreadId", consultComment.getParentThreadId());
                    hashMap.put("ReplyId", consultComment.getCommentId());
                    hashMap.put("PageSize", UserProfile.PHYSICIAN_ID);
                    hashMap.put("PageIndex", "" + currentPageIndex);
                    String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(repliesUrl, hashMap);
                    HttpRequestObject httpRequestObject = new HttpRequestObject();
                    httpRequestObject.setUrl(urlFromUrlWithMap);
                    httpRequestObject.setRequestMethod("GET");
                    httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                    ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                        public void onCommunityDataReceived(JSONObject jSONObject) {
                            if (jSONObject != null) {
                                List<ConsultFeedItem> consultCommentsForJsonArray = CommunityResponseParser.getConsultCommentsForJsonArray(jSONObject.optJSONArray("Items"), ConsultDataManager.mZimbraHiddenForumId, Constants.CONSULT_COMMENT_TYPE_REPLY, true);
                                ConsultFeed consultFeed = new ConsultFeed();
                                consultFeed.addItemsToEnd(consultCommentsForJsonArray);
                                consultFeed.setTotalItems(jSONObject.optInt("TotalCount"));
                                consultFeed.setCurrentPageIndex(currentPageIndex);
                                iFeedReceivedListener.onFeedReceived(consultFeed, -1, (String) null);
                            }
                        }

                        public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                            iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, -1, (String) null);
                        }
                    }, false);
                }
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, -1, (String) null);
            }
        });
    }

    public void getFullPostForPost(final ConsultPost consultPost, final IPostReceivedListener iPostReceivedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultPost consultPost = consultPost;
                if (consultPost == null || !StringUtil.isNotEmpty(consultPost.getPostId())) {
                    iPostReceivedListener.onPostRequestFailed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_post_id_missing)));
                    return;
                }
                String postUrl = ConsultUrls.getPostUrl(ConsultDataManager.this.mContext);
                String access$1000 = ConsultDataManager.mZimbraForumId;
                if (consultPost.isHidden()) {
                    access$1000 = ConsultDataManager.mZimbraHiddenForumId;
                }
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.ZIMBRA_FORUM_ID, access$1000);
                hashMap.put("ThreadId", consultPost.getPostId());
                hashMap.put("PageSize", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(postUrl, hashMap);
                HttpRequestObject httpRequestObject = new HttpRequestObject();
                httpRequestObject.setUrl(urlFromUrlWithMap);
                httpRequestObject.setRequestMethod("GET");
                httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                    public void onCommunityDataReceived(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            ConsultPost parseCommunityResponse = CommunityResponseParser.parseCommunityResponse(jSONObject, ConsultDataManager.mZimbraHiddenForumId);
                            JSONObject unused = ConsultDataManager.consultJsonObject = jSONObject;
                            iPostReceivedListener.onPostReceived(parseCommunityResponse);
                            return;
                        }
                        Trace.i(ConsultDataManager.TAG, "Post object from server was null");
                        iPostReceivedListener.onPostRequestFailed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                    }

                    public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                        iPostReceivedListener.onPostRequestFailed(medscapeException);
                    }
                }, false);
            }

            public void onFailure(MedscapeException medscapeException) {
                iPostReceivedListener.onPostRequestFailed(medscapeException);
            }
        });
    }

    public void saveCommentForPost(final ConsultComment consultComment, final ConsultPost consultPost, final ICommentUpdateListener iCommentUpdateListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultPost consultPost = consultPost;
                if (consultPost == null || !StringUtil.isNotEmpty(consultPost.getPostId())) {
                    iCommentUpdateListener.onCommentUpdateFailed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_postid)));
                    return;
                }
                String postReplyUrl = ConsultUrls.getPostReplyUrl(ConsultDataManager.this.mContext);
                HttpRequestObject httpRequestObject = new HttpRequestObject();
                httpRequestObject.setUrl(postReplyUrl);
                httpRequestObject.setRequestMethod("POST");
                httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                String access$1000 = ConsultDataManager.mZimbraForumId;
                if (consultPost.isHidden()) {
                    access$1000 = ConsultDataManager.mZimbraHiddenForumId;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("threadid", consultPost.getPostId());
                hashMap.put(Constants.ZIMBRA_GROUP_ID, ConsultDataManager.mZimbraGroupId);
                hashMap.put(Constants.ZIMBRA_FORUM_ID, access$1000);
                hashMap.put("Body", consultComment.getCommentBody());
                httpRequestObject.setRequestBody(ConsultDataManager.this.getPostDataFromMap(hashMap));
                ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                    public void onCommunityDataReceived(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            ConsultComment consultCommentFromCommunityJson = CommunityResponseParser.getConsultCommentFromCommunityJson(jSONObject, ConsultDataManager.mZimbraHiddenForumId);
                            consultCommentFromCommunityJson.setCommentType(Constants.CONSULT_COMMENT_TYPE_POST);
                            consultCommentFromCommunityJson.getPoster().setProfessionID(ConsultDataManager.mCurrentConsultUser.getProfessionID());
                            consultCommentFromCommunityJson.getPoster().setEducation(ConsultDataManager.mCurrentConsultUser.getEducation());
                            iCommentUpdateListener.onCommentUpdated(consultCommentFromCommunityJson);
                        }
                    }

                    public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                        iCommentUpdateListener.onCommentUpdateFailed(medscapeException);
                    }
                }, false);
            }

            public void onFailure(MedscapeException medscapeException) {
                iCommentUpdateListener.onCommentUpdateFailed(medscapeException);
            }
        });
    }

    public void saveCommentReplyForComment(final ConsultComment consultComment, final ConsultComment consultComment2, final ICommentUpdateListener iCommentUpdateListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultComment consultComment = consultComment;
                if (consultComment == null || !StringUtil.isNotEmpty(consultComment.getParentThreadId()) || !StringUtil.isNotEmpty(consultComment.getCommentId())) {
                    iCommentUpdateListener.onCommentUpdateFailed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_missing_comment)));
                    return;
                }
                String postReplyUrl = ConsultUrls.getPostReplyUrl(ConsultDataManager.this.mContext);
                HttpRequestObject httpRequestObject = new HttpRequestObject();
                httpRequestObject.setUrl(postReplyUrl);
                httpRequestObject.setRequestMethod("POST");
                httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                String access$1000 = ConsultDataManager.mZimbraForumId;
                if (consultComment.isCommentOnHiddenPost()) {
                    access$1000 = ConsultDataManager.mZimbraHiddenForumId;
                }
                HashMap hashMap = new HashMap();
                hashMap.put("threadid", consultComment.getParentThreadId());
                hashMap.put(Constants.ZIMBRA_GROUP_ID, ConsultDataManager.mZimbraGroupId);
                hashMap.put(Constants.ZIMBRA_FORUM_ID, access$1000);
                hashMap.put("Body", consultComment2.getCommentBody());
                hashMap.put("parentReplyId", consultComment.getCommentId());
                httpRequestObject.setRequestBody(ConsultDataManager.this.getPostDataFromMap(hashMap));
                ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                    public void onCommunityDataReceived(JSONObject jSONObject) {
                        if (jSONObject != null) {
                            ConsultComment consultCommentFromCommunityJson = CommunityResponseParser.getConsultCommentFromCommunityJson(jSONObject, ConsultDataManager.mZimbraHiddenForumId);
                            consultCommentFromCommunityJson.setCommentType(Constants.CONSULT_COMMENT_TYPE_REPLY);
                            consultCommentFromCommunityJson.getPoster().setProfessionID(ConsultDataManager.mCurrentConsultUser.getProfessionID());
                            consultCommentFromCommunityJson.getPoster().setEducation(ConsultDataManager.mCurrentConsultUser.getEducation());
                            iCommentUpdateListener.onCommentUpdated(consultCommentFromCommunityJson);
                        }
                    }

                    public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                        iCommentUpdateListener.onCommentUpdateFailed(medscapeException);
                    }
                }, false);
            }

            public void onFailure(MedscapeException medscapeException) {
                iCommentUpdateListener.onCommentUpdateFailed(medscapeException);
            }
        });
    }

    public void addPostUpdate(final ConsultPost consultPost, final IPostUploadedListener iPostUploadedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultPost consultPost = consultPost;
                if (consultPost != null) {
                    ConsultDataManager.this.handleAddEditPostUpdate(consultPost, iPostUploadedListener);
                }
            }

            public void onFailure(MedscapeException medscapeException) {
                iPostUploadedListener.onPostFailed(medscapeException);
            }
        });
    }

    public void editPostUpdate(final ConsultPost consultPost, final IPostUploadedListener iPostUploadedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultPost consultPost = consultPost;
                if (consultPost != null) {
                    ConsultDataManager.this.handleAddEditPostUpdate(consultPost, iPostUploadedListener);
                }
            }

            public void onFailure(MedscapeException medscapeException) {
                iPostUploadedListener.onPostFailed(medscapeException);
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleAddEditPostUpdate(ConsultPost consultPost, final IPostUploadedListener iPostUploadedListener) {
        JSONArray jSONArray;
        String updateThreadUrl = ConsultUrls.getUpdateThreadUrl(this.mContext);
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setUrl(updateThreadUrl);
        httpRequestObject.setRequestMethod("POST");
        httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
        httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
        try {
            jSONArray = new JSONArray(new Gson().toJson((Object) consultPost.getConsultBodyUpdateList()));
        } catch (JSONException e) {
            e.printStackTrace();
            jSONArray = null;
        }
        JSONObject jSONObject = consultJsonObject;
        if (jSONObject != null) {
            jSONObject.remove("BodyUpdates");
            consultJsonObject.remove("CaseIsResolved");
            try {
                consultJsonObject.put("BodyUpdates", jSONArray);
                consultJsonObject.put("CaseIsResolved", consultPost.isCaseResolved());
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            httpRequestObject.setRequestBody(consultJsonObject.toString());
        }
        getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
            public void onCommunityDataReceived(JSONObject jSONObject) {
                Trace.i(ConsultDataManager.TAG, "Success!");
                iPostUploadedListener.onPostSentToServer(CommunityResponseParser.parseCommunityResponse(jSONObject, ConsultDataManager.mZimbraHiddenForumId));
            }

            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                Trace.i(ConsultDataManager.TAG, "Failed to upload post");
                iPostUploadedListener.onPostFailed(medscapeException);
            }
        }, false);
    }

    public void sendPostToServer(ConsultPost consultPost, boolean z, Map<ForegroundColorSpan, String> map, SpannableStringBuilder spannableStringBuilder, IPostUploadedListener iPostUploadedListener) {
        final ConsultPost consultPost2 = consultPost;
        final Map<ForegroundColorSpan, String> map2 = map;
        final SpannableStringBuilder spannableStringBuilder2 = spannableStringBuilder;
        final IPostUploadedListener iPostUploadedListener2 = iPostUploadedListener;
        final boolean z2 = z;
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                if (consultPost2 != null) {
                    new UploadMediaTask(ConsultDataManager.this.mContext, consultPost2, ConsultDataManager.mZimbraGroupId, ConsultDataManager.mZimbraMediaGalleryId, new IUploadMediaListener() {
                        public void onMediaUploadSuccess(final List<String> list) {
                            String postBody = consultPost2.getPostBody();
                            if (!StringUtil.isNotEmpty(postBody)) {
                                return;
                            }
                            if (map2 == null || map2.size() <= 0) {
                                AnonymousClass34.this.submitCleanPostDataToServer(list, postBody);
                            } else {
                                new UpdatePostBodyWithMentionsTask(ConsultDataManager.this.mContext, spannableStringBuilder2, map2, ConsultDataManager.mZimbraGroupId, ConsultDataManager.mZimbraForumId, new IPostBodyUpdatedWithMentionsListener() {
                                    public void onPostBodyUpdated(String str) {
                                        AnonymousClass34.this.submitCleanPostDataToServer(list, str);
                                    }
                                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                            }
                        }

                        public void onMediaUploadFail(MedscapeException medscapeException) {
                            Trace.i(ConsultDataManager.TAG, "Failed to upload post");
                            iPostUploadedListener2.onPostFailed(medscapeException);
                        }
                    }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    return;
                }
                Trace.i(ConsultDataManager.TAG, "Failed to upload post");
                iPostUploadedListener2.onPostFailed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_post_failed)));
            }

            /* access modifiers changed from: private */
            public void submitCleanPostDataToServer(List<String> list, String str) {
                AuthenticationManager instance = AuthenticationManager.getInstance(ConsultDataManager.this.mContext);
                String access$3100 = ConsultDataManager.this.getPostMethodUrl(z2);
                HttpRequestObject httpRequestObject = new HttpRequestObject();
                httpRequestObject.setUrl(access$3100);
                httpRequestObject.setRequestMethod("POST");
                httpRequestObject.setAuthorization(instance.getAuthenticationToken());
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.ZIMBRA_GROUP_ID, ConsultDataManager.mZimbraGroupId);
                hashMap.put(Constants.ZIMBRA_FORUM_ID, ConsultDataManager.mZimbraForumId);
                if (z2) {
                    hashMap.put("Id", consultPost2.getPostId());
                }
                String subject = consultPost2.getSubject();
                if (StringUtil.isNotEmpty(subject)) {
                    hashMap.put("Title", subject);
                }
                hashMap.put("Body", str);
                String postDataFromMap = ConsultDataManager.this.getPostDataFromMap(ConsultDataManager.this.addTagsToMap(consultPost2, hashMap));
                if (list != null) {
                    try {
                        if (list.size() != 0) {
                            for (String encode : list) {
                                postDataFromMap = postDataFromMap.concat(String.format("&ImageUrls=%s", new Object[]{URLEncoder.encode(encode, "UTF-8")}));
                            }
                            httpRequestObject.setRequestBody(postDataFromMap);
                            ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                                public void onCommunityDataReceived(JSONObject jSONObject) {
                                    Trace.i(ConsultDataManager.TAG, "Success!");
                                    ConsultPost parseCommunityResponse = CommunityResponseParser.parseCommunityResponse(jSONObject, ConsultDataManager.mZimbraHiddenForumId);
                                    parseCommunityResponse.setTimestamp(Calendar.getInstance());
                                    parseCommunityResponse.getPoster().setProfessionID(ConsultDataManager.mCurrentConsultUser.getProfessionID());
                                    parseCommunityResponse.getPoster().setEducation(ConsultDataManager.mCurrentConsultUser.getEducation());
                                    iPostUploadedListener2.onPostSentToServer(parseCommunityResponse);
                                }

                                public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                                    Trace.i(ConsultDataManager.TAG, "Failed to upload post");
                                    iPostUploadedListener2.onPostFailed(medscapeException);
                                }
                            }, false);
                        }
                    } catch (Exception unused) {
                        Trace.w(ConsultDataManager.TAG, "Failed to encode image urls");
                        iPostUploadedListener2.onPostFailed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                    }
                }
                postDataFromMap = postDataFromMap.concat("&ImageUrls");
                httpRequestObject.setRequestBody(postDataFromMap);
                ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                    public void onCommunityDataReceived(JSONObject jSONObject) {
                        Trace.i(ConsultDataManager.TAG, "Success!");
                        ConsultPost parseCommunityResponse = CommunityResponseParser.parseCommunityResponse(jSONObject, ConsultDataManager.mZimbraHiddenForumId);
                        parseCommunityResponse.setTimestamp(Calendar.getInstance());
                        parseCommunityResponse.getPoster().setProfessionID(ConsultDataManager.mCurrentConsultUser.getProfessionID());
                        parseCommunityResponse.getPoster().setEducation(ConsultDataManager.mCurrentConsultUser.getEducation());
                        iPostUploadedListener2.onPostSentToServer(parseCommunityResponse);
                    }

                    public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                        Trace.i(ConsultDataManager.TAG, "Failed to upload post");
                        iPostUploadedListener2.onPostFailed(medscapeException);
                    }
                }, false);
            }

            public void onFailure(MedscapeException medscapeException) {
                iPostUploadedListener2.onPostFailed(medscapeException);
            }
        });
    }

    public void getMentionablesForText(final String str, final ConsultFeed consultFeed, final IFeedReceivedListener iFeedReceivedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                if (StringUtil.isNotEmpty(str)) {
                    try {
                        String mentionablesUrl = ConsultUrls.getMentionablesUrl(ConsultDataManager.this.mContext);
                        HashMap hashMap = new HashMap();
                        hashMap.put("groupId", ConsultDataManager.mZimbraGroupId);
                        hashMap.put("forumId", ConsultDataManager.mZimbraForumId);
                        hashMap.put(SearchIntents.EXTRA_QUERY, URLEncoder.encode(str, "UTF-8"));
                        hashMap.put("pageSize", "20");
                        String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(mentionablesUrl, hashMap);
                        AuthenticationManager instance = AuthenticationManager.getInstance(ConsultDataManager.this.mContext);
                        HttpRequestObject httpRequestObject = new HttpRequestObject();
                        httpRequestObject.setUrl(urlFromUrlWithMap);
                        httpRequestObject.setRequestMethod("GET");
                        httpRequestObject.setAuthorization(instance.getAuthenticationToken());
                        ConsultDataManager.this.getArrayFromMedscapeCommunityRequest(httpRequestObject, new ICommunityArrayReceivedListener() {
                            public void onCommunityDataReceived(JSONArray jSONArray) {
                                if (jSONArray != null) {
                                    ArrayList arrayList = new ArrayList();
                                    int i = 0;
                                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                        JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                                        if (optJSONObject != null) {
                                            String optString = optJSONObject.optString("PreviewName");
                                            String optString2 = optJSONObject.optString("MentionToken");
                                            if (StringUtil.isNotEmpty(optString) && StringUtil.isNotEmpty(optString2)) {
                                                ConsultUser consultUser = new ConsultUser();
                                                consultUser.setDisplayName(optString);
                                                consultUser.setMentionToken(optString2);
                                                arrayList.add(consultUser);
                                            }
                                        }
                                    }
                                    if (consultFeed != null) {
                                        i = consultFeed.getCurrentPageIndex() + 1;
                                    }
                                    int length = jSONArray.length();
                                    ConsultFeed consultFeed = consultFeed;
                                    if (consultFeed == null) {
                                        consultFeed = new ConsultFeed();
                                    }
                                    consultFeed.addItemsToEnd(arrayList);
                                    consultFeed.setTotalItems(length);
                                    consultFeed.setCurrentPageIndex(i);
                                    iFeedReceivedListener.onFeedReceived(consultFeed, -1, str);
                                    return;
                                }
                                iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), -1, str);
                            }

                            public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                                iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, -1, str);
                            }
                        }, true);
                    } catch (Exception unused) {
                        Trace.w(ConsultDataManager.TAG, "Failed to search for mentionables by tag");
                        iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)), -1, str);
                    }
                } else {
                    iFeedReceivedListener.onFailedToReceiveFeed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_find_mentions)), -1, str);
                }
            }

            public void onFailure(MedscapeException medscapeException) {
                iFeedReceivedListener.onFailedToReceiveFeed(medscapeException, -1, str);
            }
        });
    }

    /* access modifiers changed from: private */
    public Map<String, String> addTagsToMap(ConsultPost consultPost, Map<String, String> map) {
        List<String> tags;
        if (!(consultPost == null || (tags = consultPost.getTags()) == null || tags.isEmpty())) {
            String str = "";
            for (String next : tags) {
                if (StringUtil.isNotEmpty(next)) {
                    str = str.concat(next + ",");
                }
            }
            if (StringUtil.isNotEmpty(str)) {
                map.put("Tags", str.substring(0, str.length() - 1));
            }
        }
        return map;
    }

    /* access modifiers changed from: private */
    public String getPostMethodUrl(boolean z) {
        Context context = this.mContext;
        if (context == null) {
            return null;
        }
        if (z) {
            return ConsultUrls.getUpdateThreadUrl(context);
        }
        return ConsultUrls.getPostThreadUrl(context);
    }

    public void upVoteContent(String str, String str2, ICommunityDataReceivedListener iCommunityDataReceivedListener) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(str2)) {
            HttpRequestObject httpRequestObject = new HttpRequestObject();
            httpRequestObject.setUrl(ConsultUrls.getUpVoteUrl(this.mContext));
            httpRequestObject.setRequestMethod("POST");
            httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
            HashMap hashMap = new HashMap();
            hashMap.put("ContentId", str);
            hashMap.put("ContentTypeId", str2);
            String postDataFromMap = getPostDataFromMap(hashMap);
            if (StringUtil.isNotEmpty(postDataFromMap)) {
                httpRequestObject.setRequestBody(postDataFromMap);
            }
            getMapFromMedscapeCommunityRequest(httpRequestObject, iCommunityDataReceivedListener, false);
        } else if (iCommunityDataReceivedListener != null) {
            Trace.w(TAG, "Failed to like post");
            iCommunityDataReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_missing_contentId)));
        }
    }

    public void deleteUpVoteContent(String str, String str2, ICommunityDataReceivedListener iCommunityDataReceivedListener) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(str2)) {
            HttpRequestObject httpRequestObject = new HttpRequestObject();
            httpRequestObject.setUrl(ConsultUrls.getDeleteUpVoteUrl(this.mContext));
            httpRequestObject.setRequestMethod("POST");
            httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
            HashMap hashMap = new HashMap();
            hashMap.put("ContentId", str);
            hashMap.put("ContentTypeId", str2);
            String postDataFromMap = getPostDataFromMap(hashMap);
            if (StringUtil.isNotEmpty(postDataFromMap)) {
                httpRequestObject.setRequestBody(postDataFromMap);
            }
            getDataFromMedscapeCommunityRequest(httpRequestObject, iCommunityDataReceivedListener, false);
        } else if (iCommunityDataReceivedListener != null) {
            Trace.w(TAG, "Failed to unlike post");
            iCommunityDataReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_missing_contentId)));
        }
    }

    public void downVoteContent(String str, String str2, ICommunityDataReceivedListener iCommunityDataReceivedListener) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(str2)) {
            HttpRequestObject httpRequestObject = new HttpRequestObject();
            httpRequestObject.setUrl(ConsultUrls.getDownVoteUrl(this.mContext));
            httpRequestObject.setRequestMethod("POST");
            httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
            HashMap hashMap = new HashMap();
            hashMap.put("ContentId", str);
            hashMap.put("ContentTypeId", str2);
            String postDataFromMap = getPostDataFromMap(hashMap);
            if (StringUtil.isNotEmpty(postDataFromMap)) {
                httpRequestObject.setRequestBody(postDataFromMap);
            }
            getMapFromMedscapeCommunityRequest(httpRequestObject, iCommunityDataReceivedListener, false);
        } else if (iCommunityDataReceivedListener != null) {
            Trace.w(TAG, "Failed to downVote post");
            iCommunityDataReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_missing_contentId)));
        }
    }

    public void deleteDownVoteContent(String str, String str2, ICommunityDataReceivedListener iCommunityDataReceivedListener) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(str2)) {
            HttpRequestObject httpRequestObject = new HttpRequestObject();
            httpRequestObject.setUrl(ConsultUrls.getDeleteDownVoteUrl(this.mContext));
            httpRequestObject.setRequestMethod("POST");
            httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
            HashMap hashMap = new HashMap();
            hashMap.put("ContentId", str);
            String postDataFromMap = getPostDataFromMap(hashMap);
            if (StringUtil.isNotEmpty(postDataFromMap)) {
                httpRequestObject.setRequestBody(postDataFromMap);
            }
            getDataFromMedscapeCommunityRequest(httpRequestObject, iCommunityDataReceivedListener, false);
        } else if (iCommunityDataReceivedListener != null) {
            Trace.w(TAG, "Failed to delete downVote post");
            iCommunityDataReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_missing_contentId)));
        }
    }

    public void reportAbuseForContentId(String str, String str2, ICommunityDataReceivedListener iCommunityDataReceivedListener) {
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(str2)) {
            HttpRequestObject httpRequestObject = new HttpRequestObject();
            httpRequestObject.setUrl(ConsultUrls.getReportAbuseUrl(this.mContext));
            httpRequestObject.setRequestMethod("POST");
            httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
            HashMap hashMap = new HashMap();
            hashMap.put("ContentId", str);
            hashMap.put("ContentTypeId", str2);
            String postDataFromMap = getPostDataFromMap(hashMap);
            if (StringUtil.isNotEmpty(postDataFromMap)) {
                httpRequestObject.setRequestBody(postDataFromMap);
            }
            getMapFromMedscapeCommunityRequest(httpRequestObject, iCommunityDataReceivedListener, false);
        } else if (iCommunityDataReceivedListener != null) {
            Trace.w(TAG, "Failed to report abuse");
            iCommunityDataReceivedListener.onFailedToReceiveCommunityData(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_missing_contentId)));
        }
    }

    public void deletePost(ConsultPost consultPost, ICommunityRequestCompleteListener iCommunityRequestCompleteListener) {
        if (consultPost == null || !StringUtil.isNotEmpty(consultPost.getPostId())) {
            Trace.w(TAG, "Failed to delete post");
            iCommunityRequestCompleteListener.onCommunityRequestFailed(new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), this.mContext.getString(R.string.consult_error_post_id_missing)));
            return;
        }
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        String deleteThreadUrl = ConsultUrls.getDeleteThreadUrl(this.mContext);
        HashMap hashMap = new HashMap();
        hashMap.put("forumId", mZimbraForumId);
        hashMap.put("threadId", consultPost.getPostId());
        httpRequestObject.setUrl(Util.getUrlFromUrlWithMap(deleteThreadUrl, hashMap));
        httpRequestObject.setRequestMethod("DELETE");
        httpRequestObject.setAuthorization(AuthenticationManager.getInstance(this.mContext).getAuthenticationToken());
        getRawJsonMapFromMedscapeCommunityRequest(httpRequestObject, iCommunityRequestCompleteListener, false);
    }

    public void setFollowPost(final ConsultPost consultPost, final int i, final ICommunityRequestCompleteListener iCommunityRequestCompleteListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                ConsultPost consultPost = consultPost;
                if (consultPost == null || !StringUtil.isNotEmpty(consultPost.getPostId())) {
                    Trace.w(ConsultDataManager.TAG, "Failed to toggle follow post");
                    iCommunityRequestCompleteListener.onCommunityRequestFailed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_post_id_missing)));
                    return;
                }
                HttpRequestObject httpRequestObject = new HttpRequestObject();
                httpRequestObject.setUrl(ConsultUrls.getSubscribeUrl(ConsultDataManager.this.mContext));
                httpRequestObject.setRequestMethod("POST");
                httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                String access$1000 = ConsultDataManager.mZimbraForumId;
                if (consultPost.isHidden()) {
                    access$1000 = ConsultDataManager.mZimbraHiddenForumId;
                }
                HashMap hashMap = new HashMap();
                hashMap.put(Constants.ZIMBRA_FORUM_ID, access$1000);
                hashMap.put("ThreadId", consultPost.getPostId());
                if (i == 3020) {
                    hashMap.put("IsSubscribed", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                } else {
                    hashMap.put("IsSubscribed", "false");
                }
                String postDataFromMap = ConsultDataManager.this.getPostDataFromMap(hashMap);
                if (StringUtil.isNotEmpty(postDataFromMap)) {
                    httpRequestObject.setRequestBody(postDataFromMap);
                }
                ConsultDataManager.this.getRawJsonMapFromMedscapeCommunityRequest(httpRequestObject, iCommunityRequestCompleteListener, false);
            }

            public void onFailure(MedscapeException medscapeException) {
                iCommunityRequestCompleteListener.onCommunityRequestFailed(medscapeException);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000c, code lost:
        r0 = mCurrentConsultUser;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isCurrentUser(com.medscape.android.consult.models.ConsultUser r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x002c
            java.lang.String r0 = r2.getUserId()
            boolean r0 = com.medscape.android.util.StringUtil.isNotEmpty(r0)
            if (r0 == 0) goto L_0x002c
            com.medscape.android.consult.models.ConsultUser r0 = mCurrentConsultUser
            if (r0 == 0) goto L_0x002c
            java.lang.String r0 = r0.getUserId()
            boolean r0 = com.medscape.android.util.StringUtil.isNotEmpty(r0)
            if (r0 == 0) goto L_0x002c
            java.lang.String r2 = r2.getUserId()
            com.medscape.android.consult.models.ConsultUser r0 = mCurrentConsultUser
            java.lang.String r0 = r0.getUserId()
            boolean r2 = r2.equalsIgnoreCase(r0)
            if (r2 == 0) goto L_0x002c
            r2 = 1
            goto L_0x002d
        L_0x002c:
            r2 = 0
        L_0x002d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.consult.managers.ConsultDataManager.isCurrentUser(com.medscape.android.consult.models.ConsultUser):boolean");
    }

    public List<String> getSpecialtyTagList() {
        return mSpecialtyTagList;
    }

    public ConsultUser getCurrentUser() {
        return mCurrentConsultUser;
    }

    public void logOut() {
        mCurrentConsultUser = null;
    }

    public List<ConsultTimeLineFilterItem> getTopLevelFeedsFilterList() {
        return mAdditionalTopLevelFeeds;
    }

    public void getNotificationPreferences(final Context context, final IGetNotificationPreferencesListener iGetNotificationPreferencesListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                String notificationPreferencesUrl = ConsultUrls.getNotificationPreferencesUrl(context);
                HttpRequestObject httpRequestObject = new HttpRequestObject();
                httpRequestObject.setRequestMethod("GET");
                httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                httpRequestObject.setUrl(notificationPreferencesUrl);
                httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
                ConsultDataManager.this.getArrayFromMedscapeCommunityRequest(httpRequestObject, new ICommunityArrayReceivedListener() {
                    public void onCommunityDataReceived(JSONArray jSONArray) {
                        if (iGetNotificationPreferencesListener == null) {
                            Trace.w(ConsultDataManager.TAG, "Listener for full profile was null!");
                        } else if (jSONArray != null) {
                            Trace.i(ConsultDataManager.TAG, "Received community response from server for Notification Preferences");
                            ArrayList<NotificationPreference> notificationsFromCommunityResponse = NotificationPreferencesParser.getNotificationsFromCommunityResponse(jSONArray);
                            if (notificationsFromCommunityResponse != null) {
                                Trace.i(ConsultDataManager.TAG, "Calling back with user data");
                                iGetNotificationPreferencesListener.onNotificationPreferencesReceived(notificationsFromCommunityResponse);
                                return;
                            }
                            new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing));
                            iGetNotificationPreferencesListener.onNotificationPreferencesFailedToReceive();
                        } else {
                            new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_full_profile_fail));
                            iGetNotificationPreferencesListener.onNotificationPreferencesFailedToReceive();
                        }
                    }

                    public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                        if (iGetNotificationPreferencesListener != null) {
                            iGetNotificationPreferencesListener.onNotificationPreferencesFailedToReceive();
                        } else {
                            Trace.w(ConsultDataManager.TAG, "Listener for full profile was null!");
                        }
                    }
                }, false);
            }

            public void onFailure(MedscapeException medscapeException) {
                IGetNotificationPreferencesListener iGetNotificationPreferencesListener = iGetNotificationPreferencesListener;
                if (iGetNotificationPreferencesListener != null) {
                    iGetNotificationPreferencesListener.onNotificationPreferencesFailedToReceive();
                }
            }
        });
    }

    public void updateNotificationPreferences(final Context context, final ArrayList<NotificationPreference> arrayList, final IUpdateNotificationPreferencesListener iUpdateNotificationPreferencesListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                String updateNotificationPreferencesUrl = ConsultUrls.getUpdateNotificationPreferencesUrl(context);
                HttpRequestObject httpRequestObject = new HttpRequestObject();
                httpRequestObject.setRequestMethod("POST");
                httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                httpRequestObject.setUrl(updateNotificationPreferencesUrl);
                httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
                httpRequestObject.setRequestBody(NotificationPreferencesParser.getPreferencesJSONArrayfromObj(arrayList).toString());
                ConsultDataManager.this.getDataFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                    public void onCommunityDataReceived(JSONObject jSONObject) {
                        if (iUpdateNotificationPreferencesListener == null) {
                            Trace.w(ConsultDataManager.TAG, "Listener for full profile was null!");
                        } else if (jSONObject != null) {
                            Trace.i(ConsultDataManager.TAG, "Received community response from server for Notification Preferences");
                            if (NotificationPreferencesParser.getUpdatedStatusFromCommunityResponse(jSONObject)) {
                                iUpdateNotificationPreferencesListener.onNotificationPreferencesUpdated();
                                return;
                            }
                            new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing));
                            iUpdateNotificationPreferencesListener.onNotificationPreferencesFailedToUpdated();
                        } else {
                            new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_full_profile_fail));
                            iUpdateNotificationPreferencesListener.onNotificationPreferencesFailedToUpdated();
                        }
                    }

                    public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                        if (iUpdateNotificationPreferencesListener != null) {
                            iUpdateNotificationPreferencesListener.onNotificationPreferencesFailedToUpdated();
                        } else {
                            Trace.w(ConsultDataManager.TAG, "Listener for full profile was null!");
                        }
                    }
                }, false);
            }

            public void onFailure(MedscapeException medscapeException) {
                IUpdateNotificationPreferencesListener iUpdateNotificationPreferencesListener = iUpdateNotificationPreferencesListener;
                if (iUpdateNotificationPreferencesListener != null) {
                    iUpdateNotificationPreferencesListener.onNotificationPreferencesFailedToUpdated();
                }
            }
        });
    }

    public void getMentionableToken(final String str, final IMentionTokenListenerByID iMentionTokenListenerByID) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                if (StringUtil.isNotEmpty(str)) {
                    try {
                        new GetMentionTokenByUserID(ConsultDataManager.this.mContext, str, ConsultDataManager.mZimbraGroupId, ConsultDataManager.mZimbraForumId, new IMentionTokenListenerByID() {
                            public void onRecievingMentionToken(String str) {
                                iMentionTokenListenerByID.onRecievingMentionToken(str);
                            }

                            public void onErrorRecievingMentionToken() {
                                iMentionTokenListenerByID.onErrorRecievingMentionToken();
                            }
                        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    } catch (Exception unused) {
                        Trace.w(ConsultDataManager.TAG, "Failed to search for mentionables by UserID");
                        iMentionTokenListenerByID.onErrorRecievingMentionToken();
                    }
                } else {
                    iMentionTokenListenerByID.onErrorRecievingMentionToken();
                }
            }

            public void onFailure(MedscapeException medscapeException) {
                iMentionTokenListenerByID.onErrorRecievingMentionToken();
            }
        });
    }

    /* access modifiers changed from: private */
    public void waitforAuthFailed(IAuthFlowCompletedListener iAuthFlowCompletedListener, MedscapeException medscapeException) {
        this.mGetNecessaryConsultInfoTask = null;
        iAuthFlowCompletedListener.onFailure(medscapeException);
        while (!this.mAuthCompletedListeners.isEmpty()) {
            IAuthFlowCompletedListener poll = this.mAuthCompletedListeners.poll();
            if (poll != null) {
                Trace.w(TAG, "Failed to receive initial data. Showing an alert");
                poll.onFailure(medscapeException);
            }
        }
    }

    public void getSponsoredAdPost(final String str, final IPostReceivedListener iPostReceivedListener) {
        waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                if (StringUtil.isNotEmpty(str)) {
                    String postUrl = ConsultUrls.getPostUrl(ConsultDataManager.this.mContext);
                    HashMap hashMap = new HashMap();
                    hashMap.put("ThreadId", str);
                    hashMap.put(Constants.ZIMBRA_GROUP_ID, "4");
                    hashMap.put(Constants.ZIMBRA_FORUM_ID, ExifInterface.GPS_MEASUREMENT_3D);
                    String urlFromUrlWithMap = Util.getUrlFromUrlWithMap(postUrl, hashMap);
                    HttpRequestObject httpRequestObject = new HttpRequestObject();
                    httpRequestObject.setUrl(urlFromUrlWithMap);
                    httpRequestObject.setRequestMethod("GET");
                    httpRequestObject.setAuthorization(AuthenticationManager.getInstance(ConsultDataManager.this.mContext).getAuthenticationToken());
                    ConsultDataManager.this.getMapFromMedscapeCommunityRequest(httpRequestObject, new ICommunityDataReceivedListener() {
                        public void onCommunityDataReceived(JSONObject jSONObject) {
                            if (jSONObject != null) {
                                ConsultPost parseCommunityResponse = CommunityResponseParser.parseCommunityResponse(jSONObject, ConsultDataManager.mZimbraHiddenForumId);
                                if (parseCommunityResponse.getPoster() == null) {
                                    parseCommunityResponse.setPoster(new ConsultUser());
                                }
                                parseCommunityResponse.getPoster().setCanShowSponsoredLabel(true);
                                parseCommunityResponse.setFromAD(true);
                                iPostReceivedListener.onPostReceived(parseCommunityResponse);
                                return;
                            }
                            Trace.i(ConsultDataManager.TAG, "Post object from server was null");
                            iPostReceivedListener.onPostRequestFailed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_failed_parsing)));
                        }

                        public void onFailedToReceiveCommunityData(MedscapeException medscapeException) {
                            iPostReceivedListener.onPostRequestFailed(medscapeException);
                        }
                    }, false);
                    return;
                }
                iPostReceivedListener.onPostRequestFailed(new MedscapeException(ConsultDataManager.this.mContext.getString(R.string.consult_error_message_title), ConsultDataManager.this.mContext.getString(R.string.consult_error_post_id_missing)));
            }

            public void onFailure(MedscapeException medscapeException) {
                iPostReceivedListener.onPostRequestFailed(medscapeException);
            }
        });
    }
}
