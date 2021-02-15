package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;

class MethodContact implements Contact {
    private MethodPart get;
    private Class item;
    private Class[] items;
    private Annotation label;
    private String name;
    private Class owner;
    private MethodPart set;
    private Class type;

    public MethodContact(MethodPart methodPart) {
        this(methodPart, (MethodPart) null);
    }

    public MethodContact(MethodPart methodPart, MethodPart methodPart2) {
        this.owner = methodPart.getDeclaringClass();
        this.label = methodPart.getAnnotation();
        this.items = methodPart.getDependents();
        this.item = methodPart.getDependent();
        this.type = methodPart.getType();
        this.name = methodPart.getName();
        this.set = methodPart2;
        this.get = methodPart;
    }

    public boolean isReadOnly() {
        return this.set == null;
    }

    public MethodPart getRead() {
        return this.get;
    }

    public MethodPart getWrite() {
        return this.set;
    }

    public Annotation getAnnotation() {
        return this.label;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0013, code lost:
        r1 = r2.set;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T extends java.lang.annotation.Annotation> T getAnnotation(java.lang.Class<T> r3) {
        /*
            r2 = this;
            org.simpleframework.xml.core.MethodPart r0 = r2.get
            java.lang.annotation.Annotation r0 = r0.getAnnotation(r3)
            java.lang.annotation.Annotation r1 = r2.label
            java.lang.Class r1 = r1.annotationType()
            if (r3 != r1) goto L_0x0011
            java.lang.annotation.Annotation r3 = r2.label
            return r3
        L_0x0011:
            if (r0 != 0) goto L_0x001c
            org.simpleframework.xml.core.MethodPart r1 = r2.set
            if (r1 == 0) goto L_0x001c
            java.lang.annotation.Annotation r3 = r1.getAnnotation(r3)
            return r3
        L_0x001c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.simpleframework.xml.core.MethodContact.getAnnotation(java.lang.Class):java.lang.annotation.Annotation");
    }

    public Class getType() {
        return this.type;
    }

    public Class getDependent() {
        return this.item;
    }

    public Class[] getDependents() {
        return this.items;
    }

    public Class getDeclaringClass() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }

    public void set(Object obj, Object obj2) throws Exception {
        Class<?> declaringClass = this.get.getMethod().getDeclaringClass();
        MethodPart methodPart = this.set;
        if (methodPart != null) {
            methodPart.getMethod().invoke(obj, new Object[]{obj2});
            return;
        }
        throw new MethodException("Property '%s' is read only in %s", this.name, declaringClass);
    }

    public Object get(Object obj) throws Exception {
        return this.get.getMethod().invoke(obj, new Object[0]);
    }

    public String toString() {
        return String.format("method '%s'", new Object[]{this.name});
    }
}
