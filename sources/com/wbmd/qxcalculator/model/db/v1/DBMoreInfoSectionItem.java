package com.wbmd.qxcalculator.model.db.v1;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBMoreInfoSectionItem {
    private String accentColor;
    private String backgroundColor;
    private String color;
    private transient DaoSession daoSession;
    private String footer;
    private Long id;
    private String identifier;
    private String leftImage;
    private Long moreInfoSectionId;
    private Long moreInfoSectionItemId;
    private List<DBMoreInfoSectionItem> moreInfoSectionItems;
    private transient DBMoreInfoSectionItemDao myDao;
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

    public DBMoreInfoSectionItem() {
    }

    public DBMoreInfoSectionItem(Long l) {
        this.id = l;
    }

    public DBMoreInfoSectionItem(Long l, String str, Integer num, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, Long l2, Long l3) {
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
        this.moreInfoSectionId = l2;
        this.moreInfoSectionItemId = l3;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBMoreInfoSectionItemDao() : null;
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

    public Long getMoreInfoSectionId() {
        return this.moreInfoSectionId;
    }

    public void setMoreInfoSectionId(Long l) {
        this.moreInfoSectionId = l;
    }

    public Long getMoreInfoSectionItemId() {
        return this.moreInfoSectionItemId;
    }

    public void setMoreInfoSectionItemId(Long l) {
        this.moreInfoSectionItemId = l;
    }

    public List<DBMoreInfoSectionItem> getMoreInfoSectionItems() {
        if (this.moreInfoSectionItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBMoreInfoSectionItem> _queryDBMoreInfoSectionItem_MoreInfoSectionItems = daoSession2.getDBMoreInfoSectionItemDao()._queryDBMoreInfoSectionItem_MoreInfoSectionItems(this.id);
                synchronized (this) {
                    if (this.moreInfoSectionItems == null) {
                        this.moreInfoSectionItems = _queryDBMoreInfoSectionItem_MoreInfoSectionItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.moreInfoSectionItems;
    }

    public synchronized void resetMoreInfoSectionItems() {
        this.moreInfoSectionItems = null;
    }

    public void delete() {
        DBMoreInfoSectionItemDao dBMoreInfoSectionItemDao = this.myDao;
        if (dBMoreInfoSectionItemDao != null) {
            dBMoreInfoSectionItemDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBMoreInfoSectionItemDao dBMoreInfoSectionItemDao = this.myDao;
        if (dBMoreInfoSectionItemDao != null) {
            dBMoreInfoSectionItemDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBMoreInfoSectionItemDao dBMoreInfoSectionItemDao = this.myDao;
        if (dBMoreInfoSectionItemDao != null) {
            dBMoreInfoSectionItemDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
