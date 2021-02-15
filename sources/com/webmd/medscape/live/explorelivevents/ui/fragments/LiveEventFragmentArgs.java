package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.NavArgs;
import com.webmd.medscape.live.explorelivevents.common.StyleManager;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\u0006\u0010\u0014\u001a\u00020\u0015J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/LiveEventFragmentArgs;", "Landroidx/navigation/NavArgs;", "eventUrl", "", "styleManager", "Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "(Ljava/lang/String;Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;)V", "getEventUrl", "()Ljava/lang/String;", "getStyleManager", "()Lcom/webmd/medscape/live/explorelivevents/common/StyleManager;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toString", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventFragmentArgs.kt */
public final class LiveEventFragmentArgs implements NavArgs {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String eventUrl;
    private final StyleManager styleManager;

    public static /* synthetic */ LiveEventFragmentArgs copy$default(LiveEventFragmentArgs liveEventFragmentArgs, String str, StyleManager styleManager2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = liveEventFragmentArgs.eventUrl;
        }
        if ((i & 2) != 0) {
            styleManager2 = liveEventFragmentArgs.styleManager;
        }
        return liveEventFragmentArgs.copy(str, styleManager2);
    }

    @JvmStatic
    public static final LiveEventFragmentArgs fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    public final String component1() {
        return this.eventUrl;
    }

    public final StyleManager component2() {
        return this.styleManager;
    }

    public final LiveEventFragmentArgs copy(String str, StyleManager styleManager2) {
        Intrinsics.checkNotNullParameter(str, "eventUrl");
        return new LiveEventFragmentArgs(str, styleManager2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LiveEventFragmentArgs)) {
            return false;
        }
        LiveEventFragmentArgs liveEventFragmentArgs = (LiveEventFragmentArgs) obj;
        return Intrinsics.areEqual((Object) this.eventUrl, (Object) liveEventFragmentArgs.eventUrl) && Intrinsics.areEqual((Object) this.styleManager, (Object) liveEventFragmentArgs.styleManager);
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
        return "LiveEventFragmentArgs(eventUrl=" + this.eventUrl + ", styleManager=" + this.styleManager + ")";
    }

    public LiveEventFragmentArgs(String str, StyleManager styleManager2) {
        Intrinsics.checkNotNullParameter(str, "eventUrl");
        this.eventUrl = str;
        this.styleManager = styleManager2;
    }

    public final String getEventUrl() {
        return this.eventUrl;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LiveEventFragmentArgs(String str, StyleManager styleManager2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : styleManager2);
    }

    public final StyleManager getStyleManager() {
        return this.styleManager;
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("event_url", this.eventUrl);
        if (Parcelable.class.isAssignableFrom(StyleManager.class)) {
            bundle.putParcelable("style_manager", this.styleManager);
        } else if (Serializable.class.isAssignableFrom(StyleManager.class)) {
            bundle.putSerializable("style_manager", (Serializable) this.styleManager);
        }
        return bundle;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/ui/fragments/LiveEventFragmentArgs$Companion;", "", "()V", "fromBundle", "Lcom/webmd/medscape/live/explorelivevents/ui/fragments/LiveEventFragmentArgs;", "bundle", "Landroid/os/Bundle;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: LiveEventFragmentArgs.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final LiveEventFragmentArgs fromBundle(Bundle bundle) {
            StyleManager styleManager;
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            bundle.setClassLoader(LiveEventFragmentArgs.class.getClassLoader());
            if (bundle.containsKey("event_url")) {
                String string = bundle.getString("event_url");
                if (string != null) {
                    if (!bundle.containsKey("style_manager")) {
                        styleManager = null;
                    } else if (Parcelable.class.isAssignableFrom(StyleManager.class) || Serializable.class.isAssignableFrom(StyleManager.class)) {
                        styleManager = (StyleManager) bundle.get("style_manager");
                    } else {
                        throw new UnsupportedOperationException(StyleManager.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
                    }
                    return new LiveEventFragmentArgs(string, styleManager);
                }
                throw new IllegalArgumentException("Argument \"event_url\" is marked as non-null but was passed a null value.");
            }
            throw new IllegalArgumentException("Required argument \"event_url\" is missing and does not have an android:defaultValue");
        }
    }
}
