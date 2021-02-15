package com.wbmd.qxcalculator.model.db.v6;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBContentItem {
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
    private DBItemFileZip itemFileZip;
    private Long itemFileZipId;
    private Long itemFileZip__resolvedKey;
    private Long lastModifiedDate;
    private Long lastUsedDate;
    private List<DBMoreInfoSection> moreInfoSections;
    private String moreInformation;
    private String moreInformationSource;
    private transient DBContentItemDao myDao;
    private String name;
    private List<DBPlatform> platforms;
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

    public DBContentItem() {
    }

    public DBContentItem(Long l) {
        this.id = l;
    }

    public DBContentItem(Long l, String str, String str2, String str3, String str4, Long l2, Long l3, String str5, Long l4, Integer num, String str6, String str7, Boolean bool, Boolean bool2, String str8, Long l5, Boolean bool3, Boolean bool4, Boolean bool5, Boolean bool6, Long l6, String str9, Long l7, Long l8, Long l9, Long l10, Long l11, Long l12, Long l13, Long l14) {
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
}
