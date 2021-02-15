package com.wbmd.wbmdcommons.callbacks;

public interface ICallbackEvent<T, E> {
    void onCompleted(T t);

    void onError(E e);
}
