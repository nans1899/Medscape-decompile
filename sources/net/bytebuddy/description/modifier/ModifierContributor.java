package net.bytebuddy.description.modifier;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;

public interface ModifierContributor {
    public static final int EMPTY_MASK = 0;

    public interface ForField extends ModifierContributor {
        public static final int MASK = 151775;
    }

    public interface ForMethod extends ModifierContributor {
        public static final int MASK = 7679;
    }

    public interface ForParameter extends ModifierContributor {
        public static final int MASK = 36880;
    }

    public interface ForType extends ModifierContributor {
        public static final int MASK = 161311;
    }

    int getMask();

    int getRange();

    boolean isDefault();

    @HashCodeAndEqualsPlugin.Enhance
    public static class Resolver<T extends ModifierContributor> {
        private final Collection<? extends T> modifierContributors;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.modifierContributors.equals(((Resolver) obj).modifierContributors);
        }

        public int hashCode() {
            return 527 + this.modifierContributors.hashCode();
        }

        protected Resolver(Collection<? extends T> collection) {
            this.modifierContributors = collection;
        }

        public static Resolver<ForType> of(ForType... forTypeArr) {
            return of(Arrays.asList(forTypeArr));
        }

        public static Resolver<ForField> of(ForField... forFieldArr) {
            return of(Arrays.asList(forFieldArr));
        }

        public static Resolver<ForMethod> of(ForMethod... forMethodArr) {
            return of(Arrays.asList(forMethodArr));
        }

        public static Resolver<ForParameter> of(ForParameter... forParameterArr) {
            return of(Arrays.asList(forParameterArr));
        }

        public static <S extends ModifierContributor> Resolver<S> of(Collection<? extends S> collection) {
            return new Resolver<>(collection);
        }

        public int resolve() {
            return resolve(0);
        }

        public int resolve(int i) {
            Iterator<? extends T> it = this.modifierContributors.iterator();
            while (it.hasNext()) {
                ModifierContributor modifierContributor = (ModifierContributor) it.next();
                i = (i & (~modifierContributor.getRange())) | modifierContributor.getMask();
            }
            return i;
        }
    }
}
