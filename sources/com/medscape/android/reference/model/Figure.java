package com.medscape.android.reference.model;

import com.facebook.share.internal.ShareConstants;
import java.io.Serializable;

public class Figure implements Serializable {
    public Para caption;
    public Graphic graphic;
    public String id;
    public Graphic thumbnail;
    public Para title;

    public class Graphic implements Serializable {
        public String extension;
        public String format;
        public String href;
        public String id;

        public Graphic() {
        }
    }

    public boolean isVideo() {
        Graphic graphic2 = this.graphic;
        if (graphic2 != null) {
            return ShareConstants.VIDEO_URL.equalsIgnoreCase(graphic2.format);
        }
        return false;
    }

    public boolean isImage() {
        Graphic graphic2 = this.graphic;
        if (graphic2 != null) {
            return ShareConstants.IMAGE_URL.equalsIgnoreCase(graphic2.format);
        }
        return false;
    }
}
