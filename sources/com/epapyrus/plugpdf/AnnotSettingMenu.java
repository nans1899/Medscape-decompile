package com.epapyrus.plugpdf;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.epapyrus.plugpdf.core.annotation.tool.AnnotSetting;
import com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool;

public class AnnotSettingMenu {
    private static final String PERCENT = "%";
    private static final String PX = "px";
    /* access modifiers changed from: private */
    public BaseAnnotTool.AnnotToolType mAnnotType;
    private Context mContext;
    private LayoutInflater mInflater;
    private PopupWindow mPopupWindow;

    /* access modifiers changed from: private */
    public int getColorFromSpinner(int i) {
        if (i == 1) {
            return 16711680;
        }
        if (i == 2) {
            return 16776960;
        }
        if (i == 3) {
            return MotionEventCompat.ACTION_POINTER_INDEX_MASK;
        }
        if (i == 4) {
            return 255;
        }
        if (i != 5) {
            return 0;
        }
        return ViewCompat.MEASURED_SIZE_MASK;
    }

    private int getSpinnerPos(int i) {
        if (i == 255) {
            return 4;
        }
        if (i == 65280) {
            return 3;
        }
        if (i == 16711680) {
            return 1;
        }
        if (i != 16776960) {
            return i != 16777215 ? 0 : 5;
        }
        return 2;
    }

    public enum ColorType {
        BLACK(0),
        RED(1),
        YELLOW(2),
        GREEN(3),
        BLUE(4),
        WHITE(5);
        
        private int mValue;

        private ColorType(int i) {
            this.mValue = i;
        }

        public int value() {
            return this.mValue;
        }
    }

    public AnnotSettingMenu(Context context) {
        this.mContext = context;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    private void createPopupWindow(BaseAnnotTool.AnnotToolType annotToolType) {
        View inflate = this.mInflater.inflate(R.layout.annot_setting, (ViewGroup) null);
        PopupWindow popupWindow = new PopupWindow(inflate, -2, -2);
        this.mPopupWindow = popupWindow;
        popupWindow.setOutsideTouchable(true);
        this.mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        final AnnotSetting instance = AnnotSetting.instance();
        Spinner spinner = (Spinner) inflate.findViewById(R.id.annot_line_color_value);
        spinner.setSelection(getSpinnerPos(instance.getLineColor(this.mAnnotType)));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                instance.setLineColor(AnnotSettingMenu.this.getColorFromSpinner(i), AnnotSettingMenu.this.mAnnotType);
            }
        });
        Spinner spinner2 = (Spinner) inflate.findViewById(R.id.annot_fill_color_value);
        spinner2.setSelection(getSpinnerPos(instance.getFillColor(this.mAnnotType)));
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                instance.setFillColor(AnnotSettingMenu.this.getColorFromSpinner(i), AnnotSettingMenu.this.mAnnotType);
            }
        });
        final TextView textView = (TextView) inflate.findViewById(R.id.annot_opacity_value_display);
        textView.setText(Integer.toString((int) ((((float) AnnotSetting.instance().getOpacity(this.mAnnotType)) / 255.0f) * 100.0f)) + PERCENT);
        SeekBar seekBar = (SeekBar) inflate.findViewById(R.id.annot_opacity_value);
        seekBar.setProgress(instance.getOpacity(this.mAnnotType));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                TextView textView = textView;
                textView.setText(Integer.toString((int) ((((float) i) / 255.0f) * 100.0f)) + AnnotSettingMenu.PERCENT);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                instance.setOpacity(seekBar.getProgress(), AnnotSettingMenu.this.mAnnotType);
                TextView textView = textView;
                textView.setText(Integer.toString((int) ((((float) instance.getOpacity(AnnotSettingMenu.this.mAnnotType)) / 255.0f) * 100.0f)) + AnnotSettingMenu.PERCENT);
            }
        });
        final TextView textView2 = (TextView) inflate.findViewById(R.id.annot_line_width_value_display);
        textView2.setText(Integer.toString(instance.getLineWidth(this.mAnnotType)) + PX);
        SeekBar seekBar2 = (SeekBar) inflate.findViewById(R.id.annot_line_width_value);
        seekBar2.setProgress(instance.getLineWidth(this.mAnnotType));
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                TextView textView = textView2;
                textView.setText(Integer.toString(i) + AnnotSettingMenu.PX);
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                instance.setLineWidth(seekBar.getProgress(), AnnotSettingMenu.this.mAnnotType);
                TextView textView = textView2;
                textView.setText(Integer.toString(instance.getLineWidth(AnnotSettingMenu.this.mAnnotType)) + AnnotSettingMenu.PX);
            }
        });
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.annot_line_straight);
        checkBox.setChecked(instance.getInkLineStraight());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                checkBox.setChecked(z);
                instance.setInkLineStraight(z);
            }
        });
        final CheckBox checkBox2 = (CheckBox) inflate.findViewById(R.id.annot_line_squiggly);
        checkBox2.setChecked(instance.isSquiggly());
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                checkBox2.setChecked(z);
                instance.setSquiggly(z);
            }
        });
        switch (AnonymousClass7.$SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType[this.mAnnotType.ordinal()]) {
            case 1:
                inflate.findViewById(R.id.annot_fill_color_title).setVisibility(8);
                inflate.findViewById(R.id.annot_fill_color_value).setVisibility(8);
                inflate.findViewById(R.id.annot_line_squiggly).setVisibility(8);
                return;
            case 2:
            case 3:
                inflate.findViewById(R.id.annot_line_squiggly).setVisibility(8);
                break;
            case 4:
                break;
            case 5:
            case 6:
                inflate.findViewById(R.id.annot_line_straight).setVisibility(8);
                inflate.findViewById(R.id.annot_line_squiggly).setVisibility(8);
                ((TextView) inflate.findViewById(R.id.annot_line_color_title)).setText("line color");
                return;
            default:
                return;
        }
        inflate.findViewById(R.id.annot_line_straight).setVisibility(8);
        inflate.findViewById(R.id.annot_fill_color_title).setVisibility(8);
        inflate.findViewById(R.id.annot_fill_color_value).setVisibility(8);
        inflate.findViewById(R.id.annot_opacity_title).setVisibility(8);
        inflate.findViewById(R.id.annot_opacity_value).setVisibility(8);
        inflate.findViewById(R.id.annot_opacity_value_display).setVisibility(8);
        inflate.findViewById(R.id.annot_line_width_title).setVisibility(8);
        inflate.findViewById(R.id.annot_line_width_value).setVisibility(8);
        inflate.findViewById(R.id.annot_line_width_value_display).setVisibility(8);
    }

    /* renamed from: com.epapyrus.plugpdf.AnnotSettingMenu$7  reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
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
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.STRIKEOUT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.HIGHLIGHT     // Catch:{ NoSuchFieldError -> 0x0028 }
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
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.SQUARE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$epapyrus$plugpdf$core$annotation$tool$BaseAnnotTool$AnnotToolType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool$AnnotToolType r1 = com.epapyrus.plugpdf.core.annotation.tool.BaseAnnotTool.AnnotToolType.CIRCLE     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.epapyrus.plugpdf.AnnotSettingMenu.AnonymousClass7.<clinit>():void");
        }
    }

    public synchronized void show(View view, int i, int i2, BaseAnnotTool.AnnotToolType annotToolType) {
        this.mAnnotType = annotToolType;
        createPopupWindow(annotToolType);
        this.mPopupWindow.showAtLocation(view, 17, i, i2);
    }

    public synchronized void close() {
        if (this.mPopupWindow != null) {
            this.mPopupWindow.dismiss();
        }
    }
}
