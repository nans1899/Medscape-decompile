package org.simpleframework.xml.core;

import java.util.Iterator;
import org.simpleframework.xml.Version;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.NamespaceMap;
import org.simpleframework.xml.stream.NodeMap;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Position;

class Composite implements Converter {
    private final Context context;
    private final Criteria criteria;
    private final ObjectFactory factory;
    private final Primitive primitive;
    private final Revision revision;
    private final Type type;

    public Composite(Context context2, Type type2) {
        this(context2, type2, (Class) null);
    }

    public Composite(Context context2, Type type2, Class cls) {
        this.factory = new ObjectFactory(context2, type2, cls);
        this.primitive = new Primitive(context2, type2);
        this.criteria = new Collector();
        this.revision = new Revision();
        this.context = context2;
        this.type = type2;
    }

    public Object read(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        Class type2 = instance.getType();
        if (instance.isReference()) {
            return instance.getInstance();
        }
        if (this.context.isPrimitive(type2)) {
            return readPrimitive(inputNode, instance);
        }
        return read(inputNode, instance, type2);
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Schema schema = this.context.getSchema(obj.getClass());
        Caller caller = schema.getCaller();
        read(inputNode, obj, schema);
        this.criteria.commit(obj);
        caller.validate(obj);
        caller.commit(obj);
        return readResolve(inputNode, obj, caller);
    }

    private Object read(InputNode inputNode, Instance instance, Class cls) throws Exception {
        Schema schema = this.context.getSchema(cls);
        Caller caller = schema.getCaller();
        Object read = read(schema, instance).read(inputNode);
        caller.validate(read);
        caller.commit(read);
        instance.setInstance(read);
        return readResolve(inputNode, read, caller);
    }

    private Builder read(Schema schema, Instance instance) throws Exception {
        if (schema.getInstantiator().isDefault()) {
            return new Builder(this, this.criteria, schema, instance);
        }
        return new Injector(this, this.criteria, schema, instance);
    }

    private Object readPrimitive(InputNode inputNode, Instance instance) throws Exception {
        Class type2 = instance.getType();
        Object read = this.primitive.read(inputNode, type2);
        if (type2 != null) {
            instance.setInstance(read);
        }
        return read;
    }

    private Object readResolve(InputNode inputNode, Object obj, Caller caller) throws Exception {
        if (obj == null) {
            return obj;
        }
        Position position = inputNode.getPosition();
        Object resolve = caller.resolve(obj);
        Class type2 = this.type.getType();
        Class<?> cls = resolve.getClass();
        if (type2.isAssignableFrom(cls)) {
            return resolve;
        }
        throw new ElementException("Type %s does not match %s at %s", cls, type2, position);
    }

    private void read(InputNode inputNode, Object obj, Schema schema) throws Exception {
        Section section = schema.getSection();
        readVersion(inputNode, obj, schema);
        readSection(inputNode, obj, section);
    }

    private void readSection(InputNode inputNode, Object obj, Section section) throws Exception {
        readText(inputNode, obj, section);
        readAttributes(inputNode, obj, section);
        readElements(inputNode, obj, section);
    }

    /* access modifiers changed from: private */
    public void readVersion(InputNode inputNode, Object obj, Schema schema) throws Exception {
        Label version = schema.getVersion();
        Class type2 = this.type.getType();
        if (version != null) {
            InputNode remove = inputNode.getAttributes().remove(version.getName());
            if (remove != null) {
                readVersion(remove, obj, version);
                return;
            }
            Version version2 = this.context.getVersion(type2);
            Double valueOf = Double.valueOf(this.revision.getDefault());
            Double valueOf2 = Double.valueOf(version2.revision());
            this.criteria.set(version, valueOf);
            this.revision.compare(valueOf2, valueOf);
        }
    }

    private void readVersion(InputNode inputNode, Object obj, Label label) throws Exception {
        Object readInstance = readInstance(inputNode, obj, label);
        Class type2 = this.type.getType();
        if (readInstance != null) {
            Double valueOf = Double.valueOf(this.context.getVersion(type2).revision());
            if (!readInstance.equals(this.revision)) {
                this.revision.compare(valueOf, readInstance);
            }
        }
    }

