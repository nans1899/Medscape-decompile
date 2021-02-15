package com.webmd.wbmdcmepulse.live_events.remoteconfig;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0010\u0010\u0010\u001a\u00020\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/remoteconfig/LiveEventsConfigManager;", "", "context", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "getContext", "()Landroidx/fragment/app/FragmentActivity;", "setContext", "remoteConfig", "Lcom/webmd/wbmdcmepulse/live_events/remoteconfig/RemoteConfig;", "getRemoteConfig", "()Lcom/webmd/wbmdcmepulse/live_events/remoteconfig/RemoteConfig;", "setRemoteConfig", "(Lcom/webmd/wbmdcmepulse/live_events/remoteconfig/RemoteConfig;)V", "getLiveEventsUrl", "", "parseLiveEventsConfig", "jsonString", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventsConfigManager.kt */
public final class LiveEventsConfigManager {
    private FragmentActivity context;
    private RemoteConfig remoteConfig = new RemoteConfig();

    public LiveEventsConfigManager(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        this.context = fragmentActivity;
    }

    public final FragmentActivity getContext() {
        return this.context;
    }

    public final void setContext(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<set-?>");
        this.context = fragmentActivity;
    }

    public final RemoteConfig getRemoteConfig() {
        return this.remoteConfig;
    }

    public final void setRemoteConfig(RemoteConfig remoteConfig2) {
        Intrinsics.checkNotNullParameter(remoteConfig2, "<set-?>");
        this.remoteConfig = remoteConfig2;
    }

    public final String getLiveEventsUrl() {
        this.remoteConfig.fetchRemoteConfig(this.context);
        String string = this.remoteConfig.getMFirebaseRemoteConfig().getString("LiveEventsConfig");
        Intrinsics.checkNotNullExpressionValue(string, "remoteConfig.mFirebaseRe…tants.LIVE_EVENTS_CONFIG)");
        return parseLiveEventsConfig(string);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0013 A[Catch:{ all -> 0x002d }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String parseLiveEventsConfig(java.lang.String r5) {
        /*
            r4 = this;
            java.lang.String r0 = "url"
            r1 = r5
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x0010
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)     // Catch:{ all -> 0x002d }
            if (r1 == 0) goto L_0x000e
            goto L_0x0010
        L_0x000e:
            r1 = 0
            goto L_0x0011
        L_0x0010:
            r1 = 1
        L_0x0011:
            if (r1 != 0) goto L_0x0031
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ all -> 0x002d }
            r1.<init>(r5)     // Catch:{ all -> 0x002d }
            java.lang.String r5 = r1.optString(r0)     // Catch:{ all -> 0x002d }
            com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider r1 = com.webmd.wbmdproffesionalauthentication.providers.SharedPreferenceProvider.get()     // Catch:{ all -> 0x002d }
            androidx.fragment.app.FragmentActivity r2 = r4.context     // Catch:{ all -> 0x002d }
            android.content.Context r2 = (android.content.Context) r2     // Catch:{ all -> 0x002d }
            java.lang.String r3 = "com.webmd.wbmdcmepulse.live.event.register.url"
            r1.saveString(r2, r3, r5)     // Catch:{ all -> 0x002d }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r0)     // Catch:{ all -> 0x002d }
            return r5
        L_0x002d:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0031:
            java.lang.String r5 = ""
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.live_events.remoteconfig.LiveEventsConfigManager.parseLiveEventsConfig(java.lang.String):java.lang.String");
    }
}
