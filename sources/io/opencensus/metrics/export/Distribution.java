package io.opencensus.metrics.export;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import io.opencensus.common.Function;
import io.opencensus.internal.Utils;
import io.opencensus.metrics.data.Exemplar;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public abstract class Distribution {
    @Nullable
    public abstract BucketOptions getBucketOptions();

    public abstract List<Bucket> getBuckets();

    public abstract long getCount();

    public abstract double getSum();

    public abstract double getSumOfSquaredDeviations();

    Distribution() {
    }

    public static Distribution create(long j, double d, double d2, BucketOptions bucketOptions, List<Bucket> list) {
        boolean z = true;
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        Utils.checkArgument(i >= 0, "count should be non-negative.");
        int i2 = (d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : (d2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0 : -1));
        Utils.checkArgument(i2 >= 0, "sum of squared deviations should be non-negative.");
        if (i == 0) {
            Utils.checkArgument(d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, "sum should be 0 if count is 0.");
            if (i2 != 0) {
                z = false;
            }
            Utils.checkArgument(z, "sum of squared deviations should be 0 if count is 0.");
        }
        Utils.checkNotNull(bucketOptions, "bucketOptions");
        List unmodifiableList = Collections.unmodifiableList(new ArrayList((Collection) Utils.checkNotNull(list, "buckets")));
        Utils.checkListElementNotNull(unmodifiableList, "bucket");
        return new AutoValue_Distribution(j, d, d2, bucketOptions, unmodifiableList);
    }

    public static abstract class BucketOptions {
        public abstract <T> T match(Function<? super ExplicitOptions, T> function, Function<? super BucketOptions, T> function2);

        private BucketOptions() {
        }

        public static BucketOptions explicitOptions(List<Double> list) {
            return ExplicitOptions.create(list);
        }

        public static abstract class ExplicitOptions extends BucketOptions {
            public abstract List<Double> getBucketBoundaries();

            ExplicitOptions() {
                super();
            }

            public final <T> T match(Function<? super ExplicitOptions, T> function, Function<? super BucketOptions, T> function2) {
                return function.apply(this);
            }

            /* access modifiers changed from: private */
            public static ExplicitOptions create(List<Double> list) {
                Utils.checkNotNull(list, "bucketBoundaries");
                List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
                checkBucketBoundsAreSorted(unmodifiableList);
                return new AutoValue_Distribution_BucketOptions_ExplicitOptions(unmodifiableList);
            }

            private static void checkBucketBoundsAreSorted(List<Double> list) {
                if (list.size() >= 1) {
                    double doubleValue = ((Double) Utils.checkNotNull(list.get(0), "bucketBoundary")).doubleValue();
                    Utils.checkArgument(doubleValue > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, "bucket boundary should be > 0");
                    int i = 1;
                    while (i < list.size()) {
                        double doubleValue2 = ((Double) Utils.checkNotNull(list.get(i), "bucketBoundary")).doubleValue();
                        Utils.checkArgument(doubleValue < doubleValue2, "bucket boundaries not sorted.");
                        i++;
                        doubleValue = doubleValue2;
                    }
                }
            }
        }
    }

    public static abstract class Bucket {
        public abstract long getCount();

        @Nullable
        public abstract Exemplar getExemplar();

        Bucket() {
        }

        public static Bucket create(long j) {
            Utils.checkArgument(j >= 0, "bucket count should be non-negative.");
            return new AutoValue_Distribution_Bucket(j, (Exemplar) null);
        }

        public static Bucket create(long j, Exemplar exemplar) {
            Utils.checkArgument(j >= 0, "bucket count should be non-negative.");
            Utils.checkNotNull(exemplar, "exemplar");
            return new AutoValue_Distribution_Bucket(j, exemplar);
        }
    }
}
