package org.simpleframework.xml.core;

import java.util.ArrayList;
import java.util.Iterator;

class TreeModel implements Model {
    private LabelMap attributes;
    private Detail detail;
    private LabelMap elements;
    private Expression expression;
    private int index;
    private Label list;
    private ModelMap models;
    private String name;
    private OrderList order;
    private Policy policy;
    private String prefix;
    private Label text;

    private static class OrderList extends ArrayList<String> {
    }

    public TreeModel(Policy policy2, Detail detail2) {
        this(policy2, detail2, (String) null, (String) null, 1);
    }

    public TreeModel(Policy policy2, Detail detail2, String str, String str2, int i) {
        this.attributes = new LabelMap(policy2);
        this.elements = new LabelMap(policy2);
        this.models = new ModelMap(detail2);
        this.order = new OrderList();
        this.detail = detail2;
        this.policy = policy2;
        this.prefix = str2;
        this.index = i;
        this.name = str;
    }

    public Model lookup(Expression expression2) {
        Model lookup = lookup(expression2.getFirst(), expression2.getIndex());
        if (expression2.isPath()) {
            Expression path = expression2.getPath(1, 0);
            if (lookup != null) {
                return lookup.lookup(path);
            }
        }
        return lookup;
    }

    public void registerElement(String str) throws Exception {
        if (!this.order.contains(str)) {
            this.order.add(str);
        }
        this.elements.put(str, (Object) null);
    }

    public void registerAttribute(String str) throws Exception {
        this.attributes.put(str, (Object) null);
    }

    public void registerText(Label label) throws Exception {
        if (this.text == null) {
            this.text = label;
        } else {
            throw new TextException("Duplicate text annotation on %s", label);
        }
    }

    public void registerAttribute(Label label) throws Exception {
        String name2 = label.getName();
        if (this.attributes.get(name2) == null) {
            this.attributes.put(name2, label);
        } else {
            throw new AttributeException("Duplicate annotation of name '%s' on %s", name2, label);
        }
    }

    public void registerElement(Label label) throws Exception {
        String name2 = label.getName();
        if (this.elements.get(name2) == null) {
            if (!this.order.contains(name2)) {
                this.order.add(name2);
            }
            if (label.isTextList()) {
                this.list = label;
            }
            this.elements.put(name2, label);
            return;
        }
        throw new ElementException("Duplicate annotation of name '%s' on %s", name2, label);
    }

    public ModelMap getModels() throws Exception {
        return this.models.getModels();
    }

    public LabelMap getAttributes() throws Exception {
        return this.attributes.getLabels();
    }

    public LabelMap getElements() throws Exception {
        return this.elements.getLabels();
    }

    public boolean isModel(String str) {
        return this.models.containsKey(str);
    }

    public boolean isElement(String str) {
        return this.elements.containsKey(str);
    }

    public boolean isAttribute(String str) {
        return this.attributes.containsKey(str);
    }

