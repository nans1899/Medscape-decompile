package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.splashpage.SplashPage;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBSplashPageDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public class DBSplashPage {
    public static final String TAG = DBSplashPage.class.getSimpleName();
    private Long id;
    private String identifier;
    private String source;
    private String type;

    public DBSplashPage() {
    }

    public DBSplashPage(Long l) {
        this.id = l;
    }

    public DBSplashPage(Long l, String str, String str2, String str3) {
        this.id = l;
        this.identifier = str;
        this.source = str2;
        this.type = str3;
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

    public String getSource() {
        return this.source;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public static void deleteUnusedSplashPages(DaoSession daoSession) {
        List<DBContentItem> loadAll = daoSession.getDBContentItemDao().loadAll();
        ArrayList arrayList = new ArrayList();
        for (DBContentItem dBContentItem : loadAll) {
            if (dBContentItem.getSplashPageId() != null) {
                arrayList.add(dBContentItem.getSplashPageId());
            }
        }
        List allWithPropertyNotInData = DatabaseHelper.getAllWithPropertyNotInData(daoSession.getDBSplashPageDao(), DBSplashPageDao.Properties.Id, arrayList);
        String str = TAG;
        Log.d(str, "Purging DBSplashPages: " + allWithPropertyNotInData.size());
        deleteSplashPages(daoSession, allWithPropertyNotInData);
    }

    public static void deleteSplashPages(DaoSession daoSession, List<DBSplashPage> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList(list.size());
            for (DBSplashPage id2 : list) {
                arrayList.add(id2.getId());
            }
            List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBContentItemDao(), DBContentItemDao.Properties.SplashPageId, arrayList);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBContentItem splashPageId : allWithPropertyInData) {
                    splashPageId.setSplashPageId((Long) null);
                }
                daoSession.getDBContentItemDao().updateInTx(allWithPropertyInData);
            }
            daoSession.getDBSplashPageDao().deleteInTx(list);
        }
    }

    public static synchronized List<DBSplashPage> insertAndRetrieveDbEntities(DaoSession daoSession, List<SplashPage> list) {
        synchronized (DBSplashPage.class) {
            if (daoSession == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (SplashPage splashPage : list) {
                arrayList2.add(splashPage.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBSplashPageDao(), DBSplashPageDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (SplashPage next : list) {
                DBSplashPage dBSplashPage = linkedHashMap.containsKey(next) ? (DBSplashPage) linkedHashMap.get(next) : null;
                if (dBSplashPage == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBSplashPage dBSplashPage2 = (DBSplashPage) it.next();
                        if (dBSplashPage2.getIdentifier().equals(next.identifier)) {
                            dBSplashPage = dBSplashPage2;
                            break;
                        }
                    }
                }
                if (dBSplashPage == null) {
                    dBSplashPage = new DBSplashPage();
                    arrayList3.add(dBSplashPage);
                }
                dBSplashPage.setIdentifier(next.identifier);
                dBSplashPage.setSource(next.source);
                dBSplashPage.setType(next.type);
                linkedHashMap.put(next, dBSplashPage);
            }
            if (arrayList3.size() > 0) {
                daoSession.getDBSplashPageDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.values());
            daoSession.getDBSplashPageDao().updateInTx(arrayList4);
            return arrayList4;
        }
    }
}
