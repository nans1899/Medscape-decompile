package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.db.DBFilterDao;
import com.wbmd.qxcalculator.model.db.DBProfessionDao;
import com.wbmd.qxcalculator.model.parsedObjects.Profession;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.List;

public class DBProfession {
    public static final String TAG = DBProfession.class.getSimpleName();
    private Long filterId;
    private Long id;
    private Long identifier;
    private String name;

    public DBProfession() {
    }

    public DBProfession(Long l) {
        this.id = l;
    }

    public DBProfession(Long l, Long l2, String str, Long l3) {
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

    public static DBProfession getDBProfession(DaoSession daoSession, Profession profession) {
        List list = daoSession.getDBProfessionDao().queryBuilder().where(DBProfessionDao.Properties.Identifier.eq(profession.identifier), DBProfessionDao.Properties.FilterId.isNull()).list();
        DBProfession dBProfession = !list.isEmpty() ? (DBProfession) list.get(0) : null;
        if (dBProfession == null) {
            dBProfession = new DBProfession();
            dBProfession.setIdentifier(profession.identifier);
            daoSession.getDBProfessionDao().insert(dBProfession);
        }
        dBProfession.setName(profession.name);
        daoSession.getDBProfessionDao().update(dBProfession);
        return dBProfession;
    }

    public static void deleteUnusedProfessions(DaoSession daoSession) {
        List list = daoSession.getDBProfessionDao().queryBuilder().where(DBProfessionDao.Properties.FilterId.isNull(), new WhereCondition[0]).list();
        DBProfession profession = UserManager.getInstance().getDbUser().getProfession();
        if (profession != null) {
            list.remove(profession);
        }
        String str = TAG;
        Log.d(str, "Purging DBProfession: " + list.size());
        deleteProfessions(daoSession, list);
    }

    public static void deleteProfessions(DaoSession daoSession, List<DBProfession> list) {
        if (daoSession != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (DBProfession next : list) {
                if (next.getFilterId() != null) {
                    arrayList.add(next.getFilterId());
                }
            }
            if (!arrayList.isEmpty()) {
                List<DBFilter> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession.getDBFilterDao(), DBFilterDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBFilter resetProfessions : allWithPropertyInData) {
                        resetProfessions.resetProfessions();
                    }
                }
            }
            daoSession.getDBProfessionDao().deleteInTx(list);
        }
    }
}
