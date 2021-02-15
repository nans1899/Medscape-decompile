package com.adobe.mobile;

import com.adobe.mobile.Media;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;

final class MediaAnalytics {
    private static final String AD_CLICKED_KEY = "a.media.ad.clicked";
    private static final String AD_COMPLETE_KEY = "a.media.ad.complete";
    private static final String AD_CPM = "a.media.ad.CPM";
    private static final String AD_LENGTH_KEY = "a.media.ad.length";
    private static final String AD_MILESTONE_KEY = "a.media.ad.milestone";
    private static final String AD_NAME_KEY = "a.media.ad.name";
    private static final String AD_OFFSET_MILESTONE_KEY = "a.media.ad.offsetMilestone";
    private static final String AD_PLAYER_NAME_KEY = "a.media.ad.playerName";
    private static final String AD_POD = "a.media.ad.pod";
    private static final String AD_POD_POSITION = "a.media.ad.podPosition";
    private static final String AD_SEGMENT_KEY = "a.media.ad.segment";
    private static final String AD_SEGMENT_NUM_KEY = "a.media.ad.segmentNum";
    private static final String AD_SEGMENT_VIEW_KEY = "a.media.ad.segmentView";
    private static final String AD_TIME_PLAYED_KEY = "a.media.ad.timePlayed";
    private static final String AD_VIEW_KEY = "a.media.ad.view";
    private static final String CHANNEL_KEY = "a.media.channel";
    private static final String COMPLETE_KEY = "a.media.complete";
    private static final String CONTENT_TYPE_KEY = "a.contentType";
    private static final String CONTENT_TYPE_VALUE = "video";
    private static final String CONTENT_TYPE_VALUE_AD = "videoAd";
    private static final String DEFAULT_PLAYER_NAME = "Not_Specified";
    private static final String INITIAL_HIT_PAGE_EVENT = "m_s";
    private static final String LENGTH_KEY = "a.media.length";
    protected static final double LIVE_EVENT_LENGTH = -1.0d;
    private static final String MEDIA_CLICKED_KEY = "a.media.clicked";
    private static final String MEDIA_HIT_PAGE_EVENT = "m_i";
    private static final String MEDIA_VIEW_KEY = "a.media.view";
    private static final String MILESTONE_KEY = "a.media.milestone";
    private static final String NAME_KEY = "a.media.name";
    private static final String OFFSET_MILESTONE_KEY = "a.media.offsetMilestone";
    private static final String PAGE_EVENT_VAR_OVERRIDE = "&&pe";
    private static final String PEV_VALUE_OVERRIDE = "video";
    private static final String PEV_VALUE_OVERRIDE_AD = "videoAd";
    private static final String PEV_VAR_OVERRIDE = "&&pev3";
    private static final String PLAYER_NAME_KEY = "a.media.playerName";
    private static final String SEGMENT_KEY = "a.media.segment";
    private static final String SEGMENT_NUM_KEY = "a.media.segmentNum";
    private static final String SEGMENT_VIEW_KEY = "a.media.segmentView";
    private static final String TIME_PLAYED_KEY = "a.media.timePlayed";
    private static MediaAnalytics _instance = null;
    private static final Object _instanceMutex = new Object();
    private static final List<String> unwantedValues = Arrays.asList(new String[]{null, ""});
    protected int completeCloseOffsetThreshold = 1;
    private final HashMap<String, Object> mediaItemList = new HashMap<>();
    protected int trackSeconds = 0;

    MediaAnalytics() {
    }

    protected static MediaAnalytics sharedInstance() {
        MediaAnalytics mediaAnalytics;
        synchronized (_instanceMutex) {
            if (_instance == null) {
                _instance = new MediaAnalytics();
            }
            mediaAnalytics = _instance;
        }
        return mediaAnalytics;
    }

