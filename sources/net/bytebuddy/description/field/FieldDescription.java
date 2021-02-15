package net.bytebuddy.description.field;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;
import net.bytebuddy.build.CachedReturnPlugin;
import net.bytebuddy.description.ByteCodeElement;
import net.bytebuddy.description.ModifierReviewable;
import net.bytebuddy.description.NamedElement;
import net.bytebuddy.description.annotation.AnnotationDescription;
import net.bytebuddy.description.annotation.AnnotationList;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.description.type.TypeVariableToken;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import net.bytebuddy.jar.asm.signature.SignatureWriter;
import net.bytebuddy.matcher.ElementMatcher;

public interface FieldDescription extends ByteCodeElement, ModifierReviewable.ForFieldDescription, NamedElement.WithGenericName, ByteCodeElement.TypeDependant<InDefinedShape, Token> {
    public static final Object NO_DEFAULT_VALUE = null;

    public interface InDefinedShape extends FieldDescription {

        public static abstract class AbstractBase extends AbstractBase implements InDefinedShape {
            public InDefinedShape asDefined() {
                return this;
            }
        }

        TypeDescription getDeclaringType();
    }

    public interface InGenericShape extends FieldDescription {
        TypeDescription.Generic getDeclaringType();
    }

    SignatureToken asSignatureToken();

    int getActualModifiers();

    TypeDescription.Generic getType();

    public static abstract class AbstractBase extends ModifierReviewable.AbstractBase implements FieldDescription {
        public String getInternalName() {
            return getName();
        }

        public String getActualName() {
            return getName();
        }

        public String getDescriptor() {
            return getType().asErasure().getDescriptor();
        }

        public String getGenericSignature() {
            TypeDescription.Generic type = getType();
            try {
                if (type.getSort().isNonGeneric()) {
                    return NON_GENERIC_SIGNATURE;
                }
                return ((SignatureVisitor) type.accept(new TypeDescription.Generic.Visitor.ForSignatureVisitor(new SignatureWriter()))).toString();
            } catch (GenericSignatureFormatError unused) {
                return NON_GENERIC_SIGNATURE;
            }
        }

        public boolean isVisibleTo(TypeDescription typeDescription) {
            return getDeclaringType().asErasure().isVisibleTo(typeDescription) && (isPublic() || typeDescription.equals(getDeclaringType().asErasure()) || ((isProtected() && getDeclaringType().asErasure().isAssignableFrom(typeDescription)) || (!isPrivate() && typeDescription.isSamePackage(getDeclaringType().asErasure()))));
        }

        public boolean isAccessibleTo(TypeDescription typeDescription) {
            return isPublic() || typeDescription.equals(getDeclaringType().asErasure()) || (!isPrivate() && typeDescription.isSamePackage(getDeclaringType().asErasure()));
        }

        public int getActualModifiers() {
            return getModifiers() | (getDeclaredAnnotations().isAnnotationPresent((Class<? extends Annotation>) Deprecated.class) ? 131072 : 0);
        }

        public Token asToken(ElementMatcher<? super TypeDescription> elementMatcher) {
            return new Token(getName(), getModifiers(), (TypeDescription.Generic) getType().accept(new TypeDescription.Generic.Visitor.Substitutor.ForDetachment(elementMatcher)), getDeclaredAnnotations());
        }

        public SignatureToken asSignatureToken() {
            return new SignatureToken(getInternalName(), getType().asErasure());
        }

