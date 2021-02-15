package io.branch.referral.validators;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import io.branch.referral.BranchAsyncTask;
import io.branch.referral.BranchUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

class BranchIntegrationModel {
    boolean appSettingsAvailable = false;
    final List<String> applinkScheme = new ArrayList();
    private final String branchKeyLive;
    private final String branchKeyTest;
    JSONObject deeplinkUriScheme;
    final String packageName;

    public BranchIntegrationModel(Context context) {
        String str;
        String str2;
        this.packageName = context.getPackageName();
        String str3 = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                str2 = applicationInfo.metaData.getString("io.branch.sdk.BranchKey");
                try {
                    str3 = applicationInfo.metaData.getString("io.branch.sdk.BranchKey.test");
                } catch (PackageManager.NameNotFoundException e) {
                    e = e;
                    e.printStackTrace();
                    str = str3;
                    str3 = str2;
                    this.branchKeyLive = str3;
                    this.branchKeyTest = str;
                    updateDeepLinkSchemes(context);
                }
                str = str3;
                str3 = str2;
                this.branchKeyLive = str3;
                this.branchKeyTest = str;
                updateDeepLinkSchemes(context);
            }
            str = null;
            this.branchKeyLive = str3;
            this.branchKeyTest = str;
            updateDeepLinkSchemes(context);
        } catch (PackageManager.NameNotFoundException e2) {
            e = e2;
            str2 = null;
            e.printStackTrace();
            str = str3;
            str3 = str2;
            this.branchKeyLive = str3;
            this.branchKeyTest = str;
            updateDeepLinkSchemes(context);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateDeepLinkSchemes(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            io.branch.referral.validators.BranchIntegrationModel$getDeepLinkSchemeTasks r2 = new io.branch.referral.validators.BranchIntegrationModel$getDeepLinkSchemeTasks     // Catch:{ all -> 0x001e }
            r2.<init>()     // Catch:{ all -> 0x001e }
            r3 = 1
            android.content.Context[] r4 = new android.content.Context[r3]     // Catch:{ all -> 0x001e }
            r4[r0] = r7     // Catch:{ all -> 0x001e }
            android.os.AsyncTask r7 = r2.executeTask(r4)     // Catch:{ all -> 0x001e }
            r4 = 2500(0x9c4, double:1.235E-320)
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x001e }
            java.lang.Object r7 = r7.get(r4, r2)     // Catch:{ all -> 0x001e }
            org.json.JSONObject r7 = (org.json.JSONObject) r7     // Catch:{ all -> 0x001e }
            r6.appSettingsAvailable = r3     // Catch:{ all -> 0x001d }
            goto L_0x001f
        L_0x001d:
            r1 = r7
        L_0x001e:
            r7 = r1
        L_0x001f:
            if (r7 == 0) goto L_0x004b
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.URIScheme
            java.lang.String r1 = r1.getKey()
            org.json.JSONObject r1 = r7.optJSONObject(r1)
            r6.deeplinkUriScheme = r1
            io.branch.referral.Defines$Jsonkey r1 = io.branch.referral.Defines.Jsonkey.AppLinks
            java.lang.String r1 = r1.getKey()
            org.json.JSONArray r7 = r7.optJSONArray(r1)
            if (r7 == 0) goto L_0x004b
        L_0x0039:
            int r1 = r7.length()
            if (r0 >= r1) goto L_0x004b
            java.util.List<java.lang.String> r1 = r6.applinkScheme
            java.lang.String r2 = r7.optString(r0)
            r1.add(r2)
            int r0 = r0 + 1
            goto L_0x0039
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.validators.BranchIntegrationModel.updateDeepLinkSchemes(android.content.Context):void");
    }

    private class getDeepLinkSchemeTasks extends BranchAsyncTask<Context, Void, JSONObject> {
        private getDeepLinkSchemeTasks() {
        }

        /* access modifiers changed from: protected */
        public JSONObject doInBackground(Context... contextArr) {
            return BranchUtil.getDeepLinkSchemes(contextArr[0]);
        }
    }
}