    public Iterator<String> iterator() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.order.iterator();
        while (it.hasNext()) {
            arrayList.add((String) it.next());
        }
        return arrayList.iterator();
    }

    public void validate(Class cls) throws Exception {
        validateExpressions(cls);
        validateAttributes(cls);
        validateElements(cls);
        validateModels(cls);
        validateText(cls);
    }

    private void validateText(Class cls) throws Exception {
        if (this.text == null) {
            return;
        }
        if (!this.elements.isEmpty()) {
            throw new TextException("Text annotation %s used with elements in %s", this.text, cls);
        } else if (isComposite()) {
            throw new TextException("Text annotation %s can not be used with paths in %s", this.text, cls);
        }
    }

    private void validateExpressions(Class cls) throws Exception {
        Iterator<Label> it = this.elements.iterator();
        while (it.hasNext()) {
            Label next = it.next();
            if (next != null) {
                validateExpression(next);
            }
        }
        Iterator<Label> it2 = this.attributes.iterator();
        while (it2.hasNext()) {
            Label next2 = it2.next();
            if (next2 != null) {
                validateExpression(next2);
            }
        }
        Label label = this.text;
        if (label != null) {
            validateExpression(label);
        }
    }

    private void validateExpression(Label label) throws Exception {
        Expression expression2 = label.getExpression();
        Expression expression3 = this.expression;
        if (expression3 != null) {
            String path = expression3.getPath();
            String path2 = expression2.getPath();
            if (!path.equals(path2)) {
                throw new PathException("Path '%s' does not match '%s' in %s", path, path2, this.detail);
            }
            return;
        }
        this.expression = expression2;
    }

    private void validateModels(Class cls) throws Exception {
        Iterator<ModelList> it = this.models.iterator();
        while (it.hasNext()) {
            Iterator it2 = it.next().iterator();
            int i = 1;
            while (true) {
                if (it2.hasNext()) {
                    Model model = (Model) it2.next();
                    if (model != null) {
                        String name2 = model.getName();
                        int index2 = model.getIndex();
                        int i2 = i + 1;
                        if (index2 == i) {
                            model.validate(cls);
                            i = i2;
                        } else {
                            throw new ElementException("Path section '%s[%s]' is out of sequence in %s", name2, Integer.valueOf(index2), cls);
                        }
                    }
                }
            }
        }
    }

    private void validateAttributes(Class cls) throws Exception {
        for (String str : this.attributes.keySet()) {
            if (((Label) this.attributes.get(str)) != null) {
                Expression expression2 = this.expression;
                if (expression2 != null) {
                    expression2.getAttribute(str);
                }
            } else {
                throw new AttributeException("Ordered attribute '%s' does not exist in %s", str, cls);
            }
        }
    }

    private void validateElements(Class cls) throws Exception {
        for (String str : this.elements.keySet()) {
            ModelList modelList = (ModelList) this.models.get(str);
            Label label = (Label) this.elements.get(str);
            if (modelList == null && label == null) {
                throw new ElementException("Ordered element '%s' does not exist in %s", str, cls);
            } else if (modelList == null || label == null || modelList.isEmpty()) {
                Expression expression2 = this.expression;
                if (expression2 != null) {
                    expression2.getElement(str);
                }
            } else {
                throw new ElementException("Element '%s' is also a path name in %s", str, cls);
            }
        }
    }

    public void register(Label label) throws Exception {
        if (label.isAttribute()) {
            registerAttribute(label);
        } else if (label.isText()) {
            registerText(label);
        } else {
            registerElement(label);
        }
    }

    public Model lookup(String str, int i) {
        return this.models.lookup(str, i);
    }

    public Model register(String str, String str2, int i) throws Exception {
        Model lookup = this.models.lookup(str, i);
        return lookup == null ? create(str, str2, i) : lookup;
    }

    private Model create(String str, String str2, int i) throws Exception {
        TreeModel treeModel = new TreeModel(this.policy, this.detail, str, str2, i);
        if (str != null) {
            this.models.register(str, treeModel);
            this.order.add(str);
        }
        return treeModel;
    }

    public boolean isComposite() {
        Iterator<ModelList> it = this.models.iterator();
        while (it.hasNext()) {
            Iterator it2 = it.next().iterator();
            while (true) {
                if (it2.hasNext()) {
                    Model model = (Model) it2.next();
                    if (model != null && !model.isEmpty()) {
                        return true;
                    }
                }
            }
        }
        return !this.models.isEmpty();
    }

    public boolean isEmpty() {
        if (this.text == null && this.elements.isEmpty() && this.attributes.isEmpty()) {
            return !isComposite();
        }
        return false;
    }

    public Label getText() {
        Label label = this.list;
        if (label != null) {
            return label;
        }
        return this.text;
    }

    public Expression getExpression() {
        return this.expression;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return this.index;
    }

    public String toString() {
        return String.format("model '%s[%s]'", new Object[]{this.name, Integer.valueOf(this.index)});
    }
}
