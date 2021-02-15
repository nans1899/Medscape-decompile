package com.medscape.android.activity.webviews;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.cache.Cache;
import com.medscape.android.parser.model.Article;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.Util;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/medscape/android/activity/webviews/WebviewUtil;", "", "()V", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: WebviewUtil.kt */
public final class WebviewUtil {
    public static final int ACTIONBAR_DYNAMIC_TITLE = 2;
    public static final int ACTIONBAR_NO_TITLE = 0;
    public static final String ACTIONBAR_THEME_EXTRA = "base.webview.actionbar.theme.extra";
    public static final String ACTIONBAR_TITLE_EXTRA = "base.webview.actionbar.title.extra";
    public static final int ACTIONBAR_W_TITLE = 1;
    public static final String ARTICLE_EXTRA = "article";
    public static final String BYLINE_TXT_EXTRA = "base.webview.bylinetxt.extra";
    public static final String CONTENT_ID_EXTRA = "base.webview.contentId.extra";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DATE_EXTRA = "base.webview.date.extra";
    public static final String IMAGE_URL_EXTRA = "base.webview.imageurl.extra";
    public static final String LINK_EXTRA = "base.webview.link.extra";
    public static final String LOGO_EXTRA = "base.webview.logo.extra";
    public static final int MEDSCAPE_LOGO = 0;
    public static final int NEWS_LOGO = 1;
    public static final String SAVEABLE_EXTRA = "base.webview.saveable.extra";
    public static final String SAVETYPE_EXTRA = "base.webview.savetype.extra";
    public static final String SHAREABLE_EXTRA = "base.webview.shareable.exra";
    public static final String TITLE_EXTRA = "base.webview.title.extra";
    public static final String WITH_ADS_EXTRA = "base.webview.with.ads";
    public static final String WITH_AUTO_ROTATE = "base.webview.with.autorotate";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0004J\u0006\u0010\u001e\u001a\u00020\u0007JH\u0010\u001f\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\b\b\u0002\u0010(\u001a\u00020&J\u0016\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010+\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007J\u0016\u0010,\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u0007J8\u0010-\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u00072\b\u0010/\u001a\u0004\u0018\u00010\u00072\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007JV\u00100\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u00072\b\u0010/\u001a\u0004\u0018\u00010\u00072\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\b\u00101\u001a\u0004\u0018\u00010\u00072\b\u00102\u001a\u0004\u0018\u00010\u00072\b\u00103\u001a\u0004\u0018\u00010\u0007JT\u00104\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u00072\b\u0010/\u001a\u0004\u0018\u00010\u00072\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\b\u00105\u001a\u0004\u0018\u00010\u00072\u0006\u00106\u001a\u00020\u00042\b\b\u0002\u00107\u001a\u00020&J.\u00108\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u00109\u001a\u00020:2\u0006\u0010'\u001a\u00020&2\u0006\u0010%\u001a\u00020&2\u0006\u0010;\u001a\u00020\u0007JH\u0010<\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u00072\b\u0010/\u001a\u0004\u0018\u00010\u00072\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u00072\u0006\u00106\u001a\u00020\u0004J\u000e\u0010=\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/medscape/android/activity/webviews/WebviewUtil$Companion;", "", "()V", "ACTIONBAR_DYNAMIC_TITLE", "", "ACTIONBAR_NO_TITLE", "ACTIONBAR_THEME_EXTRA", "", "ACTIONBAR_TITLE_EXTRA", "ACTIONBAR_W_TITLE", "ARTICLE_EXTRA", "BYLINE_TXT_EXTRA", "CONTENT_ID_EXTRA", "DATE_EXTRA", "IMAGE_URL_EXTRA", "LINK_EXTRA", "LOGO_EXTRA", "MEDSCAPE_LOGO", "NEWS_LOGO", "SAVEABLE_EXTRA", "SAVETYPE_EXTRA", "SHAREABLE_EXTRA", "TITLE_EXTRA", "WITH_ADS_EXTRA", "WITH_AUTO_ROTATE", "getActionBarLogo", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "logo_type", "getCMETrackerUrl", "getCommonWebViewIntent", "Landroid/content/Intent;", "url", "referringModule", "referringLink", "channel", "isShareable", "", "isSavable", "isAutoRotate", "getNewsFullUrl", "articleId", "getNewsUrl", "insertEnvironment", "launchMedline", "", "title", "launchNews", "date", "imageURl", "bylineTxt", "launchPlainWebView", "contentType", "actionBarTitle", "autoRotate", "launchRecentlySavedWebView", "meta", "Landroid/os/Bundle;", "type", "launchShareableWebView", "parseArticleID", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: WebviewUtil.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Drawable getActionBarLogo(Context context, int i) {
            Drawable drawable;
            Intrinsics.checkNotNullParameter(context, "context");
            if (i != 0) {
                drawable = i != 1 ? null : ContextCompat.getDrawable(context, R.drawable.news_logo);
            } else {
                drawable = ContextCompat.getDrawable(context, R.drawable.home_logo);
            }
            if (drawable != null) {
                DrawableCompat.setTint(drawable, ContextCompat.getColor(context, R.color.white));
            }
            return drawable;
        }

