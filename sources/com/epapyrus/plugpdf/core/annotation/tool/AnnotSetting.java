package com.epapyrus.plugpdf.core.annotation.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.util.Base64;
import androidx.core.view.ViewCompat;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;
import java.io.ByteArrayOutputStream;

public class AnnotSetting {
    private static final String CIRCLE_FILL_COLOR_KEY = "CircleFillColor";
    private static final String CIRCLE_INNER_TRANSPARENT_KEY = "CircleInnerTransparent";
    private static final String CIRCLE_LINE_COLOR_KEY = "CircleLineColor";
    private static final String CIRCLE_LINE_WIDTH_KEY = "CircleLineWidth";
    private static final String CIRCLE_OPACITY_KEY = "CircleOpacity";
    private static final String FILE_NAME = "plugpdf_annot_setting";
    private static final String FREE_TEXT_COLOR_KEY = "FreeTextColor";
    private static final String FREE_TEXT_FONT_KEY = "FreeTextFontName";
    private static final String FREE_TEXT_OPACITY_KEY = "FreeTextOpacity";
    private static final String FREE_TEXT_SIZE_KEY = "TextSize";
    private static final String HIGHLIGHT_LINE_COLOR_KEY = "HighlightLineColor";
    private static final String INK_LINE_COLOR_KEY = "InkLineColor";
    private static final String INK_LINE_OPACITY_KEY = "InkLineOpacity";
    private static final String INK_LINE_STRAIGHT_KEY = "InkLineStraight";
    private static final String INK_LINE_WIDTH_KEY = "InkLineWidth";
    private static final String LINE_COLOR_KEY = "LineColor";
    private static final String LINE_OPACITY_KEY = "LineOpacity";
    private static final String LINE_WIDTH_KEY = "LineWidth";
    private static final String SQUARE_FILL_COLOR_KEY = "SquareFillColor";
    private static final String SQUARE_INNER_TRANSPARENT_KEY = "SquareInnerTransparent";
    private static final String SQUARE_LINE_COLOR_KEY = "SquareLineColor";
    private static final String SQUARE_LINE_WIDTH_KEY = "SquareLineWidth";
    private static final String SQUARE_OPACITY_KEY = "SquareOpacity";
    private static final String STAMP_IMAGE_KEY = "StampImage";
    private static final String STAMP_OPACITY_KEY = "StampOpacity";
    private static final String STRIKEOUT_LINE_COLOR_KEY = "StrikeoutLineColor";
    private static final String TEXT_SELECT_POPUP_POSITION_X_KEY = "TextSelectPopUpPositionX";
    private static final String TEXT_SELECT_POPUP_POSITION_Y_KEY = "TextSelectPopUpPositionY";
    private static final String UNDERLINE_LINE_COLOR_KEY = "UnderlineLineColor";
    private static final String UNDERLINE_SQUIGGLY_KEY = "Squiggly";
    private static AnnotSetting mAnnotSetting;
    private SharedPreferences mPreferences;

    public static AnnotSetting instance() {
        return mAnnotSetting;
    }

    public static void init(Context context) {
        mAnnotSetting = new AnnotSetting(context);
    }

