package com.wbmd.qxcalculator.model.db.v1;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBDefinition {
    private transient DaoSession daoSession;
    private List<DBDefinitionSection> definitionSections;
    private Long id;
    private String identifier;
    private transient DBDefinitionDao myDao;

    public DBDefinition() {
    }

    public DBDefinition(Long l) {
        this.id = l;
    }

    public DBDefinition(Long l, String str) {
        this.id = l;
        this.identifier = str;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBDefinitionDao() : null;
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

    public List<DBDefinitionSection> getDefinitionSections() {
        if (this.definitionSections == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBDefinitionSection> _queryDBDefinition_DefinitionSections = daoSession2.getDBDefinitionSectionDao()._queryDBDefinition_DefinitionSections(this.id);
                synchronized (this) {
                    if (this.definitionSections == null) {
                        this.definitionSections = _queryDBDefinition_DefinitionSections;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.definitionSections;
    }

    public synchronized void resetDefinitionSections() {
        this.definitionSections = null;
    }

    public void delete() {
        DBDefinitionDao dBDefinitionDao = this.myDao;
        if (dBDefinitionDao != null) {
            dBDefinitionDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBDefinitionDao dBDefinitionDao = this.myDao;
        if (dBDefinitionDao != null) {
            dBDefinitionDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBDefinitionDao dBDefinitionDao = this.myDao;
        if (dBDefinitionDao != null) {
            dBDefinitionDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
