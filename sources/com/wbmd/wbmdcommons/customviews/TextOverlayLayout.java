package com.wbmd.wbmdcommons.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.wbmd.wbmdcommons.R;
import com.wbmd.wbmdcommons.utils.Device;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/wbmd/wbmdcommons/customviews/TextOverlayLayout;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "arrowPosition", "overlayText", "", "wbmdcommons_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: TextOverlayLayout.kt */
public final class TextOverlayLayout extends LinearLayout {
    private int arrowPosition;
    private String overlayText;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TextOverlayLayout(Context context) {
        this(context, (AttributeSet) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TextOverlayLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TextOverlayLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.overlayText = "";
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.OverLay, 0, 0);
        try {
            this.arrowPosition = obtainStyledAttributes.getInteger(R.styleable.OverLay_arrowPosition, 0);
            String string = obtainStyledAttributes.getString(R.styleable.OverLay_text);
            if (string != null) {
                this.overlayText = string;
            }
            setOrientation(1);
            Object systemService = context.getSystemService("layout_inflater");
            if (systemService != null) {
                LayoutInflater layoutInflater = (LayoutInflater) systemService;
                View inflate = layoutInflater.inflate(R.layout.view_rounded_background_text, this, false);
                if (inflate != null) {
                    TextView textView = (TextView) inflate;
                    View inflate2 = layoutInflater.inflate(R.layout.view_up_arrow, this, false);
                    textView.setText(this.overlayText);
                    addView(inflate2);
                    addView(textView);
                    Intrinsics.checkNotNullExpressionValue(inflate2, "upArrow");
                    ViewGroup.LayoutParams layoutParams = inflate2.getLayoutParams();
                    if (layoutParams != null) {
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layoutParams;
                        int i2 = this.arrowPosition;
                        if (i2 == 0) {
                            layoutParams2.setMarginEnd((int) Device.dpToPixel(18.0f, context));
                            layoutParams2.gravity = GravityCompat.END;
                        } else if (i2 == 1) {
                            layoutParams2.gravity = 17;
                        } else if (i2 == 2) {
                            layoutParams2.setMarginStart((int) Device.dpToPixel(18.0f, context));
                            layoutParams2.gravity = GravityCompat.START;
                        }
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout.LayoutParams");
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
            }
            throw new NullPointerException("null cannot be cast to non-null type android.view.LayoutInflater");
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
