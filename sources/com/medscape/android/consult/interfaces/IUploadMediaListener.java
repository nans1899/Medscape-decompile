package com.medscape.android.consult.interfaces;

import com.medscape.android.util.MedscapeException;
import java.util.List;

public interface IUploadMediaListener {
    void onMediaUploadFail(MedscapeException medscapeException);

    void onMediaUploadSuccess(List<String> list);
}
