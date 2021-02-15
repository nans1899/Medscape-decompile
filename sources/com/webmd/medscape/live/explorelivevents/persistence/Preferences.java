package com.webmd.medscape.live.explorelivevents.persistence;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0003H&J\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J$\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\nH&J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0003H&J\u001a\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u0005H&J\u001e\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\nH&¨\u0006\u0011"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/persistence/Preferences;", "", "getBoolean", "", "key", "", "defaultValue", "getString", "getStringArray", "", "", "saveBoolean", "", "value", "saveString", "saveStringSet", "Companion", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Preferences.kt */
public interface Preferences {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String KEY_ALL_LOCATIONS_SELECTED = "KEY_ALL_LOCATIONS_SELECTED";
    public static final String KEY_ALL_SPECIALTIES_SELECTED = "KEY_ALL_SPECIALTIES_SELECTED";
    public static final String KEY_FILTERS_CACHE = "filters_cache";
    public static final String KEY_LOCATIONS_CACHE = "locations_cache";
    public static final String KEY_LOCATION_PERMISSION = "KEY_LOCATION_PERMISSION";
    public static final String KEY_PREFS_BASE_URL = "KEY_PREFS_BASE_URL";
    public static final String KEY_PREFS_COOKIE = "KEY_PREFS_COOKIE_STRING";
    public static final String KEY_PREFS_LOCATION = "KEY_PREFS_LOCATION";
    public static final String KEY_PREFS_SPECIALTY = "KEY_PREFS_SPECIALTY";
    public static final String KEY_SELECTED_LOCATIONS = "KEY_SELECTED_LOCATIONS";
    public static final String KEY_SELECTED_SPECIALTIES = "KEY_SELECTED_SPECIALTIES";
    public static final String KEY_SPECIALTIES_CACHE = "specialties_cache";

    boolean getBoolean(String str, boolean z);

    String getString(String str, String str2);

    List<String> getStringArray(String str, Set<String> set);

    void saveBoolean(String str, boolean z);

    void saveString(String str, String str2);

    void saveStringSet(String str, Set<String> set);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/persistence/Preferences$Companion;", "", "()V", "KEY_ALL_LOCATIONS_SELECTED", "", "KEY_ALL_SPECIALTIES_SELECTED", "KEY_FILTERS_CACHE", "KEY_LOCATIONS_CACHE", "KEY_LOCATION_PERMISSION", "KEY_PREFS_BASE_URL", "KEY_PREFS_COOKIE", "KEY_PREFS_LOCATION", "KEY_PREFS_SPECIALTY", "KEY_SELECTED_LOCATIONS", "KEY_SELECTED_SPECIALTIES", "KEY_SPECIALTIES_CACHE", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: Preferences.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String KEY_ALL_LOCATIONS_SELECTED = "KEY_ALL_LOCATIONS_SELECTED";
        public static final String KEY_ALL_SPECIALTIES_SELECTED = "KEY_ALL_SPECIALTIES_SELECTED";
        public static final String KEY_FILTERS_CACHE = "filters_cache";
        public static final String KEY_LOCATIONS_CACHE = "locations_cache";
        public static final String KEY_LOCATION_PERMISSION = "KEY_LOCATION_PERMISSION";
        public static final String KEY_PREFS_BASE_URL = "KEY_PREFS_BASE_URL";
        public static final String KEY_PREFS_COOKIE = "KEY_PREFS_COOKIE_STRING";
        public static final String KEY_PREFS_LOCATION = "KEY_PREFS_LOCATION";
        public static final String KEY_PREFS_SPECIALTY = "KEY_PREFS_SPECIALTY";
        public static final String KEY_SELECTED_LOCATIONS = "KEY_SELECTED_LOCATIONS";
        public static final String KEY_SELECTED_SPECIALTIES = "KEY_SELECTED_SPECIALTIES";
        public static final String KEY_SPECIALTIES_CACHE = "specialties_cache";

        private Companion() {
        }
    }
}
