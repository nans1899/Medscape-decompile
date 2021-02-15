package com.webmd.medscape.live.explorelivevents.common;

import com.facebook.internal.NativeProtocol;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/IOmnitureListener;", "", "sendOmnitureActionCall", "", "params", "Lcom/webmd/medscape/live/explorelivevents/common/OmnitureInfo;", "sendOmniturePageView", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: IOmnitureListener.kt */
public interface IOmnitureListener {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    /* compiled from: IOmnitureListener.kt */
    public static final class DefaultImpls {
        public static void sendOmnitureActionCall(IOmnitureListener iOmnitureListener, OmnitureInfo omnitureInfo) {
            Intrinsics.checkNotNullParameter(omnitureInfo, NativeProtocol.WEB_DIALOG_PARAMS);
        }

        public static void sendOmniturePageView(IOmnitureListener iOmnitureListener, OmnitureInfo omnitureInfo) {
            Intrinsics.checkNotNullParameter(omnitureInfo, NativeProtocol.WEB_DIALOG_PARAMS);
        }
    }

    void sendOmnitureActionCall(OmnitureInfo omnitureInfo);

    void sendOmniturePageView(OmnitureInfo omnitureInfo);
}
