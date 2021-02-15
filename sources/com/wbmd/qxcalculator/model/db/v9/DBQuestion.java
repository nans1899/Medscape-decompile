package com.wbmd.qxcalculator.model.db.v9;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBQuestion {
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
}
