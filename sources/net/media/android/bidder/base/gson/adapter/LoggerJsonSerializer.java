package net.media.android.bidder.base.gson.adapter;

import com.mnet.gson.i;
import com.mnet.gson.j;
import com.mnet.gson.k;
import com.mnet.gson.n;
import com.mnet.gson.r;
import com.mnet.gson.s;
import java.lang.reflect.Type;
import mnetinternal.da;
import net.media.android.bidder.base.models.internal.ErrorLog;
import net.media.android.bidder.base.models.internal.Event;
import net.media.android.bidder.base.models.internal.Log;
import net.media.android.bidder.base.models.internal.Logger;

public class LoggerJsonSerializer implements j<Logger>, s<Logger> {
    public k a(Logger logger, Type type, r rVar) {
        if (logger == null) {
            return null;
        }
        n nVar = new n();
        nVar.a("tp", logger.getType());
        nVar.a("lvl", logger.getLoggingLevel());
        nVar.a("ts", (Number) Long.valueOf(logger.getTimestamp()));
        nVar.a("plt", logger.getPlatform());
        nVar.a("lg", rVar.a(logger.getMessage()));
        return nVar;
    }

    /* renamed from: a */
    public Logger b(k kVar, Type type, i iVar) {
        if (kVar.l() || !kVar.j()) {
            return null;
        }
        n m = kVar.m();
        Logger logger = new Logger();
        if (da.a(m, "tp")) {
            logger.setType(m.b("tp").c());
        }
        if (da.a(m, "lvl")) {
            logger.setLoggingLevel(m.b("lvl").c());
        }
        if (da.a(m, "ts")) {
            logger.setTimestamp(m.b("ts").f());
        }
        if (da.a(m, "plt")) {
            logger.setLoggingLevel(m.b("plt").c());
        }
        if (da.a(m, "lg")) {
            if (logger.isErrorType()) {
                logger.setMessage(iVar.a(m.b("lg").m(), ErrorLog.class));
            } else if (logger.isLogType()) {
                logger.setMessage(iVar.a(m.b("lg").m(), Log.class));
            } else {
                try {
                    logger.setMessage(iVar.a(m.b("lg").m(), Event.class));
                } catch (Exception unused) {
                    net.media.android.bidder.base.logging.Logger.error("##LoggerJsonSerializer##", "deserialization failed for Event class");
                }
            }
        }
        return logger;
    }
}
