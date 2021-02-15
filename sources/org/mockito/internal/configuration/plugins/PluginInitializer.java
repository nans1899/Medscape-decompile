package org.mockito.internal.configuration.plugins;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import org.mockito.internal.util.collections.Iterables;
import org.mockito.plugins.PluginSwitch;

class PluginInitializer {
    private final String alias;
    private final PluginSwitch pluginSwitch;
    private final DefaultMockitoPlugins plugins;

    PluginInitializer(PluginSwitch pluginSwitch2, String str, DefaultMockitoPlugins defaultMockitoPlugins) {
        this.pluginSwitch = pluginSwitch2;
        this.alias = str;
        this.plugins = defaultMockitoPlugins;
    }

    public <T> T loadImpl(Class<T> cls) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            contextClassLoader = ClassLoader.getSystemClassLoader();
        }
        try {
            Enumeration<URL> resources = contextClassLoader.getResources("mockito-extensions/" + cls.getName());
            try {
                String findPluginClass = new PluginFinder(this.pluginSwitch).findPluginClass(Iterables.toIterable(resources));
                if (findPluginClass == null) {
                    return null;
                }
                if (findPluginClass.equals(this.alias)) {
                    findPluginClass = this.plugins.getDefaultPluginClass(this.alias);
                }
                return cls.cast(contextClassLoader.loadClass(findPluginClass).newInstance());
            } catch (Exception e) {
                throw new IllegalStateException("Failed to load " + cls + " implementation declared in " + resources, e);
            }
        } catch (IOException e2) {
            throw new IllegalStateException("Failed to load " + cls, e2);
        }
    }
}
