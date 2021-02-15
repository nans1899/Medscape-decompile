package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

class ObjectScanner implements Scanner {
    private StructureBuilder builder;
    private Detail detail;
    private ClassScanner scanner;
    private Structure structure;
    private Support support;

    public ObjectScanner(Detail detail2, Support support2) throws Exception {
        this.scanner = new ClassScanner(detail2, support2);
        this.builder = new StructureBuilder(this, detail2, support2);
        this.support = support2;
        this.detail = detail2;
        scan(detail2);
    }

    public Signature getSignature() {
        return this.scanner.getSignature();
    }

    public List<Signature> getSignatures() {
        return this.scanner.getSignatures();
    }

    public ParameterMap getParameters() {
        return this.scanner.getParameters();
    }

    public Instantiator getInstantiator() {
        return this.structure.getInstantiator();
    }

    public Class getType() {
        return this.detail.getType();
    }

    public Decorator getDecorator() {
        return this.scanner.getDecorator();
    }

    public Caller getCaller(Context context) {
        return new Caller(this, context);
    }

    public Section getSection() {
        return this.structure.getSection();
    }

    public Version getRevision() {
        return this.structure.getRevision();
    }

    public Order getOrder() {
        return this.scanner.getOrder();
    }

    public Label getVersion() {
        return this.structure.getVersion();
    }

    public Label getText() {
        return this.structure.getText();
    }

    public String getName() {
        return this.detail.getName();
    }

    public Function getCommit() {
        return this.scanner.getCommit();
    }

    public Function getValidate() {
        return this.scanner.getValidate();
    }

    public Function getPersist() {
        return this.scanner.getPersist();
    }

    public Function getComplete() {
        return this.scanner.getComplete();
    }

    public Function getReplace() {
        return this.scanner.getReplace();
    }

    public Function getResolve() {
        return this.scanner.getResolve();
    }

    public boolean isPrimitive() {
        return this.structure.isPrimitive();
    }

    public boolean isEmpty() {
        return this.scanner.getRoot() == null;
    }

    public boolean isStrict() {
        return this.detail.isStrict();
    }

    private void scan(Detail detail2) throws Exception {
        order(detail2);
        field(detail2);
        method(detail2);
        validate(detail2);
        commit(detail2);
    }

    private void order(Detail detail2) throws Exception {
        this.builder.assemble(detail2.getType());
    }

    private void commit(Detail detail2) throws Exception {
        Class type = detail2.getType();
        if (this.structure == null) {
            this.structure = this.builder.build(type);
        }
        this.builder = null;
    }

    private void validate(Detail detail2) throws Exception {
        Class type = detail2.getType();
        this.builder.commit(type);
        this.builder.validate(type);
    }

    private void field(Detail detail2) throws Exception {
        Iterator it = this.support.getFields(detail2.getType(), detail2.getOverride()).iterator();
        while (it.hasNext()) {
            Contact contact = (Contact) it.next();
            Annotation annotation = contact.getAnnotation();
            if (annotation != null) {
                this.builder.process(contact, annotation);
            }
        }
    }

    private void method(Detail detail2) throws Exception {
        Iterator it = this.support.getMethods(detail2.getType(), detail2.getOverride()).iterator();
        while (it.hasNext()) {
            Contact contact = (Contact) it.next();
            Annotation annotation = contact.getAnnotation();
            if (annotation != null) {
                this.builder.process(contact, annotation);
            }
        }
    }
}
