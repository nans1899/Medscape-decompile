package com.wbmd.decisionpoint.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.decisionpoint.R;

public abstract class RowExpertsAnswersBinding extends ViewDataBinding {
    public final TextView textMessage;
    public final TextView textTitle;

    protected RowExpertsAnswersBinding(Object obj, View view, int i, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.textMessage = textView;
        this.textTitle = textView2;
    }

    public static RowExpertsAnswersBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowExpertsAnswersBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RowExpertsAnswersBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_experts_answers, viewGroup, z, obj);
    }

    public static RowExpertsAnswersBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowExpertsAnswersBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RowExpertsAnswersBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_experts_answers, (ViewGroup) null, false, obj);
    }

    public static RowExpertsAnswersBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowExpertsAnswersBinding bind(View view, Object obj) {
        return (RowExpertsAnswersBinding) bind(obj, view, R.layout.row_experts_answers);
    }
}
