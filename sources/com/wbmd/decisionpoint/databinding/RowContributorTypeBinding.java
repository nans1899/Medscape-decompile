package com.wbmd.decisionpoint.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.decisionpoint.R;

public abstract class RowContributorTypeBinding extends ViewDataBinding {
    @Bindable
    protected String mTitle;
    public final TextView textProfession;
    public final View viewLine;

    public abstract void setTitle(String str);

    protected RowContributorTypeBinding(Object obj, View view, int i, TextView textView, View view2) {
        super(obj, view, i);
        this.textProfession = textView;
        this.viewLine = view2;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public static RowContributorTypeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowContributorTypeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RowContributorTypeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_contributor_type, viewGroup, z, obj);
    }

    public static RowContributorTypeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowContributorTypeBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RowContributorTypeBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_contributor_type, (ViewGroup) null, false, obj);
    }

    public static RowContributorTypeBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowContributorTypeBinding bind(View view, Object obj) {
        return (RowContributorTypeBinding) bind(obj, view, R.layout.row_contributor_type);
    }
}
