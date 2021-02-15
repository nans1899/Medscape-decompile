package com.wbmd.qxcalculator.model.db.v6;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.share.internal.ShareConstants;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.List;

public class DBContentItemDao extends AbstractDao<DBContentItem, Long> {
    public static final String TABLENAME = "DBCONTENT_ITEM";
    private Query<DBContentItem> dBUser_ContentItemsQuery;
    private DaoSession daoSession;
    private String selectDeep;

    public static class Properties {
        public static final Property CalculatorId = new Property(23, Long.class, "calculatorId", false, "CALCULATOR_ID");
        public static final Property CommonFileId = new Property(29, Long.class, "commonFileId", false, "COMMON_FILE_ID");
        public static final Property CopiedFromContentItemId = new Property(21, String.class, "copiedFromContentItemId", false, "COPIED_FROM_CONTENT_ITEM_ID");
        public static final Property DateAdded = new Property(20, Long.class, "dateAdded", false, "DATE_ADDED");
        public static final Property DebugType = new Property(9, Integer.class, "debugType", false, "DEBUG_TYPE");
        public static final Property DefinitionId = new Property(24, Long.class, "definitionId", false, "DEFINITION_ID");
        public static final Property Description = new Property(3, String.class, "description", false, ShareConstants.DESCRIPTION);
        public static final Property EndDate = new Property(6, Long.class, "endDate", false, "END_DATE");
        public static final Property FileSourceId = new Property(25, Long.class, "fileSourceId", false, "FILE_SOURCE_ID");
        public static final Property Footer = new Property(4, String.class, "footer", false, "FOOTER");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property IsFavorite = new Property(16, Boolean.class, "isFavorite", false, "IS_FAVORITE");
        public static final Property IsNewlyAdded = new Property(17, Boolean.class, "isNewlyAdded", false, "IS_NEWLY_ADDED");
        public static final Property ItemFileZipId = new Property(28, Long.class, "itemFileZipId", false, "ITEM_FILE_ZIP_ID");
        public static final Property LastModifiedDate = new Property(8, Long.class, "lastModifiedDate", false, "LAST_MODIFIED_DATE");
        public static final Property LastUsedDate = new Property(15, Long.class, "lastUsedDate", false, "LAST_USED_DATE");
        public static final Property MoreInformation = new Property(10, String.class, "moreInformation", false, "MORE_INFORMATION");
        public static final Property MoreInformationSource = new Property(11, String.class, "moreInformationSource", false, "MORE_INFORMATION_SOURCE");
        public static final Property Name = new Property(2, String.class, "name", false, "NAME");
        public static final Property ReferenceBookId = new Property(27, Long.class, "referenceBookId", false, "REFERENCE_BOOK_ID");
        public static final Property RequiresUpdate = new Property(12, Boolean.class, "requiresUpdate", false, "REQUIRES_UPDATE");
        public static final Property ResourcesRequireUpdate = new Property(13, Boolean.class, "resourcesRequireUpdate", false, "RESOURCES_REQUIRE_UPDATE");
        public static final Property ShouldNotifiedBecomeAvailable = new Property(18, Boolean.class, "shouldNotifiedBecomeAvailable", false, "SHOULD_NOTIFIED_BECOME_AVAILABLE");
        public static final Property ShowAds = new Property(19, Boolean.class, "showAds", false, "SHOW_ADS");
        public static final Property SplashPageId = new Property(26, Long.class, "splashPageId", false, "SPLASH_PAGE_ID");
        public static final Property StartDate = new Property(5, Long.class, "startDate", false, "START_DATE");
        public static final Property TrackerId = new Property(7, String.class, "trackerId", false, "TRACKER_ID");
        public static final Property Type = new Property(14, String.class, "type", false, "TYPE");
        public static final Property UserId = new Property(22, Long.class, Constants.USER_TAG_KEY_USER_ID, false, "USER_ID");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBContentItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBContentItemDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBCONTENT_ITEM' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'NAME' TEXT,'DESCRIPTION' TEXT,'FOOTER' TEXT,'START_DATE' INTEGER,'END_DATE' INTEGER,'TRACKER_ID' TEXT,'LAST_MODIFIED_DATE' INTEGER,'DEBUG_TYPE' INTEGER,'MORE_INFORMATION' TEXT,'MORE_INFORMATION_SOURCE' TEXT,'REQUIRES_UPDATE' INTEGER,'RESOURCES_REQUIRE_UPDATE' INTEGER,'TYPE' TEXT,'LAST_USED_DATE' INTEGER,'IS_FAVORITE' INTEGER,'IS_NEWLY_ADDED' INTEGER,'SHOULD_NOTIFIED_BECOME_AVAILABLE' INTEGER,'SHOW_ADS' INTEGER,'DATE_ADDED' INTEGER,'COPIED_FROM_CONTENT_ITEM_ID' TEXT,'USER_ID' INTEGER,'CALCULATOR_ID' INTEGER,'DEFINITION_ID' INTEGER,'FILE_SOURCE_ID' INTEGER,'SPLASH_PAGE_ID' INTEGER,'REFERENCE_BOOK_ID' INTEGER,'ITEM_FILE_ZIP_ID' INTEGER,'COMMON_FILE_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBCONTENT_ITEM'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBContentItem dBContentItem) {
        sQLiteStatement.clearBindings();
        Long id = dBContentItem.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBContentItem.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String name = dBContentItem.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        String description = dBContentItem.getDescription();
        if (description != null) {
            sQLiteStatement.bindString(4, description);
        }
        String footer = dBContentItem.getFooter();
        if (footer != null) {
            sQLiteStatement.bindString(5, footer);
        }
        Long startDate = dBContentItem.getStartDate();
        if (startDate != null) {
            sQLiteStatement.bindLong(6, startDate.longValue());
        }
        Long endDate = dBContentItem.getEndDate();
        if (endDate != null) {
            sQLiteStatement.bindLong(7, endDate.longValue());
        }
        String trackerId = dBContentItem.getTrackerId();
        if (trackerId != null) {
            sQLiteStatement.bindString(8, trackerId);
        }
        Long lastModifiedDate = dBContentItem.getLastModifiedDate();
        if (lastModifiedDate != null) {
            sQLiteStatement.bindLong(9, lastModifiedDate.longValue());
        }
        Integer debugType = dBContentItem.getDebugType();
        if (debugType != null) {
            sQLiteStatement.bindLong(10, (long) debugType.intValue());
        }
        String moreInformation = dBContentItem.getMoreInformation();
        if (moreInformation != null) {
            sQLiteStatement.bindString(11, moreInformation);
        }
        String moreInformationSource = dBContentItem.getMoreInformationSource();
        if (moreInformationSource != null) {
            sQLiteStatement.bindString(12, moreInformationSource);
        }
        Boolean requiresUpdate = dBContentItem.getRequiresUpdate();
        long j = 1;
        if (requiresUpdate != null) {
            sQLiteStatement.bindLong(13, requiresUpdate.booleanValue() ? 1 : 0);
        }
        Boolean resourcesRequireUpdate = dBContentItem.getResourcesRequireUpdate();
        if (resourcesRequireUpdate != null) {
            sQLiteStatement.bindLong(14, resourcesRequireUpdate.booleanValue() ? 1 : 0);
        }
        String type = dBContentItem.getType();
        if (type != null) {
            sQLiteStatement.bindString(15, type);
        }
        Long lastUsedDate = dBContentItem.getLastUsedDate();
        if (lastUsedDate != null) {
            sQLiteStatement.bindLong(16, lastUsedDate.longValue());
        }
        Boolean isFavorite = dBContentItem.getIsFavorite();
        if (isFavorite != null) {
            sQLiteStatement.bindLong(17, isFavorite.booleanValue() ? 1 : 0);
        }
        Boolean isNewlyAdded = dBContentItem.getIsNewlyAdded();
        if (isNewlyAdded != null) {
            sQLiteStatement.bindLong(18, isNewlyAdded.booleanValue() ? 1 : 0);
        }
        Boolean shouldNotifiedBecomeAvailable = dBContentItem.getShouldNotifiedBecomeAvailable();
        if (shouldNotifiedBecomeAvailable != null) {
            sQLiteStatement.bindLong(19, shouldNotifiedBecomeAvailable.booleanValue() ? 1 : 0);
        }
        Boolean showAds = dBContentItem.getShowAds();
        if (showAds != null) {
            if (!showAds.booleanValue()) {
                j = 0;
            }
            sQLiteStatement.bindLong(20, j);
        }
        Long dateAdded = dBContentItem.getDateAdded();
        if (dateAdded != null) {
            sQLiteStatement.bindLong(21, dateAdded.longValue());
        }
        String copiedFromContentItemId = dBContentItem.getCopiedFromContentItemId();
        if (copiedFromContentItemId != null) {
            sQLiteStatement.bindString(22, copiedFromContentItemId);
        }
        Long userId = dBContentItem.getUserId();
        if (userId != null) {
            sQLiteStatement.bindLong(23, userId.longValue());
        }
        Long calculatorId = dBContentItem.getCalculatorId();
        if (calculatorId != null) {
            sQLiteStatement.bindLong(24, calculatorId.longValue());
        }
        Long definitionId = dBContentItem.getDefinitionId();
        if (definitionId != null) {
            sQLiteStatement.bindLong(25, definitionId.longValue());
        }
        Long fileSourceId = dBContentItem.getFileSourceId();
        if (fileSourceId != null) {
            sQLiteStatement.bindLong(26, fileSourceId.longValue());
        }
        Long splashPageId = dBContentItem.getSplashPageId();
        if (splashPageId != null) {
            sQLiteStatement.bindLong(27, splashPageId.longValue());
        }
        Long referenceBookId = dBContentItem.getReferenceBookId();
        if (referenceBookId != null) {
            sQLiteStatement.bindLong(28, referenceBookId.longValue());
        }
        Long itemFileZipId = dBContentItem.getItemFileZipId();
        if (itemFileZipId != null) {
            sQLiteStatement.bindLong(29, itemFileZipId.longValue());
        }
        Long commonFileId = dBContentItem.getCommonFileId();
        if (commonFileId != null) {
            sQLiteStatement.bindLong(30, commonFileId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBContentItem dBContentItem) {
        super.attachEntity(dBContentItem);
        dBContentItem.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBContentItem readEntity(Cursor cursor, int i) {
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        Boolean bool4;
        Boolean bool5;
        Boolean bool6;
        Cursor cursor2 = cursor;
        int i2 = i + 0;
        Long valueOf = cursor2.isNull(i2) ? null : Long.valueOf(cursor2.getLong(i2));
        int i3 = i + 1;
        String string = cursor2.isNull(i3) ? null : cursor2.getString(i3);
        int i4 = i + 2;
        String string2 = cursor2.isNull(i4) ? null : cursor2.getString(i4);
        int i5 = i + 3;
        String string3 = cursor2.isNull(i5) ? null : cursor2.getString(i5);
        int i6 = i + 4;
        String string4 = cursor2.isNull(i6) ? null : cursor2.getString(i6);
        int i7 = i + 5;
        Long valueOf2 = cursor2.isNull(i7) ? null : Long.valueOf(cursor2.getLong(i7));
        int i8 = i + 6;
        Long valueOf3 = cursor2.isNull(i8) ? null : Long.valueOf(cursor2.getLong(i8));
        int i9 = i + 7;
        String string5 = cursor2.isNull(i9) ? null : cursor2.getString(i9);
        int i10 = i + 8;
        Long valueOf4 = cursor2.isNull(i10) ? null : Long.valueOf(cursor2.getLong(i10));
        int i11 = i + 9;
        Integer valueOf5 = cursor2.isNull(i11) ? null : Integer.valueOf(cursor2.getInt(i11));
        int i12 = i + 10;
        String string6 = cursor2.isNull(i12) ? null : cursor2.getString(i12);
        int i13 = i + 11;
        String string7 = cursor2.isNull(i13) ? null : cursor2.getString(i13);
        int i14 = i + 12;
        boolean z = true;
        if (cursor2.isNull(i14)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor2.getShort(i14) != 0);
        }
        int i15 = i + 13;
        if (cursor2.isNull(i15)) {
            bool2 = null;
        } else {
            bool2 = Boolean.valueOf(cursor2.getShort(i15) != 0);
        }
        int i16 = i + 14;
        String string8 = cursor2.isNull(i16) ? null : cursor2.getString(i16);
        int i17 = i + 15;
        Long valueOf6 = cursor2.isNull(i17) ? null : Long.valueOf(cursor2.getLong(i17));
        int i18 = i + 16;
        if (cursor2.isNull(i18)) {
            bool3 = null;
        } else {
            bool3 = Boolean.valueOf(cursor2.getShort(i18) != 0);
        }
        int i19 = i + 17;
        if (cursor2.isNull(i19)) {
            bool4 = null;
        } else {
            bool4 = Boolean.valueOf(cursor2.getShort(i19) != 0);
        }
        int i20 = i + 18;
        if (cursor2.isNull(i20)) {
            bool5 = null;
        } else {
            bool5 = Boolean.valueOf(cursor2.getShort(i20) != 0);
        }
        int i21 = i + 19;
        if (cursor2.isNull(i21)) {
            bool6 = null;
        } else {
            if (cursor2.getShort(i21) == 0) {
                z = false;
            }
            bool6 = Boolean.valueOf(z);
        }
        int i22 = i + 20;
        Long valueOf7 = cursor2.isNull(i22) ? null : Long.valueOf(cursor2.getLong(i22));
        int i23 = i + 21;
        String string9 = cursor2.isNull(i23) ? null : cursor2.getString(i23);
        int i24 = i + 22;
        Long valueOf8 = cursor2.isNull(i24) ? null : Long.valueOf(cursor2.getLong(i24));
        int i25 = i + 23;
        Long valueOf9 = cursor2.isNull(i25) ? null : Long.valueOf(cursor2.getLong(i25));
        int i26 = i + 24;
        Long valueOf10 = cursor2.isNull(i26) ? null : Long.valueOf(cursor2.getLong(i26));
        int i27 = i + 25;
        Long valueOf11 = cursor2.isNull(i27) ? null : Long.valueOf(cursor2.getLong(i27));
        int i28 = i + 26;
        Long valueOf12 = cursor2.isNull(i28) ? null : Long.valueOf(cursor2.getLong(i28));
        int i29 = i + 27;
        Long valueOf13 = cursor2.isNull(i29) ? null : Long.valueOf(cursor2.getLong(i29));
        int i30 = i + 28;
        Long valueOf14 = cursor2.isNull(i30) ? null : Long.valueOf(cursor2.getLong(i30));
        int i31 = i + 29;
        return new DBContentItem(valueOf, string, string2, string3, string4, valueOf2, valueOf3, string5, valueOf4, valueOf5, string6, string7, bool, bool2, string8, valueOf6, bool3, bool4, bool5, bool6, valueOf7, string9, valueOf8, valueOf9, valueOf10, valueOf11, valueOf12, valueOf13, valueOf14, cursor2.isNull(i31) ? null : Long.valueOf(cursor2.getLong(i31)));
    }

    public void readEntity(Cursor cursor, DBContentItem dBContentItem, int i) {
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        Boolean bool4;
        Boolean bool5;
        Boolean bool6;
        int i2 = i + 0;
        Long l = null;
        dBContentItem.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBContentItem.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBContentItem.setName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBContentItem.setDescription(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBContentItem.setFooter(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBContentItem.setStartDate(cursor.isNull(i7) ? null : Long.valueOf(cursor.getLong(i7)));
        int i8 = i + 6;
        dBContentItem.setEndDate(cursor.isNull(i8) ? null : Long.valueOf(cursor.getLong(i8)));
        int i9 = i + 7;
        dBContentItem.setTrackerId(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        dBContentItem.setLastModifiedDate(cursor.isNull(i10) ? null : Long.valueOf(cursor.getLong(i10)));
        int i11 = i + 9;
        dBContentItem.setDebugType(cursor.isNull(i11) ? null : Integer.valueOf(cursor.getInt(i11)));
        int i12 = i + 10;
        dBContentItem.setMoreInformation(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 11;
        dBContentItem.setMoreInformationSource(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 12;
        boolean z = true;
        if (cursor.isNull(i14)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i14) != 0);
        }
        dBContentItem.setRequiresUpdate(bool);
        int i15 = i + 13;
        if (cursor.isNull(i15)) {
            bool2 = null;
        } else {
            bool2 = Boolean.valueOf(cursor.getShort(i15) != 0);
        }
        dBContentItem.setResourcesRequireUpdate(bool2);
        int i16 = i + 14;
        dBContentItem.setType(cursor.isNull(i16) ? null : cursor.getString(i16));
        int i17 = i + 15;
        dBContentItem.setLastUsedDate(cursor.isNull(i17) ? null : Long.valueOf(cursor.getLong(i17)));
        int i18 = i + 16;
        if (cursor.isNull(i18)) {
            bool3 = null;
        } else {
            bool3 = Boolean.valueOf(cursor.getShort(i18) != 0);
        }
        dBContentItem.setIsFavorite(bool3);
        int i19 = i + 17;
        if (cursor.isNull(i19)) {
            bool4 = null;
        } else {
            bool4 = Boolean.valueOf(cursor.getShort(i19) != 0);
        }
        dBContentItem.setIsNewlyAdded(bool4);
        int i20 = i + 18;
        if (cursor.isNull(i20)) {
            bool5 = null;
        } else {
            bool5 = Boolean.valueOf(cursor.getShort(i20) != 0);
        }
        dBContentItem.setShouldNotifiedBecomeAvailable(bool5);
        int i21 = i + 19;
        if (cursor.isNull(i21)) {
            bool6 = null;
        } else {
            if (cursor.getShort(i21) == 0) {
                z = false;
            }
            bool6 = Boolean.valueOf(z);
        }
        dBContentItem.setShowAds(bool6);
        int i22 = i + 20;
        dBContentItem.setDateAdded(cursor.isNull(i22) ? null : Long.valueOf(cursor.getLong(i22)));
        int i23 = i + 21;
        dBContentItem.setCopiedFromContentItemId(cursor.isNull(i23) ? null : cursor.getString(i23));
        int i24 = i + 22;
        dBContentItem.setUserId(cursor.isNull(i24) ? null : Long.valueOf(cursor.getLong(i24)));
        int i25 = i + 23;
        dBContentItem.setCalculatorId(cursor.isNull(i25) ? null : Long.valueOf(cursor.getLong(i25)));
        int i26 = i + 24;
        dBContentItem.setDefinitionId(cursor.isNull(i26) ? null : Long.valueOf(cursor.getLong(i26)));
        int i27 = i + 25;
        dBContentItem.setFileSourceId(cursor.isNull(i27) ? null : Long.valueOf(cursor.getLong(i27)));
        int i28 = i + 26;
        dBContentItem.setSplashPageId(cursor.isNull(i28) ? null : Long.valueOf(cursor.getLong(i28)));
        int i29 = i + 27;
        dBContentItem.setReferenceBookId(cursor.isNull(i29) ? null : Long.valueOf(cursor.getLong(i29)));
        int i30 = i + 28;
        dBContentItem.setItemFileZipId(cursor.isNull(i30) ? null : Long.valueOf(cursor.getLong(i30)));
        int i31 = i + 29;
        if (!cursor.isNull(i31)) {
            l = Long.valueOf(cursor.getLong(i31));
        }
        dBContentItem.setCommonFileId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBContentItem dBContentItem, long j) {
        dBContentItem.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBContentItem dBContentItem) {
        if (dBContentItem != null) {
            return dBContentItem.getId();
        }
        return null;
    }

    public List<DBContentItem> _queryDBUser_ContentItems(Long l) {
        synchronized (this) {
            if (this.dBUser_ContentItemsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserId.eq((Object) null), new WhereCondition[0]);
                this.dBUser_ContentItemsQuery = queryBuilder.build();
            }
        }
        Query<DBContentItem> forCurrentThread = this.dBUser_ContentItemsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }

    /* access modifiers changed from: protected */
    public String getSelectDeep() {
        if (this.selectDeep == null) {
            StringBuilder sb = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(sb, "T", getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T0", this.daoSession.getDBCalculatorDao().getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T1", this.daoSession.getDBDefinitionDao().getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T2", this.daoSession.getDBFileSourceDao().getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T3", this.daoSession.getDBSplashPageDao().getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T4", this.daoSession.getDBReferenceBookDao().getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T5", this.daoSession.getDBItemFileZipDao().getAllColumns());
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            SqlUtils.appendColumns(sb, "T6", this.daoSession.getDBCommonFileDao().getAllColumns());
            sb.append(" FROM DBCONTENT_ITEM T");
            sb.append(" LEFT JOIN DBCALCULATOR T0 ON T.'CALCULATOR_ID'=T0.'_id'");
            sb.append(" LEFT JOIN DBDEFINITION T1 ON T.'DEFINITION_ID'=T1.'_id'");
            sb.append(" LEFT JOIN DBFILE_SOURCE T2 ON T.'FILE_SOURCE_ID'=T2.'_id'");
            sb.append(" LEFT JOIN DBSPLASH_PAGE T3 ON T.'SPLASH_PAGE_ID'=T3.'_id'");
            sb.append(" LEFT JOIN DBREFERENCE_BOOK T4 ON T.'REFERENCE_BOOK_ID'=T4.'_id'");
            sb.append(" LEFT JOIN DBITEM_FILE_ZIP T5 ON T.'ITEM_FILE_ZIP_ID'=T5.'_id'");
            sb.append(" LEFT JOIN DBCOMMON_FILE T6 ON T.'COMMON_FILE_ID'=T6.'_id'");
            sb.append(' ');
            this.selectDeep = sb.toString();
        }
        return this.selectDeep;
    }

    /* access modifiers changed from: protected */
    public DBContentItem loadCurrentDeep(Cursor cursor, boolean z) {
        DBContentItem dBContentItem = (DBContentItem) loadCurrent(cursor, 0, z);
        int length = getAllColumns().length;
        dBContentItem.setCalculator((DBCalculator) loadCurrentOther(this.daoSession.getDBCalculatorDao(), cursor, length));
        int length2 = length + this.daoSession.getDBCalculatorDao().getAllColumns().length;
        dBContentItem.setDefinition((DBDefinition) loadCurrentOther(this.daoSession.getDBDefinitionDao(), cursor, length2));
        int length3 = length2 + this.daoSession.getDBDefinitionDao().getAllColumns().length;
        dBContentItem.setFileSource((DBFileSource) loadCurrentOther(this.daoSession.getDBFileSourceDao(), cursor, length3));
        int length4 = length3 + this.daoSession.getDBFileSourceDao().getAllColumns().length;
        dBContentItem.setSplashPage((DBSplashPage) loadCurrentOther(this.daoSession.getDBSplashPageDao(), cursor, length4));
        int length5 = length4 + this.daoSession.getDBSplashPageDao().getAllColumns().length;
        dBContentItem.setReferenceBook((DBReferenceBook) loadCurrentOther(this.daoSession.getDBReferenceBookDao(), cursor, length5));
        int length6 = length5 + this.daoSession.getDBReferenceBookDao().getAllColumns().length;
        dBContentItem.setItemFileZip((DBItemFileZip) loadCurrentOther(this.daoSession.getDBItemFileZipDao(), cursor, length6));
        dBContentItem.setCommonFile((DBCommonFile) loadCurrentOther(this.daoSession.getDBCommonFileDao(), cursor, length6 + this.daoSession.getDBItemFileZipDao().getAllColumns().length));
        return dBContentItem;
    }

    public DBContentItem loadDeep(Long l) {
        assertSinglePk();
        if (l == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(getSelectDeep());
        sb.append("WHERE ");
        SqlUtils.appendColumnsEqValue(sb, "T", getPkColumns());
        Cursor rawQuery = this.db.rawQuery(sb.toString(), new String[]{l.toString()});
        try {
            if (!rawQuery.moveToFirst()) {
                return null;
            }
            if (rawQuery.isLast()) {
                DBContentItem loadCurrentDeep = loadCurrentDeep(rawQuery, true);
                rawQuery.close();
                return loadCurrentDeep;
            }
            throw new IllegalStateException("Expected unique result, but count was " + rawQuery.getCount());
        } finally {
            rawQuery.close();
        }
    }

    public List<DBContentItem> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        ArrayList arrayList = new ArrayList(count);
        if (cursor.moveToFirst()) {
            if (this.identityScope != null) {
                this.identityScope.lock();
                this.identityScope.reserveRoom(count);
            }
            do {
                try {
                    arrayList.add(loadCurrentDeep(cursor, false));
                } finally {
                    if (this.identityScope != null) {
                        this.identityScope.unlock();
                    }
                }
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public List<DBContentItem> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }

    public List<DBContentItem> queryDeep(String str, String... strArr) {
        SQLiteDatabase sQLiteDatabase = this.db;
        return loadDeepAllAndCloseCursor(sQLiteDatabase.rawQuery(getSelectDeep() + str, strArr));
    }
}
