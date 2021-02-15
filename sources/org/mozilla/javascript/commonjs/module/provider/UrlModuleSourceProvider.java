package org.mozilla.javascript.commonjs.module.provider;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;

public class UrlModuleSourceProvider extends ModuleSourceProviderBase {
    private static final long serialVersionUID = 1;
    private final Iterable<URI> fallbackUris;
    private final Iterable<URI> privilegedUris;
    private final UrlConnectionExpiryCalculator urlConnectionExpiryCalculator;
    private final UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider;

    /* access modifiers changed from: protected */
    public void onFailedClosingUrlConnection(URLConnection uRLConnection, IOException iOException) {
    }

    public UrlModuleSourceProvider(Iterable<URI> iterable, Iterable<URI> iterable2) {
        this(iterable, iterable2, new DefaultUrlConnectionExpiryCalculator(), (UrlConnectionSecurityDomainProvider) null);
    }

    public UrlModuleSourceProvider(Iterable<URI> iterable, Iterable<URI> iterable2, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator2, UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider2) {
        this.privilegedUris = iterable;
        this.fallbackUris = iterable2;
        this.urlConnectionExpiryCalculator = urlConnectionExpiryCalculator2;
        this.urlConnectionSecurityDomainProvider = urlConnectionSecurityDomainProvider2;
    }

    /* access modifiers changed from: protected */
    public ModuleSource loadFromPrivilegedLocations(String str, Object obj) throws IOException, URISyntaxException {
        return loadFromPathList(str, obj, this.privilegedUris);
    }

    /* access modifiers changed from: protected */
    public ModuleSource loadFromFallbackLocations(String str, Object obj) throws IOException, URISyntaxException {
        return loadFromPathList(str, obj, this.fallbackUris);
    }

