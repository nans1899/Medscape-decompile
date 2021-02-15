package com.tapstream.sdk;

import android.app.Activity;
import android.app.Application;
import android.view.View;
import com.tapstream.sdk.AndroidApiClient;
import com.tapstream.sdk.landers.ILanderDelegate;
import com.tapstream.sdk.landers.InAppLanderImpl;
import com.tapstream.sdk.landers.Lander;
import com.tapstream.sdk.landers.LanderApiResponse;
import com.tapstream.sdk.wordofmouth.OfferApiResponse;
import com.tapstream.sdk.wordofmouth.RewardApiResponse;
import com.tapstream.sdk.wordofmouth.WordOfMouth;
import com.tapstream.sdk.wordofmouth.WordOfMouthImpl;
import java.io.IOException;

public class Tapstream implements AndroidApiClient {
    private static ClientBuilder clientBuilder;
    private static Tapstream instance;
    private ApiClient client;
    private InAppLanderImpl landerImpl;
    private WordOfMouth wom;

    public interface ClientBuilder {
        ApiClient build(Platform platform, Config config);
    }

    static {
        if (!Logging.isConfigured()) {
            Logging.setLogger(new AndroidLogger());
        }
    }

    public static class DefaultClientBuilder implements ClientBuilder {
        public ApiClient build(Platform platform, Config config) {
            HttpApiClient httpApiClient = new HttpApiClient(platform, config);
            httpApiClient.start();
            return httpApiClient;
        }
    }

    public static synchronized void setClientBuilder(ClientBuilder clientBuilder2) {
        synchronized (Tapstream.class) {
            clientBuilder = clientBuilder2;
        }
    }

    public static synchronized void create(Application application, Config config) {
        synchronized (Tapstream.class) {
            if (instance == null) {
                ClientBuilder defaultClientBuilder = clientBuilder == null ? new DefaultClientBuilder() : clientBuilder;
                AndroidPlatform androidPlatform = new AndroidPlatform(application);
                InAppLanderImpl inAppLanderImpl = null;
                WordOfMouth instance2 = config.getUseWordOfMouth() ? WordOfMouthImpl.getInstance(androidPlatform) : null;
                if (config.getUseInAppLanders()) {
                    inAppLanderImpl = InAppLanderImpl.getInstance(androidPlatform);
                }
                instance = new Tapstream(defaultClientBuilder.build(androidPlatform, config), instance2, inAppLanderImpl);
            } else {
                Logging.log(5, "Tapstream Warning: Tapstream already instantiated, it cannot be re-created.", new Object[0]);
            }
        }
    }

    public static synchronized Tapstream getInstance() {
        Tapstream tapstream;
        synchronized (Tapstream.class) {
            if (instance != null) {
                tapstream = instance;
            } else {
                throw new RuntimeException("You must first call Tapstream.create");
            }
        }
        return tapstream;
    }

    Tapstream(ApiClient apiClient, WordOfMouth wordOfMouth, InAppLanderImpl inAppLanderImpl) {
        this.client = apiClient;
        this.wom = wordOfMouth;
        this.landerImpl = inAppLanderImpl;
    }

    public void close() throws IOException {
        instance.close();
    }

    public ApiFuture<EventApiResponse> fireEvent(Event event) {
        return this.client.fireEvent(event);
    }

    public ApiFuture<TimelineApiResponse> lookupTimeline() {
        return this.client.lookupTimeline();
    }

    public ApiFuture<TimelineSummaryResponse> getTimelineSummary() {
        return this.client.getTimelineSummary();
    }

    public ApiFuture<OfferApiResponse> getWordOfMouthOffer(String str) {
        return this.client.getWordOfMouthOffer(str);
    }

    public ApiFuture<RewardApiResponse> getWordOfMouthRewardList() {
        return this.client.getWordOfMouthRewardList();
    }

    public WordOfMouth getWordOfMouth() throws AndroidApiClient.ServiceNotEnabled {
        WordOfMouth wordOfMouth = this.wom;
        if (wordOfMouth != null) {
            return wordOfMouth;
        }
        throw new AndroidApiClient.ServiceNotEnabled("To use Word of Mouth features, ensure that the useWordOfMouth setting in your configuration is enabled.");
    }

    public ApiFuture<LanderApiResponse> getInAppLander() {
        return this.client.getInAppLander();
    }

    public void showLanderIfUnseen(Activity activity, View view, Lander lander) throws AndroidApiClient.ServiceNotEnabled {
        showLanderIfUnseen(activity, view, lander, new ILanderDelegate() {
            public void dismissedLander() {
            }

            public void showedLander(Lander lander) {
            }

            public void submittedLander() {
            }
        });
    }

    public void showLanderIfUnseen(Activity activity, View view, Lander lander, ILanderDelegate iLanderDelegate) throws AndroidApiClient.ServiceNotEnabled {
        InAppLanderImpl inAppLanderImpl = this.landerImpl;
        if (inAppLanderImpl == null) {
            throw new AndroidApiClient.ServiceNotEnabled("To use In-App Lander features, ensure that the getUseInAppLanders setting in your configuration is enabled.");
        } else if (inAppLanderImpl.shouldShowLander(lander)) {
            this.landerImpl.showLander(activity, view, lander, iLanderDelegate);
        }
    }
}
