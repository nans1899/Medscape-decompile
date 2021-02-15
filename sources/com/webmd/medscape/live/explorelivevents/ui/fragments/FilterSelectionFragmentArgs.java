package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 '2\u00020\u0001:\u0001'BQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003JW\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010 \u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u0003HÖ\u0001J\u0006\u0010$\u001a\u00020%J\t\u0010&\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006("}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/FilterSelectionFragmentArgs;", "Landroidx/navigation/NavArgs;", "viewType", "", "isFromExplore", "", "baseUrl", "", "title", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "startDate", "endDate", "(IZLjava/lang/String;Ljava/lang/String;Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;Ljava/lang/String;Ljava/lang/String;)V", "getBaseUrl", "()Ljava/lang/String;", "getEndDate", "()Z", "getStartDate", "getStyleManager", "()Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "getTitle", "getViewType", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "hashCode", "toBundle", "Landroid/os/Bundle;", "toString", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FilterSelectionFragmentArgs.kt */
public final class FilterSelectionFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String baseUrl;
    private final String endDate;
    private final boolean isFromExplore;
    private final String startDate;
    private final StyleManager styleManager;
    private final String title;
    private final int viewType;

    public static /* synthetic */ FilterSelectionFragmentArgs copy$default(FilterSelectionFragmentArgs filterSelectionFragmentArgs, int i, boolean z, String str, String str2, StyleManager styleManager2, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = filterSelectionFragmentArgs.viewType;
        }
        if ((i2 & 2) != 0) {
            z = filterSelectionFragmentArgs.isFromExplore;
        }
        boolean z2 = z;
        if ((i2 & 4) != 0) {
            str = filterSelectionFragmentArgs.baseUrl;
        }
        String str5 = str;
        if ((i2 & 8) != 0) {
            str2 = filterSelectionFragmentArgs.title;
        }
        String str6 = str2;
        if ((i2 & 16) != 0) {
            styleManager2 = filterSelectionFragmentArgs.styleManager;
        }
        StyleManager styleManager3 = styleManager2;
        if ((i2 & 32) != 0) {
            str3 = filterSelectionFragmentArgs.startDate;
        }
        String str7 = str3;
        if ((i2 & 64) != 0) {
            str4 = filterSelectionFragmentArgs.endDate;
        }
        return filterSelectionFragmentArgs.copy(i, z2, str5, str6, styleManager3, str7, str4);
    }

    @JvmStatic
    public static final FilterSelectionFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    public final int component1() {
        return this.viewType;
    }

    public final boolean component2() {
        return this.isFromExplore;
    }

    public final String component3() {
        return this.baseUrl;
    }

    public final String component4() {
        return this.title;
    }

    public final StyleManager component5() {
        return this.styleManager;
    }

    public final String component6() {
        return this.startDate;
    }

    public final String component7() {
        return this.endDate;
    }

    public final FilterSelectionFragmentArgs copy(int i, boolean z, String str, String str2, StyleManager styleManager2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        return new FilterSelectionFragmentArgs(i, z, str, str2, styleManager2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FilterSelectionFragmentArgs)) {
            return false;
        }
        FilterSelectionFragmentArgs filterSelectionFragmentArgs = (FilterSelectionFragmentArgs) obj;
        return this.viewType == filterSelectionFragmentArgs.viewType && this.isFromExplore == filterSelectionFragmentArgs.isFromExplore && Intrinsics.areEqual((Object) this.baseUrl, (Object) filterSelectionFragmentArgs.baseUrl) && Intrinsics.areEqual((Object) this.title, (Object) filterSelectionFragmentArgs.title) && Intrinsics.areEqual((Object) this.styleManager, (Object) filterSelectionFragmentArgs.styleManager) && Intrinsics.areEqual((Object) this.startDate, (Object) filterSelectionFragmentArgs.startDate) && Intrinsics.areEqual((Object) this.endDate, (Object) filterSelectionFragmentArgs.endDate);
    }

    public int hashCode() {
        int i = this.viewType * 31;
        boolean z = this.isFromExplore;
        if (z) {
            z = true;
        }
        int i2 = (i + (z ? 1 : 0)) * 31;
        String str = this.baseUrl;
        int i3 = 0;
        int hashCode = (i2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.title;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        StyleManager styleManager2 = this.styleManager;
        int hashCode3 = (hashCode2 + (styleManager2 != null ? styleManager2.hashCode() : 0)) * 31;
        String str3 = this.startDate;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.endDate;
        if (str4 != null) {
            i3 = str4.hashCode();
        }
        return hashCode4 + i3;
    }

    public String toString() {
        return "FilterSelectionFragmentArgs(viewType=" + this.viewType + ", isFromExplore=" + this.isFromExplore + ", baseUrl=" + this.baseUrl + ", title=" + this.title + ", styleManager=" + this.styleManager + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ")";
    }

    public FilterSelectionFragmentArgs(int i, boolean z, String str, String str2, StyleManager styleManager2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        this.viewType = i;
        this.isFromExplore = z;
        this.baseUrl = str;
        this.title = str2;
        this.styleManager = styleManager2;
        this.startDate = str3;
        this.endDate = str4;
    }

    public final int getViewType() {
        return this.viewType;
    }

    public final boolean isFromExplore() {
        return this.isFromExplore;
    }

    public final String getBaseUrl() {
        return this.baseUrl;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FilterSelectionFragmentArgs(int i, boolean z, String str, String str2, StyleManager styleManager2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 1 : i, (i2 & 2) != 0 ? false : z, str, (i2 & 8) != 0 ? null : str2, (i2 & 16) != 0 ? null : styleManager2, (i2 & 32) != 0 ? null : str3, (i2 & 64) != 0 ? null : str4);
    }

    public final String getTitle() {
        return this.title;
    }

    public final StyleManager getStyleManager() {
        return this.styleManager;
    }

    public final String getStartDate() {
        return this.startDate;
    }

    public final String getEndDate() {
        return this.endDate;
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("view_type", this.viewType);
        bundle.putBoolean("is_from_explore", this.isFromExplore);
        bundle.putString("base_url", this.baseUrl);
        bundle.putString("title", this.title);
        if (Parcelable.class.isAssignableFrom(StyleManager.class)) {
            bundle.putParcelable("styleManager", this.styleManager);
        } else if (Serializable.class.isAssignableFrom(StyleManager.class)) {
            bundle.putSerializable("styleManager", (Serializable) this.styleManager);
        }
        bundle.putString(FirebaseAnalytics.Param.START_DATE, this.startDate);
        bundle.putString(FirebaseAnalytics.Param.END_DATE, this.endDate);
        return bundle;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/FilterSelectionFragmentArgs$Companion;", "", "()V", "fromBundle", "Lcom/webmd/medscape/live/explorelivevents/ui/fragments/FilterSelectionFragmentArgs;", "bundle", "Landroid/os/Bundle;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FilterSelectionFragmentArgs.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final FilterSelectionFragmentArgs fromBundle(Bundle bundle) {
            String str;
            StyleManager styleManager;
            String str2;
            String str3;
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(FilterSelectionFragmentArgs.class.getClassLoader());
            int i = bundle.containsKey("view_type") ? bundle.getInt("view_type") : 1;
            boolean z = bundle.containsKey("is_from_explore") ? bundle.getBoolean("is_from_explore") : false;
            if (bundle.containsKey("base_url")) {
                String string = bundle.getString("base_url");
                if (string != null) {
                    if (bundle.containsKey("title")) {
                        str = bundle.getString("title");
                    } else {
                        str = null;
                    }
                    if (!bundle.containsKey("styleManager")) {
                        styleManager = null;
                    } else if (Parcelable.class.isAssignableFrom(StyleManager.class) || Serializable.class.isAssignableFrom(StyleManager.class)) {
                        styleManager = (StyleManager) bundle.get("styleManager");
                    } else {
                        throw new UnsupportedOperationException(StyleManager.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                    }
                    StyleManager styleManager2 = styleManager;
                    if (bundle.containsKey(FirebaseAnalytics.Param.START_DATE)) {
                        str2 = bundle.getString(FirebaseAnalytics.Param.START_DATE);
                    } else {
                        str2 = null;
                    }
                    String str4 = str2;
                    if (bundle.containsKey(FirebaseAnalytics.Param.END_DATE)) {
                        str3 = bundle.getString(FirebaseAnalytics.Param.END_DATE);
                    } else {
                        str3 = null;
                    }
                    return new FilterSelectionFragmentArgs(i, z, string, str, styleManager2, str4, str3);
                }
                throw new IllegalArgumentException("Argument \"base_url\" is marked as non-null but was passed a null value.");
            }
            throw new IllegalArgumentException("Required argument \"base_url\" is missing and does not have an android:defaultValue");
        }
    }
}
