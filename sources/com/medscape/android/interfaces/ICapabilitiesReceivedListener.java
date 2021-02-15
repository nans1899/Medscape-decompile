package com.medscape.android.interfaces;

import com.medscape.android.capabilities.models.CapabilitiesFeature;
import java.util.Map;

public interface ICapabilitiesReceivedListener {
    void onCapabilitiesReceived(Map<String, CapabilitiesFeature> map);
}
