package org.mozilla.javascript;

import com.dd.plist.ASCIIPropertyListParser;
import com.facebook.internal.ServerProtocol;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import net.bytebuddy.pool.TypePool;
import okhttp3.HttpUrl;
import org.mozilla.javascript.json.JsonParser;

public final class NativeJSON extends IdScriptableObject {
    private static final int Id_parse = 2;
    private static final int Id_stringify = 3;
    private static final int Id_toSource = 1;
    private static final Object JSON_TAG = JsonFactory.FORMAT_NAME_JSON;
    private static final int LAST_METHOD_ID = 3;
    private static final int MAX_ID = 3;
    private static final int MAX_STRINGIFY_GAP_LENGTH = 10;
    static final long serialVersionUID = -4567599697595654984L;

    public String getClassName() {
        return JsonFactory.FORMAT_NAME_JSON;
    }

    static void init(Scriptable scriptable, boolean z) {
        NativeJSON nativeJSON = new NativeJSON();
        nativeJSON.activatePrototypeMap(3);
        nativeJSON.setPrototype(getObjectPrototype(scriptable));
        nativeJSON.setParentScope(scriptable);
        if (z) {
            nativeJSON.sealObject();
        }
        ScriptableObject.defineProperty(scriptable, JsonFactory.FORMAT_NAME_JSON, nativeJSON, 2);
    }

    private NativeJSON() {
    }

    /* access modifiers changed from: protected */
    public void initPrototypeId(int i) {
        String str;
        int i2 = 3;
        if (i <= 3) {
            if (i == 1) {
                i2 = 0;
                str = "toSource";
            } else if (i == 2) {
                str = "parse";
                i2 = 2;
            } else if (i == 3) {
                str = "stringify";
            } else {
                throw new IllegalStateException(String.valueOf(i));
            }
            initPrototypeMethod(JSON_TAG, i, str, i2);
            return;
        }
        throw new IllegalStateException(String.valueOf(i));
    }

    public Object execIdCall(IdFunctionObject idFunctionObject, Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Callable callable;
        Callable callable2;
        Callable callable3;
        if (!idFunctionObject.hasTag(JSON_TAG)) {
            return super.execIdCall(idFunctionObject, context, scriptable, scriptable2, objArr);
        }
        int methodId = idFunctionObject.methodId();
        if (methodId == 1) {
            return JsonFactory.FORMAT_NAME_JSON;
        }
        Callable callable4 = null;
        if (methodId == 2) {
            String scriptRuntime = ScriptRuntime.toString(objArr, 0);
            if (objArr.length > 1) {
                callable4 = objArr[1];
            }
            if (callable4 instanceof Callable) {
                return parse(context, scriptable, scriptRuntime, callable4);
            }
            return parse(context, scriptable, scriptRuntime);
        } else if (methodId == 3) {
            int length = objArr.length;
            if (length != 0) {
                if (length != 1) {
                    if (length != 2) {
                        callable4 = objArr[2];
                    }
                    Callable callable5 = callable4;
                    callable4 = objArr[1];
                    callable3 = callable5;
                } else {
                    callable3 = null;
                }
                callable = callable3;
                callable2 = callable4;
                callable4 = objArr[0];
            } else {
                callable2 = null;
                callable = null;
            }
            return stringify(context, scriptable, callable4, callable2, callable);
        } else {
            throw new IllegalStateException(String.valueOf(methodId));
        }
    }

    private static Object parse(Context context, Scriptable scriptable, String str) {
        try {
            return new JsonParser(context, scriptable).parseValue(str);
        } catch (JsonParser.ParseException e) {
            throw ScriptRuntime.constructError("SyntaxError", e.getMessage());
        }
    }

    public static Object parse(Context context, Scriptable scriptable, String str, Callable callable) {
        Object parse = parse(context, scriptable, str);
        Scriptable newObject = context.newObject(scriptable);
        newObject.put("", newObject, parse);
        return walk(context, scriptable, callable, newObject, "");
    }

    private static Object walk(Context context, Scriptable scriptable, Callable callable, Scriptable scriptable2, Object obj) {
        Object obj2;
        if (obj instanceof Number) {
            obj2 = scriptable2.get(((Number) obj).intValue(), scriptable2);
        } else {
            obj2 = scriptable2.get((String) obj, scriptable2);
        }
        if (obj2 instanceof Scriptable) {
            Scriptable scriptable3 = (Scriptable) obj2;
            if (scriptable3 instanceof NativeArray) {
                int length = (int) ((NativeArray) scriptable3).getLength();
                for (int i = 0; i < length; i++) {
                    Object walk = walk(context, scriptable, callable, scriptable3, Integer.valueOf(i));
                    if (walk == Undefined.instance) {
                        scriptable3.delete(i);
                    } else {
                        scriptable3.put(i, scriptable3, walk);
                    }
                }
            } else {
                for (Object obj3 : scriptable3.getIds()) {
                    Object walk2 = walk(context, scriptable, callable, scriptable3, obj3);
                    if (walk2 == Undefined.instance) {
                        if (obj3 instanceof Number) {
                            scriptable3.delete(((Number) obj3).intValue());
                        } else {
                            scriptable3.delete((String) obj3);
                        }
                    } else if (obj3 instanceof Number) {
                        scriptable3.put(((Number) obj3).intValue(), scriptable3, walk2);
                    } else {
                        scriptable3.put((String) obj3, scriptable3, walk2);
                    }
                }
            }
        }
        return callable.call(context, scriptable, scriptable2, new Object[]{obj, obj2});
    }

