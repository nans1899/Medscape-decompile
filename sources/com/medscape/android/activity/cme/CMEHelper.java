package com.medscape.android.activity.cme;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.medscape.android.activity.cme.views.MedscapeCMEInfoActivity;
import com.medscape.android.activity.cme.views.MedscapeCMETrackerActivity;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.cache.CacheBroadCastReceiver;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.parser.model.Article;
import com.medscape.android.parser.model.MetricsUserProfile;
import com.medscape.android.util.StringUtil;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdproffesionalauthentication.model.BasicInfo;
import com.webmd.wbmdproffesionalauthentication.model.UserProfession;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.net.URI;
import java.net.URISyntaxException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J,\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000fH\u0007J\u001a\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007J\u0012\u0010\u0012\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0007J\u001a\u0010\u0013\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u0014\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\t¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/activity/cme/CMEHelper;", "", "()V", "getArticleIDFromLink", "", "articleLink", "getCommonUserProfile", "Lcom/webmd/wbmdproffesionalauthentication/model/UserProfile;", "context", "Landroid/content/Context;", "launchCMEArticle", "", "article", "Lcom/medscape/android/parser/model/Article;", "isFromPush", "", "isFromSaved", "launchCMETracker", "registerSaveReceiver", "saveToRecentlyViewed", "unRegisterSaveReceiver", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CMEHelper.kt */
public final class CMEHelper {
    public static final CMEHelper INSTANCE = new CMEHelper();

    private CMEHelper() {
    }

    private final UserProfile getCommonUserProfile(Context context) {
        UserProfile userProfile = new UserProfile();
        UserProfession professionProfile = userProfile.getProfessionProfile();
        BasicInfo basicProfile = userProfile.getBasicProfile();
        MetricsUserProfile metricsUserProfile = UserProfileProvider.INSTANCE.getMetricsUserProfile(context);
        if (metricsUserProfile != null) {
            professionProfile.setAbimId(metricsUserProfile.getAbimID());
            Intrinsics.checkNotNullExpressionValue(professionProfile, "profession");
            professionProfile.setOccupationId(metricsUserProfile.getOccupationID());
            professionProfile.setOccupation(metricsUserProfile.getOccupation());
            professionProfile.setSpecialityId(metricsUserProfile.getSpecialtyID());
            professionProfile.setSpeciality(metricsUserProfile.getSpecialty());
            professionProfile.setProfessionId(metricsUserProfile.getProfessionID());
            professionProfile.setProfession(metricsUserProfile.getProfession());
            String registeredId = metricsUserProfile.getRegisteredId();
            try {
                Intrinsics.checkNotNullExpressionValue(registeredId, "uid");
                long parseLong = Long.parseLong(registeredId);
                registeredId = "" + (parseLong * ((long) 27));
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intrinsics.checkNotNullExpressionValue(basicProfile, "basicInfo");
            basicProfile.setUserId(registeredId);
        }
        return userProfile;
    }

    public static /* synthetic */ void launchCMEArticle$default(Context context, Article article, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        launchCMEArticle(context, article, z, z2);
    }

    @JvmStatic
    public static final void launchCMEArticle(Context context, Article article, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(article, "article");
        registerSaveReceiver(context);
        String str = article.mArticleId;
        String str2 = article.mLink;
        if (!StringUtil.isNullOrEmpty(str2)) {
            WebviewUtil.Companion companion = WebviewUtil.Companion;
            Intrinsics.checkNotNullExpressionValue(str2, "articleLink");
            str2 = companion.insertEnvironment(context, str2);
        }
        if (StringUtil.isNullOrEmpty(str2) && StringUtil.isNotEmpty(str)) {
            str2 = Utilities.getCMEUrl(context, str);
        }
        if (StringUtil.isNullOrEmpty(str)) {
            CMEHelper cMEHelper = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(str2, "articleLink");
            str = cMEHelper.getArticleIDFromLink(str2);
        }
        Intent intent = new Intent(context, MedscapeCMEInfoActivity.class);
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, str);
        intent.putExtra(Constants.BUNDLE_KEY_FEED_URL, str2);
        intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, article.mTitle);
        intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, INSTANCE.getCommonUserProfile(context));
        intent.putExtra(com.medscape.android.Constants.EXTRA_RECENTLY_VIEWED_ARTICLE, article);
        if (z) {
            intent.putExtra(Constants.BUNDLE_KEY_REFERRING_LINK, com.medscape.android.Constants.OMNITURE_MLINK_OPEN);
            Intrinsics.checkNotNullExpressionValue(intent.putExtra(Constants.BUNDLE_KEY_REFERRING_MODULE, "push"), "intent.putExtra(Constant…REFERRING_MODULE, \"push\")");
        } else if (z2) {
            intent.putExtra(Constants.BUNDLE_KEY_REFERRING_LINK, com.medscape.android.Constants.OMNITURE_MLINK_OPEN);
            intent.putExtra(Constants.BUNDLE_KEY_REFERRING_MODULE, "save");
        }
        context.startActivity(intent);
    }

    public static /* synthetic */ void launchCMETracker$default(Context context, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        launchCMETracker(context, z);
    }

    @JvmStatic
    public static final void launchCMETracker(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        registerSaveReceiver(context);
        Intent intent = new Intent(context, MedscapeCMETrackerActivity.class);
        intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, INSTANCE.getCommonUserProfile(context));
        if (z) {
            intent.putExtra(Constants.BUNDLE_KEY_REFERRING_LINK, com.medscape.android.Constants.OMNITURE_MLINK_OPEN);
            intent.putExtra(Constants.BUNDLE_KEY_REFERRING_MODULE, "push");
        }
        context.startActivity(intent);
    }

    private final String getArticleIDFromLink(String str) {
        String str2 = null;
        if (!StringUtil.isNotEmpty(str)) {
            return str2;
        }
        try {
            String path = new URI(str).getPath();
            Intrinsics.checkNotNullExpressionValue(path, "articlePath");
            int lastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) path, '/', 0, false, 6, (Object) null) + 1;
            if (path != null) {
                String substring = path.substring(lastIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.String).substring(startIndex)");
                return substring;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public final void saveToRecentlyViewed(Context context, Article article) {
        if (article != null && StringUtil.isNotEmpty(article.mTitle)) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("article", article);
            bundle.putString("type", "education");
            RecentlyViewedSuggestionHelper.addToRecentlyViewed(context, article.mTitle, bundle);
        }
    }

    @JvmStatic
    public static final void registerSaveReceiver(Context context) {
        try {
            Intrinsics.checkNotNull(context);
            LocalBroadcastManager.getInstance(context).registerReceiver(new CacheBroadCastReceiver(), new IntentFilter(Constants.CONTENT_SAVE_UNSAVE_ACTION));
            LocalBroadcastManager.getInstance(context).registerReceiver(new CacheBroadCastReceiver(), new IntentFilter(Constants.CONTENT_CHECK_SAVE));
        } catch (Throwable unused) {
        }
    }

    public final void unRegisterSaveReceiver(Context context) {
        try {
            Intrinsics.checkNotNull(context);
            LocalBroadcastManager.getInstance(context).unregisterReceiver(new CacheBroadCastReceiver());
        } catch (Throwable unused) {
        }
    }
}
