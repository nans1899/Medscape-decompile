package com.google.firebase.abt;

import android.content.Context;
import android.os.Bundle;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.remoteconfig.internal.LegacyConfigsHandler;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
public class FirebaseABTesting {
    static final String ABT_PREFERENCES = "com.google.firebase.abt";
    static final String ORIGIN_LAST_KNOWN_START_TIME_KEY_FORMAT = "%s_lastKnownExperimentStartTime";
    private final AnalyticsConnector analyticsConnector;
    private Integer maxUserProperties = null;
    private final String originService;

    @Retention(RetentionPolicy.SOURCE)
    /* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
    public @interface OriginService {
        public static final String INAPP_MESSAGING = "fiam";
        public static final String REMOTE_CONFIG = "frc";
    }

    public FirebaseABTesting(Context context, AnalyticsConnector analyticsConnector2, String str) {
        this.analyticsConnector = analyticsConnector2;
        this.originService = str;
    }

    public void replaceAllExperiments(List<Map<String, String>> list) throws AbtException {
        throwAbtExceptionIfAnalyticsIsNull();
        if (list != null) {
            replaceAllExperimentsWith(convertMapsToExperimentInfos(list));
            return;
        }
        throw new IllegalArgumentException("The replacementExperiments list is null.");
    }

    public void removeAllExperiments() throws AbtException {
        throwAbtExceptionIfAnalyticsIsNull();
        removeExperiments(getAllExperimentsInAnalytics());
    }

    public List<AbtExperimentInfo> getAllExperiments() throws AbtException {
        throwAbtExceptionIfAnalyticsIsNull();
        List<AnalyticsConnector.ConditionalUserProperty> allExperimentsInAnalytics = getAllExperimentsInAnalytics();
        ArrayList arrayList = new ArrayList();
        for (AnalyticsConnector.ConditionalUserProperty fromConditionalUserProperty : allExperimentsInAnalytics) {
            arrayList.add(AbtExperimentInfo.fromConditionalUserProperty(fromConditionalUserProperty));
        }
        return arrayList;
    }

    public void reportActiveExperiment(AbtExperimentInfo abtExperimentInfo) throws AbtException {
        throwAbtExceptionIfAnalyticsIsNull();
        AbtExperimentInfo.validateAbtExperimentInfo(abtExperimentInfo);
        ArrayList arrayList = new ArrayList();
        Map<String, String> stringMap = abtExperimentInfo.toStringMap();
        stringMap.remove(LegacyConfigsHandler.EXPERIMENT_TRIGGER_EVENT_KEY);
        arrayList.add(AbtExperimentInfo.fromMap(stringMap));
        addExperiments(arrayList);
    }

    public void validateRunningExperiments(List<AbtExperimentInfo> list) throws AbtException {
        throwAbtExceptionIfAnalyticsIsNull();
        HashSet hashSet = new HashSet();
        for (AbtExperimentInfo experimentId : list) {
            hashSet.add(experimentId.getExperimentId());
        }
        removeExperiments(getExperimentsToRemove(getAllExperimentsInAnalytics(), hashSet));
    }

    private void replaceAllExperimentsWith(List<AbtExperimentInfo> list) throws AbtException {
        if (list.isEmpty()) {
            removeAllExperiments();
            return;
        }
        HashSet hashSet = new HashSet();
        for (AbtExperimentInfo experimentId : list) {
            hashSet.add(experimentId.getExperimentId());
        }
        List<AnalyticsConnector.ConditionalUserProperty> allExperimentsInAnalytics = getAllExperimentsInAnalytics();
        HashSet hashSet2 = new HashSet();
        for (AnalyticsConnector.ConditionalUserProperty conditionalUserProperty : allExperimentsInAnalytics) {
            hashSet2.add(conditionalUserProperty.name);
        }
        removeExperiments(getExperimentsToRemove(allExperimentsInAnalytics, hashSet));
        addExperiments(getExperimentsToAdd(list, hashSet2));
    }

    private ArrayList<AnalyticsConnector.ConditionalUserProperty> getExperimentsToRemove(List<AnalyticsConnector.ConditionalUserProperty> list, Set<String> set) {
        ArrayList<AnalyticsConnector.ConditionalUserProperty> arrayList = new ArrayList<>();
        for (AnalyticsConnector.ConditionalUserProperty next : list) {
            if (!set.contains(next.name)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private ArrayList<AbtExperimentInfo> getExperimentsToAdd(List<AbtExperimentInfo> list, Set<String> set) {
        ArrayList<AbtExperimentInfo> arrayList = new ArrayList<>();
        for (AbtExperimentInfo next : list) {
            if (!set.contains(next.getExperimentId())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private void addExperiments(List<AbtExperimentInfo> list) {
        ArrayDeque arrayDeque = new ArrayDeque(getAllExperimentsInAnalytics());
        int maxUserPropertiesInAnalytics = getMaxUserPropertiesInAnalytics();
        for (AbtExperimentInfo next : list) {
            while (arrayDeque.size() >= maxUserPropertiesInAnalytics) {
                removeExperimentFromAnalytics(((AnalyticsConnector.ConditionalUserProperty) arrayDeque.pollFirst()).name);
            }
            AnalyticsConnector.ConditionalUserProperty conditionalUserProperty = next.toConditionalUserProperty(this.originService);
            addExperimentToAnalytics(conditionalUserProperty);
            arrayDeque.offer(conditionalUserProperty);
        }
    }

    private void removeExperiments(Collection<AnalyticsConnector.ConditionalUserProperty> collection) {
        for (AnalyticsConnector.ConditionalUserProperty conditionalUserProperty : collection) {
            removeExperimentFromAnalytics(conditionalUserProperty.name);
        }
    }

    private static List<AbtExperimentInfo> convertMapsToExperimentInfos(List<Map<String, String>> list) throws AbtException {
        ArrayList arrayList = new ArrayList();
        for (Map<String, String> fromMap : list) {
            arrayList.add(AbtExperimentInfo.fromMap(fromMap));
        }
        return arrayList;
    }

    private void addExperimentToAnalytics(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        this.analyticsConnector.setConditionalUserProperty(conditionalUserProperty);
    }

    private void throwAbtExceptionIfAnalyticsIsNull() throws AbtException {
        if (this.analyticsConnector == null) {
            throw new AbtException("The Analytics SDK is not available. Please check that the Analytics SDK is included in your app dependencies.");
        }
    }

    private void removeExperimentFromAnalytics(String str) {
        this.analyticsConnector.clearConditionalUserProperty(str, (String) null, (Bundle) null);
    }

    private int getMaxUserPropertiesInAnalytics() {
        if (this.maxUserProperties == null) {
            this.maxUserProperties = Integer.valueOf(this.analyticsConnector.getMaxUserProperties(this.originService));
        }
        return this.maxUserProperties.intValue();
    }

    private List<AnalyticsConnector.ConditionalUserProperty> getAllExperimentsInAnalytics() {
        return this.analyticsConnector.getConditionalUserProperties(this.originService, "");
    }
}
