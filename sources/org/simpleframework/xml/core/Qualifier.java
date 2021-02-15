package org.simpleframework.xml.core;

import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.stream.OutputNode;

class Qualifier implements Decorator {
    private NamespaceDecorator decorator = new NamespaceDecorator();

    public Qualifier(Contact contact) {
        scan(contact);
    }

    public void decorate(OutputNode outputNode) {
        this.decorator.decorate(outputNode);
    }

    public void decorate(OutputNode outputNode, Decorator decorator2) {
        this.decorator.decorate(outputNode, decorator2);
    }

    private void scan(Contact contact) {
        namespace(contact);
        scope(contact);
    }

    private void namespace(Contact contact) {
        Namespace namespace = (Namespace) contact.getAnnotation(Namespace.class);
        if (namespace != null) {
            this.decorator.set(namespace);
            this.decorator.add(namespace);
        }
    }

    private void scope(Contact contact) {
        NamespaceList namespaceList = (NamespaceList) contact.getAnnotation(NamespaceList.class);
        if (namespaceList != null) {
            for (Namespace add : namespaceList.value()) {
                this.decorator.add(add);
            }
        }
    }
}
