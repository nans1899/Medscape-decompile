package com.google.android.gms.internal.ads;

import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzdjj implements zzeoy<zzdgw<JSONObject>> {
    public static zzdgw<JSONObject> zza(zzaxt zzaxt, Object obj, zzdhr zzdhr, zzdio zzdio, zzeos<zzdhl> zzeos, zzeos<zzdhv> zzeos2, zzeos<zzdhz> zzeos3, zzeos<zzdif> zzeos4, zzeos<zzdik> zzeos5, zzeos<zzdit> zzeos6, zzeos<zzdix> zzeos7, zzeos<zzdjl> zzeos8, Executor executor, ScheduledExecutorService scheduledExecutorService) {
        HashSet hashSet = new HashSet();
        hashSet.add((zzdig) obj);
        hashSet.add(zzdhr);
        hashSet.add(zzdio);
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvk)).booleanValue()) {
            hashSet.add(zzeos.get());
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvl)).booleanValue()) {
            hashSet.add(zzeos2.get());
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvm)).booleanValue()) {
            hashSet.add(zzeos3.get());
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvn)).booleanValue()) {
            hashSet.add(zzeos4.get());
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvr)).booleanValue()) {
            hashSet.add(new zzdfi(zzeos6.get(), ((Long) zzwm.zzpx().zzd(zzabb.zzcsm)).longValue(), scheduledExecutorService));
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvt)).booleanValue()) {
            hashSet.add(zzeos7.get());
        }
        if (((Boolean) zzwm.zzpx().zzd(zzabb.zzcvu)).booleanValue()) {
            hashSet.add(zzeos8.get());
        }
        return (zzdgw) zzepe.zza(new zzdgw(executor, hashSet), "Cannot return null from a non-@Nullable @Provides method");
    }

    public final /* synthetic */ Object get() {
        throw new NoSuchMethodError();
    }
}
