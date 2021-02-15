package com.medscape.android.ads;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.medscape.android.util.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/medscape/android/ads/ADBindingHelper;", "", "()V", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ADBindingHelper.kt */
public final class ADBindingHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u001a\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J*\u0010\u0013\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a¨\u0006\u001b"}, d2 = {"Lcom/medscape/android/ads/ADBindingHelper$Companion;", "", "()V", "applyFullScreenADWidth", "", "adView", "Lcom/google/android/gms/ads/doubleclick/PublisherAdView;", "bindCombinedAD", "adRootLayout", "Landroid/view/View;", "nativeDFPAD", "Lcom/medscape/android/ads/NativeDFPAD;", "bindNativeAD", "nativeLayout", "removeAdView", "dfpAdLayout", "Landroid/widget/LinearLayout;", "adPos", "", "setClickableSpanForLinks", "content", "", "spanUrl", "context", "Landroid/content/Context;", "textView", "Landroid/widget/TextView;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ADBindingHelper.kt */
    public static final class Companion {

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[NativeStyleAd.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[NativeStyleAd.TEXT_AD.ordinal()] = 1;
                $EnumSwitchMapping$0[NativeStyleAd.GRAPHIC_DRIVER_SMALL_AD.ordinal()] = 2;
                $EnumSwitchMapping$0[NativeStyleAd.GRAPHIC_DRIVER_LARGE_AD.ordinal()] = 3;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
            if ((r10 != null ? r10.getNativeAD() : null) != null) goto L_0x0029;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void bindCombinedAD(android.view.View r9, com.medscape.android.ads.NativeDFPAD r10) {
            /*
                r8 = this;
                if (r9 == 0) goto L_0x0101
                r0 = 2131363203(0x7f0a0583, float:1.8346208E38)
                android.view.View r0 = r9.findViewById(r0)
                r1 = 2131362603(0x7f0a032b, float:1.8344991E38)
                android.view.View r1 = r9.findViewById(r1)
                android.widget.LinearLayout r1 = (android.widget.LinearLayout) r1
                r2 = 0
                if (r10 == 0) goto L_0x001a
                com.google.android.gms.ads.doubleclick.PublisherAdView r3 = r10.getDfpAD()
                goto L_0x001b
            L_0x001a:
                r3 = r2
            L_0x001b:
                r4 = 8
                if (r3 != 0) goto L_0x0029
                if (r10 == 0) goto L_0x0026
                com.google.android.gms.ads.formats.NativeCustomTemplateAd r3 = r10.getNativeAD()
                goto L_0x0027
            L_0x0026:
                r3 = r2
            L_0x0027:
                if (r3 == 0) goto L_0x00ee
            L_0x0029:
                if (r0 == 0) goto L_0x00ee
                if (r1 == 0) goto L_0x00ee
                r3 = 0
                r9.setVisibility(r3)
                r5 = 2131362602(0x7f0a032a, float:1.834499E38)
                android.view.View r5 = r9.findViewById(r5)
                r6 = 2131362025(0x7f0a00e9, float:1.8343819E38)
                android.view.View r9 = r9.findViewById(r6)
                if (r5 == 0) goto L_0x0069
                com.google.android.gms.ads.doubleclick.PublisherAdView r6 = r10.getDfpAD()
                if (r6 == 0) goto L_0x0064
                com.google.android.gms.ads.AdSize[] r6 = com.medscape.android.ads.DFPAdAction.advLabelSizes
                java.lang.String r7 = "DFPAdAction.advLabelSizes"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
                com.google.android.gms.ads.doubleclick.PublisherAdView r7 = r10.getDfpAD()
                if (r7 == 0) goto L_0x0059
                com.google.android.gms.ads.AdSize r7 = r7.getAdSize()
                goto L_0x005a
            L_0x0059:
                r7 = r2
            L_0x005a:
                boolean r6 = kotlin.collections.ArraysKt.contains((T[]) r6, r7)
                if (r6 == 0) goto L_0x0064
                r5.setVisibility(r3)
                goto L_0x0067
            L_0x0064:
                r5.setVisibility(r4)
            L_0x0067:
                r5 = 1
                goto L_0x006a
            L_0x0069:
                r5 = 0
            L_0x006a:
                if (r9 == 0) goto L_0x006e
                int r5 = r5 + 1
            L_0x006e:
                com.google.android.gms.ads.doubleclick.PublisherAdView r9 = r10.getDfpAD()
                if (r9 == 0) goto L_0x00e2
                r0.setVisibility(r4)
                r1.setVisibility(r4)
                com.medscape.android.ads.ADBindingHelper$Companion r9 = com.medscape.android.ads.ADBindingHelper.Companion
                r9.removeAdView(r1, r5)
                r1.setVisibility(r3)
                com.google.android.gms.ads.doubleclick.PublisherAdView r9 = r10.getDfpAD()
                if (r9 == 0) goto L_0x008d
                android.view.ViewParent r9 = r9.getParent()
                goto L_0x008e
            L_0x008d:
                r9 = r2
            L_0x008e:
                if (r9 == 0) goto L_0x00b0
                com.google.android.gms.ads.doubleclick.PublisherAdView r9 = r10.getDfpAD()
                if (r9 == 0) goto L_0x009a
                android.view.ViewParent r2 = r9.getParent()
            L_0x009a:
                if (r2 == 0) goto L_0x00a8
                android.view.ViewGroup r2 = (android.view.ViewGroup) r2
                com.google.android.gms.ads.doubleclick.PublisherAdView r9 = r10.getDfpAD()
                android.view.View r9 = (android.view.View) r9
                r2.removeView(r9)
                goto L_0x00b0
            L_0x00a8:
                java.lang.NullPointerException r9 = new java.lang.NullPointerException
                java.lang.String r10 = "null cannot be cast to non-null type android.view.ViewGroup"
                r9.<init>(r10)
                throw r9
            L_0x00b0:
                android.widget.LinearLayout$LayoutParams r9 = new android.widget.LinearLayout$LayoutParams
                r0 = -1
                r2 = -2
                r9.<init>(r0, r2)
                r0 = 17
                r9.gravity = r0
                com.google.android.gms.ads.doubleclick.PublisherAdView r0 = r10.getDfpAD()
                if (r0 == 0) goto L_0x00c6
                android.view.ViewGroup$LayoutParams r9 = (android.view.ViewGroup.LayoutParams) r9
                r0.setLayoutParams(r9)
            L_0x00c6:
                com.google.android.gms.ads.doubleclick.PublisherAdView r9 = r10.getDfpAD()
                if (r9 == 0) goto L_0x00cf
                r9.setVisibility(r3)
            L_0x00cf:
                com.medscape.android.ads.ADBindingHelper$Companion r9 = com.medscape.android.ads.ADBindingHelper.Companion
                com.google.android.gms.ads.doubleclick.PublisherAdView r0 = r10.getDfpAD()
                r9.applyFullScreenADWidth(r0)
                com.google.android.gms.ads.doubleclick.PublisherAdView r9 = r10.getDfpAD()
                android.view.View r9 = (android.view.View) r9
                r1.addView(r9, r5)
                goto L_0x0101
            L_0x00e2:
                r0.setVisibility(r3)
                r1.setVisibility(r4)
                com.medscape.android.ads.ADBindingHelper$Companion r9 = com.medscape.android.ads.ADBindingHelper.Companion
                r9.bindNativeAD(r0, r10)
                goto L_0x0101
            L_0x00ee:
                java.lang.String r10 = "nativeAdLayout"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r10)
                r0.setVisibility(r4)
                java.lang.String r10 = "dfpAdLayout"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r10)
                r1.setVisibility(r4)
                r9.setVisibility(r4)
            L_0x0101:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.ads.ADBindingHelper.Companion.bindCombinedAD(android.view.View, com.medscape.android.ads.NativeDFPAD):void");
        }

        /* JADX WARNING: Removed duplicated region for block: B:161:0x0517  */
        /* JADX WARNING: Removed duplicated region for block: B:162:0x0519  */
        /* JADX WARNING: Removed duplicated region for block: B:167:0x0525  */
        /* JADX WARNING: Removed duplicated region for block: B:168:0x0560  */
        /* JADX WARNING: Removed duplicated region for block: B:171:0x0579  */
        /* JADX WARNING: Removed duplicated region for block: B:172:0x057b  */
        /* JADX WARNING: Removed duplicated region for block: B:177:0x0587  */
        /* JADX WARNING: Removed duplicated region for block: B:178:0x05c2  */
        /* JADX WARNING: Removed duplicated region for block: B:180:0x05d4  */
        /* JADX WARNING: Removed duplicated region for block: B:181:0x05db  */
        /* JADX WARNING: Removed duplicated region for block: B:184:0x05e9  */
        /* JADX WARNING: Removed duplicated region for block: B:185:0x05eb  */
        /* JADX WARNING: Removed duplicated region for block: B:190:0x05f9  */
        /* JADX WARNING: Removed duplicated region for block: B:197:0x0676  */
        /* JADX WARNING: Removed duplicated region for block: B:202:0x06aa  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void bindNativeAD(android.view.View r21, com.medscape.android.ads.NativeDFPAD r22) {
            /*
                r20 = this;
                r0 = r21
                java.lang.String r1 = "nativeLayout"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
                android.content.Context r1 = r21.getContext()
                r2 = 2131951704(0x7f130058, float:1.953983E38)
                java.lang.String r1 = r1.getString(r2)
                java.lang.String r2 = "nativeLayout.context.get…g(R.string.advertisement)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                if (r22 == 0) goto L_0x001e
                com.google.android.gms.ads.formats.NativeCustomTemplateAd r3 = r22.getNativeAD()
                goto L_0x001f
            L_0x001e:
                r3 = 0
            L_0x001f:
                if (r3 == 0) goto L_0x0028
                java.lang.String r4 = "Title"
                java.lang.CharSequence r4 = r3.getText(r4)
                goto L_0x0029
            L_0x0028:
                r4 = 0
            L_0x0029:
                java.lang.String r4 = java.lang.String.valueOf(r4)
                if (r3 == 0) goto L_0x0036
                java.lang.String r5 = "Label"
                java.lang.CharSequence r5 = r3.getText(r5)
                goto L_0x0037
            L_0x0036:
                r5 = 0
            L_0x0037:
                java.lang.String r5 = java.lang.String.valueOf(r5)
                if (r3 == 0) goto L_0x0044
                java.lang.String r6 = "Body"
                java.lang.CharSequence r6 = r3.getText(r6)
                goto L_0x0045
            L_0x0044:
                r6 = 0
            L_0x0045:
                java.lang.String r6 = java.lang.String.valueOf(r6)
                r7 = r6
                java.lang.CharSequence r7 = (java.lang.CharSequence) r7
                int r7 = r7.length()
                r8 = 1
                r9 = 0
                if (r7 != 0) goto L_0x0056
                r7 = 1
                goto L_0x0057
            L_0x0056:
                r7 = 0
            L_0x0057:
                if (r7 == 0) goto L_0x0067
                if (r3 == 0) goto L_0x0062
                java.lang.String r6 = "subtitle"
                java.lang.CharSequence r6 = r3.getText(r6)
                goto L_0x0063
            L_0x0062:
                r6 = 0
            L_0x0063:
                java.lang.String r6 = java.lang.String.valueOf(r6)
            L_0x0067:
                if (r3 == 0) goto L_0x0070
                java.lang.String r7 = "CTAText"
                java.lang.CharSequence r7 = r3.getText(r7)
                goto L_0x0071
            L_0x0070:
                r7 = 0
            L_0x0071:
                java.lang.String r7 = java.lang.String.valueOf(r7)
                if (r3 == 0) goto L_0x007e
                java.lang.String r10 = "References"
                java.lang.CharSequence r10 = r3.getText(r10)
                goto L_0x007f
            L_0x007e:
                r10 = 0
            L_0x007f:
                java.lang.String r10 = java.lang.String.valueOf(r10)
                if (r3 == 0) goto L_0x00aa
                java.lang.String r11 = "AdLabel"
                java.lang.CharSequence r11 = r3.getText(r11)
                if (r11 == 0) goto L_0x00aa
                java.lang.String r11 = r11.toString()
                if (r11 == 0) goto L_0x00aa
                r12 = r11
                java.lang.CharSequence r12 = (java.lang.CharSequence) r12
                boolean r12 = kotlin.text.StringsKt.isBlank(r12)
                r12 = r12 ^ r8
                if (r12 == 0) goto L_0x009e
                r1 = r11
            L_0x009e:
                java.lang.String r12 = "ifi"
                boolean r11 = kotlin.text.StringsKt.equals(r11, r12, r8)
                if (r11 == 0) goto L_0x00a8
                java.lang.String r1 = "INFORMATION FROM INDUSTRY"
            L_0x00a8:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
            L_0x00aa:
                r11 = r4
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11
                if (r11 == 0) goto L_0x00b8
                int r12 = r11.length()
                if (r12 != 0) goto L_0x00b6
                goto L_0x00b8
            L_0x00b6:
                r12 = 0
                goto L_0x00b9
            L_0x00b8:
                r12 = 1
            L_0x00b9:
                if (r12 != 0) goto L_0x06c2
                java.lang.String r12 = "null"
                boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r12)
                r14 = r14 ^ r8
                if (r14 == 0) goto L_0x06c2
                r0.setVisibility(r9)
                if (r22 == 0) goto L_0x00ce
                com.medscape.android.ads.NativeStyleAd r14 = r22.getNativeStyle()
                goto L_0x00cf
            L_0x00ce:
                r14 = 0
            L_0x00cf:
                java.lang.String r15 = "nativeLayout.content"
                java.lang.String r2 = "nativeLayout.graphic_driver_ad_inline"
                java.lang.String r16 = ""
                java.lang.String r9 = "nativeLayout.context"
                java.lang.String r13 = "nativeLayout.graphic_driver_ad_banner"
                java.lang.String r8 = "nativeLayout.label"
                r17 = r3
                java.lang.String r3 = "nativeLayout.text_ad"
                if (r14 == 0) goto L_0x0450
                com.medscape.android.ads.NativeStyleAd r14 = r22.getNativeStyle()
                if (r14 != 0) goto L_0x00e9
                goto L_0x01cd
            L_0x00e9:
                int[] r18 = com.medscape.android.ads.ADBindingHelper.Companion.WhenMappings.$EnumSwitchMapping$0
                int r14 = r14.ordinal()
                r14 = r18[r14]
                r18 = r10
                r10 = 2
                r19 = r15
                r15 = 1
                if (r14 == r15) goto L_0x01d6
                if (r14 == r10) goto L_0x0170
                r4 = 3
                if (r14 == r4) goto L_0x0100
                goto L_0x01cd
            L_0x0100:
                int r4 = com.medscape.android.R.id.text_ad
                android.view.View r4 = r0.findViewById(r4)
                android.widget.LinearLayout r4 = (android.widget.LinearLayout) r4
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r3)
                r5 = 8
                r4.setVisibility(r5)
                int r4 = com.medscape.android.R.id.graphic_driver_ad_banner
                android.view.View r4 = r0.findViewById(r4)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r13)
                r4.setVisibility(r5)
                int r4 = com.medscape.android.R.id.graphic_driver_ad_inline
                android.view.View r4 = r0.findViewById(r4)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
                r2 = 0
                r4.setVisibility(r2)
                int r2 = com.medscape.android.R.id.graphic_title_inline
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                java.lang.String r4 = "nativeLayout.graphic_title_inline"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
                r2.setText(r11)
                int r2 = com.medscape.android.R.id.graphic_body
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                java.lang.String r4 = "nativeLayout.graphic_body"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6
                r2.setText(r6)
                int r2 = com.medscape.android.R.id.graphic_adLabel_inline
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                java.lang.String r4 = "nativeLayout.graphic_adLabel_inline"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r2.setText(r1)
                int r1 = com.medscape.android.R.id.graphic_open_inline
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                java.lang.String r2 = "nativeLayout.graphic_open_inline"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                java.lang.CharSequence r7 = (java.lang.CharSequence) r7
                r1.setText(r7)
                goto L_0x01cd
            L_0x0170:
                int r4 = com.medscape.android.R.id.text_ad
                android.view.View r4 = r0.findViewById(r4)
                android.widget.LinearLayout r4 = (android.widget.LinearLayout) r4
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r3)
                r5 = 8
                r4.setVisibility(r5)
                int r4 = com.medscape.android.R.id.graphic_driver_ad_banner
                android.view.View r4 = r0.findViewById(r4)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r13)
                r6 = 0
                r4.setVisibility(r6)
                int r4 = com.medscape.android.R.id.graphic_driver_ad_inline
                android.view.View r4 = r0.findViewById(r4)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
                r4.setVisibility(r5)
                int r2 = com.medscape.android.R.id.graphic_title
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                java.lang.String r4 = "nativeLayout.graphic_title"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
                r2.setText(r11)
                int r2 = com.medscape.android.R.id.graphic_adLabel
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                java.lang.String r4 = "nativeLayout.graphic_adLabel"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r2.setText(r1)
                int r1 = com.medscape.android.R.id.graphic_open
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                java.lang.String r2 = "nativeLayout.graphic_open"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                java.lang.CharSequence r7 = (java.lang.CharSequence) r7
                r1.setText(r7)
            L_0x01cd:
                r1 = r16
                r2 = r1
                r5 = r2
                r6 = r5
                r4 = r17
                goto L_0x050e
            L_0x01d6:
                int r1 = com.medscape.android.R.id.text_ad
                android.view.View r1 = r0.findViewById(r1)
                android.widget.LinearLayout r1 = (android.widget.LinearLayout) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                r14 = 0
                r1.setVisibility(r14)
                int r1 = com.medscape.android.R.id.graphic_driver_ad_banner
                android.view.View r1 = r0.findViewById(r1)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r13)
                r14 = 8
                r1.setVisibility(r14)
                int r1 = com.medscape.android.R.id.graphic_driver_ad_inline
                android.view.View r1 = r0.findViewById(r1)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                r1.setVisibility(r14)
                r1 = r5
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                if (r1 == 0) goto L_0x020d
                int r2 = r1.length()
                if (r2 != 0) goto L_0x020b
                goto L_0x020d
            L_0x020b:
                r2 = 0
                goto L_0x020e
            L_0x020d:
                r2 = 1
            L_0x020e:
                if (r2 != 0) goto L_0x0236
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r12)
                r5 = 1
                r2 = r2 ^ r5
                if (r2 == 0) goto L_0x0236
                int r2 = com.medscape.android.R.id.label
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r8)
                r2.setText(r1)
                int r1 = com.medscape.android.R.id.label
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
                r2 = 0
                r1.setVisibility(r2)
                goto L_0x0246
            L_0x0236:
                int r1 = com.medscape.android.R.id.label
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
                r2 = 8
                r1.setVisibility(r2)
            L_0x0246:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                if (r4 == 0) goto L_0x0448
                java.lang.CharSequence r2 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r11)
                java.lang.String r2 = r2.toString()
                r1.append(r2)
                java.lang.String r2 = ": "
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                android.text.SpannableStringBuilder r2 = new android.text.SpannableStringBuilder
                r4 = r1
                java.lang.CharSequence r4 = (java.lang.CharSequence) r4
                r2.<init>(r4)
                android.text.style.StyleSpan r4 = new android.text.style.StyleSpan
                r5 = 1
                r4.<init>(r5)
                int r1 = r1.length()
                r5 = 33
                r8 = 0
                r2.setSpan(r4, r8, r1, r5)
                java.lang.String r1 = r22.getPosValue()
                android.content.Context r4 = r21.getContext()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r9)
                android.content.res.Resources r4 = r4.getResources()
                r8 = 2131953369(0x7f1306d9, float:1.9543207E38)
                java.lang.String r4 = r4.getString(r8)
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)
                java.lang.String r4 = "nativeLayout.next_arrow"
                java.lang.String r8 = " "
                java.lang.String r11 = "nativeLayout.references"
                if (r1 == 0) goto L_0x02dd
                int r1 = com.medscape.android.R.id.next_arrow
                android.view.View r1 = r0.findViewById(r1)
                android.widget.ImageView r1 = (android.widget.ImageView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
                r4 = 0
                r1.setVisibility(r4)
                int r1 = com.medscape.android.R.id.references
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r11)
                r4 = 8
                r1.setVisibility(r4)
                int r1 = com.medscape.android.R.id.content
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                r14 = r19
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r14)
                int r4 = r2.length()
                int r4 = r4 - r10
                int r5 = r2.length()
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8
                android.text.SpannableStringBuilder r2 = r2.replace(r4, r5, r8)
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.setText(r2)
                goto L_0x040a
            L_0x02dd:
                r14 = r19
                android.text.SpannableStringBuilder r1 = new android.text.SpannableStringBuilder
                r1.<init>()
                java.lang.String r1 = r22.getPosValue()
                android.content.Context r15 = r21.getContext()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r9)
                android.content.res.Resources r15 = r15.getResources()
                r5 = 2131951825(0x7f1300d1, float:1.9540075E38)
                java.lang.String r5 = r15.getString(r5)
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r5)
                if (r1 == 0) goto L_0x0325
                int r1 = com.medscape.android.R.id.references
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r11)
                r5 = 8
                r1.setVisibility(r5)
                int r1 = r2.length()
                int r1 = r1 - r10
                int r5 = r2.length()
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8
                android.text.SpannableStringBuilder r1 = r2.replace(r1, r5, r8)
                java.lang.String r2 = "str.replace(str.length - 2, str.length,  \" \")"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                goto L_0x0371
            L_0x0325:
                int r1 = com.medscape.android.R.id.references
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r11)
                java.lang.String r5 = r22.getStyleConfig()
                java.lang.String r15 = "text and graphic with body"
                boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r15)
                if (r5 == 0) goto L_0x033e
                r5 = 0
                goto L_0x0340
            L_0x033e:
                r5 = 8
            L_0x0340:
                r1.setVisibility(r5)
                java.lang.CharSequence r6 = (java.lang.CharSequence) r6
                if (r6 == 0) goto L_0x0350
                int r1 = r6.length()
                if (r1 != 0) goto L_0x034e
                goto L_0x0350
            L_0x034e:
                r1 = 0
                goto L_0x0351
            L_0x0350:
                r1 = 1
            L_0x0351:
                if (r1 != 0) goto L_0x035d
                android.text.SpannableStringBuilder r1 = r2.append(r6)
                java.lang.String r2 = "str.append(body)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                goto L_0x0371
            L_0x035d:
                int r1 = r2.length()
                int r1 = r1 - r10
                int r5 = r2.length()
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8
                android.text.SpannableStringBuilder r1 = r2.replace(r1, r5, r8)
                java.lang.String r2 = "str.replace(str.length - 2, str.length, \" \")"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            L_0x0371:
                r2 = r7
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                if (r2 == 0) goto L_0x037f
                int r2 = r2.length()
                if (r2 != 0) goto L_0x037d
                goto L_0x037f
            L_0x037d:
                r2 = 0
                goto L_0x0380
            L_0x037f:
                r2 = 1
            L_0x0380:
                if (r2 != 0) goto L_0x03d6
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r12)
                r5 = 1
                r2 = r2 ^ r5
                if (r2 == 0) goto L_0x03d6
                android.text.SpannableStringBuilder r2 = new android.text.SpannableStringBuilder
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                r6 = 32
                r5.append(r6)
                r5.append(r7)
                java.lang.String r5 = r5.toString()
                java.lang.CharSequence r5 = (java.lang.CharSequence) r5
                r2.<init>(r5)
                android.text.style.StyleSpan r5 = new android.text.style.StyleSpan
                r5.<init>(r10)
                int r6 = r7.length()
                r8 = 33
                r10 = 0
                r2.setSpan(r5, r10, r6, r8)
                android.text.style.ForegroundColorSpan r5 = new android.text.style.ForegroundColorSpan
                android.content.Context r6 = r21.getContext()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r9)
                android.content.res.Resources r6 = r6.getResources()
                r15 = 2131099983(0x7f06014f, float:1.7812335E38)
                int r6 = r6.getColor(r15)
                r5.<init>(r6)
                int r6 = r7.length()
                r7 = 1
                int r6 = r6 + r7
                r2.setSpan(r5, r10, r6, r8)
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.append(r2)
            L_0x03d6:
                int r2 = com.medscape.android.R.id.content
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r14)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r2.setText(r1)
                int r1 = com.medscape.android.R.id.references
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r11)
                android.text.Spanned r2 = android.text.Html.fromHtml(r18)
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.setText(r2)
                int r1 = com.medscape.android.R.id.next_arrow
                android.view.View r1 = r0.findViewById(r1)
                android.widget.ImageView r1 = (android.widget.ImageView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
                r2 = 8
                r1.setVisibility(r2)
            L_0x040a:
                if (r17 == 0) goto L_0x0415
                java.lang.String r1 = "Additionallink1text"
                r4 = r17
                java.lang.CharSequence r1 = r4.getText(r1)
                goto L_0x0418
            L_0x0415:
                r4 = r17
                r1 = 0
            L_0x0418:
                java.lang.String r16 = java.lang.String.valueOf(r1)
                if (r4 == 0) goto L_0x0425
                java.lang.String r1 = "AdditionalLink1ClickURL"
                java.lang.CharSequence r1 = r4.getText(r1)
                goto L_0x0426
            L_0x0425:
                r1 = 0
            L_0x0426:
                java.lang.String r1 = java.lang.String.valueOf(r1)
                if (r4 == 0) goto L_0x0433
                java.lang.String r2 = "Additionallink2text"
                java.lang.CharSequence r2 = r4.getText(r2)
                goto L_0x0434
            L_0x0433:
                r2 = 0
            L_0x0434:
                java.lang.String r2 = java.lang.String.valueOf(r2)
                if (r4 == 0) goto L_0x0441
                java.lang.String r5 = "AdditionalLink2ClickURL"
                java.lang.CharSequence r5 = r4.getText(r5)
                goto L_0x0442
            L_0x0441:
                r5 = 0
            L_0x0442:
                java.lang.String r5 = java.lang.String.valueOf(r5)
                goto L_0x0509
            L_0x0448:
                java.lang.NullPointerException r0 = new java.lang.NullPointerException
                java.lang.String r1 = "null cannot be cast to non-null type kotlin.CharSequence"
                r0.<init>(r1)
                throw r0
            L_0x0450:
                r14 = r15
                r4 = r17
                int r1 = com.medscape.android.R.id.text_ad
                android.view.View r1 = r0.findViewById(r1)
                android.widget.LinearLayout r1 = (android.widget.LinearLayout) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                r6 = 0
                r1.setVisibility(r6)
                int r1 = com.medscape.android.R.id.graphic_driver_ad_banner
                android.view.View r1 = r0.findViewById(r1)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r13)
                r6 = 8
                r1.setVisibility(r6)
                int r1 = com.medscape.android.R.id.graphic_driver_ad_inline
                android.view.View r1 = r0.findViewById(r1)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                r1.setVisibility(r6)
                r1 = r5
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                if (r1 == 0) goto L_0x048a
                int r2 = r1.length()
                if (r2 != 0) goto L_0x0488
                goto L_0x048a
            L_0x0488:
                r2 = 0
                goto L_0x048b
            L_0x048a:
                r2 = 1
            L_0x048b:
                if (r2 != 0) goto L_0x04b3
                boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r12)
                r5 = 1
                r2 = r2 ^ r5
                if (r2 == 0) goto L_0x04b3
                int r2 = com.medscape.android.R.id.label
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r8)
                r2.setText(r1)
                int r1 = com.medscape.android.R.id.label
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
                r2 = 0
                r1.setVisibility(r2)
                goto L_0x04c3
            L_0x04b3:
                int r1 = com.medscape.android.R.id.label
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
                r2 = 8
                r1.setVisibility(r2)
            L_0x04c3:
                int r1 = com.medscape.android.R.id.content
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r14)
                r1.setText(r11)
                if (r4 == 0) goto L_0x04da
                java.lang.String r1 = "AdditionalLinksText1"
                java.lang.CharSequence r1 = r4.getText(r1)
                goto L_0x04db
            L_0x04da:
                r1 = 0
            L_0x04db:
                java.lang.String r16 = java.lang.String.valueOf(r1)
                if (r4 == 0) goto L_0x04e8
                java.lang.String r1 = "AdditionalLinksURL1"
                java.lang.CharSequence r1 = r4.getText(r1)
                goto L_0x04e9
            L_0x04e8:
                r1 = 0
            L_0x04e9:
                java.lang.String r1 = java.lang.String.valueOf(r1)
                if (r4 == 0) goto L_0x04f6
                java.lang.String r2 = "AdditionalLinksText2"
                java.lang.CharSequence r2 = r4.getText(r2)
                goto L_0x04f7
            L_0x04f6:
                r2 = 0
            L_0x04f7:
                java.lang.String r2 = java.lang.String.valueOf(r2)
                if (r4 == 0) goto L_0x0504
                java.lang.String r5 = "AdditionalLinksURL2"
                java.lang.CharSequence r5 = r4.getText(r5)
                goto L_0x0505
            L_0x0504:
                r5 = 0
            L_0x0505:
                java.lang.String r5 = java.lang.String.valueOf(r5)
            L_0x0509:
                r6 = r5
                r5 = r2
                r2 = r1
                r1 = r16
            L_0x050e:
                r7 = r1
                java.lang.CharSequence r7 = (java.lang.CharSequence) r7
                int r7 = r7.length()
                if (r7 <= 0) goto L_0x0519
                r7 = 1
                goto L_0x051a
            L_0x0519:
                r7 = 0
            L_0x051a:
                java.lang.String r8 = "nativeLayout.additional_link1_text"
                if (r7 == 0) goto L_0x0560
                r7 = 1
                boolean r10 = kotlin.text.StringsKt.equals(r1, r12, r7)
                if (r10 != 0) goto L_0x0560
                int r7 = com.medscape.android.R.id.additional_link1_text
                android.view.View r7 = r0.findViewById(r7)
                android.widget.TextView r7 = (android.widget.TextView) r7
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
                r10 = 0
                r7.setVisibility(r10)
                int r7 = com.medscape.android.R.id.additional_link1_text
                android.view.View r7 = r0.findViewById(r7)
                android.widget.TextView r7 = (android.widget.TextView) r7
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
                android.text.method.MovementMethod r10 = android.text.method.LinkMovementMethod.getInstance()
                r7.setMovementMethod(r10)
                r7 = r20
                com.medscape.android.ads.ADBindingHelper$Companion r7 = (com.medscape.android.ads.ADBindingHelper.Companion) r7
                android.content.Context r10 = r21.getContext()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r9)
                int r11 = com.medscape.android.R.id.additional_link1_text
                android.view.View r11 = r0.findViewById(r11)
                android.widget.TextView r11 = (android.widget.TextView) r11
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r8)
                r7.setClickableSpanForLinks(r1, r2, r10, r11)
                goto L_0x0570
            L_0x0560:
                int r1 = com.medscape.android.R.id.additional_link1_text
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r8)
                r2 = 8
                r1.setVisibility(r2)
            L_0x0570:
                r1 = r5
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                int r1 = r1.length()
                if (r1 <= 0) goto L_0x057b
                r1 = 1
                goto L_0x057c
            L_0x057b:
                r1 = 0
            L_0x057c:
                java.lang.String r2 = "nativeLayout.additional_link2_text"
                if (r1 == 0) goto L_0x05c2
                r1 = 1
                boolean r7 = kotlin.text.StringsKt.equals(r5, r12, r1)
                if (r7 != 0) goto L_0x05c2
                int r1 = com.medscape.android.R.id.additional_link2_text
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                r7 = 0
                r1.setVisibility(r7)
                int r1 = com.medscape.android.R.id.additional_link2_text
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                android.text.method.MovementMethod r7 = android.text.method.LinkMovementMethod.getInstance()
                r1.setMovementMethod(r7)
                r1 = r20
                com.medscape.android.ads.ADBindingHelper$Companion r1 = (com.medscape.android.ads.ADBindingHelper.Companion) r1
                android.content.Context r7 = r21.getContext()
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r9)
                int r8 = com.medscape.android.R.id.additional_link2_text
                android.view.View r8 = r0.findViewById(r8)
                android.widget.TextView r8 = (android.widget.TextView) r8
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r2)
                r1.setClickableSpanForLinks(r5, r6, r7, r8)
                goto L_0x05d2
            L_0x05c2:
                int r1 = com.medscape.android.R.id.additional_link2_text
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                r2 = 8
                r1.setVisibility(r2)
            L_0x05d2:
                if (r4 == 0) goto L_0x05db
                java.lang.String r1 = "JobCode"
                java.lang.CharSequence r2 = r4.getText(r1)
                goto L_0x05dc
            L_0x05db:
                r2 = 0
            L_0x05dc:
                java.lang.String r1 = java.lang.String.valueOf(r2)
                r2 = r1
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                int r5 = r2.length()
                if (r5 <= 0) goto L_0x05eb
                r5 = 1
                goto L_0x05ec
            L_0x05eb:
                r5 = 0
            L_0x05ec:
                java.lang.String r6 = "nativeLayout.job_code"
                java.lang.String r7 = "nativeLayout.graphic_job_code"
                if (r5 == 0) goto L_0x0676
                r5 = 1
                boolean r1 = kotlin.text.StringsKt.equals(r1, r12, r5)
                if (r1 != 0) goto L_0x0676
                int r1 = com.medscape.android.R.id.text_ad
                android.view.View r1 = r0.findViewById(r1)
                android.widget.LinearLayout r1 = (android.widget.LinearLayout) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                int r1 = r1.getVisibility()
                if (r1 != 0) goto L_0x0629
                int r1 = com.medscape.android.R.id.job_code
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
                r3 = 0
                r1.setVisibility(r3)
                int r1 = com.medscape.android.R.id.job_code
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
                r1.setText(r2)
                goto L_0x06a8
            L_0x0629:
                int r1 = com.medscape.android.R.id.graphic_driver_ad_banner
                android.view.View r1 = r0.findViewById(r1)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r13)
                int r1 = r1.getVisibility()
                if (r1 != 0) goto L_0x0656
                int r1 = com.medscape.android.R.id.graphic_job_code
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r7)
                r3 = 0
                r1.setVisibility(r3)
                int r1 = com.medscape.android.R.id.graphic_job_code
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r7)
                r1.setText(r2)
                goto L_0x06a8
            L_0x0656:
                int r1 = com.medscape.android.R.id.graphic_job_code_inline
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                java.lang.String r3 = "nativeLayout.graphic_job_code_inline"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                r5 = 0
                r1.setVisibility(r5)
                int r1 = com.medscape.android.R.id.graphic_job_code_inline
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                r1.setText(r2)
                goto L_0x06a8
            L_0x0676:
                int r1 = com.medscape.android.R.id.text_ad
                android.view.View r1 = r0.findViewById(r1)
                android.widget.LinearLayout r1 = (android.widget.LinearLayout) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
                int r1 = r1.getVisibility()
                if (r1 != 0) goto L_0x0698
                int r1 = com.medscape.android.R.id.job_code
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
                r2 = 8
                r1.setVisibility(r2)
                goto L_0x06a8
            L_0x0698:
                r2 = 8
                int r1 = com.medscape.android.R.id.graphic_job_code
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r7)
                r1.setVisibility(r2)
            L_0x06a8:
                if (r4 == 0) goto L_0x06af
                r4.recordImpression()
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
            L_0x06af:
                int r1 = com.medscape.android.R.id.native_ad_layout
                android.view.View r0 = r0.findViewById(r1)
                android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
                com.medscape.android.ads.ADBindingHelper$Companion$bindNativeAD$2 r1 = new com.medscape.android.ads.ADBindingHelper$Companion$bindNativeAD$2
                r1.<init>(r4)
                android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
                r0.setOnClickListener(r1)
                goto L_0x06d4
            L_0x06c2:
                int r1 = com.medscape.android.R.id.native_ad_layout
                android.view.View r0 = r0.findViewById(r1)
                android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
                java.lang.String r1 = "nativeLayout.native_ad_layout"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                r1 = 8
                r0.setVisibility(r1)
            L_0x06d4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.ads.ADBindingHelper.Companion.bindNativeAD(android.view.View, com.medscape.android.ads.NativeDFPAD):void");
        }

        private final void removeAdView(LinearLayout linearLayout, int i) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt != null && (childAt instanceof PublisherAdView)) {
                linearLayout.removeViewAt(i);
            }
        }

        public final void setClickableSpanForLinks(String str, String str2, Context context, TextView textView) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(textView, "textView");
            CharSequence charSequence = str;
            if (!(charSequence == null || charSequence.length() == 0)) {
                SpannableString spannableString = new SpannableString(charSequence);
                spannableString.setSpan(new ADBindingHelper$Companion$setClickableSpanForLinks$clickableSpan$1(str2, context), 0, spannableString.length(), 33);
                textView.setText(spannableString);
                textView.setVisibility(0);
                return;
            }
            textView.setVisibility(8);
        }

        private final void applyFullScreenADWidth(PublisherAdView publisherAdView) {
            if (publisherAdView != null && Intrinsics.areEqual((Object) publisherAdView.getAdSize(), (Object) DFPAdAction.ADSIZE_3x1)) {
                publisherAdView.setAdSizes(new AdSize(Util.getDisplayWidthInDp(publisherAdView.getContext()), 400));
            }
        }
    }
}
