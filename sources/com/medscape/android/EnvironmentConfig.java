package com.medscape.android;

import com.wbmd.environment.EnvironmentConstants;
import com.wbmd.environment.IEnvironmentConfig;
import com.wbmd.environment.data.Environment;
import com.wbmd.environment.data.Module;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import webmd.com.environmentswitcher.EnvironmentManagerData;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006H\u0002J\u0018\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006H\u0002J\u0018\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006H\u0002J\u0018\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006H\u0002J\u0018\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0004j\b\u0012\u0004\u0012\u00020\u000b`\u0006H\u0002J\u0018\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006H\u0002J\u0018\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\u0004j\b\u0012\u0004\u0012\u00020\u000b`\u0006H\u0016¨\u0006\u000f"}, d2 = {"Lcom/medscape/android/EnvironmentConfig;", "Lcom/wbmd/environment/IEnvironmentConfig;", "()V", "generateAdEnvironments", "Ljava/util/ArrayList;", "Lcom/wbmd/environment/data/Environment;", "Lkotlin/collections/ArrayList;", "generateContentEnvironments", "generateFeedEnvironments", "generateLiveEventEnvironments", "generateModules", "Lcom/wbmd/environment/data/Module;", "generateServiceEnvironments", "getModules", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EnvironmentConfig.kt */
public final class EnvironmentConfig implements IEnvironmentConfig {
    public static final String API_LIVE_EVENTS_PRODUCTION = "https://www.medscapelive.com/api/";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String ENV_AD_QA = "QA (2)";
    public static final String ENV_AD_STAGING = "STAGING (1)";
    public static final String ENV_BIZDEV = "BIZDEV";
    public static final String ENV_DEV = "DEV";
    public static final String ENV_DEV01 = "DEV01";
    public static final String ENV_PERF = "PERF";
    public static final String ENV_PROD = "PROD";
    public static final String ENV_QA = "QA";
    public static final String ENV_QA00 = "QA00";
    public static final String ENV_QA01 = "QA01";
    public static final String ENV_QA02 = "QA02";
    public static final String ENV_SANDBOX = "SANDBOX";
    public static final String ENV_STAGING = "STAGING";
    public static final String MODULE_LIVE_EVENTS = "module_live_events";

    public ArrayList<Module> getModules() {
        return generateModules();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/medscape/android/EnvironmentConfig$Companion;", "", "()V", "API_LIVE_EVENTS_PRODUCTION", "", "ENV_AD_QA", "ENV_AD_STAGING", "ENV_BIZDEV", "ENV_DEV", "ENV_DEV01", "ENV_PERF", "ENV_PROD", "ENV_QA", "ENV_QA00", "ENV_QA01", "ENV_QA02", "ENV_SANDBOX", "ENV_STAGING", "MODULE_LIVE_EVENTS", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EnvironmentConfig.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final ArrayList<Module> generateModules() {
        ArrayList<Module> arrayList = new ArrayList<>();
        String string = MedscapeApplication.get().getString(R.string.content_env);
        Intrinsics.checkNotNullExpressionValue(string, "MedscapeApplication.get(…ing(R.string.content_env)");
        arrayList.add(new Module(EnvironmentConstants.MODULE_CONTENT, string, generateContentEnvironments()));
        String string2 = MedscapeApplication.get().getString(R.string.feed_env);
        Intrinsics.checkNotNullExpressionValue(string2, "MedscapeApplication.get(…String(R.string.feed_env)");
        arrayList.add(new Module(EnvironmentConstants.MODULE_FEED, string2, generateFeedEnvironments()));
        String string3 = MedscapeApplication.get().getString(R.string.service_env);
        Intrinsics.checkNotNullExpressionValue(string3, "MedscapeApplication.get(…ing(R.string.service_env)");
        arrayList.add(new Module(EnvironmentConstants.MODULE_SERVICE, string3, generateServiceEnvironments()));
        String string4 = MedscapeApplication.get().getString(R.string.ad_env);
        Intrinsics.checkNotNullExpressionValue(string4, "MedscapeApplication.get(…etString(R.string.ad_env)");
        arrayList.add(new Module(EnvironmentConstants.MODULE_AD, string4, generateAdEnvironments()));
        String string5 = MedscapeApplication.get().getString(R.string.live_env);
        Intrinsics.checkNotNullExpressionValue(string5, "MedscapeApplication.get(…String(R.string.live_env)");
        arrayList.add(new Module(MODULE_LIVE_EVENTS, string5, generateLiveEventEnvironments()));
        return arrayList;
    }

    private final ArrayList<Environment> generateContentEnvironments() {
        ArrayList<Environment> arrayList = new ArrayList<>();
        Map<String, String> mapForEnvironment = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_PROD);
        if (mapForEnvironment != null) {
            arrayList.add(new Environment(EnvironmentConstants.PRODUCTION, ENV_PROD, "", true, (HashMap) mapForEnvironment));
            arrayList.add(new Environment(EnvironmentConstants.QA, ENV_QA, "", false, (HashMap) null, 24, (DefaultConstructorMarker) null));
            arrayList.add(new Environment(EnvironmentConstants.SANDBOX, ENV_SANDBOX, "", false, (HashMap) null, 24, (DefaultConstructorMarker) null));
            arrayList.add(new Environment(EnvironmentConstants.SANDBOX, ENV_BIZDEV, "", false, (HashMap) null, 24, (DefaultConstructorMarker) null));
            return arrayList;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
    }

