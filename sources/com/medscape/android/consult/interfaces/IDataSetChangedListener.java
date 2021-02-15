package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.ConsultUser;

public interface IDataSetChangedListener {
    void updateUserAndPosition(int i, ConsultUser consultUser);
}
