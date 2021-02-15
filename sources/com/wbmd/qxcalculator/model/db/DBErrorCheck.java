package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.ErrorCheck;
import com.wbmd.qxcalculator.model.db.DBCalculatorDao;
import com.wbmd.qxcalculator.model.db.DBErrorCheckDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBErrorCheck {
    public static final String TAG = DBErrorCheck.class.getSimpleName();
    private String answer;
    private Long calculatorId;
    private String formula;
    private Long id;
    private String identifier;
    private Long orderedId;
    private Integer position;
    private String title;
    private String type;

    public DBErrorCheck() {
    }

    public DBErrorCheck(Long l) {
        this.id = l;
    }

    public DBErrorCheck(Long l, String str, Long l2, Integer num, String str2, String str3, String str4, String str5, Long l3) {
        this.id = l;
        this.identifier = str;
        this.orderedId = l2;
        this.position = num;
        this.type = str2;
        this.title = str3;
        this.answer = str4;
        this.formula = str5;
        this.calculatorId = l3;
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

    public Long getOrderedId() {
        return this.orderedId;
    }

    public void setOrderedId(Long l) {
        this.orderedId = l;
    }

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer num) {
        this.position = num;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String str) {
        this.formula = str;
    }

    public Long getCalculatorId() {
        return this.calculatorId;
    }

    public void setCalculatorId(Long l) {
        this.calculatorId = l;
    }

    public static void deleteUnusedErrorChecks(DaoSession daoSession) {
        List list = daoSession.getDBErrorCheckDao().queryBuilder().where(DBErrorCheckDao.Properties.CalculatorId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBErrorCheck: " + list.size());
        deleteErrorChecks(daoSession, list);
    }

    public static void deleteErrorChecks(DaoSession daoSession, List<DBErrorCheck> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBErrorCheck next : list) {
                if (next.getCalculatorId() != null) {
                    arrayList.add(next.getCalculatorId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBCalculator> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBCalculatorDao(), DBCalculatorDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBCalculator resetErrorChecks : allWithPropertyInData) {
                        resetErrorChecks.resetErrorChecks();
                    }
                }
            }
            daoSession.getDBErrorCheckDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBErrorCheck insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.ErrorCheck r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBErrorCheck> r0 = com.wbmd.qxcalculator.model.db.DBErrorCheck.class
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
            com.wbmd.qxcalculator.model.db.DBErrorCheck r1 = (com.wbmd.qxcalculator.model.db.DBErrorCheck) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBErrorCheck.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.ErrorCheck):com.wbmd.qxcalculator.model.db.DBErrorCheck");
    }

    public static synchronized List<DBErrorCheck> insertAndRetrieveDbEntities(DaoSession daoSession, List<ErrorCheck> list) {
        synchronized (DBErrorCheck.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (ErrorCheck errorCheck : list) {
                arrayList2.add(errorCheck.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBErrorCheckDao(), DBErrorCheckDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (ErrorCheck next : list) {
                DBErrorCheck dBErrorCheck = linkedHashMap.containsKey(next) ? (DBErrorCheck) linkedHashMap.get(next) : null;
                if (dBErrorCheck == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBErrorCheck dBErrorCheck2 = (DBErrorCheck) it.next();
                        if (dBErrorCheck2.getIdentifier().equals(next.identifier)) {
                            dBErrorCheck = dBErrorCheck2;
                            break;
                        }
                    }
                }
                if (dBErrorCheck == null) {
                    dBErrorCheck = new DBErrorCheck();
                    arrayList3.add(dBErrorCheck);
                }
                dBErrorCheck.setIdentifier(next.identifier);
                dBErrorCheck.setOrderedId(next.orderedId);
                dBErrorCheck.setPosition(next.position);
                dBErrorCheck.setType(next.type);
                dBErrorCheck.setTitle(next.title);
                dBErrorCheck.setFormula(next.formula);
                dBErrorCheck.setAnswer(next.answer);
                linkedHashMap.put(next, dBErrorCheck);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBErrorCheckDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBErrorCheckDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
