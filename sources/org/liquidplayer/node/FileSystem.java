package org.liquidplayer.node;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import org.liquidplayer.javascript.JSContext;
import org.liquidplayer.javascript.JSFunction;
import org.liquidplayer.javascript.JSObject;

/* compiled from: FIleSystem */
class FileSystem extends JSObject {
    private static final long SESSION_WATCHDOG_TIMER = 300000;
    /* access modifiers changed from: private */
    public static final ArrayList<String> activeSessions = new ArrayList<>();
    /* access modifiers changed from: private */
    public static long lastSessionBark = 0;
    private static final Object lock = new Object();
    /* access modifiers changed from: private */
    public static final Object sessionMutex = new Object();
    private final Context androidCtx;
    private final String sessionID = UUID.randomUUID().toString();
    private final String uniqueID;

    /* compiled from: FIleSystem */
    private class JSBuilder {
        private StringBuilder js;

        private JSBuilder() {
            this.js = new StringBuilder();
        }

        private void realDir(String str) {
            this.js.append("(function(){try {return fs.realpathSync('");
            this.js.append(str);
            this.js.append("');}catch(e){}})()");
        }

        private void alias(String str, String str2, int i) {
            this.js.append("fs_.aliases_['");
            this.js.append(str);
            this.js.append("']=");
            realDir(str2);
            this.js.append(";fs_.access_['");
            this.js.append(str);
            this.js.append("']=");
            this.js.append(i);
            this.js.append(";");
        }

        /* access modifiers changed from: private */
        public void mkdir(String str, String str2, int i) {
            if (new File(str2).mkdirs()) {
                Log.i("mkdir", "Created directory " + str2);
            }
            alias(str, str2, i);
        }

        /* access modifiers changed from: private */
        public void symlink(String str, String str2, String str3, int i) {
            this.js.append("(function(){fs.symlinkSync('");
            this.js.append(str2);
            this.js.append("','");
            this.js.append(str3);
            this.js.append("');})();");
            alias(str, str2, i);
        }

        /* access modifiers changed from: private */
        public void mkdirAndSymlink(String str, String str2, String str3, int i) {
            if (new File(str2).mkdirs()) {
                Log.i("mkdir", "Created directory " + str2);
            }
            symlink(str, str2, str3, i);
        }

        /* access modifiers changed from: private */
        public void linkMedia(String str, String str2, String str3, int i) {
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(str);
            if (externalStoragePublicDirectory.mkdirs()) {
                Log.i("linkMedia", "Created external directory " + externalStoragePublicDirectory);
            }
            this.js.append("{var m=");
            realDir(externalStoragePublicDirectory.getAbsolutePath());
            this.js.append(";");
            this.js.append("if(m){");
            this.js.append("(function(){fs.symlinkSync(m,'");
            this.js.append(str3);
            this.js.append("/public/media/");
            this.js.append(str2);
            this.js.append("');})();");
            this.js.append("fs_.aliases_['");
            this.js.append("/home/public/media/");
            this.js.append(str2);
            this.js.append("']=m;fs_.access_['");
            this.js.append("/home/public/media/");
            this.js.append(str2);
            this.js.append("']=");
            this.js.append(i);
            this.js.append(";}}");
        }

        /* access modifiers changed from: private */
        public void append(String str) {
            this.js.append(str);
        }

        /* access modifiers changed from: private */
        public String build() {
            return this.js.toString();
        }
    }

    private static boolean isSymlink(File file) throws IOException {
        if (file != null) {
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            return !file.getCanonicalFile().equals(file.getAbsoluteFile());
        }
        throw new NullPointerException("File must not be null");
    }

    private static void deleteRecursive(File file) {
        try {
            if (file.isDirectory() && !isSymlink(file) && file.listFiles() != null) {
                for (File deleteRecursive : file.listFiles()) {
                    deleteRecursive(deleteRecursive);
                }
            }
        } catch (IOException e) {
            Log.e("deleteRecursive", e.getMessage());
        }
        if (!file.delete()) {
            Log.e("deleteRecursive", "Failed to delete " + file.getAbsolutePath());
        }
    }

