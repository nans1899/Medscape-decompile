package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.definition.DefinitionSection;
import com.wbmd.qxcalculator.model.db.DBDefinitionDao;
import com.wbmd.qxcalculator.model.db.DBDefinitionSectionDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBDefinitionSection {
    public static final String TAG = DBDefinitionSection.class.getSimpleName();
    private String body;
    private Long definitionId;
    private Long id;
    private String identifier;
    private Integer position;
    private String title;

    public DBDefinitionSection() {
    }

    public DBDefinitionSection(Long l) {
        this.id = l;
    }

    public DBDefinitionSection(Long l, String str, String str2, String str3, Integer num, Long l2) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.body = str3;
        this.position = num;
        this.definitionId = l2;
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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer num) {
        this.position = num;
    }

    public Long getDefinitionId() {
        return this.definitionId;
    }

    public void setDefinitionId(Long l) {
        this.definitionId = l;
    }

    public static void deleteUnusedDefinitionSections(DaoSession daoSession) {
        List list = daoSession.getDBDefinitionSectionDao().queryBuilder().where(DBDefinitionSectionDao.Properties.DefinitionId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBDefinitionSection: " + list.size());
        deleteDefinitionSections(daoSession, list);
    }

    public static void deleteDefinitionSections(DaoSession daoSession, List<DBDefinitionSection> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBDefinitionSection next : list) {
                if (next.getDefinitionId() != null) {
                    arrayList.add(next.getDefinitionId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBDefinition> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBDefinitionDao(), DBDefinitionDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBDefinition resetDefinitionSections : allWithPropertyInData) {
                        resetDefinitionSections.resetDefinitionSections();
                    }
                }
            }
            daoSession.getDBDefinitionSectionDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBDefinitionSection insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.definition.DefinitionSection r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBDefinitionSection> r0 = com.wbmd.qxcalculator.model.db.DBDefinitionSection.class
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
            com.wbmd.qxcalculator.model.db.DBDefinitionSection r1 = (com.wbmd.qxcalculator.model.db.DBDefinitionSection) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBDefinitionSection.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.definition.DefinitionSection):com.wbmd.qxcalculator.model.db.DBDefinitionSection");
    }

    public static synchronized List<DBDefinitionSection> insertAndRetrieveDbEntities(DaoSession daoSession, List<DefinitionSection> list) {
        synchronized (DBDefinitionSection.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (DefinitionSection definitionSection : list) {
                arrayList2.add(definitionSection.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBDefinitionSectionDao(), DBDefinitionSectionDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (DefinitionSection next : list) {
                DBDefinitionSection dBDefinitionSection = linkedHashMap.containsKey(next) ? (DBDefinitionSection) linkedHashMap.get(next) : null;
                if (dBDefinitionSection == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBDefinitionSection dBDefinitionSection2 = (DBDefinitionSection) it.next();
                        if (dBDefinitionSection2.getIdentifier().equals(next.identifier)) {
                            dBDefinitionSection = dBDefinitionSection2;
                            break;
                        }
                    }
                }
                if (dBDefinitionSection == null) {
                    dBDefinitionSection = new DBDefinitionSection();
                    arrayList3.add(dBDefinitionSection);
                }
                dBDefinitionSection.setIdentifier(next.identifier);
                dBDefinitionSection.setPosition(next.position);
                dBDefinitionSection.setTitle(next.title);
                dBDefinitionSection.setBody(next.body);
                linkedHashMap.put(next, dBDefinitionSection);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBDefinitionSectionDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBDefinitionSectionDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
