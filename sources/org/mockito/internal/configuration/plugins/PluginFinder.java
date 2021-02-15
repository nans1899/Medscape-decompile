package org.mockito.internal.configuration.plugins;

import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.io.IOUtil;
import org.mockito.plugins.PluginSwitch;

class PluginFinder {
    private final PluginSwitch pluginSwitch;

    public PluginFinder(PluginSwitch pluginSwitch2) {
        this.pluginSwitch = pluginSwitch2;
    }

    /* access modifiers changed from: package-private */
    public String findPluginClass(Iterable<URL> iterable) {
        Iterator<URL> it = iterable.iterator();
        while (true) {
            InputStream inputStream = null;
            if (!it.hasNext()) {
                return null;
            }
            URL next = it.next();
            try {
                inputStream = next.openStream();
                String readPluginClass = new PluginFileReader().readPluginClass(inputStream);
                if (readPluginClass != null) {
                    if (this.pluginSwitch.isEnabled(readPluginClass)) {
                        IOUtil.closeQuietly(inputStream);
                        return readPluginClass;
                    }
                }
                IOUtil.closeQuietly(inputStream);
            } catch (Exception e) {
                throw new MockitoException("Problems reading plugin implementation from: " + next, e);
            } catch (Throwable th) {
                IOUtil.closeQuietly(inputStream);
                throw th;
            }
        }
    }
}