    /* access modifiers changed from: protected */
    public final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /* access modifiers changed from: protected */
    public synchronized void open(MediaSettings mediaSettings, Media.MediaCallback mediaCallback) {
        String cleanName = cleanName(mediaSettings.name);
        if (isNilOrEmptyString(cleanName)) {
            StaticMethods.logWarningFormat("Analytics - ADBMediaSettings is required with a valid name. Media item not opened", new Object[0]);
        } else if (!mediaSettings.isMediaAd || !isNilOrEmptyString(mediaSettings.parentName)) {
            double d = mediaSettings.length > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? mediaSettings.length : LIVE_EVENT_LENGTH;
            String cleanName2 = isNilOrEmptyString(mediaSettings.playerName) ? DEFAULT_PLAYER_NAME : cleanName(mediaSettings.playerName);
            if (this.mediaItemList.containsKey(cleanName)) {
                close(cleanName);
            }
            if (!isNilOrEmptyString(mediaSettings.playerID)) {
                Iterator<String> it = this.mediaItemList.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next = it.next();
                    String playerID = ((MediaItem) this.mediaItemList.get(next)).getPlayerID();
                    if (playerID != null && playerID.equals(mediaSettings.playerID)) {
                        close(next);
                        break;
                    }
                }
            }
            MediaItem mediaItem = new MediaItem(mediaSettings, this, cleanName, d, cleanName2);
            mediaItem.callback = mediaCallback;
            this.mediaItemList.put(cleanName, mediaItem);
        } else {
            StaticMethods.logWarningFormat("Analytics - Media ad requires parent name, please specify a parent name. Media item not opened", new Object[0]);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0032, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void close(java.lang.String r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            com.adobe.mobile.MediaItem r7 = r6.mediaItemWithName(r7)     // Catch:{ all -> 0x0033 }
            if (r7 != 0) goto L_0x0009
            monitor-exit(r6)
            return
        L_0x0009:
            r0 = 0
            r7.trackCalled = r0     // Catch:{ all -> 0x0033 }
            r7.close()     // Catch:{ all -> 0x0033 }
            r6.notifyDelegateOfMediaState(r7)     // Catch:{ all -> 0x0033 }
            boolean r0 = r7.trackCalled     // Catch:{ all -> 0x0033 }
            r1 = 1
            if (r0 != 0) goto L_0x002f
            com.adobe.mobile.MediaState r0 = r7.currentMediaState     // Catch:{ all -> 0x0033 }
            double r2 = r0.getTimePlayed()     // Catch:{ all -> 0x0033 }
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0027
            r0 = 0
            r6.trackMediaStateIfNecessary(r7, r0, r1)     // Catch:{ all -> 0x0033 }
        L_0x0027:
            java.util.HashMap<java.lang.String, java.lang.Object> r0 = r6.mediaItemList     // Catch:{ all -> 0x0033 }
            java.lang.String r7 = r7.name     // Catch:{ all -> 0x0033 }
            r0.remove(r7)     // Catch:{ all -> 0x0033 }
            goto L_0x0031
        L_0x002f:
            r7.itemClosed = r1     // Catch:{ all -> 0x0033 }
        L_0x0031:
            monitor-exit(r6)
            return
        L_0x0033:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MediaAnalytics.close(java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public synchronized void play(String str, double d) {
        MediaItem mediaItemWithName = mediaItemWithName(str);
        if (mediaItemWithName != null) {
            mediaItemWithName.trackCalled = false;
            mediaItemWithName.play(d);
            notifyDelegateOfMediaState(mediaItemWithName);
            if (!mediaItemWithName.trackCalled) {
                if (mediaItemWithName.previousMediaState == null) {
                    trackMediaViewed(mediaItemWithName);
                } else if (mediaItemWithName.currentMediaState.segmentNum == mediaItemWithName.lastTrackSegmentNumber || mediaItemWithName.currentMediaState.timePlayed <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                    trackMediaStateIfNecessary(mediaItemWithName, (HashMap<String, Object>) null, false);
                } else {
                    trackMediaStateIfNecessary(mediaItemWithName, (HashMap<String, Object>) null, true);
                }
            }
            mediaItemWithName.trackCalled = false;
            removeMediaItemIfComplete(mediaItemWithName);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0026, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void complete(java.lang.String r2, double r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            com.adobe.mobile.MediaItem r2 = r1.mediaItemWithName(r2)     // Catch:{ all -> 0x0027 }
            if (r2 == 0) goto L_0x0025
            boolean r0 = r2.isPlaying()     // Catch:{ all -> 0x0027 }
            if (r0 != 0) goto L_0x000e
            goto L_0x0025
        L_0x000e:
            r0 = 0
            r2.trackCalled = r0     // Catch:{ all -> 0x0027 }
            r2.complete(r3)     // Catch:{ all -> 0x0027 }
            r1.notifyDelegateOfMediaState(r2)     // Catch:{ all -> 0x0027 }
            boolean r3 = r2.trackCalled     // Catch:{ all -> 0x0027 }
            if (r3 != 0) goto L_0x0023
            com.adobe.mobile.MediaState r3 = r2.previousMediaState     // Catch:{ all -> 0x0027 }
            if (r3 == 0) goto L_0x0023
            r3 = 0
            r1.trackMediaStateIfNecessary(r2, r3, r0)     // Catch:{ all -> 0x0027 }
        L_0x0023:
            monitor-exit(r1)
            return
        L_0x0025:
            monitor-exit(r1)
            return
        L_0x0027:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MediaAnalytics.complete(java.lang.String, double):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0024, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0026, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void stop(java.lang.String r2, double r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            com.adobe.mobile.MediaItem r2 = r1.mediaItemWithName(r2)     // Catch:{ all -> 0x0027 }
            if (r2 == 0) goto L_0x0025
            boolean r0 = r2.isPlaying()     // Catch:{ all -> 0x0027 }
            if (r0 != 0) goto L_0x000e
            goto L_0x0025
        L_0x000e:
            r0 = 0
            r2.trackCalled = r0     // Catch:{ all -> 0x0027 }
            r2.stop(r3)     // Catch:{ all -> 0x0027 }
            r1.notifyDelegateOfMediaState(r2)     // Catch:{ all -> 0x0027 }
            boolean r3 = r2.trackCalled     // Catch:{ all -> 0x0027 }
            if (r3 != 0) goto L_0x0023
            com.adobe.mobile.MediaState r3 = r2.previousMediaState     // Catch:{ all -> 0x0027 }
            if (r3 == 0) goto L_0x0023
            r3 = 0
            r1.trackMediaStateIfNecessary(r2, r3, r0)     // Catch:{ all -> 0x0027 }
        L_0x0023:
            monitor-exit(r1)
            return
        L_0x0025:
            monitor-exit(r1)
            return
        L_0x0027:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MediaAnalytics.stop(java.lang.String, double):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void click(java.lang.String r2, double r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            com.adobe.mobile.MediaItem r2 = r1.mediaItemWithName(r2)     // Catch:{ all -> 0x0028 }
            if (r2 == 0) goto L_0x0026
            boolean r0 = r2.isPlaying()     // Catch:{ all -> 0x0028 }
            if (r0 != 0) goto L_0x000e
            goto L_0x0026
        L_0x000e:
            r0 = 0
            r2.trackCalled = r0     // Catch:{ all -> 0x0028 }
            r2.click(r3)     // Catch:{ all -> 0x0028 }
            r1.notifyDelegateOfMediaState(r2)     // Catch:{ all -> 0x0028 }
            boolean r3 = r2.trackCalled     // Catch:{ all -> 0x0028 }
            if (r3 != 0) goto L_0x0024
            com.adobe.mobile.MediaState r3 = r2.previousMediaState     // Catch:{ all -> 0x0028 }
            if (r3 == 0) goto L_0x0024
            r3 = 0
            r4 = 1
            r1.trackMediaStateIfNecessary(r2, r3, r4)     // Catch:{ all -> 0x0028 }
        L_0x0024:
            monitor-exit(r1)
            return
        L_0x0026:
            monitor-exit(r1)
            return
        L_0x0028:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MediaAnalytics.click(java.lang.String, double):void");
    }

    /* access modifiers changed from: protected */
    public void setTrackCalledOnItem(String str) {
        MediaItem mediaItemWithName = mediaItemWithName(str);
        if (mediaItemWithName != null) {
            mediaItemWithName.trackCalled = true;
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void track(String str, Map<String, Object> map) {
        MediaItem mediaItemWithName = mediaItemWithName(str);
        if (mediaItemWithName != null) {
            if (mediaItemWithName.currentMediaState != null) {
                HashMap hashMap = map != null ? new HashMap(map) : new HashMap();
                removeEmptyValues(hashMap);
                trackMediaStateIfNecessary(mediaItemWithName, hashMap, true);
            }
            if (mediaItemWithName.itemClosed) {
                this.mediaItemList.remove(mediaItemWithName.name);
            }
            mediaItemWithName.trackCalled = false;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void monitor(java.lang.String r3, double r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.adobe.mobile.MediaItem r3 = r2.mediaItemWithName(r3)     // Catch:{ all -> 0x002d }
            if (r3 == 0) goto L_0x002b
            boolean r0 = r3.isPlaying()     // Catch:{ all -> 0x002d }
            if (r0 != 0) goto L_0x000e
            goto L_0x002b
        L_0x000e:
            boolean r0 = r3.trackCalled     // Catch:{ all -> 0x002d }
            r1 = 0
            if (r0 == 0) goto L_0x0017
            r3.trackCalled = r1     // Catch:{ all -> 0x002d }
            monitor-exit(r2)
            return
        L_0x0017:
            r3.monitor(r4)     // Catch:{ all -> 0x002d }
            r2.notifyDelegateOfMediaState(r3)     // Catch:{ all -> 0x002d }
            com.adobe.mobile.MediaState r4 = r3.previousMediaState     // Catch:{ all -> 0x002d }
            if (r4 == 0) goto L_0x0029
            boolean r4 = r3.trackCalled     // Catch:{ all -> 0x002d }
            if (r4 != 0) goto L_0x0029
            r4 = 0
            r2.trackMediaStateIfNecessary(r3, r4, r1)     // Catch:{ all -> 0x002d }
        L_0x0029:
            monitor-exit(r2)
            return
        L_0x002b:
            monitor-exit(r2)
            return
        L_0x002d:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.mobile.MediaAnalytics.monitor(java.lang.String, double):void");
    }

    private void notifyDelegateOfMediaState(MediaItem mediaItem) {
        if (mediaItem.callback != null) {
            mediaItem.callback.call(mediaItem.getReportMediaState());
        }
    }

    private void trackMediaViewed(MediaItem mediaItem) {
        HashMap hashMap = new HashMap();
        hashMap.put(!mediaItem.mediaAd ? MEDIA_VIEW_KEY : AD_VIEW_KEY, String.valueOf(true));
        addGenericMediaContextData(hashMap, mediaItem, true);
        addSegmentContextData(hashMap, mediaItem);
        trackMediaItemWithContextData(mediaItem, hashMap);
        removeMediaItemIfComplete(mediaItem);
    }

    private void trackMediaStateIfNecessary(MediaItem mediaItem, HashMap<String, Object> hashMap, boolean z) {
        HashMap hashMap2;
        if (hashMap == null) {
            hashMap2 = new HashMap();
        }
        addGenericMediaContextData(hashMap2, mediaItem, false);
        addSegmentContextData(hashMap2, mediaItem);
        boolean z2 = true;
        if (mediaItem.previousMediaState == null) {
            hashMap2.put(PAGE_EVENT_VAR_OVERRIDE, INITIAL_HIT_PAGE_EVENT);
            hashMap2.put(!mediaItem.mediaAd ? MEDIA_VIEW_KEY : AD_VIEW_KEY, true);
            trackMediaItemWithContextData(mediaItem, hashMap2);
            return;
        }
        if (mediaItem.currentMediaState.complete) {
            if (!mediaItem.isCompleteTracked()) {
                hashMap2.put(!mediaItem.mediaAd ? COMPLETE_KEY : AD_COMPLETE_KEY, String.valueOf(true));
                mediaItem.setCompleteTracked(true);
                z = true;
            }
            removeMediaItemIfComplete(mediaItem);
        }
        if (mediaItem.currentMediaState.clicked) {
            hashMap2.put(!mediaItem.mediaAd ? MEDIA_CLICKED_KEY : AD_CLICKED_KEY, String.valueOf(true));
        }
        if (mediaItem.currentMediaState.offsetMilestone > mediaItem.previousMediaState.offsetMilestone) {
            hashMap2.put(!mediaItem.mediaAd ? OFFSET_MILESTONE_KEY : AD_OFFSET_MILESTONE_KEY, Integer.toString(mediaItem.currentMediaState.offsetMilestone));
            z = true;
        }
        if (mediaItem.currentMediaState.milestone > mediaItem.previousMediaState.milestone) {
            hashMap2.put(!mediaItem.mediaAd ? MILESTONE_KEY : AD_MILESTONE_KEY, Integer.toString(mediaItem.currentMediaState.milestone));
            z = true;
        }
        if (mediaItem.getTrackSecondsThreshold() <= 0 || mediaItem.currentMediaState.getTimePlayedSinceTrack() < ((double) mediaItem.getTrackSecondsThreshold())) {
            z2 = z;
        }
        if (z2) {
            if (mediaItem.currentMediaState.getTimePlayedSinceTrack() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                hashMap2.put(!mediaItem.mediaAd ? TIME_PLAYED_KEY : AD_TIME_PLAYED_KEY, Integer.toString((int) mediaItem.currentMediaState.getTimePlayedSinceTrack()));
            }
            trackMediaItemWithContextData(mediaItem, hashMap2);
        }
    }

    private void trackMediaItemWithContextData(MediaItem mediaItem, HashMap<String, Object> hashMap) {
        trackMedia(hashMap);
        mediaItem.currentMediaState.setTimePlayedSinceTrack(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    private void addSegmentContextData(HashMap<String, Object> hashMap, MediaItem mediaItem) {
        if (mediaItem.isSegmentByMilestones() || mediaItem.isSegmentByOffsetMilestones()) {
            MediaState mediaState = mediaItem.currentMediaState;
            if (mediaItem.previousMediaState != null) {
                if (mediaItem.currentMediaState.segmentNum != mediaItem.lastTrackSegmentNumber || mediaItem.currentMediaState.complete) {
                    hashMap.put(!mediaItem.mediaAd ? SEGMENT_VIEW_KEY : AD_SEGMENT_VIEW_KEY, String.valueOf(true));
                }
                if (mediaItem.currentMediaState.segmentNum != mediaItem.previousMediaState.segmentNum) {
                    mediaState = mediaItem.previousMediaState;
                }
            }
            if (mediaState.segmentNum > 0) {
                hashMap.put(!mediaItem.mediaAd ? SEGMENT_NUM_KEY : AD_SEGMENT_NUM_KEY, Integer.toString(mediaState.segmentNum));
            }
            if (mediaState.segment != null) {
                hashMap.put(!mediaItem.mediaAd ? SEGMENT_KEY : AD_SEGMENT_KEY, mediaState.segment);
            }
        }
        mediaItem.lastTrackSegmentNumber = mediaItem.currentMediaState.segmentNum;
    }

    private void addGenericMediaContextData(HashMap<String, Object> hashMap, MediaItem mediaItem, boolean z) {
        hashMap.put(PAGE_EVENT_VAR_OVERRIDE, z ? INITIAL_HIT_PAGE_EVENT : MEDIA_HIT_PAGE_EVENT);
        if (!mediaItem.mediaAd || isNilOrEmptyString(mediaItem.parentName)) {
            hashMap.put(PEV_VAR_OVERRIDE, "video");
            hashMap.put(CONTENT_TYPE_KEY, "video");
            hashMap.put(NAME_KEY, mediaItem.getName());
            hashMap.put(PLAYER_NAME_KEY, mediaItem.getPlayerName());
            if (!mediaItem.isLive()) {
                hashMap.put(LENGTH_KEY, Integer.toString((int) mediaItem.getLength()));
            }
        } else {
            hashMap.put(PEV_VAR_OVERRIDE, "videoAd");
            hashMap.put(CONTENT_TYPE_KEY, "videoAd");
            hashMap.put(AD_NAME_KEY, mediaItem.getName());
            hashMap.put(AD_PLAYER_NAME_KEY, mediaItem.getPlayerName());
            hashMap.put(NAME_KEY, cleanName(mediaItem.parentName));
            if (!mediaItem.isLive()) {
                hashMap.put(AD_LENGTH_KEY, Integer.toString((int) mediaItem.getLength()));
            }
            if (mediaItem.parentPod != null && mediaItem.parentPod.length() > 0) {
                hashMap.put(AD_POD, mediaItem.parentPod);
            }
            if (mediaItem.parentPodPosition > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                hashMap.put(AD_POD_POSITION, Integer.toString((int) mediaItem.parentPodPosition));
            }
            if (z && !isNilOrEmptyString(mediaItem.CPM)) {
                hashMap.put(AD_CPM, mediaItem.CPM);
            }
        }
        if (!isNilOrEmptyString(mediaItem.channel)) {
            hashMap.put(CHANNEL_KEY, mediaItem.channel);
        }
    }

    private void trackMedia(HashMap<String, Object> hashMap) {
        AnalyticsTrackInternal.trackInternal("Media", hashMap, StaticMethods.getTimeSince1970());
    }

    private void removeMediaItemIfComplete(MediaItem mediaItem) {
        if (mediaItem.currentMediaState.percent >= 100.0d) {
            this.mediaItemList.remove(mediaItem.name);
        }
    }

    private String cleanName(String str) {
        if (isNilOrEmptyString(str)) {
            return null;
        }
        return str.replace(IOUtils.LINE_SEPARATOR_UNIX, "").replace("\r", "").replace("--**--", "");
    }

    private MediaItem mediaItemWithName(String str) {
        String cleanName = cleanName(str);
        if (isNilOrEmptyString(cleanName) || hashMapIsNullOrEmpty(this.mediaItemList)) {
            return null;
        }
        return (MediaItem) this.mediaItemList.get(cleanName);
    }

    private void removeEmptyValues(HashMap<String, Object> hashMap) {
        hashMap.values().removeAll(unwantedValues);
    }

    private boolean hashMapIsNullOrEmpty(HashMap hashMap) {
        return hashMap == null || hashMap.size() == 0;
    }

    private boolean isNilOrEmptyString(String str) {
        return str == null || str.trim().length() == 0;
    }
}