    private void setUp(JSBuilder jSBuilder, int i) {
        String str = "/__org.liquidplayer.node__/_" + this.uniqueID;
        String str2 = this.androidCtx.getCacheDir().getAbsolutePath() + ("/__org.liquidplayer.node__/sessions/" + this.sessionID);
        String str3 = this.androidCtx.getCacheDir().getAbsolutePath() + str;
        String str4 = this.androidCtx.getFilesDir().getAbsolutePath() + str;
        jSBuilder.mkdir("/home", str2 + "/home", 1);
        if (new File(str4 + "/module").mkdirs()) {
            Log.i("FileSystem", "Created directory " + str3 + "/module");
        }
        jSBuilder.symlink("/home/module", str4 + "/module", str2 + "/home/module", 1);
        jSBuilder.mkdirAndSymlink("/home/temp", str2 + "/temp", str2 + "/home/temp", 3);
        jSBuilder.mkdirAndSymlink("/home/cache", str3 + "/cache", str2 + "/home/cache", 3);
        jSBuilder.mkdirAndSymlink("/home/local", str4 + "/local", str2 + "/home/local", 3);
        if (new File(str4 + "/node_modules").mkdirs()) {
            Log.i("FileSystem", "Created directory " + str3 + "/node_module");
        }
        jSBuilder.symlink("/home/node_modules", str4 + "/node_modules", str2 + "/home/node_modules", 1);
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState) || "shared".equals(externalStorageState)) {
            if ("mounted_ro".equals(externalStorageState) && (i & 2) != 0) {
                Log.w("FileSystem", "Warning: external storage is read only.");
                i &= -3;
            }
            if (!new File(str2 + "/home/public").mkdirs()) {
                Log.e("FileSystem", "Error: Failed to set up /home/public");
            }
            File externalFilesDir = this.androidCtx.getExternalFilesDir((String) null);
            if (externalFilesDir != null) {
                String str5 = externalFilesDir.getAbsolutePath() + "/LiquidPlayer/" + this.uniqueID;
                if (new File(str5).mkdirs()) {
                    Log.i("FileSystem", "Created external directory " + str5);
                }
                jSBuilder.symlink("/home/public/data", str5, str2 + "/home/public/data", 3);
            }
            if (!new File(str2 + "/home/public/media").mkdirs()) {
                Log.e("FileSystem", "Error: Failed to set up /home/public/media");
            }
            if (!((i & 2) == 0 || ContextCompat.checkSelfPermission(this.androidCtx, "android.permission.WRITE_EXTERNAL_STORAGE") == 0)) {
                i &= -3;
            }
            jSBuilder.linkMedia(Environment.DIRECTORY_MOVIES, "Movies", str2 + "/home", i);
            jSBuilder.linkMedia(Environment.DIRECTORY_PICTURES, "Pictures", str2 + "/home", i);
            jSBuilder.linkMedia(Environment.DIRECTORY_DCIM, "DCIM", str2 + "/home", i);
            jSBuilder.linkMedia(Environment.DIRECTORY_ALARMS, "Alarms", str2 + "/home", i);
            if (Build.VERSION.SDK_INT >= 19) {
                jSBuilder.linkMedia(Environment.DIRECTORY_DOCUMENTS, "Documents", str2 + "/home", i);
            }
            jSBuilder.linkMedia(Environment.DIRECTORY_DOWNLOADS, "Downloads", str2 + "/home", i);
            jSBuilder.linkMedia(Environment.DIRECTORY_MUSIC, "Music", str2 + "/home", i);
            jSBuilder.linkMedia(Environment.DIRECTORY_NOTIFICATIONS, "Notifications", str2 + "/home", i);
            jSBuilder.linkMedia(Environment.DIRECTORY_PODCASTS, "Podcasts", str2 + "/home", i);
            jSBuilder.linkMedia(Environment.DIRECTORY_RINGTONES, "Ringtones", str2 + "/home", i);
        } else {
            Log.w("FileSystem", "Warning: external storage is unavailable");
        }
        jSBuilder.append("fs_.cwd='/home';");
    }

    /* access modifiers changed from: private */
    public static void uninstallSession(Context context, String str) {
        synchronized (lock) {
            File file = new File(context.getCacheDir().getAbsolutePath() + ("/__org.liquidplayer.node__/sessions/" + str));
            Log.i("sessionWatchdog", "deleting session " + file);
            deleteRecursive(file);
        }
    }

    static void uninstallLocal(Context context, String str) {
        String str2 = "/__org.liquidplayer.node__/_" + str;
        deleteRecursive(new File(context.getCacheDir().getAbsolutePath() + str2));
        deleteRecursive(new File(context.getFilesDir().getAbsolutePath() + str2));
    }

    static void uninstallGlobal(Context context, String str) {
        File externalFilesDir = context.getExternalFilesDir((String) null);
        if (externalFilesDir != null) {
            deleteRecursive(new File(externalFilesDir.getAbsolutePath() + "/LiquidPlayer/" + str));
        }
    }

    FileSystem(JSContext jSContext, Context context, String str, int i) {
        super(jSContext);
        this.androidCtx = context;
        sessionWatchdog(context);
        this.uniqueID = str;
        synchronized (sessionMutex) {
            activeSessions.add(this.sessionID);
        }
        JSBuilder jSBuilder = new JSBuilder();
        jSBuilder.append("fs_.aliases_={};fs_.access_={};fs_.require=global.require;");
        setUp(jSBuilder, i);
        jSBuilder.append("fs_.fs=function(file){");
        jSBuilder.append("if (!file.startsWith('/')) { file = ''+this.cwd+'/'+file; }try { file = fs_.require('path').resolve(file); } catch (e) {console.log(e);}var access = 0;var keys = Object.keys(this.aliases_).sort().reverse();for (var p=0; p<keys.length; p++) {    if (file.startsWith(this.aliases_[keys[p]] + '/')) {        file = keys[p] + '/' + file.substring(this.aliases_[keys[p]].length + 1);        break;    } else if (file == this.aliases_[keys[p]]) {        file = keys[p];        break;    }}var acckeys = Object.keys(this.access_).sort().reverse();for (var p=0; p<acckeys.length; p++) {    if (file.startsWith(acckeys[p] + '/') || acckeys[p]==file) {        access = this.access_[acckeys[p]];        break;    }}var newfile = file;for (var p=0; p<keys.length; p++) {    if (file.startsWith(keys[p] + '/')) {        newfile = this.aliases_[keys[p]] +'/'+file.substring(keys[p].length + 1);        break;    } else if (file == keys[p]) {        newfile = this.aliases_[keys[p]];        break;    }}return [access,newfile];");
        jSBuilder.append("};");
        jSBuilder.append("fs_.alias=function(file){");
        jSBuilder.append("var keys = Object.keys(this.aliases_).sort().reverse();for (var p=0; p<keys.length; p++) {   if (file.startsWith(this.aliases_[keys[p]] + '/')) {       file = keys[p] + '/' + file.substring(this.aliases_[keys[p]].length + 1);       break;   } else if (file == this.aliases_[keys[p]]) {       file = keys[p];       break;   }}return file;");
        jSBuilder.append("};");
        new JSFunction(jSContext, "setup", new String[]{"fs_"}, jSBuilder.build(), (String) null, 0).call((JSObject) null, this);
    }

    private static void sessionWatchdog(final Context context) {
        if (new Date().getTime() - lastSessionBark > SESSION_WATCHDOG_TIMER) {
            new Thread() {
                public void run() {
                    boolean contains;
                    File[] listFiles = new File(context.getCacheDir().getAbsolutePath() + "/__org.liquidplayer.node__/sessions").listFiles();
                    if (listFiles != null) {
                        for (File file : listFiles) {
                            synchronized (FileSystem.sessionMutex) {
                                contains = FileSystem.activeSessions.contains(file.getName());
                            }
                            if (!contains) {
                                FileSystem.uninstallSession(context, file.getName());
                            }
                        }
                    }
                    long unused = FileSystem.lastSessionBark = new Date().getTime();
                }
            }.start();
        }
    }

    /* access modifiers changed from: package-private */
    public void cleanUp() {
        boolean contains;
        synchronized (sessionMutex) {
            contains = activeSessions.contains(this.sessionID);
            activeSessions.remove(this.sessionID);
        }
        if (contains) {
            uninstallSession(this.androidCtx, this.sessionID);
        }
    }
}
