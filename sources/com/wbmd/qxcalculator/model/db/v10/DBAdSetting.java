package com.wbmd.qxcalculator.model.db.v10;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBAdSetting {
    private List<DBAdTargetingParameter> adTargetingParameters;
    private String adUnitId;
    private transient DaoSession daoSession;
    private Long id;
    private String identifier;
    private transient DBAdSettingDao myDao;
    private String type;

    public DBAdSetting() {
    }

    public DBAdSetting(Long l) {
        this.id = l;
    }

    public DBAdSetting(Long l, String str, String str2, String str3) {
        this.id = l;
        this.identifier = str;
        this.type = str2;
        this.adUnitId = str3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBAdSettingDao() : null;
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

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getAdUnitId() {
        return this.adUnitId;
    }

    public void setAdUnitId(String str) {
        this.adUnitId = str;
    }

    public List<DBAdTargetingParameter> getAdTargetingParameters() {
        if (this.adTargetingParameters == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBAdTargetingParameter> _queryDBAdSetting_AdTargetingParameters = daoSession2.getDBAdTargetingParameterDao()._queryDBAdSetting_AdTargetingParameters(this.id);
                synchronized (this) {
                    if (this.adTargetingParameters == null) {
                        this.adTargetingParameters = _queryDBAdSetting_AdTargetingParameters;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.adTargetingParameters;
    }

    public synchronized void resetAdTargetingParameters() {
        this.adTargetingParameters = null;
    }

    public void delete() {
        DBAdSettingDao dBAdSettingDao = this.myDao;
        if (dBAdSettingDao != null) {
            dBAdSettingDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBAdSettingDao dBAdSettingDao = this.myDao;
        if (dBAdSettingDao != null) {
            dBAdSettingDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBAdSettingDao dBAdSettingDao = this.myDao;
        if (dBAdSettingDao != null) {
            dBAdSettingDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
