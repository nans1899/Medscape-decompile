package com.medscape.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.medscape.android.R;
import com.medscape.android.consult.models.BodyUpdates;

public abstract class ItemConsultPostUpdateBinding extends ViewDataBinding {
    public final TextView consultEdit;
    public final TextView displayTime;
    @Bindable
    protected BodyUpdates mBodyupdate;
    public final TextView postBody;
    public final LinearLayout postUpdateLayout;
    public final TextView updateText;

    public abstract void setBodyupdate(BodyUpdates bodyUpdates);

    protected ItemConsultPostUpdateBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, LinearLayout linearLayout, TextView textView4) {
        super(obj, view, i);
        this.consultEdit = textView;
        this.displayTime = textView2;
        this.postBody = textView3;
        this.postUpdateLayout = linearLayout;
        this.updateText = textView4;
    }

    public BodyUpdates getBodyupdate() {
        return this.mBodyupdate;
    }

    public static ItemConsultPostUpdateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemConsultPostUpdateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemConsultPostUpdateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_consult_post_update, viewGroup, z, obj);
    }

    public static ItemConsultPostUpdateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemConsultPostUpdateBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemConsultPostUpdateBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.item_consult_post_update, (ViewGroup) null, false, obj);
    }

    public static ItemConsultPostUpdateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemConsultPostUpdateBinding bind(View view, Object obj) {
        return (ItemConsultPostUpdateBinding) bind(obj, view, R.layout.item_consult_post_update);
    }
}
