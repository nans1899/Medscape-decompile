package io.opencensus.metrics.export;

import io.opencensus.internal.Utils;
import io.opencensus.metrics.export.MetricDescriptor;
import io.opencensus.metrics.export.Value;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Metric {
    public abstract MetricDescriptor getMetricDescriptor();

    public abstract List<TimeSeries> getTimeSeriesList();

    Metric() {
    }

    public static Metric create(MetricDescriptor metricDescriptor, List<TimeSeries> list) {
        Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "timeSeriesList"), "timeSeries");
        return createInternal(metricDescriptor, Collections.unmodifiableList(new ArrayList(list)));
    }

    public static Metric createWithOneTimeSeries(MetricDescriptor metricDescriptor, TimeSeries timeSeries) {
        return createInternal(metricDescriptor, Collections.singletonList(Utils.checkNotNull(timeSeries, "timeSeries")));
    }

    private static Metric createInternal(MetricDescriptor metricDescriptor, List<TimeSeries> list) {
        Utils.checkNotNull(metricDescriptor, "metricDescriptor");
        checkTypeMatch(metricDescriptor.getType(), list);
        return new AutoValue_Metric(metricDescriptor, list);
    }

    private static void checkTypeMatch(MetricDescriptor.Type type, List<TimeSeries> list) {
        for (TimeSeries points : list) {
            for (Point value : points.getPoints()) {
                Value value2 = value.getValue();
                String simpleName = value2.getClass().getSuperclass() != null ? value2.getClass().getSuperclass().getSimpleName() : "";
                switch (AnonymousClass1.$SwitchMap$io$opencensus$metrics$export$MetricDescriptor$Type[type.ordinal()]) {
                    case 1:
                    case 2:
                        Utils.checkArgument(value2 instanceof Value.ValueLong, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                    case 3:
                    case 4:
                        Utils.checkArgument(value2 instanceof Value.ValueDouble, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                    case 5:
                    case 6:
                        Utils.checkArgument(value2 instanceof Value.ValueDistribution, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                    case 7:
                        Utils.checkArgument(value2 instanceof Value.ValueSummary, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                }
            }
        }
    }

    /* renamed from: io.opencensus.metrics.export.Metric$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$opencensus$metrics$export$MetricDescriptor$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.opencensus.metrics.export.MetricDescriptor$Type[] r0 = io.opencensus.metrics.export.MetricDescriptor.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$opencensus$metrics$export$MetricDescriptor$Type = r0
                io.opencensus.metrics.export.MetricDescriptor$Type r1 = io.opencensus.metrics.export.MetricDescriptor.Type.GAUGE_INT64     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$opencensus$metrics$export$MetricDescriptor$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                io.opencensus.metrics.export.MetricDescriptor$Type r1 = io.opencensus.metrics.export.MetricDescriptor.Type.CUMULATIVE_INT64     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$opencensus$metrics$export$MetricDescriptor$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.opencensus.metrics.export.MetricDescriptor$Type r1 = io.opencensus.metrics.export.MetricDescriptor.Type.CUMULATIVE_DOUBLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$opencensus$metrics$export$MetricDescriptor$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.opencensus.metrics.export.MetricDescriptor$Type r1 = io.opencensus.metrics.export.MetricDescriptor.Type.GAUGE_DOUBLE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$opencensus$metrics$export$MetricDescriptor$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                io.opencensus.metrics.export.MetricDescriptor$Type r1 = io.opencensus.metrics.export.MetricDescriptor.Type.GAUGE_DISTRIBUTION     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$io$opencensus$metrics$export$MetricDescriptor$Type     // Catch:{ NoSuchFieldError -> 0x0049 }
                io.opencensus.metrics.export.MetricDescriptor$Type r1 = io.opencensus.metrics.export.MetricDescriptor.Type.CUMULATIVE_DISTRIBUTION     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$io$opencensus$metrics$export$MetricDescriptor$Type     // Catch:{ NoSuchFieldError -> 0x0054 }
                io.opencensus.metrics.export.MetricDescriptor$Type r1 = io.opencensus.metrics.export.MetricDescriptor.Type.SUMMARY     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.opencensus.metrics.export.Metric.AnonymousClass1.<clinit>():void");
        }
    }
}
