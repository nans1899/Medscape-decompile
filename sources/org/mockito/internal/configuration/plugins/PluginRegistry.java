package org.mockito.internal.configuration.plugins;

import org.mockito.internal.creation.instance.InstantiatorProviderAdapter;
import org.mockito.plugins.AnnotationEngine;
import org.mockito.plugins.InstantiatorProvider;
import org.mockito.plugins.InstantiatorProvider2;
import org.mockito.plugins.MockMaker;
import org.mockito.plugins.MockitoLogger;
import org.mockito.plugins.PluginSwitch;
import org.mockito.plugins.StackTraceCleanerProvider;

class PluginRegistry {
    private final AnnotationEngine annotationEngine = ((AnnotationEngine) new PluginLoader(this.pluginSwitch).loadPlugin(AnnotationEngine.class));
    private final InstantiatorProvider2 instantiatorProvider;
    private final MockMaker mockMaker;
    private final MockitoLogger mockitoLogger = ((MockitoLogger) new PluginLoader(this.pluginSwitch).loadPlugin(MockitoLogger.class));
    private final PluginSwitch pluginSwitch;
    private final StackTraceCleanerProvider stackTraceCleanerProvider = ((StackTraceCleanerProvider) new PluginLoader(this.pluginSwitch).loadPlugin(StackTraceCleanerProvider.class));

    PluginRegistry() {
        PluginSwitch pluginSwitch2 = (PluginSwitch) new PluginLoader(new DefaultPluginSwitch()).loadPlugin(PluginSwitch.class);
        this.pluginSwitch = pluginSwitch2;
        this.mockMaker = (MockMaker) new PluginLoader(pluginSwitch2, "mock-maker-inline").loadPlugin(MockMaker.class);
        Object loadPlugin = new PluginLoader(this.pluginSwitch).loadPlugin(InstantiatorProvider2.class, InstantiatorProvider.class);
        if (loadPlugin instanceof InstantiatorProvider) {
            this.instantiatorProvider = new InstantiatorProviderAdapter((InstantiatorProvider) loadPlugin);
        } else {
            this.instantiatorProvider = (InstantiatorProvider2) loadPlugin;
        }
    }

    /* access modifiers changed from: package-private */
    public StackTraceCleanerProvider getStackTraceCleanerProvider() {
        return this.stackTraceCleanerProvider;
    }

    /* access modifiers changed from: package-private */
    public MockMaker getMockMaker() {
        return this.mockMaker;
    }

    /* access modifiers changed from: package-private */
    public InstantiatorProvider2 getInstantiatorProvider() {
        return this.instantiatorProvider;
    }

    /* access modifiers changed from: package-private */
    public AnnotationEngine getAnnotationEngine() {
        return this.annotationEngine;
    }

    /* access modifiers changed from: package-private */
    public MockitoLogger getMockitoLogger() {
        return this.mockitoLogger;
    }
}
