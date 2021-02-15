package mnetinternal;

import android.content.Context;
import android.os.Build;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import mnetinternal.ag;
import net.media.android.bidder.base.configs.AdUnitConfig;
import net.media.android.bidder.base.error.a;
import net.media.android.bidder.base.logging.Logger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;

public final class bc implements al {
    private OkHttpClient a;

    public bc(Context context) {
        OkHttpClient.Builder followRedirects = new OkHttpClient.Builder().cache(ba.a(context)).followRedirects(true);
        followRedirects = Build.VERSION.SDK_INT < 21 ? a(followRedirects) : followRedirects;
        if (AdUnitConfig.getInstance().getPublisherConfig().shouldUseGzip()) {
            followRedirects.addInterceptor(new az());
        }
        followRedirects.retryOnConnectionFailure(true);
        this.a = followRedirects.build();
    }

    private static OkHttpClient.Builder a(OkHttpClient.Builder builder) {
        if (Build.VERSION.SDK_INT < 22) {
            try {
                TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                instance.init((KeyStore) null);
                TrustManager[] trustManagers = instance.getTrustManagers();
                if (trustManagers.length == 1) {
                    if (trustManagers[0] instanceof X509TrustManager) {
                        X509TrustManager x509TrustManager = (X509TrustManager) trustManagers[0];
                        String javaName = TlsVersion.TLS_1_2.javaName();
                        if (Build.VERSION.SDK_INT < 16) {
                            javaName = TlsVersion.TLS_1_0.javaName();
                        }
                        SSLContext instance2 = SSLContext.getInstance(javaName);
                        instance2.init((KeyManager[]) null, trustManagers, new SecureRandom());
                        builder.sslSocketFactory(new am(instance2.getSocketFactory(), new String[]{javaName}), x509TrustManager);
                        ConnectionSpec build = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS).tlsVersions(TlsVersion.TLS_1_0, TlsVersion.TLS_1_1, TlsVersion.TLS_1_2).build();
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(build);
                        arrayList.add(ConnectionSpec.COMPATIBLE_TLS);
                        arrayList.add(ConnectionSpec.CLEARTEXT);
                        builder.connectionSpecs(arrayList);
                    }
                }
                Logger.error("##OkHttpWorker##", "Unexpected default trust managers:" + Arrays.toString(trustManagers));
                return builder;
            } catch (Exception e) {
                Logger.notify("##OkHttpWorker##", "Error while setting TLS", e);
            }
        }
        return builder;
    }

    public void a(ai aiVar, final ag.b bVar) {
        Request c = ((bb) aiVar).c();
        AnonymousClass1 r1 = new Callback() {
            public void onFailure(Call call, final IOException iOException) {
                Logger.debug("##OkHttpWorker##", "network error: " + iOException.getMessage());
                aa.a((Runnable) new ac() {
                    public void a() {
                        IOException iOException = iOException;
                        if (iOException instanceof SocketTimeoutException) {
                            bVar.a((Throwable) new a("timeout error"));
                        } else if (iOException instanceof SSLHandshakeException) {
                            bVar.a((Throwable) iOException);
                        } else if (iOException.getMessage() != null) {
                            bVar.a(new Throwable(iOException.getMessage()));
                        } else {
                            bVar.a((Throwable) iOException);
                        }
                    }
                });
            }

            public void onResponse(Call call, Response response) {
                try {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            bVar.a(response.body().byteStream());
                            return;
                        }
                    }
                    Logger.warning("##OkHttpWorker##", "network error code: " + response.code() + " message: " + response.message());
                    if (response.body() != null) {
                        response.body().close();
                    }
                    aa.a((Runnable) new ac() {
                        public void a() {
                            bVar.a(new Throwable("invalid response"));
                        }
                    });
                } catch (Exception e) {
                    aa.a((Runnable) new ac() {
                        public void a() {
                            bVar.a(e.getCause());
                        }
                    });
                    throw e;
                }
            }
        };
        Logger.debug("##OkHttpWorker##", "firing http request");
        final Call newCall = this.a.newCall(c);
        newCall.enqueue(r1);
        if (aiVar.a() > 0) {
            aa.a(new ac() {
                public void a() {
                    Logger.debug("##OkHttpWorker##", "call timeout");
                    if (!newCall.isExecuted() || !newCall.isCanceled()) {
                        Logger.debug("##OkHttpWorker##", "Canceling call");
                        newCall.cancel();
                    }
                }
            }, (long) aiVar.a(), TimeUnit.MILLISECONDS);
        }
    }
}
