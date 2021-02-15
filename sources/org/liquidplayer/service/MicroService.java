package org.liquidplayer.service;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import com.google.common.net.HttpHeaders;
import com.tapstream.sdk.http.RequestBuilders;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import kotlinx.coroutines.DebugKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.liquidplayer.javascript.JSContext;
import org.liquidplayer.javascript.JSException;
import org.liquidplayer.javascript.JSFunction;
import org.liquidplayer.javascript.JSON;
import org.liquidplayer.javascript.JSObject;
import org.liquidplayer.javascript.JSValue;
import org.liquidplayer.node.BuildConfig;
import org.liquidplayer.node.Process;

public class MicroService implements Process.EventListener {
    private static final Map<String, MicroService> serviceMap = Collections.synchronizedMap(new HashMap());
    private static final Object serviceMapMutex = new Object();
    private final Context androidCtx;
    private String[] argv;
    private JSObject emitter;
    private ServiceErrorListener errorListener;
    private ServiceExitListener exitListener;
    private final Map<String, Map<EventListener, JSFunction>> listeners;
    private final Object listenersMutex;
    private String module;
    private Process process;
    /* access modifiers changed from: private */
    public JSFunction safeStringify;
    private String serviceId;
    private final URI serviceURI;
    private ServiceStartListener startListener;
    private boolean started;
    private final String uuid;

    public interface EventListener {
        void onEvent(MicroService microService, String str, JSONObject jSONObject);
    }

    public interface ServiceErrorListener {
        void onError(MicroService microService, Exception exc);
    }

    public interface ServiceExitListener {
        void onExit(MicroService microService, Integer num);
    }

    public interface ServiceStartListener {
        void onStart(MicroService microService);
    }

    private static void IGNORE_RESULT(boolean z) {
    }

    public class ServiceAlreadyStartedError extends RuntimeException {
        public ServiceAlreadyStartedError() {
        }
    }

    public static URI DevServer(String str, Integer num) {
        if (str == null) {
            str = "liquid.js";
        }
        if (num == null) {
            num = 8082;
        }
        if (str.endsWith(".js")) {
            str = str.substring(0, str.length() - 3);
        }
        if (!str.endsWith(".bundle")) {
            str = str.concat(".bundle");
        }
        return URI.create("http://10.0.2.2:" + num + "/" + str + "?platform=android&dev=true");
    }

    public static URI DevServer() {
        return DevServer((String) null, (Integer) null);
    }

