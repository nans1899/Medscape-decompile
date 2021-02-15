package com.medscape.android.reference.interfaces;

public interface ArticleDownloadListener {
    void articlDownloadUnsuccessful();

    void articleDownloadComplete(String str);
}
