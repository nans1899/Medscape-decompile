package com.tapstream.sdk;

import com.tapstream.sdk.landers.Lander;
import com.tapstream.sdk.wordofmouth.Reward;
import java.util.Set;
import java.util.concurrent.Callable;

public interface Platform {
    void consumeReward(Reward reward);

    ActivityEventSource getActivityEventSource();

    Callable<AdvertisingID> getAdIdFetcher();

    String getAppName();

    String getAppVersion();

    String getLocale();

    String getManufacturer();

    String getModel();

    String getOs();

    String getPackageName();

    String getReferrer();

    String getResolution();

    boolean hasShown(Lander lander);

    boolean isConsumed(Reward reward);

    Set<String> loadFiredEvents();

    String loadSessionId();

    void registerLanderShown(Lander lander);

    void saveFiredEvents(Set<String> set);
}
