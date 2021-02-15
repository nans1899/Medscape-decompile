package com.qxmd.eventssdkandroid.managers;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.google.firebase.appindexing.Indexable;
import com.qxmd.eventssdkandroid.api.APIRequest;
import com.qxmd.eventssdkandroid.api.APIResponse;
import com.qxmd.eventssdkandroid.api.APITask;
import com.qxmd.eventssdkandroid.model.QxError;
import com.qxmd.eventssdkandroid.model.db.EventDB;
import com.qxmd.eventssdkandroid.util.Log;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QXEEventsManager {
    private static QXEEventsManager ourInstance;
    private String appName;
    private Context context;
    private HashMap<String, Set<QXEEventsManagerListener>> dataManagerListenerHashMap;
    private boolean debugMode;
    private String deviceName;
    private String emailHash;
    private Long locationId;
    Runnable mDispatchToServerRunnable = new Runnable() {
        public void run() {
            try {
                Log.d((Object) this, "send pending events to server");
                QXEEventsManager.this.sendPendingEventsToServer();
            } finally {
                QXEEventsManager.this.mHandler.postDelayed(QXEEventsManager.this.mDispatchToServerRunnable, 20000);
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler mHandler;
    private final int mInterval = Indexable.MAX_STRING_LENGTH;
    /* access modifiers changed from: private */
    public ArrayList<Long> objectIdsSending = new ArrayList<>();
    private Long professionId;
    public QXEEventsManagerListener requestListener;
    private Date sessionStartDate;
    private String source;
    private Long specialtyId;
    private Long userId;

    public interface QXEEventsManagerListener {
        boolean dataManagerMethodFinished(String str, boolean z, List<QxError> list, Bundle bundle);

        void dataManagerMethodQueued(String str);

        void dataManagerMethodStarted(String str);

        void eventQueuedWithWarning(long j, Exception exc);

        void eventSendCompleted(EventDB eventDB, boolean z, List<QxError> list);

        void requestDidComplete(APIResponse aPIResponse, boolean z, List<QxError> list);

        void willSendEvent(EventDB eventDB);
    }

    public static synchronized void initializeApp(Context context2) {
        synchronized (QXEEventsManager.class) {
            if (ourInstance == null) {
                ourInstance = new QXEEventsManager(context2);
            }
        }
    }

    public static QXEEventsManager getInstance() {
        return ourInstance;
    }

    private QXEEventsManager(Context context2) {
        this.context = context2;
        InternetConnectivityManager.initializeInstance(context2);
        DatabaseManager.getInstance().loadDatabase(context2);
        Handler handler = new Handler();
        this.mHandler = handler;
        handler.postDelayed(this.mDispatchToServerRunnable, 20000);
    }

    public void registerEventsManagerListener(QXEEventsManagerListener qXEEventsManagerListener, String str) {
        Set set = this.dataManagerListenerHashMap.get(str);
        if (set == null) {
            set = new HashSet();
            this.dataManagerListenerHashMap.put(str, set);
        }
        set.add(qXEEventsManagerListener);
    }

    public void deRegisterEventsManagerListener(QXEEventsManagerListener qXEEventsManagerListener, String str) {
        Set set = this.dataManagerListenerHashMap.get(str);
        if (set != null) {
            set.remove(qXEEventsManagerListener);
        }
    }

    public Set<QXEEventsManagerListener> getListenersForTaskId(String str) {
        if (str == null) {
            return null;
        }
        return this.dataManagerListenerHashMap.get(str);
    }

    public void setDebugMode(boolean z) {
        this.debugMode = z;
    }

    public boolean getDebugMode() {
        return this.debugMode;
    }

    public void setProfessionId(Long l) {
        this.professionId = l;
    }

    public Long getProfessionId() {
        return this.professionId;
    }

    public void setSpecialtyId(Long l) {
        this.specialtyId = l;
    }

    public Long getSpecialtyId() {
        return this.specialtyId;
    }

    public void setLocationId(Long l) {
        this.locationId = l;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public void setUserId(Long l) {
        this.userId = l;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getSource() {
        return this.source;
    }

    public void setUserEmailAddress(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
                StringBuffer stringBuffer = new StringBuffer();
                for (byte b : digest) {
                    stringBuffer.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                }
                this.emailHash = stringBuffer.toString();
            } catch (NoSuchAlgorithmException unused) {
                this.emailHash = null;
            } catch (UnsupportedEncodingException unused2) {
                this.emailHash = null;
            }
        }
    }

    public String getEmailHash() {
        return this.emailHash;
    }

    public void setAppRead() {
        this.appName = "read_android";
    }

    public void setAppCalculate() {
        this.appName = "calculate_android";
    }

    public void startCalculateSession(String str, Long l, Long l2, Long l3, String str2) {
        this.appName = "calculate_android";
        Long l4 = null;
        if (str != null && !str.isEmpty()) {
            try {
                l4 = Long.valueOf(Long.parseLong(str));
            } catch (NumberFormatException unused) {
            }
        }
        startSession(110L, l4, l, l2, l3, str2);
    }

    public void startReadSession(Long l, Long l2, Long l3, Long l4, String str) {
        this.appName = "read_android";
        startSession(210L, l, l2, l3, l4, str);
    }

    public void startSession(Long l, Long l2, Long l3, Long l4, Long l5, String str) {
        setUserId(l2);
        setProfessionId(l3);
        setSpecialtyId(l4);
        setLocationId(l5);
        setUserEmailAddress(str);
        this.sessionStartDate = new Date();
        trackEvent(l, (String) null, (String) null, (String) null, (Long) null, (Long) null, (Long) null);
    }

    public void stopCalculateSession() {
        stopSession(111L);
    }

    public void stopReadSession() {
        stopSession(211L);
    }

    public void stopSession(Long l) {
        trackEvent(l, (String) null, (String) null, (String) null, (Long) null, (Long) null, this.sessionStartDate != null ? Long.valueOf(new Date().getTime() - this.sessionStartDate.getTime()) : null);
    }

    public void updateUserDetails(Long l, Long l2, Long l3, Long l4, String str) {
        setUserId(l);
        setProfessionId(l2);
        setSpecialtyId(l3);
        setLocationId(l4);
        setUserEmailAddress(str);
    }

    public void trackEvent(Long l, String str, String str2, String str3, Long l2, Long l3, Long l4) {
        EventDB eventDB = new EventDB();
        String str4 = this.appName;
        if (str4 != null) {
            eventDB.setAppName(str4);
        } else {
            QXEEventsManagerListener qXEEventsManagerListener = this.requestListener;
            if (qXEEventsManagerListener != null) {
                long longValue = l.longValue();
                qXEEventsManagerListener.eventQueuedWithWarning(longValue, new Exception("Event queued with no app name: " + l));
            }
        }
        try {
            eventDB.setAppVersion(this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName);
            eventDB.setDevice(Build.MODEL);
        } catch (Exception unused) {
        }
        eventDB.setTimeStamp(new Date());
        if (!this.debugMode || l.longValue() <= 0) {
            eventDB.setEventTypeId(l);
        } else {
            eventDB.setEventTypeId(Long.valueOf(-l.longValue()));
        }
        eventDB.setObjectId(l2);
        eventDB.setDuration(l4);
        eventDB.setCategory(str);
        eventDB.setLabel(str2);
        eventDB.setUrl(str3);
        eventDB.setCampaignAdId(l3);
        eventDB.setSource(this.source);
        Long l5 = this.professionId;
        if (l5 != null) {
            eventDB.setProfessionId(l5);
        } else {
            QXEEventsManagerListener qXEEventsManagerListener2 = this.requestListener;
            if (qXEEventsManagerListener2 != null) {
                long longValue2 = l.longValue();
                qXEEventsManagerListener2.eventQueuedWithWarning(longValue2, new Exception("Event queued with no profession ID: " + l));
            }
        }
        Long l6 = this.specialtyId;
        if (l6 != null) {
            eventDB.setSpecialtyId(l6);
        } else {
            QXEEventsManagerListener qXEEventsManagerListener3 = this.requestListener;
            if (qXEEventsManagerListener3 != null) {
                long longValue3 = l.longValue();
                qXEEventsManagerListener3.eventQueuedWithWarning(longValue3, new Exception("Event queued with no specialty ID: " + l));
            }
        }
        Long l7 = this.locationId;
        if (l7 != null) {
            eventDB.setLocationId(l7);
        } else {
            QXEEventsManagerListener qXEEventsManagerListener4 = this.requestListener;
            if (qXEEventsManagerListener4 != null) {
                long longValue4 = l.longValue();
                qXEEventsManagerListener4.eventQueuedWithWarning(longValue4, new Exception("Event queued with no location ID: " + l));
            }
        }
        Long l8 = this.userId;
        if (l8 != null) {
            eventDB.setUserId(l8);
        } else {
            QXEEventsManagerListener qXEEventsManagerListener5 = this.requestListener;
            if (qXEEventsManagerListener5 != null) {
                long longValue5 = l.longValue();
                qXEEventsManagerListener5.eventQueuedWithWarning(longValue5, new Exception("Event queued with no user ID: " + l));
            }
        }
        String str5 = this.emailHash;
        if (str5 != null) {
            eventDB.setEmailHash(str5);
        } else {
            QXEEventsManagerListener qXEEventsManagerListener6 = this.requestListener;
            if (qXEEventsManagerListener6 != null) {
                long longValue6 = l.longValue();
                qXEEventsManagerListener6.eventQueuedWithWarning(longValue6, new Exception("Event queued with no email hash: " + l));
            }
        }
        DatabaseManager.getInstance().getDaoSession().getEventDBDao().insert(eventDB);
    }

    /* access modifiers changed from: private */
    public void sendPendingEventsToServer() {
        final String str;
        if (InternetConnectivityManager.getInstance().isConnectedToInternet()) {
            List<EventDB> loadAll = DatabaseManager.getInstance().getDaoSession().getEventDBDao().loadAll();
            int min = Math.min(loadAll.size(), 20);
            final ArrayList<EventDB> arrayList = new ArrayList<>(min);
            final ArrayList arrayList2 = new ArrayList(min);
            if (loadAll != null && !loadAll.isEmpty()) {
                int i = 0;
                for (EventDB eventDB : loadAll) {
                    if (!this.objectIdsSending.contains(eventDB.getId())) {
                        arrayList.add(eventDB);
                        arrayList2.add(eventDB.getId());
                        this.objectIdsSending.add(eventDB.getId());
                        i++;
                        if (i >= 20) {
                            break;
                        }
                    }
                }
            }
            if (arrayList.isEmpty()) {
                str = null;
            } else {
                str = EventDB.convertEventsToJsonString(arrayList);
            }
            if (str != null && !str.isEmpty()) {
                if (this.requestListener != null) {
                    for (EventDB willSendEvent : arrayList) {
                        this.requestListener.willSendEvent(willSendEvent);
                    }
                }
                TaskManager.getInstance().addTask(new APITask((APITask.OnPrepareRequestBlock) new APITask.OnPrepareRequestBlock() {
                    public APIRequest prepareRequest() {
                        return APIRequest.sendEventsRequest(str);
                    }
                }, (APITask.OnProcessResponseBlock) new APITask.OnProcessResponseBlock() {
                    public APIResponse processResponse(APIResponse aPIResponse, boolean z, List<QxError> list) {
                        return aPIResponse;
                    }
                }, (APITask.OnCompletionBlock) new APITask.OnCompletionBlock() {
                    public Bundle onCompletion(APIResponse aPIResponse, boolean z, List<QxError> list) {
                        if (QXEEventsManager.this.requestListener != null) {
                            for (EventDB eventSendCompleted : arrayList) {
                                QXEEventsManager.this.requestListener.eventSendCompleted(eventSendCompleted, z, list);
                            }
                            QXEEventsManager.this.requestListener.requestDidComplete(aPIResponse, z, list);
                        }
                        if (z) {
                            DatabaseManager.getInstance().getDaoSession().getEventDBDao().deleteInTx(arrayList);
                        }
                        QXEEventsManager.this.objectIdsSending.removeAll(arrayList2);
                        return null;
                    }
                }, (String) null, this.context));
            }
        }
    }
}
