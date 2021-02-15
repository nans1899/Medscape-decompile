package org.mockito.internal.configuration;

import java.io.Serializable;
import org.mockito.configuration.AnnotationEngine;
import org.mockito.configuration.DefaultMockitoConfiguration;
import org.mockito.configuration.IMockitoConfiguration;
import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.stubbing.Answer;

public class GlobalConfiguration implements IMockitoConfiguration, Serializable {
    private static final ThreadLocal<IMockitoConfiguration> GLOBAL_CONFIGURATION = new ThreadLocal<>();
    private static final long serialVersionUID = -2860353062105505938L;

    /* access modifiers changed from: package-private */
    public IMockitoConfiguration getIt() {
        return GLOBAL_CONFIGURATION.get();
    }

    public GlobalConfiguration() {
        if (GLOBAL_CONFIGURATION.get() == null) {
            GLOBAL_CONFIGURATION.set(createConfig());
        }
    }

    private IMockitoConfiguration createConfig() {
        DefaultMockitoConfiguration defaultMockitoConfiguration = new DefaultMockitoConfiguration();
        IMockitoConfiguration loadConfiguration = new ClassPathLoader().loadConfiguration();
        return loadConfiguration != null ? loadConfiguration : defaultMockitoConfiguration;
    }

    public static void validate() {
        new GlobalConfiguration();
    }

    public AnnotationEngine getAnnotationEngine() {
        return GLOBAL_CONFIGURATION.get().getAnnotationEngine();
    }

    public org.mockito.plugins.AnnotationEngine tryGetPluginAnnotationEngine() {
        IMockitoConfiguration iMockitoConfiguration = GLOBAL_CONFIGURATION.get();
        if (iMockitoConfiguration.getClass() == DefaultMockitoConfiguration.class) {
            return Plugins.getAnnotationEngine();
        }
        return iMockitoConfiguration.getAnnotationEngine();
    }

    public boolean cleansStackTrace() {
        return GLOBAL_CONFIGURATION.get().cleansStackTrace();
    }

    public boolean enableClassCache() {
        return GLOBAL_CONFIGURATION.get().enableClassCache();
    }

    public Answer<Object> getDefaultAnswer() {
        return GLOBAL_CONFIGURATION.get().getDefaultAnswer();
    }
}
