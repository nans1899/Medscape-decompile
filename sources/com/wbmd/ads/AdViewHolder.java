package com.wbmd.ads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.wbmd.ads.model.AdContainer;
import com.wbmd.ads.model.AdStatus;
import com.wbmd.ads.model.BaseNativeADViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/wbmd/ads/AdViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Landroidx/databinding/ViewDataBinding;", "(Landroidx/databinding/ViewDataBinding;)V", "adLabel", "Landroid/widget/TextView;", "getAdLabel", "()Landroid/widget/TextView;", "setAdLabel", "(Landroid/widget/TextView;)V", "parentView", "Landroid/view/View;", "getParentView", "()Landroid/view/View;", "bindAd", "", "adContainer", "Lcom/wbmd/ads/model/AdContainer;", "nativeAdViewModel", "Lcom/wbmd/ads/model/BaseNativeADViewModel;", "removeAdView", "", "Companion", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdViewHolder.kt */
public final class AdViewHolder extends RecyclerView.ViewHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private TextView adLabel;
    private final ViewDataBinding binding;
    private final View parentView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AdViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        Intrinsics.checkNotNullParameter(viewDataBinding, "binding");
        this.binding = viewDataBinding;
        View root = viewDataBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        this.parentView = root;
        this.adLabel = (TextView) root.findViewById(R.id.ad_label);
    }

    public final View getParentView() {
        return this.parentView;
    }

    public final TextView getAdLabel() {
        return this.adLabel;
    }

    public final void setAdLabel(TextView textView) {
        this.adLabel = textView;
    }

    public static /* synthetic */ void bindAd$default(AdViewHolder adViewHolder, AdContainer adContainer, BaseNativeADViewModel baseNativeADViewModel, int i, Object obj) {
        if ((i & 2) != 0) {
            baseNativeADViewModel = null;
        }
        adViewHolder.bindAd(adContainer, baseNativeADViewModel);
    }

    public final void bindAd(AdContainer adContainer, BaseNativeADViewModel baseNativeADViewModel) {
        int removeAdView = removeAdView();
        if ((adContainer != null ? adContainer.getStatus() : null) != AdStatus.loaded || (adContainer.getAdView() == null && adContainer.getNativeAd() == null)) {
            this.parentView.setVisibility(8);
            LinearLayout linearLayout = (LinearLayout) this.parentView.findViewById(R.id.ad_layout);
            Intrinsics.checkNotNullExpressionValue(linearLayout, "parentView.ad_layout");
            linearLayout.setVisibility(8);
            View findViewById = this.parentView.findViewById(R.id.native_ad_layout);
            Intrinsics.checkNotNullExpressionValue(findViewById, "parentView.native_ad_layout");
            findViewById.setVisibility(8);
            return;
        }
        this.parentView.setVisibility(0);
        if (adContainer.getAdView() != null) {
            LinearLayout linearLayout2 = (LinearLayout) this.parentView.findViewById(R.id.ad_layout);
            Intrinsics.checkNotNullExpressionValue(linearLayout2, "parentView.ad_layout");
            linearLayout2.setVisibility(0);
            View findViewById2 = this.parentView.findViewById(R.id.native_ad_layout);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "parentView.native_ad_layout");
            findViewById2.setVisibility(8);
            if (adContainer.getAdView().getParent() != null) {
                ViewParent parent = adContainer.getAdView().getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(adContainer.getAdView());
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
                }
            }
            ((FrameLayout) this.parentView.findViewById(R.id.ad_root_view)).addView(adContainer.getAdView(), removeAdView);
            adContainer.getAdView().setVisibility(0);
        } else if (adContainer.getNativeAd() != null) {
            LinearLayout linearLayout3 = (LinearLayout) this.parentView.findViewById(R.id.ad_layout);
            Intrinsics.checkNotNullExpressionValue(linearLayout3, "parentView.ad_layout");
            linearLayout3.setVisibility(8);
            View findViewById3 = this.parentView.findViewById(R.id.native_ad_layout);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "parentView.native_ad_layout");
            findViewById3.setVisibility(0);
            if (baseNativeADViewModel != null) {
                baseNativeADViewModel.bindVariables(adContainer.getNativeAd());
            }
            if (baseNativeADViewModel != null) {
                this.binding.setVariable(BR.admodel, baseNativeADViewModel);
            }
        } else {
            this.parentView.setVisibility(8);
        }
    }

    private final int removeAdView() {
        int i = 0;
        if (((FrameLayout) this.parentView.findViewById(R.id.ad_root_view)) == null) {
            return 0;
        }
        FrameLayout frameLayout = (FrameLayout) this.parentView.findViewById(R.id.ad_root_view);
        Intrinsics.checkNotNullExpressionValue(frameLayout, "parentView.ad_root_view");
        int childCount = frameLayout.getChildCount();
        if (childCount > 0) {
            while (i < childCount) {
                View childAt = ((FrameLayout) this.parentView.findViewById(R.id.ad_root_view)).getChildAt(i);
                if (childAt == null || !(childAt instanceof PublisherAdView)) {
                    i++;
                } else {
                    ((FrameLayout) this.parentView.findViewById(R.id.ad_root_view)).removeViewAt(i);
                    return i;
                }
            }
        }
        return childCount;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/wbmd/ads/AdViewHolder$Companion;", "", "()V", "create", "Lcom/wbmd/ads/AdViewHolder;", "parent", "Landroid/view/ViewGroup;", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: AdViewHolder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AdViewHolder create(ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.ad_item_layout, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "view");
            return new AdViewHolder(inflate);
        }
    }
}
