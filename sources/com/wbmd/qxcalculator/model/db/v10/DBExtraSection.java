package com.wbmd.qxcalculator.model.db.v10;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBExtraSection {
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
}
