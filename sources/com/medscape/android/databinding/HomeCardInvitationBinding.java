package com.medscape.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.medscape.android.R;
import com.medscape.android.landingfeed.model.FeedDataItem;

public abstract class HomeCardInvitationBinding extends ViewDataBinding {
    public final ImageView imageBadge;
    @Bindable
    protected FeedDataItem mItem;
    public final ConstraintLayout rootContainer;
    public final TextView textDescription;
    public final TextView textInformation;
    public final TextView textTitle;
    public final TextView textType;
    public final TextView textViewButton;

    public abstract void setItem(FeedDataItem feedDataItem);

    protected HomeCardInvitationBinding(Object obj, View view, int i, ImageView imageView, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.imageBadge = imageView;
        this.rootContainer = constraintLayout;
        this.textDescription = textView;
        this.textInformation = textView2;
        this.textTitle = textView3;
        this.textType = textView4;
        this.textViewButton = textView5;
    }

    public FeedDataItem getItem() {
        return this.mItem;
    }

    public static HomeCardInvitationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HomeCardInvitationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (HomeCardInvitationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.home_card_invitation, viewGroup, z, obj);
    }

    public static HomeCardInvitationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HomeCardInvitationBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (HomeCardInvitationBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.home_card_invitation, (ViewGroup) null, false, obj);
    }

    public static HomeCardInvitationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static HomeCardInvitationBinding bind(View view, Object obj) {
        return (HomeCardInvitationBinding) bind(obj, view, R.layout.home_card_invitation);
    }
}
