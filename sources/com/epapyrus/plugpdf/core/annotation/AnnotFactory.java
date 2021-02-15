package com.epapyrus.plugpdf.core.annotation;

import android.content.Context;
import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import java.util.HashMap;

public class AnnotFactory {
    private static final String CLASS_PATH = "com.epapyrus.plugpdf.core.annotation";
    private static AnnotFactory mAnnotFactory;
    private HashMap<String, String> mClsNameMap;

    public static AnnotFactory instance() {
        if (mAnnotFactory == null) {
            mAnnotFactory = new AnnotFactory();
        }
        return mAnnotFactory;
    }

    private AnnotFactory() {
        HashMap<String, String> hashMap = new HashMap<>();
        this.mClsNameMap = hashMap;
        hashMap.put("INK", "com.epapyrus.plugpdf.core.annotation.AnnotInk");
        this.mClsNameMap.put("NOTE", "com.epapyrus.plugpdf.core.annotation.AnnotNote");
        this.mClsNameMap.put(ShareConstants.CONTENT_URL, "com.epapyrus.plugpdf.core.annotation.AnnotLink");
        this.mClsNameMap.put("HIGHLIGHT", "com.epapyrus.plugpdf.core.annotation.AnnotHighlight");
        this.mClsNameMap.put("UNDERLINE", "com.epapyrus.plugpdf.core.annotation.AnnotUnderline");
        this.mClsNameMap.put("STRIKEOUT", "com.epapyrus.plugpdf.core.annotation.AnnotStrikeout");
        this.mClsNameMap.put("SQUIGGLY", "com.epapyrus.plugpdf.core.annotation.AnnotSquiggly");
        this.mClsNameMap.put("LINE", "com.epapyrus.plugpdf.core.annotation.AnnotLine");
        this.mClsNameMap.put("SQUARE", "com.epapyrus.plugpdf.core.annotation.AnnotSquare");
        this.mClsNameMap.put("CIRCLE", "com.epapyrus.plugpdf.core.annotation.AnnotCircle");
        this.mClsNameMap.put("POLYGON", "com.epapyrus.plugpdf.core.annotation.AnnotPolygon");
        this.mClsNameMap.put("POLYLINE", "com.epapyrus.plugpdf.core.annotation.AnnotPolyline");
        this.mClsNameMap.put("FILE_ATTACHMENT", "com.epapyrus.plugpdf.core.annotation.AnnotFile");
        this.mClsNameMap.put("FREE_TEXT", "com.epapyrus.plugpdf.core.annotation.AnnotFreeText");
        this.mClsNameMap.put("STAMP", "com.epapyrus.plugpdf.core.annotation.AnnotStamp");
        this.mClsNameMap.put("SOUND", "com.epapyrus.plugpdf.core.annotation.AnnotSound");
        this.mClsNameMap.put("RICHMEDIA", "com.epapyrus.plugpdf.core.annotation.AnnotRichMedia");
    }

    public void registerAnnotClassName(String str, String str2) {
        this.mClsNameMap.put(str, str2);
    }

    public BaseAnnot createAnnot(Context context, String str) {
        return (BaseAnnot) newInstance(context, this.mClsNameMap.get(str));
    }

    private Object newInstance(Context context, String str) {
        try {
            return Class.forName(str).getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
        } catch (Exception e) {
            Log.e("PlugPDF", "[ERROR] AnnotFactory.newInstance ", e);
            return null;
        }
    }
}
