package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSection;
import com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSectionItem;
import com.wbmd.qxcalculator.model.db.DBCalculatorDao;
import com.wbmd.qxcalculator.model.db.DBExtraSectionDao;
import com.wbmd.qxcalculator.model.db.DBExtraSectionItemDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBExtraSection {
    public static final String TAG = DBExtraSection.class.getSimpleName();
    private Long calculatorId;
    private String conditionFormula;
    private transient DaoSession daoSession;
    private List<DBExtraSectionItem> extraSectionItems;
    private Boolean hideWhenNoResults;
    private Long id;
    private String identifier;
    private transient DBExtraSectionDao myDao;
    private Integer sectionIndex;
    private Boolean showSeparators;
    private String subTitle;
    private String title;

    public DBExtraSection() {
    }

    public DBExtraSection(Long l) {
        this.id = l;
    }

    public DBExtraSection(Long l, String str, String str2, String str3, Integer num, Boolean bool, Boolean bool2, String str4, Long l2) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.subTitle = str3;
        this.sectionIndex = num;
        this.showSeparators = bool;
        this.hideWhenNoResults = bool2;
        this.conditionFormula = str4;
        this.calculatorId = l2;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBExtraSectionDao() : null;
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

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public Integer getSectionIndex() {
        return this.sectionIndex;
    }

    public void setSectionIndex(Integer num) {
        this.sectionIndex = num;
    }

    public Boolean getShowSeparators() {
        return this.showSeparators;
    }

    public void setShowSeparators(Boolean bool) {
        this.showSeparators = bool;
    }

    public Boolean getHideWhenNoResults() {
        return this.hideWhenNoResults;
    }

    public void setHideWhenNoResults(Boolean bool) {
        this.hideWhenNoResults = bool;
    }

    public String getConditionFormula() {
        return this.conditionFormula;
    }

    public void setConditionFormula(String str) {
        this.conditionFormula = str;
    }

    public Long getCalculatorId() {
        return this.calculatorId;
    }

    public void setCalculatorId(Long l) {
        this.calculatorId = l;
    }

    public List<DBExtraSectionItem> getExtraSectionItems() {
        if (this.extraSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBExtraSectionItem> _queryDBExtraSection_ExtraSectionItems = daoSession2.getDBExtraSectionItemDao()._queryDBExtraSection_ExtraSectionItems(this.id);
                synchronized (this) {
                    if (this.extraSectionItems == null) {
                        this.extraSectionItems = _queryDBExtraSection_ExtraSectionItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.extraSectionItems;
    }

    public synchronized void resetExtraSectionItems() {
        this.extraSectionItems = null;
    }

    public void delete() {
        DBExtraSectionDao dBExtraSectionDao = this.myDao;
        if (dBExtraSectionDao != null) {
            dBExtraSectionDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBExtraSectionDao dBExtraSectionDao = this.myDao;
        if (dBExtraSectionDao != null) {
            dBExtraSectionDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBExtraSectionDao dBExtraSectionDao = this.myDao;
        if (dBExtraSectionDao != null) {
            dBExtraSectionDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void deleteUnusedExtraSections(DaoSession daoSession2) {
        List list = daoSession2.getDBExtraSectionDao().queryBuilder().where(DBExtraSectionDao.Properties.CalculatorId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBExtraSection: " + list.size());
        deleteExtraSections(daoSession2, list);
    }

    public static void deleteExtraSections(DaoSession daoSession2, List<DBExtraSection> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(list.size());
            for (DBExtraSection next : list) {
                arrayList2.add(next.getId());
                next.resetExtraSectionItems();
                if (next.getCalculatorId() != null) {
                    arrayList.add(next.getCalculatorId());
                }
            }
            List<DBExtraSectionItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBExtraSectionItemDao(), DBExtraSectionItemDao.Properties.ExtraSectionId, arrayList2);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBExtraSectionItem extraSectionId : allWithPropertyInData) {
                    extraSectionId.setExtraSectionId((Long) null);
                }
                DBExtraSectionItem.deleteExtraSectionItems(daoSession2, allWithPropertyInData);
            }
            if (!arrayList.isEmpty()) {
                List<DBCalculator> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBCalculatorDao(), DBCalculatorDao.Properties.Id, arrayList);
                if (!allWithPropertyInData2.isEmpty()) {
                    for (DBCalculator resetExtraSections : allWithPropertyInData2) {
                        resetExtraSections.resetExtraSections();
                    }
                }
            }
            daoSession2.getDBExtraSectionDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBExtraSection insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSection r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBExtraSection> r0 = com.wbmd.qxcalculator.model.db.DBExtraSection.class
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
            com.wbmd.qxcalculator.model.db.DBExtraSection r1 = (com.wbmd.qxcalculator.model.db.DBExtraSection) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBExtraSection.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSection):com.wbmd.qxcalculator.model.db.DBExtraSection");
    }

    public static synchronized List<DBExtraSection> insertAndRetrieveDbEntities(DaoSession daoSession2, List<ExtraSection> list) {
        synchronized (DBExtraSection.class) {
            if (daoSession2 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (ExtraSection next : list) {
                arrayList2.add(next.identifier);
                if (next.sectionItems != null) {
                    arrayList3.addAll(next.sectionItems);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBExtraSectionDao(), DBExtraSectionDao.Properties.Identifier, arrayList2);
            ArrayList arrayList4 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList5 = new ArrayList();
            if (arrayList3.size() > 0) {
                arrayList5 = DBExtraSectionItem.insertAndRetrieveDbEntities(daoSession2, arrayList3);
            }
            Iterator<ExtraSection> it = list.iterator();
            while (true) {
                DBExtraSection dBExtraSection = null;
                if (!it.hasNext()) {
                    break;
                }
                ExtraSection next2 = it.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBExtraSection = (DBExtraSection) linkedHashMap.get(next2);
                }
                if (dBExtraSection == null) {
                    Iterator it2 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBExtraSection dBExtraSection2 = (DBExtraSection) it2.next();
                        if (dBExtraSection2.getIdentifier().equals(next2.identifier)) {
                            dBExtraSection = dBExtraSection2;
                            break;
                        }
                    }
                }
                if (dBExtraSection == null) {
                    dBExtraSection = new DBExtraSection();
                    arrayList4.add(dBExtraSection);
                }
                dBExtraSection.setIdentifier(next2.identifier);
                dBExtraSection.setTitle(next2.title);
                dBExtraSection.setSubTitle(next2.subTitle);
                dBExtraSection.setSectionIndex(next2.sectionIndex);
                dBExtraSection.setShowSeparators(next2.showSeparators);
                dBExtraSection.setHideWhenNoResults(next2.hideWhenNoResults);
                linkedHashMap.put(next2, dBExtraSection);
            }
            if (arrayList4.size() > 0) {
                daoSession2.getDBExtraSectionDao().insertInTx(arrayList4);
            }
            ArrayList arrayList6 = new ArrayList(linkedHashMap.size());
            for (DBExtraSection id2 : linkedHashMap.values()) {
                arrayList6.add(id2.getId());
            }
            List<DBExtraSectionItem> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBExtraSectionItemDao(), DBExtraSectionItemDao.Properties.ExtraSectionId, arrayList6);
            for (DBExtraSectionItem extraSectionId : allWithPropertyInData2) {
                extraSectionId.setExtraSectionId((Long) null);
            }
            ArrayList arrayList7 = new ArrayList(arrayList5.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                ExtraSection extraSection = (ExtraSection) entry.getKey();
                DBExtraSection dBExtraSection3 = (DBExtraSection) entry.getValue();
                if (extraSection.sectionItems != null && !extraSection.sectionItems.isEmpty()) {
                    for (ExtraSectionItem next3 : extraSection.sectionItems) {
                        Iterator it3 = arrayList5.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            DBExtraSectionItem dBExtraSectionItem = (DBExtraSectionItem) it3.next();
                            if (dBExtraSectionItem.getIdentifier().equals(next3.identifier)) {
                                dBExtraSectionItem.setExtraSectionId(dBExtraSection3.getId());
                                arrayList7.add(dBExtraSectionItem);
                                break;
                            }
                        }
                    }
                }
                dBExtraSection3.resetExtraSectionItems();
            }
            ArrayList arrayList8 = new ArrayList(allWithPropertyInData2.size());
            for (DBExtraSectionItem dBExtraSectionItem2 : allWithPropertyInData2) {
                if (dBExtraSectionItem2.getExtraSectionId() == null) {
                    arrayList8.add(dBExtraSectionItem2);
                }
            }
            if (!arrayList8.isEmpty()) {
                daoSession2.getDBExtraSectionItemDao().deleteInTx(arrayList8);
            }
            if (!arrayList7.isEmpty()) {
                daoSession2.getDBExtraSectionItemDao().updateInTx(arrayList7);
            }
            ArrayList arrayList9 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBExtraSectionDao().updateInTx(arrayList9);
            return arrayList9;
        }
    }
}