    private ModuleSource loadFromPathList(String str, Object obj, Iterable<URI> iterable) throws IOException, URISyntaxException {
        if (iterable == null) {
            return null;
        }
        for (URI next : iterable) {
            ModuleSource loadFromUri = loadFromUri(next.resolve(str), next, obj);
            if (loadFromUri != null) {
                return loadFromUri;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public ModuleSource loadFromUri(URI uri, URI uri2, Object obj) throws IOException, URISyntaxException {
        ModuleSource loadFromActualUri = loadFromActualUri(new URI(uri + ".js"), uri2, obj);
        return loadFromActualUri != null ? loadFromActualUri : loadFromActualUri(uri, uri2, obj);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        if (r15.appliesTo(r13) != false) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.mozilla.javascript.commonjs.module.provider.ModuleSource loadFromActualUri(java.net.URI r13, java.net.URI r14, java.lang.Object r15) throws java.io.IOException {
        /*
            r12 = this;
            java.net.URL r0 = new java.net.URL
            r1 = 0
            if (r14 != 0) goto L_0x0007
            r2 = r1
            goto L_0x000b
        L_0x0007:
            java.net.URL r2 = r14.toURL()
        L_0x000b:
            java.lang.String r3 = r13.toString()
            r0.<init>(r2, r3)
            long r7 = java.lang.System.currentTimeMillis()
            java.net.URLConnection r0 = r12.openUrlConnection(r0)
            boolean r2 = r15 instanceof org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider.URLValidator
            if (r2 == 0) goto L_0x0027
            org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider$URLValidator r15 = (org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider.URLValidator) r15
            boolean r2 = r15.appliesTo(r13)
            if (r2 == 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r15 = r1
        L_0x0028:
            if (r15 == 0) goto L_0x002d
            r15.applyConditionals(r0)
        L_0x002d:
            r0.connect()     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            if (r15 == 0) goto L_0x0040
            org.mozilla.javascript.commonjs.module.provider.UrlConnectionExpiryCalculator r2 = r12.urlConnectionExpiryCalculator     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            boolean r15 = r15.updateValidator(r0, r7, r2)     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            if (r15 == 0) goto L_0x0040
            r12.close(r0)     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            org.mozilla.javascript.commonjs.module.provider.ModuleSource r13 = NOT_MODIFIED     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            return r13
        L_0x0040:
            org.mozilla.javascript.commonjs.module.provider.ModuleSource r15 = new org.mozilla.javascript.commonjs.module.provider.ModuleSource     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            java.io.Reader r3 = getReader(r0)     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            java.lang.Object r10 = r12.getSecurityDomain(r0)     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider$URLValidator r11 = new org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider$URLValidator     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            org.mozilla.javascript.commonjs.module.provider.UrlConnectionExpiryCalculator r9 = r12.urlConnectionExpiryCalculator     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            r4 = r11
            r5 = r13
            r6 = r0
            r4.<init>(r5, r6, r7, r9)     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            r2 = r15
            r4 = r10
            r5 = r13
            r6 = r14
            r7 = r11
            r2.<init>(r3, r4, r5, r6, r7)     // Catch:{ FileNotFoundException -> 0x0067, RuntimeException -> 0x0062, IOException -> 0x005d }
            return r15
        L_0x005d:
            r13 = move-exception
            r12.close(r0)
            throw r13
        L_0x0062:
            r13 = move-exception
            r12.close(r0)
            throw r13
        L_0x0067:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.commonjs.module.provider.UrlModuleSourceProvider.loadFromActualUri(java.net.URI, java.net.URI, java.lang.Object):org.mozilla.javascript.commonjs.module.provider.ModuleSource");
    }

    private static Reader getReader(URLConnection uRLConnection) throws IOException {
        return new InputStreamReader(uRLConnection.getInputStream(), getCharacterEncoding(uRLConnection));
    }

    private static String getCharacterEncoding(URLConnection uRLConnection) {
        ParsedContentType parsedContentType = new ParsedContentType(uRLConnection.getContentType());
        String encoding = parsedContentType.getEncoding();
        if (encoding != null) {
            return encoding;
        }
        String contentType = parsedContentType.getContentType();
        return (contentType == null || !contentType.startsWith("text/")) ? "utf-8" : "8859_1";
    }

    private Object getSecurityDomain(URLConnection uRLConnection) {
        UrlConnectionSecurityDomainProvider urlConnectionSecurityDomainProvider2 = this.urlConnectionSecurityDomainProvider;
        if (urlConnectionSecurityDomainProvider2 == null) {
            return null;
        }
        return urlConnectionSecurityDomainProvider2.getSecurityDomain(uRLConnection);
    }

    private void close(URLConnection uRLConnection) {
        try {
            uRLConnection.getInputStream().close();
        } catch (IOException e) {
            onFailedClosingUrlConnection(uRLConnection, e);
        }
    }

    /* access modifiers changed from: protected */
    public URLConnection openUrlConnection(URL url) throws IOException {
        return url.openConnection();
    }

    /* access modifiers changed from: protected */
    public boolean entityNeedsRevalidation(Object obj) {
        return !(obj instanceof URLValidator) || ((URLValidator) obj).entityNeedsRevalidation();
    }

    private static class URLValidator implements Serializable {
        private static final long serialVersionUID = 1;
        private final String entityTags;
        private long expiry;
        private final long lastModified;
        private final URI uri;

        public URLValidator(URI uri2, URLConnection uRLConnection, long j, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) {
            this.uri = uri2;
            this.lastModified = uRLConnection.getLastModified();
            this.entityTags = getEntityTags(uRLConnection);
            this.expiry = calculateExpiry(uRLConnection, j, urlConnectionExpiryCalculator);
        }

        /* access modifiers changed from: package-private */
        public boolean updateValidator(URLConnection uRLConnection, long j, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) throws IOException {
            boolean isResourceChanged = isResourceChanged(uRLConnection);
            if (!isResourceChanged) {
                this.expiry = calculateExpiry(uRLConnection, j, urlConnectionExpiryCalculator);
            }
            return isResourceChanged;
        }

        private boolean isResourceChanged(URLConnection uRLConnection) throws IOException {
            if (uRLConnection instanceof HttpURLConnection) {
                if (((HttpURLConnection) uRLConnection).getResponseCode() == 304) {
                    return true;
                }
                return false;
            } else if (this.lastModified == uRLConnection.getLastModified()) {
                return true;
            } else {
                return false;
            }
        }

        private long calculateExpiry(URLConnection uRLConnection, long j, UrlConnectionExpiryCalculator urlConnectionExpiryCalculator) {
            if ("no-cache".equals(uRLConnection.getHeaderField(HttpHeaders.PRAGMA))) {
                return 0;
            }
            String headerField = uRLConnection.getHeaderField(HttpHeaders.CACHE_CONTROL);
            if (headerField != null) {
                if (headerField.indexOf("no-cache") != -1) {
                    return 0;
                }
                int maxAge = getMaxAge(headerField);
                if (-1 != maxAge) {
                    long currentTimeMillis = System.currentTimeMillis();
                    return (((long) maxAge) * 1000) + (currentTimeMillis - (Math.max(Math.max(0, currentTimeMillis - uRLConnection.getDate()), ((long) uRLConnection.getHeaderFieldInt(HttpHeaders.AGE, 0)) * 1000) + (currentTimeMillis - j)));
                }
            }
            long headerFieldDate = uRLConnection.getHeaderFieldDate(HttpHeaders.EXPIRES, -1);
            if (headerFieldDate != -1) {
                return headerFieldDate;
            }
            if (urlConnectionExpiryCalculator == null) {
                return 0;
            }
            return urlConnectionExpiryCalculator.calculateExpiry(uRLConnection);
        }

        private int getMaxAge(String str) {
            int indexOf;
            String str2;
            int indexOf2 = str.indexOf("max-age");
            if (indexOf2 == -1 || (indexOf = str.indexOf(61, indexOf2 + 7)) == -1) {
                return -1;
            }
            int i = indexOf + 1;
            int indexOf3 = str.indexOf(44, i);
            if (indexOf3 == -1) {
                str2 = str.substring(i);
            } else {
                str2 = str.substring(i, indexOf3);
            }
            try {
                return Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        private String getEntityTags(URLConnection uRLConnection) {
            List list = uRLConnection.getHeaderFields().get(HttpHeaders.ETAG);
            if (list == null || list.isEmpty()) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            Iterator it = list.iterator();
            sb.append((String) it.next());
            while (it.hasNext()) {
                sb.append(", ");
                sb.append((String) it.next());
            }
            return sb.toString();
        }

        /* access modifiers changed from: package-private */
        public boolean appliesTo(URI uri2) {
            return this.uri.equals(uri2);
        }

        /* access modifiers changed from: package-private */
        public void applyConditionals(URLConnection uRLConnection) {
            long j = this.lastModified;
            if (j != 0) {
                uRLConnection.setIfModifiedSince(j);
            }
            String str = this.entityTags;
            if (str != null && str.length() > 0) {
                uRLConnection.addRequestProperty(HttpHeaders.IF_NONE_MATCH, this.entityTags);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean entityNeedsRevalidation() {
            return System.currentTimeMillis() > this.expiry;
        }
    }
}
