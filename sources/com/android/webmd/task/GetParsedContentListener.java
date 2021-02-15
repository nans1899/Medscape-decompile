package com.android.webmd.task;

import com.android.webmd.model.Device;
import java.util.List;

public interface GetParsedContentListener {
    void onContentsDownloaded(List<Device> list);
}
