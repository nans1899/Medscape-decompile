package com.wbmd.ads;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.wbmd.ads.ScrollSpeedLimitSupport;
import com.wbmd.ads.model.AdContainer;
import com.wbmd.ads.model.AdStatus;
import com.wbmd.ads.model.BaseNativeADViewModel;
import java.util.ArrayList;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 42\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u00014B\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020#H\u0004J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000fH&J\u000e\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020)J\b\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020#2\u0006\u0010&\u001a\u00020\u000fH\u0002J\u0010\u0010-\u001a\u00020#2\u0006\u0010.\u001a\u00020/H\u0016J\u0018\u00100\u001a\u00020#2\u0006\u00101\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\u000fH\u0016J\u0010\u00102\u001a\u00020#2\u0006\u0010&\u001a\u00020\u000fH\u0002J\b\u00103\u001a\u00020!H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0011j\b\u0012\u0004\u0012\u00020\u0012`\u0013X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00120\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lcom/wbmd/ads/BaseAdListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/wbmd/ads/ScrollSpeedLimitSupport;", "adManager", "Lcom/wbmd/ads/AdManager;", "adListener", "Lcom/wbmd/ads/IAdListener;", "(Lcom/wbmd/ads/AdManager;Lcom/wbmd/ads/IAdListener;)V", "getAdListener", "()Lcom/wbmd/ads/IAdListener;", "setAdListener", "(Lcom/wbmd/ads/IAdListener;)V", "adRequestStack", "Ljava/util/Stack;", "", "mAdCache", "Ljava/util/ArrayList;", "Lcom/wbmd/ads/model/AdContainer;", "Lkotlin/collections/ArrayList;", "mAdPositionMap", "Landroid/util/SparseArray;", "getMAdPositionMap", "()Landroid/util/SparseArray;", "maximumOnScreenAds", "getMaximumOnScreenAds", "()I", "setMaximumOnScreenAds", "(I)V", "pendingAdRequestCount", "scrollListener", "Lcom/wbmd/ads/ScrollSpeedRecycleViewScrollListener;", "canRequestAd", "", "clearAds", "", "getADParams", "Lcom/wbmd/ads/IAdParams;", "position", "getAdViewHolder", "parent", "Landroid/view/ViewGroup;", "getNativeAdViewModel", "Lcom/wbmd/ads/model/BaseNativeADViewModel;", "loadAD", "onAttachedToRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onBindViewHolder", "holder", "requestAD", "shouldLimitAdInjectionOnScrollSpeed", "Companion", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BaseAdListAdapter.kt */
public abstract class BaseAdListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ScrollSpeedLimitSupport {
    /* access modifiers changed from: private */
    public static final int AD_ITEM_VIEW_TYPE = R.layout.ad_item_layout;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private IAdListener adListener;
    private final AdManager adManager;
    /* access modifiers changed from: private */
    public Stack<Integer> adRequestStack;
    /* access modifiers changed from: private */
    public final ArrayList<AdContainer> mAdCache;
    private final SparseArray<AdContainer> mAdPositionMap;
    private int maximumOnScreenAds;
    /* access modifiers changed from: private */
    public int pendingAdRequestCount;
    private ScrollSpeedRecycleViewScrollListener scrollListener;

    public abstract IAdParams getADParams(int i);

    public boolean shouldLimitAdInjectionOnScrollSpeed() {
        return false;
    }

