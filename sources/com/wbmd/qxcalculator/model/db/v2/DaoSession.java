package com.wbmd.qxcalculator.model.db.v2;

import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;
import java.util.Map;

public class DaoSession extends AbstractDaoSession {
    private final DBAdSettingDao dBAdSettingDao = new DBAdSettingDao(this.dBAdSettingDaoConfig, this);
    private final DaoConfig dBAdSettingDaoConfig;
    private final DBAdTargetingParameterDao dBAdTargetingParameterDao = new DBAdTargetingParameterDao(this.dBAdTargetingParameterDaoConfig, this);
    private final DaoConfig dBAdTargetingParameterDaoConfig;
    private final DBAnswerChoiceDao dBAnswerChoiceDao = new DBAnswerChoiceDao(this.dBAnswerChoiceDaoConfig, this);
    private final DaoConfig dBAnswerChoiceDaoConfig;
    private final DBCalculatorDao dBCalculatorDao = new DBCalculatorDao(this.dBCalculatorDaoConfig, this);
    private final DaoConfig dBCalculatorDaoConfig;
    private final DBCategoryDao dBCategoryDao = new DBCategoryDao(this.dBCategoryDaoConfig, this);
    private final DaoConfig dBCategoryDaoConfig;
    private final DBCommonFileDao dBCommonFileDao = new DBCommonFileDao(this.dBCommonFileDaoConfig, this);
    private final DaoConfig dBCommonFileDaoConfig;
    private final DBContentItemDao dBContentItemDao = new DBContentItemDao(this.dBContentItemDaoConfig, this);
    private final DaoConfig dBContentItemDaoConfig;
    private final DBDefinitionDao dBDefinitionDao = new DBDefinitionDao(this.dBDefinitionDaoConfig, this);
    private final DaoConfig dBDefinitionDaoConfig;
    private final DBDefinitionSectionDao dBDefinitionSectionDao = new DBDefinitionSectionDao(this.dBDefinitionSectionDaoConfig, this);
    private final DaoConfig dBDefinitionSectionDaoConfig;
    private final DBErrorCheckDao dBErrorCheckDao = new DBErrorCheckDao(this.dBErrorCheckDaoConfig, this);
    private final DaoConfig dBErrorCheckDaoConfig;
    private final DBExtraSectionDao dBExtraSectionDao = new DBExtraSectionDao(this.dBExtraSectionDaoConfig, this);
    private final DaoConfig dBExtraSectionDaoConfig;
    private final DBExtraSectionItemDao dBExtraSectionItemDao = new DBExtraSectionItemDao(this.dBExtraSectionItemDaoConfig, this);
    private final DaoConfig dBExtraSectionItemDaoConfig;
    private final DBFeaturedContentAdDao dBFeaturedContentAdDao = new DBFeaturedContentAdDao(this.dBFeaturedContentAdDaoConfig, this);
    private final DaoConfig dBFeaturedContentAdDaoConfig;
    private final DBFileSourceDao dBFileSourceDao = new DBFileSourceDao(this.dBFileSourceDaoConfig, this);
    private final DaoConfig dBFileSourceDaoConfig;
    private final DBFilterDao dBFilterDao = new DBFilterDao(this.dBFilterDaoConfig, this);
    private final DaoConfig dBFilterDaoConfig;
    private final DBInstitutionDao dBInstitutionDao = new DBInstitutionDao(this.dBInstitutionDaoConfig, this);
    private final DaoConfig dBInstitutionDaoConfig;
    private final DBItemFileZipDao dBItemFileZipDao = new DBItemFileZipDao(this.dBItemFileZipDaoConfig, this);
    private final DaoConfig dBItemFileZipDaoConfig;
    private final DBLinkedCalculatorItemDao dBLinkedCalculatorItemDao = new DBLinkedCalculatorItemDao(this.dBLinkedCalculatorItemDaoConfig, this);
    private final DaoConfig dBLinkedCalculatorItemDaoConfig;
    private final DBLocationDao dBLocationDao = new DBLocationDao(this.dBLocationDaoConfig, this);
    private final DaoConfig dBLocationDaoConfig;
    private final DBMoreInfoSectionDao dBMoreInfoSectionDao = new DBMoreInfoSectionDao(this.dBMoreInfoSectionDaoConfig, this);
    private final DaoConfig dBMoreInfoSectionDaoConfig;
    private final DBMoreInfoSectionItemDao dBMoreInfoSectionItemDao = new DBMoreInfoSectionItemDao(this.dBMoreInfoSectionItemDaoConfig, this);
    private final DaoConfig dBMoreInfoSectionItemDaoConfig;
    private final DBPlatformDao dBPlatformDao = new DBPlatformDao(this.dBPlatformDaoConfig, this);
    private final DaoConfig dBPlatformDaoConfig;
    private final DBProfessionDao dBProfessionDao = new DBProfessionDao(this.dBProfessionDaoConfig, this);
    private final DaoConfig dBProfessionDaoConfig;
    private final DBQuestionDao dBQuestionDao = new DBQuestionDao(this.dBQuestionDaoConfig, this);
    private final DaoConfig dBQuestionDaoConfig;
    private final DBReferenceBookDao dBReferenceBookDao = new DBReferenceBookDao(this.dBReferenceBookDaoConfig, this);
    private final DaoConfig dBReferenceBookDaoConfig;
    private final DBReferenceBookSectionDao dBReferenceBookSectionDao = new DBReferenceBookSectionDao(this.dBReferenceBookSectionDaoConfig, this);
    private final DaoConfig dBReferenceBookSectionDaoConfig;
    private final DBReferenceBookSectionItemDao dBReferenceBookSectionItemDao = new DBReferenceBookSectionItemDao(this.dBReferenceBookSectionItemDaoConfig, this);
    private final DaoConfig dBReferenceBookSectionItemDaoConfig;
    private final DBResourceFileDao dBResourceFileDao = new DBResourceFileDao(this.dBResourceFileDaoConfig, this);
    private final DaoConfig dBResourceFileDaoConfig;
    private final DBResultDao dBResultDao = new DBResultDao(this.dBResultDaoConfig, this);
    private final DaoConfig dBResultDaoConfig;
    private final DBSpecialtyDao dBSpecialtyDao = new DBSpecialtyDao(this.dBSpecialtyDaoConfig, this);
    private final DaoConfig dBSpecialtyDaoConfig;
    private final DBTagDao dBTagDao = new DBTagDao(this.dBTagDaoConfig, this);
    private final DaoConfig dBTagDaoConfig;
    private final DBUnitDao dBUnitDao = new DBUnitDao(this.dBUnitDaoConfig, this);
    private final DaoConfig dBUnitDaoConfig;
    private final DBUserDao dBUserDao = new DBUserDao(this.dBUserDaoConfig, this);
    private final DaoConfig dBUserDaoConfig;

