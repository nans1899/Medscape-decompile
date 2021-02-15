package net.bytebuddy.dynamic.scaffold;

import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.medscape.android.settings.Settings;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.annotation.AnnotationValue;
import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.field.FieldList;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.MethodList;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.type.PackageDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeList;
import net.bytebuddy.description.type.TypeVariableToken;
import net.bytebuddy.dynamic.TargetType;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;
import net.bytebuddy.implementation.LoadedTypeInitializer;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.CompoundList;
import net.media.android.bidder.base.common.Constants;

public interface InstrumentedType extends TypeDescription {

    public interface Prepareable {
        InstrumentedType prepare(InstrumentedType instrumentedType);
    }

    public interface WithFlexibleName extends InstrumentedType {
        WithFlexibleName withAnnotations(List<? extends AnnotationDescription> list);

        WithFlexibleName withAnonymousClass(boolean z);

        WithFlexibleName withDeclaredTypes(TypeList typeList);

        WithFlexibleName withDeclaringType(TypeDescription typeDescription);

        WithFlexibleName withEnclosingMethod(MethodDescription.InDefinedShape inDefinedShape);

        WithFlexibleName withEnclosingType(TypeDescription typeDescription);

        WithFlexibleName withField(FieldDescription.Token token);

        WithFlexibleName withInitializer(LoadedTypeInitializer loadedTypeInitializer);

        WithFlexibleName withInitializer(ByteCodeAppender byteCodeAppender);

        WithFlexibleName withInterfaces(TypeList.Generic generic);

        WithFlexibleName withLocalClass(boolean z);

        WithFlexibleName withMethod(MethodDescription.Token token);

        WithFlexibleName withModifiers(int i);

        WithFlexibleName withName(String str);

        WithFlexibleName withNestHost(TypeDescription typeDescription);

        WithFlexibleName withNestMembers(TypeList typeList);

        WithFlexibleName withTypeVariable(TypeVariableToken typeVariableToken);

        WithFlexibleName withTypeVariables(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer);
    }

    LoadedTypeInitializer getLoadedTypeInitializer();

    TypeInitializer getTypeInitializer();

    TypeDescription validated();

    InstrumentedType withAnnotations(List<? extends AnnotationDescription> list);

    InstrumentedType withAnonymousClass(boolean z);

    InstrumentedType withDeclaredTypes(TypeList typeList);

    InstrumentedType withDeclaringType(TypeDescription typeDescription);

    InstrumentedType withEnclosingMethod(MethodDescription.InDefinedShape inDefinedShape);

    InstrumentedType withEnclosingType(TypeDescription typeDescription);

    InstrumentedType withField(FieldDescription.Token token);

    InstrumentedType withInitializer(LoadedTypeInitializer loadedTypeInitializer);

    InstrumentedType withInitializer(ByteCodeAppender byteCodeAppender);

    InstrumentedType withInterfaces(TypeList.Generic generic);

    InstrumentedType withLocalClass(boolean z);

    InstrumentedType withMethod(MethodDescription.Token token);

    InstrumentedType withModifiers(int i);

    InstrumentedType withNestHost(TypeDescription typeDescription);

    InstrumentedType withNestMembers(TypeList typeList);

    InstrumentedType withTypeVariable(TypeVariableToken typeVariableToken);

    public interface Factory {
        WithFlexibleName represent(TypeDescription typeDescription);

        WithFlexibleName subclass(String str, int i, TypeDescription.Generic generic);

        public enum Default implements Factory {
            MODIFIABLE {
                public WithFlexibleName represent(TypeDescription typeDescription) {
                    TypeDescription typeDescription2;
                    boolean z;
                    List list;
                    String name = typeDescription.getName();
                    int modifiers = typeDescription.getModifiers();
                    TypeDescription.Generic superClass = typeDescription.getSuperClass();
                    ByteCodeElement.Token.TokenList<TypeVariableToken> asTokenList = typeDescription.getTypeVariables().asTokenList(ElementMatchers.is((Object) typeDescription));
                    TypeList.Generic accept = typeDescription.getInterfaces().accept(TypeDescription.Generic.Visitor.Substitutor.ForDetachment.of(typeDescription));
                    ByteCodeElement.Token.TokenList<FieldDescription.Token> asTokenList2 = typeDescription.getDeclaredFields().asTokenList(ElementMatchers.is((Object) typeDescription));
                    ByteCodeElement.Token.TokenList<MethodDescription.Token> asTokenList3 = typeDescription.getDeclaredMethods().asTokenList(ElementMatchers.is((Object) typeDescription));
                    AnnotationList declaredAnnotations = typeDescription.getDeclaredAnnotations();
                    TypeInitializer.None none = TypeInitializer.None.INSTANCE;
                    LoadedTypeInitializer.NoOp noOp = LoadedTypeInitializer.NoOp.INSTANCE;
                    TypeDescription declaringType = typeDescription.getDeclaringType();
                    MethodDescription.InDefinedShape enclosingMethod = typeDescription.getEnclosingMethod();
                    TypeDescription enclosingType = typeDescription.getEnclosingType();
                    TypeList declaredTypes = typeDescription.getDeclaredTypes();
                    boolean isAnonymousType = typeDescription.isAnonymousType();
                    boolean isLocalType = typeDescription.isLocalType();
                    if (typeDescription.isNestHost()) {
                        typeDescription2 = TargetType.DESCRIPTION;
                    } else {
                        typeDescription2 = typeDescription.getNestHost();
                    }
                    TypeDescription typeDescription3 = typeDescription2;
                    if (typeDescription.isNestHost()) {
                        z = isAnonymousType;
                        list = typeDescription.getNestMembers().filter(ElementMatchers.not(ElementMatchers.is((Object) typeDescription)));
                    } else {
                        z = isAnonymousType;
                        list = Collections.emptyList();
                    }
                    return new Default(name, modifiers, superClass, asTokenList, accept, asTokenList2, asTokenList3, declaredAnnotations, none, noOp, declaringType, enclosingMethod, enclosingType, declaredTypes, z, isLocalType, typeDescription3, list);
                }
            },
            FROZEN {
                public WithFlexibleName represent(TypeDescription typeDescription) {
                    return new Frozen(typeDescription, LoadedTypeInitializer.NoOp.INSTANCE);
                }
            };

