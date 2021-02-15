package org.simpleframework.xml.core;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

class SignatureBuilder {
    private final Constructor factory;
    private final ParameterTable table = new ParameterTable();

    public SignatureBuilder(Constructor constructor) {
        this.factory = constructor;
    }

    public boolean isValid() {
        return this.factory.getParameterTypes().length == this.table.width();
    }

    public void insert(Parameter parameter, int i) {
        this.table.insert(parameter, i);
    }

    public List<Signature> build() throws Exception {
        return build(new ParameterTable());
    }

    private List<Signature> build(ParameterTable parameterTable) throws Exception {
        if (this.table.isEmpty()) {
            return create();
        }
        build(parameterTable, 0);
        return create(parameterTable);
    }

    private List<Signature> create() throws Exception {
        ArrayList arrayList = new ArrayList();
        Signature signature = new Signature(this.factory);
        if (isValid()) {
            arrayList.add(signature);
        }
        return arrayList;
    }

    private List<Signature> create(ParameterTable parameterTable) throws Exception {
        ArrayList arrayList = new ArrayList();
        int access$100 = parameterTable.height();
        int access$000 = parameterTable.width();
        for (int i = 0; i < access$100; i++) {
            Signature signature = new Signature(this.factory);
            int i2 = 0;
            while (i2 < access$000) {
                Parameter parameter = parameterTable.get(i2, i);
                String path = parameter.getPath();
                if (!signature.contains(parameter.getKey())) {
                    signature.add(parameter);
                    i2++;
                } else {
                    throw new ConstructorException("Parameter '%s' is a duplicate in %s", path, this.factory);
                }
            }
            arrayList.add(signature);
        }
        return arrayList;
    }

    private void build(ParameterTable parameterTable, int i) {
        build(parameterTable, new ParameterList(), i);
    }

    private void build(ParameterTable parameterTable, ParameterList parameterList, int i) {
        ParameterList parameterList2 = this.table.get(i);
        int size = parameterList2.size();
        if (this.table.width() - 1 > i) {
            for (int i2 = 0; i2 < size; i2++) {
                ParameterList parameterList3 = new ParameterList(parameterList);
                if (parameterList != null) {
                    parameterList3.add((Parameter) parameterList2.get(i2));
                    build(parameterTable, parameterList3, i + 1);
                }
            }
            return;
        }
        populate(parameterTable, parameterList, i);
    }

    private void populate(ParameterTable parameterTable, ParameterList parameterList, int i) {
        ParameterList parameterList2 = this.table.get(i);
        int size = parameterList.size();
        int size2 = parameterList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            for (int i3 = 0; i3 < size; i3++) {
                parameterTable.get(i3).add((Parameter) parameterList.get(i3));
            }
            parameterTable.get(i).add((Parameter) parameterList2.get(i2));
        }
    }

    private static class ParameterTable extends ArrayList<ParameterList> {
        /* access modifiers changed from: private */
        public int height() {
            if (width() > 0) {
                return get(0).size();
            }
            return 0;
        }

        /* access modifiers changed from: private */
        public int width() {
            return size();
        }

        public void insert(Parameter parameter, int i) {
            ParameterList parameterList = get(i);
            if (parameterList != null) {
                parameterList.add(parameter);
            }
        }

        public ParameterList get(int i) {
            for (int size = size(); size <= i; size++) {
                add(new ParameterList());
            }
            return (ParameterList) super.get(i);
        }

        public Parameter get(int i, int i2) {
            return (Parameter) get(i).get(i2);
        }
    }

    private static class ParameterList extends ArrayList<Parameter> {
        public ParameterList() {
        }

        public ParameterList(ParameterList parameterList) {
            super(parameterList);
        }
    }
}
