package com.medscape.android.drugs.details.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.InformationWebViewFragment;
import com.medscape.android.ads.AdsConstants;
import com.medscape.android.ads.AdsSegvarIntf;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.ads.bidding.AdBidder;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.contentviewer.interfaces.ILoadNextListener;
import com.medscape.android.drugs.details.util.AdHelper;
import com.medscape.android.drugs.details.util.DrugDetailsOmnitureHelper;
import com.medscape.android.drugs.details.viewmodels.DrugDetailsActivityViewModel;
import com.medscape.android.drugs.details.viewmodels.DrugSectionViewModel;
import com.medscape.android.drugs.model.DrugMonographIndexElement;
import com.medscape.android.util.Util;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.wbmd.wbmdcommons.receivers.ShareDataObservable;
import com.wbmd.wbmdcommons.receivers.ShareReceiver;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0006\u0010<\u001a\u00020=J\u0006\u0010>\u001a\u00020=J\b\u0010?\u001a\u00020@H\u0002J\u000e\u0010A\u001a\u00020=2\u0006\u0010B\u001a\u00020CJ\u0012\u0010D\u001a\u00020=2\b\u0010E\u001a\u0004\u0018\u00010FH\u0014J\u0012\u0010G\u001a\u00020\u00182\b\u0010H\u001a\u0004\u0018\u00010IH\u0016J0\u0010J\u001a\u00020=2\f\u0010K\u001a\b\u0012\u0002\b\u0003\u0018\u00010L2\b\u0010M\u001a\u0004\u0018\u00010\r2\u0006\u0010N\u001a\u00020\u001d2\u0006\u0010O\u001a\u00020PH\u0016J\u0018\u0010Q\u001a\u00020\u00182\u0006\u0010R\u001a\u00020\u001d2\u0006\u0010H\u001a\u00020IH\u0016J\u0016\u0010S\u001a\u00020=2\f\u0010K\u001a\b\u0012\u0002\b\u0003\u0018\u00010LH\u0016J\u0010\u0010T\u001a\u00020\u00182\u0006\u0010U\u001a\u00020VH\u0016J\u0012\u0010W\u001a\u00020\u00182\b\u0010H\u001a\u0004\u0018\u00010IH\u0016J\b\u0010X\u001a\u00020=H\u0002J\b\u0010Y\u001a\u00020=H\u0016J\b\u0010Z\u001a\u00020=H\u0002J\b\u0010[\u001a\u00020=H\u0002J\b\u0010\\\u001a\u00020=H\u0002J\u0006\u0010]\u001a\u00020=J\u001c\u0010^\u001a\u00020=2\b\u0010_\u001a\u0004\u0018\u00010`2\b\u0010a\u001a\u0004\u0018\u00010bH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X.¢\u0006\u0002\n\u0000R&\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0+X.¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u00020\u0016X.¢\u0006\u0002\n\u0000R\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020\u001a02X.¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u001a\u00104\u001a\u000205X.¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u000e\u0010:\u001a\u00020;X.¢\u0006\u0002\n\u0000¨\u0006c"}, d2 = {"Lcom/medscape/android/drugs/details/views/DrugDetailsActivity;", "Lcom/medscape/android/base/BaseActivity;", "Landroid/widget/AdapterView$OnItemSelectedListener;", "Lcom/medscape/android/ads/AdsSegvarIntf;", "Ljava/util/Observer;", "()V", "adBidder", "Lcom/medscape/android/ads/bidding/AdBidder;", "assetId", "", "closeFind", "Landroid/widget/ImageView;", "contentContainer", "Landroid/view/View;", "drugSectionViewModel", "Lcom/medscape/android/drugs/details/viewmodels/DrugSectionViewModel;", "getDrugSectionViewModel", "()Lcom/medscape/android/drugs/details/viewmodels/DrugSectionViewModel;", "setDrugSectionViewModel", "(Lcom/medscape/android/drugs/details/viewmodels/DrugSectionViewModel;)V", "findContainer", "mDrugNameTitle", "Landroid/widget/TextView;", "mIsBannerADavailable", "", "mSectionElement", "Lcom/medscape/android/drugs/model/DrugMonographIndexElement;", "mSectionSet", "Ljava/util/HashSet;", "", "mToolbarSpinner", "Landroid/widget/Spinner;", "getMToolbarSpinner", "()Landroid/widget/Spinner;", "setMToolbarSpinner", "(Landroid/widget/Spinner;)V", "nextSearch", "omnitureHelper", "Lcom/medscape/android/drugs/details/util/DrugDetailsOmnitureHelper;", "prevSearch", "queryEditText", "Landroid/widget/EditText;", "screenMap", "Ljava/util/HashMap;", "getScreenMap", "()Ljava/util/HashMap;", "setScreenMap", "(Ljava/util/HashMap;)V", "searchCount", "spinnerAdapter", "Landroid/widget/ArrayAdapter;", "spinnerContainer", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "getToolbar", "()Landroidx/appcompat/widget/Toolbar;", "setToolbar", "(Landroidx/appcompat/widget/Toolbar;)V", "toolbarViewModel", "Lcom/medscape/android/drugs/details/viewmodels/DrugDetailsActivityViewModel;", "forceHideBannerAD", "", "getAd", "getDrugPricingControllerFragment", "Landroidx/fragment/app/Fragment;", "makeShareThroughADRequest", "nativeAdAction", "Lcom/medscape/android/ads/NativeAdAction;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onItemSelected", "parent", "Landroid/widget/AdapterView;", "view", "position", "id", "", "onMenuOpened", "featureId", "onNothingSelected", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "resetADStuff", "setScreenSpecificMap", "setUpListeners", "setUpObservers", "setUpViews", "showHiddenBannerAD", "update", "observable", "Ljava/util/Observable;", "arg", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivity.kt */
public class DrugDetailsActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, AdsSegvarIntf, Observer {
    private HashMap _$_findViewCache;
    private AdBidder adBidder = new AdBidder();
    private String assetId;
    private ImageView closeFind;
    /* access modifiers changed from: private */
    public View contentContainer;
    public DrugSectionViewModel drugSectionViewModel;
    /* access modifiers changed from: private */
    public View findContainer;
    private TextView mDrugNameTitle;
    /* access modifiers changed from: private */
    public boolean mIsBannerADavailable;
    private DrugMonographIndexElement mSectionElement;
    private HashSet<Integer> mSectionSet = SetsKt.hashSetOf(0, 3, 4, 5, 6, 10, 11, 90);
    public Spinner mToolbarSpinner;
    private ImageView nextSearch;
    private DrugDetailsOmnitureHelper omnitureHelper;
    private ImageView prevSearch;
    /* access modifiers changed from: private */
    public EditText queryEditText;
    public HashMap<String, String> screenMap;
    /* access modifiers changed from: private */
    public TextView searchCount;
    private ArrayAdapter<DrugMonographIndexElement> spinnerAdapter;
    private View spinnerContainer;
    public Toolbar toolbar;
    /* access modifiers changed from: private */
    public DrugDetailsActivityViewModel toolbarViewModel;

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

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    public static final /* synthetic */ View access$getContentContainer$p(DrugDetailsActivity drugDetailsActivity) {
        View view = drugDetailsActivity.contentContainer;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentContainer");
        }
        return view;
    }

    public static final /* synthetic */ View access$getFindContainer$p(DrugDetailsActivity drugDetailsActivity) {
        View view = drugDetailsActivity.findContainer;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("findContainer");
        }
        return view;
    }

    public static final /* synthetic */ EditText access$getQueryEditText$p(DrugDetailsActivity drugDetailsActivity) {
        EditText editText = drugDetailsActivity.queryEditText;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("queryEditText");
        }
        return editText;
    }

    public static final /* synthetic */ TextView access$getSearchCount$p(DrugDetailsActivity drugDetailsActivity) {
        TextView textView = drugDetailsActivity.searchCount;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchCount");
        }
        return textView;
    }

    public static final /* synthetic */ DrugDetailsActivityViewModel access$getToolbarViewModel$p(DrugDetailsActivity drugDetailsActivity) {
        DrugDetailsActivityViewModel drugDetailsActivityViewModel = drugDetailsActivity.toolbarViewModel;
        if (drugDetailsActivityViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        return drugDetailsActivityViewModel;
    }

    public final Spinner getMToolbarSpinner() {
        Spinner spinner = this.mToolbarSpinner;
        if (spinner == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mToolbarSpinner");
        }
        return spinner;
    }

    public final void setMToolbarSpinner(Spinner spinner) {
        Intrinsics.checkNotNullParameter(spinner, "<set-?>");
        this.mToolbarSpinner = spinner;
    }

    public final DrugSectionViewModel getDrugSectionViewModel() {
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        return drugSectionViewModel2;
    }

    public final void setDrugSectionViewModel(DrugSectionViewModel drugSectionViewModel2) {
        Intrinsics.checkNotNullParameter(drugSectionViewModel2, "<set-?>");
        this.drugSectionViewModel = drugSectionViewModel2;
    }

    public final Toolbar getToolbar() {
        Toolbar toolbar2 = this.toolbar;
        if (toolbar2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
        }
        return toolbar2;
    }

    public final void setToolbar(Toolbar toolbar2) {
        Intrinsics.checkNotNullParameter(toolbar2, "<set-?>");
        this.toolbar = toolbar2;
    }

    public final HashMap<String, String> getScreenMap() {
        HashMap<String, String> hashMap = this.screenMap;
        if (hashMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenMap");
        }
        return hashMap;
    }

    public final void setScreenMap(HashMap<String, String> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
        this.screenMap = hashMap;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_drug_details);
        setSupportActionBar((Toolbar) _$_findCachedViewById(R.id.drug_details_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ColorDrawable colorDrawable = new ColorDrawable(ContextCompat.getColor(this, R.color.medscape_blue));
        ActionBar supportActionBar2 = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar2);
        supportActionBar2.setBackgroundDrawable(colorDrawable);
        if (getIntent() == null || getIntent().getSerializableExtra("drug") == null) {
            finish();
            return;
        }
        setupNativeBottomBannerAd(DFPReferenceAdListener.AD_UNIT_ID);
        this.omnitureHelper = new DrugDetailsOmnitureHelper();
        FragmentActivity fragmentActivity = this;
        ViewModel viewModel = ViewModelProviders.of(fragmentActivity).get(DrugDetailsActivityViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProviders.of(th…ityViewModel::class.java)");
        this.toolbarViewModel = (DrugDetailsActivityViewModel) viewModel;
        ViewModel viewModel2 = ViewModelProviders.of(fragmentActivity).get(DrugSectionViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProviders.of(th…ionViewModel::class.java)");
        this.drugSectionViewModel = (DrugSectionViewModel) viewModel2;
        DrugDetailsActivityViewModel drugDetailsActivityViewModel = this.toolbarViewModel;
        if (drugDetailsActivityViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        drugDetailsActivityViewModel.setUp(intent);
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        Intent intent2 = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent2, "intent");
        drugSectionViewModel2.setUp(intent2);
        setScreenSpecificMap();
        setUpViews();
        setUpListeners();
        setUpObservers();
        TextView textView = this.mDrugNameTitle;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mDrugNameTitle");
        }
        DrugDetailsActivityViewModel drugDetailsActivityViewModel2 = this.toolbarViewModel;
        if (drugDetailsActivityViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        textView.setText(drugDetailsActivityViewModel2.getToolbarHeader());
        PlatformRouteDispatcher platformRouteDispatcher = new PlatformRouteDispatcher(this, false, true);
        DrugSectionViewModel drugSectionViewModel3 = this.drugSectionViewModel;
        if (drugSectionViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        platformRouteDispatcher.routeEvent(String.valueOf(drugSectionViewModel3.getDrugSectionContentRepo().getContentId()));
    }

    public final void getAd() {
        ArrayList<Integer> arrayList;
        DrugDetailsActivityViewModel drugDetailsActivityViewModel = this.toolbarViewModel;
        if (drugDetailsActivityViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        Context context = this;
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        String tabName = drugSectionViewModel2.getTabName();
        HashMap<String, String> hashMap = this.screenMap;
        if (hashMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenMap");
        }
        drugDetailsActivityViewModel.sendCpEvent(context, tabName, hashMap);
        resetADStuff();
        if (this.mPvid != null) {
            HashMap<String, String> hashMap2 = this.screenMap;
            if (hashMap2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenMap");
            }
            String str = this.mPvid;
            Intrinsics.checkNotNullExpressionValue(str, "mPvid");
            hashMap2.put("pvid", str);
        }
        if (this.nativeAdAction != null) {
            NativeAdAction nativeAdAction = this.nativeAdAction;
            Intrinsics.checkNotNullExpressionValue(nativeAdAction, "nativeAdAction");
            this.nativeAdAction.prepADWithCombinedRequests(new DrugDetailsActivity$getAd$1(this), nativeAdAction.getBottomBannerADsizes());
        }
        DrugMonographIndexElement drugMonographIndexElement = this.mSectionElement;
        Integer num = null;
        Collection collection = drugMonographIndexElement != null ? drugMonographIndexElement.indexes : null;
        if (!(collection == null || collection.isEmpty())) {
            HashMap<String, String> hashMap3 = this.screenMap;
            if (hashMap3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenMap");
            }
            Map map = hashMap3;
            DrugMonographIndexElement drugMonographIndexElement2 = this.mSectionElement;
            if (!(drugMonographIndexElement2 == null || (arrayList = drugMonographIndexElement2.indexes) == null)) {
                num = arrayList.get(0);
            }
            map.put(AdsConstants.MEDTAB, String.valueOf(num));
        }
        AdBidder adBidder2 = this.adBidder;
        HashMap<String, String> hashMap4 = this.screenMap;
        if (hashMap4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenMap");
        }
        NativeAdAction nativeAdAction2 = this.nativeAdAction;
        Intrinsics.checkNotNullExpressionValue(nativeAdAction2, "nativeAdAction");
        adBidder2.makeADCallWithBidding(context, hashMap4, nativeAdAction2);
    }

    private final void setUpObservers() {
        DrugDetailsActivityViewModel drugDetailsActivityViewModel = this.toolbarViewModel;
        if (drugDetailsActivityViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        drugDetailsActivityViewModel.isSearchMode().observe(this, new DrugDetailsActivity$setUpObservers$1(this));
    }

    private final void setUpViews() {
        Spinner spinner = (Spinner) _$_findCachedViewById(R.id.toolbar_spinner);
        Intrinsics.checkNotNullExpressionValue(spinner, "toolbar_spinner");
        this.mToolbarSpinner = spinner;
        TextView textView = (TextView) _$_findCachedViewById(R.id.drugGenericName);
        Intrinsics.checkNotNullExpressionValue(textView, "drugGenericName");
        this.mDrugNameTitle = textView;
        ImageView imageView = (ImageView) _$_findCachedViewById(R.id.close_find);
        Intrinsics.checkNotNullExpressionValue(imageView, "close_find");
        this.closeFind = imageView;
        EditText editText = (EditText) _$_findCachedViewById(R.id.find_query);
        Intrinsics.checkNotNullExpressionValue(editText, "find_query");
        this.queryEditText = editText;
        ImageView imageView2 = (ImageView) _$_findCachedViewById(R.id.next_find);
        Intrinsics.checkNotNullExpressionValue(imageView2, "next_find");
        this.nextSearch = imageView2;
        ImageView imageView3 = (ImageView) _$_findCachedViewById(R.id.prev_find);
        Intrinsics.checkNotNullExpressionValue(imageView3, "prev_find");
        this.prevSearch = imageView3;
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.find_counter);
        Intrinsics.checkNotNullExpressionValue(textView2, "find_counter");
        this.searchCount = textView2;
        View findViewById = findViewById(R.id.find_actionbar_container);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.find_actionbar_container)");
        this.findContainer = findViewById;
        View findViewById2 = findViewById(R.id.drug_details_spinner_container);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.drug_details_spinner_container)");
        this.spinnerContainer = findViewById2;
        View findViewById3 = findViewById(R.id.drug_details_container);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(R.id.drug_details_container)");
        this.contentContainer = findViewById3;
        View findViewById4 = findViewById(R.id.drug_details_toolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(R.id.drug_details_toolbar)");
        this.toolbar = (Toolbar) findViewById4;
        Context context = this;
        DrugDetailsActivityViewModel drugDetailsActivityViewModel = this.toolbarViewModel;
        if (drugDetailsActivityViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        ArrayAdapter<DrugMonographIndexElement> arrayAdapter = new ArrayAdapter<>(context, R.layout.toolbar_spinner_item_actionbar, drugDetailsActivityViewModel.getSpinnerOptions());
        this.spinnerAdapter = arrayAdapter;
        if (arrayAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerAdapter");
        }
        arrayAdapter.setDropDownViewResource(R.layout.toolbar_spinner_item_dropdown);
        Spinner spinner2 = this.mToolbarSpinner;
        if (spinner2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mToolbarSpinner");
        }
        ArrayAdapter<DrugMonographIndexElement> arrayAdapter2 = this.spinnerAdapter;
        if (arrayAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("spinnerAdapter");
        }
        spinner2.setAdapter(arrayAdapter2);
        Spinner spinner3 = this.mToolbarSpinner;
        if (spinner3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mToolbarSpinner");
        }
        spinner3.setOnItemSelectedListener(this);
        Spinner spinner4 = this.mToolbarSpinner;
        if (spinner4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mToolbarSpinner");
        }
        DrugDetailsActivityViewModel drugDetailsActivityViewModel2 = this.toolbarViewModel;
        if (drugDetailsActivityViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        spinner4.setSelection(drugDetailsActivityViewModel2.getSelectedSpinnerOption());
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        DrugDetailsActivityViewModel drugDetailsActivityViewModel3 = this.toolbarViewModel;
        if (drugDetailsActivityViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        drugSectionViewModel2.setTabFlag(drugDetailsActivityViewModel3.getSelectedSpinnerOption());
    }

    private final void setUpListeners() {
        ImageView imageView = this.closeFind;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeFind");
        }
        imageView.setOnClickListener(new DrugDetailsActivity$setUpListeners$1(this));
        EditText editText = this.queryEditText;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("queryEditText");
        }
        editText.setOnEditorActionListener(new DrugDetailsActivity$setUpListeners$2(this));
        EditText editText2 = this.queryEditText;
        if (editText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("queryEditText");
        }
        editText2.addTextChangedListener(new DrugDetailsActivity$setUpListeners$3(this));
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        drugSectionViewModel2.getFindCount().observe(this, new DrugDetailsActivity$setUpListeners$4(this));
        ImageView imageView2 = this.nextSearch;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextSearch");
        }
        imageView2.setOnClickListener(new DrugDetailsActivity$setUpListeners$5(this));
        ImageView imageView3 = this.prevSearch;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("prevSearch");
        }
        imageView3.setOnClickListener(new DrugDetailsActivity$setUpListeners$6(this));
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        Fragment fragment;
        DrugDetailsActivityViewModel drugDetailsActivityViewModel = this.toolbarViewModel;
        if (drugDetailsActivityViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        this.mSectionElement = drugDetailsActivityViewModel.getSpinnerOptions().get(i);
        DrugDetailsActivityViewModel drugDetailsActivityViewModel2 = this.toolbarViewModel;
        if (drugDetailsActivityViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        drugDetailsActivityViewModel2.setGoingToNextSection(true);
        DrugDetailsActivityViewModel drugDetailsActivityViewModel3 = this.toolbarViewModel;
        if (drugDetailsActivityViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        drugDetailsActivityViewModel3.setSelectedSpinnerOption(i);
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        drugSectionViewModel2.getCurrentIndex().setValue(new Pair(Integer.valueOf(i), 0));
        DrugDetailsActivityViewModel drugDetailsActivityViewModel4 = this.toolbarViewModel;
        if (drugDetailsActivityViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        drugDetailsActivityViewModel4.setGoingToNextSection(false);
        DrugDetailsOmnitureHelper drugDetailsOmnitureHelper = this.omnitureHelper;
        if (drugDetailsOmnitureHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("omnitureHelper");
        }
        DrugSectionViewModel drugSectionViewModel3 = this.drugSectionViewModel;
        if (drugSectionViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        drugDetailsOmnitureHelper.sendPageChangeOmniture(drugSectionViewModel3, this, this.assetId);
        DrugSectionViewModel drugSectionViewModel4 = this.drugSectionViewModel;
        if (drugSectionViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        if (drugSectionViewModel4.isImageSection()) {
            fragment = getDrugPricingControllerFragment();
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("assetId", this.assetId);
            Fragment drugDetailsActivityFragment = new DrugDetailsActivityFragment();
            ((DrugDetailsActivityFragment) drugDetailsActivityFragment).setArguments(bundle);
            fragment = drugDetailsActivityFragment;
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(beginTransaction, "supportFragmentManager.beginTransaction()");
        beginTransaction.replace(R.id.drug_details_container, fragment);
        beginTransaction.commitAllowingStateLoss();
        invalidateOptionsMenu();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem findItem;
        MenuItem findItem2;
        DrugDetailsActivity drugDetailsActivity = this;
        if (drugDetailsActivity.toolbarViewModel != null) {
            MenuInflater menuInflater = getMenuInflater();
            DrugDetailsActivityViewModel drugDetailsActivityViewModel = this.toolbarViewModel;
            if (drugDetailsActivityViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
            }
            menuInflater.inflate(drugDetailsActivityViewModel.getOptionMenu(), menu);
        }
        if (drugDetailsActivity.drugSectionViewModel != null) {
            if (!(menu == null || (findItem2 = menu.findItem(R.id.action_add_to_interactions)) == null)) {
                DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
                if (drugSectionViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
                }
                findItem2.setVisible(drugSectionViewModel2.hasInteractions());
            }
            if (!(menu == null || (findItem = menu.findItem(R.id.action_find)) == null)) {
                DrugSectionViewModel drugSectionViewModel3 = this.drugSectionViewModel;
                if (drugSectionViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
                }
                findItem.setVisible(!drugSectionViewModel3.isImageSection());
            }
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onOptionsItemSelected(android.view.MenuItem r19) {
        /*
            r18 = this;
            r0 = r18
            java.lang.String r1 = "item"
            r2 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            int r1 = r19.getItemId()
            r3 = 0
            java.lang.String r4 = "drugSectionViewModel"
            java.lang.String r5 = "toolbarViewModel"
            r6 = 1
            switch(r1) {
                case 16908332: goto L_0x0167;
                case 2131361964: goto L_0x0147;
                case 2131361990: goto L_0x012b;
                case 2131362004: goto L_0x010c;
                case 2131362005: goto L_0x00bd;
                case 2131362010: goto L_0x001c;
                default: goto L_0x0016;
            }
        L_0x0016:
            boolean r6 = super.onOptionsItemSelected(r19)
            goto L_0x016a
        L_0x001c:
            com.medscape.android.drugs.details.viewmodels.DrugSectionViewModel r1 = r0.drugSectionViewModel
            if (r1 != 0) goto L_0x0023
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L_0x0023:
            androidx.lifecycle.MutableLiveData r1 = r1.getCurrentIndex()
            java.lang.Object r1 = r1.getValue()
            kotlin.Pair r1 = (kotlin.Pair) r1
            com.medscape.android.drugs.model.DrugMonographIndexElement r2 = r0.mSectionElement
            if (r2 == 0) goto L_0x0062
            if (r1 == 0) goto L_0x0062
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.util.ArrayList<java.lang.Integer> r2 = r2.indexes
            java.lang.Object r1 = r1.getSecond()
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            java.lang.Object r1 = r2.get(r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            java.util.HashSet<java.lang.Integer> r2 = r0.mSectionSet
            boolean r2 = r2.contains(r1)
            if (r2 == 0) goto L_0x0062
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r4 = 35
            r2.append(r4)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            goto L_0x0064
        L_0x0062:
            java.lang.String r1 = ""
        L_0x0064:
            r11 = r1
            com.wbmd.wbmdcommons.receivers.ShareReceiver r1 = new com.wbmd.wbmdcommons.receivers.ShareReceiver
            r1.<init>()
            r7 = r0
            android.content.Context r7 = (android.content.Context) r7
            android.app.PendingIntent r1 = r1.createPendingIntent(r7)
            int r2 = android.os.Build.VERSION.SDK_INT
            r4 = 22
            if (r2 > r4) goto L_0x008f
            android.app.PendingIntent r3 = (android.app.PendingIntent) r3
            com.medscape.android.BI.omniture.OmnitureManager r12 = com.medscape.android.BI.omniture.OmnitureManager.get()
            com.wbmd.wbmdcommons.receivers.ShareReceiver$Companion r1 = com.wbmd.wbmdcommons.receivers.ShareReceiver.Companion
            java.lang.String r15 = r1.getSHARE_MODULE_CONTENT()
            r17 = 0
            java.lang.String r14 = "reference and tools"
            java.lang.String r16 = "app"
            r13 = r7
            r12.trackModule(r13, r14, r15, r16, r17)
            r12 = r3
            goto L_0x0098
        L_0x008f:
            com.wbmd.wbmdcommons.receivers.ShareDataObservable r2 = com.wbmd.wbmdcommons.receivers.ShareDataObservable.INSTANCE
            r3 = r0
            java.util.Observer r3 = (java.util.Observer) r3
            r2.addObserver(r3)
            r12 = r1
        L_0x0098:
            com.medscape.android.drugs.details.viewmodels.DrugDetailsActivityViewModel r1 = r0.toolbarViewModel
            if (r1 != 0) goto L_0x009f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x009f:
            java.lang.String r8 = r1.getContentLink()
            com.medscape.android.drugs.details.viewmodels.DrugDetailsActivityViewModel r1 = r0.toolbarViewModel
            if (r1 != 0) goto L_0x00aa
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x00aa:
            java.lang.String r9 = r1.getDrugName()
            com.medscape.android.drugs.details.viewmodels.DrugDetailsActivityViewModel r1 = r0.toolbarViewModel
            if (r1 != 0) goto L_0x00b5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x00b5:
            java.lang.String r10 = r1.getSubjetForShareEmail()
            com.wbmd.wbmdcommons.utils.Util.share(r7, r8, r9, r10, r11, r12)
            return r6
        L_0x00bd:
            com.medscape.android.drugs.details.viewmodels.DrugDetailsActivityViewModel r1 = r0.toolbarViewModel
            if (r1 != 0) goto L_0x00c4
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x00c4:
            r1.updateContentSaveStatus()
            r18.invalidateOptionsMenu()
            r1 = r0
            android.content.Context r1 = (android.content.Context) r1
            r2 = r0
            android.app.Activity r2 = (android.app.Activity) r2
            java.lang.String r4 = "drugSaved"
            com.medscape.android.appboy.AppboyEventsHandler.logDailyEvent(r1, r4, r2)
            java.lang.String r2 = "reference and tools"
            java.lang.String r4 = "drugs"
            com.medscape.android.MedscapeMenu.sendSaveBIPings(r1, r2, r4)
            r2 = 24
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            com.medscape.android.drugs.details.viewmodels.DrugDetailsActivityViewModel r7 = r0.toolbarViewModel
            if (r7 != 0) goto L_0x00ea
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x00ea:
            java.lang.String r5 = r7.getDrugName()
            r4.append(r5)
            java.lang.String r5 = " "
            r4.append(r5)
            r5 = 2131951766(0x7f130096, float:1.9539956E38)
            java.lang.String r5 = r0.getString(r5)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.app.AlertDialog r1 = com.medscape.android.util.DialogUtil.showAlertDialog(r2, r3, r4, r1)
            r1.show()
            goto L_0x016a
        L_0x010c:
            com.medscape.android.drugs.details.viewmodels.DrugDetailsActivityViewModel r1 = r0.toolbarViewModel
            if (r1 != 0) goto L_0x0113
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x0113:
            r1.updateContentSaveStatus()
            com.medscape.android.BI.omniture.OmnitureManager r7 = com.medscape.android.BI.omniture.OmnitureManager.get()
            r8 = r0
            android.content.Context r8 = (android.content.Context) r8
            r12 = 0
            java.lang.String r9 = "reference and tools"
            java.lang.String r10 = "save"
            java.lang.String r11 = "delete"
            r7.trackModule(r8, r9, r10, r11, r12)
            r18.invalidateOptionsMenu()
            goto L_0x016a
        L_0x012b:
            com.medscape.android.drugs.details.viewmodels.DrugDetailsActivityViewModel r1 = r0.toolbarViewModel
            if (r1 != 0) goto L_0x0132
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
        L_0x0132:
            r1.setSearchMode((boolean) r6)
            com.medscape.android.BI.omniture.OmnitureManager r7 = com.medscape.android.BI.omniture.OmnitureManager.get()
            r8 = r0
            android.content.Context r8 = (android.content.Context) r8
            r12 = 0
            java.lang.String r9 = "reference and tools"
            java.lang.String r10 = "find-on-page"
            java.lang.String r11 = "drgs"
            r7.trackModule(r8, r9, r10, r11, r12)
            return r6
        L_0x0147:
            android.content.Intent r1 = new android.content.Intent
            r2 = r0
            android.content.Context r2 = (android.content.Context) r2
            java.lang.Class<com.medscape.android.activity.interactions.DrugInteractionActivity> r3 = com.medscape.android.activity.interactions.DrugInteractionActivity.class
            r1.<init>(r2, r3)
            com.medscape.android.drugs.details.viewmodels.DrugSectionViewModel r2 = r0.drugSectionViewModel
            if (r2 != 0) goto L_0x0158
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L_0x0158:
            com.medscape.android.db.model.Drug r2 = r2.getDrugForInteraction()
            java.io.Serializable r2 = (java.io.Serializable) r2
            java.lang.String r3 = "drug"
            r1.putExtra(r3, r2)
            r0.startActivity(r1)
            return r6
        L_0x0167:
            r18.onBackPressed()
        L_0x016a:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.details.views.DrugDetailsActivity.onOptionsItemSelected(android.view.MenuItem):boolean");
    }

    private final Fragment getDrugPricingControllerFragment() {
        Fragment newInstance = InformationWebViewFragment.newInstance((ILoadNextListener) null);
        Bundle bundle = new Bundle();
        StringBuilder sb = new StringBuilder();
        sb.append("https://reference.medscape.com/features/mobileutils/drug-images?");
        DrugDetailsActivityViewModel drugDetailsActivityViewModel = this.toolbarViewModel;
        if (drugDetailsActivityViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        sb.append(drugDetailsActivityViewModel.getContentId());
        bundle.putParcelable("uri", Uri.parse(sb.toString()));
        bundle.putString("contentType", "reference");
        DrugDetailsActivityViewModel drugDetailsActivityViewModel2 = this.toolbarViewModel;
        if (drugDetailsActivityViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        bundle.putString("header", drugDetailsActivityViewModel2.getDrugName());
        DrugDetailsActivityViewModel drugDetailsActivityViewModel3 = this.toolbarViewModel;
        if (drugDetailsActivityViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        bundle.putInt("contentId", drugDetailsActivityViewModel3.getContentId());
        Intrinsics.checkNotNullExpressionValue(newInstance, "informationWebViewFragment");
        newInstance.setArguments(bundle);
        return newInstance;
    }

    public void setScreenSpecificMap() {
        String str;
        AdHelper adHelper = new AdHelper();
        DrugDetailsActivityViewModel drugDetailsActivityViewModel = this.toolbarViewModel;
        if (drugDetailsActivityViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        this.screenMap = adHelper.getDrugSectionScreenMap(drugDetailsActivityViewModel.getContentId(), this);
        Activity activity = this;
        DrugDetailsActivityViewModel drugDetailsActivityViewModel2 = this.toolbarViewModel;
        if (drugDetailsActivityViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbarViewModel");
        }
        Util.sendFirebaseContentInfo(activity, AdParameterKeys.DRUG_ID, String.valueOf(drugDetailsActivityViewModel2.getContentId()));
        HashMap<String, String> hashMap = this.screenMap;
        if (hashMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenMap");
        }
        if (hashMap.containsKey("article-id")) {
            HashMap<String, String> hashMap2 = this.screenMap;
            if (hashMap2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("screenMap");
            }
            str = hashMap2.get("article-id");
        } else {
            str = null;
        }
        this.assetId = str;
        DrugDetailsOmnitureHelper drugDetailsOmnitureHelper = this.omnitureHelper;
        if (drugDetailsOmnitureHelper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("omnitureHelper");
        }
        drugDetailsOmnitureHelper.setMOmnitureContentData(adHelper.getMOmnitureContentData());
    }

    public final void forceHideBannerAD() {
        if (!this.nativeAdAction.adDismissed) {
            if (this.mIsBannerADavailable && !this.nativeAdAction.isForceHideAD) {
                Util.hideViewAnimation(this, this.adLayout);
            }
            this.nativeAdAction.isForceHideAD = true;
            View view = this.contentContainer;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentContainer");
            }
            Util.setContainerRule(false, view, R.id.ad);
        }
    }

    public final void showHiddenBannerAD() {
        if (!this.nativeAdAction.adDismissed && this.nativeAdAction.isForceHideAD && this.mIsBannerADavailable) {
            Util.showViewAnimation(this, this.adLayout);
            this.nativeAdAction.isForceHideAD = false;
            View view = this.contentContainer;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentContainer");
            }
            Util.setContainerRule(true, view, R.id.ad);
        }
    }

    private final void resetADStuff() {
        this.nativeAdAction.adDismissed = false;
        this.nativeAdAction.isForceHideAD = false;
        this.mIsBannerADavailable = false;
        View view = this.adLayout;
        Intrinsics.checkNotNullExpressionValue(view, "adLayout");
        view.setVisibility(8);
        View view2 = this.contentContainer;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentContainer");
        }
        Util.setContainerRule(false, view2, R.id.ad);
        this.nativeAdAction.clearGlobalMap();
    }

    public final void makeShareThroughADRequest(NativeAdAction nativeAdAction) {
        Intrinsics.checkNotNullParameter(nativeAdAction, "nativeAdAction");
        HashMap hashMap = new HashMap();
        AdHelper.Companion companion = AdHelper.Companion;
        HashMap<String, String> hashMap2 = this.screenMap;
        if (hashMap2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("screenMap");
        }
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        hashMap.putAll(companion.getUpdatedADParams(hashMap2, drugSectionViewModel2));
        Map map = hashMap;
        String string = getResources().getString(R.string.sharethrough_ad_pos);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.sharethrough_ad_pos)");
        map.put("pos", string);
        if (this.mPvid != null) {
            String str = this.mPvid;
            Intrinsics.checkNotNullExpressionValue(str, "mPvid");
            map.put("pvid", str);
        }
        this.adBidder.makeADCallWithBidding((Context) this, (HashMap<String, String>) hashMap, nativeAdAction);
    }

    public void update(Observable observable, Object obj) {
        if (observable instanceof ShareDataObservable) {
            if (obj instanceof String) {
                OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, ShareReceiver.Companion.getSHARE_MODULE_CONTENT(), obj.toString(), (Map<String, Object>) null);
            }
            ShareDataObservable.INSTANCE.deleteObserver(this);
        }
    }

    public boolean onMenuOpened(int i, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, Category.K_MENU_CATEGORY);
        OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "drg-drawer", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
        return super.onMenuOpened(i, menu);
    }
}
