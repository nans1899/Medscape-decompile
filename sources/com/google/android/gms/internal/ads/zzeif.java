package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzeie;
import com.google.android.gms.internal.ads.zzeif;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public abstract class zzeif<MessageType extends zzeif<MessageType, BuilderType>, BuilderType extends zzeie<MessageType, BuilderType>> implements zzelj {
    protected int zziee = 0;

    public final zzeip zzbdw() {
        try {
            zzeix zzfw = zzeip.zzfw(zzbgh());
            zzb(zzfw.zzbeo());
            return zzfw.zzben();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzbgh()];
            zzejj zzw = zzejj.zzw(bArr);
            zzb(zzw);
            zzw.zzbfq();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "byte array".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("byte array");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public int zzbdx() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public void zzfq(int i) {
        throw new UnsupportedOperationException();
    }

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzekb.checkNotNull(iterable);
        if (iterable instanceof zzeku) {
            List<?> zzbhj = ((zzeku) iterable).zzbhj();
            zzeku zzeku = (zzeku) list;
            int size = list.size();
            for (Object next : zzbhj) {
                if (next == null) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(zzeku.size() - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    for (int size2 = zzeku.size() - 1; size2 >= size; size2--) {
                        zzeku.remove(size2);
                    }
                    throw new NullPointerException(sb2);
                } else if (next instanceof zzeip) {
                    zzeku.zzaj((zzeip) next);
                } else {
                    zzeku.add((String) next);
                }
            }
        } else if (iterable instanceof zzelv) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(list.size() + ((Collection) iterable).size());
            }
            int size3 = list.size();
            for (T next2 : iterable) {
                if (next2 == null) {
                    StringBuilder sb3 = new StringBuilder(37);
                    sb3.append("Element at index ");
                    sb3.append(list.size() - size3);
                    sb3.append(" is null.");
                    String sb4 = sb3.toString();
                    for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                        list.remove(size4);
                    }
                    throw new NullPointerException(sb4);
                }
                list.add(next2);
            }
        }
    }
}
