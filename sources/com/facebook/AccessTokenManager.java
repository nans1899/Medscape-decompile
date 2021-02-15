package com.facebook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestBatch;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.medscape.android.Constants;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public final class AccessTokenManager {
    public static final String ACTION_CURRENT_ACCESS_TOKEN_CHANGED = "com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED";
    public static final String EXTRA_NEW_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN";
    public static final String EXTRA_OLD_ACCESS_TOKEN = "com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN";
    private static final String ME_PERMISSIONS_GRAPH_PATH = "me/permissions";
    public static final String SHARED_PREFERENCES_NAME = "com.facebook.AccessTokenManager.SharedPreferences";
    public static final String TAG = "AccessTokenManager";
    private static final String TOKEN_EXTEND_GRAPH_PATH = "oauth/access_token";
    private static final int TOKEN_EXTEND_RETRY_SECONDS = 3600;
    private static final int TOKEN_EXTEND_THRESHOLD_SECONDS = 86400;
    private static volatile AccessTokenManager instance;
    private final AccessTokenCache accessTokenCache;
    private AccessToken currentAccessToken;
    private Date lastAttemptedTokenExtendDate = new Date(0);
    private final LocalBroadcastManager localBroadcastManager;
    /* access modifiers changed from: private */
    public AtomicBoolean tokenRefreshInProgress = new AtomicBoolean(false);

    AccessTokenManager(LocalBroadcastManager localBroadcastManager2, AccessTokenCache accessTokenCache2) {
        Validate.notNull(localBroadcastManager2, "localBroadcastManager");
        Validate.notNull(accessTokenCache2, "accessTokenCache");
        this.localBroadcastManager = localBroadcastManager2;
        this.accessTokenCache = accessTokenCache2;
    }

    static AccessTokenManager getInstance() {
        if (instance == null) {
            synchronized (AccessTokenManager.class) {
                if (instance == null) {
                    instance = new AccessTokenManager(LocalBroadcastManager.getInstance(FacebookSdk.getApplicationContext()), new AccessTokenCache());
                }
            }
        }
        return instance;
    }

    /* access modifiers changed from: package-private */
    public AccessToken getCurrentAccessToken() {
        return this.currentAccessToken;
    }

    /* access modifiers changed from: package-private */
    public boolean loadCurrentAccessToken() {
        AccessToken load = this.accessTokenCache.load();
        if (load == null) {
            return false;
        }
        setCurrentAccessToken(load, false);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void setCurrentAccessToken(AccessToken accessToken) {
        setCurrentAccessToken(accessToken, true);
    }

    private void setCurrentAccessToken(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.currentAccessToken;
        this.currentAccessToken = accessToken;
        this.tokenRefreshInProgress.set(false);
        this.lastAttemptedTokenExtendDate = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.accessTokenCache.save(accessToken);
            } else {
                this.accessTokenCache.clear();
                Utility.clearFacebookCookies(FacebookSdk.getApplicationContext());
            }
        }
        if (!Utility.areObjectsEqual(accessToken2, accessToken)) {
            sendCurrentAccessTokenChangedBroadcastIntent(accessToken2, accessToken);
            setTokenExpirationBroadcastAlarm();
        }
    }

    /* access modifiers changed from: package-private */
    public void currentAccessTokenChanged() {
        AccessToken accessToken = this.currentAccessToken;
        sendCurrentAccessTokenChangedBroadcastIntent(accessToken, accessToken);
    }

    private void sendCurrentAccessTokenChangedBroadcastIntent(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent(FacebookSdk.getApplicationContext(), CurrentAccessTokenExpirationBroadcastReceiver.class);
        intent.setAction(ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
        intent.putExtra(EXTRA_OLD_ACCESS_TOKEN, accessToken);
        intent.putExtra(EXTRA_NEW_ACCESS_TOKEN, accessToken2);
        this.localBroadcastManager.sendBroadcast(intent);
    }

    private void setTokenExpirationBroadcastAlarm() {
        Context applicationContext = FacebookSdk.getApplicationContext();
        AccessToken currentAccessToken2 = AccessToken.getCurrentAccessToken();
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (AccessToken.isCurrentAccessTokenActive() && currentAccessToken2.getExpires() != null && alarmManager != null) {
            Intent intent = new Intent(applicationContext, CurrentAccessTokenExpirationBroadcastReceiver.class);
            intent.setAction(ACTION_CURRENT_ACCESS_TOKEN_CHANGED);
            alarmManager.set(1, currentAccessToken2.getExpires().getTime(), PendingIntent.getBroadcast(applicationContext, 0, intent, 0));
        }
    }

    /* access modifiers changed from: package-private */
    public void extendAccessTokenIfNeeded() {
        if (shouldExtendAccessToken()) {
            refreshCurrentAccessToken((AccessToken.AccessTokenRefreshCallback) null);
        }
    }

    private boolean shouldExtendAccessToken() {
        if (this.currentAccessToken == null) {
            return false;
        }
        Long valueOf = Long.valueOf(new Date().getTime());
        if (!this.currentAccessToken.getSource().canExtendToken() || valueOf.longValue() - this.lastAttemptedTokenExtendDate.getTime() <= Constants.HOUR_IN_MILLIS || valueOf.longValue() - this.currentAccessToken.getLastRefresh().getTime() <= Constants.DAY_IN_MILLIS) {
            return false;
        }
        return true;
    }

    private static GraphRequest createGrantedPermissionsRequest(AccessToken accessToken, GraphRequest.Callback callback) {
        return new GraphRequest(accessToken, ME_PERMISSIONS_GRAPH_PATH, new Bundle(), HttpMethod.GET, callback);
    }

    private static GraphRequest createExtendAccessTokenRequest(AccessToken accessToken, GraphRequest.Callback callback) {
        Bundle bundle = new Bundle();
        bundle.putString("grant_type", "fb_extend_sso_token");
        return new GraphRequest(accessToken, TOKEN_EXTEND_GRAPH_PATH, bundle, HttpMethod.GET, callback);
    }

    private static class RefreshResult {
        public String accessToken;
        public Long dataAccessExpirationTime;
        public int expiresAt;

        private RefreshResult() {
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshCurrentAccessToken(final AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    AccessTokenManager.this.refreshCurrentAccessTokenImpl(accessTokenRefreshCallback);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void refreshCurrentAccessTokenImpl(AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback) {
        AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback2 = accessTokenRefreshCallback;
        AccessToken accessToken = this.currentAccessToken;
        if (accessToken == null) {
            if (accessTokenRefreshCallback2 != null) {
                accessTokenRefreshCallback2.OnTokenRefreshFailed(new FacebookException("No current access token to refresh"));
            }
        } else if (this.tokenRefreshInProgress.compareAndSet(false, true)) {
            this.lastAttemptedTokenExtendDate = new Date();
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            HashSet hashSet3 = new HashSet();
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final RefreshResult refreshResult = new RefreshResult();
            final AtomicBoolean atomicBoolean2 = atomicBoolean;
            AnonymousClass2 r10 = r0;
            final HashSet hashSet4 = hashSet;
            final HashSet hashSet5 = hashSet2;
            HashSet hashSet6 = hashSet2;
            final HashSet hashSet7 = hashSet3;
            AnonymousClass2 r0 = new GraphRequest.Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                    JSONArray optJSONArray;
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    if (jSONObject != null && (optJSONArray = jSONObject.optJSONArray("data")) != null) {
                        atomicBoolean2.set(true);
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                String optString = optJSONObject.optString("permission");
                                String optString2 = optJSONObject.optString("status");
                                if (!Utility.isNullOrEmpty(optString) && !Utility.isNullOrEmpty(optString2)) {
                                    String lowerCase = optString2.toLowerCase(Locale.US);
                                    if (lowerCase.equals("granted")) {
                                        hashSet4.add(optString);
                                    } else if (lowerCase.equals("declined")) {
                                        hashSet5.add(optString);
                                    } else if (lowerCase.equals("expired")) {
                                        hashSet7.add(optString);
                                    } else {
                                        Log.w(AccessTokenManager.TAG, "Unexpected status: " + lowerCase);
                                    }
                                }
                            }
                        }
                    }
                }
            };
            GraphRequestBatch graphRequestBatch = new GraphRequestBatch(createGrantedPermissionsRequest(accessToken, r10), createExtendAccessTokenRequest(accessToken, new GraphRequest.Callback() {
                public void onCompleted(GraphResponse graphResponse) {
                    JSONObject jSONObject = graphResponse.getJSONObject();
                    if (jSONObject != null) {
                        refreshResult.accessToken = jSONObject.optString("access_token");
                        refreshResult.expiresAt = jSONObject.optInt("expires_at");
                        refreshResult.dataAccessExpirationTime = Long.valueOf(jSONObject.optLong(AccessToken.DATA_ACCESS_EXPIRATION_TIME));
                    }
                }
            }));
            final AccessToken accessToken2 = accessToken;
            final AccessToken.AccessTokenRefreshCallback accessTokenRefreshCallback3 = accessTokenRefreshCallback;
            final AtomicBoolean atomicBoolean3 = atomicBoolean;
            final RefreshResult refreshResult2 = refreshResult;
            final HashSet hashSet8 = hashSet;
            final HashSet hashSet9 = hashSet6;
            final HashSet hashSet10 = hashSet3;
            graphRequestBatch.addCallback(new GraphRequestBatch.Callback() {
                /* JADX WARNING: Unknown top exception splitter block from list: {B:18:0x0053=Splitter:B:18:0x0053, B:51:0x010d=Splitter:B:51:0x010d} */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onBatchCompleted(com.facebook.GraphRequestBatch r19) {
                    /*
                        r18 = this;
                        r1 = r18
                        r2 = 0
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.getInstance()     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessToken r0 = r0.getCurrentAccessToken()     // Catch:{ all -> 0x0129 }
                        if (r0 == 0) goto L_0x010d
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.getInstance()     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessToken r0 = r0.getCurrentAccessToken()     // Catch:{ all -> 0x0129 }
                        java.lang.String r0 = r0.getUserId()     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessToken r4 = r2     // Catch:{ all -> 0x0129 }
                        java.lang.String r4 = r4.getUserId()     // Catch:{ all -> 0x0129 }
                        if (r0 == r4) goto L_0x0023
                        goto L_0x010d
                    L_0x0023:
                        java.util.concurrent.atomic.AtomicBoolean r0 = r4     // Catch:{ all -> 0x0129 }
                        boolean r0 = r0.get()     // Catch:{ all -> 0x0129 }
                        if (r0 != 0) goto L_0x0053
                        com.facebook.AccessTokenManager$RefreshResult r0 = r5     // Catch:{ all -> 0x0129 }
                        java.lang.String r0 = r0.accessToken     // Catch:{ all -> 0x0129 }
                        if (r0 != 0) goto L_0x0053
                        com.facebook.AccessTokenManager$RefreshResult r0 = r5     // Catch:{ all -> 0x0129 }
                        int r0 = r0.expiresAt     // Catch:{ all -> 0x0129 }
                        if (r0 != 0) goto L_0x0053
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3     // Catch:{ all -> 0x0129 }
                        if (r0 == 0) goto L_0x0047
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3     // Catch:{ all -> 0x0129 }
                        com.facebook.FacebookException r4 = new com.facebook.FacebookException     // Catch:{ all -> 0x0129 }
                        java.lang.String r5 = "Failed to refresh access token"
                        r4.<init>((java.lang.String) r5)     // Catch:{ all -> 0x0129 }
                        r0.OnTokenRefreshFailed(r4)     // Catch:{ all -> 0x0129 }
                    L_0x0047:
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.tokenRefreshInProgress
                        r0.set(r2)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3
                        return
                    L_0x0053:
                        com.facebook.AccessToken r15 = new com.facebook.AccessToken     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessTokenManager$RefreshResult r0 = r5     // Catch:{ all -> 0x0129 }
                        java.lang.String r0 = r0.accessToken     // Catch:{ all -> 0x0129 }
                        if (r0 == 0) goto L_0x0060
                        com.facebook.AccessTokenManager$RefreshResult r0 = r5     // Catch:{ all -> 0x0129 }
                        java.lang.String r0 = r0.accessToken     // Catch:{ all -> 0x0129 }
                        goto L_0x0066
                    L_0x0060:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x0129 }
                        java.lang.String r0 = r0.getToken()     // Catch:{ all -> 0x0129 }
                    L_0x0066:
                        r5 = r0
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x0129 }
                        java.lang.String r6 = r0.getApplicationId()     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x0129 }
                        java.lang.String r7 = r0.getUserId()     // Catch:{ all -> 0x0129 }
                        java.util.concurrent.atomic.AtomicBoolean r0 = r4     // Catch:{ all -> 0x0129 }
                        boolean r0 = r0.get()     // Catch:{ all -> 0x0129 }
                        if (r0 == 0) goto L_0x007e
                        java.util.Set r0 = r6     // Catch:{ all -> 0x0129 }
                        goto L_0x0084
                    L_0x007e:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x0129 }
                        java.util.Set r0 = r0.getPermissions()     // Catch:{ all -> 0x0129 }
                    L_0x0084:
                        r8 = r0
                        java.util.concurrent.atomic.AtomicBoolean r0 = r4     // Catch:{ all -> 0x0129 }
                        boolean r0 = r0.get()     // Catch:{ all -> 0x0129 }
                        if (r0 == 0) goto L_0x0090
                        java.util.Set r0 = r7     // Catch:{ all -> 0x0129 }
                        goto L_0x0096
                    L_0x0090:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x0129 }
                        java.util.Set r0 = r0.getDeclinedPermissions()     // Catch:{ all -> 0x0129 }
                    L_0x0096:
                        r9 = r0
                        java.util.concurrent.atomic.AtomicBoolean r0 = r4     // Catch:{ all -> 0x0129 }
                        boolean r0 = r0.get()     // Catch:{ all -> 0x0129 }
                        if (r0 == 0) goto L_0x00a2
                        java.util.Set r0 = r8     // Catch:{ all -> 0x0129 }
                        goto L_0x00a8
                    L_0x00a2:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x0129 }
                        java.util.Set r0 = r0.getExpiredPermissions()     // Catch:{ all -> 0x0129 }
                    L_0x00a8:
                        r10 = r0
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessTokenSource r11 = r0.getSource()     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessTokenManager$RefreshResult r0 = r5     // Catch:{ all -> 0x0129 }
                        int r0 = r0.expiresAt     // Catch:{ all -> 0x0129 }
                        r12 = 1000(0x3e8, double:4.94E-321)
                        if (r0 == 0) goto L_0x00c4
                        java.util.Date r0 = new java.util.Date     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessTokenManager$RefreshResult r4 = r5     // Catch:{ all -> 0x0129 }
                        int r4 = r4.expiresAt     // Catch:{ all -> 0x0129 }
                        long r3 = (long) r4     // Catch:{ all -> 0x0129 }
                        long r3 = r3 * r12
                        r0.<init>(r3)     // Catch:{ all -> 0x0129 }
                        goto L_0x00ca
                    L_0x00c4:
                        com.facebook.AccessToken r0 = r2     // Catch:{ all -> 0x0129 }
                        java.util.Date r0 = r0.getExpires()     // Catch:{ all -> 0x0129 }
                    L_0x00ca:
                        java.util.Date r3 = new java.util.Date     // Catch:{ all -> 0x0129 }
                        r3.<init>()     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessTokenManager$RefreshResult r4 = r5     // Catch:{ all -> 0x0129 }
                        java.lang.Long r4 = r4.dataAccessExpirationTime     // Catch:{ all -> 0x0129 }
                        if (r4 == 0) goto L_0x00e5
                        java.util.Date r4 = new java.util.Date     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessTokenManager$RefreshResult r14 = r5     // Catch:{ all -> 0x0129 }
                        java.lang.Long r14 = r14.dataAccessExpirationTime     // Catch:{ all -> 0x0129 }
                        long r16 = r14.longValue()     // Catch:{ all -> 0x0129 }
                        long r12 = r12 * r16
                        r4.<init>(r12)     // Catch:{ all -> 0x0129 }
                        goto L_0x00eb
                    L_0x00e5:
                        com.facebook.AccessToken r4 = r2     // Catch:{ all -> 0x0129 }
                        java.util.Date r4 = r4.getDataAccessExpirationTime()     // Catch:{ all -> 0x0129 }
                    L_0x00eb:
                        r14 = r4
                        r4 = r15
                        r12 = r0
                        r13 = r3
                        r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x0129 }
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.getInstance()     // Catch:{ all -> 0x010a }
                        r0.setCurrentAccessToken(r15)     // Catch:{ all -> 0x010a }
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.tokenRefreshInProgress
                        r0.set(r2)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3
                        if (r0 == 0) goto L_0x0109
                        r0.OnTokenRefreshed(r15)
                    L_0x0109:
                        return
                    L_0x010a:
                        r0 = move-exception
                        r3 = r15
                        goto L_0x012b
                    L_0x010d:
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3     // Catch:{ all -> 0x0129 }
                        if (r0 == 0) goto L_0x011d
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3     // Catch:{ all -> 0x0129 }
                        com.facebook.FacebookException r3 = new com.facebook.FacebookException     // Catch:{ all -> 0x0129 }
                        java.lang.String r4 = "No current access token to refresh"
                        r3.<init>((java.lang.String) r4)     // Catch:{ all -> 0x0129 }
                        r0.OnTokenRefreshFailed(r3)     // Catch:{ all -> 0x0129 }
                    L_0x011d:
                        com.facebook.AccessTokenManager r0 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r0 = r0.tokenRefreshInProgress
                        r0.set(r2)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r0 = r3
                        return
                    L_0x0129:
                        r0 = move-exception
                        r3 = 0
                    L_0x012b:
                        com.facebook.AccessTokenManager r4 = com.facebook.AccessTokenManager.this
                        java.util.concurrent.atomic.AtomicBoolean r4 = r4.tokenRefreshInProgress
                        r4.set(r2)
                        com.facebook.AccessToken$AccessTokenRefreshCallback r2 = r3
                        if (r2 == 0) goto L_0x013d
                        if (r3 == 0) goto L_0x013d
                        r2.OnTokenRefreshed(r3)
                    L_0x013d:
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessTokenManager.AnonymousClass4.onBatchCompleted(com.facebook.GraphRequestBatch):void");
                }
            });
            graphRequestBatch.executeAsync();
        } else if (accessTokenRefreshCallback2 != null) {
            accessTokenRefreshCallback2.OnTokenRefreshFailed(new FacebookException("Refresh already in progress"));
        }
    }
}
