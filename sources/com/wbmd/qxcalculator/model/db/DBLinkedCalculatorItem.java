package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.LinkedCalculatorItem;
import com.wbmd.qxcalculator.model.db.DBLinkedCalculatorItemDao;
import com.wbmd.qxcalculator.model.db.DBQuestionDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBLinkedCalculatorItem {
    public static final String TAG = DBLinkedCalculatorItem.class.getSimpleName();
    private String calculatorIdentifier;
    private Long id;
    private String identifier;
    private Long questionId;
    private String resultConvertFormula;
    private String resultUnits;

    public DBLinkedCalculatorItem() {
    }

    public DBLinkedCalculatorItem(Long l) {
        this.id = l;
    }

    public DBLinkedCalculatorItem(Long l, String str, String str2, String str3, String str4, Long l2) {
        this.id = l;
        this.identifier = str;
        this.calculatorIdentifier = str2;
        this.resultConvertFormula = str3;
        this.resultUnits = str4;
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

    public String getCalculatorIdentifier() {
        return this.calculatorIdentifier;
    }

    public void setCalculatorIdentifier(String str) {
        this.calculatorIdentifier = str;
    }

    public String getResultConvertFormula() {
        return this.resultConvertFormula;
    }

    public void setResultConvertFormula(String str) {
        this.resultConvertFormula = str;
    }

    public String getResultUnits() {
        return this.resultUnits;
    }

    public void setResultUnits(String str) {
        this.resultUnits = str;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long l) {
        this.questionId = l;
    }

    public static void deleteUnusedLinkedCalculatorItems(DaoSession daoSession) {
        List list = daoSession.getDBLinkedCalculatorItemDao().queryBuilder().where(DBLinkedCalculatorItemDao.Properties.QuestionId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBLinkedCalculatorItem: " + list.size());
        deleteLinkedCalculatorItems(daoSession, list);
    }

    public static void deleteLinkedCalculatorItems(DaoSession daoSession, List<DBLinkedCalculatorItem> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBLinkedCalculatorItem next : list) {
                if (next.getQuestionId() != null) {
                    arrayList.add(next.getQuestionId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBQuestion> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBQuestionDao(), DBQuestionDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBQuestion resetLinkedCalculatorItems : allWithPropertyInData) {
                        resetLinkedCalculatorItems.resetLinkedCalculatorItems();
                    }
                }
            }
            daoSession.getDBLinkedCalculatorItemDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBLinkedCalculatorItem insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.LinkedCalculatorItem r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBLinkedCalculatorItem> r0 = com.wbmd.qxcalculator.model.db.DBLinkedCalculatorItem.class
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
            com.wbmd.qxcalculator.model.db.DBLinkedCalculatorItem r1 = (com.wbmd.qxcalculator.model.db.DBLinkedCalculatorItem) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBLinkedCalculatorItem.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.LinkedCalculatorItem):com.wbmd.qxcalculator.model.db.DBLinkedCalculatorItem");
    }

    public static synchronized List<DBLinkedCalculatorItem> insertAndRetrieveDbEntities(DaoSession daoSession, List<LinkedCalculatorItem> list) {
        synchronized (DBLinkedCalculatorItem.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (LinkedCalculatorItem linkedCalculatorItem : list) {
                arrayList2.add(linkedCalculatorItem.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBLinkedCalculatorItemDao(), DBLinkedCalculatorItemDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (LinkedCalculatorItem next : list) {
                DBLinkedCalculatorItem dBLinkedCalculatorItem = linkedHashMap.containsKey(next) ? (DBLinkedCalculatorItem) linkedHashMap.get(next) : null;
                if (dBLinkedCalculatorItem == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBLinkedCalculatorItem dBLinkedCalculatorItem2 = (DBLinkedCalculatorItem) it.next();
                        if (dBLinkedCalculatorItem2.getIdentifier().equals(next.identifier)) {
                            dBLinkedCalculatorItem = dBLinkedCalculatorItem2;
                            break;
                        }
                    }
                }
                if (dBLinkedCalculatorItem == null) {
                    dBLinkedCalculatorItem = new DBLinkedCalculatorItem();
                    arrayList3.add(dBLinkedCalculatorItem);
                }
                dBLinkedCalculatorItem.setIdentifier(next.identifier);
                dBLinkedCalculatorItem.setCalculatorIdentifier(next.calculatorIdentifier);
                dBLinkedCalculatorItem.setResultConvertFormula(next.resultConvertFormula);
                dBLinkedCalculatorItem.setResultUnits(next.resultUnits);
                linkedHashMap.put(next, dBLinkedCalculatorItem);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBLinkedCalculatorItemDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBLinkedCalculatorItemDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
