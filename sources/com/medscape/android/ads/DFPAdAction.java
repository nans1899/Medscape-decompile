package com.medscape.android.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.ads.AdRequest;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.Correlator;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.medscape.android.BI.BIManager;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.CP.FireCPEventWithAdsSegvarAsyncTask;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.ads.proclivity.ProclivityDataModel;
import com.medscape.android.ads.proclivity.ProclivityUtils;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.activity.ConsultTimelineActivity;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.slideshow.SlideshowUtil;
import com.medscape.android.slideshow.SlideshowViewer;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.anko.DimensionsKt;

public class DFPAdAction extends AdListener implements AppEventListener {
    public static final AdSize ADSIZE_10x10 = new AdSize(10, 10);
    public static final AdSize ADSIZE_1x1 = new AdSize(1, 1);
    public static final AdSize ADSIZE_1x3 = new AdSize(1, 3);
    public static final AdSize ADSIZE_2x8 = new AdSize(2, 8);
    public static final AdSize ADSIZE_300x106 = new AdSize(300, 106);
    public static final AdSize ADSIZE_300x121 = new AdSize(300, 121);
    public static final AdSize ADSIZE_300x136 = new AdSize(300, 136);
    public static final AdSize ADSIZE_300x250 = new AdSize(300, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    public static final AdSize ADSIZE_300x400 = new AdSize(300, 400);
    public static final AdSize ADSIZE_300x45 = new AdSize(300, 45);
    public static final AdSize ADSIZE_300x50 = new AdSize(300, 50);
    public static final AdSize ADSIZE_300x500;
    public static final AdSize ADSIZE_300x61 = new AdSize(300, 61);
    public static final AdSize ADSIZE_300x76 = new AdSize(300, 76);
    public static final AdSize ADSIZE_300x91 = new AdSize(300, 91);
    public static final AdSize ADSIZE_320x50 = new AdSize(DimensionsKt.XHDPI, 50);
    public static final AdSize ADSIZE_320x80 = new AdSize(DimensionsKt.XHDPI, 80);
    public static final AdSize ADSIZE_320x95 = new AdSize(DimensionsKt.XHDPI, 95);
    public static final AdSize ADSIZE_335x452 = new AdSize(335, 452);
    public static final AdSize ADSIZE_3x1 = new AdSize(3, 1);
    public static final AdSize ADSIZE_3x3 = new AdSize(3, 3);
    public static String NG_USERID = "NGUserID";
    private static final String TAG = "DFPAdAction";
    public static final AdSize[] advLabelSizes;
    public static long startTime = 0;
    public boolean adDismissed;
    protected View adLayout;
    public PublisherAdView adView;
    protected AdsSegvar adsSegvar;
    /* access modifiers changed from: private */
    public OnAdListener callbackAdListner;
    protected Context context;
    protected DFPAd dfpAd;
    private HashMap<String, String> globalMap;
    private boolean isDisplayFullScreenAd;
    public boolean isForceHideAD;
    public boolean isInlineAd;
    public boolean isSharethrough;
    protected OnAdListener onAdListener;
    protected OnAdListener onPreCacheAdListener;

    private String getErrorReason(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "" : "No fill" : "Network Error" : "Invalid request" : "Internal error";
    }

    static {
        AdSize adSize = new AdSize(300, 500);
        ADSIZE_300x500 = adSize;
        advLabelSizes = new AdSize[]{ADSIZE_320x50, ADSIZE_300x50, ADSIZE_300x250, ADSIZE_300x400, ADSIZE_335x452, adSize};
    }

    public DFPAdAction(Context context2, View view, PublisherAdView publisherAdView) {
        this.globalMap = new HashMap<>();
        this.isDisplayFullScreenAd = false;
        this.isInlineAd = false;
        this.isSharethrough = false;
        this.isForceHideAD = false;
        this.adDismissed = false;
        this.context = context2;
        AdsSegvar instance = AdsSegvar.getInstance();
        this.adsSegvar = instance;
        instance.setGlobalMap(context2);
        this.adLayout = view;
        this.adView = publisherAdView;
        publisherAdView.setAdListener(this);
        this.adView.setAppEventListener(this);
        this.dfpAd = new DFPAd(context2);
        this.onAdListener = new LocalOnAdListener();
    }

    public DFPAdAction(Context context2, View view, PublisherAdView publisherAdView, boolean z) {
        this(context2, view, publisherAdView);
        this.isInlineAd = z;
    }

    public DFPAdAction(Context context2, PublisherAdView publisherAdView, boolean z, OnAdListener onAdListener2) {
        this(context2, (View) null, publisherAdView);
        this.isInlineAd = z;
        this.onPreCacheAdListener = onAdListener2;
    }

    protected DFPAdAction() {
        this.globalMap = new HashMap<>();
        this.isDisplayFullScreenAd = false;
        this.isInlineAd = false;
        this.isSharethrough = false;
        this.isForceHideAD = false;
        this.adDismissed = false;
    }

    public void setOnUpdateListener(OnAdListener onAdListener2) {
        this.callbackAdListner = onAdListener2;
    }

    public void makeADRequestWithoutBidding(HashMap<String, String> hashMap) {
        prepareAdForBidding(hashMap);
        this.adView.loadAd(createAdRequestWithDetails());
    }

    public void prepareAdForBidding(HashMap<String, String> hashMap) {
        updateGlobalmap(hashMap);
        PublisherAdView publisherAdView = this.adView;
        if (publisherAdView != null) {
            publisherAdView.setCorrelator(new Correlator());
            if (!this.isInlineAd) {
                this.adView.setAdSizes(getBottomBannerADsizes());
            }
        }
    }

    public AdSize[] getBottomBannerADsizes() {
        if (Boolean.valueOf(Settings.singleton(this.context).getSetting(DFPAdListener.SHOW_FULLSCREENAD, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)).booleanValue()) {
            return new AdSize[]{AdSize.BANNER, ADSIZE_300x50, ADSIZE_3x3, ADSIZE_1x1, ADSIZE_1x3};
        }
        return new AdSize[]{AdSize.BANNER, ADSIZE_300x50, ADSIZE_3x3, ADSIZE_1x3};
    }

    public void makeADRequestAfterBidding(PublisherAdRequest publisherAdRequest, List<ProclivityDataModel> list) {
        this.adView.loadAd(new PublisherAdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, getCombinedBundle(publisherAdRequest, list, this.adView.getAdSizes())).build());
    }

