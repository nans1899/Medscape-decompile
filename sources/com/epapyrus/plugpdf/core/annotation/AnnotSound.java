package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import com.epapyrus.plugpdf.core.PDFDocument;
import java.io.File;
import java.io.IOException;

public class AnnotSound extends BaseAnnot {
    private String mFilePath;
    /* access modifiers changed from: private */
    public MediaPlayer player;

    public AnnotSound(Context context) {
        super(context, "SOUND");
        setScale(1.0f);
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.player = mediaPlayer;
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mediaPlayer) {
                AnnotSound.this.player.release();
            }
        });
    }

    public void setFilePath(String str) {
        this.mFilePath = str;
    }

    public void playSound(PDFDocument pDFDocument) {
        try {
            if (!new File(this.mFilePath).exists()) {
                pDFDocument.writeSoundFile(this.mPageIdx, getObjID(), 0, this.mFilePath);
            }
            if (this.player.isPlaying()) {
                this.player.stop();
                return;
            }
            this.player.reset();
            this.player.setAudioStreamType(3);
            this.player.setDataSource(this.mFilePath);
            this.player.prepare();
            this.player.start();
        } catch (IOException e) {
            Log.d("PlugPDF", "[DEBUG] AnnotSound.playSound ", e);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mFilePath != null) {
            File file = new File(this.mFilePath);
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
