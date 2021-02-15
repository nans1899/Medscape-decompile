package com.google.firebase.inappmessaging.internal.injection.modules;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.google.common.io.BaseEncoding;
import com.google.firebase.FirebaseApp;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.InAppMessagingSdkServingGrpc;
import dagger.Module;
import dagger.Provides;
import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class GrpcClientModule {
    private final FirebaseApp firebaseApp;

    public GrpcClientModule(FirebaseApp firebaseApp2) {
        this.firebaseApp = firebaseApp2;
    }

    @Provides
    public Metadata providesApiKeyHeaders() {
        Metadata.Key<String> of = Metadata.Key.of("X-Goog-Api-Key", Metadata.ASCII_STRING_MARSHALLER);
        Metadata.Key<String> of2 = Metadata.Key.of("X-Android-Package", Metadata.ASCII_STRING_MARSHALLER);
        Metadata.Key<String> of3 = Metadata.Key.of("X-Android-Cert", Metadata.ASCII_STRING_MARSHALLER);
        Metadata metadata = new Metadata();
        String packageName = this.firebaseApp.getApplicationContext().getPackageName();
        metadata.put(of, this.firebaseApp.getOptions().getApiKey());
        metadata.put(of2, packageName);
        String signature = getSignature(this.firebaseApp.getApplicationContext().getPackageManager(), packageName);
        if (signature != null) {
            metadata.put(of3, signature);
        }
        return metadata;
    }

    public static String getSignature(PackageManager packageManager, String str) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            if (!(packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length == 0)) {
                if (packageInfo.signatures[0] != null) {
                    return signatureDigest(packageInfo.signatures[0]);
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    private static String signatureDigest(Signature signature) {
        try {
            return BaseEncoding.base16().upperCase().encode(MessageDigest.getInstance("SHA1").digest(signature.toByteArray()));
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    @Provides
    public InAppMessagingSdkServingGrpc.InAppMessagingSdkServingBlockingStub providesInAppMessagingSdkServingStub(Channel channel, Metadata metadata) {
        return InAppMessagingSdkServingGrpc.newBlockingStub(ClientInterceptors.intercept(channel, MetadataUtils.newAttachHeadersInterceptor(metadata)));
    }
}
