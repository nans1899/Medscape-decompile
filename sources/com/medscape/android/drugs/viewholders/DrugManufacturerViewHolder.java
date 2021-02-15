package com.medscape.android.drugs.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
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
import com.medscape.android.drugs.model.DrugManufactureAd;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 '2\u00020\u0001:\u0001'B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u001a\u0010\u001b\u001a\u00020\u00152\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u001a\u0010 \u001a\u00020\u00152\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010!\u001a\u00020\"H\u0002J\u001a\u0010#\u001a\u00020$2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010%\u001a\u0004\u0018\u00010&R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/medscape/android/drugs/viewholders/DrugManufacturerViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "dfpADLayout", "Landroid/widget/FrameLayout;", "divider", "falbackHeader", "Landroid/widget/TextView;", "fallbackLayout", "fallbackText", "manufacturerADLayout", "manufacturerADSubTitle", "manufacturerADTitle", "nativeLabel", "nativeLayout", "nativeSubTitle", "nativeTitle", "progressLayout", "bindDfpAd", "", "dfpAD", "Lcom/google/android/gms/ads/doubleclick/PublisherAdView;", "bindDrugManufacturerAd", "dm", "Lcom/medscape/android/drugs/model/DrugManufacturer;", "bindFallback", "context", "Landroid/content/Context;", "fallback", "Lcom/medscape/android/ads/sharethrough/SharethroughFallback;", "bindNativeAd", "nativeAD", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "onBind", "", "adObj", "Lcom/medscape/android/drugs/model/DrugManufactureAd;", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugManufacturerViewHolder.kt */
public final class DrugManufacturerViewHolder extends RecyclerView.ViewHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final FrameLayout dfpADLayout;
    private final View divider;
    private final TextView falbackHeader;
    private final View fallbackLayout;
    private final TextView fallbackText;
    private final View manufacturerADLayout;
    private final TextView manufacturerADSubTitle;
    private final TextView manufacturerADTitle;
    private final TextView nativeLabel;
    private final View nativeLayout;
    private final TextView nativeSubTitle;
    private final TextView nativeTitle;
    private final View progressLayout;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DrugManufacturerViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
        View findViewById = view.findViewById(R.id.progress_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.progress_layout)");
        this.progressLayout = findViewById;
        View findViewById2 = view.findViewById(R.id.divider);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.divider)");
        this.divider = findViewById2;
        View findViewById3 = view.findViewById(R.id.native_ad_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.native_ad_layout)");
        this.nativeLayout = findViewById3;
        View findViewById4 = view.findViewById(R.id.native_label);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "view.findViewById(R.id.native_label)");
        this.nativeLabel = (TextView) findViewById4;
        View findViewById5 = view.findViewById(R.id.native_title);
        Intrinsics.checkNotNullExpressionValue(findViewById5, "view.findViewById(R.id.native_title)");
        this.nativeTitle = (TextView) findViewById5;
        View findViewById6 = view.findViewById(R.id.native_sub_title);
        Intrinsics.checkNotNullExpressionValue(findViewById6, "view.findViewById(R.id.native_sub_title)");
        this.nativeSubTitle = (TextView) findViewById6;
        View findViewById7 = view.findViewById(R.id.drugMonographManufacturersLayout);
        Intrinsics.checkNotNullExpressionValue(findViewById7, "view.findViewById(R.id.d…graphManufacturersLayout)");
        this.manufacturerADLayout = findViewById7;
        View findViewById8 = view.findViewById(R.id.drugMonographManufacturersTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById8, "view.findViewById(R.id.d…ographManufacturersTitle)");
        this.manufacturerADTitle = (TextView) findViewById8;
        View findViewById9 = view.findViewById(R.id.drugMonographManufacturersSubTitle);
        Intrinsics.checkNotNullExpressionValue(findViewById9, "view.findViewById(R.id.d…aphManufacturersSubTitle)");
        this.manufacturerADSubTitle = (TextView) findViewById9;
        View findViewById10 = view.findViewById(R.id.dfp_adLayout);
        Intrinsics.checkNotNullExpressionValue(findViewById10, "view.findViewById(R.id.dfp_adLayout)");
        this.dfpADLayout = (FrameLayout) findViewById10;
        View findViewById11 = view.findViewById(R.id.sharethrough_fallback);
        Intrinsics.checkNotNullExpressionValue(findViewById11, "view.findViewById(R.id.sharethrough_fallback)");
        this.fallbackLayout = findViewById11;
        View findViewById12 = view.findViewById(R.id.sharethrough_fallback_header);
        Intrinsics.checkNotNullExpressionValue(findViewById12, "view.findViewById(R.id.s…ethrough_fallback_header)");
        this.falbackHeader = (TextView) findViewById12;
        View findViewById13 = view.findViewById(R.id.sharethrough_fallback_text);
        Intrinsics.checkNotNullExpressionValue(findViewById13, "view.findViewById(R.id.sharethrough_fallback_text)");
        this.fallbackText = (TextView) findViewById13;
    }

    public final boolean onBind(Context context, DrugManufactureAd drugManufactureAd) {
        if (drugManufactureAd == null || drugManufactureAd.getShowProgress()) {
            this.dfpADLayout.setVisibility(8);
            this.fallbackLayout.setVisibility(8);
            this.manufacturerADLayout.setVisibility(8);
            this.progressLayout.setVisibility(0);
        } else {
            this.progressLayout.setVisibility(8);
        }
        SharethroughFallback sharethroughFallback = null;
        if ((drugManufactureAd != null ? drugManufactureAd.getAd() : null) == null) {
            if ((drugManufactureAd != null ? drugManufactureAd.getDm() : null) != null) {
                bindDrugManufacturerAd(drugManufactureAd.getDm());
            } else {
                if (drugManufactureAd != null) {
                    sharethroughFallback = drugManufactureAd.getFallBack();
                }
                if (sharethroughFallback == null) {
                    return false;
                }
                bindFallback(context, drugManufactureAd.getFallBack());
                return false;
            }
        } else if (drugManufactureAd.getAd().getNativeAD() != null) {
            NativeCustomTemplateAd nativeAD = drugManufactureAd.getAd().getNativeAD();
            Intrinsics.checkNotNull(nativeAD);
            bindNativeAd(context, nativeAD);
        } else if (drugManufactureAd.getAd().getDfpAD() == null) {
            return false;
        } else {
            PublisherAdView dfpAD = drugManufactureAd.getAd().getDfpAD();
            Intrinsics.checkNotNull(dfpAD);
            bindDfpAd(dfpAD);
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void bindDrugManufacturerAd(com.medscape.android.drugs.model.DrugManufacturer r6) {
        /*
            r5 = this;
            android.view.View r0 = r5.nativeLayout
            r1 = 8
            r0.setVisibility(r1)
            android.widget.FrameLayout r0 = r5.dfpADLayout
            r0.setVisibility(r1)
            android.view.View r0 = r5.fallbackLayout
            r0.setVisibility(r1)
            android.view.View r0 = r5.manufacturerADLayout
            r2 = 0
            r0.setVisibility(r2)
            java.lang.String r0 = r6.getTitle()
            r3 = 1
            if (r0 == 0) goto L_0x0072
            java.lang.String r0 = r6.getTitle()
            java.lang.String r4 = "dm.title"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0031
            r0 = 1
            goto L_0x0032
        L_0x0031:
            r0 = 0
        L_0x0032:
            if (r0 == 0) goto L_0x0072
            android.widget.TextView r0 = r5.manufacturerADTitle
            java.lang.String r4 = r6.getTitle()
            android.text.Spanned r4 = android.text.Html.fromHtml(r4)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r0.setText(r4)
            android.widget.TextView r0 = r5.manufacturerADTitle
            r0.setVisibility(r2)
            java.lang.String r0 = r6.getTitleColor()
            if (r0 == 0) goto L_0x0077
            java.lang.String r0 = r6.getTitleColor()
            java.lang.String r4 = "dm.titleColor"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0061
            r0 = 1
            goto L_0x0062
        L_0x0061:
            r0 = 0
        L_0x0062:
            if (r0 == 0) goto L_0x0077
            android.widget.TextView r0 = r5.manufacturerADTitle
            java.lang.String r4 = r6.getTitleColor()
            int r4 = android.graphics.Color.parseColor(r4)
            r0.setTextColor(r4)
            goto L_0x0077
        L_0x0072:
            android.widget.TextView r0 = r5.manufacturerADTitle
            r0.setVisibility(r1)
        L_0x0077:
            java.lang.String r0 = r6.getSubTitle()
            if (r0 == 0) goto L_0x00a8
            java.lang.String r0 = r6.getSubTitle()
            java.lang.String r4 = "dm.subTitle"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0090
            r0 = 1
            goto L_0x0091
        L_0x0090:
            r0 = 0
        L_0x0091:
            if (r0 == 0) goto L_0x00a8
            android.widget.TextView r0 = r5.manufacturerADSubTitle
            java.lang.String r1 = r6.getSubTitle()
            android.text.Spanned r1 = android.text.Html.fromHtml(r1)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r0.setText(r1)
            android.widget.TextView r0 = r5.manufacturerADSubTitle
            r0.setVisibility(r2)
            goto L_0x00ad
        L_0x00a8:
            android.widget.TextView r0 = r5.manufacturerADSubTitle
            r0.setVisibility(r1)
        L_0x00ad:
            java.lang.String r0 = r6.getUrl()
            if (r0 == 0) goto L_0x00d3
            java.lang.String r0 = r6.getUrl()
            java.lang.String r1 = "dm.url"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x00c5
            r2 = 1
        L_0x00c5:
            if (r2 == 0) goto L_0x00d3
            android.view.View r0 = r5.manufacturerADLayout
            com.medscape.android.drugs.viewholders.DrugManufacturerViewHolder$bindDrugManufacturerAd$1 r1 = new com.medscape.android.drugs.viewholders.DrugManufacturerViewHolder$bindDrugManufacturerAd$1
            r1.<init>(r5, r6)
            android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
            r0.setOnClickListener(r1)
        L_0x00d3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.viewholders.DrugManufacturerViewHolder.bindDrugManufacturerAd(com.medscape.android.drugs.model.DrugManufacturer):void");
    }

    private final void bindDfpAd(PublisherAdView publisherAdView) {
        this.nativeLayout.setVisibility(8);
        this.dfpADLayout.setVisibility(0);
        this.fallbackLayout.setVisibility(8);
        this.manufacturerADLayout.setVisibility(8);
        if (publisherAdView.getParent() != null) {
            ViewParent parent = publisherAdView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeAllViews();
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
        }
        this.dfpADLayout.removeAllViews();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 8388627;
        publisherAdView.setLayoutParams(layoutParams);
        this.dfpADLayout.addView(publisherAdView);
    }

    private final void bindNativeAd(Context context, NativeCustomTemplateAd nativeCustomTemplateAd) {
        this.nativeLayout.setVisibility(0);
        this.dfpADLayout.setVisibility(8);
        this.fallbackLayout.setVisibility(8);
        this.manufacturerADLayout.setVisibility(8);
        CharSequence text = nativeCustomTemplateAd.getText("AdLabel");
        CharSequence text2 = nativeCustomTemplateAd.getText("Title");
        CharSequence text3 = nativeCustomTemplateAd.getText("Body");
        CharSequence text4 = nativeCustomTemplateAd.getText("ClickthroughURL");
        boolean z = true;
        if (!(text == null || StringsKt.isBlank(text))) {
            this.nativeLabel.setVisibility(0);
            this.nativeLabel.setText(text);
        } else {
            this.nativeLabel.setVisibility(8);
        }
        if (!(text2 == null || StringsKt.isBlank(text2))) {
            this.nativeTitle.setVisibility(0);
            this.nativeTitle.setText(text2);
        } else {
            this.nativeTitle.setVisibility(8);
        }
        if (text3 != null && !StringsKt.isBlank(text3)) {
            z = false;
        }
        if (!z) {
            this.nativeSubTitle.setVisibility(0);
            this.nativeSubTitle.setText(text3);
        } else {
            this.nativeSubTitle.setVisibility(8);
        }
        this.nativeLayout.setOnClickListener(new DrugManufacturerViewHolder$bindNativeAd$1(nativeCustomTemplateAd, text4, context));
    }

    private final void bindFallback(Context context, SharethroughFallback sharethroughFallback) {
        this.nativeLayout.setVisibility(8);
        this.dfpADLayout.setVisibility(8);
        this.fallbackLayout.setVisibility(0);
        this.manufacturerADLayout.setVisibility(8);
        this.fallbackText.setText(sharethroughFallback.getText());
        this.falbackHeader.setText(sharethroughFallback.getHeader());
        this.fallbackLayout.setOnClickListener(new DrugManufacturerViewHolder$bindFallback$1(context, sharethroughFallback));
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/medscape/android/drugs/viewholders/DrugManufacturerViewHolder$Companion;", "", "()V", "create", "Lcom/medscape/android/drugs/viewholders/DrugManufacturerViewHolder;", "inflator", "Landroid/view/LayoutInflater;", "parent", "Landroid/view/ViewGroup;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: DrugManufacturerViewHolder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DrugManufacturerViewHolder create(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(layoutInflater, "inflator");
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            View inflate = layoutInflater.inflate(R.layout.drug_manufacturer_ad, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflator.inflate(R.layou…cturer_ad, parent, false)");
            return new DrugManufacturerViewHolder(inflate);
        }
    }
}
