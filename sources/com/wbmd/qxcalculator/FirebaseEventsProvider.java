package com.wbmd.qxcalculator;

import android.os.Bundle;

public interface FirebaseEventsProvider {
    void sendEventName(String str);

    void sendEventName(String str, Bundle bundle);

    void sendScreenName(String str);
}
