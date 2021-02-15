package io.branch.referral;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import androidx.browser.customtabs.CustomTabsService;
import io.branch.referral.Defines;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

class BranchStrongMatchHelper {
    private static final int STRONG_MATCH_CHECK_TIME_OUT = 500;
    public static final int STRONG_MATCH_URL_HIT_DELAY = 750;
    private static int StrongMatchUrlHitDelay = 750;
    private static final long THIRTY_DAYS_EPOCH_MILLI_SEC = 2592000000L;
    private static BranchStrongMatchHelper branchStrongMatchHelper_;
    Class<?> CustomServiceTabConnectionClass;
    Class<?> CustomTabsCallbackClass;
    Class<?> CustomTabsClientClass;
    Class<?> CustomTabsSessionClass;
    Class<?> ICustomTabsServiceClass;
    private boolean isCustomTabsAvailable_ = true;
    boolean isStrongMatchUrlLaunched = false;
    Object mClient_ = null;
    private final Handler timeOutHandler_;

    interface StrongMatchCheckEvents {
        void onStrongMatchCheckFinished();
    }

    private BranchStrongMatchHelper() {
        try {
            this.CustomTabsClientClass = Class.forName("androidx.browser.customtabs.CustomTabsClient");
            this.CustomServiceTabConnectionClass = Class.forName("androidx.browser.customtabs.CustomTabsServiceConnection");
            this.CustomTabsCallbackClass = Class.forName("androidx.browser.customtabs.CustomTabsCallback");
            this.CustomTabsSessionClass = Class.forName("androidx.browser.customtabs.CustomTabsSession");
            this.ICustomTabsServiceClass = Class.forName("android.support.customtabs.ICustomTabsService");
        } catch (Throwable unused) {
            this.isCustomTabsAvailable_ = false;
        }
        this.timeOutHandler_ = new Handler();
    }

    public static BranchStrongMatchHelper getInstance() {
        if (branchStrongMatchHelper_ == null) {
            branchStrongMatchHelper_ = new BranchStrongMatchHelper();
        }
        return branchStrongMatchHelper_;
    }

    public void setStrongMatchUrlHitDelay(int i) {
        StrongMatchUrlHitDelay = i;
    }

    public void checkForStrongMatch(Context context, String str, DeviceInfo deviceInfo, PrefHelper prefHelper, SystemObserver systemObserver, StrongMatchCheckEvents strongMatchCheckEvents) {
        final StrongMatchCheckEvents strongMatchCheckEvents2 = strongMatchCheckEvents;
        this.isStrongMatchUrlLaunched = false;
        if (System.currentTimeMillis() - prefHelper.getLastStrongMatchTime() < 2592000000L) {
            updateStrongMatchCheckFinished(strongMatchCheckEvents2, this.isStrongMatchUrlLaunched);
        } else if (!this.isCustomTabsAvailable_) {
            updateStrongMatchCheckFinished(strongMatchCheckEvents2, this.isStrongMatchUrlLaunched);
        } else {
            try {
                if (deviceInfo.getHardwareID() != null) {
                    final Uri buildStrongMatchUrl = buildStrongMatchUrl(str, deviceInfo, prefHelper, systemObserver, context);
                    if (buildStrongMatchUrl != null) {
                        this.timeOutHandler_.postDelayed(new Runnable() {
                            public void run() {
                                BranchStrongMatchHelper branchStrongMatchHelper = BranchStrongMatchHelper.this;
                                branchStrongMatchHelper.updateStrongMatchCheckFinished(strongMatchCheckEvents2, branchStrongMatchHelper.isStrongMatchUrlLaunched);
                            }
                        }, 500);
                        this.CustomTabsClientClass.getMethod("bindCustomTabsService", new Class[]{Context.class, String.class, this.CustomServiceTabConnectionClass});
                        Method method = this.CustomTabsClientClass.getMethod("warmup", new Class[]{Long.TYPE});
                        Method method2 = this.CustomTabsClientClass.getMethod("newSession", new Class[]{this.CustomTabsCallbackClass});
                        Method method3 = this.CustomTabsSessionClass.getMethod("mayLaunchUrl", new Class[]{Uri.class, Bundle.class, List.class});
                        Intent intent = new Intent(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
                        intent.setPackage("com.android.chrome");
                        final Method method4 = method;
                        final Method method5 = method2;
                        final Method method6 = method3;
                        final PrefHelper prefHelper2 = prefHelper;
                        final StrongMatchCheckEvents strongMatchCheckEvents3 = strongMatchCheckEvents;
                        Context context2 = context;
                        context.bindService(intent, new MockCustomTabServiceConnection() {
                            public void onCustomTabsServiceConnected(ComponentName componentName, Object obj) {
                                BranchStrongMatchHelper branchStrongMatchHelper = BranchStrongMatchHelper.this;
                                branchStrongMatchHelper.mClient_ = branchStrongMatchHelper.CustomTabsClientClass.cast(obj);
                                if (BranchStrongMatchHelper.this.mClient_ != null) {
                                    try {
                                        method4.invoke(BranchStrongMatchHelper.this.mClient_, new Object[]{0});
                                        Object invoke = method5.invoke(BranchStrongMatchHelper.this.mClient_, new Object[]{null});
                                        if (invoke != null) {
                                            PrefHelper.Debug("BranchSDK", "Strong match request " + buildStrongMatchUrl);
                                            method6.invoke(invoke, new Object[]{buildStrongMatchUrl, null, null});
                                            prefHelper2.saveLastStrongMatchTime(System.currentTimeMillis());
                                            BranchStrongMatchHelper.this.isStrongMatchUrlLaunched = true;
                                        }
                                    } catch (Throwable unused) {
                                        BranchStrongMatchHelper.this.mClient_ = null;
                                        BranchStrongMatchHelper branchStrongMatchHelper2 = BranchStrongMatchHelper.this;
                                        branchStrongMatchHelper2.updateStrongMatchCheckFinished(strongMatchCheckEvents3, branchStrongMatchHelper2.isStrongMatchUrlLaunched);
                                    }
                                }
                            }

                            public void onServiceDisconnected(ComponentName componentName) {
                                BranchStrongMatchHelper.this.mClient_ = null;
                                BranchStrongMatchHelper branchStrongMatchHelper = BranchStrongMatchHelper.this;
                                branchStrongMatchHelper.updateStrongMatchCheckFinished(strongMatchCheckEvents3, branchStrongMatchHelper.isStrongMatchUrlLaunched);
                            }
                        }, 33);
                        return;
                    }
                    updateStrongMatchCheckFinished(strongMatchCheckEvents2, this.isStrongMatchUrlLaunched);
                    return;
                }
                updateStrongMatchCheckFinished(strongMatchCheckEvents2, this.isStrongMatchUrlLaunched);
                Log.d("BranchSDK", "Cannot use cookie-based matching since device id is not available");
            } catch (Throwable unused) {
                updateStrongMatchCheckFinished(strongMatchCheckEvents2, this.isStrongMatchUrlLaunched);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateStrongMatchCheckFinished(final StrongMatchCheckEvents strongMatchCheckEvents, boolean z) {
        if (strongMatchCheckEvents == null) {
            return;
        }
        if (z) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    strongMatchCheckEvents.onStrongMatchCheckFinished();
                }
            }, (long) StrongMatchUrlHitDelay);
        } else {
            strongMatchCheckEvents.onStrongMatchCheckFinished();
        }
    }