    public MicroService(Context context, URI uri, ServiceStartListener serviceStartListener, ServiceErrorListener serviceErrorListener, ServiceExitListener serviceExitListener) {
        this.started = false;
        this.listeners = Collections.synchronizedMap(new HashMap());
        this.listenersMutex = new Object();
        this.serviceURI = uri;
        try {
            this.serviceId = URLEncoder.encode(uri.toString().substring(0, uri.toString().lastIndexOf(47)), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("MicrosService", e.toString());
        }
        this.startListener = serviceStartListener;
        this.exitListener = serviceExitListener;
        this.errorListener = serviceErrorListener;
        this.androidCtx = context;
        String uuid2 = UUID.randomUUID().toString();
        this.uuid = uuid2;
        serviceMap.put(uuid2, this);
    }

    public MicroService(Context context, URI uri, ServiceStartListener serviceStartListener, ServiceErrorListener serviceErrorListener) {
        this(context, uri, serviceStartListener, serviceErrorListener, (ServiceExitListener) null);
    }

    public MicroService(Context context, URI uri, ServiceStartListener serviceStartListener) {
        this(context, uri, serviceStartListener, (ServiceErrorListener) null);
    }

    public MicroService(Context context, URI uri) {
        this(context, uri, (ServiceStartListener) null);
    }

    public void addEventListener(String str, EventListener eventListener) {
        JSObject jSObject = this.emitter;
        if (jSObject != null) {
            if (this.safeStringify == null) {
                this.safeStringify = new JSFunction(jSObject.getContext(), "safeStringify", "   function serializer(replacer, cycleReplacer) {\n       var stack = [], keys = []\n       if (cycleReplacer == null) cycleReplacer = function(key, value) {\n           if (stack[0] === value) return '[Circular ~]'\n           return'[Circular ~.' + keys.slice(0, stack.indexOf(value)).join('.')+']'\n       }\n       return function(key, value) {\n           if (stack.length > 0) {\n               var thisPos = stack.indexOf(this)\n               ~thisPos ? stack.splice(thisPos + 1) : stack.push(this)\n               ~thisPos ? keys.splice(thisPos, Infinity, key) : keys.push(key)\n               if (~stack.indexOf(value))value=cycleReplacer.call(this, key, value)\n           }\n           else stack.push(value)\n\n           if (typeof value === 'function') {\n               value = {};\n           }\n\n           return replacer == null ? value : replacer.call(this, key, value)\n       }\n   }\n   return JSON.stringify(obj, serializer(replacer, cycleReplacer), spaces)\n", "obj", "replacer", "spaces", "cycleReplacer");
            }
            final EventListener eventListener2 = eventListener;
            final String str2 = str;
            AnonymousClass1 r5 = new JSFunction(this.emitter.getContext(), DebugKt.DEBUG_PROPERTY_VALUE_ON) {
                public void on(JSValue jSValue) throws JSONException {
                    JSONObject jSONObject;
                    JSONObject jSONObject2 = null;
                    if (jSValue != null && !jSValue.isNull().booleanValue() && !jSValue.isUndefined().booleanValue()) {
                        if (jSValue.isArray().booleanValue()) {
                            JSONArray jSONArray = new JSONArray(MicroService.this.safeStringify.call((JSObject) null, jSValue).toString());
                            jSONObject2 = new JSONObject();
                            jSONObject2.put("_", jSONArray);
                        } else if (jSValue.isNumber().booleanValue()) {
                            jSONObject2 = new JSONObject();
                            jSONObject2.put("_", jSValue.toNumber());
                        } else if (jSValue.isBoolean().booleanValue()) {
                            jSONObject2 = new JSONObject();
                            jSONObject2.put("_", jSValue.toBoolean());
                        } else {
                            if (jSValue.isDate().booleanValue()) {
                                jSONObject = new JSONObject();
                                jSONObject.put("_", MicroService.this.safeStringify.call((JSObject) null, jSValue).toString());
                            } else if (jSValue.isObject().booleanValue()) {
                                jSONObject = new JSONObject(MicroService.this.safeStringify.call((JSObject) null, jSValue).toString());
                            } else {
                                jSONObject2 = new JSONObject();
                                jSONObject2.put("_", jSValue.toString());
                            }
                            jSONObject2 = jSONObject;
                        }
                    }
                    eventListener2.onEvent(MicroService.this, str2, jSONObject2);
                }
            };
            synchronized (this.listenersMutex) {
                Map map = this.listeners.get(str);
                if (map == null) {
                    map = Collections.synchronizedMap(new HashMap());
                    this.listeners.put(str, map);
                }
                map.put(eventListener, r5);
            }
            this.emitter.property(DebugKt.DEBUG_PROPERTY_VALUE_ON).toFunction().call(this.emitter, str, r5);
        }
    }

    public String getId() {
        return this.uuid;
    }

    public Process getProcess() {
        return this.process;
    }

    public URI getServiceURI() {
        return this.serviceURI;
    }

    public void removeEventListener(String str, EventListener eventListener) {
        Map map;
        JSFunction jSFunction;
        synchronized (this.listenersMutex) {
            map = this.listeners.get(str);
        }
        if (map != null) {
            synchronized (this.listenersMutex) {
                jSFunction = (JSFunction) map.get(eventListener);
                if (jSFunction != null) {
                    map.remove(eventListener);
                    if (map.size() == 0) {
                        this.listeners.remove(str);
                    }
                }
            }
            JSObject jSObject = this.emitter;
            if (jSObject != null) {
                jSObject.property("removeListener").toFunction().call(this.emitter, str, jSFunction);
            }
        }
    }

    public void emit(String str, JSONObject jSONObject) {
        JSValue jSValue;
        JSObject jSObject = this.emitter;
        if (jSObject != null) {
            if (jSONObject != null) {
                jSValue = JSON.parse(jSObject.getContext(), jSONObject.toString());
            } else {
                jSValue = new JSValue(jSObject.getContext());
            }
            this.emitter.property("emit").toFunction().call(this.emitter, str, jSValue);
        }
    }

    public void emit(String str) {
        emit(str, (JSONObject) null);
    }

    public void emit(String str, Integer num) {
        JSObject jSObject = this.emitter;
        if (jSObject != null) {
            jSObject.property("emit").toFunction().call(this.emitter, str, num);
        }
    }

    public void emit(String str, Long l) {
        JSObject jSObject = this.emitter;
        if (jSObject != null) {
            jSObject.property("emit").toFunction().call(this.emitter, str, l);
        }
    }

    public void emit(String str, Float f) {
        JSObject jSObject = this.emitter;
        if (jSObject != null) {
            jSObject.property("emit").toFunction().call(this.emitter, str, f);
        }
    }

    public void emit(String str, Double d) {
        JSObject jSObject = this.emitter;
        if (jSObject != null) {
            jSObject.property("emit").toFunction().call(this.emitter, str, d);
        }
    }

    public void emit(String str, String str2) {
        JSObject jSObject = this.emitter;
        if (jSObject != null) {
            jSObject.property("emit").toFunction().call(this.emitter, str, str2);
        }
    }

    public void emit(String str, Boolean bool) {
        JSObject jSObject = this.emitter;
        if (jSObject != null) {
            jSObject.property("emit").toFunction().call(this.emitter, str, bool);
        }
    }

    public void emit(String str, JSONArray jSONArray) {
        JSValue jSValue;
        JSObject jSObject = this.emitter;
        if (jSObject != null) {
            if (jSONArray != null) {
                jSValue = JSON.parse(jSObject.getContext(), jSONArray.toString());
            } else {
                jSValue = new JSValue(jSObject.getContext());
            }
            this.emitter.property("emit").toFunction().call(this.emitter, str, jSValue);
        }
    }

    public synchronized void start(String... strArr) {
        if (!this.started) {
            this.started = true;
            this.argv = strArr;
            this.process = new Process(this.androidCtx, this.serviceId, 3, this);
        } else {
            throw new ServiceAlreadyStartedError();
        }
    }

    public static void uninstall(Context context, URI uri) {
        try {
            Process.uninstall(context, URLEncoder.encode(uri.toString().substring(0, uri.toString().lastIndexOf(47)), "UTF-8"), Process.UninstallScope.Global);
        } catch (UnsupportedEncodingException e) {
            Log.e("MicrosService", e.toString());
        }
    }

    private File getModulePath() {
        return new File(this.androidCtx.getFilesDir().getAbsolutePath() + ("/__org.liquidplayer.node__/_" + this.serviceId) + "/module");
    }

    private File getNodeModulesPath() {
        return new File(this.androidCtx.getFilesDir().getAbsolutePath() + ("/__org.liquidplayer.node__/_" + this.serviceId) + "/node_modules");
    }

    private void fetchService() throws IOException {
        InputStream inputStream;
        String path = this.serviceURI.getPath();
        String substring = path.substring(path.lastIndexOf(47) + 1);
        this.module = substring;
        if (!substring.endsWith(".js")) {
            this.module += ".js";
        }
        File file = new File(getModulePath().getAbsolutePath() + "/" + this.module);
        long lastModified = file.lastModified();
        String scheme = this.serviceURI.getScheme();
        InputStream inputStream2 = null;
        if ("http".equals(scheme) || RequestBuilders.DEFAULT_SCHEME.equals(scheme)) {
            HttpURLConnection httpURLConnection = (HttpURLConnection) this.serviceURI.toURL().openConnection();
            httpURLConnection.setReadTimeout(10000);
            if (lastModified > 0) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.US);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                httpURLConnection.setRequestProperty(HttpHeaders.IF_MODIFIED_SINCE, simpleDateFormat.format(new Date(lastModified)) + " GMT");
            }
            httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, "gzip");
            String str = "LiquidCore/" + BuildConfig.VERSION_NAME + " (" + ("Android; API " + Build.VERSION.SDK_INT) + ")";
            Log.d("MicroService", "User-Agent : " + str);
            httpURLConnection.setRequestProperty("User-Agent", str);
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                if (httpURLConnection.getHeaderField("Content-Encoding") == null || !httpURLConnection.getHeaderField("Content-Encoding").equals("gzip")) {
                    inputStream2 = httpURLConnection.getInputStream();
                } else {
                    inputStream2 = new GZIPInputStream(httpURLConnection.getInputStream());
                }
            } else if (responseCode != 304) {
                Log.e("FileNotFound", "responseCode = " + responseCode);
                throw new FileNotFoundException();
            }
            inputStream = inputStream2;
        } else if ("jarfile".equals(scheme)) {
            int lastIndexOf = this.serviceURI.getPath().lastIndexOf("/");
            ClassLoader classLoader = getClass().getClassLoader();
            if (classLoader != null) {
                inputStream = classLoader.getResourceAsStream(this.serviceURI.getPath().substring(lastIndexOf + 1));
            } else {
                throw new IOException("Failed to get class loader");
            }
        } else if (!"file".equals(scheme) || !path.startsWith("/android_asset/")) {
            inputStream = this.androidCtx.getContentResolver().openInputStream(Uri.parse(this.serviceURI.toString()));
        } else {
            inputStream = this.androidCtx.getAssets().open(path.substring(15));
        }
        if (inputStream != null) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[16384];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    inputStream.close();
                    return;
                }
            }
        }
    }

    private static String canon(String str) {
        String replaceAll = str.replaceAll("[^a-zA-Z0-9]", "*");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        boolean z = true;
        while (i < replaceAll.length()) {
            int i2 = i + 1;
            if (replaceAll.substring(i, i2).equals("*")) {
                z = true;
            } else {
                char charAt = replaceAll.charAt(i);
                if (z && charAt >= 'a' && charAt <= 'z') {
                    charAt = (char) ((charAt + 'A') - 97);
                }
                sb.append(charAt);
                z = false;
            }
            i = i2;
        }
        return sb.toString();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ae, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        r9.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00cf, code lost:
        r14 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00d1, code lost:
        r14 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fa, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00fb, code lost:
        r14.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00ff, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0100, code lost:
        r14.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0104, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0105, code lost:
        r14.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0109, code lost:
        android.util.Log.e("LiquidCore AddOn", "" + r14.getCanonicalName() + " must be public.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0125, code lost:
        android.util.Log.e("LiquidCore AddOn", "" + r14.getCanonicalName() + " must have a public constructor that takes os.android.Context as its lone parameter.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0141, code lost:
        android.util.Log.e("bindings", "Class org.liquidplayer.addon." + r4 + " not found.");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x015d, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x015e, code lost:
        android.util.Log.e("LiquidCore.bindings", r14.stack());
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[ExcHandler: IllegalAccessException (unused java.lang.IllegalAccessException), SYNTHETIC, Splitter:B:18:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:31:? A[ExcHandler: NoSuchMethodException (unused java.lang.NoSuchMethodException), SYNTHETIC, Splitter:B:18:0x006a] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00fa A[ExcHandler: IOException (r14v17 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:13:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ff A[ExcHandler: InstantiationException (r14v16 'e' java.lang.InstantiationException A[CUSTOM_DECLARE]), Splitter:B:13:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0104 A[ExcHandler: InvocationTargetException (r14v15 'e' java.lang.reflect.InvocationTargetException A[CUSTOM_DECLARE]), Splitter:B:13:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[ExcHandler: ClassNotFoundException (unused java.lang.ClassNotFoundException), SYNTHETIC, Splitter:B:13:0x003f] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x015d A[ExcHandler: JSException (r14v3 'e' org.liquidplayer.javascript.JSException A[CUSTOM_DECLARE]), Splitter:B:13:0x003f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.liquidplayer.javascript.JSValue bindings(org.liquidplayer.javascript.JSContext r13, java.lang.String r14, org.liquidplayer.javascript.JSFunction r15) {
        /*
            r12 = this;
            java.lang.String r0 = "LiquidCore AddOn"
            java.lang.String r1 = "LiquidCore.bindings"
            r2 = 47
            int r2 = r14.lastIndexOf(r2)
            r3 = 1
            int r2 = r2 + r3
            java.lang.String r2 = r14.substring(r2)
            r4 = 46
            int r4 = r2.lastIndexOf(r4)
            java.lang.String r5 = ""
            if (r4 < 0) goto L_0x0021
            int r6 = r4 + 1
            java.lang.String r6 = r2.substring(r6)
            goto L_0x0022
        L_0x0021:
            r6 = r5
        L_0x0022:
            r7 = 0
            if (r4 >= 0) goto L_0x0027
            r4 = r2
            goto L_0x002b
        L_0x0027:
            java.lang.String r4 = r2.substring(r7, r4)
        L_0x002b:
            java.lang.String r8 = "node"
            boolean r6 = r8.equals(r6)
            r8 = 0
            if (r6 != 0) goto L_0x003d
            java.lang.Object[] r13 = new java.lang.Object[r3]
            r13[r7] = r14
            org.liquidplayer.javascript.JSValue r13 = r15.call(r8, r13)
            return r13
        L_0x003d:
            java.lang.Class<org.liquidplayer.service.AddOn> r14 = org.liquidplayer.service.AddOn.class
            java.lang.Class r6 = r12.getClass()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.ClassLoader r6 = r6.getClassLoader()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            if (r6 == 0) goto L_0x00f2
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r9.<init>()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r10 = "org.liquidplayer.addon."
            r9.append(r10)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r10 = canon(r4)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r9.append(r10)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r9 = r9.toString()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.Class r6 = r6.loadClass(r9)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.Class<org.liquidplayer.service.AddOn> r9 = org.liquidplayer.service.AddOn.class
            boolean r9 = r9.isAssignableFrom(r6)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            if (r9 == 0) goto L_0x00d3
            java.lang.Class[] r14 = new java.lang.Class[r3]     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.Class<android.content.Context> r9 = android.content.Context.class
            r14[r7] = r9     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.reflect.Constructor r14 = r6.getConstructor(r14)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            android.content.Context r10 = r12.androidCtx     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r9[r7] = r10     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.Object r14 = r14.newInstance(r9)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            org.liquidplayer.service.AddOn r14 = (org.liquidplayer.service.AddOn) r14     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r14.register(r4)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.io.File r9 = new java.io.File     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r10.<init>()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.io.File r11 = r12.getNodeModulesPath()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r10.append(r11)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r11 = "/"
            r10.append(r11)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r10.append(r2)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r10 = r10.toString()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r9.<init>(r10)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            boolean r10 = r9.exists()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            if (r10 != 0) goto L_0x00b2
            boolean r9 = r9.createNewFile()     // Catch:{ IOException -> 0x00ae, JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff }
            IGNORE_RESULT(r9)     // Catch:{ IOException -> 0x00ae, JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff }
            goto L_0x00b2
        L_0x00ae:
            r9 = move-exception
            r9.printStackTrace()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
        L_0x00b2:
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r9.<init>()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r10 = "/home/node_modules/"
            r9.append(r10)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r9.append(r2)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r2 = r9.toString()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r3[r7] = r2     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            org.liquidplayer.javascript.JSValue r15 = r15.call(r8, r3)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r14.require(r15, r12)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x00d1, IllegalAccessException -> 0x00cf, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            return r15
        L_0x00cf:
            r14 = r6
            goto L_0x0109
        L_0x00d1:
            r14 = r6
            goto L_0x0125
        L_0x00d3:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r15.<init>()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r2 = "Class "
            r15.append(r2)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r2 = r6.getCanonicalName()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            r15.append(r2)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r2 = " does not implement org.liquidplayer.service.AddOn"
            r15.append(r2)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r15 = r15.toString()     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            android.util.Log.e(r1, r15)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            goto L_0x0165
        L_0x00f2:
            java.io.IOException r15 = new java.io.IOException     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            java.lang.String r2 = "Failed to get class loader"
            r15.<init>(r2)     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
            throw r15     // Catch:{ JSException -> 0x015d, ClassNotFoundException -> 0x0141, NoSuchMethodException -> 0x0125, IllegalAccessException -> 0x0109, InvocationTargetException -> 0x0104, InstantiationException -> 0x00ff, IOException -> 0x00fa }
        L_0x00fa:
            r14 = move-exception
            r14.printStackTrace()
            goto L_0x0165
        L_0x00ff:
            r14 = move-exception
            r14.printStackTrace()
            goto L_0x0165
        L_0x0104:
            r14 = move-exception
            r14.printStackTrace()
            goto L_0x0165
        L_0x0109:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r5)
            java.lang.String r14 = r14.getCanonicalName()
            r15.append(r14)
            java.lang.String r14 = " must be public."
            r15.append(r14)
            java.lang.String r14 = r15.toString()
            android.util.Log.e(r0, r14)
            goto L_0x0165
        L_0x0125:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r5)
            java.lang.String r14 = r14.getCanonicalName()
            r15.append(r14)
            java.lang.String r14 = " must have a public constructor that takes os.android.Context as its lone parameter."
            r15.append(r14)
            java.lang.String r14 = r15.toString()
            android.util.Log.e(r0, r14)
            goto L_0x0165
        L_0x0141:
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Class org.liquidplayer.addon."
            r14.append(r15)
            r14.append(r4)
            java.lang.String r15 = " not found."
            r14.append(r15)
            java.lang.String r14 = r14.toString()
            java.lang.String r15 = "bindings"
            android.util.Log.e(r15, r14)
            goto L_0x0165
        L_0x015d:
            r14 = move-exception
            java.lang.String r14 = r14.stack()
            android.util.Log.e(r1, r14)
        L_0x0165:
            org.liquidplayer.javascript.JSValue r14 = new org.liquidplayer.javascript.JSValue
            r14.<init>(r13)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.liquidplayer.service.MicroService.bindings(org.liquidplayer.javascript.JSContext, java.lang.String, org.liquidplayer.javascript.JSFunction):org.liquidplayer.javascript.JSValue");
    }

    public void onProcessStart(Process process2, JSContext jSContext) {
        this.emitter = jSContext.evaluateScript("(()=>{\n   class LiquidCore extends require('events') {}\n   global.LiquidCore = new LiquidCore();\n   return global.LiquidCore;})()").toObject();
        final JSValue property = jSContext.property("require");
        AnonymousClass2 r2 = new JSFunction(jSContext, "bindings") {
            public JSValue bindings(String str) {
                return MicroService.this.bindings(this.context, str, property.toFunction());
            }
        };
        r2.prototype(property);
        jSContext.property("require", r2);
        try {
            fetchService();
            if (this.startListener != null) {
                this.startListener.onStart(this);
                this.startListener = null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add("node");
            arrayList.add("/home/module/" + this.module);
            if (this.argv != null) {
                arrayList.addAll(Arrays.asList(this.argv));
            }
            jSContext.property("process").toObject().property("argv", arrayList);
            try {
                jSContext.evaluateScript("(()=>{  const fs = require('fs'), vm = require('vm');   (new vm.Script(fs.readFileSync('/home/module/" + this.module + "'),      {filename: '" + this.module + "'} )).runInThisContext();})()");
            } catch (JSException e) {
                Log.e("JSEXCEPTION", "Unhandled exception: " + e.getError().toString());
                Log.e("JSEXCEPTION", e.getError().stack());
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            onProcessFailed((Process) null, e2);
        }
    }

    private void clean() {
        this.exitListener = null;
        this.errorListener = null;
        this.emitter = null;
        synchronized (serviceMapMutex) {
            Iterator<Map.Entry<String, MicroService>> it = serviceMap.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry next = it.next();
                if (next.getValue() == this) {
                    serviceMap.remove(next.getKey());
                    this.process.removeEventListener(this);
                    break;
                }
            }
        }
        this.process = null;
    }

    public void onProcessAboutToExit(Process process2, int i) {
        onProcessExit(process2, i);
    }

    public void onProcessExit(Process process2, int i) {
        ServiceExitListener serviceExitListener = this.exitListener;
        if (serviceExitListener != null) {
            serviceExitListener.onExit(this, Integer.valueOf(i));
        }
        clean();
    }

    public void onProcessFailed(Process process2, Exception exc) {
        ServiceErrorListener serviceErrorListener = this.errorListener;
        if (serviceErrorListener != null) {
            serviceErrorListener.onError(this, exc);
        }
        clean();
    }

    public static MicroService getService(String str) {
        MicroService microService;
        synchronized (serviceMapMutex) {
            microService = serviceMap.get(str);
        }
        return microService;
    }
}
