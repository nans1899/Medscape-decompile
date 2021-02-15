package com.webmd.wbmdsimullytics.platform;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.gson.Gson;
import com.webmd.wbmdsimullytics.R;
import com.webmd.wbmdsimullytics.callbacks.IFetchPlatformsListener;
import com.webmd.wbmdsimullytics.callbacks.IPlatformConfigurationListener;
import com.webmd.wbmdsimullytics.callbacks.IUserIdListener;
import com.webmd.wbmdsimullytics.constants.PlatformConstants;
import com.webmd.wbmdsimullytics.model.Platform;
import com.webmd.wbmdsimullytics.model.PlatformUserConfig;
import com.webmd.wbmdsimullytics.routers.BrazeRouter;
import com.webmd.wbmdsimullytics.routers.FirebaseRouter;
import java.io.IOException;
import java.util.List;

public class PlatformConfigurationManager {
    /* access modifiers changed from: private */
    public static final String TAG = PlatformConfigurationManager.class.getSimpleName();
    /* access modifiers changed from: private */
    public static FirebaseRemoteConfig mFirebaseRemoteConfig;
    /* access modifiers changed from: private */
    public static Platform mPlatform;
    private static PlatformConfigurationManager mPlatformConfigurationManager;

    private static long getCacheExpiration() {
        return 0;
    }

    private PlatformConfigurationManager() {
        init();
    }

    public static PlatformConfigurationManager getInstance() {
        if (mPlatformConfigurationManager == null) {
            mPlatformConfigurationManager = new PlatformConfigurationManager();
        }
        return mPlatformConfigurationManager;
    }

    public void fetchPlatforms(Activity activity, final IFetchPlatformsListener iFetchPlatformsListener) {
        Platform platform = mPlatform;
        if (!(platform == null || platform.getPlatforms() == null || mPlatform.getPlatforms().size() <= 0)) {
            iFetchPlatformsListener.onPlatformsFetched(mPlatform.getPlatforms());
        }
        if (mFirebaseRemoteConfig == null) {
            init();
        }
        mFirebaseRemoteConfig.fetch(getCacheExpiration()).addOnCompleteListener(activity, new OnCompleteListener<Void>() {
            public void onComplete(Task<Void> task) {
                if (task.isSuccessful()) {
                    PlatformConfigurationManager.mFirebaseRemoteConfig.activateFetched();
                }
                Platform unused = PlatformConfigurationManager.mPlatform = (Platform) new Gson().fromJson(PlatformConfigurationManager.mFirebaseRemoteConfig.getString(PlatformConstants.REMOTE_CONFIG_SERVICE_KEY), Platform.class);
                if (PlatformConfigurationManager.mPlatform != null) {
                    iFetchPlatformsListener.onPlatformsFetched(PlatformConfigurationManager.mPlatform.getPlatforms());
                }
            }
        });
    }

    private static void init() {
        FirebaseInstanceId.getInstance().getInstanceId();
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mFirebaseRemoteConfig.setConfigSettings(new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(false).build());
        mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);
    }

    public void getUserConfigs(final Activity activity, List<String> list, final IPlatformConfigurationListener iPlatformConfigurationListener) {
        if (list != null) {
            for (String next : list) {
                if (next.equalsIgnoreCase(PlatformConstants.BRAZE_SERVICE)) {
                    new BrazeRouter(activity).getUserId(new IUserIdListener() {
                        public void onUserIdReceived(String str) {
                            PlatformUserConfig platformUserConfig = new PlatformUserConfig();
                            platformUserConfig.setName(PlatformConstants.BRAZE_SERVICE);
                            new Thread(new Runnable() {
                                public void run() {
                                    try {
                                        FirebaseInstanceId.getInstance().getToken(activity.getResources().getString(R.string.fcm_sender_id), FirebaseMessaging.INSTANCE_ID_SCOPE);
                                    } catch (Exception e) {
                                        Log.e(PlatformConfigurationManager.TAG, e.getMessage());
                                    }
                                }
                            }).start();
                            platformUserConfig.setUserId(str);
                            iPlatformConfigurationListener.onPlatformConfigurationReceived(platformUserConfig);
                        }
                    });
                } else if (next.equalsIgnoreCase(PlatformConstants.FIREBASE_SERVICE)) {
                    new FirebaseRouter(activity).getUserId(activity, new IUserIdListener() {
                        public void onUserIdReceived(String str) {
                            PlatformUserConfig platformUserConfig = new PlatformUserConfig();
                            platformUserConfig.setName(PlatformConstants.FIREBASE_SERVICE);
                            platformUserConfig.setUserId(str);
                            iPlatformConfigurationListener.onPlatformConfigurationReceived(platformUserConfig);
                        }
                    });
                }
            }
        }
    }

    public static String getFirebaseToken(Context context) {
        try {
            return FirebaseInstanceId.getInstance().getToken(context.getResources().getString(R.string.fcm_sender_id), FirebaseMessaging.INSTANCE_ID_SCOPE);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
