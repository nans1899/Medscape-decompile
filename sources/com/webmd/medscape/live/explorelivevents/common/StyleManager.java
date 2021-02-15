package com.webmd.medscape.live.explorelivevents.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u0019\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001aHÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0017\"\u0004\b\u0018\u0010\u0004¨\u0006 "}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "Landroid/os/Parcelable;", "isFirstScreen", "", "(Z)V", "colors", "Lcom/webmd/medscape/live/explorelivevents/common/ExploreColorsContainer;", "getColors", "()Lcom/webmd/medscape/live/explorelivevents/common/ExploreColorsContainer;", "customView", "Landroid/view/View;", "getCustomView", "()Landroid/view/View;", "setCustomView", "(Landroid/view/View;)V", "fonts", "Lcom/webmd/medscape/live/explorelivevents/common/ExploreFontsContainer;", "getFonts", "()Lcom/webmd/medscape/live/explorelivevents/common/ExploreFontsContainer;", "icons", "Lcom/webmd/medscape/live/explorelivevents/common/ExploreIconsContainer;", "getIcons", "()Lcom/webmd/medscape/live/explorelivevents/common/ExploreIconsContainer;", "()Z", "setFirstScreen", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: StyleManager.kt */
public class StyleManager implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final ExploreColorsContainer colors;
    private View customView;
    private final ExploreFontsContainer fonts;
    private final ExploreIconsContainer icons;
    private boolean isFirstScreen;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "in");
            return new StyleManager(parcel.readInt() != 0);
        }

        public final Object[] newArray(int i) {
            return new StyleManager[i];
        }
    }

    public StyleManager() {
        this(false, 1, (DefaultConstructorMarker) null);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(this.isFirstScreen ? 1 : 0);
    }

    public StyleManager(boolean z) {
        this.isFirstScreen = z;
        ExploreColorsContainer exploreColorsContainer = r1;
        ExploreColorsContainer exploreColorsContainer2 = new ExploreColorsContainer(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 524287, (DefaultConstructorMarker) null);
        this.colors = exploreColorsContainer;
        this.icons = new ExploreIconsContainer(0, 0, 3, (DefaultConstructorMarker) null);
        this.fonts = new ExploreFontsContainer(0, 0, 0, 0, 0, 31, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ StyleManager(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final boolean isFirstScreen() {
        return this.isFirstScreen;
    }

    public final void setFirstScreen(boolean z) {
        this.isFirstScreen = z;
    }

    public final ExploreColorsContainer getColors() {
        return this.colors;
    }

    public final ExploreIconsContainer getIcons() {
        return this.icons;
    }

    public final ExploreFontsContainer getFonts() {
        return this.fonts;
    }

    public final View getCustomView() {
        return this.customView;
    }

    public final void setCustomView(View view) {
        this.customView = view;
    }
}
