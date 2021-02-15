package com.medscape.android.activity.help;

import android.content.Context;
import com.medscape.android.capabilities.CapabilitiesManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003J\u000e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001bJ\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u000fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0010\"\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\""}, d2 = {"Lcom/medscape/android/activity/help/HelpDataProvider;", "", "context", "Landroid/content/Context;", "manager", "Lcom/medscape/android/capabilities/CapabilitiesManager;", "(Landroid/content/Context;Lcom/medscape/android/capabilities/CapabilitiesManager;)V", "capabilitiesManager", "getCapabilitiesManager", "()Lcom/medscape/android/capabilities/CapabilitiesManager;", "setCapabilitiesManager", "(Lcom/medscape/android/capabilities/CapabilitiesManager;)V", "getContext", "()Landroid/content/Context;", "isLogOutWarningActive", "", "()Z", "setLogOutWarningActive", "(Z)V", "itemList", "", "Lcom/medscape/android/activity/help/ProfileMenuItems;", "getItemList", "()Ljava/util/List;", "setItemList", "(Ljava/util/List;)V", "findLogOutPosition", "", "getListItems", "getSelectedElement", "position", "setLogOutState", "", "isLogOutWarningState", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HelpDataProvider.kt */
public final class HelpDataProvider {
    private CapabilitiesManager capabilitiesManager;
    private final Context context;
    private boolean isLogOutWarningActive;
    private List<ProfileMenuItems> itemList;

    public HelpDataProvider(Context context2, CapabilitiesManager capabilitiesManager2) {
        this.context = context2;
        if (capabilitiesManager2 == null) {
            this.capabilitiesManager = CapabilitiesManager.getInstance(context2);
        } else {
            this.capabilitiesManager = capabilitiesManager2;
        }
        this.itemList = getListItems(this.context);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ HelpDataProvider(Context context2, CapabilitiesManager capabilitiesManager2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i & 2) != 0 ? null : capabilitiesManager2);
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<ProfileMenuItems> getItemList() {
        return this.itemList;
    }

    public final void setItemList(List<ProfileMenuItems> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.itemList = list;
    }

    public final boolean isLogOutWarningActive() {
        return this.isLogOutWarningActive;
    }

    public final void setLogOutWarningActive(boolean z) {
        this.isLogOutWarningActive = z;
    }

    public final CapabilitiesManager getCapabilitiesManager() {
        return this.capabilitiesManager;
    }

    public final void setCapabilitiesManager(CapabilitiesManager capabilitiesManager2) {
        this.capabilitiesManager = capabilitiesManager2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
        r2 = r0.getString(com.medscape.android.R.string.title_content_preference);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.medscape.android.activity.help.ProfileMenuItems> getListItems(android.content.Context r24) {
        /*
            r23 = this;
            r0 = r24
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r1 = (java.util.List) r1
            com.medscape.android.activity.help.ProfileMenuItems r8 = new com.medscape.android.activity.help.ProfileMenuItems
            java.lang.String r9 = ""
            if (r0 == 0) goto L_0x001a
            r2 = 2131953651(0x7f1307f3, float:1.954378E38)
            java.lang.String r2 = r0.getString(r2)
            if (r2 == 0) goto L_0x001a
            r3 = r2
            goto L_0x001b
        L_0x001a:
            r3 = r9
        L_0x001b:
            java.lang.String r10 = "context?.getString(R.str…ce)\n                ?: \"\""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r10)
            r4 = 0
            r5 = 0
            r6 = 6
            r7 = 0
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            r1.add(r8)
            com.medscape.android.activity.help.ProfileMenuItems r2 = new com.medscape.android.activity.help.ProfileMenuItems
            if (r0 == 0) goto L_0x003a
            r3 = 2131952352(0x7f1302e0, float:1.9541144E38)
            java.lang.String r3 = r0.getString(r3)
            if (r3 == 0) goto L_0x003a
            r12 = r3
            goto L_0x003b
        L_0x003a:
            r12 = r9
        L_0x003b:
            java.lang.String r3 = "context?.getString(R.str…ta)\n                ?: \"\""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r3)
            r13 = 0
            r14 = 0
            r15 = 6
            r16 = 0
            r11 = r2
            r11.<init>(r12, r13, r14, r15, r16)
            r1.add(r2)
            com.medscape.android.activity.help.ProfileMenuItems r2 = new com.medscape.android.activity.help.ProfileMenuItems
            if (r0 == 0) goto L_0x005b
            r3 = 2131952350(0x7f1302de, float:1.954114E38)
            java.lang.String r3 = r0.getString(r3)
            if (r3 == 0) goto L_0x005b
            r4 = r3
            goto L_0x005c
        L_0x005b:
            r4 = r9
        L_0x005c:
            java.lang.String r3 = "context?.getString(R.str…rs)\n                ?: \"\""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r3)
            r5 = 0
            r6 = 0
            r7 = 6
            r8 = 0
            r3 = r2
            r3.<init>(r4, r5, r6, r7, r8)
            r1.add(r2)
            r2 = r23
            com.medscape.android.capabilities.CapabilitiesManager r3 = r2.capabilitiesManager
            if (r3 == 0) goto L_0x009a
            boolean r3 = r3.isConsultFeatureAvailable()
            r4 = 1
            if (r3 != r4) goto L_0x009a
            com.medscape.android.activity.help.ProfileMenuItems r3 = new com.medscape.android.activity.help.ProfileMenuItems
            if (r0 == 0) goto L_0x0088
            r4 = 2131952359(0x7f1302e7, float:1.9541159E38)
            java.lang.String r4 = r0.getString(r4)
            if (r4 == 0) goto L_0x0088
            r12 = r4
            goto L_0x0089
        L_0x0088:
            r12 = r9
        L_0x0089:
            java.lang.String r4 = "context?.getString(R.str…                    ?: \"\""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r4)
            r13 = 0
            r14 = 0
            r15 = 6
            r16 = 0
            r11 = r3
            r11.<init>(r12, r13, r14, r15, r16)
            r1.add(r3)
        L_0x009a:
            com.medscape.android.activity.help.ProfileMenuItems r3 = new com.medscape.android.activity.help.ProfileMenuItems
            if (r0 == 0) goto L_0x00a8
            r4 = 2131952354(0x7f1302e2, float:1.9541148E38)
            java.lang.String r4 = r0.getString(r4)
            if (r4 == 0) goto L_0x00a8
            goto L_0x00a9
        L_0x00a8:
            r4 = r9
        L_0x00a9:
            java.lang.String r5 = "context?.getString(R.str…lp)\n                ?: \"\""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            r19 = 0
            r20 = 0
            r21 = 6
            r22 = 0
            r17 = r3
            r18 = r4
            r17.<init>(r18, r19, r20, r21, r22)
            r1.add(r3)
            com.medscape.android.activity.help.ProfileMenuItems r3 = new com.medscape.android.activity.help.ProfileMenuItems
            if (r0 == 0) goto L_0x00cf
            r4 = 2131952360(0x7f1302e8, float:1.954116E38)
            java.lang.String r4 = r0.getString(r4)
            if (r4 == 0) goto L_0x00cf
            r12 = r4
            goto L_0x00d0
        L_0x00cf:
            r12 = r9
        L_0x00d0:
            java.lang.String r4 = "context?.getString(R.str…rsonal_information) ?: \"\""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r4)
            r13 = 0
            r14 = 0
            r15 = 6
            r16 = 0
            r11 = r3
            r11.<init>(r12, r13, r14, r15, r16)
            r1.add(r3)
            com.medscape.android.activity.help.ProfileMenuItems r3 = new com.medscape.android.activity.help.ProfileMenuItems
            if (r0 == 0) goto L_0x00ef
            r4 = 2131953662(0x7f1307fe, float:1.9543801E38)
            java.lang.String r0 = r0.getString(r4)
            if (r0 == 0) goto L_0x00ef
            goto L_0x00f0
        L_0x00ef:
            r0 = r9
        L_0x00f0:
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r10)
            r19 = 0
            r20 = 0
            r21 = 6
            r22 = 0
            r17 = r3
            r18 = r0
            r17.<init>(r18, r19, r20, r21, r22)
            r1.add(r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.help.HelpDataProvider.getListItems(android.content.Context):java.util.List");
    }

    public final ProfileMenuItems getSelectedElement(int i) {
        int size = this.itemList.size();
        if (i >= 0 && size >= i) {
            return this.itemList.get(i);
        }
        return new ProfileMenuItems((String) null, false, false, 7, (DefaultConstructorMarker) null);
    }

    public final void setLogOutState(boolean z) {
        int findLogOutPosition = findLogOutPosition();
        this.isLogOutWarningActive = z;
        if (findLogOutPosition == -1) {
            return;
        }
        if (z) {
            getSelectedElement(findLogOutPosition).setLogOut(true);
            getSelectedElement(findLogOutPosition).setText("Are you sure?");
            return;
        }
        getSelectedElement(findLogOutPosition).setLogOut(false);
        getSelectedElement(findLogOutPosition).setText("Log out");
    }

    public final int findLogOutPosition() {
        int i = 0;
        for (ProfileMenuItems profileMenuItems : this.itemList) {
            if (profileMenuItems.getText().equals("Log out") || profileMenuItems.getText().equals("Are you sure?")) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