    private Uri buildStrongMatchUrl(String str, DeviceInfo deviceInfo, PrefHelper prefHelper, SystemObserver systemObserver, Context context) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String str2 = ("https://" + str + "/_strong_match?os=" + deviceInfo.getOsName()) + "&" + Defines.Jsonkey.HardwareID.getKey() + "=" + deviceInfo.getHardwareID();
        String str3 = str2 + "&" + Defines.Jsonkey.HardwareIDType.getKey() + "=" + (deviceInfo.isHardwareIDReal() ? Defines.Jsonkey.HardwareIDTypeVendor : Defines.Jsonkey.HardwareIDTypeRandom).getKey();
        if (SystemObserver.GAIDString_ != null && !BranchUtil.isTestModeEnabled(context)) {
            str3 = str3 + "&" + Defines.Jsonkey.GoogleAdvertisingID.getKey() + "=" + SystemObserver.GAIDString_;
        }
        if (!prefHelper.getDeviceFingerPrintID().equals("bnc_no_value")) {
            str3 = str3 + "&" + Defines.Jsonkey.DeviceFingerprintID.getKey() + "=" + prefHelper.getDeviceFingerPrintID();
        }
        if (!deviceInfo.getAppVersion().equals("bnc_no_value")) {
            str3 = str3 + "&" + Defines.Jsonkey.AppVersion.getKey() + "=" + deviceInfo.getAppVersion();
        }
        if (!prefHelper.getBranchKey().equals("bnc_no_value")) {
            str3 = str3 + "&" + Defines.Jsonkey.BranchKey.getKey() + "=" + prefHelper.getBranchKey();
        }
        return Uri.parse(str3 + "&sdk=android2.19.5");
    }

    private abstract class MockCustomTabServiceConnection implements ServiceConnection {
        public abstract void onCustomTabsServiceConnected(ComponentName componentName, Object obj);

        public MockCustomTabServiceConnection() {
        }

        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                Constructor<?> declaredConstructor = BranchStrongMatchHelper.this.CustomTabsClientClass.getDeclaredConstructor(new Class[]{BranchStrongMatchHelper.this.ICustomTabsServiceClass, ComponentName.class});
                declaredConstructor.setAccessible(true);
                onCustomTabsServiceConnected(componentName, declaredConstructor.newInstance(new Object[]{Class.forName("android.support.customtabs.ICustomTabsService$Stub").getMethod("asInterface", new Class[]{IBinder.class}).invoke((Object) null, new Object[]{iBinder}), componentName}));
            } catch (Throwable unused) {
                onCustomTabsServiceConnected((ComponentName) null, (Object) null);
            }
        }
    }
}
