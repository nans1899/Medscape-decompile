package org.mockito.internal.util.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.MockUtil;

public class FieldInitializer {
    private final Field field;
    private final Object fieldOwner;
    private final ConstructorInstantiator instantiator;

    public interface ConstructorArgumentResolver {
        Object[] resolveTypeInstances(Class<?>... clsArr);
    }

    private interface ConstructorInstantiator {
        FieldInitializationReport instantiate();
    }

    public FieldInitializer(Object obj, Field field2) {
        this(obj, field2, (ConstructorInstantiator) new NoArgConstructorInstantiator(obj, field2));
    }

    public FieldInitializer(Object obj, Field field2, ConstructorArgumentResolver constructorArgumentResolver) {
        this(obj, field2, (ConstructorInstantiator) new ParameterizedConstructorInstantiator(obj, field2, constructorArgumentResolver));
    }

    private FieldInitializer(Object obj, Field field2, ConstructorInstantiator constructorInstantiator) {
        if (new FieldReader(obj, field2).isNull()) {
            checkNotLocal(field2);
            checkNotInner(field2);
            checkNotInterface(field2);
            checkNotEnum(field2);
            checkNotAbstract(field2);
        }
        this.fieldOwner = obj;
        this.field = field2;
        this.instantiator = constructorInstantiator;
    }

    public FieldInitializationReport initialize() {
        AccessibilityChanger accessibilityChanger = new AccessibilityChanger();
        accessibilityChanger.enableAccess(this.field);
        try {
            FieldInitializationReport acquireFieldInstance = acquireFieldInstance();
            accessibilityChanger.safelyDisableAccess(this.field);
            return acquireFieldInstance;
        } catch (IllegalAccessException e) {
            throw new MockitoException("Problems initializing field '" + this.field.getName() + "' of type '" + this.field.getType().getSimpleName() + "'", e);
        } catch (Throwable th) {
            accessibilityChanger.safelyDisableAccess(this.field);
            throw th;
        }
    }

    private void checkNotLocal(Field field2) {
        if (field2.getType().isLocalClass()) {
            throw new MockitoException("the type '" + field2.getType().getSimpleName() + "' is a local class.");
        }
    }

    private void checkNotInner(Field field2) {
        Class<?> type = field2.getType();
        if (type.isMemberClass() && !Modifier.isStatic(type.getModifiers())) {
            throw new MockitoException("the type '" + type.getSimpleName() + "' is an inner non static class.");
        }
    }

    private void checkNotInterface(Field field2) {
        if (field2.getType().isInterface()) {
            throw new MockitoException("the type '" + field2.getType().getSimpleName() + "' is an interface.");
        }
    }

    private void checkNotAbstract(Field field2) {
        if (Modifier.isAbstract(field2.getType().getModifiers())) {
            throw new MockitoException("the type '" + field2.getType().getSimpleName() + "' is an abstract class.");
        }
    }

    private void checkNotEnum(Field field2) {
        if (field2.getType().isEnum()) {
            throw new MockitoException("the type '" + field2.getType().getSimpleName() + "' is an enum.");
        }
    }

    private FieldInitializationReport acquireFieldInstance() throws IllegalAccessException {
        Object obj = this.field.get(this.fieldOwner);
        if (obj != null) {
            return new FieldInitializationReport(obj, false, false);
        }
        return this.instantiator.instantiate();
    }

    static class NoArgConstructorInstantiator implements ConstructorInstantiator {
        private final Field field;
        private final Object testClass;

        NoArgConstructorInstantiator(Object obj, Field field2) {
            this.testClass = obj;
            this.field = field2;
        }

