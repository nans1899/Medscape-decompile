package com.medscape.android.interfaces;

public interface IProviderCallbacks {
    <T> void onCompleted(T t);

    <T> void onError(T t);
}
