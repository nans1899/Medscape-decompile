package net.bytebuddy.implementation;

import net.bytebuddy.description.field.FieldDescription;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.Implementation;

public interface MethodAccessorFactory {
    MethodDescription.InDefinedShape registerAccessorFor(Implementation.SpecialMethodInvocation specialMethodInvocation, AccessType accessType);

    MethodDescription.InDefinedShape registerGetterFor(FieldDescription fieldDescription, AccessType accessType);

    MethodDescription.InDefinedShape registerSetterFor(FieldDescription fieldDescription, AccessType accessType);

    public enum AccessType {
        PUBLIC(Visibility.PUBLIC),
        DEFAULT(Visibility.PACKAGE_PRIVATE);
        
        private final Visibility visibility;

        private AccessType(Visibility visibility2) {
            this.visibility = visibility2;
        }

        public Visibility getVisibility() {
            return this.visibility;
        }
    }

    public enum Illegal implements MethodAccessorFactory {
        INSTANCE;

        public MethodDescription.InDefinedShape registerAccessorFor(Implementation.SpecialMethodInvocation specialMethodInvocation, AccessType accessType) {
            throw new IllegalStateException("It is illegal to register an accessor for this type");
        }

        public MethodDescription.InDefinedShape registerGetterFor(FieldDescription fieldDescription, AccessType accessType) {
            throw new IllegalStateException("It is illegal to register a field getter for this type");
        }

        public MethodDescription.InDefinedShape registerSetterFor(FieldDescription fieldDescription, AccessType accessType) {
            throw new IllegalStateException("It is illegal to register a field setter for this type");
        }
    }
}
