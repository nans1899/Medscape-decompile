package com.webmd.medscape.live.explorelivevents.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.webmd.medscape.live.explorelivevents.R;

public final class FragmentLiveEventBinding implements ViewBinding {
    public final AppBarLayout actionBar;
    public final NotFoundErrorViewBinding lytError;
    public final RelativeLayout lytLoader;
    public final ProgressBar progressBar;
    private final CoordinatorLayout rootView;
    public final Toolbar toolbar;
    public final WebView wvEvents;

    private FragmentLiveEventBinding(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, NotFoundErrorViewBinding notFoundErrorViewBinding, RelativeLayout relativeLayout, ProgressBar progressBar2, Toolbar toolbar2, WebView webView) {
        this.rootView = coordinatorLayout;
        this.actionBar = appBarLayout;
        this.lytError = notFoundErrorViewBinding;
        this.lytLoader = relativeLayout;
        this.progressBar = progressBar2;
        this.toolbar = toolbar2;
        this.wvEvents = webView;
    }

    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static FragmentLiveEventBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, (ViewGroup) null, false);
    }

    public static FragmentLiveEventBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_live_event, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000b, code lost:
        r0 = com.webmd.medscape.live.explorelivevents.R.id.lyt_error;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.webmd.medscape.live.explorelivevents.databinding.FragmentLiveEventBinding bind(android.view.View r10) {
        /*
            int r0 = com.webmd.medscape.live.explorelivevents.R.id.actionBar
            android.view.View r1 = r10.findViewById(r0)
            r4 = r1
            com.google.android.material.appbar.AppBarLayout r4 = (com.google.android.material.appbar.AppBarLayout) r4
            if (r4 == 0) goto L_0x004d
            int r0 = com.webmd.medscape.live.explorelivevents.R.id.lyt_error
            android.view.View r1 = r10.findViewById(r0)
            if (r1 == 0) goto L_0x004d
            com.webmd.medscape.live.explorelivevents.databinding.NotFoundErrorViewBinding r5 = com.webmd.medscape.live.explorelivevents.databinding.NotFoundErrorViewBinding.bind(r1)
            int r0 = com.webmd.medscape.live.explorelivevents.R.id.lyt_loader
            android.view.View r1 = r10.findViewById(r0)
            r6 = r1
            android.widget.RelativeLayout r6 = (android.widget.RelativeLayout) r6
            if (r6 == 0) goto L_0x004d
            int r0 = com.webmd.medscape.live.explorelivevents.R.id.progress_bar
            android.view.View r1 = r10.findViewById(r0)
            r7 = r1
            android.widget.ProgressBar r7 = (android.widget.ProgressBar) r7
            if (r7 == 0) goto L_0x004d
            int r0 = com.webmd.medscape.live.explorelivevents.R.id.toolbar
            android.view.View r1 = r10.findViewById(r0)
            r8 = r1
            androidx.appcompat.widget.Toolbar r8 = (androidx.appcompat.widget.Toolbar) r8
            if (r8 == 0) goto L_0x004d
            int r0 = com.webmd.medscape.live.explorelivevents.R.id.wv_events
            android.view.View r1 = r10.findViewById(r0)
            r9 = r1
            android.webkit.WebView r9 = (android.webkit.WebView) r9
            if (r9 == 0) goto L_0x004d
            com.webmd.medscape.live.explorelivevents.databinding.FragmentLiveEventBinding r0 = new com.webmd.medscape.live.explorelivevents.databinding.FragmentLiveEventBinding
            r3 = r10
            androidx.coordinatorlayout.widget.CoordinatorLayout r3 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r3
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return r0
        L_0x004d:
            android.content.res.Resources r10 = r10.getResources()
            java.lang.String r10 = r10.getResourceName(r0)
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "Missing required view with ID: "
            java.lang.String r10 = r1.concat(r10)
            r0.<init>(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.databinding.FragmentLiveEventBinding.bind(android.view.View):com.webmd.medscape.live.explorelivevents.databinding.FragmentLiveEventBinding");
    }
}
