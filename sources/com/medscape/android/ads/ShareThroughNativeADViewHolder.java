package com.medscape.android.ads;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.medscape.android.R;
import com.medscape.android.ads.sharethrough.SharethroughFallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u001a\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0018\u0010\u0019\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ \u0010\u0019\u001a\u00020\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001dJ\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0010\u0010 \u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u001dH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/medscape/android/ads/ShareThroughNativeADViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "adLayout", "Landroid/widget/FrameLayout;", "dfpADLayout", "Landroid/widget/LinearLayout;", "divider", "falbackHeader", "Landroid/widget/TextView;", "fallbackLayout", "fallbackText", "nativeADLayout", "progressLayout", "applyPadding", "", "paddingLeft", "", "bindFallback", "context", "Landroid/content/Context;", "fallback", "Lcom/medscape/android/ads/sharethrough/SharethroughFallback;", "onBind", "nativeDFPAD", "Lcom/medscape/android/ads/NativeDFPAD;", "withDivider", "", "isLoading", "setDividerVisibility", "setLoading", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ShareThroughNativeADViewHolder.kt */
public final class ShareThroughNativeADViewHolder extends RecyclerView.ViewHolder {
    private final FrameLayout adLayout;
    private final LinearLayout dfpADLayout;
    private final View divider;
    private final TextView falbackHeader;
    private final View fallbackLayout;
    private final TextView fallbackText;
    private final View nativeADLayout;
    private final View progressLayout;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ShareThroughNativeADViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
        View findViewById = view.findViewById(R.id.ad_root_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.ad_root_layout)");
        this.adLayout = (FrameLayout) findViewById;
        View findViewById2 = view.findViewById(R.id.progress_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.progress_layout)");
        this.progressLayout = findViewById2;
        View findViewById3 = view.findViewById(R.id.divider);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.divider)");
        this.divider = findViewById3;
        this.falbackHeader = (TextView) view.findViewById(R.id.sharethrough_fallback_header);
        this.fallbackText = (TextView) view.findViewById(R.id.sharethrough_fallback_text);
        this.fallbackLayout = view.findViewById(R.id.sharethrough_fallback);
        View findViewById4 = view.findViewById(R.id.dfp_adLayout);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "view.findViewById(R.id.dfp_adLayout)");
        this.dfpADLayout = (LinearLayout) findViewById4;
        View findViewById5 = view.findViewById(R.id.native_ad_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "view.findViewById(R.id.native_ad_layout)");
        this.nativeADLayout = findViewById5;
    }

    public final void applyPadding(float f) {
        this.adLayout.setPadding((int) f, 0, 0, 0);
    }

    public final void onBind(NativeDFPAD nativeDFPAD, boolean z) {
        setDividerVisibility(z);
        NativeCustomTemplateAd nativeCustomTemplateAd = null;
        PublisherAdView dfpAD = nativeDFPAD != null ? nativeDFPAD.getDfpAD() : null;
        if (nativeDFPAD != null) {
            nativeCustomTemplateAd = nativeDFPAD.getNativeAD();
        }
        if (dfpAD == null && nativeCustomTemplateAd == null) {
            this.adLayout.setVisibility(8);
            this.progressLayout.setVisibility(0);
        } else {
            View view = this.fallbackLayout;
            if (view != null) {
                view.setVisibility(8);
            }
            this.adLayout.setVisibility(0);
            this.progressLayout.setVisibility(8);
        }
        if (dfpAD != null) {
            if (dfpAD.getParent() != null) {
                ViewParent parent = dfpAD.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeAllViews();
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
                }
            }
            this.dfpADLayout.setVisibility(0);
            this.nativeADLayout.setVisibility(8);
            this.dfpADLayout.removeAllViews();
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 8388627;
            dfpAD.setLayoutParams(layoutParams);
            this.dfpADLayout.addView(dfpAD);
        } else if (nativeCustomTemplateAd != null) {
            this.adLayout.setPadding(0, 0, 0, 0);
            this.dfpADLayout.setVisibility(8);
            ADBindingHelper.Companion.bindNativeAD(this.nativeADLayout, nativeDFPAD);
        }
    }

    public final void onBind(NativeDFPAD nativeDFPAD, boolean z, boolean z2) {
        if (z2) {
            setDividerVisibility(z);
            setLoading(z2);
            return;
        }
        NativeCustomTemplateAd nativeCustomTemplateAd = null;
        if ((nativeDFPAD != null ? nativeDFPAD.getDfpAD() : null) == null) {
            if (nativeDFPAD != null) {
                nativeCustomTemplateAd = nativeDFPAD.getNativeAD();
            }
            if (nativeCustomTemplateAd == null) {
                setDividerVisibility(false);
                setLoading(false);
                return;
            }
        }
        onBind(nativeDFPAD, z);
    }

    private final void setDividerVisibility(boolean z) {
        this.divider.setVisibility(z ? 0 : 8);
    }

    private final void setLoading(boolean z) {
        int i = 8;
        this.adLayout.setVisibility(8);
        View view = this.progressLayout;
        if (z) {
            i = 0;
        }
        view.setVisibility(i);
    }

    public final void bindFallback(Context context, SharethroughFallback sharethroughFallback) {
        if (sharethroughFallback != null) {
            this.progressLayout.setVisibility(8);
            View view = this.fallbackLayout;
            if (view != null) {
                view.setVisibility(0);
            }
            this.adLayout.setVisibility(8);
            TextView textView = this.fallbackText;
            if (textView != null) {
                textView.setText(sharethroughFallback.getText());
            }
            TextView textView2 = this.falbackHeader;
            if (textView2 != null) {
                textView2.setText(sharethroughFallback.getHeader());
            }
            View view2 = this.fallbackLayout;
            if (view2 != null) {
                view2.setOnClickListener(new ShareThroughNativeADViewHolder$bindFallback$1(context, sharethroughFallback));
            }
        }
    }
}
