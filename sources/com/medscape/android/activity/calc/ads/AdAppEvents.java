package com.medscape.android.activity.calc.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.facebook.internal.ServerProtocol;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.CP.FireCPEventWithAdsSegvarAsyncTask;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.ads.AdWebViewAcitivity;
import com.medscape.android.ads.DFPAd;
import com.medscape.android.ads.DFPAdListener;
import com.medscape.android.ads.GetAdContentTask;
import com.medscape.android.slideshow.SlideshowUtil;
import com.medscape.android.slideshow.SlideshowViewer;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.wbmd.ads.IAdListener;
import com.wbmd.ads.IAppEventListener;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010\u0014J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\n2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002J\u001a\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\nH\u0002J\u0010\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\nH\u0002J\"\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\u001c\u0010 \u001a\u00020\u00162\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\u001a\u0010!\u001a\u00020\u00162\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010\nH\u0002R\u000e\u0010\t\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/medscape/android/activity/calc/ads/AdAppEvents;", "Lcom/wbmd/ads/IAppEventListener;", "context", "Landroid/content/Context;", "isInlineAd", "", "adLayout", "Landroid/view/View;", "(Landroid/content/Context;ZLandroid/view/View;)V", "TAG", "", "dfpAd", "Lcom/medscape/android/ads/DFPAd;", "getDfpAd", "()Lcom/medscape/android/ads/DFPAd;", "setDfpAd", "(Lcom/medscape/android/ads/DFPAd;)V", "getTextColorBasedOnLuminosity", "", "backgroundColorString", "(Ljava/lang/String;)Ljava/lang/Integer;", "handleDFPAd", "", "info", "handleOpenFullscreenAd", "adLoadListener", "Lcom/wbmd/ads/IAdListener;", "handleOpenSlideshow", "handleSendCPEvent", "onAppEvent", "name", "resetDfpAd", "showClassicFullScreenAd", "showClassicFullScreenViewer", "adContent", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: AdAppEvents.kt */
public final class AdAppEvents implements IAppEventListener {
    private final String TAG;
    private final View adLayout;
    private final Context context;
    private DFPAd dfpAd;
    private final boolean isInlineAd;

