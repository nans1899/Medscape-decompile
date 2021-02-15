package io.branch.referral;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.RemoteException;
import com.android.installreferrer.api.InstallReferrerClient;
import com.android.installreferrer.api.InstallReferrerStateListener;
import com.android.installreferrer.api.ReferrerDetails;

public class InstallListener extends BroadcastReceiver {
    private static IInstallReferrerEvents callback_ = null;
    private static String installID_ = "bnc_no_value";
    private static boolean isReferrerClientAvailable;
    private static boolean isWaitingForReferrer;
    static boolean unReportedReferrerAvailable;

    interface IInstallReferrerEvents {
        void onInstallReferrerEventsFinished();
    }

    public static void captureInstallReferrer(Context context, long j, IInstallReferrerEvents iInstallReferrerEvents) {
        callback_ = iInstallReferrerEvents;
        if (unReportedReferrerAvailable) {
            reportInstallReferrer();
            return;
        }
        isWaitingForReferrer = true;
        isReferrerClientAvailable = new ReferrerClientWrapper(context).getReferrerUsingReferrerClient();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                InstallListener.reportInstallReferrer();
            }
        }, j);
    }

    public void onReceive(Context context, Intent intent) {
        processReferrerInfo(context, intent.getStringExtra("referrer"), 0, 0);
        if (isWaitingForReferrer && !isReferrerClientAvailable) {
            reportInstallReferrer();
        }
    }

    /* access modifiers changed from: private */
    public static void onReferrerClientFinished(Context context, String str, long j, long j2) {
        processReferrerInfo(context, str, j, j2);
        if (isWaitingForReferrer) {
            reportInstallReferrer();
        }
    }

    /* access modifiers changed from: private */
    public static void onReferrerClientError() {
        isReferrerClientAvailable = false;
    }

    private static class ReferrerClientWrapper {
        /* access modifiers changed from: private */
        public Context context_;
        /* access modifiers changed from: private */
        public Object mReferrerClient;

        private ReferrerClientWrapper(Context context) {
            this.context_ = context;
        }

        /* access modifiers changed from: private */
        public boolean getReferrerUsingReferrerClient() {
            try {
                InstallReferrerClient build = InstallReferrerClient.newBuilder(this.context_).build();
                this.mReferrerClient = build;
                build.startConnection(new InstallReferrerStateListener() {
                    public void onInstallReferrerSetupFinished(int i) {
                        long j;
                        long j2;
                        String str;
                        if (i == 0) {
                            try {
                                if (ReferrerClientWrapper.this.mReferrerClient != null) {
                                    ReferrerDetails installReferrer = ((InstallReferrerClient) ReferrerClientWrapper.this.mReferrerClient).getInstallReferrer();
                                    if (installReferrer != null) {
                                        String installReferrer2 = installReferrer.getInstallReferrer();
                                        long referrerClickTimestampSeconds = installReferrer.getReferrerClickTimestampSeconds();
                                        j = installReferrer.getInstallBeginTimestampSeconds();
                                        j2 = referrerClickTimestampSeconds;
                                        str = installReferrer2;
                                    } else {
                                        j = 0;
                                        str = null;
                                        j2 = 0;
                                    }
                                    InstallListener.onReferrerClientFinished(ReferrerClientWrapper.this.context_, str, j2, j);
                                }
                            } catch (RemoteException e) {
                                PrefHelper.Debug("BranchSDK", e.getMessage());
                                InstallListener.onReferrerClientError();
                            }
                        } else if (i == 1) {
                            InstallListener.onReferrerClientError();
                        } else if (i == 2) {
                            InstallListener.onReferrerClientError();
                        }
                    }

                    public void onInstallReferrerServiceDisconnected() {
                        InstallListener.onReferrerClientError();
                    }
                });
                return true;
            } catch (Throwable th) {
                PrefHelper.Debug("BranchSDK", th.getMessage());
                return false;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0052 A[Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0061 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void processReferrerInfo(android.content.Context r6, java.lang.String r7, long r8, long r10) {
        /*
            java.lang.String r0 = "="
            java.lang.String r1 = "UTF-8"
            io.branch.referral.PrefHelper r6 = io.branch.referral.PrefHelper.getInstance(r6)
            r2 = 0
            int r4 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r4 <= 0) goto L_0x0013
            java.lang.String r4 = "bnc_referrer_click_ts"
            r6.setLong(r4, r8)
        L_0x0013:
            int r8 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r8 <= 0) goto L_0x001c
            java.lang.String r8 = "bnc_install_begin_ts"
            r6.setLong(r8, r10)
        L_0x001c:
            if (r7 == 0) goto L_0x00ec
            java.lang.String r7 = java.net.URLDecoder.decode(r7, r1)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.util.HashMap r8 = new java.util.HashMap     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            r8.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = "&"
            java.lang.String[] r9 = r7.split(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            int r10 = r9.length     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            r11 = 0
            r2 = 0
        L_0x0030:
            if (r2 >= r10) goto L_0x0064
            r3 = r9[r2]     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            if (r4 != 0) goto L_0x0061
            boolean r4 = r3.contains(r0)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r5 = "-"
            if (r4 != 0) goto L_0x0049
            boolean r4 = r3.contains(r5)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            if (r4 == 0) goto L_0x0049
            goto L_0x004a
        L_0x0049:
            r5 = r0
        L_0x004a:
            java.lang.String[] r3 = r3.split(r5)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            int r4 = r3.length     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            r5 = 1
            if (r4 <= r5) goto L_0x0061
            r4 = r3[r11]     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r4 = java.net.URLDecoder.decode(r4, r1)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            r3 = r3[r5]     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r3 = java.net.URLDecoder.decode(r3, r1)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            r8.put(r4, r3)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
        L_0x0061:
            int r2 = r2 + 1
            goto L_0x0030
        L_0x0064:
            io.branch.referral.Defines$Jsonkey r9 = io.branch.referral.Defines.Jsonkey.LinkClickID     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = r9.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            boolean r9 = r8.containsKey(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            if (r9 == 0) goto L_0x0081
            io.branch.referral.Defines$Jsonkey r9 = io.branch.referral.Defines.Jsonkey.LinkClickID     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = r9.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.Object r9 = r8.get(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            installID_ = r9     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            r6.setLinkClickIdentifier(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
        L_0x0081:
            io.branch.referral.Defines$Jsonkey r9 = io.branch.referral.Defines.Jsonkey.IsFullAppConv     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = r9.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            boolean r9 = r8.containsKey(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            if (r9 == 0) goto L_0x00bb
            io.branch.referral.Defines$Jsonkey r9 = io.branch.referral.Defines.Jsonkey.ReferringLink     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = r9.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            boolean r9 = r8.containsKey(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            if (r9 == 0) goto L_0x00bb
            io.branch.referral.Defines$Jsonkey r9 = io.branch.referral.Defines.Jsonkey.IsFullAppConv     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = r9.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.Object r9 = r8.get(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            boolean r9 = java.lang.Boolean.parseBoolean(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            r6.setIsFullAppConversion(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            io.branch.referral.Defines$Jsonkey r9 = io.branch.referral.Defines.Jsonkey.ReferringLink     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = r9.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.Object r9 = r8.get(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            r6.setAppLink(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
        L_0x00bb:
            io.branch.referral.Defines$Jsonkey r9 = io.branch.referral.Defines.Jsonkey.GoogleSearchInstallReferrer     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = r9.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            boolean r9 = r8.containsKey(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            if (r9 == 0) goto L_0x00ec
            io.branch.referral.Defines$Jsonkey r9 = io.branch.referral.Defines.Jsonkey.GoogleSearchInstallReferrer     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r9 = r9.getKey()     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.Object r8 = r8.get(r9)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            r6.setGoogleSearchInstallIdentifier(r8)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            r6.setGooglePlayReferrer(r7)     // Catch:{ UnsupportedEncodingException -> 0x00dc, IllegalArgumentException -> 0x00da }
            goto L_0x00ec
        L_0x00da:
            r6 = move-exception
            goto L_0x00de
        L_0x00dc:
            r6 = move-exception
            goto L_0x00e9
        L_0x00de:
            r6.printStackTrace()
            java.lang.String r6 = "BranchSDK"
            java.lang.String r7 = "Illegal characters in url encoded string"
            android.util.Log.w(r6, r7)
            goto L_0x00ec
        L_0x00e9:
            r6.printStackTrace()
        L_0x00ec:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.InstallListener.processReferrerInfo(android.content.Context, java.lang.String, long, long):void");
    }

    public static String getInstallationID() {
        return installID_;
    }

    /* access modifiers changed from: private */
    public static void reportInstallReferrer() {
        unReportedReferrerAvailable = true;
        IInstallReferrerEvents iInstallReferrerEvents = callback_;
        if (iInstallReferrerEvents != null) {
            iInstallReferrerEvents.onInstallReferrerEventsFinished();
            callback_ = null;
            unReportedReferrerAvailable = false;
            isWaitingForReferrer = false;
            isReferrerClientAvailable = false;
        }
    }
}
