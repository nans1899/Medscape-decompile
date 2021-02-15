package org.apache.commons.io.function;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.function.IOFunction;

@FunctionalInterface
public interface IOFunction<T, R> {
    IOConsumer<T> andThen(Consumer<? super R> consumer);

    IOConsumer<T> andThen(IOConsumer<? super R> iOConsumer);

    <V> IOFunction<T, V> andThen(Function<? super R, ? extends V> function);

    <V> IOFunction<T, V> andThen(IOFunction<? super R, ? extends V> iOFunction);

    R apply(T t) throws IOException;

    <V> IOFunction<V, R> compose(Function<? super V, ? extends T> function);

    <V> IOFunction<V, R> compose(IOFunction<? super V, ? extends T> iOFunction);

    IOSupplier<R> compose(Supplier<? extends T> supplier);

    IOSupplier<R> compose(IOSupplier<? extends T> iOSupplier);

    /* renamed from: org.apache.commons.io.function.IOFunction$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static /* synthetic */ Object lambda$identity$8(Object obj) throws IOException {
            return obj;
        }

        public static <V> IOFunction $default$compose(IOFunction _this, IOFunction iOFunction) {
            Objects.requireNonNull(iOFunction);
            return new IOFunction(iOFunction) {
                public final /* synthetic */ IOFunction f$1;

                {
                    this.f$1 = r2;
                }

                public /* synthetic */ IOConsumer<T> andThen(Consumer<? super R> consumer) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (Consumer) consumer);
                }

