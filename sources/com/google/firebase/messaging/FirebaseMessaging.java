package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.Metadata;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;
import com.wbmd.adlibrary.constants.AdParameterKeys;

/* compiled from: com.google.firebase:firebase-messaging@@20.3.0 */
public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";
    static TransportFactory transportFactory;
    private final Context context;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstanceId iid;
    private final Task<TopicsSubscriber> topicsSubscriberTask;

    public static synchronized FirebaseMessaging getInstance() {
        FirebaseMessaging instance;
        synchronized (FirebaseMessaging.class) {
            instance = getInstance(FirebaseApp.getInstance());
        }
        return instance;
    }

    static synchronized FirebaseMessaging getInstance(FirebaseApp firebaseApp2) {
        FirebaseMessaging firebaseMessaging;
        Class cls = FirebaseMessaging.class;
        synchronized (cls) {
            firebaseMessaging = (FirebaseMessaging) firebaseApp2.get(cls);
            Preconditions.checkNotNull(firebaseMessaging, "Firebase Messaging component is not present");
        }
        return firebaseMessaging;
    }

    FirebaseMessaging(FirebaseApp firebaseApp2, FirebaseInstanceId firebaseInstanceId, UserAgentPublisher userAgentPublisher, HeartBeatInfo heartBeatInfo, FirebaseInstallationsApi firebaseInstallationsApi, TransportFactory transportFactory2) {
        transportFactory = transportFactory2;
        this.firebaseApp = firebaseApp2;
        this.iid = firebaseInstanceId;
        Context applicationContext = firebaseApp2.getApplicationContext();
        this.context = applicationContext;
        Task<TopicsSubscriber> createInstance = TopicsSubscriber.createInstance(firebaseApp2, firebaseInstanceId, new Metadata(applicationContext), userAgentPublisher, heartBeatInfo, firebaseInstallationsApi, this.context, FcmExecutors.newTopicsSyncExecutor());
        this.topicsSubscriberTask = createInstance;
        createInstance.addOnSuccessListener(FcmExecutors.newTopicsSyncTriggerExecutor(), (OnSuccessListener<? super TopicsSubscriber>) new FirebaseMessaging$$Lambda$0(this));
    }

    public boolean isAutoInitEnabled() {
        return this.iid.isFcmAutoInitEnabled();
    }

    public void setAutoInitEnabled(boolean z) {
        this.iid.setFcmAutoInitEnabled(z);
    }

    public boolean deliveryMetricsExportToBigQueryEnabled() {
        return MessagingAnalytics.deliveryMetricsExportToBigQueryEnabled();
    }

    public void setDeliveryMetricsExportToBigQuery(boolean z) {
        MessagingAnalytics.setDeliveryMetricsExportToBigQuery(z);
    }

    public Task<String> getToken() {
        return this.iid.getInstanceId().continueWith(FirebaseMessaging$$Lambda$1.$instance);
    }

    public Task<Void> deleteToken() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        FcmExecutors.newNetworkIOExecutor().execute(new FirebaseMessaging$$Lambda$2(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public Task<Void> subscribeToTopic(String str) {
        return this.topicsSubscriberTask.onSuccessTask(new FirebaseMessaging$$Lambda$3(str));
    }

    public Task<Void> unsubscribeFromTopic(String str) {
        return this.topicsSubscriberTask.onSuccessTask(new FirebaseMessaging$$Lambda$4(str));
    }

    public void send(RemoteMessage remoteMessage) {
        if (!TextUtils.isEmpty(remoteMessage.getTo())) {
            Intent intent = new Intent("com.google.android.gcm.intent.SEND");
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            intent.putExtra(AdParameterKeys.SECTION_ID, PendingIntent.getBroadcast(this.context, 0, intent2, 0));
            intent.setPackage("com.google.android.gms");
            remoteMessage.populateSendMessageIntent(intent);
            this.context.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
            return;
        }
        throw new IllegalArgumentException("Missing 'to'");
    }

    public static TransportFactory getTransportFactory() {
        return transportFactory;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$deleteToken$2$FirebaseMessaging(TaskCompletionSource taskCompletionSource) {
        try {
            this.iid.deleteToken(Metadata.getDefaultSenderId(this.firebaseApp), INSTANCE_ID_SCOPE);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$new$0$FirebaseMessaging(TopicsSubscriber topicsSubscriber) {
        if (isAutoInitEnabled()) {
            topicsSubscriber.startTopicsSyncIfNecessary();
        }
    }
}
