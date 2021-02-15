package com.comscore.streaming;

import com.comscore.streaming.StreamingConfiguration;
import com.comscore.util.cpp.CppJavaBinder;

public class StreamingAnalytics extends CppJavaBinder {
    private final Object b;
    private long c;
    private StreamingConfiguration d;
    private StreamingExtendedAnalytics e;

    public StreamingAnalytics() {
        this((StreamingConfiguration) null);
    }

    public StreamingAnalytics(StreamingConfiguration streamingConfiguration) {
        this.b = new Object();
        this.c = 0;
        streamingConfiguration = streamingConfiguration == null ? new StreamingConfiguration.Builder().build() : streamingConfiguration;
        this.d = streamingConfiguration;
        try {
            this.c = newCppInstanceNative(streamingConfiguration.b());
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    private native void addListenerNative(long j, StreamingListener streamingListener);

    private native void createPlaybackSessionNative(long j);

    private native void destroyCppInstanceNative(long j);

    private native String getPlaybackSessionIdNative(long j);

    private native void loopPlaybackSessionNative(long j);

    private native long newCppInstanceNative(long j);

    private native void notifyBufferStartNative(long j);

    private native void notifyBufferStopNative(long j);

    private native void notifyChangePlaybackRateNative(long j, float f);

    private native void notifyEndNative(long j);

    private native void notifyPauseNative(long j);

    private native void notifyPlayNative(long j);

    private native void notifySeekStartNative(long j);

    private native void removeListenerNative(long j, StreamingListener streamingListener);

    private native void setDvrWindowLengthNative(long j, long j2);

    private native void setImplementationIdNative(long j, String str);

    private native void setMediaPlayerNameNative(long j, String str);

    private native void setMediaPlayerVersionNative(long j, String str);

    private native void setMetadataNative(long j, long j2);

    private native void setProjectIdNative(long j, String str);

    private native void startFromDvrWindowOffsetNative(long j, long j2);

    private native void startFromPositionNative(long j, long j2);

    private native void startFromSegmentNative(long j, int i);

    /* access modifiers changed from: package-private */
    public long a() {
        return this.c;
    }

    public void addListener(StreamingListener streamingListener) {
        try {
            addListenerNative(this.c, streamingListener);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void createPlaybackSession() {
        try {
            createPlaybackSessionNative(this.c);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    /* access modifiers changed from: protected */
    public void destroyCppObject() {
        try {
            destroyCppInstanceNative(this.c);
            this.c = 0;
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof StreamingAnalytics) && ((StreamingAnalytics) obj).c == this.c;
    }

    public StreamingConfiguration getConfiguration() {
        return this.d;
    }

    public StreamingExtendedAnalytics getExtendedAnalytics() {
        if (this.e == null) {
            synchronized (this.b) {
                if (this.e == null) {
                    this.e = new StreamingExtendedAnalytics(this);
                }
            }
        }
        return this.e;
    }

    public String getPlaybackSessionId() {
        try {
            return getPlaybackSessionIdNative(this.c);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
            return null;
        }
    }

    public void loopPlaybackSession() {
        try {
            loopPlaybackSessionNative(this.c);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void notifyBufferStart() {
        try {
            notifyBufferStartNative(this.c);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void notifyBufferStop() {
        try {
            notifyBufferStopNative(this.c);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void notifyChangePlaybackRate(float f) {
        try {
            notifyChangePlaybackRateNative(this.c, f);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void notifyEnd() {
        try {
            notifyEndNative(this.c);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void notifyPause() {
        try {
            notifyPauseNative(this.c);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void notifyPlay() {
        try {
            notifyPlayNative(this.c);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void notifySeekStart() {
        try {
            notifySeekStartNative(this.c);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void removeListener(StreamingListener streamingListener) {
        try {
            removeListenerNative(this.c, streamingListener);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void setDvrWindowLength(long j) {
        try {
            setDvrWindowLengthNative(this.c, j);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void setImplementationId(String str) {
        if (str != null) {
            try {
                setImplementationIdNative(this.c, str);
            } catch (UnsatisfiedLinkError e2) {
                printException(e2);
            }
        }
    }

    public void setMediaPlayerName(String str) {
        if (str != null) {
            try {
                setMediaPlayerNameNative(this.c, str);
            } catch (UnsatisfiedLinkError e2) {
                printException(e2);
            }
        }
    }

    public void setMediaPlayerVersion(String str) {
        if (str != null) {
            try {
                setMediaPlayerVersionNative(this.c, str);
            } catch (UnsatisfiedLinkError e2) {
                printException(e2);
            }
        }
    }

    public void setMetadata(AssetMetadata assetMetadata) {
        if (assetMetadata != null) {
            try {
                setMetadataNative(this.c, assetMetadata.a());
            } catch (UnsatisfiedLinkError e2) {
                printException(e2);
            }
        }
    }

    public void setProjectId(String str) {
        if (str != null) {
            try {
                setProjectIdNative(this.c, str);
            } catch (UnsatisfiedLinkError e2) {
                printException(e2);
            }
        }
    }

    public void startFromDvrWindowOffset(long j) {
        try {
            startFromDvrWindowOffsetNative(this.c, j);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void startFromPosition(long j) {
        try {
            startFromPositionNative(this.c, j);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }

    public void startFromSegment(int i) {
        try {
            startFromSegmentNative(this.c, i);
        } catch (UnsatisfiedLinkError e2) {
            printException(e2);
        }
    }
}
