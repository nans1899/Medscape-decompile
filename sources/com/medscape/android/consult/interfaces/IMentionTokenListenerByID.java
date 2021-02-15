package com.medscape.android.consult.interfaces;

public interface IMentionTokenListenerByID {
    void onErrorRecievingMentionToken();

    void onRecievingMentionToken(String str);
}
