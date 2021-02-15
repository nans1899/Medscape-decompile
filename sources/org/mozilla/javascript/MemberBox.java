package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

final class MemberBox implements Serializable {
    private static final Class<?>[] primitives = {Boolean.TYPE, Byte.TYPE, Character.TYPE, Double.TYPE, Float.TYPE, Integer.TYPE, Long.TYPE, Short.TYPE, Void.TYPE};
    static final long serialVersionUID = 6358550398665688245L;
    transient Class<?>[] argTypes;
    transient Object delegateTo;
    private transient Member memberObject;
    transient boolean vararg;

    MemberBox(Method method) {
        init(method);
    }

    MemberBox(Constructor<?> constructor) {
        init(constructor);
    }

    private void init(Method method) {
        this.memberObject = method;
        this.argTypes = method.getParameterTypes();
        this.vararg = VMBridge.instance.isVarArgs(method);
    }

    private void init(Constructor<?> constructor) {
        this.memberObject = constructor;
        this.argTypes = constructor.getParameterTypes();
        this.vararg = VMBridge.instance.isVarArgs(constructor);
    }

    /* access modifiers changed from: package-private */
    public Method method() {
        return (Method) this.memberObject;
    }

    /* access modifiers changed from: package-private */
    public Constructor<?> ctor() {
        return (Constructor) this.memberObject;
    }

    /* access modifiers changed from: package-private */
    public Member member() {
        return this.memberObject;
    }

    /* access modifiers changed from: package-private */
    public boolean isMethod() {
        return this.memberObject instanceof Method;
    }

    /* access modifiers changed from: package-private */
    public boolean isCtor() {
        return this.memberObject instanceof Constructor;
    }

    /* access modifiers changed from: package-private */
    public boolean isStatic() {
        return Modifier.isStatic(this.memberObject.getModifiers());
    }

    /* access modifiers changed from: package-private */
    public String getName() {
        return this.memberObject.getName();
    }

    /* access modifiers changed from: package-private */
    public Class<?> getDeclaringClass() {
        return this.memberObject.getDeclaringClass();
    }

