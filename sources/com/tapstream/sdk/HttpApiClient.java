package com.tapstream.sdk;

import com.tapstream.sdk.ActivityEventSource;
import com.tapstream.sdk.Event;
import com.tapstream.sdk.errors.ApiException;
import com.tapstream.sdk.errors.UnrecoverableApiException;
import com.tapstream.sdk.http.AsyncHttpClient;
import com.tapstream.sdk.http.AsyncHttpRequest;
import com.tapstream.sdk.http.HttpClient;
import com.tapstream.sdk.http.HttpResponse;
import com.tapstream.sdk.http.RequestBuilders;
import com.tapstream.sdk.http.StdLibHttpClient;
import com.tapstream.sdk.landers.Lander;
import com.tapstream.sdk.landers.LanderApiResponse;
import com.tapstream.sdk.wordofmouth.Offer;
import com.tapstream.sdk.wordofmouth.OfferApiResponse;
import com.tapstream.sdk.wordofmouth.Reward;
import com.tapstream.sdk.wordofmouth.RewardApiResponse;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpApiClient implements ApiClient {
    public static final String VERSION = "3.3.1";
    private final AsyncHttpClient asyncClient;
    /* access modifiers changed from: private */
    public Event.Params commonEventParams;
    private final Config config;
    private final ScheduledExecutorService executor;
    /* access modifiers changed from: private */
    public final OneTimeOnlyEventTracker oneTimeEventTracker;
    /* access modifiers changed from: private */
    public final Platform platform;
    private boolean queueRequests;
    private List<Runnable> queuedRequests;
    private final AtomicBoolean started;

    public HttpApiClient(Platform platform2, Config config2) {
        this(platform2, config2, new StdLibHttpClient(), Executors.newSingleThreadScheduledExecutor(new DaemonThreadFactory()));
    }

    public HttpApiClient(Platform platform2, Config config2, HttpClient httpClient, ScheduledExecutorService scheduledExecutorService) {
        this.started = new AtomicBoolean(false);
        this.queueRequests = true;
        this.queuedRequests = new ArrayList();
        this.platform = platform2;
        this.config = config2;
        this.executor = scheduledExecutorService;
        this.asyncClient = new AsyncHttpClient(httpClient, scheduledExecutorService);
        this.oneTimeEventTracker = new OneTimeOnlyEventTracker(platform2);
    }

    /* access modifiers changed from: package-private */
    public Event.Params getCommonEventParams() {
        return this.commonEventParams;
    }

    public void close() throws IOException {
        Utils.closeQuietly(this.asyncClient);
        this.executor.shutdownNow();
        try {
            this.executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (Exception unused) {
            Logging.log(5, "Failed to shutdown executor", new Object[0]);
        }
    }

    public void start() {
        final String str;
        String str2;
        if (this.started.compareAndSet(false, true)) {
            String str3 = (String) Utils.getOrDefault(this.platform.getAppName(), "");
            if (this.config.getFireAutomaticInstallEvent()) {
                if (this.config.getInstallEventName() == null) {
                    str2 = String.format(Locale.US, "android-%s-install", new Object[]{str3});
                } else {
                    str2 = this.config.getInstallEventName();
                }
                fireEvent(new Event(str2, true));
            }
            if (this.config.getFireAutomaticOpenEvent()) {
                if (this.config.getOpenEventName() == null) {
                    str = String.format(Locale.US, "android-%s-open", new Object[]{str3});
                } else {
                    str = this.config.getOpenEventName();
                }
                ActivityEventSource activityEventSource = this.platform.getActivityEventSource();
                if (activityEventSource == null || this.config.getActivityListenerBindsLate()) {
                    fireEvent(new Event(str, false));
                }
                if (activityEventSource != null) {
                    activityEventSource.setListener(new ActivityEventSource.ActivityListener() {
                        public void onOpen() {
                            HttpApiClient.this.fireEvent(new Event(str, false));
                        }
                    });
                }
            }
            this.executor.submit(new Runnable() {
                public void run() {
                    HttpApiClient httpApiClient = HttpApiClient.this;
                    Event.Params unused = httpApiClient.commonEventParams = httpApiClient.buildCommonEventParams();
                    HttpApiClient.this.dispatchQueuedRequests();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public Event.Params buildCommonEventParams() {
        AdvertisingID advertisingID;
        Event.Params params = new Event.Params();
        params.put("secret", this.config.getDeveloperSecret());
        params.put("sdkversion", "3.3.1");
        params.put("hardware-odin1", this.config.getOdin1());
        params.put("hardware-open-udid", this.config.getOpenUdid());
        params.put("hardware-wifi-mac", this.config.getWifiMac());
        params.put("hardware-android-device-id", this.config.getDeviceId());
        params.put("hardware-android-android-id", this.config.getAndroidId());
        params.put("uuid", this.platform.loadSessionId());
        params.put("platform", "Android");
        params.put("vendor", this.platform.getManufacturer());
        params.put("model", this.platform.getModel());
        params.put(AdParameterKeys.OS, this.platform.getOs());
        params.put("resolution", this.platform.getResolution());
        params.put("locale", this.platform.getLocale());
        params.put("app-name", this.platform.getAppName());
        params.put("app-version", this.platform.getAppVersion());
        params.put("package-name", this.platform.getPackageName());
        params.put("gmtoffset", Integer.toString(TimeZone.getDefault().getOffset(new Date().getTime()) / 1000));
        Callable<AdvertisingID> adIdFetcher = this.platform.getAdIdFetcher();
        if (adIdFetcher != null && this.config.getCollectAdvertisingId()) {
            try {
                advertisingID = adIdFetcher.call();
            } catch (Exception unused) {
                advertisingID = null;
            }
            if (advertisingID == null || !advertisingID.isValid()) {
                Logging.log(5, "Advertising ID could not be collected. Is Google Play Services installed?", new Object[0]);
            } else {
                params.put("hardware-android-advertising-id", advertisingID.getId());
                params.put("android-limit-ad-tracking", Boolean.toString(advertisingID.isLimitAdTracking()));
            }
        }
        String referrer = this.platform.getReferrer();
        if (referrer != null && referrer.length() > 0) {
            params.put("android-referrer", referrer);
        }
        return params;
    }

    /* access modifiers changed from: private */
    public synchronized void dispatchQueuedRequests() {
        this.queueRequests = false;
        for (Runnable submit : this.queuedRequests) {
            this.executor.submit(submit);
        }
        this.queuedRequests = null;
    }

    public ApiFuture<EventApiResponse> fireEvent(Event event) {
        SettableApiFuture settableApiFuture = new SettableApiFuture();
        try {
            fireEvent(event, settableApiFuture);
        } catch (Exception e) {
            settableApiFuture.setException(e);
        }
        return settableApiFuture;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r5.prepare((java.lang.String) com.tapstream.sdk.Utils.getOrDefault(r4.platform.getAppName(), ""));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (r5.isOneTimeOnly() == false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r4.oneTimeEventTracker.hasBeenAlreadySent(r5) == false) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        com.tapstream.sdk.Logging.log(4, "Ignoring event named \"%s\" because it is a one-time-only event that has already been fired", r5.getName());
        r6.setException(new com.tapstream.sdk.errors.EventAlreadyFiredException());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0049, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004a, code lost:
        r4.oneTimeEventTracker.inProgress(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004f, code lost:
        r4.asyncClient.sendRequest(com.tapstream.sdk.http.RequestBuilders.eventRequestBuilder(r4.config.getAccountName(), r5.getName()).postBody(r5.buildPostBody(r4.commonEventParams, r4.config.getGlobalEventParams())).build(), r4.config.getDataCollectionRetryStrategy(), new com.tapstream.sdk.HttpApiClient.AnonymousClass4(r4), r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void fireEvent(final com.tapstream.sdk.Event r5, final com.tapstream.sdk.SettableApiFuture<com.tapstream.sdk.EventApiResponse> r6) {
        /*
            r4 = this;
            monitor-enter(r4)     // Catch:{ Exception -> 0x0085 }
            boolean r0 = r4.queueRequests     // Catch:{ all -> 0x0082 }
            if (r0 == 0) goto L_0x0011
            java.util.List<java.lang.Runnable> r0 = r4.queuedRequests     // Catch:{ all -> 0x0082 }
            com.tapstream.sdk.HttpApiClient$3 r1 = new com.tapstream.sdk.HttpApiClient$3     // Catch:{ all -> 0x0082 }
            r1.<init>(r5, r6)     // Catch:{ all -> 0x0082 }
            r0.add(r1)     // Catch:{ all -> 0x0082 }
            monitor-exit(r4)     // Catch:{ all -> 0x0082 }
            return
        L_0x0011:
            monitor-exit(r4)     // Catch:{ all -> 0x0082 }
            com.tapstream.sdk.Platform r0 = r4.platform     // Catch:{ Exception -> 0x0085 }
            java.lang.String r0 = r0.getAppName()     // Catch:{ Exception -> 0x0085 }
            java.lang.String r1 = ""
            java.lang.Object r0 = com.tapstream.sdk.Utils.getOrDefault(r0, r1)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0085 }
            r5.prepare(r0)     // Catch:{ Exception -> 0x0085 }
            boolean r0 = r5.isOneTimeOnly()     // Catch:{ Exception -> 0x0085 }
            if (r0 == 0) goto L_0x004f
            com.tapstream.sdk.OneTimeOnlyEventTracker r0 = r4.oneTimeEventTracker     // Catch:{ Exception -> 0x0085 }
            boolean r0 = r0.hasBeenAlreadySent(r5)     // Catch:{ Exception -> 0x0085 }
            if (r0 == 0) goto L_0x004a
            r0 = 4
            java.lang.String r1 = "Ignoring event named \"%s\" because it is a one-time-only event that has already been fired"
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0085 }
            r3 = 0
            java.lang.String r5 = r5.getName()     // Catch:{ Exception -> 0x0085 }
            r2[r3] = r5     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.Logging.log(r0, r1, r2)     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.errors.EventAlreadyFiredException r5 = new com.tapstream.sdk.errors.EventAlreadyFiredException     // Catch:{ Exception -> 0x0085 }
            r5.<init>()     // Catch:{ Exception -> 0x0085 }
            r6.setException(r5)     // Catch:{ Exception -> 0x0085 }
            return
        L_0x004a:
            com.tapstream.sdk.OneTimeOnlyEventTracker r0 = r4.oneTimeEventTracker     // Catch:{ Exception -> 0x0085 }
            r0.inProgress(r5)     // Catch:{ Exception -> 0x0085 }
        L_0x004f:
            com.tapstream.sdk.Config r0 = r4.config     // Catch:{ Exception -> 0x0085 }
            java.lang.String r0 = r0.getAccountName()     // Catch:{ Exception -> 0x0085 }
            java.lang.String r1 = r5.getName()     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.http.HttpRequest$Builder r0 = com.tapstream.sdk.http.RequestBuilders.eventRequestBuilder(r0, r1)     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.Event$Params r1 = r4.commonEventParams     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.Config r2 = r4.config     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.Event$Params r2 = r2.getGlobalEventParams()     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.http.FormPostBody r1 = r5.buildPostBody(r1, r2)     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.http.HttpRequest$Builder r0 = r0.postBody(r1)     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.http.HttpRequest r0 = r0.build()     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.HttpApiClient$4 r1 = new com.tapstream.sdk.HttpApiClient$4     // Catch:{ Exception -> 0x0085 }
            r1.<init>(r5)     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.http.AsyncHttpClient r5 = r4.asyncClient     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.Config r2 = r4.config     // Catch:{ Exception -> 0x0085 }
            com.tapstream.sdk.Retry$Strategy r2 = r2.getDataCollectionRetryStrategy()     // Catch:{ Exception -> 0x0085 }
            r5.sendRequest(r0, r2, r1, r6)     // Catch:{ Exception -> 0x0085 }
            goto L_0x0089
        L_0x0082:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0082 }
            throw r5     // Catch:{ Exception -> 0x0085 }
        L_0x0085:
            r5 = move-exception
            r6.setException(r5)
        L_0x0089:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapstream.sdk.HttpApiClient.fireEvent(com.tapstream.sdk.Event, com.tapstream.sdk.SettableApiFuture):void");
    }

    public ApiFuture<TimelineApiResponse> lookupTimeline() {
        SettableApiFuture settableApiFuture = new SettableApiFuture();
        try {
            lookupTimeline(settableApiFuture);
        } catch (Exception e) {
            settableApiFuture.setException(e);
        }
        return settableApiFuture;
    }

    /* access modifiers changed from: private */
    public void lookupTimeline(final SettableApiFuture<TimelineApiResponse> settableApiFuture) {
        try {
            synchronized (this) {
                if (this.queueRequests) {
                    this.queuedRequests.add(new Runnable() {
                        public void run() {
                            HttpApiClient.this.lookupTimeline(settableApiFuture);
                        }
                    });
                    return;
                }
                this.asyncClient.sendRequest(RequestBuilders.timelineLookupRequestBuilder(this.config.getDeveloperSecret(), this.platform.loadSessionId()).build(), this.config.getUserFacingRequestRetryStrategy(), new AsyncHttpRequest.Handler<TimelineApiResponse>() {
                    public TimelineApiResponse checkedRun(HttpResponse httpResponse) throws IOException, ApiException {
                        return new TimelineApiResponse(httpResponse);
                    }
                }, settableApiFuture);
            }
        } catch (Exception e) {
            settableApiFuture.setException(e);
        }
    }

    public ApiFuture<TimelineSummaryResponse> getTimelineSummary() {
        SettableApiFuture settableApiFuture = new SettableApiFuture();
        try {
            getTimelineSummary(settableApiFuture);
        } catch (Exception e) {
            settableApiFuture.setException(e);
        }
        return settableApiFuture;
    }

    /* access modifiers changed from: private */
    public void getTimelineSummary(final SettableApiFuture<TimelineSummaryResponse> settableApiFuture) {
        try {
            synchronized (this) {
                if (this.queueRequests) {
                    this.queuedRequests.add(new Runnable() {
                        public void run() {
                            HttpApiClient.this.getTimelineSummary(settableApiFuture);
                        }
                    });
                    return;
                }
                this.asyncClient.sendRequest(RequestBuilders.timelineSummaryRequestBuilder(this.config.getDeveloperSecret(), this.platform.loadSessionId()).build(), this.config.getUserFacingRequestRetryStrategy(), new AsyncHttpRequest.Handler<TimelineSummaryResponse>() {
                    public TimelineSummaryResponse checkedRun(HttpResponse httpResponse) throws IOException, ApiException {
                        return TimelineSummaryResponse.createSummaryResponse(httpResponse);
                    }
                }, settableApiFuture);
            }
        } catch (Exception e) {
            settableApiFuture.setException(e);
        }
    }

    public ApiFuture<OfferApiResponse> getWordOfMouthOffer(String str) {
        SettableApiFuture settableApiFuture = new SettableApiFuture();
        try {
            this.asyncClient.sendRequest(RequestBuilders.wordOfMouthOfferRequestBuilder(this.config.getDeveloperSecret(), str, this.platform.getPackageName()).build(), this.config.getUserFacingRequestRetryStrategy(), new AsyncHttpRequest.Handler<OfferApiResponse>() {
                public OfferApiResponse checkedRun(HttpResponse httpResponse) throws IOException, ApiException {
                    return new OfferApiResponse(httpResponse, Offer.fromApiResponse(new JSONObject(httpResponse.getBodyAsString())));
                }
            }, settableApiFuture);
        } catch (Exception e) {
            settableApiFuture.setException(e);
        }
        return settableApiFuture;
    }

    public ApiFuture<RewardApiResponse> getWordOfMouthRewardList() {
        SettableApiFuture settableApiFuture = new SettableApiFuture();
        try {
            this.asyncClient.sendRequest(RequestBuilders.wordOfMouthRewardRequestBuilder(this.config.getDeveloperSecret(), this.platform.loadSessionId()).build(), this.config.getUserFacingRequestRetryStrategy(), new AsyncHttpRequest.Handler<RewardApiResponse>() {
                public RewardApiResponse checkedRun(HttpResponse httpResponse) throws IOException, ApiException {
                    JSONArray jSONArray = new JSONArray(httpResponse.getBodyAsString());
                    ArrayList arrayList = new ArrayList(jSONArray.length());
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Reward fromApiResponse = Reward.fromApiResponse(jSONArray.getJSONObject(i));
                        if (!HttpApiClient.this.platform.isConsumed(fromApiResponse)) {
                            arrayList.add(fromApiResponse);
                        }
                    }
                    return new RewardApiResponse(httpResponse, arrayList);
                }
            }, settableApiFuture);
        } catch (Exception e) {
            settableApiFuture.setException(e);
        }
        return settableApiFuture;
    }

    public ApiFuture<LanderApiResponse> getInAppLander() {
        SettableApiFuture settableApiFuture = new SettableApiFuture();
        try {
            this.asyncClient.sendRequest(RequestBuilders.inAppLanderRequestBuilder(this.config.getDeveloperSecret(), this.platform.loadSessionId()).build(), this.config.getUserFacingRequestRetryStrategy(), new AsyncHttpRequest.Handler<LanderApiResponse>() {
                public LanderApiResponse checkedRun(HttpResponse httpResponse) throws IOException, ApiException {
                    return new LanderApiResponse(httpResponse, Lander.fromApiResponse(new JSONObject(httpResponse.getBodyAsString())));
                }

                public void onFailure(UnrecoverableApiException unrecoverableApiException, HttpResponse httpResponse) {
                    if (httpResponse.getStatus() == 404) {
                        Logging.log(4, "No lander found for this session.", new Object[0]);
                    } else {
                        onError(unrecoverableApiException);
                    }
                }
            }, settableApiFuture);
        } catch (Exception e) {
            settableApiFuture.setException(e);
        }
        return settableApiFuture;
    }
}
