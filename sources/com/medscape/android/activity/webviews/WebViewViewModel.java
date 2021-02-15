package com.medscape.android.activity.webviews;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.analytics.remoteconfig.reference.FeatureConfigManager;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.cache.Cache;
import com.medscape.android.cache.CacheManager;
import com.medscape.android.homescreen.home_nav_tray.repositories.NavItemManager;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.parser.model.Article;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.bytebuddy.description.type.TypeDescription;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010A\u001a\u0004\u0018\u00010\n2\b\u0010B\u001a\u0004\u0018\u00010\nJ\u0006\u0010C\u001a\u00020\nJ\u0006\u0010D\u001a\u00020\nJ\u0006\u0010E\u001a\u00020\nJ\u000e\u0010F\u001a\u00020\n2\u0006\u0010G\u001a\u00020HJ\u000e\u0010I\u001a\u00020\f2\u0006\u0010G\u001a\u00020HJ\u000e\u0010J\u001a\u00020K2\u0006\u0010L\u001a\u00020MJ \u0010N\u001a\u00020K2\u0006\u0010G\u001a\u00020H2\b\u0010O\u001a\u0004\u0018\u00010P2\u0006\u0010Q\u001a\u00020RJ\u001f\u0010S\u001a\u0004\u0018\u00010\f2\u0006\u0010G\u001a\u00020H2\b\u0010O\u001a\u0004\u0018\u00010P¢\u0006\u0002\u0010TJ\"\u0010U\u001a\u00020K2\u0006\u0010G\u001a\u00020H2\b\u0010V\u001a\u0004\u0018\u00010W2\b\u0010;\u001a\u0004\u0018\u00010XJ\u0018\u0010Y\u001a\u00020K2\u0006\u0010Q\u001a\u00020R2\b\u0010Z\u001a\u0004\u0018\u00010[J\u0010\u0010\\\u001a\u00020\f2\u0006\u0010G\u001a\u00020HH\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u001a\u0010\u0019\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u001a\u0010\u001b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R\u001a\u0010\u001d\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010R \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R\u001a\u0010(\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0006\"\u0004\b*\u0010\bR\u001c\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0006\"\u0004\b3\u0010\bR\u001f\u00104\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n05¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u000e\"\u0004\b:\u0010\u0010R\u001c\u0010;\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u0013\"\u0004\b=\u0010\u0015R\u001a\u0010>\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\u000e\"\u0004\b@\u0010\u0010¨\u0006]"}, d2 = {"Lcom/medscape/android/activity/webviews/WebViewViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "actionBarTitleMode", "", "getActionBarTitleMode", "()I", "setActionBarTitleMode", "(I)V", "byline", "", "canSendEvent", "", "getCanSendEvent", "()Z", "setCanSendEvent", "(Z)V", "date", "getDate", "()Ljava/lang/String;", "setDate", "(Ljava/lang/String;)V", "imageUrl", "getImageUrl", "setImageUrl", "isAutoRotate", "setAutoRotate", "isSavable", "setSavable", "isShareable", "setShareable", "leadConceptSegVar", "Landroidx/lifecycle/MutableLiveData;", "getLeadConceptSegVar", "()Landroidx/lifecycle/MutableLiveData;", "setLeadConceptSegVar", "(Landroidx/lifecycle/MutableLiveData;)V", "link", "getLink", "setLink", "logoType", "getLogoType", "setLogoType", "mArticle", "Lcom/medscape/android/parser/model/Article;", "getMArticle", "()Lcom/medscape/android/parser/model/Article;", "setMArticle", "(Lcom/medscape/android/parser/model/Article;)V", "saveType", "getSaveType", "setSaveType", "screenSpecificMap", "Ljava/util/HashMap;", "getScreenSpecificMap", "()Ljava/util/HashMap;", "screenSpecificMapSet", "getScreenSpecificMapSet", "setScreenSpecificMapSet", "title", "getTitle", "setTitle", "withAds", "getWithAds", "setWithAds", "cleanUrl", "url", "getAppBoyEvent", "getOmnitureSaveChannel", "getOmnitureSaveLink", "getSavedMessage", "context", "Landroid/content/Context;", "isContentSaved", "makeADCall", "", "adAction", "Lcom/medscape/android/ads/DFPAdAction;", "onClickSaveIcon", "webView", "Landroid/webkit/WebView;", "activity", "Landroid/app/Activity;", "saveContent", "(Landroid/content/Context;Landroid/webkit/WebView;)Ljava/lang/Boolean;", "setActionBarTitle", "actionbar", "Landroidx/appcompat/app/ActionBar;", "", "setIntentData", "intent", "Landroid/content/Intent;", "unSaveContent", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: WebViewViewModel.kt */
public final class WebViewViewModel extends ViewModel {
    private int actionBarTitleMode;
    private String byline;
    private boolean canSendEvent;
    private String date;
    private String imageUrl;
    private boolean isAutoRotate;
    private boolean isSavable;
    private boolean isShareable;
    private MutableLiveData<String> leadConceptSegVar = new MutableLiveData<>();
    private String link;
    private int logoType = -1;
    private Article mArticle;
    private int saveType = Cache.NEWS;
    private final HashMap<String, String> screenSpecificMap = new HashMap<>();
    private boolean screenSpecificMapSet;
    private String title;
    private boolean withAds;

    public final String getLink() {
        return this.link;
    }

    public final void setLink(String str) {
        this.link = str;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final Article getMArticle() {
        return this.mArticle;
    }

    public final void setMArticle(Article article) {
        this.mArticle = article;
    }

    public final int getLogoType() {
        return this.logoType;
    }

    public final void setLogoType(int i) {
        this.logoType = i;
    }

    public final boolean isShareable() {
        return this.isShareable;
    }

    public final void setShareable(boolean z) {
        this.isShareable = z;
    }

    public final boolean isSavable() {
        return this.isSavable;
    }

    public final void setSavable(boolean z) {
        this.isSavable = z;
    }

    public final int getSaveType() {
        return this.saveType;
    }

    public final void setSaveType(int i) {
        this.saveType = i;
    }

    public final int getActionBarTitleMode() {
        return this.actionBarTitleMode;
    }

    public final void setActionBarTitleMode(int i) {
        this.actionBarTitleMode = i;
    }

    public final String getDate() {
        return this.date;
    }

    public final void setDate(String str) {
        this.date = str;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public final HashMap<String, String> getScreenSpecificMap() {
        return this.screenSpecificMap;
    }

    public final boolean getScreenSpecificMapSet() {
        return this.screenSpecificMapSet;
    }

    public final void setScreenSpecificMapSet(boolean z) {
        this.screenSpecificMapSet = z;
    }

    public final boolean getWithAds() {
        return this.withAds;
    }

    public final void setWithAds(boolean z) {
        this.withAds = z;
    }

    public final MutableLiveData<String> getLeadConceptSegVar() {
        return this.leadConceptSegVar;
    }

    public final void setLeadConceptSegVar(MutableLiveData<String> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.leadConceptSegVar = mutableLiveData;
    }

    public final boolean getCanSendEvent() {
        return this.canSendEvent;
    }

    public final void setCanSendEvent(boolean z) {
        this.canSendEvent = z;
    }

    public final boolean isAutoRotate() {
        return this.isAutoRotate;
    }

    public final void setAutoRotate(boolean z) {
        this.isAutoRotate = z;
    }

    public final void setIntentData(Activity activity, Intent intent) {
        String str;
        Article article;
        Object obj;
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (!(extras == null || (obj = extras.get("article")) == null)) {
                if (obj != null) {
                    this.mArticle = (Article) obj;
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.parser.model.Article");
                }
            }
            String stringExtra = intent.getStringExtra(WebviewUtil.LINK_EXTRA);
            String str2 = "";
            if (stringExtra == null) {
                stringExtra = str2;
            }
            this.link = stringExtra;
            this.link = Util.attachHttpsToUrl(stringExtra);
            String stringExtra2 = intent.getStringExtra(WebviewUtil.TITLE_EXTRA);
            if (stringExtra2 == null) {
                stringExtra2 = str2;
            }
            this.title = stringExtra2;
            this.logoType = intent.getIntExtra(WebviewUtil.LOGO_EXTRA, -1);
            boolean z = false;
            this.isShareable = intent.getBooleanExtra(WebviewUtil.SHAREABLE_EXTRA, false);
            this.isSavable = intent.getBooleanExtra(WebviewUtil.SAVEABLE_EXTRA, false);
            this.saveType = intent.getIntExtra(WebviewUtil.SAVETYPE_EXTRA, Cache.NEWS);
            this.actionBarTitleMode = intent.getIntExtra(WebviewUtil.ACTIONBAR_TITLE_EXTRA, 0);
            String stringExtra3 = intent.getStringExtra(WebviewUtil.DATE_EXTRA);
            if (stringExtra3 == null) {
                stringExtra3 = str2;
            }
            this.date = stringExtra3;
            String stringExtra4 = intent.getStringExtra(WebviewUtil.IMAGE_URL_EXTRA);
            if (stringExtra4 == null) {
                stringExtra4 = str2;
            }
            this.imageUrl = stringExtra4;
            String stringExtra5 = intent.getStringExtra(WebviewUtil.BYLINE_TXT_EXTRA);
            if (stringExtra5 != null) {
                str2 = stringExtra5;
            }
            this.byline = str2;
            this.isAutoRotate = intent.getBooleanExtra(WebviewUtil.WITH_AUTO_ROTATE, false);
            Context context = activity;
            if (new NavItemManager().shouldEnableFeature(FeatureConfigManager.Companion.getReferenceConfig(), Constants.ADS_BANNER_NEWS_LABEL, context)) {
                this.withAds = intent.getBooleanExtra(WebviewUtil.WITH_ADS_EXTRA, false);
            }
            this.canSendEvent = this.saveType == Cache.NEWS && this.withAds;
            CharSequence charSequence = this.link;
            if ((charSequence == null || charSequence.length() == 0) && (article = this.mArticle) != null) {
                this.link = article.mLink;
                CharSequence charSequence2 = this.title;
                if (charSequence2 == null || charSequence2.length() == 0) {
                    this.title = article.mTitle;
                }
            }
            CharSequence stringExtra6 = intent.getStringExtra("wapp.mlink");
            if (!(stringExtra6 == null || stringExtra6.length() == 0)) {
                CharSequence stringExtra7 = intent.getStringExtra("wapp.mmodule");
                if (stringExtra7 == null || stringExtra7.length() == 0) {
                    z = true;
                }
                if (z) {
                    str = "webview-launch";
                } else {
                    str = intent.getStringExtra("wapp.mmodule");
                }
                OmnitureManager.get().trackModule(context, intent.getStringExtra("wapp.chn"), str, intent.getStringExtra("wapp.mlink"), (Map<String, Object>) null);
            }
        }
    }

    public final boolean isContentSaved(Context context) {
        Cache cache;
        Intrinsics.checkNotNullParameter(context, "context");
        if (this.link == null || (cache = new CacheManager(context).getCache(cleanUrl(this.link))) == null || !cache.isSaved()) {
            return false;
        }
        return true;
    }

    public final Boolean saveContent(Context context, WebView webView) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.title;
        CharSequence charSequence = str;
        boolean z = false;
        boolean z2 = true;
        if ((charSequence == null || charSequence.length() == 0) && webView != null) {
            str = webView.getTitle();
        }
        CharSequence charSequence2 = str;
        if (charSequence2 == null || charSequence2.length() == 0) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("isSaved", 1);
        String[] strArr = {cleanUrl(this.link)};
        CacheManager cacheManager = new CacheManager(context);
        boolean updateCache = cacheManager.updateCache(contentValues, "url = ?", strArr);
        if (!updateCache) {
            Cache cache = new Cache();
            cache.setUrl(cleanUrl(this.link));
            cache.setType(this.saveType);
            CharSequence charSequence3 = this.date;
            if (!(charSequence3 == null || charSequence3.length() == 0)) {
                cache.setTime(this.date);
            }
            CharSequence charSequence4 = this.imageUrl;
            if (!(charSequence4 == null || charSequence4.length() == 0)) {
                cache.setImageUrl(this.imageUrl);
            }
            CharSequence charSequence5 = this.byline;
            if (charSequence5 == null || charSequence5.length() == 0) {
                z = true;
            }
            if (!z) {
                cache.setByline(this.byline);
            }
            cache.setTitle(str);
            cache.setSaved(true);
            cacheManager.addCache(cache);
        } else {
            z2 = updateCache;
        }
        return Boolean.valueOf(z2);
    }

    private final boolean unSaveContent(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("isSaved", 0);
        return new CacheManager(context).updateCache(contentValues, "url = ?", new String[]{cleanUrl(this.link)});
    }

    public final void onClickSaveIcon(Context context, WebView webView, Activity activity) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.link == null) {
            return;
        }
        if (isContentSaved(context)) {
            unSaveContent(context);
            OmnitureManager.get().trackModule(context, getOmnitureSaveChannel(), "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
            return;
        }
        Boolean saveContent = saveContent(context, webView);
        if (saveContent != null) {
            saveContent.booleanValue();
            if (saveContent.booleanValue()) {
                AppboyEventsHandler.logDailyEvent(context, getAppBoyEvent(), activity);
                Toast.makeText(context, getSavedMessage(context), 0).show();
                OmnitureManager.get().trackModule(context, getOmnitureSaveChannel(), "save", getOmnitureSaveLink(), (Map<String, Object>) null);
            }
        }
    }

    public final String cleanUrl(String str) {
        String str2;
        if (str == null) {
            str2 = null;
        } else if (str != null) {
            str2 = StringsKt.trim((CharSequence) str).toString();
        } else {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        if (str2 == null) {
            return str2;
        }
        CharSequence charSequence = str2;
        int indexOf$default = StringsKt.contains$default(charSequence, (CharSequence) TypeDescription.Generic.OfWildcardType.SYMBOL, false, 2, (Object) null) ? StringsKt.indexOf$default(charSequence, TypeDescription.Generic.OfWildcardType.SYMBOL, 0, false, 6, (Object) null) : str2.length();
        if (str2 != null) {
            String substring = str2.substring(0, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    public final String getOmnitureSaveChannel() {
        int i = this.saveType;
        if (i != Cache.NEWS && i == Cache.CME) {
            return "education";
        }
        return "news";
    }

    public final String getOmnitureSaveLink() {
        int i = this.saveType;
        if (i != Cache.NEWS && i == Cache.CME) {
            return FeedConstants.CME_ITEM;
        }
        return "news";
    }

    public final String getAppBoyEvent() {
        int i = this.saveType;
        if (i != Cache.NEWS && i == Cache.CME) {
            return AppboyConstants.APPBOY_EVENT_CME_SAVED;
        }
        return AppboyConstants.APPBOY_EVENT_NEWS_SAVED;
    }

    public final String getSavedMessage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i = this.saveType;
        if (i == Cache.NEWS) {
            String string = context.getString(R.string.news_article_saved);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.news_article_saved)");
            return string;
        } else if (i == Cache.CME) {
            String string2 = context.getString(R.string.education_acticle_saved);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.stri….education_acticle_saved)");
            return string2;
        } else {
            String string3 = context.getString(R.string.article_saved);
            Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.article_saved)");
            return string3;
        }
    }

    public final void makeADCall(DFPAdAction dFPAdAction) {
        Intrinsics.checkNotNullParameter(dFPAdAction, "adAction");
        dFPAdAction.makeADRequestWithoutBidding(this.screenSpecificMap);
    }

    public final void setActionBarTitle(Context context, ActionBar actionBar, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (charSequence == null) {
            return;
        }
        if (charSequence.length() > 28) {
            SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.dimen.action_bar_small_text_size)), 0, charSequence.length(), 18);
            if (actionBar != null) {
                actionBar.setTitle((CharSequence) spannableString);
            }
        } else if (actionBar != null) {
            actionBar.setTitle(charSequence);
        }
    }
}
