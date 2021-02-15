package com.webmd.medscape.live.explorelivevents.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.share.internal.ShareConstants;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import com.webmd.medscape.live.explorelivevents.data.FiltersResponse;
import com.webmd.medscape.live.explorelivevents.data.SearchFilter;
import com.webmd.medscape.live.explorelivevents.databinding.LocationItemBinding;
import com.webmd.medscape.live.explorelivevents.databinding.SpecialtyItemBinding;
import com.webmd.medscape.live.explorelivevents.ui.viewholders.LocationViewHolder;
import com.webmd.medscape.live.explorelivevents.ui.viewholders.SpecialtyViewHolder;
import com.webmd.medscape.live.explorelivevents.util.ExtensionsKt;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\u0010\u0014J\u0018\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0002H\u0002J\u0018\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0002H\u0002J\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0018J\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\n0\u0018J\b\u0010!\u001a\u00020\u0004H\u0016J\u0010\u0010\"\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0016J\u0018\u0010#\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0004H\u0016J\u0018\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0003\u001a\u00020\u0004H\u0016J\u000e\u0010'\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010(\u001a\u00020\u00112\b\u0010)\u001a\u0004\u0018\u00010*J\u000e\u0010+\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010,\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0004R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u0018X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/adapters/FiltersRecyclerViewAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "viewType", "", "filters", "", "Lcom/webmd/medscape/live/explorelivevents/data/SearchFilter;", "selectedLocations", "", "", "selectedSpecialties", "previousSelectedSpecialties", "previousSelectedLocations", "applyCallback", "Lkotlin/Function1;", "", "", "unselectAllCallback", "Lkotlin/Function0;", "(ILjava/util/List;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "chosenFilters", "chosenLocations", "locations", "", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "bindLocation", "position", "holder", "bindSpecialty", "getAllSelectedFilters", "getAllSelectedLocations", "getItemCount", "getItemViewType", "onBindViewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "selectAll", "setData", "data", "Lcom/webmd/medscape/live/explorelivevents/data/FiltersResponse;", "setStyle", "unselectAll", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FiltersRecyclerViewAdapter.kt */
public final class FiltersRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int VIEW_LOCATIONS = 0;
    public static final int VIEW_SPECIALTIES = 1;
    /* access modifiers changed from: private */
    public final Function1<Boolean, Unit> applyCallback;
    /* access modifiers changed from: private */
    public final Set<SearchFilter> chosenFilters;
    /* access modifiers changed from: private */
    public final Set<String> chosenLocations;
    /* access modifiers changed from: private */
    public final List<SearchFilter> filters;
    /* access modifiers changed from: private */
    public List<String> locations;
    /* access modifiers changed from: private */
    public final Set<String> previousSelectedLocations;
    /* access modifiers changed from: private */
    public final Set<SearchFilter> previousSelectedSpecialties;
    /* access modifiers changed from: private */
    public final Set<String> selectedLocations;
    /* access modifiers changed from: private */
    public final Set<SearchFilter> selectedSpecialties;
    private StyleManager styleManager;
    /* access modifiers changed from: private */
    public final Function0<Unit> unselectAllCallback;
    private final int viewType;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FiltersRecyclerViewAdapter(int i, List list, Set set, Set set2, Set set3, Set set4, Function1 function1, Function0 function0, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? new ArrayList() : list, (i2 & 4) != 0 ? new LinkedHashSet() : set, (i2 & 8) != 0 ? new LinkedHashSet() : set2, (i2 & 16) != 0 ? new LinkedHashSet() : set3, (i2 & 32) != 0 ? new LinkedHashSet() : set4, function1, function0);
    }

    public FiltersRecyclerViewAdapter(int i, List<SearchFilter> list, Set<String> set, Set<SearchFilter> set2, Set<SearchFilter> set3, Set<String> set4, Function1<? super Boolean, Unit> function1, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(list, ShareConstants.WEB_DIALOG_PARAM_FILTERS);
        Intrinsics.checkNotNullParameter(set, "selectedLocations");
        Intrinsics.checkNotNullParameter(set2, "selectedSpecialties");
        Intrinsics.checkNotNullParameter(set3, "previousSelectedSpecialties");
        Intrinsics.checkNotNullParameter(set4, "previousSelectedLocations");
        Intrinsics.checkNotNullParameter(function1, "applyCallback");
        Intrinsics.checkNotNullParameter(function0, "unselectAllCallback");
        this.viewType = i;
        this.filters = list;
        this.selectedLocations = set;
        this.selectedSpecialties = set2;
        this.previousSelectedSpecialties = set3;
        this.previousSelectedLocations = set4;
        this.applyCallback = function1;
        this.unselectAllCallback = function0;
        this.locations = CollectionsKt.emptyList();
        this.chosenLocations = new LinkedHashSet();
        this.chosenFilters = new LinkedHashSet();
        this.styleManager = new StyleManager(false, 1, (DefaultConstructorMarker) null);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 0) {
            LocationItemBinding inflate = LocationItemBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "LocationItemBinding.infl…tInflater, parent, false)");
            return new LocationViewHolder(inflate);
        } else if (i == 1) {
            SpecialtyItemBinding inflate2 = SpecialtyItemBinding.inflate(from, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "SpecialtyItemBinding.inf…tInflater, parent, false)");
            return new SpecialtyViewHolder(inflate2);
        } else {
            throw new Exception("Invalid view type");
        }
    }

    public int getItemCount() {
        if (this.viewType == 0) {
            return this.locations.size();
        }
        return this.filters.size();
    }

    public final void setData(FiltersResponse filtersResponse) {
        if (filtersResponse != null) {
            Set linkedHashSet = new LinkedHashSet();
            linkedHashSet.addAll(filtersResponse.getSearchFilters());
            this.filters.addAll(CollectionsKt.toList(linkedHashSet));
            this.locations = filtersResponse.getAllLocations();
        }
    }

    public final void setStyle(StyleManager styleManager2) {
        Intrinsics.checkNotNullParameter(styleManager2, "styleManager");
        this.styleManager = styleManager2;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        int itemViewType = getItemViewType(i);
        if (itemViewType == 0) {
            bindLocation(i, viewHolder);
        } else if (itemViewType == 1) {
            bindSpecialty(i, viewHolder);
        }
    }

    private final void bindSpecialty(int i, RecyclerView.ViewHolder viewHolder) {
        SearchFilter searchFilter = this.filters.get(i);
        if (viewHolder != null) {
            SpecialtyViewHolder specialtyViewHolder = (SpecialtyViewHolder) viewHolder;
            specialtyViewHolder.getBinding().setSearchFilter(this.filters.get(i));
            specialtyViewHolder.getBinding().setStyleManager(this.styleManager);
            CheckBox checkBox = specialtyViewHolder.getBinding().cbIsChecked;
            Intrinsics.checkNotNullExpressionValue(checkBox, "specialtyHolder.binding.cbIsChecked");
            checkBox.setChecked(this.chosenFilters.contains(searchFilter) || this.selectedSpecialties.contains(searchFilter));
            specialtyViewHolder.getBinding().getRoot().setOnClickListener(new FiltersRecyclerViewAdapter$bindSpecialty$$inlined$let$lambda$1(searchFilter, this, i, viewHolder));
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.ui.viewholders.SpecialtyViewHolder");
    }

    private final void bindLocation(int i, RecyclerView.ViewHolder viewHolder) {
        String str = this.locations.get(i);
        if (viewHolder != null) {
            LocationViewHolder locationViewHolder = (LocationViewHolder) viewHolder;
            locationViewHolder.getBinding().setLocation(str);
            locationViewHolder.getBinding().setStyleManager(this.styleManager);
            CheckBox checkBox = locationViewHolder.getBinding().cbIsChecked;
            Intrinsics.checkNotNullExpressionValue(checkBox, "locationHolder.binding.cbIsChecked");
            checkBox.setChecked(this.chosenLocations.contains(this.locations.get(i)) || this.selectedLocations.contains(this.locations.get(i)));
            locationViewHolder.getBinding().getRoot().setOnClickListener(new FiltersRecyclerViewAdapter$bindLocation$$inlined$with$lambda$1(str, this, viewHolder, str, i));
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.ui.viewholders.LocationViewHolder");
    }

    public int getItemViewType(int i) {
        return this.viewType != 0 ? 1 : 0;
    }

    public final List<SearchFilter> getAllSelectedFilters() {
        return ExtensionsKt.getSelectedItems(this.chosenFilters, this.selectedSpecialties);
    }

    public final List<String> getAllSelectedLocations() {
        return ExtensionsKt.getSelectedItems(this.chosenLocations, this.selectedLocations);
    }

    public final void selectAll(int i) {
        if (i == 0) {
            this.selectedLocations.addAll(this.locations);
            Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new FiltersRecyclerViewAdapter$selectAll$1(this, (Continuation) null), 2, (Object) null);
            return;
        }
        this.selectedSpecialties.addAll(this.filters);
        Job unused2 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new FiltersRecyclerViewAdapter$selectAll$2(this, (Continuation) null), 2, (Object) null);
    }

    public final void unselectAll(int i) {
        if (i == 0) {
            this.selectedLocations.clear();
            if (!this.chosenLocations.isEmpty()) {
                this.applyCallback.invoke(true);
            } else if (!this.selectedLocations.isEmpty()) {
                this.applyCallback.invoke(true);
            } else {
                this.applyCallback.invoke(false);
            }
        } else {
            this.selectedSpecialties.clear();
            if (!this.chosenFilters.isEmpty()) {
                this.applyCallback.invoke(true);
            } else if (!this.selectedSpecialties.isEmpty()) {
                this.applyCallback.invoke(true);
            } else {
                this.applyCallback.invoke(false);
            }
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), (CoroutineStart) null, new FiltersRecyclerViewAdapter$unselectAll$1(this, (Continuation) null), 2, (Object) null);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/adapters/FiltersRecyclerViewAdapter$Companion;", "", "()V", "VIEW_LOCATIONS", "", "VIEW_SPECIALTIES", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FiltersRecyclerViewAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
