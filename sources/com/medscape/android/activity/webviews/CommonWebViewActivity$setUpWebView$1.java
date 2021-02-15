package com.medscape.android.activity.webviews;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.medscape.android.R;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.db.FeedMaster;
import com.medscape.android.parser.model.Article;
import com.medscape.android.player.VideoPlayer;
import com.medscape.android.slideshow.SlideshowUtil;
import com.medscape.android.slideshow.SlideshowViewer;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import com.medscape.android.view.CustomChromeClient;
import com.tapstream.sdk.http.RequestBuilders;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000I\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J&\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0017J.\u0010\u000b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007H\u0016J\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u0016\u001a\u00020\u00172\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\u0019"}, d2 = {"com/medscape/android/activity/webviews/CommonWebViewActivity$setUpWebView$1", "Landroid/webkit/WebViewClient;", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "", "onPageStarted", "favicon", "Landroid/graphics/Bitmap;", "onReceivedError", "request", "Landroid/webkit/WebResourceRequest;", "error", "Landroid/webkit/WebResourceError;", "errorCode", "", "description", "failingUrl", "shouldInterceptRequest", "Landroid/webkit/WebResourceResponse;", "shouldOverrideUrlLoading", "", "lUrl", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CommonWebViewActivity.kt */
public final class CommonWebViewActivity$setUpWebView$1 extends WebViewClient {
    final /* synthetic */ CommonWebViewActivity this$0;

