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

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000  2\u00020\u0001:\u0001 B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003JC\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\u0006\u0010\u001d\u001a\u00020\u001eJ\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006!"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragmentArgs;", "Landroidx/navigation/NavArgs;", "baseUrl", "", "title", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "startDate", "endDate", "(Ljava/lang/String;Ljava/lang/String;Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;Ljava/lang/String;Ljava/lang/String;)V", "getBaseUrl", "()Ljava/lang/String;", "getEndDate", "getStartDate", "getStyleManager", "()Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "getTitle", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toString", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EventsFragmentArgs.kt */
public final class EventsFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String baseUrl;
    private final String endDate;
    private final String startDate;
    private final StyleManager styleManager;
    private final String title;

    public static /* synthetic */ EventsFragmentArgs copy$default(EventsFragmentArgs eventsFragmentArgs, String str, String str2, StyleManager styleManager2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = eventsFragmentArgs.baseUrl;
        }
        if ((i & 2) != 0) {
            str2 = eventsFragmentArgs.title;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            styleManager2 = eventsFragmentArgs.styleManager;
        }
        StyleManager styleManager3 = styleManager2;
        if ((i & 8) != 0) {
            str3 = eventsFragmentArgs.startDate;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            str4 = eventsFragmentArgs.endDate;
        }
        return eventsFragmentArgs.copy(str, str5, styleManager3, str6, str4);
    }

    @JvmStatic
    public static final EventsFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
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

    public final EventsFragmentArgs copy(String str, String str2, StyleManager styleManager2, String str3, String str4) {
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        return new EventsFragmentArgs(str, str2, styleManager2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EventsFragmentArgs)) {
            return false;
        }
        EventsFragmentArgs eventsFragmentArgs = (EventsFragmentArgs) obj;
        return Intrinsics.areEqual((Object) this.baseUrl, (Object) eventsFragmentArgs.baseUrl) && Intrinsics.areEqual((Object) this.title, (Object) eventsFragmentArgs.title) && Intrinsics.areEqual((Object) this.styleManager, (Object) eventsFragmentArgs.styleManager) && Intrinsics.areEqual((Object) this.startDate, (Object) eventsFragmentArgs.startDate) && Intrinsics.areEqual((Object) this.endDate, (Object) eventsFragmentArgs.endDate);
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
        return "EventsFragmentArgs(baseUrl=" + this.baseUrl + ", title=" + this.title + ", styleManager=" + this.styleManager + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ")";
    }

    public EventsFragmentArgs(String str, String str2, StyleManager styleManager2, String str3, String str4) {
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
    public /* synthetic */ EventsFragmentArgs(String str, String str2, StyleManager styleManager2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
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

    public final Bundle toBundle() {
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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragmentArgs$Companion;", "", "()V", "fromBundle", "Lcom/webmd/medscape/live/explorelivevents/ui/fragments/EventsFragmentArgs;", "bundle", "Landroid/os/Bundle;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EventsFragmentArgs.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final EventsFragmentArgs fromBundle(Bundle bundle) {
            String str;
            StyleManager styleManager;
            String str2;
            String str3;
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(EventsFragmentArgs.class.getClassLoader());
            if (bundle.containsKey("base_url")) {
                String string = bundle.getString("base_url");
                if (string != null) {
                    if (bundle.containsKey("title")) {
                        str = bundle.getString("title");
                    } else {
                        str = null;
                    }
                    String str4 = str;
                    if (!bundle.containsKey("style_manager")) {
                        styleManager = null;
                    } else if (Parcelable.class.isAssignableFrom(StyleManager.class) || Serializable.class.isAssignableFrom(StyleManager.class)) {
                        styleManager = (StyleManager) bundle.get("style_manager");
                    } else {
                        throw new UnsupportedOperationException(StyleManager.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                    }
                    StyleManager styleManager2 = styleManager;
                    if (bundle.containsKey(FirebaseAnalytics.Param.START_DATE)) {
                        str2 = bundle.getString(FirebaseAnalytics.Param.START_DATE);
                    } else {
                        str2 = null;
                    }
                    String str5 = str2;
                    if (bundle.containsKey(FirebaseAnalytics.Param.END_DATE)) {
                        str3 = bundle.getString(FirebaseAnalytics.Param.END_DATE);
                    } else {
                        str3 = null;
                    }
                    return new EventsFragmentArgs(string, str4, styleManager2, str5, str3);
                }
                throw new IllegalArgumentException("Argument \"base_url\" is marked as non-null but was passed a null value.");
            }
            throw new IllegalArgumentException("Required argument \"base_url\" is missing and does not have an android:defaultValue");
        }
    }
}
