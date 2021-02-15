package com.google.android.play.core.appupdate.testing;

import android.app.Activity;
import android.content.Context;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateOptions;
import com.google.android.play.core.appupdate.a;
import com.google.android.play.core.common.IntentSenderForResultStarter;
import com.google.android.play.core.install.InstallException;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

public class FakeAppUpdateManager implements AppUpdateManager {
    private final a a;
    private final Context b;
    private int c = 0;
    private int d = 0;
    private boolean e = false;
    private int f = 0;
    private Integer g = null;
    private int h = 0;
    private long i = 0;
    private long j = 0;
    private boolean k = false;
    private boolean l = false;
    private boolean m = false;
    private Integer n;
    private Integer o;

    public FakeAppUpdateManager(Context context) {
        this.a = new a(context);
        this.b = context;
    }

    private final int a() {
        if (!this.e) {
            return 1;
        }
        int i2 = this.c;
        return (i2 == 0 || i2 == 4 || i2 == 5 || i2 == 6) ? 2 : 3;
    }

    private final boolean a(AppUpdateInfo appUpdateInfo, AppUpdateOptions appUpdateOptions) {
        int i2;
        if (!appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions) && (!AppUpdateOptions.defaultOptions(appUpdateOptions.appUpdateType()).equals(appUpdateOptions) || !appUpdateInfo.isUpdateTypeAllowed(appUpdateOptions.appUpdateType()))) {
            return false;
        }
        if (appUpdateOptions.appUpdateType() == 1) {
            this.l = true;
            i2 = 1;
        } else {
            this.k = true;
            i2 = 0;
        }
        this.o = i2;
        return true;
    }

    private final void b() {
        this.a.a(InstallState.a(this.c, this.i, this.j, this.d, this.b.getPackageName()));
    }

    public Task<Void> completeUpdate() {
        int i2 = this.d;
        if (i2 != 0 && i2 != 1) {
            return Tasks.a((Exception) new InstallException(i2));
        }
        int i3 = this.c;
        if (i3 != 11) {
            return i3 == 3 ? Tasks.a((Exception) new InstallException(-8)) : Tasks.a((Exception) new InstallException(-7));
        }
        this.c = 3;
        this.m = true;
        Integer num = 0;
        if (num.equals(this.o)) {
            b();
        }
        return Tasks.a(null);
    }

    public void downloadCompletes() {
        int i2 = this.c;
        if (i2 == 2 || i2 == 1) {
            this.c = 11;
            this.i = 0;
            this.j = 0;
            Integer num = 0;
            if (num.equals(this.o)) {
                b();
                return;
            }
            Integer num2 = 1;
            if (num2.equals(this.o)) {
                completeUpdate();
            }
        }
    }

    public void downloadFails() {
        int i2 = this.c;
        if (i2 == 1 || i2 == 2) {
            this.c = 5;
            Integer num = 0;
            if (num.equals(this.o)) {
                b();
            }
            this.o = null;
            this.l = false;
            this.c = 0;
        }
    }

    public void downloadStarts() {
        if (this.c == 1) {
            this.c = 2;
            Integer num = 0;
            if (num.equals(this.o)) {
                b();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        if (r1.equals(r0.n) == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0058, code lost:
        if (r1.equals(r0.n) == false) goto L_0x0069;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0071  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.play.core.tasks.Task<com.google.android.play.core.appupdate.AppUpdateInfo> getAppUpdateInfo() {
        /*
            r24 = this;
            r0 = r24
            int r1 = r0.d
            r2 = 1
            if (r1 != 0) goto L_0x0008
            goto L_0x0014
        L_0x0008:
            if (r1 == r2) goto L_0x0014
            com.google.android.play.core.install.InstallException r2 = new com.google.android.play.core.install.InstallException
            r2.<init>(r1)
            com.google.android.play.core.tasks.Task r1 = com.google.android.play.core.tasks.Tasks.a((java.lang.Exception) r2)
            return r1
        L_0x0014:
            int r1 = r24.a()
            r3 = 2
            r4 = 0
            r5 = 0
            if (r1 != r3) goto L_0x003f
            int r1 = r0.d
            if (r1 != 0) goto L_0x0022
            goto L_0x0031
        L_0x0022:
            if (r1 != r2) goto L_0x003f
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            java.lang.Integer r6 = r0.n
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L_0x0031
            goto L_0x003f
        L_0x0031:
            android.content.Context r1 = r0.b
            android.content.Intent r6 = new android.content.Intent
            r6.<init>()
            android.app.PendingIntent r1 = android.app.PendingIntent.getBroadcast(r1, r4, r6, r4)
            r21 = r1
            goto L_0x0041
        L_0x003f:
            r21 = r5
        L_0x0041:
            int r1 = r24.a()
            if (r1 != r3) goto L_0x0069
            int r1 = r0.d
            if (r1 != 0) goto L_0x004c
            goto L_0x005b
        L_0x004c:
            if (r1 != r2) goto L_0x0069
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r6 = r0.n
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L_0x005b
            goto L_0x0069
        L_0x005b:
            android.content.Context r1 = r0.b
            android.content.Intent r6 = new android.content.Intent
            r6.<init>()
            android.app.PendingIntent r1 = android.app.PendingIntent.getBroadcast(r1, r4, r6, r4)
            r20 = r1
            goto L_0x006b
        L_0x0069:
            r20 = r5
        L_0x006b:
            int r1 = r24.a()
            if (r1 != r3) goto L_0x0094
            int r1 = r0.d
            if (r1 != 0) goto L_0x0076
            goto L_0x0079
        L_0x0076:
            if (r1 == r2) goto L_0x0079
            goto L_0x0094
        L_0x0079:
            android.content.Context r1 = r0.b
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            android.app.PendingIntent r5 = android.app.PendingIntent.getBroadcast(r1, r4, r2, r4)
            android.content.Context r1 = r0.b
            android.content.Intent r2 = new android.content.Intent
            r2.<init>()
            android.app.PendingIntent r1 = android.app.PendingIntent.getBroadcast(r1, r4, r2, r4)
            r22 = r1
            r23 = r5
            goto L_0x0098
        L_0x0094:
            r22 = r5
            r23 = r22
        L_0x0098:
            android.content.Context r1 = r0.b
            java.lang.String r6 = r1.getPackageName()
            int r7 = r0.f
            int r8 = r24.a()
            int r9 = r0.c
            java.lang.Integer r10 = r0.g
            int r11 = r0.h
            long r12 = r0.i
            long r14 = r0.j
            r16 = 0
            r18 = 0
            com.google.android.play.core.appupdate.AppUpdateInfo r1 = com.google.android.play.core.appupdate.AppUpdateInfo.a(r6, r7, r8, r9, r10, r11, r12, r14, r16, r18, r20, r21, r22, r23)
            com.google.android.play.core.tasks.Task r1 = com.google.android.play.core.tasks.Tasks.a(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.appupdate.testing.FakeAppUpdateManager.getAppUpdateInfo():com.google.android.play.core.tasks.Task");
    }

    public Integer getPartiallyAllowedUpdateType() {
        return this.n;
    }

    public Integer getTypeForUpdateInProgress() {
        return this.o;
    }

    public void installCompletes() {
        if (this.c == 3) {
            this.c = 4;
            this.e = false;
            this.f = 0;
            this.g = null;
            this.h = 0;
            this.i = 0;
            this.j = 0;
            this.l = false;
            this.m = false;
            Integer num = 0;
            if (num.equals(this.o)) {
                b();
            }
            this.o = null;
            this.c = 0;
        }
    }

    public void installFails() {
        if (this.c == 3) {
            this.c = 5;
            Integer num = 0;
            if (num.equals(this.o)) {
                b();
            }
            this.o = null;
            this.m = false;
            this.l = false;
            this.c = 0;
        }
    }

    public boolean isConfirmationDialogVisible() {
        return this.k;
    }

    public boolean isImmediateFlowVisible() {
        return this.l;
    }

    public boolean isInstallSplashScreenVisible() {
        return this.m;
    }

    public void registerListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.a.a(installStateUpdatedListener);
    }

    public void setBytesDownloaded(long j2) {
        if (this.c == 2 && j2 <= this.j) {
            this.i = j2;
            Integer num = 0;
            if (num.equals(this.o)) {
                b();
            }
        }
    }

    public void setClientVersionStalenessDays(Integer num) {
        if (this.e) {
            this.g = num;
        }
    }

    public void setInstallErrorCode(int i2) {
        this.d = i2;
    }

    public void setPartiallyAllowedUpdateType(Integer num) {
        this.n = num;
        this.d = 1;
    }

    public void setTotalBytesToDownload(long j2) {
        if (this.c == 2) {
            this.j = j2;
            Integer num = 0;
            if (num.equals(this.o)) {
                b();
            }
        }
    }

    public void setUpdateAvailable(int i2) {
        this.e = true;
        this.f = i2;
    }

    public void setUpdateNotAvailable() {
        this.e = false;
        this.g = null;
    }

    public void setUpdatePriority(int i2) {
        if (this.e) {
            this.h = i2;
        }
    }

    public final Task<Integer> startUpdateFlow(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions) {
        return a(appUpdateInfo, appUpdateOptions) ? Tasks.a(-1) : Tasks.a((Exception) new InstallException(-6));
    }

    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int i2, Activity activity, int i3) {
        return a(appUpdateInfo, AppUpdateOptions.newBuilder(i2).build());
    }

    public boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, int i2, IntentSenderForResultStarter intentSenderForResultStarter, int i3) {
        return a(appUpdateInfo, AppUpdateOptions.newBuilder(i2).build());
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, Activity activity, AppUpdateOptions appUpdateOptions, int i2) {
        return a(appUpdateInfo, appUpdateOptions);
    }

    public final boolean startUpdateFlowForResult(AppUpdateInfo appUpdateInfo, IntentSenderForResultStarter intentSenderForResultStarter, AppUpdateOptions appUpdateOptions, int i2) {
        return a(appUpdateInfo, appUpdateOptions);
    }

    public void unregisterListener(InstallStateUpdatedListener installStateUpdatedListener) {
        this.a.b(installStateUpdatedListener);
    }

    public void userAcceptsUpdate() {
        if (this.k || this.l) {
            this.k = false;
            this.c = 1;
            Integer num = 0;
            if (num.equals(this.o)) {
                b();
            }
        }
    }

    public void userCancelsDownload() {
        int i2 = this.c;
        if (i2 == 1 || i2 == 2) {
            this.c = 6;
            Integer num = 0;
            if (num.equals(this.o)) {
                b();
            }
            this.o = null;
            this.l = false;
            this.c = 0;
        }
    }

    public void userRejectsUpdate() {
        if (this.k || this.l) {
            this.k = false;
            this.l = false;
            this.o = null;
            this.c = 0;
        }
    }
}
