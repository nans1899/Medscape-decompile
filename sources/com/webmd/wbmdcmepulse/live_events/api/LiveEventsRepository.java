package com.webmd.wbmdcmepulse.live_events.api;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0005XD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u001a\u0010\u0011\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\f\"\u0004\b\u0013\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/api/LiveEventsRepository;", "", "context", "Landroidx/fragment/app/FragmentActivity;", "currentSpecialityID", "", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;)V", "getContext", "()Landroidx/fragment/app/FragmentActivity;", "setContext", "(Landroidx/fragment/app/FragmentActivity;)V", "getCurrentSpecialityID", "()Ljava/lang/String;", "setCurrentSpecialityID", "(Ljava/lang/String;)V", "dateFormat", "getDateFormat", "mTAG", "getMTAG", "setMTAG", "getLiveEvents", "", "listener", "Lcom/webmd/wbmdcmepulse/live_events/util/LiveEventsLoadFinish;", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventsRepository.kt */
public final class LiveEventsRepository {
    private FragmentActivity context;
    private String currentSpecialityID;
    private final String dateFormat = "E, MMMM dd, yyyy";
    private String mTAG = "LiveEventsRepository";

    public LiveEventsRepository(FragmentActivity fragmentActivity, String str) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        Intrinsics.checkNotNullParameter(str, "currentSpecialityID");
        this.context = fragmentActivity;
        this.currentSpecialityID = str;
    }

    public final FragmentActivity getContext() {
        return this.context;
    }

    public final String getCurrentSpecialityID() {
        return this.currentSpecialityID;
    }

    public final void setContext(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<set-?>");
        this.context = fragmentActivity;
    }

    public final void setCurrentSpecialityID(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.currentSpecialityID = str;
    }

    public final String getMTAG() {
        return this.mTAG;
    }

    public final void setMTAG(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mTAG = str;
    }

    public final String getDateFormat() {
        return this.dateFormat;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void getLiveEvents(com.webmd.wbmdcmepulse.live_events.util.LiveEventsLoadFinish r8) {
        /*
            r7 = this;
            java.lang.String r0 = "listener"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            com.wbmd.environment.EnvironmentManager r0 = new com.wbmd.environment.EnvironmentManager
            r0.<init>()
            androidx.fragment.app.FragmentActivity r1 = r7.context
            android.content.Context r1 = (android.content.Context) r1
            java.lang.String r2 = "module_feed"
            java.lang.String r0 = r0.getEnvironmentWithDefault(r1, r2)
            int r1 = r0.hashCode()
            r2 = 206969445(0xc561a65, float:1.6493903E-31)
            r3 = 0
            if (r1 == r2) goto L_0x0039
            r2 = 568961725(0x21e9aabd, float:1.5833888E-18)
            if (r1 == r2) goto L_0x0024
            goto L_0x0059
        L_0x0024:
            java.lang.String r1 = "environment_qa01"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0059
            com.webmd.wbmdcmepulse.live_events.api.LiveEventsApi$Companion r0 = com.webmd.wbmdcmepulse.live_events.api.LiveEventsApi.Companion
            androidx.fragment.app.FragmentActivity r1 = r7.context
            android.content.Context r1 = (android.content.Context) r1
            java.lang.String r2 = "https://www.qa01.medscape.org/ws/getLiveEventFragments/"
            com.webmd.wbmdcmepulse.live_events.api.LiveEventsApi r0 = r0.create(r2, r1)
            goto L_0x005a
        L_0x0039:
            java.lang.String r1 = "environment_production"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0059
            com.webmd.wbmdcmepulse.live_events.remoteconfig.LiveEventsConfigManager r0 = new com.webmd.wbmdcmepulse.live_events.remoteconfig.LiveEventsConfigManager
            androidx.fragment.app.FragmentActivity r1 = r7.context
            r0.<init>(r1)
            java.lang.String r0 = r0.getLiveEventsUrl()
            if (r0 == 0) goto L_0x0059
            com.webmd.wbmdcmepulse.live_events.api.LiveEventsApi$Companion r1 = com.webmd.wbmdcmepulse.live_events.api.LiveEventsApi.Companion
            androidx.fragment.app.FragmentActivity r2 = r7.context
            android.content.Context r2 = (android.content.Context) r2
            com.webmd.wbmdcmepulse.live_events.api.LiveEventsApi r0 = r1.create(r0, r2)
            goto L_0x005a
        L_0x0059:
            r0 = r3
        L_0x005a:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.webmd.wbmdcmepulse.live_events.util.LiveEventsUtil r2 = new com.webmd.wbmdcmepulse.live_events.util.LiveEventsUtil
            r2.<init>()
            androidx.fragment.app.FragmentActivity r4 = r7.context
            android.content.Context r4 = (android.content.Context) r4
            java.lang.String r2 = r2.getJsonDataForLiveEvents(r4)
            java.lang.String r4 = r7.currentSpecialityID
            if (r4 == 0) goto L_0x00b1
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            java.lang.CharSequence r4 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r4)
            java.lang.String r4 = r4.toString()
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            kotlin.text.Regex r5 = new kotlin.text.Regex
            java.lang.String r6 = "^[0-9]*$"
            r5.<init>((java.lang.String) r6)
            boolean r4 = r5.matches(r4)
            if (r4 == 0) goto L_0x009a
            if (r2 == 0) goto L_0x009a
            com.webmd.wbmdcmepulse.live_events.util.LiveEventsUtil r3 = new com.webmd.wbmdcmepulse.live_events.util.LiveEventsUtil
            r3.<init>()
            java.lang.String r4 = r7.currentSpecialityID
            int r4 = java.lang.Integer.parseInt(r4)
            java.lang.String r3 = r3.getSpecialityNameById(r2, r4)
        L_0x009a:
            if (r0 == 0) goto L_0x00ad
            if (r3 == 0) goto L_0x00ad
            retrofit2.Call r0 = r0.getLiveEvents(r3)
            com.webmd.wbmdcmepulse.live_events.api.LiveEventsRepository$getLiveEvents$2 r2 = new com.webmd.wbmdcmepulse.live_events.api.LiveEventsRepository$getLiveEvents$2
            r2.<init>(r7, r8, r1)
            retrofit2.Callback r2 = (retrofit2.Callback) r2
            r0.enqueue(r2)
            goto L_0x00b0
        L_0x00ad:
            r8.onLiveEventsLoaded(r1)
        L_0x00b0:
            return
        L_0x00b1:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.CharSequence"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.live_events.api.LiveEventsRepository.getLiveEvents(com.webmd.wbmdcmepulse.live_events.util.LiveEventsLoadFinish):void");
    }
}
