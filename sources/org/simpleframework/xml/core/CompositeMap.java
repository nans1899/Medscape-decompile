package org.simpleframework.xml.core;

import java.util.Map;
import org.simpleframework.xml.strategy.Type;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.Style;

class CompositeMap implements Converter {
    private final Entry entry;
    private final MapFactory factory;
    private final Converter key;
    private final Style style;
    private final Converter value;

    public CompositeMap(Context context, Entry entry2, Type type) throws Exception {
        this.factory = new MapFactory(context, type);
        this.value = entry2.getValue(context);
        this.key = entry2.getKey(context);
        this.style = context.getStyle();
        this.entry = entry2;
    }

    public Object read(InputNode inputNode) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        Object instance2 = instance.getInstance();
        return !instance.isReference() ? populate(inputNode, instance2) : instance2;
    }

    public Object read(InputNode inputNode, Object obj) throws Exception {
        Instance instance = this.factory.getInstance(inputNode);
        if (instance.isReference()) {
            return instance.getInstance();
        }
        instance.setInstance(obj);
        return obj != null ? populate(inputNode, obj) : obj;
    }

    private Object populate(InputNode inputNode, Object obj) throws Exception {
        Map map = (Map) obj;
        while (true) {
            InputNode next = inputNode.getNext();
            if (next == null) {
                return map;
            }
            map.put(this.key.read(next), this.value.read(next));
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

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean validate(org.simpleframework.xml.stream.InputNode r3, java.lang.Class r4) throws java.lang.Exception {
        /*
            r2 = this;
        L_0x0000:
            org.simpleframework.xml.stream.InputNode r4 = r3.getNext()
            if (r4 != 0) goto L_0x0008
            r3 = 1
            return r3
        L_0x0008:
            org.simpleframework.xml.core.Converter r0 = r2.key
            boolean r0 = r0.validate(r4)
            r1 = 0
            if (r0 != 0) goto L_0x0012
            return r1
        L_0x0012:
            org.simpleframework.xml.core.Converter r0 = r2.value
            boolean r4 = r0.validate(r4)
            if (r4 != 0) goto L_0x0000
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.simpleframework.xml.core.CompositeMap.validate(org.simpleframework.xml.stream.InputNode, java.lang.Class):boolean");
    }

    public void write(OutputNode outputNode, Object obj) throws Exception {
        Map map = (Map) obj;
        for (Object next : map.keySet()) {
            OutputNode child = outputNode.getChild(this.style.getElement(this.entry.getEntry()));
            Object obj2 = map.get(next);
            this.key.write(child, next);
            this.value.write(child, obj2);
        }
    }
}
