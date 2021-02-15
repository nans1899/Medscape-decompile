package org.apache.commons.io.file;

import java.math.BigInteger;
import java.util.Objects;

public class Counters {

    public interface Counter {
        void add(long j);

        long get();

        BigInteger getBigInteger();

        Long getLong();

        void increment();
    }

    public interface PathCounters {
        Counter getByteCounter();

        Counter getDirectoryCounter();

        Counter getFileCounter();
    }

    private static class AbstractPathCounters implements PathCounters {
        private final Counter byteCounter;
        private final Counter directoryCounter;
        private final Counter fileCounter;

        protected AbstractPathCounters(Counter counter, Counter counter2, Counter counter3) {
            this.byteCounter = counter;
            this.directoryCounter = counter2;
            this.fileCounter = counter3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof AbstractPathCounters)) {
                return false;
            }
            AbstractPathCounters abstractPathCounters = (AbstractPathCounters) obj;
            if (!Objects.equals(this.byteCounter, abstractPathCounters.byteCounter) || !Objects.equals(this.directoryCounter, abstractPathCounters.directoryCounter) || !Objects.equals(this.fileCounter, abstractPathCounters.fileCounter)) {
                return false;
            }
            return true;
        }

        public Counter getByteCounter() {
            return this.byteCounter;
        }

        public Counter getDirectoryCounter() {
            return this.directoryCounter;
        }

        public Counter getFileCounter() {
            return this.fileCounter;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.byteCounter, this.directoryCounter, this.fileCounter});
        }

        public String toString() {
            return String.format("%,d files, %,d directories, %,d bytes", new Object[]{Long.valueOf(this.fileCounter.get()), Long.valueOf(this.directoryCounter.get()), Long.valueOf(this.byteCounter.get())});
        }
    }

    private static class BigIntegerCounter implements Counter {
        private BigInteger value;

        private BigIntegerCounter() {
            this.value = BigInteger.ZERO;
        }

        public void add(long j) {
            this.value = this.value.add(BigInteger.valueOf(j));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Counter)) {
                return false;
            }
            return Objects.equals(this.value, ((Counter) obj).getBigInteger());
        }

        public long get() {
            return this.value.longValueExact();
        }

        public BigInteger getBigInteger() {
            return this.value;
        }

        public Long getLong() {
            return Long.valueOf(this.value.longValueExact());
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.value});
        }

        public void increment() {
            this.value = this.value.add(BigInteger.ONE);
        }

        public String toString() {
            return this.value.toString();
        }
    }

    private static class BigIntegerPathCounters extends AbstractPathCounters {
        protected BigIntegerPathCounters() {
            super(Counters.bigIntegerCounter(), Counters.bigIntegerCounter(), Counters.bigIntegerCounter());
        }
    }

    private static class LongCounter implements Counter {
        private long value;

        private LongCounter() {
        }

        public void add(long j) {
            this.value += j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof Counter) && this.value == ((Counter) obj).get()) {
                return true;
            }
            return false;
        }

        public long get() {
            return this.value;
        }

        public BigInteger getBigInteger() {
            return BigInteger.valueOf(this.value);
        }

        public Long getLong() {
            return Long.valueOf(this.value);
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.value)});
        }

        public void increment() {
            this.value++;
        }

        public String toString() {
            return Long.toString(this.value);
        }
    }

    private static class LongPathCounters extends AbstractPathCounters {
        protected LongPathCounters() {
            super(Counters.longCounter(), Counters.longCounter(), Counters.longCounter());
        }
    }

    public static Counter bigIntegerCounter() {
        return new BigIntegerCounter();
    }

    public static PathCounters bigIntegerPathCounters() {
        return new BigIntegerPathCounters();
    }

    public static Counter longCounter() {
        return new LongCounter();
    }

    public static PathCounters longPathCounters() {
        return new LongPathCounters();
    }
}
