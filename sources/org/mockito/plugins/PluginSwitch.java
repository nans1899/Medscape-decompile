package org.mockito.plugins;

import org.mockito.Incubating;

@Incubating
public interface PluginSwitch {
    boolean isEnabled(String str);
}
