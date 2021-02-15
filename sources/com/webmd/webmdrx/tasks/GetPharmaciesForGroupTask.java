package com.webmd.webmdrx.tasks;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.http.HttpRequestObject;
import com.webmd.webmdrx.http.HttpResponseObject;
import com.webmd.webmdrx.http.HttpUtils;
import com.webmd.webmdrx.intf.IPricingReceivedListener;
import com.webmd.webmdrx.util.WebMDException;

public class GetPharmaciesForGroupTask extends AsyncTask<Void, String, HttpResponseObject> {
    /* access modifiers changed from: private */
    public Context mContext;
    private Location mLocation;
    /* access modifiers changed from: private */
    public IPricingReceivedListener mPricingReceivedListener;
    private HttpRequestObject mRequestObject;
    /* access modifiers changed from: private */
    public GetPharmaciesForGroupTask task;

    public GetPharmaciesForGroupTask(Context context, HttpRequestObject httpRequestObject, String str, Location location, IPricingReceivedListener iPricingReceivedListener) {
        this.mContext = context;
        this.mRequestObject = httpRequestObject;
        this.mPricingReceivedListener = iPricingReceivedListener;
        this.mLocation = location;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        this.task = this;
        new CountDownTimer(10000, 10000) {
            public void onTick(long j) {
            }

            public void onFinish() {
                if (GetPharmaciesForGroupTask.this.task.getStatus() == AsyncTask.Status.RUNNING && !GetPharmaciesForGroupTask.this.task.isCancelled()) {
                    GetPharmaciesForGroupTask.this.task.cancel(true);
                    GetPharmaciesForGroupTask.this.mPricingReceivedListener.onPricingRequestFailed(new WebMDException(GetPharmaciesForGroupTask.this.mContext.getString(R.string.connection_error_message)));
                }
            }
        }.start();
    }

