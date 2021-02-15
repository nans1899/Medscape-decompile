package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import org.simpleframework.xml.DefaultType;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

class ClassScanner {
    private Function commit;
    private Function complete;
    private NamespaceDecorator decorator = new NamespaceDecorator();
    private Order order;
    private Function persist;
    private Function replace;
    private Function resolve;
    private Root root;
    private ConstructorScanner scanner;
    private Support support;
    private Function validate;

    public ClassScanner(Detail detail, Support support2) throws Exception {
        this.scanner = new ConstructorScanner(detail, support2);
        this.support = support2;
        scan(detail);
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

    public Decorator getDecorator() {
        return this.decorator;
    }

    public Order getOrder() {
        return this.order;
    }

    public Root getRoot() {
        return this.root;
    }

    public Function getCommit() {
        return this.commit;
    }

    public Function getValidate() {
        return this.validate;
    }

    public Function getPersist() {
        return this.persist;
    }

    public Function getComplete() {
        return this.complete;
    }

    public Function getReplace() {
        return this.replace;
    }

    public Function getResolve() {
        return this.resolve;
    }

    private void scan(Detail detail) throws Exception {
        DefaultType override = detail.getOverride();
        Class type = detail.getType();
        while (type != null) {
            Detail detail2 = this.support.getDetail(type, override);
            namespace(detail2);
            method(detail2);
            definition(detail2);
            type = detail2.getSuper();
        }
        commit(detail);
    }

    private void definition(Detail detail) throws Exception {
        if (this.root == null) {
            this.root = detail.getRoot();
        }
        if (this.order == null) {
            this.order = detail.getOrder();
        }
    }

    private void namespace(Detail detail) throws Exception {
        NamespaceList namespaceList = detail.getNamespaceList();
        Namespace namespace = detail.getNamespace();
        if (namespace != null) {
            this.decorator.add(namespace);
        }
        if (namespaceList != null) {
            for (Namespace add : namespaceList.value()) {
                this.decorator.add(add);
            }
        }
    }

    private void commit(Detail detail) {
        Namespace namespace = detail.getNamespace();
        if (namespace != null) {
            this.decorator.set(namespace);
        }
    }

    private void method(Detail detail) throws Exception {
        for (MethodDetail method : detail.getMethods()) {
            method(method);
        }
    }

    private void method(MethodDetail methodDetail) {
        Annotation[] annotations = methodDetail.getAnnotations();
        Method method = methodDetail.getMethod();
        for (Annotation annotation : annotations) {
            if (annotation instanceof Commit) {
                commit(method);
            }
            if (annotation instanceof Validate) {
                validate(method);
            }
            if (annotation instanceof Persist) {
                persist(method);
            }
            if (annotation instanceof Complete) {
                complete(method);
            }
            if (annotation instanceof Replace) {
                replace(method);
            }
            if (annotation instanceof Resolve) {
                resolve(method);
            }
        }
    }

    private void replace(Method method) {
        if (this.replace == null) {
            this.replace = getFunction(method);
        }
    }

    private void resolve(Method method) {
        if (this.resolve == null) {
            this.resolve = getFunction(method);
        }
    }

    private void commit(Method method) {
        if (this.commit == null) {
            this.commit = getFunction(method);
        }
    }

    private void validate(Method method) {
        if (this.validate == null) {
            this.validate = getFunction(method);
        }
    }

    private void persist(Method method) {
        if (this.persist == null) {
            this.persist = getFunction(method);
        }
    }

    private void complete(Method method) {
        if (this.complete == null) {
            this.complete = getFunction(method);
        }
    }

    private Function getFunction(Method method) {
        boolean isContextual = isContextual(method);
        if (!method.isAccessible()) {
            method.setAccessible(true);
        }
        return new Function(method, isContextual);
    }

    private boolean isContextual(Method method) {
        Class[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 1) {
            return Map.class.equals(parameterTypes[0]);
        }
        return false;
    }
}
