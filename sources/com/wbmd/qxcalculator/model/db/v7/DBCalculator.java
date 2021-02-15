package com.wbmd.qxcalculator.model.db.v7;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBCalculator {
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
}
