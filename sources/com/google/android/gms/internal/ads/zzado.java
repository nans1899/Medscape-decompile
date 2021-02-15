package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzado extends RelativeLayout {
    private static final float[] zzddi = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    private AnimationDrawable zzddj;

    public zzado(Context context, zzadl zzadl, RelativeLayout.LayoutParams layoutParams) {
        super(context);
        Preconditions.checkNotNull(zzadl);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(zzddi, (RectF) null, (float[]) null));
        shapeDrawable.getPaint().setColor(zzadl.getBackgroundColor());
        setLayoutParams(layoutParams);
        zzp.zzkt();
        setBackground(shapeDrawable);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(zzadl.getText())) {
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams3);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(zzadl.getText());
            textView.setTextColor(zzadl.getTextColor());
            textView.setTextSize((float) zzadl.getTextSize());
            zzwm.zzpt();
            int zzc = zzbbg.zzc(context, 4);
            zzwm.zzpt();
            textView.setPadding(zzc, 0, zzbbg.zzc(context, 4), 0);
            addView(textView);
            layoutParams2.addRule(1, textView.getId());
        }
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams2);
        imageView.setId(1195835394);
        List<zzadq> zzsc = zzadl.zzsc();
        if (zzsc != null && zzsc.size() > 1) {
            this.zzddj = new AnimationDrawable();
            for (zzadq zzsg : zzsc) {
                try {
                    this.zzddj.addFrame((Drawable) ObjectWrapper.unwrap(zzsg.zzsg()), zzadl.zzsd());
                } catch (Exception e) {
                    zzayp.zzc("Error while getting drawable.", e);
                }
            }
            zzp.zzkt();
            imageView.setBackground(this.zzddj);
        } else if (zzsc.size() == 1) {
            try {
                imageView.setImageDrawable((Drawable) ObjectWrapper.unwrap(zzsc.get(0).zzsg()));
            } catch (Exception e2) {
                zzayp.zzc("Error while getting drawable.", e2);
            }
        }
        addView(imageView);
    }

    public final void onAttachedToWindow() {
        AnimationDrawable animationDrawable = this.zzddj;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        super.onAttachedToWindow();
    }
}
