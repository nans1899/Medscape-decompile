package com.medscape.android.analytics.remoteconfig;

import android.app.Activity;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.medscape.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/RemoteConfig;", "", "()V", "cacheExpiration", "", "getCacheExpiration", "()J", "setCacheExpiration", "(J)V", "mFirebaseRemoteConfig", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "getMFirebaseRemoteConfig", "()Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "setMFirebaseRemoteConfig", "(Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;)V", "fetchRemoteConfig", "", "activity", "Landroid/app/Activity;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: RemoteConfig.kt */
public final class RemoteConfig {
    private long cacheExpiration = 21600;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    public RemoteConfig() {
        FirebaseRemoteConfig instance = FirebaseRemoteConfig.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "FirebaseRemoteConfig.getInstance()");
        this.mFirebaseRemoteConfig = instance;
        FirebaseRemoteConfigSettings build = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(this.cacheExpiration).build();
        Intrinsics.checkNotNullExpressionValue(build, "FirebaseRemoteConfigSett…\n                .build()");
        this.mFirebaseRemoteConfig.setConfigSettingsAsync(build);
        this.mFirebaseRemoteConfig.setDefaults((int) R.xml.remote_config_defaults);
    }

    public final FirebaseRemoteConfig getMFirebaseRemoteConfig() {
        return this.mFirebaseRemoteConfig;
    }

    public final void setMFirebaseRemoteConfig(FirebaseRemoteConfig firebaseRemoteConfig) {
        Intrinsics.checkNotNullParameter(firebaseRemoteConfig, "<set-?>");
        this.mFirebaseRemoteConfig = firebaseRemoteConfig;
    }

    public final long getCacheExpiration() {
        return this.cacheExpiration;
    }

    public final void setCacheExpiration(long j) {
        this.cacheExpiration = j;
    }

    public final void fetchRemoteConfig(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.mFirebaseRemoteConfig.fetch(this.cacheExpiration).addOnCompleteListener(activity, new RemoteConfig$fetchRemoteConfig$1(this));
    }
}
