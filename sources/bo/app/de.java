package bo.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public final class de extends SQLiteOpenHelper {
    private static final String a = AppboyLogger.getAppboyLogTag(de.class);
    private static volatile Map<String, de> b = new HashMap();
    private static final String[] c = {"ab_events", "ab_sessions", "sessions", "appboy_events"};

    public static de a(Context context, String str, String str2) {
        String b2 = b(context, str, str2);
        if (!b.containsKey(b2)) {
            synchronized (de.class) {
                if (!b.containsKey(b2)) {
                    de deVar = new de(context, b2);
                    b.put(b2, deVar);
                    return deVar;
                }
            }
        }
        return b.get(b2);
    }

    private de(Context context, String str) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, 3);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        AppboyLogger.i(a, "Creating ab_events table");
        sQLiteDatabase.execSQL("CREATE TABLE ab_events (_id INTEGER PRIMARY KEY AUTOINCREMENT, session_id TEXT, user_id TEXT, event_type TEXT NOT NULL, event_data TEXT NOT NULL, event_guid TEXT NOT NULL, timestamp INTEGER NOT NULL);");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str = a;
        AppboyLogger.i(str, "Upgrading storage from version " + i + " to " + i2 + ". Dropping all current tables.");
        a(sQLiteDatabase);
        onCreate(sQLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        String str = a;
        AppboyLogger.i(str, "Downgrading storage from version " + i + " to " + i2 + ". Dropping all current tables.");
        a(sQLiteDatabase);
        onCreate(sQLiteDatabase);
    }

    public static void a(Context context) {
        File parentFile = context.getDatabasePath(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).getParentFile();
        if (parentFile.exists() && parentFile.isDirectory()) {
            for (File file : parentFile.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.startsWith("appboy.db");
                }
            })) {
                AppboyLogger.v(a, "Deleting database file at: " + file.getAbsolutePath());
                context.deleteDatabase(file.getName());
            }
        }
    }

    private static void a(SQLiteDatabase sQLiteDatabase) {
        for (String str : c) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + str);
            AppboyLogger.d(a, "Dropped table with name: " + str);
        }
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
    }

    static String b(Context context, String str, String str2) {
        if (StringUtils.isNullOrBlank(str)) {
            return "appboy.db";
        }
        return "appboy.db" + StringUtils.getCacheFileSuffix(context, str, str2);
    }
}
