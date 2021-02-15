package org.simpleframework.xml.core;

import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class DetailExtractor {
    private final Cache<Detail> details;
    private final Cache<ContactList> fields;
    private final Cache<ContactList> methods;
    private final DefaultType override;
    private final Support support;

    public DetailExtractor(Support support2) {
        this(support2, (DefaultType) null);
    }

    public DetailExtractor(Support support2, DefaultType defaultType) {
        this.methods = new ConcurrentCache();
        this.fields = new ConcurrentCache();
        this.details = new ConcurrentCache();
        this.override = defaultType;
        this.support = support2;
    }

    public Detail getDetail(Class cls) {
        Detail fetch = this.details.fetch(cls);
        if (fetch != null) {
            return fetch;
        }
        DetailScanner detailScanner = new DetailScanner(cls, this.override);
        this.details.cache(cls, detailScanner);
        return detailScanner;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r1 = getDetail(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.simpleframework.xml.core.ContactList getFields(java.lang.Class r3) throws java.lang.Exception {
        /*
            r2 = this;
            org.simpleframework.xml.util.Cache<org.simpleframework.xml.core.ContactList> r0 = r2.fields
            java.lang.Object r0 = r0.fetch(r3)
            org.simpleframework.xml.core.ContactList r0 = (org.simpleframework.xml.core.ContactList) r0
            if (r0 != 0) goto L_0x0014
            org.simpleframework.xml.core.Detail r1 = r2.getDetail(r3)
            if (r1 == 0) goto L_0x0014
            org.simpleframework.xml.core.ContactList r0 = r2.getFields(r3, r1)
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.simpleframework.xml.core.DetailExtractor.getFields(java.lang.Class):org.simpleframework.xml.core.ContactList");
    }

    private ContactList getFields(Class cls, Detail detail) throws Exception {
        FieldScanner fieldScanner = new FieldScanner(detail, this.support);
        if (detail != null) {
            this.fields.cache(cls, fieldScanner);
        }
        return fieldScanner;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r1 = getDetail(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.simpleframework.xml.core.ContactList getMethods(java.lang.Class r3) throws java.lang.Exception {
        /*
            r2 = this;
            org.simpleframework.xml.util.Cache<org.simpleframework.xml.core.ContactList> r0 = r2.methods
            java.lang.Object r0 = r0.fetch(r3)
            org.simpleframework.xml.core.ContactList r0 = (org.simpleframework.xml.core.ContactList) r0
            if (r0 != 0) goto L_0x0014
            org.simpleframework.xml.core.Detail r1 = r2.getDetail(r3)
            if (r1 == 0) goto L_0x0014
            org.simpleframework.xml.core.ContactList r0 = r2.getMethods(r3, r1)
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.simpleframework.xml.core.DetailExtractor.getMethods(java.lang.Class):org.simpleframework.xml.core.ContactList");
    }

    private ContactList getMethods(Class cls, Detail detail) throws Exception {
        MethodScanner methodScanner = new MethodScanner(detail, this.support);
        if (detail != null) {
            this.methods.cache(cls, methodScanner);
        }
        return methodScanner;
    }
}
