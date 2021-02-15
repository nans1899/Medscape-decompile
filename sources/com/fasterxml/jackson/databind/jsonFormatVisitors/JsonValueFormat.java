package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import com.fasterxml.jackson.annotation.JsonValue;
import com.medscape.android.cache.Cache;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;

public enum JsonValueFormat {
    COLOR("color"),
    DATE(OmnitureConstants.OMNITURE_FILTER_DATE),
    DATE_TIME("date-time"),
    EMAIL("email"),
    HOST_NAME("host-name"),
    IP_ADDRESS("ip-address"),
    IPV6("ipv6"),
    PHONE(PlaceFields.PHONE),
    REGEX("regex"),
    STYLE(AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE),
    TIME(Cache.Caches.TIME),
    URI("uri"),
    UTC_MILLISEC("utc-millisec");
    
    private final String _desc;

    private JsonValueFormat(String str) {
        this._desc = str;
    }

    @JsonValue
    public String toString() {
        return this._desc;
    }
}
