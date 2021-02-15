package com.qxmd.eventssdkandroid.model.db;

import com.facebook.AccessToken;
import com.facebook.internal.NativeProtocol;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;

public class EventDB {
    private static SimpleDateFormat dateFormatter;
    private String appName;
    private String appVersion;
    private Long campaignAdId;
    private String category;
    private String device;
    private Long duration;
    private String emailHash;
    private Long eventTypeId;
    private Long id;
    private Long institutionId;
    private String label;
    private Long locationId;
    private Long objectId;
    private Long professionId;
    private String source;
    private Long specialtyId;
    private Date timeStamp;
    private String url;
    private Long userId;

    public EventDB() {
    }

    public EventDB(Long l) {
        this.id = l;
    }

    public EventDB(Long l, String str, String str2, String str3, Long l2, String str4, Long l3, Long l4, Long l5, Long l6, Long l7, Long l8, Long l9, Date date, String str5, String str6, String str7, Long l10, String str8) {
        this.id = l;
        this.appName = str;
        this.appVersion = str2;
        this.device = str3;
        this.duration = l2;
        this.emailHash = str4;
        this.eventTypeId = l3;
        this.institutionId = l4;
        this.locationId = l5;
        this.objectId = l6;
        this.professionId = l7;
        this.specialtyId = l8;
        this.userId = l9;
        this.timeStamp = date;
        this.category = str5;
        this.label = str6;
        this.url = str7;
        this.campaignAdId = l10;
        this.source = str8;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getDevice() {
        return this.device;
    }

    public void setDevice(String str) {
        this.device = str;
    }

    public Long getDuration() {
        return this.duration;
    }

    public void setDuration(Long l) {
        this.duration = l;
    }

    public String getEmailHash() {
        return this.emailHash;
    }

    public void setEmailHash(String str) {
        this.emailHash = str;
    }

    public Long getEventTypeId() {
        return this.eventTypeId;
    }

    public void setEventTypeId(Long l) {
        this.eventTypeId = l;
    }

    public Long getInstitutionId() {
        return this.institutionId;
    }

    public void setInstitutionId(Long l) {
        this.institutionId = l;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Long l) {
        this.locationId = l;
    }

    public Long getObjectId() {
        return this.objectId;
    }

    public void setObjectId(Long l) {
        this.objectId = l;
    }

    public Long getProfessionId() {
        return this.professionId;
    }

    public void setProfessionId(Long l) {
        this.professionId = l;
    }

    public Long getSpecialtyId() {
        return this.specialtyId;
    }

    public void setSpecialtyId(Long l) {
        this.specialtyId = l;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long l) {
        this.userId = l;
    }

    public Date getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(Date date) {
        this.timeStamp = date;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Long getCampaignAdId() {
        return this.campaignAdId;
    }

    public void setCampaignAdId(Long l) {
        this.campaignAdId = l;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public static String convertEventsToJsonString(List<EventDB> list) {
        ArrayList arrayList = new ArrayList();
        for (EventDB next : list) {
            HashMap hashMap = new HashMap();
            if (next.getAppName() != null) {
                hashMap.put(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, next.getAppName());
            }
            if (next.getAppVersion() != null) {
                hashMap.put("app_version", next.getAppVersion());
            }
            if (next.getDevice() != null) {
                hashMap.put("device", next.getDevice());
            }
            if (next.getDuration() != null) {
                hashMap.put("duration", next.getDuration());
            }
            if (next.getEventTypeId() != null) {
                hashMap.put("event_type_id", next.getEventTypeId());
            }
            if (next.getInstitutionId() != null) {
                hashMap.put("institution_id", next.getInstitutionId());
            }
            if (next.getLocationId() != null) {
                hashMap.put(FirebaseAnalytics.Param.LOCATION_ID, next.getLocationId());
            }
            if (next.getObjectId() != null) {
                hashMap.put("object_id", next.getObjectId());
            }
            if (next.getProfessionId() != null) {
                hashMap.put("profession_id", next.getProfessionId());
            }
            if (next.getSpecialtyId() != null) {
                hashMap.put("specialty_id", next.getSpecialtyId());
            }
            if (next.getUserId() != null) {
                hashMap.put(AccessToken.USER_ID_KEY, next.getUserId());
            }
            if (next.getEmailHash() != null) {
                hashMap.put("email_hash", next.getEmailHash());
            }
            if (next.getCampaignAdId() != null) {
                hashMap.put("campaign_ad_id", next.getCampaignAdId());
            }
            if (next.getCategory() != null) {
                hashMap.put("category", next.getCategory());
            }
            if (next.getLabel() != null) {
                hashMap.put(Constants.ScionAnalytics.PARAM_LABEL, next.getLabel());
            }
            if (next.getUrl() != null) {
                hashMap.put("url", next.getUrl());
            }
            if (next.getSource() != null) {
                hashMap.put("source", next.getSource());
            }
            if (next.getTimeStamp() != null) {
                if (dateFormatter == null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
                    dateFormatter = simpleDateFormat;
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                }
                hashMap.put("client_timestamp", dateFormatter.format(next.getTimeStamp()));
            }
            arrayList.add(hashMap);
        }
        return new JSONArray(arrayList).toString();
    }
}
