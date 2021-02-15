package com.medscape.android.activity.calc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter;
import com.medscape.android.activity.calc.ads.InlineAdLoaded;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.saved.viewmodel.QxSaveViewModel;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.InlineAdTouchHelper;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.ads.bidding.AdBidder;
import com.medscape.android.util.Util;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.wbmd.adlibrary.utilities.AdScrollHandler;
import com.wbmd.ads.model.AdContentData;
import com.wbmd.omniture.utils.OmnitureHelper;
import com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity;
import com.wbmd.qxcalculator.activities.contentItems.ContentItemActivity;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.util.Log;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.wbmd.wbmdcommons.callbacks.IScrollEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u00106\u001a\u000207H\u0014J\u0010\u00108\u001a\u0002072\b\u0010&\u001a\u0004\u0018\u00010'J\u0012\u00109\u001a\u0002072\b\u0010:\u001a\u0004\u0018\u00010;H\u0014J\b\u0010<\u001a\u000207H\u0016J\u0010\u0010=\u001a\u00020\u00182\u0006\u0010>\u001a\u00020?H\u0016J\b\u0010@\u001a\u000207H\u0016J\u0012\u0010A\u001a\u00020\u00182\b\u0010B\u001a\u0004\u0018\u00010CH\u0016J\b\u0010D\u001a\u000207H\u0016J\b\u0010E\u001a\u000207H\u0016J\u0010\u0010F\u001a\u0002072\u0006\u0010G\u001a\u00020HH\u0014J\b\u0010I\u001a\u000207H\u0002J\b\u0010J\u001a\u000207H\u0014J\u0006\u0010K\u001a\u000207J\b\u0010L\u001a\u000207H\u0016J\b\u0010M\u001a\u000207H\u0002R\u0014\u0010\u0005\u001a\u00020\u0006XD¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R*\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0012j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006`\u0013X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\b\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X.¢\u0006\u0002\n\u0000R*\u0010/\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0012j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006`\u0013X\u0004¢\u0006\u0002\n\u0000R\u001a\u00100\u001a\u000201X.¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105¨\u0006N"}, d2 = {"Lcom/medscape/android/activity/calc/MedscapeCalculatorActivity;", "Lcom/wbmd/qxcalculator/activities/contentItems/CalculatorActivity;", "Lcom/wbmd/wbmdcommons/callbacks/IScrollEvent;", "Lcom/medscape/android/activity/calc/ads/InlineAdLoaded;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "adBidder", "Lcom/medscape/android/ads/bidding/AdBidder;", "adLayout", "Landroid/view/View;", "getAdLayout", "()Landroid/view/View;", "setAdLayout", "(Landroid/view/View;)V", "bannerScreenSpecificMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "dfpAdLayout", "getDfpAdLayout", "setDfpAdLayout", "isAdded", "", "()Z", "setAdded", "(Z)V", "mContainer", "Landroid/widget/LinearLayout;", "getMContainer", "()Landroid/widget/LinearLayout;", "setMContainer", "(Landroid/widget/LinearLayout;)V", "mPvid", "getMPvid", "setMPvid", "(Ljava/lang/String;)V", "nativeAdAction", "Lcom/medscape/android/ads/NativeAdAction;", "getNativeAdAction", "()Lcom/medscape/android/ads/NativeAdAction;", "setNativeAdAction", "(Lcom/medscape/android/ads/NativeAdAction;)V", "pclass", "qxSaveViewModel", "Lcom/medscape/android/activity/saved/viewmodel/QxSaveViewModel;", "screenSpecificMap", "user", "Lcom/wbmd/qxcalculator/model/db/DBUser;", "getUser", "()Lcom/wbmd/qxcalculator/model/db/DBUser;", "setUser", "(Lcom/wbmd/qxcalculator/model/db/DBUser;)V", "dialogDismissed", "", "getAd", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onInlineAdLoaded", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPreCacheEvent", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onResume", "onScrollThresholdReached", "openQuestion", "index", "", "requestBottomBannerAD", "sendPageNameForOmniture", "setScreenSpecificMap", "setupAdapter", "setupNativeBottomBannerAd", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MedscapeCalculatorActivity.kt */
public class MedscapeCalculatorActivity extends CalculatorActivity implements IScrollEvent, InlineAdLoaded {
    private final String TAG = "CalculatorActivity";
    private HashMap _$_findViewCache;
    private AdBidder adBidder = new AdBidder();
    public View adLayout;
    private final HashMap<String, String> bannerScreenSpecificMap = new HashMap<>();
    private View dfpAdLayout;
    private boolean isAdded;
    public LinearLayout mContainer;
    private String mPvid = "";
    private NativeAdAction nativeAdAction;
    private String pclass = "content";
    private QxSaveViewModel qxSaveViewModel;
    private final HashMap<String, String> screenSpecificMap = new HashMap<>();
    public DBUser user;

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

