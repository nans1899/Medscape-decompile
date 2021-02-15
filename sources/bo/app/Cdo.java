package bo.app;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.appboy.support.AppboyLogger;
import com.facebook.AccessToken;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONException;

@Deprecated
/* renamed from: bo.app.do  reason: invalid class name */
public class Cdo implements dl {
    private static final String a = AppboyLogger.getAppboyLogTag(Cdo.class);
    private static final String[] b = {"session_id", AccessToken.USER_ID_KEY, "event_type", "event_data", "event_guid", "timestamp"};
    private SQLiteDatabase c;
    private boolean d = false;
    private final de e;

    public Cdo(de deVar) {
        this.e = deVar;
    }

    public synchronized SQLiteDatabase c() {
        if (this.c == null || !this.c.isOpen()) {
            this.c = this.e.getWritableDatabase();
        }
        return this.c;
    }

    public void a(bz bzVar) {
        if (this.d) {
            String str = a;
            AppboyLogger.w(str, "Storage provider is closed. Not adding event: " + bzVar);
            return;
        }
        if (c().insert("ab_events", (String) null, c(bzVar)) == -1) {
            String str2 = a;
            AppboyLogger.w(str2, "Failed to add event [" + bzVar.toString() + "] to storage");
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.util.Collection<bo.app.bz>, android.database.Cursor] */
    public Collection<bz> a() {
        Cursor cursor = 0;
        if (this.d) {
            AppboyLogger.w(a, "Storage provider is closed. Not getting all events.");
            return cursor;
        }
        try {
            cursor = c().query("ab_events", b, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            return a(cursor);
        } finally {
            if (cursor != 0) {
                cursor.close();
            }
        }
    }

    public void b(bz bzVar) {
        if (this.d) {
            String str = a;
            AppboyLogger.w(str, "Storage provider is closed. Not deleting event: " + bzVar);
            return;
        }
        c().beginTransaction();
        try {
            int delete = c().delete("ab_events", "event_guid = ?", new String[]{bzVar.d()});
            String str2 = a;
            AppboyLogger.d(str2, "Deleting event with uid " + bzVar.d() + " removed " + delete + " row.", false);
            c().setTransactionSuccessful();
        } finally {
            c().endTransaction();
        }
    }

    public void b() {
        AppboyLogger.w(a, "Closing SQL database and setting this provider to closed.");
        this.d = true;
        c().close();
    }

    private ContentValues c(bz bzVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("event_type", bzVar.b().forJsonPut());
        contentValues.put("event_data", bzVar.c().toString());
        contentValues.put("timestamp", Double.valueOf(bzVar.a()));
        if (bzVar.g() != null) {
            contentValues.put("session_id", bzVar.g().toString());
        }
        if (bzVar.f() != null) {
            contentValues.put(AccessToken.USER_ID_KEY, bzVar.f());
        }
        if (bzVar.d() != null) {
            contentValues.put("event_guid", bzVar.d());
        }
        return contentValues;
    }

    private Collection<bz> a(Cursor cursor) {
        Cursor cursor2 = cursor;
        ArrayList arrayList = new ArrayList();
        int columnIndex = cursor2.getColumnIndex("session_id");
        int columnIndex2 = cursor2.getColumnIndex(AccessToken.USER_ID_KEY);
        int columnIndex3 = cursor2.getColumnIndex("event_type");
        int columnIndex4 = cursor2.getColumnIndex("event_data");
        int columnIndex5 = cursor2.getColumnIndex("event_guid");
        int columnIndex6 = cursor2.getColumnIndex("timestamp");
        while (cursor.moveToNext()) {
            String string = cursor2.getString(columnIndex3);
            String string2 = cursor2.getString(columnIndex4);
            double d2 = cursor2.getDouble(columnIndex6);
            String string3 = cursor2.getString(columnIndex5);
            String string4 = cursor2.getString(columnIndex2);
            String str = string;
            String string5 = cursor2.getString(columnIndex);
            String str2 = string3;
            String str3 = string4;
            int i = columnIndex;
            int i2 = columnIndex2;
            double d3 = d2;
            String str4 = string2;
            try {
                arrayList.add(cf.a(str, string2, d2, str2, str3, string5));
            } catch (JSONException unused) {
                String str5 = a;
                AppboyLogger.e(str5, "Could not create AppboyEvent from [type=" + string + ", data=" + str4 + ", timestamp=" + d3 + ", uniqueId=" + str2 + ", userId=" + str3 + ", sessionId=" + string5 + "] ... Skipping");
            }
            cursor2 = cursor;
            columnIndex = i;
            columnIndex2 = i2;
        }
        return arrayList;
    }
}
