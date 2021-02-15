package org.jsoup.helper;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.common.net.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import kotlin.text.Typography;
import net.bytebuddy.description.type.TypeDescription;
import org.apache.commons.io.IOUtils;
import org.jsoup.Connection;
import org.jsoup.UncheckedIOException;
import org.jsoup.internal.ConstrainableInputStream;
import org.jsoup.internal.Normalizer;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.parser.TokenQueue;

public class HttpConnection implements Connection {
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DEFAULT_UA = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36";
    private static final String DefaultUploadType = "application/octet-stream";
    public static final String FORM_URL_ENCODED = "application/x-www-form-urlencoded";
    private static final int HTTP_TEMP_REDIR = 307;
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String USER_AGENT = "User-Agent";
    private Connection.Request req = new Request();
    private Connection.Response res = new Response();

    public static Connection connect(String str) {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.url(str);
        return httpConnection;
    }

    public static Connection connect(URL url) {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.url(url);
        return httpConnection;
    }

    private static String encodeUrl(String str) {
        try {
            return encodeUrl(new URL(str)).toExternalForm();
        } catch (Exception unused) {
            return str;
        }
    }

    static URL encodeUrl(URL url) {
        try {
            return new URL(new URI(url.toExternalForm().replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20")).toASCIIString());
        } catch (MalformedURLException | URISyntaxException unused) {
            return url;
        }
    }

    /* access modifiers changed from: private */
    public static String encodeMimeName(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("\"", "%22");
    }

    public Connection url(URL url) {
        this.req.url(url);
        return this;
    }

    public Connection url(String str) {
        Validate.notEmpty(str, "Must supply a valid URL");
        try {
            this.req.url(new URL(encodeUrl(str)));
            return this;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed URL: " + str, e);
        }
    }

    public Connection proxy(Proxy proxy) {
        this.req.proxy(proxy);
        return this;
    }

    public Connection proxy(String str, int i) {
        this.req.proxy(str, i);
        return this;
    }

    public Connection userAgent(String str) {
        Validate.notNull(str, "User agent must not be null");
        this.req.header("User-Agent", str);
        return this;
    }

    public Connection timeout(int i) {
        this.req.timeout(i);
        return this;
    }

    public Connection maxBodySize(int i) {
        this.req.maxBodySize(i);
        return this;
    }

    public Connection followRedirects(boolean z) {
        this.req.followRedirects(z);
        return this;
    }

    public Connection referrer(String str) {
        Validate.notNull(str, "Referrer must not be null");
        this.req.header(HttpHeaders.REFERER, str);
        return this;
    }

    public Connection method(Connection.Method method) {
        this.req.method(method);
        return this;
    }

    public Connection ignoreHttpErrors(boolean z) {
        this.req.ignoreHttpErrors(z);
        return this;
    }

    public Connection ignoreContentType(boolean z) {
        this.req.ignoreContentType(z);
        return this;
    }

    public Connection data(String str, String str2) {
        this.req.data(KeyVal.create(str, str2));
        return this;
    }

    public Connection sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.req.sslSocketFactory(sSLSocketFactory);
        return this;
    }

    public Connection data(String str, String str2, InputStream inputStream) {
        this.req.data(KeyVal.create(str, str2, inputStream));
        return this;
    }

    public Connection data(String str, String str2, InputStream inputStream, String str3) {
        this.req.data(KeyVal.create(str, str2, inputStream).contentType(str3));
        return this;
    }

    public Connection data(Map<String, String> map) {
        Validate.notNull(map, "Data map must not be null");
        for (Map.Entry next : map.entrySet()) {
            this.req.data(KeyVal.create((String) next.getKey(), (String) next.getValue()));
        }
        return this;
    }

    public Connection data(String... strArr) {
        Validate.notNull(strArr, "Data key value pairs must not be null");
        Validate.isTrue(strArr.length % 2 == 0, "Must supply an even number of key value pairs");
        for (int i = 0; i < strArr.length; i += 2) {
            String str = strArr[i];
            String str2 = strArr[i + 1];
            Validate.notEmpty(str, "Data key must not be empty");
            Validate.notNull(str2, "Data value must not be null");
            this.req.data(KeyVal.create(str, str2));
        }
        return this;
    }

    public Connection data(Collection<Connection.KeyVal> collection) {
        Validate.notNull(collection, "Data collection must not be null");
        for (Connection.KeyVal data : collection) {
            this.req.data(data);
        }
        return this;
    }

    public Connection.KeyVal data(String str) {
        Validate.notEmpty(str, "Data key must not be empty");
        for (Connection.KeyVal next : request().data()) {
            if (next.key().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public Connection requestBody(String str) {
        this.req.requestBody(str);
        return this;
    }

    public Connection header(String str, String str2) {
        this.req.header(str, str2);
        return this;
    }

    public Connection headers(Map<String, String> map) {
        Validate.notNull(map, "Header map must not be null");
        for (Map.Entry next : map.entrySet()) {
            this.req.header((String) next.getKey(), (String) next.getValue());
        }
        return this;
    }

    public Connection cookie(String str, String str2) {
        this.req.cookie(str, str2);
        return this;
    }

    public Connection cookies(Map<String, String> map) {
        Validate.notNull(map, "Cookie map must not be null");
        for (Map.Entry next : map.entrySet()) {
            this.req.cookie((String) next.getKey(), (String) next.getValue());
        }
        return this;
    }

    public Connection parser(Parser parser) {
        this.req.parser(parser);
        return this;
    }

    public Document get() throws IOException {
        this.req.method(Connection.Method.GET);
        execute();
        return this.res.parse();
    }

    public Document post() throws IOException {
        this.req.method(Connection.Method.POST);
        execute();
        return this.res.parse();
    }

    public Connection.Response execute() throws IOException {
        Response execute = Response.execute(this.req);
        this.res = execute;
        return execute;
    }

    public Connection.Request request() {
        return this.req;
    }

    public Connection request(Connection.Request request) {
        this.req = request;
        return this;
    }

    public Connection.Response response() {
        return this.res;
    }

    public Connection response(Connection.Response response) {
        this.res = response;
        return this;
    }

    public Connection postDataCharset(String str) {
        this.req.postDataCharset(str);
        return this;
    }

    private static abstract class Base<T extends Connection.Base> implements Connection.Base<T> {
        Map<String, String> cookies;
        Map<String, List<String>> headers;
        Connection.Method method;
        URL url;

        private Base() {
            this.headers = new LinkedHashMap();
            this.cookies = new LinkedHashMap();
        }

        public URL url() {
            return this.url;
        }

        public T url(URL url2) {
            Validate.notNull(url2, "URL must not be null");
            this.url = url2;
            return this;
        }

        public Connection.Method method() {
            return this.method;
        }

        public T method(Connection.Method method2) {
            Validate.notNull(method2, "Method must not be null");
            this.method = method2;
            return this;
        }

        public String header(String str) {
            Validate.notNull(str, "Header name must not be null");
            List<String> headersCaseInsensitive = getHeadersCaseInsensitive(str);
            if (headersCaseInsensitive.size() > 0) {
                return StringUtil.join((Collection) headersCaseInsensitive, ", ");
            }
            return null;
        }

        public T addHeader(String str, String str2) {
            Validate.notEmpty(str);
            if (str2 == null) {
                str2 = "";
            }
            List headers2 = headers(str);
            if (headers2.isEmpty()) {
                headers2 = new ArrayList();
                this.headers.put(str, headers2);
            }
            headers2.add(fixHeaderEncoding(str2));
            return this;
        }

        public List<String> headers(String str) {
            Validate.notEmpty(str);
            return getHeadersCaseInsensitive(str);
        }

        private static String fixHeaderEncoding(String str) {
            try {
                byte[] bytes = str.getBytes("ISO-8859-1");
                if (!looksLikeUtf8(bytes)) {
                    return str;
                }
                return new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return str;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
            if ((((r8[1] & 255) == 187) & ((r8[2] & 255) == 191)) != false) goto L_0x002a;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static boolean looksLikeUtf8(byte[] r8) {
            /*
                int r0 = r8.length
                r1 = 3
                r2 = 1
                r3 = 0
                if (r0 < r1) goto L_0x0029
                byte r0 = r8[r3]
                r0 = r0 & 255(0xff, float:3.57E-43)
                r4 = 239(0xef, float:3.35E-43)
                if (r0 != r4) goto L_0x0029
                byte r0 = r8[r2]
                r0 = r0 & 255(0xff, float:3.57E-43)
                r4 = 187(0xbb, float:2.62E-43)
                if (r0 != r4) goto L_0x0018
                r0 = 1
                goto L_0x0019
            L_0x0018:
                r0 = 0
            L_0x0019:
                r4 = 2
                byte r4 = r8[r4]
                r4 = r4 & 255(0xff, float:3.57E-43)
                r5 = 191(0xbf, float:2.68E-43)
                if (r4 != r5) goto L_0x0024
                r4 = 1
                goto L_0x0025
            L_0x0024:
                r4 = 0
            L_0x0025:
                r0 = r0 & r4
                if (r0 == 0) goto L_0x0029
                goto L_0x002a
            L_0x0029:
                r1 = 0
            L_0x002a:
                int r0 = r8.length
            L_0x002b:
                if (r1 >= r0) goto L_0x0061
                byte r4 = r8[r1]
                r5 = r4 & 128(0x80, float:1.794E-43)
                if (r5 != 0) goto L_0x0034
                goto L_0x005e
            L_0x0034:
                r5 = r4 & 224(0xe0, float:3.14E-43)
                r6 = 192(0xc0, float:2.69E-43)
                if (r5 != r6) goto L_0x003d
                int r4 = r1 + 1
                goto L_0x004e
            L_0x003d:
                r5 = r4 & 240(0xf0, float:3.36E-43)
                r7 = 224(0xe0, float:3.14E-43)
                if (r5 != r7) goto L_0x0046
                int r4 = r1 + 2
                goto L_0x004e
            L_0x0046:
                r4 = r4 & 248(0xf8, float:3.48E-43)
                r5 = 240(0xf0, float:3.36E-43)
                if (r4 != r5) goto L_0x0060
                int r4 = r1 + 3
            L_0x004e:
                int r5 = r8.length
                if (r4 < r5) goto L_0x0052
                return r3
            L_0x0052:
                if (r1 >= r4) goto L_0x005e
                int r1 = r1 + 1
                byte r5 = r8[r1]
                r5 = r5 & r6
                r7 = 128(0x80, float:1.794E-43)
                if (r5 == r7) goto L_0x0052
                return r3
            L_0x005e:
                int r1 = r1 + r2
                goto L_0x002b
            L_0x0060:
                return r3
            L_0x0061:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.HttpConnection.Base.looksLikeUtf8(byte[]):boolean");
        }

        public T header(String str, String str2) {
            Validate.notEmpty(str, "Header name must not be empty");
            removeHeader(str);
            addHeader(str, str2);
            return this;
        }

        public boolean hasHeader(String str) {
            Validate.notEmpty(str, "Header name must not be empty");
            return !getHeadersCaseInsensitive(str).isEmpty();
        }

        public boolean hasHeaderWithValue(String str, String str2) {
            Validate.notEmpty(str);
            Validate.notEmpty(str2);
            for (String equalsIgnoreCase : headers(str)) {
                if (str2.equalsIgnoreCase(equalsIgnoreCase)) {
                    return true;
                }
            }
            return false;
        }

        public T removeHeader(String str) {
            Validate.notEmpty(str, "Header name must not be empty");
            Map.Entry<String, List<String>> scanHeaders = scanHeaders(str);
            if (scanHeaders != null) {
                this.headers.remove(scanHeaders.getKey());
            }
            return this;
        }

        public Map<String, String> headers() {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.headers.size());
            for (Map.Entry next : this.headers.entrySet()) {
                String str = (String) next.getKey();
                List list = (List) next.getValue();
                if (list.size() > 0) {
                    linkedHashMap.put(str, list.get(0));
                }
            }
            return linkedHashMap;
        }

        public Map<String, List<String>> multiHeaders() {
            return this.headers;
        }

        private List<String> getHeadersCaseInsensitive(String str) {
            Validate.notNull(str);
            for (Map.Entry next : this.headers.entrySet()) {
                if (str.equalsIgnoreCase((String) next.getKey())) {
                    return (List) next.getValue();
                }
            }
            return Collections.emptyList();
        }

        private Map.Entry<String, List<String>> scanHeaders(String str) {
            String lowerCase = Normalizer.lowerCase(str);
            for (Map.Entry<String, List<String>> next : this.headers.entrySet()) {
                if (Normalizer.lowerCase(next.getKey()).equals(lowerCase)) {
                    return next;
                }
            }
            return null;
        }

        public String cookie(String str) {
            Validate.notEmpty(str, "Cookie name must not be empty");
            return this.cookies.get(str);
        }

        public T cookie(String str, String str2) {
            Validate.notEmpty(str, "Cookie name must not be empty");
            Validate.notNull(str2, "Cookie value must not be null");
            this.cookies.put(str, str2);
            return this;
        }

        public boolean hasCookie(String str) {
            Validate.notEmpty(str, "Cookie name must not be empty");
            return this.cookies.containsKey(str);
        }

        public T removeCookie(String str) {
            Validate.notEmpty(str, "Cookie name must not be empty");
            this.cookies.remove(str);
            return this;
        }

        public Map<String, String> cookies() {
            return this.cookies;
        }
    }

    public static class Request extends Base<Connection.Request> implements Connection.Request {
        private String body = null;
        private Collection<Connection.KeyVal> data = new ArrayList();
        private boolean followRedirects = true;
        private boolean ignoreContentType = false;
        private boolean ignoreHttpErrors = false;
        private int maxBodySizeBytes = 2097152;
        private Parser parser;
        /* access modifiers changed from: private */
        public boolean parserDefined = false;
        private String postDataCharset = "UTF-8";
        private Proxy proxy;
        private SSLSocketFactory sslSocketFactory;
        private int timeoutMilliseconds = 30000;

        public /* bridge */ /* synthetic */ Connection.Base addHeader(String str, String str2) {
            return super.addHeader(str, str2);
        }

        public /* bridge */ /* synthetic */ String cookie(String str) {
            return super.cookie(str);
        }

        public /* bridge */ /* synthetic */ Connection.Base cookie(String str, String str2) {
            return super.cookie(str, str2);
        }

        public /* bridge */ /* synthetic */ Map cookies() {
            return super.cookies();
        }

        public /* bridge */ /* synthetic */ boolean hasCookie(String str) {
            return super.hasCookie(str);
        }

        public /* bridge */ /* synthetic */ boolean hasHeader(String str) {
            return super.hasHeader(str);
        }

        public /* bridge */ /* synthetic */ boolean hasHeaderWithValue(String str, String str2) {
            return super.hasHeaderWithValue(str, str2);
        }

        public /* bridge */ /* synthetic */ String header(String str) {
            return super.header(str);
        }

        public /* bridge */ /* synthetic */ Connection.Base header(String str, String str2) {
            return super.header(str, str2);
        }

        public /* bridge */ /* synthetic */ List headers(String str) {
            return super.headers(str);
        }

        public /* bridge */ /* synthetic */ Map headers() {
            return super.headers();
        }

        public /* bridge */ /* synthetic */ Connection.Base method(Connection.Method method) {
            return super.method(method);
        }

        public /* bridge */ /* synthetic */ Connection.Method method() {
            return super.method();
        }

        public /* bridge */ /* synthetic */ Map multiHeaders() {
            return super.multiHeaders();
        }

        public /* bridge */ /* synthetic */ Connection.Base removeCookie(String str) {
            return super.removeCookie(str);
        }

        public /* bridge */ /* synthetic */ Connection.Base removeHeader(String str) {
            return super.removeHeader(str);
        }

        public /* bridge */ /* synthetic */ URL url() {
            return super.url();
        }

        public /* bridge */ /* synthetic */ Connection.Base url(URL url) {
            return super.url(url);
        }

        Request() {
            super();
            this.method = Connection.Method.GET;
            addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip");
            addHeader("User-Agent", HttpConnection.DEFAULT_UA);
            this.parser = Parser.htmlParser();
        }

        public Proxy proxy() {
            return this.proxy;
        }

        public Request proxy(Proxy proxy2) {
            this.proxy = proxy2;
            return this;
        }

        public Request proxy(String str, int i) {
            this.proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(str, i));
            return this;
        }

        public int timeout() {
            return this.timeoutMilliseconds;
        }

        public Request timeout(int i) {
            Validate.isTrue(i >= 0, "Timeout milliseconds must be 0 (infinite) or greater");
            this.timeoutMilliseconds = i;
            return this;
        }

        public int maxBodySize() {
            return this.maxBodySizeBytes;
        }

        public Connection.Request maxBodySize(int i) {
            Validate.isTrue(i >= 0, "maxSize must be 0 (unlimited) or larger");
            this.maxBodySizeBytes = i;
            return this;
        }

        public boolean followRedirects() {
            return this.followRedirects;
        }

        public Connection.Request followRedirects(boolean z) {
            this.followRedirects = z;
            return this;
        }

        public boolean ignoreHttpErrors() {
            return this.ignoreHttpErrors;
        }

        public SSLSocketFactory sslSocketFactory() {
            return this.sslSocketFactory;
        }

        public void sslSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.sslSocketFactory = sSLSocketFactory;
        }

        public Connection.Request ignoreHttpErrors(boolean z) {
            this.ignoreHttpErrors = z;
            return this;
        }

        public boolean ignoreContentType() {
            return this.ignoreContentType;
        }

        public Connection.Request ignoreContentType(boolean z) {
            this.ignoreContentType = z;
            return this;
        }

        public Request data(Connection.KeyVal keyVal) {
            Validate.notNull(keyVal, "Key val must not be null");
            this.data.add(keyVal);
            return this;
        }

        public Collection<Connection.KeyVal> data() {
            return this.data;
        }

        public Connection.Request requestBody(String str) {
            this.body = str;
            return this;
        }

        public String requestBody() {
            return this.body;
        }

        public Request parser(Parser parser2) {
            this.parser = parser2;
            this.parserDefined = true;
            return this;
        }

        public Parser parser() {
            return this.parser;
        }

        public Connection.Request postDataCharset(String str) {
            Validate.notNull(str, "Charset must not be null");
            if (Charset.isSupported(str)) {
                this.postDataCharset = str;
                return this;
            }
            throw new IllegalCharsetNameException(str);
        }

        public String postDataCharset() {
            return this.postDataCharset;
        }
    }

    public static class Response extends Base<Connection.Response> implements Connection.Response {
        private static final String LOCATION = "Location";
        private static final int MAX_REDIRECTS = 20;
        private static final Pattern xmlContentTypeRxp = Pattern.compile("(application|text)/\\w*\\+?xml.*");
        private InputStream bodyStream;
        private ByteBuffer byteData;
        private String charset;
        private HttpURLConnection conn;
        private String contentType;
        private boolean executed = false;
        private boolean inputStreamRead = false;
        private int numRedirects = 0;
        private Connection.Request req;
        private int statusCode;
        private String statusMessage;

        public /* bridge */ /* synthetic */ Connection.Base addHeader(String str, String str2) {
            return super.addHeader(str, str2);
        }

        public /* bridge */ /* synthetic */ String cookie(String str) {
            return super.cookie(str);
        }

        public /* bridge */ /* synthetic */ Connection.Base cookie(String str, String str2) {
            return super.cookie(str, str2);
        }

        public /* bridge */ /* synthetic */ Map cookies() {
            return super.cookies();
        }

        public /* bridge */ /* synthetic */ boolean hasCookie(String str) {
            return super.hasCookie(str);
        }

        public /* bridge */ /* synthetic */ boolean hasHeader(String str) {
            return super.hasHeader(str);
        }

        public /* bridge */ /* synthetic */ boolean hasHeaderWithValue(String str, String str2) {
            return super.hasHeaderWithValue(str, str2);
        }

        public /* bridge */ /* synthetic */ String header(String str) {
            return super.header(str);
        }

        public /* bridge */ /* synthetic */ Connection.Base header(String str, String str2) {
            return super.header(str, str2);
        }

        public /* bridge */ /* synthetic */ List headers(String str) {
            return super.headers(str);
        }

        public /* bridge */ /* synthetic */ Map headers() {
            return super.headers();
        }

        public /* bridge */ /* synthetic */ Connection.Base method(Connection.Method method) {
            return super.method(method);
        }

        public /* bridge */ /* synthetic */ Connection.Method method() {
            return super.method();
        }

        public /* bridge */ /* synthetic */ Map multiHeaders() {
            return super.multiHeaders();
        }

        public /* bridge */ /* synthetic */ Connection.Base removeCookie(String str) {
            return super.removeCookie(str);
        }

        public /* bridge */ /* synthetic */ Connection.Base removeHeader(String str) {
            return super.removeHeader(str);
        }

        public /* bridge */ /* synthetic */ URL url() {
            return super.url();
        }

        public /* bridge */ /* synthetic */ Connection.Base url(URL url) {
            return super.url(url);
        }

        Response() {
            super();
        }

        private Response(Response response) throws IOException {
            super();
            if (response != null) {
                int i = response.numRedirects + 1;
                this.numRedirects = i;
                if (i >= 20) {
                    throw new IOException(String.format("Too many redirects occurred trying to load URL %s", new Object[]{response.url()}));
                }
            }
        }

        static Response execute(Connection.Request request) throws IOException {
            return execute(request, (Response) null);
        }

        /* JADX WARNING: Removed duplicated region for block: B:24:0x008b A[Catch:{ IOException -> 0x01fe }] */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x00ac A[Catch:{ IOException -> 0x01fb }] */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x0117 A[Catch:{ IOException -> 0x01fb }] */
        /* JADX WARNING: Removed duplicated region for block: B:95:0x0201  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static org.jsoup.helper.HttpConnection.Response execute(org.jsoup.Connection.Request r9, org.jsoup.helper.HttpConnection.Response r10) throws java.io.IOException {
            /*
                java.lang.String r0 = "Content-Encoding"
                java.lang.String r1 = "Location"
                java.lang.String r2 = "Request must not be null"
                org.jsoup.helper.Validate.notNull(r9, r2)
                java.net.URL r2 = r9.url()
                java.lang.String r3 = "URL must be specified to connect"
                org.jsoup.helper.Validate.notNull(r2, r3)
                java.net.URL r2 = r9.url()
                java.lang.String r2 = r2.getProtocol()
                java.lang.String r3 = "http"
                boolean r3 = r2.equals(r3)
                if (r3 != 0) goto L_0x0033
                java.lang.String r3 = "https"
                boolean r2 = r2.equals(r3)
                if (r2 == 0) goto L_0x002b
                goto L_0x0033
            L_0x002b:
                java.net.MalformedURLException r9 = new java.net.MalformedURLException
                java.lang.String r10 = "Only http & https protocols supported"
                r9.<init>(r10)
                throw r9
            L_0x0033:
                org.jsoup.Connection$Method r2 = r9.method()
                boolean r2 = r2.hasBody()
                java.lang.String r3 = r9.requestBody()
                r4 = 1
                if (r3 == 0) goto L_0x0044
                r3 = 1
                goto L_0x0045
            L_0x0044:
                r3 = 0
            L_0x0045:
                if (r2 != 0) goto L_0x005f
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "Cannot set a request body for HTTP method "
                r5.append(r6)
                org.jsoup.Connection$Method r6 = r9.method()
                r5.append(r6)
                java.lang.String r5 = r5.toString()
                org.jsoup.helper.Validate.isFalse(r3, r5)
            L_0x005f:
                java.util.Collection r5 = r9.data()
                int r5 = r5.size()
                r6 = 0
                if (r5 <= 0) goto L_0x0072
                if (r2 == 0) goto L_0x006e
                if (r3 == 0) goto L_0x0072
            L_0x006e:
                serialiseRequestUrl(r9)
                goto L_0x0079
            L_0x0072:
                if (r2 == 0) goto L_0x0079
                java.lang.String r2 = setOutputContentType(r9)
                goto L_0x007a
            L_0x0079:
                r2 = r6
            L_0x007a:
                long r7 = java.lang.System.nanoTime()
                java.net.HttpURLConnection r3 = createConnection(r9)
                r3.connect()     // Catch:{ IOException -> 0x01fe }
                boolean r5 = r3.getDoOutput()     // Catch:{ IOException -> 0x01fe }
                if (r5 == 0) goto L_0x0092
                java.io.OutputStream r5 = r3.getOutputStream()     // Catch:{ IOException -> 0x01fe }
                writePost(r9, r5, r2)     // Catch:{ IOException -> 0x01fe }
            L_0x0092:
                int r2 = r3.getResponseCode()     // Catch:{ IOException -> 0x01fe }
                org.jsoup.helper.HttpConnection$Response r5 = new org.jsoup.helper.HttpConnection$Response     // Catch:{ IOException -> 0x01fe }
                r5.<init>(r10)     // Catch:{ IOException -> 0x01fe }
                r5.setupFromConnection(r3, r10)     // Catch:{ IOException -> 0x01fb }
                r5.req = r9     // Catch:{ IOException -> 0x01fb }
                boolean r10 = r5.hasHeader(r1)     // Catch:{ IOException -> 0x01fb }
                if (r10 == 0) goto L_0x0117
                boolean r10 = r9.followRedirects()     // Catch:{ IOException -> 0x01fb }
                if (r10 == 0) goto L_0x0117
                r10 = 307(0x133, float:4.3E-43)
                if (r2 == r10) goto L_0x00c4
                org.jsoup.Connection$Method r10 = org.jsoup.Connection.Method.GET     // Catch:{ IOException -> 0x01fb }
                r9.method(r10)     // Catch:{ IOException -> 0x01fb }
                java.util.Collection r10 = r9.data()     // Catch:{ IOException -> 0x01fb }
                r10.clear()     // Catch:{ IOException -> 0x01fb }
                r9.requestBody(r6)     // Catch:{ IOException -> 0x01fb }
                java.lang.String r10 = "Content-Type"
                r9.removeHeader(r10)     // Catch:{ IOException -> 0x01fb }
            L_0x00c4:
                java.lang.String r10 = r5.header(r1)     // Catch:{ IOException -> 0x01fb }
                java.lang.String r0 = "http:/"
                boolean r0 = r10.startsWith(r0)     // Catch:{ IOException -> 0x01fb }
                if (r0 == 0) goto L_0x00dd
                r0 = 6
                char r1 = r10.charAt(r0)     // Catch:{ IOException -> 0x01fb }
                r2 = 47
                if (r1 == r2) goto L_0x00dd
                java.lang.String r10 = r10.substring(r0)     // Catch:{ IOException -> 0x01fb }
            L_0x00dd:
                java.net.URL r0 = r9.url()     // Catch:{ IOException -> 0x01fb }
                java.net.URL r10 = org.jsoup.internal.StringUtil.resolve((java.net.URL) r0, (java.lang.String) r10)     // Catch:{ IOException -> 0x01fb }
                java.net.URL r10 = org.jsoup.helper.HttpConnection.encodeUrl((java.net.URL) r10)     // Catch:{ IOException -> 0x01fb }
                r9.url(r10)     // Catch:{ IOException -> 0x01fb }
                java.util.Map r10 = r5.cookies     // Catch:{ IOException -> 0x01fb }
                java.util.Set r10 = r10.entrySet()     // Catch:{ IOException -> 0x01fb }
                java.util.Iterator r10 = r10.iterator()     // Catch:{ IOException -> 0x01fb }
            L_0x00f6:
                boolean r0 = r10.hasNext()     // Catch:{ IOException -> 0x01fb }
                if (r0 == 0) goto L_0x0112
                java.lang.Object r0 = r10.next()     // Catch:{ IOException -> 0x01fb }
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ IOException -> 0x01fb }
                java.lang.Object r1 = r0.getKey()     // Catch:{ IOException -> 0x01fb }
                java.lang.String r1 = (java.lang.String) r1     // Catch:{ IOException -> 0x01fb }
                java.lang.Object r0 = r0.getValue()     // Catch:{ IOException -> 0x01fb }
                java.lang.String r0 = (java.lang.String) r0     // Catch:{ IOException -> 0x01fb }
                r9.cookie(r1, r0)     // Catch:{ IOException -> 0x01fb }
                goto L_0x00f6
            L_0x0112:
                org.jsoup.helper.HttpConnection$Response r9 = execute(r9, r5)     // Catch:{ IOException -> 0x01fb }
                return r9
            L_0x0117:
                r10 = 200(0xc8, float:2.8E-43)
                if (r2 < r10) goto L_0x011f
                r10 = 400(0x190, float:5.6E-43)
                if (r2 < r10) goto L_0x0125
            L_0x011f:
                boolean r10 = r9.ignoreHttpErrors()     // Catch:{ IOException -> 0x01fb }
                if (r10 == 0) goto L_0x01eb
            L_0x0125:
                java.lang.String r10 = r5.contentType()     // Catch:{ IOException -> 0x01fb }
                if (r10 == 0) goto L_0x0156
                boolean r1 = r9.ignoreContentType()     // Catch:{ IOException -> 0x01fb }
                if (r1 != 0) goto L_0x0156
                java.lang.String r1 = "text/"
                boolean r1 = r10.startsWith(r1)     // Catch:{ IOException -> 0x01fb }
                if (r1 != 0) goto L_0x0156
                java.util.regex.Pattern r1 = xmlContentTypeRxp     // Catch:{ IOException -> 0x01fb }
                java.util.regex.Matcher r1 = r1.matcher(r10)     // Catch:{ IOException -> 0x01fb }
                boolean r1 = r1.matches()     // Catch:{ IOException -> 0x01fb }
                if (r1 == 0) goto L_0x0146
                goto L_0x0156
            L_0x0146:
                org.jsoup.UnsupportedMimeTypeException r0 = new org.jsoup.UnsupportedMimeTypeException     // Catch:{ IOException -> 0x01fb }
                java.lang.String r1 = "Unhandled content type. Must be text/*, application/xml, or application/*+xml"
                java.net.URL r9 = r9.url()     // Catch:{ IOException -> 0x01fb }
                java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x01fb }
                r0.<init>(r1, r10, r9)     // Catch:{ IOException -> 0x01fb }
                throw r0     // Catch:{ IOException -> 0x01fb }
            L_0x0156:
                if (r10 == 0) goto L_0x0178
                java.util.regex.Pattern r1 = xmlContentTypeRxp     // Catch:{ IOException -> 0x01fb }
                java.util.regex.Matcher r10 = r1.matcher(r10)     // Catch:{ IOException -> 0x01fb }
                boolean r10 = r10.matches()     // Catch:{ IOException -> 0x01fb }
                if (r10 == 0) goto L_0x0178
                boolean r10 = r9 instanceof org.jsoup.helper.HttpConnection.Request     // Catch:{ IOException -> 0x01fb }
                if (r10 == 0) goto L_0x0178
                r10 = r9
                org.jsoup.helper.HttpConnection$Request r10 = (org.jsoup.helper.HttpConnection.Request) r10     // Catch:{ IOException -> 0x01fb }
                boolean r10 = r10.parserDefined     // Catch:{ IOException -> 0x01fb }
                if (r10 != 0) goto L_0x0178
                org.jsoup.parser.Parser r10 = org.jsoup.parser.Parser.xmlParser()     // Catch:{ IOException -> 0x01fb }
                r9.parser(r10)     // Catch:{ IOException -> 0x01fb }
            L_0x0178:
                java.lang.String r10 = r5.contentType     // Catch:{ IOException -> 0x01fb }
                java.lang.String r10 = org.jsoup.helper.DataUtil.getCharsetFromContentType(r10)     // Catch:{ IOException -> 0x01fb }
                r5.charset = r10     // Catch:{ IOException -> 0x01fb }
                int r10 = r3.getContentLength()     // Catch:{ IOException -> 0x01fb }
                if (r10 == 0) goto L_0x01e2
                org.jsoup.Connection$Method r10 = r9.method()     // Catch:{ IOException -> 0x01fb }
                org.jsoup.Connection$Method r1 = org.jsoup.Connection.Method.HEAD     // Catch:{ IOException -> 0x01fb }
                if (r10 == r1) goto L_0x01e2
                r5.bodyStream = r6     // Catch:{ IOException -> 0x01fb }
                java.io.InputStream r10 = r3.getErrorStream()     // Catch:{ IOException -> 0x01fb }
                if (r10 == 0) goto L_0x019b
                java.io.InputStream r10 = r3.getErrorStream()     // Catch:{ IOException -> 0x01fb }
                goto L_0x019f
            L_0x019b:
                java.io.InputStream r10 = r3.getInputStream()     // Catch:{ IOException -> 0x01fb }
            L_0x019f:
                r5.bodyStream = r10     // Catch:{ IOException -> 0x01fb }
                java.lang.String r10 = "gzip"
                boolean r10 = r5.hasHeaderWithValue(r0, r10)     // Catch:{ IOException -> 0x01fb }
                if (r10 == 0) goto L_0x01b3
                java.util.zip.GZIPInputStream r10 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x01fb }
                java.io.InputStream r0 = r5.bodyStream     // Catch:{ IOException -> 0x01fb }
                r10.<init>(r0)     // Catch:{ IOException -> 0x01fb }
                r5.bodyStream = r10     // Catch:{ IOException -> 0x01fb }
                goto L_0x01c9
            L_0x01b3:
                java.lang.String r10 = "deflate"
                boolean r10 = r5.hasHeaderWithValue(r0, r10)     // Catch:{ IOException -> 0x01fb }
                if (r10 == 0) goto L_0x01c9
                java.util.zip.InflaterInputStream r10 = new java.util.zip.InflaterInputStream     // Catch:{ IOException -> 0x01fb }
                java.io.InputStream r0 = r5.bodyStream     // Catch:{ IOException -> 0x01fb }
                java.util.zip.Inflater r1 = new java.util.zip.Inflater     // Catch:{ IOException -> 0x01fb }
                r1.<init>(r4)     // Catch:{ IOException -> 0x01fb }
                r10.<init>(r0, r1)     // Catch:{ IOException -> 0x01fb }
                r5.bodyStream = r10     // Catch:{ IOException -> 0x01fb }
            L_0x01c9:
                java.io.InputStream r10 = r5.bodyStream     // Catch:{ IOException -> 0x01fb }
                r0 = 32768(0x8000, float:4.5918E-41)
                int r1 = r9.maxBodySize()     // Catch:{ IOException -> 0x01fb }
                org.jsoup.internal.ConstrainableInputStream r10 = org.jsoup.internal.ConstrainableInputStream.wrap(r10, r0, r1)     // Catch:{ IOException -> 0x01fb }
                int r9 = r9.timeout()     // Catch:{ IOException -> 0x01fb }
                long r0 = (long) r9     // Catch:{ IOException -> 0x01fb }
                org.jsoup.internal.ConstrainableInputStream r9 = r10.timeout(r7, r0)     // Catch:{ IOException -> 0x01fb }
                r5.bodyStream = r9     // Catch:{ IOException -> 0x01fb }
                goto L_0x01e8
            L_0x01e2:
                java.nio.ByteBuffer r9 = org.jsoup.helper.DataUtil.emptyByteBuffer()     // Catch:{ IOException -> 0x01fb }
                r5.byteData = r9     // Catch:{ IOException -> 0x01fb }
            L_0x01e8:
                r5.executed = r4
                return r5
            L_0x01eb:
                org.jsoup.HttpStatusException r10 = new org.jsoup.HttpStatusException     // Catch:{ IOException -> 0x01fb }
                java.lang.String r0 = "HTTP error fetching URL"
                java.net.URL r9 = r9.url()     // Catch:{ IOException -> 0x01fb }
                java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x01fb }
                r10.<init>(r0, r2, r9)     // Catch:{ IOException -> 0x01fb }
                throw r10     // Catch:{ IOException -> 0x01fb }
            L_0x01fb:
                r9 = move-exception
                r6 = r5
                goto L_0x01ff
            L_0x01fe:
                r9 = move-exception
            L_0x01ff:
                if (r6 == 0) goto L_0x0204
                r6.safeClose()
            L_0x0204:
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.HttpConnection.Response.execute(org.jsoup.Connection$Request, org.jsoup.helper.HttpConnection$Response):org.jsoup.helper.HttpConnection$Response");
        }

        public int statusCode() {
            return this.statusCode;
        }

        public String statusMessage() {
            return this.statusMessage;
        }

        public String charset() {
            return this.charset;
        }

        public Response charset(String str) {
            this.charset = str;
            return this;
        }

        public String contentType() {
            return this.contentType;
        }

        public Document parse() throws IOException {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before parsing response");
            if (this.byteData != null) {
                this.bodyStream = new ByteArrayInputStream(this.byteData.array());
                this.inputStreamRead = false;
            }
            Validate.isFalse(this.inputStreamRead, "Input stream already read and parsed, cannot re-read.");
            Document parseInputStream = DataUtil.parseInputStream(this.bodyStream, this.charset, this.url.toExternalForm(), this.req.parser());
            this.charset = parseInputStream.outputSettings().charset().name();
            this.inputStreamRead = true;
            safeClose();
            return parseInputStream;
        }

        private void prepareByteData() {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            if (this.byteData == null) {
                Validate.isFalse(this.inputStreamRead, "Request has already been read (with .parse())");
                try {
                    this.byteData = DataUtil.readToByteBuffer(this.bodyStream, this.req.maxBodySize());
                    this.inputStreamRead = true;
                    safeClose();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                } catch (Throwable th) {
                    this.inputStreamRead = true;
                    safeClose();
                    throw th;
                }
            }
        }

        public String body() {
            String str;
            prepareByteData();
            String str2 = this.charset;
            if (str2 == null) {
                str = Charset.forName("UTF-8").decode(this.byteData).toString();
            } else {
                str = Charset.forName(str2).decode(this.byteData).toString();
            }
            this.byteData.rewind();
            return str;
        }

        public byte[] bodyAsBytes() {
            prepareByteData();
            return this.byteData.array();
        }

        public Connection.Response bufferUp() {
            prepareByteData();
            return this;
        }

        public BufferedInputStream bodyStream() {
            Validate.isTrue(this.executed, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            Validate.isFalse(this.inputStreamRead, "Request has already been read");
            this.inputStreamRead = true;
            return ConstrainableInputStream.wrap(this.bodyStream, 32768, this.req.maxBodySize());
        }

        private static HttpURLConnection createConnection(Connection.Request request) throws IOException {
            URLConnection uRLConnection;
            if (request.proxy() == null) {
                uRLConnection = request.url().openConnection();
            } else {
                uRLConnection = request.url().openConnection(request.proxy());
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
            httpURLConnection.setRequestMethod(request.method().name());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setConnectTimeout(request.timeout());
            httpURLConnection.setReadTimeout(request.timeout() / 2);
            if (request.sslSocketFactory() != null && (httpURLConnection instanceof HttpsURLConnection)) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(request.sslSocketFactory());
            }
            if (request.method().hasBody()) {
                httpURLConnection.setDoOutput(true);
            }
            if (request.cookies().size() > 0) {
                httpURLConnection.addRequestProperty("Cookie", getRequestCookieString(request));
            }
            for (Map.Entry next : request.multiHeaders().entrySet()) {
                for (String addRequestProperty : (List) next.getValue()) {
                    httpURLConnection.addRequestProperty((String) next.getKey(), addRequestProperty);
                }
            }
            return httpURLConnection;
        }

        private void safeClose() {
            InputStream inputStream = this.bodyStream;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                } catch (Throwable th) {
                    this.bodyStream = null;
                    throw th;
                }
                this.bodyStream = null;
            }
            HttpURLConnection httpURLConnection = this.conn;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                this.conn = null;
            }
        }

        private void setupFromConnection(HttpURLConnection httpURLConnection, Response response) throws IOException {
            this.conn = httpURLConnection;
            this.method = Connection.Method.valueOf(httpURLConnection.getRequestMethod());
            this.url = httpURLConnection.getURL();
            this.statusCode = httpURLConnection.getResponseCode();
            this.statusMessage = httpURLConnection.getResponseMessage();
            this.contentType = httpURLConnection.getContentType();
            processResponseHeaders(createHeaderMap(httpURLConnection));
            if (response != null) {
                for (Map.Entry entry : response.cookies().entrySet()) {
                    if (!hasCookie((String) entry.getKey())) {
                        cookie((String) entry.getKey(), (String) entry.getValue());
                    }
                }
                response.safeClose();
            }
        }

        private static LinkedHashMap<String, List<String>> createHeaderMap(HttpURLConnection httpURLConnection) {
            LinkedHashMap<String, List<String>> linkedHashMap = new LinkedHashMap<>();
            int i = 0;
            while (true) {
                String headerFieldKey = httpURLConnection.getHeaderFieldKey(i);
                String headerField = httpURLConnection.getHeaderField(i);
                if (headerFieldKey == null && headerField == null) {
                    return linkedHashMap;
                }
                i++;
                if (!(headerFieldKey == null || headerField == null)) {
                    if (linkedHashMap.containsKey(headerFieldKey)) {
                        linkedHashMap.get(headerFieldKey).add(headerField);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(headerField);
                        linkedHashMap.put(headerFieldKey, arrayList);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void processResponseHeaders(Map<String, List<String>> map) {
            for (Map.Entry next : map.entrySet()) {
                String str = (String) next.getKey();
                if (str != null) {
                    List<String> list = (List) next.getValue();
                    if (str.equalsIgnoreCase("Set-Cookie")) {
                        for (String str2 : list) {
                            if (str2 != null) {
                                TokenQueue tokenQueue = new TokenQueue(str2);
                                String trim = tokenQueue.chompTo("=").trim();
                                String trim2 = tokenQueue.consumeTo(";").trim();
                                if (trim.length() > 0) {
                                    cookie(trim, trim2);
                                }
                            }
                        }
                    }
                    for (String addHeader : list) {
                        addHeader(str, addHeader);
                    }
                }
            }
        }

        private static String setOutputContentType(Connection.Request request) {
            if (request.hasHeader("Content-Type")) {
                if (request.header("Content-Type").contains(HttpConnection.MULTIPART_FORM_DATA) && !request.header("Content-Type").contains("boundary")) {
                    String mimeBoundary = DataUtil.mimeBoundary();
                    request.header("Content-Type", "multipart/form-data; boundary=" + mimeBoundary);
                    return mimeBoundary;
                }
            } else if (HttpConnection.needsMultipart(request)) {
                String mimeBoundary2 = DataUtil.mimeBoundary();
                request.header("Content-Type", "multipart/form-data; boundary=" + mimeBoundary2);
                return mimeBoundary2;
            } else {
                request.header("Content-Type", "application/x-www-form-urlencoded; charset=" + request.postDataCharset());
            }
            return null;
        }

        private static void writePost(Connection.Request request, OutputStream outputStream, String str) throws IOException {
            Collection<Connection.KeyVal> data = request.data();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, request.postDataCharset()));
            if (str != null) {
                for (Connection.KeyVal next : data) {
                    bufferedWriter.write("--");
                    bufferedWriter.write(str);
                    bufferedWriter.write(IOUtils.LINE_SEPARATOR_WINDOWS);
                    bufferedWriter.write("Content-Disposition: form-data; name=\"");
                    bufferedWriter.write(HttpConnection.encodeMimeName(next.key()));
                    bufferedWriter.write("\"");
                    if (next.hasInputStream()) {
                        bufferedWriter.write("; filename=\"");
                        bufferedWriter.write(HttpConnection.encodeMimeName(next.value()));
                        bufferedWriter.write("\"\r\nContent-Type: ");
                        bufferedWriter.write(next.contentType() != null ? next.contentType() : HttpConnection.DefaultUploadType);
                        bufferedWriter.write("\r\n\r\n");
                        bufferedWriter.flush();
                        DataUtil.crossStreams(next.inputStream(), outputStream);
                        outputStream.flush();
                    } else {
                        bufferedWriter.write("\r\n\r\n");
                        bufferedWriter.write(next.value());
                    }
                    bufferedWriter.write(IOUtils.LINE_SEPARATOR_WINDOWS);
                }
                bufferedWriter.write("--");
                bufferedWriter.write(str);
                bufferedWriter.write("--");
            } else if (request.requestBody() != null) {
                bufferedWriter.write(request.requestBody());
            } else {
                boolean z = true;
                for (Connection.KeyVal next2 : data) {
                    if (!z) {
                        bufferedWriter.append(Typography.amp);
                    } else {
                        z = false;
                    }
                    bufferedWriter.write(URLEncoder.encode(next2.key(), request.postDataCharset()));
                    bufferedWriter.write(61);
                    bufferedWriter.write(URLEncoder.encode(next2.value(), request.postDataCharset()));
                }
            }
            bufferedWriter.close();
        }

        private static String getRequestCookieString(Connection.Request request) {
            StringBuilder borrowBuilder = StringUtil.borrowBuilder();
            boolean z = true;
            for (Map.Entry next : request.cookies().entrySet()) {
                if (!z) {
                    borrowBuilder.append("; ");
                } else {
                    z = false;
                }
                borrowBuilder.append((String) next.getKey());
                borrowBuilder.append('=');
                borrowBuilder.append((String) next.getValue());
            }
            return StringUtil.releaseBuilder(borrowBuilder);
        }

        private static void serialiseRequestUrl(Connection.Request request) throws IOException {
            boolean z;
            URL url = request.url();
            StringBuilder borrowBuilder = StringUtil.borrowBuilder();
            borrowBuilder.append(url.getProtocol());
            borrowBuilder.append("://");
            borrowBuilder.append(url.getAuthority());
            borrowBuilder.append(url.getPath());
            borrowBuilder.append(TypeDescription.Generic.OfWildcardType.SYMBOL);
            if (url.getQuery() != null) {
                borrowBuilder.append(url.getQuery());
                z = false;
            } else {
                z = true;
            }
            for (Connection.KeyVal next : request.data()) {
                Validate.isFalse(next.hasInputStream(), "InputStream data not supported in URL query string.");
                if (!z) {
                    borrowBuilder.append(Typography.amp);
                } else {
                    z = false;
                }
                borrowBuilder.append(URLEncoder.encode(next.key(), "UTF-8"));
                borrowBuilder.append('=');
                borrowBuilder.append(URLEncoder.encode(next.value(), "UTF-8"));
            }
            request.url(new URL(StringUtil.releaseBuilder(borrowBuilder)));
            request.data().clear();
        }
    }

    /* access modifiers changed from: private */
    public static boolean needsMultipart(Connection.Request request) {
        for (Connection.KeyVal hasInputStream : request.data()) {
            if (hasInputStream.hasInputStream()) {
                return true;
            }
        }
        return false;
    }

    public static class KeyVal implements Connection.KeyVal {
        private String contentType;
        private String key;
        private InputStream stream;
        private String value;

        public static KeyVal create(String str, String str2) {
            return new KeyVal().key(str).value(str2);
        }

        public static KeyVal create(String str, String str2, InputStream inputStream) {
            return new KeyVal().key(str).value(str2).inputStream(inputStream);
        }

        private KeyVal() {
        }

        public KeyVal key(String str) {
            Validate.notEmpty(str, "Data key must not be empty");
            this.key = str;
            return this;
        }

        public String key() {
            return this.key;
        }

        public KeyVal value(String str) {
            Validate.notNull(str, "Data value must not be null");
            this.value = str;
            return this;
        }

        public String value() {
            return this.value;
        }

        public KeyVal inputStream(InputStream inputStream) {
            Validate.notNull(this.value, "Data input stream must not be null");
            this.stream = inputStream;
            return this;
        }

        public InputStream inputStream() {
            return this.stream;
        }

        public boolean hasInputStream() {
            return this.stream != null;
        }

        public Connection.KeyVal contentType(String str) {
            Validate.notEmpty(str);
            this.contentType = str;
            return this;
        }

        public String contentType() {
            return this.contentType;
        }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
