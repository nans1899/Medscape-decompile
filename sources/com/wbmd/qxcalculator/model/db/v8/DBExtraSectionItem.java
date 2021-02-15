package com.wbmd.qxcalculator.model.db.v8;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBExtraSectionItem {
    private String accentColor;
    private String backgroundColor;
    private String color;
    private String conditionFormula;
    private transient DaoSession daoSession;
    private Long extraSectionId;
    private Long extraSectionItemId;
    private List<DBExtraSectionItem> extraSectionItems;
    private String footer;
    private Boolean hideWhenNoResults;
    private Long id;
    private String identifier;
    private String leftImage;
    private transient DBExtraSectionItemDao myDao;
    private Integer position;
    private String rightImage;
    private String source;
    private String subTitle;
    private String title;
    private String trackerCategory;
    private String trackerEvent;
    private String trackerId;
    private String trackerLabel;
    private String type;

    public DBExtraSectionItem() {
    }

    public DBExtraSectionItem(Long l) {
        this.id = l;
    }

    public DBExtraSectionItem(Long l, String str, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, Boolean bool, String str16, Long l2, Long l3) {
        this.id = l;
        this.identifier = str;
        this.position = num;
        this.title = str2;
        this.subTitle = str3;
        this.footer = str4;
        this.type = str5;
        this.source = str6;
        this.leftImage = str7;
        this.rightImage = str8;
        this.backgroundColor = str9;
        this.color = str10;
        this.accentColor = str11;
        this.trackerId = str12;
        this.trackerCategory = str13;
        this.trackerEvent = str14;
        this.trackerLabel = str15;
        this.hideWhenNoResults = bool;
        this.conditionFormula = str16;
        this.extraSectionId = l2;
        this.extraSectionItemId = l3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBExtraSectionItemDao() : null;
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

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer num) {
        this.position = num;
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

    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String str) {
        this.footer = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getLeftImage() {
        return this.leftImage;
    }

    public void setLeftImage(String str) {
        this.leftImage = str;
    }

    public String getRightImage() {
        return this.rightImage;
    }

    public void setRightImage(String str) {
        this.rightImage = str;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
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

    public String getTrackerId() {
        return this.trackerId;
    }

    public void setTrackerId(String str) {
        this.trackerId = str;
    }

    public String getTrackerCategory() {
        return this.trackerCategory;
    }

    public void setTrackerCategory(String str) {
        this.trackerCategory = str;
    }

    public String getTrackerEvent() {
        return this.trackerEvent;
    }

    public void setTrackerEvent(String str) {
        this.trackerEvent = str;
    }

    public String getTrackerLabel() {
        return this.trackerLabel;
    }

    public void setTrackerLabel(String str) {
        this.trackerLabel = str;
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

    public Long getExtraSectionId() {
        return this.extraSectionId;
    }

    public void setExtraSectionId(Long l) {
        this.extraSectionId = l;
    }

    public Long getExtraSectionItemId() {
        return this.extraSectionItemId;
    }

    public void setExtraSectionItemId(Long l) {
        this.extraSectionItemId = l;
    }

    public List<DBExtraSectionItem> getExtraSectionItems() {
        if (this.extraSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBExtraSectionItem> _queryDBExtraSectionItem_ExtraSectionItems = daoSession2.getDBExtraSectionItemDao()._queryDBExtraSectionItem_ExtraSectionItems(this.id);
                synchronized (this) {
                    if (this.extraSectionItems == null) {
                        this.extraSectionItems = _queryDBExtraSectionItem_ExtraSectionItems;
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
        DBExtraSectionItemDao dBExtraSectionItemDao = this.myDao;
        if (dBExtraSectionItemDao != null) {
            dBExtraSectionItemDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBExtraSectionItemDao dBExtraSectionItemDao = this.myDao;
        if (dBExtraSectionItemDao != null) {
            dBExtraSectionItemDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBExtraSectionItemDao dBExtraSectionItemDao = this.myDao;
        if (dBExtraSectionItemDao != null) {
            dBExtraSectionItemDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