        public int hashCode() {
            return getDeclaringType().hashCode() + ((getName().hashCode() + 17) * 31);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FieldDescription)) {
                return false;
            }
            FieldDescription fieldDescription = (FieldDescription) obj;
            if (!getName().equals(fieldDescription.getName()) || !getDeclaringType().equals(fieldDescription.getDeclaringType())) {
                return false;
            }
            return true;
        }

        public String toGenericString() {
            StringBuilder sb = new StringBuilder();
            if (getModifiers() != 0) {
                sb.append(Modifier.toString(getModifiers()));
                sb.append(' ');
            }
            sb.append(getType().getActualName());
            sb.append(' ');
            sb.append(getDeclaringType().asErasure().getActualName());
            sb.append('.');
            sb.append(getName());
            return sb.toString();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (getModifiers() != 0) {
                sb.append(Modifier.toString(getModifiers()));
                sb.append(' ');
            }
            sb.append(getType().asErasure().getActualName());
            sb.append(' ');
            sb.append(getDeclaringType().asErasure().getActualName());
            sb.append('.');
            sb.append(getName());
            return sb.toString();
        }
    }

    public static class ForLoadedField extends InDefinedShape.AbstractBase {
        private transient /* synthetic */ AnnotationList declaredAnnotations;
        private final Field field;

        public ForLoadedField(Field field2) {
            this.field = field2;
        }

        public TypeDescription.Generic getType() {
            if (TypeDescription.AbstractBase.RAW_TYPES) {
                return TypeDescription.Generic.OfNonGenericType.ForLoadedType.of(this.field.getType());
            }
            return new TypeDescription.Generic.LazyProjection.ForLoadedFieldType(this.field);
        }

        @CachedReturnPlugin.Enhance("declaredAnnotations")
        public AnnotationList getDeclaredAnnotations() {
            AnnotationList.ForLoadedAnnotations forLoadedAnnotations = this.declaredAnnotations != null ? null : new AnnotationList.ForLoadedAnnotations(this.field.getDeclaredAnnotations());
            if (forLoadedAnnotations == null) {
                return this.declaredAnnotations;
            }
            this.declaredAnnotations = forLoadedAnnotations;
            return forLoadedAnnotations;
        }

        public String getName() {
            return this.field.getName();
        }

        public TypeDescription getDeclaringType() {
            return TypeDescription.ForLoadedType.of(this.field.getDeclaringClass());
        }

        public int getModifiers() {
            return this.field.getModifiers();
        }

        public boolean isSynthetic() {
            return this.field.isSynthetic();
        }
    }

    public static class Latent extends InDefinedShape.AbstractBase {
        private final List<? extends AnnotationDescription> declaredAnnotations;
        private final TypeDescription declaringType;
        private final String fieldName;
        private final TypeDescription.Generic fieldType;
        private final int modifiers;

        public Latent(TypeDescription typeDescription, Token token) {
            this(typeDescription, token.getName(), token.getModifiers(), token.getType(), token.getAnnotations());
        }

        public Latent(TypeDescription typeDescription, String str, int i, TypeDescription.Generic generic, List<? extends AnnotationDescription> list) {
            this.declaringType = typeDescription;
            this.fieldName = str;
            this.modifiers = i;
            this.fieldType = generic;
            this.declaredAnnotations = list;
        }

        public TypeDescription.Generic getType() {
            return (TypeDescription.Generic) this.fieldType.accept(TypeDescription.Generic.Visitor.Substitutor.ForAttachment.of((FieldDescription) this));
        }

        public AnnotationList getDeclaredAnnotations() {
            return new AnnotationList.Explicit(this.declaredAnnotations);
        }

        public String getName() {
            return this.fieldName;
        }

        public TypeDescription getDeclaringType() {
            return this.declaringType;
        }

        public int getModifiers() {
            return this.modifiers;
        }
    }

    public static class TypeSubstituting extends AbstractBase implements InGenericShape {
        private final TypeDescription.Generic declaringType;
        private final FieldDescription fieldDescription;
        private final TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor;

        public TypeSubstituting(TypeDescription.Generic generic, FieldDescription fieldDescription2, TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor2) {
            this.declaringType = generic;
            this.fieldDescription = fieldDescription2;
            this.visitor = visitor2;
        }

        public TypeDescription.Generic getType() {
            return (TypeDescription.Generic) this.fieldDescription.getType().accept(this.visitor);
        }

        public AnnotationList getDeclaredAnnotations() {
            return this.fieldDescription.getDeclaredAnnotations();
        }

        public TypeDescription.Generic getDeclaringType() {
            return this.declaringType;
        }

        public int getModifiers() {
            return this.fieldDescription.getModifiers();
        }

        public String getName() {
            return this.fieldDescription.getName();
        }

        public InDefinedShape asDefined() {
            return (InDefinedShape) this.fieldDescription.asDefined();
        }
    }

    public static class Token implements ByteCodeElement.Token<Token> {
        private final List<? extends AnnotationDescription> annotations;
        private final int modifiers;
        private final String name;
        private final TypeDescription.Generic type;

        public Token(String str, int i, TypeDescription.Generic generic) {
            this(str, i, generic, Collections.emptyList());
        }

        public Token(String str, int i, TypeDescription.Generic generic, List<? extends AnnotationDescription> list) {
            this.name = str;
            this.modifiers = i;
            this.type = generic;
            this.annotations = list;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription.Generic getType() {
            return this.type;
        }

        public int getModifiers() {
            return this.modifiers;
        }

        public AnnotationList getAnnotations() {
            return new AnnotationList.Explicit(this.annotations);
        }

        public Token accept(TypeDescription.Generic.Visitor<? extends TypeDescription.Generic> visitor) {
            return new Token(this.name, this.modifiers, (TypeDescription.Generic) this.type.accept(visitor), this.annotations);
        }

        public SignatureToken asSignatureToken(TypeDescription typeDescription) {
            return new SignatureToken(this.name, (TypeDescription) this.type.accept(new TypeDescription.Generic.Visitor.Reducing(typeDescription, new TypeVariableToken[0])));
        }

        public int hashCode() {
            return (((((this.name.hashCode() * 31) + this.modifiers) * 31) + this.type.hashCode()) * 31) + this.annotations.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Token token = (Token) obj;
            if (this.modifiers != token.modifiers || !this.name.equals(token.name) || !this.type.equals(token.type) || !this.annotations.equals(token.annotations)) {
                return false;
            }
            return true;
        }
    }

    public static class SignatureToken {
        private final String name;
        private final TypeDescription type;

        public SignatureToken(String str, TypeDescription typeDescription) {
            this.name = str;
            this.type = typeDescription;
        }

        public String getName() {
            return this.name;
        }

        public TypeDescription getType() {
            return this.type;
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.type.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SignatureToken)) {
                return false;
            }
            SignatureToken signatureToken = (SignatureToken) obj;
            if (!this.name.equals(signatureToken.name) || !this.type.equals(signatureToken.type)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return this.type + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.name;
        }
    }
}
