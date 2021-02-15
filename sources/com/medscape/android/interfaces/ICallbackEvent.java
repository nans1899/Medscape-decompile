package com.medscape.android.interfaces;

public interface ICallbackEvent<T, E> {
    void onCompleted(T t);

    void onError(E e);
}
