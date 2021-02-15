package org.mockito.configuration;

import org.mockito.internal.configuration.InjectingAnnotationEngine;
import org.mockito.internal.stubbing.defaultanswers.ReturnsEmptyValues;
import org.mockito.stubbing.Answer;

public class DefaultMockitoConfiguration implements IMockitoConfiguration {
    public boolean cleansStackTrace() {
        return true;
    }

    public boolean enableClassCache() {
        return true;
    }

    public Answer<Object> getDefaultAnswer() {
        return new ReturnsEmptyValues();
    }

    public AnnotationEngine getAnnotationEngine() {
        return new InjectingAnnotationEngine();
    }
}