    public DaoSession(SQLiteDatabase sQLiteDatabase, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(sQLiteDatabase);
        DaoConfig clone = map.get(DBUserDao.class).clone();
        this.dBUserDaoConfig = clone;
        clone.initIdentityScope(identityScopeType);
        DaoConfig clone2 = map.get(DBAdSettingDao.class).clone();
        this.dBAdSettingDaoConfig = clone2;
        clone2.initIdentityScope(identityScopeType);
        DaoConfig clone3 = map.get(DBAdTargetingParameterDao.class).clone();
        this.dBAdTargetingParameterDaoConfig = clone3;
        clone3.initIdentityScope(identityScopeType);
        DaoConfig clone4 = map.get(DBProfessionDao.class).clone();
        this.dBProfessionDaoConfig = clone4;
        clone4.initIdentityScope(identityScopeType);
        DaoConfig clone5 = map.get(DBSpecialtyDao.class).clone();
        this.dBSpecialtyDaoConfig = clone5;
        clone5.initIdentityScope(identityScopeType);
        DaoConfig clone6 = map.get(DBLocationDao.class).clone();
        this.dBLocationDaoConfig = clone6;
        clone6.initIdentityScope(identityScopeType);
        DaoConfig clone7 = map.get(DBInstitutionDao.class).clone();
        this.dBInstitutionDaoConfig = clone7;
        clone7.initIdentityScope(identityScopeType);
        DaoConfig clone8 = map.get(DBResourceFileDao.class).clone();
        this.dBResourceFileDaoConfig = clone8;
        clone8.initIdentityScope(identityScopeType);
        DaoConfig clone9 = map.get(DBItemFileZipDao.class).clone();
        this.dBItemFileZipDaoConfig = clone9;
        clone9.initIdentityScope(identityScopeType);
        DaoConfig clone10 = map.get(DBCategoryDao.class).clone();
        this.dBCategoryDaoConfig = clone10;
        clone10.initIdentityScope(identityScopeType);
        DaoConfig clone11 = map.get(DBTagDao.class).clone();
        this.dBTagDaoConfig = clone11;
        clone11.initIdentityScope(identityScopeType);
        DaoConfig clone12 = map.get(DBExtraSectionDao.class).clone();
        this.dBExtraSectionDaoConfig = clone12;
        clone12.initIdentityScope(identityScopeType);
        DaoConfig clone13 = map.get(DBExtraSectionItemDao.class).clone();
        this.dBExtraSectionItemDaoConfig = clone13;
        clone13.initIdentityScope(identityScopeType);
        DaoConfig clone14 = map.get(DBMoreInfoSectionDao.class).clone();
        this.dBMoreInfoSectionDaoConfig = clone14;
        clone14.initIdentityScope(identityScopeType);
        DaoConfig clone15 = map.get(DBMoreInfoSectionItemDao.class).clone();
        this.dBMoreInfoSectionItemDaoConfig = clone15;
        clone15.initIdentityScope(identityScopeType);
        DaoConfig clone16 = map.get(DBPlatformDao.class).clone();
        this.dBPlatformDaoConfig = clone16;
        clone16.initIdentityScope(identityScopeType);
        DaoConfig clone17 = map.get(DBContentItemDao.class).clone();
        this.dBContentItemDaoConfig = clone17;
        clone17.initIdentityScope(identityScopeType);
        DaoConfig clone18 = map.get(DBFilterDao.class).clone();
        this.dBFilterDaoConfig = clone18;
        clone18.initIdentityScope(identityScopeType);
        DaoConfig clone19 = map.get(DBFeaturedContentAdDao.class).clone();
        this.dBFeaturedContentAdDaoConfig = clone19;
        clone19.initIdentityScope(identityScopeType);
        DaoConfig clone20 = map.get(DBCalculatorDao.class).clone();
        this.dBCalculatorDaoConfig = clone20;
        clone20.initIdentityScope(identityScopeType);
        DaoConfig clone21 = map.get(DBQuestionDao.class).clone();
        this.dBQuestionDaoConfig = clone21;
        clone21.initIdentityScope(identityScopeType);
        DaoConfig clone22 = map.get(DBAnswerChoiceDao.class).clone();
        this.dBAnswerChoiceDaoConfig = clone22;
        clone22.initIdentityScope(identityScopeType);
        DaoConfig clone23 = map.get(DBUnitDao.class).clone();
        this.dBUnitDaoConfig = clone23;
        clone23.initIdentityScope(identityScopeType);
        DaoConfig clone24 = map.get(DBResultDao.class).clone();
        this.dBResultDaoConfig = clone24;
        clone24.initIdentityScope(identityScopeType);
        DaoConfig clone25 = map.get(DBErrorCheckDao.class).clone();
        this.dBErrorCheckDaoConfig = clone25;
        clone25.initIdentityScope(identityScopeType);
        DaoConfig clone26 = map.get(DBLinkedCalculatorItemDao.class).clone();
        this.dBLinkedCalculatorItemDaoConfig = clone26;
        clone26.initIdentityScope(identityScopeType);
        DaoConfig clone27 = map.get(DBDefinitionDao.class).clone();
        this.dBDefinitionDaoConfig = clone27;
        clone27.initIdentityScope(identityScopeType);
        DaoConfig clone28 = map.get(DBDefinitionSectionDao.class).clone();
        this.dBDefinitionSectionDaoConfig = clone28;
        clone28.initIdentityScope(identityScopeType);
        DaoConfig clone29 = map.get(DBFileSourceDao.class).clone();
        this.dBFileSourceDaoConfig = clone29;
        clone29.initIdentityScope(identityScopeType);
        DaoConfig clone30 = map.get(DBCommonFileDao.class).clone();
        this.dBCommonFileDaoConfig = clone30;
        clone30.initIdentityScope(identityScopeType);
        DaoConfig clone31 = map.get(DBReferenceBookDao.class).clone();
        this.dBReferenceBookDaoConfig = clone31;
        clone31.initIdentityScope(identityScopeType);
        DaoConfig clone32 = map.get(DBReferenceBookSectionDao.class).clone();
        this.dBReferenceBookSectionDaoConfig = clone32;
        clone32.initIdentityScope(identityScopeType);
        DaoConfig clone33 = map.get(DBReferenceBookSectionItemDao.class).clone();
        this.dBReferenceBookSectionItemDaoConfig = clone33;
        clone33.initIdentityScope(identityScopeType);
        registerDao(DBUser.class, this.dBUserDao);
        registerDao(DBAdSetting.class, this.dBAdSettingDao);
        registerDao(DBAdTargetingParameter.class, this.dBAdTargetingParameterDao);
        registerDao(DBProfession.class, this.dBProfessionDao);
        registerDao(DBSpecialty.class, this.dBSpecialtyDao);
        registerDao(DBLocation.class, this.dBLocationDao);
        registerDao(DBInstitution.class, this.dBInstitutionDao);
        registerDao(DBResourceFile.class, this.dBResourceFileDao);
        registerDao(DBItemFileZip.class, this.dBItemFileZipDao);
        registerDao(DBCategory.class, this.dBCategoryDao);
        registerDao(DBTag.class, this.dBTagDao);
        registerDao(DBExtraSection.class, this.dBExtraSectionDao);
        registerDao(DBExtraSectionItem.class, this.dBExtraSectionItemDao);
        registerDao(DBMoreInfoSection.class, this.dBMoreInfoSectionDao);
        registerDao(DBMoreInfoSectionItem.class, this.dBMoreInfoSectionItemDao);
        registerDao(DBPlatform.class, this.dBPlatformDao);
        registerDao(DBContentItem.class, this.dBContentItemDao);
        registerDao(DBFilter.class, this.dBFilterDao);
        registerDao(DBFeaturedContentAd.class, this.dBFeaturedContentAdDao);
        registerDao(DBCalculator.class, this.dBCalculatorDao);
        registerDao(DBQuestion.class, this.dBQuestionDao);
        registerDao(DBAnswerChoice.class, this.dBAnswerChoiceDao);
        registerDao(DBUnit.class, this.dBUnitDao);
        registerDao(DBResult.class, this.dBResultDao);
        registerDao(DBErrorCheck.class, this.dBErrorCheckDao);
        registerDao(DBLinkedCalculatorItem.class, this.dBLinkedCalculatorItemDao);
        registerDao(DBDefinition.class, this.dBDefinitionDao);
        registerDao(DBDefinitionSection.class, this.dBDefinitionSectionDao);
        registerDao(DBFileSource.class, this.dBFileSourceDao);
        registerDao(DBCommonFile.class, this.dBCommonFileDao);
        registerDao(DBReferenceBook.class, this.dBReferenceBookDao);
        registerDao(DBReferenceBookSection.class, this.dBReferenceBookSectionDao);
        registerDao(DBReferenceBookSectionItem.class, this.dBReferenceBookSectionItemDao);
    }

