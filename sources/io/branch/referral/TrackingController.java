package io.branch.referral;

import android.content.Context;

class TrackingController {
    private boolean trackingDisabled = true;

    TrackingController(Context context) {
        updateTrackingState(context);
    }

    /* access modifiers changed from: package-private */
    public void disableTracking(Context context, boolean z) {
        if (this.trackingDisabled != z) {
            this.trackingDisabled = z;
            if (z) {
                onTrackingDisabled(context);
            } else {
                onTrackingEnabled();
            }
            PrefHelper.getInstance(context).setBool("bnc_tracking_state", Boolean.valueOf(z));
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isTrackingDisabled() {
        return this.trackingDisabled;
    }

    /* access modifiers changed from: package-private */
    public void updateTrackingState(Context context) {
        this.trackingDisabled = PrefHelper.getInstance(context).getBool("bnc_tracking_state");
    }

    private void onTrackingDisabled(Context context) {
        Branch.getInstance().clearPendingRequests();
        PrefHelper instance = PrefHelper.getInstance(context);
        instance.clearBranchAnalyticsData();
        instance.setSessionID("bnc_no_value");
        instance.setLinkClickID("bnc_no_value");
        instance.setLinkClickIdentifier("bnc_no_value");
        instance.setAppLink("bnc_no_value");
        instance.setInstallReferrerParams("bnc_no_value");
        instance.setGooglePlayReferrer("bnc_no_value");
        instance.setGoogleSearchInstallIdentifier("bnc_no_value");
        instance.setExternalIntentUri("bnc_no_value");
        instance.setExternalIntentExtra("bnc_no_value");
        instance.setSessionParams("bnc_no_value");
        instance.saveLastStrongMatchTime(0);
    }

    private void onTrackingEnabled() {
        if (Branch.getInstance() != null) {
            Branch.getInstance().registerAppReInit();
        }
    }
}
