package com.medscape.android.updater;

import com.medscape.android.updater.model.Update;
import java.util.List;

public interface OnUpdateListener {
    void onNetworkError(int i);

    void onProgressUpdate(int i);

    void onUpdateAvailable(int i, List<Update> list, int i2);

    void onUpdateComplete(int i, Update update);

    void onUpdateNotAvailable(int i);

    void setMaxProgress(int i);

    void setProgressMessage(String str);
}