    public void clear() {
        this.dBUserDaoConfig.getIdentityScope().clear();
        this.dBAdSettingDaoConfig.getIdentityScope().clear();
        this.dBAdTargetingParameterDaoConfig.getIdentityScope().clear();
        this.dBProfessionDaoConfig.getIdentityScope().clear();
        this.dBSpecialtyDaoConfig.getIdentityScope().clear();
        this.dBLocationDaoConfig.getIdentityScope().clear();
        this.dBInstitutionDaoConfig.getIdentityScope().clear();
        this.dBResourceFileDaoConfig.getIdentityScope().clear();
        this.dBItemFileZipDaoConfig.getIdentityScope().clear();
        this.dBCategoryDaoConfig.getIdentityScope().clear();
        this.dBTagDaoConfig.getIdentityScope().clear();
        this.dBExtraSectionDaoConfig.getIdentityScope().clear();
        this.dBExtraSectionItemDaoConfig.getIdentityScope().clear();
        this.dBMoreInfoSectionDaoConfig.getIdentityScope().clear();
        this.dBMoreInfoSectionItemDaoConfig.getIdentityScope().clear();
        this.dBPlatformDaoConfig.getIdentityScope().clear();
        this.dBContentItemDaoConfig.getIdentityScope().clear();
        this.dBFilterDaoConfig.getIdentityScope().clear();
        this.dBFeaturedContentAdDaoConfig.getIdentityScope().clear();
        this.dBCalculatorDaoConfig.getIdentityScope().clear();
        this.dBQuestionDaoConfig.getIdentityScope().clear();
        this.dBAnswerChoiceDaoConfig.getIdentityScope().clear();
        this.dBUnitDaoConfig.getIdentityScope().clear();
        this.dBResultDaoConfig.getIdentityScope().clear();
        this.dBErrorCheckDaoConfig.getIdentityScope().clear();
        this.dBLinkedCalculatorItemDaoConfig.getIdentityScope().clear();
        this.dBDefinitionDaoConfig.getIdentityScope().clear();
        this.dBDefinitionSectionDaoConfig.getIdentityScope().clear();
        this.dBFileSourceDaoConfig.getIdentityScope().clear();
        this.dBCommonFileDaoConfig.getIdentityScope().clear();
        this.dBReferenceBookDaoConfig.getIdentityScope().clear();
        this.dBReferenceBookSectionDaoConfig.getIdentityScope().clear();
        this.dBReferenceBookSectionItemDaoConfig.getIdentityScope().clear();
    }