    private static String repeat(char c, int i) {
        char[] cArr = new char[i];
        Arrays.fill(cArr, c);
        return new String(cArr);
    }

    private static class StringifyState {
        Context cx;
        String gap;
        String indent;
        List<Object> propertyList;
        Callable replacer;
        Scriptable scope;
        Object space;
        Stack<Scriptable> stack = new Stack<>();

        StringifyState(Context context, Scriptable scriptable, String str, String str2, Callable callable, List<Object> list, Object obj) {
            this.cx = context;
            this.scope = scriptable;
            this.indent = str;
            this.gap = str2;
            this.replacer = callable;
            this.propertyList = list;
            this.space = obj;
        }
    }

    public static Object stringify(Context context, Scriptable scriptable, Object obj, Object obj2, Object obj3) {
        LinkedList linkedList;
        Callable callable;
        Object obj4;
        Object obj5;
        String str;
        String str2;
        Object obj6 = obj2;
        Object obj7 = obj3;
        if (obj6 instanceof Callable) {
            callable = (Callable) obj6;
            linkedList = null;
        } else if (obj6 instanceof NativeArray) {
            LinkedList linkedList2 = new LinkedList();
            NativeArray nativeArray = (NativeArray) obj6;
            for (Integer intValue : nativeArray.getIndexIds()) {
                Object obj8 = nativeArray.get(intValue.intValue(), nativeArray);
                if ((obj8 instanceof String) || (obj8 instanceof Number)) {
                    linkedList2.add(obj8);
                } else if ((obj8 instanceof NativeString) || (obj8 instanceof NativeNumber)) {
                    linkedList2.add(ScriptRuntime.toString(obj8));
                }
            }
            linkedList = linkedList2;
            callable = null;
        } else {
            callable = null;
            linkedList = null;
        }
        if (obj7 instanceof NativeNumber) {
            obj4 = Double.valueOf(ScriptRuntime.toNumber(obj3));
        } else {
            obj4 = obj7 instanceof NativeString ? ScriptRuntime.toString(obj3) : obj7;
        }
        if (obj4 instanceof Number) {
            int min = Math.min(10, (int) ScriptRuntime.toInteger(obj4));
            if (min > 0) {
                str2 = repeat(' ', min);
            } else {
                str2 = "";
            }
            obj4 = Integer.valueOf(min);
        } else if (obj4 instanceof String) {
            str2 = (String) obj4;
            if (str2.length() > 10) {
                str2 = str2.substring(0, 10);
            }
        } else {
            obj5 = obj4;
            str = "";
            StringifyState stringifyState = new StringifyState(context, scriptable, "", str, callable, linkedList, obj5);
            NativeObject nativeObject = new NativeObject();
            nativeObject.setParentScope(scriptable);
            nativeObject.setPrototype(ScriptableObject.getObjectPrototype(scriptable));
            nativeObject.defineProperty("", obj, 0);
            return str("", nativeObject, stringifyState);
        }
        obj5 = obj4;
        str = str2;
        StringifyState stringifyState2 = new StringifyState(context, scriptable, "", str, callable, linkedList, obj5);
        NativeObject nativeObject2 = new NativeObject();
        nativeObject2.setParentScope(scriptable);
        nativeObject2.setPrototype(ScriptableObject.getObjectPrototype(scriptable));
        nativeObject2.defineProperty("", obj, 0);
        return str("", nativeObject2, stringifyState2);
    }

    private static Object str(Object obj, Scriptable scriptable, StringifyState stringifyState) {
        Object obj2;
        if (obj instanceof String) {
            obj2 = getProperty(scriptable, (String) obj);
        } else {
            obj2 = getProperty(scriptable, ((Number) obj).intValue());
        }
        if (obj2 instanceof Scriptable) {
            Scriptable scriptable2 = (Scriptable) obj2;
            if (getProperty(scriptable2, "toJSON") instanceof Callable) {
                obj2 = callMethod(stringifyState.cx, scriptable2, "toJSON", new Object[]{obj});
            }
        }
        if (stringifyState.replacer != null) {
            obj2 = stringifyState.replacer.call(stringifyState.cx, stringifyState.scope, scriptable, new Object[]{obj, obj2});
        }
        if (obj2 instanceof NativeNumber) {
            obj2 = Double.valueOf(ScriptRuntime.toNumber(obj2));
        } else if (obj2 instanceof NativeString) {
            obj2 = ScriptRuntime.toString(obj2);
        } else if (obj2 instanceof NativeBoolean) {
            obj2 = ((NativeBoolean) obj2).getDefaultValue(ScriptRuntime.BooleanClass);
        }
        if (obj2 == null) {
            return "null";
        }
        if (obj2.equals(Boolean.TRUE)) {
            return ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
        }
        if (obj2.equals(Boolean.FALSE)) {
            return "false";
        }
        if (obj2 instanceof CharSequence) {
            return quote(obj2.toString());
        }
        if (obj2 instanceof Number) {
            double doubleValue = ((Number) obj2).doubleValue();
            if (doubleValue != doubleValue || doubleValue == Double.POSITIVE_INFINITY || doubleValue == Double.NEGATIVE_INFINITY) {
                return "null";
            }
            return ScriptRuntime.toString(obj2);
        } else if (!(obj2 instanceof Scriptable) || (obj2 instanceof Callable)) {
            return Undefined.instance;
        } else {
            if (obj2 instanceof NativeArray) {
                return ja((NativeArray) obj2, stringifyState);
            }
            return jo((Scriptable) obj2, stringifyState);
        }
    }

