package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementArray;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.ElementMapUnion;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.Version;

class StructureBuilder {
    private ModelAssembler assembler;
    private LabelMap attributes;
    private ExpressionBuilder builder;
    private LabelMap elements;
    private Instantiator factory;
    private boolean primitive;
    private InstantiatorBuilder resolver;
    private Model root;
    private Scanner scanner;
    private Support support;
    private Label text;
    private LabelMap texts;
    private Label version;

    public StructureBuilder(Scanner scanner2, Detail detail, Support support2) throws Exception {
        ExpressionBuilder expressionBuilder = new ExpressionBuilder(detail, support2);
        this.builder = expressionBuilder;
        this.assembler = new ModelAssembler(expressionBuilder, detail, support2);
        this.resolver = new InstantiatorBuilder(scanner2, detail);
        this.root = new TreeModel(scanner2, detail);
        this.attributes = new LabelMap(scanner2);
        this.elements = new LabelMap(scanner2);
        this.texts = new LabelMap(scanner2);
        this.scanner = scanner2;
        this.support = support2;
    }

    public void assemble(Class cls) throws Exception {
        Order order = this.scanner.getOrder();
        if (order != null) {
            this.assembler.assemble(this.root, order);
        }
    }

    public void process(Contact contact, Annotation annotation) throws Exception {
        if (annotation instanceof Attribute) {
            process(contact, annotation, this.attributes);
        }
        if (annotation instanceof ElementUnion) {
            union(contact, annotation, this.elements);
        }
        if (annotation instanceof ElementListUnion) {
            union(contact, annotation, this.elements);
        }
        if (annotation instanceof ElementMapUnion) {
            union(contact, annotation, this.elements);
        }
        if (annotation instanceof ElementList) {
            process(contact, annotation, this.elements);
        }
        if (annotation instanceof ElementArray) {
            process(contact, annotation, this.elements);
        }
        if (annotation instanceof ElementMap) {
            process(contact, annotation, this.elements);
        }
        if (annotation instanceof Element) {
            process(contact, annotation, this.elements);
        }
        if (annotation instanceof Version) {
            version(contact, annotation);
        }
        if (annotation instanceof Text) {
            text(contact, annotation);
        }
    }

    private void union(Contact contact, Annotation annotation, LabelMap labelMap) throws Exception {
        for (Label next : this.support.getLabels(contact, annotation)) {
            String path = next.getPath();
            String name = next.getName();
            if (labelMap.get(path) == null) {
                process(contact, next, labelMap);
            } else {
                throw new PersistenceException("Duplicate annotation of name '%s' on %s", name, next);
            }
        }
    }

    private void process(Contact contact, Annotation annotation, LabelMap labelMap) throws Exception {
        Label label = this.support.getLabel(contact, annotation);
        String path = label.getPath();
        String name = label.getName();
        if (labelMap.get(path) == null) {
            process(contact, label, labelMap);
        } else {
            throw new PersistenceException("Duplicate annotation of name '%s' on %s", name, contact);
        }
    }

    private void process(Contact contact, Label label, LabelMap labelMap) throws Exception {
        Expression expression = label.getExpression();
        String path = label.getPath();
        Model model = this.root;
        if (!expression.isEmpty()) {
            model = register(expression);
        }
        this.resolver.register(label);
        model.register(label);
        labelMap.put(path, label);
    }

    private void text(Contact contact, Annotation annotation) throws Exception {
        Label label = this.support.getLabel(contact, annotation);
        Expression expression = label.getExpression();
        String path = label.getPath();
        Model model = this.root;
        if (!expression.isEmpty()) {
            model = register(expression);
        }
        if (this.texts.get(path) == null) {
            this.resolver.register(label);
            model.register(label);
            this.texts.put(path, label);
            return;
        }
        throw new TextException("Multiple text annotations in %s", annotation);
    }

    private void version(Contact contact, Annotation annotation) throws Exception {
        Label label = this.support.getLabel(contact, annotation);
        if (this.version == null) {
            this.version = label;
        } else {
            throw new AttributeException("Multiple version annotations in %s", annotation);
        }
    }

    public Structure build(Class cls) throws Exception {
        return new Structure(this.factory, this.root, this.version, this.text, this.primitive);
    }

