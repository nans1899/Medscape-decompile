package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.definition.Definition;
import com.wbmd.qxcalculator.model.contentItems.definition.DefinitionSection;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBDefinitionDao;
import com.wbmd.qxcalculator.model.db.DBDefinitionSectionDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBDefinition {
    public static final String TAG = DBDefinition.class.getSimpleName();
    private transient DaoSession daoSession;
    private List<DBDefinitionSection> definitionSections;
    private Long id;
    private String identifier;
    private transient DBDefinitionDao myDao;

    public DBDefinition() {
    }

    public DBDefinition(Long l) {
        this.id = l;
    }

    public DBDefinition(Long l, String str) {
        this.id = l;
        this.identifier = str;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBDefinitionDao() : null;
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

    public List<DBDefinitionSection> getDefinitionSections() {
        if (this.definitionSections == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBDefinitionSection> _queryDBDefinition_DefinitionSections = daoSession2.getDBDefinitionSectionDao()._queryDBDefinition_DefinitionSections(this.id);
                synchronized (this) {
                    if (this.definitionSections == null) {
                        this.definitionSections = _queryDBDefinition_DefinitionSections;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.definitionSections;
    }

    public synchronized void resetDefinitionSections() {
        this.definitionSections = null;
    }

    public void delete() {
        DBDefinitionDao dBDefinitionDao = this.myDao;
        if (dBDefinitionDao != null) {
            dBDefinitionDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBDefinitionDao dBDefinitionDao = this.myDao;
        if (dBDefinitionDao != null) {
            dBDefinitionDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBDefinitionDao dBDefinitionDao = this.myDao;
        if (dBDefinitionDao != null) {
            dBDefinitionDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void deleteUnusedDefinitions(DaoSession daoSession2) {
        List<DBContentItem> loadAll = daoSession2.getDBContentItemDao().loadAll();
        ArrayList arrayList = new ArrayList();
        for (DBContentItem dBContentItem : loadAll) {
            if (dBContentItem.getDefinitionId() != null) {
                arrayList.add(dBContentItem.getDefinitionId());
            }
        }
        List allWithPropertyNotInData = DatabaseHelper.getAllWithPropertyNotInData(daoSession2.getDBDefinitionDao(), DBDefinitionDao.Properties.Id, arrayList);
        String str = TAG;
        Log.d(str, "Purging DBDefinition: " + allWithPropertyNotInData.size());
        deleteDefinitions(daoSession2, allWithPropertyNotInData);
    }

    public static void deleteDefinitions(DaoSession daoSession2, List<DBDefinition> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList(list.size());
            for (DBDefinition next : list) {
                arrayList.add(next.getId());
                next.resetDefinitionSections();
            }
            List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.DefinitionId, arrayList);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBContentItem definitionId : allWithPropertyInData) {
                    definitionId.setDefinitionId((Long) null);
                }
                daoSession2.getDBContentItemDao().updateInTx(allWithPropertyInData);
            }
            List<DBDefinitionSection> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBDefinitionSectionDao(), DBDefinitionSectionDao.Properties.DefinitionId, arrayList);
            if (!allWithPropertyInData2.isEmpty()) {
                for (DBDefinitionSection definitionId2 : allWithPropertyInData2) {
                    definitionId2.setDefinitionId((Long) null);
                }
                DBDefinitionSection.deleteDefinitionSections(daoSession2, allWithPropertyInData2);
            }
            daoSession2.getDBDefinitionDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBDefinition insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.definition.Definition r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBDefinition> r0 = com.wbmd.qxcalculator.model.db.DBDefinition.class
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
            com.wbmd.qxcalculator.model.db.DBDefinition r1 = (com.wbmd.qxcalculator.model.db.DBDefinition) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBDefinition.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.definition.Definition):com.wbmd.qxcalculator.model.db.DBDefinition");
    }

    public static synchronized List<DBDefinition> insertAndRetrieveDbEntities(DaoSession daoSession2, List<Definition> list) {
        synchronized (DBDefinition.class) {
            if (daoSession2 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (Definition next : list) {
                arrayList2.add(next.identifier);
                if (next.definitionSections != null) {
                    arrayList3.addAll(next.definitionSections);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBDefinitionDao(), DBDefinitionDao.Properties.Identifier, arrayList2);
            ArrayList arrayList4 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList5 = new ArrayList();
            if (arrayList3.size() > 0) {
                arrayList5 = DBDefinitionSection.insertAndRetrieveDbEntities(daoSession2, arrayList3);
            }
            Iterator<Definition> it = list.iterator();
            while (true) {
                DBDefinition dBDefinition = null;
                if (!it.hasNext()) {
                    break;
                }
                Definition next2 = it.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBDefinition = (DBDefinition) linkedHashMap.get(next2);
                }
                if (dBDefinition == null) {
                    Iterator it2 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBDefinition dBDefinition2 = (DBDefinition) it2.next();
                        if (dBDefinition2.getIdentifier().equals(next2.identifier)) {
                            dBDefinition = dBDefinition2;
                            break;
                        }
                    }
                }
                if (dBDefinition == null) {
                    dBDefinition = new DBDefinition();
                    arrayList4.add(dBDefinition);
                }
                dBDefinition.setIdentifier(next2.identifier);
                linkedHashMap.put(next2, dBDefinition);
            }
            if (arrayList4.size() > 0) {
                daoSession2.getDBDefinitionDao().insertInTx(arrayList4);
            }
            ArrayList arrayList6 = new ArrayList(linkedHashMap.size());
            for (DBDefinition id2 : linkedHashMap.values()) {
                arrayList6.add(id2.getId());
            }
            List<DBDefinitionSection> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBDefinitionSectionDao(), DBDefinitionSectionDao.Properties.DefinitionId, arrayList6);
            for (DBDefinitionSection definitionId : allWithPropertyInData2) {
                definitionId.setDefinitionId((Long) null);
            }
            ArrayList arrayList7 = new ArrayList(arrayList5.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                Definition definition = (Definition) entry.getKey();
                DBDefinition dBDefinition3 = (DBDefinition) entry.getValue();
                if (definition.definitionSections != null && !definition.definitionSections.isEmpty()) {
                    for (DefinitionSection next3 : definition.definitionSections) {
                        Iterator it3 = arrayList5.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            DBDefinitionSection dBDefinitionSection = (DBDefinitionSection) it3.next();
                            if (dBDefinitionSection.getIdentifier().equals(next3.identifier)) {
                                dBDefinitionSection.setDefinitionId(dBDefinition3.getId());
                                arrayList7.add(dBDefinitionSection);
                                break;
                            }
                        }
                    }
                }
                dBDefinition3.resetDefinitionSections();
            }
            ArrayList arrayList8 = new ArrayList(allWithPropertyInData2.size());
            for (DBDefinitionSection dBDefinitionSection2 : allWithPropertyInData2) {
                if (dBDefinitionSection2.getDefinitionId() == null) {
                    arrayList8.add(dBDefinitionSection2);
                }
            }
            if (!arrayList8.isEmpty()) {
                daoSession2.getDBDefinitionSectionDao().deleteInTx(arrayList8);
            }
            if (!arrayList7.isEmpty()) {
                daoSession2.getDBDefinitionSectionDao().updateInTx(arrayList7);
            }
            ArrayList arrayList9 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBDefinitionDao().updateInTx(arrayList9);
            return arrayList9;
        }
    }
}
