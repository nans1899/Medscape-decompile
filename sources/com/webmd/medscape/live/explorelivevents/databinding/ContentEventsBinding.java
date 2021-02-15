package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.ui.views.DropDownFiltersView;

public final class ContentEventsBinding implements ViewBinding {
    public final ConstraintLayout constraintView;
    public final DropDownFiltersView filters;
    public final ProgressBar pbLoader;
    private final NestedScrollView rootView;
    public final RecyclerView rvItems;
    public final TextView tvNoData;

    private ContentEventsBinding(NestedScrollView nestedScrollView, ConstraintLayout constraintLayout, DropDownFiltersView dropDownFiltersView, ProgressBar progressBar, RecyclerView recyclerView, TextView textView) {
        this.rootView = nestedScrollView;
        this.constraintView = constraintLayout;
        this.filters = dropDownFiltersView;
        this.pbLoader = progressBar;
        this.rvItems = recyclerView;
        this.tvNoData = textView;
    }

    public NestedScrollView getRoot() {
        return this.rootView;
    }

    public static ContentEventsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static ContentEventsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.content_events, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ContentEventsBinding bind(View view) {
        int i = R.id.constraintView;
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(i);
        if (constraintLayout != null) {
            i = R.id.filters;
            DropDownFiltersView dropDownFiltersView = (DropDownFiltersView) view.findViewById(i);
            if (dropDownFiltersView != null) {
                i = R.id.pb_loader;
                ProgressBar progressBar = (ProgressBar) view.findViewById(i);
                if (progressBar != null) {
                    i = R.id.rv_items;
                    RecyclerView recyclerView = (RecyclerView) view.findViewById(i);
                    if (recyclerView != null) {
                        i = R.id.tv_no_data;
                        TextView textView = (TextView) view.findViewById(i);
                        if (textView != null) {
                            return new ContentEventsBinding((NestedScrollView) view, constraintLayout, dropDownFiltersView, progressBar, recyclerView, textView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
