package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.db.DBCategoryDao;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBFeaturedContentAdDao;
import com.wbmd.qxcalculator.model.db.DBFilterDao;
import com.wbmd.qxcalculator.model.db.DBMoreInfoSectionDao;
import com.wbmd.qxcalculator.model.db.DBPlatformDao;
import com.wbmd.qxcalculator.model.db.DBPromotionDao;
import com.wbmd.qxcalculator.model.db.DBResourceFileDao;
import com.wbmd.qxcalculator.model.db.DBRestrictionDao;
import com.wbmd.qxcalculator.model.db.DBTagDao;
import com.wbmd.qxcalculator.model.db.DBUserDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DBContentItem {
    public static final String TAG = DBContentItem.class.getSimpleName();
    private String allSpecialty;
    private DBCalculator calculator;
    private Long calculatorId;
    private Long calculator__resolvedKey;
    private List<DBCategory> categories;
    private DBCommonFile commonFile;
    private Long commonFileId;
    private Long commonFile__resolvedKey;
    private String copiedFromContentItemId;
    private transient DaoSession daoSession;
    private Long dateAdded;
    private Integer debugType;
    private DBDefinition definition;
    private Long definitionId;
    private Long definition__resolvedKey;
    private String description;
    private Long endDate;
    private List<DBFeaturedContentAd> featuredContentAds;
    private DBFileSource fileSource;
    private Long fileSourceId;
    private Long fileSource__resolvedKey;
    private List<DBFilter> filters;
    private String footer;
    private Long id;
    private String identifier;
    private Boolean isFavorite;
    private Boolean isNewlyAdded;
    private Boolean isRecent;
    private boolean isUpdating;
    private DBItemFileZip itemFileZip;
    private Long itemFileZipId;
    private Long itemFileZip__resolvedKey;
    private Long lastModifiedDate;
    private Long lastUsedDate;
    private String leadConcept;
    private String leadSpecialty;
    private List<DBMoreInfoSection> moreInfoSections;
    private String moreInformation;
    private String moreInformationSource;
    private transient DBContentItemDao myDao;
    private String name;
    private List<WeakReference<OnUpdateListener>> onUpdateListenerWeakReferences = new ArrayList();
    public String overrideName;
    private String parentCalcName;
    private List<DBPlatform> platforms;
    public DBPromotion promotionToUse;
    private List<DBPromotion> promotions;
    private DBReferenceBook referenceBook;
    private Long referenceBookId;
    private Long referenceBook__resolvedKey;
    private Boolean requiresUpdate;
    private List<DBResourceFile> resourceFiles;
    private Boolean resourcesRequireUpdate;
    private List<DBRestriction> restrictions;
    private Boolean shouldNotifiedBecomeAvailable;
    private Boolean showAds;
    private DBSplashPage splashPage;
    private Long splashPageId;
    private Long splashPage__resolvedKey;
    private Long startDate;
    private List<DBTag> tags;
    private String trackerId;
    private String type;
    private Long userId;

    public interface OnUpdateListener {
        void isUpdatingStatusChanged(DBContentItem dBContentItem);
    }

    public DBContentItem() {
    }

    public DBContentItem(Long l) {
        this.id = l;
    }

    public DBContentItem(Long l, String str, String str2, String str3, String str4, Long l2, Long l3, String str5, Long l4, Integer num, String str6, String str7, Boolean bool, Boolean bool2, String str8, Long l5, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Long l6, String str9, Boolean bool7, String str10, String str11, String str12, Long l7, Long l8, Long l9, Long l10, Long l11, Long l12, Long l13, Long l14) {
        this.id = l;
        this.identifier = str;
        this.name = str2;
        this.description = str3;
        this.footer = str4;
        this.startDate = l2;
        this.endDate = l3;
        this.trackerId = str5;
        this.lastModifiedDate = l4;
        this.debugType = num;
        this.moreInformation = str6;
        this.moreInformationSource = str7;
        this.requiresUpdate = bool;
        this.resourcesRequireUpdate = bool2;
        this.type = str8;
        this.lastUsedDate = l5;
        this.isFavorite = bool3;
        this.isNewlyAdded = bool4;
        this.shouldNotifiedBecomeAvailable = bool5;
        this.showAds = bool6;
        this.dateAdded = l6;
        this.copiedFromContentItemId = str9;
        this.isRecent = bool7;
        this.leadSpecialty = str10;
        this.leadConcept = str11;
        this.allSpecialty = str12;
        this.userId = l7;
        this.calculatorId = l8;
        this.definitionId = l9;
        this.fileSourceId = l10;
        this.splashPageId = l11;
        this.referenceBookId = l12;
        this.itemFileZipId = l13;
        this.commonFileId = l14;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBContentItemDao() : null;
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

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getFooter() {
        return this.footer;
    }

    public void setFooter(String str) {
        this.footer = str;
    }

    public Long getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Long l) {
        this.startDate = l;
    }

    public Long getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Long l) {
        this.endDate = l;
    }

    public String getTrackerId() {
        return this.trackerId;
    }

    public void setTrackerId(String str) {
        this.trackerId = str;
    }

    public Long getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Long l) {
        this.lastModifiedDate = l;
    }

    public Integer getDebugType() {
        return this.debugType;
    }

    public void setDebugType(Integer num) {
        this.debugType = num;
    }

    public String getMoreInformation() {
        return this.moreInformation;
    }

    public void setMoreInformation(String str) {
        this.moreInformation = str;
    }

    public String getMoreInformationSource() {
        return this.moreInformationSource;
    }

    public void setMoreInformationSource(String str) {
        this.moreInformationSource = str;
    }

    public Boolean getRequiresUpdate() {
        return this.requiresUpdate;
    }

    public void setRequiresUpdate(Boolean bool) {
        this.requiresUpdate = bool;
    }

    public Boolean getResourcesRequireUpdate() {
        return this.resourcesRequireUpdate;
    }

    public void setResourcesRequireUpdate(Boolean bool) {
        this.resourcesRequireUpdate = bool;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Long getLastUsedDate() {
        return this.lastUsedDate;
    }

    public void setLastUsedDate(Long l) {
        this.lastUsedDate = l;
    }

    public Boolean getIsFavorite() {
        return this.isFavorite;
    }

    public void setIsFavorite(Boolean bool) {
        this.isFavorite = bool;
    }

    public Boolean getIsNewlyAdded() {
        return this.isNewlyAdded;
    }

    public void setIsNewlyAdded(Boolean bool) {
        this.isNewlyAdded = bool;
    }

    public Boolean getShouldNotifiedBecomeAvailable() {
        return this.shouldNotifiedBecomeAvailable;
    }

    public void setShouldNotifiedBecomeAvailable(Boolean bool) {
        this.shouldNotifiedBecomeAvailable = bool;
    }

    public Boolean getShowAds() {
        return this.showAds;
    }

    public void setShowAds(Boolean bool) {
        this.showAds = bool;
    }

    public Long getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Long l) {
        this.dateAdded = l;
    }

    public String getCopiedFromContentItemId() {
        return this.copiedFromContentItemId;
    }

    public void setCopiedFromContentItemId(String str) {
        this.copiedFromContentItemId = str;
    }

    public Boolean getIsRecent() {
        return this.isRecent;
    }

    public void setIsRecent(Boolean bool) {
        this.isRecent = bool;
    }

    public String getLeadSpecialty() {
        return this.leadSpecialty;
    }

    public void setLeadSpecialty(String str) {
        this.leadSpecialty = str;
    }

    public String getLeadConcept() {
        return this.leadConcept;
    }

    public void setLeadConcept(String str) {
        this.leadConcept = str;
    }

    public String getAllSpecialty() {
        return this.allSpecialty;
    }

    public void setAllSpecialty(String str) {
        this.allSpecialty = str;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long l) {
        this.userId = l;
    }

    public Long getCalculatorId() {
        return this.calculatorId;
    }

    public void setCalculatorId(Long l) {
        this.calculatorId = l;
    }

    public Long getDefinitionId() {
        return this.definitionId;
    }

    public void setDefinitionId(Long l) {
        this.definitionId = l;
    }

    public Long getFileSourceId() {
        return this.fileSourceId;
    }

    public void setFileSourceId(Long l) {
        this.fileSourceId = l;
    }

    public Long getSplashPageId() {
        return this.splashPageId;
    }

    public void setSplashPageId(Long l) {
        this.splashPageId = l;
    }

    public Long getReferenceBookId() {
        return this.referenceBookId;
    }

    public void setReferenceBookId(Long l) {
        this.referenceBookId = l;
    }

    public Long getItemFileZipId() {
        return this.itemFileZipId;
    }

    public void setItemFileZipId(Long l) {
        this.itemFileZipId = l;
    }

    public Long getCommonFileId() {
        return this.commonFileId;
    }

    public void setCommonFileId(Long l) {
        this.commonFileId = l;
    }

    public DBCalculator getCalculator() {
        Long l = this.calculatorId;
        Long l2 = this.calculator__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBCalculator dBCalculator = (DBCalculator) daoSession2.getDBCalculatorDao().load(l);
                synchronized (this) {
                    this.calculator = dBCalculator;
                    this.calculator__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.calculator;
    }

    public void setCalculator(DBCalculator dBCalculator) {
        Long l;
        synchronized (this) {
            this.calculator = dBCalculator;
            if (dBCalculator == null) {
                l = null;
            } else {
                l = dBCalculator.getId();
            }
            this.calculatorId = l;
            this.calculator__resolvedKey = l;
        }
    }

    public DBDefinition getDefinition() {
        Long l = this.definitionId;
        Long l2 = this.definition__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBDefinition dBDefinition = (DBDefinition) daoSession2.getDBDefinitionDao().load(l);
                synchronized (this) {
                    this.definition = dBDefinition;
                    this.definition__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.definition;
    }

    public void setDefinition(DBDefinition dBDefinition) {
        Long l;
        synchronized (this) {
            this.definition = dBDefinition;
            if (dBDefinition == null) {
                l = null;
            } else {
                l = dBDefinition.getId();
            }
            this.definitionId = l;
            this.definition__resolvedKey = l;
        }
    }

    public DBFileSource getFileSource() {
        Long l = this.fileSourceId;
        Long l2 = this.fileSource__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBFileSource dBFileSource = (DBFileSource) daoSession2.getDBFileSourceDao().load(l);
                synchronized (this) {
                    this.fileSource = dBFileSource;
                    this.fileSource__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.fileSource;
    }

    public void setFileSource(DBFileSource dBFileSource) {
        Long l;
        synchronized (this) {
            this.fileSource = dBFileSource;
            if (dBFileSource == null) {
                l = null;
            } else {
                l = dBFileSource.getId();
            }
            this.fileSourceId = l;
            this.fileSource__resolvedKey = l;
        }
    }

    public DBSplashPage getSplashPage() {
        Long l = this.splashPageId;
        Long l2 = this.splashPage__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBSplashPage dBSplashPage = (DBSplashPage) daoSession2.getDBSplashPageDao().load(l);
                synchronized (this) {
                    this.splashPage = dBSplashPage;
                    this.splashPage__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.splashPage;
    }

    public void setSplashPage(DBSplashPage dBSplashPage) {
        Long l;
        synchronized (this) {
            this.splashPage = dBSplashPage;
            if (dBSplashPage == null) {
                l = null;
            } else {
                l = dBSplashPage.getId();
            }
            this.splashPageId = l;
            this.splashPage__resolvedKey = l;
        }
    }

    public DBReferenceBook getReferenceBook() {
        Long l = this.referenceBookId;
        Long l2 = this.referenceBook__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBReferenceBook dBReferenceBook = (DBReferenceBook) daoSession2.getDBReferenceBookDao().load(l);
                synchronized (this) {
                    this.referenceBook = dBReferenceBook;
                    this.referenceBook__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.referenceBook;
    }

    public void setReferenceBook(DBReferenceBook dBReferenceBook) {
        Long l;
        synchronized (this) {
            this.referenceBook = dBReferenceBook;
            if (dBReferenceBook == null) {
                l = null;
            } else {
                l = dBReferenceBook.getId();
            }
            this.referenceBookId = l;
            this.referenceBook__resolvedKey = l;
        }
    }

    public DBItemFileZip getItemFileZip() {
        Long l = this.itemFileZipId;
        Long l2 = this.itemFileZip__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBItemFileZip dBItemFileZip = (DBItemFileZip) daoSession2.getDBItemFileZipDao().load(l);
                synchronized (this) {
                    this.itemFileZip = dBItemFileZip;
                    this.itemFileZip__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.itemFileZip;
    }

    public void setItemFileZip(DBItemFileZip dBItemFileZip) {
        Long l;
        synchronized (this) {
            this.itemFileZip = dBItemFileZip;
            if (dBItemFileZip == null) {
                l = null;
            } else {
                l = dBItemFileZip.getId();
            }
            this.itemFileZipId = l;
            this.itemFileZip__resolvedKey = l;
        }
    }

    public DBCommonFile getCommonFile() {
        Long l = this.commonFileId;
        Long l2 = this.commonFile__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBCommonFile dBCommonFile = (DBCommonFile) daoSession2.getDBCommonFileDao().load(l);
                synchronized (this) {
                    this.commonFile = dBCommonFile;
                    this.commonFile__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.commonFile;
    }

    public void setCommonFile(DBCommonFile dBCommonFile) {
        Long l;
        synchronized (this) {
            this.commonFile = dBCommonFile;
            if (dBCommonFile == null) {
                l = null;
            } else {
                l = dBCommonFile.getId();
            }
            this.commonFileId = l;
            this.commonFile__resolvedKey = l;
        }
    }

    public List<DBCategory> getCategories() {
        if (this.categories == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBCategory> _queryDBContentItem_Categories = daoSession2.getDBCategoryDao()._queryDBContentItem_Categories(this.id);
                synchronized (this) {
                    if (this.categories == null) {
                        this.categories = _queryDBContentItem_Categories;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.categories;
    }

    public synchronized void resetCategories() {
        this.categories = null;
    }

    public List<DBTag> getTags() {
        if (this.tags == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBTag> _queryDBContentItem_Tags = daoSession2.getDBTagDao()._queryDBContentItem_Tags(this.id);
                synchronized (this) {
                    if (this.tags == null) {
                        this.tags = _queryDBContentItem_Tags;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.tags;
    }

    public synchronized void resetTags() {
        this.tags = null;
    }

    public List<DBResourceFile> getResourceFiles() {
        if (this.resourceFiles == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBResourceFile> _queryDBContentItem_ResourceFiles = daoSession2.getDBResourceFileDao()._queryDBContentItem_ResourceFiles(this.id);
                synchronized (this) {
                    if (this.resourceFiles == null) {
                        this.resourceFiles = _queryDBContentItem_ResourceFiles;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.resourceFiles;
    }

    public synchronized void resetResourceFiles() {
        this.resourceFiles = null;
    }

    public List<DBPlatform> getPlatforms() {
        if (this.platforms == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBPlatform> _queryDBContentItem_Platforms = daoSession2.getDBPlatformDao()._queryDBContentItem_Platforms(this.id);
                synchronized (this) {
                    if (this.platforms == null) {
                        this.platforms = _queryDBContentItem_Platforms;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.platforms;
    }

    public synchronized void resetPlatforms() {
        this.platforms = null;
    }

    public List<DBMoreInfoSection> getMoreInfoSections() {
        if (this.moreInfoSections == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBMoreInfoSection> _queryDBContentItem_MoreInfoSections = daoSession2.getDBMoreInfoSectionDao()._queryDBContentItem_MoreInfoSections(this.id);
                synchronized (this) {
                    if (this.moreInfoSections == null) {
                        this.moreInfoSections = _queryDBContentItem_MoreInfoSections;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.moreInfoSections;
    }

    public synchronized void resetMoreInfoSections() {
        this.moreInfoSections = null;
    }

    public List<DBFilter> getFilters() {
        if (this.filters == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBFilter> _queryDBContentItem_Filters = daoSession2.getDBFilterDao()._queryDBContentItem_Filters(this.id);
                synchronized (this) {
                    if (this.filters == null) {
                        this.filters = _queryDBContentItem_Filters;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.filters;
    }

    public synchronized void resetFilters() {
        this.filters = null;
    }

    public List<DBFeaturedContentAd> getFeaturedContentAds() {
        if (this.featuredContentAds == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBFeaturedContentAd> _queryDBContentItem_FeaturedContentAds = daoSession2.getDBFeaturedContentAdDao()._queryDBContentItem_FeaturedContentAds(this.id);
                synchronized (this) {
                    if (this.featuredContentAds == null) {
                        this.featuredContentAds = _queryDBContentItem_FeaturedContentAds;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.featuredContentAds;
    }

    public synchronized void resetFeaturedContentAds() {
        this.featuredContentAds = null;
    }

    public List<DBRestriction> getRestrictions() {
        if (this.restrictions == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBRestriction> _queryDBContentItem_Restrictions = daoSession2.getDBRestrictionDao()._queryDBContentItem_Restrictions(this.id);
                synchronized (this) {
                    if (this.restrictions == null) {
                        this.restrictions = _queryDBContentItem_Restrictions;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.restrictions;
    }

    public synchronized void resetRestrictions() {
        this.restrictions = null;
    }

    public List<DBPromotion> getPromotions() {
        if (this.promotions == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBPromotion> _queryDBContentItem_Promotions = daoSession2.getDBPromotionDao()._queryDBContentItem_Promotions(this.id);
                synchronized (this) {
                    if (this.promotions == null) {
                        this.promotions = _queryDBContentItem_Promotions;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.promotions;
    }

    public synchronized void resetPromotions() {
        this.promotions = null;
    }

    public void delete() {
        DBContentItemDao dBContentItemDao = this.myDao;
        if (dBContentItemDao != null) {
            dBContentItemDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBContentItemDao dBContentItemDao = this.myDao;
        if (dBContentItemDao != null) {
            dBContentItemDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBContentItemDao dBContentItemDao = this.myDao;
        if (dBContentItemDao != null) {
            dBContentItemDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void updatePromotionToUse() {
        this.promotionToUse = null;
        if (getPromotions() != null && !getPromotions().isEmpty()) {
            for (DBPromotion next : getPromotions()) {
                if (DBFilter.shouldIncludeFilters(next.getFilters()) && DBPlatform.shouldIncludePlatforms(next.getPlatforms())) {
                    this.promotionToUse = next;
                    return;
                }
            }
        }
    }

    public static void preloadPromotionRelations(DaoSession daoSession2, List<DBContentItem> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPromotionDao(), DBPromotionDao.Properties.ContentItemId, list2);
        ArrayList<DBPromotion> arrayList = new ArrayList<>(allWithPropertyInData);
        for (DBContentItem next : list) {
            next.promotions = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBPromotion dBPromotion = (DBPromotion) it.next();
                if (dBPromotion.getContentItemId().equals(next.getId())) {
                    next.promotions.add(dBPromotion);
                    it.remove();
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(allWithPropertyInData.size());
        for (DBPromotion id2 : arrayList) {
            arrayList2.add(id2.getId());
        }
        DBPromotion.preloadFilterRelations(daoSession2, arrayList, arrayList2);
        DBPromotion.preloadPlatformRelations(daoSession2, arrayList, arrayList2);
    }

    public static void preloadFeaturedContentRelations(DaoSession daoSession2, List<DBContentItem> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFeaturedContentAdDao(), DBFeaturedContentAdDao.Properties.ContentItemId, list2);
        ArrayList<DBFeaturedContentAd> arrayList = new ArrayList<>(allWithPropertyInData);
        for (DBContentItem next : list) {
            next.featuredContentAds = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBFeaturedContentAd dBFeaturedContentAd = (DBFeaturedContentAd) it.next();
                if (dBFeaturedContentAd.getContentItemId().equals(next.getId())) {
                    next.featuredContentAds.add(dBFeaturedContentAd);
                    it.remove();
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(allWithPropertyInData.size());
        for (DBFeaturedContentAd id2 : arrayList) {
            arrayList2.add(id2.getId());
        }
        DBFeaturedContentAd.preloadFilterRelations(daoSession2, arrayList, arrayList2);
        DBFeaturedContentAd.preloadPlatformRelations(daoSession2, arrayList, arrayList2);
    }

    public static void preloadRestrictionRelations(DaoSession daoSession2, List<DBContentItem> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBRestrictionDao(), DBRestrictionDao.Properties.ContentItemId, list2);
        ArrayList<DBRestriction> arrayList = new ArrayList<>(allWithPropertyInData);
        for (DBContentItem next : list) {
            next.restrictions = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBRestriction dBRestriction = (DBRestriction) it.next();
                if (dBRestriction.getContentItemId().equals(next.getId())) {
                    next.restrictions.add(dBRestriction);
                    it.remove();
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(allWithPropertyInData.size());
        for (DBRestriction id2 : arrayList) {
            arrayList2.add(id2.getId());
        }
        DBRestriction.preloadFilterRelations(daoSession2, arrayList, arrayList2);
        DBRestriction.preloadPlatformRelations(daoSession2, arrayList, arrayList2);
    }

    public static void preloadResourceFileRelations(DaoSession daoSession2, List<DBContentItem> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBResourceFileDao(), DBResourceFileDao.Properties.ContentItemId, list2);
        for (DBContentItem next : list) {
            next.resourceFiles = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBResourceFile dBResourceFile = (DBResourceFile) it.next();
                if (dBResourceFile.getContentItemId().equals(next.getId())) {
                    next.resourceFiles.add(dBResourceFile);
                    it.remove();
                }
            }
        }
    }

    public static void preloadFilterRelations(DaoSession daoSession2, List<DBContentItem> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.ContentItemId, list2);
        for (DBContentItem next : list) {
            next.filters = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBFilter dBFilter = (DBFilter) it.next();
                if (dBFilter.getContentItemId().equals(next.getId())) {
                    next.filters.add(dBFilter);
                    it.remove();
                }
            }
        }
        DBFilter.preloadRelations(daoSession2, allWithPropertyInData);
    }

    public static void preloadPlatformRelations(DaoSession daoSession2, List<DBContentItem> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.ContentItemId, list2);
        for (DBContentItem next : list) {
            next.platforms = new ArrayList();
            Iterator<DbType> it = allWithPropertyInData.iterator();
            while (it.hasNext()) {
                DBPlatform dBPlatform = (DBPlatform) it.next();
                if (!(dBPlatform == null || dBPlatform.getContentItemId() == null || !dBPlatform.getContentItemId().equals(next.getId()))) {
                    next.platforms.add(dBPlatform);
                    it.remove();
                }
            }
        }
    }

    public static void preloadCategoryRelations(DaoSession daoSession2, List<DBContentItem> list, List<Long> list2) {
        List<DbType> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBCategoryDao(), DBCategoryDao.Properties.ContentItemId, list2);
        ArrayList<DBCategory> arrayList = new ArrayList<>(allWithPropertyInData);
        for (DBContentItem next : list) {
            next.categories = new ArrayList();
            for (DBCategory dBCategory : arrayList) {
                if (!(dBCategory == null || dBCategory.getContentItemId() == null || next == null || !dBCategory.getContentItemId().equals(next.getId()))) {
                    next.categories.add(dBCategory);
                }
            }
        }
        DBCategory.preloadSubCategoryRelations(daoSession2, allWithPropertyInData);
    }

    public static void deleteUnusedContentItems(DaoSession daoSession2) {
        List list = daoSession2.getDBContentItemDao().queryBuilder().where(DBContentItemDao.Properties.UserId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBContentItem: " + list.size());
        deleteContentItems(daoSession2, list);
    }

    public static void deleteContentItems(DaoSession daoSession2, List<DBContentItem> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList(list.size());
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            ArrayList arrayList7 = new ArrayList();
            ArrayList arrayList8 = new ArrayList();
            ArrayList arrayList9 = new ArrayList();
            for (DBContentItem next : list) {
                arrayList2.add(next.getId());
                next.resetCategories();
                next.resetTags();
                next.resetResourceFiles();
                next.resetPromotions();
                if (next.getUserId() != null) {
                    arrayList.add(next.getUserId());
                }
                if (next.getItemFileZip() != null) {
                    arrayList3.add(next.getItemFileZip());
                }
                if (next.getCalculator() != null) {
                    arrayList4.add(next.getCalculator());
                }
                if (next.getReferenceBook() != null) {
                    arrayList5.add(next.getReferenceBook());
                }
                if (next.getFileSource() != null) {
                    arrayList6.add(next.getFileSource());
                }
                if (next.getDefinition() != null) {
                    arrayList7.add(next.getDefinition());
                }
                if (next.getCommonFile() != null) {
                    arrayList8.add(next.getCommonFile());
                }
                if (next.getSplashPage() != null) {
                    arrayList9.add(next.getSplashPage());
                }
            }
            List<DBCategory> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBCategoryDao(), DBCategoryDao.Properties.ContentItemId, arrayList2);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBCategory contentItemId : allWithPropertyInData) {
                    contentItemId.setContentItemId((Long) null);
                }
                DBCategory.deleteCategories(daoSession2, allWithPropertyInData);
            }
            List<DBTag> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBTagDao(), DBTagDao.Properties.ContentItemId, arrayList2);
            if (!allWithPropertyInData2.isEmpty()) {
                for (DBTag contentItemId2 : allWithPropertyInData2) {
                    contentItemId2.setContentItemId((Long) null);
                }
                DBTag.deleteTags(daoSession2, allWithPropertyInData2);
            }
            List<DBMoreInfoSection> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBMoreInfoSectionDao(), DBMoreInfoSectionDao.Properties.ContentItemId, arrayList2);
            if (!allWithPropertyInData3.isEmpty()) {
                for (DBMoreInfoSection contentItemId3 : allWithPropertyInData3) {
                    contentItemId3.setContentItemId((Long) null);
                }
                DBMoreInfoSection.deleteMoreInfoSections(daoSession2, allWithPropertyInData3);
            }
            List<DBPlatform> allWithPropertyInData4 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPlatformDao(), DBPlatformDao.Properties.ContentItemId, arrayList2);
            if (!allWithPropertyInData4.isEmpty()) {
                for (DBPlatform contentItemId4 : allWithPropertyInData4) {
                    contentItemId4.setContentItemId((Long) null);
                }
                DBPlatform.deletePlatforms(daoSession2, allWithPropertyInData4);
            }
            List<DBFilter> allWithPropertyInData5 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.ContentItemId, arrayList2);
            if (!allWithPropertyInData5.isEmpty()) {
                for (DBFilter contentItemId5 : allWithPropertyInData5) {
                    contentItemId5.setContentItemId((Long) null);
                }
                DBFilter.deleteFilters(daoSession2, allWithPropertyInData5);
            }
            List<DBFeaturedContentAd> allWithPropertyInData6 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFeaturedContentAdDao(), DBFeaturedContentAdDao.Properties.ContentItemId, arrayList2);
            if (!allWithPropertyInData6.isEmpty()) {
                for (DBFeaturedContentAd contentItemId6 : allWithPropertyInData6) {
                    contentItemId6.setContentItemId((Long) null);
                }
                DBFeaturedContentAd.deleteFeaturedContentAds(daoSession2, allWithPropertyInData6);
            }
            List<DBRestriction> allWithPropertyInData7 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBRestrictionDao(), DBRestrictionDao.Properties.ContentItemId, arrayList2);
            if (!allWithPropertyInData7.isEmpty()) {
                for (DBRestriction contentItemId7 : allWithPropertyInData7) {
                    contentItemId7.setContentItemId((Long) null);
                }
                DBRestriction.deleteRestrictions(daoSession2, allWithPropertyInData7);
            }
            List<DBResourceFile> allWithPropertyInData8 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBResourceFileDao(), DBResourceFileDao.Properties.ContentItemId, arrayList2);
            if (!allWithPropertyInData8.isEmpty()) {
                for (DBResourceFile contentItemId8 : allWithPropertyInData8) {
                    contentItemId8.setContentItemId((Long) null);
                }
                DBResourceFile.deleteResourceFiles(daoSession2, allWithPropertyInData8);
            }
            List<DBPromotion> allWithPropertyInData9 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBPromotionDao(), DBPromotionDao.Properties.ContentItemId, arrayList2);
            if (!allWithPropertyInData9.isEmpty()) {
                for (DBPromotion contentItemId9 : allWithPropertyInData9) {
                    contentItemId9.setContentItemId((Long) null);
                }
                DBPromotion.deletePromotions(daoSession2, allWithPropertyInData9);
            }
            for (DBContentItem deleteFolderForContentItem : list) {
                FileHelper.getInstance().deleteFolderForContentItem(deleteFolderForContentItem);
            }
            DBItemFileZip.deleteItemFileZips(daoSession2, arrayList3);
            DBCalculator.deleteCalculators(daoSession2, arrayList4);
            DBReferenceBook.deleteReferenceBooks(daoSession2, arrayList5);
            DBFileSource.deleteFileSources(daoSession2, arrayList6);
            DBDefinition.deleteDefinitions(daoSession2, arrayList7);
            DBCommonFile.deleteCommonFiles(daoSession2, arrayList8);
            DBSplashPage.deleteSplashPages(daoSession2, arrayList9);
            daoSession2.getDBContentItemDao().deleteInTx(list);
            if (!arrayList.isEmpty()) {
                List<DBUser> allWithPropertyInData10 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBUserDao(), DBUserDao.Properties.Id, arrayList);
                if (!allWithPropertyInData10.isEmpty()) {
                    for (DBUser dBUser : allWithPropertyInData10) {
                        dBUser.resetContentItems();
                        dBUser.resetRecentItems();
                        dBUser.resetFavoriteItems();
                    }
                }
            }
        }
    }

    public static List<DBContentItem> getNewlyAddedContentItems(DaoSession daoSession2) {
        List<DBContentItem> list = daoSession2.getDBContentItemDao().queryBuilder().where(DBContentItemDao.Properties.IsNewlyAdded.eq(true), new WhereCondition[0]).list();
        ArrayList arrayList = new ArrayList(list.size());
        for (DBContentItem dBContentItem : list) {
            if (dBContentItem.getListType().equals(ContentItem.ContentItemListType.DEFAULT) && shouldIncludeItem(dBContentItem)) {
                arrayList.add(dBContentItem);
            }
        }
        return arrayList;
    }

    public static List<DBContentItem> getContentItemsShouldNotifyWhenBecomeAvailable(DaoSession daoSession2) {
        return daoSession2.getDBContentItemDao().queryBuilder().where(DBContentItemDao.Properties.ShouldNotifiedBecomeAvailable.eq(true), new WhereCondition[0]).list();
    }

    public static synchronized DBContentItem getContentItemForIdentifier(DaoSession daoSession2, String str) {
        synchronized (DBContentItem.class) {
            List list = daoSession2.getDBContentItemDao().queryBuilder().where(DBContentItemDao.Properties.Identifier.eq(str), new WhereCondition[0]).list();
            if (list != null) {
                if (!list.isEmpty()) {
                    DBContentItem dBContentItem = (DBContentItem) list.get(0);
                    return dBContentItem;
                }
            }
            return null;
        }
    }

    public static synchronized List<DBContentItem> getContentItemsForIdentifiers(DaoSession daoSession2, List<String> list) {
        synchronized (DBContentItem.class) {
            if (list != null) {
                if (!list.isEmpty()) {
                    List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.Identifier, list);
                    return allWithPropertyInData;
                }
            }
            return null;
        }
    }

    public static synchronized List<DBContentItem> getContentItemsForIds(DaoSession daoSession2, List<Long> list) {
        synchronized (DBContentItem.class) {
            if (list == null) {
                return null;
            }
            List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.Id, list);
            return allWithPropertyInData;
        }
    }

    public static synchronized List<DBContentItem> getContentItemsThatNeedUpdating(DaoSession daoSession2) {
        List<DBContentItem> list;
        synchronized (DBContentItem.class) {
            QueryBuilder queryBuilder = daoSession2.getDBContentItemDao().queryBuilder();
            list = queryBuilder.whereOr(queryBuilder.and(DBContentItemDao.Properties.RequiresUpdate.isNotNull(), DBContentItemDao.Properties.RequiresUpdate.eq(true), new WhereCondition[0]), queryBuilder.and(DBContentItemDao.Properties.ResourcesRequireUpdate.isNotNull(), DBContentItemDao.Properties.ResourcesRequireUpdate.eq(true), new WhereCondition[0]), new WhereCondition[0]).list();
        }
        return list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBContentItem insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.common.ContentItem r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBContentItem> r0 = com.wbmd.qxcalculator.model.db.DBContentItem.class
            monitor-enter(r0)
            r1 = 0
            if (r3 == 0) goto L_0x0029
            if (r4 != 0) goto L_0x0009
            goto L_0x0029
        L_0x0009:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0026 }
            r2.<init>()     // Catch:{ all -> 0x0026 }
            r2.add(r4)     // Catch:{ all -> 0x0026 }
            java.util.List r3 = insertAndRetrieveDbEntities(r3, r2)     // Catch:{ all -> 0x0026 }
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x0026 }
            if (r4 == 0) goto L_0x001c
            goto L_0x0024
        L_0x001c:
            r4 = 0
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x0026 }
            r1 = r3
            com.wbmd.qxcalculator.model.db.DBContentItem r1 = (com.wbmd.qxcalculator.model.db.DBContentItem) r1     // Catch:{ all -> 0x0026 }
        L_0x0024:
            monitor-exit(r0)
            return r1
        L_0x0026:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        L_0x0029:
            monitor-exit(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBContentItem.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.common.ContentItem):com.wbmd.qxcalculator.model.db.DBContentItem");
    }

    /* JADX WARNING: Removed duplicated region for block: B:124:0x02cd A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x02df A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x035e A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x038c A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0396 A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x03bd A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x03c5 A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x03ef A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x03f9 A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x041b A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0423 A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0445 A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x044d A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x046f A[Catch:{ Exception -> 0x07ce, all -> 0x0c03 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.util.List<com.wbmd.qxcalculator.model.db.DBContentItem> insertAndRetrieveDbEntities(com.wbmd.qxcalculator.model.db.DaoSession r39, java.util.List<com.wbmd.qxcalculator.model.contentItems.common.ContentItem> r40) {
        /*
            r1 = r39
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBContentItem> r2 = com.wbmd.qxcalculator.model.db.DBContentItem.class
            monitor-enter(r2)
            if (r1 == 0) goto L_0x0bfc
            if (r40 != 0) goto L_0x000b
            goto L_0x0bfc
        L_0x000b:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r3.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r4.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r5.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r6.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r7.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r8.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r9.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r10.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r11.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r12.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r13.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r14.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r15.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r1.<init>()     // Catch:{ all -> 0x0c03 }
            r16 = r1
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r1.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r17 = r40.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x005c:
            boolean r18 = r17.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r18 == 0) goto L_0x0111
            java.lang.Object r18 = r17.next()     // Catch:{ all -> 0x0c03 }
            r19 = r1
            r1 = r18
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem r1 = (com.wbmd.qxcalculator.model.contentItems.common.ContentItem) r1     // Catch:{ all -> 0x0c03 }
            r18 = r8
            java.lang.String r8 = r1.identifier     // Catch:{ all -> 0x0c03 }
            r3.add(r8)     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Category> r8 = r1.categories     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x007c
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Category> r8 = r1.categories     // Catch:{ all -> 0x0c03 }
            r4.addAll(r8)     // Catch:{ all -> 0x0c03 }
        L_0x007c:
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.ResourceFile> r8 = r1.resourceFiles     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x0085
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.ResourceFile> r8 = r1.resourceFiles     // Catch:{ all -> 0x0c03 }
            r9.addAll(r8)     // Catch:{ all -> 0x0c03 }
        L_0x0085:
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Platform> r8 = r1.platforms     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x008e
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Platform> r8 = r1.platforms     // Catch:{ all -> 0x0c03 }
            r10.addAll(r8)     // Catch:{ all -> 0x0c03 }
        L_0x008e:
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Filter> r8 = r1.filters     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x0097
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Filter> r8 = r1.filters     // Catch:{ all -> 0x0c03 }
            r11.addAll(r8)     // Catch:{ all -> 0x0c03 }
        L_0x0097:
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.FeaturedContentAd> r8 = r1.featuredContentAds     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x00a0
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.FeaturedContentAd> r8 = r1.featuredContentAds     // Catch:{ all -> 0x0c03 }
            r12.addAll(r8)     // Catch:{ all -> 0x0c03 }
        L_0x00a0:
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Restriction> r8 = r1.restrictions     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x00a9
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Restriction> r8 = r1.restrictions     // Catch:{ all -> 0x0c03 }
            r13.addAll(r8)     // Catch:{ all -> 0x0c03 }
        L_0x00a9:
            com.wbmd.qxcalculator.model.contentItems.common.ItemFileZip r8 = r1.itemFileZip     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x00b2
            com.wbmd.qxcalculator.model.contentItems.common.ItemFileZip r8 = r1.itemFileZip     // Catch:{ all -> 0x0c03 }
            r14.add(r8)     // Catch:{ all -> 0x0c03 }
        L_0x00b2:
            com.wbmd.qxcalculator.model.contentItems.calculator.Calculator r8 = r1.calculator     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x00bb
            com.wbmd.qxcalculator.model.contentItems.calculator.Calculator r8 = r1.calculator     // Catch:{ all -> 0x0c03 }
            r5.add(r8)     // Catch:{ all -> 0x0c03 }
        L_0x00bb:
            com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBook r8 = r1.referenceBook     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x00c4
            com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBook r8 = r1.referenceBook     // Catch:{ all -> 0x0c03 }
            r6.add(r8)     // Catch:{ all -> 0x0c03 }
        L_0x00c4:
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource r8 = r1.fileSource     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x00cd
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource r8 = r1.fileSource     // Catch:{ all -> 0x0c03 }
            r7.add(r8)     // Catch:{ all -> 0x0c03 }
        L_0x00cd:
            java.util.List<com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection> r8 = r1.moreInfoSections     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x00d6
            java.util.List<com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection> r8 = r1.moreInfoSections     // Catch:{ all -> 0x0c03 }
            r15.addAll(r8)     // Catch:{ all -> 0x0c03 }
        L_0x00d6:
            com.wbmd.qxcalculator.model.contentItems.definition.Definition r8 = r1.definition     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x00e4
            com.wbmd.qxcalculator.model.contentItems.definition.Definition r8 = r1.definition     // Catch:{ all -> 0x0c03 }
            r20 = r15
            r15 = r18
            r15.add(r8)     // Catch:{ all -> 0x0c03 }
            goto L_0x00e8
        L_0x00e4:
            r20 = r15
            r15 = r18
        L_0x00e8:
            com.wbmd.qxcalculator.model.contentItems.splashpage.SplashPage r8 = r1.splashPage     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x00f6
            com.wbmd.qxcalculator.model.contentItems.splashpage.SplashPage r8 = r1.splashPage     // Catch:{ all -> 0x0c03 }
            r18 = r15
            r15 = r16
            r15.add(r8)     // Catch:{ all -> 0x0c03 }
            goto L_0x00fa
        L_0x00f6:
            r18 = r15
            r15 = r16
        L_0x00fa:
            java.util.List<com.wbmd.qxcalculator.model.parsedObjects.Promotion> r8 = r1.promotions     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x0106
            java.util.List<com.wbmd.qxcalculator.model.parsedObjects.Promotion> r1 = r1.promotions     // Catch:{ all -> 0x0c03 }
            r8 = r19
            r8.addAll(r1)     // Catch:{ all -> 0x0c03 }
            goto L_0x0108
        L_0x0106:
            r8 = r19
        L_0x0108:
            r1 = r8
            r16 = r15
            r8 = r18
            r15 = r20
            goto L_0x005c
        L_0x0111:
            r18 = r8
            r20 = r15
            r15 = r16
            r8 = r1
            com.wbmd.qxcalculator.model.db.DBContentItemDao r1 = r39.getDBContentItemDao()     // Catch:{ all -> 0x0c03 }
            de.greenrobot.dao.Property r15 = com.wbmd.qxcalculator.model.db.DBContentItemDao.Properties.Identifier     // Catch:{ all -> 0x0c03 }
            java.util.List r1 = com.wbmd.qxcalculator.util.DatabaseHelper.getAllWithPropertyInData(r1, r15, r3)     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r3.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.LinkedHashMap r15 = new java.util.LinkedHashMap     // Catch:{ all -> 0x0c03 }
            r15.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r17 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r17.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r19 = r4.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r19 != 0) goto L_0x0140
            r19 = r3
            r3 = r39
            java.util.List r17 = com.wbmd.qxcalculator.model.db.DBCategory.insertAndRetrieveDbEntities(r3, r4)     // Catch:{ all -> 0x0c03 }
            goto L_0x0144
        L_0x0140:
            r19 = r3
            r3 = r39
        L_0x0144:
            r4 = r17
            java.util.ArrayList r17 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r17.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r21 = r9.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r21 != 0) goto L_0x0155
            java.util.List r17 = com.wbmd.qxcalculator.model.db.DBResourceFile.insertAndRetrieveDbEntities(r3, r9)     // Catch:{ all -> 0x0c03 }
        L_0x0155:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r9.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r21 = r10.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r21 != 0) goto L_0x0164
            java.util.List r9 = com.wbmd.qxcalculator.model.db.DBPlatform.insertAndRetrieveDbEntities(r3, r10)     // Catch:{ all -> 0x0c03 }
        L_0x0164:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r10.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r21 = r11.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r21 != 0) goto L_0x0173
            java.util.List r10 = com.wbmd.qxcalculator.model.db.DBFilter.insertAndRetrieveDbEntities(r3, r11)     // Catch:{ all -> 0x0c03 }
        L_0x0173:
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r11.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r21 = r12.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r21 != 0) goto L_0x0182
            java.util.List r11 = com.wbmd.qxcalculator.model.db.DBFeaturedContentAd.insertAndRetrieveDbEntities(r3, r12)     // Catch:{ all -> 0x0c03 }
        L_0x0182:
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r21 = r10
            int r10 = r11.size()     // Catch:{ all -> 0x0c03 }
            r12.<init>(r10)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r10 = r11.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0191:
            boolean r22 = r10.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r22 == 0) goto L_0x01a9
            java.lang.Object r22 = r10.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBFeaturedContentAd r22 = (com.wbmd.qxcalculator.model.db.DBFeaturedContentAd) r22     // Catch:{ all -> 0x0c03 }
            r23 = r10
            java.lang.Long r10 = r22.getId()     // Catch:{ all -> 0x0c03 }
            r12.add(r10)     // Catch:{ all -> 0x0c03 }
            r10 = r23
            goto L_0x0191
        L_0x01a9:
            com.wbmd.qxcalculator.model.db.DBFeaturedContentAd.preloadFilterRelations(r3, r11, r12)     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBFeaturedContentAd.preloadPlatformRelations(r3, r11, r12)     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r10.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r12 = r13.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r12 != 0) goto L_0x01be
            java.util.List r10 = com.wbmd.qxcalculator.model.db.DBRestriction.insertAndRetrieveDbEntities(r3, r13)     // Catch:{ all -> 0x0c03 }
        L_0x01be:
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            int r13 = r10.size()     // Catch:{ all -> 0x0c03 }
            r12.<init>(r13)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r13 = r10.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x01cb:
            boolean r22 = r13.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r22 == 0) goto L_0x01e3
            java.lang.Object r22 = r13.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBRestriction r22 = (com.wbmd.qxcalculator.model.db.DBRestriction) r22     // Catch:{ all -> 0x0c03 }
            r23 = r13
            java.lang.Long r13 = r22.getId()     // Catch:{ all -> 0x0c03 }
            r12.add(r13)     // Catch:{ all -> 0x0c03 }
            r13 = r23
            goto L_0x01cb
        L_0x01e3:
            com.wbmd.qxcalculator.model.db.DBRestriction.preloadFilterRelations(r3, r10, r12)     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBRestriction.preloadPlatformRelations(r3, r10, r12)     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r12.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r13 = r14.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r13 != 0) goto L_0x01f8
            java.util.List r12 = com.wbmd.qxcalculator.model.db.DBItemFileZip.insertAndRetrieveDbEntities(r3, r14)     // Catch:{ all -> 0x0c03 }
        L_0x01f8:
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r13.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r14 = r5.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r14 != 0) goto L_0x0207
            java.util.List r13 = com.wbmd.qxcalculator.model.db.DBCalculator.insertAndRetrieveDbEntities(r3, r5)     // Catch:{ all -> 0x0c03 }
        L_0x0207:
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r5.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r14 = r6.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r14 != 0) goto L_0x0216
            java.util.List r5 = com.wbmd.qxcalculator.model.db.DBReferenceBook.insertAndRetrieveDbEntities(r3, r6)     // Catch:{ all -> 0x0c03 }
        L_0x0216:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r6.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r14 = r7.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r14 != 0) goto L_0x0225
            java.util.List r6 = com.wbmd.qxcalculator.model.db.DBFileSource.insertAndRetrieveDbEntities(r3, r7)     // Catch:{ all -> 0x0c03 }
        L_0x0225:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r7.<init>()     // Catch:{ all -> 0x0c03 }
            int r14 = r20.size()     // Catch:{ all -> 0x0c03 }
            if (r14 <= 0) goto L_0x0236
            r14 = r20
            java.util.List r7 = com.wbmd.qxcalculator.model.db.DBMoreInfoSection.insertAndRetrieveDbEntities(r3, r14)     // Catch:{ all -> 0x0c03 }
        L_0x0236:
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r14.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r20 = r18.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r20 != 0) goto L_0x0248
            r14 = r18
            java.util.List r14 = com.wbmd.qxcalculator.model.db.DBDefinition.insertAndRetrieveDbEntities(r3, r14)     // Catch:{ all -> 0x0c03 }
            goto L_0x024a
        L_0x0248:
            r20 = r14
        L_0x024a:
            java.util.ArrayList r18 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r18.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r20 = r16.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r20 != 0) goto L_0x025e
            r20 = r10
            r10 = r16
            java.util.List r18 = com.wbmd.qxcalculator.model.db.DBSplashPage.insertAndRetrieveDbEntities(r3, r10)     // Catch:{ all -> 0x0c03 }
            goto L_0x0260
        L_0x025e:
            r20 = r10
        L_0x0260:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r10.<init>()     // Catch:{ all -> 0x0c03 }
            boolean r16 = r8.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r16 != 0) goto L_0x026f
            java.util.List r10 = com.wbmd.qxcalculator.model.db.DBPromotion.insertAndRetrieveDbEntities(r3, r8)     // Catch:{ all -> 0x0c03 }
        L_0x026f:
            java.util.Date r8 = new java.util.Date     // Catch:{ all -> 0x0c03 }
            r8.<init>()     // Catch:{ all -> 0x0c03 }
            long r22 = r8.getTime()     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r8 = r40.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x027c:
            boolean r16 = r8.hasNext()     // Catch:{ all -> 0x0c03 }
            r24 = 1
            r25 = r10
            if (r16 == 0) goto L_0x0488
            java.lang.Object r16 = r8.next()     // Catch:{ all -> 0x0c03 }
            r10 = r16
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem r10 = (com.wbmd.qxcalculator.model.contentItems.common.ContentItem) r10     // Catch:{ all -> 0x0c03 }
            boolean r16 = r15.containsKey(r10)     // Catch:{ all -> 0x0c03 }
            if (r16 == 0) goto L_0x029b
            java.lang.Object r16 = r15.get(r10)     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBContentItem r16 = (com.wbmd.qxcalculator.model.db.DBContentItem) r16     // Catch:{ all -> 0x0c03 }
            goto L_0x029d
        L_0x029b:
            r16 = 0
        L_0x029d:
            if (r16 != 0) goto L_0x02c7
            java.util.Iterator r26 = r1.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x02a3:
            boolean r27 = r26.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r27 == 0) goto L_0x02c7
            java.lang.Object r27 = r26.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBContentItem r27 = (com.wbmd.qxcalculator.model.db.DBContentItem) r27     // Catch:{ all -> 0x0c03 }
            r28 = r1
            java.lang.String r1 = r27.getIdentifier()     // Catch:{ all -> 0x0c03 }
            r29 = r8
            java.lang.String r8 = r10.identifier     // Catch:{ all -> 0x0c03 }
            boolean r1 = r1.equals(r8)     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x02c2
            r16 = r27
            goto L_0x02cb
        L_0x02c2:
            r1 = r28
            r8 = r29
            goto L_0x02a3
        L_0x02c7:
            r28 = r1
            r29 = r8
        L_0x02cb:
            if (r16 != 0) goto L_0x02df
            com.wbmd.qxcalculator.model.db.DBContentItem r1 = new com.wbmd.qxcalculator.model.db.DBContentItem     // Catch:{ all -> 0x0c03 }
            r1.<init>()     // Catch:{ all -> 0x0c03 }
            java.lang.Long r8 = java.lang.Long.valueOf(r22)     // Catch:{ all -> 0x0c03 }
            r1.setDateAdded(r8)     // Catch:{ all -> 0x0c03 }
            r8 = r19
            r8.add(r1)     // Catch:{ all -> 0x0c03 }
            goto L_0x02e3
        L_0x02df:
            r8 = r19
            r1 = r16
        L_0x02e3:
            r16 = r11
            java.lang.String r11 = r10.type     // Catch:{ all -> 0x0c03 }
            r1.setType(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r10.identifier     // Catch:{ all -> 0x0c03 }
            r1.setIdentifier(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r10.name     // Catch:{ all -> 0x0c03 }
            r1.setName(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r10.description     // Catch:{ all -> 0x0c03 }
            r1.setDescription(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r10.footer     // Catch:{ all -> 0x0c03 }
            r1.setFooter(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.Long r11 = r10.startDate     // Catch:{ all -> 0x0c03 }
            r1.setStartDate(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.Long r11 = r10.endDate     // Catch:{ all -> 0x0c03 }
            r1.setEndDate(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r10.trackerId     // Catch:{ all -> 0x0c03 }
            r1.setTrackerId(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.Long r11 = r10.lastModifiedDate     // Catch:{ all -> 0x0c03 }
            r1.setLastModifiedDate(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.Integer r11 = r10.debugType     // Catch:{ all -> 0x0c03 }
            r1.setDebugType(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r10.moreInformation     // Catch:{ all -> 0x0c03 }
            r1.setMoreInformation(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r10.moreInfoSource     // Catch:{ all -> 0x0c03 }
            r1.setMoreInformationSource(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.Boolean r11 = r10.showAds     // Catch:{ all -> 0x0c03 }
            r1.setShowAds(r11)     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r10.copiedFromContentItemId     // Catch:{ all -> 0x0c03 }
            r1.setCopiedFromContentItemId(r11)     // Catch:{ all -> 0x0c03 }
            r11 = 0
            r19 = r9
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r11)     // Catch:{ all -> 0x0c03 }
            r1.setRequiresUpdate(r9)     // Catch:{ all -> 0x0c03 }
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r11)     // Catch:{ all -> 0x0c03 }
            r1.setResourcesRequireUpdate(r9)     // Catch:{ all -> 0x0c03 }
            java.lang.Long r9 = r10.startDate     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x035a
            java.util.Date r9 = new java.util.Date     // Catch:{ all -> 0x0c03 }
            r9.<init>()     // Catch:{ all -> 0x0c03 }
            long r26 = r9.getTime()     // Catch:{ all -> 0x0c03 }
            java.lang.Long r9 = r10.startDate     // Catch:{ all -> 0x0c03 }
            long r30 = r9.longValue()     // Catch:{ all -> 0x0c03 }
            int r9 = (r26 > r30 ? 1 : (r26 == r30 ? 0 : -1))
            if (r9 >= 0) goto L_0x035a
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r24)     // Catch:{ all -> 0x0c03 }
            r1.setShouldNotifiedBecomeAvailable(r9)     // Catch:{ all -> 0x0c03 }
        L_0x035a:
            com.wbmd.qxcalculator.model.contentItems.common.ItemFileZip r9 = r10.itemFileZip     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x038c
            java.util.Iterator r9 = r12.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0362:
            boolean r11 = r9.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r11 == 0) goto L_0x0389
            java.lang.Object r11 = r9.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBItemFileZip r11 = (com.wbmd.qxcalculator.model.db.DBItemFileZip) r11     // Catch:{ all -> 0x0c03 }
            r24 = r9
            java.lang.String r9 = r11.getIdentifier()     // Catch:{ all -> 0x0c03 }
            r26 = r12
            com.wbmd.qxcalculator.model.contentItems.common.ItemFileZip r12 = r10.itemFileZip     // Catch:{ all -> 0x0c03 }
            java.lang.String r12 = r12.url     // Catch:{ all -> 0x0c03 }
            boolean r9 = r9.equals(r12)     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x0384
            r1.setItemFileZip(r11)     // Catch:{ all -> 0x0c03 }
            goto L_0x0392
        L_0x0384:
            r9 = r24
            r12 = r26
            goto L_0x0362
        L_0x0389:
            r26 = r12
            goto L_0x0392
        L_0x038c:
            r26 = r12
            r9 = 0
            r1.setItemFileZip(r9)     // Catch:{ all -> 0x0c03 }
        L_0x0392:
            com.wbmd.qxcalculator.model.contentItems.calculator.Calculator r9 = r10.calculator     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x03bd
            java.util.Iterator r9 = r13.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x039a:
            boolean r11 = r9.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r11 == 0) goto L_0x03c1
            java.lang.Object r11 = r9.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBCalculator r11 = (com.wbmd.qxcalculator.model.db.DBCalculator) r11     // Catch:{ all -> 0x0c03 }
            java.lang.String r12 = r11.getIdentifier()     // Catch:{ all -> 0x0c03 }
            r24 = r9
            com.wbmd.qxcalculator.model.contentItems.calculator.Calculator r9 = r10.calculator     // Catch:{ all -> 0x0c03 }
            java.lang.String r9 = r9.identifier     // Catch:{ all -> 0x0c03 }
            boolean r9 = r12.equals(r9)     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x03ba
            r1.setCalculator(r11)     // Catch:{ all -> 0x0c03 }
            goto L_0x03c1
        L_0x03ba:
            r9 = r24
            goto L_0x039a
        L_0x03bd:
            r9 = 0
            r1.setCalculator(r9)     // Catch:{ all -> 0x0c03 }
        L_0x03c1:
            com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBook r9 = r10.referenceBook     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x03ef
            java.util.Iterator r9 = r5.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x03c9:
            boolean r11 = r9.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r11 == 0) goto L_0x03ec
            java.lang.Object r11 = r9.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBReferenceBook r11 = (com.wbmd.qxcalculator.model.db.DBReferenceBook) r11     // Catch:{ all -> 0x0c03 }
            java.lang.String r12 = r11.getIdentifier()     // Catch:{ all -> 0x0c03 }
            r27 = r5
            com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBook r5 = r10.referenceBook     // Catch:{ all -> 0x0c03 }
            java.lang.String r5 = r5.identifier     // Catch:{ all -> 0x0c03 }
            boolean r5 = r12.equals(r5)     // Catch:{ all -> 0x0c03 }
            if (r5 == 0) goto L_0x03e9
            r1.setReferenceBook(r11)     // Catch:{ all -> 0x0c03 }
            goto L_0x03f5
        L_0x03e9:
            r5 = r27
            goto L_0x03c9
        L_0x03ec:
            r27 = r5
            goto L_0x03f5
        L_0x03ef:
            r27 = r5
            r5 = 0
            r1.setReferenceBook(r5)     // Catch:{ all -> 0x0c03 }
        L_0x03f5:
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource r5 = r10.fileSource     // Catch:{ all -> 0x0c03 }
            if (r5 == 0) goto L_0x041b
            java.util.Iterator r5 = r6.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x03fd:
            boolean r9 = r5.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x041f
            java.lang.Object r9 = r5.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBFileSource r9 = (com.wbmd.qxcalculator.model.db.DBFileSource) r9     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r9.getIdentifier()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.contentItems.filesource.FileSource r12 = r10.fileSource     // Catch:{ all -> 0x0c03 }
            java.lang.String r12 = r12.identifier     // Catch:{ all -> 0x0c03 }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0c03 }
            if (r11 == 0) goto L_0x03fd
            r1.setFileSource(r9)     // Catch:{ all -> 0x0c03 }
            goto L_0x041f
        L_0x041b:
            r5 = 0
            r1.setFileSource(r5)     // Catch:{ all -> 0x0c03 }
        L_0x041f:
            com.wbmd.qxcalculator.model.contentItems.definition.Definition r5 = r10.definition     // Catch:{ all -> 0x0c03 }
            if (r5 == 0) goto L_0x0445
            java.util.Iterator r5 = r14.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0427:
            boolean r9 = r5.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x0449
            java.lang.Object r9 = r5.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBDefinition r9 = (com.wbmd.qxcalculator.model.db.DBDefinition) r9     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r9.getIdentifier()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.contentItems.definition.Definition r12 = r10.definition     // Catch:{ all -> 0x0c03 }
            java.lang.String r12 = r12.identifier     // Catch:{ all -> 0x0c03 }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0c03 }
            if (r11 == 0) goto L_0x0427
            r1.setDefinition(r9)     // Catch:{ all -> 0x0c03 }
            goto L_0x0449
        L_0x0445:
            r5 = 0
            r1.setDefinition(r5)     // Catch:{ all -> 0x0c03 }
        L_0x0449:
            com.wbmd.qxcalculator.model.contentItems.splashpage.SplashPage r5 = r10.splashPage     // Catch:{ all -> 0x0c03 }
            if (r5 == 0) goto L_0x046f
            java.util.Iterator r5 = r18.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0451:
            boolean r9 = r5.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x0473
            java.lang.Object r9 = r5.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBSplashPage r9 = (com.wbmd.qxcalculator.model.db.DBSplashPage) r9     // Catch:{ all -> 0x0c03 }
            java.lang.String r11 = r9.getIdentifier()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.contentItems.splashpage.SplashPage r12 = r10.splashPage     // Catch:{ all -> 0x0c03 }
            java.lang.String r12 = r12.identifier     // Catch:{ all -> 0x0c03 }
            boolean r11 = r11.equals(r12)     // Catch:{ all -> 0x0c03 }
            if (r11 == 0) goto L_0x0451
            r1.setSplashPage(r9)     // Catch:{ all -> 0x0c03 }
            goto L_0x0473
        L_0x046f:
            r5 = 0
            r1.setSplashPage(r5)     // Catch:{ all -> 0x0c03 }
        L_0x0473:
            r15.put(r10, r1)     // Catch:{ all -> 0x0c03 }
            r11 = r16
            r9 = r19
            r10 = r25
            r12 = r26
            r5 = r27
            r1 = r28
            r19 = r8
            r8 = r29
            goto L_0x027c
        L_0x0488:
            r16 = r11
            r8 = r19
            r19 = r9
            int r1 = r8.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x049b
            com.wbmd.qxcalculator.model.db.DBContentItemDao r1 = r39.getDBContentItemDao()     // Catch:{ all -> 0x0c03 }
            r1.insertInTx(r8)     // Catch:{ all -> 0x0c03 }
        L_0x049b:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            int r5 = r15.size()     // Catch:{ all -> 0x0c03 }
            r1.<init>(r5)     // Catch:{ all -> 0x0c03 }
            java.util.Collection r5 = r15.values()     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x04ac:
            boolean r6 = r5.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r6 == 0) goto L_0x04c0
            java.lang.Object r6 = r5.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBContentItem r6 = (com.wbmd.qxcalculator.model.db.DBContentItem) r6     // Catch:{ all -> 0x0c03 }
            java.lang.Long r6 = r6.getId()     // Catch:{ all -> 0x0c03 }
            r1.add(r6)     // Catch:{ all -> 0x0c03 }
            goto L_0x04ac
        L_0x04c0:
            com.wbmd.qxcalculator.model.db.DBCategoryDao r5 = r39.getDBCategoryDao()     // Catch:{ all -> 0x0c03 }
            de.greenrobot.dao.Property r6 = com.wbmd.qxcalculator.model.db.DBCategoryDao.Properties.ContentItemId     // Catch:{ all -> 0x0c03 }
            java.util.List r5 = com.wbmd.qxcalculator.util.DatabaseHelper.getAllWithPropertyInData(r5, r6, r1)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r6 = r5.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x04ce:
            boolean r8 = r6.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r8 == 0) goto L_0x04df
            java.lang.Object r8 = r6.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBCategory r8 = (com.wbmd.qxcalculator.model.db.DBCategory) r8     // Catch:{ all -> 0x0c03 }
            r9 = 0
            r8.setContentItemId(r9)     // Catch:{ all -> 0x0c03 }
            goto L_0x04ce
        L_0x04df:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r6.<init>()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBTagDao r8 = r39.getDBTagDao()     // Catch:{ all -> 0x0c03 }
            de.greenrobot.dao.Property r9 = com.wbmd.qxcalculator.model.db.DBTagDao.Properties.ContentItemId     // Catch:{ all -> 0x0c03 }
            java.util.List r8 = com.wbmd.qxcalculator.util.DatabaseHelper.getAllWithPropertyInData(r8, r9, r1)     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r9.<init>()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBResourceFileDao r10 = r39.getDBResourceFileDao()     // Catch:{ all -> 0x0c03 }
            de.greenrobot.dao.Property r11 = com.wbmd.qxcalculator.model.db.DBResourceFileDao.Properties.ContentItemId     // Catch:{ all -> 0x0c03 }
            java.util.List r10 = com.wbmd.qxcalculator.util.DatabaseHelper.getAllWithPropertyInData(r10, r11, r1)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r11 = r10.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0501:
            boolean r12 = r11.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r12 == 0) goto L_0x0512
            java.lang.Object r12 = r11.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBResourceFile r12 = (com.wbmd.qxcalculator.model.db.DBResourceFile) r12     // Catch:{ all -> 0x0c03 }
            r13 = 0
            r12.setContentItemId(r13)     // Catch:{ all -> 0x0c03 }
            goto L_0x0501
        L_0x0512:
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r11.<init>()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBPlatformDao r12 = r39.getDBPlatformDao()     // Catch:{ all -> 0x0c03 }
            de.greenrobot.dao.Property r13 = com.wbmd.qxcalculator.model.db.DBPlatformDao.Properties.ContentItemId     // Catch:{ all -> 0x0c03 }
            java.util.List r12 = com.wbmd.qxcalculator.util.DatabaseHelper.getAllWithPropertyInData(r12, r13, r1)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r13 = r12.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0525:
            boolean r14 = r13.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r14 == 0) goto L_0x053a
            java.lang.Object r14 = r13.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBPlatform r14 = (com.wbmd.qxcalculator.model.db.DBPlatform) r14     // Catch:{ all -> 0x0c03 }
            r18 = r13
            r13 = 0
            r14.setContentItemId(r13)     // Catch:{ all -> 0x0c03 }
            r13 = r18
            goto L_0x0525
        L_0x053a:
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r13.<init>()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBFilterDao r14 = r39.getDBFilterDao()     // Catch:{ all -> 0x0c03 }
            r18 = r8
            de.greenrobot.dao.Property r8 = com.wbmd.qxcalculator.model.db.DBFilterDao.Properties.ContentItemId     // Catch:{ all -> 0x0c03 }
            java.util.List r8 = com.wbmd.qxcalculator.util.DatabaseHelper.getAllWithPropertyInData(r14, r8, r1)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r14 = r8.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x054f:
            boolean r22 = r14.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r22 == 0) goto L_0x056a
            java.lang.Object r22 = r14.next()     // Catch:{ all -> 0x0c03 }
            r23 = r14
            r14 = r22
            com.wbmd.qxcalculator.model.db.DBFilter r14 = (com.wbmd.qxcalculator.model.db.DBFilter) r14     // Catch:{ all -> 0x0c03 }
            r22 = r8
            r8 = 0
            r14.setContentItemId(r8)     // Catch:{ all -> 0x0c03 }
            r8 = r22
            r14 = r23
            goto L_0x054f
        L_0x056a:
            r22 = r8
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r8.<init>()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBFeaturedContentAdDao r14 = r39.getDBFeaturedContentAdDao()     // Catch:{ all -> 0x0c03 }
            r23 = r12
            de.greenrobot.dao.Property r12 = com.wbmd.qxcalculator.model.db.DBFeaturedContentAdDao.Properties.ContentItemId     // Catch:{ all -> 0x0c03 }
            java.util.List r12 = com.wbmd.qxcalculator.util.DatabaseHelper.getAllWithPropertyInData(r14, r12, r1)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r14 = r12.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0581:
            boolean r26 = r14.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r26 == 0) goto L_0x059c
            java.lang.Object r26 = r14.next()     // Catch:{ all -> 0x0c03 }
            r27 = r14
            r14 = r26
            com.wbmd.qxcalculator.model.db.DBFeaturedContentAd r14 = (com.wbmd.qxcalculator.model.db.DBFeaturedContentAd) r14     // Catch:{ all -> 0x0c03 }
            r26 = r12
            r12 = 0
            r14.setContentItemId(r12)     // Catch:{ all -> 0x0c03 }
            r12 = r26
            r14 = r27
            goto L_0x0581
        L_0x059c:
            r26 = r12
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r12.<init>()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBRestrictionDao r14 = r39.getDBRestrictionDao()     // Catch:{ all -> 0x0c03 }
            r27 = r10
            de.greenrobot.dao.Property r10 = com.wbmd.qxcalculator.model.db.DBRestrictionDao.Properties.ContentItemId     // Catch:{ all -> 0x0c03 }
            java.util.List r10 = com.wbmd.qxcalculator.util.DatabaseHelper.getAllWithPropertyInData(r14, r10, r1)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r14 = r10.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x05b3:
            boolean r28 = r14.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r28 == 0) goto L_0x05ce
            java.lang.Object r28 = r14.next()     // Catch:{ all -> 0x0c03 }
            r29 = r14
            r14 = r28
            com.wbmd.qxcalculator.model.db.DBRestriction r14 = (com.wbmd.qxcalculator.model.db.DBRestriction) r14     // Catch:{ all -> 0x0c03 }
            r28 = r10
            r10 = 0
            r14.setContentItemId(r10)     // Catch:{ all -> 0x0c03 }
            r10 = r28
            r14 = r29
            goto L_0x05b3
        L_0x05ce:
            r28 = r10
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r10.<init>()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBMoreInfoSectionDao r14 = r39.getDBMoreInfoSectionDao()     // Catch:{ all -> 0x0c03 }
            r29 = r5
            de.greenrobot.dao.Property r5 = com.wbmd.qxcalculator.model.db.DBMoreInfoSectionDao.Properties.ContentItemId     // Catch:{ all -> 0x0c03 }
            java.util.List r5 = com.wbmd.qxcalculator.util.DatabaseHelper.getAllWithPropertyInData(r14, r5, r1)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r14 = r5.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x05e5:
            boolean r30 = r14.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r30 == 0) goto L_0x0600
            java.lang.Object r30 = r14.next()     // Catch:{ all -> 0x0c03 }
            r31 = r14
            r14 = r30
            com.wbmd.qxcalculator.model.db.DBMoreInfoSection r14 = (com.wbmd.qxcalculator.model.db.DBMoreInfoSection) r14     // Catch:{ all -> 0x0c03 }
            r30 = r5
            r5 = 0
            r14.setContentItemId(r5)     // Catch:{ all -> 0x0c03 }
            r5 = r30
            r14 = r31
            goto L_0x05e5
        L_0x0600:
            r30 = r5
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            int r14 = r7.size()     // Catch:{ all -> 0x0c03 }
            r5.<init>(r14)     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBPromotionDao r14 = r39.getDBPromotionDao()     // Catch:{ all -> 0x0c03 }
            r31 = r5
            de.greenrobot.dao.Property r5 = com.wbmd.qxcalculator.model.db.DBPromotionDao.Properties.ContentItemId     // Catch:{ all -> 0x0c03 }
            java.util.List r1 = com.wbmd.qxcalculator.util.DatabaseHelper.getAllWithPropertyInData(r14, r5, r1)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x061b:
            boolean r5 = r1.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r5 == 0) goto L_0x062c
            java.lang.Object r5 = r1.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBPromotion r5 = (com.wbmd.qxcalculator.model.db.DBPromotion) r5     // Catch:{ all -> 0x0c03 }
            r14 = 0
            r5.setContentItemId(r14)     // Catch:{ all -> 0x0c03 }
            goto L_0x061b
        L_0x062c:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r1.<init>()     // Catch:{ all -> 0x0c03 }
            java.util.Set r5 = r15.entrySet()     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0639:
            boolean r14 = r5.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r14 == 0) goto L_0x0a02
            java.lang.Object r14 = r5.next()     // Catch:{ all -> 0x0c03 }
            java.util.Map$Entry r14 = (java.util.Map.Entry) r14     // Catch:{ all -> 0x0c03 }
            java.lang.Object r32 = r14.getKey()     // Catch:{ all -> 0x0c03 }
            r40 = r5
            r5 = r32
            com.wbmd.qxcalculator.model.contentItems.common.ContentItem r5 = (com.wbmd.qxcalculator.model.contentItems.common.ContentItem) r5     // Catch:{ all -> 0x0c03 }
            java.lang.Object r14 = r14.getValue()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBContentItem r14 = (com.wbmd.qxcalculator.model.db.DBContentItem) r14     // Catch:{ all -> 0x0c03 }
            r32 = r15
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r15.<init>()     // Catch:{ all -> 0x0c03 }
            r14.categories = r15     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Category> r15 = r5.categories     // Catch:{ all -> 0x0c03 }
            if (r15 == 0) goto L_0x06ca
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Category> r15 = r5.categories     // Catch:{ all -> 0x0c03 }
            boolean r15 = r15.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r15 != 0) goto L_0x06ca
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Category> r15 = r5.categories     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r15 = r15.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0670:
            boolean r33 = r15.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r33 == 0) goto L_0x06ca
            java.lang.Object r33 = r15.next()     // Catch:{ all -> 0x0c03 }
            r34 = r15
            r15 = r33
            com.wbmd.qxcalculator.model.contentItems.common.Category r15 = (com.wbmd.qxcalculator.model.contentItems.common.Category) r15     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r33 = r4.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0684:
            boolean r35 = r33.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r35 == 0) goto L_0x06bb
            java.lang.Object r35 = r33.next()     // Catch:{ all -> 0x0c03 }
            r36 = r1
            r1 = r35
            com.wbmd.qxcalculator.model.db.DBCategory r1 = (com.wbmd.qxcalculator.model.db.DBCategory) r1     // Catch:{ all -> 0x0c03 }
            r35 = r7
            java.lang.String r7 = r1.getContentSpecificIdentifier()     // Catch:{ all -> 0x0c03 }
            r37 = r9
            java.lang.String r9 = r15.contentSpecificIdentifier     // Catch:{ all -> 0x0c03 }
            boolean r7 = r7.equals(r9)     // Catch:{ all -> 0x0c03 }
            if (r7 == 0) goto L_0x06b4
            java.lang.Long r7 = r14.getId()     // Catch:{ all -> 0x0c03 }
            r1.setContentItemId(r7)     // Catch:{ all -> 0x0c03 }
            r6.add(r1)     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.db.DBCategory> r7 = r14.categories     // Catch:{ all -> 0x0c03 }
            r7.add(r1)     // Catch:{ all -> 0x0c03 }
            goto L_0x06c1
        L_0x06b4:
            r7 = r35
            r1 = r36
            r9 = r37
            goto L_0x0684
        L_0x06bb:
            r36 = r1
            r35 = r7
            r37 = r9
        L_0x06c1:
            r15 = r34
            r7 = r35
            r1 = r36
            r9 = r37
            goto L_0x0670
        L_0x06ca:
            r36 = r1
            r35 = r7
            r37 = r9
            com.wbmd.qxcalculator.model.db.DBCategory.preloadSubCategoryRelations(r3, r4)     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r1.<init>()     // Catch:{ all -> 0x0c03 }
            r14.resourceFiles = r1     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.ResourceFile> r1 = r5.resourceFiles     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x0736
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.ResourceFile> r1 = r5.resourceFiles     // Catch:{ all -> 0x0c03 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r1 != 0) goto L_0x0736
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.ResourceFile> r1 = r5.resourceFiles     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x06ec:
            boolean r7 = r1.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r7 == 0) goto L_0x0736
            java.lang.Object r7 = r1.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.contentItems.common.ResourceFile r7 = (com.wbmd.qxcalculator.model.contentItems.common.ResourceFile) r7     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r9 = r17.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x06fc:
            boolean r15 = r9.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r15 == 0) goto L_0x072d
            java.lang.Object r15 = r9.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBResourceFile r15 = (com.wbmd.qxcalculator.model.db.DBResourceFile) r15     // Catch:{ all -> 0x0c03 }
            r33 = r1
            java.lang.String r1 = r15.getIdentifier()     // Catch:{ all -> 0x0c03 }
            r34 = r4
            java.lang.String r4 = r7.identifier     // Catch:{ all -> 0x0c03 }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x0728
            java.lang.Long r1 = r14.getId()     // Catch:{ all -> 0x0c03 }
            r15.setContentItemId(r1)     // Catch:{ all -> 0x0c03 }
            r11.add(r15)     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.db.DBResourceFile> r1 = r14.resourceFiles     // Catch:{ all -> 0x0c03 }
            r1.add(r15)     // Catch:{ all -> 0x0c03 }
            goto L_0x0731
        L_0x0728:
            r1 = r33
            r4 = r34
            goto L_0x06fc
        L_0x072d:
            r33 = r1
            r34 = r4
        L_0x0731:
            r1 = r33
            r4 = r34
            goto L_0x06ec
        L_0x0736:
            r34 = r4
            com.wbmd.qxcalculator.util.Version r1 = new com.wbmd.qxcalculator.util.Version     // Catch:{ Exception -> 0x07ce }
            com.wbmd.qxcalculator.AppConfiguration r4 = com.wbmd.qxcalculator.AppConfiguration.getInstance()     // Catch:{ Exception -> 0x07ce }
            java.lang.String r4 = r4.getAppBuildVersion()     // Catch:{ Exception -> 0x07ce }
            r1.<init>(r4)     // Catch:{ Exception -> 0x07ce }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x07ce }
            r4.<init>()     // Catch:{ Exception -> 0x07ce }
            r14.platforms = r4     // Catch:{ Exception -> 0x07ce }
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Platform> r4 = r5.platforms     // Catch:{ Exception -> 0x07ce }
            if (r4 == 0) goto L_0x07d3
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Platform> r4 = r5.platforms     // Catch:{ Exception -> 0x07ce }
            boolean r4 = r4.isEmpty()     // Catch:{ Exception -> 0x07ce }
            if (r4 != 0) goto L_0x07d3
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Platform> r4 = r5.platforms     // Catch:{ Exception -> 0x07ce }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ Exception -> 0x07ce }
        L_0x075e:
            boolean r7 = r4.hasNext()     // Catch:{ Exception -> 0x07ce }
            if (r7 == 0) goto L_0x07d3
            java.lang.Object r7 = r4.next()     // Catch:{ Exception -> 0x07ce }
            com.wbmd.qxcalculator.model.contentItems.common.Platform r7 = (com.wbmd.qxcalculator.model.contentItems.common.Platform) r7     // Catch:{ Exception -> 0x07ce }
            java.util.Iterator r9 = r19.iterator()     // Catch:{ Exception -> 0x07ce }
        L_0x076e:
            boolean r15 = r9.hasNext()     // Catch:{ Exception -> 0x07ce }
            if (r15 == 0) goto L_0x07c9
            java.lang.Object r15 = r9.next()     // Catch:{ Exception -> 0x07ce }
            com.wbmd.qxcalculator.model.db.DBPlatform r15 = (com.wbmd.qxcalculator.model.db.DBPlatform) r15     // Catch:{ Exception -> 0x07ce }
            r33 = r4
            java.lang.String r4 = r15.getIdentifier()     // Catch:{ Exception -> 0x07ce }
            r38 = r9
            java.lang.String r9 = r7.identifier     // Catch:{ Exception -> 0x07ce }
            boolean r4 = r4.equals(r9)     // Catch:{ Exception -> 0x07ce }
            if (r4 == 0) goto L_0x07c4
            java.lang.Long r4 = r14.getId()     // Catch:{ Exception -> 0x07ce }
            r15.setContentItemId(r4)     // Catch:{ Exception -> 0x07ce }
            r13.add(r15)     // Catch:{ Exception -> 0x07ce }
            java.lang.String r4 = r7.os     // Catch:{ Exception -> 0x07ce }
            java.lang.String r9 = "android"
            boolean r4 = r4.equals(r9)     // Catch:{ Exception -> 0x07ce }
            if (r4 == 0) goto L_0x07be
            java.lang.String r4 = r7.minVersion     // Catch:{ Exception -> 0x07ce }
            if (r4 == 0) goto L_0x07be
            java.lang.String r4 = r7.minVersion     // Catch:{ Exception -> 0x07ce }
            boolean r4 = r4.isEmpty()     // Catch:{ Exception -> 0x07ce }
            if (r4 != 0) goto L_0x07be
            com.wbmd.qxcalculator.util.Version r4 = new com.wbmd.qxcalculator.util.Version     // Catch:{ Exception -> 0x07ce }
            java.lang.String r7 = r7.minVersion     // Catch:{ Exception -> 0x07ce }
            r4.<init>(r7)     // Catch:{ Exception -> 0x07ce }
            int r4 = r1.compareTo((com.wbmd.qxcalculator.util.Version) r4)     // Catch:{ Exception -> 0x07ce }
            if (r4 >= 0) goto L_0x07be
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r24)     // Catch:{ Exception -> 0x07ce }
            r14.setShouldNotifiedBecomeAvailable(r4)     // Catch:{ Exception -> 0x07ce }
        L_0x07be:
            java.util.List<com.wbmd.qxcalculator.model.db.DBPlatform> r4 = r14.platforms     // Catch:{ Exception -> 0x07ce }
            r4.add(r15)     // Catch:{ Exception -> 0x07ce }
            goto L_0x07cb
        L_0x07c4:
            r4 = r33
            r9 = r38
            goto L_0x076e
        L_0x07c9:
            r33 = r4
        L_0x07cb:
            r4 = r33
            goto L_0x075e
        L_0x07ce:
            r0 = move-exception
            r1 = r0
            r1.printStackTrace()     // Catch:{ all -> 0x0c03 }
        L_0x07d3:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r1.<init>()     // Catch:{ all -> 0x0c03 }
            r14.filters = r1     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Filter> r1 = r5.filters     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x082e
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Filter> r1 = r5.filters     // Catch:{ all -> 0x0c03 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r1 != 0) goto L_0x082e
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Filter> r1 = r5.filters     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x07ec:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r4 == 0) goto L_0x082e
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.contentItems.common.Filter r4 = (com.wbmd.qxcalculator.model.contentItems.common.Filter) r4     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r7 = r21.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x07fc:
            boolean r9 = r7.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x0829
            java.lang.Object r9 = r7.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBFilter r9 = (com.wbmd.qxcalculator.model.db.DBFilter) r9     // Catch:{ all -> 0x0c03 }
            java.lang.String r15 = r9.getIdentifier()     // Catch:{ all -> 0x0c03 }
            r33 = r1
            java.lang.String r1 = r4.identifier     // Catch:{ all -> 0x0c03 }
            boolean r1 = r15.equals(r1)     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x0826
            java.lang.Long r1 = r14.getId()     // Catch:{ all -> 0x0c03 }
            r9.setContentItemId(r1)     // Catch:{ all -> 0x0c03 }
            r8.add(r9)     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.db.DBFilter> r1 = r14.filters     // Catch:{ all -> 0x0c03 }
            r1.add(r9)     // Catch:{ all -> 0x0c03 }
            goto L_0x082b
        L_0x0826:
            r1 = r33
            goto L_0x07fc
        L_0x0829:
            r33 = r1
        L_0x082b:
            r1 = r33
            goto L_0x07ec
        L_0x082e:
            r1 = r21
            com.wbmd.qxcalculator.model.db.DBFilter.preloadRelations(r3, r1)     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r4.<init>()     // Catch:{ all -> 0x0c03 }
            r14.featuredContentAds = r4     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.FeaturedContentAd> r4 = r5.featuredContentAds     // Catch:{ all -> 0x0c03 }
            if (r4 == 0) goto L_0x0896
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.FeaturedContentAd> r4 = r5.featuredContentAds     // Catch:{ all -> 0x0c03 }
            boolean r4 = r4.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r4 != 0) goto L_0x0896
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.FeaturedContentAd> r4 = r5.featuredContentAds     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x084c:
            boolean r7 = r4.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r7 == 0) goto L_0x0896
            java.lang.Object r7 = r4.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.contentItems.common.FeaturedContentAd r7 = (com.wbmd.qxcalculator.model.contentItems.common.FeaturedContentAd) r7     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r9 = r16.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x085c:
            boolean r15 = r9.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r15 == 0) goto L_0x088d
            java.lang.Object r15 = r9.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBFeaturedContentAd r15 = (com.wbmd.qxcalculator.model.db.DBFeaturedContentAd) r15     // Catch:{ all -> 0x0c03 }
            r21 = r1
            java.lang.String r1 = r15.getIdentifier()     // Catch:{ all -> 0x0c03 }
            r33 = r4
            java.lang.String r4 = r7.identifier     // Catch:{ all -> 0x0c03 }
            boolean r1 = r1.equals(r4)     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x0888
            java.lang.Long r1 = r14.getId()     // Catch:{ all -> 0x0c03 }
            r15.setContentItemId(r1)     // Catch:{ all -> 0x0c03 }
            r12.add(r15)     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.db.DBFeaturedContentAd> r1 = r14.featuredContentAds     // Catch:{ all -> 0x0c03 }
            r1.add(r15)     // Catch:{ all -> 0x0c03 }
            goto L_0x0891
        L_0x0888:
            r1 = r21
            r4 = r33
            goto L_0x085c
        L_0x088d:
            r21 = r1
            r33 = r4
        L_0x0891:
            r1 = r21
            r4 = r33
            goto L_0x084c
        L_0x0896:
            r21 = r1
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r1.<init>()     // Catch:{ all -> 0x0c03 }
            r14.restrictions = r1     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Restriction> r1 = r5.restrictions     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x08f3
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Restriction> r1 = r5.restrictions     // Catch:{ all -> 0x0c03 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r1 != 0) goto L_0x08f3
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Restriction> r1 = r5.restrictions     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x08b1:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r4 == 0) goto L_0x08f3
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.contentItems.common.Restriction r4 = (com.wbmd.qxcalculator.model.contentItems.common.Restriction) r4     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r7 = r20.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x08c1:
            boolean r9 = r7.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r9 == 0) goto L_0x08ee
            java.lang.Object r9 = r7.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBRestriction r9 = (com.wbmd.qxcalculator.model.db.DBRestriction) r9     // Catch:{ all -> 0x0c03 }
            java.lang.String r15 = r9.getIdentifier()     // Catch:{ all -> 0x0c03 }
            r33 = r1
            java.lang.String r1 = r4.identifier     // Catch:{ all -> 0x0c03 }
            boolean r1 = r15.equals(r1)     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x08eb
            java.lang.Long r1 = r14.getId()     // Catch:{ all -> 0x0c03 }
            r9.setContentItemId(r1)     // Catch:{ all -> 0x0c03 }
            r10.add(r9)     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.db.DBRestriction> r1 = r14.restrictions     // Catch:{ all -> 0x0c03 }
            r1.add(r9)     // Catch:{ all -> 0x0c03 }
            goto L_0x08f0
        L_0x08eb:
            r1 = r33
            goto L_0x08c1
        L_0x08ee:
            r33 = r1
        L_0x08f0:
            r1 = r33
            goto L_0x08b1
        L_0x08f3:
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Tag> r1 = r5.tags     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x092a
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Tag> r1 = r5.tags     // Catch:{ all -> 0x0c03 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r1 != 0) goto L_0x092a
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.Tag> r1 = r5.tags     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0905:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r4 == 0) goto L_0x092a
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.contentItems.common.Tag r4 = (com.wbmd.qxcalculator.model.contentItems.common.Tag) r4     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBTag r7 = new com.wbmd.qxcalculator.model.db.DBTag     // Catch:{ all -> 0x0c03 }
            r7.<init>()     // Catch:{ all -> 0x0c03 }
            java.lang.Long r9 = r14.getId()     // Catch:{ all -> 0x0c03 }
            r7.setContentItemId(r9)     // Catch:{ all -> 0x0c03 }
            java.lang.String r4 = r4.name     // Catch:{ all -> 0x0c03 }
            r7.setName(r4)     // Catch:{ all -> 0x0c03 }
            r4 = r37
            r4.add(r7)     // Catch:{ all -> 0x0c03 }
            r37 = r4
            goto L_0x0905
        L_0x092a:
            r4 = r37
            r14.resetTags()     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection> r1 = r5.moreInfoSections     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x0988
            java.util.List<com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection> r1 = r5.moreInfoSections     // Catch:{ all -> 0x0c03 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r1 != 0) goto L_0x0988
            java.util.List<com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection> r1 = r5.moreInfoSections     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0941:
            boolean r7 = r1.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r7 == 0) goto L_0x0988
            java.lang.Object r7 = r1.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection r7 = (com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection) r7     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r9 = r35.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0951:
            boolean r15 = r9.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r15 == 0) goto L_0x097f
            java.lang.Object r15 = r9.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBMoreInfoSection r15 = (com.wbmd.qxcalculator.model.db.DBMoreInfoSection) r15     // Catch:{ all -> 0x0c03 }
            r33 = r1
            java.lang.String r1 = r15.getIdentifier()     // Catch:{ all -> 0x0c03 }
            r37 = r9
            java.lang.String r9 = r7.identifier     // Catch:{ all -> 0x0c03 }
            boolean r1 = r1.equals(r9)     // Catch:{ all -> 0x0c03 }
            if (r1 == 0) goto L_0x097a
            java.lang.Long r1 = r14.getId()     // Catch:{ all -> 0x0c03 }
            r15.setContentItemId(r1)     // Catch:{ all -> 0x0c03 }
            r1 = r31
            r1.add(r15)     // Catch:{ all -> 0x0c03 }
            goto L_0x0983
        L_0x097a:
            r1 = r33
            r9 = r37
            goto L_0x0951
        L_0x097f:
            r33 = r1
            r1 = r31
        L_0x0983:
            r31 = r1
            r1 = r33
            goto L_0x0941
        L_0x0988:
            r1 = r31
            r14.resetMoreInfoSections()     // Catch:{ all -> 0x0c03 }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r7.<init>()     // Catch:{ all -> 0x0c03 }
            r14.promotions = r7     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.parsedObjects.Promotion> r7 = r5.promotions     // Catch:{ all -> 0x0c03 }
            if (r7 == 0) goto L_0x09f2
            java.util.List<com.wbmd.qxcalculator.model.parsedObjects.Promotion> r7 = r5.promotions     // Catch:{ all -> 0x0c03 }
            boolean r7 = r7.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r7 != 0) goto L_0x09f2
            java.util.List<com.wbmd.qxcalculator.model.parsedObjects.Promotion> r5 = r5.promotions     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x09a6:
            boolean r7 = r5.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r7 == 0) goto L_0x09f2
            java.lang.Object r7 = r5.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.parsedObjects.Promotion r7 = (com.wbmd.qxcalculator.model.parsedObjects.Promotion) r7     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r9 = r25.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x09b6:
            boolean r15 = r9.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r15 == 0) goto L_0x09e9
            java.lang.Object r15 = r9.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBPromotion r15 = (com.wbmd.qxcalculator.model.db.DBPromotion) r15     // Catch:{ all -> 0x0c03 }
            r31 = r5
            java.lang.String r5 = r15.getIdentifier()     // Catch:{ all -> 0x0c03 }
            r33 = r9
            java.lang.String r9 = r7.identifier     // Catch:{ all -> 0x0c03 }
            boolean r5 = r5.equals(r9)     // Catch:{ all -> 0x0c03 }
            if (r5 == 0) goto L_0x09e4
            java.lang.Long r5 = r14.getId()     // Catch:{ all -> 0x0c03 }
            r15.setContentItemId(r5)     // Catch:{ all -> 0x0c03 }
            r5 = r36
            r5.add(r15)     // Catch:{ all -> 0x0c03 }
            java.util.List<com.wbmd.qxcalculator.model.db.DBPromotion> r7 = r14.promotions     // Catch:{ all -> 0x0c03 }
            r7.add(r15)     // Catch:{ all -> 0x0c03 }
            goto L_0x09ed
        L_0x09e4:
            r5 = r31
            r9 = r33
            goto L_0x09b6
        L_0x09e9:
            r31 = r5
            r5 = r36
        L_0x09ed:
            r36 = r5
            r5 = r31
            goto L_0x09a6
        L_0x09f2:
            r5 = r36
            r31 = r1
            r9 = r4
            r1 = r5
            r15 = r32
            r4 = r34
            r7 = r35
            r5 = r40
            goto L_0x0639
        L_0x0a02:
            r5 = r1
            r4 = r9
            r32 = r15
            r1 = r31
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            int r9 = r29.size()     // Catch:{ all -> 0x0c03 }
            r7.<init>(r9)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r9 = r29.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0a15:
            boolean r14 = r9.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r14 == 0) goto L_0x0a2b
            java.lang.Object r14 = r9.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBCategory r14 = (com.wbmd.qxcalculator.model.db.DBCategory) r14     // Catch:{ all -> 0x0c03 }
            java.lang.Long r15 = r14.getContentItemId()     // Catch:{ all -> 0x0c03 }
            if (r15 != 0) goto L_0x0a15
            r7.add(r14)     // Catch:{ all -> 0x0c03 }
            goto L_0x0a15
        L_0x0a2b:
            int r9 = r7.size()     // Catch:{ all -> 0x0c03 }
            if (r9 <= 0) goto L_0x0a34
            com.wbmd.qxcalculator.model.db.DBCategory.deleteCategories(r3, r7)     // Catch:{ all -> 0x0c03 }
        L_0x0a34:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            int r9 = r27.size()     // Catch:{ all -> 0x0c03 }
            r7.<init>(r9)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r9 = r27.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0a41:
            boolean r14 = r9.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r14 == 0) goto L_0x0a57
            java.lang.Object r14 = r9.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBResourceFile r14 = (com.wbmd.qxcalculator.model.db.DBResourceFile) r14     // Catch:{ all -> 0x0c03 }
            java.lang.Long r15 = r14.getContentItemId()     // Catch:{ all -> 0x0c03 }
            if (r15 != 0) goto L_0x0a41
            r7.add(r14)     // Catch:{ all -> 0x0c03 }
            goto L_0x0a41
        L_0x0a57:
            int r9 = r7.size()     // Catch:{ all -> 0x0c03 }
            if (r9 <= 0) goto L_0x0a64
            com.wbmd.qxcalculator.model.db.DBResourceFileDao r9 = r39.getDBResourceFileDao()     // Catch:{ all -> 0x0c03 }
            r9.deleteInTx(r7)     // Catch:{ all -> 0x0c03 }
        L_0x0a64:
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            int r9 = r23.size()     // Catch:{ all -> 0x0c03 }
            r7.<init>(r9)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r9 = r23.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0a71:
            boolean r14 = r9.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r14 == 0) goto L_0x0a87
            java.lang.Object r14 = r9.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBPlatform r14 = (com.wbmd.qxcalculator.model.db.DBPlatform) r14     // Catch:{ all -> 0x0c03 }
            java.lang.Long r15 = r14.getContentItemId()     // Catch:{ all -> 0x0c03 }
            if (r15 != 0) goto L_0x0a71
            r7.add(r14)     // Catch:{ all -> 0x0c03 }
            goto L_0x0a71
        L_0x0a87:
            java.util.ArrayList r9 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            int r14 = r22.size()     // Catch:{ all -> 0x0c03 }
            r9.<init>(r14)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r14 = r22.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0a94:
            boolean r15 = r14.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r15 == 0) goto L_0x0aaa
            java.lang.Object r15 = r14.next()     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBFilter r15 = (com.wbmd.qxcalculator.model.db.DBFilter) r15     // Catch:{ all -> 0x0c03 }
            java.lang.Long r16 = r15.getContentItemId()     // Catch:{ all -> 0x0c03 }
            if (r16 != 0) goto L_0x0a94
            r9.add(r15)     // Catch:{ all -> 0x0c03 }
            goto L_0x0a94
        L_0x0aaa:
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            int r15 = r26.size()     // Catch:{ all -> 0x0c03 }
            r14.<init>(r15)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r15 = r26.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0ab7:
            boolean r16 = r15.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r16 == 0) goto L_0x0ad3
            java.lang.Object r16 = r15.next()     // Catch:{ all -> 0x0c03 }
            r40 = r15
            r15 = r16
            com.wbmd.qxcalculator.model.db.DBFeaturedContentAd r15 = (com.wbmd.qxcalculator.model.db.DBFeaturedContentAd) r15     // Catch:{ all -> 0x0c03 }
            java.lang.Long r16 = r15.getContentItemId()     // Catch:{ all -> 0x0c03 }
            if (r16 != 0) goto L_0x0ad0
            r14.add(r15)     // Catch:{ all -> 0x0c03 }
        L_0x0ad0:
            r15 = r40
            goto L_0x0ab7
        L_0x0ad3:
            java.util.ArrayList r15 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r37 = r4
            int r4 = r28.size()     // Catch:{ all -> 0x0c03 }
            r15.<init>(r4)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r4 = r28.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0ae2:
            boolean r16 = r4.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r16 == 0) goto L_0x0afe
            java.lang.Object r16 = r4.next()     // Catch:{ all -> 0x0c03 }
            r40 = r4
            r4 = r16
            com.wbmd.qxcalculator.model.db.DBRestriction r4 = (com.wbmd.qxcalculator.model.db.DBRestriction) r4     // Catch:{ all -> 0x0c03 }
            java.lang.Long r16 = r4.getContentItemId()     // Catch:{ all -> 0x0c03 }
            if (r16 != 0) goto L_0x0afb
            r15.add(r4)     // Catch:{ all -> 0x0c03 }
        L_0x0afb:
            r4 = r40
            goto L_0x0ae2
        L_0x0afe:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r31 = r1
            int r1 = r30.size()     // Catch:{ all -> 0x0c03 }
            r4.<init>(r1)     // Catch:{ all -> 0x0c03 }
            java.util.Iterator r1 = r30.iterator()     // Catch:{ all -> 0x0c03 }
        L_0x0b0d:
            boolean r16 = r1.hasNext()     // Catch:{ all -> 0x0c03 }
            if (r16 == 0) goto L_0x0b29
            java.lang.Object r16 = r1.next()     // Catch:{ all -> 0x0c03 }
            r40 = r1
            r1 = r16
            com.wbmd.qxcalculator.model.db.DBMoreInfoSection r1 = (com.wbmd.qxcalculator.model.db.DBMoreInfoSection) r1     // Catch:{ all -> 0x0c03 }
            java.lang.Long r16 = r1.getContentItemId()     // Catch:{ all -> 0x0c03 }
            if (r16 != 0) goto L_0x0b26
            r4.add(r1)     // Catch:{ all -> 0x0c03 }
        L_0x0b26:
            r1 = r40
            goto L_0x0b0d
        L_0x0b29:
            int r1 = r7.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0b36
            com.wbmd.qxcalculator.model.db.DBPlatformDao r1 = r39.getDBPlatformDao()     // Catch:{ all -> 0x0c03 }
            r1.deleteInTx(r7)     // Catch:{ all -> 0x0c03 }
        L_0x0b36:
            int r1 = r9.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0b43
            com.wbmd.qxcalculator.model.db.DBFilterDao r1 = r39.getDBFilterDao()     // Catch:{ all -> 0x0c03 }
            r1.deleteInTx(r9)     // Catch:{ all -> 0x0c03 }
        L_0x0b43:
            int r1 = r14.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0b4c
            com.wbmd.qxcalculator.model.db.DBFeaturedContentAd.deleteFeaturedContentAds(r3, r14)     // Catch:{ all -> 0x0c03 }
        L_0x0b4c:
            int r1 = r15.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0b55
            com.wbmd.qxcalculator.model.db.DBRestriction.deleteRestrictions(r3, r15)     // Catch:{ all -> 0x0c03 }
        L_0x0b55:
            int r1 = r18.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0b64
            com.wbmd.qxcalculator.model.db.DBTagDao r1 = r39.getDBTagDao()     // Catch:{ all -> 0x0c03 }
            r7 = r18
            r1.deleteInTx(r7)     // Catch:{ all -> 0x0c03 }
        L_0x0b64:
            boolean r1 = r4.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r1 != 0) goto L_0x0b71
            com.wbmd.qxcalculator.model.db.DBMoreInfoSectionDao r1 = r39.getDBMoreInfoSectionDao()     // Catch:{ all -> 0x0c03 }
            r1.deleteInTx(r4)     // Catch:{ all -> 0x0c03 }
        L_0x0b71:
            int r1 = r6.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0b7e
            com.wbmd.qxcalculator.model.db.DBCategoryDao r1 = r39.getDBCategoryDao()     // Catch:{ all -> 0x0c03 }
            r1.updateInTx(r6)     // Catch:{ all -> 0x0c03 }
        L_0x0b7e:
            int r1 = r11.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0b8b
            com.wbmd.qxcalculator.model.db.DBResourceFileDao r1 = r39.getDBResourceFileDao()     // Catch:{ all -> 0x0c03 }
            r1.updateInTx(r11)     // Catch:{ all -> 0x0c03 }
        L_0x0b8b:
            int r1 = r5.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0b98
            com.wbmd.qxcalculator.model.db.DBPromotionDao r1 = r39.getDBPromotionDao()     // Catch:{ all -> 0x0c03 }
            r1.updateInTx(r5)     // Catch:{ all -> 0x0c03 }
        L_0x0b98:
            int r1 = r13.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0ba5
            com.wbmd.qxcalculator.model.db.DBPlatformDao r1 = r39.getDBPlatformDao()     // Catch:{ all -> 0x0c03 }
            r1.updateInTx(r13)     // Catch:{ all -> 0x0c03 }
        L_0x0ba5:
            int r1 = r8.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0bb2
            com.wbmd.qxcalculator.model.db.DBFilterDao r1 = r39.getDBFilterDao()     // Catch:{ all -> 0x0c03 }
            r1.updateInTx(r8)     // Catch:{ all -> 0x0c03 }
        L_0x0bb2:
            int r1 = r12.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0bbf
            com.wbmd.qxcalculator.model.db.DBFeaturedContentAdDao r1 = r39.getDBFeaturedContentAdDao()     // Catch:{ all -> 0x0c03 }
            r1.updateInTx(r12)     // Catch:{ all -> 0x0c03 }
        L_0x0bbf:
            int r1 = r10.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0bcc
            com.wbmd.qxcalculator.model.db.DBRestrictionDao r1 = r39.getDBRestrictionDao()     // Catch:{ all -> 0x0c03 }
            r1.updateInTx(r10)     // Catch:{ all -> 0x0c03 }
        L_0x0bcc:
            boolean r1 = r31.isEmpty()     // Catch:{ all -> 0x0c03 }
            if (r1 != 0) goto L_0x0bdb
            com.wbmd.qxcalculator.model.db.DBMoreInfoSectionDao r1 = r39.getDBMoreInfoSectionDao()     // Catch:{ all -> 0x0c03 }
            r4 = r31
            r1.updateInTx(r4)     // Catch:{ all -> 0x0c03 }
        L_0x0bdb:
            int r1 = r37.size()     // Catch:{ all -> 0x0c03 }
            if (r1 <= 0) goto L_0x0bea
            com.wbmd.qxcalculator.model.db.DBTagDao r1 = r39.getDBTagDao()     // Catch:{ all -> 0x0c03 }
            r4 = r37
            r1.insertInTx(r4)     // Catch:{ all -> 0x0c03 }
        L_0x0bea:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            java.util.Collection r4 = r32.values()     // Catch:{ all -> 0x0c03 }
            r1.<init>(r4)     // Catch:{ all -> 0x0c03 }
            com.wbmd.qxcalculator.model.db.DBContentItemDao r3 = r39.getDBContentItemDao()     // Catch:{ all -> 0x0c03 }
            r3.updateInTx(r1)     // Catch:{ all -> 0x0c03 }
            monitor-exit(r2)
            return r1
        L_0x0bfc:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0c03 }
            r1.<init>()     // Catch:{ all -> 0x0c03 }
            monitor-exit(r2)
            return r1
        L_0x0c03:
            r0 = move-exception
            r1 = r0
            monitor-exit(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBContentItem.insertAndRetrieveDbEntities(com.wbmd.qxcalculator.model.db.DaoSession, java.util.List):java.util.List");
    }

    public void addIsUpdatingWeakListener(OnUpdateListener onUpdateListener) {
        this.onUpdateListenerWeakReferences.add(new WeakReference(onUpdateListener));
    }

    public void removeIsUpdatingWeakListener(OnUpdateListener onUpdateListener) {
        Iterator<WeakReference<OnUpdateListener>> it = this.onUpdateListenerWeakReferences.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            if (next.get() != null && ((OnUpdateListener) next.get()).equals(onUpdateListener)) {
                it.remove();
                return;
            }
        }
    }

    public void setIsUpdating(boolean z) {
        this.isUpdating = z;
        for (WeakReference next : this.onUpdateListenerWeakReferences) {
            if (next.get() != null) {
                ((OnUpdateListener) next.get()).isUpdatingStatusChanged(this);
            }
        }
    }

    public boolean getIsUpdating() {
        return this.isUpdating;
    }

    public ContentItem.ContentItemListType getListType() {
        if (getCategories() == null || getCategories().isEmpty()) {
            if (getSplashPage() != null) {
                return ContentItem.ContentItemListType.SPLASH_PAGE;
            }
            return ContentItem.ContentItemListType.HIDDEN;
        } else if (getIdentifier().equalsIgnoreCase(DataManager.K_PRIVACY_POLICY_IDENTIFIER)) {
            return ContentItem.ContentItemListType.MENU_PRIVACY_POLICY;
        } else {
            if (getIdentifier().equalsIgnoreCase(DataManager.K_TERMS_OF_USE_IDENTIFIER)) {
                return ContentItem.ContentItemListType.MENU_TERMS_OF_USE;
            }
            boolean z = false;
            Iterator<DBCategory> it = getCategories().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().isMenuItem()) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (z) {
                return ContentItem.ContentItemListType.MENU;
            }
            return ContentItem.ContentItemListType.DEFAULT;
        }
    }

    public static boolean shouldIncludeItem(DBContentItem dBContentItem) {
        if (!DBPlatform.shouldIncludePlatforms(dBContentItem.getPlatforms()) || !DBFilter.shouldIncludeFilters(dBContentItem.getFilters())) {
            return false;
        }
        if (dBContentItem.getStartDate() != null && new Date().getTime() < dBContentItem.getStartDate().longValue()) {
            return false;
        }
        if (dBContentItem.getEndDate() == null || new Date().getTime() <= dBContentItem.getEndDate().longValue()) {
            return true;
        }
        return false;
    }

    public static DBRestriction getContentItemRestriction(DBContentItem dBContentItem) {
        if (dBContentItem.getRestrictions() != null && !dBContentItem.getRestrictions().isEmpty()) {
            for (DBRestriction next : dBContentItem.getRestrictions()) {
                if (DBPlatform.shouldIncludePlatforms(next.getPlatforms()) && DBFilter.shouldIncludeFilters(next.getFilters())) {
                    return next;
                }
            }
        }
        return null;
    }

    public boolean isMenuContentItem() {
        if (getCategories() == null || getCategories().isEmpty()) {
            return false;
        }
        for (DBCategory isMenuItem : getCategories()) {
            if (isMenuItem.isMenuItem()) {
                return true;
            }
        }
        return false;
    }

    public void setParentCalcName(String str) {
        this.parentCalcName = str;
    }

    public String getParentCalcName() {
        return this.parentCalcName;
    }
}
