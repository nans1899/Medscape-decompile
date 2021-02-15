package com.tapstream.sdk;

import com.tapstream.sdk.http.HttpResponse;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class TimelineApiResponse implements ApiResponse {
    private static final Pattern legacyEmptyTimelinePattern = Pattern.compile("^\\s*\\[\\s*\\]\\s*$");
    private final HttpResponse httpResponse;
    private final boolean isEmpty;
    private final String rawResponse;

    public TimelineApiResponse(HttpResponse httpResponse2) {
        this.httpResponse = httpResponse2;
        String bodyAsString = httpResponse2.getBodyAsString();
        this.rawResponse = bodyAsString;
        this.isEmpty = bodyAsString == null || bodyAsString.isEmpty() || legacyEmptyTimelinePattern.matcher(this.rawResponse).matches();
    }

    public HttpResponse getHttpResponse() {
        return this.httpResponse;
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public String getRawResponse() {
        return this.rawResponse;
    }

    public JSONObject parse() {
        if (this.isEmpty) {
            return null;
        }
        return new JSONObject(this.rawResponse);
    }
}
