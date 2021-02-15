package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.calculator.AnswerChoice;
import com.wbmd.qxcalculator.model.contentItems.calculator.LinkedCalculatorItem;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.db.DBAnswerChoiceDao;
import com.wbmd.qxcalculator.model.db.DBCalculatorDao;
import com.wbmd.qxcalculator.model.db.DBLinkedCalculatorItemDao;
import com.wbmd.qxcalculator.model.db.DBQuestionDao;
import com.wbmd.qxcalculator.model.db.DBUnitDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBQuestion {
    public static final String TAG = DBQuestion.class.getSimpleName();
    private Boolean allowNegativeAnswer;
    private List<DBAnswerChoice> answerChoices;
    private Long calculatorId;
    private String category;
    private transient DaoSession daoSession;
    private Long id;
    private String identifier;
    private Double initialValue;
    private String lastUsedUnits;
    private List<DBLinkedCalculatorItem> linkedCalculatorItems;
    private String linkedCalculatorTitle;
    private String moreInfoSource;
    private String moreInformation;
    private transient DBQuestionDao myDao;
    private Long orderedId;
    private Integer position;
    private String sectionName;
    private String title;
    private String type;
    private List<DBUnit> units;

    public DBQuestion() {
    }

    public DBQuestion(Long l) {
        this.id = l;
    }

    public DBQuestion(Long l, String str, Long l2, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, Double d, Boolean bool, Long l3) {
        this.id = l;
        this.identifier = str;
        this.orderedId = l2;
        this.position = num;
        this.sectionName = str2;
        this.title = str3;
        this.type = str4;
        this.category = str5;
        this.moreInformation = str6;
        this.moreInfoSource = str7;
        this.linkedCalculatorTitle = str8;
        this.lastUsedUnits = str9;
        this.initialValue = d;
        this.allowNegativeAnswer = bool;
        this.calculatorId = l3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBQuestionDao() : null;
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

    public String getSectionName() {
        return this.sectionName;
    }

    public void setSectionName(String str) {
        this.sectionName = str;
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

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public String getMoreInformation() {
        return this.moreInformation;
    }

    public void setMoreInformation(String str) {
        this.moreInformation = str;
    }

    public String getMoreInfoSource() {
        return this.moreInfoSource;
    }

    public void setMoreInfoSource(String str) {
        this.moreInfoSource = str;
    }

    public String getLinkedCalculatorTitle() {
        return this.linkedCalculatorTitle;
    }

    public void setLinkedCalculatorTitle(String str) {
        this.linkedCalculatorTitle = str;
    }

    public String getLastUsedUnits() {
        return this.lastUsedUnits;
    }

    public void setLastUsedUnits(String str) {
        this.lastUsedUnits = str;
    }

    public Double getInitialValue() {
        return this.initialValue;
    }

    public void setInitialValue(Double d) {
        this.initialValue = d;
    }

    public Boolean getAllowNegativeAnswer() {
        return this.allowNegativeAnswer;
    }

    public void setAllowNegativeAnswer(Boolean bool) {
        this.allowNegativeAnswer = bool;
    }

    public Long getCalculatorId() {
        return this.calculatorId;
    }

    public void setCalculatorId(Long l) {
        this.calculatorId = l;
    }

    public List<DBAnswerChoice> getAnswerChoices() {
        if (this.answerChoices == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBAnswerChoice> _queryDBQuestion_AnswerChoices = daoSession2.getDBAnswerChoiceDao()._queryDBQuestion_AnswerChoices(this.id);
                synchronized (this) {
                    if (this.answerChoices == null) {
                        this.answerChoices = _queryDBQuestion_AnswerChoices;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.answerChoices;
    }

    public synchronized void resetAnswerChoices() {
        this.answerChoices = null;
    }

    public List<DBUnit> getUnits() {
        if (this.units == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBUnit> _queryDBQuestion_Units = daoSession2.getDBUnitDao()._queryDBQuestion_Units(this.id);
                synchronized (this) {
                    if (this.units == null) {
                        this.units = _queryDBQuestion_Units;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.units;
    }

    public synchronized void resetUnits() {
        this.units = null;
    }

    public List<DBLinkedCalculatorItem> getLinkedCalculatorItems() {
        if (this.linkedCalculatorItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBLinkedCalculatorItem> _queryDBQuestion_LinkedCalculatorItems = daoSession2.getDBLinkedCalculatorItemDao()._queryDBQuestion_LinkedCalculatorItems(this.id);
                synchronized (this) {
                    if (this.linkedCalculatorItems == null) {
                        this.linkedCalculatorItems = _queryDBQuestion_LinkedCalculatorItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.linkedCalculatorItems;
    }

    public synchronized void resetLinkedCalculatorItems() {
        this.linkedCalculatorItems = null;
    }

    public void delete() {
        DBQuestionDao dBQuestionDao = this.myDao;
        if (dBQuestionDao != null) {
            dBQuestionDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBQuestionDao dBQuestionDao = this.myDao;
        if (dBQuestionDao != null) {
            dBQuestionDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBQuestionDao dBQuestionDao = this.myDao;
        if (dBQuestionDao != null) {
            dBQuestionDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void deleteUnusedQuestions(DaoSession daoSession2) {
        List list = daoSession2.getDBQuestionDao().queryBuilder().where(DBQuestionDao.Properties.CalculatorId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBQuestion: " + list.size());
        deleteQuestions(daoSession2, list);
    }

    public static void deleteQuestions(DaoSession daoSession2, List<DBQuestion> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(list.size());
            for (DBQuestion next : list) {
                arrayList2.add(next.getId());
                next.resetAnswerChoices();
                next.resetUnits();
                next.resetLinkedCalculatorItems();
                if (next.getCalculatorId() != null) {
                    arrayList.add(next.getCalculatorId());
                }
            }
            List<DBAnswerChoice> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBAnswerChoiceDao(), DBAnswerChoiceDao.Properties.QuestionId, arrayList2);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBAnswerChoice questionId : allWithPropertyInData) {
                    questionId.setQuestionId((Long) null);
                }
                DBAnswerChoice.deleteAnswerChoices(daoSession2, allWithPropertyInData);
            }
            List<DBUnit> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBUnitDao(), DBUnitDao.Properties.QuestionId, arrayList2);
            if (!allWithPropertyInData2.isEmpty()) {
                for (DBUnit questionId2 : allWithPropertyInData2) {
                    questionId2.setQuestionId((Long) null);
                }
                DBUnit.deleteUnits(daoSession2, allWithPropertyInData2);
            }
            List<DBLinkedCalculatorItem> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBLinkedCalculatorItemDao(), DBLinkedCalculatorItemDao.Properties.QuestionId, arrayList2);
            if (!allWithPropertyInData3.isEmpty()) {
                for (DBLinkedCalculatorItem questionId3 : allWithPropertyInData3) {
                    questionId3.setQuestionId((Long) null);
                }
                DBLinkedCalculatorItem.deleteLinkedCalculatorItems(daoSession2, allWithPropertyInData3);
            }
            if (!arrayList.isEmpty()) {
                List<DBCalculator> allWithPropertyInData4 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBCalculatorDao(), DBCalculatorDao.Properties.Id, arrayList);
                if (!allWithPropertyInData4.isEmpty()) {
                    for (DBCalculator resetQuestions : allWithPropertyInData4) {
                        resetQuestions.resetQuestions();
                    }
                }
            }
            daoSession2.getDBQuestionDao().deleteInTx(list);
        }
    }

    public static void setLastUsedUnits(DaoSession daoSession2, Question question, String str) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(question.identifier);
        List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBQuestionDao(), DBQuestionDao.Properties.Identifier, arrayList);
        if (!allWithPropertyInData.isEmpty()) {
            DBQuestion dBQuestion = (DBQuestion) allWithPropertyInData.get(0);
            dBQuestion.setLastUsedUnits(str);
            dBQuestion.update();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBQuestion insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.calculator.Question r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBQuestion> r0 = com.wbmd.qxcalculator.model.db.DBQuestion.class
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
            com.wbmd.qxcalculator.model.db.DBQuestion r1 = (com.wbmd.qxcalculator.model.db.DBQuestion) r1     // Catch:{ all -> 0x0026 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBQuestion.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.calculator.Question):com.wbmd.qxcalculator.model.db.DBQuestion");
    }

    public static synchronized List<DBQuestion> insertAndRetrieveDbEntities(DaoSession daoSession2, List<Question> list) {
        Iterator<Unit> it;
        List list2;
        List list3;
        Iterator it2;
        Iterator<AnswerChoice> it3;
        DaoSession daoSession3 = daoSession2;
        synchronized (DBQuestion.class) {
            if (daoSession3 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            for (Question next : list) {
                arrayList2.add(next.identifier);
                if (next.answerChoices != null) {
                    arrayList3.addAll(next.answerChoices);
                }
                if (next.units != null) {
                    arrayList4.addAll(next.units);
                }
                if (next.linkedCalculatorItems != null) {
                    arrayList5.addAll(next.linkedCalculatorItems);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBQuestionDao(), DBQuestionDao.Properties.Identifier, arrayList2);
            ArrayList arrayList6 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList7 = new ArrayList();
            if (arrayList3.size() > 0) {
                arrayList7 = DBAnswerChoice.insertAndRetrieveDbEntities(daoSession3, arrayList3);
            }
            List arrayList8 = new ArrayList();
            if (arrayList4.size() > 0) {
                arrayList8 = DBUnit.insertAndRetrieveDbEntities(daoSession3, arrayList4);
            }
            List arrayList9 = new ArrayList();
            if (arrayList5.size() > 0) {
                arrayList9 = DBLinkedCalculatorItem.insertAndRetrieveDbEntities(daoSession3, arrayList5);
            }
            Iterator<Question> it4 = list.iterator();
            while (true) {
                DBQuestion dBQuestion = null;
                if (!it4.hasNext()) {
                    break;
                }
                Question next2 = it4.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBQuestion = (DBQuestion) linkedHashMap.get(next2);
                }
                if (dBQuestion == null) {
                    Iterator it5 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it5.hasNext()) {
                            break;
                        }
                        DBQuestion dBQuestion2 = (DBQuestion) it5.next();
                        if (dBQuestion2.getIdentifier().equals(next2.identifier)) {
                            dBQuestion = dBQuestion2;
                            break;
                        }
                    }
                }
                if (dBQuestion == null) {
                    dBQuestion = new DBQuestion();
                    arrayList6.add(dBQuestion);
                }
                dBQuestion.setIdentifier(next2.identifier);
                dBQuestion.setOrderedId(next2.orderedId);
                dBQuestion.setPosition(next2.position);
                dBQuestion.setSectionName(next2.sectionName);
                dBQuestion.setTitle(next2.title);
                dBQuestion.setType(next2.type);
                dBQuestion.setMoreInformation(next2.moreInformation);
                dBQuestion.setMoreInfoSource(next2.moreInfoSource);
                dBQuestion.setInitialValue(next2.initialValue);
                dBQuestion.setLinkedCalculatorTitle(next2.linkedCalculatorTitle);
                dBQuestion.setAllowNegativeAnswer(next2.allowNegativeAnswer);
                linkedHashMap.put(next2, dBQuestion);
            }
            if (arrayList6.size() > 0) {
                daoSession2.getDBQuestionDao().insertInTx(arrayList6);
            }
            ArrayList arrayList10 = new ArrayList(linkedHashMap.size());
            for (DBQuestion id2 : linkedHashMap.values()) {
                arrayList10.add(id2.getId());
            }
            List<DBAnswerChoice> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBAnswerChoiceDao(), DBAnswerChoiceDao.Properties.QuestionId, arrayList10);
            for (DBAnswerChoice questionId : allWithPropertyInData2) {
                questionId.setQuestionId((Long) null);
            }
            List<DBUnit> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBUnitDao(), DBUnitDao.Properties.QuestionId, arrayList10);
            for (DBUnit questionId2 : allWithPropertyInData3) {
                questionId2.setQuestionId((Long) null);
            }
            List<DBLinkedCalculatorItem> allWithPropertyInData4 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBLinkedCalculatorItemDao(), DBLinkedCalculatorItemDao.Properties.QuestionId, arrayList10);
            for (DBLinkedCalculatorItem questionId3 : allWithPropertyInData4) {
                questionId3.setQuestionId((Long) null);
            }
            ArrayList arrayList11 = new ArrayList(arrayList7.size());
            ArrayList arrayList12 = new ArrayList(arrayList8.size());
            ArrayList arrayList13 = new ArrayList(arrayList9.size());
            Iterator it6 = linkedHashMap.entrySet().iterator();
            while (it6.hasNext()) {
                Map.Entry entry = (Map.Entry) it6.next();
                Question question = (Question) entry.getKey();
                DBQuestion dBQuestion3 = (DBQuestion) entry.getValue();
                if (question.answerChoices != null && !question.answerChoices.isEmpty()) {
                    Iterator<AnswerChoice> it7 = question.answerChoices.iterator();
                    while (it7.hasNext()) {
                        AnswerChoice next3 = it7.next();
                        Iterator it8 = arrayList7.iterator();
                        while (true) {
                            if (!it8.hasNext()) {
                                list3 = arrayList7;
                                it2 = it6;
                                it3 = it7;
                                break;
                            }
                            list3 = arrayList7;
                            DBAnswerChoice dBAnswerChoice = (DBAnswerChoice) it8.next();
                            it2 = it6;
                            it3 = it7;
                            if (dBAnswerChoice.getIdentifier().equals(next3.identifier)) {
                                dBAnswerChoice.setQuestionId(dBQuestion3.getId());
                                arrayList11.add(dBAnswerChoice);
                                break;
                            }
                            it6 = it2;
                            it7 = it3;
                            arrayList7 = list3;
                        }
                        DaoSession daoSession4 = daoSession2;
                        it6 = it2;
                        it7 = it3;
                        arrayList7 = list3;
                    }
                }
                List list4 = arrayList7;
                Iterator it9 = it6;
                dBQuestion3.resetAnswerChoices();
                if (question.units != null && !question.units.isEmpty()) {
                    Iterator<Unit> it10 = question.units.iterator();
                    while (it10.hasNext()) {
                        Unit next4 = it10.next();
                        Iterator it11 = arrayList8.iterator();
                        while (true) {
                            if (!it11.hasNext()) {
                                it = it10;
                                list2 = arrayList8;
                                break;
                            }
                            DBUnit dBUnit = (DBUnit) it11.next();
                            it = it10;
                            list2 = arrayList8;
                            if (dBUnit.getIdentifier().equals(next4.identifier)) {
                                dBUnit.setQuestionId(dBQuestion3.getId());
                                arrayList12.add(dBUnit);
                                break;
                            }
                            it10 = it;
                            arrayList8 = list2;
                        }
                        it10 = it;
                        arrayList8 = list2;
                    }
                }
                List list5 = arrayList8;
                dBQuestion3.resetUnits();
                if (question.linkedCalculatorItems != null && !question.linkedCalculatorItems.isEmpty()) {
                    for (LinkedCalculatorItem next5 : question.linkedCalculatorItems) {
                        Iterator it12 = arrayList9.iterator();
                        while (true) {
                            if (!it12.hasNext()) {
                                break;
                            }
                            DBLinkedCalculatorItem dBLinkedCalculatorItem = (DBLinkedCalculatorItem) it12.next();
                            if (dBLinkedCalculatorItem.getIdentifier().equals(next5.identifier)) {
                                dBLinkedCalculatorItem.setQuestionId(dBQuestion3.getId());
                                arrayList13.add(dBLinkedCalculatorItem);
                                break;
                            }
                        }
                    }
                }
                dBQuestion3.resetLinkedCalculatorItems();
                DaoSession daoSession5 = daoSession2;
                it6 = it9;
                arrayList8 = list5;
                arrayList7 = list4;
            }
            ArrayList arrayList14 = new ArrayList(allWithPropertyInData2.size());
            ArrayList arrayList15 = new ArrayList(allWithPropertyInData3.size());
            ArrayList arrayList16 = new ArrayList(allWithPropertyInData4.size());
            for (DBAnswerChoice dBAnswerChoice2 : allWithPropertyInData2) {
                if (dBAnswerChoice2.getQuestionId() == null) {
                    arrayList14.add(dBAnswerChoice2);
                }
            }
            for (DBUnit dBUnit2 : allWithPropertyInData3) {
                if (dBUnit2.getQuestionId() == null) {
                    arrayList15.add(dBUnit2);
                }
            }
            for (DBLinkedCalculatorItem dBLinkedCalculatorItem2 : allWithPropertyInData4) {
                if (dBLinkedCalculatorItem2.getQuestionId() == null) {
                    arrayList16.add(dBLinkedCalculatorItem2);
                }
            }
            if (!arrayList14.isEmpty()) {
                daoSession2.getDBAnswerChoiceDao().deleteInTx(arrayList14);
            }
            if (!arrayList15.isEmpty()) {
                daoSession2.getDBUnitDao().deleteInTx(arrayList15);
            }
            if (!arrayList16.isEmpty()) {
                daoSession2.getDBLinkedCalculatorItemDao().deleteInTx(arrayList16);
            }
            if (!arrayList11.isEmpty()) {
                daoSession2.getDBAnswerChoiceDao().updateInTx(arrayList11);
            }
            if (!arrayList12.isEmpty()) {
                daoSession2.getDBUnitDao().updateInTx(arrayList12);
            }
            if (!arrayList13.isEmpty()) {
                daoSession2.getDBLinkedCalculatorItemDao().updateInTx(arrayList13);
            }
            ArrayList arrayList17 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBQuestionDao().updateInTx(arrayList17);
            return arrayList17;
        }
    }
}
