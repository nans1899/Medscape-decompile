package com.medscape.android.analytics.remoteconfig.reference;

import com.medscape.android.Constants;
import com.medscape.android.analytics.remoteconfig.RemoteConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\b"}, d2 = {"Lcom/medscape/android/analytics/remoteconfig/reference/ReferenceArticleTOCEnabledManager;", "", "()V", "getRefTOCData", "", "parseTOCData", "response", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ReferenceArticleTOCEnabledManager.kt */
public final class ReferenceArticleTOCEnabledManager {
    public final boolean getRefTOCData() {
        String string = new RemoteConfig().getMFirebaseRemoteConfig().getString(Constants.REMOTE_REF_TOC_ENABLED);
        Intrinsics.checkNotNullExpressionValue(string, "RemoteConfig().mFirebase…s.REMOTE_REF_TOC_ENABLED)");
        return parseTOCData(string);
    }

    private final boolean parseTOCData(String str) {
        return Boolean.parseBoolean(str);
    }
}
