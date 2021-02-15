package com.webmd.wbmdcmepulse.models.utils.closecaptioning;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;
import androidx.appcompat.widget.AppCompatTextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.joda.time.DateTimeConstants;

public class CloseCaptionTextView extends AppCompatTextView implements Runnable {
    private static final boolean DEBUG = true;
    private static final String TAG = "SubtitleView";
    private static final int UPDATE_INTERVAL = 300;
    private ArrayList<CloseCaption> mCaptions;
    private VideoView mVideoView;

    public CloseCaptionTextView(Context context) {
        super(context);
    }

    public CloseCaptionTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CloseCaptionTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void run() {
        ArrayList<CloseCaption> arrayList;
        if (!(this.mVideoView == null || (arrayList = this.mCaptions) == null || arrayList.size() <= 0)) {
            int currentPosition = this.mVideoView.getCurrentPosition() / 1000;
            setText(getTimedText((long) this.mVideoView.getCurrentPosition()));
        }
        postDelayed(this, 300);
    }

    private String getTimedText(long j) {
        ArrayList<CloseCaption> arrayList = this.mCaptions;
        if (arrayList == null || arrayList.size() <= 0) {
            return "";
        }
        Iterator<CloseCaption> it = this.mCaptions.iterator();
        while (it.hasNext()) {
            CloseCaption next = it.next();
            if (j > next.getStartTime() && j < next.getEndTime()) {
                return next.getCaptionString();
            }
        }
        return "";
    }

    public String secondsToDuration(int i) {
        return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i / DateTimeConstants.SECONDS_PER_HOUR), Integer.valueOf((i % DateTimeConstants.SECONDS_PER_HOUR) / 60), Integer.valueOf(i % 60), Locale.US});
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        postDelayed(this, 300);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this);
    }

    public void setVideoView(VideoView videoView) {
        this.mVideoView = videoView;
    }

    public void setCloseCaption(ArrayList<CloseCaption> arrayList) {
        this.mCaptions = arrayList;
    }
}
