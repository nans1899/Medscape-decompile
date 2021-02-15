package com.webmd.medscape.live.explorelivevents.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\"\n\u0002\b\u0006\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u001c\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0016J$\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u00122\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J\u001a\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\fH\u0016J\u001e\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;", "Lcom/webmd/medscape/live/explorelivevents/persistence/Preferences;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "editor", "Landroid/content/SharedPreferences$Editor;", "sharedPrefs", "Landroid/content/SharedPreferences;", "clear", "", "key", "", "getBoolean", "", "defaultValue", "getString", "getStringArray", "", "", "saveBoolean", "value", "saveString", "saveStringSet", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SharedPreferencesManager.kt */
public final class SharedPreferencesManager implements Preferences {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static SharedPreferencesManager INSTANCE = null;
    public static final String PREFS_NAME = "MEDSCAPE_LIVE_PREFERENCES";
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPrefs;

    public SharedPreferencesManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
        this.sharedPrefs = sharedPreferences;
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Intrinsics.checkNotNullExpressionValue(edit, "sharedPrefs.edit()");
        this.editor = edit;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager$Companion;", "", "()V", "INSTANCE", "Lcom/webmd/medscape/live/explorelivevents/persistence/SharedPreferencesManager;", "PREFS_NAME", "", "getInstance", "context", "Landroid/content/Context;", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SharedPreferencesManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SharedPreferencesManager getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (SharedPreferencesManager.INSTANCE == null) {
                synchronized (Reflection.getOrCreateKotlinClass(SharedPreferencesManager.class)) {
                    if (SharedPreferencesManager.INSTANCE == null) {
                        SharedPreferencesManager.INSTANCE = new SharedPreferencesManager(context);
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
            SharedPreferencesManager access$getINSTANCE$cp = SharedPreferencesManager.INSTANCE;
            if (access$getINSTANCE$cp != null) {
                return access$getINSTANCE$cp;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.webmd.medscape.live.explorelivevents.persistence.SharedPreferencesManager");
        }
    }

    public void saveBoolean(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "key");
        SharedPreferences.Editor editor2 = this.editor;
        editor2.putBoolean(str, z);
        editor2.apply();
    }

    public void saveString(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        SharedPreferences.Editor editor2 = this.editor;
        editor2.putString(str, str2);
        editor2.apply();
    }

    public void saveStringSet(String str, Set<String> set) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(set, "value");
        SharedPreferences.Editor editor2 = this.editor;
        editor2.putStringSet(str, set);
        editor2.apply();
    }

    public boolean getBoolean(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "key");
        return this.sharedPrefs.getBoolean(str, z);
    }

    public String getString(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        return this.sharedPrefs.getString(str, str2);
    }

    public List<String> getStringArray(String str, Set<String> set) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(set, "defaultValue");
        Set<String> stringSet = this.sharedPrefs.getStringSet(str, set);
        if (stringSet != null) {
            return CollectionsKt.toList(stringSet);
        }
        return CollectionsKt.emptyList();
    }

    public final void clear(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        this.sharedPrefs.edit().remove(str).apply();
    }
}
