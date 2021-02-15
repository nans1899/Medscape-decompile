package com.tapstream.sdk.http;

import com.facebook.internal.ServerProtocol;
import com.tapstream.sdk.http.HttpRequest;

public class RequestBuilders {
    public static final String API_TAPSTREAM_COM = "api.tapstream.com";
    public static final String APP_TAPSTREAM_COM = "app.tapstream.com";
    public static final String DEFAULT_SCHEME = "https";
    public static final String REPORTING_TAPSTREAM_COM = "reporting.tapstream.com";

    public static HttpRequest.Builder eventRequestBuilder(String str, String str2) {
        return new HttpRequest.Builder().method(HttpMethod.POST).scheme(DEFAULT_SCHEME).host(API_TAPSTREAM_COM).path("/" + URLEncoding.QUERY_STRING_ENCODER.encode(str) + "/event/" + URLEncoding.QUERY_STRING_ENCODER.encode(str2));
    }

    public static HttpRequest.Builder timelineLookupRequestBuilder(String str, String str2) {
        return new HttpRequest.Builder().method(HttpMethod.GET).scheme(DEFAULT_SCHEME).host(REPORTING_TAPSTREAM_COM).path("/v1/timelines/lookup").addQueryParameter("secret", str).addQueryParameter("event_session", str2).addQueryParameter("blocking", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }

    public static HttpRequest.Builder timelineSummaryRequestBuilder(String str, String str2) {
        return new HttpRequest.Builder().method(HttpMethod.GET).scheme(DEFAULT_SCHEME).host(REPORTING_TAPSTREAM_COM).path("/v1/timelines/summary").addQueryParameter("secret", str).addQueryParameter("event_session", str2).addQueryParameter("blocking", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
    }

    public static HttpRequest.Builder wordOfMouthOfferRequestBuilder(String str, String str2, String str3) {
        return new HttpRequest.Builder().method(HttpMethod.GET).scheme(DEFAULT_SCHEME).host(APP_TAPSTREAM_COM).path("/api/v1/word-of-mouth/offers/").addQueryParameter("secret", str).addQueryParameter("insertion_point", str2).addQueryParameter("bundle", str3);
    }

    public static HttpRequest.Builder wordOfMouthRewardRequestBuilder(String str, String str2) {
        return new HttpRequest.Builder().method(HttpMethod.GET).scheme(DEFAULT_SCHEME).host(APP_TAPSTREAM_COM).path("/api/v1/word-of-mouth/rewards/").addQueryParameter("secret", str).addQueryParameter("event_session", str2);
    }

    public static HttpRequest.Builder inAppLanderRequestBuilder(String str, String str2) {
        return new HttpRequest.Builder().method(HttpMethod.GET).scheme(DEFAULT_SCHEME).host(REPORTING_TAPSTREAM_COM).path("/v1/in_app_landers/display/").addQueryParameter("secret", str).addQueryParameter("event_session", str2);
    }
}
