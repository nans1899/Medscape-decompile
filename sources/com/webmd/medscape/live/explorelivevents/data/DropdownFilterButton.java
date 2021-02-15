package com.webmd.medscape.live.explorelivevents.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J1\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u000b\"\u0004\b\u000e\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/DropdownFilterButton;", "", "text", "", "isEnabled", "", "type", "", "canceled", "(Ljava/lang/String;ZIZ)V", "getCanceled", "()Z", "setCanceled", "(Z)V", "setEnabled", "getText", "()Ljava/lang/String;", "setText", "(Ljava/lang/String;)V", "getType", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DropdownFilterButton.kt */
public final class DropdownFilterButton {
    private boolean canceled;
    private boolean isEnabled;
    private String text;
    private final int type;

    public static /* synthetic */ DropdownFilterButton copy$default(DropdownFilterButton dropdownFilterButton, String str, boolean z, int i, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = dropdownFilterButton.text;
        }
        if ((i2 & 2) != 0) {
            z = dropdownFilterButton.isEnabled;
        }
        if ((i2 & 4) != 0) {
            i = dropdownFilterButton.type;
        }
        if ((i2 & 8) != 0) {
            z2 = dropdownFilterButton.canceled;
        }
        return dropdownFilterButton.copy(str, z, i, z2);
    }

    public final String component1() {
        return this.text;
    }

    public final boolean component2() {
        return this.isEnabled;
    }

    public final int component3() {
        return this.type;
    }

    public final boolean component4() {
        return this.canceled;
    }

    public final DropdownFilterButton copy(String str, boolean z, int i, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "text");
        return new DropdownFilterButton(str, z, i, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DropdownFilterButton)) {
            return false;
        }
        DropdownFilterButton dropdownFilterButton = (DropdownFilterButton) obj;
        return Intrinsics.areEqual((Object) this.text, (Object) dropdownFilterButton.text) && this.isEnabled == dropdownFilterButton.isEnabled && this.type == dropdownFilterButton.type && this.canceled == dropdownFilterButton.canceled;
    }

    public int hashCode() {
        String str = this.text;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.isEnabled;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i = (((hashCode + (z ? 1 : 0)) * 31) + this.type) * 31;
        boolean z3 = this.canceled;
        if (!z3) {
            z2 = z3;
        }
        return i + (z2 ? 1 : 0);
    }

    public String toString() {
        return "DropdownFilterButton(text=" + this.text + ", isEnabled=" + this.isEnabled + ", type=" + this.type + ", canceled=" + this.canceled + ")";
    }

    public DropdownFilterButton(String str, boolean z, int i, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "text");
        this.text = str;
        this.isEnabled = z;
        this.type = i;
        this.canceled = z2;
    }

    public final String getText() {
        return this.text;
    }

    public final void setText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.text = str;
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public final int getType() {
        return this.type;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DropdownFilterButton(String str, boolean z, int i, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, i, (i2 & 8) != 0 ? false : z2);
    }

    public final boolean getCanceled() {
        return this.canceled;
    }

    public final void setCanceled(boolean z) {
        this.canceled = z;
    }
}
