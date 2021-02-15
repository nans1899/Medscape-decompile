package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.customview.widget.Openable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavArgs;
import androidx.navigation.NavArgsLazy;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.FragmentKt;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.ToolbarKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.materialdialogs.DialogBehavior;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.list.DialogSingleChoiceExtKt;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.BaseFiltersView;
import com.webmd.medscape.live.explorelivevents.common.IOmnitureListener;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.medscape.live.explorelivevents.common.OmnitureInfo;
import com.webmd.medscape.live.explorelivevents.common.OnDropdownFilterSelectedListener;
import com.webmd.medscape.live.explorelivevents.common.OnFilterResetListener;
import com.webmd.medscape.live.explorelivevents.common.OnLiveEventSelectedListener;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.DropdownFilterButton;
import com.webmd.medscape.live.explorelivevents.data.Error;
import com.webmd.medscape.live.explorelivevents.data.LiveEvent;
import com.webmd.medscape.live.explorelivevents.data.Resource;
import com.webmd.medscape.live.explorelivevents.data.SearchFilter;
import com.webmd.medscape.live.explorelivevents.data.Status;
import com.webmd.medscape.live.explorelivevents.data.UiState;
import com.webmd.medscape.live.explorelivevents.databinding.FragmentEventsBinding;
import com.webmd.medscape.live.explorelivevents.databinding.NotFoundErrorViewBinding;
import com.webmd.medscape.live.explorelivevents.persistence.SharedPreferencesManager;
import com.webmd.medscape.live.explorelivevents.ui.adapters.EventsRecyclerViewAdapter;
import com.webmd.medscape.live.explorelivevents.ui.decorators.MiddleDividerItemDecoration;
import com.webmd.medscape.live.explorelivevents.ui.fragments.EventsFragmentDirections;
import com.webmd.medscape.live.explorelivevents.ui.viewmodels.ExploreEventsViewModel;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import com.webmd.medscape.live.explorelivevents.util.GsonUtils;
import com.webmd.medscape.live.explorelivevents.util.OmnitureUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.threeten.bp.ZonedDateTime;
import retrofit2.Retrofit;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 Y2\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0001YB\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010%\u001a\u00020&2\b\u0010\n\u001a\u0004\u0018\u00010'H\u0016J(\u0010(\u001a\u00020&2\u0016\u0010)\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010+\u0012\u0006\u0012\u0004\u0018\u00010+0*2\u0006\u0010,\u001a\u00020-H\u0002J\b\u0010.\u001a\u00020\bH\u0002J\u0012\u0010/\u001a\u00020&2\b\u00100\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u00101\u001a\u00020&2\b\u00100\u001a\u0004\u0018\u00010\u0011H\u0016J \u00102\u001a\u00020&\"\b\b\u0000\u00103*\u0002042\f\u00105\u001a\b\u0012\u0004\u0012\u0002H306H\u0016J\u001a\u00107\u001a\u00020&2\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;H\u0016J\b\u0010<\u001a\u00020&H\u0002J\b\u0010=\u001a\u00020&H\u0016J\b\u0010>\u001a\u00020&H\u0002J\u0010\u0010?\u001a\u00020&2\u0006\u0010@\u001a\u00020\u001cH\u0016J\b\u0010A\u001a\u00020&H\u0002J\b\u0010B\u001a\u00020&H\u0016J\u0010\u0010C\u001a\u00020&2\u0006\u0010D\u001a\u00020EH\u0016J\u0010\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020HH\u0016J&\u0010I\u001a\u0004\u0018\u00010J2\u0006\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;2\b\u0010K\u001a\u0004\u0018\u00010LH\u0016J\b\u0010M\u001a\u00020&H\u0016J\b\u0010N\u001a\u00020&H\u0016J\u0010\u0010O\u001a\u00020&2\u0006\u0010P\u001a\u00020\u001cH\u0016J\b\u0010Q\u001a\u00020&H\u0016J\b\u0010R\u001a\u00020&H\u0016J\b\u0010S\u001a\u00020&H\u0016J\"\u0010T\u001a\u00020&2\u0006\u0010U\u001a\u00020\u00112\b\u0010V\u001a\u0004\u0018\u00010\u00112\u0006\u0010W\u001a\u00020\bH\u0016J\u0012\u0010X\u001a\u00020&2\b\u0010$\u001a\u0004\u0018\u00010\u0011H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u001aX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\u00020\u001c8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00110\u001aX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X.¢\u0006\u0002\n\u0000R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00110\u001aX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006Z"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragment;", "Lcom/webmd/medscape/live/explorelivevents/ui/fragments/BaseFragment;", "Lcom/webmd/medscape/live/explorelivevents/common/BaseFiltersView;", "Lcom/webmd/medscape/live/explorelivevents/common/OnLiveEventSelectedListener;", "Lcom/webmd/medscape/live/explorelivevents/common/OnDropdownFilterSelectedListener;", "Lcom/webmd/medscape/live/explorelivevents/common/OnFilterResetListener;", "()V", "areAllLocationsSelected", "", "areAllSpecialtiesSelected", "args", "Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragmentArgs;", "getArgs", "()Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "baseUrl", "", "binding", "Lcom/webmd/medscape/live/explorelivevents/databinding/FragmentEventsBinding;", "dateTitle", "endDate", "eventsAdapter", "Lcom/webmd/medscape/live/explorelivevents/ui/adapters/EventsRecyclerViewAdapter;", "goingToLocation", "itemsList", "", "layoutResId", "", "getLayoutResId", "()I", "locationsToSearch", "retrofit", "Lretrofit2/Retrofit;", "specialtiesToSearch", "startDate", "title", "extractArguments", "", "Landroidx/navigation/NavArgs;", "extractDateFilterUserSelectionAndLoadData", "dateRange", "Lkotlin/Pair;", "Lorg/threeten/bp/ZonedDateTime;", "text", "", "extractLocationAndSpecialtyFilters", "handleErrorResponse", "message", "handleLoadingResponse", "handleSuccessResponse", "T", "", "resource", "Lcom/webmd/medscape/live/explorelivevents/data/Resource;", "initBinding", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "initRecyclerView", "initUI", "loadFilters", "navigateToFilterSelection", "viewType", "observeForEvents", "observeViewModel", "onAttach", "context", "Landroid/content/Context;", "onClick", "event", "Lcom/webmd/medscape/live/explorelivevents/data/LiveEvent;", "onCreateView", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "onDateSelected", "onDestroyView", "onFilterReset", "type", "onLocationSelected", "onResume", "onSpecialtySelected", "sendFilterActionCall", "filter", "selection", "isTapped", "updateDateFilter", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EventsFragment.kt */
public final class EventsFragment extends BaseFragment implements BaseFiltersView, OnLiveEventSelectedListener, OnDropdownFilterSelectedListener, OnFilterResetListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String TAG = EventsFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public static final List<DropdownFilterButton> filters = new ArrayList();
    /* access modifiers changed from: private */
    public static int omnitureScreenType = 1;
    private HashMap _$_findViewCache;
    private boolean areAllLocationsSelected;
    private boolean areAllSpecialtiesSelected;
    private final NavArgsLazy args$delegate = new NavArgsLazy(Reflection.getOrCreateKotlinClass(EventsFragmentArgs.class), new EventsFragment$$special$$inlined$navArgs$1(this));
    private String baseUrl;
    /* access modifiers changed from: private */
    public FragmentEventsBinding binding;
    /* access modifiers changed from: private */
    public String dateTitle;
    /* access modifiers changed from: private */
    public String endDate;
    /* access modifiers changed from: private */
    public final EventsRecyclerViewAdapter eventsAdapter = new EventsRecyclerViewAdapter(this, (StyleManager) null, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public boolean goingToLocation;
    private final List<String> itemsList = new ArrayList();
    private final int layoutResId = R.layout.fragment_events;
    private List<String> locationsToSearch = new ArrayList();
    private Retrofit retrofit;
    private List<String> specialtiesToSearch = new ArrayList();
    /* access modifiers changed from: private */
    public String startDate;
    /* access modifiers changed from: private */
    public String title;

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

    private final EventsFragmentArgs getArgs() {
        return (EventsFragmentArgs) this.args$delegate.getValue();
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

    public static final /* synthetic */ FragmentEventsBinding access$getBinding$p(EventsFragment eventsFragment) {
        FragmentEventsBinding fragmentEventsBinding = eventsFragment.binding;
        if (fragmentEventsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return fragmentEventsBinding;
    }

    public final int getLayoutResId() {
        return this.layoutResId;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        initBinding(layoutInflater, viewGroup);
        initDependencies();
        observeViewModel();
        initUI();
        FragmentEventsBinding fragmentEventsBinding = this.binding;
        if (fragmentEventsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        return fragmentEventsBinding.getRoot();
    }

    public void initUI() {
        extractArguments(getArgs());
        this.areAllLocationsSelected = getPreferences().getBoolean("KEY_ALL_LOCATIONS_SELECTED", false);
        this.areAllSpecialtiesSelected = getPreferences().getBoolean("KEY_ALL_SPECIALTIES_SELECTED", false);
        FragmentEventsBinding fragmentEventsBinding = this.binding;
        if (fragmentEventsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentEventsBinding.setViewModel(getViewModel());
        FragmentEventsBinding fragmentEventsBinding2 = this.binding;
        if (fragmentEventsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        NotFoundErrorViewBinding notFoundErrorViewBinding = fragmentEventsBinding2.lytError;
        Intrinsics.checkNotNullExpressionValue(notFoundErrorViewBinding, "binding.lytError");
        notFoundErrorViewBinding.setViewModel(getViewModel());
        NavGraph graph = FragmentKt.findNavController(this).getGraph();
        Intrinsics.checkNotNullExpressionValue(graph, "findNavController().graph");
        AppBarConfiguration build = new AppBarConfiguration.Builder(graph).setOpenableLayout((Openable) null).setFallbackOnNavigateUpListener(new EventsFragment$inlined$sam$i$androidx_navigation_ui_AppBarConfiguration_OnNavigateUpListener$0(EventsFragment$initUI$$inlined$AppBarConfiguration$1.INSTANCE)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        FragmentEventsBinding fragmentEventsBinding3 = this.binding;
        if (fragmentEventsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar = fragmentEventsBinding3.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar, "binding.toolbar");
        ToolbarKt.setupWithNavController(toolbar, FragmentKt.findNavController(this), build);
        FragmentEventsBinding fragmentEventsBinding4 = this.binding;
        if (fragmentEventsBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar2 = fragmentEventsBinding4.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar2, "binding.toolbar");
        setToolbar(toolbar2, build);
        FragmentEventsBinding fragmentEventsBinding5 = this.binding;
        if (fragmentEventsBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar3 = fragmentEventsBinding5.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar3, "binding.toolbar");
        renderToolbar(toolbar3, false);
        FragmentEventsBinding fragmentEventsBinding6 = this.binding;
        if (fragmentEventsBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        Toolbar toolbar4 = fragmentEventsBinding6.toolbar;
        Intrinsics.checkNotNullExpressionValue(toolbar4, "binding.toolbar");
        toolbar4.setTitle((CharSequence) getString(R.string.upcoming_events_title));
        FragmentEventsBinding fragmentEventsBinding7 = this.binding;
        if (fragmentEventsBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentEventsBinding7.toolbar.setNavigationOnClickListener(new EventsFragment$initUI$1(this));
        setupStatusBar(getStyleManager().getColors().getStatusBarColor());
        SharedPreferencesManager.Companion companion = SharedPreferencesManager.Companion;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        setPreferences(companion.getInstance(requireContext));
        String[] stringArray = getResources().getStringArray(R.array.quick_filters);
        Intrinsics.checkNotNullExpressionValue(stringArray, "resources.getStringArray(R.array.quick_filters)");
        List list = ArraysKt.toList((T[]) stringArray);
        this.itemsList.clear();
        this.itemsList.addAll(list);
        List<String> list2 = this.itemsList;
        String string = getString(R.string.specific_filter_date);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.specific_filter_date)");
        list2.add(string);
        initRecyclerView();
        String str = this.baseUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseUrl");
        }
        this.retrofit = getRetrofit(str);
        ExploreEventsViewModel viewModel = getViewModel();
        Retrofit retrofit3 = this.retrofit;
        if (retrofit3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("retrofit");
        }
        viewModel.setRetrofit(retrofit3);
        loadFilters();
    }

    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        try {
            setIOmnitureListener((IOmnitureListener) context);
        } catch (ClassCastException unused) {
            throw new ClassCastException(context + " must implement IOmnitureListener");
        }
    }

    public void extractArguments(NavArgs navArgs) {
        if (navArgs == null) {
            return;
        }
        if (navArgs != null) {
            EventsFragmentArgs eventsFragmentArgs = (EventsFragmentArgs) navArgs;
            this.specialtiesToSearch.clear();
            this.locationsToSearch.clear();
            this.baseUrl = eventsFragmentArgs.getBaseUrl();
            this.startDate = eventsFragmentArgs.getStartDate();
            this.endDate = eventsFragmentArgs.getEndDate();
            this.title = eventsFragmentArgs.getTitle();
            StyleManager styleManager = eventsFragmentArgs.getStyleManager();
            if (styleManager == null) {
                styleManager = new StyleManager(false, 1, (DefaultConstructorMarker) null);
            }
            setStyleManager(styleManager);
            FragmentEventsBinding fragmentEventsBinding = this.binding;
            if (fragmentEventsBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            fragmentEventsBinding.filters.setOnDropDownFilterListener(this);
            FragmentEventsBinding fragmentEventsBinding2 = this.binding;
            if (fragmentEventsBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            fragmentEventsBinding2.filters.setDateFilterResetListener(this);
            this.eventsAdapter.setStyle(getStyleManager());
            extractLocationAndSpecialtyFilters();
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.ui.fragments.EventsFragmentArgs");
    }

    private final boolean extractLocationAndSpecialtyFilters() {
        Iterable<String> stringArray = getPreferences().getStringArray("KEY_SELECTED_SPECIALTIES", SetsKt.emptySet());
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(stringArray, 10));
        for (String deserialize : stringArray) {
            SearchFilter searchFilter = (SearchFilter) GsonUtils.INSTANCE.deserialize(SearchFilter.class, deserialize);
            arrayList.add(searchFilter != null ? searchFilter.getSpecialtyKey() : null);
        }
        this.specialtiesToSearch.addAll((List) arrayList);
        return this.locationsToSearch.addAll(getPreferences().getStringArray("KEY_SELECTED_LOCATIONS", SetsKt.emptySet()));
    }

    public void initBinding(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(layoutInflater, this.layoutResId, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "DataBindingUtil.inflate(…tResId, container, false)");
        FragmentEventsBinding fragmentEventsBinding = (FragmentEventsBinding) inflate;
        this.binding = fragmentEventsBinding;
        if (fragmentEventsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentEventsBinding.setLifecycleOwner(this);
    }

    private final void loadFilters() {
        filters.clear();
        String str = this.startDate;
        if (str == null || this.endDate == null) {
            String str2 = this.startDate;
            if (str2 != null) {
                String parseServerDateToReadableFormat = ExtensionsKt.parseServerDateToReadableFormat(str2);
                String str3 = this.title;
                if (str3 == null) {
                    filters.add(0, new DropdownFilterButton(parseServerDateToReadableFormat, true, 0, false, 8, (DefaultConstructorMarker) null));
                } else if (str3 != null) {
                    filters.add(0, new DropdownFilterButton(str3, true, 0, false, 8, (DefaultConstructorMarker) null));
                }
            } else {
                List<DropdownFilterButton> list = filters;
                String string = requireContext().getString(R.string.date_filter_title);
                Intrinsics.checkNotNullExpressionValue(string, "requireContext().getStri…string.date_filter_title)");
                list.add(0, new DropdownFilterButton(string, false, 0, false, 8, (DefaultConstructorMarker) null));
            }
        } else {
            String parseServerDateToReadableFormat2 = ExtensionsKt.parseServerDateToReadableFormat(str);
            String parseServerDateToReadableFormat3 = ExtensionsKt.parseServerDateToReadableFormat(this.endDate);
            String str4 = this.title;
            if (str4 == null) {
                List<DropdownFilterButton> list2 = filters;
                list2.add(0, new DropdownFilterButton(parseServerDateToReadableFormat2 + " - " + parseServerDateToReadableFormat3, true, 0, false, 8, (DefaultConstructorMarker) null));
            } else if (str4 != null) {
                filters.add(0, new DropdownFilterButton(str4, true, 0, false, 8, (DefaultConstructorMarker) null));
            }
        }
        if (!this.locationsToSearch.isEmpty()) {
            List<DropdownFilterButton> list3 = filters;
            List<String> list4 = this.locationsToSearch;
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            list3.add(1, new DropdownFilterButton(ExtensionsKt.toFilterTitle(list4, requireContext, true), true, 1, false, 8, (DefaultConstructorMarker) null));
        } else {
            List<DropdownFilterButton> list5 = filters;
            String string2 = getString(R.string.location_filter_title);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.location_filter_title)");
            list5.add(1, new DropdownFilterButton(string2, false, 1, false, 8, (DefaultConstructorMarker) null));
        }
        if (!this.specialtiesToSearch.isEmpty()) {
            List<DropdownFilterButton> list6 = filters;
            List<String> list7 = this.specialtiesToSearch;
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            list6.add(2, new DropdownFilterButton(ExtensionsKt.toFilterTitle(list7, requireContext2, false), true, 2, false, 8, (DefaultConstructorMarker) null));
        } else {
            List<DropdownFilterButton> list8 = filters;
            String string3 = getString(R.string.specialty_filter_title);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.specialty_filter_title)");
            list8.add(2, new DropdownFilterButton(string3, false, 2, false, 8, (DefaultConstructorMarker) null));
        }
        FragmentEventsBinding fragmentEventsBinding = this.binding;
        if (fragmentEventsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentEventsBinding.filters.setFilters(filters);
    }

    private final void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        linearLayoutManager.setOrientation(1);
        FragmentEventsBinding fragmentEventsBinding = this.binding;
        if (fragmentEventsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        RecyclerView recyclerView = fragmentEventsBinding.rvItems;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "this");
        recyclerView.setAdapter(this.eventsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        Context context = recyclerView.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        recyclerView.addItemDecoration(new MiddleDividerItemDecoration(context, 1));
        this.eventsAdapter.registerAdapterDataObserver(new EventsFragment$initRecyclerView$2(this));
    }

    /* access modifiers changed from: private */
    public final void observeForEvents() {
        getViewModel().loadEvents(this.specialtiesToSearch, this.locationsToSearch, this.startDate, this.endDate);
        sendOmniturePageViewCallToApp(new OmnitureInfo.Builder((List) null, (String) null, (String) null, (String) null, (String) null, (String) null, 63, (DefaultConstructorMarker) null).pageNames(CollectionsKt.mutableListOf(OmnitureConstants.PAGE_NAME_EXPLORE, OmnitureConstants.PAGE_NAME_RESULTS)).channel(OmnitureConstants.CHANNEL).module(OmnitureConstants.OMNITURE_MODULE_FILTER).link("search").userSeg(OmnitureUtil.INSTANCE.getUserSegStringFromFilterSelections(omnitureScreenType, this.specialtiesToSearch, this.locationsToSearch, new Pair(ExtensionsKt.toUserSegFormat(this.startDate), ExtensionsKt.toUserSegFormat(this.endDate)), this.areAllLocationsSelected, this.areAllSpecialtiesSelected)).build());
    }

    public void observeViewModel() {
        getViewModel().observeLiveEvents().observe(getViewLifecycleOwner(), new EventsFragment$observeViewModel$1(this));
    }

    public <T> void handleSuccessResponse(Resource<T> resource) {
        Intrinsics.checkNotNullParameter(resource, ContentParser.RESOURCE);
        T data = resource.getData();
        if (data != null) {
            Set linkedHashSet = new LinkedHashSet();
            linkedHashSet.addAll((List) data);
            this.eventsAdapter.refresh(CollectionsKt.toList(CollectionsKt.toList(linkedHashSet)));
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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragment$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "filters", "", "Lcom/webmd/medscape/live/explorelivevents/data/DropdownFilterButton;", "getFilters", "()Ljava/util/List;", "omnitureScreenType", "", "getOmnitureScreenType", "()I", "setOmnitureScreenType", "(I)V", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EventsFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return EventsFragment.TAG;
        }

        public final List<DropdownFilterButton> getFilters() {
            return EventsFragment.filters;
        }

        public final int getOmnitureScreenType() {
            return EventsFragment.omnitureScreenType;
        }

        public final void setOmnitureScreenType(int i) {
            EventsFragment.omnitureScreenType = i;
        }
    }

    public void onResume() {
        super.onResume();
        observeForEvents();
    }

    /* access modifiers changed from: private */
    public final void updateDateFilter(String str) {
        if (str != null) {
            FragmentEventsBinding fragmentEventsBinding = this.binding;
            if (fragmentEventsBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            }
            fragmentEventsBinding.filters.updateFilters(0, str);
            return;
        }
        FragmentEventsBinding fragmentEventsBinding2 = this.binding;
        if (fragmentEventsBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        }
        fragmentEventsBinding2.filters.disableFilterAtPosition(0);
    }

    public void onClick(LiveEvent liveEvent) {
        Intrinsics.checkNotNullParameter(liveEvent, "event");
        sendOmnitureActionCallToApp(new OmnitureInfo.Builder((List) null, (String) null, (String) null, (String) null, (String) null, (String) null, 63, (DefaultConstructorMarker) null).pageNames(CollectionsKt.mutableListOf(OmnitureConstants.PAGE_NAME_EXPLORE, OmnitureConstants.PAGE_NAME_RESULTS)).channel(OmnitureConstants.CHANNEL).module(OmnitureConstants.OMNITURE_MODULE_EVENT_CLICK).link(OmnitureConstants.OMNITURE_LINK_EVENT_INFO).exitUrl(ExtensionsKt.createExitUrl(liveEvent)).build());
        FragmentKt.findNavController(this).navigate(EventsFragmentDirections.Companion.actionEventsToLiveEvent(liveEvent.getRegisterLink(), getStyleManager()));
    }

    public void onDateSelected() {
        omnitureScreenType = 1;
        sendFilterActionCall(OmnitureConstants.OMNITURE_FILTER_DATE, (String) null, true);
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        MaterialDialog materialDialog = new MaterialDialog(requireContext, (DialogBehavior) null, 2, (DefaultConstructorMarker) null);
        MaterialDialog.title$default(materialDialog, Integer.valueOf(R.string.pick_date_title), (String) null, 2, (Object) null);
        materialDialog.cancelable(false);
        MaterialDialog materialDialog2 = materialDialog;
        DialogSingleChoiceExtKt.listItemsSingleChoice$default(materialDialog2, (Integer) null, this.itemsList, (int[]) null, 0, false, new EventsFragment$onDateSelected$$inlined$show$lambda$1(this), 21, (Object) null);
        MaterialDialog.positiveButton$default(materialDialog2, 17039370, (CharSequence) null, new EventsFragment$onDateSelected$1$2(materialDialog), 2, (Object) null);
        MaterialDialog.negativeButton$default(materialDialog2, 17039360, (CharSequence) null, new EventsFragment$onDateSelected$$inlined$show$lambda$2(materialDialog, this), 2, (Object) null);
        materialDialog.show();
    }

    /* access modifiers changed from: private */
    public final void extractDateFilterUserSelectionAndLoadData(Pair<ZonedDateTime, ZonedDateTime> pair, CharSequence charSequence) {
        this.dateTitle = charSequence.toString();
        Intrinsics.checkNotNullExpressionValue(getString(R.string.custom_date_title, ExtensionsKt.parseServerDateToReadableFormat(pair.getFirst()), ExtensionsKt.parseServerDateToReadableFormat(pair.getSecond())), "getString(\n            R…eadableFormat()\n        )");
        String str = this.dateTitle;
        this.title = str;
        BaseFiltersView.DefaultImpls.sendFilterActionCall$default(this, OmnitureConstants.OMNITURE_FILTER_DATE, str, false, 4, (Object) null);
        updateDateFilter(this.dateTitle);
        this.startDate = ExtensionsKt.toServerDate(pair.getFirst());
        this.endDate = ExtensionsKt.toServerDate(pair.getSecond());
        observeForEvents();
    }

    public void sendFilterActionCall(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, OmnitureConstants.OMNITURE_MODULE_FILTER);
        if (z) {
            sendOmnitureActionCallToApp(new OmnitureInfo.Builder((List) null, (String) null, (String) null, (String) null, (String) null, (String) null, 63, (DefaultConstructorMarker) null).pageNames(CollectionsKt.mutableListOf(OmnitureConstants.PAGE_NAME_EXPLORE, OmnitureConstants.PAGE_NAME_RESULTS)).channel(OmnitureConstants.CHANNEL).module(str).link(OmnitureConstants.PAGE_NAME_BROWSE).build());
        } else if (str2 != null) {
            sendOmnitureActionCallToApp(new OmnitureInfo.Builder((List) null, (String) null, (String) null, (String) null, (String) null, (String) null, 63, (DefaultConstructorMarker) null).pageNames(CollectionsKt.mutableListOf(OmnitureConstants.PAGE_NAME_EXPLORE, OmnitureConstants.PAGE_NAME_RESULTS)).channel(OmnitureConstants.CHANNEL).module(str).link(str2).build());
        }
    }

    public void navigateToFilterSelection(int i) {
        this.goingToLocation = true;
        EventsFragmentDirections.Companion companion = EventsFragmentDirections.Companion;
        String str = this.baseUrl;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("baseUrl");
        }
        FragmentKt.findNavController(this).navigate(companion.actionEventsToFilterSelection(i, false, str, this.title, getStyleManager(), this.startDate, this.endDate));
    }

    public void onLocationSelected() {
        omnitureScreenType = 1;
        sendFilterActionCall("location", (String) null, true);
        navigateToFilterSelection(0);
    }

    public void onSpecialtySelected() {
        omnitureScreenType = 1;
        sendFilterActionCall(OmnitureConstants.OMNITURE_FILTER_SPECIALTY, (String) null, true);
        navigateToFilterSelection(1);
    }

    public void onFilterReset(int i) {
        observeForEvents();
    }

    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "Destroying view");
        _$_clearFindViewByIdCache();
    }
}