    public AdAppEvents(Context context2, boolean z, View view) {
        this.context = context2;
        this.isInlineAd = z;
        this.adLayout = view;
        this.TAG = "AdAppEvents";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AdAppEvents(Context context2, boolean z, View view, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i & 2) != 0 ? false : z, (i & 4) != 0 ? null : view);
    }

    public final DFPAd getDfpAd() {
        return this.dfpAd;
    }

    public final void setDfpAd(DFPAd dFPAd) {
        this.dfpAd = dFPAd;
    }

    /* JADX WARNING: type inference failed for: r11v8, types: [androidx.lifecycle.ViewModel] */
    /* JADX WARNING: type inference failed for: r11v11, types: [androidx.lifecycle.ViewModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAppEvent(java.lang.String r11, java.lang.String r12, com.wbmd.ads.IAdListener r13) {
        /*
            r10 = this;
            java.lang.String r0 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "info"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "name:"
            r0.append(r1)
            r0.append(r11)
            java.lang.String r1 = "=info:"
            r0.append(r1)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "App Event"
            android.util.Log.d(r1, r0)
            java.lang.String r0 = "adInfo"
            r1 = 1
            boolean r0 = kotlin.text.StringsKt.equals(r0, r11, r1)
            if (r0 == 0) goto L_0x0036
            android.view.View r0 = r10.adLayout
            r10.handleDFPAd(r12, r0)
        L_0x0036:
            java.lang.String r0 = "openFullscreenAd"
            boolean r0 = kotlin.text.StringsKt.equals(r0, r11, r1)
            if (r0 == 0) goto L_0x0041
            r10.handleOpenFullscreenAd(r12, r13)
        L_0x0041:
            java.lang.String r13 = "openSlideshow"
            boolean r13 = kotlin.text.StringsKt.equals(r13, r11, r1)
            if (r13 == 0) goto L_0x004c
            r10.handleOpenSlideshow(r12)
        L_0x004c:
            java.lang.String r13 = "sendCPEvent"
            boolean r13 = kotlin.text.StringsKt.equals(r13, r11, r1)
            if (r13 == 0) goto L_0x0057
            r10.handleSendCPEvent(r12)
        L_0x0057:
            java.lang.String r13 = "close"
            boolean r13 = kotlin.text.StringsKt.equals(r11, r13, r1)
            if (r13 == 0) goto L_0x006c
            android.content.Context r13 = r10.context
            if (r13 == 0) goto L_0x006c
            boolean r0 = r13 instanceof com.medscape.android.drugs.DrugMonographMainActivity
            if (r0 == 0) goto L_0x006c
            com.medscape.android.drugs.DrugMonographMainActivity r13 = (com.medscape.android.drugs.DrugMonographMainActivity) r13
            r13.dismissAdDialog()
        L_0x006c:
            java.lang.String r13 = "openDrug"
            boolean r13 = kotlin.text.StringsKt.equals(r11, r13, r1)
            if (r13 == 0) goto L_0x00c8
            com.medscape.android.util.RedirectHandler r13 = new com.medscape.android.util.RedirectHandler
            r0 = 0
            r13.<init>(r0)
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x00c4 }
            r2.<init>(r12)     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r3 = "contentURL"
            java.lang.String r2 = r2.getString(r3)     // Catch:{ Exception -> 0x00c4 }
            boolean r3 = com.medscape.android.util.StringUtil.isNotEmpty(r2)     // Catch:{ Exception -> 0x00c4 }
            if (r3 == 0) goto L_0x00c8
            android.content.Context r3 = r10.context     // Catch:{ Exception -> 0x00c4 }
            if (r3 == 0) goto L_0x00be
            android.content.Context r3 = r10.context     // Catch:{ Exception -> 0x00c4 }
            boolean r3 = r3 instanceof com.medscape.android.drugs.DrugMonographMainActivity     // Catch:{ Exception -> 0x00c4 }
            if (r3 == 0) goto L_0x00be
            android.content.Context r3 = r10.context     // Catch:{ Exception -> 0x00c4 }
            com.medscape.android.drugs.DrugMonographMainActivity r3 = (com.medscape.android.drugs.DrugMonographMainActivity) r3     // Catch:{ Exception -> 0x00c4 }
            int r3 = r3.mContentId     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r4 = "link"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)     // Catch:{ Exception -> 0x00c4 }
            java.lang.String r5 = "drug://"
            java.lang.String r6 = ""
            r7 = 0
            r8 = 4
            r9 = 0
            r4 = r2
            java.lang.String r4 = kotlin.text.StringsKt.replace$default((java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (boolean) r7, (int) r8, (java.lang.Object) r9)     // Catch:{ Exception -> 0x00c4 }
            boolean r3 = kotlin.text.StringsKt.equals(r3, r4, r1)     // Catch:{ Exception -> 0x00c4 }
            if (r3 == 0) goto L_0x00be
            android.content.Context r13 = r10.context     // Catch:{ Exception -> 0x00c4 }
            com.medscape.android.drugs.DrugMonographMainActivity r13 = (com.medscape.android.drugs.DrugMonographMainActivity) r13     // Catch:{ Exception -> 0x00c4 }
            r13.dismissAdDialog()     // Catch:{ Exception -> 0x00c4 }
            return
        L_0x00be:
            android.content.Context r3 = r10.context     // Catch:{ Exception -> 0x00c4 }
            r13.handleRedirect(r3, r2, r0)     // Catch:{ Exception -> 0x00c4 }
            goto L_0x00c8
        L_0x00c4:
            r13 = move-exception
            r13.printStackTrace()
        L_0x00c8:
            java.lang.String r13 = "sendBIEvent"
            boolean r13 = kotlin.text.StringsKt.equals(r11, r13, r1)
            if (r13 == 0) goto L_0x011e
            com.medscape.android.ads.adparsers.OmnitureDataParser$Companion r13 = com.medscape.android.ads.adparsers.OmnitureDataParser.Companion
            com.medscape.android.ads.adparsers.OmnitureDataModel r13 = r13.parseOmnitureData(r12)
            if (r13 == 0) goto L_0x011e
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r7 = r0
            java.util.Map r7 = (java.util.Map) r7
            java.lang.String r0 = r13.getExiturl()
            java.lang.String r2 = "wapp.exiturl"
            r7.put(r2, r0)
            android.content.Context r0 = r10.context
            if (r0 == 0) goto L_0x010b
            boolean r2 = r0 instanceof com.medscape.android.base.BaseActivity
            if (r2 == 0) goto L_0x010b
            com.medscape.android.base.BaseActivity r0 = (com.medscape.android.base.BaseActivity) r0
            java.lang.String r0 = r0.mPvid
            boolean r0 = com.medscape.android.util.StringUtil.isNotEmpty(r0)
            if (r0 == 0) goto L_0x010b
            android.content.Context r0 = r10.context
            com.medscape.android.base.BaseActivity r0 = (com.medscape.android.base.BaseActivity) r0
            java.lang.String r0 = r0.mPvid
            java.lang.String r2 = "context.mPvid"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            java.lang.String r2 = "wapp.pvid"
            r7.put(r2, r0)
        L_0x010b:
            com.medscape.android.BI.omniture.OmnitureManager r2 = com.medscape.android.BI.omniture.OmnitureManager.get()
            android.content.Context r3 = r10.context
            java.lang.String r5 = r13.getMmodule()
            java.lang.String r6 = r13.getMlink()
            java.lang.String r4 = "reference and tools"
            r2.trackModuleAbsolute(r3, r4, r5, r6, r7)
        L_0x011e:
            java.lang.String r13 = "consultDriver"
            boolean r11 = kotlin.text.StringsKt.equals(r11, r13, r1)
            if (r11 == 0) goto L_0x0174
            android.content.Context r11 = r10.context
            if (r11 == 0) goto L_0x0174
            r13 = 0
            com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel r13 = (com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel) r13
            boolean r0 = r11 instanceof com.medscape.android.consult.activity.ConsultTimelineActivity
            if (r0 == 0) goto L_0x0141
            androidx.fragment.app.FragmentActivity r11 = (androidx.fragment.app.FragmentActivity) r11
            androidx.lifecycle.ViewModelProvider r11 = androidx.lifecycle.ViewModelProviders.of((androidx.fragment.app.FragmentActivity) r11)
            java.lang.Class<com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel> r13 = com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel.class
            androidx.lifecycle.ViewModel r11 = r11.get(r13)
            r13 = r11
            com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel r13 = (com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel) r13
            goto L_0x0154
        L_0x0141:
            boolean r0 = r11 instanceof com.medscape.android.consult.activity.ConsultSearchActivity
            if (r0 == 0) goto L_0x0154
            androidx.fragment.app.FragmentActivity r11 = (androidx.fragment.app.FragmentActivity) r11
            androidx.lifecycle.ViewModelProvider r11 = androidx.lifecycle.ViewModelProviders.of((androidx.fragment.app.FragmentActivity) r11)
            java.lang.Class<com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel> r13 = com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel.class
            androidx.lifecycle.ViewModel r11 = r11.get(r13)
            r13 = r11
            com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel r13 = (com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel) r13
        L_0x0154:
            if (r13 == 0) goto L_0x0174
            org.json.JSONObject r11 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0170 }
            r11.<init>(r12)     // Catch:{ JSONException -> 0x0170 }
            java.lang.String r12 = "postId"
            java.lang.String r11 = r11.optString(r12)     // Catch:{ JSONException -> 0x0170 }
            boolean r12 = com.medscape.android.util.StringUtil.isNotEmpty(r11)     // Catch:{ JSONException -> 0x0170 }
            if (r12 == 0) goto L_0x0174
            java.lang.String r12 = "postID"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r12)     // Catch:{ JSONException -> 0x0170 }
            r13.setValueForSponsoredPostID(r11)     // Catch:{ JSONException -> 0x0170 }
            goto L_0x0174
        L_0x0170:
            r11 = move-exception
            r11.printStackTrace()
        L_0x0174:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.calc.ads.AdAppEvents.onAppEvent(java.lang.String, java.lang.String, com.wbmd.ads.IAdListener):void");
    }

    private final void handleDFPAd(String str, View view) {
        DFPAd dFPAd = this.dfpAd;
        if (dFPAd != null) {
            dFPAd.setInfo(str);
        }
        if (view != null) {
            View findViewById = view.findViewById(R.id.adLabel);
            Intrinsics.checkNotNullExpressionValue(findViewById, "adLayout.findViewById(R.id.adLabel)");
            TextView textView = (TextView) findViewById;
            DFPAd dFPAd2 = this.dfpAd;
            if ((dFPAd2 == null || !dFPAd2.isFullScreenAd()) && !this.isInlineAd) {
                Settings.singleton(this.context).saveSetting(DFPAdListener.SHOW_FULLSCREENAD, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                DFPAd dFPAd3 = this.dfpAd;
                String str2 = null;
                textView.setText(dFPAd3 != null ? dFPAd3.getBannerLabel() : null);
                DFPAd dFPAd4 = this.dfpAd;
                textView.setBackgroundColor(Color.parseColor(dFPAd4 != null ? dFPAd4.getLabelColor() : null));
                DFPAd dFPAd5 = this.dfpAd;
                if (dFPAd5 != null) {
                    str2 = dFPAd5.getLabelColor();
                }
                Integer textColorBasedOnLuminosity = getTextColorBasedOnLuminosity(str2);
                if (textColorBasedOnLuminosity != null) {
                    textView.setTextColor(textColorBasedOnLuminosity.intValue());
                }
                textView.setVisibility(0);
                view.setVisibility(0);
            } else if (this.isInlineAd) {
                view.setVisibility(0);
            } else {
                Settings.singleton(this.context).saveSetting(DFPAdListener.SHOW_FULLSCREENAD, "false");
                view.setVisibility(8);
                textView.setVisibility(8);
            }
        }
    }

    private final void handleOpenFullscreenAd(String str, IAdListener iAdListener) {
        DFPAd dFPAd = this.dfpAd;
        if (dFPAd != null) {
            dFPAd.setFullScreenAdInfo(str);
        }
        DFPAd dFPAd2 = this.dfpAd;
        if (dFPAd2 != null && dFPAd2.isFullScreenAd()) {
            Settings.singleton(this.context).saveSetting(DFPAdListener.SHOW_FULLSCREENAD, "false");
        }
        showClassicFullScreenAd(this.dfpAd, iAdListener);
        resetDfpAd();
    }

    private final void handleOpenSlideshow(String str) {
        DFPAd dFPAd = this.dfpAd;
        if (dFPAd != null) {
            dFPAd.setSlideshowInfo(str);
        }
        Intent intent = new Intent(this.context, SlideshowViewer.class);
        DFPAd dFPAd2 = this.dfpAd;
        String str2 = null;
        String url = dFPAd2 != null ? dFPAd2.getUrl() : null;
        DFPAd dFPAd3 = this.dfpAd;
        Intent putExtra = intent.putExtra("slideshowUrl", Intrinsics.stringPlus(url, SlideshowUtil.toAppend(dFPAd3 != null ? dFPAd3.getUrl() : null)));
        DFPAd dFPAd4 = this.dfpAd;
        Intent putExtra2 = putExtra.putExtra("activityId", dFPAd4 != null ? dFPAd4.getActivityId() : null);
        DFPAd dFPAd5 = this.dfpAd;
        Intent putExtra3 = putExtra2.putExtra("tacticId", dFPAd5 != null ? dFPAd5.getTcid() : null);
        DFPAd dFPAd6 = this.dfpAd;
        Intent putExtra4 = putExtra3.putExtra("parId", dFPAd6 != null ? dFPAd6.getParId() : null);
        DFPAd dFPAd7 = this.dfpAd;
        Intent putExtra5 = putExtra4.putExtra("isEditorial", dFPAd7 != null ? Boolean.valueOf(dFPAd7.isEditorial()) : null);
        DFPAd dFPAd8 = this.dfpAd;
        Intent putExtra6 = putExtra5.putExtra("orientationLock", dFPAd8 != null ? dFPAd8.getOrientationLock() : null);
        DFPAd dFPAd9 = this.dfpAd;
        if (dFPAd9 != null) {
            str2 = dFPAd9.getSlideType();
        }
        Intent putExtra7 = putExtra6.putExtra("slideType", str2);
        Intrinsics.checkNotNullExpressionValue(putExtra7, "Intent(context, Slidesho…eType\", dfpAd?.slideType)");
        Settings.singleton(this.context).saveSetting(DFPAdListener.SHOW_FULLSCREENAD, "false");
        Context context2 = this.context;
        Intrinsics.checkNotNull(context2);
        context2.startActivity(putExtra7);
    }

    private final void handleSendCPEvent(String str) {
        if (StringUtil.isNotEmpty(str)) {
            try {
                String str2 = OmnitureManager.get().getmCurrentPageName();
                Context context2 = this.context;
                String str3 = "" + null;
                CharSequence charSequence = str;
                int length = charSequence.length() - 1;
                int i = 0;
                boolean z = false;
                while (true) {
                    if (i > length) {
                        break;
                    }
                    boolean z2 = Intrinsics.compare((int) charSequence.charAt(!z ? i : length), 32) <= 0;
                    if (!z) {
                        if (!z2) {
                            z = true;
                        } else {
                            i++;
                        }
                    } else if (!z2) {
                        break;
                    } else {
                        length--;
                    }
                }
                new FireCPEventWithAdsSegvarAsyncTask(context2, (String) null, (String) null, str3, str2, charSequence.subSequence(i, length + 1).toString()).execute(new HashMap[0]);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private final Integer getTextColorBasedOnLuminosity(String str) {
        int i;
        if (str == null) {
            return null;
        }
        boolean z = false;
        if (StringsKt.startsWith$default(str, "#", false, 2, (Object) null)) {
            if (str != null) {
                str = str.substring(1);
                Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.String).substring(startIndex)");
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
        }
        int parseLong = (int) Long.parseLong(str, CharsKt.checkRadix(16));
        int i2 = (parseLong >> 16) & 255;
        int i3 = (parseLong >> 8) & 255;
        int i4 = (parseLong >> 0) & 255;
        if (i2 == i3 && i3 == i4) {
            z = true;
        }
        if (((((double) i2) / 255.0d) * 0.21d) + ((((double) i3) / 255.0d) * 0.72d) + ((((double) i4) / 255.0d) * 0.07d) < 0.5d) {
            i = -1;
        } else {
            i = z ? -12303292 : ViewCompat.MEASURED_STATE_MASK;
        }
        return Integer.valueOf(i);
    }

    private final void showClassicFullScreenAd(DFPAd dFPAd, IAdListener iAdListener) {
        if ((dFPAd != null ? dFPAd.getUrl() : null) == null) {
            return;
        }
        if (!dFPAd.isImmediate()) {
            GetAdContentTask.getAdInBackground(this.context, dFPAd.getUrl(), new AdAppEvents$showClassicFullScreenAd$1(this, iAdListener, dFPAd));
        } else {
            showClassicFullScreenViewer(dFPAd, (String) null);
        }
    }

    /* access modifiers changed from: private */
    public final void showClassicFullScreenViewer(DFPAd dFPAd, String str) {
        Intent intent = new Intent(this.context, AdWebViewAcitivity.class);
        intent.setData(Uri.parse(dFPAd.getUrl()));
        intent.putExtra("uri", Uri.parse(dFPAd.getUrl()).toString());
        intent.putExtra("navBar", !dFPAd.isNavigationBarHidden());
        intent.putExtra("toolBar", !dFPAd.isToolbarHidden());
        intent.putExtra("headerText", dFPAd.getNavigationBarTitle());
        intent.putExtra("headerColor", dFPAd.getNavigationBarColor());
        intent.putExtra("buttonText", dFPAd.getCloseButtonText());
        intent.putExtra("isImmediate", dFPAd.isImmediate());
        intent.putExtra("isFullScreenAd", dFPAd.isFullScreenAd());
        intent.putExtra("announceURL", dFPAd.getUrl());
        intent.putExtra("adID", dFPAd.getId());
        intent.putExtra("adContent", str);
        intent.putExtra("sendBI", dFPAd.isSendBI());
        Util.isFullScreenAd = true;
        intent.addFlags(65536);
        if (!dFPAd.isImmediate()) {
            Context context2 = this.context;
            if (context2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.Activity");
            } else if (((Activity) context2).isFinishing()) {
                LogUtil.d(this.TAG, "Don't dispaly full screen app  class = %s", ((Activity) this.context).getClass().getName());
                return;
            }
        }
        if (!dFPAd.isImmediate() && str == null) {
            LogUtil.d(this.TAG, "Don't dispaly full screen app  No preloaded Ad available ", new Object[0]);
        } else if (dFPAd.isImmediate() || str == null) {
            Context context3 = this.context;
            Intrinsics.checkNotNull(context3);
            context3.startActivity(intent);
            Context context4 = this.context;
            if (context4 != null) {
                ((Activity) context4).overridePendingTransition(R.anim.slide_in_up, R.anim.ad_current_screen_anim);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.app.Activity");
        } else {
            Context context5 = this.context;
            Intrinsics.checkNotNull(context5);
            context5.startActivity(intent);
            Context context6 = this.context;
            if (context6 != null) {
                ((Activity) context6).overridePendingTransition(R.anim.slide_in_up, R.anim.ad_current_screen_anim);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.app.Activity");
        }
    }

    private final void resetDfpAd() {
        this.dfpAd = new DFPAd(this.context);
    }
}
