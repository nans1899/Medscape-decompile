package net.bytebuddy.asm;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.bytebuddy.asm.AsmVisitorWrapper;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.modifier.ModifierContributor;
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
public class ModifierAdjustment extends AsmVisitorWrapper.AbstractBase {
    private final List<Adjustment<FieldDescription.InDefinedShape>> fieldAdjustments;
    private final List<Adjustment<MethodDescription>> methodAdjustments;
    private final List<Adjustment<TypeDescription>> typeAdjustments;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ModifierAdjustment modifierAdjustment = (ModifierAdjustment) obj;
        return this.typeAdjustments.equals(modifierAdjustment.typeAdjustments) && this.fieldAdjustments.equals(modifierAdjustment.fieldAdjustments) && this.methodAdjustments.equals(modifierAdjustment.methodAdjustments);
    }

    public int hashCode() {
        return ((((527 + this.typeAdjustments.hashCode()) * 31) + this.fieldAdjustments.hashCode()) * 31) + this.methodAdjustments.hashCode();
    }

    public ModifierAdjustment() {
        this(Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    protected ModifierAdjustment(List<Adjustment<TypeDescription>> list, List<Adjustment<FieldDescription.InDefinedShape>> list2, List<Adjustment<MethodDescription>> list3) {
        this.typeAdjustments = list;
        this.fieldAdjustments = list2;
        this.methodAdjustments = list3;
    }

    public ModifierAdjustment withTypeModifiers(ModifierContributor.ForType... forTypeArr) {
        return withTypeModifiers((List<? extends ModifierContributor.ForType>) Arrays.asList(forTypeArr));
    }

    public ModifierAdjustment withTypeModifiers(List<? extends ModifierContributor.ForType> list) {
        return withTypeModifiers((ElementMatcher<? super TypeDescription>) ElementMatchers.any(), list);
    }

    public ModifierAdjustment withTypeModifiers(ElementMatcher<? super TypeDescription> elementMatcher, ModifierContributor.ForType... forTypeArr) {
        return withTypeModifiers(elementMatcher, (List<? extends ModifierContributor.ForType>) Arrays.asList(forTypeArr));
    }

    public ModifierAdjustment withTypeModifiers(ElementMatcher<? super TypeDescription> elementMatcher, List<? extends ModifierContributor.ForType> list) {
        return new ModifierAdjustment(CompoundList.of(new Adjustment(elementMatcher, ModifierContributor.Resolver.of(list)), this.typeAdjustments), this.fieldAdjustments, this.methodAdjustments);
    }

    public ModifierAdjustment withFieldModifiers(ModifierContributor.ForField... forFieldArr) {
        return withFieldModifiers((List<? extends ModifierContributor.ForField>) Arrays.asList(forFieldArr));
    }

    public ModifierAdjustment withFieldModifiers(List<? extends ModifierContributor.ForField> list) {
        return withFieldModifiers((ElementMatcher<? super FieldDescription.InDefinedShape>) ElementMatchers.any(), list);
    }

    public ModifierAdjustment withFieldModifiers(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, ModifierContributor.ForField... forFieldArr) {
        return withFieldModifiers(elementMatcher, (List<? extends ModifierContributor.ForField>) Arrays.asList(forFieldArr));
    }

    public ModifierAdjustment withFieldModifiers(ElementMatcher<? super FieldDescription.InDefinedShape> elementMatcher, List<? extends ModifierContributor.ForField> list) {
        return new ModifierAdjustment(this.typeAdjustments, CompoundList.of(new Adjustment(elementMatcher, ModifierContributor.Resolver.of(list)), this.fieldAdjustments), this.methodAdjustments);
    }

    public ModifierAdjustment withMethodModifiers(ModifierContributor.ForMethod... forMethodArr) {
        return withMethodModifiers((List<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withMethodModifiers(List<? extends ModifierContributor.ForMethod> list) {
        return withMethodModifiers((ElementMatcher<? super MethodDescription>) ElementMatchers.any(), list);
    }

    public ModifierAdjustment withMethodModifiers(ElementMatcher<? super MethodDescription> elementMatcher, ModifierContributor.ForMethod... forMethodArr) {
        return withMethodModifiers(elementMatcher, (List<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withMethodModifiers(ElementMatcher<? super MethodDescription> elementMatcher, List<? extends ModifierContributor.ForMethod> list) {
        return withInvokableModifiers((ElementMatcher<? super MethodDescription>) ElementMatchers.isMethod().and(elementMatcher), list);
    }

    public ModifierAdjustment withConstructorModifiers(ModifierContributor.ForMethod... forMethodArr) {
        return withConstructorModifiers((List<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withConstructorModifiers(List<? extends ModifierContributor.ForMethod> list) {
        return withConstructorModifiers((ElementMatcher<? super MethodDescription>) ElementMatchers.any(), list);
    }

    public ModifierAdjustment withConstructorModifiers(ElementMatcher<? super MethodDescription> elementMatcher, ModifierContributor.ForMethod... forMethodArr) {
        return withConstructorModifiers(elementMatcher, (List<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withConstructorModifiers(ElementMatcher<? super MethodDescription> elementMatcher, List<? extends ModifierContributor.ForMethod> list) {
        return withInvokableModifiers((ElementMatcher<? super MethodDescription>) ElementMatchers.isConstructor().and(elementMatcher), list);
    }

    public ModifierAdjustment withInvokableModifiers(ModifierContributor.ForMethod... forMethodArr) {
        return withInvokableModifiers((List<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withInvokableModifiers(List<? extends ModifierContributor.ForMethod> list) {
        return withInvokableModifiers((ElementMatcher<? super MethodDescription>) ElementMatchers.any(), list);
    }

    public ModifierAdjustment withInvokableModifiers(ElementMatcher<? super MethodDescription> elementMatcher, ModifierContributor.ForMethod... forMethodArr) {
        return withInvokableModifiers(elementMatcher, (List<? extends ModifierContributor.ForMethod>) Arrays.asList(forMethodArr));
    }

    public ModifierAdjustment withInvokableModifiers(ElementMatcher<? super MethodDescription> elementMatcher, List<? extends ModifierContributor.ForMethod> list) {
        return new ModifierAdjustment(this.typeAdjustments, this.fieldAdjustments, CompoundList.of(new Adjustment(elementMatcher, ModifierContributor.Resolver.of(list)), this.methodAdjustments));
    }

    public ModifierAdjustingClassVisitor wrap(TypeDescription typeDescription, ClassVisitor classVisitor, Implementation.Context context, TypePool typePool, FieldList<FieldDescription.InDefinedShape> fieldList, MethodList<?> methodList, int i, int i2) {
        HashMap hashMap = new HashMap();
        for (FieldDescription.InDefinedShape inDefinedShape : fieldList) {
            hashMap.put(inDefinedShape.getInternalName() + inDefinedShape.getDescriptor(), inDefinedShape);
        }
        HashMap hashMap2 = new HashMap();
        TypeDescription typeDescription2 = typeDescription;
        for (S s : CompoundList.of(methodList, new MethodDescription.Latent.TypeInitializer(typeDescription))) {
            hashMap2.put(s.getInternalName() + s.getDescriptor(), s);
        }
        return new ModifierAdjustingClassVisitor(classVisitor, this.typeAdjustments, this.fieldAdjustments, this.methodAdjustments, typeDescription, hashMap, hashMap2);
    }

    @HashCodeAndEqualsPlugin.Enhance
    protected static class Adjustment<T> implements ElementMatcher<T> {
        private final ElementMatcher<? super T> matcher;
        private final ModifierContributor.Resolver<?> resolver;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Adjustment adjustment = (Adjustment) obj;
            return this.matcher.equals(adjustment.matcher) && this.resolver.equals(adjustment.resolver);
        }

        public int hashCode() {
            return ((527 + this.matcher.hashCode()) * 31) + this.resolver.hashCode();
        }

        protected Adjustment(ElementMatcher<? super T> elementMatcher, ModifierContributor.Resolver<?> resolver2) {
            this.matcher = elementMatcher;
            this.resolver = resolver2;
        }

        public boolean matches(T t) {
            return this.matcher.matches(t);
        }

        /* access modifiers changed from: protected */
        public int resolve(int i) {
            return this.resolver.resolve(i);
        }
    }

    protected static class ModifierAdjustingClassVisitor extends ClassVisitor {
        private final List<Adjustment<FieldDescription.InDefinedShape>> fieldAdjustments;
        private final Map<String, FieldDescription.InDefinedShape> fields;
        private final TypeDescription instrumentedType;
        private final List<Adjustment<MethodDescription>> methodAdjustments;
        private final Map<String, MethodDescription> methods;
        private final List<Adjustment<TypeDescription>> typeAdjustments;

        protected ModifierAdjustingClassVisitor(ClassVisitor classVisitor, List<Adjustment<TypeDescription>> list, List<Adjustment<FieldDescription.InDefinedShape>> list2, List<Adjustment<MethodDescription>> list3, TypeDescription typeDescription, Map<String, FieldDescription.InDefinedShape> map, Map<String, MethodDescription> map2) {
            super(OpenedClassReader.ASM_API, classVisitor);
            this.typeAdjustments = list;
            this.fieldAdjustments = list2;
            this.methodAdjustments = list3;
            this.instrumentedType = typeDescription;
            this.fields = map;
            this.methods = map2;
        }

        public void visit(int i, int i2, String str, String str2, String str3, String[] strArr) {
            Iterator<Adjustment<TypeDescription>> it = this.typeAdjustments.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Adjustment next = it.next();
                if (next.matches(this.instrumentedType)) {
                    i2 = next.resolve(i2);
                    break;
                }
            }
            super.visit(i, i2, str, str2, str3, strArr);
        }

        public void visitInnerClass(String str, String str2, String str3, int i) {
            if (this.instrumentedType.getInternalName().equals(str)) {
                Iterator<Adjustment<TypeDescription>> it = this.typeAdjustments.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Adjustment next = it.next();
                    if (next.matches(this.instrumentedType)) {
                        i = next.resolve(i);
                        break;
                    }
                }
            }
            super.visitInnerClass(str, str2, str3, i);
        }

        public FieldVisitor visitField(int i, String str, String str2, String str3, Object obj) {
            Map<String, FieldDescription.InDefinedShape> map = this.fields;
            FieldDescription.InDefinedShape inDefinedShape = map.get(str + str2);
            if (inDefinedShape != null) {
                Iterator<Adjustment<FieldDescription.InDefinedShape>> it = this.fieldAdjustments.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Adjustment next = it.next();
                    if (next.matches(inDefinedShape)) {
                        i = next.resolve(i);
                        break;
                    }
                }
            }
            return super.visitField(i, str, str2, str3, obj);
        }

        public MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr) {
            Map<String, MethodDescription> map = this.methods;
            MethodDescription methodDescription = map.get(str + str2);
            if (methodDescription != null) {
                Iterator<Adjustment<MethodDescription>> it = this.methodAdjustments.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Adjustment next = it.next();
                    if (next.matches(methodDescription)) {
                        i = next.resolve(i);
                        break;
                    }
                }
            }
            return super.visitMethod(i, str, str2, str3, strArr);
        }
    }
}
