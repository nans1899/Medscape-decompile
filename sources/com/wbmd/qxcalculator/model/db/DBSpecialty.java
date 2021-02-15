package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.db.DBFilterDao;
import com.wbmd.qxcalculator.model.db.DBSpecialtyDao;
import com.wbmd.qxcalculator.model.parsedObjects.Specialty;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.List;

public class DBSpecialty {
    public static final String TAG = DBProfession.class.getSimpleName();
    private String categoryIdentifier;
    private String contributingAuthor;
    private Long filterId;
    private Long id;
    private Long identifier;
    private String name;

    public DBSpecialty() {
    }

    public DBSpecialty(Long l) {
        this.id = l;
    }

    public DBSpecialty(Long l, Long l2, String str, String str2, String str3, Long l3) {
        this.id = l;
        this.identifier = l2;
        this.name = str;
        this.categoryIdentifier = str2;
        this.contributingAuthor = str3;
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

    public String getCategoryIdentifier() {
        return this.categoryIdentifier;
    }

    public void setCategoryIdentifier(String str) {
        this.categoryIdentifier = str;
    }

    public String getContributingAuthor() {
        return this.contributingAuthor;
    }

    public void setContributingAuthor(String str) {
        this.contributingAuthor = str;
    }

    public Long getFilterId() {
        return this.filterId;
    }

    public void setFilterId(Long l) {
        this.filterId = l;
    }

    public static DBSpecialty getDBSpecialty(DaoSession daoSession, Specialty specialty) {
        List list = daoSession.getDBSpecialtyDao().queryBuilder().where(DBSpecialtyDao.Properties.Identifier.eq(specialty.identifier), DBSpecialtyDao.Properties.FilterId.isNull()).list();
        DBSpecialty dBSpecialty = !list.isEmpty() ? (DBSpecialty) list.get(0) : null;
        if (dBSpecialty == null) {
            dBSpecialty = new DBSpecialty();
            dBSpecialty.setIdentifier(specialty.identifier);
            daoSession.getDBSpecialtyDao().insert(dBSpecialty);
        }
        dBSpecialty.setName(specialty.name);
        dBSpecialty.setCategoryIdentifier(specialty.categoryIdentifier);
        dBSpecialty.setContributingAuthor(specialty.contributingAuthor);
        daoSession.getDBSpecialtyDao().update(dBSpecialty);
        return dBSpecialty;
    }

    public static void deleteUnusedSpecialties(DaoSession daoSession) {
        List list = daoSession.getDBSpecialtyDao().queryBuilder().where(DBSpecialtyDao.Properties.FilterId.isNull(), new WhereCondition[0]).list();
        DBSpecialty specialty = UserManager.getInstance().getDbUser().getSpecialty();
        if (specialty != null) {
            list.remove(specialty);
        }
        String str = TAG;
        Log.d(str, "Purging DBSpecialty: " + list.size());
        deleteSpecialties(daoSession, list);
    }

    public static void deleteSpecialties(DaoSession daoSession, List<DBSpecialty> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBSpecialty next : list) {
                if (next.getFilterId() != null) {
                    arrayList.add(next.getFilterId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBFilter> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBFilterDao(), DBFilterDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBFilter resetSpecialties : allWithPropertyInData) {
                        resetSpecialties.resetSpecialties();
                    }
                }
            }
            daoSession.getDBSpecialtyDao().deleteInTx(list);
        }
    }
}
