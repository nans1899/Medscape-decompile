package com.webmd.medscape.live.explorelivevents.data;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/MenuUiState;", "", "selectionStatus", "", "applyIconStatus", "allSelectedStatus", "(ZZZ)V", "getAllSelectedStatus", "()Z", "getApplyIconStatus", "getSelectionStatus", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: UiState.kt */
public final class MenuUiState {
    private final boolean allSelectedStatus;
    private final boolean applyIconStatus;
    private final boolean selectionStatus;

    public MenuUiState() {
        this(false, false, false, 7, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ MenuUiState copy$default(MenuUiState menuUiState, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = menuUiState.selectionStatus;
        }
        if ((i & 2) != 0) {
            z2 = menuUiState.applyIconStatus;
        }
        if ((i & 4) != 0) {
            z3 = menuUiState.allSelectedStatus;
        }
        return menuUiState.copy(z, z2, z3);
    }

    public final boolean component1() {
        return this.selectionStatus;
    }

    public final boolean component2() {
        return this.applyIconStatus;
    }

    public final boolean component3() {
        return this.allSelectedStatus;
    }

    public final MenuUiState copy(boolean z, boolean z2, boolean z3) {
        return new MenuUiState(z, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MenuUiState)) {
            return false;
        }
        MenuUiState menuUiState = (MenuUiState) obj;
        return this.selectionStatus == menuUiState.selectionStatus && this.applyIconStatus == menuUiState.applyIconStatus && this.allSelectedStatus == menuUiState.allSelectedStatus;
    }

    public int hashCode() {
        boolean z = this.selectionStatus;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        boolean z3 = this.applyIconStatus;
        if (z3) {
            z3 = true;
        }
        int i2 = (i + (z3 ? 1 : 0)) * 31;
        boolean z4 = this.allSelectedStatus;
        if (!z4) {
            z2 = z4;
        }
        return i2 + (z2 ? 1 : 0);
    }

    public String toString() {
        return "MenuUiState(selectionStatus=" + this.selectionStatus + ", applyIconStatus=" + this.applyIconStatus + ", allSelectedStatus=" + this.allSelectedStatus + ")";
    }

    public MenuUiState(boolean z, boolean z2, boolean z3) {
        this.selectionStatus = z;
        this.applyIconStatus = z2;
        this.allSelectedStatus = z3;
    }

    public final boolean getSelectionStatus() {
        return this.selectionStatus;
    }

    public final boolean getApplyIconStatus() {
        return this.applyIconStatus;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MenuUiState(boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) != 0 ? false : z3);
    }

    public final boolean getAllSelectedStatus() {
        return this.allSelectedStatus;
    }
}
