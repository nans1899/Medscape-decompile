package com.medscape.android.player;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.VideoView;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbBaseActivity;
import com.medscape.android.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class VideoPlayer extends AbstractBreadcrumbBaseActivity implements MediaPlayer.OnErrorListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {
    private static final int FAIL_TO_LOAD = 8;
    protected static final int LOAD_VIDEO = 7;
    protected static final int RESUME_PLAY = 4;
    protected static final int START_BACKFROUND_PROGRESS = 5;
    protected static final int START_PLAY = 3;
    protected static final int STOP_BACKFROUND_PROGRESS = 6;
    protected static final int STOP_PLAY = 2;
    protected static final int UPDATE_PROGRESS_BAR_STATUS = 1;
    private static final String VIDEO_POSITION = "videoPosition";
    private static final String VIDEO_SRC = "videosrc";
    private String current;
    private String datasrc = null;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 2:
                    VideoPlayer.this.stopPlay();
                    return true;
                case 3:
                    VideoPlayer.this.startPlay(message.obj.toString(), message.arg1);
                    return true;
                case 4:
                    VideoPlayer.this.restartPlay();
                    return true;
                case 5:
                    VideoPlayer.this.findViewById(R.id.progress).setVisibility(0);
                    return true;
                case 6:
                    VideoPlayer.this.findViewById(R.id.progress).setVisibility(8);
                    return true;
                case 7:
                    new Thread(VideoPlayer.this.r).start();
                    return true;
                default:
                    return true;
            }
        }
    });
    /* access modifiers changed from: private */
    public VideoView mVideoView;
    private MediaPlayer mp;
    private String path;
    Runnable r = new Runnable() {
        public void run() {
            VideoPlayer.this.playVideo1();
        }
    };

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
    }

    public boolean onSearchRequested() {
        return false;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.layout_videoplayer);
        this.mVideoView = (VideoView) findViewById(R.id.surface);
        ImageButton imageButton = (ImageButton) findViewById(R.id.play);
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.pause);
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.reset);
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.stop);
        String string = getIntent().getExtras().getString("articleTitle");
        if (string == null || string.equals("")) {
            setTitle(getResources().getString(R.string.clinical_procedure_header_text_view));
        } else {
            setTitle(string);
        }
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (VideoPlayer.this.mVideoView != null && !VideoPlayer.this.mVideoView.isPlaying()) {
                    VideoPlayer.this.mVideoView.start();
                }
            }
        });
        this.path = getIntent().getStringExtra("path");
        imageButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (VideoPlayer.this.mVideoView != null && VideoPlayer.this.mVideoView.isPlaying()) {
                    VideoPlayer.this.mVideoView.pause();
                }
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (VideoPlayer.this.mVideoView != null) {
                    VideoPlayer.this.mVideoView.seekTo(0);
                }
            }
        });
        imageButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (VideoPlayer.this.mVideoView != null) {
                    VideoPlayer.this.mVideoView.seekTo(0);
                    VideoPlayer.this.mVideoView.pause();
                }
            }
        });
        getWindow().setFormat(-2);
        if (bundle == null || !bundle.containsKey(VIDEO_SRC)) {
            loadVideo();
            return;
        }
        Message message = new Message();
        message.what = 3;
        int i = 0;
        if (bundle.containsKey(VIDEO_POSITION)) {
            i = Integer.parseInt(bundle.get(VIDEO_POSITION).toString());
        }
        message.arg1 = i;
        message.obj = bundle.get(VIDEO_SRC);
        this.h.sendEmptyMessage(5);
        this.h.sendMessage(message);
    }

    public void loadVideo() {
        this.h.sendEmptyMessage(7);
        this.h.sendEmptyMessage(5);
    }

    private void setDataSource(String str) throws IOException {
        if (!URLUtil.isNetworkUrl(str)) {
            this.mp.setDataSource(str);
            return;
        }
        URLConnection openConnection = new URL(str).openConnection();
        openConnection.connect();
        InputStream inputStream = openConnection.getInputStream();
        if (inputStream != null) {
            File createTempFile = File.createTempFile("mediaplayertmp", "dat");
            String absolutePath = createTempFile.getAbsolutePath();
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            byte[] bArr = new byte[128];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    this.mp.setDataSource(absolutePath);
                    try {
                        inputStream.close();
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                } else {
                    fileOutputStream.write(bArr, 0, read);
                }
            }
        } else {
            throw new RuntimeException("stream is null");
        }
    }

    public void onPause() {
        super.onPause();
        MediaPlayer mediaPlayer = this.mp;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.mp.stop();
            this.mp.release();
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (mediaPlayer == null) {
            return false;
        }
        mediaPlayer.stop();
        mediaPlayer.release();
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        String str = this.datasrc;
        if (!(str == null || str.length() == 0)) {
            bundle.putString(VIDEO_SRC, this.datasrc);
        }
        VideoView videoView = this.mVideoView;
        if (videoView != null && videoView.isPlaying()) {
            bundle.putString(VIDEO_POSITION, "" + this.mVideoView.getCurrentPosition());
        }
    }

    /* access modifiers changed from: private */
    public void playVideo1() {
        try {
            if (this.path != null && this.path.length() != 0) {
                if (!this.path.equals(this.current) || this.mVideoView == null) {
                    if (this.path != null) {
                        if (this.path.length() != 0) {
                            this.current = this.path;
                            Message message = new Message();
                            message.obj = getDataSource(this.path);
                            message.what = 3;
                            this.h.sendMessage(message);
                            return;
                        }
                    }
                    this.h.sendEmptyMessage(6);
                    this.h.sendEmptyMessage(8);
                    return;
                }
                this.h.sendEmptyMessage(4);
            }
        } catch (Exception unused) {
            this.h.sendEmptyMessage(2);
            this.h.sendEmptyMessage(6);
        }
    }

    /* access modifiers changed from: private */
    public void restartPlay() {
        VideoView videoView = this.mVideoView;
        if (videoView != null) {
            videoView.start();
            this.mVideoView.requestFocus();
            this.h.sendEmptyMessage(6);
        }
    }

    /* access modifiers changed from: private */
    public void startPlay(String str, int i) {
        VideoView videoView = this.mVideoView;
        if (videoView != null) {
            this.datasrc = str;
            videoView.setVideoPath(str);
            this.mVideoView.start();
            this.mVideoView.requestFocus();
            this.mVideoView.seekTo(i);
            this.h.sendEmptyMessage(6);
        }
    }

    /* access modifiers changed from: private */
    public void stopPlay() {
        VideoView videoView = this.mVideoView;
        if (videoView != null) {
            videoView.stopPlayback();
        }
    }

    private String getDataSource(String str) throws IOException {
        if (!URLUtil.isNetworkUrl(str)) {
            return str;
        }
        URLConnection openConnection = new URL(str).openConnection();
        openConnection.connect();
        openConnection.setReadTimeout(Util.TIMEOUT);
        InputStream inputStream = openConnection.getInputStream();
        if (inputStream != null) {
            File createTempFile = File.createTempFile("mediaplayertmp", "dat");
            createTempFile.deleteOnExit();
            String absolutePath = createTempFile.getAbsolutePath();
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            byte[] bArr = new byte[128];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    try {
                        break;
                    } catch (IOException unused) {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    fileOutputStream.write(bArr, 0, read);
                }
            }
            inputStream.close();
            return absolutePath;
        }
        throw new RuntimeException("stream is null");
    }
}
