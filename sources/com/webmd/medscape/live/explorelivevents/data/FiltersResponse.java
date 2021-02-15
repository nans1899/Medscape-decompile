package com.webmd.medscape.live.explorelivevents.data;

import com.google.gson.annotations.Expose;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J)\u0010\r\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0006HÖ\u0001R\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\u0014"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/FiltersResponse;", "", "searchFilters", "", "Lcom/webmd/medscape/live/explorelivevents/data/SearchFilter;", "allLocations", "", "(Ljava/util/List;Ljava/util/List;)V", "getAllLocations", "()Ljava/util/List;", "getSearchFilters", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FiltersResponse.kt */
public final class FiltersResponse {
    @Expose
    private final List<String> allLocations;
    @Expose
    private final List<SearchFilter> searchFilters;

    public static /* synthetic */ FiltersResponse copy$default(FiltersResponse filtersResponse, List<SearchFilter> list, List<String> list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = filtersResponse.searchFilters;
        }
        if ((i & 2) != 0) {
            list2 = filtersResponse.allLocations;
        }
        return filtersResponse.copy(list, list2);
    }

    public final List<SearchFilter> component1() {
        return this.searchFilters;
    }

    public final List<String> component2() {
        return this.allLocations;
    }

    public final FiltersResponse copy(List<SearchFilter> list, List<String> list2) {
        Intrinsics.checkNotNullParameter(list, "searchFilters");
        Intrinsics.checkNotNullParameter(list2, "allLocations");
        return new FiltersResponse(list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FiltersResponse)) {
            return false;
        }
        FiltersResponse filtersResponse = (FiltersResponse) obj;
        return Intrinsics.areEqual((Object) this.searchFilters, (Object) filtersResponse.searchFilters) && Intrinsics.areEqual((Object) this.allLocations, (Object) filtersResponse.allLocations);
    }

    public int hashCode() {
        List<SearchFilter> list = this.searchFilters;
        int i = 0;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        List<String> list2 = this.allLocations;
        if (list2 != null) {
            i = list2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "FiltersResponse(searchFilters=" + this.searchFilters + ", allLocations=" + this.allLocations + ")";
    }

    public FiltersResponse(List<SearchFilter> list, List<String> list2) {
        Intrinsics.checkNotNullParameter(list, "searchFilters");
        Intrinsics.checkNotNullParameter(list2, "allLocations");
        this.searchFilters = list;
        this.allLocations = list2;
    }

    public final List<SearchFilter> getSearchFilters() {
        return this.searchFilters;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FiltersResponse(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final List<String> getAllLocations() {
        return this.allLocations;
    }
}
