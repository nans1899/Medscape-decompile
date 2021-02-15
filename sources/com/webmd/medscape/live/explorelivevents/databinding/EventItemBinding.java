package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;

public abstract class EventItemBinding extends ViewDataBinding {
    @Bindable
    protected LiveEvent mLiveEvent;
    @Bindable
    protected StyleManager mStyleManager;
    public final TextView tvAccreditation;
    public final TextView tvEventDate;
    public final TextView tvEventDescription;
    public final TextView tvEventTitle;
    public final TextView tvLocation;

    public abstract void setLiveEvent(LiveEvent liveEvent);

    public abstract void setStyleManager(StyleManager styleManager);

    protected EventItemBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.tvAccreditation = textView;
        this.tvEventDate = textView2;
        this.tvEventDescription = textView3;
        this.tvEventTitle = textView4;
        this.tvLocation = textView5;
    }

    public LiveEvent getLiveEvent() {
        return this.mLiveEvent;
    }

    public StyleManager getStyleManager() {
        return this.mStyleManager;
    }

    public static EventItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EventItemBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (EventItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.event_item, viewGroup, z, obj);
    }

    public static EventItemBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EventItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (EventItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.event_item, (ViewGroup) null, false, obj);
    }

    public static EventItemBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static EventItemBinding bind(View view, Object obj) {
        return (EventItemBinding) bind(obj, view, R.layout.event_item);
    }
}
