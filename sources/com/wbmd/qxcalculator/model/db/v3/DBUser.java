package com.wbmd.qxcalculator.model.db.v3;

import de.greenrobot.dao.DaoException;
import java.util.List;

public class DBUser {
    private DBAdSetting adSetting;
    private Long adSettingId;
    private Long adSetting__resolvedKey;
    private String appVersion;
    private Boolean autoEnterFirstQuestion;
    private Boolean bannerAdsEnabled;
    private List<DBContentItem> contentItems;
    private transient DaoSession daoSession;
    private String defaultUnits;
    private String defaultUnitsLength;
    private String defaultUnitsTemp;
    private String defaultUnitsWeight;
    private String description;
    private String email;
    private String emailEncoded;
    private String firstName;
    private Long id;
    private String identifier;
    private DBInstitution institution;
    private Long institutionId;
    private Long institution__resolvedKey;
    private Boolean isValidated;
    private Boolean isVerified;
    private String lastName;
    private Long lastRefreshTime;
    private String lastUsedTabId;
    private DBLocation location;
    private Long locationId;
    private Long location__resolvedKey;
    private transient DBUserDao myDao;
    private String nickname;
    private String npi;
    private String oldEmail;
    private Boolean pnEnabled;
    private DBProfession profession;
    private Long professionId;
    private Long profession__resolvedKey;
    private Boolean shouldDispatchUpdatesToServer;
    private Boolean shouldRegisterUserWithServer;
    private Boolean shouldSendRemovePnTokenRequest;
    private Boolean shouldShowPromptForAutoEnterFirstQuestion;
    private Long showAppReviewAtUsageCount;
    private Boolean showItemDescription;
    private Boolean showUpdatedPrivacyDialog;
    private Boolean showUpdatedTermsDialog;
    private DBSpecialty specialty;
    private Long specialtyId;
    private Long specialty__resolvedKey;
    private Long updateProfilePromptTime;
    private Long usageCount;
    private String zipCode;

    public DBUser() {
    }

    public DBUser(Long l) {
        this.id = l;
    }

