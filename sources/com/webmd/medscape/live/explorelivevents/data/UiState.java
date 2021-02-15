package com.webmd.medscape.live.explorelivevents.data;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00032\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/UiState;", "", "loading", "", "noData", "hasData", "showMoreItems", "(ZZZZ)V", "getHasData", "()Z", "getLoading", "getNoData", "getShowMoreItems", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: UiState.kt */
public final class UiState {
    private final boolean hasData;
    private final boolean loading;
    private final boolean noData;
    private final boolean showMoreItems;

    public UiState() {
        this(false, false, false, false, 15, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ UiState copy$default(UiState uiState, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 1) != 0) {
            z = uiState.loading;
        }
        if ((i & 2) != 0) {
            z2 = uiState.noData;
        }
        if ((i & 4) != 0) {
            z3 = uiState.hasData;
        }
        if ((i & 8) != 0) {
            z4 = uiState.showMoreItems;
        }
        return uiState.copy(z, z2, z3, z4);
    }

    public final boolean component1() {
        return this.loading;
    }

    public final boolean component2() {
        return this.noData;
    }

    public final boolean component3() {
        return this.hasData;
    }

    public final boolean component4() {
        return this.showMoreItems;
    }

    public final UiState copy(boolean z, boolean z2, boolean z3, boolean z4) {
        return new UiState(z, z2, z3, z4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UiState)) {
            return false;
        }
        UiState uiState = (UiState) obj;
        return this.loading == uiState.loading && this.noData == uiState.noData && this.hasData == uiState.hasData && this.showMoreItems == uiState.showMoreItems;
    }

    public int hashCode() {
        boolean z = this.loading;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        boolean z3 = this.noData;
        if (z3) {
            z3 = true;
        }
        int i2 = (i + (z3 ? 1 : 0)) * 31;
        boolean z4 = this.hasData;
        if (z4) {
            z4 = true;
        }
        int i3 = (i2 + (z4 ? 1 : 0)) * 31;
        boolean z5 = this.showMoreItems;
        if (!z5) {
            z2 = z5;
        }
        return i3 + (z2 ? 1 : 0);
    }

    public String toString() {
        return "UiState(loading=" + this.loading + ", noData=" + this.noData + ", hasData=" + this.hasData + ", showMoreItems=" + this.showMoreItems + ")";
    }

    public UiState(boolean z, boolean z2, boolean z3, boolean z4) {
        this.loading = z;
        this.noData = z2;
        this.hasData = z3;
        this.showMoreItems = z4;
    }

    public final boolean getLoading() {
        return this.loading;
    }

    public final boolean getNoData() {
        return this.noData;
    }

    public final boolean getHasData() {
        return this.hasData;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ UiState(boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3, (i & 8) != 0 ? false : z4);
    }

    public final boolean getShowMoreItems() {
        return this.showMoreItems;
    }
}
