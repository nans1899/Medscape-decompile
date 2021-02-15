package com.webmd.wbmdsimullytics.platform;

import android.app.Activity;
import android.os.Bundle;
import com.webmd.wbmdsimullytics.callbacks.IFetchPlatformsListener;
import com.webmd.wbmdsimullytics.routers.IRouter;
import java.util.List;

public class PlatformRouteDispatcher implements IRouter {
    private static final String TAG = PlatformRouteDispatcher.class.getSimpleName();
    private boolean disableAppBoyRoute;
    private boolean disableFirebaseRoute;
    private Activity mActivity;
    /* access modifiers changed from: private */
    public List<String> mPlatforms;

    public PlatformRouteDispatcher(Activity activity) {
        this.mActivity = activity;
        this.disableAppBoyRoute = false;
        this.disableFirebaseRoute = false;
        PlatformConfigurationManager.getInstance().fetchPlatforms(activity, new IFetchPlatformsListener() {
            public void onPlatformsFetched(List<String> list) {
                List unused = PlatformRouteDispatcher.this.mPlatforms = list;
            }
        });
    }

    public PlatformRouteDispatcher(Activity activity, boolean z, boolean z2) {
        this(activity);
        this.disableFirebaseRoute = !z2;
        this.disableAppBoyRoute = !z;
    }

    public void routeEvent(String str) {
        List<String> list = this.mPlatforms;
        if (list != null) {
            for (String routerByPlatformName : list) {
                IRouter routerByPlatformName2 = getRouterByPlatformName(routerByPlatformName);
                if (routerByPlatformName2 != null) {
                    routerByPlatformName2.routeEvent(str);
                }
            }
        }
    }

    public void routeEvent(String str, Bundle bundle) {
        List<String> list = this.mPlatforms;
        if (list != null) {
            for (String routerByPlatformName : list) {
                IRouter routerByPlatformName2 = getRouterByPlatformName(routerByPlatformName);
                if (routerByPlatformName2 != null) {
                    routerByPlatformName2.routeEvent(str, bundle);
                }
            }
        }
    }

    public void routeUserAttribute(String str, String str2) {
        List<String> list = this.mPlatforms;
        if (list != null) {
            for (String routerByPlatformName : list) {
                IRouter routerByPlatformName2 = getRouterByPlatformName(routerByPlatformName);
                if (routerByPlatformName2 != null) {
                    routerByPlatformName2.routeUserAttribute(str, str2);
                }
            }
        }
    }

    public void routeUserAttribute(String str, boolean z) {
        List<String> list = this.mPlatforms;
        if (list != null) {
            for (String routerByPlatformName : list) {
                IRouter routerByPlatformName2 = getRouterByPlatformName(routerByPlatformName);
                if (routerByPlatformName2 != null) {
                    routerByPlatformName2.routeUserAttribute(str, z);
                }
            }
        }
    }

    public void routeUserAttribute(String str, String[] strArr) {
        List<String> list = this.mPlatforms;
        if (list != null) {
            for (String routerByPlatformName : list) {
                IRouter routerByPlatformName2 = getRouterByPlatformName(routerByPlatformName);
                if (routerByPlatformName2 != null) {
                    routerByPlatformName2.routeUserAttribute(str, strArr);
                }
            }
        }
    }

    public void unrouteUserAttribute(String str) {
        List<String> list = this.mPlatforms;
        if (list != null) {
            for (String routerByPlatformName : list) {
                IRouter routerByPlatformName2 = getRouterByPlatformName(routerByPlatformName);
                if (routerByPlatformName2 != null) {
                    routerByPlatformName2.unrouteUserAttribute(str);
                }
            }
        }
    }

    public void routeUserId(String str) {
        List<String> list = this.mPlatforms;
        if (list != null) {
            for (String routerByPlatformName : list) {
                IRouter routerByPlatformName2 = getRouterByPlatformName(routerByPlatformName);
                if (routerByPlatformName2 != null) {
                    routerByPlatformName2.routeUserId(str);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.webmd.wbmdsimullytics.routers.IRouter getRouterByPlatformName(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -498706905(0xffffffffe2465627, float:-9.146658E20)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 64445660(0x3d75cdc, float:1.2657884E-36)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "Braze"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = 0
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "Firebase"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = 1
            goto L_0x0025
        L_0x0024:
            r4 = -1
        L_0x0025:
            r0 = 0
            if (r4 == 0) goto L_0x0038
            if (r4 == r2) goto L_0x002b
            return r0
        L_0x002b:
            boolean r4 = r3.disableFirebaseRoute
            if (r4 == 0) goto L_0x0030
            goto L_0x0037
        L_0x0030:
            com.webmd.wbmdsimullytics.routers.FirebaseRouter r0 = new com.webmd.wbmdsimullytics.routers.FirebaseRouter
            android.app.Activity r4 = r3.mActivity
            r0.<init>(r4)
        L_0x0037:
            return r0
        L_0x0038:
            boolean r4 = r3.disableAppBoyRoute
            if (r4 == 0) goto L_0x003d
            goto L_0x0044
        L_0x003d:
            com.webmd.wbmdsimullytics.routers.BrazeRouter r0 = new com.webmd.wbmdsimullytics.routers.BrazeRouter
            android.app.Activity r4 = r3.mActivity
            r0.<init>(r4)
        L_0x0044:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher.getRouterByPlatformName(java.lang.String):com.webmd.wbmdsimullytics.routers.IRouter");
    }
}
