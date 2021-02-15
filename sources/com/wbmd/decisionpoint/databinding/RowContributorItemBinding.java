package com.wbmd.decisionpoint.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.decisionpoint.R;
import com.wbmd.decisionpoint.domain.contributors.Contributor;

public abstract class RowContributorItemBinding extends ViewDataBinding {
    public final ImageView imageAvatar;
    @Bindable
    protected Contributor mContributor;
    public final TextView textName;
    public final TextView textProfession;

    public abstract void setContributor(Contributor contributor);

    protected RowContributorItemBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.imageAvatar = imageView;
        this.textName = textView;
        this.textProfession = textView2;
    }

    public Contributor getContributor() {
        return this.mContributor;
    }

    public static RowContributorItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowContributorItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RowContributorItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_contributor_item, viewGroup, z, obj);
    }

    public static RowContributorItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowContributorItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RowContributorItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_contributor_item, (ViewGroup) null, false, obj);
    }

    public static RowContributorItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowContributorItemBinding bind(View view, Object obj) {
        return (RowContributorItemBinding) bind(obj, view, R.layout.row_contributor_item);
    }
}
