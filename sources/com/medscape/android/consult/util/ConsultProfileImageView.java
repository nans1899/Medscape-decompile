package com.medscape.android.consult.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.res.ResourcesCompat;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.medscape.android.R;

public class ConsultProfileImageView extends AppCompatImageView {
    boolean isMedStudent = false;

    public ConsultProfileImageView(Context context) {
        super(context);
    }

    public ConsultProfileImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ConsultProfileImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Bitmap bitmap;
        Drawable drawable = getDrawable();
        if (drawable != null && getWidth() != 0 && getHeight() != 0) {
            if (drawable instanceof BitmapDrawable) {
                bitmap = ((BitmapDrawable) drawable).getBitmap();
            } else {
                bitmap = drawable instanceof GifDrawable ? ((GifDrawable) drawable).getFirstFrame() : null;
            }
            if (!this.isMedStudent || bitmap == null) {
                super.onDraw(canvas);
                return;
            }
            Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            int width = getWidth();
            canvas.drawBitmap(getCroppedBitmap(copy, width - 4), 0.0f, 0.0f, (Paint) null);
            Bitmap bitmap2 = ((BitmapDrawable) ResourcesCompat.getDrawable(getResources(), R.drawable.ic_med_student, (Resources.Theme) null)).getBitmap();
            canvas.drawBitmap(bitmap2, 0.0f, (float) (width - bitmap2.getHeight()), (Paint) null);
        }
    }

    private Bitmap getCroppedBitmap(Bitmap bitmap, int i) {
        if (!(bitmap.getWidth() == i && bitmap.getHeight() == i)) {
            float min = ((float) Math.min(bitmap.getWidth(), bitmap.getHeight())) / ((float) i);
            bitmap = Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) / min), (int) (((float) bitmap.getHeight()) / min), false);
        }
        int color = ResourcesCompat.getColor(getResources(), R.color.consult_med_student, (Resources.Theme) null);
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(2, 0, i, i);
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        float f = (float) (i / 2);
        canvas.drawCircle(2.7f + f, 0.7f + f, f - 2.9f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        Paint paint2 = new Paint(1);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(color);
        canvas.translate(0.0f, 0.0f);
        paint2.setStrokeWidth(4.0f);
        canvas.drawCircle(3.7f + f, 0.6f + f, f - 4.5f, paint2);
        return createBitmap;
    }

    public void setMedStudent(boolean z) {
        this.isMedStudent = z;
    }
}
