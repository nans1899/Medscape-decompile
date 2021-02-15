package com.tapstream.sdk;

import com.tapstream.sdk.landers.LanderApiResponse;
import com.tapstream.sdk.wordofmouth.OfferApiResponse;
import com.tapstream.sdk.wordofmouth.RewardApiResponse;
import java.io.Closeable;

public interface ApiClient extends Closeable {
    ApiFuture<EventApiResponse> fireEvent(Event event);

    ApiFuture<LanderApiResponse> getInAppLander();

    ApiFuture<TimelineSummaryResponse> getTimelineSummary();

    ApiFuture<OfferApiResponse> getWordOfMouthOffer(String str);

    ApiFuture<RewardApiResponse> getWordOfMouthRewardList();

    ApiFuture<TimelineApiResponse> lookupTimeline();
}