        public final void launchNews(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
            Context context2 = context;
            String str9 = str;
            String str10 = str2;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "url");
            String str11 = str3;
            Intrinsics.checkNotNullParameter(str11, "referringModule");
            String str12 = str4;
            Intrinsics.checkNotNullParameter(str12, "referringLink");
            String str13 = str5;
            Intrinsics.checkNotNullParameter(str13, "channel");
            Companion companion = this;
            Intent commonWebViewIntent$default = getCommonWebViewIntent$default(companion, context, companion.insertEnvironment(context, str), str11, str12, str13, true, true, false, 128, (Object) null);
            commonWebViewIntent$default.putExtra(WebviewUtil.LOGO_EXTRA, 1);
            commonWebViewIntent$default.putExtra(WebviewUtil.SAVETYPE_EXTRA, Cache.NEWS);
            commonWebViewIntent$default.putExtra(WebviewUtil.TITLE_EXTRA, str10);
            commonWebViewIntent$default.putExtra(WebviewUtil.DATE_EXTRA, str6);
            commonWebViewIntent$default.putExtra(WebviewUtil.IMAGE_URL_EXTRA, str7);
            commonWebViewIntent$default.putExtra(WebviewUtil.BYLINE_TXT_EXTRA, str8);
            commonWebViewIntent$default.putExtra(WebviewUtil.WITH_ADS_EXTRA, true);
            Bundle bundle = new Bundle(commonWebViewIntent$default.getExtras());
            bundle.putString("type", "news");
            RecentlyViewedSuggestionHelper.addToRecentlyViewed(context, str10, bundle);
            context.startActivity(commonWebViewIntent$default);
        }

        public final void launchMedline(Context context, String str, String str2, String str3, String str4, String str5) {
            Context context2 = context;
            String str6 = str2;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "url");
            String str7 = str3;
            Intrinsics.checkNotNullParameter(str7, "referringModule");
            String str8 = str4;
            Intrinsics.checkNotNullParameter(str8, "referringLink");
            String str9 = str5;
            Intrinsics.checkNotNullParameter(str9, "channel");
            Intent commonWebViewIntent$default = getCommonWebViewIntent$default(this, context, str, str7, str8, str9, true, false, false, 128, (Object) null);
            commonWebViewIntent$default.putExtra(WebviewUtil.LOGO_EXTRA, 0);
            commonWebViewIntent$default.putExtra(WebviewUtil.TITLE_EXTRA, str6);
            Bundle bundle = new Bundle(commonWebViewIntent$default.getExtras());
            bundle.putString("type", RecentlyViewedSuggestionHelper.TYPE_MEDLINE);
            RecentlyViewedSuggestionHelper.addToRecentlyViewed(context, str6, bundle);
            context.startActivity(commonWebViewIntent$default);
        }

        public static /* synthetic */ void launchPlainWebView$default(Companion companion, Context context, String str, String str2, String str3, String str4, String str5, String str6, int i, boolean z, int i2, Object obj) {
            companion.launchPlainWebView(context, str, str2, str3, str4, str5, str6, i, (i2 & 256) != 0 ? false : z);
        }

        public final void launchPlainWebView(Context context, String str, String str2, String str3, String str4, String str5, String str6, int i, boolean z) {
            Context context2 = context;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(str3, "referringModule");
            Intrinsics.checkNotNullParameter(str4, "referringLink");
            String str7 = str5;
            Intrinsics.checkNotNullParameter(str7, "channel");
            Intent commonWebViewIntent = getCommonWebViewIntent(context, str, str3, str4, str7, false, false, z);
            String str8 = str2;
            commonWebViewIntent.putExtra(WebviewUtil.TITLE_EXTRA, str2);
            commonWebViewIntent.putExtra("contentType", str6);
            commonWebViewIntent.putExtra(WebviewUtil.ACTIONBAR_TITLE_EXTRA, i);
            context.startActivity(commonWebViewIntent);
        }

        public final void launchShareableWebView(Context context, String str, String str2, String str3, String str4, String str5, String str6, int i) {
            Context context2 = context;
            String str7 = str6;
            Intrinsics.checkNotNullParameter(context, "context");
            String str8 = str;
            Intrinsics.checkNotNullParameter(str8, "url");
            String str9 = str3;
            Intrinsics.checkNotNullParameter(str9, "referringModule");
            String str10 = str4;
            Intrinsics.checkNotNullParameter(str10, "referringLink");
            String str11 = str5;
            Intrinsics.checkNotNullParameter(str11, "channel");
            Intrinsics.checkNotNullParameter(str7, "contentType");
            Intent commonWebViewIntent$default = getCommonWebViewIntent$default(this, context, str8, str9, str10, str11, true, false, false, 128, (Object) null);
            commonWebViewIntent$default.putExtra(WebviewUtil.TITLE_EXTRA, str2);
            commonWebViewIntent$default.putExtra("contentType", str7);
            commonWebViewIntent$default.putExtra(WebviewUtil.ACTIONBAR_TITLE_EXTRA, i);
            context.startActivity(commonWebViewIntent$default);
        }

        public final void launchRecentlySavedWebView(Context context, Bundle bundle, boolean z, boolean z2, String str) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(bundle, JSONAPISpecConstants.META);
            Intrinsics.checkNotNullParameter(str, "type");
            Intent intent = new Intent(context, CommonWebViewActivity.class);
            intent.putExtras(bundle);
            String string = bundle.getString(WebviewUtil.TITLE_EXTRA);
            CharSequence charSequence = string;
            boolean z3 = false;
            if (!(charSequence == null || charSequence.length() == 0)) {
                Object obj = bundle.get("article");
                if (obj != null) {
                    if (obj != null) {
                        string = ((Article) obj).mTitle;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.parser.model.Article");
                    }
                }
                CharSequence charSequence2 = string;
                if (charSequence2 == null || charSequence2.length() == 0) {
                    z3 = true;
                }
                if (!z3) {
                    RecentlyViewedSuggestionHelper.addToRecentlyViewed(context, string, bundle);
                }
            }
            if (Intrinsics.areEqual((Object) str, (Object) "news")) {
                intent.putExtra("wapp.chn", Constants.OMNITURE_CHANNEL_NEWS);
            } else {
                intent.putExtra("wapp.chn", OmnitureManager.get().mSearchChannel);
            }
            intent.putExtra("wapp.mmodule", "wv-launch-rvwd");
            intent.putExtra(WebviewUtil.SHAREABLE_EXTRA, z2);
            intent.putExtra(WebviewUtil.SAVEABLE_EXTRA, z);
            context.startActivity(intent);
        }

        public static /* synthetic */ Intent getCommonWebViewIntent$default(Companion companion, Context context, String str, String str2, String str3, String str4, boolean z, boolean z2, boolean z3, int i, Object obj) {
            return companion.getCommonWebViewIntent(context, str, str2, str3, str4, z, z2, (i & 128) != 0 ? false : z3);
        }

        public final Intent getCommonWebViewIntent(Context context, String str, String str2, String str3, String str4, boolean z, boolean z2, boolean z3) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(str, "url");
            Intrinsics.checkNotNullParameter(str2, "referringModule");
            Intrinsics.checkNotNullParameter(str3, "referringLink");
            Intrinsics.checkNotNullParameter(str4, "channel");
            Intent intent = new Intent(context, CommonWebViewActivity.class);
            intent.putExtra(WebviewUtil.LINK_EXTRA, str);
            intent.putExtra(WebviewUtil.SHAREABLE_EXTRA, z);
            intent.putExtra(WebviewUtil.SAVEABLE_EXTRA, z2);
            intent.putExtra(WebviewUtil.WITH_AUTO_ROTATE, z3);
            intent.putExtra("wapp.mmodule", str2);
            intent.putExtra("wapp.mlink", str3);
            intent.putExtra("wapp.chn", str4);
            return intent;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.String insertEnvironment(android.content.Context r8, java.lang.String r9) {
            /*
                r7 = this;
                java.lang.String r0 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                java.lang.String r0 = "url"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                r0 = r9
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0
                boolean r1 = kotlin.text.StringsKt.isBlank(r0)
                if (r1 != 0) goto L_0x009f
                com.wbmd.environment.EnvironmentManager r1 = new com.wbmd.environment.EnvironmentManager
                r1.<init>()
                java.lang.String r2 = "module_feed"
                java.lang.String r8 = r1.getEnvironmentWithDefault(r8, r2)
                int r1 = r8.hashCode()
                switch(r1) {
                    case 68759055: goto L_0x005d;
                    case 446124970: goto L_0x0052;
                    case 568961724: goto L_0x0047;
                    case 568961725: goto L_0x003c;
                    case 568961726: goto L_0x0031;
                    case 1680909289: goto L_0x0026;
                    default: goto L_0x0025;
                }
            L_0x0025:
                goto L_0x0068
            L_0x0026:
                java.lang.String r1 = "environment_dev"
                boolean r8 = r8.equals(r1)
                if (r8 == 0) goto L_0x0068
                java.lang.String r8 = "dev."
                goto L_0x006a
            L_0x0031:
                java.lang.String r1 = "environment_qa02"
                boolean r8 = r8.equals(r1)
                if (r8 == 0) goto L_0x0068
                java.lang.String r8 = "qa02."
                goto L_0x006a
            L_0x003c:
                java.lang.String r1 = "environment_qa01"
                boolean r8 = r8.equals(r1)
                if (r8 == 0) goto L_0x0068
                java.lang.String r8 = "qa01."
                goto L_0x006a
            L_0x0047:
                java.lang.String r1 = "environment_qa00"
                boolean r8 = r8.equals(r1)
                if (r8 == 0) goto L_0x0068
                java.lang.String r8 = "qa00."
                goto L_0x006a
            L_0x0052:
                java.lang.String r1 = "environment_dev01"
                boolean r8 = r8.equals(r1)
                if (r8 == 0) goto L_0x0068
                java.lang.String r8 = "dev01."
                goto L_0x006a
            L_0x005d:
                java.lang.String r1 = "environment_staging"
                boolean r8 = r8.equals(r1)
                if (r8 == 0) goto L_0x0068
                java.lang.String r8 = "staging."
                goto L_0x006a
            L_0x0068:
                java.lang.String r8 = ""
            L_0x006a:
                java.lang.String r2 = "//www."
                r1 = r8
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r3 = kotlin.text.StringsKt.isBlank(r1)
                if (r3 != 0) goto L_0x009f
                r3 = 0
                r4 = 2
                r5 = 0
                boolean r1 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) r1, (boolean) r3, (int) r4, (java.lang.Object) r5)
                if (r1 != 0) goto L_0x009f
                r1 = r2
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                boolean r0 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) r1, (boolean) r3, (int) r4, (java.lang.Object) r5)
                if (r0 == 0) goto L_0x009f
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r0.append(r2)
                r0.append(r8)
                java.lang.String r3 = r0.toString()
                r4 = 0
                r5 = 4
                r6 = 0
                r1 = r9
                java.lang.String r8 = kotlin.text.StringsKt.replace$default((java.lang.String) r1, (java.lang.String) r2, (java.lang.String) r3, (boolean) r4, (int) r5, (java.lang.Object) r6)
                return r8
            L_0x009f:
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.webviews.WebviewUtil.Companion.insertEnvironment(android.content.Context, java.lang.String):java.lang.String");
        }

        public final String getNewsUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "articleId");
            return "https://www.medscape.com/viewarticle/" + str;
        }

        public final String getNewsFullUrl(String str, Context context) {
            Intrinsics.checkNotNullParameter(str, "articleId");
            Intrinsics.checkNotNullParameter(context, "context");
            String str2 = "https://www.medscape.com/viewarticle/" + str;
            Util.getApplicationVersion(context);
            String str3 = str2 + com.wbmd.wbmdcommons.utils.Util.attachAndrodSrcTagToUrl(str2);
            return str3 + com.wbmd.wbmdcommons.utils.Util.addBasicQueryParams(str3);
        }

        public final String getCMETrackerUrl() {
            return OldConstants.CME_TRACKER_URL + com.wbmd.wbmdcommons.utils.Util.attachSrcTagToUrl(OldConstants.CME_TRACKER_URL);
        }

        public final String parseArticleID(String str) {
            Intrinsics.checkNotNullParameter(str, "url");
            if (StringsKt.contains((CharSequence) str, (CharSequence) "viewarticle", true)) {
                Uri parse = Uri.parse(str);
                Intrinsics.checkNotNullExpressionValue(parse, "Uri.parse(url)");
                if (parse.getLastPathSegment() != null) {
                    Uri parse2 = Uri.parse(str);
                    Intrinsics.checkNotNullExpressionValue(parse2, "Uri.parse(url)");
                    String lastPathSegment = parse2.getLastPathSegment();
                    Intrinsics.checkNotNull(lastPathSegment);
                    return lastPathSegment;
                }
            }
            return "";
        }
    }
}
