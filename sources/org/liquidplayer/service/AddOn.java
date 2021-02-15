package org.liquidplayer.service;

import org.liquidplayer.javascript.JSValue;

public interface AddOn {
    void register(String str);

    void require(JSValue jSValue, MicroService microService);
}
