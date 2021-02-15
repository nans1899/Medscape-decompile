package net.bytebuddy.agent;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.newsclub.net.unix.AFUNIXSocket;
import org.newsclub.net.unix.AFUNIXSocketAddress;

public interface VirtualMachine {
    void detach() throws IOException;

    void loadAgent(String str, String str2) throws IOException;

    public static abstract class ForHotSpot implements VirtualMachine {
        private static final String ARGUMENT_DELIMITER = "=";
        private static final byte[] BLANK = {0};
        private static final String INSTRUMENT_COMMAND = "instrument";
        private static final String LOAD_COMMAND = "load";
        private static final String PROTOCOL_VERSION = "1";
        private static final String UTF_8 = "UTF-8";
        protected final String processId;

        /* access modifiers changed from: protected */
        public abstract void connect() throws IOException;

        /* access modifiers changed from: protected */
        public abstract int read(byte[] bArr) throws IOException;

        /* access modifiers changed from: protected */
        public abstract void write(byte[] bArr) throws IOException;

        protected ForHotSpot(String str) {
            this.processId = str;
        }

        public void loadAgent(String str, String str2) throws IOException {
            connect();
            write("1".getBytes("UTF-8"));
            write(BLANK);
            write(LOAD_COMMAND.getBytes("UTF-8"));
            write(BLANK);
            write(INSTRUMENT_COMMAND.getBytes("UTF-8"));
            write(BLANK);
            write(Boolean.FALSE.toString().getBytes("UTF-8"));
            write(BLANK);
            if (str2 != null) {
                str = str + ARGUMENT_DELIMITER + str2;
            }
            write(str.getBytes("UTF-8"));
            write(BLANK);
            byte[] bArr = new byte[1];
            StringBuilder sb = new StringBuilder();
            while (true) {
                int read = read(bArr);
                if (read == -1) {
                    break;
                } else if (read > 0) {
                    if (bArr[0] == 10) {
                        break;
                    }
                    sb.append((char) bArr[0]);
                }
            }
            int parseInt = Integer.parseInt(sb.toString());
            if (parseInt == 0) {
                return;
            }
            if (parseInt != 101) {
                byte[] bArr2 = new byte[1024];
                StringBuilder sb2 = new StringBuilder();
                while (true) {
                    int read2 = read(bArr2);
                    if (read2 != -1) {
                        sb2.append(new String(bArr2, 0, read2, "UTF-8"));
                    } else {
                        throw new IllegalStateException(sb2.toString());
                    }
                }
            } else {
                throw new IOException("Protocol mismatch with target VM");
            }
        }

        public static class OnUnix extends ForHotSpot {
            private static final String ATTACH_FILE_PREFIX = ".attach_pid";
            private static final int DEFAULT_ATTEMPTS = 10;
            private static final long DEFAULT_PAUSE = 200;
            private static final long DEFAULT_TIMEOUT = 5000;
            private static final String SOCKET_FILE_PREFIX = ".java_pid";
            private static final String TEMPORARY_DIRECTORY = "/tmp";
            private final int attempts;
            private final long pause;
            private final Object socket;
            private final TimeUnit timeUnit;
            private final long timeout;

            public OnUnix(String str, Object obj, int i, long j, long j2, TimeUnit timeUnit2) {
                super(str);
                this.socket = obj;
                this.attempts = i;
                this.pause = j;
                this.timeout = j2;
                this.timeUnit = timeUnit2;
            }

            public static Class<?> assertAvailability() throws Throwable {
                try {
                    Class<?> cls = Class.forName("java.lang.Module");
                    Method method = Class.class.getMethod("getModule", new Class[0]);
                    Method method2 = cls.getMethod("canRead", new Class[]{cls});
                    Object invoke = method.invoke(OnUnix.class, new Object[0]);
                    Object invoke2 = method.invoke(AFUNIXSocket.class, new Object[0]);
                    if (!((Boolean) method2.invoke(invoke, new Object[]{invoke2})).booleanValue()) {
                        cls.getMethod("addReads", new Class[]{cls}).invoke(invoke, new Object[]{invoke2});
                    }
                    return doAssertAvailability();
                } catch (ClassNotFoundException unused) {
                    return doAssertAvailability();
                }
            }

