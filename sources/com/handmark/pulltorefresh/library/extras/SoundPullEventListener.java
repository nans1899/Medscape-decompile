package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import java.util.HashMap;

public class SoundPullEventListener<V extends View> implements PullToRefreshBase.OnPullEventListener<V> {
    private final Context mContext;
    private MediaPlayer mCurrentMediaPlayer;
    private final HashMap<PullToRefreshBase.State, Integer> mSoundMap = new HashMap<>();

    public SoundPullEventListener(Context context) {
        this.mContext = context;
    }

    public final void onPullEvent(PullToRefreshBase<V> pullToRefreshBase, PullToRefreshBase.State state, PullToRefreshBase.Mode mode) {
        Integer num = this.mSoundMap.get(state);
        if (num != null) {
            playSound(num.intValue());
        }
    }

    public void addSoundEvent(PullToRefreshBase.State state, int i) {
        this.mSoundMap.put(state, Integer.valueOf(i));
    }

    public void clearSounds() {
        this.mSoundMap.clear();
    }

    public MediaPlayer getCurrentMediaPlayer() {
        return this.mCurrentMediaPlayer;
    }

    private void playSound(int i) {
        MediaPlayer mediaPlayer = this.mCurrentMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mCurrentMediaPlayer.release();
        }
        MediaPlayer create = MediaPlayer.create(this.mContext, i);
        this.mCurrentMediaPlayer = create;
        if (create != null) {
            create.start();
        }
    }
}
