package com.comscore.streaming;

import com.comscore.util.cpp.CppJavaBinder;
import java.lang.ref.WeakReference;
import java.util.Map;

public class StreamingPublisherConfiguration extends CppJavaBinder {
    private WeakReference<StreamingConfiguration> b;
    private String c;

    StreamingPublisherConfiguration(StreamingConfiguration streamingConfiguration, String str) {
        this.b = new WeakReference<>(streamingConfiguration);
        this.c = str;
    }

    private native void addLabelsNative(long j, String str, Map<String, String> map);

    private native void removeAllLabelsNative(long j, String str);

    private native void removeLabelNative(long j, String str, String str2);

    private native void setLabelNative(long j, String str, String str2, String str3);

    public void addLabels(Map<String, String> map) {
        try {
            StreamingConfiguration streamingConfiguration = (StreamingConfiguration) this.b.get();
            if (streamingConfiguration != null) {
                addLabelsNative(streamingConfiguration.b(), this.c, map);
            }
        } catch (UnsatisfiedLinkError e) {
            printException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void destroyCppObject() {
    }

    public void removeAllLabels() {
        try {
            StreamingConfiguration streamingConfiguration = (StreamingConfiguration) this.b.get();
            if (streamingConfiguration != null) {
                removeAllLabelsNative(streamingConfiguration.b(), this.c);
            }
        } catch (UnsatisfiedLinkError e) {
            printException(e);
        }
    }

    public void removeLabel(String str) {
        try {
            StreamingConfiguration streamingConfiguration = (StreamingConfiguration) this.b.get();
            if (streamingConfiguration != null) {
                removeLabelNative(streamingConfiguration.b(), this.c, str);
            }
        } catch (UnsatisfiedLinkError e) {
            printException(e);
        }
    }

    public void setLabel(String str, String str2) {
        if (str != null && str2 != null) {
            try {
                StreamingConfiguration streamingConfiguration = (StreamingConfiguration) this.b.get();
                if (streamingConfiguration != null) {
                    setLabelNative(streamingConfiguration.b(), this.c, str, str2);
                }
            } catch (UnsatisfiedLinkError e) {
                printException(e);
            }
        }
    }
}
