package com.webmd.medscape.live.explorelivevents.common;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\bQ\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002BÃ\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\u0004\u0012\b\b\u0002\u0010\t\u001a\u00020\u0004\u0012\b\b\u0002\u0010\n\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0004\u0012\b\b\u0002\u0010\f\u001a\u00020\u0004\u0012\b\b\u0002\u0010\r\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0004¢\u0006\u0002\u0010\u0017J\t\u0010@\u001a\u00020\u0004HÆ\u0003J\t\u0010A\u001a\u00020\u0004HÆ\u0003J\t\u0010B\u001a\u00020\u0004HÆ\u0003J\t\u0010C\u001a\u00020\u0004HÆ\u0003J\t\u0010D\u001a\u00020\u0004HÆ\u0003J\t\u0010E\u001a\u00020\u0004HÆ\u0003J\t\u0010F\u001a\u00020\u0004HÆ\u0003J\t\u0010G\u001a\u00020\u0004HÆ\u0003J\t\u0010H\u001a\u00020\u0004HÆ\u0003J\t\u0010I\u001a\u00020\u0004HÆ\u0003J\t\u0010J\u001a\u00020\u0004HÆ\u0003J\t\u0010K\u001a\u00020\u0004HÆ\u0003J\t\u0010L\u001a\u00020\u0004HÆ\u0003J\t\u0010M\u001a\u00020\u0004HÆ\u0003J\t\u0010N\u001a\u00020\u0004HÆ\u0003J\t\u0010O\u001a\u00020\u0004HÆ\u0003J\t\u0010P\u001a\u00020\u0004HÆ\u0003J\t\u0010Q\u001a\u00020\u0004HÆ\u0003J\t\u0010R\u001a\u00020\u0004HÆ\u0003JÇ\u0001\u0010S\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0012\u001a\u00020\u00042\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u0004HÆ\u0001J\t\u0010T\u001a\u00020\u0004HÖ\u0001J\u0013\u0010U\u001a\u00020V2\b\u0010W\u001a\u0004\u0018\u00010XHÖ\u0003J\t\u0010Y\u001a\u00020\u0004HÖ\u0001J\t\u0010Z\u001a\u00020[HÖ\u0001J\u0019\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020\u0004HÖ\u0001R\u001a\u0010\r\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u000b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0019\"\u0004\b\u001f\u0010\u001bR\u001a\u0010\u000e\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001bR\u001a\u0010\u0012\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0019\"\u0004\b#\u0010\u001bR\u001a\u0010\u0013\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0019\"\u0004\b%\u0010\u001bR\u001a\u0010\u0011\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0019\"\u0004\b'\u0010\u001bR\u001a\u0010\u0010\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0019\"\u0004\b)\u0010\u001bR\u001a\u0010\u0016\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0019\"\u0004\b+\u0010\u001bR\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0019\"\u0004\b-\u0010\u001bR\u001a\u0010\u0014\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0019\"\u0004\b/\u0010\u001bR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0019\"\u0004\b1\u0010\u001bR\u001a\u0010\u0005\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0019\"\u0004\b3\u0010\u001bR\u001a\u0010\n\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0019\"\u0004\b5\u0010\u001bR\u001a\u0010\b\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0019\"\u0004\b7\u0010\u001bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0019\"\u0004\b9\u0010\u001bR\u001a\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0019\"\u0004\b;\u0010\u001bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u0019\"\u0004\b=\u0010\u001bR\u001a\u0010\u0006\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u0019\"\u0004\b?\u0010\u001b¨\u0006a"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/ExploreColorsContainer;", "Lcom/webmd/medscape/live/explorelivevents/common/IExploreColors;", "Landroid/os/Parcelable;", "navHeaderBackgroundColor", "", "navTitleColor", "statusBarColor", "quickFiltersViewBackgroundColor", "quickFiltersHeadingTextColor", "quickFiltersItemTextColor", "quickFiltersDividerColor", "bigTitleTextColor", "showAllButtonTextColor", "bigHeadingTextColor", "eventDateTextColor", "eventAccreditationTextColor", "eventTitleTextColor", "eventLocationTextColor", "eventIconTintColor", "eventItemBackgroundColor", "filterItemTextColor", "filterItemBackgroundColor", "filterCheckBoxSelectedColor", "(IIIIIIIIIIIIIIIIIII)V", "getBigHeadingTextColor", "()I", "setBigHeadingTextColor", "(I)V", "getBigTitleTextColor", "setBigTitleTextColor", "getEventAccreditationTextColor", "setEventAccreditationTextColor", "getEventDateTextColor", "setEventDateTextColor", "getEventIconTintColor", "setEventIconTintColor", "getEventItemBackgroundColor", "setEventItemBackgroundColor", "getEventLocationTextColor", "setEventLocationTextColor", "getEventTitleTextColor", "setEventTitleTextColor", "getFilterCheckBoxSelectedColor", "setFilterCheckBoxSelectedColor", "getFilterItemBackgroundColor", "setFilterItemBackgroundColor", "getFilterItemTextColor", "setFilterItemTextColor", "getNavHeaderBackgroundColor", "setNavHeaderBackgroundColor", "getNavTitleColor", "setNavTitleColor", "getQuickFiltersDividerColor", "setQuickFiltersDividerColor", "getQuickFiltersHeadingTextColor", "setQuickFiltersHeadingTextColor", "getQuickFiltersItemTextColor", "setQuickFiltersItemTextColor", "getQuickFiltersViewBackgroundColor", "setQuickFiltersViewBackgroundColor", "getShowAllButtonTextColor", "setShowAllButtonTextColor", "getStatusBarColor", "setStatusBarColor", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ExploreColorsContainer.kt */
public final class ExploreColorsContainer implements IExploreColors, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private int bigHeadingTextColor;
    private int bigTitleTextColor;
    private int eventAccreditationTextColor;
    private int eventDateTextColor;
    private int eventIconTintColor;
    private int eventItemBackgroundColor;
    private int eventLocationTextColor;
    private int eventTitleTextColor;
    private int filterCheckBoxSelectedColor;
    private int filterItemBackgroundColor;
    private int filterItemTextColor;
    private int navHeaderBackgroundColor;
    private int navTitleColor;
    private int quickFiltersDividerColor;
    private int quickFiltersHeadingTextColor;
    private int quickFiltersItemTextColor;
    private int quickFiltersViewBackgroundColor;
    private int showAllButtonTextColor;
    private int statusBarColor;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "in");
            return new ExploreColorsContainer(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public final Object[] newArray(int i) {
            return new ExploreColorsContainer[i];
        }
    }

    public ExploreColorsContainer() {
        this(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524287, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ ExploreColorsContainer copy$default(ExploreColorsContainer exploreColorsContainer, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, Object obj) {
        int i21 = i20;
        return exploreColorsContainer.copy((i21 & 1) != 0 ? exploreColorsContainer.getNavHeaderBackgroundColor() : i, (i21 & 2) != 0 ? exploreColorsContainer.getNavTitleColor() : i2, (i21 & 4) != 0 ? exploreColorsContainer.getStatusBarColor() : i3, (i21 & 8) != 0 ? exploreColorsContainer.getQuickFiltersViewBackgroundColor() : i4, (i21 & 16) != 0 ? exploreColorsContainer.getQuickFiltersHeadingTextColor() : i5, (i21 & 32) != 0 ? exploreColorsContainer.getQuickFiltersItemTextColor() : i6, (i21 & 64) != 0 ? exploreColorsContainer.getQuickFiltersDividerColor() : i7, (i21 & 128) != 0 ? exploreColorsContainer.getBigTitleTextColor() : i8, (i21 & 256) != 0 ? exploreColorsContainer.getShowAllButtonTextColor() : i9, (i21 & 512) != 0 ? exploreColorsContainer.getBigHeadingTextColor() : i10, (i21 & 1024) != 0 ? exploreColorsContainer.getEventDateTextColor() : i11, (i21 & 2048) != 0 ? exploreColorsContainer.getEventAccreditationTextColor() : i12, (i21 & 4096) != 0 ? exploreColorsContainer.getEventTitleTextColor() : i13, (i21 & 8192) != 0 ? exploreColorsContainer.getEventLocationTextColor() : i14, (i21 & 16384) != 0 ? exploreColorsContainer.getEventIconTintColor() : i15, (i21 & 32768) != 0 ? exploreColorsContainer.getEventItemBackgroundColor() : i16, (i21 & 65536) != 0 ? exploreColorsContainer.getFilterItemTextColor() : i17, (i21 & 131072) != 0 ? exploreColorsContainer.getFilterItemBackgroundColor() : i18, (i21 & 262144) != 0 ? exploreColorsContainer.getFilterCheckBoxSelectedColor() : i19);
    }

    public final int component1() {
        return getNavHeaderBackgroundColor();
    }

    public final int component10() {
        return getBigHeadingTextColor();
    }

    public final int component11() {
        return getEventDateTextColor();
    }

    public final int component12() {
        return getEventAccreditationTextColor();
    }

    public final int component13() {
        return getEventTitleTextColor();
    }

    public final int component14() {
        return getEventLocationTextColor();
    }

    public final int component15() {
        return getEventIconTintColor();
    }

    public final int component16() {
        return getEventItemBackgroundColor();
    }

    public final int component17() {
        return getFilterItemTextColor();
    }

    public final int component18() {
        return getFilterItemBackgroundColor();
    }

    public final int component19() {
        return getFilterCheckBoxSelectedColor();
    }

    public final int component2() {
        return getNavTitleColor();
    }

    public final int component3() {
        return getStatusBarColor();
    }

    public final int component4() {
        return getQuickFiltersViewBackgroundColor();
    }

    public final int component5() {
        return getQuickFiltersHeadingTextColor();
    }

    public final int component6() {
        return getQuickFiltersItemTextColor();
    }

    public final int component7() {
        return getQuickFiltersDividerColor();
    }

    public final int component8() {
        return getBigTitleTextColor();
    }

    public final int component9() {
        return getShowAllButtonTextColor();
    }

    public final ExploreColorsContainer copy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19) {
        return new ExploreColorsContainer(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ExploreColorsContainer)) {
            return false;
        }
        ExploreColorsContainer exploreColorsContainer = (ExploreColorsContainer) obj;
        return getNavHeaderBackgroundColor() == exploreColorsContainer.getNavHeaderBackgroundColor() && getNavTitleColor() == exploreColorsContainer.getNavTitleColor() && getStatusBarColor() == exploreColorsContainer.getStatusBarColor() && getQuickFiltersViewBackgroundColor() == exploreColorsContainer.getQuickFiltersViewBackgroundColor() && getQuickFiltersHeadingTextColor() == exploreColorsContainer.getQuickFiltersHeadingTextColor() && getQuickFiltersItemTextColor() == exploreColorsContainer.getQuickFiltersItemTextColor() && getQuickFiltersDividerColor() == exploreColorsContainer.getQuickFiltersDividerColor() && getBigTitleTextColor() == exploreColorsContainer.getBigTitleTextColor() && getShowAllButtonTextColor() == exploreColorsContainer.getShowAllButtonTextColor() && getBigHeadingTextColor() == exploreColorsContainer.getBigHeadingTextColor() && getEventDateTextColor() == exploreColorsContainer.getEventDateTextColor() && getEventAccreditationTextColor() == exploreColorsContainer.getEventAccreditationTextColor() && getEventTitleTextColor() == exploreColorsContainer.getEventTitleTextColor() && getEventLocationTextColor() == exploreColorsContainer.getEventLocationTextColor() && getEventIconTintColor() == exploreColorsContainer.getEventIconTintColor() && getEventItemBackgroundColor() == exploreColorsContainer.getEventItemBackgroundColor() && getFilterItemTextColor() == exploreColorsContainer.getFilterItemTextColor() && getFilterItemBackgroundColor() == exploreColorsContainer.getFilterItemBackgroundColor() && getFilterCheckBoxSelectedColor() == exploreColorsContainer.getFilterCheckBoxSelectedColor();
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((getNavHeaderBackgroundColor() * 31) + getNavTitleColor()) * 31) + getStatusBarColor()) * 31) + getQuickFiltersViewBackgroundColor()) * 31) + getQuickFiltersHeadingTextColor()) * 31) + getQuickFiltersItemTextColor()) * 31) + getQuickFiltersDividerColor()) * 31) + getBigTitleTextColor()) * 31) + getShowAllButtonTextColor()) * 31) + getBigHeadingTextColor()) * 31) + getEventDateTextColor()) * 31) + getEventAccreditationTextColor()) * 31) + getEventTitleTextColor()) * 31) + getEventLocationTextColor()) * 31) + getEventIconTintColor()) * 31) + getEventItemBackgroundColor()) * 31) + getFilterItemTextColor()) * 31) + getFilterItemBackgroundColor()) * 31) + getFilterCheckBoxSelectedColor();
    }

    public String toString() {
        return "ExploreColorsContainer(navHeaderBackgroundColor=" + getNavHeaderBackgroundColor() + ", navTitleColor=" + getNavTitleColor() + ", statusBarColor=" + getStatusBarColor() + ", quickFiltersViewBackgroundColor=" + getQuickFiltersViewBackgroundColor() + ", quickFiltersHeadingTextColor=" + getQuickFiltersHeadingTextColor() + ", quickFiltersItemTextColor=" + getQuickFiltersItemTextColor() + ", quickFiltersDividerColor=" + getQuickFiltersDividerColor() + ", bigTitleTextColor=" + getBigTitleTextColor() + ", showAllButtonTextColor=" + getShowAllButtonTextColor() + ", bigHeadingTextColor=" + getBigHeadingTextColor() + ", eventDateTextColor=" + getEventDateTextColor() + ", eventAccreditationTextColor=" + getEventAccreditationTextColor() + ", eventTitleTextColor=" + getEventTitleTextColor() + ", eventLocationTextColor=" + getEventLocationTextColor() + ", eventIconTintColor=" + getEventIconTintColor() + ", eventItemBackgroundColor=" + getEventItemBackgroundColor() + ", filterItemTextColor=" + getFilterItemTextColor() + ", filterItemBackgroundColor=" + getFilterItemBackgroundColor() + ", filterCheckBoxSelectedColor=" + getFilterCheckBoxSelectedColor() + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(this.navHeaderBackgroundColor);
        parcel.writeInt(this.navTitleColor);
        parcel.writeInt(this.statusBarColor);
        parcel.writeInt(this.quickFiltersViewBackgroundColor);
        parcel.writeInt(this.quickFiltersHeadingTextColor);
        parcel.writeInt(this.quickFiltersItemTextColor);
        parcel.writeInt(this.quickFiltersDividerColor);
        parcel.writeInt(this.bigTitleTextColor);
        parcel.writeInt(this.showAllButtonTextColor);
        parcel.writeInt(this.bigHeadingTextColor);
        parcel.writeInt(this.eventDateTextColor);
        parcel.writeInt(this.eventAccreditationTextColor);
        parcel.writeInt(this.eventTitleTextColor);
        parcel.writeInt(this.eventLocationTextColor);
        parcel.writeInt(this.eventIconTintColor);
        parcel.writeInt(this.eventItemBackgroundColor);
        parcel.writeInt(this.filterItemTextColor);
        parcel.writeInt(this.filterItemBackgroundColor);
        parcel.writeInt(this.filterCheckBoxSelectedColor);
    }

    public ExploreColorsContainer(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19) {
        this.navHeaderBackgroundColor = i;
        this.navTitleColor = i2;
        this.statusBarColor = i3;
        this.quickFiltersViewBackgroundColor = i4;
        this.quickFiltersHeadingTextColor = i5;
        this.quickFiltersItemTextColor = i6;
        this.quickFiltersDividerColor = i7;
        this.bigTitleTextColor = i8;
        this.showAllButtonTextColor = i9;
        this.bigHeadingTextColor = i10;
        this.eventDateTextColor = i11;
        this.eventAccreditationTextColor = i12;
        this.eventTitleTextColor = i13;
        this.eventLocationTextColor = i14;
        this.eventIconTintColor = i15;
        this.eventItemBackgroundColor = i16;
        this.filterItemTextColor = i17;
        this.filterItemBackgroundColor = i18;
        this.filterCheckBoxSelectedColor = i19;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ExploreColorsContainer(int r21, int r22, int r23, int r24, int r25, int r26, int r27, int r28, int r29, int r30, int r31, int r32, int r33, int r34, int r35, int r36, int r37, int r38, int r39, int r40, kotlin.jvm.internal.DefaultConstructorMarker r41) {
        /*
            r20 = this;
            r0 = r40
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0009
            int r1 = com.webmd.medscape.live.explorelivevents.R.color.white
            goto L_0x000b
        L_0x0009:
            r1 = r21
        L_0x000b:
            r2 = r0 & 2
            if (r2 == 0) goto L_0x0012
            int r2 = com.webmd.medscape.live.explorelivevents.R.color.shades
            goto L_0x0014
        L_0x0012:
            r2 = r22
        L_0x0014:
            r3 = r0 & 4
            if (r3 == 0) goto L_0x001b
            int r3 = com.webmd.medscape.live.explorelivevents.R.color.silver
            goto L_0x001d
        L_0x001b:
            r3 = r23
        L_0x001d:
            r4 = r0 & 8
            if (r4 == 0) goto L_0x0024
            int r4 = com.webmd.medscape.live.explorelivevents.R.color.white
            goto L_0x0026
        L_0x0024:
            r4 = r24
        L_0x0026:
            r5 = r0 & 16
            if (r5 == 0) goto L_0x002d
            int r5 = com.webmd.medscape.live.explorelivevents.R.color.bandicoot
            goto L_0x002f
        L_0x002d:
            r5 = r25
        L_0x002f:
            r6 = r0 & 32
            if (r6 == 0) goto L_0x0036
            int r6 = com.webmd.medscape.live.explorelivevents.R.color.mine_shaft
            goto L_0x0038
        L_0x0036:
            r6 = r26
        L_0x0038:
            r7 = r0 & 64
            if (r7 == 0) goto L_0x003f
            int r7 = com.webmd.medscape.live.explorelivevents.R.color.silver
            goto L_0x0041
        L_0x003f:
            r7 = r27
        L_0x0041:
            r8 = r0 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto L_0x0048
            int r8 = com.webmd.medscape.live.explorelivevents.R.color.mine_shaft
            goto L_0x004a
        L_0x0048:
            r8 = r28
        L_0x004a:
            r9 = r0 & 256(0x100, float:3.59E-43)
            if (r9 == 0) goto L_0x0051
            int r9 = com.webmd.medscape.live.explorelivevents.R.color.colorPrimaryDark
            goto L_0x0053
        L_0x0051:
            r9 = r29
        L_0x0053:
            r10 = r0 & 512(0x200, float:7.175E-43)
            if (r10 == 0) goto L_0x005a
            int r10 = com.webmd.medscape.live.explorelivevents.R.color.white
            goto L_0x005c
        L_0x005a:
            r10 = r30
        L_0x005c:
            r11 = r0 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L_0x0063
            int r11 = com.webmd.medscape.live.explorelivevents.R.color.colorPrimaryDark
            goto L_0x0065
        L_0x0063:
            r11 = r31
        L_0x0065:
            r12 = r0 & 2048(0x800, float:2.87E-42)
            if (r12 == 0) goto L_0x006c
            int r12 = com.webmd.medscape.live.explorelivevents.R.color.colorPrimaryDark
            goto L_0x006e
        L_0x006c:
            r12 = r32
        L_0x006e:
            r13 = r0 & 4096(0x1000, float:5.74E-42)
            if (r13 == 0) goto L_0x0075
            int r13 = com.webmd.medscape.live.explorelivevents.R.color.mine_shaft
            goto L_0x0077
        L_0x0075:
            r13 = r33
        L_0x0077:
            r14 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r14 == 0) goto L_0x007e
            int r14 = com.webmd.medscape.live.explorelivevents.R.color.dark_grey
            goto L_0x0080
        L_0x007e:
            r14 = r34
        L_0x0080:
            r15 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r15 == 0) goto L_0x0087
            int r15 = com.webmd.medscape.live.explorelivevents.R.color.dark_grey
            goto L_0x0089
        L_0x0087:
            r15 = r35
        L_0x0089:
            r16 = 32768(0x8000, float:4.5918E-41)
            r16 = r0 & r16
            if (r16 == 0) goto L_0x0093
            int r16 = com.webmd.medscape.live.explorelivevents.R.color.white
            goto L_0x0095
        L_0x0093:
            r16 = r36
        L_0x0095:
            r17 = 65536(0x10000, float:9.18355E-41)
            r17 = r0 & r17
            if (r17 == 0) goto L_0x009e
            int r17 = com.webmd.medscape.live.explorelivevents.R.color.dark_grey
            goto L_0x00a0
        L_0x009e:
            r17 = r37
        L_0x00a0:
            r18 = 131072(0x20000, float:1.83671E-40)
            r18 = r0 & r18
            if (r18 == 0) goto L_0x00a9
            int r18 = com.webmd.medscape.live.explorelivevents.R.color.white
            goto L_0x00ab
        L_0x00a9:
            r18 = r38
        L_0x00ab:
            r19 = 262144(0x40000, float:3.67342E-40)
            r0 = r0 & r19
            if (r0 == 0) goto L_0x00b4
            int r0 = com.webmd.medscape.live.explorelivevents.R.color.colorPrimaryDark
            goto L_0x00b6
        L_0x00b4:
            r0 = r39
        L_0x00b6:
            r21 = r20
            r22 = r1
            r23 = r2
            r24 = r3
            r25 = r4
            r26 = r5
            r27 = r6
            r28 = r7
            r29 = r8
            r30 = r9
            r31 = r10
            r32 = r11
            r33 = r12
            r34 = r13
            r35 = r14
            r36 = r15
            r37 = r16
            r38 = r17
            r39 = r18
            r40 = r0
            r21.<init>(r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.medscape.live.explorelivevents.common.ExploreColorsContainer.<init>(int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public int getNavHeaderBackgroundColor() {
        return this.navHeaderBackgroundColor;
    }

    public void setNavHeaderBackgroundColor(int i) {
        this.navHeaderBackgroundColor = i;
    }

    public int getNavTitleColor() {
        return this.navTitleColor;
    }

    public void setNavTitleColor(int i) {
        this.navTitleColor = i;
    }

    public int getStatusBarColor() {
        return this.statusBarColor;
    }

    public void setStatusBarColor(int i) {
        this.statusBarColor = i;
    }

    public int getQuickFiltersViewBackgroundColor() {
        return this.quickFiltersViewBackgroundColor;
    }

    public void setQuickFiltersViewBackgroundColor(int i) {
        this.quickFiltersViewBackgroundColor = i;
    }

    public int getQuickFiltersHeadingTextColor() {
        return this.quickFiltersHeadingTextColor;
    }

    public void setQuickFiltersHeadingTextColor(int i) {
        this.quickFiltersHeadingTextColor = i;
    }

    public int getQuickFiltersItemTextColor() {
        return this.quickFiltersItemTextColor;
    }

    public void setQuickFiltersItemTextColor(int i) {
        this.quickFiltersItemTextColor = i;
    }

    public int getQuickFiltersDividerColor() {
        return this.quickFiltersDividerColor;
    }

    public void setQuickFiltersDividerColor(int i) {
        this.quickFiltersDividerColor = i;
    }

    public int getBigTitleTextColor() {
        return this.bigTitleTextColor;
    }

    public void setBigTitleTextColor(int i) {
        this.bigTitleTextColor = i;
    }

    public int getShowAllButtonTextColor() {
        return this.showAllButtonTextColor;
    }

    public void setShowAllButtonTextColor(int i) {
        this.showAllButtonTextColor = i;
    }

    public int getBigHeadingTextColor() {
        return this.bigHeadingTextColor;
    }

    public void setBigHeadingTextColor(int i) {
        this.bigHeadingTextColor = i;
    }

    public int getEventDateTextColor() {
        return this.eventDateTextColor;
    }

    public void setEventDateTextColor(int i) {
        this.eventDateTextColor = i;
    }

    public int getEventAccreditationTextColor() {
        return this.eventAccreditationTextColor;
    }

    public void setEventAccreditationTextColor(int i) {
        this.eventAccreditationTextColor = i;
    }

    public int getEventTitleTextColor() {
        return this.eventTitleTextColor;
    }

    public void setEventTitleTextColor(int i) {
        this.eventTitleTextColor = i;
    }

    public int getEventLocationTextColor() {
        return this.eventLocationTextColor;
    }

    public void setEventLocationTextColor(int i) {
        this.eventLocationTextColor = i;
    }

    public int getEventIconTintColor() {
        return this.eventIconTintColor;
    }

    public void setEventIconTintColor(int i) {
        this.eventIconTintColor = i;
    }

    public int getEventItemBackgroundColor() {
        return this.eventItemBackgroundColor;
    }

    public void setEventItemBackgroundColor(int i) {
        this.eventItemBackgroundColor = i;
    }

    public int getFilterItemTextColor() {
        return this.filterItemTextColor;
    }

    public void setFilterItemTextColor(int i) {
        this.filterItemTextColor = i;
    }

    public int getFilterItemBackgroundColor() {
        return this.filterItemBackgroundColor;
    }

    public void setFilterItemBackgroundColor(int i) {
        this.filterItemBackgroundColor = i;
    }

    public int getFilterCheckBoxSelectedColor() {
        return this.filterCheckBoxSelectedColor;
    }

    public void setFilterCheckBoxSelectedColor(int i) {
        this.filterCheckBoxSelectedColor = i;
    }
}
