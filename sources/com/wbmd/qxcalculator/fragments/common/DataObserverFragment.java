package com.wbmd.qxcalculator.fragments.common;

import android.os.Bundle;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.model.QxError;
import java.util.List;

public abstract class DataObserverFragment extends QxMDFragment implements DataManager.DataManagerListener {
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

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        List<String> dataChangeTaskIdsToObserve = getDataChangeTaskIdsToObserve();
        if (dataChangeTaskIdsToObserve != null) {
            for (String registerDataManagerListener : dataChangeTaskIdsToObserve) {
                DataManager.getInstance().registerDataManagerListener(this, registerDataManagerListener);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        List<String> dataChangeTaskIdsToObserve = getDataChangeTaskIdsToObserve();
        if (dataChangeTaskIdsToObserve != null) {
            for (String deRegisterDataManagerListener : dataChangeTaskIdsToObserve) {
                DataManager.getInstance().deRegisterDataManagerListener(this, deRegisterDataManagerListener);
            }
        }
    }
}
