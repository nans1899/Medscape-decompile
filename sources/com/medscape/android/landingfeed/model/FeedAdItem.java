package com.medscape.android.landingfeed.model;

import com.medscape.android.ads.NativeDFPAD;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/landingfeed/model/FeedAdItem;", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "title", "", "(Ljava/lang/String;)V", "isAdRequested", "", "()Z", "setAdRequested", "(Z)V", "nativeDFPAD", "Lcom/medscape/android/ads/NativeDFPAD;", "getNativeDFPAD", "()Lcom/medscape/android/ads/NativeDFPAD;", "setNativeDFPAD", "(Lcom/medscape/android/ads/NativeDFPAD;)V", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedAdItem.kt */
public final class FeedAdItem extends FeedDataItem {
    private boolean isAdRequested;
    private NativeDFPAD nativeDFPAD;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeedAdItem(String str) {
        super((String) null, (Author) null, (String) null, (List) null, (String) null, (String) null, (String) null, (String) null, str, (String) null, (Integer) null, (Integer) null, (String) null, (Integer) null, (String) null, (String) null, (String) null, (String) null, (List) null, (String) null, (String) null, (Double) null, (String) null, (String) null, (String) null, (String) null, (Integer) null, (Boolean) null, (String) null, (Boolean) null, (String) null, (String) null, (List) null, (Boolean) null, (ArrayList) null, (Boolean) null, (String) null, (String) null, (String) null, -257, 127, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "title");
    }

    public final NativeDFPAD getNativeDFPAD() {
        return this.nativeDFPAD;
    }

    public final void setNativeDFPAD(NativeDFPAD nativeDFPAD2) {
        this.nativeDFPAD = nativeDFPAD2;
    }

    public final boolean isAdRequested() {
        return this.isAdRequested;
    }

    public final void setAdRequested(boolean z) {
        this.isAdRequested = z;
    }
}
