package com.comscore.streaming;

import java.util.Map;

public interface StreamingListener {
    void onStateChanged(int i, int i2, Map<String, String> map);
}