    /* access modifiers changed from: package-private */
    public String toJavaDeclaration() {
        StringBuffer stringBuffer = new StringBuffer();
        if (isMethod()) {
            Method method = method();
            stringBuffer.append(method.getReturnType());
            stringBuffer.append(' ');
            stringBuffer.append(method.getName());
        } else {
            String name = ctor().getDeclaringClass().getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf >= 0) {
                name = name.substring(lastIndexOf + 1);
            }
            stringBuffer.append(name);
        }
        stringBuffer.append(JavaMembers.liveConnectSignature(this.argTypes));
        return stringBuffer.toString();
    }

    public String toString() {
        return this.memberObject.toString();
    }

    /* access modifiers changed from: package-private */
    public Object invoke(Object obj, Object[] objArr) {
        Method method = method();
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            Method searchAccessibleMethod = searchAccessibleMethod(method, this.argTypes);
            if (searchAccessibleMethod != null) {
                this.memberObject = searchAccessibleMethod;
                method = searchAccessibleMethod;
            } else if (!VMBridge.instance.tryToMakeAccessible(method)) {
                throw Context.throwAsScriptRuntimeEx(e);
            }
            return method.invoke(obj, objArr);
        } catch (InvocationTargetException e2) {
            e = e2;
            do {
                e = ((InvocationTargetException) e).getTargetException();
            } while (e instanceof InvocationTargetException);
            if (e instanceof ContinuationPending) {
                throw ((ContinuationPending) e);
            }
            throw Context.throwAsScriptRuntimeEx(e);
        } catch (Exception e3) {
            throw Context.throwAsScriptRuntimeEx(e3);
        }
    }

    /* access modifiers changed from: package-private */
    public Object newInstance(Object[] objArr) {
        Constructor<?> ctor = ctor();
        try {
            return ctor.newInstance(objArr);
        } catch (IllegalAccessException e) {
            if (VMBridge.instance.tryToMakeAccessible(ctor)) {
                return ctor.newInstance(objArr);
            }
            throw Context.throwAsScriptRuntimeEx(e);
        } catch (Exception e2) {
            throw Context.throwAsScriptRuntimeEx(e2);
        }
    }

    private static Method searchAccessibleMethod(Method method, Class<?>[] clsArr) {
        int modifiers = method.getModifiers();
        if (!Modifier.isPublic(modifiers) || Modifier.isStatic(modifiers)) {
            return null;
        }
        Class declaringClass = method.getDeclaringClass();
        if (Modifier.isPublic(declaringClass.getModifiers())) {
            return null;
        }
        String name = method.getName();
        Class[] interfaces = declaringClass.getInterfaces();
        int i = 0;
        int length = interfaces.length;
        while (i != length) {
            Class cls = interfaces[i];
            if (Modifier.isPublic(cls.getModifiers())) {
                try {
                    return cls.getMethod(name, clsArr);
                } catch (NoSuchMethodException | SecurityException unused) {
                }
            } else {
                i++;
            }
        }
        while (true) {
            declaringClass = declaringClass.getSuperclass();
            if (declaringClass == null) {
                return null;
            }
            if (Modifier.isPublic(declaringClass.getModifiers())) {
                try {
                    Method method2 = declaringClass.getMethod(name, clsArr);
                    int modifiers2 = method2.getModifiers();
                    if (Modifier.isPublic(modifiers2) && !Modifier.isStatic(modifiers2)) {
                        return method2;
                    }
                } catch (NoSuchMethodException | SecurityException unused2) {
                    continue;
                }
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Member readMember = readMember(objectInputStream);
        if (readMember instanceof Method) {
            init((Method) readMember);
        } else {
            init((Constructor<?>) (Constructor) readMember);
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        writeMember(objectOutputStream, this.memberObject);
    }

    private static void writeMember(ObjectOutputStream objectOutputStream, Member member) throws IOException {
        if (member == null) {
            objectOutputStream.writeBoolean(false);
            return;
        }
        objectOutputStream.writeBoolean(true);
        boolean z = member instanceof Method;
        if (z || (member instanceof Constructor)) {
            objectOutputStream.writeBoolean(z);
            objectOutputStream.writeObject(member.getName());
            objectOutputStream.writeObject(member.getDeclaringClass());
            if (z) {
                writeParameters(objectOutputStream, ((Method) member).getParameterTypes());
            } else {
                writeParameters(objectOutputStream, ((Constructor) member).getParameterTypes());
            }
        } else {
            throw new IllegalArgumentException("not Method or Constructor");
        }
    }

    private static Member readMember(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        if (!objectInputStream.readBoolean()) {
            return null;
        }
        boolean readBoolean = objectInputStream.readBoolean();
        String str = (String) objectInputStream.readObject();
        Class cls = (Class) objectInputStream.readObject();
        Class[] readParameters = readParameters(objectInputStream);
        if (!readBoolean) {
            return cls.getConstructor(readParameters);
        }
        try {
            return cls.getMethod(str, readParameters);
        } catch (NoSuchMethodException e) {
            throw new IOException("Cannot find member: " + e);
        }
    }

    private static void writeParameters(ObjectOutputStream objectOutputStream, Class<?>[] clsArr) throws IOException {
        objectOutputStream.writeShort(clsArr.length);
        for (Class<?> cls : clsArr) {
            boolean isPrimitive = cls.isPrimitive();
            objectOutputStream.writeBoolean(isPrimitive);
            if (!isPrimitive) {
                objectOutputStream.writeObject(cls);
            } else {
                int i = 0;
                while (true) {
                    Class<?>[] clsArr2 = primitives;
                    if (i >= clsArr2.length) {
                        throw new IllegalArgumentException("Primitive " + cls + " not found");
                    } else if (cls.equals(clsArr2[i])) {
                        objectOutputStream.writeByte(i);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    private static Class<?>[] readParameters(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int readShort = objectInputStream.readShort();
        Class<?>[] clsArr = new Class[readShort];
        for (int i = 0; i < readShort; i++) {
            if (!objectInputStream.readBoolean()) {
                clsArr[i] = (Class) objectInputStream.readObject();
            } else {
                clsArr[i] = primitives[objectInputStream.readByte()];
            }
        }
        return clsArr;
    }
}
