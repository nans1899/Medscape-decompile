package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public interface zzang extends IInterface {
    void destroy() throws RemoteException;

    Bundle getInterstitialAdapterInfo() throws RemoteException;

    zzyo getVideoController() throws RemoteException;

    boolean isInitialized() throws RemoteException;

    void pause() throws RemoteException;

    void resume() throws RemoteException;

    void setImmersiveMode(boolean z) throws RemoteException;

    void showInterstitial() throws RemoteException;

    void showVideo() throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzais zzais, List<zzaja> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzaur zzaur, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzanh zzanh) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzaur zzaur, String str2) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, String str2, zzanh zzanh) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, String str2, zzanh zzanh, zzadu zzadu, List<String> list) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzvn zzvn, zzvg zzvg, String str, zzanh zzanh) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, zzvn zzvn, zzvg zzvg, String str, String str2, zzanh zzanh) throws RemoteException;

    void zza(zzvg zzvg, String str) throws RemoteException;

    void zza(zzvg zzvg, String str, String str2) throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzanh zzanh) throws RemoteException;

    void zzc(IObjectWrapper iObjectWrapper, zzvg zzvg, String str, zzanh zzanh) throws RemoteException;

    void zzs(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzt(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzts() throws RemoteException;

    zzano zztt() throws RemoteException;

    zzanp zztu() throws RemoteException;

    Bundle zztv() throws RemoteException;

    Bundle zztw() throws RemoteException;

    boolean zztx() throws RemoteException;

    zzafa zzty() throws RemoteException;

    zzanu zztz() throws RemoteException;

    zzapv zzua() throws RemoteException;

    zzapv zzub() throws RemoteException;
}