        public FieldInitializationReport instantiate() {
            AccessibilityChanger accessibilityChanger = new AccessibilityChanger();
            Constructor<?> constructor = null;
            try {
                constructor = this.field.getType().getDeclaredConstructor(new Class[0]);
                accessibilityChanger.enableAccess(constructor);
                FieldSetter.setField(this.testClass, this.field, constructor.newInstance(new Object[0]));
                FieldInitializationReport fieldInitializationReport = new FieldInitializationReport(this.field.get(this.testClass), true, false);
                if (constructor != null) {
                    accessibilityChanger.safelyDisableAccess(constructor);
                }
                return fieldInitializationReport;
            } catch (NoSuchMethodException e) {
                throw new MockitoException("the type '" + this.field.getType().getSimpleName() + "' has no default constructor", e);
            } catch (InvocationTargetException e2) {
                throw new MockitoException("the default constructor of type '" + this.field.getType().getSimpleName() + "' has raised an exception (see the stack trace for cause): " + e2.getTargetException().toString(), e2);
            } catch (InstantiationException e3) {
                throw new MockitoException("InstantiationException (see the stack trace for cause): " + e3.toString(), e3);
            } catch (IllegalAccessException e4) {
                throw new MockitoException("IllegalAccessException (see the stack trace for cause): " + e4.toString(), e4);
            } catch (Throwable th) {
                if (constructor != null) {
                    accessibilityChanger.safelyDisableAccess(constructor);
                }
                throw th;
            }
        }
    }

    static class ParameterizedConstructorInstantiator implements ConstructorInstantiator {
        private final ConstructorArgumentResolver argResolver;
        private final Comparator<Constructor<?>> byParameterNumber = new Comparator<Constructor<?>>() {
            public int compare(Constructor<?> constructor, Constructor<?> constructor2) {
                int length = constructor2.getParameterTypes().length - constructor.getParameterTypes().length;
                if (length != 0) {
                    return length;
                }
                return countMockableParams(constructor2) - countMockableParams(constructor);
            }

            private int countMockableParams(Constructor<?> constructor) {
                int i = 0;
                for (Class typeMockabilityOf : constructor.getParameterTypes()) {
                    if (MockUtil.typeMockabilityOf(typeMockabilityOf).mockable()) {
                        i++;
                    }
                }
                return i;
            }
        };
        private final Field field;
        private final Object testClass;

        ParameterizedConstructorInstantiator(Object obj, Field field2, ConstructorArgumentResolver constructorArgumentResolver) {
            this.testClass = obj;
            this.field = field2;
            this.argResolver = constructorArgumentResolver;
        }

        public FieldInitializationReport instantiate() {
            AccessibilityChanger accessibilityChanger = new AccessibilityChanger();
            Constructor<?> constructor = null;
            try {
                constructor = biggestConstructor(this.field.getType());
                accessibilityChanger.enableAccess(constructor);
                FieldSetter.setField(this.testClass, this.field, constructor.newInstance(this.argResolver.resolveTypeInstances(constructor.getParameterTypes())));
                FieldInitializationReport fieldInitializationReport = new FieldInitializationReport(this.field.get(this.testClass), false, true);
                if (constructor != null) {
                    accessibilityChanger.safelyDisableAccess(constructor);
                }
                return fieldInitializationReport;
            } catch (IllegalArgumentException e) {
                throw new MockitoException("internal error : argResolver provided incorrect types for constructor " + constructor + " of type " + this.field.getType().getSimpleName(), e);
            } catch (InvocationTargetException e2) {
                throw new MockitoException("the constructor of type '" + this.field.getType().getSimpleName() + "' has raised an exception (see the stack trace for cause): " + e2.getTargetException().toString(), e2);
            } catch (InstantiationException e3) {
                throw new MockitoException("InstantiationException (see the stack trace for cause): " + e3.toString(), e3);
            } catch (IllegalAccessException e4) {
                throw new MockitoException("IllegalAccessException (see the stack trace for cause): " + e4.toString(), e4);
            } catch (Throwable th) {
                if (constructor != null) {
                    accessibilityChanger.safelyDisableAccess(constructor);
                }
                throw th;
            }
        }

        private void checkParameterized(Constructor<?> constructor, Field field2) {
            if (constructor.getParameterTypes().length == 0) {
                throw new MockitoException("the field " + field2.getName() + " of type " + field2.getType() + " has no parameterized constructor");
            }
        }

        private Constructor<?> biggestConstructor(Class<?> cls) {
            List asList = Arrays.asList(cls.getDeclaredConstructors());
            Collections.sort(asList, this.byParameterNumber);
            Constructor<?> constructor = (Constructor) asList.get(0);
            checkParameterized(constructor, this.field);
            return constructor;
        }
    }
}