    /* access modifiers changed from: private */
    public void readAttributes(InputNode inputNode, Object obj, Section section) throws Exception {
        NodeMap<InputNode> attributes = inputNode.getAttributes();
        LabelMap attributes2 = section.getAttributes();
        Iterator<String> it = attributes.iterator();
        while (it.hasNext()) {
            InputNode attribute = inputNode.getAttribute(it.next());
            if (attribute != null) {
                readAttribute(attribute, obj, section, attributes2);
            }
        }
        validate(inputNode, attributes2, obj);
    }

    /* access modifiers changed from: private */
    public void readElements(InputNode inputNode, Object obj, Section section) throws Exception {
        LabelMap elements = section.getElements();
        InputNode next = inputNode.getNext();
        while (next != null) {
            Section section2 = section.getSection(next.getName());
            if (section2 != null) {
                readSection(next, obj, section2);
            } else {
                readElement(next, obj, section, elements);
            }
            next = inputNode.getNext();
        }
        validate(inputNode, elements, obj);
    }

    /* access modifiers changed from: private */
    public void readText(InputNode inputNode, Object obj, Section section) throws Exception {
        Label text = section.getText();
        if (text != null) {
            readInstance(inputNode, obj, text);
        }
    }

    private void readAttribute(InputNode inputNode, Object obj, Section section, LabelMap labelMap) throws Exception {
        String attribute = section.getAttribute(inputNode.getName());
        Label label = labelMap.getLabel(attribute);
        if (label == null) {
            Position position = inputNode.getPosition();
            Class type2 = this.context.getType(this.type, obj);
            if (labelMap.isStrict(this.context) && this.revision.isEqual()) {
                throw new AttributeException("Attribute '%s' does not have a match in %s at %s", attribute, type2, position);
            }
            return;
        }
        readInstance(inputNode, obj, label);
    }

    private void readElement(InputNode inputNode, Object obj, Section section, LabelMap labelMap) throws Exception {
        String path = section.getPath(inputNode.getName());
        Label label = labelMap.getLabel(path);
        if (label == null) {
            label = this.criteria.resolve(path);
        }
        if (label == null) {
            Position position = inputNode.getPosition();
            Class type2 = this.context.getType(this.type, obj);
            if (!labelMap.isStrict(this.context) || !this.revision.isEqual()) {
                inputNode.skip();
            } else {
                throw new ElementException("Element '%s' does not have a match in %s at %s", path, type2, position);
            }
        } else {
            readUnion(inputNode, obj, labelMap, label);
        }
    }

    private void readUnion(InputNode inputNode, Object obj, LabelMap labelMap, Label label) throws Exception {
        Object readInstance = readInstance(inputNode, obj, label);
        for (String label2 : label.getPaths()) {
            labelMap.getLabel(label2);
        }
        if (label.isInline()) {
            this.criteria.set(label, readInstance);
        }
    }

    private Object readInstance(InputNode inputNode, Object obj, Label label) throws Exception {
        Object readVariable = readVariable(inputNode, obj, label);
        if (readVariable == null) {
            Position position = inputNode.getPosition();
            Class type2 = this.context.getType(this.type, obj);
            if (label.isRequired() && this.revision.isEqual()) {
                throw new ValueRequiredException("Empty value for %s in %s at %s", label, type2, position);
            }
        } else if (readVariable != label.getEmpty(this.context)) {
            this.criteria.set(label, readVariable);
        }
        return readVariable;
    }

    private Object readVariable(InputNode inputNode, Object obj, Label label) throws Exception {
        Object obj2;
        Converter converter = label.getConverter(this.context);
        if (label.isCollection()) {
            Variable variable = this.criteria.get(label);
            Contact contact = label.getContact();
            if (variable != null) {
                return converter.read(inputNode, variable.getValue());
            }
            if (!(obj == null || (obj2 = contact.get(obj)) == null)) {
                return converter.read(inputNode, obj2);
            }
        }
        return converter.read(inputNode);
    }

