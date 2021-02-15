package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import com.webmd.medscape.live.explorelivevents.R;

public final class QuickFiltersViewBinding implements ViewBinding {
    public final View bottomDivider;
    public final TextView btSpecificDate;
    private final RelativeLayout rootView;
    public final RecyclerView rvButtons;

    private QuickFiltersViewBinding(RelativeLayout relativeLayout, View view, TextView textView, RecyclerView recyclerView) {
        this.rootView = relativeLayout;
        this.bottomDivider = view;
        this.btSpecificDate = textView;
        this.rvButtons = recyclerView;
    }

    public RelativeLayout getRoot() {
        return this.rootView;
    }

    public static QuickFiltersViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static QuickFiltersViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.quick_filters_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static QuickFiltersViewBinding bind(View view) {
        int i = R.id.bottom_divider;
        View findViewById = view.findViewById(i);
        if (findViewById != null) {
            i = R.id.bt_specific_date;
            TextView textView = (TextView) view.findViewById(i);
            if (textView != null) {
                i = R.id.rv_buttons;
                RecyclerView recyclerView = (RecyclerView) view.findViewById(i);
                if (recyclerView != null) {
                    return new QuickFiltersViewBinding((RelativeLayout) view, findViewById, textView, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
