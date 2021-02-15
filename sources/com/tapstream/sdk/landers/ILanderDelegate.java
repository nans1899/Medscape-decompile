package com.tapstream.sdk.landers;

public interface ILanderDelegate {
    void dismissedLander();

    void showedLander(Lander lander);

    void submittedLander();
}
