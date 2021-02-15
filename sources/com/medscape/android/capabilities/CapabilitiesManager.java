package com.medscape.android.capabilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.capabilities.models.CapabilitiesFeature;
import com.medscape.android.consult.activity.ConsultProfileActivity;
import com.medscape.android.consult.activity.ConsultTermsActivity;
import com.medscape.android.consult.activity.ConsultTimelineActivity;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.interfaces.IAuthFlowCompletedListener;
import com.medscape.android.interfaces.ICapabilitiesReceivedListener;
import com.medscape.android.task.GetCapabilitiesTask;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.SerializerUtils;
import com.wbmd.wbmdcommons.caching.CacheProvider;
import com.wbmd.wbmdcommons.caching.ICacheProvider;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CapabilitiesManager implements ICapabilitiesReceivedListener {
    private static final String CAPABILITES_CONSULT = "consult";
    private static final String CAPABILITES_PROCLIVITY = "proclivity";
    private static final String CAPABILITES_SHARETHROUGH = "sharethrough";
    private static final String CAPABILITES_TOPFEED = "topFeed";
    private static final String CAPABILITIES_FEATURE_CACHE_KEY = "capabilities_features";
    private static final String CAPABILITIES_LEADERBOARD = "leaderboard";
    public static final String CAPABILITIES_MEDIA_NET = "mediaNet";
    static String TAG = CapabilitiesManager.class.getSimpleName();
    private static Map<String, CapabilitiesFeature> mFeatureMap;
    private static Calendar mLastUpdateDate;
    private static CapabilitiesManager mManager;
    private Context mContext;
    private GetCapabilitiesTask mGetCapabilitiesTask;

    public static CapabilitiesManager getInstance(Context context) {
        if (mManager == null) {
            mManager = new CapabilitiesManager();
        }
        mManager.setContext(context);
        return mManager;
    }

    private void setContext(Context context) {
        this.mContext = context;
    }

    public void setFeatureMap(Map<String, CapabilitiesFeature> map) {
        mFeatureMap = map;
    }

    public Map<String, CapabilitiesFeature> getFeatureMap() {
        return mFeatureMap;
    }

    public void getCapabilitiesFromServer() {
        GetCapabilitiesTask getCapabilitiesTask = this.mGetCapabilitiesTask;
        if (getCapabilitiesTask != null) {
            if (getCapabilitiesTask.getStatus() != AsyncTask.Status.RUNNING) {
                if (this.mGetCapabilitiesTask.getStatus() == AsyncTask.Status.FINISHED) {
                    this.mGetCapabilitiesTask = new GetCapabilitiesTask(this.mContext, this);
                }
            } else {
                return;
            }
        }
        String remoteCapabilitiesUrl = getRemoteCapabilitiesUrl(this.mContext);
        if (remoteCapabilitiesUrl != null) {
            if (this.mGetCapabilitiesTask == null) {
                this.mGetCapabilitiesTask = new GetCapabilitiesTask(this.mContext, this);
            }
            String[] strArr = {remoteCapabilitiesUrl};
            GetCapabilitiesTask getCapabilitiesTask2 = this.mGetCapabilitiesTask;
            if (getCapabilitiesTask2 != null && getCapabilitiesTask2.getStatus() != AsyncTask.Status.RUNNING) {
                try {
                    this.mGetCapabilitiesTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, strArr);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getRemoteCapabilitiesUrl(android.content.Context r5) {
        /*
            com.wbmd.environment.EnvironmentManager r0 = new com.wbmd.environment.EnvironmentManager
            r0.<init>()
            java.lang.String r1 = "module_feed"
            java.lang.String r0 = r0.getEnvironmentWithDefault(r5, r1)
            int r1 = r0.hashCode()
            r2 = 1
            r3 = 0
            r4 = 2
            switch(r1) {
                case 68759055: goto L_0x0052;
                case 206969445: goto L_0x0048;
                case 446124970: goto L_0x003e;
                case 568961724: goto L_0x0034;
                case 568961725: goto L_0x002a;
                case 568961726: goto L_0x0020;
                case 1680909289: goto L_0x0016;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x005c
        L_0x0016:
            java.lang.String r1 = "environment_dev"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 1
            goto L_0x005d
        L_0x0020:
            java.lang.String r1 = "environment_qa02"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 5
            goto L_0x005d
        L_0x002a:
            java.lang.String r1 = "environment_qa01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 4
            goto L_0x005d
        L_0x0034:
            java.lang.String r1 = "environment_qa00"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 3
            goto L_0x005d
        L_0x003e:
            java.lang.String r1 = "environment_dev01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 6
            goto L_0x005d
        L_0x0048:
            java.lang.String r1 = "environment_production"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 0
            goto L_0x005d
        L_0x0052:
            java.lang.String r1 = "environment_staging"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x005c
            r0 = 2
            goto L_0x005d
        L_0x005c:
            r0 = -1
        L_0x005d:
            switch(r0) {
                case 0: goto L_0x0074;
                case 1: goto L_0x0071;
                case 2: goto L_0x006e;
                case 3: goto L_0x006b;
                case 4: goto L_0x0068;
                case 5: goto L_0x0065;
                case 6: goto L_0x0062;
                default: goto L_0x0060;
            }
        L_0x0060:
            r0 = 0
            goto L_0x0076
        L_0x0062:
            java.lang.String r0 = "http://www.dev01.medscape.com/noscan/mobileapp/public/native-ipad/"
            goto L_0x0076
        L_0x0065:
            java.lang.String r0 = "http://www.qa02.medscape.com/noscan/mobileapp/public/native-ipad/"
            goto L_0x0076
        L_0x0068:
            java.lang.String r0 = "http://www.qa01.medscape.com/noscan/mobileapp/public/native-ipad/"
            goto L_0x0076
        L_0x006b:
            java.lang.String r0 = "http://www.qa00.medscape.com/noscan/mobileapp/public/native-ipad/"
            goto L_0x0076
        L_0x006e:
            java.lang.String r0 = "http://www.staging.medscape.com/noscan/mobileapp/public/native-ipad/"
            goto L_0x0076
        L_0x0071:
            java.lang.String r0 = "http://www.staging.medscape.com/noscan/mobileapp/public/native-ipad2/"
            goto L_0x0076
        L_0x0074:
            java.lang.String r0 = "https://www.medscape.com/noscan/mobileapp/public/native-ipad/"
        L_0x0076:
            if (r0 == 0) goto L_0x009b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.String r4 = com.wbmd.wbmdcommons.utils.Util.getPhoneOSVersion()
            r0[r3] = r4
            java.lang.String r5 = com.medscape.android.util.Util.getApplicationVersion(r5)
            r0[r2] = r5
            java.lang.String r5 = "config/Capabilities.json?devicetype=android&osversion=%s&appversion=%s&src=medscapeapp-android"
            java.lang.String r5 = java.lang.String.format(r5, r0)
            r1.append(r5)
            java.lang.String r0 = r1.toString()
        L_0x009b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.capabilities.CapabilitiesManager.getRemoteCapabilitiesUrl(android.content.Context):java.lang.String");
    }

    public boolean isConsultFeatureAvailable() {
        CapabilitiesFeature consultFeature = getConsultFeature();
        return consultFeature != null && consultFeature.getCapabilitiesStatus() == 2001;
    }

    public boolean isProclivityFeatureAvailable() {
        CapabilitiesFeature featureWithName = getFeatureWithName(CAPABILITES_PROCLIVITY);
        return featureWithName == null || featureWithName.getCapabilitiesStatus() != 2002;
    }

    public boolean isSharethroughFeatureAvailable() {
        CapabilitiesFeature featureWithName = getFeatureWithName(CAPABILITES_SHARETHROUGH);
        return featureWithName == null || featureWithName.getCapabilitiesStatus() == 2001;
    }

    public boolean isMediaNetFeatureAvailable() {
        CapabilitiesFeature featureWithName = getFeatureWithName(CAPABILITIES_MEDIA_NET);
        return featureWithName == null || featureWithName.getCapabilitiesStatus() != 2002;
    }

    private CapabilitiesFeature getConsultFeature() {
        return getFeatureWithName("consult");
    }

    private CapabilitiesFeature getFeatureWithName(String str) {
        updateCapabilitiesIfNecessary();
        Map<String, CapabilitiesFeature> map = mFeatureMap;
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    public void updateCapabilitiesIfNecessary() {
        if (mFeatureMap == null) {
            setFeatureMap(getFeaturesFromCache());
        }
        if (shouldUpdateCapabilities()) {
            getCapabilitiesFromServer();
        }
    }

    private boolean shouldUpdateCapabilities() {
        if (mFeatureMap == null || mLastUpdateDate == null || Calendar.getInstance().getTimeInMillis() - mLastUpdateDate.getTimeInMillis() > Constants.BG_UPDATE_TIME_IN_MILLIS) {
            return true;
        }
        return false;
    }

    private Map<String, CapabilitiesFeature> getFeaturesFromCache() {
        CacheProvider.Entry entry;
        Object deserialize;
        try {
            ICacheProvider cacheProvider = MedscapeApplication.getCacheProvider();
            if (cacheProvider == null || (entry = cacheProvider.get(CAPABILITIES_FEATURE_CACHE_KEY, true)) == null || (deserialize = SerializerUtils.deserialize(entry.data)) == null || !(deserialize instanceof Map)) {
                return null;
            }
            return (Map) deserialize;
        } catch (Exception unused) {
            return null;
        }
    }

    public void onCapabilitiesReceived(Map<String, CapabilitiesFeature> map) {
        setFeatureMap(updateCapabilitiesWithCapabilitiesFromServer(map));
        cacheResponse(mFeatureMap);
        LocalBroadcastManager.getInstance(this.mContext).sendBroadcast(new Intent(Constants.HOMESCREEN_BROADCAST_UPDATE));
        mLastUpdateDate = Calendar.getInstance();
    }

    public Map<String, CapabilitiesFeature> updateCapabilitiesWithCapabilitiesFromServer(Map<String, CapabilitiesFeature> map) {
        Map<String, CapabilitiesFeature> map2 = mFeatureMap;
        if (map2 == null) {
            map2 = new HashMap<>();
        }
        if (map != null) {
            for (String next : map.keySet()) {
                CapabilitiesFeature capabilitiesFeature = map.get(next);
                CapabilitiesFeature capabilitiesFeature2 = map2.get(next);
                if (capabilitiesFeature != null) {
                    if (capabilitiesFeature2 != null) {
                        capabilitiesFeature2.updateFeatureWithServerFeature(capabilitiesFeature);
                        map2.put(next, capabilitiesFeature2);
                    } else {
                        map2.put(next, capabilitiesFeature);
                    }
                }
            }
        }
        return map2;
    }

    private void cacheResponse(Map<String, CapabilitiesFeature> map) {
        ICacheProvider cacheProvider = MedscapeApplication.getCacheProvider();
        if (cacheProvider != null) {
            CacheProvider.Entry entry = new CacheProvider.Entry();
            entry.data = SerializerUtils.serialize(map);
            entry.key = CAPABILITIES_FEATURE_CACHE_KEY;
            cacheProvider.put(entry);
        }
    }

    public void startConsultActivity(Activity activity, boolean z) {
        ConsultDataManager.mForceUpdateConfig = true;
        ConsultDataManager.mAdditionalTopLevelFeeds = null;
        CapabilitiesFeature consultFeature = getConsultFeature();
        if (!shouldDisplayEulaForFeature(consultFeature)) {
            updateConsultLastAcceptedEulaVersionToCurrent();
            if (this.mContext != null) {
                if (z) {
                    OmnitureManager.get().trackModule(this.mContext, "other", Constants.OMNITURE_MMODULE_REFMENU, "consult", (Map<String, Object>) null);
                    OmnitureManager.get().markModule("consultnav", "tap", (Map<String, Object>) null);
                }
                Intent intent = new Intent(this.mContext, ConsultTimelineActivity.class);
                if (activity != null) {
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, 0);
                }
            }
        } else if (this.mContext != null) {
            Intent intent2 = new Intent(this.mContext, ConsultTermsActivity.class);
            intent2.putExtra("com.medscape.android.EXTRA_WEBVIEW_URL", consultFeature.getEulaUrl());
            this.mContext.startActivity(intent2);
        }
    }

    public void startConsultActivity(String str) {
        ConsultDataManager.mForceUpdateConfig = true;
        ConsultDataManager.mAdditionalTopLevelFeeds = null;
        CapabilitiesFeature consultFeature = getConsultFeature();
        if (!shouldDisplayEulaForFeature(consultFeature)) {
            updateConsultLastAcceptedEulaVersionToCurrent();
            if (this.mContext != null) {
                Intent intent = new Intent(this.mContext, ConsultTimelineActivity.class);
                intent.putExtra(Constants.EXTRA_CONSULT_DEEPLINK_URL, str);
                this.mContext.startActivity(intent);
            }
        } else if (this.mContext != null) {
            Intent intent2 = new Intent(this.mContext, ConsultTermsActivity.class);
            intent2.setFlags(604045312);
            intent2.putExtra("com.medscape.android.EXTRA_WEBVIEW_URL", consultFeature.getEulaUrl());
            intent2.putExtra(Constants.EXTRA_CONSULT_DEEPLINK_URL, str);
            this.mContext.startActivity(intent2);
        }
    }

    public void updateConsultLastAcceptedEulaVersionToCurrent() {
        updateLastAcceptedEulaVersionToCurrentVersion("consult", getConsultFeature());
    }

    private void updateLastAcceptedEulaVersionToCurrentVersion(String str, CapabilitiesFeature capabilitiesFeature) {
        if (capabilitiesFeature != null) {
            capabilitiesFeature.setLastAcceptedEulaVersion(capabilitiesFeature.getEulaVersion());
        }
        Map<String, CapabilitiesFeature> featureMap = getFeatureMap();
        if (featureMap != null) {
            featureMap.put(str, capabilitiesFeature);
            cacheResponse(featureMap);
        }
    }

    private boolean shouldDisplayEulaForFeature(CapabilitiesFeature capabilitiesFeature) {
        return capabilitiesFeature != null && capabilitiesFeature.getLastAcceptedEulaVersion() < capabilitiesFeature.getEulaVersion();
    }

    public void clearCachedCapabilities() {
        ICacheProvider cacheProvider = MedscapeApplication.getCacheProvider();
        if (cacheProvider != null) {
            cacheProvider.removeKey(CAPABILITIES_FEATURE_CACHE_KEY);
        }
        mFeatureMap = null;
        mLastUpdateDate = null;
    }

    public void startConsultProfile(final Context context, final Activity activity, final View view) {
        ConsultDataManager.getInstance(context, activity).waitForInit(new IAuthFlowCompletedListener() {
            public void onSuccess() {
                View view = view;
                if (view != null) {
                    view.setVisibility(8);
                }
                Intent intent = new Intent(activity, ConsultProfileActivity.class);
                intent.putExtra(Constants.CONSULT_USER_BUNDLE_KEY, ConsultDataManager.getInstance(context, activity).getCurrentUser());
                activity.startActivity(intent);
                Activity activity = activity;
                if (activity instanceof ConsultTermsActivity) {
                    activity.finish();
                }
            }

            public void onFailure(MedscapeException medscapeException) {
                View view = view;
                if (view != null) {
                    view.setVisibility(8);
                }
                Toast.makeText(context, medscapeException.getMessage(), 0).show();
            }
        });
    }

    public void startConsultProfileEluaCheck(Context context, Activity activity, View view) {
        CapabilitiesFeature consultFeature = getConsultFeature();
        if (shouldDisplayEulaForFeature(consultFeature)) {
            if (view != null) {
                view.setVisibility(8);
            }
            if (this.mContext != null) {
                Intent intent = new Intent(this.mContext, ConsultTermsActivity.class);
                intent.setFlags(604045312);
                intent.putExtra("com.medscape.android.EXTRA_WEBVIEW_URL", consultFeature.getEulaUrl());
                intent.putExtra(Constants.CONSULT_GO_PROFILE, true);
                this.mContext.startActivity(intent);
                return;
            }
            return;
        }
        updateConsultLastAcceptedEulaVersionToCurrent();
        startConsultProfile(context, activity, view);
    }
}
