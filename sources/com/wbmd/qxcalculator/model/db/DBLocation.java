package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.db.DBFilterDao;
import com.wbmd.qxcalculator.model.db.DBLocationDao;
import com.wbmd.qxcalculator.model.parsedObjects.Location;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.List;

public class DBLocation {
    public static final String TAG = DBProfession.class.getSimpleName();
    private Long filterId;
    private Long id;
    private Long identifier;
    private String name;

    public DBLocation() {
    }

    public DBLocation(Long l) {
        this.id = l;
    }

    public DBLocation(Long l, Long l2, String str, Long l3) {
        this.id = l;
        this.identifier = l2;
        this.name = str;
        this.filterId = l3;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public Long getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(Long l) {
        this.identifier = l;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Long getFilterId() {
        return this.filterId;
    }

    public void setFilterId(Long l) {
        this.filterId = l;
    }

    public static DBLocation getDBLocation(DaoSession daoSession, Location location) {
        List list = daoSession.getDBLocationDao().queryBuilder().where(DBLocationDao.Properties.Identifier.eq(location.identifier), DBLocationDao.Properties.FilterId.isNull()).list();
        DBLocation dBLocation = !list.isEmpty() ? (DBLocation) list.get(0) : null;
        if (dBLocation == null) {
            dBLocation = new DBLocation();
            dBLocation.setIdentifier(location.identifier);
            daoSession.getDBLocationDao().insert(dBLocation);
        }
        dBLocation.setName(location.name);
        daoSession.getDBLocationDao().update(dBLocation);
        return dBLocation;
    }

    public static void deleteUnusedLocations(DaoSession daoSession) {
        List list = daoSession.getDBLocationDao().queryBuilder().where(DBLocationDao.Properties.FilterId.isNull(), new WhereCondition[0]).list();
        DBLocation location = UserManager.getInstance().getDbUser().getLocation();
        if (location != null) {
            list.remove(location);
        }
        String str = TAG;
        Log.d(str, "Purging DBLocation: " + list.size());
        deleteLocations(daoSession, list);
    }

    public static void deleteLocations(DaoSession daoSession, List<DBLocation> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBLocation next : list) {
                if (next.getFilterId() != null) {
                    arrayList.add(next.getFilterId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBFilter> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBFilterDao(), DBFilterDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBFilter resetLocations : allWithPropertyInData) {
                        resetLocations.resetLocations();
                    }
                }
            }
            daoSession.getDBLocationDao().deleteInTx(list);
        }
    }
}
