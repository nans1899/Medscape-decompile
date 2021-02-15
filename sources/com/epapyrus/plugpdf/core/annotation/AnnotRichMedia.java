package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.VideoView;
import com.epapyrus.plugpdf.core.PDFDocument;
import java.io.File;

public class AnnotRichMedia extends BaseAnnot {
    private Context mContext;
    /* access modifiers changed from: private */
    public String mMediaFilePath;
    /* access modifiers changed from: private */
    public File mTempMediaFile;
    /* access modifiers changed from: private */
    public VideoView videoView;

    public AnnotRichMedia(Context context) {
        super(context, "RICHMEDIA");
        this.mContext = context;
        setScale(1.0f);
    }

    public void setMediaFileName(String str) {
        this.mMediaFilePath = this.mContext.getExternalCacheDir().getAbsolutePath() + File.separator + str;
    }

    public void playMovie(PDFDocument pDFDocument) {
        try {
            if (this.videoView == null) {
                this.videoView = new VideoView(this.mContext);
                ((ViewGroup) getParent()).addView(this.videoView);
                pDFDocument.loadRichMediaFile(this.mPageIdx, getObjID(), 0, this.mMediaFilePath);
            }
            this.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    Log.e("PlugPDF", "[ERROR] AnnotRichMedia.playMovie error, error code: " + i);
                    ((ViewGroup) AnnotRichMedia.this.getParent()).removeView(AnnotRichMedia.this.videoView);
                    VideoView unused = AnnotRichMedia.this.videoView = null;
                    File unused2 = AnnotRichMedia.this.mTempMediaFile = new File(AnnotRichMedia.this.mMediaFilePath);
                    Uri fromFile = Uri.fromFile(AnnotRichMedia.this.mTempMediaFile);
                    String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(fromFile.toString()));
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addCategory("android.intent.category.DEFAULT");
                    intent.setDataAndType(fromFile, mimeTypeFromExtension);
                    intent.addFlags(268435456);
                    AnnotRichMedia.this.getContext().startActivity(intent);
                    return false;
                }
            });
            this.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mediaPlayer) {
                    AnnotRichMedia.this.videoView.setVisibility(8);
                    AnnotRichMedia.this.setVisibility(0);
                }
            });
            if (this.videoView.isPlaying()) {
                this.videoView.stopPlayback();
                this.videoView.setVisibility(8);
                setVisibility(0);
                return;
            }
            this.videoView.setVisibility(0);
            setVisibility(8);
            this.videoView.layout((int) (this.mBbox.left * this.mScale), (int) (this.mBbox.top * this.mScale), (int) (this.mBbox.right * this.mScale), (int) (this.mBbox.bottom * this.mScale));
            this.videoView.setVideoPath(this.mMediaFilePath);
            this.videoView.requestFocus();
            this.videoView.start();
        } catch (Exception e) {
            Log.e("PlugPDF", "[ERROR] AnnotRichMedia.playMovie ", e);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        VideoView videoView2 = this.videoView;
        if (videoView2 != null) {
            videoView2.layout((int) (this.mBbox.left * this.mScale), (int) (this.mBbox.top * this.mScale), (int) (this.mBbox.right * this.mScale), (int) (this.mBbox.bottom * this.mScale));
        }
    }

    public void bringToFront() {
        super.bringToFront();
        VideoView videoView2 = this.videoView;
        if (videoView2 != null) {
            videoView2.bringToFront();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        File file = this.mTempMediaFile;
        if (file != null && file.exists()) {
            this.mTempMediaFile.delete();
        }
    }
}
