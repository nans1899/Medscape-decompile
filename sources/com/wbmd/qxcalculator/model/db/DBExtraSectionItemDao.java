package com.wbmd.qxcalculator.model.db;

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

public class DBExtraSectionItemDao extends AbstractDao<DBExtraSectionItem, Long> {
    public static final String TABLENAME = "DBEXTRA_SECTION_ITEM";
    private Query<DBExtraSectionItem> dBExtraSectionItem_ExtraSectionItemsQuery;
    private Query<DBExtraSectionItem> dBExtraSection_ExtraSectionItemsQuery;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property AccentColor = new Property(12, String.class, "accentColor", false, "ACCENT_COLOR");
        public static final Property BackgroundColor = new Property(10, String.class, "backgroundColor", false, "BACKGROUND_COLOR");
        public static final Property Color = new Property(11, String.class, "color", false, "COLOR");
        public static final Property ConditionFormula = new Property(18, String.class, "conditionFormula", false, "CONDITION_FORMULA");
        public static final Property ExtraSectionId = new Property(19, Long.class, "extraSectionId", false, "EXTRA_SECTION_ID");
        public static final Property ExtraSectionItemId = new Property(20, Long.class, "extraSectionItemId", false, "EXTRA_SECTION_ITEM_ID");
        public static final Property Footer = new Property(5, String.class, "footer", false, "FOOTER");
        public static final Property HideWhenNoResults = new Property(17, Boolean.class, "hideWhenNoResults", false, "HIDE_WHEN_NO_RESULTS");
        public static final Property Id = new Property(0, Long.class, "id", true, "_id");
        public static final Property Identifier = new Property(1, String.class, "identifier", false, "IDENTIFIER");
        public static final Property LeftImage = new Property(8, String.class, "leftImage", false, "LEFT_IMAGE");
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

    public DBExtraSectionItemDao(DaoConfig daoConfig) {
        super(daoConfig);
    }

    public DBExtraSectionItemDao(DaoConfig daoConfig, DaoSession daoSession2) {
        super(daoConfig, daoSession2);
        this.daoSession = daoSession2;
    }

