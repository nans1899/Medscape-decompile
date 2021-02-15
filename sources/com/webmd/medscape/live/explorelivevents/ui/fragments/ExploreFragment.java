package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.os.BundleKt;
import androidx.customview.widget.Openable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavArgs;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.FragmentKt;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.medscape.live.explorelivevents.common.OmnitureInfo;
import com.webmd.medscape.live.explorelivevents.common.OnFilterSelectedListener;
import com.webmd.medscape.live.explorelivevents.common.OnLiveEventSelectedListener;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;
import com.webmd.medscape.live.explorelivevents.data.Resource;
import com.webmd.medscape.live.explorelivevents.data.Status;
import com.webmd.medscape.live.explorelivevents.data.UiState;
import com.webmd.medscape.live.explorelivevents.databinding.FragmentExploreBinding;
import com.webmd.medscape.live.explorelivevents.databinding.NotFoundErrorViewBinding;
import com.webmd.medscape.live.explorelivevents.persistence.SharedPreferencesManager;
import com.webmd.medscape.live.explorelivevents.ui.adapters.EventsRecyclerViewAdapter;
import com.webmd.medscape.live.explorelivevents.ui.decorators.MiddleDividerItemDecoration;
import com.webmd.medscape.live.explorelivevents.ui.fragments.ExploreFragmentDirections;
import com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel;
import com.webmd.medscape.live.explorelivevents.util.Constants;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.ZonedDateTime;
import retrofit2.Retrofit;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 A2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001AB\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0012\u0010\u0017\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0019\u001a\u00020\u00142\b\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\u0016J \u0010\u001a\u001a\u00020\u0014\"\b\b\u0000\u0010\u001b*\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u0014H\u0002J\b\u0010%\u001a\u00020\u0014H\u0016J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u000eH\u0016J\b\u0010(\u001a\u00020\u0014H\u0016J\u0012\u0010)\u001a\u00020\u00142\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0010\u0010,\u001a\u00020\u00142\u0006\u0010-\u001a\u00020.H\u0016J(\u0010,\u001a\u00020\u00142\u0006\u0010/\u001a\u00020\u00062\u0016\u00100\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u000102\u0012\u0006\u0012\u0004\u0018\u00010201H\u0016J\u0018\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u0002052\u0006\u0010 \u001a\u000206H\u0016J&\u00107\u001a\u0004\u0018\u00010\n2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u00108\u001a\u00020\u0014H\u0016J\b\u00109\u001a\u00020\u0014H\u0016J\b\u0010:\u001a\u00020\u0014H\u0016J\b\u0010;\u001a\u00020\u0014H\u0016J\b\u0010<\u001a\u00020\u0014H\u0016J\u001a\u0010=\u001a\u00020\u00142\u0006\u0010>\u001a\u00020\n2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010?\u001a\u00020\u0014H\u0002J \u0010@\u001a\u00020\u00142\u0016\u00100\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u000102\u0012\u0006\u0012\u0004\u0018\u00010201H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u00020\u000e8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/ExploreFragment;", "Lcom/webmd/medscape/live/explorelivevents/ui/fragments/BaseFragment;", "Lcom/webmd/medscape/live/explorelivevents/common/OnFilterSelectedListener;", "Lcom/webmd/medscape/live/explorelivevents/common/OnLiveEventSelectedListener;", "()V", "baseUrl", "", "binding", "Lcom/webmd/medscape/live/explorelivevents/databinding/FragmentExploreBinding;", "customHeader", "Landroid/view/View;", "eventsAdapter", "Lcom/webmd/medscape/live/explorelivevents/ui/adapters/EventsRecyclerViewAdapter;", "layoutResId", "", "getLayoutResId", "()I", "retrofit", "Lretrofit2/Retrofit;", "extractArguments", "", "args", "Landroidx/navigation/NavArgs;", "handleErrorResponse", "message", "handleLoadingResponse", "handleSuccessResponse", "T", "", "resource", "Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "initBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "initRecyclerView", "initUI", "navigateToFilterSelection", "viewType", "observeViewModel", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onClick", "event", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "title", "datesSelected", "Lkotlin/Pair;", "Lorg/threeten/bp/ZonedDateTime;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "Landroid/view/MenuInflater;", "onCreateView", "onDateRangeFilterSelected", "onDestroyView", "onLocationFilterSelected", "onResume", "onSpecialtyFilterSelected", "onViewCreated", "view", "renderLocationText", "showEvents", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ExploreFragment.kt */
public final class ExploreFragment extends BaseFragment implements OnFilterSelectedListener, OnLiveEventSelectedListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String TAG;
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public String baseUrl;
    private FragmentExploreBinding binding;
    private View customHeader;
    /* access modifiers changed from: private */
    public final EventsRecyclerViewAdapter eventsAdapter = new EventsRecyclerViewAdapter(this, (StyleManager) null, 2, (DefaultConstructorMarker) null);
    private final int layoutResId = R.layout.fragment_explore;
    private Retrofit retrofit;

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

    public static final /* synthetic */ String access$getBaseUrl$p(ExploreFragment exploreFragment) {
        String str = exploreFragment.baseUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseUrl");
        }
        return str;
    }

    public void onSpecificFilterSelected() {
        OnFilterSelectedListener.DefaultImpls.onSpecificFilterSelected(this);
    }

    public final int getLayoutResId() {
        return this.layoutResId;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        initBinding(layoutInflater, viewGroup);
        extractArguments((NavArgs) null);
        NavGraph graph = FragmentKt.findNavController(this).getGraph();
        Intrinsics.checkNotNullExpressionValue(graph, "findNavController().graph");
        AppBarConfiguration build = new AppBarConfiguration.Builder(graph).setOpenableLayout((Openable) null).setFallbackOnNavigateUpListener(new ExploreFragment$inlined$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(ExploreFragment$onCreateView$$inlined$AppBarConfiguration$1.INSTANCE)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        FragmentExploreBinding fragmentExploreBinding = this.binding;
        if (fragmentExploreBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar = fragmentExploreBinding.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar, "binding.toolbar");
        setToolbar(toolbar, build);
        FragmentExploreBinding fragmentExploreBinding2 = this.binding;
        if (fragmentExploreBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar2 = fragmentExploreBinding2.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar2, "binding.toolbar");
        renderToolbar(toolbar2, true);
        setupStatusBar(getStyleManager().getColors().getStatusBarColor());
        View customView = getStyleManager().getCustomView();
        this.customHeader = customView;
        if (customView != null) {
            FragmentExploreBinding fragmentExploreBinding3 = this.binding;
            if (fragmentExploreBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            Toolbar toolbar3 = fragmentExploreBinding3.toolbar;
            Intrinsics.checkNotNullExpressionValue(toolbar3, "binding.toolbar");
            toolbar3.setTitle((CharSequence) "");
            FragmentExploreBinding fragmentExploreBinding4 = this.binding;
            if (fragmentExploreBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            fragmentExploreBinding4.toolbar.addView(customView);
        }
        initDependencies();
        initRecyclerView();
        clearFilterSelections();
        FragmentExploreBinding fragmentExploreBinding5 = this.binding;
        if (fragmentExploreBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return fragmentExploreBinding5.getRoot();
    }

    public void onDestroyView() {
        View view = this.customHeader;
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeAllViews();
            } else {
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup");
            }
        }
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    private final void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        FragmentExploreBinding fragmentExploreBinding = this.binding;
        if (fragmentExploreBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        RecyclerView recyclerView = fragmentExploreBinding.rvEvents;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "this");
        recyclerView.setAdapter(this.eventsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        Context context = recyclerView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        recyclerView.addItemDecoration(new MiddleDividerItemDecoration(context, 1));
        this.eventsAdapter.registerAdapterDataObserver(new ExploreFragment$initRecyclerView$2(this));
    }

    public void initUI() {
        renderLocationText();
        FragmentExploreBinding fragmentExploreBinding = this.binding;
        if (fragmentExploreBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentExploreBinding.lytFilters.setOnFilterSelectedListener(this);
        FragmentExploreBinding fragmentExploreBinding2 = this.binding;
        if (fragmentExploreBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentExploreBinding2.lytFilters.setStyle(getStyleManager());
        FragmentExploreBinding fragmentExploreBinding3 = this.binding;
        if (fragmentExploreBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentExploreBinding3.btShowAll.setOnClickListener(new ExploreFragment$initUI$1(this));
        FragmentExploreBinding fragmentExploreBinding4 = this.binding;
        if (fragmentExploreBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentExploreBinding4.setViewModel(getViewModel());
        FragmentExploreBinding fragmentExploreBinding5 = this.binding;
        if (fragmentExploreBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        NotFoundErrorViewBinding notFoundErrorViewBinding = fragmentExploreBinding5.notFound;
        Intrinsics.checkNotNullExpressionValue(notFoundErrorViewBinding, "binding.notFound");
        notFoundErrorViewBinding.setViewModel(getViewModel());
        ZonedDateTime now = ZonedDateTime.now();
        ExploreEventsViewModel viewModel = getViewModel();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        viewModel.getFiltersFromApi(requireContext);
        getViewModel().loadEvents((List<String>) null, (List<String>) null, ExtensionsKt.toServerDate(now), (String) null);
    }

    private final void renderLocationText() {
        String string = getString(R.string.events_happening);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.events_happening)");
        FragmentExploreBinding fragmentExploreBinding = this.binding;
        if (fragmentExploreBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        TextView textView = fragmentExploreBinding.tvEventsHappening;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.tvEventsHappening");
        textView.setText(string);
    }

    public void navigateToFilterSelection(int i) {
        ExploreFragmentDirections.Companion companion = ExploreFragmentDirections.Companion;
        String str = this.baseUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseUrl");
        }
        FragmentKt.findNavController(this).navigate(companion.actionExploreToFilterSelection(i, true, str, (String) null, getStyleManager(), (String) null, (String) null));
    }

    /* access modifiers changed from: private */
    public final void showEvents(Pair<ZonedDateTime, ZonedDateTime> pair) {
        ExploreFragmentDirections.Companion companion = ExploreFragmentDirections.Companion;
        String str = this.baseUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseUrl");
        }
        FragmentKt.findNavController(this).navigate(companion.actionExploreToEvents(str, (String) null, getStyleManager(), ExtensionsKt.toServerDate(pair.getFirst()), ExtensionsKt.toServerDate(pair.getSecond())));
    }

    public void observeViewModel() {
        getViewModel().observeLiveEvents().observe(getViewLifecycleOwner(), new ExploreFragment$observeViewModel$1(this));
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        Intrinsics.checkNotNullParameter(menu, Category.K_MENU_CATEGORY);
        Intrinsics.checkNotNullParameter(menuInflater, "inflater");
        menuInflater.inflate(R.menu.menu_explore, menu);
    }

    public void initBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(layoutInflater, this.layoutResId, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "DataBindingUtil.inflate(…tResId, container, false)");
        FragmentExploreBinding fragmentExploreBinding = (FragmentExploreBinding) inflate;
        this.binding = fragmentExploreBinding;
        if (fragmentExploreBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentExploreBinding.setLifecycleOwner(this);
    }

    /* JADX WARNING: type inference failed for: r0v7, types: [com.webmd.medscape.live.explorelivevents.data.UiState] */
    public <T> void handleSuccessResponse(Resource<T> resource) {
        UiState uiState;
        Intrinsics.checkNotNullParameter(resource, ContentParser.RESOURCE);
        Collection data = resource.getData();
        if (data != null) {
            Set linkedHashSet = new LinkedHashSet();
            linkedHashSet.addAll((List) data);
            List list = CollectionsKt.toList(linkedHashSet);
            if (list.size() < 8) {
                this.eventsAdapter.refresh(list);
            } else {
                this.eventsAdapter.refresh(CollectionsKt.take(list, 8));
            }
            if (list.size() > 8) {
                MutableLiveData<UiState> uiState2 = getViewModel().getUiState();
                ? uiState3 = getViewModel().getUiState();
                if (uiState3 != 0) {
                    uiState = uiState3.copy(false, false, true, true);
                } else {
                    uiState = null;
                }
                uiState2.setValue(uiState);
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<com.webmd.medscape.live.explorelivevents.data.LiveEvent>");
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.webmd.medscape.live.explorelivevents.data.UiState] */
    public void handleLoadingResponse(String str) {
        UiState uiState = null;
        getViewModel().getErrorObserver().setValue(new Error(false, (String) null, (Drawable) null));
        MutableLiveData<UiState> uiState2 = getViewModel().getUiState();
        ? uiState3 = getViewModel().getUiState();
        if (uiState3 != 0) {
            uiState = uiState3.copy(true, false, false, false);
        }
        uiState2.setValue(uiState);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [com.webmd.medscape.live.explorelivevents.data.UiState] */
    public void handleErrorResponse(String str) {
        UiState uiState = null;
        getViewModel().getErrorObserver().setValue(new Error(true, str, (Drawable) null));
        MutableLiveData<UiState> uiState2 = getViewModel().getUiState();
        ? uiState3 = getViewModel().getUiState();
        if (uiState3 != 0) {
            uiState = UiState.copy$default(uiState3, false, false, false, false, 8, (Object) null);
        }
        uiState2.setValue(uiState);
    }

    public void extractArguments(NavArgs navArgs) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString(Constants.KEY_BASE_URL);
            if (string != null) {
                this.baseUrl = string;
                Parcelable parcelable = arguments.getParcelable("styleManager");
                if (parcelable != null) {
                    setStyleManager((StyleManager) parcelable);
                    FragmentExploreBinding fragmentExploreBinding = this.binding;
                    if (fragmentExploreBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    }
                    fragmentExploreBinding.setStyleManager(getStyleManager());
                    this.eventsAdapter.setStyle(getStyleManager());
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.common.StyleManager");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
    }

    public void onResume() {
        super.onResume();
        initUI();
        sendOmniturePageViewCallToApp(new OmnitureInfo.Builder((List) null, (String) null, (String) null, (String) null, (String) null, (String) null, 63, (DefaultConstructorMarker) null).pageNames(CollectionsKt.mutableListOf(OmnitureConstants.PAGE_NAME_EXPLORE, OmnitureConstants.PAGE_NAME_BROWSE)).channel(OmnitureConstants.CHANNEL).build());
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        observeViewModel();
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        SharedPreferencesManager preferences = getPreferences();
        String str = this.baseUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseUrl");
        }
        preferences.saveString("KEY_PREFS_BASE_URL", str);
        String str2 = this.baseUrl;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseUrl");
        }
        this.retrofit = getRetrofit(str2);
        ExploreEventsViewModel viewModel = getViewModel();
        Retrofit retrofit3 = this.retrofit;
        if (retrofit3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
        }
        viewModel.setRetrofit(retrofit3);
    }

    public void onClick(String str, Pair<ZonedDateTime, ZonedDateTime> pair) {
        Intrinsics.checkNotNullParameter(str, "title");
        Intrinsics.checkNotNullParameter(pair, "datesSelected");
        showEvents(pair);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/ExploreFragment$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "getArgs", "Landroid/os/Bundle;", "baseUrl", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ExploreFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return ExploreFragment.TAG;
        }

        public static /* synthetic */ Bundle getArgs$default(Companion companion, String str, StyleManager styleManager, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "https://www.medscapelive.com/api/";
            }
            if ((i & 2) != 0) {
                styleManager = new StyleManager(false, 1, (DefaultConstructorMarker) null);
            }
            return companion.getArgs(str, styleManager);
        }

        public final Bundle getArgs(String str, StyleManager styleManager) {
            Intrinsics.checkNotNullParameter(str, "baseUrl");
            Intrinsics.checkNotNullParameter(styleManager, "styleManager");
            return BundleKt.bundleOf(TuplesKt.to(Constants.KEY_BASE_URL, str), TuplesKt.to("styleManager", styleManager));
        }
    }

    static {
        String simpleName = ExploreFragment.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "ExploreFragment::class.java.simpleName");
        TAG = simpleName;
    }

    public void onClick(LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(liveEvent, "event");
        sendOmnitureActionCallToApp(new OmnitureInfo.Builder((List) null, (String) null, (String) null, (String) null, (String) null, (String) null, 63, (DefaultConstructorMarker) null).pageNames(CollectionsKt.mutableListOf(OmnitureConstants.PAGE_NAME_EXPLORE, OmnitureConstants.PAGE_NAME_BROWSE)).channel(OmnitureConstants.CHANNEL).module(OmnitureConstants.OMNITURE_MODULE_EVENT_CLICK).link(OmnitureConstants.OMNITURE_LINK_EVENT_INFO).exitUrl(ExtensionsKt.createExitUrl(liveEvent)).build());
        FragmentKt.findNavController(this).navigate(ExploreFragmentDirections.Companion.actionExploreToLiveEvent(liveEvent.getRegisterLink(), getStyleManager()));
    }

    public void onSpecialtyFilterSelected() {
        EventsFragment.Companion.setOmnitureScreenType(0);
        navigateToFilterSelection(1);
    }

    public void onDateRangeFilterSelected() {
        EventsFragment.Companion.setOmnitureScreenType(0);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ZonedDateTime now = ZonedDateTime.now();
            Intrinsics.checkNotNullExpressionValue(now, "ZonedDateTime.now()");
            ExtensionsKt.showDateRangePicker(activity, now, new ExploreFragment$onDateRangeFilterSelected$1(this));
        }
    }

    public void onLocationFilterSelected() {
        EventsFragment.Companion.setOmnitureScreenType(0);
        navigateToFilterSelection(0);
    }
}
