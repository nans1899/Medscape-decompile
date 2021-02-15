package com.wbmd.qxcalculator.model.db.v4;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBReferenceBookSectionItem {
    private String accentColor;
    private String color;
    private transient DaoSession daoSession;
    private Long id;
    private String identifier;
    private transient DBReferenceBookSectionItemDao myDao;
    private Long position;
    private Long referenceBookSectionId;
    private Long referenceBookSectionItemId;
    private List<DBReferenceBookSectionItem> referenceBookSubSectionItems;
    private String shareLink;
    private String source;
    private String sourceId;
    private String subTitle;
    private String title;

    public DBReferenceBookSectionItem() {
    }

    public DBReferenceBookSectionItem(Long l) {
        this.id = l;
    }

    public DBReferenceBookSectionItem(Long l, String str, String str2, String str3, String str4, String str5, String str6, String str7, Long l2, String str8, Long l3, Long l4) {
        this.id = l;
        this.identifier = str;
        this.title = str2;
        this.subTitle = str3;
        this.color = str4;
        this.accentColor = str5;
        this.source = str6;
        this.sourceId = str7;
        this.position = l2;
        this.shareLink = str8;
        this.referenceBookSectionId = l3;
        this.referenceBookSectionItemId = l4;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBReferenceBookSectionItemDao() : null;
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

    public String getColor() {
        return this.color;
    }

    public void setColor(String str) {
        this.color = str;
    }

    public String getAccentColor() {
        return this.accentColor;
    }

    public void setAccentColor(String str) {
        this.accentColor = str;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public void setSourceId(String str) {
        this.sourceId = str;
    }

    public Long getPosition() {
        return this.position;
    }

    public void setPosition(Long l) {
        this.position = l;
    }

    public String getShareLink() {
        return this.shareLink;
    }

    public void setShareLink(String str) {
        this.shareLink = str;
    }

    public Long getReferenceBookSectionId() {
        return this.referenceBookSectionId;
    }

    public void setReferenceBookSectionId(Long l) {
        this.referenceBookSectionId = l;
    }

    public Long getReferenceBookSectionItemId() {
        return this.referenceBookSectionItemId;
    }

    public void setReferenceBookSectionItemId(Long l) {
        this.referenceBookSectionItemId = l;
    }

    public List<DBReferenceBookSectionItem> getReferenceBookSubSectionItems() {
        if (this.referenceBookSubSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBReferenceBookSectionItem> _queryDBReferenceBookSectionItem_ReferenceBookSubSectionItems = daoSession2.getDBReferenceBookSectionItemDao()._queryDBReferenceBookSectionItem_ReferenceBookSubSectionItems(this.id);
                synchronized (this) {
                    if (this.referenceBookSubSectionItems == null) {
                        this.referenceBookSubSectionItems = _queryDBReferenceBookSectionItem_ReferenceBookSubSectionItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.referenceBookSubSectionItems;
    }

    public synchronized void resetReferenceBookSubSectionItems() {
        this.referenceBookSubSectionItems = null;
    }

    public void delete() {
        DBReferenceBookSectionItemDao dBReferenceBookSectionItemDao = this.myDao;
        if (dBReferenceBookSectionItemDao != null) {
            dBReferenceBookSectionItemDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBReferenceBookSectionItemDao dBReferenceBookSectionItemDao = this.myDao;
        if (dBReferenceBookSectionItemDao != null) {
            dBReferenceBookSectionItemDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBReferenceBookSectionItemDao dBReferenceBookSectionItemDao = this.myDao;
        if (dBReferenceBookSectionItemDao != null) {
            dBReferenceBookSectionItemDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
