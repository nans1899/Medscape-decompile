package com.webmd.webmdrx.tasks;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import com.google.gson.Gson;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.http.HttpRequestObject;
import com.webmd.webmdrx.http.HttpResponseObject;
import com.webmd.webmdrx.http.HttpUtils;
import com.webmd.webmdrx.intf.IPricingReceivedListener;
import com.webmd.webmdrx.models.Price;
import com.webmd.webmdrx.models.PricingResponse;
import com.webmd.webmdrx.models.RxPricingData;
import com.webmd.webmdrx.util.StringUtil;
import com.webmd.webmdrx.util.WebMDException;

public class GetPricingDetailsForDrugTask extends AsyncTask<Void, String, HttpResponseObject> {
    /* access modifiers changed from: private */
    public Context mContext;
    private Location mLocation;
    /* access modifiers changed from: private */
    public IPricingReceivedListener mPricingReceivedListener;
    private HttpRequestObject mRequestObject;
    /* access modifiers changed from: private */
    public GetPricingDetailsForDrugTask task;

    public GetPricingDetailsForDrugTask(Context context, HttpRequestObject httpRequestObject, String str, Location location, IPricingReceivedListener iPricingReceivedListener) {
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
                if (GetPricingDetailsForDrugTask.this.task.getStatus() == AsyncTask.Status.RUNNING && !GetPricingDetailsForDrugTask.this.task.isCancelled()) {
                    GetPricingDetailsForDrugTask.this.task.cancel(true);
                    GetPricingDetailsForDrugTask.this.mPricingReceivedListener.onPricingRequestFailed(new WebMDException(GetPricingDetailsForDrugTask.this.mContext.getString(R.string.connection_error_message)));
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
    public void onPostExecute(HttpResponseObject httpResponseObject) {
        super.onPostExecute(httpResponseObject);
        if (this.mPricingReceivedListener != null && !isCancelled()) {
            if (httpResponseObject == null || StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
                if (httpResponseObject != null && StringUtil.isNotEmpty(httpResponseObject.getResponseErrorMsg())) {
                    this.mPricingReceivedListener.onPricingRequestFailed(new WebMDException(httpResponseObject.getResponseErrorMsg()));
                }
            } else if (StringUtil.isNotEmpty(httpResponseObject.getResponseData())) {
                try {
                    PricingResponse pricingResponse = (PricingResponse) new Gson().fromJson(httpResponseObject.getResponseData(), PricingResponse.class);
                    if (pricingResponse != null) {
                        RxPricingData data = pricingResponse.getData().getRxpricing().getData();
                        if (!(data == null || data.getPrices() == null)) {
                            for (Price price : data.getPrices()) {
                                Location location = new Location("");
                                location.setLatitude(price.getPharmacy().getLatitude().doubleValue());
                                location.setLongitude(price.getPharmacy().getLongitude().doubleValue());
                                price.getPharmacy().setDistance(((double) this.mLocation.distanceTo(location)) / 1600.0d);
                            }
                        }
                        this.mPricingReceivedListener.onPricingReceived(pricingResponse.getData().getRxpricing());
                        return;
                    }
                    this.mPricingReceivedListener.onPricingRequestFailed(new WebMDException(this.mContext.getString(R.string.unknown_format_error_message)));
                } catch (Exception unused) {
                    this.mPricingReceivedListener.onPricingRequestFailed(new WebMDException(this.mContext.getString(R.string.no_pricing_results)));
                }
            } else {
                this.mPricingReceivedListener.onPricingRequestFailed(new WebMDException(this.mContext.getString(R.string.connection_error_message)));
            }
        }
    }
}
