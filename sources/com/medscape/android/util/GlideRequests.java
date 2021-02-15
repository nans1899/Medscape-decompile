package com.medscape.android.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.net.URL;

public class GlideRequests extends RequestManager {
    public GlideRequests(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode requestManagerTreeNode, Context context) {
        super(glide, lifecycle, requestManagerTreeNode, context);
    }

    public <ResourceType> GlideRequest<ResourceType> as(Class<ResourceType> cls) {
        return new GlideRequest<>(this.glide, this, cls, this.context);
    }

    public synchronized GlideRequests applyDefaultRequestOptions(RequestOptions requestOptions) {
        return (GlideRequests) super.applyDefaultRequestOptions(requestOptions);
    }

    public synchronized GlideRequests setDefaultRequestOptions(RequestOptions requestOptions) {
        return (GlideRequests) super.setDefaultRequestOptions(requestOptions);
    }

    public GlideRequests addDefaultRequestListener(RequestListener<Object> requestListener) {
        return (GlideRequests) super.addDefaultRequestListener(requestListener);
    }

    public GlideRequest<Bitmap> asBitmap() {
        return (GlideRequest) super.asBitmap();
    }

    public GlideRequest<GifDrawable> asGif() {
        return (GlideRequest) super.asGif();
    }

    public GlideRequest<Drawable> asDrawable() {
        return (GlideRequest) super.asDrawable();
    }

    public GlideRequest<Drawable> load(Bitmap bitmap) {
        return (GlideRequest) super.load(bitmap);
    }

    public GlideRequest<Drawable> load(Drawable drawable) {
        return (GlideRequest) super.load(drawable);
    }

    public GlideRequest<Drawable> load(String str) {
        return (GlideRequest) super.load(str);
    }

    public GlideRequest<Drawable> load(Uri uri) {
        return (GlideRequest) super.load(uri);
    }

    public GlideRequest<Drawable> load(File file) {
        return (GlideRequest) super.load(file);
    }

    public GlideRequest<Drawable> load(Integer num) {
        return (GlideRequest) super.load(num);
    }

    @Deprecated
    public GlideRequest<Drawable> load(URL url) {
        return (GlideRequest) super.load(url);
    }

    public GlideRequest<Drawable> load(byte[] bArr) {
        return (GlideRequest) super.load(bArr);
    }

    public GlideRequest<Drawable> load(Object obj) {
        return (GlideRequest) super.load(obj);
    }

    public GlideRequest<File> downloadOnly() {
        return (GlideRequest) super.downloadOnly();
    }

    public GlideRequest<File> download(Object obj) {
        return (GlideRequest) super.download(obj);
    }

    public GlideRequest<File> asFile() {
        return (GlideRequest) super.asFile();
    }

    /* access modifiers changed from: protected */
    public void setRequestOptions(RequestOptions requestOptions) {
        if (requestOptions instanceof GlideOptions) {
            super.setRequestOptions(requestOptions);
        } else {
            super.setRequestOptions(new GlideOptions().apply((BaseRequestOptions) requestOptions));
        }
    }
}
