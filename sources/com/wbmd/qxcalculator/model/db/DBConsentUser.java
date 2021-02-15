package com.wbmd.qxcalculator.model.db;

import com.wbmd.qxcalculator.model.db.DBConsentUserDao;
import com.wbmd.qxcalculator.model.db.DBUserDao;
import com.wbmd.qxcalculator.model.parsedObjects.ConsentUser;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBConsentUser {
    private Long id;
    private String identifier;
    private Boolean isExplicit;
    private Boolean isOptIn;
    private String label;
    private Long userId;

    public DBConsentUser() {
    }

    public DBConsentUser(Long l) {
        this.id = l;
    }

    public DBConsentUser(Long l, String str, String str2, Boolean bool, Boolean bool2, Long l2) {
        this.id = l;
        this.identifier = str;
        this.label = str2;
        this.isOptIn = bool;
        this.isExplicit = bool2;
        this.userId = l2;
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

    public Boolean getIsOptIn() {
        return this.isOptIn;
    }

    public void setIsOptIn(Boolean bool) {
        this.isOptIn = bool;
    }

    public Boolean getIsExplicit() {
        return this.isExplicit;
    }

    public void setIsExplicit(Boolean bool) {
        this.isExplicit = bool;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long l) {
        this.userId = l;
    }

    public static void deleteConsents(DaoSession daoSession, List<DBConsentUser> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(list.size());
            for (DBConsentUser next : list) {
                arrayList2.add(next.getId());
                if (next.getUserId() != null) {
                    arrayList.add(next.getUserId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBUser> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBUserDao(), DBUserDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBUser resetCurrentConsents : allWithPropertyInData) {
                        resetCurrentConsents.resetCurrentConsents();
                    }
                }
            }
            daoSession.getDBConsentUserDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBConsentUser insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.parsedObjects.ConsentUser r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBConsentUser> r0 = com.wbmd.qxcalculator.model.db.DBConsentUser.class
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
            com.wbmd.qxcalculator.model.db.DBConsentUser r1 = (com.wbmd.qxcalculator.model.db.DBConsentUser) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBConsentUser.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.parsedObjects.ConsentUser):com.wbmd.qxcalculator.model.db.DBConsentUser");
    }

    public static synchronized List<DBConsentUser> insertAndRetrieveDbEntities(DaoSession daoSession, List<ConsentUser> list) {
        synchronized (DBConsentUser.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (ConsentUser consentUser : list) {
                arrayList2.add(consentUser.consentId);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBConsentUserDao(), DBConsentUserDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (ConsentUser next : list) {
                DBConsentUser dBConsentUser = linkedHashMap.containsKey(next) ? (DBConsentUser) linkedHashMap.get(next) : null;
                if (dBConsentUser == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBConsentUser dBConsentUser2 = (DBConsentUser) it.next();
                        if (dBConsentUser2.getIdentifier().equals(next.consentId)) {
                            dBConsentUser = dBConsentUser2;
                            break;
                        }
                    }
                }
                if (dBConsentUser == null) {
                    dBConsentUser = new DBConsentUser();
                    arrayList3.add(dBConsentUser);
                }
                dBConsentUser.setIdentifier(next.consentId);
                dBConsentUser.setLabel(next.label);
                dBConsentUser.setIsOptIn(next.isOptIn);
                dBConsentUser.setIsExplicit(next.isExplicit);
                linkedHashMap.put(next, dBConsentUser);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBConsentUserDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBConsentUserDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