    private static String join(Collection<Object> collection, String str) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        Iterator<Object> it = collection.iterator();
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(it.next().toString());
        while (it.hasNext()) {
            sb.append(str);
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    private static String jo(Scriptable scriptable, StringifyState stringifyState) {
        Object[] objArr;
        String str;
        if (stringifyState.stack.search(scriptable) == -1) {
            stringifyState.stack.push(scriptable);
            String str2 = stringifyState.indent;
            stringifyState.indent += stringifyState.gap;
            if (stringifyState.propertyList != null) {
                objArr = stringifyState.propertyList.toArray();
            } else {
                objArr = scriptable.getIds();
            }
            LinkedList linkedList = new LinkedList();
            for (Object obj : objArr) {
                Object str3 = str(obj, scriptable, stringifyState);
                if (str3 != Undefined.instance) {
                    String str4 = quote(obj.toString()) + ":";
                    if (stringifyState.gap.length() > 0) {
                        str4 = str4 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
                    }
                    linkedList.add(str4 + str3);
                }
            }
            if (linkedList.isEmpty()) {
                str = "{}";
            } else if (stringifyState.gap.length() == 0) {
                str = ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN + join(linkedList, ",") + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
            } else {
                str = "{\n" + stringifyState.indent + join(linkedList, ",\n" + stringifyState.indent) + 10 + str2 + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
            }
            stringifyState.stack.pop();
            stringifyState.indent = str2;
            return str;
        }
        throw ScriptRuntime.typeError0("msg.cyclic.value");
    }

    private static String ja(NativeArray nativeArray, StringifyState stringifyState) {
        String str;
        if (stringifyState.stack.search(nativeArray) == -1) {
            stringifyState.stack.push(nativeArray);
            String str2 = stringifyState.indent;
            stringifyState.indent += stringifyState.gap;
            LinkedList linkedList = new LinkedList();
            int length = (int) nativeArray.getLength();
            for (int i = 0; i < length; i++) {
                Object str3 = str(Integer.valueOf(i), nativeArray, stringifyState);
                if (str3 == Undefined.instance) {
                    linkedList.add("null");
                } else {
                    linkedList.add(str3);
                }
            }
            if (linkedList.isEmpty()) {
                str = HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
            } else if (stringifyState.gap.length() == 0) {
                str = TypePool.Default.LazyTypeDescription.GenericTypeToken.COMPONENT_TYPE_PATH + join(linkedList, ",") + ']';
            } else {
                str = "[\n" + stringifyState.indent + join(linkedList, ",\n" + stringifyState.indent) + 10 + str2 + ']';
            }
            stringifyState.stack.pop();
            stringifyState.indent = str2;
            return str;
        }
        throw ScriptRuntime.typeError0("msg.cyclic.value");
    }

    private static String quote(String str) {
        StringBuffer stringBuffer = new StringBuffer(str.length() + 2);
        stringBuffer.append('\"');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == 12) {
                stringBuffer.append("\\f");
            } else if (charAt == 13) {
                stringBuffer.append("\\r");
            } else if (charAt == '\"') {
                stringBuffer.append("\\\"");
            } else if (charAt != '\\') {
                switch (charAt) {
                    case 8:
                        stringBuffer.append("\\b");
                        break;
                    case 9:
                        stringBuffer.append("\\t");
                        break;
                    case 10:
                        stringBuffer.append("\\n");
                        break;
                    default:
                        if (charAt >= ' ') {
                            stringBuffer.append(charAt);
                            break;
                        } else {
                            stringBuffer.append("\\u");
                            stringBuffer.append(String.format("%04x", new Object[]{Integer.valueOf(charAt)}));
                            break;
                        }
                }
            } else {
                stringBuffer.append("\\\\");
            }
        }
        stringBuffer.append('\"');
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public int findPrototypeId(String str) {
        int i;
        String str2;
        int length = str.length();
        if (length == 5) {
            str2 = "parse";
            i = 2;
        } else if (length == 8) {
            str2 = "toSource";
            i = 1;
        } else if (length != 9) {
            str2 = null;
            i = 0;
        } else {
            str2 = "stringify";
            i = 3;
        }
        if (str2 == null || str2 == str || str2.equals(str)) {
            return i;
        }
        return 0;
    }
}
