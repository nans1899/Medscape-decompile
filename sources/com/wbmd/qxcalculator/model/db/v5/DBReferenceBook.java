package com.wbmd.qxcalculator.model.db.v5;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBReferenceBook {
    private transient DaoSession daoSession;
    private Long id;
    private String identifier;
    private transient DBReferenceBookDao myDao;
    private List<DBReferenceBookSection> referenceBookSections;
    private String titlePageBackgroundColor;
    private String titlePageSource;

    public DBReferenceBook() {
    }

    public DBReferenceBook(Long l) {
        this.id = l;
    }

    public DBReferenceBook(Long l, String str, String str2, String str3) {
        this.id = l;
        this.identifier = str;
        this.titlePageSource = str2;
        this.titlePageBackgroundColor = str3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBReferenceBookDao() : null;
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

    public String getTitlePageSource() {
        return this.titlePageSource;
    }

    public void setTitlePageSource(String str) {
        this.titlePageSource = str;
    }

    public String getTitlePageBackgroundColor() {
        return this.titlePageBackgroundColor;
    }

    public void setTitlePageBackgroundColor(String str) {
        this.titlePageBackgroundColor = str;
    }

    public List<DBReferenceBookSection> getReferenceBookSections() {
        if (this.referenceBookSections == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBReferenceBookSection> _queryDBReferenceBook_ReferenceBookSections = daoSession2.getDBReferenceBookSectionDao()._queryDBReferenceBook_ReferenceBookSections(this.id);
                synchronized (this) {
                    if (this.referenceBookSections == null) {
                        this.referenceBookSections = _queryDBReferenceBook_ReferenceBookSections;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.referenceBookSections;
    }

    public synchronized void resetReferenceBookSections() {
        this.referenceBookSections = null;
    }

    public void delete() {
        DBReferenceBookDao dBReferenceBookDao = this.myDao;
        if (dBReferenceBookDao != null) {
            dBReferenceBookDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBReferenceBookDao dBReferenceBookDao = this.myDao;
        if (dBReferenceBookDao != null) {
            dBReferenceBookDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBReferenceBookDao dBReferenceBookDao = this.myDao;
        if (dBReferenceBookDao != null) {
            dBReferenceBookDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
