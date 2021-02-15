package com.medscape.android.drugs.details.datamodels;

import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.contentviewer.CrossLink;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/medscape/android/drugs/details/datamodels/InlineAdLineItem;", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "()V", "adView", "Lcom/medscape/android/ads/NativeDFPAD;", "getAdView", "()Lcom/medscape/android/ads/NativeDFPAD;", "setAdView", "(Lcom/medscape/android/ads/NativeDFPAD;)V", "isSharethrough", "", "()Z", "setSharethrough", "(Z)V", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: InlineAdLineItem.kt */
public final class InlineAdLineItem extends LineItem {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private NativeDFPAD adView;
    private boolean isSharethrough;

    public InlineAdLineItem() {
        super((CrossLink) null, (CharSequence) null, 0, false, false, false, 62, (DefaultConstructorMarker) null);
    }

    public final NativeDFPAD getAdView() {
        return this.adView;
    }

    public final void setAdView(NativeDFPAD nativeDFPAD) {
        this.adView = nativeDFPAD;
    }

    public final boolean isSharethrough() {
        return this.isSharethrough;
    }

    public final void setSharethrough(boolean z) {
        this.isSharethrough = z;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/medscape/android/drugs/details/datamodels/InlineAdLineItem$Companion;", "", "()V", "createNativeAD", "Lcom/medscape/android/drugs/details/datamodels/InlineAdLineItem;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: InlineAdLineItem.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final InlineAdLineItem createNativeAD() {
            InlineAdLineItem inlineAdLineItem = new InlineAdLineItem();
            inlineAdLineItem.setSharethrough(true);
            return inlineAdLineItem;
        }
    }
}
