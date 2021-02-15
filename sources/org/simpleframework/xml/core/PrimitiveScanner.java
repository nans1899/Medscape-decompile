package org.simpleframework.xml.core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Version;

class PrimitiveScanner implements Scanner {
    private final Detail detail;
    private final Section section = new EmptySection(this);

    public Function getCommit() {
        return null;
    }

    public Function getComplete() {
        return null;
    }

    public Decorator getDecorator() {
        return null;
    }

    public Instantiator getInstantiator() {
        return null;
    }

    public String getName() {
        return null;
    }

    public Order getOrder() {
        return null;
    }

    public Function getPersist() {
        return null;
    }

    public Function getReplace() {
        return null;
    }

    public Function getResolve() {
        return null;
    }

    public Version getRevision() {
        return null;
    }

    public Signature getSignature() {
        return null;
    }

    public Label getText() {
        return null;
    }

    public Function getValidate() {
        return null;
    }

    public Label getVersion() {
        return null;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean isPrimitive() {
        return true;
    }

    public boolean isStrict() {
        return true;
    }

    public PrimitiveScanner(Detail detail2) {
        this.detail = detail2;
    }

    public List<Signature> getSignatures() {
        return new LinkedList();
    }

    public ParameterMap getParameters() {
        return new ParameterMap();
    }

    public Class getType() {
        return this.detail.getType();
    }

    public Caller getCaller(Context context) {
        return new Caller(this, context);
    }

    public Section getSection() {
        return this.section;
    }

    private static class EmptySection implements Section {
        private final List<String> list = new LinkedList();
        private final Scanner scanner;

        public String getAttribute(String str) {
            return null;
        }

        public Label getElement(String str) {
            return null;
        }

        public String getName() {
            return null;
        }

        public String getPath(String str) {
            return null;
        }

        public String getPrefix() {
            return null;
        }

        public Section getSection(String str) {
            return null;
        }

        public Label getText() {
            return null;
        }

        public boolean isSection(String str) {
            return false;
        }

        public EmptySection(Scanner scanner2) {
            this.scanner = scanner2;
        }

        public Iterator<String> iterator() {
            return this.list.iterator();
        }

        public LabelMap getElements() {
            return new LabelMap(this.scanner);
        }

        public LabelMap getAttributes() {
            return new LabelMap(this.scanner);
        }
    }
}
