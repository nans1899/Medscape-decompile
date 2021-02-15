package com.medscape.android.activity.calc.managers;

import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import com.wbmd.qxcalculator.model.contentItems.filesource.FileSource;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J4\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0016J>\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\r\u001a\u00020\u0004H\u0016¨\u0006\u0010"}, d2 = {"Lcom/medscape/android/activity/calc/managers/ContentItemLaunchManager;", "Lcom/wbmd/qxcalculator/managers/ContentItemLaunchManager;", "()V", "launchContentItem", "", "dbContentItem", "Lcom/wbmd/qxcalculator/model/db/DBContentItem;", "fromActivity", "Landroidx/fragment/app/FragmentActivity;", "extras", "Landroid/content/Intent;", "resultCode", "", "presentWithModalStyle", "overridingTrackerId", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ContentItemLaunchManager.kt */
public final class ContentItemLaunchManager extends com.wbmd.qxcalculator.managers.ContentItemLaunchManager {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FileSource.FileSourceType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[FileSource.FileSourceType.HTML_EXT.ordinal()] = 1;
            $EnumSwitchMapping$0[FileSource.FileSourceType.HTML_EXT_BROWSER.ordinal()] = 2;
            $EnumSwitchMapping$0[FileSource.FileSourceType.HTML_INT.ordinal()] = 3;
            $EnumSwitchMapping$0[FileSource.FileSourceType.PDF_EXT.ordinal()] = 4;
            $EnumSwitchMapping$0[FileSource.FileSourceType.PDF_INT.ordinal()] = 5;
        }
    }

    public boolean launchContentItem(DBContentItem dBContentItem, FragmentActivity fragmentActivity, Intent intent, int i, boolean z) {
        Intrinsics.checkNotNullParameter(dBContentItem, "dbContentItem");
        return launchContentItem(dBContentItem, fragmentActivity, intent, i, (String) null, z);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0073, code lost:
        if (r6.booleanValue() == false) goto L_0x0075;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0088, code lost:
        if (r6.booleanValue() != false) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x008a, code lost:
        new android.app.AlertDialog.Builder(r1).setTitle(com.medscape.android.R.string.oops).setMessage(com.medscape.android.R.string.content_item_needs_update).setPositiveButton(com.medscape.android.R.string.update_now, com.medscape.android.activity.calc.managers.ContentItemLaunchManager$launchContentItem$1.INSTANCE).setNegativeButton(com.medscape.android.R.string.dismiss, (android.content.DialogInterface.OnClickListener) null).create().show();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00ac, code lost:
        return false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x036c A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x0397  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x03d3  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x03e4  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x0479  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x047c  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0491  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x04a1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x04ae  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x04b2  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x04cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean launchContentItem(com.wbmd.qxcalculator.model.db.DBContentItem r24, androidx.fragment.app.FragmentActivity r25, android.content.Intent r26, int r27, java.lang.String r28, boolean r29) {
        /*
            r23 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            r3 = r27
            r4 = r28
            java.lang.String r5 = "dbContentItem"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r5)
            r5 = 0
            if (r1 != 0) goto L_0x0013
            return r5
        L_0x0013:
            com.wbmd.qxcalculator.util.CrashLogger r6 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "launch item: "
            r7.append(r8)
            java.lang.String r8 = r24.getIdentifier()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r6.leaveBreadcrumb(r7)
            boolean r6 = r24.getIsUpdating()
            r7 = 2131952922(0x7f13051a, float:1.95423E38)
            r8 = 2131952334(0x7f1302ce, float:1.9541108E38)
            r9 = 0
            if (r6 == 0) goto L_0x005a
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder
            android.content.Context r1 = (android.content.Context) r1
            r0.<init>(r1)
            android.app.AlertDialog$Builder r0 = r0.setTitle(r7)
            r1 = 2131952144(0x7f130210, float:1.9540722E38)
            android.app.AlertDialog$Builder r0 = r0.setMessage(r1)
            android.app.AlertDialog$Builder r0 = r0.setNegativeButton(r8, r9)
            android.app.AlertDialog r0 = r0.create()
            r0.show()
            return r5
        L_0x005a:
            java.lang.Boolean r6 = r24.getRequiresUpdate()
            r10 = 2131953723(0x7f13083b, float:1.9543925E38)
            r11 = 2131952143(0x7f13020f, float:1.954072E38)
            if (r6 == 0) goto L_0x0075
            java.lang.Boolean r6 = r24.getRequiresUpdate()
            java.lang.String r12 = "dbContentItem.requiresUpdate"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r12)
            boolean r6 = r6.booleanValue()
            if (r6 != 0) goto L_0x008a
        L_0x0075:
            java.lang.Boolean r6 = r24.getResourcesRequireUpdate()
            if (r6 == 0) goto L_0x00ad
            java.lang.Boolean r6 = r24.getResourcesRequireUpdate()
            java.lang.String r12 = "dbContentItem.resourcesRequireUpdate"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r12)
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x00ad
        L_0x008a:
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder
            android.content.Context r1 = (android.content.Context) r1
            r0.<init>(r1)
            android.app.AlertDialog$Builder r0 = r0.setTitle(r7)
            android.app.AlertDialog$Builder r0 = r0.setMessage(r11)
            com.medscape.android.activity.calc.managers.ContentItemLaunchManager$launchContentItem$1 r1 = com.medscape.android.activity.calc.managers.ContentItemLaunchManager$launchContentItem$1.INSTANCE
            android.content.DialogInterface$OnClickListener r1 = (android.content.DialogInterface.OnClickListener) r1
            android.app.AlertDialog$Builder r0 = r0.setPositiveButton(r10, r1)
            android.app.AlertDialog$Builder r0 = r0.setNegativeButton(r8, r9)
            android.app.AlertDialog r0 = r0.create()
            r0.show()
            return r5
        L_0x00ad:
            com.wbmd.qxcalculator.model.db.DBRestriction r6 = com.wbmd.qxcalculator.model.db.DBContentItem.getContentItemRestriction(r24)
            r12 = 1
            if (r6 == 0) goto L_0x010b
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder
            r2 = r1
            android.content.Context r2 = (android.content.Context) r2
            r0.<init>(r2)
            java.lang.String r2 = r6.getAlertTitle()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            android.app.AlertDialog$Builder r0 = r0.setTitle(r2)
            java.lang.String r2 = r6.getAlertMessage()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            android.app.AlertDialog$Builder r0 = r0.setMessage(r2)
            java.lang.String r2 = r6.getAlternateUrl()
            if (r2 == 0) goto L_0x0100
            java.lang.String r2 = r6.getAlternateUrl()
            java.lang.String r3 = "restriction.alternateUrl"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r2 = r2.length()
            if (r2 != 0) goto L_0x00e8
            goto L_0x00e9
        L_0x00e8:
            r12 = 0
        L_0x00e9:
            if (r12 != 0) goto L_0x0100
            r2 = 2131953542(0x7f130786, float:1.9543558E38)
            com.medscape.android.activity.calc.managers.ContentItemLaunchManager$launchContentItem$2 r3 = new com.medscape.android.activity.calc.managers.ContentItemLaunchManager$launchContentItem$2
            r3.<init>(r6, r1)
            android.content.DialogInterface$OnClickListener r3 = (android.content.DialogInterface.OnClickListener) r3
            android.app.AlertDialog$Builder r1 = r0.setPositiveButton(r2, r3)
            r2 = 2131951879(0x7f130107, float:1.9540185E38)
            r1.setNegativeButton(r2, r9)
            goto L_0x0103
        L_0x0100:
            r0.setNegativeButton(r8, r9)
        L_0x0103:
            android.app.AlertDialog r0 = r0.create()
            r0.show()
            return r5
        L_0x010b:
            com.wbmd.qxcalculator.managers.ContentDataManager r6 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            boolean r6 = r6.findAndMarkMissingFilesForContentItem(r0)
            if (r6 == 0) goto L_0x0138
            android.app.AlertDialog$Builder r0 = new android.app.AlertDialog$Builder
            android.content.Context r1 = (android.content.Context) r1
            r0.<init>(r1)
            android.app.AlertDialog$Builder r0 = r0.setTitle(r7)
            android.app.AlertDialog$Builder r0 = r0.setMessage(r11)
            com.medscape.android.activity.calc.managers.ContentItemLaunchManager$launchContentItem$3 r1 = com.medscape.android.activity.calc.managers.ContentItemLaunchManager$launchContentItem$3.INSTANCE
            android.content.DialogInterface$OnClickListener r1 = (android.content.DialogInterface.OnClickListener) r1
            android.app.AlertDialog$Builder r0 = r0.setPositiveButton(r10, r1)
            android.app.AlertDialog$Builder r0 = r0.setNegativeButton(r8, r9)
            android.app.AlertDialog r0 = r0.create()
            r0.show()
            return r5
        L_0x0138:
            r6 = r9
            android.content.Intent r6 = (android.content.Intent) r6
            r7 = r9
            java.lang.String r7 = (java.lang.String) r7
            java.lang.String r8 = r24.getType()
            java.lang.String r10 = "ContentItem.CALCULATOR"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r10)
            java.lang.String r10 = "ContentItemLaunchManager.TASK_ID_SET_RECENT"
            java.lang.String r11 = "InternetConnectivityManager.getInstance()"
            java.lang.String r13 = "UserManager.getInstance()"
            r14 = 2
            if (r8 == 0) goto L_0x0173
            java.lang.String r6 = r24.getIdentifier()
            java.lang.String r8 = "calculator_235"
            boolean r6 = kotlin.text.StringsKt.equals(r6, r8, r12)
            if (r6 == 0) goto L_0x0168
            android.content.Intent r6 = new android.content.Intent
            r8 = r1
            android.content.Context r8 = (android.content.Context) r8
            java.lang.Class<com.wbmd.qxcalculator.custom.baxter.BaxterRxCalculatorActivity> r15 = com.wbmd.qxcalculator.custom.baxter.BaxterRxCalculatorActivity.class
            r6.<init>(r8, r15)
            goto L_0x0189
        L_0x0168:
            android.content.Intent r6 = new android.content.Intent
            r8 = r1
            android.content.Context r8 = (android.content.Context) r8
            java.lang.Class<com.medscape.android.activity.calc.MedscapeCalculatorActivity> r15 = com.medscape.android.activity.calc.MedscapeCalculatorActivity.class
            r6.<init>(r8, r15)
            goto L_0x0189
        L_0x0173:
            java.lang.String r8 = r24.getType()
            java.lang.String r15 = "ContentItem.DEFINITION"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r15)
            if (r8 == 0) goto L_0x018c
            android.content.Intent r6 = new android.content.Intent
            r8 = r1
            android.content.Context r8 = (android.content.Context) r8
            java.lang.Class<com.wbmd.qxcalculator.activities.contentItems.DefinitionActivity> r15 = com.wbmd.qxcalculator.activities.contentItems.DefinitionActivity.class
            r6.<init>(r8, r15)
        L_0x0189:
            r5 = r7
            goto L_0x036a
        L_0x018c:
            java.lang.String r8 = r24.getType()
            java.lang.String r15 = "ContentItem.REFERENCE_BOOK"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r15)
            if (r8 == 0) goto L_0x01a3
            android.content.Intent r6 = new android.content.Intent
            r8 = r1
            android.content.Context r8 = (android.content.Context) r8
            java.lang.Class<com.wbmd.qxcalculator.activities.contentItems.ReferenceBookActivity> r15 = com.wbmd.qxcalculator.activities.contentItems.ReferenceBookActivity.class
            r6.<init>(r8, r15)
            goto L_0x0189
        L_0x01a3:
            java.lang.String r8 = r24.getType()
            java.lang.String r15 = "ContentItem.FILE_SOURCE"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r15)
            if (r8 == 0) goto L_0x0365
            com.wbmd.qxcalculator.model.db.DBFileSource r8 = r24.getFileSource()
            java.lang.String r15 = "fileSource"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r15)
            java.lang.String r15 = r8.getSource()
            if (r15 != 0) goto L_0x01c1
            r16 = r9
            goto L_0x01d7
        L_0x01c1:
            java.lang.String r15 = r8.getSource()
            java.lang.String r12 = "fileSource.source"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r15, r12)
            if (r15 == 0) goto L_0x035d
            java.lang.String r12 = r15.toLowerCase()
            java.lang.String r15 = "(this as java.lang.String).toLowerCase()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r15)
            r16 = r12
        L_0x01d7:
            if (r16 == 0) goto L_0x02d5
            r12 = r16
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12
            java.lang.String r15 = "qxmd.com/r/"
            r22 = r6
            r6 = r15
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            boolean r6 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r12, (java.lang.CharSequence) r6, (boolean) r5, (int) r14, (java.lang.Object) r9)
            if (r6 == 0) goto L_0x0205
            r19 = 0
            r20 = 4
            r21 = 0
            java.lang.String r17 = "www."
            java.lang.String r18 = ""
            java.lang.String r6 = kotlin.text.StringsKt.replace$default((java.lang.String) r16, (java.lang.String) r17, (java.lang.String) r18, (boolean) r19, (int) r20, (java.lang.Object) r21)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            kotlin.text.Regex r12 = new kotlin.text.Regex
            r12.<init>((java.lang.String) r15)
            java.lang.String r15 = "read.qxmd.com/"
            java.lang.String r16 = r12.replace((java.lang.CharSequence) r6, (java.lang.String) r15)
        L_0x0205:
            r6 = r16
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            java.lang.String r12 = "read.qxmd.com"
            java.lang.CharSequence r12 = (java.lang.CharSequence) r12
            boolean r6 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r6, (java.lang.CharSequence) r12, (boolean) r5, (int) r14, (java.lang.Object) r9)
            if (r6 == 0) goto L_0x02d7
            java.lang.String r6 = "com.qxmd.readbyqxmd"
            android.net.Uri r12 = android.net.Uri.parse(r16)
            android.content.Intent r15 = new android.content.Intent
            java.lang.String r5 = "android.intent.action.VIEW"
            r15.<init>(r5, r12)
            android.content.pm.PackageManager r5 = r25.getPackageManager()
            r9 = 65536(0x10000, float:9.18355E-41)
            java.util.List r5 = r5.queryIntentActivities(r15, r9)
            java.lang.String r9 = "packageManager.queryInte…nager.MATCH_DEFAULT_ONLY)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r9)
            java.util.Iterator r5 = r5.iterator()
        L_0x0233:
            boolean r9 = r5.hasNext()
            if (r9 == 0) goto L_0x024b
            java.lang.Object r9 = r5.next()
            android.content.pm.ResolveInfo r9 = (android.content.pm.ResolveInfo) r9
            android.content.pm.ActivityInfo r9 = r9.activityInfo
            java.lang.String r9 = r9.packageName
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r6)
            if (r9 == 0) goto L_0x0233
            r5 = 1
            goto L_0x024c
        L_0x024b:
            r5 = 0
        L_0x024c:
            if (r5 == 0) goto L_0x02d7
            android.content.pm.PackageManager r5 = r25.getPackageManager()
            android.content.Intent r5 = r5.getLaunchIntentForPackage(r6)
            if (r5 == 0) goto L_0x02d7
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem r2 = new com.wbmd.qxcalculator.model.contentItems.common.ContentItem
            r2.<init>(r0)
            com.wbmd.qxcalculator.managers.EventsManager r3 = com.wbmd.qxcalculator.managers.EventsManager.getInstance()
            java.lang.String r4 = r2.identifier
            com.wbmd.qxcalculator.model.parsedObjects.Promotion r2 = r2.promotionToUse
            r3.trackContentItemUsed(r4, r2)
            com.wbmd.qxcalculator.managers.UserManager r2 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r13)
            com.wbmd.qxcalculator.model.db.DBUser r2 = r2.getDbUser()
            com.wbmd.qxcalculator.managers.UserManager r3 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            boolean r3 = r3.hasFinishedUpgradingToUniversalAccounts()
            if (r3 == 0) goto L_0x02b9
            com.wbmd.qxcalculator.managers.InternetConnectivityManager r2 = com.wbmd.qxcalculator.managers.InternetConnectivityManager.getInstance()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r11)
            boolean r2 = r2.isConnectedToInternet()
            if (r2 == 0) goto L_0x02c8
            com.wbmd.qxcalculator.managers.ContentDataManager r2 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            java.util.List<com.wbmd.qxcalculator.model.QxError> r2 = r2.accountRefreshErrors
            if (r2 == 0) goto L_0x029e
            com.wbmd.qxcalculator.managers.ContentDataManager r2 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            java.util.List<com.wbmd.qxcalculator.model.QxError> r2 = r2.accountRefreshErrors
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x02c8
        L_0x029e:
            com.wbmd.qxcalculator.managers.ContentDataManager r2 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            boolean r2 = r2.shouldRefresh()
            if (r2 != 0) goto L_0x02c8
            com.wbmd.qxcalculator.managers.ContentDataManager r2 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            java.lang.String r0 = r24.getIdentifier()
            java.util.Date r3 = new java.util.Date
            r3.<init>()
            r2.setRecentlyUsed(r0, r3, r10)
            goto L_0x02c8
        L_0x02b9:
            java.util.Date r3 = new java.util.Date
            r3.<init>()
            long r3 = r3.getTime()
            r6 = r1
            android.content.Context r6 = (android.content.Context) r6
            r2.markContentItemUsed(r0, r3, r6)
        L_0x02c8:
            java.lang.String r0 = "android.intent.action.CALL"
            r5.setAction(r0)
            r5.setData(r12)
            r1.startActivity(r5)
        L_0x02d3:
            r0 = 1
            return r0
        L_0x02d5:
            r22 = r6
        L_0x02d7:
            r5 = r16
            java.lang.String r6 = r8.getType()
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r6 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.getFileSourceType(r6)
            if (r5 == 0) goto L_0x02f8
            java.lang.String r9 = ".pdf"
            r12 = 0
            r15 = 0
            boolean r9 = kotlin.text.StringsKt.endsWith$default(r5, r9, r15, r14, r12)
            if (r9 != 0) goto L_0x02f8
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r9 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.PDF_EXT
            if (r6 == r9) goto L_0x02f5
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r9 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.PDF_INT
            if (r6 != r9) goto L_0x02f8
        L_0x02f5:
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r6 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.HTML_EXT_BROWSER
            goto L_0x030e
        L_0x02f8:
            if (r5 == 0) goto L_0x030e
            java.lang.String r9 = ".html"
            r12 = 0
            r15 = 0
            boolean r5 = kotlin.text.StringsKt.endsWith$default(r5, r9, r15, r14, r12)
            if (r5 != 0) goto L_0x030e
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r5 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.HTML_EXT
            if (r6 == r5) goto L_0x030c
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r5 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.HTML_INT
            if (r6 != r5) goto L_0x030e
        L_0x030c:
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource$FileSourceType r6 = com.wbmd.qxcalculator.model.contentItems.filesource.FileSource.FileSourceType.HTML_EXT_BROWSER
        L_0x030e:
            if (r6 != 0) goto L_0x0311
            goto L_0x0367
        L_0x0311:
            int[] r5 = com.medscape.android.activity.calc.managers.ContentItemLaunchManager.WhenMappings.$EnumSwitchMapping$0
            int r6 = r6.ordinal()
            r5 = r5[r6]
            r6 = 1
            if (r5 == r6) goto L_0x0351
            if (r5 == r14) goto L_0x034c
            r6 = 3
            if (r5 == r6) goto L_0x0340
            r6 = 4
            if (r5 == r6) goto L_0x0334
            r6 = 5
            if (r5 == r6) goto L_0x0328
            goto L_0x0367
        L_0x0328:
            android.content.Intent r6 = new android.content.Intent
            r5 = r1
            android.content.Context r5 = (android.content.Context) r5
            java.lang.Class<com.wbmd.qxcalculator.activities.contentItems.PdfViewerActivity> r8 = com.wbmd.qxcalculator.activities.contentItems.PdfViewerActivity.class
            r6.<init>(r5, r8)
            goto L_0x0189
        L_0x0334:
            android.content.Intent r6 = new android.content.Intent
            r5 = r1
            android.content.Context r5 = (android.content.Context) r5
            java.lang.Class<com.wbmd.qxcalculator.activities.contentItems.PdfViewerActivity> r8 = com.wbmd.qxcalculator.activities.contentItems.PdfViewerActivity.class
            r6.<init>(r5, r8)
            goto L_0x0189
        L_0x0340:
            android.content.Intent r6 = new android.content.Intent
            r5 = r1
            android.content.Context r5 = (android.content.Context) r5
            java.lang.Class<com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity> r8 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.class
            r6.<init>(r5, r8)
            goto L_0x0189
        L_0x034c:
            java.lang.String r5 = r8.getSource()
            goto L_0x0368
        L_0x0351:
            android.content.Intent r6 = new android.content.Intent
            r5 = r1
            android.content.Context r5 = (android.content.Context) r5
            java.lang.Class<com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity> r8 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.class
            r6.<init>(r5, r8)
            goto L_0x0189
        L_0x035d:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "null cannot be cast to non-null type java.lang.String"
            r0.<init>(r1)
            throw r0
        L_0x0365:
            r22 = r6
        L_0x0367:
            r5 = r7
        L_0x0368:
            r6 = r22
        L_0x036a:
            if (r6 != 0) goto L_0x0372
            if (r5 == 0) goto L_0x036f
            goto L_0x0372
        L_0x036f:
            r5 = 0
            goto L_0x04d7
        L_0x0372:
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem r8 = new com.wbmd.qxcalculator.model.contentItems.common.ContentItem
            r8.<init>(r0)
            com.wbmd.qxcalculator.managers.EventsManager r9 = com.wbmd.qxcalculator.managers.EventsManager.getInstance()
            java.lang.String r12 = r8.identifier
            com.wbmd.qxcalculator.model.parsedObjects.Promotion r15 = r8.promotionToUse
            r9.trackContentItemUsed(r12, r15)
            com.wbmd.qxcalculator.managers.UserManager r9 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r13)
            com.wbmd.qxcalculator.model.db.DBUser r9 = r9.getDbUser()
            com.wbmd.qxcalculator.managers.UserManager r12 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            boolean r12 = r12.hasFinishedUpgradingToUniversalAccounts()
            if (r12 == 0) goto L_0x03d3
            com.wbmd.qxcalculator.managers.InternetConnectivityManager r9 = com.wbmd.qxcalculator.managers.InternetConnectivityManager.getInstance()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r11)
            boolean r9 = r9.isConnectedToInternet()
            if (r9 == 0) goto L_0x03e2
            com.wbmd.qxcalculator.managers.ContentDataManager r9 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            java.util.List<com.wbmd.qxcalculator.model.QxError> r9 = r9.accountRefreshErrors
            if (r9 == 0) goto L_0x03b8
            com.wbmd.qxcalculator.managers.ContentDataManager r9 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            java.util.List<com.wbmd.qxcalculator.model.QxError> r9 = r9.accountRefreshErrors
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x03e2
        L_0x03b8:
            com.wbmd.qxcalculator.managers.ContentDataManager r9 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            boolean r9 = r9.shouldRefresh()
            if (r9 != 0) goto L_0x03e2
            com.wbmd.qxcalculator.managers.ContentDataManager r9 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            java.lang.String r11 = r24.getIdentifier()
            java.util.Date r12 = new java.util.Date
            r12.<init>()
            r9.setRecentlyUsed(r11, r12, r10)
            goto L_0x03e2
        L_0x03d3:
            java.util.Date r10 = new java.util.Date
            r10.<init>()
            long r10 = r10.getTime()
            r12 = r1
            android.content.Context r12 = (android.content.Context) r12
            r9.markContentItemUsed(r0, r10, r12)
        L_0x03e2:
            if (r5 == 0) goto L_0x0479
            r6 = r1
            android.content.Context r6 = (android.content.Context) r6
            java.util.ArrayList r9 = com.wbmd.qxcalculator.managers.ContentItemLaunchManager.getCustomTabsPackages(r6)
            java.util.List r9 = (java.util.List) r9
            if (r9 == 0) goto L_0x0448
            java.util.Iterator r10 = r9.iterator()
            r15 = 0
        L_0x03f4:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x0410
            java.lang.Object r11 = r10.next()
            android.content.pm.ResolveInfo r11 = (android.content.pm.ResolveInfo) r11
            android.content.pm.ActivityInfo r12 = r11.activityInfo
            java.lang.String r12 = r12.packageName
            if (r12 == 0) goto L_0x040e
            int r14 = r11.preferredOrder
            if (r14 <= r15) goto L_0x040e
            int r7 = r11.preferredOrder
            r15 = r7
            r7 = r12
        L_0x040e:
            r14 = 2
            goto L_0x03f4
        L_0x0410:
            if (r7 != 0) goto L_0x0448
            java.util.Iterator r9 = r9.iterator()
        L_0x0416:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0448
            java.lang.Object r7 = r9.next()
            android.content.pm.ResolveInfo r7 = (android.content.pm.ResolveInfo) r7
            android.content.pm.ActivityInfo r7 = r7.activityInfo
            java.lang.String r7 = r7.packageName
            if (r7 == 0) goto L_0x0444
            r10 = r7
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            java.lang.String r11 = "chrome"
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            r12 = 2
            r14 = 0
            r15 = 0
            boolean r11 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r10, (java.lang.CharSequence) r11, (boolean) r15, (int) r12, (java.lang.Object) r14)
            if (r11 == 0) goto L_0x0439
            goto L_0x0449
        L_0x0439:
            java.lang.String r11 = "firefox"
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            boolean r10 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r10, (java.lang.CharSequence) r11, (boolean) r15, (int) r12, (java.lang.Object) r14)
            if (r10 == 0) goto L_0x0416
            goto L_0x0449
        L_0x0444:
            r12 = 2
            r14 = 0
            r15 = 0
            goto L_0x0416
        L_0x0448:
            r14 = 0
        L_0x0449:
            if (r7 == 0) goto L_0x0470
            androidx.browser.customtabs.CustomTabsIntent$Builder r0 = new androidx.browser.customtabs.CustomTabsIntent$Builder
            r0.<init>()
            android.content.res.Resources r1 = r25.getResources()
            r2 = 2131099677(0x7f06001d, float:1.7811714E38)
            int r1 = r1.getColor(r2)
            r0.setToolbarColor(r1)
            androidx.browser.customtabs.CustomTabsIntent r0 = r0.build()
            android.content.Intent r1 = r0.intent
            r1.setPackage(r7)
            android.net.Uri r1 = android.net.Uri.parse(r5)
            r0.launchUrl(r6, r1)
            goto L_0x02d3
        L_0x0470:
            android.content.Intent r5 = new android.content.Intent
            java.lang.Class<com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity> r7 = com.wbmd.qxcalculator.activities.contentItems.FileSourceHtmlActivity.class
            r5.<init>(r6, r7)
            r6 = r5
            goto L_0x047a
        L_0x0479:
            r14 = 0
        L_0x047a:
            if (r4 == 0) goto L_0x047e
            r8.trackerId = r4
        L_0x047e:
            com.wbmd.qxcalculator.managers.UserManager r4 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r13)
            com.wbmd.qxcalculator.model.db.DBUser r4 = r4.getDbUser()
            r5 = r1
            android.content.Context r5 = (android.content.Context) r5
            r8.initializeContentItem(r4, r5)
            if (r6 == 0) goto L_0x0498
            android.os.Parcelable r8 = (android.os.Parcelable) r8
            java.lang.String r4 = "ContentItemActivity.KEY_EXTRA_CONTENT_ITEM"
            r6.putExtra(r4, r8)
        L_0x0498:
            if (r2 == 0) goto L_0x049f
            if (r6 == 0) goto L_0x049f
            r6.putExtras(r2)
        L_0x049f:
            if (r29 == 0) goto L_0x04aa
            if (r6 == 0) goto L_0x04aa
            java.lang.String r2 = "QxMDActivity.KEY_MODAL_ANIM_STYLE"
            r4 = 1
            r6.putExtra(r2, r4)
            goto L_0x04ab
        L_0x04aa:
            r4 = 1
        L_0x04ab:
            r2 = -1
            if (r3 == r2) goto L_0x04b2
            r1.startActivityForResult(r6, r3)
            goto L_0x04cb
        L_0x04b2:
            com.wbmd.qxcalculator.LaunchQxCallback r2 = com.wbmd.qxcalculator.managers.ContentItemLaunchManager.calcCallback
            if (r2 == 0) goto L_0x04c8
            com.wbmd.qxcalculator.LaunchQxCallback r2 = com.wbmd.qxcalculator.managers.ContentItemLaunchManager.calcCallback
            if (r6 == 0) goto L_0x04c3
            android.os.Bundle r9 = r6.getExtras()
            goto L_0x04c4
        L_0x04c3:
            r9 = r14
        L_0x04c4:
            r2.onQxItemClicked(r0, r9)
            goto L_0x04cb
        L_0x04c8:
            r1.startActivity(r6)
        L_0x04cb:
            if (r29 == 0) goto L_0x04d6
            r0 = 2130772040(0x7f010048, float:1.7147187E38)
            r2 = 2130772043(0x7f01004b, float:1.7147193E38)
            r1.overridePendingTransition(r0, r2)
        L_0x04d6:
            r5 = 1
        L_0x04d7:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.calc.managers.ContentItemLaunchManager.launchContentItem(com.wbmd.qxcalculator.model.db.DBContentItem, androidx.fragment.app.FragmentActivity, android.content.Intent, int, java.lang.String, boolean):boolean");
    }
}
