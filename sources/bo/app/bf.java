package bo.app;

import android.content.Context;
import com.amazon.device.messaging.ADM;
import com.amazon.device.messaging.development.ADMManifest;
import com.appboy.support.AppboyLogger;

public class bf {
    private static final String c = AppboyLogger.getAppboyLogTag(bf.class);
    private final Context a;
    private final bu b;

    public bf(Context context, bu buVar) {
        this.a = context;
        this.b = buVar;
    }

    public void a() {
        if (this.b.a() != null) {
            AppboyLogger.i(c, "The device is already registered with the ADM server and is eligible to receive ADM messages.");
            String str = c;
            AppboyLogger.i(str, "ADM registration id: " + this.b.a());
            bu buVar = this.b;
            buVar.a(buVar.a());
            return;
        }
        ADM adm = new ADM(this.a);
        if (adm.isSupported()) {
            AppboyLogger.i(c, "Registering with ADM server...");
            adm.startRegister();
        }
    }

    public static boolean a(Context context) {
        return b() && b(context);
    }

    private static boolean b() {
        try {
            Class.forName("com.amazon.device.messaging.ADM");
            return true;
        } catch (Exception unused) {
            AppboyLogger.i(c, "com.amazon.device.messaging.ADM not found");
            return false;
        }
    }

    private static boolean b(Context context) {
        try {
            ADMManifest.checkManifestAuthoredProperly(context);
            return true;
        } catch (Exception e) {
            AppboyLogger.i(c, "Manifest not authored properly to support ADM.");
            AppboyLogger.i(c, "ADM manifest exception: ", (Throwable) e);
            return false;
        }
    }
}
