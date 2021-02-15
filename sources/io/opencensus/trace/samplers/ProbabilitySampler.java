package io.opencensus.trace.samplers;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import io.opencensus.internal.Utils;
import io.opencensus.trace.Sampler;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.TraceId;
import java.util.List;
import javax.annotation.Nullable;

abstract class ProbabilitySampler extends Sampler {
    /* access modifiers changed from: package-private */
    public abstract long getIdUpperBound();

    /* access modifiers changed from: package-private */
    public abstract double getProbability();

    ProbabilitySampler() {
    }

    static ProbabilitySampler create(double d) {
        int i = (d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 1 : (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0 : -1));
        Utils.checkArgument(i >= 0 && d <= 1.0d, "probability must be in range [0.0, 1.0]");
        return new AutoValue_ProbabilitySampler(d, i == 0 ? Long.MIN_VALUE : d == 1.0d ? Long.MAX_VALUE : (long) (9.223372036854776E18d * d));
    }

    public final boolean shouldSample(@Nullable SpanContext spanContext, @Nullable Boolean bool, TraceId traceId, SpanId spanId, String str, @Nullable List<Span> list) {
        if (spanContext != null && spanContext.getTraceOptions().isSampled()) {
            return true;
        }
        if (list != null) {
            for (Span context : list) {
                if (context.getContext().getTraceOptions().isSampled()) {
                    return true;
                }
            }
        }
        if (Math.abs(traceId.getLowerLong()) < getIdUpperBound()) {
            return true;
        }
        return false;
    }

    public final String getDescription() {
        return String.format("ProbabilitySampler{%.6f}", new Object[]{Double.valueOf(getProbability())});
    }
}
