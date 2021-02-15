package com.wbmd.qxcalculator.model.db.v1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import de.greenrobot.dao.query.WhereCondition;
import java.util.List;

public class DBResourceFileDao extends AbstractDao<DBResourceFile, Long> {
    public static final String TABLENAME = "DBRESOURCE_FILE";
    private Query<DBResourceFile> dBContentItem_ResourceFilesQuery;

    public static class Properties {
        public static final Property AspectRatio = new Property(12, Double.class, "aspectRatio", false, "ASPECT_RATIO");
        public static final Property BaseName = new Property(9, String.class, "baseName", false, "BASE_NAME");
        public static final Property Content = new Property(14, String.class, "content", false, "CONTENT");
        public static final Property ContentItemId = new Property(15, Long.class, "contentItemId", false, "CONTENT_ITEM_ID");
        public static final Property ContentItemIdentifier = new Property(13, String.class, "contentItemIdentifier", false, "CONTENT_ITEM_IDENTIFIER");
        public static final Property DeviceType = new Property(10, String.class, "deviceType", false, "DEVICE_TYPE");
        public static final Property DipHeight = new Property(8, Double.class, "dipHeight", false, "DIP_HEIGHT");
        public static final Property DipWidth = new Property(7, Double.class, "dipWidth", false, "DIP_WIDTH");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property LastModifiedDate = new Property(3, Long.class, "lastModifiedDate", false, "LAST_MODIFIED_DATE");
        public static final Property Name = new Property(2, String.class, "name", false, "NAME");
        public static final Property ScaleFactor = new Property(11, Double.class, "scaleFactor", false, "SCALE_FACTOR");
        public static final Property Size = new Property(4, Long.class, "size", false, "SIZE");
        public static final Property Src = new Property(5, String.class, "src", false, "SRC");
        public static final Property Type = new Property(6, String.class, "type", false, "TYPE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBResourceFileDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBResourceFileDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBRESOURCE_FILE' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'NAME' TEXT,'LAST_MODIFIED_DATE' INTEGER,'SIZE' INTEGER,'SRC' TEXT,'TYPE' TEXT,'DIP_WIDTH' REAL,'DIP_HEIGHT' REAL,'BASE_NAME' TEXT,'DEVICE_TYPE' TEXT,'SCALE_FACTOR' REAL,'ASPECT_RATIO' REAL,'CONTENT_ITEM_IDENTIFIER' TEXT,'CONTENT' TEXT,'CONTENT_ITEM_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBRESOURCE_FILE'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBResourceFile dBResourceFile) {
        sQLiteStatement.clearBindings();
        Long id = dBResourceFile.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBResourceFile.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        String name = dBResourceFile.getName();
        if (name != null) {
            sQLiteStatement.bindString(3, name);
        }
        Long lastModifiedDate = dBResourceFile.getLastModifiedDate();
        if (lastModifiedDate != null) {
            sQLiteStatement.bindLong(4, lastModifiedDate.longValue());
        }
        Long size = dBResourceFile.getSize();
        if (size != null) {
            sQLiteStatement.bindLong(5, size.longValue());
        }
        String src = dBResourceFile.getSrc();
        if (src != null) {
            sQLiteStatement.bindString(6, src);
        }
        String type = dBResourceFile.getType();
        if (type != null) {
            sQLiteStatement.bindString(7, type);
        }
        Double dipWidth = dBResourceFile.getDipWidth();
        if (dipWidth != null) {
            sQLiteStatement.bindDouble(8, dipWidth.doubleValue());
        }
        Double dipHeight = dBResourceFile.getDipHeight();
        if (dipHeight != null) {
            sQLiteStatement.bindDouble(9, dipHeight.doubleValue());
        }
        String baseName = dBResourceFile.getBaseName();
        if (baseName != null) {
            sQLiteStatement.bindString(10, baseName);
        }
        String deviceType = dBResourceFile.getDeviceType();
        if (deviceType != null) {
            sQLiteStatement.bindString(11, deviceType);
        }
        Double scaleFactor = dBResourceFile.getScaleFactor();
        if (scaleFactor != null) {
            sQLiteStatement.bindDouble(12, scaleFactor.doubleValue());
        }
        Double aspectRatio = dBResourceFile.getAspectRatio();
        if (aspectRatio != null) {
            sQLiteStatement.bindDouble(13, aspectRatio.doubleValue());
        }
        String contentItemIdentifier = dBResourceFile.getContentItemIdentifier();
        if (contentItemIdentifier != null) {
            sQLiteStatement.bindString(14, contentItemIdentifier);
        }
        String content = dBResourceFile.getContent();
        if (content != null) {
            sQLiteStatement.bindString(15, content);
        }
        Long contentItemId = dBResourceFile.getContentItemId();
        if (contentItemId != null) {
            sQLiteStatement.bindLong(16, contentItemId.longValue());
        }
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBResourceFile readEntity(Cursor cursor, int i) {
        Cursor cursor2 = cursor;
        int i2 = i + 0;
        Long valueOf = cursor2.isNull(i2) ? null : Long.valueOf(cursor2.getLong(i2));
        int i3 = i + 1;
        String string = cursor2.isNull(i3) ? null : cursor2.getString(i3);
        int i4 = i + 2;
        String string2 = cursor2.isNull(i4) ? null : cursor2.getString(i4);
        int i5 = i + 3;
        Long valueOf2 = cursor2.isNull(i5) ? null : Long.valueOf(cursor2.getLong(i5));
        int i6 = i + 4;
        Long valueOf3 = cursor2.isNull(i6) ? null : Long.valueOf(cursor2.getLong(i6));
        int i7 = i + 5;
        String string3 = cursor2.isNull(i7) ? null : cursor2.getString(i7);
        int i8 = i + 6;
        String string4 = cursor2.isNull(i8) ? null : cursor2.getString(i8);
        int i9 = i + 7;
        Double valueOf4 = cursor2.isNull(i9) ? null : Double.valueOf(cursor2.getDouble(i9));
        int i10 = i + 8;
        Double valueOf5 = cursor2.isNull(i10) ? null : Double.valueOf(cursor2.getDouble(i10));
        int i11 = i + 9;
        String string5 = cursor2.isNull(i11) ? null : cursor2.getString(i11);
        int i12 = i + 10;
        String string6 = cursor2.isNull(i12) ? null : cursor2.getString(i12);
        int i13 = i + 11;
        Double valueOf6 = cursor2.isNull(i13) ? null : Double.valueOf(cursor2.getDouble(i13));
        int i14 = i + 12;
        Double valueOf7 = cursor2.isNull(i14) ? null : Double.valueOf(cursor2.getDouble(i14));
        int i15 = i + 13;
        String string7 = cursor2.isNull(i15) ? null : cursor2.getString(i15);
        int i16 = i + 14;
        String string8 = cursor2.isNull(i16) ? null : cursor2.getString(i16);
        int i17 = i + 15;
        return new DBResourceFile(valueOf, string, string2, valueOf2, valueOf3, string3, string4, valueOf4, valueOf5, string5, string6, valueOf6, valueOf7, string7, string8, cursor2.isNull(i17) ? null : Long.valueOf(cursor2.getLong(i17)));
    }

    public void readEntity(Cursor cursor, DBResourceFile dBResourceFile, int i) {
        int i2 = i + 0;
        Long l = null;
        dBResourceFile.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBResourceFile.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBResourceFile.setName(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 3;
        dBResourceFile.setLastModifiedDate(cursor.isNull(i5) ? null : Long.valueOf(cursor.getLong(i5)));
        int i6 = i + 4;
        dBResourceFile.setSize(cursor.isNull(i6) ? null : Long.valueOf(cursor.getLong(i6)));
        int i7 = i + 5;
        dBResourceFile.setSrc(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBResourceFile.setType(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        dBResourceFile.setDipWidth(cursor.isNull(i9) ? null : Double.valueOf(cursor.getDouble(i9)));
        int i10 = i + 8;
        dBResourceFile.setDipHeight(cursor.isNull(i10) ? null : Double.valueOf(cursor.getDouble(i10)));
        int i11 = i + 9;
        dBResourceFile.setBaseName(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 10;
        dBResourceFile.setDeviceType(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 11;
        dBResourceFile.setScaleFactor(cursor.isNull(i13) ? null : Double.valueOf(cursor.getDouble(i13)));
        int i14 = i + 12;
        dBResourceFile.setAspectRatio(cursor.isNull(i14) ? null : Double.valueOf(cursor.getDouble(i14)));
        int i15 = i + 13;
        dBResourceFile.setContentItemIdentifier(cursor.isNull(i15) ? null : cursor.getString(i15));
        int i16 = i + 14;
        dBResourceFile.setContent(cursor.isNull(i16) ? null : cursor.getString(i16));
        int i17 = i + 15;
        if (!cursor.isNull(i17)) {
            l = Long.valueOf(cursor.getLong(i17));
        }
        dBResourceFile.setContentItemId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBResourceFile dBResourceFile, long j) {
        dBResourceFile.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBResourceFile dBResourceFile) {
        if (dBResourceFile != null) {
            return dBResourceFile.getId();
        }
        return null;
    }

    public List<DBResourceFile> _queryDBContentItem_ResourceFiles(Long l) {
        synchronized (this) {
            if (this.dBContentItem_ResourceFilesQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContentItemId.eq((Object) null), new WhereCondition[0]);
                this.dBContentItem_ResourceFilesQuery = queryBuilder.build();
            }
        }
        Query<DBResourceFile> forCurrentThread = this.dBContentItem_ResourceFilesQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
