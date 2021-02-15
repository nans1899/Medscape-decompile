package coil.util;

import android.util.Log;
import com.wbmd.wbmddrugscommons.constants.Constants;
import java.io.PrintWriter;
import java.io.StringWriter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0003H\u0002J,\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003@VX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004¨\u0006\u0012"}, d2 = {"Lcoil/util/DebugLogger;", "Lcoil/util/Logger;", "level", "", "(I)V", "value", "getLevel", "()I", "setLevel", "assertValidLevel", "", "log", "tag", "", "priority", "message", "throwable", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DebugLogger.kt */
public final class DebugLogger implements Logger {
    private int level;

    public DebugLogger() {
        this(0, 1, (DefaultConstructorMarker) null);
    }

    public DebugLogger(int i) {
        this.level = i;
        assertValidLevel(i);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DebugLogger(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 3 : i);
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        assertValidLevel(i);
        this.level = i;
    }

    public void log(String str, int i, String str2, Throwable th) {
        Intrinsics.checkParameterIsNotNull(str, Constants.WBMDDrugKeyTag);
        if (str2 != null) {
            Log.println(i, str, str2);
        }
        if (th != null) {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            Log.println(i, str, stringWriter.toString());
        }
    }

    private final void assertValidLevel(int i) {
        if (!(2 <= i && 7 >= i)) {
            throw new IllegalArgumentException(("Invalid log level: " + i).toString());
        }
    }
}
