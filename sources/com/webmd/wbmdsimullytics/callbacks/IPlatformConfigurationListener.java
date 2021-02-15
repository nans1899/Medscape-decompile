package com.webmd.wbmdsimullytics.callbacks;

import com.webmd.wbmdsimullytics.model.PlatformUserConfig;

public interface IPlatformConfigurationListener {
    void onPlatformConfigurationReceived(PlatformUserConfig platformUserConfig);
}
