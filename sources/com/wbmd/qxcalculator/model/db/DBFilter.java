package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.contentItems.common.Filter;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.model.db.DBFeaturedContentAdDao;
import com.wbmd.qxcalculator.model.db.DBFilterDao;
import com.wbmd.qxcalculator.model.db.DBLocationDao;
import com.wbmd.qxcalculator.model.db.DBProfessionDao;
import com.wbmd.qxcalculator.model.db.DBRestrictionDao;
import com.wbmd.qxcalculator.model.db.DBSpecialtyDao;
import com.wbmd.qxcalculator.model.parsedObjects.Location;
import com.wbmd.qxcalculator.model.parsedObjects.Profession;
import com.wbmd.qxcalculator.model.parsedObjects.Specialty;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBFilter {
    public static final String TAG = DBFilter.class.getSimpleName();
    private Long contentItemId;
    private transient DaoSession daoSession;
    private Long featuredContentAdId;
    private Long id;
    private String identifier;
    private List<DBLocation> locations;
    private transient DBFilterDao myDao;
    private List<DBProfession> professions;
    private Long promotionId;
    private Long restrictionId;
    private List<DBSpecialty> specialties;
    private String type;

    public DBFilter() {
    }

    public DBFilter(Long l) {
        this.id = l;
    }

    public DBFilter(Long l, String str, String str2, Long l2, Long l3, Long l4, Long l5) {
        this.id = l;
        this.identifier = str;
        this.type = str2;
        this.contentItemId = l2;
        this.featuredContentAdId = l3;
        this.restrictionId = l4;
        this.promotionId = l5;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBFilterDao() : null;
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

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public Long getFeaturedContentAdId() {
        return this.featuredContentAdId;
    }

    public void setFeaturedContentAdId(Long l) {
        this.featuredContentAdId = l;
    }

    public Long getRestrictionId() {
        return this.restrictionId;
    }

    public void setRestrictionId(Long l) {
        this.restrictionId = l;
    }

    public Long getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(Long l) {
        this.promotionId = l;
    }

    public List<DBSpecialty> getSpecialties() {
        if (this.specialties == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBSpecialty> _queryDBFilter_Specialties = daoSession2.getDBSpecialtyDao()._queryDBFilter_Specialties(this.id);
                synchronized (this) {
                    if (this.specialties == null) {
                        this.specialties = _queryDBFilter_Specialties;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.specialties;
    }

    public synchronized void resetSpecialties() {
        this.specialties = null;
    }

    public List<DBProfession> getProfessions() {
        if (this.professions == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBProfession> _queryDBFilter_Professions = daoSession2.getDBProfessionDao()._queryDBFilter_Professions(this.id);
                synchronized (this) {
                    if (this.professions == null) {
                        this.professions = _queryDBFilter_Professions;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.professions;
    }

    public synchronized void resetProfessions() {
        this.professions = null;
    }

    public List<DBLocation> getLocations() {
        if (this.locations == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBLocation> _queryDBFilter_Locations = daoSession2.getDBLocationDao()._queryDBFilter_Locations(this.id);
                synchronized (this) {
                    if (this.locations == null) {
                        this.locations = _queryDBFilter_Locations;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.locations;
    }

    public synchronized void resetLocations() {
        this.locations = null;
    }

    public void delete() {
        DBFilterDao dBFilterDao = this.myDao;
        if (dBFilterDao != null) {
            dBFilterDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBFilterDao dBFilterDao = this.myDao;
        if (dBFilterDao != null) {
            dBFilterDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBFilterDao dBFilterDao = this.myDao;
        if (dBFilterDao != null) {
            dBFilterDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void preloadRelations(DaoSession daoSession2, List<DBFilter> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (DBFilter id2 : list) {
            arrayList.add(id2.getId());
        }
        List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBSpecialtyDao(), DBSpecialtyDao.Properties.FilterId, arrayList);
        List allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBProfessionDao(), DBProfessionDao.Properties.FilterId, arrayList);
        List allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBLocationDao(), DBLocationDao.Properties.FilterId, arrayList);
        for (DBFilter next : list) {
            next.specialties = new ArrayList();
            next.professions = new ArrayList();
            next.locations = new ArrayList();
            Iterator it = allWithPropertyInData.iterator();
            Iterator it2 = allWithPropertyInData2.iterator();
            Iterator it3 = allWithPropertyInData3.iterator();
            while (it.hasNext()) {
                DBSpecialty dBSpecialty = (DBSpecialty) it.next();
                if (dBSpecialty.getFilterId().equals(next.getId())) {
                    next.specialties.add(dBSpecialty);
                    it.remove();
                }
            }
            while (it2.hasNext()) {
                DBProfession dBProfession = (DBProfession) it2.next();
                if (dBProfession.getFilterId().equals(next.getId())) {
                    next.professions.add(dBProfession);
                    it2.remove();
                }
            }
            while (it3.hasNext()) {
                DBLocation dBLocation = (DBLocation) it3.next();
                if (dBLocation.getFilterId().equals(next.getId())) {
                    next.locations.add(dBLocation);
                    it3.remove();
                }
            }
        }
    }

    public static void deleteUnusedFilters(DaoSession daoSession2) {
        List list = daoSession2.getDBFilterDao().queryBuilder().where(DBFilterDao.Properties.ContentItemId.isNull(), DBFilterDao.Properties.FeaturedContentAdId.isNull(), DBFilterDao.Properties.RestrictionId.isNull()).list();
        String str = TAG;
        Log.d(str, "Purging DBFilter: " + list.size());
        deleteFilters(daoSession2, list);
    }

    public static void deleteFilters(DaoSession daoSession2, List<DBFilter> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList(list.size());
            for (DBFilter next : list) {
                arrayList4.add(next.getId());
                if (next.getContentItemId() != null) {
                    arrayList.add(next.getContentItemId());
                }
                if (next.getFeaturedContentAdId() != null) {
                    arrayList2.add(next.getFeaturedContentAdId());
                }
                if (next.getRestrictionId() != null) {
                    arrayList3.add(next.getRestrictionId());
                }
            }
            List<DBProfession> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBProfessionDao(), DBProfessionDao.Properties.FilterId, arrayList4);
            if (!allWithPropertyInData.isEmpty()) {
                for (DBProfession filterId : allWithPropertyInData) {
                    filterId.setFilterId((Long) null);
                }
                DBProfession.deleteProfessions(daoSession2, allWithPropertyInData);
            }
            List<DBSpecialty> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBSpecialtyDao(), DBSpecialtyDao.Properties.FilterId, arrayList4);
            if (!allWithPropertyInData2.isEmpty()) {
                for (DBSpecialty filterId2 : allWithPropertyInData2) {
                    filterId2.setFilterId((Long) null);
                }
                DBSpecialty.deleteSpecialties(daoSession2, allWithPropertyInData2);
            }
            List<DBLocation> allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBLocationDao(), DBLocationDao.Properties.FilterId, arrayList4);
            if (!allWithPropertyInData3.isEmpty()) {
                for (DBLocation filterId3 : allWithPropertyInData3) {
                    filterId3.setFilterId((Long) null);
                }
                DBLocation.deleteLocations(daoSession2, allWithPropertyInData3);
            }
            if (!arrayList.isEmpty()) {
                List<DBContentItem> allWithPropertyInData4 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.Id, arrayList);
                if (!allWithPropertyInData4.isEmpty()) {
                    for (DBContentItem resetFilters : allWithPropertyInData4) {
                        resetFilters.resetFilters();
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                List<DBFeaturedContentAd> allWithPropertyInData5 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFeaturedContentAdDao(), DBFeaturedContentAdDao.Properties.Id, arrayList2);
                if (!allWithPropertyInData5.isEmpty()) {
                    for (DBFeaturedContentAd resetFilters2 : allWithPropertyInData5) {
                        resetFilters2.resetFilters();
                    }
                }
            }
            if (!arrayList3.isEmpty()) {
                List<DBRestriction> allWithPropertyInData6 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBRestrictionDao(), DBRestrictionDao.Properties.Id, arrayList3);
                if (!allWithPropertyInData6.isEmpty()) {
                    for (DBRestriction resetFilters3 : allWithPropertyInData6) {
                        resetFilters3.resetFilters();
                    }
                }
            }
            daoSession2.getDBFilterDao().deleteInTx(list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002a, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.wbmd.qxcalculator.model.db.DBFilter insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession r3, com.wbmd.qxcalculator.model.contentItems.common.Filter r4) {
        /*
            java.lang.Class<com.wbmd.qxcalculator.model.db.DBFilter> r0 = com.wbmd.qxcalculator.model.db.DBFilter.class
            monitor-enter(r0)
            r1 = 0
            if (r3 == 0) goto L_0x0029
            if (r4 != 0) goto L_0x0009
            goto L_0x0029
        L_0x0009:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0026 }
            r2.<init>()     // Catch:{ all -> 0x0026 }
            r2.add(r4)     // Catch:{ all -> 0x0026 }
            java.util.List r3 = insertAndRetrieveDbEntities(r3, r2)     // Catch:{ all -> 0x0026 }
            boolean r4 = r3.isEmpty()     // Catch:{ all -> 0x0026 }
            if (r4 == 0) goto L_0x001c
            goto L_0x0024
        L_0x001c:
            r4 = 0
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x0026 }
            r1 = r3
            com.wbmd.qxcalculator.model.db.DBFilter r1 = (com.wbmd.qxcalculator.model.db.DBFilter) r1     // Catch:{ all -> 0x0026 }
        L_0x0024:
            monitor-exit(r0)
            return r1
        L_0x0026:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        L_0x0029:
            monitor-exit(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBFilter.insertAndRetrieveDbEntity(com.wbmd.qxcalculator.model.db.DaoSession, com.wbmd.qxcalculator.model.contentItems.common.Filter):com.wbmd.qxcalculator.model.db.DBFilter");
    }

    public static synchronized List<DBFilter> insertAndRetrieveDbEntities(DaoSession daoSession2, List<Filter> list) {
        synchronized (DBFilter.class) {
            if (daoSession2 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            for (Filter filter : list) {
                arrayList2.add(filter.identifier);
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBFilterDao(), DBFilterDao.Properties.Identifier, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Filter next : list) {
                DBFilter dBFilter = linkedHashMap.containsKey(next) ? (DBFilter) linkedHashMap.get(next) : null;
                if (dBFilter == null) {
                    Iterator it = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DBFilter dBFilter2 = (DBFilter) it.next();
                        if (dBFilter2.getIdentifier().equals(next.identifier)) {
                            dBFilter = dBFilter2;
                            break;
                        }
                    }
                }
                if (dBFilter == null) {
                    dBFilter = new DBFilter();
                    arrayList3.add(dBFilter);
                }
                dBFilter.setIdentifier(next.identifier);
                dBFilter.setType(next.type);
                linkedHashMap.put(next, dBFilter);
            }
            if (arrayList3.size() > 0) {
                daoSession2.getDBFilterDao().insertInTx(arrayList3);
            }
            ArrayList arrayList4 = new ArrayList(linkedHashMap.size());
            for (DBFilter id2 : linkedHashMap.values()) {
                arrayList4.add(id2.getId());
            }
            List allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBProfessionDao(), DBProfessionDao.Properties.FilterId, arrayList4);
            List allWithPropertyInData3 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBSpecialtyDao(), DBSpecialtyDao.Properties.FilterId, arrayList4);
            List allWithPropertyInData4 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBLocationDao(), DBLocationDao.Properties.FilterId, arrayList4);
            ArrayList arrayList5 = new ArrayList();
            ArrayList arrayList6 = new ArrayList();
            ArrayList arrayList7 = new ArrayList();
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                Filter filter2 = (Filter) entry.getKey();
                DBFilter dBFilter3 = (DBFilter) entry.getValue();
                if (filter2.professions != null && !filter2.professions.isEmpty()) {
                    for (Profession next2 : filter2.professions) {
                        DBProfession dBProfession = new DBProfession();
                        dBProfession.setIdentifier(next2.identifier);
                        dBProfession.setName(next2.name);
                        dBProfession.setFilterId(dBFilter3.getId());
                        arrayList5.add(dBProfession);
                    }
                }
                dBFilter3.resetProfessions();
                if (filter2.specialties != null && !filter2.specialties.isEmpty()) {
                    for (Specialty next3 : filter2.specialties) {
                        DBSpecialty dBSpecialty = new DBSpecialty();
                        dBSpecialty.setIdentifier(next3.identifier);
                        dBSpecialty.setName(next3.name);
                        dBSpecialty.setFilterId(dBFilter3.getId());
                        arrayList6.add(dBSpecialty);
                    }
                }
                dBFilter3.resetSpecialties();
                if (filter2.locations != null && !filter2.locations.isEmpty()) {
                    for (Location next4 : filter2.locations) {
                        DBLocation dBLocation = new DBLocation();
                        dBLocation.setIdentifier(next4.identifier);
                        dBLocation.setName(next4.name);
                        dBLocation.setFilterId(dBFilter3.getId());
                        arrayList7.add(dBLocation);
                    }
                }
                dBFilter3.resetLocations();
            }
            if (!arrayList5.isEmpty()) {
                daoSession2.getDBProfessionDao().insertInTx(arrayList5);
                daoSession2.getDBProfessionDao().updateInTx(arrayList5);
            }
            if (!arrayList6.isEmpty()) {
                daoSession2.getDBSpecialtyDao().insertInTx(arrayList6);
                daoSession2.getDBSpecialtyDao().updateInTx(arrayList6);
            }
            if (!arrayList7.isEmpty()) {
                daoSession2.getDBLocationDao().insertInTx(arrayList7);
                daoSession2.getDBLocationDao().updateInTx(arrayList7);
            }
            if (!allWithPropertyInData2.isEmpty()) {
                daoSession2.getDBProfessionDao().deleteInTx(allWithPropertyInData2);
            }
            if (!allWithPropertyInData3.isEmpty()) {
                daoSession2.getDBSpecialtyDao().deleteInTx(allWithPropertyInData3);
            }
            if (!allWithPropertyInData4.isEmpty()) {
                daoSession2.getDBLocationDao().deleteInTx(allWithPropertyInData4);
            }
            ArrayList arrayList8 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBFilterDao().updateInTx(arrayList8);
            return arrayList8;
        }
    }

    public static boolean shouldIncludeFilters(List<DBFilter> list) {
        if (list != null && !list.isEmpty()) {
            for (DBFilter next : list) {
                Filter.FilterType filterType = null;
                if (next != null) {
                    if (next.getType().equalsIgnoreCase(Filter.K_INCLUSION_MATCH_ANY)) {
                        filterType = Filter.FilterType.INCLUDE_MATCH_ANY;
                    } else if (next.getType().equalsIgnoreCase(Filter.K_INCLUSION_MATCH_ALL)) {
                        filterType = Filter.FilterType.INCLUDE_MATCH_ALL;
                    } else if (next.getType().equalsIgnoreCase(Filter.K_EXCLUSION_MATCH_ANY)) {
                        filterType = Filter.FilterType.EXCLUDE_MATCH_ANY;
                    } else if (next.getType().equalsIgnoreCase(Filter.K_EXCLUSION_MATCH_ALL)) {
                        filterType = Filter.FilterType.EXCLUDE_MATCH_ALL;
                    }
                    if (!filterIncludes(next, filterType)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean filterIncludes(DBFilter dBFilter, Filter.FilterType filterType) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        if (filterType == null) {
            return true;
        }
        DBUser dbUser = UserManager.getInstance().getDbUser();
        DBProfession profession = dbUser.getProfession();
        DBSpecialty specialty = dbUser.getSpecialty();
        DBLocation location = dbUser.getLocation();
        int i = AnonymousClass1.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$common$Filter$FilterType[filterType.ordinal()];
        boolean z5 = false;
        if (i == 1) {
            if (dBFilter.getProfessions() != null && profession != null) {
                Iterator<DBProfession> it = dBFilter.getProfessions().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getIdentifier().equals(profession.getIdentifier())) {
                            z5 = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (!z5 && dBFilter.getSpecialties() != null && specialty != null) {
                Iterator<DBSpecialty> it2 = dBFilter.getSpecialties().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (it2.next().getIdentifier().equals(specialty.getIdentifier())) {
                            z5 = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (!(z5 || dBFilter.getLocations() == null || location == null)) {
                for (DBLocation identifier2 : dBFilter.getLocations()) {
                    if (identifier2.getIdentifier().equals(location.getIdentifier())) {
                        return true;
                    }
                }
            }
            return z5;
        } else if (i != 2) {
            if (i == 3) {
                if (!(dBFilter.getProfessions() == null || profession == null)) {
                    for (DBProfession identifier3 : dBFilter.getProfessions()) {
                        if (identifier3.getIdentifier().equals(profession.getIdentifier())) {
                            return false;
                        }
                    }
                }
                if (!(dBFilter.getSpecialties() == null || specialty == null)) {
                    for (DBSpecialty identifier4 : dBFilter.getSpecialties()) {
                        if (identifier4.getIdentifier().equals(specialty.getIdentifier())) {
                            return false;
                        }
                    }
                }
                if (!(dBFilter.getLocations() == null || location == null)) {
                    for (DBLocation identifier5 : dBFilter.getLocations()) {
                        if (identifier5.getIdentifier().equals(location.getIdentifier())) {
                            return false;
                        }
                    }
                }
            } else if (i != 4) {
                return true;
            }
            if (dBFilter.getProfessions() == null || dBFilter.getProfessions().isEmpty()) {
                z3 = true;
            } else {
                if (profession != null) {
                    Iterator<DBProfession> it3 = dBFilter.getProfessions().iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            if (it3.next().getIdentifier().equals(profession.getIdentifier())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                z3 = false;
            }
            if (!z3) {
                return true;
            }
            if (dBFilter.getSpecialties() == null || dBFilter.getSpecialties().isEmpty()) {
                z4 = true;
            } else {
                if (specialty != null) {
                    Iterator<DBSpecialty> it4 = dBFilter.getSpecialties().iterator();
                    while (true) {
                        if (it4.hasNext()) {
                            if (it4.next().getIdentifier().equals(specialty.getIdentifier())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    z4 = true;
                }
                z4 = false;
            }
            if (!z4) {
                return true;
            }
            if (dBFilter.getLocations() == null || dBFilter.getLocations().isEmpty()) {
                z5 = true;
            } else if (location != null) {
                Iterator<DBLocation> it5 = dBFilter.getLocations().iterator();
                while (true) {
                    if (it5.hasNext()) {
                        if (it5.next().getIdentifier().equals(location.getIdentifier())) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                z5 = true;
            }
            return !z5;
        } else {
            if (dBFilter.getProfessions() == null || dBFilter.getProfessions().isEmpty()) {
                z = true;
            } else {
                if (profession != null) {
                    Iterator<DBProfession> it6 = dBFilter.getProfessions().iterator();
                    while (true) {
                        if (it6.hasNext()) {
                            if (it6.next().getIdentifier().equals(profession.getIdentifier())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                z = false;
            }
            if (!z) {
                return false;
            }
            if (dBFilter.getSpecialties() == null || dBFilter.getSpecialties().isEmpty()) {
                z2 = true;
            } else {
                if (specialty != null) {
                    Iterator<DBSpecialty> it7 = dBFilter.getSpecialties().iterator();
                    while (true) {
                        if (it7.hasNext()) {
                            if (it7.next().getIdentifier().equals(specialty.getIdentifier())) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    z2 = true;
                }
                z2 = false;
            }
            if (!z2) {
                return false;
            }
            if (dBFilter.getLocations() == null || dBFilter.getLocations().isEmpty()) {
                return true;
            }
            if (location != null) {
                for (DBLocation identifier6 : dBFilter.getLocations()) {
                    if (identifier6.getIdentifier().equals(location.getIdentifier())) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* renamed from: com.wbmd.qxcalculator.model.db.DBFilter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$model$contentItems$common$Filter$FilterType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.wbmd.qxcalculator.model.contentItems.common.Filter$FilterType[] r0 = com.wbmd.qxcalculator.model.contentItems.common.Filter.FilterType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$model$contentItems$common$Filter$FilterType = r0
                com.wbmd.qxcalculator.model.contentItems.common.Filter$FilterType r1 = com.wbmd.qxcalculator.model.contentItems.common.Filter.FilterType.INCLUDE_MATCH_ANY     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$common$Filter$FilterType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.model.contentItems.common.Filter$FilterType r1 = com.wbmd.qxcalculator.model.contentItems.common.Filter.FilterType.INCLUDE_MATCH_ALL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$common$Filter$FilterType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.model.contentItems.common.Filter$FilterType r1 = com.wbmd.qxcalculator.model.contentItems.common.Filter.FilterType.EXCLUDE_MATCH_ANY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$common$Filter$FilterType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.qxcalculator.model.contentItems.common.Filter$FilterType r1 = com.wbmd.qxcalculator.model.contentItems.common.Filter.FilterType.EXCLUDE_MATCH_ALL     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.db.DBFilter.AnonymousClass1.<clinit>():void");
        }
    }
}