    private void validate(InputNode inputNode, LabelMap labelMap, Object obj) throws Exception {
        Class type2 = this.context.getType(this.type, obj);
        Position position = inputNode.getPosition();
        Iterator<Label> it = labelMap.iterator();
        while (it.hasNext()) {
            Label next = it.next();
            if (!next.isRequired() || !this.revision.isEqual()) {
                Object empty = next.getEmpty(this.context);
                if (empty != null) {
                    this.criteria.set(next, empty);
                }
            } else {
                throw new ValueRequiredException("Unable to satisfy %s for %s at %s", next, type2, position);
            }
        }
    }

    public boolean validate(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        if (instance.isReference()) {
            return true;
        }
        instance.setInstance((Object) null);
        return validate(inputNode, instance.getType());
    }

    private boolean validate(InputNode inputNode, Class cls) throws Exception {
        Schema schema = this.context.getSchema(cls);
        Section section = schema.getSection();
        validateText(inputNode, schema);
        validateSection(inputNode, section);
        return inputNode.isElement();
    }

    private void validateSection(InputNode inputNode, Section section) throws Exception {
        validateAttributes(inputNode, section);
        validateElements(inputNode, section);
    }

    private void validateAttributes(InputNode inputNode, Section section) throws Exception {
        NodeMap<InputNode> attributes = inputNode.getAttributes();
        LabelMap attributes2 = section.getAttributes();
        Iterator<String> it = attributes.iterator();
        while (it.hasNext()) {
            InputNode attribute = inputNode.getAttribute(it.next());
            if (attribute != null) {
                validateAttribute(attribute, section, attributes2);
            }
        }
        validate(inputNode, attributes2);
    }

    private void validateElements(InputNode inputNode, Section section) throws Exception {
        LabelMap elements = section.getElements();
        InputNode next = inputNode.getNext();
        while (next != null) {
            Section section2 = section.getSection(next.getName());
            if (section2 != null) {
                validateSection(next, section2);
            } else {
                validateElement(next, section, elements);
            }
            next = inputNode.getNext();
        }
        validate(inputNode, elements);
    }

    private void validateText(InputNode inputNode, Schema schema) throws Exception {
        Label text = schema.getText();
        if (text != null) {
            validate(inputNode, text);
        }
    }

    private void validateAttribute(InputNode inputNode, Section section, LabelMap labelMap) throws Exception {
        Position position = inputNode.getPosition();
        String attribute = section.getAttribute(inputNode.getName());
        Label label = labelMap.getLabel(attribute);
        if (label == null) {
            Class type2 = this.type.getType();
            if (labelMap.isStrict(this.context) && this.revision.isEqual()) {
                throw new AttributeException("Attribute '%s' does not exist for %s at %s", attribute, type2, position);
            }
            return;
        }
        validate(inputNode, label);
    }

    private void validateElement(InputNode inputNode, Section section, LabelMap labelMap) throws Exception {
        String path = section.getPath(inputNode.getName());
        Label label = labelMap.getLabel(path);
        if (label == null) {
            label = this.criteria.resolve(path);
        }
        if (label == null) {
            Position position = inputNode.getPosition();
            Class type2 = this.type.getType();
            if (!labelMap.isStrict(this.context) || !this.revision.isEqual()) {
                inputNode.skip();
            } else {
                throw new ElementException("Element '%s' does not exist for %s at %s", path, type2, position);
            }
        } else {
            validateUnion(inputNode, labelMap, label);
        }
    }

    private void validateUnion(InputNode inputNode, LabelMap labelMap, Label label) throws Exception {
        for (String label2 : label.getPaths()) {
            labelMap.getLabel(label2);
        }
        if (label.isInline()) {
            this.criteria.set(label, (Object) null);
        }
        validate(inputNode, label);
    }

