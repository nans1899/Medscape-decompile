package com.medscape.android.slideshow;

import android.media.MediaPlayer;
import android.view.View;
import com.medscape.android.R;

/* compiled from: SlideshowViewer */
class SlideshowAudioHandler implements View.OnClickListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    private int currentMediaPosition = 0;
    volatile boolean isPausedByApp;
    volatile boolean isPlaying;
    private String mAudioUrl;
    private MediaPlayer mMediaPlayer;

    public void resetPlayer() {
    }

    SlideshowAudioHandler() {
    }

    /* access modifiers changed from: package-private */
    public void removeMediaPlayer() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        this.mMediaPlayer = null;
    }

    /* access modifiers changed from: package-private */
    public void setAudioUrl(String str) {
        this.mAudioUrl = str;
    }

    public void onClick(View view) {
        if (!this.isPlaying) {
            startPlaying();
            view.setBackgroundResource(R.drawable.ic_audio_cancel);
            return;
        }
        stopIfPlaying();
        this.isPausedByApp = false;
        view.setBackgroundResource(R.drawable.ic_action_play);
    }

    private void startPlaying() {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mMediaPlayer = mediaPlayer;
            mediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.setOnPreparedListener(this);
            this.mMediaPlayer.setOnErrorListener(this);
            this.mMediaPlayer.setDataSource(this.mAudioUrl);
            this.mMediaPlayer.prepareAsync();
        } catch (Exception unused) {
            this.mMediaPlayer.reset();
        }
    }

    /* access modifiers changed from: package-private */
    public void stopIfPlaying() {
        if (this.isPlaying) {
            this.currentMediaPosition = this.mMediaPlayer.getCurrentPosition();
            this.mMediaPlayer.pause();
            this.isPlaying = false;
        }
    }

    /* access modifiers changed from: package-private */
    public void resumeIfPaused() {
        MediaPlayer mediaPlayer;
        if (this.isPausedByApp && (mediaPlayer = this.mMediaPlayer) != null) {
            mediaPlayer.start();
            this.mMediaPlayer.seekTo(this.currentMediaPosition);
            this.isPlaying = true;
            this.isPausedByApp = false;
        }
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.mMediaPlayer.start();
        this.isPlaying = true;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.mMediaPlayer.reset();
        this.isPlaying = false;
        return false;
    }
}