    private final ArrayList<Environment> generateFeedEnvironments() {
        ArrayList<Environment> arrayList = new ArrayList<>();
        Map<String, String> mapForEnvironment = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_PROD);
        if (mapForEnvironment != null) {
            arrayList.add(new Environment(EnvironmentConstants.PRODUCTION, ENV_PROD, "", true, (HashMap) mapForEnvironment));
            Map<String, String> mapForEnvironment2 = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_QA00);
            if (mapForEnvironment2 != null) {
                arrayList.add(new Environment(EnvironmentConstants.QA00, ENV_QA00, "", false, (HashMap) mapForEnvironment2, 8, (DefaultConstructorMarker) null));
                Map<String, String> mapForEnvironment3 = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_QA01);
                if (mapForEnvironment3 != null) {
                    arrayList.add(new Environment(EnvironmentConstants.QA01, ENV_QA01, "", false, (HashMap) mapForEnvironment3, 8, (DefaultConstructorMarker) null));
                    Map<String, String> mapForEnvironment4 = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_QA02);
                    if (mapForEnvironment4 != null) {
                        arrayList.add(new Environment(EnvironmentConstants.QA02, ENV_QA02, "", false, (HashMap) mapForEnvironment4, 8, (DefaultConstructorMarker) null));
                        arrayList.add(new Environment(EnvironmentConstants.STAGING, ENV_STAGING, "", false, (HashMap) null, 24, (DefaultConstructorMarker) null));
                        arrayList.add(new Environment(EnvironmentConstants.DEV, ENV_DEV, "", false, (HashMap) null, 24, (DefaultConstructorMarker) null));
                        Map<String, String> mapForEnvironment5 = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_DEV01);
                        if (mapForEnvironment5 != null) {
                            arrayList.add(new Environment(EnvironmentConstants.DEV01, ENV_DEV01, "", false, (HashMap) mapForEnvironment5, 8, (DefaultConstructorMarker) null));
                            return arrayList;
                        }
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
    }

    private final ArrayList<Environment> generateServiceEnvironments() {
        ArrayList<Environment> arrayList = new ArrayList<>();
        Map<String, String> mapForEnvironment = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_PROD);
        if (mapForEnvironment != null) {
            arrayList.add(new Environment(EnvironmentConstants.PRODUCTION, ENV_PROD, "", true, (HashMap) mapForEnvironment));
            Map<String, String> mapForEnvironment2 = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_QA00);
            if (mapForEnvironment2 != null) {
                arrayList.add(new Environment(EnvironmentConstants.QA00, ENV_QA00, "", false, (HashMap) mapForEnvironment2, 8, (DefaultConstructorMarker) null));
                Map<String, String> mapForEnvironment3 = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_QA01);
                if (mapForEnvironment3 != null) {
                    arrayList.add(new Environment(EnvironmentConstants.QA01, ENV_QA01, "", false, (HashMap) mapForEnvironment3, 8, (DefaultConstructorMarker) null));
                    Map<String, String> mapForEnvironment4 = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_QA02);
                    if (mapForEnvironment4 != null) {
                        arrayList.add(new Environment(EnvironmentConstants.QA02, ENV_QA02, "", false, (HashMap) mapForEnvironment4, 8, (DefaultConstructorMarker) null));
                        Map<String, String> mapForEnvironment5 = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_PERF);
                        if (mapForEnvironment5 != null) {
                            arrayList.add(new Environment(EnvironmentConstants.PERF, ENV_PERF, "", false, (HashMap) mapForEnvironment5, 8, (DefaultConstructorMarker) null));
                            Map<String, String> mapForEnvironment6 = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_DEV01);
                            if (mapForEnvironment6 != null) {
                                arrayList.add(new Environment(EnvironmentConstants.DEV01, ENV_DEV01, "", false, (HashMap) mapForEnvironment6, 8, (DefaultConstructorMarker) null));
                                return arrayList;
                            }
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
                        }
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
                    }
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
    }

    private final ArrayList<Environment> generateAdEnvironments() {
        ArrayList<Environment> arrayList = new ArrayList<>();
        Map<String, String> mapForEnvironment = new EnvironmentManagerData(MedscapeApplication.get()).getMapForEnvironment(ENV_PROD);
        if (mapForEnvironment != null) {
            arrayList.add(new Environment(EnvironmentConstants.PRODUCTION, ENV_PROD, "", true, (HashMap) mapForEnvironment));
            arrayList.add(new Environment(EnvironmentConstants.STAGING, ENV_AD_STAGING, "", false, (HashMap) null, 24, (DefaultConstructorMarker) null));
            arrayList.add(new Environment(EnvironmentConstants.QA, ENV_AD_QA, "", false, (HashMap) null, 24, (DefaultConstructorMarker) null));
            return arrayList;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.String> /* = java.util.HashMap<kotlin.String, kotlin.String> */");
    }

    private final ArrayList<Environment> generateLiveEventEnvironments() {
        ArrayList<Environment> arrayList = new ArrayList<>();
        arrayList.add(new Environment(EnvironmentConstants.PRODUCTION, ENV_PROD, "https://www.medscapelive.com/api/", true, (HashMap) null, 16, (DefaultConstructorMarker) null));
        arrayList.add(new Environment(EnvironmentConstants.DEVINT, ENV_DEV, "https://www.devint.medscapelive.com/api/", false, (HashMap) null, 24, (DefaultConstructorMarker) null));
        arrayList.add(new Environment(EnvironmentConstants.STAGING, ENV_STAGING, "https://www.staging.medscapelive.com/api/", false, (HashMap) null, 24, (DefaultConstructorMarker) null));
        arrayList.add(new Environment(EnvironmentConstants.QA, ENV_QA, "https://www.qa.medscapelive.com/api/", false, (HashMap) null, 24, (DefaultConstructorMarker) null));
        return arrayList;
    }
}