    public int maxAllowedScrollSpeedForAdInjection() {
        return ScrollSpeedLimitSupport.DefaultImpls.maxAllowedScrollSpeedForAdInjection(this);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BaseAdListAdapter(AdManager adManager2, IAdListener iAdListener, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(adManager2, (i & 2) != 0 ? null : iAdListener);
    }

    /* access modifiers changed from: protected */
    public final IAdListener getAdListener() {
        return this.adListener;
    }

    /* access modifiers changed from: protected */
    public final void setAdListener(IAdListener iAdListener) {
        this.adListener = iAdListener;
    }

    public BaseAdListAdapter(AdManager adManager2, IAdListener iAdListener) {
        Intrinsics.checkNotNullParameter(adManager2, "adManager");
        this.adManager = adManager2;
        this.adListener = iAdListener;
        this.mAdPositionMap = new SparseArray<>();
        this.mAdCache = new ArrayList<>();
        this.adRequestStack = new Stack<>();
        this.maximumOnScreenAds = 5;
    }

    /* access modifiers changed from: protected */
    public final SparseArray<AdContainer> getMAdPositionMap() {
        return this.mAdPositionMap;
    }

    /* access modifiers changed from: protected */
    public final int getMaximumOnScreenAds() {
        return this.maximumOnScreenAds;
    }

    /* access modifiers changed from: protected */
    public final void setMaximumOnScreenAds(int i) {
        this.maximumOnScreenAds = i;
    }

    public BaseNativeADViewModel getNativeAdViewModel() {
        return new BaseNativeADViewModel();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/wbmd/ads/BaseAdListAdapter$Companion;", "", "()V", "AD_ITEM_VIEW_TYPE", "", "getAD_ITEM_VIEW_TYPE", "()I", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: BaseAdListAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getAD_ITEM_VIEW_TYPE() {
            return BaseAdListAdapter.AD_ITEM_VIEW_TYPE;
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        if (shouldLimitAdInjectionOnScrollSpeed()) {
            ScrollSpeedRecycleViewScrollListener baseAdListAdapter$onAttachedToRecyclerView$1 = new BaseAdListAdapter$onAttachedToRecyclerView$1(this, maxAllowedScrollSpeedForAdInjection());
            this.scrollListener = baseAdListAdapter$onAttachedToRecyclerView$1;
            if (baseAdListAdapter$onAttachedToRecyclerView$1 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("scrollListener");
            }
            recyclerView.addOnScrollListener(baseAdListAdapter$onAttachedToRecyclerView$1);
        }
    }

    public final RecyclerView.ViewHolder getAdViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        return AdViewHolder.Companion.create(viewGroup);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (viewHolder instanceof AdViewHolder) {
            if (!this.mAdPositionMap.contains(i)) {
                this.mAdPositionMap.put(i, new AdContainer(AdStatus.needsLoading, (PublisherAdView) null, (NativeCustomTemplateAd) null, 6, (DefaultConstructorMarker) null));
            }
            if (this.mAdPositionMap.get(i).getStatus() != AdStatus.loaded && canRequestAd()) {
                if (this.mAdCache.isEmpty()) {
                    requestAD(i);
                } else {
                    SparseArray<AdContainer> sparseArray = this.mAdPositionMap;
                    AdContainer remove = this.mAdCache.remove(0);
                    Intrinsics.checkNotNullExpressionValue(remove, "mAdCache.removeAt(0)");
                    sparseArray.put(i, remove);
                }
            }
            ((AdViewHolder) viewHolder).bindAd(this.mAdPositionMap.get(i), getNativeAdViewModel());
        }
    }

    /* access modifiers changed from: protected */
    public final void clearAds() {
        this.mAdPositionMap.clear();
        notifyDataSetChanged();
    }

    private final boolean canRequestAd() {
        if (!shouldLimitAdInjectionOnScrollSpeed()) {
            return true;
        }
        ScrollSpeedRecycleViewScrollListener scrollSpeedRecycleViewScrollListener = this.scrollListener;
        if (scrollSpeedRecycleViewScrollListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("scrollListener");
        }
        return scrollSpeedRecycleViewScrollListener.getCurrentScrollSpeed() <= maxAllowedScrollSpeedForAdInjection();
    }

    private final void loadAD(int i) {
        this.pendingAdRequestCount++;
        AdManager.loadAd$default(this.adManager, new BaseAdListAdapter$loadAD$1(this), getADParams(i), (Bundle) null, 4, (Object) null);
    }

    private final void requestAD(int i) {
        if (this.adRequestStack.contains(Integer.valueOf(i))) {
            Integer peek = this.adRequestStack.peek();
            if (peek == null || peek.intValue() != i) {
                this.adRequestStack.remove(Integer.valueOf(i));
                this.adRequestStack.push(Integer.valueOf(i));
            }
            if (this.mAdCache.size() + this.pendingAdRequestCount < this.adRequestStack.size()) {
                loadAD(i);
                return;
            }
            return;
        }
        this.adRequestStack.push(Integer.valueOf(i));
        if (this.adRequestStack.size() > this.maximumOnScreenAds) {
            this.adRequestStack.removeElementAt(0);
        }
        this.mAdPositionMap.put(i, new AdContainer(AdStatus.loading, (PublisherAdView) null, (NativeCustomTemplateAd) null, 6, (DefaultConstructorMarker) null));
        loadAD(i);
    }
}
