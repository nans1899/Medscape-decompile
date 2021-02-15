package com.webmd.webmdrx.intf;

import com.webmd.webmdrx.models.RxForm;
import com.webmd.webmdrx.util.WebMDException;

public interface IRxFormReceivedListener {
    void onRxFormReceived(RxForm rxForm);

    void onRxFormRequestFailed(WebMDException webMDException);
}
