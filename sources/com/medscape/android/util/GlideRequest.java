package com.medscape.android.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import java.io.File;
import java.net.URL;

public class GlideRequest<TranscodeType> extends RequestBuilder<TranscodeType> implements Cloneable {
    GlideRequest(Class<TranscodeType> cls, RequestBuilder<?> requestBuilder) {
        super(cls, requestBuilder);
    }

    GlideRequest(Glide glide, RequestManager requestManager, Class<TranscodeType> cls, Context context) {
        super(glide, requestManager, cls, context);
    }

    /* access modifiers changed from: protected */
    public GlideRequest<File> getDownloadOnlyRequest() {
        return new GlideRequest(File.class, this).apply((BaseRequestOptions) DOWNLOAD_ONLY_OPTIONS);
    }

    public GlideRequest<TranscodeType> sizeMultiplier(float f) {
        return (GlideRequest) super.sizeMultiplier(f);
    }

    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(boolean z) {
        return (GlideRequest) super.useUnlimitedSourceGeneratorsPool(z);
    }

    public GlideRequest<TranscodeType> useAnimationPool(boolean z) {
        return (GlideRequest) super.useAnimationPool(z);
    }

    public GlideRequest<TranscodeType> onlyRetrieveFromCache(boolean z) {
        return (GlideRequest) super.onlyRetrieveFromCache(z);
    }

