package com.adobe.mobile;

public class MediaSettings {
    public String CPM;
    public String channel;
    public int completeCloseOffsetThreshold = 1;
    public boolean isMediaAd;
    public double length;
    public String milestones;
    public String name;
    public String offsetMilestones;
    public String parentName;
    public String parentPod;
    public double parentPodPosition;
    public String playerID;
    public String playerName;
    public boolean segmentByMilestones;
    public boolean segmentByOffsetMilestones;
    public int trackSeconds = 0;

    public static MediaSettings settingsWith(String str, double d, String str2, String str3) {
        MediaSettings mediaSettings = new MediaSettings();
        mediaSettings.name = str;
        mediaSettings.length = d;
        mediaSettings.playerName = str2;
        mediaSettings.playerID = str3;
        return mediaSettings;
    }

    public static MediaSettings adSettingsWith(String str, double d, String str2, String str3, String str4, double d2, String str5) {
        MediaSettings mediaSettings = new MediaSettings();
        mediaSettings.isMediaAd = true;
        mediaSettings.name = str;
        mediaSettings.length = d;
        mediaSettings.playerName = str2;
        mediaSettings.parentName = str3;
        mediaSettings.parentPod = str4;
        mediaSettings.parentPodPosition = d2;
        mediaSettings.CPM = str5;
        return mediaSettings;
    }
}
