package com.webmd.webmdrx.manager;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.places.model.PlaceFields;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.gson.JsonObject;
import com.webmd.webmdrx.http.HttpRequestObject;
import com.webmd.webmdrx.intf.IDrugDetailsListener;
import com.webmd.webmdrx.intf.IDrugInteractionsListener;
import com.webmd.webmdrx.intf.IDrugsReceivedListener;
import com.webmd.webmdrx.intf.IPricingReceivedListener;
import com.webmd.webmdrx.intf.IRxFormReceivedListener;
import com.webmd.webmdrx.intf.IRxShareSavingsCardListener;
import com.webmd.webmdrx.models.Coupon;
import com.webmd.webmdrx.models.SmsResponse;
import com.webmd.webmdrx.tasks.GetPharmaciesForGroupTask;
import com.webmd.webmdrx.tasks.GetPricingDetailsForDrugTask;
import com.webmd.webmdrx.tasks.SearchForDrugTask;
import com.webmd.webmdrx.tasks.SearchForPrescriptionDetailsTask;
import com.webmd.webmdrx.tasks.ShareSavingsCardTask;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import webmd.com.environmentswitcher.EnvironmentManager;

public class ApiManager {
    public static String APP_ID = "app-webmd-a";
    static String TAG = ApiManager.class.getSimpleName();
    private static volatile ApiManager mManager = null;
    private GetPharmaciesForGroupTask mGetPharmaciesForGroupTask;
    private GetPricingDetailsForDrugTask mGetPricingDetailsForDrugTask;
    private SearchForDrugTask mSearchForDrugsTask;
    private SearchForPrescriptionDetailsTask mSearchForPrescriptionDetailsTask;
    private ShareSavingsCardTask mShareSavingsCardTask;
    private AtomicBoolean stopFetch = new AtomicBoolean();

    public interface RetrofitServices {
        @POST("rx/print?")
        @Headers({"Accept: application/json"})
        Call<Coupon> rxCard(@HeaderMap Map<String, String> map);

        @POST("sendemail/?")
        @Headers({"Accept: application/json"})
        Call<SmsResponse> rxSendMail(@HeaderMap Map<String, String> map, @Body JsonObject jsonObject);

        @POST("rx/sendsms/?")
        @Headers({"Accept: application/json"})
        Call<SmsResponse> rxSendSms(@HeaderMap Map<String, String> map, @Body JsonObject jsonObject);
    }

    public void fetchUniqueMemberIDForUser(Context context) {
    }

    public static ApiManager getInstance() {
        if (mManager == null) {
            synchronized (ApiManager.class) {
                if (mManager == null) {
                    mManager = new ApiManager();
                }
            }
        }
        return mManager;
    }

    private ApiManager() {
    }

