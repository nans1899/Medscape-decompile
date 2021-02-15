package com.wbmd.decisionpoint.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.decisionpoint.R;

public abstract class RowHeaderBinding extends ViewDataBinding {
    public final ImageView imageLogoTop;
    public final ImageView imageMessageLine;
    public final TextView textGuidelines;
    public final TextView textMessage;

    protected RowHeaderBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.imageLogoTop = imageView;
        this.imageMessageLine = imageView2;
        this.textGuidelines = textView;
        this.textMessage = textView2;
    }

    public static RowHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RowHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_header, viewGroup, z, obj);
    }

    public static RowHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowHeaderBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RowHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_header, (ViewGroup) null, false, obj);
    }

    public static RowHeaderBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowHeaderBinding bind(View view, Object obj) {
        return (RowHeaderBinding) bind(obj, view, R.layout.row_header);
    }
}
