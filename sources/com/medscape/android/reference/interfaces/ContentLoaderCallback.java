package com.medscape.android.reference.interfaces;

import com.medscape.android.reference.model.ClinicalReferenceContent;

public interface ContentLoaderCallback {
    void handleContentDownloaded(String str);

    void handleContentLoadingError(String str);

    void handleContentNotAvailable(String str, boolean z);

    void handleContentNotDownloaded();

    void handleContentloaded(ClinicalReferenceContent clinicalReferenceContent);
}
