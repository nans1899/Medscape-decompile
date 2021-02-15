package com.wbmd.wbmdcommons.callbacks;

public interface ITypeAheadCallBackEvent<T, E> {
    void onCompleted(String str, T t);

    void onError(E e);
}
