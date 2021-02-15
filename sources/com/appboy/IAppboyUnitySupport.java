package com.appboy;

import com.appboy.models.IInAppMessage;

public interface IAppboyUnitySupport {
    IInAppMessage deserializeInAppMessageString(String str);
}
