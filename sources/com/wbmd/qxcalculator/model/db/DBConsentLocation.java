package com.wbmd.qxcalculator.model.db;

import com.wbmd.qxcalculator.model.db.DBConsentLocationDao;
import com.wbmd.qxcalculator.model.db.DBUserDao;
import com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBConsentLocation {
    private Boolean defaultOptIn;
    private Boolean explicitRequired;
    private Long id;
    private String identifier;
    private Boolean isEditable;
    private String label;
    private Boolean optInRequired;
    private String shortName;
    private String subTitle;
    private String title;
    private Long userIdConsentLocationsRequired;
    private Long userIdConsentLocationsToRequest;

    public DBConsentLocation() {
    }

    public DBConsentLocation(Long l) {
        this.id = l;
    }

    public DBConsentLocation(Long l, String str, String str2, String str3, String str4, String str5, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Long l2, Long l3) {
        this.id = l;
        this.identifier = str;
        this.label = str2;
        this.title = str3;
        this.subTitle = str4;
        this.shortName = str5;
        this.explicitRequired = bool;
        this.optInRequired = bool2;
        this.isEditable = bool3;
        this.defaultOptIn = bool4;
        this.userIdConsentLocationsRequired = l2;
        this.userIdConsentLocationsToRequest = l3;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String str) {
        this.shortName = str;
    }

    public Boolean getExplicitRequired() {
        return this.explicitRequired;
    }

    public void setExplicitRequired(Boolean bool) {
        this.explicitRequired = bool;
    }

    public Boolean getOptInRequired() {
        return this.optInRequired;
    }

    public void setOptInRequired(Boolean bool) {
        this.optInRequired = bool;
    }

    public Boolean getIsEditable() {
        return this.isEditable;
    }

    public void setIsEditable(Boolean bool) {
        this.isEditable = bool;
    }

    public Boolean getDefaultOptIn() {
        return this.defaultOptIn;
    }

    public void setDefaultOptIn(Boolean bool) {
        this.defaultOptIn = bool;
    }

    public Long getUserIdConsentLocationsRequired() {
        return this.userIdConsentLocationsRequired;
    }

    public void setUserIdConsentLocationsRequired(Long l) {
        this.userIdConsentLocationsRequired = l;
    }

    public Long getUserIdConsentLocationsToRequest() {
        return this.userIdConsentLocationsToRequest;
    }

    public void setUserIdConsentLocationsToRequest(Long l) {
        this.userIdConsentLocationsToRequest = l;
    }

    public static void deleteConsentLocations(DaoSession daoSession, List<DBConsentLocation> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList(list.size());
            for (DBConsentLocation next : list) {
                arrayList3.add(next.getId());
                if (next.getUserIdConsentLocationsRequired() != null) {
                    arrayList.add(next.getUserIdConsentLocationsRequired());
                }
                if (next.getUserIdConsentLocationsToRequest() != null) {
                    arrayList2.add(next.getUserIdConsentLocationsToRequest());
                }
            }
            if (!arrayList2.isEmpty()) {
                List<DBUser> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBUserDao(), DBUserDao.Properties.Id, arrayList2);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBUser resetConsentLocationsToRequest : allWithPropertyInData) {
                        resetConsentLocationsToRequest.resetConsentLocationsToRequest();
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBUser> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBUserDao(), DBUserDao.Properties.Id, arrayList);
                if (!allWithPropertyInData2.isEmpty()) {
                    for (DBUser resetConsentLocationsRequired : allWithPropertyInData2) {
                        resetConsentLocationsRequired.resetConsentLocationsRequired();
                    }
                }
            }
            daoSession.getDBConsentLocationDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBConsentLocation insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBConsentLocation> r0 = com.wbmd.qxcalculator.model.db.DBConsentLocation.class
            monitor-enter(r0)
            r1 = 0
            if (r3 == 0) goto L_0x0029
            if (r4 != 0) goto L_0x0009
            goto L_0x0029
        L_0x0009:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0026 }
            r2.<init>()     // Catch:{ all -> 0x0026 }
            r2.add(r4)     // Catch:{ all -> 0x0026 }
            java.util.List r3 = insertAndRetrieveDbEntities(r3, r2)     // Catch:{ all -> 0x0026 }
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x0026 }
            if (r4 == 0) goto L_0x001c
            goto L_0x0024
        L_0x001c:
            r4 = 0
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x0026 }
            r1 = r3
            com.wbmd.qxcalculator.model.db.DBConsentLocation r1 = (com.wbmd.qxcalculator.model.db.DBConsentLocation) r1     // Catch:{ all -> 0x0026 }
        L_0x0024:
            monitor-exit(r0)
            return r1
        L_0x0026:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        L_0x0029:
            monitor-exit(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBConsentLocation.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.parsedObjects.ConsentLocation):com.wbmd.qxcalculator.model.db.DBConsentLocation");
    }

    public static synchronized List<DBConsentLocation> insertAndRetrieveDbEntities(DaoSession daoSession, List<ConsentLocation> list) {
        synchronized (DBConsentLocation.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (ConsentLocation consentLocation : list) {
                arrayList2.add(consentLocation.consentId);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBConsentLocationDao(), DBConsentLocationDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (ConsentLocation next : list) {
                DBConsentLocation dBConsentLocation = linkedHashMap.containsKey(next) ? (DBConsentLocation) linkedHashMap.get(next) : null;
                if (dBConsentLocation == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBConsentLocation dBConsentLocation2 = (DBConsentLocation) it.next();
                        if (dBConsentLocation2.getIdentifier().equals(next.consentId)) {
                            dBConsentLocation = dBConsentLocation2;
                            break;
                        }
                    }
                }
                if (dBConsentLocation == null) {
                    dBConsentLocation = new DBConsentLocation();
                    arrayList3.add(dBConsentLocation);
                }
                dBConsentLocation.setIdentifier(next.consentId);
                dBConsentLocation.setLabel(next.label);
                dBConsentLocation.setTitle(next.title);
                dBConsentLocation.setSubTitle(next.subTitle);
                dBConsentLocation.setShortName(next.shortName);
                dBConsentLocation.setExplicitRequired(next.explicitRequired);
                dBConsentLocation.setOptInRequired(next.optInRequired);
                dBConsentLocation.setIsEditable(next.isEditable);
                dBConsentLocation.setDefaultOptIn(next.defaultOptIn);
                linkedHashMap.put(next, dBConsentLocation);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBConsentLocationDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBConsentLocationDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
