package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public final class zzamu extends zzbit {
    private final AppMeasurementSdk zzdji;

    zzamu(AppMeasurementSdk appMeasurementSdk) {
        this.zzdji = appMeasurementSdk;
    }

    public final void performAction(Bundle bundle) throws RemoteException {
        this.zzdji.performAction(bundle);
    }

    public final Bundle performActionWithResponse(Bundle bundle) throws RemoteException {
        return this.zzdji.performActionWithResponse(bundle);
    }

    public final void logEvent(String str, String str2, Bundle bundle) throws RemoteException {
        this.zzdji.logEvent(str, str2, bundle);
    }

    public final void zza(String str, String str2, IObjectWrapper iObjectWrapper) throws RemoteException {
        this.zzdji.setUserProperty(str, str2, iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null);
    }

    public final Map getUserProperties(String str, String str2, boolean z) throws RemoteException {
        return this.zzdji.getUserProperties(str, str2, z);
    }

    public final int getMaxUserProperties(String str) throws RemoteException {
        return this.zzdji.getMaxUserProperties(str);
    }

    public final void setConditionalUserProperty(Bundle bundle) throws RemoteException {
        this.zzdji.setConditionalUserProperty(bundle);
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        this.zzdji.clearConditionalUserProperty(str, str2, bundle);
    }

    public final List getConditionalUserProperties(String str, String str2) throws RemoteException {
        return this.zzdji.getConditionalUserProperties(str, str2);
    }

    public final String getAppInstanceId() throws RemoteException {
        return this.zzdji.getAppInstanceId();
    }

    public final String getGmpAppId() throws RemoteException {
        return this.zzdji.getGmpAppId();
    }

    public final long generateEventId() throws RemoteException {
        return this.zzdji.generateEventId();
    }

    public final void beginAdUnitExposure(String str) throws RemoteException {
        this.zzdji.beginAdUnitExposure(str);
    }

    public final void endAdUnitExposure(String str) throws RemoteException {
        this.zzdji.endAdUnitExposure(str);
    }

    public final void zzb(IObjectWrapper iObjectWrapper, String str, String str2) throws RemoteException {
        this.zzdji.setCurrentScreen(iObjectWrapper != null ? (Activity) ObjectWrapper.unwrap(iObjectWrapper) : null, str, str2);
    }

    public final String getCurrentScreenName() throws RemoteException {
        return this.zzdji.getCurrentScreenName();
    }

    public final String getCurrentScreenClass() throws RemoteException {
        return this.zzdji.getCurrentScreenClass();
    }

    public final String getAppIdOrigin() throws RemoteException {
        return this.zzdji.getAppIdOrigin();
    }
}
