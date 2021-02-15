package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavDirections;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/FilterSelectionFragmentDirections;", "", "()V", "ActionSpecialtySelectionToResults", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FilterSelectionFragmentDirections.kt */
public final class FilterSelectionFragmentDirections {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003JC\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\t\u0010\u001f\u001a\u00020\u001cHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006!"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/FilterSelectionFragmentDirections$ActionSpecialtySelectionToResults;", "Landroidx/navigation/NavDirections;", "baseUrl", "", "title", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "startDate", "endDate", "(Ljava/lang/String;Ljava/lang/String;Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;Ljava/lang/String;Ljava/lang/String;)V", "getBaseUrl", "()Ljava/lang/String;", "getEndDate", "getStartDate", "getStyleManager", "()Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "getTitle", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "getActionId", "", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FilterSelectionFragmentDirections.kt */
    private static final class ActionSpecialtySelectionToResults implements NavDirections {
        private final String baseUrl;
        private final String endDate;
        private final String startDate;
        private final StyleManager styleManager;
        private final String title;

        public static /* synthetic */ ActionSpecialtySelectionToResults copy$default(ActionSpecialtySelectionToResults actionSpecialtySelectionToResults, String str, String str2, StyleManager styleManager2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionSpecialtySelectionToResults.baseUrl;
            }
            if ((i & 2) != 0) {
                str2 = actionSpecialtySelectionToResults.title;
            }
            String str5 = str2;
            if ((i & 4) != 0) {
                styleManager2 = actionSpecialtySelectionToResults.styleManager;
            }
            StyleManager styleManager3 = styleManager2;
            if ((i & 8) != 0) {
                str3 = actionSpecialtySelectionToResults.startDate;
            }
            String str6 = str3;
            if ((i & 16) != 0) {
                str4 = actionSpecialtySelectionToResults.endDate;
            }
            return actionSpecialtySelectionToResults.copy(str, str5, styleManager3, str6, str4);
        }

        public final String component1() {
            return this.baseUrl;
        }

        public final String component2() {
            return this.title;
        }

        public final StyleManager component3() {
            return this.styleManager;
        }

        public final String component4() {
            return this.startDate;
        }

        public final String component5() {
            return this.endDate;
        }

        public final ActionSpecialtySelectionToResults copy(String str, String str2, StyleManager styleManager2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "baseUrl");
            return new ActionSpecialtySelectionToResults(str, str2, styleManager2, str3, str4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ActionSpecialtySelectionToResults)) {
                return false;
            }
            ActionSpecialtySelectionToResults actionSpecialtySelectionToResults = (ActionSpecialtySelectionToResults) obj;
            return Intrinsics.areEqual((Object) this.baseUrl, (Object) actionSpecialtySelectionToResults.baseUrl) && Intrinsics.areEqual((Object) this.title, (Object) actionSpecialtySelectionToResults.title) && Intrinsics.areEqual((Object) this.styleManager, (Object) actionSpecialtySelectionToResults.styleManager) && Intrinsics.areEqual((Object) this.startDate, (Object) actionSpecialtySelectionToResults.startDate) && Intrinsics.areEqual((Object) this.endDate, (Object) actionSpecialtySelectionToResults.endDate);
        }

        public int hashCode() {
            String str = this.baseUrl;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.title;
            int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            StyleManager styleManager2 = this.styleManager;
            int hashCode3 = (hashCode2 + (styleManager2 != null ? styleManager2.hashCode() : 0)) * 31;
            String str3 = this.startDate;
            int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.endDate;
            if (str4 != null) {
                i = str4.hashCode();
            }
            return hashCode4 + i;
        }

        public String toString() {
            return "ActionSpecialtySelectionToResults(baseUrl=" + this.baseUrl + ", title=" + this.title + ", styleManager=" + this.styleManager + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ")";
        }

        public ActionSpecialtySelectionToResults(String str, String str2, StyleManager styleManager2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "baseUrl");
            this.baseUrl = str;
            this.title = str2;
            this.styleManager = styleManager2;
            this.startDate = str3;
            this.endDate = str4;
        }

        public final String getBaseUrl() {
            return this.baseUrl;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ActionSpecialtySelectionToResults(String str, String str2, StyleManager styleManager2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : styleManager2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4);
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

        public int getActionId() {
            return R.id.action_specialty_selection_to_results;
        }

        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("base_url", this.baseUrl);
            bundle.putString("title", this.title);
            if (Parcelable.class.isAssignableFrom(StyleManager.class)) {
                bundle.putParcelable("style_manager", this.styleManager);
            } else if (Serializable.class.isAssignableFrom(StyleManager.class)) {
                bundle.putSerializable("style_manager", (Serializable) this.styleManager);
            }
            bundle.putString(FirebaseAnalytics.Param.START_DATE, this.startDate);
            bundle.putString(FirebaseAnalytics.Param.END_DATE, this.endDate);
            return bundle;
        }
    }

    private FilterSelectionFragmentDirections() {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J>\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006¨\u0006\f"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/FilterSelectionFragmentDirections$Companion;", "", "()V", "actionSpecialtySelectionToResults", "Landroidx/navigation/NavDirections;", "baseUrl", "", "title", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "startDate", "endDate", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FilterSelectionFragmentDirections.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ NavDirections actionSpecialtySelectionToResults$default(Companion companion, String str, String str2, StyleManager styleManager, String str3, String str4, int i, Object obj) {
            if ((i & 2) != 0) {
                str2 = null;
            }
            String str5 = str2;
            if ((i & 4) != 0) {
                styleManager = null;
            }
            StyleManager styleManager2 = styleManager;
            if ((i & 8) != 0) {
                str3 = null;
            }
            String str6 = str3;
            if ((i & 16) != 0) {
                str4 = null;
            }
            return companion.actionSpecialtySelectionToResults(str, str5, styleManager2, str6, str4);
        }

        public final NavDirections actionSpecialtySelectionToResults(String str, String str2, StyleManager styleManager, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "baseUrl");
            return new ActionSpecialtySelectionToResults(str, str2, styleManager, str3, str4);
        }
    }
}
