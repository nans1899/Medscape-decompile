package bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Process;
import com.appboy.support.AppboyFileUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.IntentUtils;
import com.appboy.support.StringUtils;
import com.appboy.support.WebContentUtils;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class fx implements ft {
    /* access modifiers changed from: private */
    public static final String a = AppboyLogger.getAppboyLogTag(fx.class);
    private final Context b;
    private final ThreadPoolExecutor c;
    private final SharedPreferences d;
    /* access modifiers changed from: private */
    public Map<String, String> e;
    private Map<String, String> f = new HashMap();

    public fx(Context context, ThreadPoolExecutor threadPoolExecutor, String str) {
        this.b = context;
        this.c = threadPoolExecutor;
        this.d = context.getSharedPreferences("com.appboy.storage.triggers.local_assets." + str, 0);
        this.e = a();
    }

    public void a(List<ek> list) {
        final HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (ek next : list) {
            ga d2 = next.d();
            if (d2 != null && !StringUtils.isNullOrBlank(d2.b())) {
                if (next.a()) {
                    AppboyLogger.d(a, "Received new remote path for triggered action " + next.b() + " at " + d2.b() + ".");
                    hashSet.add(d2);
                    hashSet2.add(d2.b());
                } else {
                    AppboyLogger.d(a, "Pre-fetch off for triggered action " + next.b() + ". Not pre-fetching assets at remote path " + d2.b() + ".");
                }
            }
        }
        final SharedPreferences.Editor edit = this.d.edit();
        for (String str : new HashSet(this.e.keySet())) {
            if (this.f.containsKey(str)) {
                AppboyLogger.d(a, "Not removing local path for remote path " + str + " from cache because it is being preserved until the end of the app run.");
            } else if (!hashSet2.contains(str)) {
                String str2 = this.e.get(str);
                AppboyLogger.d(a, "Removing obsolete local path " + str2 + " for obsolete remote path " + str + " from cache.");
                this.e.remove(str);
                edit.remove(str);
                AppboyFileUtils.deleteFileOrDirectory(new File(str2));
            }
        }
        edit.apply();
        try {
            File[] listFiles = b().listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    String path = file.getPath();
                    if (this.e.containsValue(path)) {
                        AppboyLogger.d(a, "Asset " + path + " is not obsolete. Not deleting.");
                    } else if (!this.f.containsValue(path)) {
                        AppboyLogger.d(a, "Deleting obsolete asset " + path + " from filesystem.");
                        AppboyFileUtils.deleteFileOrDirectory(file);
                    } else {
                        AppboyLogger.d(a, "Asset " + path + " is being preserved. Not deleting.");
                    }
                }
            }
        } catch (Exception e2) {
            AppboyLogger.d(a, "Exception while deleting obsolete assets from filesystem.", (Throwable) e2);
        }
        this.c.execute(new Runnable() {
            public void run() {
                Process.setThreadPriority(10);
                for (ga gaVar : hashSet) {
                    String b2 = gaVar.b();
                    if (!fx.this.e.containsKey(b2)) {
                        try {
                            String a2 = fx.this.a(gaVar);
                            if (!StringUtils.isNullOrBlank(a2)) {
                                String c2 = fx.a;
                                AppboyLogger.d(c2, "Adding new local path " + a2 + " for remote path " + b2 + " to cache.");
                                fx.this.e.put(b2, a2);
                                edit.putString(b2, a2);
                            }
                        } catch (Exception e) {
                            String c3 = fx.a;
                            AppboyLogger.d(c3, "Failed to add new local path for remote path " + b2 + ".", (Throwable) e);
                        }
                    }
                }
                edit.apply();
            }
        });
    }

    public String a(ek ekVar) {
        if (!ekVar.a()) {
            AppboyLogger.d(a, "Prefetch turned off for this triggered action. Not retrieving local asset path.");
            return null;
        }
        ga d2 = ekVar.d();
        if (d2 == null) {
            AppboyLogger.i(a, "Remote path was null or blank. Not retrieving local asset path.");
            return null;
        }
        String b2 = d2.b();
        if (StringUtils.isNullOrBlank(b2)) {
            AppboyLogger.w(a, "Remote asset path string was null or blank. Not retrieving local asset path.");
            return null;
        } else if (this.e.containsKey(b2)) {
            String str = this.e.get(b2);
            if (!new File(str).exists()) {
                String str2 = a;
                AppboyLogger.w(str2, "Local asset for remote asset path did not exist: " + b2);
                return null;
            }
            String str3 = a;
            AppboyLogger.i(str3, "Retrieving local asset path for remote asset path: " + b2);
            this.f.put(b2, str);
            return str;
        } else {
            String str4 = a;
            AppboyLogger.w(str4, "No local asset path found for remote asset path: " + b2);
            return null;
        }
    }

    public static void a(Context context) {
        File file = new File(context.getCacheDir(), "ab_triggers");
        String str = a;
        AppboyLogger.v(str, "Deleting triggers directory at: " + file.getAbsolutePath());
        AppboyFileUtils.deleteFileOrDirectory(file);
    }

    /* access modifiers changed from: package-private */
    public String a(ga gaVar) {
        File b2 = b();
        String b3 = gaVar.b();
        if (gaVar.a().equals(fi.ZIP)) {
            String localHtmlUrlFromRemoteUrl = WebContentUtils.getLocalHtmlUrlFromRemoteUrl(b2, b3);
            if (!StringUtils.isNullOrBlank(localHtmlUrlFromRemoteUrl)) {
                String str = a;
                AppboyLogger.i(str, "Storing local triggered action html zip asset at local path " + localHtmlUrlFromRemoteUrl + " for remote path " + b3);
                return localHtmlUrlFromRemoteUrl;
            }
            String str2 = a;
            AppboyLogger.d(str2, "Failed to store html zip asset for remote path " + b3 + ". Not storing local asset");
            return null;
        }
        File downloadFileToPath = AppboyFileUtils.downloadFileToPath(b2.toString(), b3, Integer.toString(IntentUtils.getRequestCode()), (String) null);
        if (downloadFileToPath != null) {
            Uri fromFile = Uri.fromFile(downloadFileToPath);
            if (fromFile != null) {
                String str3 = a;
                AppboyLogger.i(str3, "Storing local triggered action image asset at local path " + fromFile.getPath() + " for remote path " + b3);
                return fromFile.getPath();
            }
            String str4 = a;
            AppboyLogger.d(str4, "Failed to store image asset for remote path " + b3 + ". Not storing local asset");
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> a() {
        Set<String> keySet;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Map<String, ?> all = this.d.getAll();
        if (!(all == null || all.size() == 0 || (keySet = all.keySet()) == null || keySet.size() == 0)) {
            try {
                for (String next : keySet) {
                    String string = this.d.getString(next, (String) null);
                    if (!StringUtils.isNullOrBlank(string)) {
                        String str = a;
                        AppboyLogger.d(str, "Retrieving trigger local asset path " + string + " from local storage for remote path " + next + ".");
                        concurrentHashMap.put(next, string);
                    }
                }
            } catch (Exception e2) {
                AppboyLogger.e(a, "Encountered unexpected exception while parsing stored triggered action local assets.", e2);
            }
        }
        return concurrentHashMap;
    }

    /* access modifiers changed from: package-private */
    public File b() {
        return new File(this.b.getCacheDir().getPath() + "/" + "ab_triggers");
    }
}
