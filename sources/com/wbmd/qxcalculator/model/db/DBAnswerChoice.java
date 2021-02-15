package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.AnswerChoice;
import com.wbmd.qxcalculator.model.db.DBAnswerChoiceDao;
import com.wbmd.qxcalculator.model.db.DBQuestionDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBAnswerChoice {
    public static final String TAG = DBAnswerChoice.class.getSimpleName();
    private Double answerFactor;
    private String errorMessage;
    private Long id;
    private String identifier;
    private Long questionId;
    private String successMessage;
    private String titlePrimary;
    private String titleSecondary;
    private Boolean unitIndependent;
    private String warningMessage;

    public DBAnswerChoice() {
    }

    public DBAnswerChoice(Long l) {
        this.id = l;
    }

    public DBAnswerChoice(Long l, String str, String str2, String str3, Double d, Boolean bool, String str4, String str5, String str6, Long l2) {
        this.id = l;
        this.identifier = str;
        this.titlePrimary = str2;
        this.titleSecondary = str3;
        this.answerFactor = d;
        this.unitIndependent = bool;
        this.successMessage = str4;
        this.warningMessage = str5;
        this.errorMessage = str6;
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

    public String getTitlePrimary() {
        return this.titlePrimary;
    }

    public void setTitlePrimary(String str) {
        this.titlePrimary = str;
    }

    public String getTitleSecondary() {
        return this.titleSecondary;
    }

    public void setTitleSecondary(String str) {
        this.titleSecondary = str;
    }

    public Double getAnswerFactor() {
        return this.answerFactor;
    }

    public void setAnswerFactor(Double d) {
        this.answerFactor = d;
    }

    public Boolean getUnitIndependent() {
        return this.unitIndependent;
    }

    public void setUnitIndependent(Boolean bool) {
        this.unitIndependent = bool;
    }

    public String getSuccessMessage() {
        return this.successMessage;
    }

    public void setSuccessMessage(String str) {
        this.successMessage = str;
    }

    public String getWarningMessage() {
        return this.warningMessage;
    }

    public void setWarningMessage(String str) {
        this.warningMessage = str;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long l) {
        this.questionId = l;
    }

    public static void deleteUnusedAnswerChoices(DaoSession daoSession) {
        List list = daoSession.getDBAnswerChoiceDao().queryBuilder().where(DBAnswerChoiceDao.Properties.QuestionId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBAnswerChoice: " + list.size());
        deleteAnswerChoices(daoSession, list);
    }

    public static void deleteAnswerChoices(DaoSession daoSession, List<DBAnswerChoice> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBAnswerChoice next : list) {
                if (next.getQuestionId() != null) {
                    arrayList.add(next.getQuestionId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBQuestion> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBQuestionDao(), DBQuestionDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBQuestion resetAnswerChoices : allWithPropertyInData) {
                        resetAnswerChoices.resetAnswerChoices();
                    }
                }
            }
            daoSession.getDBAnswerChoiceDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBAnswerChoice insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.AnswerChoice r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBAnswerChoice> r0 = com.wbmd.qxcalculator.model.db.DBAnswerChoice.class
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
            com.wbmd.qxcalculator.model.db.DBAnswerChoice r1 = (com.wbmd.qxcalculator.model.db.DBAnswerChoice) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBAnswerChoice.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.AnswerChoice):com.wbmd.qxcalculator.model.db.DBAnswerChoice");
    }

    public static synchronized List<DBAnswerChoice> insertAndRetrieveDbEntities(DaoSession daoSession, List<AnswerChoice> list) {
        synchronized (DBAnswerChoice.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (AnswerChoice answerChoice : list) {
                arrayList2.add(answerChoice.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBAnswerChoiceDao(), DBAnswerChoiceDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (AnswerChoice next : list) {
                DBAnswerChoice dBAnswerChoice = linkedHashMap.containsKey(next) ? (DBAnswerChoice) linkedHashMap.get(next) : null;
                if (dBAnswerChoice == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBAnswerChoice dBAnswerChoice2 = (DBAnswerChoice) it.next();
                        if (dBAnswerChoice2.getIdentifier().equals(next.identifier)) {
                            dBAnswerChoice = dBAnswerChoice2;
                            break;
                        }
                    }
                }
                if (dBAnswerChoice == null) {
                    dBAnswerChoice = new DBAnswerChoice();
                    arrayList3.add(dBAnswerChoice);
                }
                dBAnswerChoice.setIdentifier(next.identifier);
                dBAnswerChoice.setTitlePrimary(next.titlePrimary);
                dBAnswerChoice.setTitleSecondary(next.titleSecondary);
                dBAnswerChoice.setAnswerFactor(next.answerFactor);
                dBAnswerChoice.setUnitIndependent(next.unitIndependent);
                dBAnswerChoice.setSuccessMessage(next.successMessage);
                dBAnswerChoice.setWarningMessage(next.warningMessage);
                dBAnswerChoice.setErrorMessage(next.errorMessage);
                linkedHashMap.put(next, dBAnswerChoice);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBAnswerChoiceDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBAnswerChoiceDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
