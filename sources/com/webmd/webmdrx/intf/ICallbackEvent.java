package com.webmd.webmdrx.intf;

public interface ICallbackEvent<T, E> {
    void onCompleted(T t);

    void onError(E e);
}
