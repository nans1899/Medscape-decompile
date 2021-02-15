package com.squareup.picasso;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.IOException;
import okio.Okio;

class AssetRequestHandler extends RequestHandler {
    protected static final String ANDROID_ASSET = "android_asset";
    private static final int ASSET_PREFIX_LENGTH = 22;
    private AssetManager assetManager;
    private final Context context;
    private final Object lock = new Object();

    AssetRequestHandler(Context context2) {
        this.context = context2;
    }

    public boolean canHandleRequest(Request request) {
        Uri uri = request.uri;
        if (!"file".equals(uri.getScheme()) || uri.getPathSegments().isEmpty() || !"android_asset".equals(uri.getPathSegments().get(0))) {
            return false;
        }
        return true;
    }

    public RequestHandler.Result load(Request request, int i) throws IOException {
        if (this.assetManager == null) {
            synchronized (this.lock) {
                if (this.assetManager == null) {
                    this.assetManager = this.context.getAssets();
                }
            }
        }
        return new RequestHandler.Result(Okio.source(this.assetManager.open(getFilePath(request))), Picasso.LoadedFrom.DISK);
    }

    static String getFilePath(Request request) {
        return request.uri.toString().substring(ASSET_PREFIX_LENGTH);
    }
}
