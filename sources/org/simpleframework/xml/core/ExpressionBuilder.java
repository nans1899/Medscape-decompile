package org.simpleframework.xml.core;

import org.simpleframework.xml.stream.Format;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.LimitedCache;

class ExpressionBuilder {
    private final Cache<Expression> cache = new LimitedCache();
    private final Format format;
    private final Class type;

    public ExpressionBuilder(Detail detail, Support support) {
        this.format = support.getFormat();
        this.type = detail.getType();
    }

    public Expression build(String str) throws Exception {
        Expression fetch = this.cache.fetch(str);
        return fetch == null ? create(str) : fetch;
    }

    private Expression create(String str) throws Exception {
        PathParser pathParser = new PathParser(str, new ClassType(this.type), this.format);
        Cache<Expression> cache2 = this.cache;
        if (cache2 != null) {
            cache2.cache(str, pathParser);
        }
        return pathParser;
    }
}
