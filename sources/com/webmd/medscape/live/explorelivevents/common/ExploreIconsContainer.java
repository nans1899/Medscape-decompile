package com.webmd.medscape.live.explorelivevents.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.webmd.medscape.live.explorelivevents.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001J\t\u0010\u0010\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n¨\u0006\u001d"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/ExploreIconsContainer;", "Lcom/webmd/medscape/live/explorelivevents/common/IExploreIcons;", "Landroid/os/Parcelable;", "navIcon", "", "tickIcon", "(II)V", "getNavIcon", "()I", "setNavIcon", "(I)V", "getTickIcon", "setTickIcon", "component1", "component2", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ExploreIconsContainer.kt */
public final class ExploreIconsContainer implements IExploreIcons, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private int navIcon;
    private int tickIcon;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "in");
            return new ExploreIconsContainer(parcel.readInt(), parcel.readInt());
        }

        public final Object[] newArray(int i) {
            return new ExploreIconsContainer[i];
        }
    }

    public ExploreIconsContainer() {
        this(0, 0, 3, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ ExploreIconsContainer copy$default(ExploreIconsContainer exploreIconsContainer, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = exploreIconsContainer.getNavIcon();
        }
        if ((i3 & 2) != 0) {
            i2 = exploreIconsContainer.getTickIcon();
        }
        return exploreIconsContainer.copy(i, i2);
    }

    public final int component1() {
        return getNavIcon();
    }

    public final int component2() {
        return getTickIcon();
    }

    public final ExploreIconsContainer copy(int i, int i2) {
        return new ExploreIconsContainer(i, i2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExploreIconsContainer)) {
            return false;
        }
        ExploreIconsContainer exploreIconsContainer = (ExploreIconsContainer) obj;
        return getNavIcon() == exploreIconsContainer.getNavIcon() && getTickIcon() == exploreIconsContainer.getTickIcon();
    }

    public int hashCode() {
        return (getNavIcon() * 31) + getTickIcon();
    }

    public String toString() {
        return "ExploreIconsContainer(navIcon=" + getNavIcon() + ", tickIcon=" + getTickIcon() + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(this.navIcon);
        parcel.writeInt(this.tickIcon);
    }

    public ExploreIconsContainer(int i, int i2) {
        this.navIcon = i;
        this.tickIcon = i2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ExploreIconsContainer(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? R.drawable.ic_arrow_up : i, (i3 & 2) != 0 ? R.drawable.ic_done : i2);
    }

    public int getNavIcon() {
        return this.navIcon;
    }

    public void setNavIcon(int i) {
        this.navIcon = i;
    }

    public int getTickIcon() {
        return this.tickIcon;
    }

    public void setTickIcon(int i) {
        this.tickIcon = i;
    }
}