    public GlideRequest<TranscodeType> diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        return (GlideRequest) super.diskCacheStrategy(diskCacheStrategy);
    }

    public GlideRequest<TranscodeType> priority(Priority priority) {
        return (GlideRequest) super.priority(priority);
    }

    public GlideRequest<TranscodeType> placeholder(Drawable drawable) {
        return (GlideRequest) super.placeholder(drawable);
    }

    public GlideRequest<TranscodeType> placeholder(int i) {
        return (GlideRequest) super.placeholder(i);
    }

    public GlideRequest<TranscodeType> fallback(Drawable drawable) {
        return (GlideRequest) super.fallback(drawable);
    }

    public GlideRequest<TranscodeType> fallback(int i) {
        return (GlideRequest) super.fallback(i);
    }

    public GlideRequest<TranscodeType> error(Drawable drawable) {
        return (GlideRequest) super.error(drawable);
    }

    public GlideRequest<TranscodeType> error(int i) {
        return (GlideRequest) super.error(i);
    }

    public GlideRequest<TranscodeType> theme(Resources.Theme theme) {
        return (GlideRequest) super.theme(theme);
    }

    public GlideRequest<TranscodeType> skipMemoryCache(boolean z) {
        return (GlideRequest) super.skipMemoryCache(z);
    }

    public GlideRequest<TranscodeType> override(int i, int i2) {
        return (GlideRequest) super.override(i, i2);
    }

    public GlideRequest<TranscodeType> override(int i) {
        return (GlideRequest) super.override(i);
    }

    public GlideRequest<TranscodeType> signature(Key key) {
        return (GlideRequest) super.signature(key);
    }

    public <Y> GlideRequest<TranscodeType> set(Option<Y> option, Y y) {
        return (GlideRequest) super.set(option, y);
    }

    public GlideRequest<TranscodeType> decode(Class<?> cls) {
        return (GlideRequest) super.decode(cls);
    }

    public GlideRequest<TranscodeType> encodeFormat(Bitmap.CompressFormat compressFormat) {
        return (GlideRequest) super.encodeFormat(compressFormat);
    }

    public GlideRequest<TranscodeType> encodeQuality(int i) {
        return (GlideRequest) super.encodeQuality(i);
    }

    public GlideRequest<TranscodeType> frame(long j) {
        return (GlideRequest) super.frame(j);
    }

    public GlideRequest<TranscodeType> format(DecodeFormat decodeFormat) {
        return (GlideRequest) super.format(decodeFormat);
    }

    public GlideRequest<TranscodeType> disallowHardwareConfig() {
        return (GlideRequest) super.disallowHardwareConfig();
    }

    public GlideRequest<TranscodeType> downsample(DownsampleStrategy downsampleStrategy) {
        return (GlideRequest) super.downsample(downsampleStrategy);
    }

    public GlideRequest<TranscodeType> timeout(int i) {
        return (GlideRequest) super.timeout(i);
    }

    public GlideRequest<TranscodeType> optionalCenterCrop() {
        return (GlideRequest) super.optionalCenterCrop();
    }

    public GlideRequest<TranscodeType> centerCrop() {
        return (GlideRequest) super.centerCrop();
    }

    public GlideRequest<TranscodeType> optionalFitCenter() {
        return (GlideRequest) super.optionalFitCenter();
    }

    public GlideRequest<TranscodeType> fitCenter() {
        return (GlideRequest) super.fitCenter();
    }

    public GlideRequest<TranscodeType> optionalCenterInside() {
        return (GlideRequest) super.optionalCenterInside();
    }

    public GlideRequest<TranscodeType> centerInside() {
        return (GlideRequest) super.centerInside();
    }

    public GlideRequest<TranscodeType> optionalCircleCrop() {
        return (GlideRequest) super.optionalCircleCrop();
    }

    public GlideRequest<TranscodeType> circleCrop() {
        return (GlideRequest) super.circleCrop();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.medscape.android.util.GlideRequest<TranscodeType> transform(com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r1) {
        /*
            r0 = this;
            com.bumptech.glide.request.BaseRequestOptions r1 = super.transform((com.bumptech.glide.load.Transformation<android.graphics.Bitmap>) r1)
            com.medscape.android.util.GlideRequest r1 = (com.medscape.android.util.GlideRequest) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.GlideRequest.transform(com.bumptech.glide.load.Transformation):com.medscape.android.util.GlideRequest");
    }

    public GlideRequest<TranscodeType> transform(Transformation<Bitmap>... transformationArr) {
        return (GlideRequest) super.transform(transformationArr);
    }

    @Deprecated
    public GlideRequest<TranscodeType> transforms(Transformation<Bitmap>... transformationArr) {
        return (GlideRequest) super.transforms(transformationArr);
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.bumptech.glide.load.Transformation, com.bumptech.glide.load.Transformation<android.graphics.Bitmap>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.medscape.android.util.GlideRequest<TranscodeType> optionalTransform(com.bumptech.glide.load.Transformation<android.graphics.Bitmap> r1) {
        /*
            r0 = this;
            com.bumptech.glide.request.BaseRequestOptions r1 = super.optionalTransform(r1)
            com.medscape.android.util.GlideRequest r1 = (com.medscape.android.util.GlideRequest) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.util.GlideRequest.optionalTransform(com.bumptech.glide.load.Transformation):com.medscape.android.util.GlideRequest");
    }

    public <Y> GlideRequest<TranscodeType> optionalTransform(Class<Y> cls, Transformation<Y> transformation) {
        return (GlideRequest) super.optionalTransform(cls, transformation);
    }

    public <Y> GlideRequest<TranscodeType> transform(Class<Y> cls, Transformation<Y> transformation) {
        return (GlideRequest) super.transform(cls, transformation);
    }

    public GlideRequest<TranscodeType> dontTransform() {
        return (GlideRequest) super.dontTransform();
    }

    public GlideRequest<TranscodeType> dontAnimate() {
        return (GlideRequest) super.dontAnimate();
    }

    public GlideRequest<TranscodeType> apply(BaseRequestOptions<?> baseRequestOptions) {
        return (GlideRequest) super.apply(baseRequestOptions);
    }

    public GlideRequest<TranscodeType> transition(TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        return (GlideRequest) super.transition(transitionOptions);
    }

    public GlideRequest<TranscodeType> listener(RequestListener<TranscodeType> requestListener) {
        return (GlideRequest) super.listener(requestListener);
    }

    public GlideRequest<TranscodeType> addListener(RequestListener<TranscodeType> requestListener) {
        return (GlideRequest) super.addListener(requestListener);
    }

    public GlideRequest<TranscodeType> error(RequestBuilder<TranscodeType> requestBuilder) {
        return (GlideRequest) super.error(requestBuilder);
    }

    public GlideRequest<TranscodeType> thumbnail(RequestBuilder<TranscodeType> requestBuilder) {
        return (GlideRequest) super.thumbnail(requestBuilder);
    }

    @SafeVarargs
    public final GlideRequest<TranscodeType> thumbnail(RequestBuilder<TranscodeType>... requestBuilderArr) {
        return (GlideRequest) super.thumbnail(requestBuilderArr);
    }

    public GlideRequest<TranscodeType> thumbnail(float f) {
        return (GlideRequest) super.thumbnail(f);
    }

    public GlideRequest<TranscodeType> load(Object obj) {
        return (GlideRequest) super.load(obj);
    }

    public GlideRequest<TranscodeType> load(Bitmap bitmap) {
        return (GlideRequest) super.load(bitmap);
    }

    public GlideRequest<TranscodeType> load(Drawable drawable) {
        return (GlideRequest) super.load(drawable);
    }

    public GlideRequest<TranscodeType> load(String str) {
        return (GlideRequest) super.load(str);
    }

    public GlideRequest<TranscodeType> load(Uri uri) {
        return (GlideRequest) super.load(uri);
    }

    public GlideRequest<TranscodeType> load(File file) {
        return (GlideRequest) super.load(file);
    }

    public GlideRequest<TranscodeType> load(Integer num) {
        return (GlideRequest) super.load(num);
    }

    @Deprecated
    public GlideRequest<TranscodeType> load(URL url) {
        return (GlideRequest) super.load(url);
    }

    public GlideRequest<TranscodeType> load(byte[] bArr) {
        return (GlideRequest) super.load(bArr);
    }

    public GlideRequest<TranscodeType> clone() {
        return (GlideRequest) super.clone();
    }
}
