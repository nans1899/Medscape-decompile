package com.medscape.android.landingfeed.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.databinding.HomeNativeAdLayoutBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemNativeADViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/medscape/android/databinding/HomeNativeAdLayoutBinding;", "(Lcom/medscape/android/databinding/HomeNativeAdLayoutBinding;)V", "getBinding", "()Lcom/medscape/android/databinding/HomeNativeAdLayoutBinding;", "bindAD", "", "mContext", "Landroidx/fragment/app/FragmentActivity;", "landingFeedViewModel", "Lcom/medscape/android/landingfeed/viewmodel/LandingFeedViewModel;", "adItem", "Lcom/medscape/android/landingfeed/model/FeedAdItem;", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedItemNativeADViewHolder.kt */
public final class FeedItemNativeADViewHolder extends RecyclerView.ViewHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final HomeNativeAdLayoutBinding binding;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeedItemNativeADViewHolder(HomeNativeAdLayoutBinding homeNativeAdLayoutBinding) {
        super(homeNativeAdLayoutBinding.getRoot());
        Intrinsics.checkNotNullParameter(homeNativeAdLayoutBinding, "binding");
        this.binding = homeNativeAdLayoutBinding;
    }

    public final HomeNativeAdLayoutBinding getBinding() {
        return this.binding;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
        r2 = r15.getNativeDFPAD();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void bindAD(androidx.fragment.app.FragmentActivity r13, com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel r14, com.medscape.android.landingfeed.model.FeedAdItem r15) {
        /*
            r12 = this;
            java.lang.String r0 = "mContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "landingFeedViewModel"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            com.medscape.android.databinding.HomeNativeAdLayoutBinding r0 = r12.binding
            r1 = 0
            if (r15 == 0) goto L_0x001a
            com.medscape.android.ads.NativeDFPAD r2 = r15.getNativeDFPAD()
            if (r2 == 0) goto L_0x001a
            com.google.android.gms.ads.doubleclick.PublisherAdView r2 = r2.getDfpAD()
            goto L_0x001b
        L_0x001a:
            r2 = r1
        L_0x001b:
            r0.setDfpad(r2)
            com.medscape.android.databinding.HomeNativeAdLayoutBinding r0 = r12.binding
            if (r15 == 0) goto L_0x002d
            com.medscape.android.ads.NativeDFPAD r2 = r15.getNativeDFPAD()
            if (r2 == 0) goto L_0x002d
            com.google.android.gms.ads.formats.NativeCustomTemplateAd r2 = r2.getNativeAD()
            goto L_0x002e
        L_0x002d:
            r2 = r1
        L_0x002e:
            r0.setNativead(r2)
            com.medscape.android.ads.ADBindingHelper$Companion r0 = com.medscape.android.ads.ADBindingHelper.Companion
            android.view.View r2 = r12.itemView
            java.lang.String r3 = "itemView"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            int r4 = com.medscape.android.R.id.ad_root_layout
            android.view.View r2 = r2.findViewById(r4)
            if (r15 == 0) goto L_0x0046
            com.medscape.android.ads.NativeDFPAD r1 = r15.getNativeDFPAD()
        L_0x0046:
            r0.bindCombinedAD(r2, r1)
            com.medscape.android.databinding.HomeNativeAdLayoutBinding r0 = r12.binding
            com.google.android.gms.ads.doubleclick.PublisherAdView r0 = r0.getDfpad()
            java.lang.String r1 = "itemView.ad_root_layout"
            java.lang.String r2 = "itemView.bottom_margin"
            r4 = 8
            r5 = 0
            if (r0 == 0) goto L_0x0087
            android.view.View r13 = r12.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            int r14 = com.medscape.android.R.id.ad_root_layout
            android.view.View r13 = r13.findViewById(r14)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r1)
            int r14 = com.medscape.android.R.id.top_margin
            android.view.View r13 = r13.findViewById(r14)
            java.lang.String r14 = "itemView.ad_root_layout.top_margin"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r14)
            r13.setVisibility(r4)
            android.view.View r13 = r12.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            int r14 = com.medscape.android.R.id.bottom_margin
            android.view.View r13 = r13.findViewById(r14)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            r13.setVisibility(r5)
            goto L_0x012a
        L_0x0087:
            com.medscape.android.databinding.HomeNativeAdLayoutBinding r0 = r12.binding
            com.google.android.gms.ads.formats.NativeCustomTemplateAd r0 = r0.getNativead()
            java.lang.String r6 = "itemView.top_margin"
            if (r0 == 0) goto L_0x0108
            android.view.View r0 = r12.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            int r4 = com.medscape.android.R.id.top_margin
            android.view.View r0 = r0.findViewById(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r6)
            r0.setVisibility(r5)
            android.view.View r0 = r12.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            int r4 = com.medscape.android.R.id.bottom_margin
            android.view.View r0 = r0.findViewById(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            r0.setVisibility(r5)
            if (r15 == 0) goto L_0x00c1
            com.medscape.android.ads.NativeDFPAD r0 = r15.getNativeDFPAD()
            if (r0 == 0) goto L_0x00c1
            int r5 = r0.getPgValue()
            r11 = r5
            goto L_0x00c2
        L_0x00c1:
            r11 = 0
        L_0x00c2:
            if (r15 == 0) goto L_0x00e2
            com.medscape.android.ads.NativeDFPAD r0 = r15.getNativeDFPAD()
            if (r0 == 0) goto L_0x00e2
            com.google.android.gms.ads.formats.NativeCustomTemplateAd r0 = r0.getNativeAD()
            if (r0 == 0) goto L_0x00e2
            java.lang.String r0 = r0.getCustomTemplateId()
            if (r0 == 0) goto L_0x00e2
            java.lang.String r2 = "11848473"
            boolean r0 = r0.equals(r2)
            r2 = 1
            if (r0 != r2) goto L_0x00e2
            r14.sendNativeAdViewedImpression(r13, r11)
        L_0x00e2:
            android.view.View r0 = r12.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            int r2 = com.medscape.android.R.id.ad_root_layout
            android.view.View r0 = r0.findViewById(r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int r1 = com.medscape.android.R.id.native_ad_layout
            android.view.View r0 = r0.findViewById(r1)
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            com.medscape.android.landingfeed.views.FeedItemNativeADViewHolder$bindAD$1 r1 = new com.medscape.android.landingfeed.views.FeedItemNativeADViewHolder$bindAD$1
            r6 = r1
            r7 = r12
            r8 = r15
            r9 = r14
            r10 = r13
            r6.<init>(r7, r8, r9, r10, r11)
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r0.setOnClickListener(r1)
            goto L_0x012a
        L_0x0108:
            android.view.View r13 = r12.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            int r14 = com.medscape.android.R.id.top_margin
            android.view.View r13 = r13.findViewById(r14)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r6)
            r13.setVisibility(r4)
            android.view.View r13 = r12.itemView
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r3)
            int r14 = com.medscape.android.R.id.bottom_margin
            android.view.View r13 = r13.findViewById(r14)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r2)
            r13.setVisibility(r4)
        L_0x012a:
            com.medscape.android.databinding.HomeNativeAdLayoutBinding r13 = r12.binding
            r13.executePendingBindings()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.views.FeedItemNativeADViewHolder.bindAD(androidx.fragment.app.FragmentActivity, com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel, com.medscape.android.landingfeed.model.FeedAdItem):void");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedItemNativeADViewHolder$Companion;", "", "()V", "create", "Lcom/medscape/android/landingfeed/views/FeedItemNativeADViewHolder;", "parent", "Landroid/view/ViewGroup;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FeedItemNativeADViewHolder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FeedItemNativeADViewHolder create(ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            HomeNativeAdLayoutBinding inflate = HomeNativeAdLayoutBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
            Intrinsics.checkNotNullExpressionValue(inflate, "HomeNativeAdLayoutBinding.inflate(layoutInflater)");
            return new FeedItemNativeADViewHolder(inflate);
        }
    }
}
