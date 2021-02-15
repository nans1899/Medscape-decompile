package com.wbmd.qxcalculator.model.db.v4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 4;

    public static void createAllTables(SQLiteDatabase sQLiteDatabase, boolean z) {
        DBUserDao.createTable(sQLiteDatabase, z);
        DBAdSettingDao.createTable(sQLiteDatabase, z);
        DBAdTargetingParameterDao.createTable(sQLiteDatabase, z);
        DBProfessionDao.createTable(sQLiteDatabase, z);
        DBSpecialtyDao.createTable(sQLiteDatabase, z);
        DBLocationDao.createTable(sQLiteDatabase, z);
        DBInstitutionDao.createTable(sQLiteDatabase, z);
        DBResourceFileDao.createTable(sQLiteDatabase, z);
        DBItemFileZipDao.createTable(sQLiteDatabase, z);
        DBCategoryDao.createTable(sQLiteDatabase, z);
        DBTagDao.createTable(sQLiteDatabase, z);
        DBExtraSectionDao.createTable(sQLiteDatabase, z);
        DBExtraSectionItemDao.createTable(sQLiteDatabase, z);
        DBMoreInfoSectionDao.createTable(sQLiteDatabase, z);
        DBMoreInfoSectionItemDao.createTable(sQLiteDatabase, z);
        DBPlatformDao.createTable(sQLiteDatabase, z);
        DBContentItemDao.createTable(sQLiteDatabase, z);
        DBFilterDao.createTable(sQLiteDatabase, z);
        DBFeaturedContentAdDao.createTable(sQLiteDatabase, z);
        DBCalculatorDao.createTable(sQLiteDatabase, z);
        DBQuestionDao.createTable(sQLiteDatabase, z);
        DBAnswerChoiceDao.createTable(sQLiteDatabase, z);
        DBUnitDao.createTable(sQLiteDatabase, z);
        DBResultDao.createTable(sQLiteDatabase, z);
        DBErrorCheckDao.createTable(sQLiteDatabase, z);
        DBLinkedCalculatorItemDao.createTable(sQLiteDatabase, z);
        DBDefinitionDao.createTable(sQLiteDatabase, z);
        DBDefinitionSectionDao.createTable(sQLiteDatabase, z);
        DBFileSourceDao.createTable(sQLiteDatabase, z);
        DBCommonFileDao.createTable(sQLiteDatabase, z);
        DBReferenceBookDao.createTable(sQLiteDatabase, z);
        DBReferenceBookSectionDao.createTable(sQLiteDatabase, z);
        DBReferenceBookSectionItemDao.createTable(sQLiteDatabase, z);
        DBPushNotificationDao.createTable(sQLiteDatabase, z);
    }

    public static void dropAllTables(SQLiteDatabase sQLiteDatabase, boolean z) {
        DBUserDao.dropTable(sQLiteDatabase, z);
        DBAdSettingDao.dropTable(sQLiteDatabase, z);
        DBAdTargetingParameterDao.dropTable(sQLiteDatabase, z);
        DBProfessionDao.dropTable(sQLiteDatabase, z);
        DBSpecialtyDao.dropTable(sQLiteDatabase, z);
        DBLocationDao.dropTable(sQLiteDatabase, z);
        DBInstitutionDao.dropTable(sQLiteDatabase, z);
        DBResourceFileDao.dropTable(sQLiteDatabase, z);
        DBItemFileZipDao.dropTable(sQLiteDatabase, z);
        DBCategoryDao.dropTable(sQLiteDatabase, z);
        DBTagDao.dropTable(sQLiteDatabase, z);
        DBExtraSectionDao.dropTable(sQLiteDatabase, z);
        DBExtraSectionItemDao.dropTable(sQLiteDatabase, z);
        DBMoreInfoSectionDao.dropTable(sQLiteDatabase, z);
        DBMoreInfoSectionItemDao.dropTable(sQLiteDatabase, z);
        DBPlatformDao.dropTable(sQLiteDatabase, z);
        DBContentItemDao.dropTable(sQLiteDatabase, z);
        DBFilterDao.dropTable(sQLiteDatabase, z);
        DBFeaturedContentAdDao.dropTable(sQLiteDatabase, z);
        DBCalculatorDao.dropTable(sQLiteDatabase, z);
        DBQuestionDao.dropTable(sQLiteDatabase, z);
        DBAnswerChoiceDao.dropTable(sQLiteDatabase, z);
        DBUnitDao.dropTable(sQLiteDatabase, z);
        DBResultDao.dropTable(sQLiteDatabase, z);
        DBErrorCheckDao.dropTable(sQLiteDatabase, z);
        DBLinkedCalculatorItemDao.dropTable(sQLiteDatabase, z);
        DBDefinitionDao.dropTable(sQLiteDatabase, z);
        DBDefinitionSectionDao.dropTable(sQLiteDatabase, z);
        DBFileSourceDao.dropTable(sQLiteDatabase, z);
        DBCommonFileDao.dropTable(sQLiteDatabase, z);
        DBReferenceBookDao.dropTable(sQLiteDatabase, z);
        DBReferenceBookSectionDao.dropTable(sQLiteDatabase, z);
        DBReferenceBookSectionItemDao.dropTable(sQLiteDatabase, z);
        DBPushNotificationDao.dropTable(sQLiteDatabase, z);
    }

    public static abstract class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 4);
        }

        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Log.i("greenDAO", "Creating tables for schema version 4");
            DaoMaster.createAllTables(sQLiteDatabase, false);
        }
    }

    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            DaoMaster.dropAllTables(sQLiteDatabase, true);
            onCreate(sQLiteDatabase);
        }
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase, 4);
        registerDaoClass(DBUserDao.class);
        registerDaoClass(DBAdSettingDao.class);
        registerDaoClass(DBAdTargetingParameterDao.class);
        registerDaoClass(DBProfessionDao.class);
        registerDaoClass(DBSpecialtyDao.class);
        registerDaoClass(DBLocationDao.class);
        registerDaoClass(DBInstitutionDao.class);
        registerDaoClass(DBResourceFileDao.class);
        registerDaoClass(DBItemFileZipDao.class);
        registerDaoClass(DBCategoryDao.class);
        registerDaoClass(DBTagDao.class);
        registerDaoClass(DBExtraSectionDao.class);
        registerDaoClass(DBExtraSectionItemDao.class);
        registerDaoClass(DBMoreInfoSectionDao.class);
        registerDaoClass(DBMoreInfoSectionItemDao.class);
        registerDaoClass(DBPlatformDao.class);
        registerDaoClass(DBContentItemDao.class);
        registerDaoClass(DBFilterDao.class);
        registerDaoClass(DBFeaturedContentAdDao.class);
        registerDaoClass(DBCalculatorDao.class);
        registerDaoClass(DBQuestionDao.class);
        registerDaoClass(DBAnswerChoiceDao.class);
        registerDaoClass(DBUnitDao.class);
        registerDaoClass(DBResultDao.class);
        registerDaoClass(DBErrorCheckDao.class);
        registerDaoClass(DBLinkedCalculatorItemDao.class);
        registerDaoClass(DBDefinitionDao.class);
        registerDaoClass(DBDefinitionSectionDao.class);
        registerDaoClass(DBFileSourceDao.class);
        registerDaoClass(DBCommonFileDao.class);
        registerDaoClass(DBReferenceBookDao.class);
        registerDaoClass(DBReferenceBookSectionDao.class);
        registerDaoClass(DBReferenceBookSectionItemDao.class);
        registerDaoClass(DBPushNotificationDao.class);
    }

    public DaoSession newSession() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    public DaoSession newSession(IdentityScopeType identityScopeType) {
        return new DaoSession(this.db, identityScopeType, this.daoConfigMap);
    }
}
