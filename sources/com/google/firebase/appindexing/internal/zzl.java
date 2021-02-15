package com.google.firebase.appindexing.internal;

import com.google.android.gms.common.api.internal.TaskApiCall;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
final class zzl extends TaskApiCall<zze, Void> {
    final /* synthetic */ zzj zzfh;

    zzl(zzj zzj) {
        this.zzfh = zzj;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: com.google.firebase.appindexing.internal.zzj} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v23, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: com.google.firebase.appindexing.internal.zzj} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void doExecute(com.google.android.gms.common.api.Api.AnyClient r6, com.google.android.gms.tasks.TaskCompletionSource r7) throws android.os.RemoteException {
        /*
            r5 = this;
            com.google.firebase.appindexing.internal.zze r6 = (com.google.firebase.appindexing.internal.zze) r6
            android.os.IInterface r6 = r6.getService()
            com.google.firebase.appindexing.internal.zzr r6 = (com.google.firebase.appindexing.internal.zzr) r6
            com.google.firebase.appindexing.internal.zzo r0 = new com.google.firebase.appindexing.internal.zzo
            r0.<init>(r5, r7)
            com.google.firebase.appindexing.internal.zzj r1 = r5.zzfh
            com.google.firebase.appindexing.internal.zzy r1 = r1.zzfc
            com.google.firebase.appindexing.internal.zzg r6 = r6.zza(r0, r1)
            r0 = 2
            if (r6 != 0) goto L_0x001c
            r6 = 2
            goto L_0x001e
        L_0x001c:
            int r6 = r6.status
        L_0x001e:
            r1 = 3
            r2 = 0
            r3 = 1
            r4 = 0
            if (r6 != r1) goto L_0x006f
            java.lang.String r6 = "Queue was full. API call will be retried."
            r1 = 4
            boolean r1 = com.google.firebase.appindexing.internal.zzt.isLoggable(r1)
            if (r1 == 0) goto L_0x0032
            java.lang.String r1 = "FirebaseAppIndex"
            android.util.Log.i(r1, r6)
        L_0x0032:
            boolean r6 = r7.trySetResult(r4)
            if (r6 == 0) goto L_0x00dd
            com.google.firebase.appindexing.internal.zzj r6 = r5.zzfh
            com.google.firebase.appindexing.internal.zzk r6 = r6.zzfe
            java.util.Queue r6 = r6.zzff
            monitor-enter(r6)
            com.google.firebase.appindexing.internal.zzj r7 = r5.zzfh     // Catch:{ all -> 0x006c }
            com.google.firebase.appindexing.internal.zzk r7 = r7.zzfe     // Catch:{ all -> 0x006c }
            int r7 = r7.zzfg     // Catch:{ all -> 0x006c }
            if (r7 != 0) goto L_0x0063
            com.google.firebase.appindexing.internal.zzj r7 = r5.zzfh     // Catch:{ all -> 0x006c }
            com.google.firebase.appindexing.internal.zzk r7 = r7.zzfe     // Catch:{ all -> 0x006c }
            java.util.Queue r7 = r7.zzff     // Catch:{ all -> 0x006c }
            java.lang.Object r7 = r7.peek()     // Catch:{ all -> 0x006c }
            r4 = r7
            com.google.firebase.appindexing.internal.zzj r4 = (com.google.firebase.appindexing.internal.zzj) r4     // Catch:{ all -> 0x006c }
            com.google.firebase.appindexing.internal.zzj r7 = r5.zzfh     // Catch:{ all -> 0x006c }
            if (r4 != r7) goto L_0x005f
            r2 = 1
        L_0x005f:
            com.google.android.gms.common.internal.Preconditions.checkState(r2)     // Catch:{ all -> 0x006c }
            goto L_0x006a
        L_0x0063:
            com.google.firebase.appindexing.internal.zzj r7 = r5.zzfh     // Catch:{ all -> 0x006c }
            com.google.firebase.appindexing.internal.zzk r7 = r7.zzfe     // Catch:{ all -> 0x006c }
            int unused = r7.zzfg = r0     // Catch:{ all -> 0x006c }
        L_0x006a:
            monitor-exit(r6)     // Catch:{ all -> 0x006c }
            goto L_0x00dd
        L_0x006c:
            r7 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x006c }
            throw r7
        L_0x006f:
            if (r6 == r3) goto L_0x00a6
            r0 = 41
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r0)
            java.lang.String r0 = "API call failed. Status code: "
            r1.append(r0)
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0 = 6
            boolean r0 = com.google.firebase.appindexing.internal.zzt.isLoggable(r0)
            if (r0 == 0) goto L_0x0090
            java.lang.String r0 = "FirebaseAppIndex"
            android.util.Log.e(r0, r6)
        L_0x0090:
            boolean r6 = r7.trySetResult(r4)
            if (r6 == 0) goto L_0x00a6
            com.google.firebase.appindexing.internal.zzj r6 = r5.zzfh
            com.google.android.gms.tasks.TaskCompletionSource r6 = r6.zzfd
            com.google.firebase.appindexing.FirebaseAppIndexingException r7 = new com.google.firebase.appindexing.FirebaseAppIndexingException
            java.lang.String r0 = "Indexing error."
            r7.<init>(r0)
            r6.setException(r7)
        L_0x00a6:
            com.google.firebase.appindexing.internal.zzj r6 = r5.zzfh
            com.google.firebase.appindexing.internal.zzk r6 = r6.zzfe
            java.util.Queue r6 = r6.zzff
            monitor-enter(r6)
            com.google.firebase.appindexing.internal.zzj r7 = r5.zzfh     // Catch:{ all -> 0x00e3 }
            com.google.firebase.appindexing.internal.zzk r7 = r7.zzfe     // Catch:{ all -> 0x00e3 }
            java.util.Queue r7 = r7.zzff     // Catch:{ all -> 0x00e3 }
            java.lang.Object r7 = r7.poll()     // Catch:{ all -> 0x00e3 }
            com.google.firebase.appindexing.internal.zzj r7 = (com.google.firebase.appindexing.internal.zzj) r7     // Catch:{ all -> 0x00e3 }
            com.google.firebase.appindexing.internal.zzj r0 = r5.zzfh     // Catch:{ all -> 0x00e3 }
            if (r7 != r0) goto L_0x00c2
            goto L_0x00c3
        L_0x00c2:
            r3 = 0
        L_0x00c3:
            com.google.android.gms.common.internal.Preconditions.checkState(r3)     // Catch:{ all -> 0x00e3 }
            com.google.firebase.appindexing.internal.zzj r7 = r5.zzfh     // Catch:{ all -> 0x00e3 }
            com.google.firebase.appindexing.internal.zzk r7 = r7.zzfe     // Catch:{ all -> 0x00e3 }
            java.util.Queue r7 = r7.zzff     // Catch:{ all -> 0x00e3 }
            java.lang.Object r7 = r7.peek()     // Catch:{ all -> 0x00e3 }
            r4 = r7
            com.google.firebase.appindexing.internal.zzj r4 = (com.google.firebase.appindexing.internal.zzj) r4     // Catch:{ all -> 0x00e3 }
            com.google.firebase.appindexing.internal.zzj r7 = r5.zzfh     // Catch:{ all -> 0x00e3 }
            com.google.firebase.appindexing.internal.zzk r7 = r7.zzfe     // Catch:{ all -> 0x00e3 }
            int unused = r7.zzfg = r2     // Catch:{ all -> 0x00e3 }
            monitor-exit(r6)     // Catch:{ all -> 0x00e3 }
        L_0x00dd:
            if (r4 == 0) goto L_0x00e2
            r4.execute()
        L_0x00e2:
            return
        L_0x00e3:
            r7 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x00e3 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.appindexing.internal.zzl.doExecute(com.google.android.gms.common.api.Api$AnyClient, com.google.android.gms.tasks.TaskCompletionSource):void");
    }
}
