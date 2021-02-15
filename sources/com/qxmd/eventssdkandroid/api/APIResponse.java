package com.qxmd.eventssdkandroid.api;

import com.qxmd.eventssdkandroid.model.QxError;
import java.util.ArrayList;
import java.util.List;

public class APIResponse {
    public static final int HTTP_RESPONSE_CODE_NOT_SET = 0;
    public static final int HTTP_RESPONSE_CODE_NO_INTERNET = -1;
    public static final int HTTP_RESPONSE_CODE_TIMEOUT = 258;
    public List<QxError> errors;
    public int httpStatusCode = 0;
    public boolean isNonCalledResponse;
    public String responseString;

    public boolean success() {
        List<QxError> list = this.errors;
        return list == null || list.isEmpty() || this.isNonCalledResponse;
    }

    public void addError(QxError qxError) {
        if (qxError != null) {
            if (this.errors == null) {
                this.errors = new ArrayList();
            }
            this.errors.add(qxError);
        }
    }
}
