package com.tapstream.sdk.wordofmouth;

import com.tapstream.sdk.ApiResponse;
import com.tapstream.sdk.http.HttpResponse;
import java.util.List;

public class RewardApiResponse implements ApiResponse {
    private final HttpResponse response;
    private final List<Reward> rewards;

    public RewardApiResponse(HttpResponse httpResponse, List<Reward> list) {
        this.response = httpResponse;
        this.rewards = list;
    }

    public List<Reward> getRewards() {
        return this.rewards;
    }

    public HttpResponse getHttpResponse() {
        return this.response;
    }
}
