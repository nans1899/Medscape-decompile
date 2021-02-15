package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.webmd.medscape.live.explorelivevents.R;

public final class DropdownFiltersViewBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final RecyclerView rvDropdownFilters;

    private DropdownFiltersViewBinding(ConstraintLayout constraintLayout, RecyclerView recyclerView) {
        this.rootView = constraintLayout;
        this.rvDropdownFilters = recyclerView;
    }

    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static DropdownFiltersViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static DropdownFiltersViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.dropdown_filters_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static DropdownFiltersViewBinding bind(View view) {
        int i = R.id.rv_dropdown_filters;
        RecyclerView recyclerView = (RecyclerView) view.findViewById(i);
        if (recyclerView != null) {
            return new DropdownFiltersViewBinding((ConstraintLayout) view, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
