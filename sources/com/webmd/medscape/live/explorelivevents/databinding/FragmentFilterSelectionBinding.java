package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.webmd.medscape.live.explorelivevents.R;

public final class FragmentFilterSelectionBinding implements ViewBinding {
    public final AppBarLayout actionBar;
    private final CoordinatorLayout rootView;
    public final RecyclerView rvItems;
    public final Toolbar toolbar;

    private FragmentFilterSelectionBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, RecyclerView recyclerView, Toolbar toolbar2) {
        this.rootView = coordinatorLayout;
        this.actionBar = appBarLayout;
        this.rvItems = recyclerView;
        this.toolbar = toolbar2;
    }

    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentFilterSelectionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentFilterSelectionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_filter_selection, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentFilterSelectionBinding bind(View view) {
        int i = R.id.actionBar;
        AppBarLayout appBarLayout = (AppBarLayout) view.findViewById(i);
        if (appBarLayout != null) {
            i = R.id.rv_items;
            RecyclerView recyclerView = (RecyclerView) view.findViewById(i);
            if (recyclerView != null) {
                i = R.id.toolbar;
                Toolbar toolbar2 = (Toolbar) view.findViewById(i);
                if (toolbar2 != null) {
                    return new FragmentFilterSelectionBinding((CoordinatorLayout) view, appBarLayout, recyclerView, toolbar2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