    public DBUser(Long l, String str, String str2, String str3, String str4, String str5, String str6, Boolean bool, Boolean bool2, String str7, String str8, String str9, String str10, String str11, Long l2, String str12, String str13, String str14, String str15, Boolean bool3, Boolean bool4, Long l3, Long l4, String str16, Boolean bool5, Boolean bool6, Long l5, Boolean bool7, Boolean bool8, Boolean bool9, Boolean bool10, Boolean bool11, Boolean bool12, Long l6, Long l7, Long l8, Long l9, Long l10) {
        this.id = l;
        this.appVersion = str;
        this.oldEmail = str2;
        this.email = str3;
        this.emailEncoded = str4;
        this.firstName = str5;
        this.identifier = str6;
        this.isValidated = bool;
        this.isVerified = bool2;
        this.lastName = str7;
        this.nickname = str8;
        this.description = str9;
        this.npi = str10;
        this.zipCode = str11;
        this.lastRefreshTime = l2;
        this.defaultUnits = str12;
        this.defaultUnitsWeight = str13;
        this.defaultUnitsLength = str14;
        this.defaultUnitsTemp = str15;
        this.showUpdatedTermsDialog = bool3;
        this.showUpdatedPrivacyDialog = bool4;
        this.usageCount = l3;
        this.showAppReviewAtUsageCount = l4;
        this.lastUsedTabId = str16;
        this.shouldRegisterUserWithServer = bool5;
        this.shouldDispatchUpdatesToServer = bool6;
        this.updateProfilePromptTime = l5;
        this.autoEnterFirstQuestion = bool7;
        this.shouldShowPromptForAutoEnterFirstQuestion = bool8;
        this.pnEnabled = bool9;
        this.showItemDescription = bool10;
        this.bannerAdsEnabled = bool11;
        this.shouldSendRemovePnTokenRequest = bool12;
        this.institutionId = l6;
        this.locationId = l7;
        this.professionId = l8;
        this.specialtyId = l9;
        this.adSettingId = l10;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBUserDao() : null;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getOldEmail() {
        return this.oldEmail;
    }

    public void setOldEmail(String str) {
        this.oldEmail = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getEmailEncoded() {
        return this.emailEncoded;
    }

    public void setEmailEncoded(String str) {
        this.emailEncoded = str;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public Boolean getIsValidated() {
        return this.isValidated;
    }

    public void setIsValidated(Boolean bool) {
        this.isValidated = bool;
    }

    public Boolean getIsVerified() {
        return this.isVerified;
    }

    public void setIsVerified(Boolean bool) {
        this.isVerified = bool;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getNpi() {
        return this.npi;
    }

    public void setNpi(String str) {
        this.npi = str;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String str) {
        this.zipCode = str;
    }

    public Long getLastRefreshTime() {
        return this.lastRefreshTime;
    }

    public void setLastRefreshTime(Long l) {
        this.lastRefreshTime = l;
    }

    public String getDefaultUnits() {
        return this.defaultUnits;
    }

    public void setDefaultUnits(String str) {
        this.defaultUnits = str;
    }

    public String getDefaultUnitsWeight() {
        return this.defaultUnitsWeight;
    }

    public void setDefaultUnitsWeight(String str) {
        this.defaultUnitsWeight = str;
    }

    public String getDefaultUnitsLength() {
        return this.defaultUnitsLength;
    }

    public void setDefaultUnitsLength(String str) {
        this.defaultUnitsLength = str;
    }

    public String getDefaultUnitsTemp() {
        return this.defaultUnitsTemp;
    }

    public void setDefaultUnitsTemp(String str) {
        this.defaultUnitsTemp = str;
    }

    public Boolean getShowUpdatedTermsDialog() {
        return this.showUpdatedTermsDialog;
    }

    public void setShowUpdatedTermsDialog(Boolean bool) {
        this.showUpdatedTermsDialog = bool;
    }

    public Boolean getShowUpdatedPrivacyDialog() {
        return this.showUpdatedPrivacyDialog;
    }

    public void setShowUpdatedPrivacyDialog(Boolean bool) {
        this.showUpdatedPrivacyDialog = bool;
    }

    public Long getUsageCount() {
        return this.usageCount;
    }

    public void setUsageCount(Long l) {
        this.usageCount = l;
    }

    public Long getShowAppReviewAtUsageCount() {
        return this.showAppReviewAtUsageCount;
    }

    public void setShowAppReviewAtUsageCount(Long l) {
        this.showAppReviewAtUsageCount = l;
    }

    public String getLastUsedTabId() {
        return this.lastUsedTabId;
    }

    public void setLastUsedTabId(String str) {
        this.lastUsedTabId = str;
    }

    public Boolean getShouldRegisterUserWithServer() {
        return this.shouldRegisterUserWithServer;
    }

    public void setShouldRegisterUserWithServer(Boolean bool) {
        this.shouldRegisterUserWithServer = bool;
    }

    public Boolean getShouldDispatchUpdatesToServer() {
        return this.shouldDispatchUpdatesToServer;
    }

    public void setShouldDispatchUpdatesToServer(Boolean bool) {
        this.shouldDispatchUpdatesToServer = bool;
    }

    public Long getUpdateProfilePromptTime() {
        return this.updateProfilePromptTime;
    }

    public void setUpdateProfilePromptTime(Long l) {
        this.updateProfilePromptTime = l;
    }

    public Boolean getAutoEnterFirstQuestion() {
        return this.autoEnterFirstQuestion;
    }

    public void setAutoEnterFirstQuestion(Boolean bool) {
        this.autoEnterFirstQuestion = bool;
    }

    public Boolean getShouldShowPromptForAutoEnterFirstQuestion() {
        return this.shouldShowPromptForAutoEnterFirstQuestion;
    }

    public void setShouldShowPromptForAutoEnterFirstQuestion(Boolean bool) {
        this.shouldShowPromptForAutoEnterFirstQuestion = bool;
    }

    public Boolean getPnEnabled() {
        return this.pnEnabled;
    }

    public void setPnEnabled(Boolean bool) {
        this.pnEnabled = bool;
    }

    public Boolean getShowItemDescription() {
        return this.showItemDescription;
    }

    public void setShowItemDescription(Boolean bool) {
        this.showItemDescription = bool;
    }

    public Boolean getBannerAdsEnabled() {
        return this.bannerAdsEnabled;
    }

    public void setBannerAdsEnabled(Boolean bool) {
        this.bannerAdsEnabled = bool;
    }

    public Boolean getShouldSendRemovePnTokenRequest() {
        return this.shouldSendRemovePnTokenRequest;
    }

    public void setShouldSendRemovePnTokenRequest(Boolean bool) {
        this.shouldSendRemovePnTokenRequest = bool;
    }

    public Long getInstitutionId() {
        return this.institutionId;
    }

    public void setInstitutionId(Long l) {
        this.institutionId = l;
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Long l) {
        this.locationId = l;
    }

    public Long getProfessionId() {
        return this.professionId;
    }

    public void setProfessionId(Long l) {
        this.professionId = l;
    }

    public Long getSpecialtyId() {
        return this.specialtyId;
    }

    public void setSpecialtyId(Long l) {
        this.specialtyId = l;
    }

    public Long getAdSettingId() {
        return this.adSettingId;
    }

    public void setAdSettingId(Long l) {
        this.adSettingId = l;
    }

    public DBInstitution getInstitution() {
        Long l = this.institutionId;
        Long l2 = this.institution__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBInstitution dBInstitution = (DBInstitution) daoSession2.getDBInstitutionDao().load(l);
                synchronized (this) {
                    this.institution = dBInstitution;
                    this.institution__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.institution;
    }

    public void setInstitution(DBInstitution dBInstitution) {
        Long l;
        synchronized (this) {
            this.institution = dBInstitution;
            if (dBInstitution == null) {
                l = null;
            } else {
                l = dBInstitution.getId();
            }
            this.institutionId = l;
            this.institution__resolvedKey = l;
        }
    }

    public DBLocation getLocation() {
        Long l = this.locationId;
        Long l2 = this.location__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBLocation dBLocation = (DBLocation) daoSession2.getDBLocationDao().load(l);
                synchronized (this) {
                    this.location = dBLocation;
                    this.location__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.location;
    }

    public void setLocation(DBLocation dBLocation) {
        Long l;
        synchronized (this) {
            this.location = dBLocation;
            if (dBLocation == null) {
                l = null;
            } else {
                l = dBLocation.getId();
            }
            this.locationId = l;
            this.location__resolvedKey = l;
        }
    }

    public DBProfession getProfession() {
        Long l = this.professionId;
        Long l2 = this.profession__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBProfession dBProfession = (DBProfession) daoSession2.getDBProfessionDao().load(l);
                synchronized (this) {
                    this.profession = dBProfession;
                    this.profession__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.profession;
    }

    public void setProfession(DBProfession dBProfession) {
        Long l;
        synchronized (this) {
            this.profession = dBProfession;
            if (dBProfession == null) {
                l = null;
            } else {
                l = dBProfession.getId();
            }
            this.professionId = l;
            this.profession__resolvedKey = l;
        }
    }

    public DBSpecialty getSpecialty() {
        Long l = this.specialtyId;
        Long l2 = this.specialty__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBSpecialty dBSpecialty = (DBSpecialty) daoSession2.getDBSpecialtyDao().load(l);
                synchronized (this) {
                    this.specialty = dBSpecialty;
                    this.specialty__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.specialty;
    }

    public void setSpecialty(DBSpecialty dBSpecialty) {
        Long l;
        synchronized (this) {
            this.specialty = dBSpecialty;
            if (dBSpecialty == null) {
                l = null;
            } else {
                l = dBSpecialty.getId();
            }
            this.specialtyId = l;
            this.specialty__resolvedKey = l;
        }
    }

    public DBAdSetting getAdSetting() {
        Long l = this.adSettingId;
        Long l2 = this.adSetting__resolvedKey;
        if (l2 == null || !l2.equals(l)) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                DBAdSetting dBAdSetting = (DBAdSetting) daoSession2.getDBAdSettingDao().load(l);
                synchronized (this) {
                    this.adSetting = dBAdSetting;
                    this.adSetting__resolvedKey = l;
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.adSetting;
    }

    public void setAdSetting(DBAdSetting dBAdSetting) {
        Long l;
        synchronized (this) {
            this.adSetting = dBAdSetting;
            if (dBAdSetting == null) {
                l = null;
            } else {
                l = dBAdSetting.getId();
            }
            this.adSettingId = l;
            this.adSetting__resolvedKey = l;
        }
    }

    public List<DBContentItem> getContentItems() {
        if (this.contentItems == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBContentItem> _queryDBUser_ContentItems = daoSession2.getDBContentItemDao()._queryDBUser_ContentItems(this.id);
                synchronized (this) {
                    if (this.contentItems == null) {
                        this.contentItems = _queryDBUser_ContentItems;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.contentItems;
    }

    public synchronized void resetContentItems() {
        this.contentItems = null;
    }

    public void delete() {
        DBUserDao dBUserDao = this.myDao;
        if (dBUserDao != null) {
            dBUserDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBUserDao dBUserDao = this.myDao;
        if (dBUserDao != null) {
            dBUserDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBUserDao dBUserDao = this.myDao;
        if (dBUserDao != null) {
            dBUserDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }
}
