package com.medscape.android.contentviewer.view_holders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.reference.model.Figure;
import com.medscape.android.util.GlideApp;

public class FigureDataViewHolder extends DataViewHolder {
    private static final double SCREEN_DENSITY = 10.0d;
    public final ImageView graphic;
    public Context mContext;
    /* access modifiers changed from: private */
    public DataUpdatedListener mImageLoadedListener;
    private CustomViewTarget mImageTarget;
    public boolean mIsImageLoaded = false;
    /* access modifiers changed from: private */
    public boolean mIsOnBind;

    public interface DataUpdatedListener {
        void onDataUpdated(int i);
    }

    public String toString() {
        return "figuredataviewholder";
    }

    public FigureDataViewHolder(View view, DataViewHolder.DataListClickListener dataListClickListener, DataUpdatedListener dataUpdatedListener, Context context) {
        super(view);
        this.mListener = dataListClickListener;
        ImageView imageView = (ImageView) view.findViewById(R.id.graphic);
        this.graphic = imageView;
        imageView.setOnClickListener(this);
        this.mContext = context;
        this.mImageLoadedListener = dataUpdatedListener;
        this.graphic.setImageBitmap((Bitmap) null);
    }

    public void bindFigure(Figure figure, final int i) {
        this.graphic.setImageBitmap((Bitmap) null);
        setLayoutParams(false);
        this.mIsOnBind = true;
        if (figure != null) {
            Bitmap decodeResource = BitmapFactory.decodeResource(MedscapeApplication.get().getResources(), figure.isVideo() ? R.drawable.ic_play_circle_outline_black_48dp : R.drawable.ic_insert_photo_black_48dp);
            if (figure.isImage()) {
                setLayoutParams(false);
                this.graphic.refreshDrawableState();
                this.graphic.setImageBitmap(decodeResource);
                if (!(figure.thumbnail == null || figure.thumbnail.href == null)) {
                    this.mImageTarget = new CustomViewTarget<ImageView, Bitmap>(this.graphic) {
                        public void onLoadFailed(Drawable drawable) {
                        }

                        /* access modifiers changed from: protected */
                        public void onResourceCleared(Drawable drawable) {
                        }

                        /* access modifiers changed from: protected */
                        public void onResourceLoading(Drawable drawable) {
                            super.onResourceLoading(drawable);
                            FigureDataViewHolder.this.setLayoutParams(false);
                        }

                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                            FigureDataViewHolder.this.graphic.setImageBitmap(bitmap);
                            if (!FigureDataViewHolder.this.mIsImageLoaded) {
                                if (!FigureDataViewHolder.this.mIsOnBind) {
                                    FigureDataViewHolder.this.mIsImageLoaded = true;
                                    FigureDataViewHolder.this.mImageLoadedListener.onDataUpdated(i);
                                } else {
                                    FigureDataViewHolder.this.graphic.postDelayed(new Runnable() {
                                        public void run() {
                                            if (!FigureDataViewHolder.this.mIsOnBind) {
                                                FigureDataViewHolder.this.mIsImageLoaded = true;
                                                FigureDataViewHolder.this.mImageLoadedListener.onDataUpdated(i);
                                            }
                                        }
                                    }, 10);
                                }
                            }
                            FigureDataViewHolder.this.setLayoutParams(true);
                        }
                    };
                    GlideApp.with(this.mContext).asBitmap().load(figure.thumbnail.href).into(this.mImageTarget);
                }
            } else {
                this.graphic.setLayoutParams(new LinearLayout.LayoutParams(400, 330));
                this.graphic.setPadding(0, 0, 0, 0);
                this.graphic.setImageBitmap(decodeResource);
            }
        }
        this.mIsOnBind = false;
    }

    public void setLayoutParams(boolean z) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 660);
        if (z) {
            try {
                Bitmap bitmap = ((BitmapDrawable) this.graphic.getDrawable()).getBitmap();
                if (!(bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0)) {
                    float width = (float) (this.graphic.getWidth() / bitmap.getWidth());
                    if (width != 0.0f && ((double) (((float) bitmap.getHeight()) * width)) < 660.0d) {
                        layoutParams = new LinearLayout.LayoutParams(-1, -2);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            this.graphic.setColorFilter((ColorFilter) null);
            this.graphic.setPadding(0, 0, 0, 0);
        } else {
            this.graphic.setColorFilter(Color.argb(255, 85, 85, 85));
            this.graphic.setPadding(40, 40, 40, 40);
        }
        this.graphic.setLayoutParams(layoutParams);
    }
}
