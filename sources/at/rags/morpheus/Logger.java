package at.rags.morpheus;

import android.util.Log;

public class Logger {
    private static final String TAG = "Morpheus";
    private static boolean debug = false;

    public static void debug(String str) {
        if (debug) {
            Log.d(TAG, str);
        }
    }

    public static void setDebug(boolean z) {
        debug = z;
    }
}