    private boolean isElement(String str) throws Exception {
        Expression build = this.builder.build(str);
        Model lookup = lookup(build);
        if (lookup != null) {
            String last = build.getLast();
            int index = build.getIndex();
            if (lookup.isElement(last)) {
                return true;
            }
            if (!lookup.isModel(last) || lookup.lookup(last, index).isEmpty()) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean isAttribute(String str) throws Exception {
        Expression build = this.builder.build(str);
        Model lookup = lookup(build);
        if (lookup == null) {
            return false;
        }
        String last = build.getLast();
        if (!build.isPath()) {
            return lookup.isAttribute(str);
        }
        return lookup.isAttribute(last);
    }

    private Model lookup(Expression expression) throws Exception {
        Expression path = expression.getPath(0, 1);
        if (expression.isPath()) {
            return this.root.lookup(path);
        }
        return this.root;
    }

    private Model register(Expression expression) throws Exception {
        Model lookup = this.root.lookup(expression);
        if (lookup != null) {
            return lookup;
        }
        return create(expression);
    }

    private Model create(Expression expression) throws Exception {
        Model model = this.root;
        while (model != null) {
            String prefix = expression.getPrefix();
            String first = expression.getFirst();
            int index = expression.getIndex();
            if (first != null) {
                model = model.register(first, prefix, index);
            }
            if (!expression.isPath()) {
                break;
            }
            expression = expression.getPath(1);
        }
        return model;
    }

    public void commit(Class cls) throws Exception {
        if (this.factory == null) {
            this.factory = this.resolver.build();
        }
    }

    public void validate(Class cls) throws Exception {
        Order order = this.scanner.getOrder();
        validateUnions(cls);
        validateElements(cls, order);
        validateAttributes(cls, order);
        validateModel(cls);
        validateText(cls);
        validateTextList(cls);
    }

    private void validateModel(Class cls) throws Exception {
        if (!this.root.isEmpty()) {
            this.root.validate(cls);
        }
    }

    private void validateText(Class cls) throws Exception {
        Label text2 = this.root.getText();
        if (text2 != null) {
            if (text2.isTextList()) {
                return;
            }
            if (!this.elements.isEmpty()) {
                throw new TextException("Elements used with %s in %s", text2, cls);
            } else if (this.root.isComposite()) {
                throw new TextException("Paths used with %s in %s", text2, cls);
            }
        } else if (this.scanner.isEmpty()) {
            this.primitive = isEmpty();
        }
    }

    private void validateTextList(Class cls) throws Exception {
        Label text2 = this.root.getText();
        if (text2 != null && text2.isTextList()) {
            Object key = text2.getKey();
            Iterator<Label> it = this.elements.iterator();
            while (it.hasNext()) {
                Label next = it.next();
                if (next.getKey().equals(key)) {
                    Class<String> type = next.getDependent().getType();
                    if (type == String.class) {
                        throw new TextException("Illegal entry of %s with text annotations on %s in %s", type, text2, cls);
                    }
                } else {
                    throw new TextException("Elements used with %s in %s", text2, cls);
                }
            }
            if (this.root.isComposite()) {
                throw new TextException("Paths used with %s in %s", text2, cls);
            }
        }
    }

    private void validateUnions(Class cls) throws Exception {
        Iterator<Label> it = this.elements.iterator();
        while (it.hasNext()) {
            Label next = it.next();
            String[] paths = next.getPaths();
            Contact contact = next.getContact();
            int length = paths.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    String str = paths[i];
                    Annotation annotation = contact.getAnnotation();
                    Label label = (Label) this.elements.get(str);
                    if (next.isInline() != label.isInline()) {
                        throw new UnionException("Inline must be consistent in %s for %s", annotation, contact);
                    } else if (next.isRequired() == label.isRequired()) {
                        i++;
                    } else {
                        throw new UnionException("Required must be consistent in %s for %s", annotation, contact);
                    }
                }
            }
        }
    }

    private void validateElements(Class cls, Order order) throws Exception {
        if (order != null) {
            String[] elements2 = order.elements();
            int length = elements2.length;
            int i = 0;
            while (i < length) {
                String str = elements2[i];
                if (isElement(str)) {
                    i++;
                } else {
                    throw new ElementException("Ordered element '%s' missing for %s", str, cls);
                }
            }
        }
    }

    private void validateAttributes(Class cls, Order order) throws Exception {
        if (order != null) {
            String[] attributes2 = order.attributes();
            int length = attributes2.length;
            int i = 0;
            while (i < length) {
                String str = attributes2[i];
                if (isAttribute(str)) {
                    i++;
                } else {
                    throw new AttributeException("Ordered attribute '%s' missing in %s", str, cls);
                }
            }
        }
    }

    private boolean isEmpty() {
        if (this.text != null) {
            return false;
        }
        return this.root.isEmpty();
    }
}
