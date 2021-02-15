package com.medscape.android.homescreen.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BottomNavBaseActivity;
import com.medscape.android.homescreen.viewmodel.HomeScreenViewModel;
import com.medscape.android.landingfeed.view.BaseLandingFragment;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u00012\u00020\u0002:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0003J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u001a\u0010\u0017\u001a\u00020\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\rH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/medscape/android/homescreen/views/HomeScreenFragment;", "Lcom/medscape/android/landingfeed/view/BaseLandingFragment;", "Lcom/google/android/material/appbar/AppBarLayout$OnOffsetChangedListener;", "()V", "fabButton", "Landroid/widget/FrameLayout;", "homeViewModel", "Lcom/medscape/android/homescreen/viewmodel/HomeScreenViewModel;", "profileIcon", "Landroid/widget/ImageView;", "searchBar", "Landroid/widget/LinearLayout;", "initializeViews", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onOffsetChanged", "appBarLayout", "Lcom/google/android/material/appbar/AppBarLayout;", "i", "", "setListeners", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HomeScreenFragment.kt */
public final class HomeScreenFragment extends BaseLandingFragment implements AppBarLayout.OnOffsetChangedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private HashMap _$_findViewCache;
    private FrameLayout fabButton;
    /* access modifiers changed from: private */
    public HomeScreenViewModel homeViewModel;
    private ImageView profileIcon;
    private LinearLayout searchBar;

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

    public static final /* synthetic */ HomeScreenViewModel access$getHomeViewModel$p(HomeScreenFragment homeScreenFragment) {
        HomeScreenViewModel homeScreenViewModel = homeScreenFragment.homeViewModel;
        if (homeScreenViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("homeViewModel");
        }
        return homeScreenViewModel;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_home_screen, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(R.layou…screen, container, false)");
        setRootView(inflate);
        setFeedView((RecyclerView) getRootView().findViewById(R.id.landing_feed_view));
        this.fabButton = (FrameLayout) getRootView().findViewById(R.id.fab_button);
        return getRootView();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        initializeViews();
        setListeners();
        FragmentActivity activity = getActivity();
        if (activity != null && (activity instanceof BottomNavBaseActivity)) {
            BottomNavBaseActivity bottomNavBaseActivity = (BottomNavBaseActivity) activity;
            ImageView imageView = this.profileIcon;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("profileIcon");
            }
            bottomNavBaseActivity.setProfileIcon(imageView);
        }
    }

    private final void setListeners() {
        LinearLayout linearLayout = this.searchBar;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("searchBar");
        }
        linearLayout.setOnClickListener(new HomeScreenFragment$setListeners$1(this));
        ImageView imageView = this.profileIcon;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("profileIcon");
        }
        imageView.setOnClickListener(new HomeScreenFragment$setListeners$2(this));
    }

    private final void initializeViews() {
        View findViewById = getRootView().findViewById(R.id.profile_image_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "rootView.findViewById(R.id.profile_image_view)");
        this.profileIcon = (ImageView) findViewById;
        View findViewById2 = getRootView().findViewById(R.id.searchView);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "rootView.findViewById(R.id.searchView)");
        this.searchBar = (LinearLayout) findViewById2;
        FrameLayout frameLayout = this.fabButton;
        if (frameLayout != null) {
            frameLayout.setOnTouchListener(new HomeScreenFragment$initializeViews$1(this));
        }
        ((SpecialRecyclerView) _$_findCachedViewById(R.id.landing_feed_view)).setRecylerviewReleased(new HomeScreenFragment$initializeViews$2(this));
        ((SpecialRecyclerView) _$_findCachedViewById(R.id.landing_feed_view)).setSpecialClick(new HomeScreenFragment$initializeViews$3(this));
        ((AppBarLayout) _$_findCachedViewById(R.id.app_bar_layout)).addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) this);
    }

    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            FrameLayout frameLayout = this.fabButton;
            if (frameLayout != null) {
                frameLayout.setVisibility(8);
            }
            HomeScreenViewModel homeScreenViewModel = this.homeViewModel;
            if (homeScreenViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("homeViewModel");
            }
            homeScreenViewModel.setExpanded(true);
            if (SpecialRecyclerView.Companion.isFabButtonSwipeReleased()) {
                OmnitureManager.get().trackModule(getActivity(), Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MMODULE_REFMENU, "swipe", (Map<String, Object>) null);
                SpecialRecyclerView.Companion.setFabButtonSwipeReleased(false);
                return;
            }
            return;
        }
        Integer num = null;
        Integer valueOf = appBarLayout != null ? Integer.valueOf(appBarLayout.getHeight() + i) : null;
        Toolbar toolbar = (Toolbar) _$_findCachedViewById(R.id.toolbar);
        if (toolbar != null) {
            num = Integer.valueOf(toolbar.getHeight());
        }
        if (Intrinsics.areEqual((Object) valueOf, (Object) num)) {
            FrameLayout frameLayout2 = this.fabButton;
            if (frameLayout2 != null) {
                frameLayout2.setVisibility(0);
            }
            HomeScreenViewModel homeScreenViewModel2 = this.homeViewModel;
            if (homeScreenViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("homeViewModel");
            }
            homeScreenViewModel2.setExpanded(false);
            return;
        }
        FrameLayout frameLayout3 = this.fabButton;
        if (frameLayout3 != null) {
            frameLayout3.setVisibility(8);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/medscape/android/homescreen/views/HomeScreenFragment$Companion;", "", "()V", "newInstance", "Lcom/medscape/android/homescreen/views/HomeScreenFragment;", "viewModel", "Lcom/medscape/android/homescreen/viewmodel/HomeScreenViewModel;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HomeScreenFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HomeScreenFragment newInstance(HomeScreenViewModel homeScreenViewModel) {
            Intrinsics.checkNotNullParameter(homeScreenViewModel, "viewModel");
            HomeScreenFragment homeScreenFragment = new HomeScreenFragment();
            homeScreenFragment.homeViewModel = homeScreenViewModel;
            return homeScreenFragment;
        }
    }
}
