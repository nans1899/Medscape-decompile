package io.grpc.perfmark;

import io.grpc.perfmark.PerfTag;

public final class PerfMark {
    private static final PerfTag NULL_PERF_TAG = PerfTag.TagFactory.create();

    public static void event(PerfTag perfTag, String str) {
    }

    public static void event(String str) {
    }

    public static void taskEnd(PerfTag perfTag, String str) {
    }

    public static void taskEnd(String str) {
    }

    public static void taskStart(PerfTag perfTag, String str) {
    }

    public static void taskStart(String str) {
    }

    private PerfMark() {
        throw new AssertionError("nope");
    }

    public static PerfMarkTask task(PerfTag perfTag, String str) {
        return NoopTask.INSTANCE;
    }

    public static PerfMarkTask task(String str) {
        return NoopTask.INSTANCE;
    }

    public static PerfTag createTag(long j, String str) {
        return NULL_PERF_TAG;
    }

    public static PerfTag createTag(String str) {
        return NULL_PERF_TAG;
    }

    public static PerfTag createTag(long j) {
        return NULL_PERF_TAG;
    }

    private static final class NoopTask extends PerfMarkTask {
        /* access modifiers changed from: private */
        public static final PerfMarkTask INSTANCE = new NoopTask();

        public void close() {
        }

        NoopTask() {
        }
    }
}
