package com.adobe.mobile;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Date;

public final class MediaState {
    public boolean ad;
    public boolean clicked = false;
    public boolean complete = false;
    public boolean eventFirstTime;
    protected int eventType;
    public double length;
    public String mediaEvent;
    public int milestone;
    public String name;
    public double offset;
    public int offsetMilestone;
    public Date openTime = new Date();
    public double percent;
    public String playerName;
    public String segment;
    public double segmentLength;
    public int segmentNum;
    public double timePlayed;
    private double timePlayedSinceTrack;
    private long timestamp;

    protected MediaState(String str, double d, String str2, long j) {
        this.name = str;
        this.length = d;
        this.playerName = str2;
        this.timestamp = StaticMethods.getTimeSince1970();
        this.segment = "";
        this.segmentNum = 0;
        this.segmentLength = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.openTime.setTime(j);
    }

    protected MediaState(MediaState mediaState) {
        this.name = mediaState.name;
        this.length = mediaState.length;
        this.playerName = mediaState.playerName;
        this.mediaEvent = mediaState.mediaEvent;
        this.eventFirstTime = mediaState.eventFirstTime;
        this.openTime = mediaState.openTime;
        this.offset = mediaState.offset;
        this.percent = mediaState.percent;
        this.timePlayed = mediaState.timePlayed;
        this.milestone = mediaState.milestone;
        this.offsetMilestone = mediaState.offsetMilestone;
        this.segmentNum = mediaState.segmentNum;
        this.segment = mediaState.segment;
        this.segmentLength = mediaState.segmentLength;
        this.complete = mediaState.complete;
        this.eventType = mediaState.eventType;
        this.timestamp = mediaState.timestamp;
        this.timePlayedSinceTrack = mediaState.timePlayedSinceTrack;
        this.clicked = mediaState.clicked;
        this.ad = mediaState.ad;
    }

    /* access modifiers changed from: protected */
    public int getEventType() {
        return this.eventType;
    }

    /* access modifiers changed from: protected */
    public void setEventType(int i) {
        String str;
        this.eventType = i;
        switch (i) {
            case 1:
                str = "PLAY";
                break;
            case 2:
                str = "STOP";
                break;
            case 3:
                str = "MONITOR";
                break;
            case 4:
                str = "TRACK";
                break;
            case 5:
                str = "COMPLETE";
                break;
            case 6:
                str = "CLICK";
                break;
            default:
                str = "CLOSE";
                break;
        }
        this.mediaEvent = str;
    }

    /* access modifiers changed from: protected */
    public void setOffset(double d) {
        this.offset = d;
        double d2 = this.length;
        if (d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            if (d >= d2) {
                d = d2;
            }
            this.offset = d;
        }
        if (this.offset < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.offset = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        generatePercent();
        checkComplete();
    }

    private void generatePercent() {
        double d = this.length;
        if (d != -1.0d) {
            double d2 = this.offset / d;
            double d3 = 100.0d;
            double d4 = d2 * 100.0d;
            this.percent = d4;
            if (d4 < 100.0d) {
                d3 = d4;
            }
            this.percent = d3;
        }
    }

    private void checkComplete() {
        if (this.length == -1.0d) {
            this.complete = false;
        } else if (this.percent >= 100.0d) {
            this.complete = true;
        }
    }

    /* access modifiers changed from: protected */
    public double getTimestamp() {
        return (double) this.timestamp;
    }

    /* access modifiers changed from: protected */
    public double getTimePlayedSinceTrack() {
        return this.timePlayedSinceTrack;
    }

    /* access modifiers changed from: protected */
    public void setTimePlayedSinceTrack(double d) {
        this.timePlayedSinceTrack = d;
    }

    /* access modifiers changed from: protected */
    public double getTimePlayed() {
        return this.timePlayed;
    }

    /* access modifiers changed from: protected */
    public void setTimePlayed(double d) {
        this.timePlayed = d;
    }
}
