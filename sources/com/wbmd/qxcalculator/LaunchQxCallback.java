package com.wbmd.qxcalculator;

import android.os.Bundle;
import com.wbmd.qxcalculator.model.db.DBContentItem;

public interface LaunchQxCallback {
    void onQxItemClicked(DBContentItem dBContentItem, Bundle bundle);
}
