package coil;

import android.content.Context;
import coil.util.CoilUtils;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.OkHttpClient;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lokhttp3/OkHttpClient;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: ImageLoaderBuilder.kt */
final class ImageLoaderBuilder$buildDefaultCallFactory$1 extends Lambda implements Function0<OkHttpClient> {
    final /* synthetic */ ImageLoaderBuilder this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageLoaderBuilder$buildDefaultCallFactory$1(ImageLoaderBuilder imageLoaderBuilder) {
        super(0);
        this.this$0 = imageLoaderBuilder;
    }

    public final OkHttpClient invoke() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Context access$getApplicationContext$p = this.this$0.applicationContext;
        Intrinsics.checkExpressionValueIsNotNull(access$getApplicationContext$p, "applicationContext");
        OkHttpClient build = builder.cache(CoilUtils.createDefaultCache(access$getApplicationContext$p)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "OkHttpClient.Builder()\n …xt))\n            .build()");
        return build;
    }
}
