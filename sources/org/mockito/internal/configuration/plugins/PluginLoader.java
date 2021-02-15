package org.mockito.internal.configuration.plugins;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.mockito.plugins.PluginSwitch;

class PluginLoader {
    private final PluginInitializer initializer;
    private final DefaultMockitoPlugins plugins;

    PluginLoader(DefaultMockitoPlugins defaultMockitoPlugins, PluginInitializer pluginInitializer) {
        this.plugins = defaultMockitoPlugins;
        this.initializer = pluginInitializer;
    }

    PluginLoader(PluginSwitch pluginSwitch) {
        this(new DefaultMockitoPlugins(), new PluginInitializer(pluginSwitch, (String) null, new DefaultMockitoPlugins()));
    }

    @Deprecated
    PluginLoader(PluginSwitch pluginSwitch, String str) {
        this(new DefaultMockitoPlugins(), new PluginInitializer(pluginSwitch, str, new DefaultMockitoPlugins()));
    }

    /* access modifiers changed from: package-private */
    public <T> T loadPlugin(Class<T> cls) {
        return loadPlugin(cls, (Class) null);
    }

    /* access modifiers changed from: package-private */
    public <PreferredType, AlternateType> Object loadPlugin(final Class<PreferredType> cls, final Class<AlternateType> cls2) {
        AlternateType loadImpl;
        try {
            PreferredType loadImpl2 = this.initializer.loadImpl(cls);
            if (loadImpl2 != null) {
                return loadImpl2;
            }
            if (cls2 == null || (loadImpl = this.initializer.loadImpl(cls2)) == null) {
                return this.plugins.getDefaultPlugin(cls);
            }
            return loadImpl;
        } catch (Throwable th) {
            return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
                public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                    throw new IllegalStateException("Could not initialize plugin: " + cls + " (alternate: " + cls2 + ")", th);
                }
            });
        }
    }
}
