package com.medscape.android.reference;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.util.ExtensionsAspectRatio;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.concurrent.TimeUnit;

public class FullScreenVideoPlayer extends BaseActivity {
    /* access modifiers changed from: private */
    public ImageView closeIcon;
    /* access modifiers changed from: private */
    public boolean mAreVideoControlsVisible;
    /* access modifiers changed from: private */
    public RelativeLayout mControlsLayout;
    /* access modifiers changed from: private */
    public Button mFullScreenButton;
    private boolean mHasVideo;
    /* access modifiers changed from: private */
    public boolean mIsFullScreenActive;
    private Button mPlayPauseButton;
    /* access modifiers changed from: private */
    public ProgressBar mProgressBar;
    /* access modifiers changed from: private */
    public SeekBar mSeekBar;
    /* access modifiers changed from: private */
    public TextView mTimeElapsedTextView;
    /* access modifiers changed from: private */
    public TextView mTimeRemainingTextView;
    private RelativeLayout mVideoContainerRelativeLatout;
    /* access modifiers changed from: private */
    public AlphaAnimation mVideoControlsFadeOutAnimation;
    /* access modifiers changed from: private */
    public VideoView mVideoView;
    /* access modifiers changed from: private */
    public Runnable onEverySecond = new Runnable() {
        public void run() {
            if (FullScreenVideoPlayer.this.mSeekBar != null) {
                FullScreenVideoPlayer.this.mSeekBar.setProgress(FullScreenVideoPlayer.this.mVideoView.getCurrentPosition());
            }
            if (FullScreenVideoPlayer.this.mVideoView.getCurrentPosition() == FullScreenVideoPlayer.this.mVideoView.getDuration()) {
                FullScreenVideoPlayer.this.mTimeRemainingTextView.setText(FullScreenVideoPlayer.this.getMinutesAndSecondsFromMilliseconds(0));
            }
            if (FullScreenVideoPlayer.this.mVideoView.isPlaying()) {
                FullScreenVideoPlayer.this.mSeekBar.postDelayed(FullScreenVideoPlayer.this.onEverySecond, 1000);
                FullScreenVideoPlayer.this.mTimeElapsedTextView.setText(FullScreenVideoPlayer.this.getMinutesAndSecondsFromMilliseconds(FullScreenVideoPlayer.this.mVideoView.getCurrentPosition()));
                StringBuilder sb = new StringBuilder();
                sb.append("-");
                FullScreenVideoPlayer fullScreenVideoPlayer = FullScreenVideoPlayer.this;
                sb.append(fullScreenVideoPlayer.getMinutesAndSecondsFromMilliseconds(fullScreenVideoPlayer.mVideoView.getDuration() - FullScreenVideoPlayer.this.mVideoView.getCurrentPosition()));
                FullScreenVideoPlayer.this.mTimeRemainingTextView.setText(sb.toString());
            }
        }
    };
    private String videoUrl = "";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_video_player);
        Intent intent = getIntent();
        this.videoUrl = intent.getStringExtra(Constants.BUNDLE_KEY_VIDEO_URL);
        initializeVideo(intent.getBooleanExtra(Constants.BUNDLE_KEY_VIDEO_AUTOPLAY, false));
    }

    private void initializeVideo(final boolean z) {
        this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        ImageView imageView = (ImageView) findViewById(R.id.close_video);
        this.closeIcon = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FullScreenVideoPlayer.this.finish();
            }
        });
        this.mProgressBar.setVisibility(0);
        this.mHasVideo = false;
        this.mHasVideo = true;
        this.mIsFullScreenActive = false;
        this.mAreVideoControlsVisible = true;
        this.mSeekBar = (SeekBar) findViewById(R.id.seek_bar);
        TextView textView = (TextView) findViewById(R.id.time_elapsed_text_view);
        this.mTimeElapsedTextView = textView;
        textView.setText("00:00");
        this.mTimeRemainingTextView = (TextView) findViewById(R.id.time_remaining_text_view);
        this.mVideoContainerRelativeLatout = (RelativeLayout) findViewById(R.id.video_container);
        this.mControlsLayout = (RelativeLayout) findViewById(R.id.controls_layout);
        VideoView videoView = (VideoView) findViewById(R.id.main_video_view);
        this.mVideoView = videoView;
        videoView.setVideoURI(Uri.parse(this.videoUrl));
        this.mFullScreenButton = (Button) findViewById(R.id.full_screen_button);
        this.mPlayPauseButton = (Button) findViewById(R.id.play_pause_button);
        setDefaultVideoParams(false);
        this.mVideoView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (FullScreenVideoPlayer.this.mVideoControlsFadeOutAnimation != null) {
                    FullScreenVideoPlayer.this.mVideoControlsFadeOutAnimation.cancel();
                }
                if (!FullScreenVideoPlayer.this.mAreVideoControlsVisible) {
                    FullScreenVideoPlayer.this.fadeInVideoControlsLayout();
                }
                if (FullScreenVideoPlayer.this.mVideoView.isPlaying()) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            FullScreenVideoPlayer.this.fadeOutVideoControlsLayout();
                        }
                    }, 2000);
                }
                return FullScreenVideoPlayer.this.mIsFullScreenActive;
            }
        });
        this.mControlsLayout.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FullScreenVideoPlayer.this.keepVideoControlsVisible(motionEvent);
                return false;
            }
        });
        this.mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.seekTo(1000);
                FullScreenVideoPlayer.this.mSeekBar.setProgress(1000);
                FullScreenVideoPlayer.this.mSeekBar.setMax(FullScreenVideoPlayer.this.mVideoView.getDuration());
                FullScreenVideoPlayer.this.mSeekBar.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        FullScreenVideoPlayer.this.keepVideoControlsVisible(motionEvent);
                        return false;
                    }
                });
                TextView access$900 = FullScreenVideoPlayer.this.mTimeRemainingTextView;
                FullScreenVideoPlayer fullScreenVideoPlayer = FullScreenVideoPlayer.this;
                access$900.setText(fullScreenVideoPlayer.getMinutesAndSecondsFromMilliseconds(fullScreenVideoPlayer.mVideoView.getDuration()));
                if (z) {
                    FullScreenVideoPlayer.this.mVideoView.start();
                } else {
                    FullScreenVideoPlayer.this.mVideoView.seekTo(0);
                }
                FullScreenVideoPlayer.this.mProgressBar.setVisibility(8);
                FullScreenVideoPlayer.this.closeIcon.setVisibility(0);
            }
        });
        this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    FullScreenVideoPlayer.this.mVideoView.seekTo(i);
                }
            }
        });
        this.mFullScreenButton.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    if (!FullScreenVideoPlayer.this.mIsFullScreenActive) {
                        boolean unused = FullScreenVideoPlayer.this.mIsFullScreenActive = true;
                        FullScreenVideoPlayer.this.setRequestedOrientation(-1);
                        FullScreenVideoPlayer.this.setFullScreenModeParams();
                    } else {
                        FullScreenVideoPlayer.this.setRequestedOrientation(1);
                        FullScreenVideoPlayer.this.setDefaultVideoParams(true);
                        FullScreenVideoPlayer.this.mFullScreenButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, FullScreenVideoPlayer.this.getResources().getDrawable(R.drawable.ic_fullscreen_white_24dp), (Drawable) null);
                    }
                }
                FullScreenVideoPlayer.this.keepVideoControlsVisible(motionEvent);
                return true;
            }
        });
        this.mTimeRemainingTextView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FullScreenVideoPlayer.this.keepVideoControlsVisible(motionEvent);
                return false;
            }
        });
        this.mTimeElapsedTextView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                FullScreenVideoPlayer.this.keepVideoControlsVisible(motionEvent);
                return false;
            }
        });
        this.mPlayPauseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FullScreenVideoPlayer.this.mVideoView.isPlaying()) {
                    FullScreenVideoPlayer.this.pauseVideo();
                } else {
                    FullScreenVideoPlayer.this.playVideo();
                }
            }
        });
    }

    public void playVideo() {
        VideoView videoView = this.mVideoView;
        videoView.seekTo(videoView.getCurrentPosition());
        this.mVideoView.start();
        this.mSeekBar.postDelayed(this.onEverySecond, 1000);
        this.mPlayPauseButton.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_pause_white_24dp), (Drawable) null, (Drawable) null, (Drawable) null);
        fadeOutVideoControlsLayout();
    }

    private void resizeControlsToMatchVideoWidth(final int i) {
        this.mVideoView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int i = i;
                if (i <= 0) {
                    i = FullScreenVideoPlayer.this.mVideoView.getWidth();
                }
                if (FullScreenVideoPlayer.this.mVideoView.canPause() && i > 0) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, -2);
                    layoutParams.addRule(12);
                    layoutParams.addRule(14);
                    FullScreenVideoPlayer.this.mControlsLayout.setLayoutParams(layoutParams);
                    try {
                        if (Build.VERSION.SDK_INT < 16) {
                            FullScreenVideoPlayer.this.mVideoView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            FullScreenVideoPlayer.this.mVideoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    } catch (Exception unused) {
                        Trace.w("FullScreenVideo", "Failed to remove global layout listener");
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void pauseVideo() {
        VideoView videoView = this.mVideoView;
        if (videoView != null && this.mPlayPauseButton != null) {
            videoView.pause();
            this.mPlayPauseButton.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_play_arrow_white_24dp), (Drawable) null, (Drawable) null, (Drawable) null);
            fadeInVideoControlsLayout();
        }
    }

    /* access modifiers changed from: private */
    public void keepVideoControlsVisible(MotionEvent motionEvent) {
        if (!this.mAreVideoControlsVisible) {
            AlphaAnimation alphaAnimation = this.mVideoControlsFadeOutAnimation;
            if (alphaAnimation != null) {
                alphaAnimation.cancel();
            }
            fadeInVideoControlsLayout();
        }
        if (motionEvent.getAction() == 1 && this.mVideoView.isPlaying()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    FullScreenVideoPlayer.this.fadeOutVideoControlsLayout();
                }
            }, 2000);
        }
    }

    /* access modifiers changed from: private */
    public String getMinutesAndSecondsFromMilliseconds(int i) {
        long j = (long) i;
        return String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(j)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(j)))});
    }

    private float getLogicalDensitry() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.density;
    }

    /* access modifiers changed from: private */
    public void fadeOutVideoControlsLayout() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        this.mVideoControlsFadeOutAnimation = alphaAnimation;
        alphaAnimation.setDuration(1000);
        this.mVideoControlsFadeOutAnimation.setStartOffset(2000);
        this.mVideoControlsFadeOutAnimation.setRepeatMode(2);
        this.mControlsLayout.startAnimation(this.mVideoControlsFadeOutAnimation);
        this.mVideoControlsFadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                boolean unused = FullScreenVideoPlayer.this.mAreVideoControlsVisible = false;
            }

            public void onAnimationEnd(Animation animation) {
                FullScreenVideoPlayer.this.mControlsLayout.setVisibility(4);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setFullScreenModeParams() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        getWindow().getDecorView().setSystemUiVisibility(5894);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoContainerRelativeLatout.getLayoutParams();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (getResources().getConfiguration().orientation == 1) {
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int adjustedHeight = ExtensionsAspectRatio.getAdjustedHeight(4, 3, displayMetrics.widthPixels);
            layoutParams.width = displayMetrics.widthPixels;
            layoutParams.height = adjustedHeight;
            this.mVideoView.getLayoutParams().width = displayMetrics.widthPixels;
            this.mVideoView.getLayoutParams().height = adjustedHeight;
        } else {
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int adjustedHeight2 = ExtensionsAspectRatio.getAdjustedHeight(16, 9, displayMetrics.widthPixels);
            layoutParams.width = displayMetrics.widthPixels;
            layoutParams.height = adjustedHeight2;
            this.mVideoView.getLayoutParams().width = displayMetrics.widthPixels;
            this.mVideoView.getLayoutParams().height = adjustedHeight2;
        }
        this.mFullScreenButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, getResources().getDrawable(R.drawable.ic_fullscreen_exit_white_24dp), (Drawable) null);
        this.mVideoView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int width = FullScreenVideoPlayer.this.mVideoView.getWidth();
                if (FullScreenVideoPlayer.this.mVideoView.canPause() && width > 0) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, -2);
                    layoutParams.addRule(12);
                    layoutParams.addRule(14);
                    FullScreenVideoPlayer.this.mControlsLayout.setLayoutParams(layoutParams);
                    try {
                        if (Build.VERSION.SDK_INT < 16) {
                            FullScreenVideoPlayer.this.mVideoView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            FullScreenVideoPlayer.this.mVideoView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    } catch (Exception unused) {
                        Trace.w("FullScreenVideo", "Failed to remove global layout listener");
                    }
                }
            }
        });
        resizeControlsToMatchVideoWidth(displayMetrics.widthPixels);
    }

    /* access modifiers changed from: private */
    public void setDefaultVideoParams(boolean z) {
        this.mIsFullScreenActive = false;
        if (getSupportActionBar() != null) {
            getSupportActionBar().show();
        }
        setRequestedOrientation(1);
        getWindow().getDecorView().setSystemUiVisibility(256);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mVideoContainerRelativeLatout.getLayoutParams();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int adjustedHeight = ExtensionsAspectRatio.getAdjustedHeight(4, 3, displayMetrics.widthPixels);
        layoutParams.width = displayMetrics.widthPixels;
        layoutParams.height = adjustedHeight;
        if (z && this.mHasVideo) {
            this.mVideoContainerRelativeLatout.setVisibility(0);
        }
        resizeControlsToMatchVideoWidth(displayMetrics.widthPixels);
    }

    /* access modifiers changed from: private */
    public void fadeInVideoControlsLayout() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(250);
        alphaAnimation.setStartOffset(0);
        alphaAnimation.setRepeatMode(2);
        this.mControlsLayout.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                boolean unused = FullScreenVideoPlayer.this.mAreVideoControlsVisible = true;
            }

            public void onAnimationEnd(Animation animation) {
                FullScreenVideoPlayer.this.mControlsLayout.setVisibility(0);
            }
        });
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mIsFullScreenActive) {
            setFullScreenModeParams();
        } else {
            setDefaultVideoParams(true);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
