package com.medscape.android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.medscape.android.R;
import com.medscape.android.myinvites.specific.Invitation;

public abstract class MyInvitationsCardBinding extends ViewDataBinding {
    public final View lineFeatured;
    @Bindable
    protected Invitation mInvitation;
    public final CardView rootContainer;
    public final TextView textCta;
    public final TextView textDescription;
    public final TextView textFeatured;
    public final TextView textInfo;
    public final TextView textTitle;

    public abstract void setInvitation(Invitation invitation);

    protected MyInvitationsCardBinding(Object obj, View view, int i, View view2, CardView cardView, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.lineFeatured = view2;
        this.rootContainer = cardView;
        this.textCta = textView;
        this.textDescription = textView2;
        this.textFeatured = textView3;
        this.textInfo = textView4;
        this.textTitle = textView5;
    }

    public Invitation getInvitation() {
        return this.mInvitation;
    }

    public static MyInvitationsCardBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MyInvitationsCardBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (MyInvitationsCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.my_invitations_card, viewGroup, z, obj);
    }

    public static MyInvitationsCardBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MyInvitationsCardBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (MyInvitationsCardBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.my_invitations_card, (ViewGroup) null, false, obj);
    }

    public static MyInvitationsCardBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MyInvitationsCardBinding bind(View view, Object obj) {
        return (MyInvitationsCardBinding) bind(obj, view, R.layout.my_invitations_card);
    }
}