                public /* synthetic */ IOConsumer<T> andThen(IOConsumer<? super R> iOConsumer) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (IOConsumer) iOConsumer);
                }

                public /* synthetic */ <V> IOFunction<T, V> andThen(Function<? super R, ? extends V> function) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (Function) function);
                }

                public /* synthetic */ <V> IOFunction<T, V> andThen(IOFunction<? super R, ? extends V> iOFunction) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (IOFunction) iOFunction);
                }

                public final Object apply(Object obj) {
                    return IOFunction.this.apply(this.f$1.apply(obj));
                }

                public /* synthetic */ <V> IOFunction<V, R> compose(Function<? super V, ? extends T> function) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (Function) function);
                }

                public /* synthetic */ <V> IOFunction<V, R> compose(IOFunction<? super V, ? extends T> iOFunction) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (IOFunction) iOFunction);
                }

                public /* synthetic */ IOSupplier<R> compose(Supplier<? extends T> supplier) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (Supplier) supplier);
                }

                public /* synthetic */ IOSupplier<R> compose(IOSupplier<? extends T> iOSupplier) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (IOSupplier) iOSupplier);
                }
            };
        }

        public static <V> IOFunction $default$compose(IOFunction _this, Function function) {
            Objects.requireNonNull(function);
            return new IOFunction(function) {
                public final /* synthetic */ Function f$1;

                {
                    this.f$1 = r2;
                }

                public /* synthetic */ IOConsumer<T> andThen(Consumer<? super R> consumer) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (Consumer) consumer);
                }

                public /* synthetic */ IOConsumer<T> andThen(IOConsumer<? super R> iOConsumer) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (IOConsumer) iOConsumer);
                }

                public /* synthetic */ <V> IOFunction<T, V> andThen(Function<? super R, ? extends V> function) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (Function) function);
                }

                public /* synthetic */ <V> IOFunction<T, V> andThen(IOFunction<? super R, ? extends V> iOFunction) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (IOFunction) iOFunction);
                }

                public final Object apply(Object obj) {
                    return IOFunction.this.apply(this.f$1.apply(obj));
                }

                public /* synthetic */ <V> IOFunction<V, R> compose(Function<? super V, ? extends T> function) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (Function) function);
                }

                public /* synthetic */ <V> IOFunction<V, R> compose(IOFunction<? super V, ? extends T> iOFunction) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (IOFunction) iOFunction);
                }

                public /* synthetic */ IOSupplier<R> compose(Supplier<? extends T> supplier) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (Supplier) supplier);
                }

                public /* synthetic */ IOSupplier<R> compose(IOSupplier<? extends T> iOSupplier) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (IOSupplier) iOSupplier);
                }
            };
        }

        public static IOSupplier $default$compose(IOFunction _this, IOSupplier iOSupplier) {
            Objects.requireNonNull(iOSupplier);
            return new IOSupplier(iOSupplier) {
                public final /* synthetic */ IOSupplier f$1;

                {
                    this.f$1 = r2;
                }

                public final Object get() {
                    return IOFunction.this.apply(this.f$1.get());
                }
            };
        }

        public static IOSupplier $default$compose(IOFunction _this, Supplier supplier) {
            Objects.requireNonNull(supplier);
            return new IOSupplier(supplier) {
                public final /* synthetic */ Supplier f$1;

                {
                    this.f$1 = r2;
                }

                public final Object get() {
                    return IOFunction.this.apply(this.f$1.get());
                }
            };
        }

        public static <V> IOFunction $default$andThen(IOFunction _this, IOFunction iOFunction) {
            Objects.requireNonNull(iOFunction);
            return new IOFunction(iOFunction) {
                public final /* synthetic */ IOFunction f$1;

                {
                    this.f$1 = r2;
                }

                public /* synthetic */ IOConsumer<T> andThen(Consumer<? super R> consumer) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (Consumer) consumer);
                }

                public /* synthetic */ IOConsumer<T> andThen(IOConsumer<? super R> iOConsumer) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (IOConsumer) iOConsumer);
                }

                public /* synthetic */ <V> IOFunction<T, V> andThen(Function<? super R, ? extends V> function) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (Function) function);
                }

                public /* synthetic */ <V> IOFunction<T, V> andThen(IOFunction<? super R, ? extends V> iOFunction) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (IOFunction) iOFunction);
                }

                public final Object apply(Object obj) {
                    return this.f$1.apply(IOFunction.this.apply(obj));
                }

                public /* synthetic */ <V> IOFunction<V, R> compose(Function<? super V, ? extends T> function) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (Function) function);
                }

                public /* synthetic */ <V> IOFunction<V, R> compose(IOFunction<? super V, ? extends T> iOFunction) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (IOFunction) iOFunction);
                }

                public /* synthetic */ IOSupplier<R> compose(Supplier<? extends T> supplier) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (Supplier) supplier);
                }

                public /* synthetic */ IOSupplier<R> compose(IOSupplier<? extends T> iOSupplier) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (IOSupplier) iOSupplier);
                }
            };
        }

        public static <V> IOFunction $default$andThen(IOFunction _this, Function function) {
            Objects.requireNonNull(function);
            return new IOFunction(function) {
                public final /* synthetic */ Function f$1;

                {
                    this.f$1 = r2;
                }

                public /* synthetic */ IOConsumer<T> andThen(Consumer<? super R> consumer) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (Consumer) consumer);
                }

                public /* synthetic */ IOConsumer<T> andThen(IOConsumer<? super R> iOConsumer) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (IOConsumer) iOConsumer);
                }

                public /* synthetic */ <V> IOFunction<T, V> andThen(Function<? super R, ? extends V> function) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (Function) function);
                }

                public /* synthetic */ <V> IOFunction<T, V> andThen(IOFunction<? super R, ? extends V> iOFunction) {
                    return IOFunction.CC.$default$andThen((IOFunction) this, (IOFunction) iOFunction);
                }

                public final Object apply(Object obj) {
                    return this.f$1.apply(IOFunction.this.apply(obj));
                }

                public /* synthetic */ <V> IOFunction<V, R> compose(Function<? super V, ? extends T> function) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (Function) function);
                }

                public /* synthetic */ <V> IOFunction<V, R> compose(IOFunction<? super V, ? extends T> iOFunction) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (IOFunction) iOFunction);
                }

                public /* synthetic */ IOSupplier<R> compose(Supplier<? extends T> supplier) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (Supplier) supplier);
                }

                public /* synthetic */ IOSupplier<R> compose(IOSupplier<? extends T> iOSupplier) {
                    return IOFunction.CC.$default$compose((IOFunction) this, (IOSupplier) iOSupplier);
                }
            };
        }

        public static IOConsumer $default$andThen(IOFunction _this, IOConsumer iOConsumer) {
            Objects.requireNonNull(iOConsumer);
            return new IOConsumer(iOConsumer) {
                public final /* synthetic */ IOConsumer f$1;

                {
                    this.f$1 = r2;
                }

                public final void accept(Object obj) {
                    this.f$1.accept(IOFunction.this.apply(obj));
                }

                public /* synthetic */ IOConsumer<T> andThen(IOConsumer<? super T> iOConsumer) {
                    return IOConsumer.CC.$default$andThen(this, iOConsumer);
                }
            };
        }

        public static IOConsumer $default$andThen(IOFunction _this, Consumer consumer) {
            Objects.requireNonNull(consumer);
            return new IOConsumer(consumer) {
                public final /* synthetic */ Consumer f$1;

                {
                    this.f$1 = r2;
                }

                public final void accept(Object obj) {
                    this.f$1.accept(IOFunction.this.apply(obj));
                }

                public /* synthetic */ IOConsumer<T> andThen(IOConsumer<? super T> iOConsumer) {
                    return IOConsumer.CC.$default$andThen(this, iOConsumer);
                }
            };
        }

        public static <T> IOFunction<T, T> identity() {
            return $$Lambda$IOFunction$d10gCIcroQgnvHrOtYRzZZZep8.INSTANCE;
        }
    }
}
