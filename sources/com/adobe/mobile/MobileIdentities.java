package com.adobe.mobile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MobileIdentities {
    private static final String JSON_KEY_ANALYTICS_RSIDS = "rsids";
    private static final String JSON_KEY_COMPANYCONTEXTS = "companyContexts";
    private static final String JSON_KEY_NAMESPACE = "namespace";
    private static final String JSON_KEY_TYPE = "type";
    private static final String JSON_KEY_USERIDS = "userIDs";
    private static final String JSON_KEY_USERS = "users";
    private static final String JSON_KEY_VALUE = "value";
    private static final String JSON_VALUE_NAMESPACE_ANALYTICS_AID = "AVID";
    private static final String JSON_VALUE_NAMESPACE_AUDIENCE_UUID = "0";
    private static final String JSON_VALUE_NAMESPACE_COMPANYCONTEXTS = "imsOrgID";
    private static final String JSON_VALUE_NAMESPACE_MCID = "4";
    private static final String JSON_VALUE_NAMESPACE_TARGET_THIRDPARTYID = "3rdpartyid";
    private static final String JSON_VALUE_NAMESPACE_TARGET_TNTID = "tntid";
    private static final String JSON_VALUE_NAMESPACE_USERIDENTIFIER = "vid";
    private static final String JSON_VALUE_TYPE_ANALYTICS = "analytics";
    private static final String JSON_VALUE_TYPE_INTEGRATIONCODE = "integrationCode";
    private static final String JSON_VALUE_TYPE_NAMESPACEID = "namespaceId";
    private static final String JSON_VALUE_TYPE_TARGET = "target";

    private MobileIdentities() {
    }

    /* access modifiers changed from: private */
    public static Map<String, Object> createUserIdMap(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put(JSON_KEY_NAMESPACE, str);
        hashMap.put("value", str2);
        hashMap.put("type", str3);
        return hashMap;
    }

    static String getAllIdentifiers() {
        HashMap hashMap = new HashMap();
        List<Object> companyContexts = getCompanyContexts();
        if (companyContexts != null && !companyContexts.isEmpty()) {
            hashMap.put(JSON_KEY_COMPANYCONTEXTS, companyContexts);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(getOsIdentifiers());
        arrayList.addAll(getAnalyticsIdentifiers());
        arrayList.addAll(getTargetIdentifiers());
        arrayList.addAll(getAudienceIdentifiers());
        arrayList.addAll(getVisitorIdentifiers());
        if (!arrayList.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            HashMap hashMap2 = new HashMap();
            hashMap2.put(JSON_KEY_USERIDS, arrayList);
            arrayList2.add(hashMap2);
            hashMap.put(JSON_KEY_USERS, arrayList2);
        }
        return StaticMethods.mapToJson(hashMap).toString();
    }

    private static List<Object> getAudienceIdentifiers() {
        ArrayList arrayList = new ArrayList();
        String dpuuid = AudienceManager.getDpuuid();
        if (dpuuid != null && !dpuuid.isEmpty()) {
            arrayList.add(createUserIdMap(AudienceManager.getDpid(), dpuuid, JSON_VALUE_TYPE_NAMESPACEID));
        }
        String uuid = AudienceManager.getUuid();
        if (uuid != null && !uuid.isEmpty()) {
            arrayList.add(createUserIdMap("0", uuid, JSON_VALUE_TYPE_NAMESPACEID));
        }
        return arrayList;
    }

    private static List<Object> getTargetIdentifiers() {
        ArrayList arrayList = new ArrayList();
        String pcID = Target.getPcID();
        if (pcID != null && !pcID.isEmpty()) {
            arrayList.add(createUserIdMap(JSON_VALUE_NAMESPACE_TARGET_TNTID, pcID, "target"));
        }
        String thirdPartyID = Target.getThirdPartyID();
        if (thirdPartyID != null && !thirdPartyID.isEmpty()) {
            arrayList.add(createUserIdMap(JSON_VALUE_NAMESPACE_TARGET_THIRDPARTYID, thirdPartyID, "target"));
        }
        return arrayList;
    }

    private static List<Object> getAnalyticsIdentifiers() {
        ArrayList arrayList = new ArrayList();
        String userIdentifier = Config.getUserIdentifier();
        if (userIdentifier != null && !userIdentifier.isEmpty()) {
            Map<String, Object> createUserIdMap = createUserIdMap(JSON_VALUE_NAMESPACE_USERIDENTIFIER, userIdentifier, JSON_VALUE_TYPE_ANALYTICS);
            String reportSuiteIds = MobileConfig.getInstance().getReportSuiteIds();
            if (reportSuiteIds != null && !reportSuiteIds.isEmpty()) {
                createUserIdMap.put(JSON_KEY_ANALYTICS_RSIDS, Arrays.asList(reportSuiteIds.split(",")));
            }
            arrayList.add(createUserIdMap);
        }
        String trackingIdentifier = Analytics.getTrackingIdentifier();
        if (trackingIdentifier != null && !trackingIdentifier.isEmpty()) {
            arrayList.add(createUserIdMap(JSON_VALUE_NAMESPACE_ANALYTICS_AID, Analytics.getTrackingIdentifier(), JSON_VALUE_TYPE_INTEGRATIONCODE));
        }
        return arrayList;
    }

    private static List<Object> getOsIdentifiers() {
        FutureTask futureTask = new FutureTask(new Callable<List<Object>>() {
            public List<Object> call() throws Exception {
                ArrayList arrayList = new ArrayList();
                String pushIdentifier = StaticMethods.getPushIdentifier();
                if (pushIdentifier != null && !pushIdentifier.isEmpty()) {
                    arrayList.add(MobileIdentities.createUserIdMap("20919", pushIdentifier, MobileIdentities.JSON_VALUE_TYPE_INTEGRATIONCODE));
                }
                String advertisingIdentifier = StaticMethods.getAdvertisingIdentifier();
                if (advertisingIdentifier != null && !advertisingIdentifier.isEmpty()) {
                    arrayList.add(MobileIdentities.createUserIdMap("DSID_20914", advertisingIdentifier, MobileIdentities.JSON_VALUE_TYPE_INTEGRATIONCODE));
                }
                return arrayList;
            }
        });
        StaticMethods.getAnalyticsExecutor().execute(futureTask);
        try {
            return (List) futureTask.get();
        } catch (Exception e) {
            StaticMethods.logDebugFormat("Identities - failed to get OS identifiers json (%s)", e.getMessage());
            return new ArrayList();
        }
    }

    private static List<Object> getVisitorIdentifiers() {
        ArrayList arrayList = new ArrayList();
        String marketingCloudId = Visitor.getMarketingCloudId();
        if (marketingCloudId != null && !marketingCloudId.isEmpty()) {
            arrayList.add(createUserIdMap(JSON_VALUE_NAMESPACE_MCID, marketingCloudId, JSON_VALUE_TYPE_NAMESPACEID));
        }
        List<VisitorID> identifiers = Visitor.getIdentifiers();
        if (identifiers != null && !identifiers.isEmpty()) {
            for (VisitorID next : identifiers) {
                if (next.id != null && !next.id.isEmpty()) {
                    arrayList.add(createUserIdMap(next.idType, next.id, JSON_VALUE_TYPE_INTEGRATIONCODE));
                }
            }
        }
        return arrayList;
    }

    private static List<Object> getCompanyContexts() {
        String marketingCloudOrganizationId = MobileConfig.getInstance().getMarketingCloudOrganizationId();
        if (marketingCloudOrganizationId == null || marketingCloudOrganizationId.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(JSON_KEY_NAMESPACE, JSON_VALUE_NAMESPACE_COMPANYCONTEXTS);
        hashMap.put("value", marketingCloudOrganizationId);
        ArrayList arrayList = new ArrayList();
        arrayList.add(hashMap);
        return arrayList;
    }

    static void resetAllIdentifiers() {
        StaticMethods.logDebugFormat("Config - Privacy status set to opt out, purging all Adobe SDK identities from device.", new Object[0]);
        AudienceManagerWorker.handleOptOut();
        StaticMethods.getAudienceExecutor().execute(new Runnable() {
            public void run() {
                MobileIdentities.resetAnalyticsIdentifiers();
                AudienceManagerWorker.purgeDpidAndDpuuid();
                AudienceManager.reset();
                MobileIdentities.resetOsIdentifiers();
                Target.clearCookies();
                VisitorIDService.sharedInstance().purgeIdentities();
            }
        });
    }

    /* access modifiers changed from: private */
    public static void resetAnalyticsIdentifiers() {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                StaticMethods.purgeAID();
                StaticMethods.purgeVisitorID();
            }
        });
    }

    /* access modifiers changed from: private */
    public static void resetOsIdentifiers() {
        StaticMethods.getAnalyticsExecutor().execute(new Runnable() {
            public void run() {
                StaticMethods.purgeAdvertisingIdentifier();
                StaticMethods.purgePushIdentifier();
            }
        });
    }
}
