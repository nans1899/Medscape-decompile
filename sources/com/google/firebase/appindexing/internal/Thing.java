package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.appindexing.Indexable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class Thing extends AbstractSafeParcelable implements ReflectedParcelable, Indexable {
    public static final Parcelable.Creator<Thing> CREATOR = new zzae();
    private final String type;
    private final Bundle zzay;
    private final zza zzer;
    private final int zzfv;
    private final String zzfw;

    public Thing(int i, Bundle bundle, zza zza2, String str, String str2) {
        this.zzfv = i;
        this.zzay = bundle;
        this.zzer = zza2;
        this.zzfw = str;
        this.type = str2;
        bundle.setClassLoader(getClass().getClassLoader());
    }

    /* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
    public static class zza extends AbstractSafeParcelable implements Indexable.Metadata {
        public static final Parcelable.Creator<zza> CREATOR = new zzw();
        private final int score;
        private final Bundle zzay;
        private final boolean zzeo;
        private final String zzep;

        public zza(boolean z, int i, String str, Bundle bundle) {
            this.zzeo = z;
            this.score = i;
            this.zzep = str;
            this.zzay = bundle == null ? new Bundle() : bundle;
        }

        public final Bundle zze() {
            return this.zzay;
        }

        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeBoolean(parcel, 1, this.zzeo);
            SafeParcelWriter.writeInt(parcel, 2, this.score);
            SafeParcelWriter.writeString(parcel, 3, this.zzep, false);
            SafeParcelWriter.writeBundle(parcel, 4, this.zzay, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("worksOffline: ");
            sb.append(this.zzeo);
            sb.append(", score: ");
            sb.append(this.score);
            if (!this.zzep.isEmpty()) {
                sb.append(", accountEmail: ");
                sb.append(this.zzep);
            }
            Bundle bundle = this.zzay;
            if (bundle != null && !bundle.isEmpty()) {
                sb.append(", Properties { ");
                Thing.zza(this.zzay, sb);
                sb.append("}");
            }
            return sb.toString();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza = (zza) obj;
            return Objects.equal(Boolean.valueOf(this.zzeo), Boolean.valueOf(zza.zzeo)) && Objects.equal(Integer.valueOf(this.score), Integer.valueOf(zza.score)) && Objects.equal(this.zzep, zza.zzep) && Thing.zza(this.zzay, zza.zzay);
        }

        public final int hashCode() {
            return Objects.hashCode(Boolean.valueOf(this.zzeo), Integer.valueOf(this.score), this.zzep, Integer.valueOf(Thing.zzb(this.zzay)));
        }
    }

    public Thing(Bundle bundle, zza zza2, String str, String str2) {
        this.zzfv = 10;
        this.zzay = bundle;
        this.zzer = zza2;
        this.zzfw = str;
        this.type = str2;
    }

    public final zza zzac() {
        return this.zzer;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzay, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzer, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzfw, false);
        SafeParcelWriter.writeString(parcel, 4, this.type, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzfv);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: private */
    public static void zza(Bundle bundle, StringBuilder sb) {
        try {
            Set keySet = bundle.keySet();
            String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
            Arrays.sort(strArr, zzac.zzfy);
            for (String str : strArr) {
                sb.append("{ key: '");
                sb.append(str);
                sb.append("' value: ");
                Object obj = bundle.get(str);
                if (obj == null) {
                    sb.append("<null>");
                } else if (obj.getClass().isArray()) {
                    sb.append("[ ");
                    for (int i = 0; i < Array.getLength(obj); i++) {
                        sb.append("'");
                        sb.append(Array.get(obj, i));
                        sb.append("' ");
                    }
                    sb.append("]");
                } else {
                    sb.append(obj.toString());
                }
                sb.append(" } ");
            }
        } catch (RuntimeException unused) {
            sb.append("<error>");
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type.equals("Thing") ? "Indexable" : this.type);
        sb.append(" { { id: ");
        if (this.zzfw == null) {
            sb.append("<null>");
        } else {
            sb.append("'");
            sb.append(this.zzfw);
            sb.append("'");
        }
        sb.append(" } Properties { ");
        zza(this.zzay, sb);
        sb.append("} ");
        sb.append("Metadata { ");
        sb.append(this.zzer.toString());
        sb.append(" } ");
        sb.append("}");
        return sb.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Thing)) {
            return false;
        }
        Thing thing = (Thing) obj;
        return Objects.equal(Integer.valueOf(this.zzfv), Integer.valueOf(thing.zzfv)) && Objects.equal(this.zzfw, thing.zzfw) && Objects.equal(this.type, thing.type) && Objects.equal(this.zzer, thing.zzer) && zza(this.zzay, thing.zzay);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzfv), this.zzfw, this.type, Integer.valueOf(this.zzer.hashCode()), Integer.valueOf(zzb(this.zzay)));
    }

    /* access modifiers changed from: private */
    public static boolean zza(Bundle bundle, Bundle bundle2) {
        if (bundle.size() != bundle2.size()) {
            return false;
        }
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            Object obj2 = bundle2.get(str);
            if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !zza((Bundle) obj, (Bundle) obj2)) {
                return false;
            }
            if (obj == null && (obj2 != null || !bundle2.containsKey(str))) {
                return false;
            }
            if (obj instanceof boolean[]) {
                if (!(obj2 instanceof boolean[]) || !Arrays.equals((boolean[]) obj, (boolean[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof long[]) {
                if (!(obj2 instanceof long[]) || !Arrays.equals((long[]) obj, (long[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof double[]) {
                if (!(obj2 instanceof double[]) || !Arrays.equals((double[]) obj, (double[]) obj2)) {
                    return false;
                }
            } else if (obj instanceof byte[]) {
                if (!(obj2 instanceof byte[]) || !Arrays.equals((byte[]) obj, (byte[]) obj2)) {
                    return false;
                }
            } else if ((obj instanceof Object[]) && (!(obj2 instanceof Object[]) || !Arrays.equals((Object[]) obj, (Object[]) obj2))) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static int zzb(Bundle bundle) {
        ArrayList arrayList = new ArrayList(bundle.keySet());
        Collections.sort(arrayList);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = arrayList;
        int size = arrayList3.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList3.get(i);
            i++;
            Object obj2 = bundle.get((String) obj);
            if (obj2 instanceof boolean[]) {
                arrayList2.add(Integer.valueOf(Arrays.hashCode((boolean[]) obj2)));
            } else if (obj2 instanceof long[]) {
                arrayList2.add(Integer.valueOf(Arrays.hashCode((long[]) obj2)));
            } else if (obj2 instanceof double[]) {
                arrayList2.add(Integer.valueOf(Arrays.hashCode((double[]) obj2)));
            } else if (obj2 instanceof byte[]) {
                arrayList2.add(Integer.valueOf(Arrays.hashCode((byte[]) obj2)));
            } else if (obj2 instanceof Object[]) {
                arrayList2.add(Integer.valueOf(Arrays.hashCode((Object[]) obj2)));
            } else {
                arrayList2.add(Integer.valueOf(Objects.hashCode(obj2)));
            }
        }
        return Objects.hashCode(arrayList2.toArray());
    }

    static final /* synthetic */ int zzb(String str, String str2) {
        if (str == null) {
            return str2 == null ? 0 : -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }
}