    public DBUserDao getDBUserDao() {
        return this.dBUserDao;
    }

    public DBAdSettingDao getDBAdSettingDao() {
        return this.dBAdSettingDao;
    }

    public DBAdTargetingParameterDao getDBAdTargetingParameterDao() {
        return this.dBAdTargetingParameterDao;
    }

    public DBProfessionDao getDBProfessionDao() {
        return this.dBProfessionDao;
    }

    public DBSpecialtyDao getDBSpecialtyDao() {
        return this.dBSpecialtyDao;
    }

    public DBLocationDao getDBLocationDao() {
        return this.dBLocationDao;
    }

    public DBInstitutionDao getDBInstitutionDao() {
        return this.dBInstitutionDao;
    }

    public DBResourceFileDao getDBResourceFileDao() {
        return this.dBResourceFileDao;
    }

    public DBItemFileZipDao getDBItemFileZipDao() {
        return this.dBItemFileZipDao;
    }

    public DBCategoryDao getDBCategoryDao() {
        return this.dBCategoryDao;
    }

    public DBTagDao getDBTagDao() {
        return this.dBTagDao;
    }

    public DBExtraSectionDao getDBExtraSectionDao() {
        return this.dBExtraSectionDao;
    }

    public DBExtraSectionItemDao getDBExtraSectionItemDao() {
        return this.dBExtraSectionItemDao;
    }

