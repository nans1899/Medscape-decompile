package mnetinternal;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import com.mnet.gson.n;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;
import net.media.android.bidder.base.a;
import net.media.android.bidder.base.logging.Logger;

public final class da {
    private static Pattern a = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");

    public static boolean a(WeakReference weakReference) {
        return weakReference == null || weakReference.get() == null;
    }

    public static String a(Context context) {
        return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    }

    public static int b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    public static boolean a(n nVar, String str) {
        return nVar.a(str) && !nVar.b(str).l();
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && Patterns.WEB_URL.matcher(str).matches();
    }

    public static String a(View view) {
        if (!(view == null || view.getParent() == null)) {
            try {
                return view.getResources().getResourceName(((View) view.getParent()).getId());
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public static String b(View view) {
        if (view == null) {
            return "";
        }
        try {
            return view.getResources().getResourceName(view.getId());
        } catch (Exception unused) {
            return "";
        }
    }

    public static Activity a() {
        Activity b = a.a().b();
        if (b != null) {
            return b;
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object invoke = cls.getMethod("currentActivityThread", new Class[0]).invoke((Object) null, new Object[0]);
            Field declaredField = cls.getDeclaredField("mActivities");
            declaredField.setAccessible(true);
            Map map = (Map) declaredField.get(invoke);
            if (map == null) {
                return null;
            }
            for (Object next : map.values()) {
                Class<?> cls2 = next.getClass();
                Field declaredField2 = cls2.getDeclaredField("paused");
                declaredField2.setAccessible(true);
                if (!declaredField2.getBoolean(next)) {
                    Field declaredField3 = cls2.getDeclaredField("activity");
                    declaredField3.setAccessible(true);
                    return (Activity) declaredField3.get(next);
                }
            }
            return null;
        } catch (Exception e) {
            Logger.notify("##Util##", "foreground activity fetch error", e);
        }
    }

    public static Activity c(View view) {
        Context context;
        if (view == null) {
            return null;
        }
        Context context2 = view.getContext();
        if (context2 instanceof Activity) {
            return (Activity) context2;
        }
        while (context2 instanceof ContextWrapper) {
            if (context2 instanceof Activity) {
                return (Activity) context2;
            }
            context2 = ((ContextWrapper) context2).getBaseContext();
        }
        View findViewById = view.findViewById(16908290);
        if (findViewById == null || (context = findViewById.getContext()) == null || !(context instanceof Activity)) {
            return null;
        }
        return (Activity) context;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static String b(String str) {
        return a.matcher(str).replaceAll("\\\\$0");
    }

    public static double a(double d, int i) {
        if (i >= 0) {
            return new BigDecimal(d).setScale(i, RoundingMode.HALF_UP).doubleValue();
        }
        throw new IllegalArgumentException();
    }

    public static String b() {
        return UUID.randomUUID().toString();
    }

    public static String a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                return byteArrayOutputStream.toString("UTF-8");
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public static String c(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\\.");
        if (split.length != 4) {
            return str;
        }
        split[3] = "*";
        return TextUtils.join(".", split);
    }

    public static String c(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        }
        return context.getResources().getConfiguration().locale.getLanguage();
    }
}
