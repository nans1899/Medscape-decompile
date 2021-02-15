package com.wbmd.qxcalculator.util.jsevaluator;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import org.apache.commons.io.IOUtils;

public class QxJsEvaluator {
    private static final String JS_NAMESPACE = "QxCalculateJsCallback";
    private static QxJsEvaluator mInstance;
    /* access modifiers changed from: private */
    public QxJsCallback callback;
    private Context context;
    private WebView webView;

    public interface QxJsCallback {
        void onResult(String str);
    }

    public static QxJsEvaluator getInstance() {
        if (mInstance == null) {
            mInstance = new QxJsEvaluator();
        }
        return mInstance;
    }

    public void setContext(Context context2) {
        this.context = context2;
        WebView webView2 = new WebView(context2);
        this.webView = webView2;
        webView2.setWillNotDraw(true);
        WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");
        this.webView.addJavascriptInterface(new QxJavaScriptInterface(this), JS_NAMESPACE);
        if (Build.VERSION.SDK_INT >= 19) {
            this.webView.loadUrl("");
        }
    }

    public void evaluate(String str, final QxJsCallback qxJsCallback) {
        Log.d("API", "js to eval " + str);
        if (Build.VERSION.SDK_INT >= 19) {
            this.webView.evaluateJavascript(str, new ValueCallback<String>() {
                /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x003c */
                /* JADX WARNING: Removed duplicated region for block: B:18:0x004a  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onReceiveValue(java.lang.String r5) {
                    /*
                        r4 = this;
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder
                        r0.<init>()
                        java.lang.String r1 = "JS RECEIVED "
                        r0.append(r1)
                        r0.append(r5)
                        java.lang.String r0 = r0.toString()
                        java.lang.String r1 = "TAG"
                        android.util.Log.d(r1, r0)
                        android.util.JsonReader r0 = new android.util.JsonReader
                        java.io.StringReader r1 = new java.io.StringReader
                        r1.<init>(r5)
                        r0.<init>(r1)
                        r1 = 1
                        r0.setLenient(r1)
                        r1 = 0
                        android.util.JsonToken r2 = r0.peek()     // Catch:{ IOException -> 0x003c, all -> 0x0042 }
                        android.util.JsonToken r3 = android.util.JsonToken.NULL     // Catch:{ IOException -> 0x003c, all -> 0x0042 }
                        if (r2 == r3) goto L_0x003c
                        android.util.JsonToken r2 = r0.peek()     // Catch:{ IOException -> 0x003c, all -> 0x0042 }
                        android.util.JsonToken r3 = android.util.JsonToken.STRING     // Catch:{ IOException -> 0x003c, all -> 0x0042 }
                        if (r2 != r3) goto L_0x003c
                        java.lang.String r2 = r0.nextString()     // Catch:{ IOException -> 0x003c, all -> 0x0042 }
                        if (r2 == 0) goto L_0x003c
                        r1 = r2
                    L_0x003c:
                        r0.close()     // Catch:{ IOException -> 0x0040 }
                        goto L_0x0047
                    L_0x0040:
                        goto L_0x0047
                    L_0x0042:
                        r5 = move-exception
                        r0.close()     // Catch:{ IOException -> 0x0046 }
                    L_0x0046:
                        throw r5
                    L_0x0047:
                        if (r1 != 0) goto L_0x004a
                        goto L_0x004b
                    L_0x004a:
                        r5 = r1
                    L_0x004b:
                        com.wbmd.qxcalculator.util.jsevaluator.QxJsEvaluator$QxJsCallback r0 = r5
                        java.lang.String r1 = "\\n"
                        java.lang.String r2 = "\n"
                        java.lang.String r5 = r5.replace(r1, r2)
                        r0.onResult(r5)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.util.jsevaluator.QxJsEvaluator.AnonymousClass1.onReceiveValue(java.lang.String):void");
                }
            });
            return;
        }
        String escapeCarriageReturn = escapeCarriageReturn(escapeTabs(escapeNewLines(escapeClosingScript(escapeDoubleQuotes(escapeSingleQuotes(escapeSlash(str)))))));
        this.callback = qxJsCallback;
        Log.d("API", "eval:\n" + escapeCarriageReturn);
        try {
            String encodeToString = Base64.encodeToString(("<script>javascript:QxCalculateJsCallback.returnResultToJava(eval('" + escapeCarriageReturn + "'));</script>").getBytes("UTF-8"), 0);
            WebView webView2 = this.webView;
            webView2.loadUrl("data:text/html;charset=utf-8;base64," + encodeToString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: package-private */
    public void jsCallFinished(final String str) {
        new Handler(this.context.getMainLooper()).post(new Runnable() {
            public void run() {
                QxJsCallback access$000 = QxJsEvaluator.this.callback;
                QxJsCallback unused = QxJsEvaluator.this.callback = null;
                Log.d("API", "eval response:\n" + str);
                Log.d("API", "eval 2 response:\n" + str.replace("\\n", IOUtils.LINE_SEPARATOR_UNIX));
                access$000.onResult(str.replace("\\n", IOUtils.LINE_SEPARATOR_UNIX));
            }
        });
    }

    public static String escapeCarriageReturn(String str) {
        return str.replace("\r", "\\r");
    }

    public static String escapeClosingScript(String str) {
        return str.replace("</", "<\\/");
    }

    public static String escapeNewLines(String str) {
        return str.replace(IOUtils.LINE_SEPARATOR_UNIX, "\\n");
    }

    public static String escapeTabs(String str) {
        return str.replace("\t", "\\t");
    }

    public static String escapeSingleQuotes(String str) {
        return str.replace("'", "\\'");
    }

    public static String escapeDoubleQuotes(String str) {
        return str.replace("\"", "\\\"");
    }

    public static String escapeSlash(String str) {
        return str.replace("\\", "\\\\");
    }
}
