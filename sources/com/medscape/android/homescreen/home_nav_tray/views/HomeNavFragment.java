package com.medscape.android.homescreen.home_nav_tray.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.homescreen.edit_navigation.viewmodel.NavEditViewModel;
import com.medscape.android.homescreen.home_nav_tray.adapters.HomeNavItemsAdapter;
import com.medscape.android.homescreen.home_nav_tray.helpers.NavigationProvider;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import com.medscape.android.homescreen.interfaces.INavItemClickListener;
import com.medscape.android.util.Util;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J&\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0018\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\u0011H\u0016J\b\u0010-\u001a\u00020)H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX.¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/medscape/android/homescreen/home_nav_tray/views/HomeNavFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/medscape/android/homescreen/interfaces/INavItemClickListener;", "()V", "editViewModel", "Lcom/medscape/android/homescreen/edit_navigation/viewmodel/NavEditViewModel;", "getEditViewModel", "()Lcom/medscape/android/homescreen/edit_navigation/viewmodel/NavEditViewModel;", "setEditViewModel", "(Lcom/medscape/android/homescreen/edit_navigation/viewmodel/NavEditViewModel;)V", "navigationProvider", "Lcom/medscape/android/homescreen/home_nav_tray/helpers/NavigationProvider;", "getNavigationProvider", "()Lcom/medscape/android/homescreen/home_nav_tray/helpers/NavigationProvider;", "setNavigationProvider", "(Lcom/medscape/android/homescreen/home_nav_tray/helpers/NavigationProvider;)V", "procedureFolderId", "", "getProcedureFolderId", "()I", "setProcedureFolderId", "(I)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "viewAdapter", "Lcom/medscape/android/homescreen/home_nav_tray/adapters/HomeNavItemsAdapter;", "viewManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onNavItemClicked", "", "navItem", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "position", "onResume", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HomeNavFragment.kt */
public final class HomeNavFragment extends Fragment implements INavItemClickListener {
    private HashMap _$_findViewCache;
    public NavEditViewModel editViewModel;
    public NavigationProvider navigationProvider;
    private int procedureFolderId = 44;
    public RecyclerView recyclerView;
    private HomeNavItemsAdapter viewAdapter;
    private RecyclerView.LayoutManager viewManager;

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

    public final RecyclerView getRecyclerView() {
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        return recyclerView2;
    }

    public final void setRecyclerView(RecyclerView recyclerView2) {
        Intrinsics.checkNotNullParameter(recyclerView2, "<set-?>");
        this.recyclerView = recyclerView2;
    }

    public final NavigationProvider getNavigationProvider() {
        NavigationProvider navigationProvider2 = this.navigationProvider;
        if (navigationProvider2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationProvider");
        }
        return navigationProvider2;
    }

    public final void setNavigationProvider(NavigationProvider navigationProvider2) {
        Intrinsics.checkNotNullParameter(navigationProvider2, "<set-?>");
        this.navigationProvider = navigationProvider2;
    }

    public final NavEditViewModel getEditViewModel() {
        NavEditViewModel navEditViewModel = this.editViewModel;
        if (navEditViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
        }
        return navEditViewModel;
    }

    public final void setEditViewModel(NavEditViewModel navEditViewModel) {
        Intrinsics.checkNotNullParameter(navEditViewModel, "<set-?>");
        this.editViewModel = navEditViewModel;
    }

    public final int getProcedureFolderId() {
        return this.procedureFolderId;
    }

    public final void setProcedureFolderId(int i) {
        this.procedureFolderId = i;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NavEditViewModel navEditViewModel;
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_home_nav, viewGroup, false);
        FragmentActivity activity = getActivity();
        if (activity == null || (navEditViewModel = (NavEditViewModel) ViewModelProviders.of(activity).get(NavEditViewModel.class)) == null) {
            throw new Exception("Invalid Activity");
        }
        this.editViewModel = navEditViewModel;
        this.navigationProvider = new NavigationProvider();
        this.viewManager = new GridLayoutManager(getContext(), 4, 1, false);
        NavEditViewModel navEditViewModel2 = this.editViewModel;
        if (navEditViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
        }
        List<NavItem> itemsForPage = navEditViewModel2.getItemsForPage(getContext());
        INavItemClickListener iNavItemClickListener = this;
        NavEditViewModel navEditViewModel3 = this.editViewModel;
        if (navEditViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
        }
        this.viewAdapter = new HomeNavItemsAdapter(itemsForPage, iNavItemClickListener, navEditViewModel3.getReferenceConfigModel());
        View findViewById = inflate.findViewById(R.id.choice_grid);
        RecyclerView recyclerView2 = (RecyclerView) findViewById;
        RecyclerView.LayoutManager layoutManager = this.viewManager;
        if (layoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewManager");
        }
        recyclerView2.setLayoutManager(layoutManager);
        HomeNavItemsAdapter homeNavItemsAdapter = this.viewAdapter;
        if (homeNavItemsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
        }
        recyclerView2.setAdapter(homeNavItemsAdapter);
        Unit unit = Unit.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById<androi…r = viewAdapter\n        }");
        this.recyclerView = recyclerView2;
        return inflate;
    }

    public void onResume() {
        super.onResume();
        this.procedureFolderId = Util.getFolderIdForProcedures(getActivity());
        HomeNavItemsAdapter homeNavItemsAdapter = this.viewAdapter;
        if (homeNavItemsAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
        }
        NavEditViewModel navEditViewModel = this.editViewModel;
        if (navEditViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editViewModel");
        }
        homeNavItemsAdapter.setItems(navEditViewModel.getItemsForPage(getContext()));
        HomeNavItemsAdapter homeNavItemsAdapter2 = this.viewAdapter;
        if (homeNavItemsAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewAdapter");
        }
        homeNavItemsAdapter2.notifyItemRangeChanged(0, 7);
    }

    public void onNavItemClicked(NavItem navItem, int i) {
        Intrinsics.checkNotNullParameter(navItem, "navItem");
        if (navItem.getId() == 4) {
            navItem.setFolderId(this.procedureFolderId);
        }
        NavigationProvider navigationProvider2 = this.navigationProvider;
        if (navigationProvider2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("navigationProvider");
        }
        navigationProvider2.navigateTo(getActivity(), navItem);
    }
}
