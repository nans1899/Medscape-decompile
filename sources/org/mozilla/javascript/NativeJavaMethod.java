package org.mozilla.javascript;

import com.dd.plist.ASCIIPropertyListParser;
import com.google.android.gms.ads.AdError;
import com.medscape.android.slideshow.SlideshowPageFragment;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;

public class NativeJavaMethod extends BaseFunction {
    private static final int PREFERENCE_AMBIGUOUS = 3;
    private static final int PREFERENCE_EQUAL = 0;
    private static final int PREFERENCE_FIRST_ARG = 1;
    private static final int PREFERENCE_SECOND_ARG = 2;
    private static final boolean debug = false;
    static final long serialVersionUID = -3440381785576412928L;
    private String functionName;
    MemberBox[] methods;
    private transient LinkedList<ResolvedOverload> overloadCache;

    private static void printDebug(String str, MemberBox memberBox, Object[] objArr) {
    }

    NativeJavaMethod(MemberBox[] memberBoxArr) {
        this.functionName = memberBoxArr[0].getName();
        this.methods = memberBoxArr;
    }

    NativeJavaMethod(MemberBox[] memberBoxArr, String str) {
        this.functionName = str;
        this.methods = memberBoxArr;
    }

    NativeJavaMethod(MemberBox memberBox, String str) {
        this.functionName = str;
        this.methods = new MemberBox[]{memberBox};
    }

    public NativeJavaMethod(Method method, String str) {
        this(new MemberBox(method), str);
    }

    public String getFunctionName() {
        return this.functionName;
    }

