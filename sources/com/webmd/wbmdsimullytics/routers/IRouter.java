package com.webmd.wbmdsimullytics.routers;

import android.os.Bundle;

public interface IRouter {
    void routeEvent(String str);

    void routeEvent(String str, Bundle bundle);

    void routeUserAttribute(String str, String str2);

    void routeUserAttribute(String str, boolean z);

    void routeUserAttribute(String str, String[] strArr);

    void routeUserId(String str);

    void unrouteUserAttribute(String str);
}
