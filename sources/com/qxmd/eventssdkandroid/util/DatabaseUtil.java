package com.qxmd.eventssdkandroid.util;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.query.WhereCondition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseUtil {
    public static final int BATCH_AMOUNT = 500;

    private static int min(int i, int i2) {
        return i <= i2 ? i : i2;
    }

    public static <DataType, DbType> List<DbType> getAllWithPropertyInData(AbstractDao<DbType, Long> abstractDao, Property property, List<DataType> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 500;
            arrayList.addAll(abstractDao.queryBuilder().where(property.in((Collection<?>) list.subList(i, min(i2, list.size()))), new WhereCondition[0]).list());
            i = i2;
        }
        return arrayList;
    }

    public static <DataType, DbType> List<DbType> getAllWithPropertyNotInData(AbstractDao<DbType, Long> abstractDao, Property property, List<DataType> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 500;
            arrayList.addAll(abstractDao.queryBuilder().where(property.notIn((Collection<?>) list.subList(i, min(i2, list.size()))), new WhereCondition[0]).list());
            i = i2;
        }
        return arrayList;
    }

    public static <DataType, DbType> List<DbType> getAllWithCompoundPropertiesInData(AbstractDao<DbType, Long> abstractDao, Property property, List<DataType> list, Property property2, List<DataType> list2) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 500;
            int min = min(i2, list.size());
            int i3 = 0;
            while (i3 < list2.size()) {
                int i4 = i3 + 500;
                int min2 = min(i4, list2.size());
                arrayList.addAll(abstractDao.queryBuilder().where(property.in((Collection<?>) list.subList(i, min)), property2.in((Collection<?>) list2.subList(i3, min2))).list());
                i3 = i4;
            }
            i = i2;
        }
        return arrayList;
    }
}
