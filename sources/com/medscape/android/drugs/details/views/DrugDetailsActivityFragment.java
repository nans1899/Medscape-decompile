package com.medscape.android.drugs.details.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.medscape.android.R;
import com.medscape.android.activity.calc.helper.CalcOmnitureHelper;
import com.medscape.android.ads.AdRequestHelper;
import com.medscape.android.ads.InlineAdTouchHelper;
import com.medscape.android.ads.bidding.AdBidder;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.drugs.details.adapters.DrugSectionAdapter;
import com.medscape.android.drugs.details.datamodels.InlineAdLineItem;
import com.medscape.android.drugs.details.datamodels.LineItem;
import com.medscape.android.drugs.details.util.AdHelper;
import com.medscape.android.drugs.details.viewmodels.DrugDetailsActivityViewModel;
import com.medscape.android.drugs.details.viewmodels.DrugSectionViewModel;
import com.medscape.android.drugs.helper.DrugMonographViewHelper;
import com.medscape.android.util.Util;
import com.tonicartos.superslim.LayoutManager;
import com.wbmd.adlibrary.utilities.AdScrollHandler;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.wbmdcommons.callbacks.IScrollEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J \u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010*2\f\u0010.\u001a\b\u0012\u0002\b\u0003\u0018\u00010/H\u0002J\u0012\u00100\u001a\u00020,2\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0012\u00103\u001a\u00020,2\b\u00101\u001a\u0004\u0018\u000102H\u0016J&\u00104\u001a\u0004\u0018\u00010 2\u0006\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u0001082\b\u00101\u001a\u0004\u0018\u000102H\u0016J\u0010\u00109\u001a\u00020,2\u0006\u0010:\u001a\u00020;H\u0016J\b\u0010<\u001a\u00020,H\u0016J\u001c\u0010=\u001a\u00020,2\b\u0010>\u001a\u0004\u0018\u00010?2\b\u0010@\u001a\u0004\u0018\u000102H\u0016J\b\u0010A\u001a\u00020,H\u0016J\b\u0010B\u001a\u00020,H\u0002J\u0010\u0010C\u001a\u00020,2\u0006\u0010D\u001a\u00020\u0019H\u0002J\b\u0010E\u001a\u00020,H\u0002J\b\u0010F\u001a\u00020,H\u0002R\u001a\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020 X.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u000e\u0010%\u001a\u00020&X.¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X.¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/medscape/android/drugs/details/views/DrugDetailsActivityFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/medscape/android/contentviewer/view_holders/DataViewHolder$DataListClickListener;", "Lcom/wbmd/wbmdcommons/callbacks/IScrollEvent;", "Lcom/wbmd/qxcalculator/LaunchQxCallback;", "()V", "activityViewModel", "Lcom/medscape/android/drugs/details/viewmodels/DrugDetailsActivityViewModel;", "getActivityViewModel", "()Lcom/medscape/android/drugs/details/viewmodels/DrugDetailsActivityViewModel;", "setActivityViewModel", "(Lcom/medscape/android/drugs/details/viewmodels/DrugDetailsActivityViewModel;)V", "adRequestHelper", "Lcom/medscape/android/ads/AdRequestHelper;", "adbidder", "Lcom/medscape/android/ads/bidding/AdBidder;", "assetId", "", "drugSectionViewModel", "Lcom/medscape/android/drugs/details/viewmodels/DrugSectionViewModel;", "getDrugSectionViewModel", "()Lcom/medscape/android/drugs/details/viewmodels/DrugSectionViewModel;", "setDrugSectionViewModel", "(Lcom/medscape/android/drugs/details/viewmodels/DrugSectionViewModel;)V", "isInlineADcallComplete", "", "Ljava/lang/Boolean;", "loadingSpinner", "Landroid/widget/ProgressBar;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "tabLayout", "Lcom/google/android/material/tabs/TabLayout;", "viewAdapter", "Lcom/medscape/android/drugs/details/adapters/DrugSectionAdapter;", "viewManager", "Lcom/tonicartos/superslim/LayoutManager;", "loadAd", "", "layoutManager", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreate", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDataListItemClicked", "position", "", "onPreCacheEvent", "onQxItemClicked", "contentItem", "Lcom/wbmd/qxcalculator/model/db/DBContentItem;", "bundle", "onScrollThresholdReached", "scrollToTop", "setBannerADVisibility", "isVisible", "setObservers", "setUpTabs", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugDetailsActivityFragment.kt */
public final class DrugDetailsActivityFragment extends Fragment implements DataViewHolder.DataListClickListener, IScrollEvent, LaunchQxCallback {
    private HashMap _$_findViewCache;
    public DrugDetailsActivityViewModel activityViewModel;
    private AdRequestHelper adRequestHelper;
    private final AdBidder adbidder = new AdBidder();
    /* access modifiers changed from: private */
    public String assetId = "";
    public DrugSectionViewModel drugSectionViewModel;
    /* access modifiers changed from: private */
    public Boolean isInlineADcallComplete;
    /* access modifiers changed from: private */
    public ProgressBar loadingSpinner;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    public View rootView;
    private TabLayout tabLayout;
    /* access modifiers changed from: private */
    public DrugSectionAdapter viewAdapter;
    /* access modifiers changed from: private */
    public LayoutManager viewManager;

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onPreCacheEvent() {
    }

