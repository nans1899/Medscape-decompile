package com.webmd.wbmdproffesionalauthentication.callbacks;

public interface ITrackingEvent {
    void onAction(String str);

    void onPageView(String str);
}
