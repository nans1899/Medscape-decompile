package com.adobe.mobile;

import androidx.exifinterface.media.ExifInterface;
import com.adobe.mobile.Media;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

final class MediaItem {
    private static final Object monitorMutex = new Object();
    protected String CPM;
    protected Media.MediaCallback<MediaState> callback = null;
    protected String channel;
    private int completeCloseOffsetThreshold;
    private boolean completeTracked;
    protected MediaState currentMediaState = null;
    private HashSet<String> firstEventList = new HashSet<>();
    protected boolean itemClosed;
    protected int lastTrackSegmentNumber;
    protected double length;
    protected boolean mediaAd;
    protected MediaAnalytics mediaAnalytics;
    private ArrayList<Integer> milestones = new ArrayList<>();
    private MonitorThread monitor;
    protected String name;
    private ArrayList<Integer> offsetMilestones = new ArrayList<>();
    protected String parentName;
    protected String parentPod;
    protected double parentPodPosition;
    protected String playerID;
    protected String playerName;
    protected MediaState previousMediaState = null;
    private boolean segmentByMilestones;
    private boolean segmentByOffsetMilestones;
    protected double timestamp;
    protected boolean trackCalled;
    private int trackSecondsThreshold;

    public MediaItem(MediaSettings mediaSettings, MediaAnalytics mediaAnalytics2, String str, double d, String str2) {
        int i = 0;
        this.segmentByMilestones = false;
        this.segmentByOffsetMilestones = false;
        int i2 = 1;
        this.completeCloseOffsetThreshold = 1;
        this.trackSecondsThreshold = 0;
        this.name = str;
        this.length = d;
        this.playerName = str2;
        this.mediaAnalytics = mediaAnalytics2;
        this.playerID = mediaSettings.playerID;
        this.timestamp = (double) StaticMethods.getTimeSince1970();
        this.channel = mediaSettings.channel;
        setMilestones(mediaSettings.milestones);
        setOffsetMilestones(mediaSettings.offsetMilestones);
        setSegmentByMilestones(mediaSettings.segmentByMilestones && this.milestones.size() > 0);
        setSegmentByOffsetMilestones(mediaSettings.segmentByOffsetMilestones && this.offsetMilestones.size() > 0);
        setTrackSecondsThreshold(mediaAnalytics2.trackSeconds);
        setCompleteCloseOffsetThreshold(mediaAnalytics2.completeCloseOffsetThreshold);
        if (mediaSettings.isMediaAd) {
            this.mediaAd = true;
            this.parentPodPosition = mediaSettings.parentPodPosition;
            this.parentName = mediaSettings.parentName;
            this.parentPod = mediaSettings.parentPod;
            this.CPM = mediaSettings.CPM;
        }
        this.completeCloseOffsetThreshold = mediaSettings.completeCloseOffsetThreshold > 0 ? mediaSettings.completeCloseOffsetThreshold : i2;
        this.trackSecondsThreshold = mediaSettings.trackSeconds > 0 ? mediaSettings.trackSeconds : i;
    }

    /* access modifiers changed from: protected */
    public void startMonitor() {
        MonitorThread monitorThread = this.monitor;
        if (monitorThread == null || monitorThread.canceled) {
            if (this.monitor != null) {
                stopMonitor();
            }
            MonitorThread monitorThread2 = new MonitorThread();
            this.monitor = monitorThread2;
            monitorThread2.monitorMediaItem = this;
            this.monitor.start();
        }
    }

    /* access modifiers changed from: protected */
    public void stopMonitor() {
        if (this.monitor != null) {
            synchronized (monitorMutex) {
                this.monitor.canceled = true;
                this.monitor = null;
            }
        }
    }

    private static class MonitorThread extends Thread {
        protected boolean canceled;
        long delay;
        protected MediaItem monitorMediaItem;

        private MonitorThread() {
            this.delay = 1000;
            this.canceled = false;
        }

