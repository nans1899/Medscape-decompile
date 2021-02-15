package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzge;
import com.google.android.gms.internal.vision.zzgh;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-vision-common@@19.1.2 */
public abstract class zzge<MessageType extends zzge<MessageType, BuilderType>, BuilderType extends zzgh<MessageType, BuilderType>> implements zzjn {
    protected int zzte = 0;

    public final zzgs zzee() {
        try {
            zzha zzaw = zzgs.zzaw(zzgz());
            zzb(zzaw.zzfq());
            return zzaw.zzfp();
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
            byte[] bArr = new byte[zzgz()];
            zzhl zze = zzhl.zze(bArr);
            zzb(zze);
            zze.zzgb();
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
    public int zzef() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public void zzak(int i) {
        throw new UnsupportedOperationException();
    }

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzie.checkNotNull(iterable);
        if (iterable instanceof zziu) {
            List<?> zzhs = ((zziu) iterable).zzhs();
            zziu zziu = (zziu) list;
            int size = list.size();
            for (Object next : zzhs) {
                if (next == null) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(zziu.size() - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    for (int size2 = zziu.size() - 1; size2 >= size; size2--) {
                        zziu.remove(size2);
                    }
                    throw new NullPointerException(sb2);
                } else if (next instanceof zzgs) {
                    zziu.zzc((zzgs) next);
                } else {
                    zziu.add((String) next);
                }
            }
        } else if (iterable instanceof zzjz) {
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
