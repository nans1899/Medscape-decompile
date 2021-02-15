package com.tapstream.sdk;

import android.app.Activity;
import android.view.View;
import com.tapstream.sdk.landers.ILanderDelegate;
import com.tapstream.sdk.landers.Lander;
import com.tapstream.sdk.wordofmouth.WordOfMouth;

public interface AndroidApiClient extends ApiClient {
    WordOfMouth getWordOfMouth() throws ServiceNotEnabled;

    void showLanderIfUnseen(Activity activity, View view, Lander lander) throws ServiceNotEnabled;

    void showLanderIfUnseen(Activity activity, View view, Lander lander, ILanderDelegate iLanderDelegate) throws ServiceNotEnabled;

    public static class ServiceNotEnabled extends RuntimeException {
        public ServiceNotEnabled(String str) {
            super(str);
        }
    }
}
