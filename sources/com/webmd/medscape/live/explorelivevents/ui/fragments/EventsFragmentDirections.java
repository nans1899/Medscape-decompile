package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.webmd.medscape.live.explorelivevents.R;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00052\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragmentDirections;", "", "()V", "ActionEventsToFilterSelection", "ActionEventsToLiveEvent", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EventsFragmentDirections.kt */
public final class EventsFragmentDirections {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001BQ\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003JW\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010 \u001a\u00020\u00052\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\b\u0010#\u001a\u00020\u0003H\u0016J\b\u0010$\u001a\u00020%H\u0016J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0011R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006("}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragmentDirections$ActionEventsToFilterSelection;", "Landroidx/navigation/NavDirections;", "viewType", "", "isFromExplore", "", "baseUrl", "", "title", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "startDate", "endDate", "(IZLjava/lang/String;Ljava/lang/String;Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;Ljava/lang/String;Ljava/lang/String;)V", "getBaseUrl", "()Ljava/lang/String;", "getEndDate", "()Z", "getStartDate", "getStyleManager", "()Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "getTitle", "getViewType", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "", "getActionId", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EventsFragmentDirections.kt */
    private static final class ActionEventsToFilterSelection implements NavDirections {
        private final String baseUrl;
        private final String endDate;
        private final boolean isFromExplore;
        private final String startDate;
        private final StyleManager styleManager;
        private final String title;
        private final int viewType;

        public static /* synthetic */ ActionEventsToFilterSelection copy$default(ActionEventsToFilterSelection actionEventsToFilterSelection, int i, boolean z, String str, String str2, StyleManager styleManager2, String str3, String str4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = actionEventsToFilterSelection.viewType;
            }
            if ((i2 & 2) != 0) {
                z = actionEventsToFilterSelection.isFromExplore;
            }
            boolean z2 = z;
            if ((i2 & 4) != 0) {
                str = actionEventsToFilterSelection.baseUrl;
            }
            String str5 = str;
            if ((i2 & 8) != 0) {
                str2 = actionEventsToFilterSelection.title;
            }
            String str6 = str2;
            if ((i2 & 16) != 0) {
                styleManager2 = actionEventsToFilterSelection.styleManager;
            }
            StyleManager styleManager3 = styleManager2;
            if ((i2 & 32) != 0) {
                str3 = actionEventsToFilterSelection.startDate;
            }
            String str7 = str3;
            if ((i2 & 64) != 0) {
                str4 = actionEventsToFilterSelection.endDate;
            }
            return actionEventsToFilterSelection.copy(i, z2, str5, str6, styleManager3, str7, str4);
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

