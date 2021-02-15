package com.appboy.ui.inappmessage.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.appboy.enums.inappmessage.TextAlign;
import com.appboy.models.MessageButton;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.R;
import com.appboy.ui.inappmessage.AppboyInAppMessageManager;
import com.appboy.ui.support.ViewUtils;
import java.util.List;

public class InAppMessageViewUtils {
    private static final String TAG = AppboyLogger.getAppboyLogTag(InAppMessageViewUtils.class);

    public static boolean isValidIcon(String str) {
        return str != null;
    }

    public static boolean isValidInAppMessageColor(int i) {
        return i != 0;
    }

    public static void setImage(Bitmap bitmap, ImageView imageView) {
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

    public static void setIcon(Context context, String str, int i, int i2, TextView textView) {
        if (isValidIcon(str)) {
            try {
                textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf"));
                textView.setText(str);
                setTextViewColor(textView, i);
                if (textView.getBackground() != null) {
                    setDrawableColor(textView.getBackground(), i2, context.getResources().getColor(R.color.com_appboy_inappmessage_icon_background));
                } else {
                    setViewBackgroundColor(textView, i2);
                }
            } catch (Exception e) {
                AppboyLogger.e(TAG, "Caught exception setting icon typeface. Not rendering icon.", e);
            }
        }
    }

    public static void setButtons(List<View> list, View view, int i, List<MessageButton> list2) {
        if (list2 == null || list2.size() == 0) {
            ViewUtils.removeViewFromParent(view);
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list2.size() <= i2) {
                list.get(i2).setVisibility(8);
            } else if (list.get(i2) instanceof Button) {
                Button button = (Button) list.get(i2);
                MessageButton messageButton = list2.get(i2);
                button.setText(messageButton.getText());
                setTextViewColor(button, messageButton.getTextColor());
                setDrawableColor(button.getBackground(), messageButton.getBackgroundColor(), i);
            }
        }
    }

    public static void setFrameColor(View view, Integer num) {
        if (num != null) {
            view.setBackgroundColor(num.intValue());
        }
    }

    public static void setTextViewColor(TextView textView, int i) {
        if (isValidInAppMessageColor(i)) {
            textView.setTextColor(i);
        }
    }

    public static void setViewBackgroundColor(View view, int i) {
        if (isValidInAppMessageColor(i)) {
            view.setBackgroundColor(i);
        }
    }

    public static void setViewBackgroundColorFilter(View view, int i, int i2) {
        if (isValidInAppMessageColor(i)) {
            view.getBackground().setColorFilter(i, PorterDuff.Mode.MULTIPLY);
        } else {
            view.getBackground().setColorFilter(i2, PorterDuff.Mode.MULTIPLY);
        }
    }

    public static void setDrawableColor(Drawable drawable, int i, int i2) {
        if (drawable instanceof GradientDrawable) {
            setDrawableColor((GradientDrawable) drawable, i, i2);
        } else if (isValidInAppMessageColor(i)) {
            drawable.setColorFilter(i, PorterDuff.Mode.MULTIPLY);
        } else {
            drawable.setColorFilter(i2, PorterDuff.Mode.MULTIPLY);
        }
    }

    public static void setDrawableColor(GradientDrawable gradientDrawable, int i, int i2) {
        if (isValidInAppMessageColor(i)) {
            gradientDrawable.setColor(i);
        } else {
            gradientDrawable.setColor(i2);
        }
    }

    protected static void resetMessageMarginsIfNecessary(TextView textView, TextView textView2) {
        if (textView2 == null && textView != null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(textView.getLayoutParams().width, textView.getLayoutParams().height);
            layoutParams.setMargins(0, 0, 0, 0);
            textView.setLayoutParams(layoutParams);
        }
    }

    protected static void resetButtonSizesIfNecessary(List<View> list, List<MessageButton> list2) {
        if (list2 != null && list2.size() == 1) {
            list.get(0).setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        }
    }

    public static void closeInAppMessageOnKeycodeBack() {
        AppboyLogger.d(TAG, "Back button intercepted by in-app message view, closing in-app message.");
        AppboyInAppMessageManager.getInstance().hideCurrentlyDisplayingInAppMessage(true);
    }

    public static void setTextAlignment(TextView textView, TextAlign textAlign) {
        if (textAlign.equals(TextAlign.START)) {
            textView.setGravity(GravityCompat.START);
        } else if (textAlign.equals(TextAlign.END)) {
            textView.setGravity(GravityCompat.END);
        } else if (textAlign.equals(TextAlign.CENTER)) {
            textView.setGravity(17);
        }
    }
}
