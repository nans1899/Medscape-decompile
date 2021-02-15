package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.internal.ads.zzzd;
import com.google.android.gms.internal.ads.zzzh;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class MobileAds {
    public static final String ERROR_DOMAIN = "com.google.android.gms.ads";

    @Deprecated
    /* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
    public static final class Settings {
        private final zzzh zzadk = new zzzh();

        @Deprecated
        public final String getTrackingId() {
            return null;
        }

        @Deprecated
        public final boolean isGoogleAnalyticsEnabled() {
            return false;
        }

        @Deprecated
        public final Settings setGoogleAnalyticsEnabled(boolean z) {
            return this;
        }

        @Deprecated
        public final Settings setTrackingId(String str) {
            return this;
        }
    }

    private MobileAds() {
    }

    @Deprecated
    public static void initialize(Context context, String str) {
        initialize(context, str, (Settings) null);
    }

    @Deprecated
    public static void initialize(Context context, String str, Settings settings) {
        zzzd.zzqw().zza(context, str, (OnInitializationCompleteListener) null);
    }

    public static void initialize(Context context) {
        initialize(context, (String) null, (Settings) null);
    }

    public static void initialize(Context context, OnInitializationCompleteListener onInitializationCompleteListener) {
        zzzd.zzqw().zza(context, (String) null, onInitializationCompleteListener);
    }

    @Deprecated
    public static RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        return zzzd.zzqw().getRewardedVideoAdInstance(context);
    }

    public static void setAppVolume(float f) {
        zzzd.zzqw().setAppVolume(f);
    }

    public static void setAppMuted(boolean z) {
        zzzd.zzqw().setAppMuted(z);
    }

    public static void openDebugMenu(Context context, String str) {
        zzzd.zzqw().openDebugMenu(context, str);
    }

    public static String getVersionString() {
        return zzzd.zzqw().getVersionString();
    }

    public static void registerRtbAdapter(Class<? extends RtbAdapter> cls) {
        zzzd.zzqw().registerRtbAdapter(cls);
    }

    public static InitializationStatus getInitializationStatus() {
        return zzzd.zzqw().getInitializationStatus();
    }

    public static RequestConfiguration getRequestConfiguration() {
        return zzzd.zzqw().getRequestConfiguration();
    }

    public static void setRequestConfiguration(RequestConfiguration requestConfiguration) {
        zzzd.zzqw().setRequestConfiguration(requestConfiguration);
    }

    public static void disableMediationAdapterInitialization(Context context) {
        zzzd.zzqw().disableMediationAdapterInitialization(context);
    }
}
