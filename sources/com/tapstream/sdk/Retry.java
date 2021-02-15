package com.tapstream.sdk;

import androidx.lifecycle.CoroutineLiveDataKt;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

public class Retry {
    public static final Strategy DEFAULT_DATA_COLLECTION_STRATEGY = new Exponential(1000, 2, 10, 120000);
    public static final Strategy DEFAULT_USER_FACING_RETRY_STRATEGY = new FixedDelay(500, 3, CoroutineLiveDataKt.DEFAULT_TIMEOUT);
    public static final Strategy NEVER_RETRY = new Never();

    public static class Never implements Strategy {
        public int getDelayMs(int i) {
            return 0;
        }

        public boolean shouldRetry(int i, long j) {
            return false;
        }
    }

    public interface Strategy {
        int getDelayMs(int i);

        boolean shouldRetry(int i, long j);
    }

    public static class Exponential implements Strategy {
        private final int exponent;
        private final long maxElapsedMs;
        private final int maxTries;
        private final int scale;

        public Exponential(int i, int i2, int i3, long j) {
            this.scale = i;
            this.exponent = i2;
            this.maxTries = i3;
            this.maxElapsedMs = j;
        }

        public int getDelayMs(int i) {
            if (i == 1) {
                return 0;
            }
            return (int) Math.max(Math.min(((double) this.scale) * Math.pow((double) this.exponent, (double) (i - 2)), 60000.0d), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        }

        public boolean shouldRetry(int i, long j) {
            if (j <= this.maxElapsedMs && i < this.maxTries) {
                return true;
            }
            return false;
        }
    }

    public static class FixedDelay implements Strategy {
        private final int delay;
        private final long maxElapsedMs;
        private final int maxTries;

        public FixedDelay(int i, int i2, long j) {
            this.maxTries = i2;
            this.delay = i;
            this.maxElapsedMs = j;
        }

        public int getDelayMs(int i) {
            return this.delay;
        }

        public boolean shouldRetry(int i, long j) {
            if (j <= this.maxElapsedMs && i < this.maxTries) {
                return true;
            }
            return false;
        }
    }

    public static class Retryable<T> {
        private int attempt = 1;
        private final long firstSent = System.currentTimeMillis();
        private final T obj;
        private final Strategy retryStrategy;

        public Retryable(T t, Strategy strategy) {
            this.obj = t;
            this.retryStrategy = strategy;
        }

        public T get() {
            return this.obj;
        }

        public int getAttempt() {
            return this.attempt;
        }

        public int incrementAttempt() {
            int i = this.attempt + 1;
            this.attempt = i;
            return i;
        }

        public long getFirstSent() {
            return this.firstSent;
        }

        public int getDelayMs() {
            return this.retryStrategy.getDelayMs(this.attempt);
        }

        public boolean shouldRetry() {
            return this.retryStrategy.shouldRetry(this.attempt, System.currentTimeMillis() - this.firstSent);
        }
    }
}
