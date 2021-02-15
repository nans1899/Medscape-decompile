package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

class ConstructorScanner {
    private Signature primary;
    private ParameterMap registry = new ParameterMap();
    private List<Signature> signatures = new ArrayList();
    private Support support;

    public ConstructorScanner(Detail detail, Support support2) throws Exception {
        this.support = support2;
        scan(detail);
    }

    public Signature getSignature() {
        return this.primary;
    }

    public List<Signature> getSignatures() {
        return new ArrayList(this.signatures);
    }

    public ParameterMap getParameters() {
        return this.registry;
    }

    private void scan(Detail detail) throws Exception {
        Constructor[] constructors = detail.getConstructors();
        if (detail.isInstantiable()) {
            for (Constructor constructor : constructors) {
                if (!detail.isPrimitive()) {
                    scan(constructor);
                }
            }
            return;
        }
        throw new ConstructorException("Can not construct inner %s", detail);
    }

    private void scan(Constructor constructor) throws Exception {
        SignatureScanner signatureScanner = new SignatureScanner(constructor, this.registry, this.support);
        if (signatureScanner.isValid()) {
            for (Signature next : signatureScanner.getSignatures()) {
                if (next.size() == 0) {
                    this.primary = next;
                }
                this.signatures.add(next);
            }
        }
    }
}
