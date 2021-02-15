package com.webmd.medscape.live.explorelivevents.common;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/common/OmnitureConstants;", "", "()V", "CHANNEL", "", "OMNITURE_FILTER_DATE", "OMNITURE_FILTER_LOCATION", "OMNITURE_FILTER_SPECIALTY", "OMNITURE_LINK_EVENT_INFO", "OMNITURE_LINK_SEARCH", "OMNITURE_MODULE_EVENT_CLICK", "OMNITURE_MODULE_FILTER", "OMNITURE_SCREEN_BROWSE", "", "OMNITURE_SCREEN_RESULTS", "PAGE_NAME_BROWSE", "PAGE_NAME_EXPLORE", "PAGE_NAME_RESULTS", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: OmnitureConstants.kt */
public final class OmnitureConstants {
    public static final String CHANNEL = "events";
    public static final OmnitureConstants INSTANCE = new OmnitureConstants();
    public static final String OMNITURE_FILTER_DATE = "date";
    public static final String OMNITURE_FILTER_LOCATION = "location";
    public static final String OMNITURE_FILTER_SPECIALTY = "specialty";
    public static final String OMNITURE_LINK_EVENT_INFO = "info";
    public static final String OMNITURE_LINK_SEARCH = "search";
    public static final String OMNITURE_MODULE_EVENT_CLICK = "event-card";
    public static final String OMNITURE_MODULE_FILTER = "filter";
    public static final int OMNITURE_SCREEN_BROWSE = 0;
    public static final int OMNITURE_SCREEN_RESULTS = 1;
    public static final String PAGE_NAME_BROWSE = "browse";
    public static final String PAGE_NAME_EXPLORE = "explore";
    public static final String PAGE_NAME_RESULTS = "results";

    private OmnitureConstants() {
    }
}
