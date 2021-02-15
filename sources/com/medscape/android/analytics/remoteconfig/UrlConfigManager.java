package com.medscape.android.analytics.remoteconfig;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/UrlConfigManager;", "", "()V", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: UrlConfigManager.kt */
public final class UrlConfigManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002¨\u0006\b"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/UrlConfigManager$Companion;", "", "()V", "getUrl", "", "remoteUrl", "parseJsonUrl", "value", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: UrlConfigManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "remoteUrl");
            String string = new RemoteConfig().getMFirebaseRemoteConfig().getString(str);
            Intrinsics.checkNotNullExpressionValue(string, "RemoteConfig().mFirebase…nfig.getString(remoteUrl)");
            if (string.length() == 0) {
                return null;
            }
            return parseJsonUrl(string);
        }

        private final String parseJsonUrl(String str) {
            try {
                String optString = new JSONObject(str).optString("url");
                Intrinsics.checkNotNullExpressionValue(optString, "json.optString(\"url\")");
                return optString;
            } catch (Throwable th) {
                th.printStackTrace();
                return "";
            }
        }
    }
}
