package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzanu extends IInterface {
    String getAdvertiser() throws RemoteException;

    String getBody() throws RemoteException;

    String getCallToAction() throws RemoteException;

    Bundle getExtras() throws RemoteException;

    String getHeadline() throws RemoteException;

    List getImages() throws RemoteException;

    float getMediaContentAspectRatio() throws RemoteException;

    boolean getOverrideClickHandling() throws RemoteException;

    boolean getOverrideImpressionRecording() throws RemoteException;

    String getPrice() throws RemoteException;

    double getStarRating() throws RemoteException;

    String getStore() throws RemoteException;

    zzyo getVideoController() throws RemoteException;

    float getVideoCurrentTime() throws RemoteException;

    float getVideoDuration() throws RemoteException;

    void recordImpression() throws RemoteException;

    void zzc(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException;

    zzaee zzsl() throws RemoteException;

    zzadw zzsm() throws RemoteException;

    IObjectWrapper zzsn() throws RemoteException;

    void zzu(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzue() throws RemoteException;

    IObjectWrapper zzuf() throws RemoteException;

    void zzw(IObjectWrapper iObjectWrapper) throws RemoteException;
}