    static String scriptSignature(Object[] objArr) {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i != objArr.length; i++) {
            Wrapper wrapper = objArr[i];
            if (wrapper == null) {
                str = "null";
            } else if (wrapper instanceof Boolean) {
                str = "boolean";
            } else if (wrapper instanceof String) {
                str = "string";
            } else if (wrapper instanceof Number) {
                str = "number";
            } else if (!(wrapper instanceof Scriptable)) {
                str = JavaMembers.javaSignature(wrapper.getClass());
            } else if (wrapper instanceof Undefined) {
                str = AdError.UNDEFINED_DOMAIN;
            } else if (wrapper instanceof Wrapper) {
                str = wrapper.unwrap().getClass().getName();
            } else {
                str = wrapper instanceof Function ? "function" : SlideshowPageFragment.ARG_OBJECT;
            }
            if (i != 0) {
                stringBuffer.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            }
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: package-private */
    public String decompile(int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        if ((i2 & 1) == 0) {
            z = false;
        }
        if (!z) {
            stringBuffer.append("function ");
            stringBuffer.append(getFunctionName());
            stringBuffer.append("() {");
        }
        stringBuffer.append("/*\n");
        stringBuffer.append(toString());
        stringBuffer.append(z ? "*/\n" : "*/}\n");
        return stringBuffer.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        int length = this.methods.length;
        for (int i = 0; i != length; i++) {
            if (this.methods[i].isMethod()) {
                Method method = this.methods[i].method();
                stringBuffer.append(JavaMembers.javaSignature(method.getReturnType()));
                stringBuffer.append(' ');
                stringBuffer.append(method.getName());
            } else {
                stringBuffer.append(this.methods[i].getName());
            }
            stringBuffer.append(JavaMembers.liveConnectSignature(this.methods[i].argTypes));
            stringBuffer.append(10);
        }
        return stringBuffer.toString();
    }

    public Object call(Context context, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        Object[] objArr2;
        Object obj;
        Object obj2;
        if (this.methods.length != 0) {
            int findCachedFunction = findCachedFunction(context, objArr);
            int i = 0;
            if (findCachedFunction >= 0) {
                MemberBox memberBox = this.methods[findCachedFunction];
                Class<?>[] clsArr = memberBox.argTypes;
                if (memberBox.vararg) {
                    objArr2 = new Object[clsArr.length];
                    for (int i2 = 0; i2 < clsArr.length - 1; i2++) {
                        objArr2[i2] = Context.jsToJava(objArr[i2], clsArr[i2]);
                    }
                    if (objArr.length != clsArr.length || (objArr[objArr.length - 1] != null && !(objArr[objArr.length - 1] instanceof NativeArray) && !(objArr[objArr.length - 1] instanceof NativeJavaArray))) {
                        Class<?> componentType = clsArr[clsArr.length - 1].getComponentType();
                        Object newInstance = Array.newInstance(componentType, (objArr.length - clsArr.length) + 1);
                        while (i < Array.getLength(newInstance)) {
                            Array.set(newInstance, i, Context.jsToJava(objArr[(clsArr.length - 1) + i], componentType));
                            i++;
                        }
                        obj2 = newInstance;
                    } else {
                        obj2 = Context.jsToJava(objArr[objArr.length - 1], clsArr[clsArr.length - 1]);
                    }
                    objArr2[clsArr.length - 1] = obj2;
                } else {
                    objArr2 = objArr;
                    while (i < objArr2.length) {
                        Object obj3 = objArr2[i];
                        Object jsToJava = Context.jsToJava(obj3, clsArr[i]);
                        if (jsToJava != obj3) {
                            if (objArr == objArr2) {
                                objArr2 = (Object[]) objArr2.clone();
                            }
                            objArr2[i] = jsToJava;
                        }
                        i++;
                    }
                }
                if (memberBox.isStatic()) {
                    obj = null;
                } else {
                    Class<?> declaringClass = memberBox.getDeclaringClass();
                    for (Scriptable scriptable3 = scriptable2; scriptable3 != null; scriptable3 = scriptable3.getPrototype()) {
                        if (scriptable3 instanceof Wrapper) {
                            Object unwrap = ((Wrapper) scriptable3).unwrap();
                            if (declaringClass.isInstance(unwrap)) {
                                obj = unwrap;
                            }
                        }
                    }
                    throw Context.reportRuntimeError3("msg.nonjava.method", getFunctionName(), ScriptRuntime.toString((Object) scriptable2), declaringClass.getName());
                }
                Object invoke = memberBox.invoke(obj, objArr2);
                Class<?> returnType = memberBox.method().getReturnType();
                Object wrap = context.getWrapFactory().wrap(context, scriptable, invoke, returnType);
                if (wrap == null && returnType == Void.TYPE) {
                    return Undefined.instance;
                }
                return wrap;
            }
            Class<?> declaringClass2 = this.methods[0].method().getDeclaringClass();
            throw Context.reportRuntimeError1("msg.java.no_such_method", declaringClass2.getName() + '.' + getFunctionName() + ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN + scriptSignature(objArr) + ASCIIPropertyListParser.ARRAY_END_TOKEN);
        }
        throw new RuntimeException("No methods defined for call");
    }

    /* access modifiers changed from: package-private */
    public int findCachedFunction(Context context, Object[] objArr) {
        MemberBox[] memberBoxArr = this.methods;
        if (memberBoxArr.length <= 1) {
            return findFunction(context, memberBoxArr, objArr);
        }
        LinkedList<ResolvedOverload> linkedList = this.overloadCache;
        if (linkedList != null) {
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                ResolvedOverload resolvedOverload = (ResolvedOverload) it.next();
                if (resolvedOverload.matches(objArr)) {
                    return resolvedOverload.index;
                }
            }
        } else {
            this.overloadCache = new LinkedList<>();
        }
        int findFunction = findFunction(context, this.methods, objArr);
        if (this.overloadCache.size() < this.methods.length * 2) {
            synchronized (this.overloadCache) {
                ResolvedOverload resolvedOverload2 = new ResolvedOverload(objArr, findFunction);
                if (!this.overloadCache.contains(resolvedOverload2)) {
                    this.overloadCache.addFirst(resolvedOverload2);
                }
            }
        }
        return findFunction;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009c, code lost:
        if ((r14.member().getModifiers() & r5) == 0) goto L_0x00b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00e3, code lost:
        r3 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00e5, code lost:
        if (r12 != r3) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00e7, code lost:
        r7 = r6;
        r5 = 1;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00eb, code lost:
        if (r13 != r3) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00ef, code lost:
        if (r2 != null) goto L_0x00f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00f1, code lost:
        r5 = 1;
        r2 = new int[(r0.length - 1)];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00f7, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00f8, code lost:
        r2[r8] = r6;
        r8 = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int findFunction(org.mozilla.javascript.Context r17, org.mozilla.javascript.MemberBox[] r18, java.lang.Object[] r19) {
        /*
            r0 = r18
            r1 = r19
            int r2 = r0.length
            r3 = -1
            if (r2 != 0) goto L_0x0009
            return r3
        L_0x0009:
            int r2 = r0.length
            r4 = 0
            r5 = 1
            if (r2 != r5) goto L_0x0033
            r0 = r0[r4]
            java.lang.Class<?>[] r2 = r0.argTypes
            int r5 = r2.length
            boolean r0 = r0.vararg
            if (r0 == 0) goto L_0x001d
            int r5 = r5 + -1
            int r0 = r1.length
            if (r5 <= r0) goto L_0x0021
            return r3
        L_0x001d:
            int r0 = r1.length
            if (r5 == r0) goto L_0x0021
            return r3
        L_0x0021:
            r0 = 0
        L_0x0022:
            if (r0 == r5) goto L_0x0032
            r6 = r1[r0]
            r7 = r2[r0]
            boolean r6 = org.mozilla.javascript.NativeJavaObject.canConvert(r6, r7)
            if (r6 != 0) goto L_0x002f
            return r3
        L_0x002f:
            int r0 = r0 + 1
            goto L_0x0022
        L_0x0032:
            return r4
        L_0x0033:
            r2 = 0
            r6 = 0
            r7 = -1
            r8 = 0
        L_0x0037:
            int r9 = r0.length
            if (r6 >= r9) goto L_0x0101
            r9 = r0[r6]
            java.lang.Class<?>[] r10 = r9.argTypes
            int r11 = r10.length
            boolean r12 = r9.vararg
            if (r12 == 0) goto L_0x0049
            int r11 = r11 + -1
            int r12 = r1.length
            if (r11 <= r12) goto L_0x0050
            goto L_0x004c
        L_0x0049:
            int r12 = r1.length
            if (r11 == r12) goto L_0x0050
        L_0x004c:
            r4 = r17
            goto L_0x00ed
        L_0x0050:
            r12 = 0
        L_0x0051:
            if (r12 >= r11) goto L_0x0061
            r13 = r1[r12]
            r14 = r10[r12]
            boolean r13 = org.mozilla.javascript.NativeJavaObject.canConvert(r13, r14)
            if (r13 != 0) goto L_0x005e
            goto L_0x004c
        L_0x005e:
            int r12 = r12 + 1
            goto L_0x0051
        L_0x0061:
            if (r7 >= 0) goto L_0x0067
            r4 = r17
            goto L_0x00dc
        L_0x0067:
            r11 = -1
            r12 = 0
            r13 = 0
        L_0x006a:
            if (r11 == r8) goto L_0x00e1
            if (r11 != r3) goto L_0x0070
            r14 = r7
            goto L_0x0072
        L_0x0070:
            r14 = r2[r11]
        L_0x0072:
            r14 = r0[r14]
            r15 = 13
            r4 = r17
            boolean r15 = r4.hasFeature(r15)
            if (r15 == 0) goto L_0x00a2
            java.lang.reflect.Member r15 = r14.member()
            int r15 = r15.getModifiers()
            r15 = r15 & r5
            java.lang.reflect.Member r16 = r9.member()
            int r16 = r16.getModifiers()
            r3 = r16 & 1
            if (r15 == r3) goto L_0x00a2
            java.lang.reflect.Member r3 = r14.member()
            int r3 = r3.getModifiers()
            r3 = r3 & r5
            if (r3 != 0) goto L_0x009f
            goto L_0x00b3
        L_0x009f:
            int r13 = r13 + 1
            goto L_0x00ba
        L_0x00a2:
            boolean r3 = r9.vararg
            java.lang.Class<?>[] r15 = r14.argTypes
            boolean r5 = r14.vararg
            int r3 = preferSignature(r1, r10, r3, r15, r5)
            r5 = 3
            if (r3 != r5) goto L_0x00b0
            goto L_0x00e3
        L_0x00b0:
            r5 = 1
            if (r3 != r5) goto L_0x00b6
        L_0x00b3:
            int r12 = r12 + 1
            goto L_0x00ba
        L_0x00b6:
            r5 = 2
            if (r3 != r5) goto L_0x00c0
            goto L_0x009f
        L_0x00ba:
            int r11 = r11 + 1
            r3 = -1
            r4 = 0
            r5 = 1
            goto L_0x006a
        L_0x00c0:
            if (r3 == 0) goto L_0x00c5
            org.mozilla.javascript.Kit.codeBug()
        L_0x00c5:
            boolean r3 = r14.isStatic()
            if (r3 == 0) goto L_0x00ed
            java.lang.Class r3 = r14.getDeclaringClass()
            java.lang.Class r5 = r9.getDeclaringClass()
            boolean r3 = r3.isAssignableFrom(r5)
            if (r3 == 0) goto L_0x00ed
            r3 = -1
            if (r11 != r3) goto L_0x00de
        L_0x00dc:
            r7 = r6
            goto L_0x00ed
        L_0x00de:
            r2[r11] = r6
            goto L_0x00ed
        L_0x00e1:
            r4 = r17
        L_0x00e3:
            int r3 = r8 + 1
            if (r12 != r3) goto L_0x00eb
            r7 = r6
            r5 = 1
            r8 = 0
            goto L_0x00fb
        L_0x00eb:
            if (r13 != r3) goto L_0x00ef
        L_0x00ed:
            r5 = 1
            goto L_0x00fb
        L_0x00ef:
            if (r2 != 0) goto L_0x00f7
            int r2 = r0.length
            r5 = 1
            int r2 = r2 - r5
            int[] r2 = new int[r2]
            goto L_0x00f8
        L_0x00f7:
            r5 = 1
        L_0x00f8:
            r2[r8] = r6
            r8 = r3
        L_0x00fb:
            int r6 = r6 + 1
            r3 = -1
            r4 = 0
            goto L_0x0037
        L_0x0101:
            if (r7 >= 0) goto L_0x0105
            r3 = -1
            return r3
        L_0x0105:
            r3 = -1
            if (r8 != 0) goto L_0x0109
            return r7
        L_0x0109:
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            r5 = -1
        L_0x010f:
            if (r5 == r8) goto L_0x0128
            if (r5 != r3) goto L_0x0115
            r6 = r7
            goto L_0x0117
        L_0x0115:
            r6 = r2[r5]
        L_0x0117:
            java.lang.String r9 = "\n    "
            r4.append(r9)
            r6 = r0[r6]
            java.lang.String r6 = r6.toJavaDeclaration()
            r4.append(r6)
            int r5 = r5 + 1
            goto L_0x010f
        L_0x0128:
            r2 = r0[r7]
            java.lang.String r3 = r2.getName()
            java.lang.Class r2 = r2.getDeclaringClass()
            java.lang.String r2 = r2.getName()
            r5 = 0
            r0 = r0[r5]
            boolean r0 = r0.isCtor()
            if (r0 == 0) goto L_0x014e
            java.lang.String r0 = scriptSignature(r19)
            java.lang.String r1 = r4.toString()
            java.lang.String r2 = "msg.constructor.ambiguous"
            org.mozilla.javascript.EvaluatorException r0 = org.mozilla.javascript.Context.reportRuntimeError3(r2, r3, r0, r1)
            throw r0
        L_0x014e:
            java.lang.String r0 = scriptSignature(r19)
            java.lang.String r1 = r4.toString()
            java.lang.String r4 = "msg.method.ambiguous"
            org.mozilla.javascript.EvaluatorException r0 = org.mozilla.javascript.Context.reportRuntimeError4(r4, r2, r3, r0, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeJavaMethod.findFunction(org.mozilla.javascript.Context, org.mozilla.javascript.MemberBox[], java.lang.Object[]):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0041, code lost:
        if (r4.isAssignableFrom(r3) != false) goto L_0x0045;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int preferSignature(java.lang.Object[] r9, java.lang.Class<?>[] r10, boolean r11, java.lang.Class<?>[] r12, boolean r13) {
        /*
            r0 = 0
            r1 = 0
        L_0x0002:
            int r2 = r9.length
            if (r0 >= r2) goto L_0x004c
            r2 = 1
            if (r11 == 0) goto L_0x0010
            int r3 = r10.length
            if (r0 < r3) goto L_0x0010
            int r3 = r10.length
            int r3 = r3 - r2
            r3 = r10[r3]
            goto L_0x0012
        L_0x0010:
            r3 = r10[r0]
        L_0x0012:
            if (r13 == 0) goto L_0x001c
            int r4 = r12.length
            if (r0 < r4) goto L_0x001c
            int r4 = r12.length
            int r4 = r4 - r2
            r4 = r12[r4]
            goto L_0x001e
        L_0x001c:
            r4 = r12[r0]
        L_0x001e:
            if (r3 != r4) goto L_0x0021
            goto L_0x0049
        L_0x0021:
            r5 = r9[r0]
            int r6 = org.mozilla.javascript.NativeJavaObject.getConversionWeight(r5, r3)
            int r5 = org.mozilla.javascript.NativeJavaObject.getConversionWeight(r5, r4)
            r7 = 2
            r8 = 3
            if (r6 >= r5) goto L_0x0030
            goto L_0x0045
        L_0x0030:
            if (r6 <= r5) goto L_0x0034
        L_0x0032:
            r2 = 2
            goto L_0x0045
        L_0x0034:
            if (r6 != 0) goto L_0x0044
            boolean r5 = r3.isAssignableFrom(r4)
            if (r5 == 0) goto L_0x003d
            goto L_0x0032
        L_0x003d:
            boolean r3 = r4.isAssignableFrom(r3)
            if (r3 == 0) goto L_0x0044
            goto L_0x0045
        L_0x0044:
            r2 = 3
        L_0x0045:
            r1 = r1 | r2
            if (r1 != r8) goto L_0x0049
            goto L_0x004c
        L_0x0049:
            int r0 = r0 + 1
            goto L_0x0002
        L_0x004c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NativeJavaMethod.preferSignature(java.lang.Object[], java.lang.Class[], boolean, java.lang.Class[], boolean):int");
    }
}