    CommonWebViewActivity$setUpWebView$1(CommonWebViewActivity commonWebViewActivity) {
        this.this$0 = commonWebViewActivity;
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        Intrinsics.checkNotNullParameter(webView, "view");
        Intrinsics.checkNotNullParameter(webResourceRequest, ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID);
        String uri = webResourceRequest.getUrl().toString();
        Intrinsics.checkNotNullExpressionValue(uri, "request.url.toString()");
        if (StringsKt.startsWith$default(uri, "https://ssl.o.medscape.com", false, 2, (Object) null) || StringsKt.startsWith$default(uri, "https://ssl.o.webmd.com", false, 2, (Object) null)) {
            CommonWebViewActivity commonWebViewActivity = this.this$0;
            commonWebViewActivity.omnitureCallsCounter = commonWebViewActivity.omnitureCallsCounter + 1;
            if (this.this$0.omnitureCallsCounter == 1 && this.this$0.isRecapArticle) {
                CommonWebViewActivity commonWebViewActivity2 = this.this$0;
                commonWebViewActivity2.eventsCounter = commonWebViewActivity2.eventsCounter + 1;
                PlatformRouteDispatcher firebaseRouter = this.this$0.getFirebaseRouter();
                firebaseRouter.routeEvent("OmnitureCall_" + this.this$0.eventsCounter);
            }
        }
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (Build.VERSION.SDK_INT >= 23) {
            return;
        }
        if (!Util.isOnline(this.this$0)) {
            this.this$0.showErrorSnackBar();
        } else {
            this.this$0.handleOnReceivedErrorWebview(i, str2);
        }
    }

    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        int errorCode = webResourceError != null ? webResourceError.getErrorCode() : 0;
        if (!Util.isOnline(this.this$0)) {
            this.this$0.showErrorSnackBar();
        } else {
            this.this$0.handleOnReceivedErrorWebview(errorCode, String.valueOf(webResourceRequest != null ? webResourceRequest.getUrl() : null));
        }
    }

    public void onPageFinished(WebView webView, String str) {
        String title;
        this.this$0.getMWebView().loadUrl("javascript:hasAdRefresh=1");
        CustomChromeClient access$getChromeClient$p = this.this$0.chromeClient;
        if (access$getChromeClient$p != null) {
            access$getChromeClient$p.mListener = null;
        }
        this.this$0.getProgress().setVisibility(8);
        if (this.this$0.isRecapArticle) {
            CommonWebViewActivity commonWebViewActivity = this.this$0;
            commonWebViewActivity.eventsCounter = commonWebViewActivity.eventsCounter + 1;
            PlatformRouteDispatcher firebaseRouter = this.this$0.getFirebaseRouter();
            firebaseRouter.routeEvent("PageLoad_" + this.this$0.eventsCounter);
        }
        if (this.this$0.getWebViewModel().getActionBarTitleMode() == 2) {
            if (str != null ? str.equals(this.this$0.getWebViewModel().getLink()) : false) {
                WebViewViewModel webViewModel = this.this$0.getWebViewModel();
                CommonWebViewActivity commonWebViewActivity2 = this.this$0;
                webViewModel.setActionBarTitle(commonWebViewActivity2, commonWebViewActivity2.getSupportActionBar(), this.this$0.getTitle());
            } else {
                WebViewViewModel webViewModel2 = this.this$0.getWebViewModel();
                CommonWebViewActivity commonWebViewActivity3 = this.this$0;
                webViewModel2.setActionBarTitle(commonWebViewActivity3, commonWebViewActivity3.getSupportActionBar(), (webView == null || (title = webView.getTitle()) == null) ? this.this$0.getTitle() : title);
            }
        }
        if ((str == null || !StringsKt.contains$default((CharSequence) str, (CharSequence) "about:blank", false, 2, (Object) null)) && webView != null) {
            webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
        }
        if (webView != null) {
            webView.loadUrl("javascript:document.getElementById('medscapeheader').style.display = 'none';");
        }
        if (webView != null) {
            webView.loadUrl("javascript:document.getElementById('medscapefooter').style.display = 'none';");
        }
        new Handler().postDelayed(new CommonWebViewActivity$setUpWebView$1$onPageFinished$1(this), 300);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.this$0.getProgress().setVisibility(0);
        CustomChromeClient access$getChromeClient$p = this.this$0.chromeClient;
        if (access$getChromeClient$p != null) {
            access$getChromeClient$p.startTimer();
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Article mArticle;
        if (!Util.isOnline(this.this$0)) {
            this.this$0.getProgress().setVisibility(8);
        } else if (str != null) {
            Uri parse = Uri.parse(str);
            boolean z = false;
            if (StringsKt.startsWith$default(str, "tel:", false, 2, (Object) null)) {
                Uri.parse(str);
                this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (StringsKt.startsWith$default(str, "geo", false, 2, (Object) null)) {
                Uri.parse(str);
                this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                return true;
            } else if (StringsKt.startsWith$default(str, "mail", false, 2, (Object) null)) {
                Uri.parse(str);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                if (Util.isEmailConfigured(this.this$0, intent)) {
                    this.this$0.startActivity(intent);
                } else if (!this.this$0.isFinishing()) {
                    CommonWebViewActivity commonWebViewActivity = this.this$0;
                    DialogUtil.showAlertDialog(24, (String) null, commonWebViewActivity.getString(R.string.alert_show_email_configure_message), commonWebViewActivity).show();
                }
                return true;
            } else if (StringsKt.startsWith$default(str, "adrefresh", false, 2, (Object) null)) {
                String replace$default = StringsKt.replace$default(str, "adrefresh", RequestBuilders.DEFAULT_SCHEME, false, 4, (Object) null);
                this.this$0.onAdNotAvilable();
                this.this$0.getMWebView().loadUrl(replace$default);
                return true;
            } else {
                CharSequence charSequence = str;
                if (StringsKt.contains$default(charSequence, (CharSequence) "mp4", false, 2, (Object) null) || StringsKt.contains$default(charSequence, (CharSequence) "mp3", false, 2, (Object) null)) {
                    Intent intent2 = new Intent(this.this$0, VideoPlayer.class);
                    if (StringsKt.contains$default(charSequence, (CharSequence) "exturl=", false, 2, (Object) null)) {
                        str = str.substring(StringsKt.indexOf$default(charSequence, "exturl=", 0, false, 6, (Object) null) + 7);
                        Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.String).substring(startIndex)");
                    }
                    CharSequence charSequence2 = str;
                    int length = charSequence2.length() - 1;
                    int i = 0;
                    boolean z2 = false;
                    while (i <= length) {
                        boolean z3 = Intrinsics.compare((int) charSequence2.charAt(!z2 ? i : length), 32) <= 0;
                        if (!z2) {
                            if (!z3) {
                                z2 = true;
                            } else {
                                i++;
                            }
                        } else if (!z3) {
                            break;
                        } else {
                            length--;
                        }
                    }
                    intent2.putExtra("path", charSequence2.subSequence(i, length + 1).toString());
                    intent2.putExtra("articleTitle", "  ");
                    this.this$0.startActivity(intent2);
                    return true;
                } else if (StringsKt.contains$default(charSequence, (CharSequence) "activitytracker", false, 2, (Object) null)) {
                    CMEHelper.launchCMETracker$default(this.this$0, false, 2, (Object) null);
                    return true;
                } else if (this.this$0.getWebViewModel().getMArticle() != null) {
                    Intrinsics.checkNotNullExpressionValue(parse, "uri");
                    String scheme = parse.getScheme();
                    Intrinsics.checkNotNull(scheme);
                    Intrinsics.checkNotNullExpressionValue(scheme, "uri.scheme!!");
                    if (scheme != null) {
                        String lowerCase = scheme.toLowerCase();
                        Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
                        if (Intrinsics.areEqual((Object) lowerCase, (Object) "slideshow")) {
                            String contentUrlFromSchemeSpecificPart = SlideshowUtil.getContentUrlFromSchemeSpecificPart(parse.getSchemeSpecificPart());
                            Uri parse2 = Uri.parse(contentUrlFromSchemeSpecificPart);
                            String queryParameter = parse2.getQueryParameter("isEditorial");
                            String queryParameter2 = parse2.getQueryParameter("orientationLock");
                            String queryParameter3 = parse2.getQueryParameter("slideType");
                            if (queryParameter != null && !StringsKt.equals("false", queryParameter, true) && (!Intrinsics.areEqual((Object) AppEventsConstants.EVENT_PARAM_VALUE_NO, (Object) queryParameter))) {
                                z = true;
                            }
                            Intent intent3 = new Intent(this.this$0, SlideshowViewer.class);
                            intent3.putExtra("slideshowUrl", contentUrlFromSchemeSpecificPart + SlideshowUtil.toAppend(contentUrlFromSchemeSpecificPart));
                            intent3.putExtra("isEditorial", z);
                            Article mArticle2 = this.this$0.getWebViewModel().getMArticle();
                            if (mArticle2 != null && mArticle2.mCellType == 3) {
                                Boolean bool = Boolean.TRUE;
                                Intrinsics.checkNotNullExpressionValue(bool, "java.lang.Boolean.TRUE");
                                intent3.putExtra("isBrandPlay", bool.booleanValue());
                            }
                            if (queryParameter2 != null) {
                                intent3.putExtra("orientationLock", queryParameter2);
                            }
                            if (queryParameter3 != null) {
                                intent3.putExtra("slideType", queryParameter3);
                            }
                            this.this$0.startActivity(intent3);
                            return true;
                        }
                        String scheme2 = parse.getScheme();
                        Intrinsics.checkNotNull(scheme2);
                        Intrinsics.checkNotNullExpressionValue(scheme2, "uri.scheme!!");
                        if (scheme2 != null) {
                            String lowerCase2 = scheme2.toLowerCase();
                            Intrinsics.checkNotNullExpressionValue(lowerCase2, "(this as java.lang.String).toLowerCase()");
                            if (Intrinsics.areEqual((Object) lowerCase2, (Object) "customurl")) {
                                Util.openExternalApp(this.this$0, parse);
                                return true;
                            }
                            Article mArticle3 = this.this$0.getWebViewModel().getMArticle();
                            if ((mArticle3 == null || mArticle3.mCellType != 1) && ((mArticle = this.this$0.getWebViewModel().getMArticle()) == null || mArticle.mCellType != 4)) {
                                Intent intent4 = new Intent(this.this$0, CommonWebViewActivity.class);
                                intent4.putExtra("url", str);
                                intent4.putExtra(FeedMaster.F_SPECIALTY_NAME, this.this$0.getIntent().getStringExtra(FeedMaster.F_SPECIALTY_NAME));
                                intent4.putExtra("dontShowTitle", true);
                                this.this$0.startActivity(intent4);
                            } else {
                                Intent intent5 = new Intent(this.this$0, CommonWebViewActivity.class);
                                intent5.putExtra("url", str);
                                intent5.putExtra("contentType", "promo");
                                this.this$0.startActivity(intent5);
                            }
                            return true;
                        }
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                } else {
                    this.this$0.getProgress().setVisibility(0);
                }
            }
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }
}
