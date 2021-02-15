package org.mockito.plugins;

public interface MockitoPlugins {
    <T> T getDefaultPlugin(Class<T> cls);

    MockMaker getInlineMockMaker();
}
