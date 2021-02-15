package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.db.DBQuestionDao;
import com.wbmd.qxcalculator.model.db.DBUnitDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBUnit {
    public static final String TAG = DBUnit.class.getSimpleName();
    private Long id;
    private String identifier;
    private Double initialValue;
    private Double maxValue;
    private String maxValueMessage;
    private Double minValue;
    private String minValueMessage;
    private Long questionId;
    private String title;
    private String type;
    private Double unitFactor;

    public DBUnit() {
    }

    public DBUnit(Long l) {
        this.id = l;
    }

    public DBUnit(Long l, String str, String str2, String str3, Double d, Double d2, Double d3, Double d4, String str4, String str5, Long l2) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.type = str3;
        this.initialValue = d;
        this.unitFactor = d2;
        this.minValue = d3;
        this.maxValue = d4;
        this.minValueMessage = str4;
        this.maxValueMessage = str5;
        this.questionId = l2;
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

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Double getInitialValue() {
        return this.initialValue;
    }

    public void setInitialValue(Double d) {
        this.initialValue = d;
    }

    public Double getUnitFactor() {
        return this.unitFactor;
    }

    public void setUnitFactor(Double d) {
        this.unitFactor = d;
    }

    public Double getMinValue() {
        return this.minValue;
    }

    public void setMinValue(Double d) {
        this.minValue = d;
    }

    public Double getMaxValue() {
        return this.maxValue;
    }

    public void setMaxValue(Double d) {
        this.maxValue = d;
    }

    public String getMinValueMessage() {
        return this.minValueMessage;
    }

    public void setMinValueMessage(String str) {
        this.minValueMessage = str;
    }

    public String getMaxValueMessage() {
        return this.maxValueMessage;
    }

    public void setMaxValueMessage(String str) {
        this.maxValueMessage = str;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long l) {
        this.questionId = l;
    }

    public static void deleteUnusedUnits(DaoSession daoSession) {
        List list = daoSession.getDBUnitDao().queryBuilder().where(DBUnitDao.Properties.QuestionId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBUnit: " + list.size());
        deleteUnits(daoSession, list);
    }

    public static void deleteUnits(DaoSession daoSession, List<DBUnit> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBUnit next : list) {
                if (next.getQuestionId() != null) {
                    arrayList.add(next.getQuestionId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBQuestion> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBQuestionDao(), DBQuestionDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBQuestion resetUnits : allWithPropertyInData) {
                        resetUnits.resetUnits();
                    }
                }
            }
            daoSession.getDBUnitDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBUnit insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.Unit r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBUnit> r0 = com.wbmd.qxcalculator.model.db.DBUnit.class
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
            com.wbmd.qxcalculator.model.db.DBUnit r1 = (com.wbmd.qxcalculator.model.db.DBUnit) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBUnit.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.Unit):com.wbmd.qxcalculator.model.db.DBUnit");
    }

    public static synchronized List<DBUnit> insertAndRetrieveDbEntities(DaoSession daoSession, List<Unit> list) {
        synchronized (DBUnit.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (Unit unit : list) {
                arrayList2.add(unit.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBUnitDao(), DBUnitDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Unit next : list) {
                DBUnit dBUnit = linkedHashMap.containsKey(next) ? (DBUnit) linkedHashMap.get(next) : null;
                if (dBUnit == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBUnit dBUnit2 = (DBUnit) it.next();
                        if (dBUnit2.getIdentifier().equals(next.identifier)) {
                            dBUnit = dBUnit2;
                            break;
                        }
                    }
                }
                if (dBUnit == null) {
                    dBUnit = new DBUnit();
                    arrayList3.add(dBUnit);
                }
                dBUnit.setIdentifier(next.identifier);
                dBUnit.setTitle(next.title);
                dBUnit.setType(next.type);
                dBUnit.setInitialValue(next.initialValue);
                dBUnit.setUnitFactor(next.unitFactor);
                dBUnit.setMinValue(next.minValue);
                dBUnit.setMaxValue(next.maxValue);
                dBUnit.setMinValueMessage(next.minValueMessage);
                dBUnit.setMaxValueMessage(next.maxValueMessage);
                linkedHashMap.put(next, dBUnit);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBUnitDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBUnitDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
