package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.ConsultUser;

public interface IMentionSelectedListener {
    void onMentionSelected(ConsultUser consultUser, Integer num, int i);
}
