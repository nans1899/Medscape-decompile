package com.wbmd.decisionpoint.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.wbmd.decisionpoint.R;

public abstract class ActivityHubWebViewBinding extends ViewDataBinding {
    public final AppBarLayout appBarLayout;
    public final ProgressBar progressBar;
    public final Toolbar toolbar;
    public final WebView webView;

    protected ActivityHubWebViewBinding(Object obj, View view, int i, AppBarLayout appBarLayout2, ProgressBar progressBar2, Toolbar toolbar2, WebView webView2) {
        super(obj, view, i);
        this.appBarLayout = appBarLayout2;
        this.progressBar = progressBar2;
        this.toolbar = toolbar2;
        this.webView = webView2;
    }

    public static ActivityHubWebViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHubWebViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityHubWebViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_hub_web_view, viewGroup, z, obj);
    }

    public static ActivityHubWebViewBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHubWebViewBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityHubWebViewBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_hub_web_view, (ViewGroup) null, false, obj);
    }

    public static ActivityHubWebViewBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHubWebViewBinding bind(View view, Object obj) {
        return (ActivityHubWebViewBinding) bind(obj, view, R.layout.activity_hub_web_view);
    }
}
