package com.wbmd.wbmddrugscommons.callbacks;

import com.wbmd.wbmddrugscommons.model.DrugMonograph;

public interface IContentManagerCallback {
    void onContentLoaded(DrugMonograph drugMonograph);

    void onContentLoadingError(String str);
}