            public WithFlexibleName subclass(String str, int i, TypeDescription.Generic generic) {
                return new Default(str, i, generic, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), TypeInitializer.None.INSTANCE, LoadedTypeInitializer.NoOp.INSTANCE, TypeDescription.UNDEFINED, MethodDescription.UNDEFINED, TypeDescription.UNDEFINED, Collections.emptyList(), false, false, TargetType.DESCRIPTION, Collections.emptyList());
            }
        }
    }

    public static class Default extends TypeDescription.AbstractBase.OfSimpleType implements WithFlexibleName {
        private static final Set<String> KEYWORDS = new HashSet(Arrays.asList(new String[]{"abstract", "continue", "for", AppSettingsData.STATUS_NEW, "switch", "assert", "default", "goto", Constants.APP_PACKAGE, "synchronized", "boolean", "do", "if", "private", "this", "break", "double", "implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum", "instanceof", "return", FacebookRequestErrorClassification.KEY_TRANSIENT, "catch", "extends", "int", "short", "try", "char", "final", "interface", "static", "void", Name.LABEL, "finally", Settings.LONGITUDE_KEY, "strictfp", "volatile", "const", "float", AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "super", "while"}));
        private final List<? extends AnnotationDescription> annotationDescriptions;
        private final boolean anonymousClass;
        private final List<? extends TypeDescription> declaredTypes;
        private final TypeDescription declaringType;
        private final MethodDescription.InDefinedShape enclosingMethod;
        private final TypeDescription enclosingType;
        private final List<? extends FieldDescription.Token> fieldTokens;
        private final List<? extends TypeDescription.Generic> interfaceTypes;
        private final LoadedTypeInitializer loadedTypeInitializer;
        private final boolean localClass;
        private final List<? extends MethodDescription.Token> methodTokens;
        private final int modifiers;
        private final String name;
        private final TypeDescription nestHost;
        private final List<? extends TypeDescription> nestMembers;
        private final TypeDescription.Generic superClass;
        private final TypeInitializer typeInitializer;
        private final List<? extends TypeVariableToken> typeVariables;

        protected Default(String str, int i, TypeDescription.Generic generic, List<? extends TypeVariableToken> list, List<? extends TypeDescription.Generic> list2, List<? extends FieldDescription.Token> list3, List<? extends MethodDescription.Token> list4, List<? extends AnnotationDescription> list5, TypeInitializer typeInitializer2, LoadedTypeInitializer loadedTypeInitializer2, TypeDescription typeDescription, MethodDescription.InDefinedShape inDefinedShape, TypeDescription typeDescription2, List<? extends TypeDescription> list6, boolean z, boolean z2, TypeDescription typeDescription3, List<? extends TypeDescription> list7) {
            this.name = str;
            this.modifiers = i;
            this.typeVariables = list;
            this.superClass = generic;
            this.interfaceTypes = list2;
            this.fieldTokens = list3;
            this.methodTokens = list4;
            this.annotationDescriptions = list5;
            this.typeInitializer = typeInitializer2;
            this.loadedTypeInitializer = loadedTypeInitializer2;
            this.declaringType = typeDescription;
            this.enclosingMethod = inDefinedShape;
            this.enclosingType = typeDescription2;
            this.declaredTypes = list6;
            this.anonymousClass = z;
            this.localClass = z2;
            this.nestHost = typeDescription3;
            this.nestMembers = list7;
        }

        public static InstrumentedType of(String str, TypeDescription.Generic generic, ModifierContributor.ForType... forTypeArr) {
            return of(str, generic, ModifierContributor.Resolver.of(forTypeArr).resolve());
        }

        public static InstrumentedType of(String str, TypeDescription.Generic generic, int i) {
            return Factory.Default.MODIFIABLE.subclass(str, i, generic);
        }

        public WithFlexibleName withModifiers(int i) {
            return new Default(this.name, i, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withField(FieldDescription.Token token) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, CompoundList.of(this.fieldTokens, token.accept((TypeDescription.Generic.Visitor) TypeDescription.Generic.Visitor.Substitutor.ForDetachment.of(this))), this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withMethod(MethodDescription.Token token) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, CompoundList.of(this.methodTokens, token.accept((TypeDescription.Generic.Visitor) TypeDescription.Generic.Visitor.Substitutor.ForDetachment.of(this))), this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withInterfaces(TypeList.Generic generic) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, CompoundList.of(this.interfaceTypes, (List<? extends TypeDescription.Generic>) generic.accept(TypeDescription.Generic.Visitor.Substitutor.ForDetachment.of(this))), this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withAnnotations(List<? extends AnnotationDescription> list) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, CompoundList.of(this.annotationDescriptions, list), this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withNestHost(TypeDescription typeDescription) {
            String str = this.name;
            int i = this.modifiers;
            TypeDescription.Generic generic = this.superClass;
            List<? extends TypeVariableToken> list = this.typeVariables;
            List<? extends TypeDescription.Generic> list2 = this.interfaceTypes;
            List<? extends FieldDescription.Token> list3 = this.fieldTokens;
            List<? extends MethodDescription.Token> list4 = this.methodTokens;
            List<? extends AnnotationDescription> list5 = this.annotationDescriptions;
            TypeInitializer typeInitializer2 = this.typeInitializer;
            LoadedTypeInitializer loadedTypeInitializer2 = this.loadedTypeInitializer;
            TypeDescription typeDescription2 = this.declaringType;
            MethodDescription.InDefinedShape inDefinedShape = this.enclosingMethod;
            TypeDescription typeDescription3 = this.enclosingType;
            List<? extends TypeDescription> list6 = this.declaredTypes;
            boolean z = this.anonymousClass;
            List<? extends TypeDescription> list7 = list6;
            boolean z2 = this.localClass;
            boolean z3 = z;
            TypeDescription typeDescription4 = typeDescription;
            if (typeDescription4.equals(this)) {
                typeDescription4 = TargetType.DESCRIPTION;
            }
            boolean z4 = z2;
            return new Default(str, i, generic, list, list2, list3, list4, list5, typeInitializer2, loadedTypeInitializer2, typeDescription2, inDefinedShape, typeDescription3, list7, z3, z4, typeDescription4, Collections.emptyList());
        }

        public WithFlexibleName withNestMembers(TypeList typeList) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, TargetType.DESCRIPTION, CompoundList.of(this.nestMembers, (List<? extends TypeDescription>) typeList));
        }

        public WithFlexibleName withEnclosingType(TypeDescription typeDescription) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, MethodDescription.UNDEFINED, typeDescription, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withEnclosingMethod(MethodDescription.InDefinedShape inDefinedShape) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, inDefinedShape, inDefinedShape.getDeclaringType(), this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withDeclaringType(TypeDescription typeDescription) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, typeDescription, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withDeclaredTypes(TypeList typeList) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, CompoundList.of(this.declaredTypes, (List<? extends TypeDescription>) typeList), this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withTypeVariable(TypeVariableToken typeVariableToken) {
            return new Default(this.name, this.modifiers, this.superClass, CompoundList.of(this.typeVariables, typeVariableToken.accept((TypeDescription.Generic.Visitor) TypeDescription.Generic.Visitor.Substitutor.ForDetachment.of(this))), this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withName(String str) {
            return new Default(str, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withTypeVariables(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer) {
            ArrayList arrayList = new ArrayList(this.typeVariables.size());
            int i = 0;
            for (TypeVariableToken typeVariableToken : this.typeVariables) {
                int i2 = i + 1;
                if (elementMatcher.matches(getTypeVariables().get(i))) {
                    typeVariableToken = transformer.transform(this, typeVariableToken);
                } else {
                    Transformer<TypeVariableToken> transformer2 = transformer;
                }
                arrayList.add(typeVariableToken);
                i = i2;
            }
            return new Default(this.name, this.modifiers, this.superClass, arrayList, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withLocalClass(boolean z) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, false, z, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withAnonymousClass(boolean z) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer, this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, z, false, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withInitializer(LoadedTypeInitializer loadedTypeInitializer2) {
            String str = this.name;
            int i = this.modifiers;
            TypeDescription.Generic generic = this.superClass;
            List<? extends TypeVariableToken> list = this.typeVariables;
            List<? extends TypeDescription.Generic> list2 = this.interfaceTypes;
            List<? extends FieldDescription.Token> list3 = this.fieldTokens;
            List<? extends MethodDescription.Token> list4 = this.methodTokens;
            List<? extends AnnotationDescription> list5 = this.annotationDescriptions;
            TypeInitializer typeInitializer2 = this.typeInitializer;
            LoadedTypeInitializer.Compound compound = r12;
            LoadedTypeInitializer.Compound compound2 = new LoadedTypeInitializer.Compound(this.loadedTypeInitializer, loadedTypeInitializer2);
            return new Default(str, i, generic, list, list2, list3, list4, list5, typeInitializer2, compound, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public WithFlexibleName withInitializer(ByteCodeAppender byteCodeAppender) {
            return new Default(this.name, this.modifiers, this.superClass, this.typeVariables, this.interfaceTypes, this.fieldTokens, this.methodTokens, this.annotationDescriptions, this.typeInitializer.expandWith(byteCodeAppender), this.loadedTypeInitializer, this.declaringType, this.enclosingMethod, this.enclosingType, this.declaredTypes, this.anonymousClass, this.localClass, this.nestHost, this.nestMembers);
        }

        public LoadedTypeInitializer getLoadedTypeInitializer() {
            return this.loadedTypeInitializer;
        }

        public TypeInitializer getTypeInitializer() {
            return this.typeInitializer;
        }

        public MethodDescription.InDefinedShape getEnclosingMethod() {
            return this.enclosingMethod;
        }

        public TypeDescription getEnclosingType() {
            return this.enclosingType;
        }

        public TypeList getDeclaredTypes() {
            return new TypeList.Explicit(this.declaredTypes);
        }

        public boolean isAnonymousType() {
            return this.anonymousClass;
        }

        public boolean isLocalType() {
            return this.localClass;
        }

        public PackageDescription getPackage() {
            String str;
            int lastIndexOf = this.name.lastIndexOf(46);
            if (lastIndexOf == -1) {
                str = "";
            } else {
                str = this.name.substring(0, lastIndexOf);
            }
            return new PackageDescription.Simple(str);
        }

        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Explicit(this.annotationDescriptions);
        }

        public TypeDescription getDeclaringType() {
            return this.declaringType;
        }

        public TypeDescription.Generic getSuperClass() {
            if (this.superClass == null) {
                return TypeDescription.Generic.UNDEFINED;
            }
            return new TypeDescription.Generic.LazyProjection.WithResolvedErasure(this.superClass, TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of((TypeDescription) this));
        }

        public TypeList.Generic getInterfaces() {
            return new TypeList.Generic.ForDetachedTypes.WithResolvedErasure(this.interfaceTypes, TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of((TypeDescription) this));
        }

        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            return new FieldList.ForTokens((TypeDescription) this, this.fieldTokens);
        }

        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            return new MethodList.ForTokens((TypeDescription) this, this.methodTokens);
        }

        public TypeList.Generic getTypeVariables() {
            return TypeList.Generic.ForDetachedTypes.attachVariables((TypeDescription) this, this.typeVariables);
        }

        public int getModifiers() {
            return this.modifiers;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription getNestHost() {
            return this.nestHost.represents(TargetType.class) ? this : this.nestHost;
        }

        public TypeList getNestMembers() {
            if (this.nestHost.represents(TargetType.class)) {
                return new TypeList.Explicit((List<? extends TypeDescription>) CompoundList.of(this, this.nestMembers));
            }
            return this.nestHost.getNestMembers();
        }

        public TypeDescription validated() {
            String str;
            AnnotationDescription annotationDescription;
            TypeDescription.Generic generic;
            if (!isValidIdentifier(getName().split("\\."))) {
                throw new IllegalStateException("Illegal type name: " + getName() + " for " + this);
            } else if ((getModifiers() & -161312) != 0) {
                throw new IllegalStateException("Illegal modifiers " + getModifiers() + " for " + this);
            } else if (!isPackageType() || getModifiers() == 5632) {
                TypeDescription.Generic superClass2 = getSuperClass();
                if (superClass2 != null) {
                    if (!((Boolean) superClass2.accept(TypeDescription.Generic.Visitor.Validator.SUPER_CLASS)).booleanValue()) {
                        throw new IllegalStateException("Illegal super class " + superClass2 + " for " + this);
                    } else if (!((Boolean) superClass2.accept(TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.INSTANCE)).booleanValue()) {
                        throw new IllegalStateException("Illegal type annotations on super class " + superClass2 + " for " + this);
                    } else if (!superClass2.asErasure().isVisibleTo(this)) {
                        throw new IllegalStateException("Invisible super type " + superClass2 + " for " + this);
                    }
                }
                HashSet hashSet = new HashSet();
                for (TypeDescription.Generic generic2 : getInterfaces()) {
                    if (!((Boolean) generic2.accept(TypeDescription.Generic.Visitor.Validator.INTERFACE)).booleanValue()) {
                        throw new IllegalStateException("Illegal interface " + generic2 + " for " + this);
                    } else if (!((Boolean) generic2.accept(TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.INSTANCE)).booleanValue()) {
                        throw new IllegalStateException("Illegal type annotations on interface " + generic2 + " for " + this);
                    } else if (!hashSet.add(generic2.asErasure())) {
                        throw new IllegalStateException("Already implemented interface " + generic2 + " for " + this);
                    } else if (!generic2.asErasure().isVisibleTo(this)) {
                        throw new IllegalStateException("Invisible interface type " + generic2 + " for " + this);
                    }
                }
                TypeList.Generic typeVariables2 = getTypeVariables();
                if (typeVariables2.isEmpty() || !isAssignableTo((Class<?>) Throwable.class)) {
                    HashSet hashSet2 = new HashSet();
                    Iterator it = typeVariables2.iterator();
                    loop1:
                    while (true) {
                        str = "Illegal interface bound ";
                        String str2 = "Duplicate bound ";
                        String str3 = "Illegal type annotation on '";
                        String str4 = "Illegal type variable name '";
                        String str5 = "Duplicate type variable symbol '";
                        String str6 = "' for ";
                        if (it.hasNext()) {
                            generic = (TypeDescription.Generic) it.next();
                            Iterator it2 = it;
                            String symbol = generic.getSymbol();
                            if (!hashSet2.add(symbol)) {
                                throw new IllegalStateException(str5 + generic + str6 + this);
                            } else if (!isValidIdentifier(symbol)) {
                                throw new IllegalStateException(str4 + generic + str6 + this);
                            } else if (TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.ofFormalTypeVariable(generic)) {
                                HashSet hashSet3 = new HashSet();
                                boolean z = false;
                                for (TypeDescription.Generic generic3 : generic.getUpperBounds()) {
                                    if (!((Boolean) generic3.accept(TypeDescription.Generic.Visitor.Validator.TYPE_VARIABLE)).booleanValue()) {
                                        throw new IllegalStateException("Illegal type variable bound " + generic3 + " of " + generic + " for " + this);
                                    } else if (!((Boolean) generic3.accept(TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.INSTANCE)).booleanValue()) {
                                        throw new IllegalStateException("Illegal type annotations on type variable " + generic3 + " for " + this);
                                    } else if (!hashSet3.add(generic3)) {
                                        throw new IllegalStateException(str2 + generic3 + " of " + generic + " for " + this);
                                    } else if (!z || (!generic3.getSort().isTypeVariable() && generic3.isInterface())) {
                                        z = true;
                                    }
                                }
                                if (z) {
                                    it = it2;
                                } else {
                                    throw new IllegalStateException("Type variable " + generic + " for " + this + " does not define at least one bound");
                                }
                            } else {
                                throw new IllegalStateException(str3 + generic + str6 + this);
                            }
                        } else {
                            TypeDescription enclosingType2 = getEnclosingType();
                            if (enclosingType2 == null || (!enclosingType2.isArray() && !enclosingType2.isPrimitive())) {
                                MethodDescription.InDefinedShape enclosingMethod2 = getEnclosingMethod();
                                if (enclosingMethod2 == null || !enclosingMethod2.isTypeInitializer()) {
                                    String str7 = "Illegal modifiers ";
                                    TypeDescription declaringType2 = getDeclaringType();
                                    if (declaringType2 != null) {
                                        if (declaringType2.isPrimitive() || declaringType2.isArray()) {
                                            throw new IllegalStateException("Cannot define array type or primitive type " + declaringType2 + " as declaring type for " + this);
                                        }
                                    } else if (enclosingType2 == null && enclosingMethod2 == null && (isLocalType() || isAnonymousType())) {
                                        throw new IllegalStateException("Cannot define an anonymous or local class without a declaring type for " + this);
                                    }
                                    HashSet hashSet4 = new HashSet();
                                    Iterator it3 = getDeclaredTypes().iterator();
                                    while (it3.hasNext()) {
                                        Iterator it4 = it3;
                                        TypeDescription typeDescription = (TypeDescription) it3.next();
                                        if (typeDescription.isArray() || typeDescription.isPrimitive()) {
                                            throw new IllegalStateException("Cannot define array type or primitive type " + typeDescription + " + as declared type for " + this);
                                        } else if (hashSet4.add(typeDescription)) {
                                            it3 = it4;
                                        } else {
                                            throw new IllegalStateException("Duplicate definition of declared type " + typeDescription);
                                        }
                                    }
                                    TypeDescription nestHost2 = getNestHost();
                                    TypeDescription typeDescription2 = enclosingType2;
                                    if (nestHost2.equals(this)) {
                                        HashSet hashSet5 = new HashSet();
                                        Iterator it5 = getNestMembers().iterator();
                                        while (it5.hasNext()) {
                                            Iterator it6 = it5;
                                            TypeDescription typeDescription3 = (TypeDescription) it5.next();
                                            if (typeDescription3.isArray() || typeDescription3.isPrimitive()) {
                                                throw new IllegalStateException("Cannot define array type or primitive type " + typeDescription3 + " + as nest member of " + this);
                                            } else if (!typeDescription3.isSamePackage(this)) {
                                                throw new IllegalStateException("Cannot define nest member " + typeDescription3 + " + within different package then " + this);
                                            } else if (hashSet5.add(typeDescription3)) {
                                                it5 = it6;
                                            } else {
                                                throw new IllegalStateException("Duplicate definition of nest member " + typeDescription3);
                                            }
                                        }
                                    } else if (nestHost2.isArray() || nestHost2.isPrimitive()) {
                                        throw new IllegalStateException("Cannot define array type or primitive type " + nestHost2 + " + as nest host for " + this);
                                    } else if (!nestHost2.isSamePackage(this)) {
                                        throw new IllegalStateException("Cannot define nest host " + nestHost2 + " + within different package then " + this);
                                    }
                                    HashSet hashSet6 = new HashSet();
                                    Iterator it7 = getDeclaredAnnotations().iterator();
                                    while (true) {
                                        String str8 = str5;
                                        String str9 = str4;
                                        if (it7.hasNext()) {
                                            annotationDescription = (AnnotationDescription) it7.next();
                                            Iterator it8 = it7;
                                            String str10 = str6;
                                            if (annotationDescription.getElementTypes().contains(ElementType.TYPE) || ((isAnnotation() && annotationDescription.getElementTypes().contains(ElementType.ANNOTATION_TYPE)) || (isPackageType() && annotationDescription.getElementTypes().contains(ElementType.PACKAGE)))) {
                                                if (hashSet6.add(annotationDescription.getAnnotationType())) {
                                                    str5 = str8;
                                                    str4 = str9;
                                                    it7 = it8;
                                                    str6 = str10;
                                                } else {
                                                    throw new IllegalStateException("Duplicate annotation " + annotationDescription + " for " + this);
                                                }
                                            }
                                        } else {
                                            String str11 = str6;
                                            HashSet hashSet7 = new HashSet();
                                            Iterator it9 = getDeclaredFields().iterator();
                                            while (true) {
                                                String str12 = "Illegal type annotations on ";
                                                if (it9.hasNext()) {
                                                    FieldDescription.InDefinedShape inDefinedShape = (FieldDescription.InDefinedShape) it9.next();
                                                    String name2 = inDefinedShape.getName();
                                                    Iterator it10 = it9;
                                                    if (!hashSet7.add(inDefinedShape.asSignatureToken())) {
                                                        throw new IllegalStateException("Duplicate field definition for " + inDefinedShape);
                                                    } else if (!isValidIdentifier(name2)) {
                                                        throw new IllegalStateException("Illegal field name for " + inDefinedShape);
                                                    } else if ((inDefinedShape.getModifiers() & -151776) == 0) {
                                                        TypeDescription.Generic type = inDefinedShape.getType();
                                                        HashSet hashSet8 = hashSet7;
                                                        if (!((Boolean) type.accept(TypeDescription.Generic.Visitor.Validator.FIELD)).booleanValue()) {
                                                            throw new IllegalStateException("Illegal field type " + type + " for " + inDefinedShape);
                                                        } else if (!((Boolean) type.accept(TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.INSTANCE)).booleanValue()) {
                                                            throw new IllegalStateException(str12 + type + " for " + this);
                                                        } else if (inDefinedShape.isSynthetic() || type.asErasure().isVisibleTo(this)) {
                                                            HashSet hashSet9 = new HashSet();
                                                            Iterator it11 = inDefinedShape.getDeclaredAnnotations().iterator();
                                                            while (it11.hasNext()) {
                                                                AnnotationDescription annotationDescription2 = (AnnotationDescription) it11.next();
                                                                Iterator it12 = it11;
                                                                String str13 = str3;
                                                                if (!annotationDescription2.getElementTypes().contains(ElementType.FIELD)) {
                                                                    throw new IllegalStateException("Cannot add " + annotationDescription2 + " on " + inDefinedShape);
                                                                } else if (hashSet9.add(annotationDescription2.getAnnotationType())) {
                                                                    it11 = it12;
                                                                    str3 = str13;
                                                                } else {
                                                                    throw new IllegalStateException("Duplicate annotation " + annotationDescription2 + " for " + inDefinedShape);
                                                                }
                                                            }
                                                            hashSet7 = hashSet8;
                                                            it9 = it10;
                                                        } else {
                                                            throw new IllegalStateException("Invisible field type " + inDefinedShape.getType() + " for " + inDefinedShape);
                                                        }
                                                    } else {
                                                        throw new IllegalStateException("Illegal field modifiers " + inDefinedShape.getModifiers() + " for " + inDefinedShape);
                                                    }
                                                } else {
                                                    String str14 = str3;
                                                    HashSet hashSet10 = new HashSet();
                                                    Iterator it13 = getDeclaredMethods().iterator();
                                                    while (it13.hasNext()) {
                                                        MethodDescription.InDefinedShape inDefinedShape2 = (MethodDescription.InDefinedShape) it13.next();
                                                        if (!hashSet10.add(inDefinedShape2.asSignatureToken())) {
                                                            throw new IllegalStateException("Duplicate method signature for " + inDefinedShape2);
                                                        } else if ((inDefinedShape2.getModifiers() & -7680) == 0) {
                                                            HashSet hashSet11 = new HashSet();
                                                            for (TypeDescription.Generic generic4 : inDefinedShape2.getTypeVariables()) {
                                                                HashSet hashSet12 = hashSet10;
                                                                Iterator it14 = it13;
                                                                String symbol2 = generic4.getSymbol();
                                                                if (!hashSet11.add(symbol2)) {
                                                                    throw new IllegalStateException(str8 + generic4 + str11 + inDefinedShape2);
                                                                } else if (!isValidIdentifier(symbol2)) {
                                                                    throw new IllegalStateException(str9 + generic4 + str11 + inDefinedShape2);
                                                                } else if (TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.ofFormalTypeVariable(generic4)) {
                                                                    HashSet hashSet13 = new HashSet();
                                                                    boolean z2 = false;
                                                                    for (TypeDescription.Generic generic5 : generic4.getUpperBounds()) {
                                                                        HashSet hashSet14 = hashSet11;
                                                                        String str15 = str12;
                                                                        if (!((Boolean) generic5.accept(TypeDescription.Generic.Visitor.Validator.TYPE_VARIABLE)).booleanValue()) {
                                                                            throw new IllegalStateException("Illegal type variable bound " + generic5 + " of " + generic4 + " for " + inDefinedShape2);
                                                                        } else if (!((Boolean) generic5.accept(TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.INSTANCE)).booleanValue()) {
                                                                            throw new IllegalStateException("Illegal type annotations on bound " + generic5 + " of " + generic4 + " for " + this);
                                                                        } else if (!hashSet13.add(generic5)) {
                                                                            throw new IllegalStateException(str2 + generic5 + " of " + generic4 + " for " + inDefinedShape2);
                                                                        } else if (!z2 || (!generic5.getSort().isTypeVariable() && generic5.isInterface())) {
                                                                            str12 = str15;
                                                                            hashSet11 = hashSet14;
                                                                            z2 = true;
                                                                        } else {
                                                                            throw new IllegalStateException(str + generic5 + " of " + generic4 + " for " + inDefinedShape2);
                                                                        }
                                                                    }
                                                                    String str16 = str12;
                                                                    HashSet hashSet15 = hashSet11;
                                                                    if (z2) {
                                                                        it13 = it14;
                                                                        hashSet10 = hashSet12;
                                                                        str12 = str16;
                                                                        hashSet11 = hashSet15;
                                                                    } else {
                                                                        throw new IllegalStateException("Type variable " + generic4 + " for " + inDefinedShape2 + " does not define at least one bound");
                                                                    }
                                                                } else {
                                                                    throw new IllegalStateException(str14 + generic4 + str11 + inDefinedShape2);
                                                                }
                                                            }
                                                            HashSet hashSet16 = hashSet10;
                                                            Iterator it15 = it13;
                                                            String str17 = str12;
                                                            String str18 = str8;
                                                            String str19 = str11;
                                                            String str20 = str14;
                                                            TypeDescription.Generic returnType = inDefinedShape2.getReturnType();
                                                            if (!inDefinedShape2.isTypeInitializer()) {
                                                                if (inDefinedShape2.isConstructor()) {
                                                                    str8 = str18;
                                                                    if (!returnType.represents(Void.TYPE)) {
                                                                        throw new IllegalStateException("A constructor must return void " + inDefinedShape2);
                                                                    } else if (!returnType.getDeclaredAnnotations().isEmpty()) {
                                                                        throw new IllegalStateException("The void non-type must not be annotated for " + inDefinedShape2);
                                                                    }
                                                                } else {
                                                                    str8 = str18;
                                                                    if (!isValidIdentifier(inDefinedShape2.getInternalName())) {
                                                                        throw new IllegalStateException("Illegal method name " + returnType + " for " + inDefinedShape2);
                                                                    } else if (!((Boolean) returnType.accept(TypeDescription.Generic.Visitor.Validator.METHOD_RETURN)).booleanValue()) {
                                                                        throw new IllegalStateException("Illegal return type " + returnType + " for " + inDefinedShape2);
                                                                    } else if (!((Boolean) returnType.accept(TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.INSTANCE)).booleanValue()) {
                                                                        throw new IllegalStateException("Illegal type annotations on return type " + returnType + " for " + inDefinedShape2);
                                                                    } else if (!inDefinedShape2.isSynthetic() && !inDefinedShape2.getReturnType().asErasure().isVisibleTo(this)) {
                                                                        throw new IllegalStateException("Invisible return type " + inDefinedShape2.getReturnType() + " for " + inDefinedShape2);
                                                                    }
                                                                }
                                                                HashSet hashSet17 = new HashSet();
                                                                Iterator it16 = inDefinedShape2.getParameters().iterator();
                                                                while (it16.hasNext()) {
                                                                    Iterator it17 = it16;
                                                                    ParameterDescription.InDefinedShape inDefinedShape3 = (ParameterDescription.InDefinedShape) it16.next();
                                                                    String str21 = str;
                                                                    TypeDescription.Generic type2 = inDefinedShape3.getType();
                                                                    String str22 = str20;
                                                                    if (!((Boolean) type2.accept(TypeDescription.Generic.Visitor.Validator.METHOD_PARAMETER)).booleanValue()) {
                                                                        throw new IllegalStateException("Illegal parameter type of " + inDefinedShape3 + " for " + inDefinedShape2);
                                                                    } else if (!((Boolean) type2.accept(TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.INSTANCE)).booleanValue()) {
                                                                        throw new IllegalStateException("Illegal type annotations on parameter " + inDefinedShape3 + " for " + inDefinedShape2);
                                                                    } else if (inDefinedShape2.isSynthetic() || type2.asErasure().isVisibleTo(this)) {
                                                                        if (inDefinedShape3.isNamed()) {
                                                                            String name3 = inDefinedShape3.getName();
                                                                            if (!hashSet17.add(name3)) {
                                                                                throw new IllegalStateException("Duplicate parameter name of " + inDefinedShape3 + " for " + inDefinedShape2);
                                                                            } else if (!isValidIdentifier(name3)) {
                                                                                throw new IllegalStateException("Illegal parameter name of " + inDefinedShape3 + " for " + inDefinedShape2);
                                                                            }
                                                                        }
                                                                        if (!inDefinedShape3.hasModifiers() || (inDefinedShape3.getModifiers() & -36881) == 0) {
                                                                            HashSet hashSet18 = new HashSet();
                                                                            Iterator it18 = inDefinedShape3.getDeclaredAnnotations().iterator();
                                                                            while (it18.hasNext()) {
                                                                                HashSet hashSet19 = hashSet17;
                                                                                AnnotationDescription annotationDescription3 = (AnnotationDescription) it18.next();
                                                                                Iterator it19 = it18;
                                                                                String str23 = str2;
                                                                                if (!annotationDescription3.getElementTypes().contains(ElementType.PARAMETER)) {
                                                                                    throw new IllegalStateException("Cannot add " + annotationDescription3 + " on " + inDefinedShape3);
                                                                                } else if (hashSet18.add(annotationDescription3.getAnnotationType())) {
                                                                                    it18 = it19;
                                                                                    hashSet17 = hashSet19;
                                                                                    str2 = str23;
                                                                                } else {
                                                                                    throw new IllegalStateException("Duplicate annotation " + annotationDescription3 + " of " + inDefinedShape3 + " for " + inDefinedShape2);
                                                                                }
                                                                            }
                                                                            str = str21;
                                                                            it16 = it17;
                                                                            str20 = str22;
                                                                        } else {
                                                                            throw new IllegalStateException("Illegal modifiers of " + inDefinedShape3 + " for " + inDefinedShape2);
                                                                        }
                                                                    } else {
                                                                        throw new IllegalStateException("Invisible parameter type of " + inDefinedShape3 + " for " + inDefinedShape2);
                                                                    }
                                                                }
                                                                String str24 = str;
                                                                str14 = str20;
                                                                String str25 = str2;
                                                                for (TypeDescription.Generic generic6 : inDefinedShape2.getExceptionTypes()) {
                                                                    if (!((Boolean) generic6.accept(TypeDescription.Generic.Visitor.Validator.EXCEPTION)).booleanValue()) {
                                                                        throw new IllegalStateException("Illegal exception type " + generic6 + " for " + inDefinedShape2);
                                                                    } else if (!((Boolean) generic6.accept(TypeDescription.Generic.Visitor.Validator.ForTypeAnnotations.INSTANCE)).booleanValue()) {
                                                                        throw new IllegalStateException(str17 + generic6 + " for " + inDefinedShape2);
                                                                    } else if (!inDefinedShape2.isSynthetic() && !generic6.asErasure().isVisibleTo(this)) {
                                                                        throw new IllegalStateException("Invisible exception type " + generic6 + " for " + inDefinedShape2);
                                                                    }
                                                                }
                                                                String str26 = str17;
                                                                HashSet hashSet20 = new HashSet();
                                                                Iterator it20 = inDefinedShape2.getDeclaredAnnotations().iterator();
                                                                while (it20.hasNext()) {
                                                                    AnnotationDescription annotationDescription4 = (AnnotationDescription) it20.next();
                                                                    Iterator it21 = it20;
                                                                    if (!annotationDescription4.getElementTypes().contains(inDefinedShape2.isMethod() ? ElementType.METHOD : ElementType.CONSTRUCTOR)) {
                                                                        throw new IllegalStateException("Cannot add " + annotationDescription4 + " on " + inDefinedShape2);
                                                                    } else if (hashSet20.add(annotationDescription4.getAnnotationType())) {
                                                                        it20 = it21;
                                                                    } else {
                                                                        throw new IllegalStateException("Duplicate annotation " + annotationDescription4 + " for " + inDefinedShape2);
                                                                    }
                                                                }
                                                                AnnotationValue<?, ?> defaultValue = inDefinedShape2.getDefaultValue();
                                                                if (defaultValue == null || inDefinedShape2.isDefaultValue(defaultValue)) {
                                                                    TypeDescription.Generic receiverType = inDefinedShape2.getReceiverType();
                                                                    if (receiverType == null || ((Boolean) receiverType.accept(TypeDescription.Generic.Visitor.Validator.RECEIVER)).booleanValue()) {
                                                                        if (inDefinedShape2.isStatic()) {
                                                                            if (receiverType != null) {
                                                                                throw new IllegalStateException("Static method " + inDefinedShape2 + " defines a non-null receiver " + receiverType);
                                                                            }
                                                                        } else if (inDefinedShape2.isConstructor()) {
                                                                            if (receiverType != null) {
                                                                                if (receiverType.asErasure().equals(typeDescription2 == null ? this : typeDescription2)) {
                                                                                }
                                                                            }
                                                                            throw new IllegalStateException("Constructor " + inDefinedShape2 + " defines an illegal receiver " + receiverType);
                                                                        } else if (receiverType == null || !equals(receiverType.asErasure())) {
                                                                            throw new IllegalStateException("Method " + inDefinedShape2 + " defines an illegal receiver " + receiverType);
                                                                        }
                                                                        str12 = str26;
                                                                        str11 = str19;
                                                                        str = str24;
                                                                        it13 = it15;
                                                                        hashSet10 = hashSet16;
                                                                        str2 = str25;
                                                                    } else {
                                                                        throw new IllegalStateException("Illegal receiver type " + receiverType + " for " + inDefinedShape2);
                                                                    }
                                                                } else {
                                                                    throw new IllegalStateException("Illegal default value " + defaultValue + "for " + inDefinedShape2);
                                                                }
                                                            } else {
                                                                throw new IllegalStateException("Illegal explicit declaration of a type initializer by " + this);
                                                            }
                                                        } else {
                                                            throw new IllegalStateException(str7 + inDefinedShape2.getModifiers() + " for " + inDefinedShape2);
                                                        }
                                                    }
                                                    return this;
                                                }
                                            }
                                        }
                                    }
                                    throw new IllegalStateException("Cannot add " + annotationDescription + " on " + this);
                                }
                                throw new IllegalStateException("Cannot enclose type declaration in class initializer " + enclosingMethod2);
                            }
                            throw new IllegalStateException("Cannot define array type or primitive type " + enclosingType2 + " + as enclosing type for " + this);
                        }
                    }
                    throw new IllegalStateException(str + generic3 + " of " + generic + " for " + this);
                }
                throw new IllegalStateException("Cannot define throwable " + this + " to be generic");
            } else {
                throw new IllegalStateException("Illegal modifiers " + getModifiers() + " for package " + this);
            }
        }

        private static boolean isValidIdentifier(String[] strArr) {
            if (strArr.length == 0) {
                return false;
            }
            for (String isValidIdentifier : strArr) {
                if (!isValidIdentifier(isValidIdentifier)) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isValidIdentifier(String str) {
            if (KEYWORDS.contains(str) || str.length() == 0 || !Character.isJavaIdentifierStart(str.charAt(0))) {
                return false;
            }
            if (str.equals(PackageDescription.PACKAGE_CLASS_NAME)) {
                return true;
            }
            for (int i = 1; i < str.length(); i++) {
                if (!Character.isJavaIdentifierPart(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class Frozen extends TypeDescription.AbstractBase.OfSimpleType implements WithFlexibleName {
        private final LoadedTypeInitializer loadedTypeInitializer;
        private final TypeDescription typeDescription;

        protected Frozen(TypeDescription typeDescription2, LoadedTypeInitializer loadedTypeInitializer2) {
            this.typeDescription = typeDescription2;
            this.loadedTypeInitializer = loadedTypeInitializer2;
        }

        public AnnotationList getDeclaredAnnotations() {
            return this.typeDescription.getDeclaredAnnotations();
        }

        public int getModifiers() {
            return this.typeDescription.getModifiers();
        }

        public TypeList.Generic getTypeVariables() {
            return this.typeDescription.getTypeVariables();
        }

        public String getName() {
            return this.typeDescription.getName();
        }

        public TypeDescription.Generic getSuperClass() {
            return this.typeDescription.getSuperClass();
        }

        public TypeList.Generic getInterfaces() {
            return this.typeDescription.getInterfaces();
        }

        public FieldList<FieldDescription.InDefinedShape> getDeclaredFields() {
            return this.typeDescription.getDeclaredFields();
        }

        public MethodList<MethodDescription.InDefinedShape> getDeclaredMethods() {
            return this.typeDescription.getDeclaredMethods();
        }

        public boolean isAnonymousType() {
            return this.typeDescription.isAnonymousType();
        }

        public boolean isLocalType() {
            return this.typeDescription.isLocalType();
        }

        public PackageDescription getPackage() {
            return this.typeDescription.getPackage();
        }

        public TypeDescription getEnclosingType() {
            return this.typeDescription.getEnclosingType();
        }

        public TypeDescription getDeclaringType() {
            return this.typeDescription.getDeclaringType();
        }

        public TypeList getDeclaredTypes() {
            return this.typeDescription.getDeclaredTypes();
        }

        public MethodDescription.InDefinedShape getEnclosingMethod() {
            return this.typeDescription.getEnclosingMethod();
        }

        public String getGenericSignature() {
            return this.typeDescription.getGenericSignature();
        }

        public int getActualModifiers(boolean z) {
            return this.typeDescription.getActualModifiers(z);
        }

        public TypeDescription getNestHost() {
            return this.typeDescription.getNestHost();
        }

        public TypeList getNestMembers() {
            return this.typeDescription.getNestMembers();
        }

        public WithFlexibleName withField(FieldDescription.Token token) {
            throw new IllegalStateException("Cannot define field for frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withMethod(MethodDescription.Token token) {
            throw new IllegalStateException("Cannot define method for frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withModifiers(int i) {
            throw new IllegalStateException("Cannot change modifiers for frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withInterfaces(TypeList.Generic generic) {
            throw new IllegalStateException("Cannot add interfaces for frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withTypeVariable(TypeVariableToken typeVariableToken) {
            throw new IllegalStateException("Cannot define type variable for frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withAnnotations(List<? extends AnnotationDescription> list) {
            throw new IllegalStateException("Cannot add annotation to frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withNestHost(TypeDescription typeDescription2) {
            throw new IllegalStateException("Cannot set nest host of frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withNestMembers(TypeList typeList) {
            throw new IllegalStateException("Cannot add nest members to frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withEnclosingType(TypeDescription typeDescription2) {
            throw new IllegalStateException("Cannot set enclosing type of frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withEnclosingMethod(MethodDescription.InDefinedShape inDefinedShape) {
            throw new IllegalStateException("Cannot set enclosing method of frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withDeclaringType(TypeDescription typeDescription2) {
            throw new IllegalStateException("Cannot add declaring type to frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withDeclaredTypes(TypeList typeList) {
            throw new IllegalStateException("Cannot add declared types to frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withLocalClass(boolean z) {
            throw new IllegalStateException("Cannot define local class state to frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withAnonymousClass(boolean z) {
            throw new IllegalStateException("Cannot define anonymous class state to frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withInitializer(LoadedTypeInitializer loadedTypeInitializer2) {
            return new Frozen(this.typeDescription, new LoadedTypeInitializer.Compound(this.loadedTypeInitializer, loadedTypeInitializer2));
        }

        public WithFlexibleName withInitializer(ByteCodeAppender byteCodeAppender) {
            throw new IllegalStateException("Cannot add initializer to frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withName(String str) {
            throw new IllegalStateException("Cannot change name of frozen type: " + this.typeDescription);
        }

        public WithFlexibleName withTypeVariables(ElementMatcher<? super TypeDescription.Generic> elementMatcher, Transformer<TypeVariableToken> transformer) {
            throw new IllegalStateException("Cannot add type variables of frozen type: " + this.typeDescription);
        }

        public LoadedTypeInitializer getLoadedTypeInitializer() {
            return this.loadedTypeInitializer;
        }

        public TypeInitializer getTypeInitializer() {
            return TypeInitializer.None.INSTANCE;
        }

        public TypeDescription validated() {
            return this.typeDescription;
        }
    }
}
