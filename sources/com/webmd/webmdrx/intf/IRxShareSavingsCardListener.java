package com.webmd.webmdrx.intf;

import com.webmd.webmdrx.util.WebMDException;

public interface IRxShareSavingsCardListener {
    void onFailure(WebMDException webMDException);

    void onSuccess();
}