        public final ActionEventsToFilterSelection copy(int i, boolean z, String str, String str2, StyleManager styleManager2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "baseUrl");
            return new ActionEventsToFilterSelection(i, z, str, str2, styleManager2, str3, str4);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ActionEventsToFilterSelection)) {
                return false;
            }
            ActionEventsToFilterSelection actionEventsToFilterSelection = (ActionEventsToFilterSelection) obj;
            return this.viewType == actionEventsToFilterSelection.viewType && this.isFromExplore == actionEventsToFilterSelection.isFromExplore && Intrinsics.areEqual((Object) this.baseUrl, (Object) actionEventsToFilterSelection.baseUrl) && Intrinsics.areEqual((Object) this.title, (Object) actionEventsToFilterSelection.title) && Intrinsics.areEqual((Object) this.styleManager, (Object) actionEventsToFilterSelection.styleManager) && Intrinsics.areEqual((Object) this.startDate, (Object) actionEventsToFilterSelection.startDate) && Intrinsics.areEqual((Object) this.endDate, (Object) actionEventsToFilterSelection.endDate);
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
            return "ActionEventsToFilterSelection(viewType=" + this.viewType + ", isFromExplore=" + this.isFromExplore + ", baseUrl=" + this.baseUrl + ", title=" + this.title + ", styleManager=" + this.styleManager + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ")";
        }

        public ActionEventsToFilterSelection(int i, boolean z, String str, String str2, StyleManager styleManager2, String str3, String str4) {
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
        public /* synthetic */ ActionEventsToFilterSelection(int i, boolean z, String str, String str2, StyleManager styleManager2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
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

        public int getActionId() {
            return R.id.action_events_to_filter_selection;
        }

        public Bundle getArguments() {
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
    }

    private EventsFragmentDirections() {
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragmentDirections$ActionEventsToLiveEvent;", "Landroidx/navigation/NavDirections;", "eventUrl", "", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "(Ljava/lang/String;Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;)V", "getEventUrl", "()Ljava/lang/String;", "getStyleManager", "()Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "component1", "component2", "copy", "equals", "", "other", "", "getActionId", "", "getArguments", "Landroid/os/Bundle;", "hashCode", "toString", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EventsFragmentDirections.kt */
    private static final class ActionEventsToLiveEvent implements NavDirections {
        private final String eventUrl;
        private final StyleManager styleManager;

        public static /* synthetic */ ActionEventsToLiveEvent copy$default(ActionEventsToLiveEvent actionEventsToLiveEvent, String str, StyleManager styleManager2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = actionEventsToLiveEvent.eventUrl;
            }
            if ((i & 2) != 0) {
                styleManager2 = actionEventsToLiveEvent.styleManager;
            }
            return actionEventsToLiveEvent.copy(str, styleManager2);
        }

        public final String component1() {
            return this.eventUrl;
        }

        public final StyleManager component2() {
            return this.styleManager;
        }

        public final ActionEventsToLiveEvent copy(String str, StyleManager styleManager2) {
            Intrinsics.checkNotNullParameter(str, "eventUrl");
            return new ActionEventsToLiveEvent(str, styleManager2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ActionEventsToLiveEvent)) {
                return false;
            }
            ActionEventsToLiveEvent actionEventsToLiveEvent = (ActionEventsToLiveEvent) obj;
            return Intrinsics.areEqual((Object) this.eventUrl, (Object) actionEventsToLiveEvent.eventUrl) && Intrinsics.areEqual((Object) this.styleManager, (Object) actionEventsToLiveEvent.styleManager);
        }

        public int hashCode() {
            String str = this.eventUrl;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            StyleManager styleManager2 = this.styleManager;
            if (styleManager2 != null) {
                i = styleManager2.hashCode();
            }
            return hashCode + i;
        }

        public String toString() {
            return "ActionEventsToLiveEvent(eventUrl=" + this.eventUrl + ", styleManager=" + this.styleManager + ")";
        }

        public ActionEventsToLiveEvent(String str, StyleManager styleManager2) {
            Intrinsics.checkNotNullParameter(str, "eventUrl");
            this.eventUrl = str;
            this.styleManager = styleManager2;
        }

        public final String getEventUrl() {
            return this.eventUrl;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ActionEventsToLiveEvent(String str, StyleManager styleManager2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i & 2) != 0 ? null : styleManager2);
        }

        public final StyleManager getStyleManager() {
            return this.styleManager;
        }

        public int getActionId() {
            return R.id.action_events_to_live_event;
        }

        public Bundle getArguments() {
            Bundle bundle = new Bundle();
            bundle.putString("event_url", this.eventUrl);
            if (Parcelable.class.isAssignableFrom(StyleManager.class)) {
                bundle.putParcelable("style_manager", this.styleManager);
            } else if (Serializable.class.isAssignableFrom(StyleManager.class)) {
                bundle.putSerializable("style_manager", (Serializable) this.styleManager);
            }
            return bundle;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004JR\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000bJ\u001a\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¨\u0006\u0013"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragmentDirections$Companion;", "", "()V", "actionEventsToExplore", "Landroidx/navigation/NavDirections;", "actionEventsToFilterSelection", "viewType", "", "isFromExplore", "", "baseUrl", "", "title", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "startDate", "endDate", "actionEventsToLiveEvent", "eventUrl", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EventsFragmentDirections.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ NavDirections actionEventsToFilterSelection$default(Companion companion, int i, boolean z, String str, String str2, StyleManager styleManager, String str3, String str4, int i2, Object obj) {
            return companion.actionEventsToFilterSelection((i2 & 1) != 0 ? 1 : i, (i2 & 2) != 0 ? false : z, str, (i2 & 8) != 0 ? null : str2, (i2 & 16) != 0 ? null : styleManager, (i2 & 32) != 0 ? null : str3, (i2 & 64) != 0 ? null : str4);
        }

        public final NavDirections actionEventsToFilterSelection(int i, boolean z, String str, String str2, StyleManager styleManager, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "baseUrl");
            return new ActionEventsToFilterSelection(i, z, str, str2, styleManager, str3, str4);
        }

        public static /* synthetic */ NavDirections actionEventsToLiveEvent$default(Companion companion, String str, StyleManager styleManager, int i, Object obj) {
            if ((i & 2) != 0) {
                styleManager = null;
            }
            return companion.actionEventsToLiveEvent(str, styleManager);
        }

        public final NavDirections actionEventsToLiveEvent(String str, StyleManager styleManager) {
            Intrinsics.checkNotNullParameter(str, "eventUrl");
            return new ActionEventsToLiveEvent(str, styleManager);
        }

        public final NavDirections actionEventsToExplore() {
            return new ActionOnlyNavDirections(R.id.action_events_to_explore);
        }
    }
}
