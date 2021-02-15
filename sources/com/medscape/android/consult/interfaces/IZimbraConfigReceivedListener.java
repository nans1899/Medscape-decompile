package com.medscape.android.consult.interfaces;

import com.medscape.android.consult.models.ZimbraConfigResponse;

public interface IZimbraConfigReceivedListener {
    void onZimbraConfigReceivedComplete(ZimbraConfigResponse zimbraConfigResponse);
}
