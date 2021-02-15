package com.medscape.android.landingfeed.viewmodel;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.view.View;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.ads.AdsConstants;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.ads.bidding.AdBidder;
import com.medscape.android.analytics.remoteconfig.AdConfigManager;
import com.medscape.android.analytics.remoteconfig.AdConfigParser;
import com.medscape.android.analytics.remoteconfig.RemoteConfig;
import com.medscape.android.analytics.remoteconfig.UrlConfigManager;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.cache.Cache;
import com.medscape.android.cache.CacheManager;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.FeedMaster;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.landingfeed.api.LandingFeedApi;
import com.medscape.android.landingfeed.model.FeedAdItem;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.landingfeed.model.FeedListing;
import com.medscape.android.landingfeed.repository.FeedDataSourceFactory;
import com.medscape.android.landingfeed.repository.NetworkState;
import com.medscape.android.landingfeed.repository.Status;
import com.medscape.android.landingfeed.views.FeedPagedListAdapter;
import com.medscape.android.myinvites.MyInvitationsManager;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.wbmdcommons.receivers.ShareDataObservable;
import com.wbmd.wbmdcommons.receivers.ShareReceiver;
import com.webmd.wbmdcmepulse.live_events.api.LiveEventsRepository;
import com.webmd.wbmdcmepulse.live_events.util.LiveEventsCacheManager;
import com.webmd.wbmdcmepulse.live_events.util.LiveEventsLoadFinish;
import com.webmd.wbmdomnituremanager.WBMDOmniturePaginationHandler;
import com.webmd.wbmdomnituremanager.WBMDPaginationListener;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import com.webmd.webmdrx.util.NetworkUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.bytebuddy.description.type.TypeDescription;
import org.jetbrains.anko.AsyncKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020\rH\u0002J\u0016\u0010[\u001a\u00020W2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_J\u0014\u0010`\u001a\u0004\u0018\u00010\r2\b\u0010a\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010b\u001a\u00020W2\u0006\u0010\\\u001a\u00020cH\u0002J\u0018\u0010d\u001a\u00020W2\u0006\u0010X\u001a\u00020Y2\u0006\u0010e\u001a\u00020fH\u0002J\b\u0010g\u001a\u00020\rH\u0002J\u0006\u0010h\u001a\u00020\rJ\b\u0010i\u001a\u00020\rH\u0002J\u0010\u0010j\u001a\u00020\r2\b\u0010X\u001a\u0004\u0018\u00010YJ\u0010\u0010k\u001a\u00020\r2\b\u0010X\u001a\u0004\u0018\u00010YJ\u001c\u0010l\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0m2\b\u0010a\u001a\u0004\u0018\u00010\rJ\b\u0010n\u001a\u00020\rH\u0002J\u0010\u0010o\u001a\u00020\r2\u0006\u0010p\u001a\u00020\u001cH\u0003J\u000e\u0010q\u001a\u00020\r2\u0006\u0010r\u001a\u00020\u001cJ\u0006\u0010s\u001a\u00020\rJ\n\u0010t\u001a\u0004\u0018\u00010\rH\u0003J\b\u0010u\u001a\u00020)H\u0002J\u0018\u0010v\u001a\u00020\r2\u0006\u0010p\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020YH\u0002J\u0016\u0010w\u001a\u00020W2\u000e\u0010x\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001bJ\u0018\u0010y\u001a\u00020/2\u0006\u0010p\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020YH\u0007J\u001a\u0010z\u001a\u00020/2\b\u0010a\u001a\u0004\u0018\u00010\r2\u0006\u0010X\u001a\u00020YH\u0002J\b\u0010{\u001a\u00020/H\u0002J \u0010|\u001a\u00020W2\u0006\u0010r\u001a\u00020\u001c2\u0006\u0010}\u001a\u00020c2\u0006\u0010~\u001a\u00020)H\u0007J\u0017\u0010\u001a\u00020W2\u0006\u0010\\\u001a\u00020c2\u0007\u0010\u0001\u001a\u00020)J\u000f\u0010\u0001\u001a\u00020W2\u0006\u0010\\\u001a\u00020cJ\u001a\u0010\u0001\u001a\u00020W2\u0006\u0010\\\u001a\u00020c2\t\b\u0002\u0010\u0001\u001a\u00020/J\u0007\u0010\u0001\u001a\u00020WJ\u0011\u0010\u0001\u001a\u00020/2\b\u0010\\\u001a\u0004\u0018\u00010cJ\u0007\u0010\u0001\u001a\u00020WJ\u0010\u0010\u0001\u001a\u00020W2\u0007\u0010\u0001\u001a\u00020/J\u0019\u0010\u0001\u001a\u00020W2\u0006\u0010p\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020YH\u0002J#\u0010\u0001\u001a\u00020W2\b\u0010a\u001a\u0004\u0018\u00010\r2\u0006\u0010p\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020YH\u0002J\u0019\u0010\u0001\u001a\u00020W2\u0006\u0010X\u001a\u00020Y2\b\u0010\u0001\u001a\u00030\u0001J\u0007\u0010\u0001\u001a\u00020WJ\u0007\u0010\u0001\u001a\u00020WJ\u0011\u0010\u0001\u001a\u00020W2\u0006\u0010\\\u001a\u00020cH\u0002J\"\u0010\u0001\u001a\u00020W2\u0006\u0010p\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020c2\u0007\u0010\u0001\u001a\u00020)H\u0007J\u0019\u0010\u0001\u001a\u00020/2\u0006\u0010p\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020YH\u0002J*\u0010\u0001\u001a\u00020/2\u0006\u0010a\u001a\u00020\r2\u0006\u0010p\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020Y2\u0007\u0010\u0001\u001a\u00020)H\u0002J\u0018\u0010\u0001\u001a\u00020W2\u0006\u0010X\u001a\u00020c2\u0007\u0010\u0001\u001a\u00020)J\u0018\u0010\u0001\u001a\u00020W2\u0006\u0010X\u001a\u00020c2\u0007\u0010\u0001\u001a\u00020)J\u000f\u0010\u0001\u001a\u00020W2\u0006\u0010X\u001a\u00020]J\u0011\u0010\u0001\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010YJ\u0017\u0010\u0001\u001a\u00020W2\u0006\u0010p\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020cJ\u000f\u0010\u0001\u001a\u00020W2\u0006\u0010\\\u001a\u00020cJ!\u0010\u0001\u001a\u00020W2\n\u0010\u0001\u001a\u0005\u0018\u00010\u00012\n\u0010 \u0001\u001a\u0005\u0018\u00010¡\u0001H\u0016J%\u0010¢\u0001\u001a\u00020W2\b\u0010H\u001a\u0004\u0018\u00010I2\b\u0010\\\u001a\u0004\u0018\u00010c2\b\u0010£\u0001\u001a\u00030¤\u0001J\u0017\u0010¥\u0001\u001a\u00020W2\u0006\u0010p\u001a\u00020\u001c2\u0006\u0010X\u001a\u00020YR\u001c\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0003R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R&\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u001b0\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R&\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0#0\"X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u000e\u00104\u001a\u00020/X\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020/X\u000e¢\u0006\u0002\n\u0000R\u001a\u00106\u001a\u00020/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00101\"\u0004\b8\u00103R\u001a\u00109\u001a\u00020/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u00101\"\u0004\b;\u00103R\u0010\u0010<\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u0004\u0018\u00010>X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010?\u001a\u00020)X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010+\"\u0004\bA\u0010-R\u001c\u0010B\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\u0010\"\u0004\bD\u0010\u0012R\u001a\u0010E\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0010\"\u0004\bG\u0010\u0012R \u0010H\u001a\b\u0012\u0004\u0012\u00020I0\u001aX.¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u001e\"\u0004\bK\u0010 R\u001a\u0010L\u001a\u00020MX.¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR \u0010R\u001a\b\u0012\u0004\u0012\u00020I0\u001aX.¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u001e\"\u0004\bT\u0010 R\u000e\u0010U\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000¨\u0006¦\u0001"}, d2 = {"Lcom/medscape/android/landingfeed/viewmodel/LandingFeedViewModel;", "Landroidx/lifecycle/ViewModel;", "Ljava/util/Observer;", "()V", "NETWORK_IO", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "getNETWORK_IO$annotations", "adBidder", "Lcom/medscape/android/ads/bidding/AdBidder;", "adConfigManager", "Lcom/medscape/android/analytics/remoteconfig/AdConfigManager;", "currentSpecialityID", "", "currentSpecialityName", "getCurrentSpecialityName", "()Ljava/lang/String;", "setCurrentSpecialityName", "(Ljava/lang/String;)V", "feedAdapter", "Lcom/medscape/android/landingfeed/views/FeedPagedListAdapter;", "getFeedAdapter", "()Lcom/medscape/android/landingfeed/views/FeedPagedListAdapter;", "setFeedAdapter", "(Lcom/medscape/android/landingfeed/views/FeedPagedListAdapter;)V", "feedPostItems", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "getFeedPostItems", "()Landroidx/lifecycle/LiveData;", "setFeedPostItems", "(Landroidx/lifecycle/LiveData;)V", "feedResult", "Landroidx/lifecycle/MutableLiveData;", "Lcom/medscape/android/landingfeed/model/FeedListing;", "getFeedResult", "()Landroidx/lifecycle/MutableLiveData;", "setFeedResult", "(Landroidx/lifecycle/MutableLiveData;)V", "feedType", "", "getFeedType", "()I", "setFeedType", "(I)V", "ignoreFirstError", "", "getIgnoreFirstError", "()Z", "setIgnoreFirstError", "(Z)V", "isFeedFailed", "isLoginDone", "itemsLoaded", "getItemsLoaded", "setItemsLoaded", "liveEventsLoaded", "getLiveEventsLoaded", "setLiveEventsLoaded", "mFeedDataItem", "mNoNetworkException", "Lcom/medscape/android/util/MedscapeException;", "mNumberOfAds", "getMNumberOfAds", "setMNumberOfAds", "mPayload", "getMPayload", "setMPayload", "mPvid", "getMPvid", "setMPvid", "networkState", "Lcom/medscape/android/landingfeed/repository/NetworkState;", "getNetworkState", "setNetworkState", "onLiveEventsFinished", "Lcom/webmd/wbmdcmepulse/live_events/util/LiveEventsLoadFinish;", "getOnLiveEventsFinished", "()Lcom/webmd/wbmdcmepulse/live_events/util/LiveEventsLoadFinish;", "setOnLiveEventsFinished", "(Lcom/webmd/wbmdcmepulse/live_events/util/LiveEventsLoadFinish;)V", "refreshState", "getRefreshState", "setRefreshState", "selectedItem", "addOmnitureCallForSharing", "", "context", "Landroid/content/Context;", "link", "attachOmniturePaginationHandler", "activity", "Landroid/app/Activity;", "contentView", "Landroidx/recyclerview/widget/RecyclerView;", "cleanUrl", "url", "createPagedList", "Landroidx/fragment/app/FragmentActivity;", "getAD", "nativeAdAction", "Lcom/medscape/android/ads/NativeAdAction;", "getAdPclass", "getApiFeedType", "getChannel", "getCurrentSpeciality", "getCurrentSpecialityIDwithFallBack", "getHostAndPath", "Lkotlin/Pair;", "getLastPausePrefName", "getOmnitureChannel", "data", "getOmnitureLink", "selectedData", "getOmniturePage", "getOmnitureSection", "getSlideDemoMode", "getSubjectForShareEmail", "initPreloadAds", "it", "isReferenceContentSaved", "isRssContentSaved", "isSlideDemoModeOn", "launchFeedItem", "mContext", "adapterPosition", "loadFeed", "type", "loadLiveEvents", "loadSpecificInvitations", "forced", "onPause", "onResume", "refresh", "refreshFailedFeed", "isFromAuth", "removeReferenceInfo", "removeRssInfo", "requestAd", "dfpAdLoadListener", "Lcom/medscape/android/ads/INativeDFPAdLoadListener;", "resetLastPause", "retry", "retryWAuth", "saveFeedItem", "position", "saveReferenceInfo", "saveRssInfo", "mType", "sendNativeAdClickedImpression", "pgValue", "sendNativeAdViewedImpression", "sendScreenViewPing", "setCurrentSpeciality", "shareFeedItem", "startWithAuthCheck", "update", "observable", "Ljava/util/Observable;", "arg", "", "updateNetworkErrorState", "view", "Landroid/view/View;", "updateSavedState", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LandingFeedViewModel.kt */
public final class LandingFeedViewModel extends ViewModel implements Observer {
    private final ExecutorService NETWORK_IO = Executors.newFixedThreadPool(5);
    private final AdBidder adBidder = new AdBidder();
    private AdConfigManager adConfigManager = new AdConfigManager((RemoteConfig) null, (AdConfigParser) null, 3, (DefaultConstructorMarker) null);
    private String currentSpecialityID = "";
    private String currentSpecialityName = "";
    public FeedPagedListAdapter feedAdapter;
    private LiveData<PagedList<FeedDataItem>> feedPostItems = new MutableLiveData();
    private MutableLiveData<FeedListing<FeedDataItem>> feedResult = new MutableLiveData<>();
    private int feedType = -1;
    private boolean ignoreFirstError;
    private boolean isFeedFailed;
    private boolean isLoginDone;
    private boolean itemsLoaded;
    private boolean liveEventsLoaded;
    private FeedDataItem mFeedDataItem;
    /* access modifiers changed from: private */
    public MedscapeException mNoNetworkException;
    private int mNumberOfAds;
    private String mPayload;
    private String mPvid = "";
    public LiveData<NetworkState> networkState;
    public LiveEventsLoadFinish onLiveEventsFinished;
    public LiveData<NetworkState> refreshState;
    private int selectedItem = -1;

    private static /* synthetic */ void getNETWORK_IO$annotations() {
    }

    public final MutableLiveData<FeedListing<FeedDataItem>> getFeedResult() {
        return this.feedResult;
    }

    public final void setFeedResult(MutableLiveData<FeedListing<FeedDataItem>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.feedResult = mutableLiveData;
    }

    public final LiveData<PagedList<FeedDataItem>> getFeedPostItems() {
        return this.feedPostItems;
    }

    public final void setFeedPostItems(LiveData<PagedList<FeedDataItem>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.feedPostItems = liveData;
    }

    public final LiveData<NetworkState> getNetworkState() {
        LiveData<NetworkState> liveData = this.networkState;
        if (liveData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkState");
        }
        return liveData;
    }

    public final void setNetworkState(LiveData<NetworkState> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.networkState = liveData;
    }

    public final LiveData<NetworkState> getRefreshState() {
        LiveData<NetworkState> liveData = this.refreshState;
        if (liveData == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshState");
        }
        return liveData;
    }

    public final void setRefreshState(LiveData<NetworkState> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.refreshState = liveData;
    }

    public final int getFeedType() {
        return this.feedType;
    }

    public final void setFeedType(int i) {
        this.feedType = i;
    }

    public final FeedPagedListAdapter getFeedAdapter() {
        FeedPagedListAdapter feedPagedListAdapter = this.feedAdapter;
        if (feedPagedListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("feedAdapter");
        }
        return feedPagedListAdapter;
    }

    public final void setFeedAdapter(FeedPagedListAdapter feedPagedListAdapter) {
        Intrinsics.checkNotNullParameter(feedPagedListAdapter, "<set-?>");
        this.feedAdapter = feedPagedListAdapter;
    }

    public final String getMPvid() {
        return this.mPvid;
    }

    public final void setMPvid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPvid = str;
    }

    public final String getCurrentSpecialityName() {
        return this.currentSpecialityName;
    }

    public final void setCurrentSpecialityName(String str) {
        this.currentSpecialityName = str;
    }

    public final boolean getItemsLoaded() {
        return this.itemsLoaded;
    }

    public final void setItemsLoaded(boolean z) {
        this.itemsLoaded = z;
    }

    public final boolean getIgnoreFirstError() {
        return this.ignoreFirstError;
    }

    public final void setIgnoreFirstError(boolean z) {
        this.ignoreFirstError = z;
    }

    public final String getMPayload() {
        return this.mPayload;
    }

    public final void setMPayload(String str) {
        this.mPayload = str;
    }

    public final int getMNumberOfAds() {
        return this.mNumberOfAds;
    }

    public final void setMNumberOfAds(int i) {
        this.mNumberOfAds = i;
    }

    public final LiveEventsLoadFinish getOnLiveEventsFinished() {
        LiveEventsLoadFinish liveEventsLoadFinish = this.onLiveEventsFinished;
        if (liveEventsLoadFinish == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onLiveEventsFinished");
        }
        return liveEventsLoadFinish;
    }

    public final void setOnLiveEventsFinished(LiveEventsLoadFinish liveEventsLoadFinish) {
        Intrinsics.checkNotNullParameter(liveEventsLoadFinish, "<set-?>");
        this.onLiveEventsFinished = liveEventsLoadFinish;
    }

    public final boolean getLiveEventsLoaded() {
        return this.liveEventsLoaded;
    }

    public final void setLiveEventsLoaded(boolean z) {
        this.liveEventsLoaded = z;
    }

    public final void loadFeed(FragmentActivity fragmentActivity, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        loadSpecificInvitations$default(this, fragmentActivity, false, 2, (Object) null);
        MedscapeException medscapeException = this.mNoNetworkException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
        this.feedType = i;
        createPagedList(fragmentActivity);
        LiveData<NetworkState> switchMap = Transformations.switchMap(this.feedResult, LandingFeedViewModel$loadFeed$1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(switchMap, "Transformations.switchMa…sult) { it.networkState }");
        this.networkState = switchMap;
        LiveData<NetworkState> switchMap2 = Transformations.switchMap(this.feedResult, LandingFeedViewModel$loadFeed$2.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(switchMap2, "Transformations.switchMa…sult) { it.refreshState }");
        this.refreshState = switchMap2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001e A[Catch:{ Exception -> 0x004a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlin.Pair<java.lang.String, java.lang.String> getHostAndPath(java.lang.String r10) {
        /*
            r9 = this;
            java.lang.String r0 = "/contentrecommendationservice/contentrecommendations"
            java.lang.String r1 = "https://api.medscape.com/"
            java.net.URI r2 = new java.net.URI     // Catch:{ Exception -> 0x004a }
            r2.<init>(r10)     // Catch:{ Exception -> 0x004a }
            java.lang.String r2 = r2.getPath()     // Catch:{ Exception -> 0x004a }
            r3 = r2
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3     // Catch:{ Exception -> 0x004a }
            if (r3 == 0) goto L_0x001b
            int r3 = r3.length()     // Catch:{ Exception -> 0x004a }
            if (r3 != 0) goto L_0x0019
            goto L_0x001b
        L_0x0019:
            r3 = 0
            goto L_0x001c
        L_0x001b:
            r3 = 1
        L_0x001c:
            if (r3 != 0) goto L_0x0044
            java.lang.String r3 = "/"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)     // Catch:{ Exception -> 0x004a }
            if (r3 == 0) goto L_0x0027
            goto L_0x0044
        L_0x0027:
            if (r10 == 0) goto L_0x0035
            java.lang.String r5 = "/"
            r6 = 0
            r7 = 4
            r8 = 0
            r3 = r10
            r4 = r2
            java.lang.String r10 = kotlin.text.StringsKt.replace$default((java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5, (boolean) r6, (int) r7, (java.lang.Object) r8)     // Catch:{ Exception -> 0x004a }
            goto L_0x0036
        L_0x0035:
            r10 = 0
        L_0x0036:
            if (r10 != 0) goto L_0x003e
            kotlin.Pair r10 = new kotlin.Pair     // Catch:{ Exception -> 0x004a }
            r10.<init>(r1, r0)     // Catch:{ Exception -> 0x004a }
            goto L_0x0049
        L_0x003e:
            kotlin.Pair r3 = new kotlin.Pair     // Catch:{ Exception -> 0x004a }
            r3.<init>(r10, r2)     // Catch:{ Exception -> 0x004a }
            return r3
        L_0x0044:
            kotlin.Pair r10 = new kotlin.Pair     // Catch:{ Exception -> 0x004a }
            r10.<init>(r1, r0)     // Catch:{ Exception -> 0x004a }
        L_0x0049:
            return r10
        L_0x004a:
            kotlin.Pair r10 = new kotlin.Pair
            r10.<init>(r1, r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel.getHostAndPath(java.lang.String):kotlin.Pair");
    }

    private final void createPagedList(FragmentActivity fragmentActivity) {
        String str = "";
        new Pair(str, str);
        int i = this.feedType;
        if (i == 1) {
            str = Constants.REMOTE_URL_HOME;
        } else if (i == 2) {
            str = Constants.REMOTE_URL_NEWS;
        } else if (i == 3) {
            str = Constants.REMOTE_URL_CME;
        }
        Pair<String, String> hostAndPath = getHostAndPath(UrlConfigManager.Companion.getUrl(str));
        LandingFeedApi create = LandingFeedApi.Companion.create(hostAndPath.getFirst());
        ExecutorService executorService = this.NETWORK_IO;
        Intrinsics.checkNotNullExpressionValue(executorService, "NETWORK_IO");
        FeedDataSourceFactory feedDataSourceFactory = new FeedDataSourceFactory(create, executorService, this.adConfigManager.getAdConfigData(), fragmentActivity, hostAndPath.getSecond());
        LiveData<PagedList<FeedDataItem>> build = new LivePagedListBuilder(feedDataSourceFactory, 10).build();
        Intrinsics.checkNotNullExpressionValue(build, "LivePagedListBuilder(factory, 10).build()");
        this.feedPostItems = build;
        LiveData switchMap = Transformations.switchMap(feedDataSourceFactory.getSourceLiveData(), LandingFeedViewModel$createPagedList$refreshState$1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(switchMap, "Transformations.switchMa… it.initialLoad\n        }");
        MutableLiveData<FeedListing<FeedDataItem>> mutableLiveData = this.feedResult;
        LiveData<PagedList<FeedDataItem>> liveData = this.feedPostItems;
        LiveData switchMap2 = Transformations.switchMap(feedDataSourceFactory.getSourceLiveData(), LandingFeedViewModel$createPagedList$1.INSTANCE);
        Intrinsics.checkNotNullExpressionValue(switchMap2, "Transformations.switchMa…rkState\n                }");
        mutableLiveData.setValue(new FeedListing(liveData, switchMap2, switchMap, new LandingFeedViewModel$createPagedList$3(feedDataSourceFactory), new LandingFeedViewModel$createPagedList$2(feedDataSourceFactory)));
    }

    public final void refresh() {
        Function0<Unit> refresh;
        MedscapeException medscapeException = this.mNoNetworkException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
        FeedListing value = this.feedResult.getValue();
        if (value != null && (refresh = value.getRefresh()) != null) {
            Unit invoke = refresh.invoke();
        }
    }

    /* access modifiers changed from: private */
    public final void retryWAuth(FragmentActivity fragmentActivity) {
        AuthenticationManager instance = AuthenticationManager.getInstance(fragmentActivity);
        Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.getInstance(activity)");
        if (instance.getAuthStatus() == 3011) {
            retry();
        } else {
            startWithAuthCheck(fragmentActivity);
        }
    }

    public final void retry() {
        Function0<Unit> retry;
        FeedListing value = this.feedResult.getValue();
        if (value != null && (retry = value.getRetry()) != null) {
            Unit invoke = retry.invoke();
        }
    }

    public final void loadLiveEvents(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Context context = fragmentActivity;
        if (NetworkUtil.isOnline(context)) {
            setCurrentSpeciality(context);
            this.liveEventsLoaded = false;
            new LiveEventsCacheManager().clearLiveEvents();
            Object requireNonNull = Objects.requireNonNull(fragmentActivity);
            Intrinsics.checkNotNullExpressionValue(requireNonNull, "Objects.requireNonNull(activity)");
            LiveEventsRepository liveEventsRepository = new LiveEventsRepository((FragmentActivity) requireNonNull, this.currentSpecialityID);
            LiveEventsLoadFinish liveEventsLoadFinish = this.onLiveEventsFinished;
            if (liveEventsLoadFinish == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onLiveEventsFinished");
            }
            liveEventsRepository.getLiveEvents(liveEventsLoadFinish);
        }
    }

    public final void requestAd(Context context, INativeDFPAdLoadListener iNativeDFPAdLoadListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(iNativeDFPAdLoadListener, "dfpAdLoadListener");
        NativeAdAction nativeAdAction = new NativeAdAction(context, DFPReferenceAdListener.AD_UNIT_ID, (View) null, 4, (DefaultConstructorMarker) null);
        nativeAdAction.prepADWithCombinedRequests(new LandingFeedViewModel$requestAd$1(iNativeDFPAdLoadListener), AdConfigManager.Companion.getADConfig().getAdSizes());
        getAD(context, nativeAdAction);
    }

    private final void getAD(Context context, NativeAdAction nativeAdAction) {
        if (Util.isOnline(context)) {
            this.mNumberOfAds++;
            HashMap hashMap = new HashMap();
            hashMap.putAll(AdConfigManager.Companion.getADConfig().getCustomTargeting());
            if (!hashMap.containsKey("pos") || StringsKt.equals$default((String) hashMap.get("pos"), "", false, 2, (Object) null)) {
                hashMap.put("pos", "2022");
            }
            Map map = hashMap;
            map.put("pc", getAdPclass());
            map.put("pvid", this.mPvid);
            map.put(AdsConstants.PG, String.valueOf(this.mNumberOfAds));
            nativeAdAction.setPgValue(this.mNumberOfAds);
            this.adBidder.makeADCallWithBidding(context, (HashMap<String, String>) hashMap, nativeAdAction);
        }
    }

    public final void onPause() {
        resetLastPause();
    }

    public final void resetLastPause() {
        SharedPreferenceProvider.get().save(getLastPausePrefName(), Long.valueOf(System.currentTimeMillis()));
    }

    public final void setCurrentSpeciality(Context context) {
        this.currentSpecialityID = getCurrentSpeciality(context);
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        String[] strArr = new String[2];
        strArr[0] = this.currentSpecialityID;
        strArr[1] = this.feedType == 2 ? ExifInterface.GPS_MEASUREMENT_2D : AppEventsConstants.EVENT_PARAM_VALUE_YES;
        this.currentSpecialityName = FeedMaster.getSpecialtyNameOrDefaultBySpecialtyId(databaseHelper, strArr);
    }

    public final String getCurrentSpeciality(Context context) {
        String setting = Settings.singleton(context).getSetting(UserProfileProvider.INSTANCE.getUserSpecialityIDKey(context), "");
        Intrinsics.checkNotNullExpressionValue(setting, "Settings.singleton(conte…cialityIDKey(context),\"\")");
        return setting;
    }

    public final String getCurrentSpecialityIDwithFallBack(Context context) {
        if (StringsKt.isBlank(this.currentSpecialityID)) {
            setCurrentSpeciality(context);
        }
        return StringsKt.isBlank(this.currentSpecialityID) ^ true ? this.currentSpecialityID : "17";
    }

    public final boolean onResume(FragmentActivity fragmentActivity) {
        if (System.currentTimeMillis() - SharedPreferenceProvider.get().get(getLastPausePrefName(), System.currentTimeMillis()) < TimeUnit.MINUTES.toMillis(30)) {
            if (!(this.currentSpecialityID.length() > 0) || !(!Intrinsics.areEqual((Object) this.currentSpecialityID, (Object) getCurrentSpeciality(fragmentActivity)))) {
                if (this.selectedItem > -1) {
                    FeedPagedListAdapter feedPagedListAdapter = this.feedAdapter;
                    if (feedPagedListAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("feedAdapter");
                    }
                    feedPagedListAdapter.getSaveStateUpdatePosSet().add(Integer.valueOf(this.selectedItem));
                    FeedPagedListAdapter feedPagedListAdapter2 = this.feedAdapter;
                    if (feedPagedListAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("feedAdapter");
                    }
                    feedPagedListAdapter2.notifyItemChanged(this.selectedItem);
                    this.selectedItem = -1;
                } else if (this.itemsLoaded) {
                    AsyncKt.doAsync$default(this, (Function1) null, new LandingFeedViewModel$onResume$2(this), 1, (Object) null);
                }
                return false;
            }
        }
        setCurrentSpeciality(fragmentActivity);
        resetLastPause();
        refresh();
        if (this.feedType == 3 && fragmentActivity != null) {
            loadLiveEvents(fragmentActivity);
        }
        return true;
    }

    public final void updateSavedState(FeedDataItem feedDataItem, Context context) {
        Intrinsics.checkNotNullParameter(feedDataItem, "data");
        Intrinsics.checkNotNullParameter(context, "context");
        if (StringsKt.equals$default(feedDataItem.getType(), "reference", false, 2, (Object) null)) {
            feedDataItem.setSaved(Boolean.valueOf(isReferenceContentSaved(feedDataItem, context)));
        } else {
            feedDataItem.setSaved(Boolean.valueOf(isRssContentSaved(cleanUrl(feedDataItem.getUrl()), context)));
        }
    }

    public final void saveFeedItem(FeedDataItem feedDataItem, FragmentActivity fragmentActivity, int i) {
        Intrinsics.checkNotNullParameter(feedDataItem, "data");
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        String cleanUrl = cleanUrl(feedDataItem.getUrl());
        String type = feedDataItem.getType();
        if (type == null) {
            return;
        }
        if (type != null) {
            String lowerCase = type.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
            int hashCode = lowerCase.hashCode();
            if (hashCode != -925155509) {
                if (hashCode != 98619) {
                    if (hashCode == 3377875 && lowerCase.equals("news")) {
                        int i2 = Cache.NEWS;
                        Context context = fragmentActivity;
                        if (isRssContentSaved(cleanUrl, context)) {
                            removeRssInfo(cleanUrl, feedDataItem, context);
                            OmnitureManager.get().trackModule(context, Constants.OMNITURE_CHANNEL_NEWS, "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
                        } else {
                            String url = feedDataItem.getUrl();
                            Intrinsics.checkNotNull(url);
                            if (saveRssInfo(url, feedDataItem, context, i2)) {
                                Toast.makeText(context, R.string.news_article_saved, 0).show();
                                AppboyEventsHandler.logDailyEvent(context, AppboyConstants.APPBOY_EVENT_NEWS_SAVED, fragmentActivity);
                                OmnitureManager.get().trackModule(context, Constants.OMNITURE_CHANNEL_NEWS, "save", "news", (Map<String, Object>) null);
                            }
                        }
                    }
                } else if (lowerCase.equals(FeedConstants.CME_ITEM)) {
                    int i3 = Cache.CME;
                    Context context2 = fragmentActivity;
                    if (isRssContentSaved(cleanUrl, context2)) {
                        removeRssInfo(cleanUrl, feedDataItem, context2);
                        OmnitureManager.get().trackModule(context2, "education", "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
                    } else {
                        String url2 = feedDataItem.getUrl();
                        Intrinsics.checkNotNull(url2);
                        if (saveRssInfo(url2, feedDataItem, context2, i3)) {
                            Toast.makeText(context2, R.string.education_acticle_saved, 0).show();
                            AppboyEventsHandler.logDailyEvent(context2, AppboyConstants.APPBOY_EVENT_CME_SAVED, fragmentActivity);
                            OmnitureManager.get().trackModule(context2, "education", "save", FeedConstants.CME_ITEM, (Map<String, Object>) null);
                        }
                    }
                }
            } else if (lowerCase.equals("reference")) {
                Context context3 = fragmentActivity;
                if (isReferenceContentSaved(feedDataItem, context3)) {
                    removeReferenceInfo(feedDataItem, context3);
                    OmnitureManager.get().trackModule(context3, Constants.OMNITURE_CHANNEL_REFERENCE, "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
                } else if (saveReferenceInfo(feedDataItem, context3)) {
                    AppboyEventsHandler.logDailyEvent(context3, AppboyConstants.APPBOY_EVENT_CKB_SAVED, fragmentActivity);
                    Toast.makeText(context3, R.string.reference_artical_saved, 0).show();
                    OmnitureManager.get().trackModule(context3, Constants.OMNITURE_CHANNEL_REFERENCE, "save", "ref", (Map<String, Object>) null);
                }
            }
            FeedPagedListAdapter feedPagedListAdapter = this.feedAdapter;
            if (feedPagedListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("feedAdapter");
            }
            feedPagedListAdapter.notifyItemChanged(i);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    private final boolean isRssContentSaved(String str, Context context) {
        Cache cache;
        CacheManager cacheManager = new CacheManager(context);
        if (str == null || (cache = cacheManager.getCache(str)) == null || !cache.isSaved()) {
            return false;
        }
        return true;
    }

    private final void removeRssInfo(String str, FeedDataItem feedDataItem, Context context) {
        CharSequence charSequence = str;
        if (!(charSequence == null || charSequence.length() == 0)) {
            feedDataItem.setSaved(false);
            ContentValues contentValues = new ContentValues();
            contentValues.put("isSaved", 0);
            String[] strArr = new String[1];
            int length = charSequence.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
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
            strArr[0] = charSequence.subSequence(i, length + 1).toString();
            new CacheManager(context).updateCache(contentValues, "url = ?", strArr);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean saveRssInfo(java.lang.String r11, com.medscape.android.landingfeed.model.FeedDataItem r12, android.content.Context r13, int r14) {
        /*
            r10 = this;
            int r0 = com.medscape.android.cache.Cache.NEWS
            r1 = 0
            r2 = 1
            if (r14 != r0) goto L_0x0024
            java.lang.String r0 = r12.getHeadline()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x0017
            boolean r0 = kotlin.text.StringsKt.isBlank(r0)
            if (r0 == 0) goto L_0x0015
            goto L_0x0017
        L_0x0015:
            r0 = 0
            goto L_0x0018
        L_0x0017:
            r0 = 1
        L_0x0018:
            if (r0 == 0) goto L_0x001b
            goto L_0x0024
        L_0x001b:
            java.lang.String r0 = r12.getHeadline()
            java.lang.String r0 = r0.toString()
            goto L_0x002c
        L_0x0024:
            java.lang.String r0 = r12.getTitle()
            java.lang.String r0 = java.lang.String.valueOf(r0)
        L_0x002c:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            java.lang.String r5 = "isSaved"
            r3.put(r5, r4)
            java.lang.String[] r4 = new java.lang.String[r2]
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            int r5 = r11.length()
            int r5 = r5 - r2
            r6 = 0
            r7 = 0
        L_0x0045:
            r8 = 32
            if (r6 > r5) goto L_0x006a
            if (r7 != 0) goto L_0x004d
            r9 = r6
            goto L_0x004e
        L_0x004d:
            r9 = r5
        L_0x004e:
            char r9 = r11.charAt(r9)
            int r9 = kotlin.jvm.internal.Intrinsics.compare((int) r9, (int) r8)
            if (r9 > 0) goto L_0x005a
            r9 = 1
            goto L_0x005b
        L_0x005a:
            r9 = 0
        L_0x005b:
            if (r7 != 0) goto L_0x0064
            if (r9 != 0) goto L_0x0061
            r7 = 1
            goto L_0x0045
        L_0x0061:
            int r6 = r6 + 1
            goto L_0x0045
        L_0x0064:
            if (r9 != 0) goto L_0x0067
            goto L_0x006a
        L_0x0067:
            int r5 = r5 + -1
            goto L_0x0045
        L_0x006a:
            int r5 = r5 + r2
            java.lang.CharSequence r5 = r11.subSequence(r6, r5)
            java.lang.String r5 = r5.toString()
            r4[r1] = r5
            com.medscape.android.cache.CacheManager r5 = new com.medscape.android.cache.CacheManager
            r5.<init>(r13)
            java.lang.String r13 = "url = ?"
            boolean r13 = r5.updateCache(r3, r13, r4)
            if (r13 != 0) goto L_0x00e3
            com.medscape.android.cache.Cache r13 = new com.medscape.android.cache.Cache
            r13.<init>()
            int r3 = r11.length()
            int r3 = r3 - r2
            r4 = 0
            r6 = 0
        L_0x008e:
            if (r4 > r3) goto L_0x00b1
            if (r6 != 0) goto L_0x0094
            r7 = r4
            goto L_0x0095
        L_0x0094:
            r7 = r3
        L_0x0095:
            char r7 = r11.charAt(r7)
            int r7 = kotlin.jvm.internal.Intrinsics.compare((int) r7, (int) r8)
            if (r7 > 0) goto L_0x00a1
            r7 = 1
            goto L_0x00a2
        L_0x00a1:
            r7 = 0
        L_0x00a2:
            if (r6 != 0) goto L_0x00ab
            if (r7 != 0) goto L_0x00a8
            r6 = 1
            goto L_0x008e
        L_0x00a8:
            int r4 = r4 + 1
            goto L_0x008e
        L_0x00ab:
            if (r7 != 0) goto L_0x00ae
            goto L_0x00b1
        L_0x00ae:
            int r3 = r3 + -1
            goto L_0x008e
        L_0x00b1:
            int r3 = r3 + r2
            java.lang.CharSequence r11 = r11.subSequence(r4, r3)
            java.lang.String r11 = r11.toString()
            r13.setUrl(r11)
            r13.setType(r14)
            java.lang.String r11 = r12.getPublishDate()
            r13.setTime(r11)
            r13.setTitle(r0)
            int r11 = com.medscape.android.cache.Cache.NEWS
            if (r14 != r11) goto L_0x00dc
            java.lang.String r11 = r12.getPublicationName()
            r13.setByline(r11)
            java.lang.String r11 = r12.getImageUrl()
            r13.setImageUrl(r11)
        L_0x00dc:
            r13.setSaved(r2)
            r5.addCache((com.medscape.android.cache.Cache) r13)
            goto L_0x00e4
        L_0x00e3:
            r2 = r13
        L_0x00e4:
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r2)
            r12.setSaved(r11)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel.saveRssInfo(java.lang.String, com.medscape.android.landingfeed.model.FeedDataItem, android.content.Context, int):boolean");
    }

    public final boolean isReferenceContentSaved(FeedDataItem feedDataItem, Context context) {
        Cursor query;
        Intrinsics.checkNotNullParameter(feedDataItem, "data");
        Intrinsics.checkNotNullParameter(context, "context");
        AuthenticationManager instance = AuthenticationManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.getInstance(context)");
        String maskedGuid = instance.getMaskedGuid();
        CharSequence charSequence = maskedGuid;
        if ((charSequence == null || charSequence.length() == 0) || (query = context.getContentResolver().query(ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI, (String[]) null, "articleId=? AND (userGuid='' OR userGuid=?)", new String[]{String.valueOf(feedDataItem.getContentId()), maskedGuid}, (String) null)) == null) {
            return false;
        }
        Intrinsics.checkNotNullExpressionValue(query, "context.contentResolver.…d), null) ?: return false");
        return query.moveToFirst();
    }

    private final void removeReferenceInfo(FeedDataItem feedDataItem, Context context) {
        feedDataItem.setSaved(false);
        try {
            context.getContentResolver().delete(ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI, "uniqueId = ?", new String[]{String.valueOf(feedDataItem.getContentId())});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0020 A[Catch:{ Exception -> 0x0069 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0021 A[Catch:{ Exception -> 0x0069 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean saveReferenceInfo(com.medscape.android.landingfeed.model.FeedDataItem r7, android.content.Context r8) {
        /*
            r6 = this;
            r0 = 0
            com.medscape.android.auth.AuthenticationManager r1 = com.medscape.android.auth.AuthenticationManager.getInstance(r8)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r2 = "AuthenticationManager.getInstance(context)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r1 = r1.getMaskedGuid()     // Catch:{ Exception -> 0x0069 }
            r2 = r1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2     // Catch:{ Exception -> 0x0069 }
            r3 = 1
            if (r2 == 0) goto L_0x001d
            int r2 = r2.length()     // Catch:{ Exception -> 0x0069 }
            if (r2 != 0) goto L_0x001b
            goto L_0x001d
        L_0x001b:
            r2 = 0
            goto L_0x001e
        L_0x001d:
            r2 = 1
        L_0x001e:
            if (r2 == 0) goto L_0x0021
            return r0
        L_0x0021:
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ Exception -> 0x0069 }
            r2.<init>()     // Catch:{ Exception -> 0x0069 }
            java.lang.String r4 = "isSaved"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x0069 }
            r2.put(r4, r5)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r4 = "title"
            java.lang.String r5 = r7.getTitle()     // Catch:{ Exception -> 0x0069 }
            r2.put(r4, r5)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r4 = "type"
            java.lang.String r5 = r7.getType()     // Catch:{ Exception -> 0x0069 }
            r2.put(r4, r5)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r4 = "uniqueId"
            java.lang.String r5 = r7.getContentId()     // Catch:{ Exception -> 0x0069 }
            r2.put(r4, r5)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r4 = "articleId"
            java.lang.String r5 = r7.getContentId()     // Catch:{ Exception -> 0x0069 }
            r2.put(r4, r5)     // Catch:{ Exception -> 0x0069 }
            java.lang.String r4 = "userGuid"
            r2.put(r4, r1)     // Catch:{ Exception -> 0x0069 }
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch:{ Exception -> 0x0069 }
            android.net.Uri r1 = com.medscape.android.reference.model.ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI     // Catch:{ Exception -> 0x0069 }
            r8.insert(r1, r2)     // Catch:{ Exception -> 0x0069 }
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r3)     // Catch:{ Exception -> 0x0069 }
            r7.setSaved(r8)     // Catch:{ Exception -> 0x0069 }
            return r3
        L_0x0069:
            r7 = move-exception
            r7.printStackTrace()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel.saveReferenceInfo(com.medscape.android.landingfeed.model.FeedDataItem, android.content.Context):boolean");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01b9, code lost:
        if (r4 != null) goto L_0x020a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void launchFeedItem(com.medscape.android.landingfeed.model.FeedDataItem r18, androidx.fragment.app.FragmentActivity r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            java.lang.String r2 = "selectedData"
            r3 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            java.lang.String r2 = "mContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r17.isSlideDemoModeOn()
            java.lang.String r2 = r17.getOmnitureLink(r18)
            java.lang.String r4 = r18.getType()
            r5 = 0
            if (r4 == 0) goto L_0x0032
            if (r4 == 0) goto L_0x002a
            java.lang.String r4 = r4.toLowerCase()
            java.lang.String r6 = "(this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)
            goto L_0x0033
        L_0x002a:
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "null cannot be cast to non-null type java.lang.String"
            r1.<init>(r2)
            throw r1
        L_0x0032:
            r4 = r5
        L_0x0033:
            r16 = r5
            android.content.Intent r16 = (android.content.Intent) r16
            if (r4 != 0) goto L_0x003b
            goto L_0x01e6
        L_0x003b:
            int r6 = r4.hashCode()
            r7 = 0
            java.lang.String r8 = "home-screen"
            r9 = 1
            switch(r6) {
                case -925155509: goto L_0x010f;
                case 98619: goto L_0x00c8;
                case 3377875: goto L_0x007e;
                case 951516140: goto L_0x0048;
                default: goto L_0x0046;
            }
        L_0x0046:
            goto L_0x01e6
        L_0x0048:
            java.lang.String r6 = "consult"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x01e6
            r4 = r1
            android.content.Context r4 = (android.content.Context) r4
            com.medscape.android.capabilities.CapabilitiesManager r6 = com.medscape.android.capabilities.CapabilitiesManager.getInstance(r4)
            java.lang.String r7 = "CapabilitiesManager.getInstance(mContext)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            boolean r6 = r6.isConsultFeatureAvailable()
            if (r6 == 0) goto L_0x020a
            java.lang.String r3 = r18.getUrl()
            android.net.Uri r3 = android.net.Uri.parse(r3)
            java.lang.String r3 = com.medscape.android.util.RedirectHandler.transferUniversalLinkIntoDeepLink(r3)
            com.medscape.android.BI.omniture.OmnitureManager r6 = com.medscape.android.BI.omniture.OmnitureManager.get()
            r6.markModule((java.lang.String) r8, (java.lang.String) r2, (java.util.Map<java.lang.String, java.lang.Object>) r5, (boolean) r9)
            com.medscape.android.capabilities.CapabilitiesManager r2 = com.medscape.android.capabilities.CapabilitiesManager.getInstance(r4)
            r2.startConsultActivity(r3)
            goto L_0x020a
        L_0x007e:
            java.lang.String r5 = "news"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x01e6
            java.lang.String r4 = r18.getContentId()
            if (r4 == 0) goto L_0x020a
            com.medscape.android.activity.webviews.WebviewUtil$Companion r5 = com.medscape.android.activity.webviews.WebviewUtil.Companion
            r6 = r1
            android.content.Context r6 = (android.content.Context) r6
            java.lang.String r5 = r5.getNewsFullUrl(r4, r6)
            java.lang.String r4 = r18.getTitle()
            r8 = r4
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            if (r8 == 0) goto L_0x00a4
            int r8 = r8.length()
            if (r8 != 0) goto L_0x00a5
        L_0x00a4:
            r7 = 1
        L_0x00a5:
            if (r7 == 0) goto L_0x00ab
            java.lang.String r4 = r18.getHeadline()
        L_0x00ab:
            r7 = r4
            com.medscape.android.activity.webviews.WebviewUtil$Companion r4 = com.medscape.android.activity.webviews.WebviewUtil.Companion
            java.lang.String r10 = r18.getPublishDate()
            java.lang.String r11 = r18.getImageUrl()
            java.lang.String r12 = r18.getPublicationName()
            java.lang.String r8 = ""
            java.lang.String r9 = "news and perspectives"
            r3 = r4
            r4 = r6
            r6 = r7
            r7 = r8
            r8 = r2
            r3.launchNews(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            goto L_0x020a
        L_0x00c8:
            java.lang.String r5 = "cme"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x01e6
            java.lang.String r2 = r18.getContentId()
            if (r2 == 0) goto L_0x020a
            com.medscape.android.parser.model.Article r11 = new com.medscape.android.parser.model.Article
            r11.<init>()
            r11.mArticleId = r2
            java.lang.String r2 = r18.getTitle()
            r11.mTitle = r2
            java.lang.String r2 = r11.mTitle
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L_0x00ef
            int r2 = r2.length()
            if (r2 != 0) goto L_0x00f0
        L_0x00ef:
            r7 = 1
        L_0x00f0:
            if (r7 == 0) goto L_0x00f8
            java.lang.String r2 = r18.getHeadline()
            r11.mTitle = r2
        L_0x00f8:
            java.lang.String r2 = r18.getUrl()
            java.lang.String r2 = r0.cleanUrl(r2)
            r11.mLink = r2
            r10 = r1
            android.content.Context r10 = (android.content.Context) r10
            r12 = 0
            r13 = 0
            r14 = 12
            r15 = 0
            com.medscape.android.activity.cme.CMEHelper.launchCMEArticle$default(r10, r11, r12, r13, r14, r15)
            goto L_0x020a
        L_0x010f:
            java.lang.String r6 = "reference"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x01e6
            java.lang.String r4 = r18.getCategory()
            if (r4 == 0) goto L_0x01bc
            int r6 = r4.hashCode()
            r7 = 800551195(0x2fb7711b, float:3.3367872E-10)
            if (r6 == r7) goto L_0x0143
            r7 = 908763827(0x362aa2b3, float:2.5426677E-6)
            if (r6 == r7) goto L_0x013a
            r7 = 1142656251(0x441b8cfb, float:622.2028)
            if (r6 == r7) goto L_0x0131
            goto L_0x0196
        L_0x0131:
            java.lang.String r6 = "Condition"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x0196
            goto L_0x014b
        L_0x013a:
            java.lang.String r6 = "Procedure"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x0196
            goto L_0x014b
        L_0x0143:
            java.lang.String r6 = "Anatomy"
            boolean r4 = r4.equals(r6)
            if (r4 == 0) goto L_0x0196
        L_0x014b:
            com.medscape.android.reference.model.ClinicalReferenceArticle r4 = new com.medscape.android.reference.model.ClinicalReferenceArticle
            r4.<init>()
            java.lang.String r6 = r18.getContentId()
            if (r6 == 0) goto L_0x015d
            int r6 = java.lang.Integer.parseInt(r6)
            r4.setArticleId(r6)
        L_0x015d:
            java.lang.String r6 = r18.getTitle()
            r4.setTitle(r6)
            com.medscape.android.BI.omniture.OmnitureManager r6 = com.medscape.android.BI.omniture.OmnitureManager.get()
            r6.markModule(r8, r2, r5)
            com.medscape.android.analytics.remoteconfig.reference.ReferenceArticleTOCEnabledManager r5 = new com.medscape.android.analytics.remoteconfig.reference.ReferenceArticleTOCEnabledManager
            r5.<init>()
            boolean r5 = r5.getRefTOCData()
            if (r5 == 0) goto L_0x0181
            android.content.Intent r5 = new android.content.Intent
            r6 = r1
            android.content.Context r6 = (android.content.Context) r6
            java.lang.Class<com.medscape.android.reference.ClinicalReferenceArticleLandingActivity> r7 = com.medscape.android.reference.ClinicalReferenceArticleLandingActivity.class
            r5.<init>(r6, r7)
            goto L_0x018b
        L_0x0181:
            android.content.Intent r5 = new android.content.Intent
            r6 = r1
            android.content.Context r6 = (android.content.Context) r6
            java.lang.Class<com.medscape.android.reference.ClinicalReferenceArticleActivity> r7 = com.medscape.android.reference.ClinicalReferenceArticleActivity.class
            r5.<init>(r6, r7)
        L_0x018b:
            java.io.Serializable r4 = (java.io.Serializable) r4
            java.lang.String r6 = "article"
            android.content.Intent r4 = r5.putExtra(r6, r4)
            r16 = r5
            goto L_0x01b9
        L_0x0196:
            java.lang.String r6 = r18.getUrl()
            if (r6 == 0) goto L_0x01b8
            com.medscape.android.activity.webviews.WebviewUtil$Companion r4 = com.medscape.android.activity.webviews.WebviewUtil.Companion
            r5 = r1
            android.content.Context r5 = (android.content.Context) r5
            java.lang.String r7 = r18.getTitle()
            java.lang.String r11 = r18.getType()
            r12 = 0
            r13 = 0
            r14 = 256(0x100, float:3.59E-43)
            r15 = 0
            java.lang.String r8 = ""
            java.lang.String r10 = "other"
            r9 = r2
            com.medscape.android.activity.webviews.WebviewUtil.Companion.launchPlainWebView$default(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
        L_0x01b8:
            r4 = r5
        L_0x01b9:
            if (r4 == 0) goto L_0x01bc
            goto L_0x020a
        L_0x01bc:
            r4 = r0
            com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel r4 = (com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel) r4
            java.lang.String r5 = r18.getUrl()
            if (r5 == 0) goto L_0x020a
            com.medscape.android.activity.webviews.WebviewUtil$Companion r4 = com.medscape.android.activity.webviews.WebviewUtil.Companion
            r6 = r1
            android.content.Context r6 = (android.content.Context) r6
            java.lang.String r7 = r18.getTitle()
            java.lang.String r10 = r18.getType()
            r11 = 0
            r12 = 0
            r13 = 256(0x100, float:3.59E-43)
            r14 = 0
            java.lang.String r8 = ""
            java.lang.String r9 = "other"
            r3 = r4
            r4 = r6
            r6 = r7
            r7 = r8
            r8 = r2
            com.medscape.android.activity.webviews.WebviewUtil.Companion.launchPlainWebView$default(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            goto L_0x020a
        L_0x01e6:
            java.lang.String r5 = r18.getUrl()
            if (r5 == 0) goto L_0x020a
            com.medscape.android.activity.webviews.WebviewUtil$Companion r4 = com.medscape.android.activity.webviews.WebviewUtil.Companion
            r6 = r1
            android.content.Context r6 = (android.content.Context) r6
            java.lang.String r7 = r18.getTitle()
            java.lang.String r10 = r18.getType()
            r11 = 0
            r12 = 0
            r13 = 256(0x100, float:3.59E-43)
            r14 = 0
            java.lang.String r8 = ""
            java.lang.String r9 = "other"
            r3 = r4
            r4 = r6
            r6 = r7
            r7 = r8
            r8 = r2
            com.medscape.android.activity.webviews.WebviewUtil.Companion.launchPlainWebView$default(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
        L_0x020a:
            r2 = r16
            if (r2 == 0) goto L_0x0211
            r1.startActivity(r2)
        L_0x0211:
            r1 = r20
            r0.selectedItem = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel.launchFeedItem(com.medscape.android.landingfeed.model.FeedDataItem, androidx.fragment.app.FragmentActivity, int):void");
    }

    public final void shareFeedItem(FeedDataItem feedDataItem, FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(feedDataItem, "data");
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        this.mFeedDataItem = feedDataItem;
        Context context = fragmentActivity;
        PendingIntent createPendingIntent = new ShareReceiver().createPendingIntent(context);
        if (Build.VERSION.SDK_INT <= 22) {
            createPendingIntent = null;
            addOmnitureCallForSharing(context, AdParameterKeys.SECTION_ID);
        } else {
            ShareDataObservable.INSTANCE.addObserver(this);
        }
        com.wbmd.wbmdcommons.utils.Util.share(context, feedDataItem.getUrl(), feedDataItem.getTitle(), getSubjectForShareEmail(feedDataItem, context), (String) null, createPendingIntent);
    }

    private final String getSubjectForShareEmail(FeedDataItem feedDataItem, Context context) {
        if (context instanceof DrugMonographMainActivity) {
            return "Medscape: " + feedDataItem.getTitle();
        }
        String string = context.getResources().getString(R.string.email_title);
        Intrinsics.checkNotNullExpressionValue(string, "context.resources.getString(R.string.email_title)");
        return string;
    }

    private final String getOmnitureChannel(FeedDataItem feedDataItem) {
        String type = feedDataItem.getType();
        if (type == null) {
            return "other";
        }
        if (type != null) {
            String lowerCase = type.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
            int hashCode = lowerCase.hashCode();
            if (hashCode != -925155509) {
                if (hashCode != 98619) {
                    if (hashCode == 3377875 && lowerCase.equals("news")) {
                        return "news";
                    }
                    return "other";
                } else if (lowerCase.equals(FeedConstants.CME_ITEM)) {
                    return "education";
                } else {
                    return "other";
                }
            } else if (lowerCase.equals("reference")) {
                return Constants.OMNITURE_CHANNEL_REFERENCE;
            } else {
                return "other";
            }
        } else {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
    }

    private final String cleanUrl(String str) {
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
            Intrinsics.checkNotNullExpressionValue(str2.substring(0, indexOf$default), "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return StringsKt.replace$default(str2, "http:", "https:", false, 4, (Object) null);
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    private final String getAdPclass() {
        return this.feedType != 1 ? "hp" : "start-hp";
    }

    private final String getChannel() {
        int i = this.feedType;
        if (i != 2) {
            return i != 3 ? "other" : "education";
        }
        return Constants.OMNITURE_CHANNEL_NEWS;
    }

    public final void attachOmniturePaginationHandler(Activity activity, RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(recyclerView, "contentView");
        new WBMDOmniturePaginationHandler(activity, recyclerView, 1.0d, (WBMDPaginationListener) new LandingFeedViewModel$attachOmniturePaginationHandler$1(this, activity));
    }

    public final void sendScreenViewPing(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "context");
        String trackPageView = OmnitureManager.get().trackPageView(activity, getChannel(), getOmniturePage(), "view", getOmnitureSection(), (String) null, (Map<String, Object>) null, false, this.mPvid);
        Intrinsics.checkNotNullExpressionValue(trackPageView, "OmnitureManager.get().tr…null, null, false, mPvid)");
        this.mPvid = trackPageView;
    }

    public final String getOmniturePage() {
        int i = this.feedType;
        if (i == 2) {
            OmnitureManager.get().mSearchChannel = Constants.OMNITURE_CHANNEL_NEWS;
            return "news";
        } else if (i != 3) {
            return "home-screen";
        } else {
            OmnitureManager.get().mSearchChannel = "education";
            return "edu";
        }
    }

    private final String getOmnitureSection() {
        String str = null;
        if (this.feedType == 1) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.currentSpecialityID);
        sb.append("-");
        String str2 = this.currentSpecialityName;
        if (str2 != null) {
            if (str2 != null) {
                str = str2.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.String).toLowerCase()");
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
        }
        sb.append(str);
        return sb.toString();
    }

    public final void updateNetworkErrorState(NetworkState networkState2, FragmentActivity fragmentActivity, View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        FeedPagedListAdapter feedPagedListAdapter = this.feedAdapter;
        if (feedPagedListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("feedAdapter");
        }
        feedPagedListAdapter.setNetworkState(networkState2);
        MedscapeException medscapeException = this.mNoNetworkException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
        if ((networkState2 != null ? networkState2.getStatus() : null) == Status.FAILED) {
            MedscapeException medscapeException2 = new MedscapeException(networkState2.getMsg());
            this.mNoNetworkException = medscapeException2;
            if (medscapeException2 != null) {
                medscapeException2.showSnackBar(view, -2, view.getResources().getString(R.string.retry), new LandingFeedViewModel$updateNetworkErrorState$1(this, fragmentActivity));
            }
        }
    }

    public final void startWithAuthCheck(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Context context = fragmentActivity;
        if (!AccountProvider.isUserLoggedIn(context) || !Util.isOnline(context)) {
            refreshFailedFeed(true);
        } else {
            AccountProvider.signIn(context, new LandingFeedViewModel$startWithAuthCheck$1(this, fragmentActivity));
        }
    }

    private final String getLastPausePrefName() {
        return Constants.PREF_LAST_PAUSE + this.feedType;
    }

    public final String getApiFeedType() {
        int i = this.feedType;
        if (i != 2) {
            return i != 3 ? "splash" : "CME";
        }
        return "NEWS";
    }

    private final int getSlideDemoMode() {
        MedscapeApplication medscapeApplication = MedscapeApplication.get();
        Intrinsics.checkNotNullExpressionValue(medscapeApplication, "MedscapeApplication.get()");
        return medscapeApplication.getPreferences().getInt(Constants.PREF_DEBUG_SLIDE_DEMO_MODE, 0);
    }

    private final boolean isSlideDemoModeOn() {
        MedscapeApplication medscapeApplication = MedscapeApplication.get();
        Intrinsics.checkNotNullExpressionValue(medscapeApplication, "MedscapeApplication.get()");
        if (medscapeApplication.getPreferences().getInt(Constants.PREF_DEBUG_SLIDE_DEMO_MODE, 0) <= 0) {
            return false;
        }
        MedscapeApplication medscapeApplication2 = MedscapeApplication.get();
        Intrinsics.checkNotNullExpressionValue(medscapeApplication2, "MedscapeApplication.get()");
        if (!Intrinsics.areEqual((Object) "", (Object) medscapeApplication2.getPreferences().getString(Constants.PREF_DEBUG_SLIDE_DEMO_URL, ""))) {
            return true;
        }
        return false;
    }

    public final String getOmnitureLink(FeedDataItem feedDataItem) {
        String str;
        Intrinsics.checkNotNullParameter(feedDataItem, "selectedData");
        String type = feedDataItem.getType();
        if (type == null) {
            str = null;
        } else if (type != null) {
            str = type.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(str, "(this as java.lang.String).toLowerCase()");
        } else {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String source = feedDataItem.getSource();
        boolean z = true;
        if (str != null) {
            switch (str.hashCode()) {
                case -925155509:
                    if (str.equals("reference") && StringsKt.equals(source, "BI_REF", true)) {
                        return "biref";
                    }
                case 98619:
                    if (str.equals(FeedConstants.CME_ITEM)) {
                        return "cmefd";
                    }
                    break;
                case 3377875:
                    if (str.equals("news")) {
                        int i = this.feedType;
                        if (i == 2) {
                            return "nwsfd";
                        }
                        if (i == 1) {
                            if (StringsKt.equals(source, "BI_NEWS", true)) {
                                return "binws";
                            }
                            if (StringsKt.equals(source, "BUCKET", true)) {
                                return "buckt";
                            }
                        }
                    }
                    break;
                case 951516140:
                    if (str.equals("consult")) {
                        return "feed";
                    }
                    break;
            }
        }
        CharSequence charSequence = source;
        if (charSequence != null && !StringsKt.isBlank(charSequence)) {
            z = false;
        }
        if (z) {
            return "none";
        }
        int i2 = 5;
        if (source.length() <= 5) {
            i2 = source.length();
        }
        if (source != null) {
            String substring = source.substring(0, i2);
            Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    public final void initPreloadAds(PagedList<FeedDataItem> pagedList) {
        if (pagedList != null) {
            int preloadCount = this.adConfigManager.getAdConfigData().getPreloadCount();
            int size = pagedList.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                FeedDataItem feedDataItem = pagedList.get(i2);
                if (feedDataItem instanceof FeedAdItem) {
                    if (i < preloadCount) {
                        i++;
                        ((FeedAdItem) feedDataItem).setAdRequested(true);
                        FeedPagedListAdapter feedPagedListAdapter = this.feedAdapter;
                        if (feedPagedListAdapter == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("feedAdapter");
                        }
                        feedPagedListAdapter.initPreloadAds(i2);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public final void refreshFailedFeed(boolean z) {
        if (z) {
            this.isLoginDone = true;
        } else {
            this.isFeedFailed = true;
        }
        if (this.isLoginDone && this.isFeedFailed) {
            retry();
        }
    }

    public final void sendNativeAdViewedImpression(FragmentActivity fragmentActivity, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        String channel = getChannel();
        OmnitureManager.get().trackModule(fragmentActivity, channel, "splashcrsl-imp", i + "-p", (Map<String, Object>) null);
    }

    public final void sendNativeAdClickedImpression(FragmentActivity fragmentActivity, int i) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        String channel = getChannel();
        OmnitureManager.get().trackModule(fragmentActivity, channel, "splashcrsl", i + "-p", (Map<String, Object>) null);
    }

    public void update(Observable observable, Object obj) {
        if (observable instanceof ShareDataObservable) {
            if (obj instanceof String) {
                MedscapeApplication medscapeApplication = MedscapeApplication.get();
                Intrinsics.checkNotNullExpressionValue(medscapeApplication, "MedscapeApplication.get()");
                addOmnitureCallForSharing(medscapeApplication, (String) obj);
            }
            ShareDataObservable.INSTANCE.deleteObserver(this);
        }
    }

    private final void addOmnitureCallForSharing(Context context, String str) {
        FeedDataItem feedDataItem = this.mFeedDataItem;
        if (feedDataItem != null) {
            Boolean isMedlineArticle = feedDataItem.isMedlineArticle();
            Intrinsics.checkNotNull(isMedlineArticle);
            String str2 = "";
            if (isMedlineArticle.booleanValue()) {
                String str3 = OmnitureManager.get().getmCurrentPageName();
                Intrinsics.checkNotNullExpressionValue(str3, "OmnitureManager.get().getmCurrentPageName()");
                OmnitureManager.get().setmCurrentPageName(str2);
                str2 = str3;
            }
            OmnitureManager.get().trackModule(context, getOmnitureChannel(feedDataItem), ShareReceiver.Companion.getSHARE_MODULE_FEED(), str, (Map<String, Object>) null);
            Boolean isMedlineArticle2 = feedDataItem.isMedlineArticle();
            Intrinsics.checkNotNull(isMedlineArticle2);
            if (isMedlineArticle2.booleanValue()) {
                OmnitureManager.get().setmCurrentPageName(str2);
            }
        }
    }

    public static /* synthetic */ void loadSpecificInvitations$default(LandingFeedViewModel landingFeedViewModel, FragmentActivity fragmentActivity, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        landingFeedViewModel.loadSpecificInvitations(fragmentActivity, z);
    }

    public final void loadSpecificInvitations(FragmentActivity fragmentActivity, boolean z) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
        Context context = fragmentActivity;
        Collection specificInvitations = MyInvitationsManager.Companion.get(context).getSpecificInvitations();
        if ((specificInvitations == null || specificInvitations.isEmpty()) || z) {
            MyInvitationsManager.Companion.get(context).fetchSpecificInvitations();
        }
    }
}
