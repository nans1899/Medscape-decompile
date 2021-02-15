package com.wbmd.decisionpoint;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/wbmd/decisionpoint/Constants;", "", "()V", "ALL_DECISIONPOINTS_URL", "", "CONTRIBUTORS_URL", "DECISION_POINT_PREFERENCES", "PREF_KEY_CACHED_DECISION_POINT", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: Constants.kt */
public final class Constants {
    public static final String ALL_DECISIONPOINTS_URL = "https://img.medscapestatic.com/medscape-files/dp-contributors/alldps.json";
    public static final String CONTRIBUTORS_URL = "https://img.medscapestatic.com/medscape-files/dp-contributors/odp.json";
    public static final String DECISION_POINT_PREFERENCES = "decision_point_prefs";
    public static final Constants INSTANCE = new Constants();
    public static final String PREF_KEY_CACHED_DECISION_POINT = "cached_decision_point_key";

    private Constants() {
    }
}
