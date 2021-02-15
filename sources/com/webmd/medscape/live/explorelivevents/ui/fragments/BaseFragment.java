package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.content.Context;
import android.os.Build;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavArgs;
import androidx.navigation.fragment.FragmentKt;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.ToolbarKt;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.medscape.live.explorelivevents.common.BaseFragmentView;
import com.webmd.medscape.live.explorelivevents.common.IOmnitureListener;
import com.webmd.medscape.live.explorelivevents.common.OmnitureInfo;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.Resource;
import com.webmd.medscape.live.explorelivevents.factories.ExploreViewModelFactory;
import com.webmd.medscape.live.explorelivevents.network.BasicAuthInterceptor;
import com.webmd.medscape.live.explorelivevents.persistence.SharedPreferencesManager;
import com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel;
import com.webmd.medscape.live.explorelivevents.util.Constants;
import com.webmd.medscape.live.explorelivevents.util.GsonUtils;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0002J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+J\u0018\u0010,\u001a\u00020#2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020'H\u0016J\b\u00100\u001a\u00020#H\u0016J\u0010\u00101\u001a\u00020#2\u0006\u00102\u001a\u000203H\u0016J\u0018\u00104\u001a\u00020#2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u00020'H\u0016J\u0010\u00108\u001a\u00020#2\u0006\u00109\u001a\u00020:H\u0016J\u0010\u0010;\u001a\u00020#2\u0006\u00109\u001a\u00020:H\u0016J\u0018\u0010<\u001a\u00020#2\u0006\u00105\u001a\u0002062\u0006\u0010=\u001a\u00020>H\u0016J\u0010\u0010?\u001a\u00020#2\u0006\u0010@\u001a\u00020AH\u0016J0\u0010B\u001a\u00020#2\u0006\u00105\u001a\u0002062\u0006\u0010C\u001a\u00020A2\u0006\u0010D\u001a\u00020A2\u0006\u0010E\u001a\u00020A2\u0006\u00107\u001a\u00020'H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u0006F"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/BaseFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/webmd/medscape/live/explorelivevents/common/BaseFragmentView;", "()V", "iOmnitureListener", "Lcom/webmd/medscape/live/explorelivevents/common/IOmnitureListener;", "getIOmnitureListener", "()Lcom/webmd/medscape/live/explorelivevents/common/IOmnitureListener;", "setIOmnitureListener", "(Lcom/webmd/medscape/live/explorelivevents/common/IOmnitureListener;)V", "preferences", "Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;", "getPreferences", "()Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;", "setPreferences", "(Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;)V", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "getStyleManager", "()Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "setStyleManager", "(Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;)V", "viewModel", "Lcom/webmd/medscape/live/explorelivevents/ui/viewmodels/ExploreEventsViewModel;", "getViewModel", "()Lcom/webmd/medscape/live/explorelivevents/ui/viewmodels/ExploreEventsViewModel;", "setViewModel", "(Lcom/webmd/medscape/live/explorelivevents/ui/viewmodels/ExploreEventsViewModel;)V", "viewModelFactory", "Lcom/webmd/medscape/live/explorelivevents/factories/ExploreViewModelFactory;", "getViewModelFactory", "()Lcom/webmd/medscape/live/explorelivevents/factories/ExploreViewModelFactory;", "setViewModelFactory", "(Lcom/webmd/medscape/live/explorelivevents/factories/ExploreViewModelFactory;)V", "clearFilterSelections", "", "getHttpClient", "Lokhttp3/OkHttpClient;", "isInStagingEnv", "", "getRetrofit", "Lretrofit2/Retrofit;", "baseUrl", "", "hideView", "view", "Landroid/view/View;", "hide", "initDependencies", "onAttach", "context", "Landroid/content/Context;", "renderToolbar", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "isFirstScreen", "sendOmnitureActionCallToApp", "omnitureInfoParams", "Lcom/webmd/medscape/live/explorelivevents/common/OmnitureInfo;", "sendOmniturePageViewCallToApp", "setToolbar", "appBarConfiguration", "Landroidx/navigation/ui/AppBarConfiguration;", "setupStatusBar", "statusBarColor", "", "setupToolbar", "backgroundColor", "titleColor", "navIconRes", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: BaseFragment.kt */
public abstract class BaseFragment extends Fragment implements BaseFragmentView {
    private HashMap _$_findViewCache;
    protected IOmnitureListener iOmnitureListener;
    public SharedPreferencesManager preferences;
    protected StyleManager styleManager;
    public ExploreEventsViewModel viewModel;
    public ExploreViewModelFactory viewModelFactory;

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