            private static Class<?> doAssertAvailability() {
                if (!AFUNIXSocket.isSupported()) {
                    throw new IllegalStateException("POSIX sockets are not supported on the current system");
                } else if (System.getProperty("java.vm.name").toLowerCase(Locale.US).contains("hotspot")) {
                    return OnUnix.class;
                } else {
                    throw new IllegalStateException("Cannot apply attachment on non-Hotspot compatible VM");
                }
            }

            public static VirtualMachine attach(String str) throws IOException {
                return new OnUnix(str, AFUNIXSocket.newInstance(), 10, DEFAULT_PAUSE, 5000, TimeUnit.MILLISECONDS);
            }

            /* access modifiers changed from: protected */
            public void connect() throws IOException {
                File file = new File(TEMPORARY_DIRECTORY, SOCKET_FILE_PREFIX + this.processId);
                if (!file.exists()) {
                    String str = ATTACH_FILE_PREFIX + this.processId;
                    File file2 = new File("/proc/" + this.processId + "/cwd/" + str);
                    try {
                        if (!file2.createNewFile()) {
                            if (!file2.isFile()) {
                                throw new IllegalStateException("Could not create attach file: " + file2);
                            }
                        }
                    } catch (IOException unused) {
                        file2 = new File(TEMPORARY_DIRECTORY, str);
                        if (!file2.createNewFile() && !file2.isFile()) {
                            throw new IllegalStateException("Could not create attach file: " + file2);
                        }
                    }
                    try {
                        Process exec = Runtime.getRuntime().exec("kill -3 " + this.processId);
                        int i = this.attempts;
                        boolean z = false;
                        while (true) {
                            try {
                                if (exec.exitValue() == 0) {
                                    z = true;
                                } else {
                                    throw new IllegalStateException("Error while sending signal to target VM: " + this.processId);
                                }
                            } catch (IllegalThreadStateException unused2) {
                                i--;
                                Thread.sleep(this.timeUnit.toMillis(this.pause));
                                if (i <= 0) {
                                    break;
                                }
                            }
                        }
                        if (z) {
                            int i2 = this.attempts;
                            while (true) {
                                int i3 = i2 - 1;
                                if (i2 > 0 && !file.exists()) {
                                    Thread.sleep(this.timeUnit.toMillis(this.pause));
                                    i2 = i3;
                                }
                            }
                            if (!file.exists()) {
                                throw new IllegalStateException("Target VM did not respond: " + this.processId);
                            } else if (!file2.delete()) {
                                file2.deleteOnExit();
                            }
                        } else {
                            throw new IllegalStateException("Target VM did not respond to signal: " + this.processId);
                        }
                    } catch (InterruptedException e) {
                        throw new IllegalStateException("Interrupted during wait for process", e);
                    } catch (Throwable th) {
                        if (!file2.delete()) {
                            file2.deleteOnExit();
                        }
                        throw th;
                    }
                }
                long j = this.timeout;
                if (j != 0) {
                    ((AFUNIXSocket) this.socket).setSoTimeout((int) this.timeUnit.toMillis(j));
                }
                ((AFUNIXSocket) this.socket).connect(new AFUNIXSocketAddress(file));
            }

            public int read(byte[] bArr) throws IOException {
                return ((AFUNIXSocket) this.socket).getInputStream().read(bArr);
            }

            public void write(byte[] bArr) throws IOException {
                ((AFUNIXSocket) this.socket).getOutputStream().write(bArr);
            }

            public void detach() throws IOException {
                ((AFUNIXSocket) this.socket).close();
            }
        }
    }
}
