package net.media.android.bidder.base.logging;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import net.media.android.bidder.base.error.ErrorMessage;
import org.apache.commons.io.IOUtils;

public final class MNetLog {
    private static final Logger LOGGER = Logger.getLogger(LOGGER_NAMESPACE);
    private static final String LOGGER_NAMESPACE = "net.media.android";
    private static final a LOG_HANDLER = new a();
    private static final String LOG_TAG = "MNet";

    static {
        LOGGER.setUseParentHandlers(false);
        LOGGER.setLevel(Level.ALL);
        LOG_HANDLER.setLevel(Level.FINE);
        LogManager.getLogManager().addLogger(LOGGER);
        addHandler(LOGGER, LOG_HANDLER);
    }

    private MNetLog() {
    }

    public static void verbose(String str) {
        verbose(str, (Throwable) null);
    }

    public static void debug(String str) {
        debug(str, (Throwable) null);
    }

    public static void info(String str) {
        info(str, (Throwable) null);
    }

    public static void warning(String str) {
        warning(str, (Throwable) null);
    }

    public static void warning(ErrorMessage errorMessage) {
        warning(errorMessage.toString(), (Throwable) null);
    }

    public static void error(String str) {
        error(str, (Throwable) null);
    }

    public static void error(ErrorMessage errorMessage) {
        error(errorMessage.toString(), (Throwable) null);
    }

    public static void verbose(String str, Throwable th) {
        LOGGER.log(Level.FINE, str, th);
    }

    public static void debug(String str, Throwable th) {
        LOGGER.log(Level.CONFIG, str, th);
    }

    public static void info(String str, Throwable th) {
        LOGGER.log(Level.INFO, str, th);
    }

    public static void warning(String str, Throwable th) {
        LOGGER.log(Level.WARNING, str, th);
    }

    public static void error(String str, Throwable th) {
        LOGGER.log(Level.SEVERE, str, th);
    }

    private static void addHandler(Logger logger, Handler handler) {
        Handler[] handlers = logger.getHandlers();
        int length = handlers.length;
        int i = 0;
        while (i < length) {
            if (!handlers[i].equals(handler)) {
                i++;
            } else {
                return;
            }
        }
        logger.addHandler(handler);
    }

    private static final class a extends Handler {
        private static final Map<Level, Integer> a;

        public void close() {
        }

        public void flush() {
        }

        private a() {
        }

        static {
            HashMap hashMap = new HashMap(7);
            a = hashMap;
            hashMap.put(Level.FINEST, 2);
            a.put(Level.FINER, 2);
            a.put(Level.FINE, 2);
            a.put(Level.CONFIG, 3);
            a.put(Level.INFO, 4);
            a.put(Level.WARNING, 5);
            a.put(Level.SEVERE, 6);
        }

        public void publish(LogRecord logRecord) {
            if (isLoggable(logRecord)) {
                int intValue = a.containsKey(logRecord.getLevel()) ? a.get(logRecord.getLevel()).intValue() : 2;
                String str = logRecord.getMessage() + IOUtils.LINE_SEPARATOR_UNIX;
                Throwable thrown = logRecord.getThrown();
                if (thrown != null) {
                    str = str + Log.getStackTraceString(thrown);
                }
                Log.println(intValue, MNetLog.LOG_TAG, str);
            }
        }
    }
}
