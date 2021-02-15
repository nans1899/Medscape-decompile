package com.qxmd.eventssdkandroid.model.db.v1;

import android.database.sqlite.SQLiteDatabase;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;
import java.util.Map;

public class DaoSession extends AbstractDaoSession {
    private final EventDBDao eventDBDao;
    private final DaoConfig eventDBDaoConfig;

    public DaoSession(SQLiteDatabase sQLiteDatabase, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(sQLiteDatabase);
        DaoConfig clone = map.get(EventDBDao.class).clone();
        this.eventDBDaoConfig = clone;
        clone.initIdentityScope(identityScopeType);
        EventDBDao eventDBDao2 = new EventDBDao(this.eventDBDaoConfig, this);
        this.eventDBDao = eventDBDao2;
        registerDao(EventDB.class, eventDBDao2);
    }

    public void clear() {
        this.eventDBDaoConfig.getIdentityScope().clear();
    }

    public EventDBDao getEventDBDao() {
        return this.eventDBDao;
    }
}
