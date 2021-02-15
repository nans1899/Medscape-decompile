package com.webmd.wbmdcmepulse.models.interfaces;

import android.view.View;
import com.webmd.wbmdcmepulse.models.cmetracker.CMEItem;

public interface ICMETrackerButtonHandler {
    void viewActivityButtonPressed(View view, CMEItem cMEItem);

    void viewCertificateButtonPressed(View view, CMEItem cMEItem);
}
