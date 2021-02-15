package com.wbmd.decisionpoint.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.decisionpoint.R;

public abstract class RowAnswersBinding extends ViewDataBinding {
    public final ImageView imageLineBottom;
    public final ImageView imageLineTop;
    public final View line12;
    public final View line23;
    public final TextView textCount1;
    public final TextView textCount2;
    public final TextView textCount3;
    public final TextView textMessage1;
    public final TextView textMessage2;
    public final TextView textMessage3;
    public final TextView textSteps;
    public final TextView textTitle1;
    public final TextView textTitle2;
    public final TextView textTitle3;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected RowAnswersBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, View view2, View view3, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        super(obj, view, i);
        this.imageLineBottom = imageView;
        this.imageLineTop = imageView2;
        this.line12 = view2;
        this.line23 = view3;
        this.textCount1 = textView;
        this.textCount2 = textView2;
        this.textCount3 = textView3;
        this.textMessage1 = textView4;
        this.textMessage2 = textView5;
        this.textMessage3 = textView6;
        this.textSteps = textView7;
        this.textTitle1 = textView8;
        this.textTitle2 = textView9;
        this.textTitle3 = textView10;
    }

    public static RowAnswersBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowAnswersBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RowAnswersBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_answers, viewGroup, z, obj);
    }

    public static RowAnswersBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowAnswersBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RowAnswersBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_answers, (ViewGroup) null, false, obj);
    }

    public static RowAnswersBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowAnswersBinding bind(View view, Object obj) {
        return (RowAnswersBinding) bind(obj, view, R.layout.row_answers);
    }
}