    public void onPreCacheEvent() {
    }

    public final String getTAG() {
        return this.TAG;
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

    public final View getDfpAdLayout() {
        return this.dfpAdLayout;
    }

    public final void setDfpAdLayout(View view) {
        this.dfpAdLayout = view;
    }

    public final NativeAdAction getNativeAdAction() {
        return this.nativeAdAction;
    }

    public final void setNativeAdAction(NativeAdAction nativeAdAction2) {
        this.nativeAdAction = nativeAdAction2;
    }

    public final String getMPvid() {
        return this.mPvid;
    }

    public final void setMPvid(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mPvid = str;
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

    public final DBUser getUser() {
        DBUser dBUser = this.user;
        if (dBUser == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        return dBUser;
    }

    public final void setUser(DBUser dBUser) {
        Intrinsics.checkNotNullParameter(dBUser, "<set-?>");
        this.user = dBUser;
    }

    public final boolean isAdded() {
        return this.isAdded;
    }

    public final void setAdded(boolean z) {
        this.isAdded = z;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new AdScrollHandler(this.listView, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, this);
        InlineAdTouchHelper inlineAdTouchHelper = new InlineAdTouchHelper();
        QxRecyclerView qxRecyclerView = this.listView;
        Intrinsics.checkNotNullExpressionValue(qxRecyclerView, "listView");
        InlineAdTouchHelper.applyTouchListener$default(inlineAdTouchHelper, qxRecyclerView, false, 2, (Object) null);
        this.qxSaveViewModel = (QxSaveViewModel) ViewModelProviders.of((FragmentActivity) this).get(new QxSaveViewModel().getClass());
        if (getIntent().hasExtra("pvid") && getIntent().getStringExtra("pvid") != null) {
            String stringExtra = getIntent().getStringExtra("pvid");
            Intrinsics.checkNotNull(stringExtra);
            this.mPvid = stringExtra;
        }
        View findViewById = findViewById(R.id.container);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<LinearLayout>(R.id.container)");
        this.mContainer = (LinearLayout) findViewById;
        setScreenSpecificMap();
        setupNativeBottomBannerAd();
        UserManager instance = UserManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "UserManager.getInstance()");
        DBUser dbUser = instance.getDbUser();
        Intrinsics.checkNotNullExpressionValue(dbUser, "UserManager.getInstance().dbUser");
        this.user = dbUser;
        if (dbUser == null) {
            Intrinsics.throwUninitializedPropertyAccessException("user");
        }
        if (!dbUser.getShouldShowPromptForAutoEnterFirstQuestion().booleanValue()) {
            requestBottomBannerAD();
        }
    }

    public void onResume() {
        super.onResume();
        QxSaveViewModel qxSaveViewModel2 = this.qxSaveViewModel;
        if (qxSaveViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qxSaveViewModel");
        }
        qxSaveViewModel2.saveToRecentlyViewed$medscape_release(this, this.contentItem);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuItem = null;
        MenuItem findItem = menu != null ? menu.findItem(R.id.action_fave) : null;
        if (menu != null) {
            menuItem = menu.findItem(R.id.action_share);
        }
        if (menuItem != null) {
            menuItem.setVisible(false);
        }
        if (findItem == null) {
            return true;
        }
        QxSaveViewModel qxSaveViewModel2 = this.qxSaveViewModel;
        if (qxSaveViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qxSaveViewModel");
        }
        qxSaveViewModel2.changeSaveIcon$medscape_release(this, this.contentItem, findItem);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        int itemId = menuItem.getItemId();
        Log.e("Calc", "Id : " + itemId);
        if (itemId != R.id.action_fave) {
            return super.onOptionsItemSelected(menuItem);
        }
        QxSaveViewModel qxSaveViewModel2 = this.qxSaveViewModel;
        if (qxSaveViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qxSaveViewModel");
        }
        Activity activity = this;
        qxSaveViewModel2.onOptionsItemSelected$medscape_release(activity, this.contentItem);
        QxSaveViewModel qxSaveViewModel3 = this.qxSaveViewModel;
        if (qxSaveViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("qxSaveViewModel");
        }
        qxSaveViewModel3.changeSaveIcon$medscape_release(activity, this.contentItem, menuItem);
        return true;
    }

    public void setupAdapter() {
        if (this.adapter == null) {
            ContentItem contentItem = this.contentItem;
            Intrinsics.checkNotNullExpressionValue(contentItem, "contentItem");
            this.adapter = new QxRecyclerViewAdapter(this, contentItem, this);
            this.adapter.setHasStableIds(false);
            this.adapter.setOnClickListener(this);
            this.adapter.setOnExpandCollapseListener(this);
        }
    }

    /* access modifiers changed from: protected */
    public void openQuestion(int i) {
        Intent intent = new Intent(this, QuestionPagerActivity.class);
        intent.putExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM, this.contentItem);
        intent.putExtra(CalculatorActivity.KEY_EXTRA_SELECTED_QUESTION, i);
        intent.putExtra(CalculatorActivity.KEY_EXTRA_IS_SUB_CALC, this.isSubCalc);
        intent.putExtra("pvid", this.mPvid);
        if (this.subCalcQuestion != null) {
            intent.putExtra(CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION, this.subCalcQuestion);
            intent.putExtra(CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION_LINKED_CALC_INDEX, this.subCalcQuestionLinkedCalcIndex);
        }
        if (this.contentItem.calculator.allQuestionsAnswered()) {
            intent.putExtra(CalculatorActivity.KEY_ALL_QUESTIONS_ALREADY_ANSWERED, true);
        }
        startActivityForResult(intent, 1, (Bundle) null);
    }

    public final void setScreenSpecificMap() {
        String string = getResources().getString(R.string.banner_ad_pos);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.banner_ad_pos)");
        this.screenSpecificMap.put("pos", string);
        this.screenSpecificMap.put("pc", this.pclass);
        if (this.contentItem.leadConcept != null) {
            String str = this.contentItem.leadConcept;
            Intrinsics.checkNotNullExpressionValue(str, "contentItem.leadConcept");
            this.screenSpecificMap.put(AdContentData.LEAD_CONCEPT, str);
        }
        if (this.contentItem.leadSpecialty != null) {
            String str2 = this.contentItem.leadSpecialty;
            Intrinsics.checkNotNullExpressionValue(str2, "contentItem.leadSpecialty");
            this.screenSpecificMap.put(AdContentData.LEAD_SPECIALITY, str2);
        }
        if (this.contentItem.allSpecialty != null) {
            String str3 = this.contentItem.allSpecialty;
            Intrinsics.checkNotNullExpressionValue(str3, "contentItem.allSpecialty");
            this.screenSpecificMap.put(AdContentData.RELATED_SPECIALITY, str3);
        }
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
                INativeDFPAdLoadListener medscapeCalculatorActivity$requestBottomBannerAD$1 = new MedscapeCalculatorActivity$requestBottomBannerAD$1(this);
                NativeAdAction nativeAdAction3 = this.nativeAdAction;
                nativeAdAction2.prepADWithCombinedRequests(medscapeCalculatorActivity$requestBottomBannerAD$1, nativeAdAction3 != null ? nativeAdAction3.getBottomBannerADsizes() : null);
            }
            getAd(this.nativeAdAction);
        }
    }

    public final void getAd(NativeAdAction nativeAdAction2) {
        Context context = this;
        if (Util.isOnline(context) && Util.getDisplayOrientation(context) == 0 && nativeAdAction2 != null) {
            this.screenSpecificMap.put("pvid", this.mPvid);
            HashMap hashMap = new HashMap();
            String string = getResources().getString(R.string.banner_ad_pos);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.banner_ad_pos)");
            this.screenSpecificMap.put("pos", string);
            this.bannerScreenSpecificMap.putAll(this.screenSpecificMap);
            hashMap.clear();
            hashMap.putAll(this.screenSpecificMap);
            this.adBidder.makeADCallWithBidding(context, (HashMap<String, String>) hashMap, nativeAdAction2);
        }
    }

    /* access modifiers changed from: protected */
    public void dialogDismissed() {
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

    /* access modifiers changed from: protected */
    public void sendPageNameForOmniture() {
        List arrayList = new ArrayList();
        if (this.contentItem.footer != null && StringsKt.equals(this.contentItem.footer, getString(R.string.sponsored), true)) {
            String string = getString(R.string.calc);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.wbmd.qxcalculator.R.string.calc)");
            arrayList.add(string);
            String string2 = getString(R.string.sponsored);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(com.wbmd.qxcal…lator.R.string.sponsored)");
            Locale locale = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(locale, "Locale.ROOT");
            if (string2 != null) {
                String lowerCase = string2.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                arrayList.add(lowerCase);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
        } else if (this.sectionCalculatorWasOpenedFrom == null || !StringsKt.equals(this.sectionCalculatorWasOpenedFrom, getString(R.string.search), true)) {
            String string3 = getString(R.string.calc);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(com.wbmd.qxcalculator.R.string.calc)");
            arrayList.add(string3);
        } else {
            String string4 = getString(R.string.search);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(com.wbmd.qxcalculator.R.string.search)");
            arrayList.add(string4);
            String string5 = getString(R.string.results_header);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(com.wbmd.qxcal….R.string.results_header)");
            arrayList.add(string5);
        }
        OmnitureHelper omnitureHelper = OmnitureHelper.INSTANCE;
        String str = this.contentItem.identifier;
        Intrinsics.checkNotNullExpressionValue(str, "contentItem.identifier");
        arrayList.add(omnitureHelper.getCalculatorIdForOmniture(str));
        String calcPageName = OmnitureHelper.INSTANCE.getCalcPageName(this.contentItem.name);
        if (calcPageName != null) {
            arrayList.add(calcPageName);
        }
        if (getIntent().getBooleanExtra(Constants.EXTRA_PUSH_LAUNCH, false)) {
            getIntent().removeExtra(Constants.EXTRA_PUSH_LAUNCH);
            OmnitureHelper.INSTANCE.sendOmniturePageViewWithActions(arrayList, "push", Constants.OMNITURE_MLINK_OPEN);
            return;
        }
        OmnitureHelper.INSTANCE.sendOmniturePageViewWithActions(arrayList, "brwslst", new UtilCalc().getMLinkForOmniture(this.contentItem.parentCategory));
    }

    public void onScrollThresholdReached() {
        UtilCalc utilCalc = new UtilCalc();
        com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
        Intrinsics.checkNotNullExpressionValue(qxRecyclerViewAdapter, "adapter");
        RecyclerView.Adapter adapter = qxRecyclerViewAdapter;
        QxRecyclerView qxRecyclerView = this.listView;
        Intrinsics.checkNotNullExpressionValue(qxRecyclerView, "listView");
        RecyclerView recyclerView = qxRecyclerView;
        View view = this.adLayout;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adLayout");
        }
        com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter2 = this.adapter;
        if (qxRecyclerViewAdapter2 != null) {
            utilCalc.setBannerAdVisibility(adapter, recyclerView, view, ((QxRecyclerViewAdapter) qxRecyclerViewAdapter2).isInlineADcallComplete());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter");
    }

    public void onInlineAdLoaded() {
        UtilCalc utilCalc = new UtilCalc();
        com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter = this.adapter;
        Intrinsics.checkNotNullExpressionValue(qxRecyclerViewAdapter, "adapter");
        RecyclerView.Adapter adapter = qxRecyclerViewAdapter;
        QxRecyclerView qxRecyclerView = this.listView;
        Intrinsics.checkNotNullExpressionValue(qxRecyclerView, "listView");
        RecyclerView recyclerView = qxRecyclerView;
        View view = this.adLayout;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adLayout");
        }
        com.qxmd.qxrecyclerview.QxRecyclerViewAdapter qxRecyclerViewAdapter2 = this.adapter;
        if (qxRecyclerViewAdapter2 != null) {
            utilCalc.setBannerAdVisibility(adapter, recyclerView, view, ((QxRecyclerViewAdapter) qxRecyclerViewAdapter2).isInlineADcallComplete());
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.adapters.QxRecyclerViewAdapter");
    }
}