    public static void createTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        String str = z ? "IF NOT EXISTS " : "";
        sQLiteDatabase.execSQL("CREATE TABLE " + str + "'DBEXTRA_SECTION_ITEM' ('_id' INTEGER PRIMARY KEY ,'IDENTIFIER' TEXT,'POSITION' INTEGER,'TITLE' TEXT,'SUB_TITLE' TEXT,'FOOTER' TEXT,'TYPE' TEXT,'SOURCE' TEXT,'LEFT_IMAGE' TEXT,'RIGHT_IMAGE' TEXT,'BACKGROUND_COLOR' TEXT,'COLOR' TEXT,'ACCENT_COLOR' TEXT,'TRACKER_ID' TEXT,'TRACKER_CATEGORY' TEXT,'TRACKER_EVENT' TEXT,'TRACKER_LABEL' TEXT,'HIDE_WHEN_NO_RESULTS' INTEGER,'CONDITION_FORMULA' TEXT,'EXTRA_SECTION_ID' INTEGER,'EXTRA_SECTION_ITEM_ID' INTEGER);");
    }

    public static void dropTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("'DBEXTRA_SECTION_ITEM'");
        sQLiteDatabase.execSQL(sb.toString());
    }

    /* access modifiers changed from: protected */
    public void bindValues(SQLiteStatement sQLiteStatement, DBExtraSectionItem dBExtraSectionItem) {
        sQLiteStatement.clearBindings();
        Long id = dBExtraSectionItem.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        String identifier = dBExtraSectionItem.getIdentifier();
        if (identifier != null) {
            sQLiteStatement.bindString(2, identifier);
        }
        Integer position = dBExtraSectionItem.getPosition();
        if (position != null) {
            sQLiteStatement.bindLong(3, (long) position.intValue());
        }
        String title = dBExtraSectionItem.getTitle();
        if (title != null) {
            sQLiteStatement.bindString(4, title);
        }
        String subTitle = dBExtraSectionItem.getSubTitle();
        if (subTitle != null) {
            sQLiteStatement.bindString(5, subTitle);
        }
        String footer = dBExtraSectionItem.getFooter();
        if (footer != null) {
            sQLiteStatement.bindString(6, footer);
        }
        String type = dBExtraSectionItem.getType();
        if (type != null) {
            sQLiteStatement.bindString(7, type);
        }
        String source = dBExtraSectionItem.getSource();
        if (source != null) {
            sQLiteStatement.bindString(8, source);
        }
        String leftImage = dBExtraSectionItem.getLeftImage();
        if (leftImage != null) {
            sQLiteStatement.bindString(9, leftImage);
        }
        String rightImage = dBExtraSectionItem.getRightImage();
        if (rightImage != null) {
            sQLiteStatement.bindString(10, rightImage);
        }
        String backgroundColor = dBExtraSectionItem.getBackgroundColor();
        if (backgroundColor != null) {
            sQLiteStatement.bindString(11, backgroundColor);
        }
        String color = dBExtraSectionItem.getColor();
        if (color != null) {
            sQLiteStatement.bindString(12, color);
        }
        String accentColor = dBExtraSectionItem.getAccentColor();
        if (accentColor != null) {
            sQLiteStatement.bindString(13, accentColor);
        }
        String trackerId = dBExtraSectionItem.getTrackerId();
        if (trackerId != null) {
            sQLiteStatement.bindString(14, trackerId);
        }
        String trackerCategory = dBExtraSectionItem.getTrackerCategory();
        if (trackerCategory != null) {
            sQLiteStatement.bindString(15, trackerCategory);
        }
        String trackerEvent = dBExtraSectionItem.getTrackerEvent();
        if (trackerEvent != null) {
            sQLiteStatement.bindString(16, trackerEvent);
        }
        String trackerLabel = dBExtraSectionItem.getTrackerLabel();
        if (trackerLabel != null) {
            sQLiteStatement.bindString(17, trackerLabel);
        }
        Boolean hideWhenNoResults = dBExtraSectionItem.getHideWhenNoResults();
        if (hideWhenNoResults != null) {
            sQLiteStatement.bindLong(18, hideWhenNoResults.booleanValue() ? 1 : 0);
        }
        String conditionFormula = dBExtraSectionItem.getConditionFormula();
        if (conditionFormula != null) {
            sQLiteStatement.bindString(19, conditionFormula);
        }
        Long extraSectionId = dBExtraSectionItem.getExtraSectionId();
        if (extraSectionId != null) {
            sQLiteStatement.bindLong(20, extraSectionId.longValue());
        }
        Long extraSectionItemId = dBExtraSectionItem.getExtraSectionItemId();
        if (extraSectionItemId != null) {
            sQLiteStatement.bindLong(21, extraSectionItemId.longValue());
        }
    }

    /* access modifiers changed from: protected */
    public void attachEntity(DBExtraSectionItem dBExtraSectionItem) {
        super.attachEntity(dBExtraSectionItem);
        dBExtraSectionItem.__setDaoSession(this.daoSession);
    }

    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    public DBExtraSectionItem readEntity(Cursor cursor, int i) {
        Boolean bool;
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
        if (cursor2.isNull(i19)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor2.getShort(i19) != 0);
        }
        int i20 = i + 18;
        String string16 = cursor2.isNull(i20) ? null : cursor2.getString(i20);
        int i21 = i + 19;
        Long valueOf3 = cursor2.isNull(i21) ? null : Long.valueOf(cursor2.getLong(i21));
        int i22 = i + 20;
        return new DBExtraSectionItem(valueOf, string, valueOf2, string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string14, string15, bool, string16, valueOf3, cursor2.isNull(i22) ? null : Long.valueOf(cursor2.getLong(i22)));
    }

    public void readEntity(Cursor cursor, DBExtraSectionItem dBExtraSectionItem, int i) {
        Boolean bool;
        int i2 = i + 0;
        Long l = null;
        dBExtraSectionItem.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        int i3 = i + 1;
        dBExtraSectionItem.setIdentifier(cursor.isNull(i3) ? null : cursor.getString(i3));
        int i4 = i + 2;
        dBExtraSectionItem.setPosition(cursor.isNull(i4) ? null : Integer.valueOf(cursor.getInt(i4)));
        int i5 = i + 3;
        dBExtraSectionItem.setTitle(cursor.isNull(i5) ? null : cursor.getString(i5));
        int i6 = i + 4;
        dBExtraSectionItem.setSubTitle(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 5;
        dBExtraSectionItem.setFooter(cursor.isNull(i7) ? null : cursor.getString(i7));
        int i8 = i + 6;
        dBExtraSectionItem.setType(cursor.isNull(i8) ? null : cursor.getString(i8));
        int i9 = i + 7;
        dBExtraSectionItem.setSource(cursor.isNull(i9) ? null : cursor.getString(i9));
        int i10 = i + 8;
        dBExtraSectionItem.setLeftImage(cursor.isNull(i10) ? null : cursor.getString(i10));
        int i11 = i + 9;
        dBExtraSectionItem.setRightImage(cursor.isNull(i11) ? null : cursor.getString(i11));
        int i12 = i + 10;
        dBExtraSectionItem.setBackgroundColor(cursor.isNull(i12) ? null : cursor.getString(i12));
        int i13 = i + 11;
        dBExtraSectionItem.setColor(cursor.isNull(i13) ? null : cursor.getString(i13));
        int i14 = i + 12;
        dBExtraSectionItem.setAccentColor(cursor.isNull(i14) ? null : cursor.getString(i14));
        int i15 = i + 13;
        dBExtraSectionItem.setTrackerId(cursor.isNull(i15) ? null : cursor.getString(i15));
        int i16 = i + 14;
        dBExtraSectionItem.setTrackerCategory(cursor.isNull(i16) ? null : cursor.getString(i16));
        int i17 = i + 15;
        dBExtraSectionItem.setTrackerEvent(cursor.isNull(i17) ? null : cursor.getString(i17));
        int i18 = i + 16;
        dBExtraSectionItem.setTrackerLabel(cursor.isNull(i18) ? null : cursor.getString(i18));
        int i19 = i + 17;
        if (cursor.isNull(i19)) {
            bool = null;
        } else {
            bool = Boolean.valueOf(cursor.getShort(i19) != 0);
        }
        dBExtraSectionItem.setHideWhenNoResults(bool);
        int i20 = i + 18;
        dBExtraSectionItem.setConditionFormula(cursor.isNull(i20) ? null : cursor.getString(i20));
        int i21 = i + 19;
        dBExtraSectionItem.setExtraSectionId(cursor.isNull(i21) ? null : Long.valueOf(cursor.getLong(i21)));
        int i22 = i + 20;
        if (!cursor.isNull(i22)) {
            l = Long.valueOf(cursor.getLong(i22));
        }
        dBExtraSectionItem.setExtraSectionItemId(l);
    }

    /* access modifiers changed from: protected */
    public Long updateKeyAfterInsert(DBExtraSectionItem dBExtraSectionItem, long j) {
        dBExtraSectionItem.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    public Long getKey(DBExtraSectionItem dBExtraSectionItem) {
        if (dBExtraSectionItem != null) {
            return dBExtraSectionItem.getId();
        }
        return null;
    }

    public List<DBExtraSectionItem> _queryDBExtraSection_ExtraSectionItems(Long l) {
        synchronized (this) {
            if (this.dBExtraSection_ExtraSectionItemsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ExtraSectionId.eq((Object) null), new WhereCondition[0]);
                this.dBExtraSection_ExtraSectionItemsQuery = queryBuilder.build();
            }
        }
        Query<DBExtraSectionItem> forCurrentThread = this.dBExtraSection_ExtraSectionItemsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }

    public List<DBExtraSectionItem> _queryDBExtraSectionItem_ExtraSectionItems(Long l) {
        synchronized (this) {
            if (this.dBExtraSectionItem_ExtraSectionItemsQuery == null) {
                QueryBuilder queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ExtraSectionItemId.eq((Object) null), new WhereCondition[0]);
                this.dBExtraSectionItem_ExtraSectionItemsQuery = queryBuilder.build();
            }
        }
        Query<DBExtraSectionItem> forCurrentThread = this.dBExtraSectionItem_ExtraSectionItemsQuery.forCurrentThread();
        forCurrentThread.setParameter(0, (Object) l);
        return forCurrentThread.list();
    }
}
