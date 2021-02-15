package org.simpleframework.xml.transform;

class DefaultMatcher implements Matcher {
    private Matcher array = new ArrayMatcher(this);
    private Matcher matcher;
    private Matcher primitive = new PrimitiveMatcher();
    private Matcher stock = new PackageMatcher();

    public DefaultMatcher(Matcher matcher2) {
        this.matcher = matcher2;
    }

    public Transform match(Class cls) throws Exception {
        Transform match = this.matcher.match(cls);
        if (match != null) {
            return match;
        }
        return matchType(cls);
    }

    private Transform matchType(Class cls) throws Exception {
        if (cls.isArray()) {
            return this.array.match(cls);
        }
        if (cls.isPrimitive()) {
            return this.primitive.match(cls);
        }
        return this.stock.match(cls);
    }
}