    private void validate(InputNode inputNode, Label label) throws Exception {
        Converter converter = label.getConverter(this.context);
        Position position = inputNode.getPosition();
        Class type2 = this.type.getType();
        if (converter.validate(inputNode)) {
            this.criteria.set(label, (Object) null);
        } else {
            throw new PersistenceException("Invalid value for %s in %s at %s", label, type2, position);
        }
    }

    private void validate(InputNode inputNode, LabelMap labelMap) throws Exception {
        Position position = inputNode.getPosition();
        Iterator<Label> it = labelMap.iterator();
        while (it.hasNext()) {
            Label next = it.next();
            Class type2 = this.type.getType();
            if (next.isRequired() && this.revision.isEqual()) {
                throw new ValueRequiredException("Unable to satisfy %s for %s at %s", next, type2, position);
            }
        }
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Schema schema = this.context.getSchema(obj.getClass());
        Caller caller = schema.getCaller();
        try {
            if (schema.isPrimitive()) {
                this.primitive.write(outputNode, obj);
            } else {
                caller.persist(obj);
                write(outputNode, obj, schema);
            }
        } finally {
            caller.complete(obj);
        }
    }

    private void write(OutputNode outputNode, Object obj, Schema schema) throws Exception {
        Section section = schema.getSection();
        writeVersion(outputNode, obj, schema);
        writeSection(outputNode, obj, section);
    }

    private void writeSection(OutputNode outputNode, Object obj, Section section) throws Exception {
        NamespaceMap namespaces = outputNode.getNamespaces();
        String prefix = section.getPrefix();
        if (prefix != null) {
            String reference = namespaces.getReference(prefix);
            if (reference != null) {
                outputNode.setReference(reference);
            } else {
                throw new ElementException("Namespace prefix '%s' in %s is not in scope", prefix, this.type);
            }
        }
        writeAttributes(outputNode, obj, section);
        writeElements(outputNode, obj, section);
        writeText(outputNode, obj, section);
    }

    private void writeVersion(OutputNode outputNode, Object obj, Schema schema) throws Exception {
        Version revision2 = schema.getRevision();
        Label version = schema.getVersion();
        if (revision2 != null) {
            Double valueOf = Double.valueOf(this.revision.getDefault());
            Double valueOf2 = Double.valueOf(revision2.revision());
            if (!this.revision.compare(valueOf2, valueOf)) {
                writeAttribute(outputNode, valueOf2, version);
            } else if (version.isRequired()) {
                writeAttribute(outputNode, valueOf2, version);
            }
        }
    }

    private void writeAttributes(OutputNode outputNode, Object obj, Section section) throws Exception {
        Iterator<Label> it = section.getAttributes().iterator();
        while (it.hasNext()) {
            Label next = it.next();
            Object obj2 = next.getContact().get(obj);
            Class type2 = this.context.getType(this.type, obj);
            if (obj2 == null) {
                obj2 = next.getEmpty(this.context);
            }
            if (obj2 != null || !next.isRequired()) {
                writeAttribute(outputNode, obj2, next);
            } else {
                throw new AttributeException("Value for %s is null in %s", next, type2);
            }
        }
    }