    /* access modifiers changed from: protected */
    public HttpResponseObject doInBackground(Void... voidArr) {
        HttpRequestObject httpRequestObject;
        if (this.mPricingReceivedListener == null || (httpRequestObject = this.mRequestObject) == null) {
            return null;
        }
        return HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(2:21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r8.mPricingReceivedListener.onPricingRequestFailed(new com.webmd.webmdrx.util.WebMDException(r8.mContext.getString(com.webmd.webmdrx.R.string.unknown_format_error_message)));
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x008d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPostExecute(com.webmd.webmdrx.http.HttpResponseObject r9) {
        /*
            r8 = this;
            super.onPostExecute(r9)
            com.webmd.webmdrx.intf.IPricingReceivedListener r0 = r8.mPricingReceivedListener
            if (r0 == 0) goto L_0x0100
            boolean r0 = r8.isCancelled()
            if (r0 != 0) goto L_0x0100
            if (r9 == 0) goto L_0x00e6
            java.lang.String r0 = r9.getResponseErrorMsg()
            boolean r0 = com.webmd.webmdrx.util.StringUtil.isNotEmpty(r0)
            if (r0 != 0) goto L_0x00e6
            java.lang.String r0 = r9.getResponseData()
            boolean r0 = com.webmd.webmdrx.util.StringUtil.isNotEmpty(r0)
            if (r0 == 0) goto L_0x00d3
            com.google.gson.Gson r0 = new com.google.gson.Gson     // Catch:{ Exception -> 0x00c0 }
            r0.<init>()     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r9 = r9.getResponseData()     // Catch:{ Exception -> 0x00c0 }
            java.lang.Class<com.webmd.webmdrx.models.PricingResponse> r1 = com.webmd.webmdrx.models.PricingResponse.class
            java.lang.Object r9 = r0.fromJson((java.lang.String) r9, r1)     // Catch:{ Exception -> 0x00c0 }
            com.webmd.webmdrx.models.PricingResponse r9 = (com.webmd.webmdrx.models.PricingResponse) r9     // Catch:{ Exception -> 0x00c0 }
            if (r9 == 0) goto L_0x00ad
            com.webmd.webmdrx.models.Data r0 = r9.getData()     // Catch:{ Exception -> 0x008d }
            com.webmd.webmdrx.models.RxPricing r0 = r0.getRxpricing()     // Catch:{ Exception -> 0x008d }
            com.webmd.webmdrx.models.RxPricingData r0 = r0.getData()     // Catch:{ Exception -> 0x008d }
            if (r0 == 0) goto L_0x009f
            com.webmd.webmdrx.models.Price[] r1 = r0.getPrices()     // Catch:{ Exception -> 0x008d }
            if (r1 == 0) goto L_0x009f
            com.webmd.webmdrx.models.Price[] r0 = r0.getPrices()     // Catch:{ Exception -> 0x008d }
            int r1 = r0.length     // Catch:{ Exception -> 0x008d }
            r2 = 0
        L_0x0050:
            if (r2 >= r1) goto L_0x009f
            r3 = r0[r2]     // Catch:{ Exception -> 0x008d }
            android.location.Location r4 = new android.location.Location     // Catch:{ Exception -> 0x008d }
            java.lang.String r5 = ""
            r4.<init>(r5)     // Catch:{ Exception -> 0x008d }
            com.webmd.webmdrx.models.Pharmacy r5 = r3.getPharmacy()     // Catch:{ Exception -> 0x008d }
            java.lang.Double r5 = r5.getLatitude()     // Catch:{ Exception -> 0x008d }
            double r5 = r5.doubleValue()     // Catch:{ Exception -> 0x008d }
            r4.setLatitude(r5)     // Catch:{ Exception -> 0x008d }
            com.webmd.webmdrx.models.Pharmacy r5 = r3.getPharmacy()     // Catch:{ Exception -> 0x008d }
            java.lang.Double r5 = r5.getLongitude()     // Catch:{ Exception -> 0x008d }
            double r5 = r5.doubleValue()     // Catch:{ Exception -> 0x008d }
            r4.setLongitude(r5)     // Catch:{ Exception -> 0x008d }
            android.location.Location r5 = r8.mLocation     // Catch:{ Exception -> 0x008d }
            float r4 = r5.distanceTo(r4)     // Catch:{ Exception -> 0x008d }
            double r4 = (double) r4     // Catch:{ Exception -> 0x008d }
            r6 = 4654751689864118272(0x4099000000000000, double:1600.0)
            double r4 = r4 / r6
            com.webmd.webmdrx.models.Pharmacy r3 = r3.getPharmacy()     // Catch:{ Exception -> 0x008d }
            r3.setDistance(r4)     // Catch:{ Exception -> 0x008d }
            int r2 = r2 + 1
            goto L_0x0050
        L_0x008d:
            com.webmd.webmdrx.util.WebMDException r0 = new com.webmd.webmdrx.util.WebMDException     // Catch:{ Exception -> 0x00c0 }
            android.content.Context r1 = r8.mContext     // Catch:{ Exception -> 0x00c0 }
            int r2 = com.webmd.webmdrx.R.string.unknown_format_error_message     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r1 = r1.getString(r2)     // Catch:{ Exception -> 0x00c0 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x00c0 }
            com.webmd.webmdrx.intf.IPricingReceivedListener r1 = r8.mPricingReceivedListener     // Catch:{ Exception -> 0x00c0 }
            r1.onPricingRequestFailed(r0)     // Catch:{ Exception -> 0x00c0 }
        L_0x009f:
            com.webmd.webmdrx.intf.IPricingReceivedListener r0 = r8.mPricingReceivedListener     // Catch:{ Exception -> 0x00c0 }
            com.webmd.webmdrx.models.Data r9 = r9.getData()     // Catch:{ Exception -> 0x00c0 }
            com.webmd.webmdrx.models.RxPricing r9 = r9.getRxpricing()     // Catch:{ Exception -> 0x00c0 }
            r0.onPricingReceived(r9)     // Catch:{ Exception -> 0x00c0 }
            goto L_0x0100
        L_0x00ad:
            com.webmd.webmdrx.util.WebMDException r9 = new com.webmd.webmdrx.util.WebMDException     // Catch:{ Exception -> 0x00c0 }
            android.content.Context r0 = r8.mContext     // Catch:{ Exception -> 0x00c0 }
            int r1 = com.webmd.webmdrx.R.string.unknown_format_error_message     // Catch:{ Exception -> 0x00c0 }
            java.lang.String r0 = r0.getString(r1)     // Catch:{ Exception -> 0x00c0 }
            r9.<init>(r0)     // Catch:{ Exception -> 0x00c0 }
            com.webmd.webmdrx.intf.IPricingReceivedListener r0 = r8.mPricingReceivedListener     // Catch:{ Exception -> 0x00c0 }
            r0.onPricingRequestFailed(r9)     // Catch:{ Exception -> 0x00c0 }
            goto L_0x0100
        L_0x00c0:
            com.webmd.webmdrx.util.WebMDException r9 = new com.webmd.webmdrx.util.WebMDException
            android.content.Context r0 = r8.mContext
            int r1 = com.webmd.webmdrx.R.string.connection_error_message
            java.lang.String r0 = r0.getString(r1)
            r9.<init>(r0)
            com.webmd.webmdrx.intf.IPricingReceivedListener r0 = r8.mPricingReceivedListener
            r0.onPricingRequestFailed(r9)
            goto L_0x0100
        L_0x00d3:
            com.webmd.webmdrx.util.WebMDException r9 = new com.webmd.webmdrx.util.WebMDException
            android.content.Context r0 = r8.mContext
            int r1 = com.webmd.webmdrx.R.string.connection_error_message
            java.lang.String r0 = r0.getString(r1)
            r9.<init>(r0)
            com.webmd.webmdrx.intf.IPricingReceivedListener r0 = r8.mPricingReceivedListener
            r0.onPricingRequestFailed(r9)
            goto L_0x0100
        L_0x00e6:
            if (r9 == 0) goto L_0x0100
            java.lang.String r0 = r9.getResponseErrorMsg()
            boolean r0 = com.webmd.webmdrx.util.StringUtil.isNotEmpty(r0)
            if (r0 == 0) goto L_0x0100
            com.webmd.webmdrx.util.WebMDException r0 = new com.webmd.webmdrx.util.WebMDException
            java.lang.String r9 = r9.getResponseErrorMsg()
            r0.<init>(r9)
            com.webmd.webmdrx.intf.IPricingReceivedListener r9 = r8.mPricingReceivedListener
            r9.onPricingRequestFailed(r0)
        L_0x0100:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.tasks.GetPharmaciesForGroupTask.onPostExecute(com.webmd.webmdrx.http.HttpResponseObject):void");
    }
}
