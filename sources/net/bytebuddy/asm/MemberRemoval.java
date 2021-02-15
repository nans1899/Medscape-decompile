package net.bytebuddy.asm;

import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.jar.asm.ClassVisitor;
import net.bytebuddy.jar.asm.FieldVisitor;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.pool.TypePool;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.OpenedClassReader;

@HashCodeAndEqualsPlugin.Enhance
public class MemberRemoval extends AsmVisitorWrapper.AbstractBase {
    private final ElementMatcher.Junction<FieldDescription.InDefinedShape> fieldMatcher;
    private final ElementMatcher.Junction<MethodDescription> methodMatcher;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MemberRemoval memberRemoval = (MemberRemoval) obj;
        return this.fieldMatcher.equals(memberRemoval.fieldMatcher) && this.methodMatcher.equals(memberRemoval.methodMatcher);
    }

    public int hashCode() {
        return ((527 + this.fieldMatcher.hashCode()) * 31) + this.methodMatcher.hashCode();
    }

    public MemberRemoval() {
        this(ElementMatchers.none(), ElementMatchers.none());
    }

    protected MemberRemoval(ElementMatcher.Junction<FieldDescription.InDefinedShape> junction, ElementMatcher.Junction<MethodDescription> junction2) {
        this.fieldMatcher = junction;
        this.methodMatcher = junction2;
    }

    public MemberRemoval stripFields(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher) {
        return new MemberRemoval(this.fieldMatcher.or(elementMatcher), this.methodMatcher);
    }

    public MemberRemoval stripMethods(ElementMatcher<? super MethodDescription> elementMatcher) {
        return stripInvokables(ElementMatchers.isMethod().and(elementMatcher));
    }

    public MemberRemoval stripConstructors(ElementMatcher<? super MethodDescription> elementMatcher) {
        return stripInvokables(ElementMatchers.isConstructor().and(elementMatcher));
    }

    public MemberRemoval stripInvokables(ElementMatcher<? super MethodDescription> elementMatcher) {
        return new MemberRemoval(this.fieldMatcher, this.methodMatcher.or(elementMatcher));
    }

    public ClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
        HashMap hashMap = new HashMap();
        for (FieldDescription.InDefinedShape inDefinedShape : fieldList) {
            hashMap.put(inDefinedShape.getInternalName() + inDefinedShape.getDescriptor(), inDefinedShape);
        }
        HashMap hashMap2 = new HashMap();
        for (S s : CompoundList.of(methodList, new MethodDescription.Latent.TypeInitializer(typeDescription))) {
            hashMap2.put(s.getInternalName() + s.getDescriptor(), s);
        }
        return new MemberRemovingClassVisitor(classVisitor, this.fieldMatcher, this.methodMatcher, hashMap, hashMap2);
    }

    protected static class MemberRemovingClassVisitor extends ClassVisitor {
        private static final FieldVisitor REMOVE_FIELD = null;
        private static final MethodVisitor REMOVE_METHOD = null;
        private final ElementMatcher.Junction<FieldDescription.InDefinedShape> fieldMatcher;
        private final Map<String, FieldDescription.InDefinedShape> fields;
        private final ElementMatcher.Junction<MethodDescription> methodMatcher;
        private final Map<String, MethodDescription> methods;

        protected MemberRemovingClassVisitor(ClassVisitor classVisitor, ElementMatcher.Junction<FieldDescription.InDefinedShape> junction, ElementMatcher.Junction<MethodDescription> junction2, Map<String, FieldDescription.InDefinedShape> map, Map<String, MethodDescription> map2) {
            super(OpenedClassReader.ASM_API, classVisitor);
            this.fieldMatcher = junction;
            this.methodMatcher = junction2;
            this.fields = map;
            this.methods = map2;
        }

        public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
            Map<String, FieldDescription.InDefinedShape> map = this.fields;
            FieldDescription.InDefinedShape inDefinedShape = map.get(str + str2);
            if (inDefinedShape == null || !this.fieldMatcher.matches(inDefinedShape)) {
                return super.visitField(i, str, str2, str3, obj);
            }
            return REMOVE_FIELD;
        }

        public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
            Map<String, MethodDescription> map = this.methods;
            MethodDescription methodDescription = map.get(str + str2);
            if (methodDescription == null || !this.methodMatcher.matches(methodDescription)) {
                return super.visitMethod(i, str, str2, str3, strArr);
            }
            return REMOVE_METHOD;
        }
    }
}