    public static final /* synthetic */ ProgressBar access$getLoadingSpinner$p(DrugDetailsActivityFragment drugDetailsActivityFragment) {
        ProgressBar progressBar = drugDetailsActivityFragment.loadingSpinner;
        if (progressBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingSpinner");
        }
        return progressBar;
    }

    public static final /* synthetic */ RecyclerView access$getRecyclerView$p(DrugDetailsActivityFragment drugDetailsActivityFragment) {
        RecyclerView recyclerView2 = drugDetailsActivityFragment.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        return recyclerView2;
    }

    public static final /* synthetic */ DrugSectionAdapter access$getViewAdapter$p(DrugDetailsActivityFragment drugDetailsActivityFragment) {
        DrugSectionAdapter drugSectionAdapter = drugDetailsActivityFragment.viewAdapter;
        if (drugSectionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
        }
        return drugSectionAdapter;
    }

    public static final /* synthetic */ LayoutManager access$getViewManager$p(DrugDetailsActivityFragment drugDetailsActivityFragment) {
        LayoutManager layoutManager = drugDetailsActivityFragment.viewManager;
        if (layoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewManager");
        }
        return layoutManager;
    }

    public final View getRootView() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return view;
    }

    public final void setRootView(View view) {
        Intrinsics.checkNotNullParameter(view, "<set-?>");
        this.rootView = view;
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

    public final DrugDetailsActivityViewModel getActivityViewModel() {
        DrugDetailsActivityViewModel drugDetailsActivityViewModel = this.activityViewModel;
        if (drugDetailsActivityViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityViewModel");
        }
        return drugDetailsActivityViewModel;
    }

    public final void setActivityViewModel(DrugDetailsActivityViewModel drugDetailsActivityViewModel) {
        Intrinsics.checkNotNullParameter(drugDetailsActivityViewModel, "<set-?>");
        this.activityViewModel = drugDetailsActivityViewModel;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_drug_details, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(R.layou…etails, container, false)");
        this.rootView = inflate;
        setUpTabs();
        Bundle arguments = getArguments();
        String str = (String) (arguments != null ? arguments.get("assetId") : null);
        if (str == null) {
            str = "";
        }
        this.assetId = str;
        this.viewManager = new LayoutManager((Context) getActivity());
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        List value = drugSectionViewModel2.getItems().getValue();
        if (value == null) {
            value = new ArrayList();
        }
        this.viewAdapter = new DrugSectionAdapter(value, this);
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById = view.findViewById(R.id.drug_details_recycler_view);
        RecyclerView recyclerView2 = (RecyclerView) findViewById;
        LayoutManager layoutManager = this.viewManager;
        if (layoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewManager");
        }
        recyclerView2.setLayoutManager(layoutManager);
        DrugSectionAdapter drugSectionAdapter = this.viewAdapter;
        if (drugSectionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
        }
        recyclerView2.setAdapter(drugSectionAdapter);
        Unit unit = Unit.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(findViewById, "rootView.findViewById<Re…r = viewAdapter\n        }");
        this.recyclerView = recyclerView2;
        View view2 = this.rootView;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById2 = view2.findViewById(R.id.drug_details_loading);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "rootView.findViewById(R.id.drug_details_loading)");
        this.loadingSpinner = (ProgressBar) findViewById2;
        setObservers();
        View view3 = this.rootView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return view3;
    }

    private final void setObservers() {
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        drugSectionViewModel2.getItems().observe(getViewLifecycleOwner(), new DrugDetailsActivityFragment$setObservers$1(this));
        DrugSectionViewModel drugSectionViewModel3 = this.drugSectionViewModel;
        if (drugSectionViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        drugSectionViewModel3.getLoading().observe(getViewLifecycleOwner(), new DrugDetailsActivityFragment$setObservers$2(this));
        DrugSectionViewModel drugSectionViewModel4 = this.drugSectionViewModel;
        if (drugSectionViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        drugSectionViewModel4.getScrollPosition().observe(getViewLifecycleOwner(), new DrugDetailsActivityFragment$setObservers$3(this));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ViewModel viewModel = ViewModelProviders.of(activity).get(DrugSectionViewModel.class);
            Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProviders.of(ac…ionViewModel::class.java)");
            this.drugSectionViewModel = (DrugSectionViewModel) viewModel;
            FragmentActivity activity2 = getActivity();
            if (activity2 != null) {
                ViewModel viewModel2 = ViewModelProviders.of(activity2).get(DrugDetailsActivityViewModel.class);
                Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProviders.of(ac…ityViewModel::class.java)");
                this.activityViewModel = (DrugDetailsActivityViewModel) viewModel2;
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        new AdScrollHandler(recyclerView2, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, this);
        InlineAdTouchHelper inlineAdTouchHelper = new InlineAdTouchHelper();
        RecyclerView recyclerView3 = this.recyclerView;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        InlineAdTouchHelper.applyTouchListener$default(inlineAdTouchHelper, recyclerView3, false, 2, (Object) null);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((DrugDetailsActivity) activity).getAd();
            new Handler().postDelayed(new DrugDetailsActivityFragment$onActivityCreated$1(this), 300);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.views.DrugDetailsActivity");
    }

    private final void setUpTabs() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        View findViewById = view.findViewById(R.id.drug_details_tabs);
        TabLayout tabLayout2 = (TabLayout) findViewById;
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        tabLayout2.setVisibility(drugSectionViewModel2.shouldShowTabs() ? 0 : 8);
        Unit unit = Unit.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(findViewById, "rootView.findViewById<Ta…E\n            }\n        }");
        this.tabLayout = tabLayout2;
        DrugSectionViewModel drugSectionViewModel3 = this.drugSectionViewModel;
        if (drugSectionViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        if (drugSectionViewModel3.isInteractionSection()) {
            TabLayout tabLayout3 = this.tabLayout;
            if (tabLayout3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
            }
            TabLayout tabLayout4 = this.tabLayout;
            if (tabLayout4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
            }
            tabLayout3.addTab(tabLayout4.newTab().setText((CharSequence) getString(R.string.drug_monograph_sections_interactions_tab_severity)));
            TabLayout tabLayout5 = this.tabLayout;
            if (tabLayout5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
            }
            TabLayout tabLayout6 = this.tabLayout;
            if (tabLayout6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
            }
            tabLayout5.addTab(tabLayout6.newTab().setText((CharSequence) getString(R.string.drug_monograph_sections_interactions_tab_name)));
        } else {
            DrugSectionViewModel drugSectionViewModel4 = this.drugSectionViewModel;
            if (drugSectionViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
            }
            if (drugSectionViewModel4.getIndexes() != null) {
                DrugSectionViewModel drugSectionViewModel5 = this.drugSectionViewModel;
                if (drugSectionViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
                }
                ArrayList<Integer> indexes = drugSectionViewModel5.getIndexes();
                Intrinsics.checkNotNull(indexes);
                Iterator<Integer> it = indexes.iterator();
                while (it.hasNext()) {
                    Integer next = it.next();
                    DrugSectionViewModel drugSectionViewModel6 = this.drugSectionViewModel;
                    if (drugSectionViewModel6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
                    }
                    Intrinsics.checkNotNullExpressionValue(next, FirebaseAnalytics.Param.INDEX);
                    if (!drugSectionViewModel6.isSectionEmpty(next.intValue())) {
                        TabLayout tabLayout7 = this.tabLayout;
                        if (tabLayout7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                        }
                        TabLayout tabLayout8 = this.tabLayout;
                        if (tabLayout8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
                        }
                        tabLayout7.addTab(tabLayout8.newTab().setText(DrugMonographViewHelper.getSectionIndexResource(next.intValue(), false)));
                    }
                }
            }
        }
        TabLayout tabLayout9 = this.tabLayout;
        if (tabLayout9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabLayout");
        }
        tabLayout9.addOnTabSelectedListener((TabLayout.OnTabSelectedListener) new DrugDetailsActivityFragment$setUpTabs$2(this));
    }

    /* access modifiers changed from: private */
    public final void scrollToTop() {
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        recyclerView2.postDelayed(new DrugDetailsActivityFragment$scrollToTop$1(this), 200);
    }

    public void onDataListItemClicked(int i) {
        DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
        if (drugSectionViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
        }
        List value = drugSectionViewModel2.getItems().getValue();
        String str = null;
        LineItem lineItem = value != null ? (LineItem) value.get(i) : null;
        if (lineItem == null) {
            return;
        }
        if (lineItem.isNextSection()) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                Spinner mToolbarSpinner = ((DrugDetailsActivity) activity).getMToolbarSpinner();
                DrugSectionViewModel drugSectionViewModel3 = this.drugSectionViewModel;
                if (drugSectionViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
                }
                mToolbarSpinner.setSelection(drugSectionViewModel3.goToNextSection(), true);
                DrugDetailsActivityViewModel drugDetailsActivityViewModel = this.activityViewModel;
                if (drugDetailsActivityViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activityViewModel");
                }
                drugDetailsActivityViewModel.setGoingToNextSection(true);
                DrugDetailsActivityViewModel drugDetailsActivityViewModel2 = this.activityViewModel;
                if (drugDetailsActivityViewModel2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("activityViewModel");
                }
                if (Intrinsics.areEqual((Object) drugDetailsActivityViewModel2.isSearchMode().getValue(), (Object) true)) {
                    DrugDetailsActivityViewModel drugDetailsActivityViewModel3 = this.activityViewModel;
                    if (drugDetailsActivityViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activityViewModel");
                    }
                    drugDetailsActivityViewModel3.setSearchMode(false);
                    return;
                }
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.views.DrugDetailsActivity");
        } else if (lineItem.getCrossLink() != null) {
            CrossLink crossLink = lineItem.getCrossLink();
            if ((crossLink != null ? crossLink.type : null) == CrossLink.Type.CALC) {
                Context activity2 = getActivity();
                CrossLink crossLink2 = lineItem.getCrossLink();
                if (crossLink2 != null) {
                    str = crossLink2.ref;
                }
                if (Util.findMatchingQxCalcForMedscapeCalc(activity2, str, this)) {
                    DrugDetailsActivityViewModel drugDetailsActivityViewModel4 = this.activityViewModel;
                    if (drugDetailsActivityViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("activityViewModel");
                    }
                    drugDetailsActivityViewModel4.setSearchMode(false);
                    return;
                }
                Activity activity3 = getActivity();
                View view = this.rootView;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootView");
                }
                Util.showNoCalculatorAvailable(activity3, view);
            }
        }
    }

    public void onScrollThresholdReached() {
        LayoutManager layoutManager = this.viewManager;
        if (layoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewManager");
        }
        DrugSectionAdapter drugSectionAdapter = this.viewAdapter;
        if (drugSectionAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
        }
        loadAd(layoutManager, drugSectionAdapter);
    }

    /* access modifiers changed from: private */
    public final void loadAd(LayoutManager layoutManager, RecyclerView.Adapter<?> adapter) {
        this.adRequestHelper = new AdRequestHelper();
        if (layoutManager != null && adapter != null && layoutManager.getChildCount() != 0) {
            int findLastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            int itemCount = adapter.getItemCount();
            if (itemCount > 0) {
                int i = findLastVisibleItemPosition + 2;
                if (i >= itemCount && this.isInlineADcallComplete == null) {
                    DrugSectionAdapter drugSectionAdapter = this.viewAdapter;
                    if (drugSectionAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
                    }
                    int adPosition = drugSectionAdapter.getAdPosition();
                    if (adPosition != -1) {
                        DrugSectionAdapter drugSectionAdapter2 = this.viewAdapter;
                        if (drugSectionAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
                        }
                        if (drugSectionAdapter2.getMItems().size() > adPosition) {
                            DrugSectionAdapter drugSectionAdapter3 = this.viewAdapter;
                            if (drugSectionAdapter3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
                            }
                            LineItem lineItem = drugSectionAdapter3.getMItems().get(adPosition);
                            if ((lineItem instanceof InlineAdLineItem) && ((InlineAdLineItem) lineItem).getAdView() == null && getActivity() != null) {
                                AdHelper.Companion companion = AdHelper.Companion;
                                FragmentActivity activity = getActivity();
                                if (activity != null) {
                                    HashMap<String, String> screenMap = ((DrugDetailsActivity) activity).getScreenMap();
                                    DrugSectionViewModel drugSectionViewModel2 = this.drugSectionViewModel;
                                    if (drugSectionViewModel2 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("drugSectionViewModel");
                                    }
                                    HashMap<String, String> updatedADParams = companion.getUpdatedADParams(screenMap, drugSectionViewModel2);
                                    FragmentActivity activity2 = getActivity();
                                    if (activity2 != null) {
                                        String str = ((DrugDetailsActivity) activity2).mPvid;
                                        if (str != null) {
                                            updatedADParams.put("pvid", str);
                                        }
                                        AdRequestHelper adRequestHelper2 = this.adRequestHelper;
                                        if (adRequestHelper2 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("adRequestHelper");
                                        }
                                        adRequestHelper2.makeDrugMonographInlineAdRequest(getContext(), updatedADParams, this.adbidder, new DrugDetailsActivityFragment$loadAd$1(this, lineItem));
                                    } else {
                                        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.views.DrugDetailsActivity");
                                    }
                                } else {
                                    throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.views.DrugDetailsActivity");
                                }
                            }
                        }
                    }
                }
                if (i < itemCount || !Intrinsics.areEqual((Object) this.isInlineADcallComplete, (Object) true)) {
                    setBannerADVisibility(true);
                } else {
                    setBannerADVisibility(false);
                }
            }
        }
    }

    private final void setBannerADVisibility(boolean z) {
        if (!(getActivity() instanceof DrugDetailsActivity)) {
            return;
        }
        if (z) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                ((DrugDetailsActivity) activity).showHiddenBannerAD();
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.views.DrugDetailsActivity");
        }
        FragmentActivity activity2 = getActivity();
        if (activity2 != null) {
            ((DrugDetailsActivity) activity2).forceHideBannerAD();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.views.DrugDetailsActivity");
    }

    public void onQxItemClicked(DBContentItem dBContentItem, Bundle bundle) {
        CalcOmnitureHelper calcOmnitureHelper = CalcOmnitureHelper.INSTANCE;
        Activity activity = getActivity();
        String valueOf = String.valueOf(dBContentItem != null ? dBContentItem.getCalculatorId() : null);
        String calcPageNameForOmniture = Util.getCalcPageNameForOmniture(getActivity(), dBContentItem);
        Intrinsics.checkNotNullExpressionValue(calcPageNameForOmniture, "Util.getCalcPageNameForO…re(activity, contentItem)");
        String sendOmnitureCall = calcOmnitureHelper.sendOmnitureCall(activity, "linkedcalc", valueOf, calcPageNameForOmniture);
        if (bundle != null) {
            bundle.putString("pvid", sendOmnitureCall);
        }
        Util.openQxItem(getActivity(), dBContentItem, bundle);
    }
}
