package com.wbmd.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.wbmd.ads.bidding.AdBiddingCenter;
import com.wbmd.ads.bidding.AdBiddingProvider;
import com.wbmd.ads.model.AdContainer;
import com.wbmd.ads.model.AdStatus;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J1\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u0014J\"\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/wbmd/ads/AdManager;", "", "context", "Landroid/content/Context;", "appEventListener", "Lcom/wbmd/ads/IAppEventListener;", "adConversions", "Lcom/wbmd/ads/IAdConversions;", "(Landroid/content/Context;Lcom/wbmd/ads/IAppEventListener;Lcom/wbmd/ads/IAdConversions;)V", "buildAdLoader", "Lcom/google/android/gms/ads/AdLoader$Builder;", "listener", "Lcom/wbmd/ads/IAdListener;", "adParams", "Lcom/wbmd/ads/IAdParams;", "loadAd", "", "biddingProviders", "", "Lcom/wbmd/ads/bidding/AdBiddingProvider;", "(Landroid/content/Context;[Lcom/wbmd/ads/bidding/AdBiddingProvider;Lcom/wbmd/ads/IAdListener;Lcom/wbmd/ads/IAdParams;)V", "bundleWithBids", "Landroid/os/Bundle;", "Companion", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdManager.kt */
public final class AdManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final IAdConversions adConversions;
    /* access modifiers changed from: private */
    public final IAppEventListener appEventListener;
    private final Context context;

    public AdManager(Context context2, IAppEventListener iAppEventListener, IAdConversions iAdConversions) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.appEventListener = iAppEventListener;
        this.adConversions = iAdConversions;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AdManager(Context context2, IAppEventListener iAppEventListener, IAdConversions iAdConversions, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i & 2) != 0 ? null : iAppEventListener, (i & 4) != 0 ? null : iAdConversions);
    }

    public final void loadAd(Context context2, AdBiddingProvider[] adBiddingProviderArr, IAdListener iAdListener, IAdParams iAdParams) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(adBiddingProviderArr, "biddingProviders");
        Intrinsics.checkNotNullParameter(iAdListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(iAdParams, "adParams");
        new AdBiddingCenter(adBiddingProviderArr).getBidData(context2, iAdParams, new AdManager$loadAd$1(this, iAdParams, iAdListener));
    }

    public static /* synthetic */ void loadAd$default(AdManager adManager, IAdListener iAdListener, IAdParams iAdParams, Bundle bundle, int i, Object obj) {
        if ((i & 4) != 0) {
            bundle = null;
        }
        adManager.loadAd(iAdListener, iAdParams, bundle);
    }

    public final void loadAd(IAdListener iAdListener, IAdParams iAdParams, Bundle bundle) {
        Intrinsics.checkNotNullParameter(iAdListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(iAdParams, "adParams");
        if (iAdParams.getAdSizes() == null && iAdParams.getNativeTemplates() == null) {
            iAdListener.onAdFailed(new AdContainer(AdStatus.failed, (PublisherAdView) null, (NativeCustomTemplateAd) null, 6, (DefaultConstructorMarker) null), -99);
            return;
        }
        AdLoader.Builder buildAdLoader = buildAdLoader(iAdListener, iAdParams);
        if (bundle == null) {
            bundle = Companion.getAdExtrasBundle$wbmdadsdk_release(iAdParams);
        }
        buildAdLoader.build().loadAd(new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, bundle).build());
    }

    private final AdLoader.Builder buildAdLoader(IAdListener iAdListener, IAdParams iAdParams) {
        AdLoader.Builder builder = new AdLoader.Builder(this.context, iAdParams.getAdUnitID());
        if (iAdParams.getAdSizes() != null) {
            AdSize[] adSizes = iAdParams.getAdSizes();
            Intrinsics.checkNotNull(adSizes);
            builder.forPublisherAdView(new AdManager$buildAdLoader$1(this, iAdListener), (AdSize[]) Arrays.copyOf(adSizes, adSizes.length));
            PublisherAdViewOptions.Builder builder2 = new PublisherAdViewOptions.Builder();
            builder2.setAppEventListener(new AdManager$buildAdLoader$2(this, iAdListener));
            builder.withPublisherAdViewOptions(builder2.build());
        }
        String[] nativeTemplates = iAdParams.getNativeTemplates();
        if (nativeTemplates != null) {
            for (String forCustomTemplateAd : nativeTemplates) {
                builder.forCustomTemplateAd(forCustomTemplateAd, new AdManager$buildAdLoader$$inlined$let$lambda$1(builder, iAdListener), (NativeCustomTemplateAd.OnCustomClickListener) null);
            }
        }
        builder.withAdListener(new AdManager$buildAdLoader$4(iAdListener));
        return builder;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0003\u001a&\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0004j\u0012\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u0001`\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/wbmd/ads/AdManager$Companion;", "", "()V", "getADParamsMap", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "adParams", "Lcom/wbmd/ads/IAdParams;", "getAdExtrasBundle", "Landroid/os/Bundle;", "getAdExtrasBundle$wbmdadsdk_release", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: AdManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Bundle getAdExtrasBundle$wbmdadsdk_release(IAdParams iAdParams) {
            Intrinsics.checkNotNullParameter(iAdParams, "adParams");
            Bundle bundle = new Bundle();
            HashMap<String, String> aDParamsMap = getADParamsMap(iAdParams);
            if (aDParamsMap != null) {
                Map map = aDParamsMap;
                if (!map.isEmpty()) {
                    for (Map.Entry entry : map.entrySet()) {
                        if (entry.getValue() != null) {
                            bundle.putString((String) entry.getKey(), (String) entry.getValue());
                        }
                    }
                }
            }
            return bundle;
        }

        public final HashMap<String, String> getADParamsMap(IAdParams iAdParams) {
            Intrinsics.checkNotNullParameter(iAdParams, "adParams");
            HashMap<String, String> aDParams = iAdParams.getADParams();
            if (aDParams == null) {
                aDParams = new HashMap<>();
            }
            Map map = aDParams;
            if (!map.containsKey("pos")) {
                map.put("pos", String.valueOf(iAdParams.getPosValue()));
            }
            return aDParams;
        }
    }
}
