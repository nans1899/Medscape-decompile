package com.medscape.android.drugs.details.datamodels;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/medscape/android/drugs/details/datamodels/ScrollPosition;", "", "position", "", "offset", "(II)V", "getOffset", "()I", "getPosition", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ScrollPosition.kt */
public final class ScrollPosition {
    private final int offset;
    private final int position;

    public ScrollPosition() {
        this(0, 0, 3, (DefaultConstructorMarker) null);
    }

    public ScrollPosition(int i, int i2) {
        this.position = i;
        this.offset = i2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ScrollPosition(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getPosition() {
        return this.position;
    }
}