    public void extractArguments(NavArgs navArgs) {
        BaseFragmentView.DefaultImpls.extractArguments(this, navArgs);
    }

    public void handleErrorResponse(String str) {
        BaseFragmentView.DefaultImpls.handleErrorResponse(this, str);
    }

    public void handleLoadingResponse(String str) {
        BaseFragmentView.DefaultImpls.handleLoadingResponse(this, str);
    }

    public <T> void handleSuccessResponse(Resource<T> resource) {
        Intrinsics.checkNotNullParameter(resource, ContentParser.RESOURCE);
        BaseFragmentView.DefaultImpls.handleSuccessResponse(this, resource);
    }

    public void initUI() {
        BaseFragmentView.DefaultImpls.initUI(this);
    }

    public void navigateToFilterSelection(int i) {
        BaseFragmentView.DefaultImpls.navigateToFilterSelection(this, i);
    }

    public void observeViewModel() {
        BaseFragmentView.DefaultImpls.observeViewModel(this);
    }

    public void renderMenuItem(MenuItem menuItem, int i) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        BaseFragmentView.DefaultImpls.renderMenuItem(this, menuItem, i);
    }

    public final SharedPreferencesManager getPreferences() {
        SharedPreferencesManager sharedPreferencesManager = this.preferences;
        if (sharedPreferencesManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        }
        return sharedPreferencesManager;
    }

    public final void setPreferences(SharedPreferencesManager sharedPreferencesManager) {
        Intrinsics.checkNotNullParameter(sharedPreferencesManager, "<set-?>");
        this.preferences = sharedPreferencesManager;
    }

    public final ExploreViewModelFactory getViewModelFactory() {
        ExploreViewModelFactory exploreViewModelFactory = this.viewModelFactory;
        if (exploreViewModelFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        }
        return exploreViewModelFactory;
    }

    public final void setViewModelFactory(ExploreViewModelFactory exploreViewModelFactory) {
        Intrinsics.checkNotNullParameter(exploreViewModelFactory, "<set-?>");
        this.viewModelFactory = exploreViewModelFactory;
    }

    public final ExploreEventsViewModel getViewModel() {
        ExploreEventsViewModel exploreEventsViewModel = this.viewModel;
        if (exploreEventsViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return exploreEventsViewModel;
    }

    public final void setViewModel(ExploreEventsViewModel exploreEventsViewModel) {
        Intrinsics.checkNotNullParameter(exploreEventsViewModel, "<set-?>");
        this.viewModel = exploreEventsViewModel;
    }

    /* access modifiers changed from: protected */
    public final IOmnitureListener getIOmnitureListener() {
        IOmnitureListener iOmnitureListener2 = this.iOmnitureListener;
        if (iOmnitureListener2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iOmnitureListener");
        }
        return iOmnitureListener2;
    }

    /* access modifiers changed from: protected */
    public final void setIOmnitureListener(IOmnitureListener iOmnitureListener2) {
        Intrinsics.checkNotNullParameter(iOmnitureListener2, "<set-?>");
        this.iOmnitureListener = iOmnitureListener2;
    }

    /* access modifiers changed from: protected */
    public final StyleManager getStyleManager() {
        StyleManager styleManager2 = this.styleManager;
        if (styleManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleManager");
        }
        return styleManager2;
    }

    /* access modifiers changed from: protected */
    public final void setStyleManager(StyleManager styleManager2) {
        Intrinsics.checkNotNullParameter(styleManager2, "<set-?>");
        this.styleManager = styleManager2;
    }

    private final OkHttpClient getHttpClient(boolean z) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS).callTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS);
        if (z) {
            builder.addInterceptor(new BasicAuthInterceptor(Constants.USERNAME, "staging"));
        }
        builder.addInterceptor(new HttpLoggingInterceptor((HttpLoggingInterceptor.Logger) null, 1, (DefaultConstructorMarker) null).setLevel(HttpLoggingInterceptor.Level.NONE));
        return builder.build();
    }

    public final Retrofit getRetrofit(String str) {
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        Retrofit build = new Retrofit.Builder().baseUrl(str).client(getHttpClient(StringsKt.contains$default((CharSequence) str, (CharSequence) "staging", false, 2, (Object) null))).addConverterFactory(GsonConverterFactory.create(GsonUtils.INSTANCE.getGson())).build();
        Intrinsics.checkNotNullExpressionValue(build, "Retrofit.Builder()\n     …()))\n            .build()");
        return build;
    }

    public void renderToolbar(Toolbar toolbar, boolean z) {
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        StyleManager styleManager2 = this.styleManager;
        if (styleManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleManager");
        }
        styleManager2.setFirstScreen(z);
        StyleManager styleManager3 = this.styleManager;
        if (styleManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleManager");
        }
        int navHeaderBackgroundColor = styleManager3.getColors().getNavHeaderBackgroundColor();
        StyleManager styleManager4 = this.styleManager;
        if (styleManager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleManager");
        }
        int navTitleColor = styleManager4.getColors().getNavTitleColor();
        StyleManager styleManager5 = this.styleManager;
        if (styleManager5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleManager");
        }
        setupToolbar(toolbar, navHeaderBackgroundColor, navTitleColor, styleManager5.getIcons().getNavIcon(), z);
    }

    public void hideView(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (z) {
            view.setVisibility(4);
        }
    }

    public void setToolbar(Toolbar toolbar, AppBarConfiguration appBarConfiguration) {
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        Intrinsics.checkNotNullParameter(appBarConfiguration, "appBarConfiguration");
        ToolbarKt.setupWithNavController(toolbar, FragmentKt.findNavController(this), appBarConfiguration);
    }

    public void setupToolbar(Toolbar toolbar, int i, int i2, int i3, boolean z) {
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        if (!z) {
            toolbar.setNavigationIcon(ContextCompat.getDrawable(requireContext(), i3));
        }
        toolbar.setBackgroundColor(ContextCompat.getColor(requireContext(), i));
        toolbar.setTitleTextColor(ContextCompat.getColor(requireContext(), i2));
    }

    public void setupStatusBar(int i) {
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        Window window = requireActivity.getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.clearFlags(67108864);
            window.addFlags(Integer.MIN_VALUE);
            Intrinsics.checkNotNullExpressionValue(window, "window");
            window.setStatusBarColor(ContextCompat.getColor(requireContext(), i));
        }
    }

    public void initDependencies() {
        SharedPreferencesManager.Companion companion = SharedPreferencesManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.preferences = companion.getInstance(requireContext);
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        SharedPreferencesManager sharedPreferencesManager = this.preferences;
        if (sharedPreferencesManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        }
        ExploreViewModelFactory exploreViewModelFactory = new ExploreViewModelFactory(requireContext2, sharedPreferencesManager);
        this.viewModelFactory = exploreViewModelFactory;
        ViewModelStoreOwner viewModelStoreOwner = this;
        if (exploreViewModelFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModelFactory");
        }
        ViewModel viewModel2 = new ViewModelProvider(viewModelStoreOwner, (ViewModelProvider.Factory) exploreViewModelFactory).get(ExploreEventsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel2, "ViewModelProvider(this, …ntsViewModel::class.java]");
        this.viewModel = (ExploreEventsViewModel) viewModel2;
    }

    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        try {
            this.iOmnitureListener = (IOmnitureListener) context;
        } catch (ClassCastException unused) {
            throw new ClassCastException(context + " must implement IOmnitureListener");
        }
    }

    public void sendOmniturePageViewCallToApp(OmnitureInfo omnitureInfo) {
        Intrinsics.checkNotNullParameter(omnitureInfo, "omnitureInfoParams");
        IOmnitureListener iOmnitureListener2 = this.iOmnitureListener;
        if (iOmnitureListener2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iOmnitureListener");
        }
        iOmnitureListener2.sendOmniturePageView(omnitureInfo);
    }

    public void sendOmnitureActionCallToApp(OmnitureInfo omnitureInfo) {
        Intrinsics.checkNotNullParameter(omnitureInfo, "omnitureInfoParams");
        IOmnitureListener iOmnitureListener2 = this.iOmnitureListener;
        if (iOmnitureListener2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("iOmnitureListener");
        }
        iOmnitureListener2.sendOmnitureActionCall(omnitureInfo);
    }

    public void clearFilterSelections() {
        SharedPreferencesManager sharedPreferencesManager = this.preferences;
        if (sharedPreferencesManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        }
        sharedPreferencesManager.clear("KEY_SELECTED_SPECIALTIES");
        SharedPreferencesManager sharedPreferencesManager2 = this.preferences;
        if (sharedPreferencesManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        }
        sharedPreferencesManager2.clear("KEY_SELECTED_LOCATIONS");
        SharedPreferencesManager sharedPreferencesManager3 = this.preferences;
        if (sharedPreferencesManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        }
        sharedPreferencesManager3.clear("KEY_ALL_SPECIALTIES_SELECTED");
        SharedPreferencesManager sharedPreferencesManager4 = this.preferences;
        if (sharedPreferencesManager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        }
        sharedPreferencesManager4.clear("KEY_ALL_LOCATIONS_SELECTED");
    }
}
