package com.wbmd.wbmddrugscommons.callbacks;

import com.wbmd.wbmddrugscommons.model.Tug;
import java.util.HashMap;

public interface IDrugTTSDataCallback {
    void onTTSDrugDataError(String str);

    void onTTSDrugDataReceived(HashMap<String, Tug> hashMap);
}
