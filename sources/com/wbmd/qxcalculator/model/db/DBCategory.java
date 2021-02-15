package com.wbmd.qxcalculator.model.db;

import android.util.Log;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.wbmd.qxcalculator.model.db.DBCategoryDao;
import com.wbmd.qxcalculator.model.db.DBContentItemDao;
import com.wbmd.qxcalculator.util.DatabaseHelper;
import de.greenrobot.dao.DaoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBCategory {
    public static final String TAG = DBCategory.class.getSimpleName();
    private Long categoryId;
    private Long contentItemCategoryCount;
    private Long contentItemId;
    private String contentSpecificIdentifier;
    private transient DaoSession daoSession;
    private Long id;
    private String identifier;
    private Integer itemWeight;
    private transient DBCategoryDao myDao;
    private String name;
    public String overrideName;
    private List<DBCategory> subCategories;
    private Integer weight;

    public DBCategory() {
    }

    public DBCategory(Long l) {
        this.id = l;
    }

    public DBCategory(Long l, String str, String str2, String str3, Integer num, Integer num2, Long l2, Long l3, Long l4) {
        this.id = l;
        this.contentSpecificIdentifier = str;
        this.identifier = str2;
        this.name = str3;
        this.weight = num;
        this.itemWeight = num2;
        this.contentItemCategoryCount = l2;
        this.contentItemId = l3;
        this.categoryId = l4;
    }

    public void __setDaoSession(DaoSession daoSession2) {
        this.daoSession = daoSession2;
        this.myDao = daoSession2 != null ? daoSession2.getDBCategoryDao() : null;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long l) {
        this.id = l;
    }

    public String getContentSpecificIdentifier() {
        return this.contentSpecificIdentifier;
    }

    public void setContentSpecificIdentifier(String str) {
        this.contentSpecificIdentifier = str;
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

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer num) {
        this.weight = num;
    }

    public Integer getItemWeight() {
        return this.itemWeight;
    }

    public void setItemWeight(Integer num) {
        this.itemWeight = num;
    }

    public Long getContentItemCategoryCount() {
        return this.contentItemCategoryCount;
    }

    public void setContentItemCategoryCount(Long l) {
        this.contentItemCategoryCount = l;
    }

    public Long getContentItemId() {
        return this.contentItemId;
    }

    public void setContentItemId(Long l) {
        this.contentItemId = l;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long l) {
        this.categoryId = l;
    }

    public List<DBCategory> getSubCategories() {
        if (this.subCategories == null) {
            DaoSession daoSession2 = this.daoSession;
            if (daoSession2 != null) {
                List<DBCategory> _queryDBCategory_SubCategories = daoSession2.getDBCategoryDao()._queryDBCategory_SubCategories(this.id);
                synchronized (this) {
                    if (this.subCategories == null) {
                        this.subCategories = _queryDBCategory_SubCategories;
                    }
                }
            } else {
                throw new DaoException("Entity is detached from DAO context");
            }
        }
        return this.subCategories;
    }

    public synchronized void resetSubCategories() {
        this.subCategories = null;
    }

    public void delete() {
        DBCategoryDao dBCategoryDao = this.myDao;
        if (dBCategoryDao != null) {
            dBCategoryDao.delete(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void update() {
        DBCategoryDao dBCategoryDao = this.myDao;
        if (dBCategoryDao != null) {
            dBCategoryDao.update(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public void refresh() {
        DBCategoryDao dBCategoryDao = this.myDao;
        if (dBCategoryDao != null) {
            dBCategoryDao.refresh(this);
            return;
        }
        throw new DaoException("Entity is detached from DAO context");
    }

    public static void preloadSubCategoryRelations(DaoSession daoSession2, List<DBCategory> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (DBCategory id2 : list) {
            arrayList.add(id2.getId());
        }
        List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBCategoryDao(), DBCategoryDao.Properties.CategoryId, arrayList);
        ArrayList<DBCategory> arrayList2 = new ArrayList<>(allWithPropertyInData);
        for (DBCategory next : list) {
            if (next != null) {
                next.subCategories = new ArrayList();
                for (DBCategory dBCategory : arrayList2) {
                    if (dBCategory != null && dBCategory.getCategoryId().equals(next.getId())) {
                        next.subCategories.add(dBCategory);
                    }
                }
            }
        }
        if (!allWithPropertyInData.isEmpty()) {
            preloadSubCategoryRelations(daoSession2, allWithPropertyInData);
        }
    }

    public boolean hasParent() {
        return this.categoryId != null;
    }

    public static void deleteUnusedCategories(DaoSession daoSession2) {
        List list = daoSession2.getDBCategoryDao().queryBuilder().where(DBCategoryDao.Properties.ContentItemId.isNull(), DBCategoryDao.Properties.CategoryId.isNull()).list();
        String str = TAG;
        Log.d(str, "Purging DBCategory: " + list.size());
        deleteCategories(daoSession2, list);
    }

    public static void deleteCategories(DaoSession daoSession2, List<DBCategory> list) {
        if (daoSession2 != null && list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (DBCategory next : list) {
                if (next != null) {
                    if (next.getContentItemId() != null) {
                        arrayList.add(next.getContentItemId());
                    }
                    if (next.getCategoryId() != null) {
                        arrayList2.add(next.getCategoryId());
                    }
                    if (next.getSubCategories() != null) {
                        arrayList3.addAll(next.getSubCategories());
                    }
                }
            }
            if (!arrayList3.isEmpty()) {
                arrayList3.removeAll(Collections.singletonList((Object) null));
                deleteCategories(daoSession2, arrayList3);
            }
            if (!arrayList.isEmpty()) {
                List<DBContentItem> allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBContentItemDao(), DBContentItemDao.Properties.Id, arrayList);
                if (!allWithPropertyInData.isEmpty()) {
                    for (DBContentItem resetCategories : allWithPropertyInData) {
                        resetCategories.resetCategories();
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                List<DBCategory> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBCategoryDao(), DBCategoryDao.Properties.Id, arrayList2);
                if (!allWithPropertyInData2.isEmpty()) {
                    for (DBCategory resetSubCategories : allWithPropertyInData2) {
                        resetSubCategories.resetSubCategories();
                    }
                }
            }
            daoSession2.getDBCategoryDao().deleteInTx(list);
        }
    }

    public static synchronized List<DBCategory> insertAndRetrieveDbEntities(DaoSession daoSession2, List<Category> list) {
        synchronized (DBCategory.class) {
            if (daoSession2 == null || list == null) {
                ArrayList arrayList = new ArrayList();
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (Category next : list) {
                arrayList2.add(next.contentSpecificIdentifier);
                if (next.subCategories != null) {
                    arrayList3.addAll(next.subCategories);
                }
            }
            List allWithPropertyInData = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBCategoryDao(), DBCategoryDao.Properties.ContentSpecificIdentifier, arrayList2);
            ArrayList arrayList4 = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            List arrayList5 = new ArrayList();
            if (!arrayList3.isEmpty()) {
                arrayList5 = insertAndRetrieveDbEntities(daoSession2, arrayList3);
            }
            Iterator<Category> it = list.iterator();
            while (true) {
                DBCategory dBCategory = null;
                if (!it.hasNext()) {
                    break;
                }
                Category next2 = it.next();
                if (linkedHashMap.containsKey(next2)) {
                    dBCategory = (DBCategory) linkedHashMap.get(next2);
                }
                if (dBCategory == null) {
                    Iterator it2 = allWithPropertyInData.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        DBCategory dBCategory2 = (DBCategory) it2.next();
                        if (dBCategory2.getContentSpecificIdentifier().equals(next2.contentSpecificIdentifier)) {
                            dBCategory = dBCategory2;
                            break;
                        }
                    }
                }
                if (dBCategory == null) {
                    dBCategory = new DBCategory();
                    arrayList4.add(dBCategory);
                }
                dBCategory.setIdentifier(next2.identifier);
                dBCategory.setContentSpecificIdentifier(next2.contentSpecificIdentifier);
                dBCategory.setName(next2.name);
                dBCategory.setWeight(next2.weight);
                dBCategory.setItemWeight(next2.itemWeight);
                linkedHashMap.put(next2, dBCategory);
            }
            if (!arrayList4.isEmpty()) {
                daoSession2.getDBCategoryDao().insertInTx(arrayList4);
            }
            ArrayList arrayList6 = new ArrayList(linkedHashMap.size());
            for (DBCategory id2 : linkedHashMap.values()) {
                arrayList6.add(id2.getId());
            }
            List<DBCategory> allWithPropertyInData2 = DatabaseHelper.getAllWithPropertyInData(daoSession2.getDBCategoryDao(), DBCategoryDao.Properties.CategoryId, arrayList6);
            for (DBCategory categoryId2 : allWithPropertyInData2) {
                categoryId2.setCategoryId((Long) null);
            }
            ArrayList arrayList7 = new ArrayList(arrayList5.size());
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                Category category = (Category) entry.getKey();
                DBCategory dBCategory3 = (DBCategory) entry.getValue();
                if (category.subCategories != null && !category.subCategories.isEmpty()) {
                    for (Category next3 : category.subCategories) {
                        Iterator it3 = arrayList5.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            DBCategory dBCategory4 = (DBCategory) it3.next();
                            if (dBCategory4.getContentSpecificIdentifier().equals(next3.contentSpecificIdentifier)) {
                                dBCategory4.setCategoryId(dBCategory3.getId());
                                arrayList7.add(dBCategory4);
                                break;
                            }
                        }
                    }
                }
                dBCategory3.resetSubCategories();
            }
            ArrayList arrayList8 = new ArrayList(allWithPropertyInData2.size());
            for (DBCategory dBCategory5 : allWithPropertyInData2) {
                if (dBCategory5.getCategoryId() == null) {
                    arrayList8.add(dBCategory5);
                }
            }
            if (!arrayList8.isEmpty()) {
                daoSession2.getDBCategoryDao().deleteInTx(arrayList8);
            }
            if (!arrayList7.isEmpty()) {
                daoSession2.getDBCategoryDao().updateInTx(arrayList7);
            }
            ArrayList arrayList9 = new ArrayList(linkedHashMap.values());
            daoSession2.getDBCategoryDao().updateInTx(arrayList9);
            return arrayList9;
        }
    }

    public boolean isMenuItem() {
        return getName().toLowerCase().startsWith(Category.K_MENU_CATEGORY);
    }
}
