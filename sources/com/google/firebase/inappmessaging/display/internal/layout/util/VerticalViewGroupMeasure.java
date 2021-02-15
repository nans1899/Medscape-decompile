package com.google.firebase.inappmessaging.display.internal.layout.util;

import android.view.View;
import com.google.firebase.inappmessaging.display.internal.Logging;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class VerticalViewGroupMeasure {
    private int h;
    private List<ViewMeasure> vms;
    private int w;

    public VerticalViewGroupMeasure(int i, int i2) {
        this.vms = new ArrayList();
        this.w = i;
        this.h = i2;
    }

    public VerticalViewGroupMeasure() {
        this.vms = new ArrayList();
        this.w = 0;
        this.h = 0;
    }

    public void reset(int i, int i2) {
        this.w = i;
        this.h = i2;
        this.vms = new ArrayList();
    }

    public void add(View view, boolean z) {
        ViewMeasure viewMeasure = new ViewMeasure(view, z);
        viewMeasure.setMaxDimens(this.w, this.h);
        this.vms.add(viewMeasure);
    }

    public List<ViewMeasure> getViews() {
        return this.vms;
    }

    public int getTotalHeight() {
        int i = 0;
        for (ViewMeasure desiredHeight : this.vms) {
            i += desiredHeight.getDesiredHeight();
        }
        return i;
    }

    public int getTotalFixedHeight() {
        int i = 0;
        for (ViewMeasure next : this.vms) {
            if (!next.isFlex()) {
                i += next.getDesiredHeight();
            }
        }
        return i;
    }

    public void allocateSpace(int i) {
        float f;
        ArrayList<ViewMeasure> arrayList = new ArrayList<>();
        for (ViewMeasure next : this.vms) {
            if (next.isFlex()) {
                arrayList.add(next);
            }
        }
        Collections.sort(arrayList, new Comparator<ViewMeasure>() {
            public int compare(ViewMeasure viewMeasure, ViewMeasure viewMeasure2) {
                if (viewMeasure.getDesiredHeight() > viewMeasure2.getDesiredHeight()) {
                    return -1;
                }
                return viewMeasure.getDesiredHeight() < viewMeasure2.getDesiredHeight() ? 1 : 0;
            }
        });
        int i2 = 0;
        for (ViewMeasure desiredHeight : arrayList) {
            i2 += desiredHeight.getDesiredHeight();
        }
        int size = arrayList.size();
        if (size < 6) {
            float f2 = 1.0f - (((float) (size - 1)) * 0.2f);
            Logging.logdPair("VVGM (minFrac, maxFrac)", 0.2f, f2);
            float f3 = 0.0f;
            for (ViewMeasure viewMeasure : arrayList) {
                float desiredHeight2 = ((float) viewMeasure.getDesiredHeight()) / ((float) i2);
                if (desiredHeight2 > f2) {
                    f3 += desiredHeight2 - f2;
                    f = f2;
                } else {
                    f = desiredHeight2;
                }
                if (desiredHeight2 < 0.2f) {
                    float min = Math.min(0.2f - desiredHeight2, f3);
                    f3 -= min;
                    f = desiredHeight2 + min;
                }
                Logging.logdPair("\t(desired, granted)", desiredHeight2, f);
                viewMeasure.setMaxDimens(this.w, (int) (f * ((float) i)));
            }
            return;
        }
        throw new IllegalStateException("VerticalViewGroupMeasure only supports up to 5 children");
    }
}
