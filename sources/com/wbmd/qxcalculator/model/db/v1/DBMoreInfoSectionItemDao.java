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

public class DBMoreInfoSectionItemDao extends AbstractDao<DBMoreInfoSectionItem, Long> {
    public static final String TABLENAME = "DBMORE_INFO_SECTION_ITEM";
    private Query<DBMoreInfoSectionItem> dBMoreInfoSectionItem_MoreInfoSectionItemsQuery;
    private Query<DBMoreInfoSectionItem> dBMoreInfoSection_MoreInfoSectionItemsQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property AccentColor = new Property(12, String.class, "accentColor", false, "ACCENT_COLOR");
        public static final Property BackgroundColor = new Property(10, String.class, "backgroundColor", false, "BACKGROUND_COLOR");
        public static final Property Color = new Property(11, String.class, "color", false, "COLOR");
        public static final Property Footer = new Property(5, String.class, "footer", false, "FOOTER");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property LeftImage = new Property(8, String.class, "leftImage", false, "LEFT_IMAGE");
        public static final Property MoreInfoSectionId = new Property(17, Long.class, "moreInfoSectionId", false, "MORE_INFO_SECTION_ID");
        public static final Property MoreInfoSectionItemId = new Property(18, Long.class, "moreInfoSectionItemId", false, "MORE_INFO_SECTION_ITEM_ID");
        public static final Property Position = new Property(2, Integer.class, "position", false, "POSITION");
        public static final Property RightImage = new Property(9, String.class, "rightImage", false, "RIGHT_IMAGE");
        public static final Property Source = new Property(7, String.class, "source", false, "SOURCE");
        public static final Property SubTitle = new Property(4, String.class, "subTitle", false, "SUB_TITLE");
        public static final Property Title = new Property(3, String.class, "title", false, "TITLE");
        public static final Property TrackerCategory = new Property(14, String.class, "trackerCategory", false, "TRACKER_CATEGORY");
        public static final Property TrackerEvent = new Property(15, String.class, "trackerEvent", false, "TRACKER_EVENT");
        public static final Property TrackerId = new Property(13, String.class, "trackerId", false, "TRACKER_ID");
        public static final Property TrackerLabel = new Property(16, String.class, "trackerLabel", false, "TRACKER_LABEL");
        public static final Property Type = new Property(6, String.class, "type", false, "TYPE");
    }

    /* access modifiers changed from: protected */
    public boolean isEntityUpdateable() {
        return true;
    }

    public DBMoreInfoSectionItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBMoreInfoSectionItemDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBMORE_INFO_SECTION_ITEM' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'POSITION' INTEGER,'TITLE' TEXT,'SUB_TITLE' TEXT,'FOOTER' TEXT,'TYPE' TEXT,'SOURCE' TEXT,'LEFT_IMAGE' TEXT,'RIGHT_IMAGE' TEXT,'BACKGROUND_COLOR' TEXT,'COLOR' TEXT,'ACCENT_COLOR' TEXT,'TRACKER_ID' TEXT,'TRACKER_CATEGORY' TEXT,'TRACKER_EVENT' TEXT,'TRACKER_LABEL' TEXT,'MORE_INFO_SECTION_ID' INTEGER,'MORE_INFO_SECTION_ITEM_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBMORE_INFO_SECTION_ITEM'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBMoreInfoSectionItem dBMoreInfoSectionItem) {
        sQLiteStatement.clearBindings();
        Long id = dBMoreInfoSectionItem.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBMoreInfoSectionItem.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        Integer position = dBMoreInfoSectionItem.getPosition();
        if (position != null) {
            sQLiteStatement.bindLong(3, (long) position.intValue());
        }
        String title = dBMoreInfoSectionItem.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(4, title);
        }
        String subTitle = dBMoreInfoSectionItem.getSubTitle();
        if (subTitle != null) {
            sQLiteStatement.bindString(5, subTitle);
        }
        String footer = dBMoreInfoSectionItem.getFooter();
        if (footer != null) {
            sQLiteStatement.bindString(6, footer);
        }
        String type = dBMoreInfoSectionItem.getType();
        if (type != null) {
            sQLiteStatement.bindString(7, type);
        }
        String source = dBMoreInfoSectionItem.getSource();
        if (source != null) {
            sQLiteStatement.bindString(8, source);
        }
        String leftImage = dBMoreInfoSectionItem.getLeftImage();
        if (leftImage != null) {
            sQLiteStatement.bindString(9, leftImage);
        }
        String rightImage = dBMoreInfoSectionItem.getRightImage();
        if (rightImage != null) {
            sQLiteStatement.bindString(10, rightImage);
        }
        String backgroundColor = dBMoreInfoSectionItem.getBackgroundColor();
        if (backgroundColor != null) {
            sQLiteStatement.bindString(11, backgroundColor);
        }
        String color = dBMoreInfoSectionItem.getColor();
        if (color != null) {
            sQLiteStatement.bindString(12, color);
        }
        String accentColor = dBMoreInfoSectionItem.getAccentColor();
        if (accentColor != null) {
            sQLiteStatement.bindString(13, accentColor);
        }
        String trackerId = dBMoreInfoSectionItem.getTrackerId();
        if (trackerId != null) {
            sQLiteStatement.bindString(14, trackerId);
        }
        String trackerCategory = dBMoreInfoSectionItem.getTrackerCategory();
        if (trackerCategory != null) {
            sQLiteStatement.bindString(15, trackerCategory);
        }
        String trackerEvent = dBMoreInfoSectionItem.getTrackerEvent();
        if (trackerEvent != null) {
            sQLiteStatement.bindString(16, trackerEvent);
        }
        String trackerLabel = dBMoreInfoSectionItem.getTrackerLabel();
        if (trackerLabel != null) {
            sQLiteStatement.bindString(17, trackerLabel);
        }
        Long moreInfoSectionId = dBMoreInfoSectionItem.getMoreInfoSectionId();
        if (moreInfoSectionId != null) {
            sQLiteStatement.bindLong(18, moreInfoSectionId.longValue());
        }
        Long moreInfoSectionItemId = dBMoreInfoSectionItem.getMoreInfoSectionItemId();
        if (moreInfoSectionItemId != null) {
            sQLiteStatement.bindLong(19, moreInfoSectionItemId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBMoreInfoSectionItem dBMoreInfoSectionItem) {
        super.attachEntity(dBMoreInfoSectionItem);
        dBMoreInfoSectionItem.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBMoreInfoSectionItem readEntity(Cursor cursor, int i) {
        Cursor cursor2 = cursor;
        int i2 = i + 0;
        Long valueOf = cursor2.isNull(i2) ? null : Long.valueOf(cursor2.getLong(i2));
        int i3 = i + 1;
        String string = cursor2.isNull(i3) ? null : cursor2.getString(i3);
        int i4 = i + 2;
        Integer valueOf2 = cursor2.isNull(i4) ? null : Integer.valueOf(cursor2.getInt(i4));
        int i5 = i + 3;
        String string2 = cursor2.isNull(i5) ? null : cursor2.getString(i5);
        int i6 = i + 4;
        String string3 = cursor2.isNull(i6) ? null : cursor2.getString(i6);
        int i7 = i + 5;
        String string4 = cursor2.isNull(i7) ? null : cursor2.getString(i7);
        int i8 = i + 6;
        String string5 = cursor2.isNull(i8) ? null : cursor2.getString(i8);
        int i9 = i + 7;
        String string6 = cursor2.isNull(i9) ? null : cursor2.getString(i9);
        int i10 = i + 8;
        String string7 = cursor2.isNull(i10) ? null : cursor2.getString(i10);
        int i11 = i + 9;
        String string8 = cursor2.isNull(i11) ? null : cursor2.getString(i11);
        int i12 = i + 10;
        String string9 = cursor2.isNull(i12) ? null : cursor2.getString(i12);
        int i13 = i + 11;
        String string10 = cursor2.isNull(i13) ? null : cursor2.getString(i13);
        int i14 = i + 12;
        String string11 = cursor2.isNull(i14) ? null : cursor2.getString(i14);
        int i15 = i + 13;
        String string12 = cursor2.isNull(i15) ? null : cursor2.getString(i15);
        int i16 = i + 14;
        String string13 = cursor2.isNull(i16) ? null : cursor2.getString(i16);
        int i17 = i + 15;
        String string14 = cursor2.isNull(i17) ? null : cursor2.getString(i17);
        int i18 = i + 16;
        String string15 = cursor2.isNull(i18) ? null : cursor2.getString(i18);
        int i19 = i + 17;
        Long valueOf3 = cursor2.isNull(i19) ? null : Long.valueOf(cursor2.getLong(i19));
        int i20 = i + 18;
        return new DBMoreInfoSectionItem(valueOf, string, valueOf2, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, valueOf3, cursor2.isNull(i20) ? null : Long.valueOf(cursor2.getLong(i20)));
    }

    public void readEntity(Cursor cursor, DBMoreInfoSectionItem dBMoreInfoSectionItem, int i) {
        int i2 = i + 0;
        Long l = null;
        dBMoreInfoSectionItem.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBMoreInfoSectionItem.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBMoreInfoSectionItem.setPosition(cursor.isNull(i4) ? null : Integer.valueOf(cursor.getInt(i4)));
        int i5 = i + 3;
        dBMoreInfoSectionItem.setTitle(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBMoreInfoSectionItem.setSubTitle(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBMoreInfoSectionItem.setFooter(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBMoreInfoSectionItem.setType(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        dBMoreInfoSectionItem.setSource(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        dBMoreInfoSectionItem.setLeftImage(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 9;
        dBMoreInfoSectionItem.setRightImage(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 10;
        dBMoreInfoSectionItem.setBackgroundColor(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 11;
        dBMoreInfoSectionItem.setColor(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 12;
        dBMoreInfoSectionItem.setAccentColor(cursor.isNull(i14) ? null : cursor.getString(i14));
        int i15 = i + 13;
        dBMoreInfoSectionItem.setTrackerId(cursor.isNull(i15) ? null : cursor.getString(i15));
        int i16 = i + 14;
        dBMoreInfoSectionItem.setTrackerCategory(cursor.isNull(i16) ? null : cursor.getString(i16));
        int i17 = i + 15;
        dBMoreInfoSectionItem.setTrackerEvent(cursor.isNull(i17) ? null : cursor.getString(i17));
        int i18 = i + 16;
        dBMoreInfoSectionItem.setTrackerLabel(cursor.isNull(i18) ? null : cursor.getString(i18));
        int i19 = i + 17;
        dBMoreInfoSectionItem.setMoreInfoSectionId(cursor.isNull(i19) ? null : Long.valueOf(cursor.getLong(i19)));
        int i20 = i + 18;
        if (!cursor.isNull(i20)) {
            l = Long.valueOf(cursor.getLong(i20));
        }
        dBMoreInfoSectionItem.setMoreInfoSectionItemId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBMoreInfoSectionItem dBMoreInfoSectionItem, long j) {
        dBMoreInfoSectionItem.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBMoreInfoSectionItem dBMoreInfoSectionItem) {
        if (dBMoreInfoSectionItem != null) {
            return dBMoreInfoSectionItem.getId();
        }
        return null;
    }

    public List<DBMoreInfoSectionItem> _queryDBMoreInfoSection_MoreInfoSectionItems(Long l) {
        synchronized (this) {
            if (this.dBMoreInfoSection_MoreInfoSectionItemsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.MoreInfoSectionId.eq((Object) null), new WhereCondition[0]);
                this.dBMoreInfoSection_MoreInfoSectionItemsQuery = queryBuilder.build();
            }
        }
        Query<DBMoreInfoSectionItem> forCurrentThread = this.dBMoreInfoSection_MoreInfoSectionItemsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }

    public List<DBMoreInfoSectionItem> _queryDBMoreInfoSectionItem_MoreInfoSectionItems(Long l) {
        synchronized (this) {
            if (this.dBMoreInfoSectionItem_MoreInfoSectionItemsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.MoreInfoSectionItemId.eq((Object) null), new WhereCondition[0]);
                this.dBMoreInfoSectionItem_MoreInfoSectionItemsQuery = queryBuilder.build();
            }
        }
        Query<DBMoreInfoSectionItem> forCurrentThread = this.dBMoreInfoSectionItem_MoreInfoSectionItemsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
