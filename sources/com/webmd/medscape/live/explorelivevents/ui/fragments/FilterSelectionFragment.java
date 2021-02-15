package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.customview.widget.Openable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavArgs;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.FragmentKt;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.ToolbarKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.BaseFiltersView;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.medscape.live.explorelivevents.common.OmnitureInfo;
import com.webmd.medscape.live.explorelivevents.common.OnLocationSelectedListener;
import com.webmd.medscape.live.explorelivevents.common.OnSpecialtySelectedListener;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.SearchFilter;
import com.webmd.medscape.live.explorelivevents.data.Status;
import com.webmd.medscape.live.explorelivevents.databinding.FragmentFilterSelectionBinding;
import com.webmd.medscape.live.explorelivevents.factories.ExploreViewModelFactory;
import com.webmd.medscape.live.explorelivevents.persistence.SharedPreferencesManager;
import com.webmd.medscape.live.explorelivevents.ui.adapters.FiltersRecyclerViewAdapter;
import com.webmd.medscape.live.explorelivevents.ui.decorators.MiddleDividerItemDecoration;
import com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import com.webmd.medscape.live.explorelivevents.util.GsonUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010'\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010(H\u0016J\u001a\u0010)\u001a\u00020\n2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\b\u0010.\u001a\u00020\nH\u0016J\b\u0010/\u001a\u00020\nH\u0016J6\u00100\u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010 \u001a\u0004\u0018\u00010\u00122\b\u0010\u0019\u001a\u0004\u0018\u00010\u00122\u0006\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u00020\nH\u0002J\b\u00104\u001a\u00020\nH\u0016J&\u00105\u001a\u0004\u0018\u0001062\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\b\u00107\u001a\u0004\u0018\u000108H\u0016J\u0010\u00109\u001a\u00020\n2\u0006\u0010:\u001a\u00020\u0012H\u0016J\u0012\u0010;\u001a\u00020\t2\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J\u0010\u0010>\u001a\u00020\t2\u0006\u0010<\u001a\u00020=H\u0016J\b\u0010?\u001a\u00020\nH\u0016J\u0010\u0010@\u001a\u00020\n2\u0006\u0010A\u001a\u00020\u0012H\u0016J\u001a\u0010B\u001a\u00020\n2\u0006\u0010C\u001a\u00020=2\b\b\u0001\u0010D\u001a\u00020&H\u0016J\b\u0010E\u001a\u00020\nH\u0002J\b\u0010F\u001a\u00020\nH\u0002J\u0010\u0010G\u001a\u00020\n2\u0006\u0010H\u001a\u00020\u0012H\u0002J\"\u0010I\u001a\u00020\n2\u0006\u0010J\u001a\u00020\u00122\b\u0010K\u001a\u0004\u0018\u00010\u00122\u0006\u0010L\u001a\u00020\tH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0$X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/FilterSelectionFragment;", "Lcom/webmd/medscape/live/explorelivevents/ui/fragments/BaseFragment;", "Lcom/webmd/medscape/live/explorelivevents/common/BaseFiltersView;", "Lcom/webmd/medscape/live/explorelivevents/common/OnLocationSelectedListener;", "Lcom/webmd/medscape/live/explorelivevents/common/OnSpecialtySelectedListener;", "Landroidx/appcompat/widget/Toolbar$OnMenuItemClickListener;", "()V", "applyCallback", "Lkotlin/Function1;", "", "", "args", "Lcom/webmd/medscape/live/explorelivevents/ui/fragments/FilterSelectionFragmentArgs;", "getArgs", "()Lcom/webmd/medscape/live/explorelivevents/ui/fragments/FilterSelectionFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "baseUrl", "", "binding", "Lcom/webmd/medscape/live/explorelivevents/databinding/FragmentFilterSelectionBinding;", "getBinding", "()Lcom/webmd/medscape/live/explorelivevents/databinding/FragmentFilterSelectionBinding;", "setBinding", "(Lcom/webmd/medscape/live/explorelivevents/databinding/FragmentFilterSelectionBinding;)V", "endDate", "filters", "", "Lcom/webmd/medscape/live/explorelivevents/data/SearchFilter;", "filtersAdapter", "Lcom/webmd/medscape/live/explorelivevents/ui/adapters/FiltersRecyclerViewAdapter;", "isComingFromExplore", "startDate", "title", "unselect", "unselectAllCallback", "Lkotlin/Function0;", "viewType", "", "extractArguments", "Landroidx/navigation/NavArgs;", "initBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "initDependencies", "initUI", "navigateToEvents", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "navigateUpToEvents", "observeViewModel", "onCreateView", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onLocationSelected", "location", "onMenuItemClick", "item", "Landroid/view/MenuItem;", "onOptionsItemSelected", "onResume", "onSpecialtySelected", "specialty", "renderMenuItem", "menuItem", "titleRes", "saveSelectedLocations", "saveSelectedSpecialties", "saveSpecialtyBySpecialtyKey", "specialtyKey", "sendFilterActionCall", "filter", "selection", "isTapped", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FilterSelectionFragment.kt */
public final class FilterSelectionFragment extends BaseFragment implements BaseFiltersView, OnLocationSelectedListener, OnSpecialtySelectedListener, Toolbar.OnMenuItemClickListener {
    private HashMap _$_findViewCache;
    private final Function1<Boolean, Unit> applyCallback = new FilterSelectionFragment$applyCallback$1(this);
    private final NavArgsLazy args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(FilterSelectionFragmentArgs.class), new FilterSelectionFragment$$special$$inlined$navArgs$1(this));
    private String baseUrl;
    public FragmentFilterSelectionBinding binding;
    private String endDate;
    private List<SearchFilter> filters = new ArrayList();
    /* access modifiers changed from: private */
    public FiltersRecyclerViewAdapter filtersAdapter;
    private boolean isComingFromExplore;
    private String startDate;
    private String title;
    /* access modifiers changed from: private */
    public boolean unselect;
    private final Function0<Unit> unselectAllCallback = new FilterSelectionFragment$unselectAllCallback$1(this);
    private int viewType;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Status.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Status.SUCCESS.ordinal()] = 1;
        }
    }

    private final FilterSelectionFragmentArgs getArgs() {
        return (FilterSelectionFragmentArgs) this.args$delegate.getValue();
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

    public static final /* synthetic */ FiltersRecyclerViewAdapter access$getFiltersAdapter$p(FilterSelectionFragment filterSelectionFragment) {
        FiltersRecyclerViewAdapter filtersRecyclerViewAdapter = filterSelectionFragment.filtersAdapter;
        if (filtersRecyclerViewAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filtersAdapter");
        }
        return filtersRecyclerViewAdapter;
    }

    public final FragmentFilterSelectionBinding getBinding() {
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding = this.binding;
        if (fragmentFilterSelectionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return fragmentFilterSelectionBinding;
    }

    public final void setBinding(FragmentFilterSelectionBinding fragmentFilterSelectionBinding) {
        Intrinsics.checkNotNullParameter(fragmentFilterSelectionBinding, "<set-?>");
        this.binding = fragmentFilterSelectionBinding;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        initBinding(layoutInflater, viewGroup);
        extractArguments(getArgs());
        NavGraph graph = FragmentKt.findNavController(this).getGraph();
        Intrinsics.checkNotNullExpressionValue(graph, "findNavController().graph");
        AppBarConfiguration build = new AppBarConfiguration.Builder(graph).setOpenableLayout((Openable) null).setFallbackOnNavigateUpListener(new FilterSelectionFragment$inlined$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(FilterSelectionFragment$onCreateView$$inlined$AppBarConfiguration$1.INSTANCE)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding = this.binding;
        if (fragmentFilterSelectionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar = fragmentFilterSelectionBinding.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar, "binding.toolbar");
        ToolbarKt.setupWithNavController(toolbar, FragmentKt.findNavController(this), build);
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding2 = this.binding;
        if (fragmentFilterSelectionBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar2 = fragmentFilterSelectionBinding2.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar2, "binding.toolbar");
        setToolbar(toolbar2, build);
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding3 = this.binding;
        if (fragmentFilterSelectionBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar3 = fragmentFilterSelectionBinding3.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar3, "binding.toolbar");
        renderToolbar(toolbar3, false);
        setupStatusBar(getStyleManager().getColors().getStatusBarColor());
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding4 = this.binding;
        if (fragmentFilterSelectionBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentFilterSelectionBinding4.toolbar.inflateMenu(R.menu.menu_filters);
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding5 = this.binding;
        if (fragmentFilterSelectionBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentFilterSelectionBinding5.toolbar.setOnMenuItemClickListener(this);
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding6 = this.binding;
        if (fragmentFilterSelectionBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar4 = fragmentFilterSelectionBinding6.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar4, "binding.toolbar");
        toolbar4.getMenu().findItem(R.id.action_done).setIcon(getStyleManager().getIcons().getTickIcon());
        initDependencies();
        initUI();
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding7 = this.binding;
        if (fragmentFilterSelectionBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return fragmentFilterSelectionBinding7.getRoot();
    }

    public void onResume() {
        super.onResume();
        getViewModel().getFilters();
        observeViewModel();
    }

    public void initUI() {
        List<SearchFilter> fromJsonToListOfFilters = ExtensionsKt.fromJsonToListOfFilters(getPreferences().getStringArray("KEY_SELECTED_SPECIALTIES", SetsKt.emptySet()));
        Iterable stringArray = getPreferences().getStringArray("KEY_SELECTED_LOCATIONS", SetsKt.emptySet());
        Iterable iterable = fromJsonToListOfFilters;
        FiltersRecyclerViewAdapter filtersRecyclerViewAdapter = new FiltersRecyclerViewAdapter(this.viewType, (List) null, CollectionsKt.toMutableSet(stringArray), CollectionsKt.toMutableSet(iterable), CollectionsKt.toMutableSet(iterable), CollectionsKt.toMutableSet(stringArray), this.applyCallback, this.unselectAllCallback, 2, (DefaultConstructorMarker) null);
        this.filtersAdapter = filtersRecyclerViewAdapter;
        if (filtersRecyclerViewAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filtersAdapter");
        }
        filtersRecyclerViewAdapter.setStyle(getStyleManager());
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding = this.binding;
        if (fragmentFilterSelectionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar = fragmentFilterSelectionBinding.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar, "binding.toolbar");
        MenuItem findItem = toolbar.getMenu().findItem(R.id.action_select_all);
        Intrinsics.checkNotNullExpressionValue(findItem, "selectAllItem");
        findItem.setVisible(false);
        if (this.viewType == 0) {
            FragmentFilterSelectionBinding fragmentFilterSelectionBinding2 = this.binding;
            if (fragmentFilterSelectionBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            Toolbar toolbar2 = fragmentFilterSelectionBinding2.toolbar;
            Intrinsics.checkNotNullExpressionValue(toolbar2, "binding.toolbar");
            toolbar2.setTitle((CharSequence) getString(R.string.select_location_title));
        } else {
            FragmentFilterSelectionBinding fragmentFilterSelectionBinding3 = this.binding;
            if (fragmentFilterSelectionBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            Toolbar toolbar3 = fragmentFilterSelectionBinding3.toolbar;
            Intrinsics.checkNotNullExpressionValue(toolbar3, "binding.toolbar");
            toolbar3.setTitle((CharSequence) getString(R.string.select_specialty_title));
        }
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding4 = this.binding;
        if (fragmentFilterSelectionBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        RecyclerView recyclerView = fragmentFilterSelectionBinding4.rvItems;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "this");
        FiltersRecyclerViewAdapter filtersRecyclerViewAdapter2 = this.filtersAdapter;
        if (filtersRecyclerViewAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("filtersAdapter");
        }
        recyclerView.setAdapter(filtersRecyclerViewAdapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 1, false));
        Context context = recyclerView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        recyclerView.addItemDecoration(new MiddleDividerItemDecoration(context, 1));
    }

    public void extractArguments(NavArgs navArgs) {
        if (navArgs == null) {
            return;
        }
        if (navArgs != null) {
            FilterSelectionFragmentArgs filterSelectionFragmentArgs = (FilterSelectionFragmentArgs) navArgs;
            this.viewType = filterSelectionFragmentArgs.getViewType();
            this.isComingFromExplore = filterSelectionFragmentArgs.isFromExplore();
            this.baseUrl = filterSelectionFragmentArgs.getBaseUrl();
            this.startDate = filterSelectionFragmentArgs.getStartDate();
            this.endDate = filterSelectionFragmentArgs.getEndDate();
            this.title = filterSelectionFragmentArgs.getTitle();
            StyleManager styleManager = filterSelectionFragmentArgs.getStyleManager();
            if (styleManager == null) {
                styleManager = new StyleManager(false, 1, (DefaultConstructorMarker) null);
            }
            setStyleManager(styleManager);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.ui.fragments.FilterSelectionFragmentArgs");
    }

    public void observeViewModel() {
        getViewModel().observeFilters().observe(getViewLifecycleOwner(), new FilterSelectionFragment$observeViewModel$1(this));
        getViewModel().observeMenuUiState().observe(getViewLifecycleOwner(), new FilterSelectionFragment$observeViewModel$2(this));
    }

    public void initDependencies() {
        SharedPreferencesManager.Companion companion = SharedPreferencesManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        setPreferences(companion.getInstance(requireContext));
        Context requireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
        setViewModelFactory(new ExploreViewModelFactory(requireContext2, getPreferences()));
        ViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) getViewModelFactory()).get(ExploreEventsViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProvider(this, …ntsViewModel::class.java]");
        setViewModel((ExploreEventsViewModel) viewModel);
    }

    public void initBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        FragmentFilterSelectionBinding inflate = FragmentFilterSelectionBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "FragmentFilterSelectionB…flater, container, false)");
        this.binding = inflate;
    }

    private final void saveSpecialtyBySpecialtyKey(String str) {
        SearchFilter searchFilter = this.filters.get(0);
        for (SearchFilter next : this.filters) {
            if (Intrinsics.areEqual((Object) next.getSpecialtyKey(), (Object) str)) {
                searchFilter = next;
            }
        }
        getPreferences().saveString("KEY_PREFS_SPECIALTY", GsonUtils.INSTANCE.serialize(SearchFilter.class, searchFilter));
    }

    public void onLocationSelected(String str) {
        Intrinsics.checkNotNullParameter(str, "location");
        getPreferences().saveString("KEY_PREFS_LOCATION", str);
        BaseFiltersView.DefaultImpls.sendFilterActionCall$default(this, "location", str, false, 4, (Object) null);
        if (this.isComingFromExplore) {
            FragmentKt.findNavController(this).navigateUp();
        } else {
            navigateUpToEvents();
        }
    }

    public void onSpecialtySelected(String str) {
        Intrinsics.checkNotNullParameter(str, OmnitureConstants.OMNITURE_FILTER_SPECIALTY);
        BaseFiltersView.DefaultImpls.sendFilterActionCall$default(this, OmnitureConstants.OMNITURE_FILTER_SPECIALTY, str, false, 4, (Object) null);
        saveSpecialtyBySpecialtyKey(str);
        navigateUpToEvents();
    }

    private final void navigateUpToEvents() {
        String str = this.title;
        String str2 = this.baseUrl;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseUrl");
        }
        navigateToEvents(str, str2, this.startDate, this.endDate, getStyleManager());
    }

    private final void navigateToEvents(String str, String str2, String str3, String str4, StyleManager styleManager) {
        FragmentKt.findNavController(this).navigate(FilterSelectionFragmentDirections.Companion.actionSpecialtySelectionToResults(str2, str, styleManager, str3, str4));
    }

    public void sendFilterActionCall(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, OmnitureConstants.OMNITURE_MODULE_FILTER);
        if (str2 != null) {
            sendOmnitureActionCallToApp(new OmnitureInfo.Builder((List) null, (String) null, (String) null, (String) null, (String) null, (String) null, 63, (DefaultConstructorMarker) null).pageNames(CollectionsKt.mutableListOf(OmnitureConstants.PAGE_NAME_EXPLORE, OmnitureConstants.PAGE_NAME_RESULTS)).channel(OmnitureConstants.CHANNEL).module(str).link(str2).build());
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        FragmentKt.findNavController(this).navigateUp();
        return true;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        int i = R.id.action_done;
        boolean z = false;
        if (valueOf != null && valueOf.intValue() == i) {
            int i2 = this.viewType;
            if (i2 == 0) {
                saveSelectedLocations();
                String str = this.title;
                String str2 = this.baseUrl;
                if (str2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("baseUrl");
                }
                navigateToEvents(str, str2, this.startDate, this.endDate, getStyleManager());
            } else if (i2 == 1) {
                saveSelectedSpecialties();
                String str3 = this.title;
                String str4 = this.baseUrl;
                if (str4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("baseUrl");
                }
                navigateToEvents(str3, str4, this.startDate, this.endDate, getStyleManager());
            }
            return true;
        }
        int i3 = R.id.action_select_all;
        if (valueOf == null || valueOf.intValue() != i3) {
            return false;
        }
        if (!this.unselect) {
            FiltersRecyclerViewAdapter filtersRecyclerViewAdapter = this.filtersAdapter;
            if (filtersRecyclerViewAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("filtersAdapter");
            }
            filtersRecyclerViewAdapter.selectAll(this.viewType);
            getViewModel().setMenuUiState(getViewModel().getMenuUiState().copy(true, true, true));
            z = true;
        } else {
            FiltersRecyclerViewAdapter filtersRecyclerViewAdapter2 = this.filtersAdapter;
            if (filtersRecyclerViewAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("filtersAdapter");
            }
            filtersRecyclerViewAdapter2.unselectAll(this.viewType);
            getViewModel().setMenuUiState(getViewModel().getMenuUiState().copy(false, true, false));
        }
        this.unselect = z;
        return true;
    }

    public void renderMenuItem(MenuItem menuItem, int i) {
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        menuItem.setTitle(getString(i));
    }

    private final void saveSelectedSpecialties() {
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding = this.binding;
        if (fragmentFilterSelectionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        RecyclerView recyclerView = fragmentFilterSelectionBinding.rvItems;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvItems");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            List<SearchFilter> allSelectedFilters = ((FiltersRecyclerViewAdapter) adapter).getAllSelectedFilters();
            Set linkedHashSet = new LinkedHashSet();
            Iterable<String> jsonArrayString = ExtensionsKt.toJsonArrayString(allSelectedFilters);
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(jsonArrayString, 10));
            for (String add : jsonArrayString) {
                arrayList.add(Boolean.valueOf(linkedHashSet.add(add)));
            }
            List list = (List) arrayList;
            getPreferences().saveStringSet("KEY_SELECTED_SPECIALTIES", linkedHashSet);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.ui.adapters.FiltersRecyclerViewAdapter");
    }

    private final void saveSelectedLocations() {
        FragmentFilterSelectionBinding fragmentFilterSelectionBinding = this.binding;
        if (fragmentFilterSelectionBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        RecyclerView recyclerView = fragmentFilterSelectionBinding.rvItems;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.rvItems");
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null) {
            List<String> allSelectedLocations = ((FiltersRecyclerViewAdapter) adapter).getAllSelectedLocations();
            Set linkedHashSet = new LinkedHashSet();
            linkedHashSet.addAll(allSelectedLocations);
            getPreferences().saveStringSet("KEY_SELECTED_LOCATIONS", linkedHashSet);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.ui.adapters.FiltersRecyclerViewAdapter");
    }
}
