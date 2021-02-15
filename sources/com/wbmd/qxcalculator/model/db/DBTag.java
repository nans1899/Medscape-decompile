package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.common.Tag;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBTagDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBTag {
    public static final String TAG = DBTag.class.getSimpleName();
    private Long contentItemId;
    private Long id;
    private String identifier;
    private String name;

    public DBTag() {
    }

    public DBTag(Long l) {
        this.id = l;
    }

    public DBTag(Long l, String str, String str2, Long l2) {
        this.id = l;
        this.identifier = str;
        this.name = str2;
        this.contentItemId = l2;
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

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public static void deleteUnusedTags(DaoSession daoSession) {
        List list = daoSession.getDBTagDao().queryBuilder().where(DBTagDao.Properties.ContentItemId.isNull(), new WhereCondition[0]).list();
        String str = TAG;
        Log.d(str, "Purging DBTag: " + list.size());
        deleteTags(daoSession, list);
    }

    public static void deleteTags(DaoSession daoSession, List<DBTag> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBTag next : list) {
                if (next.getContentItemId() != null) {
                    arrayList.add(next.getContentItemId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBContentItemDao(), DBContentItemDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBContentItem resetTags : allWithPropertyInData) {
                        resetTags.resetTags();
                    }
                }
            }
            daoSession.getDBTagDao().deleteInTx(list);
        }
    }

    public static synchronized List<DBTag> insertAndRetrieveDbEntities(DaoSession daoSession, List<Tag> list) {
        synchronized (DBTag.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (Tag tag : list) {
                arrayList2.add(tag.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBTagDao(), DBTagDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Tag next : list) {
                DBTag dBTag = linkedHashMap.containsKey(next) ? (DBTag) linkedHashMap.get(next) : null;
                if (dBTag == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBTag dBTag2 = (DBTag) it.next();
                        if (dBTag2.getIdentifier().equals(next.identifier)) {
                            dBTag = dBTag2;
                            break;
                        }
                    }
                }
                if (dBTag == null) {
                    dBTag = new DBTag();
                    arrayList3.add(dBTag);
                }
                dBTag.setIdentifier(next.identifier);
                dBTag.setName(next.name);
                linkedHashMap.put(next, dBTag);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBTagDao().insertInTx(arrayList3);
            }
            daoSession.getDBTagDao().updateInTx(linkedHashMap.values());
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            return arrayList4;
        }
    }
}
