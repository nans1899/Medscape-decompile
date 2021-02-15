package com.appboy.models;

public interface IInAppMessageHtml extends IInAppMessage {
    String getAssetsZipRemoteUrl();

    String getLocalAssetsDirectoryUrl();

    boolean logButtonClick(String str);

    void setAssetsZipRemoteUrl(String str);

    void setLocalAssetsDirectoryUrl(String str);
}
