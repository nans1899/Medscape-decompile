package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.Calculator;
import com.wbmd.qxcalculator.model.contentItems.calculator.ErrorCheck;
import com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSection;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.calculator.Result;
import com.wbmd.qxcalculator.model.db.DBCalculatorDao;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBErrorCheckDao;
import com.wbmd.qxcalculator.model.db.DBExtraSectionDao;
import com.wbmd.qxcalculator.model.db.DBQuestionDao;
import com.wbmd.qxcalculator.model.db.DBResultDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBCalculator {
    public static final String TAG = DBCalculator.class.getSimpleName();
    private transient DaoSession daoSession;
    private List<DBErrorCheck> errorChecks;
    private List<DBExtraSection> extraSections;
    private Long id;
    private String identifier;
    private transient DBCalculatorDao myDao;
    private List<DBQuestion> questions;
    private List<DBResult> results;

    public DBCalculator() {
    }

    public DBCalculator(Long l) {
        this.id = l;
    }

    public DBCalculator(Long l, String str) {
        this.id = l;
        this.identifier = str;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBCalculatorDao() : null;
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

    public List<DBQuestion> getQuestions() {
        if (this.questions == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBQuestion> _queryDBCalculator_Questions = daoSession2.getDBQuestionDao()._queryDBCalculator_Questions(this.id);
                synchronized (this) {
                    if (this.questions == null) {
                        this.questions = _queryDBCalculator_Questions;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.questions;
    }

    public synchronized void resetQuestions() {
        this.questions = null;
    }

    public List<DBResult> getResults() {
        if (this.results == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBResult> _queryDBCalculator_Results = daoSession2.getDBResultDao()._queryDBCalculator_Results(this.id);
                synchronized (this) {
                    if (this.results == null) {
                        this.results = _queryDBCalculator_Results;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.results;
    }

    public synchronized void resetResults() {
        this.results = null;
    }

    public List<DBErrorCheck> getErrorChecks() {
        if (this.errorChecks == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBErrorCheck> _queryDBCalculator_ErrorChecks = daoSession2.getDBErrorCheckDao()._queryDBCalculator_ErrorChecks(this.id);
                synchronized (this) {
                    if (this.errorChecks == null) {
                        this.errorChecks = _queryDBCalculator_ErrorChecks;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.errorChecks;
    }

    public synchronized void resetErrorChecks() {
        this.errorChecks = null;
    }

    public List<DBExtraSection> getExtraSections() {
        if (this.extraSections == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBExtraSection> _queryDBCalculator_ExtraSections = daoSession2.getDBExtraSectionDao()._queryDBCalculator_ExtraSections(this.id);
                synchronized (this) {
                    if (this.extraSections == null) {
                        this.extraSections = _queryDBCalculator_ExtraSections;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.extraSections;
    }

    public synchronized void resetExtraSections() {
        this.extraSections = null;
    }

    public void delete() {
        DBCalculatorDao dBCalculatorDao = this.myDao;
        if (dBCalculatorDao != null) {
            dBCalculatorDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBCalculatorDao dBCalculatorDao = this.myDao;
        if (dBCalculatorDao != null) {
            dBCalculatorDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBCalculatorDao dBCalculatorDao = this.myDao;
        if (dBCalculatorDao != null) {
            dBCalculatorDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void deleteUnusedCalculators(DaoSession daoSession2) {
        List<DBContentItem> loadAll = daoSession2.getDBContentItemDao().loadAll();
        ArrayList arrayList = new ArrayList();
        for (DBContentItem dBContentItem : loadAll) {
            if (dBContentItem.getCalculatorId() != null) {
                arrayList.add(dBContentItem.getCalculatorId());
            }
        }
        List allWithPropertyNotInData = DatabaseHelper.getAllWithPropertyNotInData(daoSession2.getDBCalculatorDao(), DBCalculatorDao.Properties.Id, arrayList);
        String str = TAG;
        Log.d(str, "Purging DBCalculator: " + allWithPropertyNotInData.size());
        deleteCalculators(daoSession2, allWithPropertyNotInData);
    }

    public static void deleteCalculators(DaoSession daoSession2, List<DBCalculator> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList(list.size());
            for (DBCalculator next : list) {
                arrayList.add(next.getId());
                next.resetQuestions();
                next.resetResults();
                next.resetErrorChecks();
                next.resetExtraSections();
            }
            List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.CalculatorId, arrayList);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBContentItem calculatorId : allWithPropertyInData) {
                    calculatorId.setCalculatorId((Long) null);
                }
                daoSession2.getDBContentItemDao().updateInTx(allWithPropertyInData);
            }
            List<DBQuestion> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBQuestionDao(), DBQuestionDao.Properties.CalculatorId, arrayList);
            if (!allWithPropertyInData2.isEmpty()) {
                for (DBQuestion calculatorId2 : allWithPropertyInData2) {
                    calculatorId2.setCalculatorId((Long) null);
                }
                DBQuestion.deleteQuestions(daoSession2, allWithPropertyInData2);
            }
            List<DBResult> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBResultDao(), DBResultDao.Properties.CalculatorId, arrayList);
            if (!allWithPropertyInData3.isEmpty()) {
                for (DBResult calculatorId3 : allWithPropertyInData3) {
                    calculatorId3.setCalculatorId((Long) null);
                }
                DBResult.deleteResults(daoSession2, allWithPropertyInData3);
            }
            List<DBErrorCheck> allWithPropertyInData4 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBErrorCheckDao(), DBErrorCheckDao.Properties.CalculatorId, arrayList);
            if (!allWithPropertyInData4.isEmpty()) {
                for (DBErrorCheck calculatorId4 : allWithPropertyInData4) {
                    calculatorId4.setCalculatorId((Long) null);
                }
                DBErrorCheck.deleteErrorChecks(daoSession2, allWithPropertyInData4);
            }
            List<DBExtraSection> allWithPropertyInData5 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBExtraSectionDao(), DBExtraSectionDao.Properties.CalculatorId, arrayList);
            if (!allWithPropertyInData5.isEmpty()) {
                for (DBExtraSection calculatorId5 : allWithPropertyInData5) {
                    calculatorId5.setCalculatorId((Long) null);
                }
                DBExtraSection.deleteExtraSections(daoSession2, allWithPropertyInData5);
            }
            daoSession2.getDBCalculatorDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBCalculator insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.Calculator r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBCalculator> r0 = com.wbmd.qxcalculator.model.db.DBCalculator.class
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
            com.wbmd.qxcalculator.model.db.DBCalculator r1 = (com.wbmd.qxcalculator.model.db.DBCalculator) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBCalculator.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.Calculator):com.wbmd.qxcalculator.model.db.DBCalculator");
    }

    public static synchronized List<DBCalculator> insertAndRetrieveDbEntities(DaoSession daoSession2, List<Calculator> list) {
        Iterator<ErrorCheck> it;
        Iterator<Result> it2;
        List list2;
        List list3;
        LinkedHashMap linkedHashMap;
        List list4;
        DaoSession daoSession3 = daoSession2;
        synchronized (DBCalculator.class) {
            if (daoSession3 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            for (Calculator next : list) {
                arrayList2.add(next.identifier);
                if (next.questions != null) {
                    arrayList3.addAll(next.questions);
                }
                if (next.results != null) {
                    arrayList4.addAll(next.results);
                }
                if (next.errorChecks != null) {
                    arrayList5.addAll(next.errorChecks);
                }
                if (next.extraSections != null) {
                    arrayList6.addAll(next.extraSections);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBCalculatorDao(), DBCalculatorDao.Properties.Identifier, arrayList2);
            ArrayList arrayList7 = new ArrayList();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            List arrayList8 = new ArrayList();
            if (arrayList3.size() > 0) {
                arrayList8 = DBQuestion.insertAndRetrieveDbEntities(daoSession3, arrayList3);
            }
            List arrayList9 = new ArrayList();
            if (arrayList4.size() > 0) {
                arrayList9 = DBResult.insertAndRetrieveDbEntities(daoSession3, arrayList4);
            }
            List arrayList10 = new ArrayList();
            if (arrayList5.size() > 0) {
                arrayList10 = DBErrorCheck.insertAndRetrieveDbEntities(daoSession3, arrayList5);
            }
            List arrayList11 = new ArrayList();
            if (arrayList6.size() > 0) {
                arrayList11 = DBExtraSection.insertAndRetrieveDbEntities(daoSession3, arrayList6);
            }
            Iterator<Calculator> it3 = list.iterator();
            while (true) {
                DBCalculator dBCalculator = null;
                if (!it3.hasNext()) {
                    break;
                }
                Calculator next2 = it3.next();
                if (linkedHashMap2.containsKey(next2)) {
                    dBCalculator = (DBCalculator) linkedHashMap2.get(next2);
                }
                if (dBCalculator == null) {
                    Iterator it4 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it4.hasNext()) {
                            break;
                        }
                        DBCalculator dBCalculator2 = (DBCalculator) it4.next();
                        if (dBCalculator2.getIdentifier().equals(next2.identifier)) {
                            dBCalculator = dBCalculator2;
                            break;
                        }
                    }
                }
                if (dBCalculator == null) {
                    dBCalculator = new DBCalculator();
                    arrayList7.add(dBCalculator);
                }
                dBCalculator.setIdentifier(next2.identifier);
                linkedHashMap2.put(next2, dBCalculator);
            }
            if (arrayList7.size() > 0) {
                daoSession2.getDBCalculatorDao().insertInTx(arrayList7);
            }
            ArrayList arrayList12 = new ArrayList(linkedHashMap2.size());
            for (DBCalculator id2 : linkedHashMap2.values()) {
                arrayList12.add(id2.getId());
            }
            List<DBQuestion> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBQuestionDao(), DBQuestionDao.Properties.CalculatorId, arrayList12);
            for (DBQuestion calculatorId : allWithPropertyInData2) {
                calculatorId.setCalculatorId((Long) null);
            }
            List<DBResult> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBResultDao(), DBResultDao.Properties.CalculatorId, arrayList12);
            for (DBResult calculatorId2 : allWithPropertyInData3) {
                calculatorId2.setCalculatorId((Long) null);
            }
            List<DBErrorCheck> allWithPropertyInData4 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBErrorCheckDao(), DBErrorCheckDao.Properties.CalculatorId, arrayList12);
            for (DBErrorCheck calculatorId3 : allWithPropertyInData4) {
                calculatorId3.setCalculatorId((Long) null);
            }
            List<DBExtraSection> allWithPropertyInData5 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBExtraSectionDao(), DBExtraSectionDao.Properties.CalculatorId, arrayList12);
            for (DBExtraSection calculatorId4 : allWithPropertyInData5) {
                calculatorId4.setCalculatorId((Long) null);
            }
            ArrayList arrayList13 = new ArrayList(arrayList8.size());
            ArrayList arrayList14 = new ArrayList(arrayList9.size());
            ArrayList arrayList15 = new ArrayList(arrayList10.size());
            ArrayList arrayList16 = new ArrayList(arrayList11.size());
            Iterator it5 = linkedHashMap2.entrySet().iterator();
            while (it5.hasNext()) {
                Map.Entry entry = (Map.Entry) it5.next();
                Calculator calculator = (Calculator) entry.getKey();
                DBCalculator dBCalculator3 = (DBCalculator) entry.getValue();
                Iterator it6 = it5;
                if (calculator.questions != null && !calculator.questions.isEmpty()) {
                    Iterator<Question> it7 = calculator.questions.iterator();
                    while (it7.hasNext()) {
                        Iterator<Question> it8 = it7;
                        Question next3 = it7.next();
                        Iterator it9 = arrayList8.iterator();
                        while (true) {
                            if (!it9.hasNext()) {
                                list3 = allWithPropertyInData5;
                                linkedHashMap = linkedHashMap2;
                                list4 = arrayList8;
                                break;
                            }
                            list4 = arrayList8;
                            DBQuestion dBQuestion = (DBQuestion) it9.next();
                            linkedHashMap = linkedHashMap2;
                            list3 = allWithPropertyInData5;
                            if (dBQuestion.getIdentifier().equals(next3.identifier)) {
                                dBQuestion.setCalculatorId(dBCalculator3.getId());
                                arrayList13.add(dBQuestion);
                                break;
                            }
                            linkedHashMap2 = linkedHashMap;
                            arrayList8 = list4;
                            allWithPropertyInData5 = list3;
                        }
                        it7 = it8;
                        linkedHashMap2 = linkedHashMap;
                        arrayList8 = list4;
                        allWithPropertyInData5 = list3;
                    }
                }
                List list5 = allWithPropertyInData5;
                LinkedHashMap linkedHashMap3 = linkedHashMap2;
                List list6 = arrayList8;
                dBCalculator3.resetQuestions();
                if (calculator.results != null && !calculator.results.isEmpty()) {
                    Iterator<Result> it10 = calculator.results.iterator();
                    while (it10.hasNext()) {
                        Result next4 = it10.next();
                        Iterator it11 = arrayList9.iterator();
                        while (true) {
                            if (!it11.hasNext()) {
                                it2 = it10;
                                list2 = arrayList9;
                                break;
                            }
                            DBResult dBResult = (DBResult) it11.next();
                            it2 = it10;
                            list2 = arrayList9;
                            if (dBResult.getIdentifier().equals(next4.identifier)) {
                                dBResult.setCalculatorId(dBCalculator3.getId());
                                arrayList14.add(dBResult);
                                break;
                            }
                            it10 = it2;
                            arrayList9 = list2;
                        }
                        it10 = it2;
                        arrayList9 = list2;
                    }
                }
                List list7 = arrayList9;
                dBCalculator3.resetResults();
                if (calculator.errorChecks != null && !calculator.errorChecks.isEmpty()) {
                    Iterator<ErrorCheck> it12 = calculator.errorChecks.iterator();
                    while (it12.hasNext()) {
                        ErrorCheck next5 = it12.next();
                        Iterator it13 = arrayList10.iterator();
                        while (true) {
                            if (!it13.hasNext()) {
                                it = it12;
                                break;
                            }
                            DBErrorCheck dBErrorCheck = (DBErrorCheck) it13.next();
                            it = it12;
                            if (dBErrorCheck.getIdentifier().equals(next5.identifier)) {
                                dBErrorCheck.setCalculatorId(dBCalculator3.getId());
                                arrayList15.add(dBErrorCheck);
                                break;
                            }
                            it12 = it;
                        }
                        it12 = it;
                    }
                }
                dBCalculator3.resetErrorChecks();
                if (calculator.extraSections != null && !calculator.extraSections.isEmpty()) {
                    for (ExtraSection next6 : calculator.extraSections) {
                        Iterator it14 = arrayList11.iterator();
                        while (true) {
                            if (!it14.hasNext()) {
                                break;
                            }
                            DBExtraSection dBExtraSection = (DBExtraSection) it14.next();
                            if (dBExtraSection.getIdentifier().equals(next6.identifier)) {
                                dBExtraSection.setCalculatorId(dBCalculator3.getId());
                                arrayList16.add(dBExtraSection);
                                break;
                            }
                        }
                    }
                }
                dBCalculator3.resetExtraSections();
                DaoSession daoSession4 = daoSession2;
                it5 = it6;
                arrayList9 = list7;
                linkedHashMap2 = linkedHashMap3;
                arrayList8 = list6;
                allWithPropertyInData5 = list5;
            }
            List<DBExtraSection> list8 = allWithPropertyInData5;
            LinkedHashMap linkedHashMap4 = linkedHashMap2;
            ArrayList arrayList17 = new ArrayList(allWithPropertyInData2.size());
            ArrayList arrayList18 = new ArrayList(allWithPropertyInData3.size());
            ArrayList arrayList19 = new ArrayList(allWithPropertyInData4.size());
            ArrayList arrayList20 = new ArrayList(list8.size());
            for (DBQuestion dBQuestion2 : allWithPropertyInData2) {
                if (dBQuestion2.getCalculatorId() == null) {
                    arrayList17.add(dBQuestion2);
                }
            }
            for (DBResult dBResult2 : allWithPropertyInData3) {
                if (dBResult2.getCalculatorId() == null) {
                    arrayList18.add(dBResult2);
                }
            }
            for (DBErrorCheck dBErrorCheck2 : allWithPropertyInData4) {
                if (dBErrorCheck2.getCalculatorId() == null) {
                    arrayList19.add(dBErrorCheck2);
                }
            }
            for (DBExtraSection dBExtraSection2 : list8) {
                if (dBExtraSection2.getCalculatorId() == null) {
                    arrayList20.add(dBExtraSection2);
                }
            }
            if (!arrayList17.isEmpty()) {
                daoSession2.getDBQuestionDao().deleteInTx(arrayList17);
            }
            if (!arrayList18.isEmpty()) {
                daoSession2.getDBResultDao().deleteInTx(arrayList18);
            }
            if (!arrayList19.isEmpty()) {
                daoSession2.getDBErrorCheckDao().deleteInTx(arrayList19);
            }
            if (!arrayList20.isEmpty()) {
                daoSession2.getDBExtraSectionDao().deleteInTx(arrayList20);
            }
            if (!arrayList13.isEmpty()) {
                daoSession2.getDBQuestionDao().updateInTx(arrayList13);
            }
            if (!arrayList14.isEmpty()) {
                daoSession2.getDBResultDao().updateInTx(arrayList14);
            }
            if (!arrayList15.isEmpty()) {
                daoSession2.getDBErrorCheckDao().updateInTx(arrayList15);
            }
            if (!arrayList16.isEmpty()) {
                daoSession2.getDBExtraSectionDao().updateInTx(arrayList16);
            }
            ArrayList arrayList21 = new ArrayList(linkedHashMap4.values());
            daoSession2.getDBCalculatorDao().updateInTx(arrayList21);
            return arrayList21;
        }
    }
}
