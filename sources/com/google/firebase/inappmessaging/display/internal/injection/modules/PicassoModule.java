package com.google.firebase.inappmessaging.display.internal.injection.modules;

import android.app.Application;
import com.google.firebase.inappmessaging.display.internal.PicassoErrorListener;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class PicassoModule {
    /* access modifiers changed from: package-private */
    @Provides
    public Picasso providesFiamController(Application application, PicassoErrorListener picassoErrorListener) {
        OkHttpClient build = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().addHeader("Accept", "image/*").build());
            }
        }).build();
        Picasso.Builder builder = new Picasso.Builder(application);
        builder.listener(picassoErrorListener).downloader(new OkHttp3Downloader(build));
        return builder.build();
    }
}
