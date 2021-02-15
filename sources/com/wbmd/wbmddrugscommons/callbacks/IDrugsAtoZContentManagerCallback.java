package com.wbmd.wbmddrugscommons.callbacks;

import com.wbmd.wbmddrugscommons.model.Drug;
import java.util.List;

public interface IDrugsAtoZContentManagerCallback {
    void onContentLoaded(List<Drug> list, List<Drug> list2);

    void onContentLoadingError(String str);
}
