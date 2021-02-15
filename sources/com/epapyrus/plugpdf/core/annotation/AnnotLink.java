package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import com.epapyrus.plugpdf.core.viewer.BasePlugPDFDisplay;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;

public class AnnotLink extends BaseAnnot {
    private String mDest;
    private ArrayList<ArrayList<PointF>> mPointContainer;

    public AnnotLink(Context context) {
        super(context, ShareConstants.CONTENT_URL);
        ArrayList<ArrayList<PointF>> arrayList = new ArrayList<>();
        this.mPointContainer = arrayList;
        arrayList.add(new ArrayList());
        setScale(1.0f);
    }

    public String getDestination() {
        return this.mDest;
    }

    public void setDestination(String str) {
        this.mDest = str;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layout((int) (this.mBbox.left * this.mScale), (int) (this.mBbox.top * this.mScale), (int) (this.mBbox.right * this.mScale), (int) (this.mBbox.bottom * this.mScale));
    }

    public void runLink(BasePlugPDFDisplay basePlugPDFDisplay) {
        String destination = getDestination();
        if (!destination.isEmpty()) {
            String substring = destination.substring(0, destination.indexOf(":"));
            String substring2 = destination.substring(destination.indexOf(":") + 1);
            if (substring2.contains("/")) {
                String substring3 = substring2.substring(0, substring2.indexOf("/"));
                if (substring3.contains(".")) {
                    String absolutePath = getContext().getFilesDir().getAbsolutePath();
                    if (substring3.equals(".")) {
                        substring2 = absolutePath + substring2.substring(substring2.indexOf("/"));
                    } else if (substring3.equals("..")) {
                        substring2 = absolutePath.substring(0, absolutePath.lastIndexOf("/")) + substring2.substring(substring2.indexOf("/"));
                    }
                }
                while (substring2.contains("/../")) {
                    String substring4 = substring2.substring(0, substring2.indexOf("/../"));
                    substring2 = substring4.substring(0, substring4.lastIndexOf("/")) + substring2.substring(substring2.indexOf("/../") + 3);
                }
            }
            if (substring.equals("GoTo")) {
                basePlugPDFDisplay.goToPage(Integer.parseInt(substring2));
            } else if (substring.equals("URI")) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(substring2));
                intent.addFlags(268435456);
                getContext().startActivity(intent);
            } else if (substring.equals("Launch")) {
                Intent launchIntentForPackage = getContext().getPackageManager().getLaunchIntentForPackage(substring2);
                if (launchIntentForPackage != null) {
                    launchIntentForPackage.addFlags(268435456);
                    getContext().startActivity(launchIntentForPackage);
                }
            } else {
                substring.equals("GoToR");
            }
        }
    }
}
