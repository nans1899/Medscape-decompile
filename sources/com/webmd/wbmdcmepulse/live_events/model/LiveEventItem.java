package com.webmd.wbmdcmepulse.live_events.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\b\u0016\u0018\u00002\u00020\u0001BU\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bR \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\rR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "Ljava/io/Serializable;", "eventHeader", "", "eventTitle", "eventDate", "eventLocation", "registerLink", "registerLabel", "excludeIfCountry", "includeIfCountry", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEventDate", "()Ljava/lang/String;", "setEventDate", "(Ljava/lang/String;)V", "getEventHeader", "getEventLocation", "getEventTitle", "getExcludeIfCountry", "getIncludeIfCountry", "getRegisterLabel", "getRegisterLink", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventItem.kt */
public class LiveEventItem implements Serializable {
    @SerializedName("eventDate")
    private String eventDate;
    @SerializedName("eventHeader")
    private final String eventHeader;
    @SerializedName("eventLocation")
    private final String eventLocation;
    @SerializedName("eventTitle")
    private final String eventTitle;
    @SerializedName("excludeIfCountry")
    private final String excludeIfCountry;
    @SerializedName("includeIfCountry")
    private final String includeIfCountry;
    @SerializedName("registerLabel")
    private final String registerLabel;
    @SerializedName("registerLink")
    private final String registerLink;

    public LiveEventItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.eventHeader = str;
        this.eventTitle = str2;
        this.eventDate = str3;
        this.eventLocation = str4;
        this.registerLink = str5;
        this.registerLabel = str6;
        this.excludeIfCountry = str7;
        this.includeIfCountry = str8;
    }

    public final String getEventHeader() {
        return this.eventHeader;
    }

    public final String getEventTitle() {
        return this.eventTitle;
    }

    public final String getEventDate() {
        return this.eventDate;
    }

    public final void setEventDate(String str) {
        this.eventDate = str;
    }

    public final String getEventLocation() {
        return this.eventLocation;
    }

    public final String getRegisterLink() {
        return this.registerLink;
    }

    public final String getRegisterLabel() {
        return this.registerLabel;
    }

    public final String getExcludeIfCountry() {
        return this.excludeIfCountry;
    }

    public final String getIncludeIfCountry() {
        return this.includeIfCountry;
    }
}
