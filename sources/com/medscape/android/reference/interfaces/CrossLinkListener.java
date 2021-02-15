package com.medscape.android.reference.interfaces;

import android.widget.TextView;
import com.medscape.android.contentviewer.CrossLink;

public interface CrossLinkListener {
    void onLinkClicked(TextView textView, CrossLink crossLink);
}
