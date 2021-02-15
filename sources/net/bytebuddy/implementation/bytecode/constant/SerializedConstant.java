package net.bytebuddy.implementation.bytecode.constant;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.Duplication;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.TypeCreation;
import net.bytebuddy.implementation.bytecode.member.MethodInvocation;
import net.bytebuddy.jar.asm.MethodVisitor;

@HashCodeAndEqualsPlugin.Enhance
public class SerializedConstant implements StackManipulation {
    private static final String CHARSET = "ISO-8859-1";
    private final String serialization;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.serialization.equals(((SerializedConstant) obj).serialization);
    }

    public int hashCode() {
        return 527 + this.serialization.hashCode();
    }

    public boolean isValid() {
        return true;
    }

    protected SerializedConstant(String str) {
        this.serialization = str;
    }

    public static StackManipulation of(Serializable serializable) {
        ObjectOutputStream objectOutputStream;
        if (serializable == null) {
            return NullConstant.INSTANCE;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            return new SerializedConstant(byteArrayOutputStream.toString(CHARSET));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot serialize " + serializable, e);
        } catch (Throwable th) {
            objectOutputStream.close();
            throw th;
        }
    }

    public StackManipulation.Size apply(MethodVisitor methodVisitor, Implementation.Context context) {
        try {
            return new StackManipulation.Compound(TypeCreation.of(TypeDescription.ForLoadedType.of(ObjectInputStream.class)), Duplication.SINGLE, TypeCreation.of(TypeDescription.ForLoadedType.of(ByteArrayInputStream.class)), Duplication.SINGLE, new TextConstant(this.serialization), new TextConstant(CHARSET), MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(String.class.getMethod("getBytes", new Class[]{String.class}))), MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(ByteArrayInputStream.class.getConstructor(new Class[]{byte[].class}))), MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedConstructor(ObjectInputStream.class.getConstructor(new Class[]{InputStream.class}))), MethodInvocation.invoke((MethodDescription.InDefinedShape) new MethodDescription.ForLoadedMethod(ObjectInputStream.class.getMethod("readObject", new Class[0])))).apply(methodVisitor, context);
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Could not locate Java API method", e);
        }
    }
}
