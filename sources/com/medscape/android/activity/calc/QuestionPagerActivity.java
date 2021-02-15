package com.medscape.android.activity.calc;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.appboy.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter;
import com.medscape.android.activity.calc.fragments.QuestionPagerFragment;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.ads.AdsConstants;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.ads.bidding.AdBidder;
import com.medscape.android.util.Util;
import com.wbmd.qxcalculator.activities.contentItems.ContentItemActivity;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u00102\u001a\u00020,H\u0016J\u0010\u00103\u001a\u0002042\b\u0010.\u001a\u0004\u0018\u00010/J\b\u00105\u001a\u00020,H\u0016J\u0012\u00106\u001a\u0002042\b\u00107\u001a\u0004\u0018\u000108H\u0014J\b\u00109\u001a\u000204H\u0016J\b\u0010:\u001a\u000204H\u0002J\u001e\u0010;\u001a\u0002042\u0006\u0010<\u001a\u00020\r2\u0006\u0010=\u001a\u00020 2\u0006\u0010>\u001a\u00020\u001aJ\u0006\u0010?\u001a\u000204J\b\u0010@\u001a\u000204H\u0014J\b\u0010A\u001a\u000204H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u001a\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/medscape/android/activity/calc/QuestionPagerActivity;", "Lcom/wbmd/qxcalculator/activities/contentItems/QuestionPagerActivity;", "Lcom/medscape/android/activity/calc/fragments/QuestionPagerFragment$OnPageSelectionChanged;", "()V", "adBidder", "Lcom/medscape/android/ads/bidding/AdBidder;", "adLayout", "Landroid/view/View;", "getAdLayout", "()Landroid/view/View;", "setAdLayout", "(Landroid/view/View;)V", "adapter", "Lcom/medscape/android/activity/calc/adapters/QxRecyclerViewAdapter;", "getAdapter", "()Lcom/medscape/android/activity/calc/adapters/QxRecyclerViewAdapter;", "setAdapter", "(Lcom/medscape/android/activity/calc/adapters/QxRecyclerViewAdapter;)V", "bannerScreenSpecificMap", "Ljava/util/HashMap;", "", "dfpAdLayout", "fragment", "Lcom/medscape/android/activity/calc/fragments/QuestionPagerFragment;", "inlineScreenSpecificMap", "isAdded", "", "isInlineADcallComplete", "()Z", "setInlineADcallComplete", "(Z)V", "listView", "Landroidx/recyclerview/widget/RecyclerView;", "getListView", "()Landroidx/recyclerview/widget/RecyclerView;", "setListView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "mContainer", "Landroid/widget/LinearLayout;", "getMContainer", "()Landroid/widget/LinearLayout;", "setMContainer", "(Landroid/widget/LinearLayout;)V", "mNumberOfAds", "", "mPvid", "nativeAdAction", "Lcom/medscape/android/ads/NativeAdAction;", "pclass", "screenSpecificMap", "getActionBarColor", "getAd", "", "getStatusBarColor", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onQuestionChanged", "requestBottomBannerAD", "setListValuesForVisibilityCheck", "a", "l", "complete", "setScreenSpecificMap", "setupFirstFragment", "setupNativeBottomBannerAd", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QuestionPagerActivity.kt */
public class QuestionPagerActivity extends com.wbmd.qxcalculator.activities.contentItems.QuestionPagerActivity implements QuestionPagerFragment.OnPageSelectionChanged {
    private HashMap _$_findViewCache;
    private AdBidder adBidder = new AdBidder();
    public View adLayout;
    private QxRecyclerViewAdapter adapter;
    private final HashMap<String, String> bannerScreenSpecificMap = new HashMap<>();
    private View dfpAdLayout;
    private QuestionPagerFragment fragment;
    private final HashMap<String, String> inlineScreenSpecificMap = new HashMap<>();
    /* access modifiers changed from: private */
    public boolean isAdded;
    private boolean isInlineADcallComplete;
    private RecyclerView listView;
    public LinearLayout mContainer;
    private int mNumberOfAds;
    private String mPvid = "";
    private NativeAdAction nativeAdAction;
    private String pclass = "content";
    private final HashMap<String, String> screenSpecificMap = new HashMap<>();

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public final LinearLayout getMContainer() {
        LinearLayout linearLayout = this.mContainer;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContainer");
        }
        return linearLayout;
    }

    public final void setMContainer(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.mContainer = linearLayout;
    }

    public final View getAdLayout() {
        View view = this.adLayout;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adLayout");
        }
        return view;
    }

    public final void setAdLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.adLayout = view;
    }

    public final QxRecyclerViewAdapter getAdapter() {
        return this.adapter;
    }

    public final void setAdapter(QxRecyclerViewAdapter qxRecyclerViewAdapter) {
        this.adapter = qxRecyclerViewAdapter;
    }

    public final RecyclerView getListView() {
        return this.listView;
    }

    public final void setListView(RecyclerView recyclerView) {
        this.listView = recyclerView;
    }

    public final boolean isInlineADcallComplete() {
        return this.isInlineADcallComplete;
    }

    public final void setInlineADcallComplete(boolean z) {
        this.isInlineADcallComplete = z;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent().getStringExtra("pvid") != null) {
            String stringExtra = getIntent().getStringExtra("pvid");
            Intrinsics.checkNotNull(stringExtra);
            this.mPvid = stringExtra;
        }
        this.contentItem = (ContentItem) getIntent().getParcelableExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM);
        View findViewById = findViewById(R.id.question_container);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<LinearLayou…(R.id.question_container)");
        this.mContainer = (LinearLayout) findViewById;
        setScreenSpecificMap();
        setupNativeBottomBannerAd();
        requestBottomBannerAD();
    }

    public final void setScreenSpecificMap() {
        String string = getResources().getString(R.string.banner_ad_pos);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.banner_ad_pos)");
        this.screenSpecificMap.put("pos", string);
        this.screenSpecificMap.put("pc", this.pclass);
        CalcArticle calcArticleFromContentItem = Util.getCalcArticleFromContentItem(this, this.contentItem);
        if (calcArticleFromContentItem != null) {
            this.screenSpecificMap.put("art", "" + calcArticleFromContentItem.getCalcId());
        }
    }

    private final void setupNativeBottomBannerAd() {
        Context context = this;
        View inflate = LayoutInflater.from(context).inflate(R.layout.combined_ad_layout, (ViewGroup) null);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(R.layou…combined_ad_layout, null)");
        this.adLayout = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adLayout");
        }
        this.dfpAdLayout = inflate.findViewById(R.id.dfp_adLayout);
        this.nativeAdAction = new NativeAdAction(context, DFPReferenceAdListener.AD_UNIT_ID, this.dfpAdLayout);
        View view = this.adLayout;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adLayout");
        }
        TextView textView = (TextView) view.findViewById(R.id.dfp_adLabel);
        if (textView != null) {
            textView.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        if (textView != null) {
            textView.setBackgroundResource(R.color.black);
        }
        if (textView != null) {
            textView.setPadding(0, 0, 0, 0);
        }
    }

    private final void requestBottomBannerAD() {
        View view = this.adLayout;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adLayout");
        }
        TextView textView = (TextView) view.findViewById(R.id.dfp_adLabel);
        if (textView != null) {
            textView.setVisibility(0);
        }
        LinearLayout linearLayout = this.mContainer;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContainer");
        }
        Util.setContainerRule(false, linearLayout, R.id.ad);
        NativeAdAction nativeAdAction2 = this.nativeAdAction;
        if (nativeAdAction2 != null) {
            if (nativeAdAction2 != null) {
                INativeDFPAdLoadListener questionPagerActivity$requestBottomBannerAD$1 = new QuestionPagerActivity$requestBottomBannerAD$1(this);
                NativeAdAction nativeAdAction3 = this.nativeAdAction;
                nativeAdAction2.prepADWithCombinedRequests(questionPagerActivity$requestBottomBannerAD$1, nativeAdAction3 != null ? nativeAdAction3.getBottomBannerADsizes() : null);
            }
            getAd(this.nativeAdAction);
        }
    }

    public final void getAd(NativeAdAction nativeAdAction2) {
        Context context = this;
        if (Util.isOnline(context) && Util.getDisplayOrientation(context) == 0 && nativeAdAction2 != null) {
            this.screenSpecificMap.put("pvid", this.mPvid);
            HashMap hashMap = new HashMap();
            if (nativeAdAction2.isSharethrough) {
                String string = getString(R.string.sharethrough_ad_pos);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.sharethrough_ad_pos)");
                this.screenSpecificMap.put("pos", string);
                hashMap.clear();
                hashMap.putAll(this.screenSpecificMap);
            } else if (nativeAdAction2.isInlineAd) {
                this.mNumberOfAds++;
                String string2 = getResources().getString(R.string.inline_ad_pos);
                Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.inline_ad_pos)");
                this.screenSpecificMap.put("pos", string2);
                this.screenSpecificMap.put(AdsConstants.PG, String.valueOf(this.mNumberOfAds));
                this.inlineScreenSpecificMap.putAll(this.screenSpecificMap);
                hashMap.clear();
                hashMap.putAll(this.screenSpecificMap);
            } else {
                String string3 = getResources().getString(R.string.banner_ad_pos);
                Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.banner_ad_pos)");
                this.screenSpecificMap.put("pos", string3);
                this.bannerScreenSpecificMap.putAll(this.screenSpecificMap);
                hashMap.clear();
                hashMap.putAll(this.screenSpecificMap);
            }
            this.adBidder.makeADCallWithBidding(context, (HashMap<String, String>) hashMap, nativeAdAction2);
        }
    }

    /* access modifiers changed from: protected */
    public void setupFirstFragment() {
        this.fragment = new QuestionPagerFragment().newInstance(this.contentItem, Boolean.valueOf(this.isSubCalc), this.subCalcQuestion, this.subCalcQuestionLinkedCalcIndex, this.selectedQuestionIndex, this.allQuestionsAlreadyAnswered);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        QuestionPagerFragment questionPagerFragment = this.fragment;
        if (questionPagerFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        }
        beginTransaction.add((int) R.id.fragment_container, (Fragment) questionPagerFragment).commit();
    }

    public void onQuestionChanged() {
        if (this.isAdded) {
            LinearLayout linearLayout = this.mContainer;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContainer");
            }
            View view = this.adLayout;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adLayout");
            }
            linearLayout.removeView(view);
            this.isAdded = false;
        }
        requestBottomBannerAD();
    }

    public int getStatusBarColor() {
        if (Build.VERSION.SDK_INT >= 23) {
            return getResources().getColor(R.color.medscape_blue, getTheme());
        }
        return getResources().getColor(R.color.medscape_blue);
    }

    public int getActionBarColor() {
        if (Build.VERSION.SDK_INT >= 23) {
            return getResources().getColor(R.color.medscape_blue, getTheme());
        }
        return getResources().getColor(R.color.medscape_blue);
    }

    public final void setListValuesForVisibilityCheck(QxRecyclerViewAdapter qxRecyclerViewAdapter, RecyclerView recyclerView, boolean z) {
        Intrinsics.checkNotNullParameter(qxRecyclerViewAdapter, Constants.APPBOY_PUSH_CONTENT_KEY);
        Intrinsics.checkNotNullParameter(recyclerView, "l");
        this.adapter = qxRecyclerViewAdapter;
        this.listView = recyclerView;
        this.isInlineADcallComplete = z;
    }
}
