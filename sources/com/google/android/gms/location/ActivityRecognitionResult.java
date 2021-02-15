package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-location@@17.1.0 */
public class ActivityRecognitionResult extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<ActivityRecognitionResult> CREATOR = new zzf();
    private List<DetectedActivity> zza;
    private long zzb;
    private long zzc;
    private int zzd;
    private Bundle zze;

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2) {
        this(list, j, j2, 0, (Bundle) null);
    }

    public ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2) {
        this(detectedActivity, j, j2, 0, (Bundle) null);
    }

    private ActivityRecognitionResult(DetectedActivity detectedActivity, long j, long j2, int i, Bundle bundle) {
        this((List<DetectedActivity>) Collections.singletonList(detectedActivity), j, j2, 0, (Bundle) null);
    }

    public static boolean hasResult(Intent intent) {
        if (intent == null) {
            return false;
        }
        if (intent == null ? false : intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
            return true;
        }
        List<ActivityRecognitionResult> zza2 = zza(intent);
        return zza2 != null && !zza2.isEmpty();
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x002a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.location.ActivityRecognitionResult extractResult(android.content.Intent r3) {
        /*
            boolean r0 = hasResult(r3)
            r1 = 0
            if (r0 == 0) goto L_0x0027
            android.os.Bundle r0 = r3.getExtras()
            java.lang.String r2 = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT"
            java.lang.Object r0 = r0.get(r2)
            boolean r2 = r0 instanceof byte[]
            if (r2 == 0) goto L_0x0020
            byte[] r0 = (byte[]) r0
            android.os.Parcelable$Creator<com.google.android.gms.location.ActivityRecognitionResult> r2 = CREATOR
            com.google.android.gms.common.internal.safeparcel.SafeParcelable r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer.deserializeFromBytes(r0, r2)
            com.google.android.gms.location.ActivityRecognitionResult r0 = (com.google.android.gms.location.ActivityRecognitionResult) r0
            goto L_0x0028
        L_0x0020:
            boolean r2 = r0 instanceof com.google.android.gms.location.ActivityRecognitionResult
            if (r2 == 0) goto L_0x0027
            com.google.android.gms.location.ActivityRecognitionResult r0 = (com.google.android.gms.location.ActivityRecognitionResult) r0
            goto L_0x0028
        L_0x0027:
            r0 = r1
        L_0x0028:
            if (r0 == 0) goto L_0x002b
            return r0
        L_0x002b:
            java.util.List r3 = zza(r3)
            if (r3 == 0) goto L_0x0045
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x0038
            goto L_0x0045
        L_0x0038:
            int r0 = r3.size()
            int r0 = r0 + -1
            java.lang.Object r3 = r3.get(r0)
            com.google.android.gms.location.ActivityRecognitionResult r3 = (com.google.android.gms.location.ActivityRecognitionResult) r3
            return r3
        L_0x0045:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityRecognitionResult.extractResult(android.content.Intent):com.google.android.gms.location.ActivityRecognitionResult");
    }

    private static List<ActivityRecognitionResult> zza(Intent intent) {
        if (!(intent == null ? false : intent.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST"))) {
            return null;
        }
        return SafeParcelableSerializer.deserializeIterableFromIntentExtra(intent, "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST", CREATOR);
    }

    public DetectedActivity getMostProbableActivity() {
        return this.zza.get(0);
    }

    public int getActivityConfidence(int i) {
        for (DetectedActivity next : this.zza) {
            if (next.getType() == i) {
                return next.getConfidence();
            }
        }
        return 0;
    }

    public List<DetectedActivity> getProbableActivities() {
        return this.zza;
    }

    public long getTime() {
        return this.zzb;
    }

    public long getElapsedRealtimeMillis() {
        return this.zzc;
    }

    public String toString() {
        String valueOf = String.valueOf(this.zza);
        long j = this.zzb;
        long j2 = this.zzc;
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 124);
        sb.append("ActivityRecognitionResult [probableActivities=");
        sb.append(valueOf);
        sb.append(", timeMillis=");
        sb.append(j);
        sb.append(", elapsedRealtimeMillis=");
        sb.append(j2);
        sb.append("]");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ActivityRecognitionResult activityRecognitionResult = (ActivityRecognitionResult) obj;
            return this.zzb == activityRecognitionResult.zzb && this.zzc == activityRecognitionResult.zzc && this.zzd == activityRecognitionResult.zzd && Objects.equal(this.zza, activityRecognitionResult.zza) && zza(this.zze, activityRecognitionResult.zze);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a9 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0023 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zza(android.os.Bundle r9, android.os.Bundle r10) {
        /*
            r0 = 1
            if (r9 != 0) goto L_0x0006
            if (r10 != 0) goto L_0x0006
            return r0
        L_0x0006:
            r1 = 0
            if (r9 != 0) goto L_0x000b
            if (r10 != 0) goto L_0x000f
        L_0x000b:
            if (r9 == 0) goto L_0x0010
            if (r10 != 0) goto L_0x0010
        L_0x000f:
            return r1
        L_0x0010:
            int r2 = r9.size()
            int r3 = r10.size()
            if (r2 == r3) goto L_0x001b
            return r1
        L_0x001b:
            java.util.Set r2 = r9.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0023:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00b9
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = r10.containsKey(r3)
            if (r4 != 0) goto L_0x0036
            return r1
        L_0x0036:
            java.lang.Object r4 = r9.get(r3)
            if (r4 != 0) goto L_0x0043
            java.lang.Object r3 = r10.get(r3)
            if (r3 == 0) goto L_0x0023
            return r1
        L_0x0043:
            java.lang.Object r4 = r9.get(r3)
            boolean r4 = r4 instanceof android.os.Bundle
            if (r4 == 0) goto L_0x005a
            android.os.Bundle r4 = r9.getBundle(r3)
            android.os.Bundle r3 = r10.getBundle(r3)
            boolean r3 = zza(r4, r3)
            if (r3 != 0) goto L_0x0023
            return r1
        L_0x005a:
            java.lang.Object r4 = r9.get(r3)
            java.lang.Class r4 = r4.getClass()
            boolean r4 = r4.isArray()
            if (r4 == 0) goto L_0x00aa
            java.lang.Object r4 = r10.get(r3)
            if (r4 == 0) goto L_0x00a9
            java.lang.Object r4 = r10.get(r3)
            java.lang.Class r4 = r4.getClass()
            boolean r4 = r4.isArray()
            if (r4 != 0) goto L_0x007d
            goto L_0x00a9
        L_0x007d:
            java.lang.Object r4 = r9.get(r3)
            java.lang.Object r3 = r10.get(r3)
            int r5 = java.lang.reflect.Array.getLength(r4)
            int r6 = java.lang.reflect.Array.getLength(r3)
            if (r5 == r6) goto L_0x0091
        L_0x008f:
            r3 = 0
            goto L_0x00a7
        L_0x0091:
            r6 = 0
        L_0x0092:
            if (r6 >= r5) goto L_0x00a6
            java.lang.Object r7 = java.lang.reflect.Array.get(r4, r6)
            java.lang.Object r8 = java.lang.reflect.Array.get(r3, r6)
            boolean r7 = com.google.android.gms.common.internal.Objects.equal(r7, r8)
            if (r7 != 0) goto L_0x00a3
            goto L_0x008f
        L_0x00a3:
            int r6 = r6 + 1
            goto L_0x0092
        L_0x00a6:
            r3 = 1
        L_0x00a7:
            if (r3 != 0) goto L_0x0023
        L_0x00a9:
            return r1
        L_0x00aa:
            java.lang.Object r4 = r9.get(r3)
            java.lang.Object r3 = r10.get(r3)
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L_0x0023
            return r1
        L_0x00b9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.location.ActivityRecognitionResult.zza(android.os.Bundle, android.os.Bundle):boolean");
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzb), Long.valueOf(this.zzc), Integer.valueOf(this.zzd), this.zza, this.zze);
    }

    public ActivityRecognitionResult(List<DetectedActivity> list, long j, long j2, int i, Bundle bundle) {
        boolean z = true;
        Preconditions.checkArgument(list != null && list.size() > 0, "Must have at least 1 detected activity");
        Preconditions.checkArgument((j <= 0 || j2 <= 0) ? false : z, "Must set times");
        this.zza = list;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = i;
        this.zze = bundle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeLong(parcel, 2, this.zzb);
        SafeParcelWriter.writeLong(parcel, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel, 4, this.zzd);
        SafeParcelWriter.writeBundle(parcel, 5, this.zze, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
