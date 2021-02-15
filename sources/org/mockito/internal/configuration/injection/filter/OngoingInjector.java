package org.mockito.internal.configuration.injection.filter;

public interface OngoingInjector {
    public static final OngoingInjector nop = new OngoingInjector() {
        public Object thenInject() {
            return null;
        }
    };

    Object thenInject();
}