    private void writeElements(OutputNode outputNode, Object obj, Section section) throws Exception {
        Iterator it = section.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Section section2 = section.getSection(str);
            if (section2 != null) {
                writeSection(outputNode.getChild(str), obj, section2);
            } else {
                Label element = section.getElement(section.getPath(str));
                Class type2 = this.context.getType(this.type, obj);
                if (this.criteria.get(element) != null) {
                    continue;
                } else if (element != null) {
                    writeUnion(outputNode, obj, section, element);
                } else {
                    throw new ElementException("Element '%s' not defined in %s", str, type2);
                }
            }
        }
    }

    private void writeUnion(OutputNode outputNode, Object obj, Section section, Label label) throws Exception {
        Object obj2 = label.getContact().get(obj);
        Class type2 = this.context.getType(this.type, obj);
        if (obj2 != null || !label.isRequired()) {
            Object writeReplace = writeReplace(obj2);
            if (writeReplace != null) {
                writeElement(outputNode, writeReplace, label);
            }
            this.criteria.set(label, writeReplace);
            return;
        }
        throw new ElementException("Value for %s is null in %s", label, type2);
    }

    private Object writeReplace(Object obj) throws Exception {
        if (obj == null) {
            return obj;
        }
        return this.context.getCaller(obj.getClass()).replace(obj);
    }

    private void writeText(OutputNode outputNode, Object obj, Section section) throws Exception {
        Label text = section.getText();
        if (text != null) {
            Object obj2 = text.getContact().get(obj);
            Class type2 = this.context.getType(this.type, obj);
            if (obj2 == null) {
                obj2 = text.getEmpty(this.context);
            }
            if (obj2 != null || !text.isRequired()) {
                writeText(outputNode, obj2, text);
            } else {
                throw new TextException("Value for %s is null in %s", text, type2);
            }
        }
    }

    private void writeAttribute(OutputNode outputNode, Object obj, Label label) throws Exception {
        if (obj != null) {
            label.getDecorator().decorate(outputNode.setAttribute(label.getName(), this.factory.getText(obj)));
        }
    }

    private void writeElement(OutputNode outputNode, Object obj, Label label) throws Exception {
        if (obj != null) {
            Class<?> cls = obj.getClass();
            Label label2 = label.getLabel(cls);
            String name = label2.getName();
            Type type2 = label.getType(cls);
            OutputNode child = outputNode.getChild(name);
            if (!label2.isInline()) {
                writeNamespaces(child, type2, label2);
            }
            if (label2.isInline() || !isOverridden(child, obj, type2)) {
                Converter converter = label2.getConverter(this.context);
                child.setData(label2.isData());
                writeElement(child, obj, converter);
            }
        }
    }

    private void writeElement(OutputNode outputNode, Object obj, Converter converter) throws Exception {
        converter.write(outputNode, obj);
    }

    private void writeNamespaces(OutputNode outputNode, Type type2, Label label) throws Exception {
        label.getDecorator().decorate(outputNode, this.context.getDecorator(type2.getType()));
    }

    private void writeText(OutputNode outputNode, Object obj, Label label) throws Exception {
        if (obj != null && !label.isTextList()) {
            String text = this.factory.getText(obj);
            outputNode.setData(label.isData());
            outputNode.setValue(text);
        }
    }

    private boolean isOverridden(OutputNode outputNode, Object obj, Type type2) throws Exception {
        return this.factory.setOverride(type2, obj, outputNode);
    }

    private static class Builder {
        protected final Composite composite;
        protected final Criteria criteria;
        protected final Schema schema;
        protected final Instance value;

        public Builder(Composite composite2, Criteria criteria2, Schema schema2, Instance instance) {
            this.composite = composite2;
            this.criteria = criteria2;
            this.schema = schema2;
            this.value = instance;
        }

        public Object read(InputNode inputNode) throws Exception {
            Object instance = this.value.getInstance();
            Section section = this.schema.getSection();
            this.value.setInstance(instance);
            this.composite.readVersion(inputNode, instance, this.schema);
            this.composite.readText(inputNode, instance, section);
            this.composite.readAttributes(inputNode, instance, section);
            this.composite.readElements(inputNode, instance, section);
            this.criteria.commit(instance);
            return instance;
        }
    }

    private class Injector extends Builder {
        private Injector(Composite composite, Criteria criteria, Schema schema, Instance instance) {
            super(composite, criteria, schema, instance);
        }

        public Object read(InputNode inputNode) throws Exception {
            Section section = this.schema.getSection();
            this.composite.readVersion(inputNode, (Object) null, this.schema);
            this.composite.readText(inputNode, (Object) null, section);
            this.composite.readAttributes(inputNode, (Object) null, section);
            this.composite.readElements(inputNode, (Object) null, section);
            return readInject(inputNode);
        }

        private Object readInject(InputNode inputNode) throws Exception {
            Object instance = this.schema.getInstantiator().getInstance(this.criteria);
            this.value.setInstance(instance);
            this.criteria.commit(instance);
            return instance;
        }
    }
}
