package bolts;

import android.content.Context;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import bolts.AppLink;
import com.facebook.appevents.AppEventsConstants;
import com.google.common.net.HttpHeaders;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebViewAppLinkResolver implements AppLinkResolver {
    private static final String KEY_AL_VALUE = "value";
    private static final String KEY_ANDROID = "android";
    private static final String KEY_APP_NAME = "app_name";
    private static final String KEY_CLASS = "class";
    private static final String KEY_PACKAGE = "package";
    private static final String KEY_SHOULD_FALLBACK = "should_fallback";
    private static final String KEY_URL = "url";
    private static final String KEY_WEB = "web";
    private static final String KEY_WEB_URL = "url";
    private static final String META_TAG_PREFIX = "al";
    private static final String PREFER_HEADER = "Prefer-Html-Meta-Tags";
    private static final String TAG_EXTRACTION_JAVASCRIPT = "javascript:boltsWebViewAppLinkResolverResult.setValue((function() {  var metaTags = document.getElementsByTagName('meta');  var results = [];  for (var i = 0; i < metaTags.length; i++) {    var property = metaTags[i].getAttribute('property');    if (property && property.substring(0, 'al:'.length) === 'al:') {      var tag = { \"property\": metaTags[i].getAttribute('property') };      if (metaTags[i].hasAttribute('content')) {        tag['content'] = metaTags[i].getAttribute('content');      }      results.push(tag);    }  }  return JSON.stringify(results);})())";
    /* access modifiers changed from: private */
    public final Context context;

    public WebViewAppLinkResolver(Context context2) {
        this.context = context2;
    }

    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        final Capture capture = new Capture();
        final Capture capture2 = new Capture();
        return Task.callInBackground(new Callable<Void>() {
            public Void call() throws Exception {
                URL url = new URL(uri.toString());
                URLConnection uRLConnection = null;
                while (url != null) {
                    uRLConnection = url.openConnection();
                    boolean z = uRLConnection instanceof HttpURLConnection;
                    if (z) {
                        ((HttpURLConnection) uRLConnection).setInstanceFollowRedirects(true);
                    }
                    uRLConnection.setRequestProperty(WebViewAppLinkResolver.PREFER_HEADER, WebViewAppLinkResolver.META_TAG_PREFIX);
                    uRLConnection.connect();
                    if (z) {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
                        if (httpURLConnection.getResponseCode() >= 300 && httpURLConnection.getResponseCode() < 400) {
                            URL url2 = new URL(httpURLConnection.getHeaderField(HttpHeaders.LOCATION));
                            httpURLConnection.disconnect();
                            url = url2;
                        }
                    }
                    url = null;
                }
                try {
                    capture.set(WebViewAppLinkResolver.readFromConnection(uRLConnection));
                    capture2.set(uRLConnection.getContentType());
                    return null;
                } finally {
                    if (uRLConnection instanceof HttpURLConnection) {
                        ((HttpURLConnection) uRLConnection).disconnect();
                    }
                }
            }
        }).onSuccessTask(new Continuation<Void, Task<JSONArray>>() {
            public Task<JSONArray> then(Task<Void> task) throws Exception {
                final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                WebView webView = new WebView(WebViewAppLinkResolver.this.context);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setNetworkAvailable(false);
                webView.setWebViewClient(new WebViewClient() {
                    private boolean loaded = false;

                    private void runJavaScript(WebView webView) {
                        if (!this.loaded) {
                            this.loaded = true;
                            webView.loadUrl(WebViewAppLinkResolver.TAG_EXTRACTION_JAVASCRIPT);
                        }
                    }

                    public void onPageFinished(WebView webView, String str) {
                        super.onPageFinished(webView, str);
                        runJavaScript(webView);
                    }

                    public void onLoadResource(WebView webView, String str) {
                        super.onLoadResource(webView, str);
                        runJavaScript(webView);
                    }
                });
                webView.addJavascriptInterface(new Object() {
                    @JavascriptInterface
                    public void setValue(String str) {
                        try {
                            taskCompletionSource.trySetResult(new JSONArray(str));
                        } catch (JSONException e) {
                            taskCompletionSource.trySetError(e);
                        }
                    }
                }, "boltsWebViewAppLinkResolverResult");
                webView.loadDataWithBaseURL(uri.toString(), (String) capture.get(), capture2.get() != null ? ((String) capture2.get()).split(";")[0] : null, (String) null, (String) null);
                return taskCompletionSource.getTask();
            }
        }, Task.UI_THREAD_EXECUTOR).onSuccess(new Continuation<JSONArray, AppLink>() {
            public AppLink then(Task<JSONArray> task) throws Exception {
                return WebViewAppLinkResolver.makeAppLinkFromAlData(WebViewAppLinkResolver.parseAlData(task.getResult()), uri);
            }
        });
    }

    /* access modifiers changed from: private */
    public static Map<String, Object> parseAlData(JSONArray jSONArray) throws JSONException {
        Map hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String[] split = jSONObject.getString("property").split(":");
            if (split[0].equals(META_TAG_PREFIX)) {
                Map map = hashMap;
                int i2 = 1;
                while (true) {
                    Map map2 = null;
                    if (i2 >= split.length) {
                        break;
                    }
                    List list = (List) map.get(split[i2]);
                    if (list == null) {
                        list = new ArrayList();
                        map.put(split[i2], list);
                    }
                    if (list.size() > 0) {
                        map2 = (Map) list.get(list.size() - 1);
                    }
                    if (map2 == null || i2 == split.length - 1) {
                        map = new HashMap();
                        list.add(map);
                    } else {
                        map = map2;
                    }
                    i2++;
                }
                if (jSONObject.has("content")) {
                    if (jSONObject.isNull("content")) {
                        map.put("value", (Object) null);
                    } else {
                        map.put("value", jSONObject.getString("content"));
                    }
                }
            }
        }
        return hashMap;
    }

    private static List<Map<String, Object>> getAlList(Map<String, Object> map, String str) {
        List<Map<String, Object>> list = (List) map.get(str);
        return list == null ? Collections.emptyList() : list;
    }

    /* access modifiers changed from: private */
    public static AppLink makeAppLinkFromAlData(Map<String, Object> map, Uri uri) {
        Uri uri2;
        Map<String, Object> map2 = map;
        ArrayList arrayList = new ArrayList();
        List list = (List) map2.get("android");
        if (list == null) {
            list = Collections.emptyList();
        }
        Iterator it = list.iterator();
        while (true) {
            int i = 0;
            uri2 = null;
            if (!it.hasNext()) {
                break;
            }
            Map map3 = (Map) it.next();
            List<Map<String, Object>> alList = getAlList(map3, "url");
            List<Map<String, Object>> alList2 = getAlList(map3, "package");
            List<Map<String, Object>> alList3 = getAlList(map3, "class");
            List<Map<String, Object>> alList4 = getAlList(map3, "app_name");
            int max = Math.max(alList.size(), Math.max(alList2.size(), Math.max(alList3.size(), alList4.size())));
            while (i < max) {
                arrayList.add(new AppLink.Target((String) (alList2.size() > i ? alList2.get(i).get("value") : null), (String) (alList3.size() > i ? alList3.get(i).get("value") : null), tryCreateUrl((String) (alList.size() > i ? alList.get(i).get("value") : null)), (String) (alList4.size() > i ? alList4.get(i).get("value") : null)));
                i++;
            }
        }
        List list2 = (List) map2.get("web");
        if (list2 == null || list2.size() <= 0) {
            uri2 = uri;
        } else {
            Map map4 = (Map) list2.get(0);
            List list3 = (List) map4.get("url");
            List list4 = (List) map4.get(KEY_SHOULD_FALLBACK);
            if (list4 == null || list4.size() <= 0 || !Arrays.asList(new String[]{"no", "false", AppEventsConstants.EVENT_PARAM_VALUE_NO}).contains(((String) ((Map) list4.get(0)).get("value")).toLowerCase())) {
                uri2 = uri;
            }
            if (!(uri2 == null || list3 == null || list3.size() <= 0)) {
                uri2 = tryCreateUrl((String) ((Map) list3.get(0)).get("value"));
            }
        }
        return new AppLink(uri, arrayList, uri2);
    }

    private static Uri tryCreateUrl(String str) {
        if (str == null) {
            return null;
        }
        return Uri.parse(str);
    }

    /* access modifiers changed from: private */
    public static String readFromConnection(URLConnection uRLConnection) throws IOException {
        InputStream inputStream;
        int i;
        if (uRLConnection instanceof HttpURLConnection) {
            try {
                inputStream = uRLConnection.getInputStream();
            } catch (Exception unused) {
                inputStream = ((HttpURLConnection) uRLConnection).getErrorStream();
            }
        } else {
            inputStream = uRLConnection.getInputStream();
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                i = 0;
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String contentEncoding = uRLConnection.getContentEncoding();
            if (contentEncoding == null) {
                String[] split = uRLConnection.getContentType().split(";");
                int length = split.length;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String trim = split[i].trim();
                    if (trim.startsWith("charset=")) {
                        contentEncoding = trim.substring(8);
                        break;
                    }
                    i++;
                }
                if (contentEncoding == null) {
                    contentEncoding = "UTF-8";
                }
            }
            return new String(byteArrayOutputStream.toByteArray(), contentEncoding);
        } finally {
            inputStream.close();
        }
    }
}