    public DBMoreInfoSectionDao getDBMoreInfoSectionDao() {
        return this.dBMoreInfoSectionDao;
    }

    public DBMoreInfoSectionItemDao getDBMoreInfoSectionItemDao() {
        return this.dBMoreInfoSectionItemDao;
    }

    public DBPlatformDao getDBPlatformDao() {
        return this.dBPlatformDao;
    }

    public DBContentItemDao getDBContentItemDao() {
        return this.dBContentItemDao;
    }

    public DBFilterDao getDBFilterDao() {
        return this.dBFilterDao;
    }

    public DBFeaturedContentAdDao getDBFeaturedContentAdDao() {
        return this.dBFeaturedContentAdDao;
    }

    public DBCalculatorDao getDBCalculatorDao() {
        return this.dBCalculatorDao;
    }

    public DBQuestionDao getDBQuestionDao() {
        return this.dBQuestionDao;
    }

    public DBAnswerChoiceDao getDBAnswerChoiceDao() {
        return this.dBAnswerChoiceDao;
    }

    public DBUnitDao getDBUnitDao() {
        return this.dBUnitDao;
    }

    public DBResultDao getDBResultDao() {
        return this.dBResultDao;
    }

    public DBErrorCheckDao getDBErrorCheckDao() {
        return this.dBErrorCheckDao;
    }

    public DBLinkedCalculatorItemDao getDBLinkedCalculatorItemDao() {
        return this.dBLinkedCalculatorItemDao;
    }

    public DBDefinitionDao getDBDefinitionDao() {
        return this.dBDefinitionDao;
    }

    public DBDefinitionSectionDao getDBDefinitionSectionDao() {
        return this.dBDefinitionSectionDao;
    }

    public DBFileSourceDao getDBFileSourceDao() {
        return this.dBFileSourceDao;
    }

    public DBCommonFileDao getDBCommonFileDao() {
        return this.dBCommonFileDao;
    }

    public DBReferenceBookDao getDBReferenceBookDao() {
        return this.dBReferenceBookDao;
    }

    public DBReferenceBookSectionDao getDBReferenceBookSectionDao() {
        return this.dBReferenceBookSectionDao;
    }

    public DBReferenceBookSectionItemDao getDBReferenceBookSectionItemDao() {
        return this.dBReferenceBookSectionItemDao;
    }
}
