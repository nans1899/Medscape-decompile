package com.wbmd.qxcalculator.model.db.v6;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBCategory {
    private Long categoryId;
    private Long contentItemCategoryCount;
    private Long contentItemId;
    private String contentSpecificIdentifier;
    private transient DaoSession daoSession;
    private Long id;
    private String identifier;
    private Integer itemWeight;
    private transient DBCategoryDao myDao;
    private String name;
    private List<DBCategory> subCategories;
    private Integer weight;

    public DBCategory() {
    }

    public DBCategory(Long l) {
        this.id = l;
    }

    public DBCategory(Long l, String str, String str2, String str3, Integer num, Integer num2, Long l2, Long l3, Long l4) {
        this.id = l;
        this.contentSpecificIdentifier = str;
        this.identifier = str2;
        this.name = str3;
        this.weight = num;
        this.itemWeight = num2;
        this.contentItemCategoryCount = l2;
        this.contentItemId = l3;
        this.categoryId = l4;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBCategoryDao() : null;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getContentSpecificIdentifier() {
        return this.contentSpecificIdentifier;
    }

    public void setContentSpecificIdentifier(String str) {
        this.contentSpecificIdentifier = str;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer num) {
        this.weight = num;
    }

    public Integer getItemWeight() {
        return this.itemWeight;
    }

    public void setItemWeight(Integer num) {
        this.itemWeight = num;
    }

    public Long getContentItemCategoryCount() {
        return this.contentItemCategoryCount;
    }

    public void setContentItemCategoryCount(Long l) {
        this.contentItemCategoryCount = l;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long l) {
        this.categoryId = l;
    }

    public List<DBCategory> getSubCategories() {
        if (this.subCategories == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBCategory> _queryDBCategory_SubCategories = daoSession2.getDBCategoryDao()._queryDBCategory_SubCategories(this.id);
                synchronized (this) {
                    if (this.subCategories == null) {
                        this.subCategories = _queryDBCategory_SubCategories;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.subCategories;
    }

    public synchronized void resetSubCategories() {
        this.subCategories = null;
    }

    public void delete() {
        DBCategoryDao dBCategoryDao = this.myDao;
        if (dBCategoryDao != null) {
            dBCategoryDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBCategoryDao dBCategoryDao = this.myDao;
        if (dBCategoryDao != null) {
            dBCategoryDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBCategoryDao dBCategoryDao = this.myDao;
        if (dBCategoryDao != null) {
            dBCategoryDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
