package com.wbmd.qxcalculator.activities.common;

import android.os.Bundle;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.model.QxError;
import java.util.List;

public abstract class DataObserverActivity extends QxMDActivity implements DataManager.DataManagerListener {
    public boolean dataManagerMethodFinished(String str, boolean z, List<QxError> list, Bundle bundle) {
        return false;
    }

    public void dataManagerMethodQueued(String str) {
    }

    public void dataManagerMethodStarted(String str) {
    }

    /* access modifiers changed from: protected */
    public List<String> getDataChangeTaskIdsToObserve() {
        return null;
    }

    public void onStart() {
        super.onStart();
        List<String> dataChangeTaskIdsToObserve = getDataChangeTaskIdsToObserve();
        if (dataChangeTaskIdsToObserve != null) {
            for (String registerDataManagerListener : dataChangeTaskIdsToObserve) {
                DataManager.getInstance().registerDataManagerListener(this, registerDataManagerListener);
            }
        }
        invalidateOptionsMenu();
    }

    public void onStop() {
        super.onStop();
        List<String> dataChangeTaskIdsToObserve = getDataChangeTaskIdsToObserve();
        if (dataChangeTaskIdsToObserve != null) {
            for (String deRegisterDataManagerListener : dataChangeTaskIdsToObserve) {
                DataManager.getInstance().deRegisterDataManagerListener(this, deRegisterDataManagerListener);
            }
        }
    }
}
