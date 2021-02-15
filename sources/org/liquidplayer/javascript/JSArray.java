package org.liquidplayer.javascript;

import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JSArray<T> extends JSBaseArray<T> {

    public interface EachBooleanCallback<T> {
        boolean callback(T t, int i, JSArray<T> jSArray);
    }

    public interface ForEachCallback<T> {
        void callback(T t, int i, JSArray<T> jSArray);
    }

    public interface MapCallback<T> {
        JSValue callback(T t, int i, JSArray<T> jSArray);
    }

    public interface ReduceCallback {
        JSValue callback(JSValue jSValue, JSValue jSValue2, int i, JSArray<JSValue> jSArray);
    }

    public interface SortCallback<T> {
        double callback(T t, T t2);
    }

    public JSArray(JSContext jSContext, JSValue[] jSValueArr, Class<T> cls) {
        super(jSContext, cls);
        JNIJSValue[] jNIJSValueArr = new JNIJSValue[jSValueArr.length];
        for (int i = 0; i < jSValueArr.length; i++) {
            jNIJSValueArr[i] = jSValueArr[i].valueRef();
        }
        try {
            this.valueRef = this.context.ctxRef().makeArray(jNIJSValueArr);
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            this.valueRef = this.context.ctxRef().make();
        }
        addJSExports();
        this.context.persistObject(this);
    }

    public JSArray(JSContext jSContext, Class<T> cls) {
        super(jSContext, cls);
        try {
            this.valueRef = this.context.ctxRef().makeArray(new JNIJSValue[0]);
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            this.valueRef = this.context.ctxRef().make();
        }
        addJSExports();
        this.context.persistObject(this);
    }

    public JSArray(JSContext jSContext, Object[] objArr, Class<T> cls) {
        super(jSContext, cls);
        JNIJSValue[] jNIJSValueArr = new JNIJSValue[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            jNIJSValueArr[i] = new JSValue(this.context, objArr[i]).valueRef();
        }
        try {
            this.valueRef = this.context.ctxRef().makeArray(jNIJSValueArr);
        } catch (JNIJSException e) {
            this.context.throwJSException(new JSException(new JSValue(e.exception, this.context)));
            this.valueRef = this.context.ctxRef().make();
        }
        addJSExports();
        this.context.persistObject(this);
    }

    protected JSArray(JNIJSObject jNIJSObject, JSContext jSContext) {
        super(jNIJSObject, jSContext, JSValue.class);
    }

    protected JSArray(JNIJSObject jNIJSObject, JSContext jSContext, Class<T> cls) {
        super(jNIJSObject, jSContext, cls);
    }

    private JSArray(JSArray<T> jSArray, int i, int i2, Class<T> cls) {
        super(jSArray, i, i2, cls);
    }

    public JSArray(JSContext jSContext, Collection collection, Class<T> cls) {
        this(jSContext, collection.toArray(), cls);
    }

    public void add(int i, T t) {
        if (this == t) {
            throw new IllegalArgumentException();
        } else if (i <= size()) {
            splice(i, 0, t);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public T remove(int i) {
        if (i >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (this.mSuperList == null) {
            return splice(i, 1, new Object[0]).get(0);
        } else {
            return this.mSuperList.remove(i + this.mLeftBuffer);
        }
    }

    public List<T> subList(int i, int i2) {
        if (i >= 0 && i2 <= size() && i <= i2) {
            return new JSArray(this, i, size() - i2, this.mType);
        }
        throw new IndexOutOfBoundsException();
    }

    public static JSArray<JSValue> from(JSContext jSContext, Object obj, JSFunction jSFunction, JSObject jSObject) {
        return (JSArray) jSContext.property("Array").toObject().property(Constants.MessagePayloadKeys.FROM).toFunction().call((JSObject) null, obj, jSFunction, jSObject).toJSArray();
    }

    public static JSArray<JSValue> from(JSContext jSContext, Object obj, JSFunction jSFunction) {
        return (JSArray) jSContext.property("Array").toObject().property(Constants.MessagePayloadKeys.FROM).toFunction().call((JSObject) null, obj, jSFunction).toJSArray();
    }

    public static JSArray<JSValue> from(JSContext jSContext, Object obj) {
        return (JSArray) jSContext.property("Array").toObject().property(Constants.MessagePayloadKeys.FROM).toFunction().call((JSObject) null, obj).toJSArray();
    }

    public static JSArray<JSValue> from(JSContext jSContext, Object obj, final MapCallback<JSValue> mapCallback) {
        jSContext.property("Array").toObject();
        return (JSArray) jSContext.property("Array").toObject().property(Constants.MessagePayloadKeys.FROM).toFunction().call((JSObject) null, obj, new JSFunction(jSContext, "_callback") {
            public JSValue _callback(JSValue jSValue, int i, JSArray jSArray) {
                return mapCallback.callback(jSValue, i, jSArray);
            }
        }).toJSArray();
    }

    public static boolean isArray(JSValue jSValue) {
        if (jSValue == null) {
            return false;
        }
        return jSValue.getContext().property("Array").toObject().property("isArray").toFunction().call((JSObject) null, jSValue).toBoolean().booleanValue();
    }

    public static JSArray<JSValue> of(JSContext jSContext, Object... objArr) {
        return (JSArray) jSContext.property("Array").toObject().property("of").toFunction().apply((JSObject) null, objArr).toJSArray();
    }

    public JSArray<T> concat(Object... objArr) {
        JSArray<T> jSArray = (JSArray) property("concat").toFunction().apply(this, objArr).toJSArray();
        jSArray.mType = this.mType;
        return jSArray;
    }

    public T pop() {
        return property("pop").toFunction().call(this, new Object[0]).toJavaObject(this.mType);
    }

    public int push(T... tArr) {
        return property("push").toFunction().apply(this, tArr).toNumber().intValue();
    }

    public T shift() {
        JSValue call = property("shift").toFunction().call(this, new Object[0]);
        if (call.isUndefined().booleanValue()) {
            return null;
        }
        return call.toJavaObject(this.mType);
    }

    public JSArray<T> splice(int i, int i2, T... tArr) {
        ArrayList arrayList = new ArrayList(Arrays.asList((Object[]) tArr));
        arrayList.add(0, Integer.valueOf(i2));
        arrayList.add(0, Integer.valueOf(i));
        JSArray<T> jSArray = (JSArray) property("splice").toFunction().apply(this, arrayList.toArray()).toJSArray();
        jSArray.mType = this.mType;
        return jSArray;
    }

    public String toLocaleString() {
        return property("toLocaleString").toFunction().call(this, new Object[0]).toString();
    }

    public int unshift(T... tArr) {
        return property("unshift").toFunction().apply(this, tArr).toNumber().intValue();
    }

    private JSValue each(JSFunction jSFunction, JSObject jSObject, String str) {
        return property(str).toFunction().call(this, jSFunction, jSObject);
    }

    private JSValue each(final EachBooleanCallback<T> eachBooleanCallback, String str) {
        return property(str).toFunction().call(this, new JSFunction(this.context, "_callback") {
            public boolean _callback(T t, int i, JSArray jSArray) {
                return eachBooleanCallback.callback(((JSValue) t).toJavaObject(JSArray.this.mType), i, jSArray);
            }
        });
    }

    private JSValue each(final ReduceCallback reduceCallback, String str, Object obj) {
        return property(str).toFunction().call(this, new JSFunction(this.context, "_callback") {
            public JSValue _callback(JSValue jSValue, JSValue jSValue2, int i, JSArray<JSValue> jSArray) {
                return reduceCallback.callback(jSValue, jSValue2, i, jSArray);
            }
        }, obj);
    }

    public class EntriesIterator<U> extends JSIterator<Map.Entry<Integer, U>> {
        public /* bridge */ /* synthetic */ JSObject getJSObject() {
            return super.getJSObject();
        }

        public /* bridge */ /* synthetic */ boolean hasNext() {
            return super.hasNext();
        }

        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        EntriesIterator(JSObject jSObject) {
            super(jSObject);
        }

        public Map.Entry<Integer, U> next() {
            JSObject object = jsnext().value().toObject();
            return new AbstractMap.SimpleEntry(Integer.valueOf(object.propertyAtIndex(0).toNumber().intValue()), object.propertyAtIndex(1).toJavaObject(JSArray.this.mType));
        }
    }

    public JSArray<T>.EntriesIterator<T> entries() {
        return new EntriesIterator<>(property(RemoteConfigConstants.ResponseFieldKey.ENTRIES).toFunction().call(this, new Object[0]).toObject());
    }

    public boolean every(JSFunction jSFunction, JSObject jSObject) {
        return each(jSFunction, jSObject, "every").toBoolean().booleanValue();
    }

    public boolean every(JSFunction jSFunction) {
        return every(jSFunction, (JSObject) null);
    }

    public boolean every(EachBooleanCallback<T> eachBooleanCallback) {
        return each(eachBooleanCallback, "every").toBoolean().booleanValue();
    }

    public T find(JSFunction jSFunction, JSObject jSObject) {
        return each(jSFunction, jSObject, "find").toJavaObject(this.mType);
    }

    public T find(JSFunction jSFunction) {
        return find(jSFunction, (JSObject) null);
    }

    public T find(EachBooleanCallback<T> eachBooleanCallback) {
        return each(eachBooleanCallback, "find").toJavaObject(this.mType);
    }

    public int findIndex(JSFunction jSFunction, JSObject jSObject) {
        return each(jSFunction, jSObject, "findIndex").toNumber().intValue();
    }

    public int findIndex(JSFunction jSFunction) {
        return findIndex(jSFunction, (JSObject) null);
    }

    public int findIndex(EachBooleanCallback<T> eachBooleanCallback) {
        return each(eachBooleanCallback, "findIndex").toNumber().intValue();
    }

    public void forEach(JSFunction jSFunction, JSObject jSObject) {
        each(jSFunction, jSObject, "forEach");
    }

    public void forEach(JSFunction jSFunction) {
        forEach(jSFunction, (JSObject) null);
    }

    public void forEach(final ForEachCallback<T> forEachCallback) {
        property("forEach").toFunction().call(this, new JSFunction(this.context, "_callback") {
            public void _callback(T t, int i, JSArray jSArray) {
                forEachCallback.callback(((JSValue) t).toJavaObject(JSArray.this.mType), i, jSArray);
            }
        });
    }

    public boolean includes(T t, int i) {
        return property("includes").toFunction().call(this, t, Integer.valueOf(i)).toBoolean().booleanValue();
    }

    public boolean includes(T t) {
        return includes(t, 0);
    }

    public int indexOf(T t, int i) {
        return property("indexOf").toFunction().call(this, t, Integer.valueOf(i)).toNumber().intValue();
    }

    public String join(String str) {
        return property("join").toFunction().call(this, str).toString();
    }

    public String join() {
        return property("join").toFunction().call(this, new Object[0]).toString();
    }

    public class KeysIterator extends JSIterator<Integer> {
        public /* bridge */ /* synthetic */ JSObject getJSObject() {
            return super.getJSObject();
        }

        public /* bridge */ /* synthetic */ boolean hasNext() {
            return super.hasNext();
        }

        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        KeysIterator(JSObject jSObject) {
            super(jSObject);
        }

        public Integer next() {
            JSIterator<T>.Next jsnext = jsnext();
            if (jsnext.value().isUndefined().booleanValue()) {
                return null;
            }
            return (Integer) jsnext.value().toJavaObject(Integer.class);
        }
    }

    public JSArray<T>.KeysIterator keys() {
        return new KeysIterator(property("keys").toFunction().call(this, new Object[0]).toObject());
    }

    public int lastIndexOf(T t, int i) {
        return property("lastIndexOf").toFunction().call(this, t, Integer.valueOf(i)).toNumber().intValue();
    }

    public JSValue reduce(JSFunction jSFunction, Object obj) {
        return property("reduce").toFunction().call(this, jSFunction, obj);
    }

    public JSValue reduce(JSFunction jSFunction) {
        return property("reduce").toFunction().call(this, jSFunction);
    }

    public JSValue reduce(ReduceCallback reduceCallback, Object obj) {
        return each(reduceCallback, "reduce", obj);
    }

    public JSValue reduce(ReduceCallback reduceCallback) {
        return reduce(reduceCallback, (Object) null);
    }

    public JSValue reduceRight(JSFunction jSFunction, Object obj) {
        return property("reduceRight").toFunction().call(this, jSFunction, obj);
    }

    public JSValue reduceRight(JSFunction jSFunction) {
        return property("reduceRight").toFunction().call(this, jSFunction);
    }

    public JSValue reduceRight(ReduceCallback reduceCallback, Object obj) {
        return each(reduceCallback, "reduceRight", obj);
    }

    public JSValue reduceRight(ReduceCallback reduceCallback) {
        return reduceRight(reduceCallback, (Object) null);
    }

    public boolean some(JSFunction jSFunction, JSObject jSObject) {
        return each(jSFunction, jSObject, "some").toBoolean().booleanValue();
    }

    public boolean some(JSFunction jSFunction) {
        return some(jSFunction, (JSObject) null);
    }

    public boolean some(EachBooleanCallback<T> eachBooleanCallback) {
        return each(eachBooleanCallback, "some").toBoolean().booleanValue();
    }

    public JSArray<T> copyWithin(int i, int i2, int i3) {
        return (JSArray) property("copyWithin").toFunction().call(this, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)).toJSArray();
    }

    public JSArray<T> copyWithin(int i, int i2) {
        return copyWithin(i, i2, size());
    }

    public JSArray<T> copyWithin(int i) {
        return copyWithin(i, 0);
    }

    public JSArray<T> fill(T t, int i, int i2) {
        return (JSArray) property("fill").toFunction().call(this, t, Integer.valueOf(i), Integer.valueOf(i2)).toJSArray();
    }

    public JSArray<T> fill(T t, int i) {
        return fill(t, i, size());
    }

    public JSArray<T> fill(T t) {
        return fill(t, 0);
    }

    public JSArray<T> filter(JSFunction jSFunction, JSObject jSObject) {
        JSArray<T> jSArray = (JSArray) each(jSFunction, jSObject, OmnitureConstants.OMNITURE_MODULE_FILTER).toJSArray();
        jSArray.mType = this.mType;
        return jSArray;
    }

    public JSArray<T> filter(JSFunction jSFunction) {
        return filter(jSFunction, (JSObject) null);
    }

    public JSArray<T> filter(EachBooleanCallback<T> eachBooleanCallback) {
        JSArray<T> jSArray = (JSArray) each(eachBooleanCallback, OmnitureConstants.OMNITURE_MODULE_FILTER).toJSArray();
        jSArray.mType = this.mType;
        return jSArray;
    }

    public JSArray<JSValue> map(JSFunction jSFunction, JSObject jSObject) {
        return (JSArray) each(jSFunction, jSObject, "map").toJSArray();
    }

    public JSArray<JSValue> map(JSFunction jSFunction) {
        return map(jSFunction, (JSObject) null);
    }

    public JSArray<JSValue> map(final MapCallback<T> mapCallback) {
        return (JSArray) property("map").toFunction().call(this, new JSFunction(this.context, "_callback") {
            public JSValue _callback(T t, int i, JSArray<T> jSArray) {
                return mapCallback.callback(((JSValue) t).toJavaObject(JSArray.this.mType), i, jSArray);
            }
        }).toJSArray();
    }

    public JSArray<T> reverse() {
        return (JSArray) property("reverse").toFunction().call(this, new Object[0]).toJSArray();
    }

    public JSArray<T> slice(int i, int i2) {
        return (JSArray) property("slice").toFunction().call(this, Integer.valueOf(i), Integer.valueOf(i2)).toJSArray();
    }

    public JSArray<T> slice(int i) {
        return (JSArray) property("slice").toFunction().call(this, Integer.valueOf(i)).toJSArray();
    }

    public JSArray<T> slice() {
        return (JSArray) property("slice").toFunction().call(this, new Object[0]).toJSArray();
    }

    public JSArray<T> sort(JSFunction jSFunction) {
        return (JSArray) property("sort").toFunction().call(this, jSFunction).toJSArray();
    }

    public JSArray<T> sort(final SortCallback<T> sortCallback) {
        return (JSArray) property("sort").toFunction().call(this, new JSFunction(this.context, "_callback") {
            public double _callback(T t, T t2) {
                return sortCallback.callback(((JSValue) t).toJavaObject(JSArray.this.mType), ((JSValue) t2).toJavaObject(JSArray.this.mType));
            }
        }).toJSArray();
    }

    public JSArray<T> sort() {
        return (JSArray) property("sort").toFunction().call(this, new Object[0]).toJSArray();
    }
}
