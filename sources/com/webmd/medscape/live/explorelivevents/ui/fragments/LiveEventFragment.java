package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.customview.widget.Openable;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavArgs;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.FragmentKt;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.ToolbarKt;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.data.Resource;
import com.webmd.medscape.live.explorelivevents.data.Status;
import com.webmd.medscape.live.explorelivevents.databinding.FragmentLiveEventBinding;
import com.webmd.medscape.live.explorelivevents.databinding.NotFoundErrorViewBinding;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0003\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010\u0016\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0010H\u0016J \u0010\u0017\u001a\u00020\u0012\"\b\b\u0000\u0010\u0018*\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u0012H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0016J&\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020\u0012H\u0016J\u0012\u0010,\u001a\u00020\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0003R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\t\u001a\u00020\nX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/LiveEventFragment;", "Lcom/webmd/medscape/live/explorelivevents/ui/fragments/BaseFragment;", "()V", "args", "Lcom/webmd/medscape/live/explorelivevents/ui/fragments/LiveEventFragmentArgs;", "getArgs", "()Lcom/webmd/medscape/live/explorelivevents/ui/fragments/LiveEventFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "Lcom/webmd/medscape/live/explorelivevents/databinding/FragmentLiveEventBinding;", "getBinding", "()Lcom/webmd/medscape/live/explorelivevents/databinding/FragmentLiveEventBinding;", "setBinding", "(Lcom/webmd/medscape/live/explorelivevents/databinding/FragmentLiveEventBinding;)V", "url", "", "extractArguments", "", "Landroidx/navigation/NavArgs;", "handleErrorResponse", "message", "handleLoadingResponse", "handleSuccessResponse", "T", "", "resource", "Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "initBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "initUI", "observeViewModel", "onCreateView", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResume", "renderWebViewAndLoadSite", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventFragment.kt */
public final class LiveEventFragment extends BaseFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_EVENT_URL = "KEY_EVENT_URL";
    private HashMap _$_findViewCache;
    private final NavArgsLazy args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(LiveEventFragmentArgs.class), new LiveEventFragment$$special$$inlined$navArgs$1(this));
    public FragmentLiveEventBinding binding;
    private String url;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Status.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Status.LOADING.ordinal()] = 1;
            $EnumSwitchMapping$0[Status.SUCCESS.ordinal()] = 2;
            $EnumSwitchMapping$0[Status.ERROR.ordinal()] = 3;
        }
    }

    private final LiveEventFragmentArgs getArgs() {
        return (LiveEventFragmentArgs) this.args$delegate.getValue();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public final FragmentLiveEventBinding getBinding() {
        FragmentLiveEventBinding fragmentLiveEventBinding = this.binding;
        if (fragmentLiveEventBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return fragmentLiveEventBinding;
    }

    public final void setBinding(FragmentLiveEventBinding fragmentLiveEventBinding) {
        Intrinsics.checkNotNullParameter(fragmentLiveEventBinding, "<set-?>");
        this.binding = fragmentLiveEventBinding;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        initBinding(layoutInflater, viewGroup);
        initDependencies();
        initUI();
        FragmentLiveEventBinding fragmentLiveEventBinding = this.binding;
        if (fragmentLiveEventBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return fragmentLiveEventBinding.getRoot();
    }

    public void initUI() {
        extractArguments(getArgs());
        NavGraph graph = FragmentKt.findNavController(this).getGraph();
        Intrinsics.checkNotNullExpressionValue(graph, "findNavController().graph");
        AppBarConfiguration build = new AppBarConfiguration.Builder(graph).setOpenableLayout((Openable) null).setFallbackOnNavigateUpListener(new LiveEventFragment$inlined$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(LiveEventFragment$initUI$$inlined$AppBarConfiguration$1.INSTANCE)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        FragmentLiveEventBinding fragmentLiveEventBinding = this.binding;
        if (fragmentLiveEventBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar = fragmentLiveEventBinding.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar, "binding.toolbar");
        ToolbarKt.setupWithNavController(toolbar, FragmentKt.findNavController(this), build);
        FragmentLiveEventBinding fragmentLiveEventBinding2 = this.binding;
        if (fragmentLiveEventBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        NotFoundErrorViewBinding notFoundErrorViewBinding = fragmentLiveEventBinding2.lytError;
        Intrinsics.checkNotNullExpressionValue(notFoundErrorViewBinding, "binding.lytError");
        notFoundErrorViewBinding.setViewModel(getViewModel());
        FragmentLiveEventBinding fragmentLiveEventBinding3 = this.binding;
        if (fragmentLiveEventBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar2 = fragmentLiveEventBinding3.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar2, "binding.toolbar");
        setToolbar(toolbar2, build);
        FragmentLiveEventBinding fragmentLiveEventBinding4 = this.binding;
        if (fragmentLiveEventBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar3 = fragmentLiveEventBinding4.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar3, "binding.toolbar");
        renderToolbar(toolbar3, false);
        setupStatusBar(getStyleManager().getColors().getStatusBarColor());
        getViewModel().loadEventUrl(this.url);
    }

    private final void renderWebViewAndLoadSite(String str) {
        if (str != null) {
            FragmentLiveEventBinding fragmentLiveEventBinding = this.binding;
            if (fragmentLiveEventBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            fragmentLiveEventBinding.wvEvents.loadUrl(str);
        }
        FragmentLiveEventBinding fragmentLiveEventBinding2 = this.binding;
        if (fragmentLiveEventBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView = fragmentLiveEventBinding2.wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView, "binding.wvEvents");
        WebSettings settings = webView.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings, "binding.wvEvents.settings");
        settings.setJavaScriptEnabled(true);
        FragmentLiveEventBinding fragmentLiveEventBinding3 = this.binding;
        if (fragmentLiveEventBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView2 = fragmentLiveEventBinding3.wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView2, "binding.wvEvents");
        WebSettings settings2 = webView2.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings2, "binding.wvEvents.settings");
        settings2.setDomStorageEnabled(true);
        FragmentLiveEventBinding fragmentLiveEventBinding4 = this.binding;
        if (fragmentLiveEventBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView3 = fragmentLiveEventBinding4.wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView3, "binding.wvEvents");
        WebSettings settings3 = webView3.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings3, "binding.wvEvents.settings");
        settings3.setLoadWithOverviewMode(true);
        FragmentLiveEventBinding fragmentLiveEventBinding5 = this.binding;
        if (fragmentLiveEventBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView4 = fragmentLiveEventBinding5.wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView4, "binding.wvEvents");
        WebSettings settings4 = webView4.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings4, "binding.wvEvents.settings");
        settings4.setUseWideViewPort(true);
        FragmentLiveEventBinding fragmentLiveEventBinding6 = this.binding;
        if (fragmentLiveEventBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView5 = fragmentLiveEventBinding6.wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView5, "binding.wvEvents");
        WebSettings settings5 = webView5.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings5, "binding.wvEvents.settings");
        settings5.setBuiltInZoomControls(true);
        FragmentLiveEventBinding fragmentLiveEventBinding7 = this.binding;
        if (fragmentLiveEventBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView6 = fragmentLiveEventBinding7.wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView6, "binding.wvEvents");
        WebSettings settings6 = webView6.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings6, "binding.wvEvents.settings");
        settings6.setDisplayZoomControls(false);
        FragmentLiveEventBinding fragmentLiveEventBinding8 = this.binding;
        if (fragmentLiveEventBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView7 = fragmentLiveEventBinding8.wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView7, "binding.wvEvents");
        webView7.getSettings().setSupportZoom(true);
        FragmentLiveEventBinding fragmentLiveEventBinding9 = this.binding;
        if (fragmentLiveEventBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView8 = fragmentLiveEventBinding9.wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView8, "binding.wvEvents");
        WebSettings settings7 = webView8.getSettings();
        Intrinsics.checkNotNullExpressionValue(settings7, "binding.wvEvents.settings");
        settings7.setDefaultTextEncodingName("utf-8");
        FragmentLiveEventBinding fragmentLiveEventBinding10 = this.binding;
        if (fragmentLiveEventBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView9 = fragmentLiveEventBinding10.wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView9, "binding.wvEvents");
        webView9.setWebViewClient(new LiveEventFragment$renderWebViewAndLoadSite$2(this));
    }

    public void observeViewModel() {
        getViewModel().observeLiveEventUrl().observe(getViewLifecycleOwner(), new LiveEventFragment$observeViewModel$1(this));
    }

    public <T> void handleSuccessResponse(Resource<T> resource) {
        Intrinsics.checkNotNullParameter(resource, ContentParser.RESOURCE);
        renderWebViewAndLoadSite((String) resource.getData());
    }

    public void handleErrorResponse(String str) {
        MutableLiveData<Error> errorObserver = getViewModel().getErrorObserver();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        errorObserver.setValue(new Error(true, str, ExtensionsKt.getErrorImageRes(0, requireContext)));
    }

    public void handleLoadingResponse(String str) {
        FragmentLiveEventBinding fragmentLiveEventBinding = this.binding;
        if (fragmentLiveEventBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        RelativeLayout relativeLayout = fragmentLiveEventBinding.lytLoader;
        Intrinsics.checkNotNullExpressionValue(relativeLayout, "binding.lytLoader");
        relativeLayout.setVisibility(0);
        FragmentLiveEventBinding fragmentLiveEventBinding2 = this.binding;
        if (fragmentLiveEventBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        WebView webView = fragmentLiveEventBinding2.wvEvents;
        Intrinsics.checkNotNullExpressionValue(webView, "binding.wvEvents");
        webView.setVisibility(8);
    }

    public void onResume() {
        super.onResume();
        observeViewModel();
    }

    public void extractArguments(NavArgs navArgs) {
        if (navArgs == null) {
            return;
        }
        if (navArgs != null) {
            LiveEventFragmentArgs liveEventFragmentArgs = (LiveEventFragmentArgs) navArgs;
            this.url = liveEventFragmentArgs.getEventUrl();
            StyleManager styleManager = liveEventFragmentArgs.getStyleManager();
            if (styleManager == null) {
                styleManager = new StyleManager(false, 1, (DefaultConstructorMarker) null);
            }
            setStyleManager(styleManager);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.ui.fragments.LiveEventFragmentArgs");
    }

    public void initBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        FragmentLiveEventBinding inflate = FragmentLiveEventBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "FragmentLiveEventBinding…flater, container, false)");
        this.binding = inflate;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        FragmentKt.findNavController(this).navigateUp();
        return true;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/LiveEventFragment$Companion;", "", "()V", "KEY_EVENT_URL", "", "getArgs", "Landroid/os/Bundle;", "eventUrl", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: LiveEventFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Bundle getArgs(String str, StyleManager styleManager) {
            Intrinsics.checkNotNullParameter(str, "eventUrl");
            Intrinsics.checkNotNullParameter(styleManager, "styleManager");
            Bundle bundle = new Bundle();
            bundle.putString(LiveEventFragment.KEY_EVENT_URL, str);
            bundle.putParcelable("styleManager", styleManager);
            return bundle;
        }
    }
}
