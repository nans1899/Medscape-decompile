package com.medscape.android.ads.bidding;

import android.content.Context;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.ads.proclivity.ProclivityDataModel;
import com.medscape.android.ads.proclivity.ProclivityUtils;
import com.medscape.android.capabilities.CapabilitiesManager;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import net.media.android.bidder.dfp.DfpBidder;
import net.media.android.bidder.dfp.callback.DfpBidderCallback;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fJ*\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u0010R&\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048B@BX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/medscape/android/ads/bidding/AdBidder;", "", "()V", "<set-?>", "Ljava/util/ArrayList;", "Lcom/medscape/android/ads/proclivity/ProclivityDataModel;", "proclivityMap", "makeADCallWithBidding", "", "context", "Landroid/content/Context;", "screenSpecificMap", "Ljava/util/HashMap;", "", "adAction", "Lcom/medscape/android/ads/DFPAdAction;", "Lcom/medscape/android/ads/NativeAdAction;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdBidder.kt */
public final class AdBidder {
    /* access modifiers changed from: private */
    public ArrayList<ProclivityDataModel> proclivityMap = new ArrayList<>();

    public final synchronized void makeADCallWithBidding(Context context, HashMap<String, String> hashMap, DFPAdAction dFPAdAction) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(hashMap, "screenSpecificMap");
        Intrinsics.checkNotNullParameter(dFPAdAction, "adAction");
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 2;
        dFPAdAction.prepareAdForBidding(hashMap);
        PublisherAdRequest createAdRequestWithDetails = dFPAdAction.createAdRequestWithDetails();
        ProclivityUtils.makeProclivityRequest(context, dFPAdAction.getGlobalMap(), new AdBidder$makeADCallWithBidding$1(this, intRef, dFPAdAction, createAdRequestWithDetails));
        CapabilitiesManager instance = CapabilitiesManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "CapabilitiesManager.getInstance(context)");
        if (instance.isMediaNetFeatureAvailable()) {
            DfpBidder.addBids(dFPAdAction.adView, createAdRequestWithDetails, (DfpBidderCallback) new AdBidder$makeADCallWithBidding$2(this, intRef, dFPAdAction, createAdRequestWithDetails));
        } else {
            intRef.element--;
        }
    }

    public final synchronized void makeADCallWithBidding(Context context, HashMap<String, String> hashMap, NativeAdAction nativeAdAction) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(hashMap, "screenSpecificMap");
        Intrinsics.checkNotNullParameter(nativeAdAction, "adAction");
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 2;
        String str = hashMap.get("pos");
        if (str != null) {
            nativeAdAction.setPosValue(str);
        }
        nativeAdAction.prepareAdForBidding(hashMap);
        PublisherAdRequest createAdRequestWithDetails = nativeAdAction.createAdRequestWithDetails();
        ProclivityUtils.makeProclivityRequest(context, nativeAdAction.getGlobalMap(), new AdBidder$makeADCallWithBidding$3(this, intRef, nativeAdAction, createAdRequestWithDetails));
        CapabilitiesManager instance = CapabilitiesManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "CapabilitiesManager.getInstance(context)");
        if (instance.isMediaNetFeatureAvailable()) {
            DfpBidder.addBids(DFPReferenceAdListener.AD_UNIT_ID, nativeAdAction.getAdsizes(), createAdRequestWithDetails, new AdBidder$makeADCallWithBidding$4(this, intRef, nativeAdAction, createAdRequestWithDetails));
        } else {
            intRef.element--;
        }
    }
}