    /* access modifiers changed from: protected */
    public Bundle getCombinedBundle(PublisherAdRequest publisherAdRequest, List<ProclivityDataModel> list, AdSize[] adSizeArr) {
        Bundle bundle = getBundle(publisherAdRequest);
        Map<String, String> proclivityMap = ProclivityUtils.getProclivityMap(list, adSizeArr);
        if (!list.isEmpty()) {
            for (String next : proclivityMap.keySet()) {
                bundle.putString(next, proclivityMap.get(next));
            }
        }
        return bundle;
    }

    /* access modifiers changed from: protected */
    public Bundle getBundle(PublisherAdRequest publisherAdRequest) {
        Bundle customTargeting = publisherAdRequest.getCustomTargeting();
        if (customTargeting == null) {
            customTargeting = new Bundle();
        }
        customTargeting.putAll(publisherAdRequest.getNetworkExtrasBundle(AdMobAdapter.class));
        return customTargeting.isEmpty() ? getGlobalMapBundle() : customTargeting;
    }

    public Map<String, String> getGlobalMap(Map<String, String> map) {
        new HashMap().clear();
        HashMap<String, String> globalMap2 = AdsSegvar.getInstance().getGlobalMap(this.context);
        if (UserProfileProvider.INSTANCE.getUserProfile() != null) {
            if (UserProfileProvider.INSTANCE.getUserProfile().getTidMap().get(NativeAppInstallAd.ASSET_HEADLINE) != null) {
                globalMap2.put("tar", UserProfileProvider.INSTANCE.getUserProfile().getTidMap().get(NativeAppInstallAd.ASSET_HEADLINE));
            }
            this.adsSegvar.createProfileSpecificDataMap();
        }
        globalMap2.putAll(this.adsSegvar.getProileSpecificDataMap());
        globalMap2.putAll(this.adsSegvar.getPageOverProileSpecificDataMap());
        globalMap2.put(AdsConstants.CN, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        globalMap2.putAll(map);
        String checkAndGetCurrentPvid = checkAndGetCurrentPvid();
        if (checkAndGetCurrentPvid != null) {
            globalMap2.put("pvid", checkAndGetCurrentPvid);
        }
        return globalMap2;
    }

    public void updateGlobalmap(HashMap<String, String> hashMap) {
        this.globalMap.clear();
        this.globalMap.putAll(getGlobalMap(hashMap));
        String checkAndGetCurrentPvid = checkAndGetCurrentPvid();
        if (checkAndGetCurrentPvid != null) {
            this.globalMap.put("pvid", checkAndGetCurrentPvid);
        }
    }

    private PublisherAdRequest.Builder createAdRequestBuilder() {
        String checkAndGetCurrentPvid;
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.globalMap.keySet());
        if (!arrayList.contains("pvid") && (checkAndGetCurrentPvid = checkAndGetCurrentPvid()) != null) {
            this.globalMap.put("pvid", checkAndGetCurrentPvid);
        }
        return new PublisherAdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, getGlobalMapBundle());
    }

    public Bundle getGlobalMapBundle() {
        Bundle bundle = new Bundle();
        for (String next : this.globalMap.keySet()) {
            bundle.putString(next, this.globalMap.get(next));
        }
        return bundle;
    }

    public void onAdOpened() {
        super.onAdOpened();
        startTime = System.currentTimeMillis();
    }

    public void onAdClosed() {
        super.onAdClosed();
        sendFullScreenBI();
    }

    public void sendFullScreenBI() {
        if (this.context != null && this.dfpAd != null) {
            BIManager bIManager = new BIManager();
            bIManager.setFullScreenAD_ELPST(startTime > 0 ? String.valueOf(System.currentTimeMillis() - startTime) : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            bIManager.startBI(this.context, "view", "alert", this.dfpAd.getId(), "fullscreenad");
        }
    }

    private void showClassicFullScreenAd(final DFPAd dFPAd) {
        if (dFPAd.getUrl() == null) {
            return;
        }
        if (!dFPAd.isImmediate()) {
            GetAdContentTask.getAdInBackground(this.context, dFPAd.getUrl(), new GetAdContentListener() {
                public void onContentsDownloaded(String str) {
                    try {
                        if (StringUtil.isNullOrEmpty(str)) {
                            DFPAdAction.this.onAdListener.onAdNotAvilable();
                        } else {
                            DFPAdAction.this.showClassicFullScreenViewer(dFPAd, str);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        DFPAdAction.this.onAdListener.onAdNotAvilable();
                    }
                }
            });
        } else {
            showClassicFullScreenViewer(dFPAd, (String) null);
        }
    }

    /* access modifiers changed from: private */
    public void showClassicFullScreenViewer(DFPAd dFPAd, String str) {
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
        if (!dFPAd.isImmediate() && ((Activity) this.context).isFinishing()) {
            LogUtil.d(TAG, "Don't dispaly full screen app  class = %s", this.context.getClass().getName());
        } else if (!dFPAd.isImmediate() && str == null) {
            LogUtil.d(TAG, "Don't dispaly full screen app  No preloaded Ad available ", new Object[0]);
        } else if (dFPAd.isImmediate() || str == null) {
            this.context.startActivity(intent);
            ((Activity) this.context).overridePendingTransition(R.anim.slide_in_up, R.anim.ad_current_screen_anim);
        } else {
            this.context.startActivity(intent);
            ((Activity) this.context).overridePendingTransition(R.anim.slide_in_up, R.anim.ad_current_screen_anim);
        }
    }

    public void onAdFailedToLoad(int i) {
        Log.d(AdRequest.LOGTAG, "Inside onAdFailedToLoad");
        Log.d(AdRequest.LOGTAG, String.format("onAdFailedToLoad (%s)", new Object[]{getErrorReason(i)}));
        this.onAdListener.onAdNotAvilable();
        OnAdListener onAdListener2 = this.onPreCacheAdListener;
        if (onAdListener2 != null) {
            onAdListener2.onAdNotAvilable();
        }
    }

    public void onAdLoaded() {
        if (this.adView.getAdSize().equals(ADSIZE_1x3)) {
            onAdFailedToLoad(3);
            OnAdListener onAdListener2 = this.onPreCacheAdListener;
            if (onAdListener2 != null) {
                onAdListener2.onAdNotAvilable();
                return;
            }
            return;
        }
        Log.d(AdRequest.LOGTAG, "Inside onAdLoaded");
        OnAdListener onAdListener3 = this.onPreCacheAdListener;
        if (onAdListener3 != null) {
            onAdListener3.onAdAvailable();
        }
        handleDFPAdLayout(this.adView);
    }

    /* access modifiers changed from: protected */
    public void handleDFPAdLayout(PublisherAdView publisherAdView) {
        View view = this.adLayout;
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.adLabel);
            if (textView == null) {
                textView = (TextView) this.adLayout.findViewById(R.id.dfp_adLabel);
            }
            if (!this.dfpAd.isFullScreenAd() && !this.dfpAd.isModified() && !this.isDisplayFullScreenAd && !this.isInlineAd) {
                displayBannerAdLabel(publisherAdView);
            }
            if (this.isInlineAd) {
                View view2 = this.adLayout;
                if (view2 != null) {
                    view2.setVisibility(0);
                }
                if (publisherAdView != null) {
                    publisherAdView.setVisibility(0);
                }
                if (textView != null) {
                    textView.setText(this.context.getResources().getString(R.string.advertisement));
                }
            }
            OnAdListener onAdListener2 = this.onAdListener;
            if (onAdListener2 != null) {
                onAdListener2.onAdAvailable();
            }
            this.isDisplayFullScreenAd = false;
            if (textView != null) {
                setAdLabelVisibility(textView, publisherAdView);
            }
        }
        if (publisherAdView != null && publisherAdView.getAdSize().equals(ADSIZE_3x3)) {
            publisherAdView.setAdSizes(new AdSize(Util.getDisplayWidthInDp(publisherAdView.getContext()), 80));
        }
    }

    /* JADX WARNING: type inference failed for: r8v8, types: [androidx.lifecycle.ViewModel] */
    /* JADX WARNING: type inference failed for: r8v11, types: [androidx.lifecycle.ViewModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAppEvent(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "name:"
            r0.append(r1)
            r0.append(r8)
            java.lang.String r1 = "=info:"
            r0.append(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "App Event"
            android.util.Log.d(r1, r0)
            java.lang.String r0 = "adInfo"
            boolean r0 = r0.equalsIgnoreCase(r8)
            if (r0 == 0) goto L_0x0029
            r7.handleDFPAd(r9)
        L_0x0029:
            java.lang.String r0 = "openFullscreenAd"
            boolean r0 = r0.equalsIgnoreCase(r8)
            if (r0 == 0) goto L_0x0034
            r7.handleOpenFullscreenAd(r9)
        L_0x0034:
            java.lang.String r0 = "openSlideshow"
            boolean r0 = r0.equalsIgnoreCase(r8)
            if (r0 == 0) goto L_0x003f
            r7.handleOpenSlideshow(r9)
        L_0x003f:
            java.lang.String r0 = "sendCPEvent"
            boolean r0 = r0.equalsIgnoreCase(r8)
            if (r0 == 0) goto L_0x004a
            r7.handleSendCPEvent(r9)
        L_0x004a:
            java.lang.String r0 = "close"
            boolean r0 = r8.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x005f
            android.content.Context r0 = r7.context
            if (r0 == 0) goto L_0x005f
            boolean r1 = r0 instanceof com.medscape.android.drugs.DrugMonographMainActivity
            if (r1 == 0) goto L_0x005f
            com.medscape.android.drugs.DrugMonographMainActivity r0 = (com.medscape.android.drugs.DrugMonographMainActivity) r0
            r0.dismissAdDialog()
        L_0x005f:
            java.lang.String r0 = "openDrug"
            boolean r0 = r8.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x00b2
            com.medscape.android.util.RedirectHandler r0 = new com.medscape.android.util.RedirectHandler
            r1 = 0
            r0.<init>(r1)
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ Exception -> 0x00ae }
            r2.<init>(r9)     // Catch:{ Exception -> 0x00ae }
            java.lang.String r3 = "contentURL"
            java.lang.String r2 = r2.getString(r3)     // Catch:{ Exception -> 0x00ae }
            boolean r3 = com.medscape.android.util.StringUtil.isNotEmpty(r2)     // Catch:{ Exception -> 0x00ae }
            if (r3 == 0) goto L_0x00b2
            android.content.Context r3 = r7.context     // Catch:{ Exception -> 0x00ae }
            if (r3 == 0) goto L_0x00a8
            android.content.Context r3 = r7.context     // Catch:{ Exception -> 0x00ae }
            boolean r3 = r3 instanceof com.medscape.android.drugs.DrugMonographMainActivity     // Catch:{ Exception -> 0x00ae }
            if (r3 == 0) goto L_0x00a8
            android.content.Context r3 = r7.context     // Catch:{ Exception -> 0x00ae }
            com.medscape.android.drugs.DrugMonographMainActivity r3 = (com.medscape.android.drugs.DrugMonographMainActivity) r3     // Catch:{ Exception -> 0x00ae }
            int r3 = r3.mContentId     // Catch:{ Exception -> 0x00ae }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ Exception -> 0x00ae }
            java.lang.String r4 = "drug://"
            java.lang.String r5 = ""
            java.lang.String r4 = r2.replace(r4, r5)     // Catch:{ Exception -> 0x00ae }
            boolean r3 = r3.equalsIgnoreCase(r4)     // Catch:{ Exception -> 0x00ae }
            if (r3 == 0) goto L_0x00a8
            android.content.Context r0 = r7.context     // Catch:{ Exception -> 0x00ae }
            com.medscape.android.drugs.DrugMonographMainActivity r0 = (com.medscape.android.drugs.DrugMonographMainActivity) r0     // Catch:{ Exception -> 0x00ae }
            r0.dismissAdDialog()     // Catch:{ Exception -> 0x00ae }
            return
        L_0x00a8:
            android.content.Context r3 = r7.context     // Catch:{ Exception -> 0x00ae }
            r0.handleRedirect(r3, r2, r1)     // Catch:{ Exception -> 0x00ae }
            goto L_0x00b2
        L_0x00ae:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00b2:
            java.lang.String r0 = "sendBIEvent"
            boolean r0 = r8.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x0100
            com.medscape.android.ads.adparsers.OmnitureDataParser$Companion r0 = com.medscape.android.ads.adparsers.OmnitureDataParser.Companion
            com.medscape.android.ads.adparsers.OmnitureDataModel r0 = r0.parseOmnitureData(r9)
            if (r0 == 0) goto L_0x0100
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            java.lang.String r1 = r0.getExiturl()
            java.lang.String r2 = "wapp.exiturl"
            r6.put(r2, r1)
            android.content.Context r1 = r7.context
            if (r1 == 0) goto L_0x00ed
            boolean r2 = r1 instanceof com.medscape.android.base.BaseActivity
            if (r2 == 0) goto L_0x00ed
            com.medscape.android.base.BaseActivity r1 = (com.medscape.android.base.BaseActivity) r1
            java.lang.String r1 = r1.mPvid
            boolean r1 = com.medscape.android.util.StringUtil.isNotEmpty(r1)
            if (r1 == 0) goto L_0x00ed
            android.content.Context r1 = r7.context
            com.medscape.android.base.BaseActivity r1 = (com.medscape.android.base.BaseActivity) r1
            java.lang.String r1 = r1.mPvid
            java.lang.String r2 = "wapp.pvid"
            r6.put(r2, r1)
        L_0x00ed:
            com.medscape.android.BI.omniture.OmnitureManager r1 = com.medscape.android.BI.omniture.OmnitureManager.get()
            android.content.Context r2 = r7.context
            java.lang.String r4 = r0.getMmodule()
            java.lang.String r5 = r0.getMlink()
            java.lang.String r3 = "reference and tools"
            r1.trackModuleAbsolute(r2, r3, r4, r5, r6)
        L_0x0100:
            java.lang.String r0 = "consultDriver"
            boolean r8 = r8.equalsIgnoreCase(r0)
            if (r8 == 0) goto L_0x014f
            android.content.Context r8 = r7.context
            if (r8 == 0) goto L_0x014f
            r0 = 0
            boolean r1 = r8 instanceof com.medscape.android.consult.activity.ConsultTimelineActivity
            if (r1 == 0) goto L_0x0121
            com.medscape.android.consult.activity.ConsultTimelineActivity r8 = (com.medscape.android.consult.activity.ConsultTimelineActivity) r8
            androidx.lifecycle.ViewModelProvider r8 = androidx.lifecycle.ViewModelProviders.of((androidx.fragment.app.FragmentActivity) r8)
            java.lang.Class<com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel> r0 = com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel.class
            androidx.lifecycle.ViewModel r8 = r8.get(r0)
            r0 = r8
            com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel r0 = (com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel) r0
            goto L_0x0134
        L_0x0121:
            boolean r1 = r8 instanceof com.medscape.android.consult.activity.ConsultSearchActivity
            if (r1 == 0) goto L_0x0134
            com.medscape.android.consult.activity.ConsultSearchActivity r8 = (com.medscape.android.consult.activity.ConsultSearchActivity) r8
            androidx.lifecycle.ViewModelProvider r8 = androidx.lifecycle.ViewModelProviders.of((androidx.fragment.app.FragmentActivity) r8)
            java.lang.Class<com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel> r0 = com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel.class
            androidx.lifecycle.ViewModel r8 = r8.get(r0)
            r0 = r8
            com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel r0 = (com.medscape.android.consult.viewmodels.ConsultSponsoredAdsViewModel) r0
        L_0x0134:
            if (r0 == 0) goto L_0x014f
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ JSONException -> 0x014b }
            r8.<init>(r9)     // Catch:{ JSONException -> 0x014b }
            java.lang.String r9 = "postId"
            java.lang.String r8 = r8.optString(r9)     // Catch:{ JSONException -> 0x014b }
            boolean r9 = com.medscape.android.util.StringUtil.isNotEmpty(r8)     // Catch:{ JSONException -> 0x014b }
            if (r9 == 0) goto L_0x014f
            r0.setValueForSponsoredPostID(r8)     // Catch:{ JSONException -> 0x014b }
            goto L_0x014f
        L_0x014b:
            r8 = move-exception
            r8.printStackTrace()
        L_0x014f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.ads.DFPAdAction.onAppEvent(java.lang.String, java.lang.String):void");
    }

    private void displayBannerAdLabel(PublisherAdView publisherAdView) {
        View view = this.adLayout;
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.adLabel);
            if (textView == null) {
                textView = (TextView) this.adLayout.findViewById(R.id.dfp_adLabel);
            }
            RelativeLayout relativeLayout = (RelativeLayout) this.adLayout.findViewById(R.id.ad_close_layout);
            TextView textView2 = (TextView) relativeLayout.findViewById(R.id.ad_close);
            if (textView2 != null) {
                textView2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Util.hideViewAnimation(DFPAdAction.this.context, DFPAdAction.this.adLayout);
                        DFPAdAction.this.adDismissed = true;
                        HashMap hashMap = new HashMap();
                        if (DFPAdAction.this.context != null && (DFPAdAction.this.context instanceof BaseActivity) && StringUtil.isNotEmpty(((BaseActivity) DFPAdAction.this.context).mPvid)) {
                            hashMap.put("wapp.pvid", ((BaseActivity) DFPAdAction.this.context).mPvid);
                        }
                        OmnitureManager.get().trackModuleAbsolute(DFPAdAction.this.context, "other", "footer", "dsms", hashMap);
                    }
                });
            }
            if (publisherAdView != null && (publisherAdView.getAdSize().equals(ADSIZE_3x3) || publisherAdView.getAdSize().equals(ADSIZE_3x3))) {
                relativeLayout.setVisibility(0);
                if (textView != null) {
                    textView.setVisibility(8);
                }
            } else if (textView != null) {
                relativeLayout.setVisibility(8);
                textView.setText(this.context.getResources().getString(R.string.advertisement));
                setAdLabelVisibility(textView, publisherAdView);
            }
        }
    }

    public void setAdLabelVisibility(TextView textView, PublisherAdView publisherAdView) {
        if (Arrays.asList(advLabelSizes).contains(publisherAdView.getAdSize())) {
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
    }

    private void handleDFPAd(String str) {
        this.dfpAd.setInfo(str);
        View view = this.adLayout;
        if (view != null) {
            TextView textView = (TextView) view.findViewById(R.id.adLabel);
            if (!this.dfpAd.isFullScreenAd() && !this.isInlineAd) {
                Settings.singleton(this.context).saveSetting(DFPAdListener.SHOW_FULLSCREENAD, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                textView.setText(this.dfpAd.getBannerLabel());
                textView.setBackgroundColor(Color.parseColor(this.dfpAd.getLabelColor()));
                textView.setTextColor(getTextColorBasedOnLuminosity(this.dfpAd.getLabelColor()));
                textView.setVisibility(0);
                this.adLayout.setVisibility(0);
            } else if (this.isInlineAd) {
                this.adLayout.setVisibility(0);
            } else {
                Settings.singleton(this.context).saveSetting(DFPAdListener.SHOW_FULLSCREENAD, "false");
                this.adLayout.setVisibility(8);
                textView.setVisibility(8);
            }
        }
    }

    private void handleOpenFullscreenAd(String str) {
        this.isDisplayFullScreenAd = true;
        this.dfpAd.setFullScreenAdInfo(str);
        if (this.dfpAd.isFullScreenAd()) {
            Settings.singleton(this.context).saveSetting(DFPAdListener.SHOW_FULLSCREENAD, "false");
        }
        showClassicFullScreenAd(this.dfpAd);
        resetDfpAd();
    }

    private void handleOpenSlideshow(String str) {
        this.dfpAd.setSlideshowInfo(str);
        Intent intent = new Intent(this.context, SlideshowViewer.class);
        Intent putExtra = intent.putExtra("slideshowUrl", this.dfpAd.getUrl() + SlideshowUtil.toAppend(this.dfpAd.getUrl())).putExtra("activityId", this.dfpAd.getActivityId()).putExtra("tacticId", this.dfpAd.getTcid()).putExtra("parId", this.dfpAd.getParId()).putExtra("isEditorial", this.dfpAd.isEditorial()).putExtra("orientationLock", this.dfpAd.getOrientationLock()).putExtra("slideType", this.dfpAd.getSlideType());
        Settings.singleton(this.context).saveSetting(DFPAdListener.SHOW_FULLSCREENAD, "false");
        this.context.startActivity(putExtra);
    }

    private void handleSendCPEvent(String str) {
        if (StringUtil.isNotEmpty(str)) {
            try {
                String str2 = OmnitureManager.get().getmCurrentPageName();
                Context context2 = this.context;
                new FireCPEventWithAdsSegvarAsyncTask(context2, (String) null, (String) null, "" + null, str2, str.trim()).execute(new HashMap[0]);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void resetDfpAd() {
        this.dfpAd = new DFPAd(this.context);
    }

    private int getTextColorBasedOnLuminosity(String str) {
        boolean z = true;
        if (str.startsWith("#")) {
            str = str.substring(1);
        }
        int parseLong = (int) Long.parseLong(str, 16);
        int i = (parseLong >> 16) & 255;
        int i2 = (parseLong >> 8) & 255;
        int i3 = (parseLong >> 0) & 255;
        if (!(i == i2 && i2 == i3)) {
            z = false;
        }
        if (((((double) i) / 255.0d) * 0.21d) + ((((double) i2) / 255.0d) * 0.72d) + ((((double) i3) / 255.0d) * 0.07d) < 0.5d) {
            return -1;
        }
        if (z) {
            return -12303292;
        }
        return ViewCompat.MEASURED_STATE_MASK;
    }

    class LocalOnAdListener implements OnAdListener {
        public void isAdExpandedByUser(boolean z) {
        }

        LocalOnAdListener() {
        }

        public void onAdAvailable() {
            if (!DFPAdAction.this.isForceHideAD) {
                if (DFPAdAction.this.adLayout != null) {
                    Util.showViewAnimation(DFPAdAction.this.context, DFPAdAction.this.adLayout);
                }
                if (DFPAdAction.this.callbackAdListner != null) {
                    DFPAdAction.this.callbackAdListner.onAdAvailable();
                }
            }
        }

        public void onAdNotAvilable() {
            try {
                if (DFPAdAction.this.adLayout != null) {
                    DFPAdAction.this.adLayout.setVisibility(8);
                }
                if (DFPAdAction.this.callbackAdListner != null && (DFPAdAction.this.callbackAdListner instanceof ConsultTimelineActivity)) {
                    DFPAdAction.this.callbackAdListner.onAdNotAvilable();
                }
            } catch (Throwable unused) {
            }
        }

        public String getCurrentPvid() {
            if (DFPAdAction.this.callbackAdListner != null) {
                return DFPAdAction.this.callbackAdListner.getCurrentPvid();
            }
            if (DFPAdAction.this.onPreCacheAdListener != null) {
                return DFPAdAction.this.onPreCacheAdListener.getCurrentPvid();
            }
            return null;
        }
    }

    public void clearGlobalMap() {
        AdsSegvar adsSegvar2 = this.adsSegvar;
        if (adsSegvar2 != null) {
            adsSegvar2.setGlobalMap(this.context);
        }
    }

    private String checkAndGetCurrentPvid() {
        OnAdListener onAdListener2 = this.onAdListener;
        if (onAdListener2 != null) {
            return onAdListener2.getCurrentPvid();
        }
        return null;
    }

    public PublisherAdRequest createAdRequestWithDetails() {
        PublisherAdRequest.Builder createAdRequestBuilder = createAdRequestBuilder();
        String str = OmnitureManager.get().getmCurrentPageName();
        if (StringUtil.isNotEmpty(str)) {
            createAdRequestBuilder.setContentUrl(str);
        }
        return createAdRequestBuilder.build();
    }

    public HashMap<String, String> getGlobalMap() {
        return this.globalMap;
    }
}
