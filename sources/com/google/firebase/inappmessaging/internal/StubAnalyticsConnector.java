package com.google.firebase.inappmessaging.internal;

import android.os.Bundle;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class StubAnalyticsConnector implements AnalyticsConnector {
    public static final StubAnalyticsConnector instance = new StubAnalyticsConnector();

    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
    }

    public List<AnalyticsConnector.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        return null;
    }

    public int getMaxUserProperties(String str) {
        return 0;
    }

    public Map<String, Object> getUserProperties(boolean z) {
        return null;
    }

    public void logEvent(String str, String str2, Bundle bundle) {
    }

    public void setConditionalUserProperty(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
    }

    public void setUserProperty(String str, String str2, Object obj) {
    }

    private StubAnalyticsConnector() {
    }

    public AnalyticsConnectorHandle registerAnalyticsConnectorListener(String str, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        return AnalyticsConnectorHandle.instance;
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class AnalyticsConnectorHandle implements AnalyticsConnector.AnalyticsConnectorHandle {
        static final AnalyticsConnectorHandle instance = new AnalyticsConnectorHandle();

        public void registerEventNames(Set<String> set) {
        }

        public void unregister() {
        }

        public void unregisterEventNames() {
        }

        private AnalyticsConnectorHandle() {
        }
    }
}
