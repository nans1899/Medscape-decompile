package com.wbmd.decisionpoint.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.wbmd.decisionpoint.R;
import com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoint;
import com.wbmd.decisionpoint.domain.interfaces.HubListener;

public abstract class RowDecisionPointBinding extends ViewDataBinding {
    public final Button buttonExplore;
    public final CardView cardView;
    public final ImageView imageType;
    @Bindable
    protected DecisionPoint mItem;
    @Bindable
    protected HubListener mListener;
    public final TextView textMessage;
    public final TextView textTitle;

    public abstract void setItem(DecisionPoint decisionPoint);

    public abstract void setListener(HubListener hubListener);

    protected RowDecisionPointBinding(Object obj, View view, int i, Button button, CardView cardView2, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.buttonExplore = button;
        this.cardView = cardView2;
        this.imageType = imageView;
        this.textMessage = textView;
        this.textTitle = textView2;
    }

    public DecisionPoint getItem() {
        return this.mItem;
    }

    public HubListener getListener() {
        return this.mListener;
    }

    public static RowDecisionPointBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowDecisionPointBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (RowDecisionPointBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_decision_point, viewGroup, z, obj);
    }

    public static RowDecisionPointBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowDecisionPointBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (RowDecisionPointBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.row_decision_point, (ViewGroup) null, false, obj);
    }

    public static RowDecisionPointBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static RowDecisionPointBinding bind(View view, Object obj) {
        return (RowDecisionPointBinding) bind(obj, view, R.layout.row_decision_point);
    }
}