        public void run() {
            while (!this.canceled) {
                try {
                    Thread.sleep(this.delay);
                    StaticMethods.getMediaExecutor().execute(new Runnable() {
                        public void run() {
                            MonitorThread.this.monitorMediaItem.mediaAnalytics.monitor(MonitorThread.this.monitorMediaItem.name, -1.0d);
                        }
                    });
                } catch (InterruptedException e) {
                    StaticMethods.logWarningFormat("Media - Background Thread Interrupted : %s", e.getMessage());
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void play(double r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            com.adobe.mobile.MediaState r0 = r1.currentMediaState     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x000d
            boolean r0 = r1.isPlaying()     // Catch:{ all -> 0x001f }
            if (r0 == 0) goto L_0x000d
            monitor-exit(r1)
            return
        L_0x000d:
            r1.updateMediaStates()     // Catch:{ all -> 0x001f }
            r0 = 1
            r1.updateCurrentMediaStateWithOffset(r2, r0)     // Catch:{ all -> 0x001f }
            com.adobe.mobile.MediaState r2 = r1.currentMediaState     // Catch:{ all -> 0x001f }
            boolean r2 = r2.complete     // Catch:{ all -> 0x001f }
            if (r2 != 0) goto L_0x001d
            r1.startMonitor()     // Catch:{ all -> 0x001f }
        L_0x001d:
            monitor-exit(r1)
            return
        L_0x001f:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MediaItem.play(double):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void monitor(double r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            r1.updateMediaStates()     // Catch:{ all -> 0x0019 }
            com.adobe.mobile.MediaState r0 = r1.previousMediaState     // Catch:{ all -> 0x0019 }
            if (r0 != 0) goto L_0x000a
            monitor-exit(r1)
            return
        L_0x000a:
            r0 = 3
            r1.updateCurrentMediaStateWithOffset(r2, r0)     // Catch:{ all -> 0x0019 }
            com.adobe.mobile.MediaState r2 = r1.currentMediaState     // Catch:{ all -> 0x0019 }
            boolean r2 = r2.complete     // Catch:{ all -> 0x0019 }
            if (r2 == 0) goto L_0x0017
            r1.stopMonitor()     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MediaItem.monitor(double):void");
    }

    /* access modifiers changed from: protected */
    public synchronized void click(double d) {
        updateMediaStates();
        if (this.previousMediaState != null) {
            updateCurrentMediaStateWithOffset(d, 6);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void complete(double r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            r2.updateMediaStates()     // Catch:{ all -> 0x0027 }
            com.adobe.mobile.MediaState r0 = r2.previousMediaState     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x0025
            com.adobe.mobile.MediaState r0 = r2.previousMediaState     // Catch:{ all -> 0x0027 }
            int r0 = r0.getEventType()     // Catch:{ all -> 0x0027 }
            r1 = 5
            if (r0 != r1) goto L_0x0012
            goto L_0x0025
        L_0x0012:
            r2.updateCurrentMediaStateWithOffset(r3, r1)     // Catch:{ all -> 0x0027 }
            com.adobe.mobile.MediaState r3 = r2.currentMediaState     // Catch:{ all -> 0x0027 }
            boolean r3 = r3.complete     // Catch:{ all -> 0x0027 }
            if (r3 == 0) goto L_0x001e
            r2.stopMonitor()     // Catch:{ all -> 0x0027 }
        L_0x001e:
            com.adobe.mobile.MediaState r3 = r2.currentMediaState     // Catch:{ all -> 0x0027 }
            r4 = 1
            r3.complete = r4     // Catch:{ all -> 0x0027 }
            monitor-exit(r2)
            return
        L_0x0025:
            monitor-exit(r2)
            return
        L_0x0027:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MediaItem.complete(double):void");
    }

    /* access modifiers changed from: protected */
    public synchronized void stop(double d) {
        updateMediaStates();
        updateCurrentMediaStateWithOffset(d, 2);
        stopMonitor();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void close() {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.updateMediaStates()     // Catch:{ all -> 0x0038 }
            com.adobe.mobile.MediaState r0 = r3.previousMediaState     // Catch:{ all -> 0x0038 }
            if (r0 == 0) goto L_0x0036
            com.adobe.mobile.MediaState r0 = r3.previousMediaState     // Catch:{ all -> 0x0038 }
            int r0 = r0.getEventType()     // Catch:{ all -> 0x0038 }
            if (r0 != 0) goto L_0x0011
            goto L_0x0036
        L_0x0011:
            com.adobe.mobile.MediaState r0 = r3.previousMediaState     // Catch:{ all -> 0x0038 }
            int r0 = r0.eventType     // Catch:{ all -> 0x0038 }
            r1 = 2
            r2 = 0
            if (r0 != r1) goto L_0x0021
            com.adobe.mobile.MediaState r0 = r3.currentMediaState     // Catch:{ all -> 0x0038 }
            double r0 = r0.offset     // Catch:{ all -> 0x0038 }
            r3.updateCurrentMediaStateWithOffset(r0, r2)     // Catch:{ all -> 0x0038 }
            goto L_0x0026
        L_0x0021:
            r0 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r3.updateCurrentMediaStateWithOffset(r0, r2)     // Catch:{ all -> 0x0038 }
        L_0x0026:
            boolean r0 = r3.isCurrentOffsetPastCompleteThreshold()     // Catch:{ all -> 0x0038 }
            if (r0 == 0) goto L_0x0031
            com.adobe.mobile.MediaState r0 = r3.currentMediaState     // Catch:{ all -> 0x0038 }
            r1 = 1
            r0.complete = r1     // Catch:{ all -> 0x0038 }
        L_0x0031:
            r3.stopMonitor()     // Catch:{ all -> 0x0038 }
            monitor-exit(r3)
            return
        L_0x0036:
            monitor-exit(r3)
            return
        L_0x0038:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MediaItem.close():void");
    }

    private void updateMediaStates() {
        this.previousMediaState = this.currentMediaState;
        this.currentMediaState = new MediaState(this.name, this.length, this.playerName, (long) this.timestamp);
    }

    private void updateCurrentMediaStateWithOffset(double d, int i) {
        this.currentMediaState.clicked = i == 6;
        this.currentMediaState.ad = this.mediaAd;
        this.currentMediaState.setOffset(validateOffset(d));
        calculateCurrentOffsetMilestoneAndSegment();
        calculateCurrentMilestoneAndSegment();
        updateTimePlayed(i);
        this.currentMediaState.setEventType(i);
        updateCurrentMediaStateMediaEventIfNeeded(i);
        setEventFirstTime(this.currentMediaState);
    }

    private void calculateCurrentMilestoneAndSegment() {
        int calculateLastPassedMilestoneIndex;
        if (!isLive() && this.milestones.size() != 0 && (calculateLastPassedMilestoneIndex = calculateLastPassedMilestoneIndex()) != -1) {
            int intValue = this.milestones.get(calculateLastPassedMilestoneIndex).intValue();
            this.currentMediaState.milestone = intValue;
            if (this.segmentByMilestones) {
                int i = calculateLastPassedMilestoneIndex + 1;
                this.currentMediaState.segmentNum = i;
                StringBuilder sb = new StringBuilder();
                sb.append("M:");
                sb.append(Integer.toString(intValue));
                sb.append("-");
                if (calculateLastPassedMilestoneIndex < this.milestones.size() - 1) {
                    sb.append(Integer.toString(this.milestones.get(i).intValue()));
                } else {
                    sb.append("100");
                }
                this.currentMediaState.segment = sb.toString();
            }
        }
    }

    private void calculateCurrentOffsetMilestoneAndSegment() {
        int calculateLastPassedOffsetMilestoneIndex;
        if (this.offsetMilestones.size() != 0 && (calculateLastPassedOffsetMilestoneIndex = calculateLastPassedOffsetMilestoneIndex()) != -1) {
            int intValue = this.offsetMilestones.get(calculateLastPassedOffsetMilestoneIndex).intValue();
            this.currentMediaState.offsetMilestone = intValue;
            if (this.segmentByOffsetMilestones) {
                int i = calculateLastPassedOffsetMilestoneIndex + 1;
                this.currentMediaState.segmentNum = i;
                StringBuilder sb = new StringBuilder();
                sb.append("O:");
                sb.append(Integer.toString(intValue));
                sb.append("-");
                if (calculateLastPassedOffsetMilestoneIndex < this.offsetMilestones.size() - 1) {
                    sb.append(Integer.toString(this.offsetMilestones.get(i).intValue()));
                } else {
                    sb.append(isLive() ? ExifInterface.LONGITUDE_EAST : Integer.toString((int) this.length));
                }
                this.currentMediaState.segment = sb.toString();
            }
        }
    }

    private int calculateLastPassedMilestoneIndex() {
        int i = -1;
        if (this.milestones.size() == 0) {
            return -1;
        }
        for (int i2 = 0; i2 < this.milestones.size(); i2++) {
            if (this.currentMediaState.percent >= ((double) this.milestones.get(i2).intValue())) {
                i = i2;
            }
        }
        return i;
    }

    private int calculateLastPassedOffsetMilestoneIndex() {
        int i = -1;
        if (this.offsetMilestones.size() == 0) {
            return -1;
        }
        for (int i2 = 0; i2 < this.offsetMilestones.size(); i2++) {
            if (this.currentMediaState.offset >= ((double) this.offsetMilestones.get(i2).intValue())) {
                i = i2;
            }
        }
        return i;
    }

    private void updateCurrentMediaStateMediaEventIfNeeded(int i) {
        if (i != 0) {
            if (this.currentMediaState.percent >= 100.0d) {
                this.currentMediaState.mediaEvent = "CLOSE";
            } else if (this.previousMediaState != null) {
                if (this.currentMediaState.milestone > this.previousMediaState.milestone) {
                    this.currentMediaState.mediaEvent = "MILESTONE";
                } else if (this.currentMediaState.offsetMilestone > this.previousMediaState.offsetMilestone) {
                    this.currentMediaState.mediaEvent = "OFFSET_MILESTONE";
                } else if (getTrackSecondsThreshold() > 0 && this.currentMediaState.getTimePlayedSinceTrack() >= ((double) getTrackSecondsThreshold())) {
                    this.currentMediaState.mediaEvent = "SECONDS";
                }
            }
        }
    }

    private double validateOffset(double d) {
        return (d >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || this.previousMediaState == null) ? d : (this.currentMediaState.getTimestamp() - this.previousMediaState.getTimestamp()) + this.previousMediaState.offset;
    }

    private void updateTimePlayed(int i) {
        if (this.previousMediaState != null) {
            double d = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            if (this.currentMediaState.offset > this.previousMediaState.offset && i != 1) {
                d = this.currentMediaState.offset - this.previousMediaState.offset;
            }
            this.currentMediaState.setTimePlayed(this.previousMediaState.getTimePlayed() + d);
            this.currentMediaState.setTimePlayedSinceTrack(this.previousMediaState.getTimePlayedSinceTrack() + d);
        }
    }

    private void setEventFirstTime(MediaState mediaState) {
        String str = mediaState.mediaEvent;
        if (str.equals("MILESTONE")) {
            str = str + "_" + mediaState.milestone;
        } else if (str.equals("OFFSET_MILESTONE")) {
            str = str + "_" + mediaState.offsetMilestone;
        }
        if (!this.firstEventList.contains(str)) {
            mediaState.eventFirstTime = true;
            this.firstEventList.add(str);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isPlaying() {
        MediaState mediaState = this.currentMediaState;
        if (mediaState == null || mediaState.eventType == 0 || this.currentMediaState.eventType == 2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isCurrentOffsetPastCompleteThreshold() {
        return this.currentMediaState.offset >= this.length - ((double) this.completeCloseOffsetThreshold);
    }

    /* access modifiers changed from: protected */
    public boolean isLive() {
        return this.length == -1.0d;
    }

    private void setMilestones(String str) {
        this.milestones.clear();
        if (str != null && str.length() > 0) {
            this.milestones.add(0);
            for (String parseDouble : str.split(",")) {
                int parseDouble2 = (int) Double.parseDouble(parseDouble);
                if (parseDouble2 > 0 && parseDouble2 <= 100 && !this.milestones.contains(Integer.valueOf(parseDouble2))) {
                    this.milestones.add(Integer.valueOf(parseDouble2));
                }
            }
            Collections.sort(this.milestones);
        }
    }

    private void setOffsetMilestones(String str) {
        this.offsetMilestones.clear();
        if (str != null && str.length() > 0) {
            this.offsetMilestones.add(0);
            for (String parseDouble : str.split(",")) {
                int parseDouble2 = (int) Double.parseDouble(parseDouble);
                if (parseDouble2 > 0 && !this.offsetMilestones.contains(Integer.valueOf(parseDouble2)) && (isLive() || ((double) parseDouble2) <= this.length)) {
                    this.offsetMilestones.add(Integer.valueOf(parseDouble2));
                }
            }
            Collections.sort(this.offsetMilestones);
        }
    }

    /* access modifiers changed from: protected */
    public MediaState getReportMediaState() {
        boolean z;
        MediaState mediaState = new MediaState(this.currentMediaState);
        if (this.previousMediaState != null) {
            boolean z2 = true;
            if (this.currentMediaState.milestone <= this.previousMediaState.milestone) {
                mediaState.milestone = 0;
                z = true;
            } else {
                z = false;
            }
            if (this.currentMediaState.offsetMilestone <= this.previousMediaState.offsetMilestone) {
                mediaState.offsetMilestone = 0;
            } else {
                z2 = z;
            }
            if (z2) {
                mediaState.segment = this.previousMediaState.segment;
                mediaState.segmentNum = this.previousMediaState.segmentNum;
                mediaState.segmentLength = this.previousMediaState.segmentLength;
            }
        }
        return mediaState;
    }

    /* access modifiers changed from: protected */
    public boolean isSegmentByMilestones() {
        return this.segmentByMilestones;
    }

    /* access modifiers changed from: protected */
    public String getName() {
        return this.name;
    }

    /* access modifiers changed from: protected */
    public double getLength() {
        return this.length;
    }

    /* access modifiers changed from: protected */
    public String getPlayerName() {
        return this.playerName;
    }

    /* access modifiers changed from: protected */
    public String getPlayerID() {
        return this.playerID;
    }

    /* access modifiers changed from: protected */
    public void setSegmentByMilestones(boolean z) {
        this.segmentByMilestones = z;
    }

    /* access modifiers changed from: protected */
    public boolean isSegmentByOffsetMilestones() {
        return this.segmentByOffsetMilestones;
    }

    /* access modifiers changed from: protected */
    public void setSegmentByOffsetMilestones(boolean z) {
        this.segmentByOffsetMilestones = z;
    }

    public int getTrackSecondsThreshold() {
        return this.trackSecondsThreshold;
    }

    public void setTrackSecondsThreshold(int i) {
        this.trackSecondsThreshold = i;
    }

    /* access modifiers changed from: protected */
    public boolean isCompleteTracked() {
        return this.completeTracked;
    }

    /* access modifiers changed from: protected */
    public void setCompleteTracked(boolean z) {
        this.completeTracked = z;
    }

    public void setCompleteCloseOffsetThreshold(int i) {
        this.completeCloseOffsetThreshold = i;
    }
}
