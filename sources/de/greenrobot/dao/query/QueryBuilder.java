package de.greenrobot.dao.query;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.DaoLog;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder<T> {
    public static boolean LOG_SQL;
    public static boolean LOG_VALUES;
    private final AbstractDao<T, ?> dao;
    private final List<Join<T, ?>> joins;
    private Integer limit;
    private Integer offset;
    private StringBuilder orderBuilder;
    private final String tablePrefix;
    private final List<Object> values;
    private final WhereCollector<T> whereCollector;

    public static <T2> QueryBuilder<T2> internalCreate(AbstractDao<T2, ?> abstractDao) {
        return new QueryBuilder<>(abstractDao);
    }

    protected QueryBuilder(AbstractDao<T, ?> abstractDao) {
        this(abstractDao, "T");
    }

    protected QueryBuilder(AbstractDao<T, ?> abstractDao, String str) {
        this.dao = abstractDao;
        this.tablePrefix = str;
        this.values = new ArrayList();
        this.joins = new ArrayList();
        this.whereCollector = new WhereCollector<>(abstractDao, str);
    }

    private void checkOrderBuilder() {
        StringBuilder sb = this.orderBuilder;
        if (sb == null) {
            this.orderBuilder = new StringBuilder();
        } else if (sb.length() > 0) {
            this.orderBuilder.append(",");
        }
    }

    public QueryBuilder<T> where(WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        this.whereCollector.add(whereCondition, whereConditionArr);
        return this;
    }

    public QueryBuilder<T> whereOr(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        this.whereCollector.add(or(whereCondition, whereCondition2, whereConditionArr), new WhereCondition[0]);
        return this;
    }

    public WhereCondition or(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.whereCollector.combineWhereConditions(" OR ", whereCondition, whereCondition2, whereConditionArr);
    }

    public WhereCondition and(WhereCondition whereCondition, WhereCondition whereCondition2, WhereCondition... whereConditionArr) {
        return this.whereCollector.combineWhereConditions(" AND ", whereCondition, whereCondition2, whereConditionArr);
    }

    public <J> Join<T, J> join(Class<J> cls, Property property) {
        return join(this.dao.getPkProperty(), cls, property);
    }

    public <J> Join<T, J> join(Property property, Class<J> cls) {
        AbstractDao<?, ?> dao2 = this.dao.getSession().getDao(cls);
        return addJoin(this.tablePrefix, property, dao2, dao2.getPkProperty());
    }

    public <J> Join<T, J> join(Property property, Class<J> cls, Property property2) {
        return addJoin(this.tablePrefix, property, this.dao.getSession().getDao(cls), property2);
    }

    public <J> Join<T, J> join(Join<?, T> join, Property property, Class<J> cls, Property property2) {
        return addJoin(join.tablePrefix, property, this.dao.getSession().getDao(cls), property2);
    }

    private <J> Join<T, J> addJoin(String str, Property property, AbstractDao<J, ?> abstractDao, Property property2) {
        Join join = new Join(str, property, abstractDao, property2, "J" + (this.joins.size() + 1));
        this.joins.add(join);
        return join;
    }

    public QueryBuilder<T> orderAsc(Property... propertyArr) {
        orderAscOrDesc(" ASC", propertyArr);
        return this;
    }

    public QueryBuilder<T> orderDesc(Property... propertyArr) {
        orderAscOrDesc(" DESC", propertyArr);
        return this;
    }

    private void orderAscOrDesc(String str, Property... propertyArr) {
        for (Property property : propertyArr) {
            checkOrderBuilder();
            append(this.orderBuilder, property);
            if (String.class.equals(property.type)) {
                this.orderBuilder.append(" COLLATE LOCALIZED");
            }
            this.orderBuilder.append(str);
        }
    }

    public QueryBuilder<T> orderCustom(Property property, String str) {
        checkOrderBuilder();
        append(this.orderBuilder, property).append(' ');
        this.orderBuilder.append(str);
        return this;
    }

    public QueryBuilder<T> orderRaw(String str) {
        checkOrderBuilder();
        this.orderBuilder.append(str);
        return this;
    }

    /* access modifiers changed from: protected */
    public StringBuilder append(StringBuilder sb, Property property) {
        this.whereCollector.checkProperty(property);
        sb.append(this.tablePrefix);
        sb.append('.');
        sb.append('\'');
        sb.append(property.columnName);
        sb.append('\'');
        return sb;
    }

    public QueryBuilder<T> limit(int i) {
        this.limit = Integer.valueOf(i);
        return this;
    }

    public QueryBuilder<T> offset(int i) {
        this.offset = Integer.valueOf(i);
        return this;
    }

    public Query<T> build() {
        int i;
        StringBuilder sb = new StringBuilder(SqlUtils.createSqlSelect(this.dao.getTablename(), this.tablePrefix, this.dao.getAllColumns()));
        appendJoinsAndWheres(sb, this.tablePrefix);
        StringBuilder sb2 = this.orderBuilder;
        if (sb2 != null && sb2.length() > 0) {
            sb.append(" ORDER BY ");
            sb.append(this.orderBuilder);
        }
        int i2 = -1;
        if (this.limit != null) {
            sb.append(" LIMIT ?");
            this.values.add(this.limit);
            i = this.values.size() - 1;
        } else {
            i = -1;
        }
        if (this.offset != null) {
            if (this.limit != null) {
                sb.append(" OFFSET ?");
                this.values.add(this.offset);
                i2 = this.values.size() - 1;
            } else {
                throw new IllegalStateException("Offset cannot be set without limit");
            }
        }
        String sb3 = sb.toString();
        checkLog(sb3);
        return Query.create(this.dao, sb3, this.values.toArray(), i, i2);
    }

    public DeleteQuery<T> buildDelete() {
        if (this.joins.isEmpty()) {
            String tablename = this.dao.getTablename();
            StringBuilder sb = new StringBuilder(SqlUtils.createSqlDelete(tablename, (String[]) null));
            appendJoinsAndWheres(sb, this.tablePrefix);
            String replace = sb.toString().replace(this.tablePrefix + ".\"", '\"' + tablename + "\".\"");
            checkLog(replace);
            return DeleteQuery.create(this.dao, replace, this.values.toArray());
        }
        throw new DaoException("JOINs are not supported for DELETE queries");
    }

    public CountQuery<T> buildCount() {
        StringBuilder sb = new StringBuilder(SqlUtils.createSqlSelectCountStar(this.dao.getTablename(), this.tablePrefix));
        appendJoinsAndWheres(sb, this.tablePrefix);
        String sb2 = sb.toString();
        checkLog(sb2);
        return CountQuery.create(this.dao, sb2, this.values.toArray());
    }

    private void checkLog(String str) {
        if (LOG_SQL) {
            DaoLog.d("Built SQL for query: " + str);
        }
        if (LOG_VALUES) {
            DaoLog.d("Values for query: " + this.values);
        }
    }

    private void appendJoinsAndWheres(StringBuilder sb, String str) {
        this.values.clear();
        for (Join next : this.joins) {
            sb.append(" JOIN ");
            sb.append(next.daoDestination.getTablename());
            sb.append(' ');
            sb.append(next.tablePrefix);
            sb.append(" ON ");
            SqlUtils.appendProperty(sb, next.sourceTablePrefix, next.joinPropertySource).append('=');
            SqlUtils.appendProperty(sb, next.tablePrefix, next.joinPropertyDestination);
        }
        boolean z = !this.whereCollector.isEmpty();
        if (z) {
            sb.append(" WHERE ");
            this.whereCollector.appendWhereClause(sb, str, this.values);
        }
        for (Join next2 : this.joins) {
            if (!next2.whereCollector.isEmpty()) {
                if (!z) {
                    sb.append(" WHERE ");
                    z = true;
                } else {
                    sb.append(" AND ");
                }
                next2.whereCollector.appendWhereClause(sb, next2.tablePrefix, this.values);
            }
        }
    }

    public List<T> list() {
        return build().list();
    }

    public LazyList<T> listLazy() {
        return build().listLazy();
    }

    public LazyList<T> listLazyUncached() {
        return build().listLazyUncached();
    }

    public CloseableListIterator<T> listIterator() {
        return build().listIterator();
    }

    public T unique() {
        return build().unique();
    }

    public T uniqueOrThrow() {
        return build().uniqueOrThrow();
    }

    public long count() {
        return buildCount().count();
    }
}