    private AnnotSetting(Context context) {
        this.mPreferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    public void setLineColor(int i, BaseAnnotTool.AnnotToolType annotToolType) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        switch (AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()]) {
            case 1:
                edit.putInt(INK_LINE_COLOR_KEY, i);
                break;
            case 2:
                edit.putInt(HIGHLIGHT_LINE_COLOR_KEY, i);
                break;
            case 3:
                edit.putInt(STRIKEOUT_LINE_COLOR_KEY, i);
                break;
            case 4:
            case 5:
                edit.putInt(UNDERLINE_LINE_COLOR_KEY, i);
                break;
            case 6:
                edit.putInt(SQUARE_LINE_COLOR_KEY, i);
                break;
            case 7:
                edit.putInt(CIRCLE_LINE_COLOR_KEY, i);
                break;
            case 8:
                edit.putInt(LINE_COLOR_KEY, i);
                break;
            default:
                return;
        }
        edit.commit();
    }

    /* renamed from: com.epapyrus.plugpdf.core.annotation.tool.AnnotSetting$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|(3:17|18|20)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType[] r0 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType = r0
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.INK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.HIGHLIGHT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.STRIKEOUT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.UNDERLINE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.SQUIGGLY     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.SQUARE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.CIRCLE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.LINE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x006c }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.STAMP     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.core.annotation.tool.AnnotSetting.AnonymousClass1.<clinit>():void");
        }
    }

    public int getLineColor(BaseAnnotTool.AnnotToolType annotToolType) {
        switch (AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()]) {
            case 1:
                return this.mPreferences.getInt(INK_LINE_COLOR_KEY, ViewCompat.MEASURED_STATE_MASK);
            case 2:
                return this.mPreferences.getInt(HIGHLIGHT_LINE_COLOR_KEY, ViewCompat.MEASURED_STATE_MASK);
            case 3:
                return this.mPreferences.getInt(STRIKEOUT_LINE_COLOR_KEY, ViewCompat.MEASURED_STATE_MASK);
            case 4:
            case 5:
                return this.mPreferences.getInt(UNDERLINE_LINE_COLOR_KEY, ViewCompat.MEASURED_STATE_MASK);
            case 6:
                return this.mPreferences.getInt(SQUARE_LINE_COLOR_KEY, ViewCompat.MEASURED_STATE_MASK);
            case 7:
                return this.mPreferences.getInt(CIRCLE_LINE_COLOR_KEY, ViewCompat.MEASURED_STATE_MASK);
            case 8:
                return this.mPreferences.getInt(LINE_COLOR_KEY, ViewCompat.MEASURED_STATE_MASK);
            default:
                return ViewCompat.MEASURED_STATE_MASK;
        }
    }

    public void setLineWidth(int i, BaseAnnotTool.AnnotToolType annotToolType) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        if (i > 255) {
            i = 255;
        }
        int i2 = AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()];
        if (i2 == 1) {
            edit.putInt(INK_LINE_WIDTH_KEY, i);
        } else if (i2 == 6) {
            edit.putInt(SQUARE_LINE_WIDTH_KEY, i);
        } else if (i2 == 7) {
            edit.putInt(CIRCLE_LINE_WIDTH_KEY, i);
        } else if (i2 == 8) {
            edit.putInt(LINE_WIDTH_KEY, i);
        } else {
            return;
        }
        edit.commit();
    }

    public int getLineWidth(BaseAnnotTool.AnnotToolType annotToolType) {
        int i = AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()];
        if (i == 1) {
            return this.mPreferences.getInt(INK_LINE_WIDTH_KEY, 10);
        }
        if (i == 6) {
            return this.mPreferences.getInt(SQUARE_LINE_WIDTH_KEY, 10);
        }
        if (i == 7) {
            return this.mPreferences.getInt(CIRCLE_LINE_WIDTH_KEY, 10);
        }
        if (i != 8) {
            return ViewCompat.MEASURED_STATE_MASK;
        }
        return this.mPreferences.getInt(LINE_WIDTH_KEY, 10);
    }

    public void setOpacity(int i, BaseAnnotTool.AnnotToolType annotToolType) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        int i2 = AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()];
        if (i2 != 1) {
            switch (i2) {
                case 6:
                    edit.putInt(SQUARE_OPACITY_KEY, i);
                    break;
                case 7:
                    edit.putInt(CIRCLE_OPACITY_KEY, i);
                    break;
                case 8:
                    edit.putInt(LINE_OPACITY_KEY, i);
                    break;
                case 9:
                    edit.putInt(STAMP_OPACITY_KEY, i);
                    return;
                default:
                    return;
            }
        } else {
            edit.putInt(INK_LINE_OPACITY_KEY, i);
        }
        edit.commit();
    }

    public int getOpacity(BaseAnnotTool.AnnotToolType annotToolType) {
        int i = AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()];
        if (i == 1) {
            return this.mPreferences.getInt(INK_LINE_OPACITY_KEY, 255);
        }
        switch (i) {
            case 6:
                return this.mPreferences.getInt(SQUARE_OPACITY_KEY, 255);
            case 7:
                return this.mPreferences.getInt(CIRCLE_OPACITY_KEY, 255);
            case 8:
                return this.mPreferences.getInt(LINE_OPACITY_KEY, 255);
            case 9:
                return this.mPreferences.getInt(STAMP_OPACITY_KEY, 255);
            default:
                return 255;
        }
    }

    public void setInnerTransparent(boolean z, BaseAnnotTool.AnnotToolType annotToolType) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        int i = AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()];
        if (i == 6) {
            edit.putBoolean(SQUARE_INNER_TRANSPARENT_KEY, z);
        } else if (i == 7) {
            edit.putBoolean(CIRCLE_INNER_TRANSPARENT_KEY, z);
        } else {
            return;
        }
        edit.commit();
    }

    public boolean isInnerTransparent(BaseAnnotTool.AnnotToolType annotToolType) {
        int i = AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()];
        if (i == 6) {
            return this.mPreferences.getBoolean(SQUARE_INNER_TRANSPARENT_KEY, false);
        }
        if (i != 7) {
            return false;
        }
        return this.mPreferences.getBoolean(CIRCLE_INNER_TRANSPARENT_KEY, false);
    }

    public void setFillColor(int i, BaseAnnotTool.AnnotToolType annotToolType) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        int i2 = AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()];
        if (i2 == 6) {
            edit.putInt(SQUARE_FILL_COLOR_KEY, i);
        } else if (i2 == 7) {
            edit.putInt(CIRCLE_FILL_COLOR_KEY, i);
        } else {
            return;
        }
        edit.commit();
    }

    public int getFillColor(BaseAnnotTool.AnnotToolType annotToolType) {
        int i = AnonymousClass1.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[annotToolType.ordinal()];
        if (i == 6) {
            return this.mPreferences.getInt(SQUARE_FILL_COLOR_KEY, -1);
        }
        if (i != 7) {
            return -1;
        }
        return this.mPreferences.getInt(CIRCLE_FILL_COLOR_KEY, -1);
    }

    public void setFreeTextSize(int i) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putInt(FREE_TEXT_SIZE_KEY, i);
        edit.commit();
    }

    public int getFreeTextSize() {
        return this.mPreferences.getInt(FREE_TEXT_SIZE_KEY, 30);
    }

    public void setFreeTextFont(String str) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putString(FREE_TEXT_FONT_KEY, str);
        edit.commit();
    }

    public String getFreeTextFont() {
        return this.mPreferences.getString(FREE_TEXT_FONT_KEY, "Roboto");
    }

    public void setFreeTextColor(int i) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putInt(FREE_TEXT_COLOR_KEY, i);
        edit.commit();
    }

    public int getFreeTextColor() {
        return this.mPreferences.getInt(FREE_TEXT_COLOR_KEY, ViewCompat.MEASURED_STATE_MASK);
    }

    public void setFreeTextOpacity(int i) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putInt(FREE_TEXT_OPACITY_KEY, i);
        edit.commit();
    }

    public int getFreeTextOpacity() {
        return this.mPreferences.getInt(FREE_TEXT_OPACITY_KEY, 100);
    }

    public void setInkLineStraight(boolean z) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putBoolean(INK_LINE_STRAIGHT_KEY, z);
        edit.commit();
    }

    public boolean getInkLineStraight() {
        return this.mPreferences.getBoolean(INK_LINE_STRAIGHT_KEY, false);
    }

    public void setSquiggly(boolean z) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putBoolean(UNDERLINE_SQUIGGLY_KEY, z);
        edit.commit();
    }

    public void setStampImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putString(STAMP_IMAGE_KEY, encodeToString);
        edit.commit();
    }

    public Bitmap getStampImage() {
        byte[] decode = Base64.decode(this.mPreferences.getString(STAMP_IMAGE_KEY, ""), 2);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public boolean isSquiggly() {
        return this.mPreferences.getBoolean(UNDERLINE_SQUIGGLY_KEY, false);
    }

    public void setTextSelectPopupPosition(int i, int i2) {
        SharedPreferences.Editor edit = this.mPreferences.edit();
        edit.putInt(TEXT_SELECT_POPUP_POSITION_X_KEY, i);
        edit.putInt(TEXT_SELECT_POPUP_POSITION_Y_KEY, i2);
        edit.commit();
    }

    public PointF getTextSelectPopupPosition() {
        int i = this.mPreferences.getInt(TEXT_SELECT_POPUP_POSITION_X_KEY, -987654321);
        int i2 = this.mPreferences.getInt(TEXT_SELECT_POPUP_POSITION_Y_KEY, -987654321);
        if (i == -987654321 && i2 == -987654321) {
            return null;
        }
        return new PointF((float) i, (float) i2);
    }

    @Deprecated
    public void setLineColor(int i) {
        setLineColor(i, BaseAnnotTool.AnnotToolType.INK);
    }

    @Deprecated
    public int getLineColor() {
        return getLineColor(BaseAnnotTool.AnnotToolType.INK);
    }

    @Deprecated
    public void setLineOpacity(int i) {
        setOpacity(i, BaseAnnotTool.AnnotToolType.INK);
    }

    @Deprecated
    public int getLineOpacity() {
        return getOpacity(BaseAnnotTool.AnnotToolType.INK);
    }

    @Deprecated
    public void setLineWidth(int i) {
        setLineWidth(i, BaseAnnotTool.AnnotToolType.INK);
    }

    @Deprecated
    public int getLineWidth() {
        return getLineColor(BaseAnnotTool.AnnotToolType.INK);
    }
}
