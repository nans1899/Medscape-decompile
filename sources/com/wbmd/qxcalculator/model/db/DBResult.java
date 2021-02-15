package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.Result;
import com.wbmd.qxcalculator.model.db.DBCalculatorDao;
import com.wbmd.qxcalculator.model.db.DBResultDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBResult {
    public static final String TAG = DBResult.class.getSimpleName();
    private String answer;
    private String answerPrimary;
    private String answerSecondary;
    private Long calculatorId;
    private String conditionFormula;
    private String formula;
    private Long id;
    private String identifier;
    private Long orderedId;
    private Integer position;
    private String subTitle;
    private String subTitleFormula;
    private String title;
    private String titleFormula;
    private String type;

    public DBResult() {
    }

    public DBResult(Long l) {
        this.id = l;
    }

    public DBResult(Long l, String str, Long l2, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, Long l3) {
        this.id = l;
        this.identifier = str;
        this.orderedId = l2;
        this.position = num;
        this.type = str2;
        this.conditionFormula = str3;
        this.title = str4;
        this.titleFormula = str5;
        this.subTitle = str6;
        this.subTitleFormula = str7;
        this.formula = str8;
        this.answer = str9;
        this.answerPrimary = str10;
        this.answerSecondary = str11;
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

    public String getConditionFormula() {
        return this.conditionFormula;
    }

    public void setConditionFormula(String str) {
        this.conditionFormula = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitleFormula() {
        return this.titleFormula;
    }

    public void setTitleFormula(String str) {
        this.titleFormula = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public String getSubTitleFormula() {
        return this.subTitleFormula;
    }

    public void setSubTitleFormula(String str) {
        this.subTitleFormula = str;
    }

    public String getFormula() {
        return this.formula;
    }

    public void setFormula(String str) {
        this.formula = str;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }

    public String getAnswerPrimary() {
        return this.answerPrimary;
    }

    public void setAnswerPrimary(String str) {
        this.answerPrimary = str;
    }

    public String getAnswerSecondary() {
        return this.answerSecondary;
    }

    public void setAnswerSecondary(String str) {
        this.answerSecondary = str;
    }

    public Long getCalculatorId() {
        return this.calculatorId;
    }

    public void setCalculatorId(Long l) {
        this.calculatorId = l;
    }

    public static void deleteUnusedResults(DaoSession daoSession) {
        List list = daoSession.getDBResultDao().queryBuilder().where(DBResultDao.Properties.CalculatorId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBResult: " + list.size());
        deleteResults(daoSession, list);
    }

    public static void deleteResults(DaoSession daoSession, List<DBResult> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBResult next : list) {
                if (next.getCalculatorId() != null) {
                    arrayList.add(next.getCalculatorId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBCalculator> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBCalculatorDao(), DBCalculatorDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBCalculator resetResults : allWithPropertyInData) {
                        resetResults.resetResults();
                    }
                }
            }
            daoSession.getDBResultDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBResult insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.Result r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBResult> r0 = com.wbmd.qxcalculator.model.db.DBResult.class
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
            com.wbmd.qxcalculator.model.db.DBResult r1 = (com.wbmd.qxcalculator.model.db.DBResult) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBResult.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.Result):com.wbmd.qxcalculator.model.db.DBResult");
    }

    public static synchronized List<DBResult> insertAndRetrieveDbEntities(DaoSession daoSession, List<Result> list) {
        synchronized (DBResult.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (Result result : list) {
                arrayList2.add(result.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBResultDao(), DBResultDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Result next : list) {
                DBResult dBResult = linkedHashMap.containsKey(next) ? (DBResult) linkedHashMap.get(next) : null;
                if (dBResult == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBResult dBResult2 = (DBResult) it.next();
                        if (dBResult2.getIdentifier().equals(next.identifier)) {
                            dBResult = dBResult2;
                            break;
                        }
                    }
                }
                if (dBResult == null) {
                    dBResult = new DBResult();
                    arrayList3.add(dBResult);
                }
                dBResult.setIdentifier(next.identifier);
                dBResult.setOrderedId(next.orderedId);
                dBResult.setPosition(next.position);
                dBResult.setType(next.type);
                dBResult.setConditionFormula(next.conditionFormula);
                dBResult.setTitle(next.title);
                dBResult.setTitleFormula(next.titleFormula);
                dBResult.setSubTitle(next.subTitle);
                dBResult.setSubTitleFormula(next.subTitleFormula);
                dBResult.setFormula(next.formula);
                dBResult.setAnswer(next.answer);
                dBResult.setAnswerPrimary(next.answerPrimary);
                dBResult.setAnswerSecondary(next.answerSecondary);
                linkedHashMap.put(next, dBResult);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBResultDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBResultDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