    public void fetchSearchResultsForRequest(Context context, IDrugsReceivedListener iDrugsReceivedListener, String str) {
        SearchForDrugTask searchForDrugTask = this.mSearchForDrugsTask;
        if (searchForDrugTask != null && searchForDrugTask.getStatus() == AsyncTask.Status.RUNNING) {
            this.mSearchForDrugsTask.cancel(true);
        }
        EnvironmentManager instance = EnvironmentManager.getInstance(context);
        long currentTimeMillis = System.currentTimeMillis();
        String clientSecretHashForTimestamp = instance.getClientSecretHashForTimestamp(currentTimeMillis);
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.addToHeadersMap("enc_data", clientSecretHashForTimestamp);
        httpRequestObject.addToHeadersMap("timestamp", String.format("%s", new Object[]{Long.valueOf(currentTimeMillis)}));
        httpRequestObject.addToHeadersMap("client_id", instance.getClientId());
        httpRequestObject.setUrl(String.format(instance.getRxTypeAheadLink() + "%s?app_id=%s&vendor=4&count=50", new Object[]{str, APP_ID}));
        httpRequestObject.setRequestMethod("GET");
        SearchForDrugTask searchForDrugTask2 = new SearchForDrugTask(context, iDrugsReceivedListener, httpRequestObject, str);
        this.mSearchForDrugsTask = searchForDrugTask2;
        searchForDrugTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void fetchPrescriptionDetailsForDrugId(Context context, String str, IRxFormReceivedListener iRxFormReceivedListener) {
        SearchForPrescriptionDetailsTask searchForPrescriptionDetailsTask = this.mSearchForPrescriptionDetailsTask;
        if (searchForPrescriptionDetailsTask != null && searchForPrescriptionDetailsTask.getStatus() == AsyncTask.Status.RUNNING) {
            this.mSearchForPrescriptionDetailsTask.cancel(true);
        }
        EnvironmentManager instance = EnvironmentManager.getInstance(context);
        long currentTimeMillis = System.currentTimeMillis();
        String clientSecretHashForTimestamp = instance.getClientSecretHashForTimestamp(currentTimeMillis);
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.addToHeadersMap("enc_data", clientSecretHashForTimestamp);
        httpRequestObject.addToHeadersMap("timestamp", String.format("%s", new Object[]{Long.valueOf(currentTimeMillis)}));
        httpRequestObject.addToHeadersMap("client_id", instance.getClientId());
        httpRequestObject.setUrl(String.format(instance.getDrugFormUrl() + "v2/%s?app_id=%s", new Object[]{str, APP_ID}));
        httpRequestObject.setRequestMethod("GET");
        SearchForPrescriptionDetailsTask searchForPrescriptionDetailsTask2 = new SearchForPrescriptionDetailsTask(context, httpRequestObject, str, iRxFormReceivedListener);
        this.mSearchForPrescriptionDetailsTask = searchForPrescriptionDetailsTask2;
        searchForPrescriptionDetailsTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void fetchPricingDetailsForDrugIdAtLocation(Context context, String str, double d, Location location, int i, IPricingReceivedListener iPricingReceivedListener, String str2) {
        GetPricingDetailsForDrugTask getPricingDetailsForDrugTask = this.mGetPricingDetailsForDrugTask;
        if (getPricingDetailsForDrugTask != null && getPricingDetailsForDrugTask.getStatus() == AsyncTask.Status.RUNNING) {
            this.mGetPricingDetailsForDrugTask.cancel(true);
        }
        EnvironmentManager instance = EnvironmentManager.getInstance(context);
        long currentTimeMillis = System.currentTimeMillis();
        String clientSecretHashForTimestamp = instance.getClientSecretHashForTimestamp(currentTimeMillis);
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.addToHeadersMap("enc_data", clientSecretHashForTimestamp);
        httpRequestObject.addToHeadersMap("timestamp", String.format("%s", new Object[]{Long.valueOf(currentTimeMillis)}));
        httpRequestObject.addToHeadersMap("client_id", instance.getClientId());
        httpRequestObject.setUrl(String.format(instance.getDrugPricingUrl() + "ndc/%s/%s/%s?&lat=%s&lng=%s&rad=%s&rollup=true&app_id=%s", new Object[]{str, Double.valueOf(d), str2, Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()), Integer.valueOf(i), APP_ID}));
        httpRequestObject.setRequestMethod("GET");
        GetPricingDetailsForDrugTask getPricingDetailsForDrugTask2 = new GetPricingDetailsForDrugTask(context, httpRequestObject, str, location, iPricingReceivedListener);
        this.mGetPricingDetailsForDrugTask = getPricingDetailsForDrugTask2;
        getPricingDetailsForDrugTask2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void fetchDrugDetails(Context context, String str, IDrugDetailsListener iDrugDetailsListener) {
        String str2 = str;
        final IDrugDetailsListener iDrugDetailsListener2 = iDrugDetailsListener;
        if (context == null || str2 == null) {
            iDrugDetailsListener2.onDrugDetailsFailed("Request failed");
            return;
        }
        RequestQueue newRequestQueue = Volley.newRequestQueue(context);
        EnvironmentManager instance = EnvironmentManager.getInstance(context);
        final long currentTimeMillis = System.currentTimeMillis();
        final String clientSecretHashForTimestamp = instance.getClientSecretHashForTimestamp(currentTimeMillis);
        final String clientId = instance.getClientId();
        newRequestQueue.add(new JsonObjectRequest(this, 0, EnvironmentManager.getInstance(context).getRequestLink("WBMD_DRUG_DETAIL_URL") + "?ndc=" + str2 + "&lang=en,app_id=" + APP_ID, (JSONObject) null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                iDrugDetailsListener2.onDrugDetailsFetch(jSONObject);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                iDrugDetailsListener2.onDrugDetailsFailed("Request failed");
            }
        }) {
            final /* synthetic */ ApiManager this$0;

            {
                this.this$0 = r8;
            }

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("client_id", clientId);
                hashMap.put("enc_data", clientSecretHashForTimestamp);
                hashMap.put("timestamp", String.format("%s", new Object[]{Long.valueOf(currentTimeMillis)}));
                return hashMap;
            }
        });
    }

    public void fetchDrugDetails(Context context, String str, String str2, IDrugDetailsListener iDrugDetailsListener) {
        String str3 = str;
        String str4 = str2;
        final IDrugDetailsListener iDrugDetailsListener2 = iDrugDetailsListener;
        if (context == null || str3 == null || str4 == null) {
            iDrugDetailsListener2.onDrugDetailsFailed("Request failed");
            return;
        }
        RequestQueue newRequestQueue = Volley.newRequestQueue(context);
        EnvironmentManager instance = EnvironmentManager.getInstance(context);
        final long currentTimeMillis = System.currentTimeMillis();
        final String clientSecretHashForTimestamp = instance.getClientSecretHashForTimestamp(currentTimeMillis);
        final String clientId = instance.getClientId();
        String requestLink = EnvironmentManager.getInstance(context).getRequestLink("WBMD_DRUG_DETAIL_URL");
        if (!str.isEmpty()) {
            requestLink = requestLink + "?drugid=" + str3;
            if (!str2.isEmpty()) {
                requestLink = requestLink + "&monoid=" + str4;
            }
        } else if (!str2.isEmpty()) {
            requestLink = requestLink + "?monoid=" + str4;
        } else {
            iDrugDetailsListener2.onDrugDetailsFailed("Unable to find drug");
        }
        newRequestQueue.add(new JsonObjectRequest(this, 0, requestLink + "&lang=en", (JSONObject) null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject jSONObject) {
                iDrugDetailsListener2.onDrugDetailsFetch(jSONObject);
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError volleyError) {
                iDrugDetailsListener2.onDrugDetailsFailed("Request failed");
            }
        }) {
            final /* synthetic */ ApiManager this$0;

            {
                this.this$0 = r8;
            }

            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap hashMap = new HashMap();
                hashMap.put("client_id", clientId);
                hashMap.put("enc_data", clientSecretHashForTimestamp);
                hashMap.put("timestamp", String.format("%s", new Object[]{Long.valueOf(currentTimeMillis)}));
                return hashMap;
            }
        });
    }

    public void fetchDrugInteractions(Context context, List<String> list, final IDrugInteractionsListener iDrugInteractionsListener) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (list == null || list.size() <= 0) {
            iDrugInteractionsListener.onInteractionsFailed("No ids");
            return;
        }
        for (String put : list) {
            jSONArray.put(put);
        }
        try {
            jSONObject.put("MaxSeverityLevel", "4");
            jSONObject.put("DrugIds", jSONArray);
            jSONObject.put("DrugType", "FDB");
            jSONObject.put("MinSeverityLevel", AppEventsConstants.EVENT_PARAM_VALUE_NO);
            jSONObject.put("MetaInfoType", "Consumer");
        } catch (JSONException unused) {
        }
        if (context != null) {
            Volley.newRequestQueue(context).add(new JsonObjectRequest(1, EnvironmentManager.getInstance(context).getRequestLink("WBMD_DRUG_INTERACTION_URL"), jSONObject, new Response.Listener<JSONObject>() {
                public void onResponse(JSONObject jSONObject) {
                    iDrugInteractionsListener.onInteractionsReceived(jSONObject);
                }
            }, new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    iDrugInteractionsListener.onInteractionsFailed(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_FAILED);
                }
            }));
        }
    }

    public void shareSavingsCard(Context context, String str, boolean z, HashMap<String, String> hashMap, IRxShareSavingsCardListener iRxShareSavingsCardListener, boolean z2) {
        ShareSavingsCardTask shareSavingsCardTask = this.mShareSavingsCardTask;
        if (shareSavingsCardTask != null && shareSavingsCardTask.getStatus() == AsyncTask.Status.RUNNING) {
            this.mShareSavingsCardTask.cancel(true);
        }
        EnvironmentManager instance = EnvironmentManager.getInstance(context);
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setUrl(instance.getRequestLink(z ? "WRX_SHARE_SAVINGS_EMAIL_URL" : "WRX_SHARE_SAVINGS_PHONE_URL", new RequestData(context)));
        httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
        httpRequestObject.setRequestMethod("POST");
        httpRequestObject.setHeaderMap(hashMap);
        JSONObject jSONObject = new JSONObject();
        if (z) {
            try {
                jSONObject.put("recipientEmail", str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            jSONObject.put(PlaceFields.PHONE, str);
        }
        httpRequestObject.setRequestBody(jSONObject.toString());
        this.mShareSavingsCardTask = new ShareSavingsCardTask(context, httpRequestObject, iRxShareSavingsCardListener, z2);
        if (Build.VERSION.SDK_INT >= 11) {
            this.mShareSavingsCardTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            this.mShareSavingsCardTask.execute(new Void[0]);
        }
    }

    public void resetAtomicBool() {
        this.stopFetch.set(false);
    }

    private Retrofit createRetrofit(String str) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Request request = chain.request();
                return chain.proceed(request.newBuilder().url(request.url().newBuilder().addQueryParameter("app_id", ApiManager.APP_ID).build()).build());
            }
        });
        return new Retrofit.Builder().baseUrl(str).addConverterFactory(GsonConverterFactory.create()).client(builder.build()).build();
    }

    public RetrofitServices provideRetrofitService(Context context) {
        if (context != null) {
            return (RetrofitServices) createRetrofit(EnvironmentManager.getInstance(context).getRxBaseUrl()).create(RetrofitServices.class);
        }
        return null;
    }
}
