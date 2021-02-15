package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
interface zzkc {
    int getTag();

    double readDouble() throws IOException;

    float readFloat() throws IOException;

    String readString() throws IOException;

    void readStringList(List<String> list) throws IOException;

    <T> T zza(zzkf<T> zzkf, zzho zzho) throws IOException;

    <T> T zza(Class<T> cls, zzho zzho) throws IOException;

    void zza(List<Double> list) throws IOException;

    <T> void zza(List<T> list, zzkf<T> zzkf, zzho zzho) throws IOException;

    <K, V> void zza(Map<K, V> map, zzje<K, V> zzje, zzho zzho) throws IOException;

    @Deprecated
    <T> T zzb(Class<T> cls, zzho zzho) throws IOException;

    void zzb(List<Float> list) throws IOException;

    @Deprecated
    <T> void zzb(List<T> list, zzkf<T> zzkf, zzho zzho) throws IOException;

    @Deprecated
    <T> T zzc(zzkf<T> zzkf, zzho zzho) throws IOException;

    void zzc(List<Long> list) throws IOException;

    void zzd(List<Long> list) throws IOException;

    void zze(List<Integer> list) throws IOException;

    int zzeo() throws IOException;

    boolean zzep() throws IOException;

    long zzeq() throws IOException;

    long zzer() throws IOException;

    int zzes() throws IOException;

    long zzet() throws IOException;

    int zzeu() throws IOException;

    boolean zzev() throws IOException;

    String zzew() throws IOException;

    zzgs zzex() throws IOException;

    int zzey() throws IOException;

    int zzez() throws IOException;

    void zzf(List<Long> list) throws IOException;

    int zzfa() throws IOException;

    long zzfb() throws IOException;

    int zzfc() throws IOException;

    long zzfd() throws IOException;

    void zzg(List<Integer> list) throws IOException;

    void zzh(List<Boolean> list) throws IOException;

    void zzi(List<String> list) throws IOException;

    void zzj(List<zzgs> list) throws IOException;

    void zzk(List<Integer> list) throws IOException;

    void zzl(List<Integer> list) throws IOException;

    void zzm(List<Integer> list) throws IOException;

    void zzn(List<Long> list) throws IOException;

    void zzo(List<Integer> list) throws IOException;

    void zzp(List<Long> list) throws IOException;
}
