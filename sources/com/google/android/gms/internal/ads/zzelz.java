package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
interface zzelz {
    int getTag();

    double readDouble() throws IOException;

    float readFloat() throws IOException;

    String readString() throws IOException;

    void readStringList(List<String> list) throws IOException;

    <T> T zza(zzemf<T> zzemf, zzejm zzejm) throws IOException;

    <T> void zza(List<T> list, zzemf<T> zzemf, zzejm zzejm) throws IOException;

    <K, V> void zza(Map<K, V> map, zzele<K, V> zzele, zzejm zzejm) throws IOException;

    void zzaa(List<Long> list) throws IOException;

    @Deprecated
    <T> T zzb(zzemf<T> zzemf, zzejm zzejm) throws IOException;

    @Deprecated
    <T> void zzb(List<T> list, zzemf<T> zzemf, zzejm zzejm) throws IOException;

    long zzbeq() throws IOException;

    long zzber() throws IOException;

    int zzbes() throws IOException;

    long zzbet() throws IOException;

    int zzbeu() throws IOException;

    boolean zzbev() throws IOException;

    String zzbew() throws IOException;

    zzeip zzbex() throws IOException;

    int zzbey() throws IOException;

    int zzbez() throws IOException;

    int zzbfa() throws IOException;

    long zzbfb() throws IOException;

    int zzbfc() throws IOException;

    long zzbfd() throws IOException;

    int zzbfn() throws IOException;

    boolean zzbfo() throws IOException;

    void zzl(List<Double> list) throws IOException;

    void zzm(List<Float> list) throws IOException;

    void zzn(List<Long> list) throws IOException;

    void zzo(List<Long> list) throws IOException;

    void zzp(List<Integer> list) throws IOException;

    void zzq(List<Long> list) throws IOException;

    void zzr(List<Integer> list) throws IOException;

    void zzs(List<Boolean> list) throws IOException;

    void zzt(List<String> list) throws IOException;

    void zzu(List<zzeip> list) throws IOException;

    void zzv(List<Integer> list) throws IOException;

    void zzw(List<Integer> list) throws IOException;

    void zzx(List<Integer> list) throws IOException;

    void zzy(List<Long> list) throws IOException;

    void zzz(List<Integer> list) throws IOException;
}
