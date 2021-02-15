package com.webmd.webmdrx.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.webmd.webmdrx.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00032\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/webmd/webmdrx/util/MarkerBackgroundProvider;", "", "()V", "Companion", "LOGO_SCALE", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MarkerBackgroundProvider.kt */
public final class MarkerBackgroundProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/webmd/webmdrx/util/MarkerBackgroundProvider$LOGO_SCALE;", "", "(Ljava/lang/String;I)V", "FULL_SIZE", "EIGHTY_PERCENT_ORIGINAL_SIZE", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: MarkerBackgroundProvider.kt */
    public enum LOGO_SCALE {
        FULL_SIZE,
        EIGHTY_PERCENT_ORIGINAL_SIZE
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcom/webmd/webmdrx/util/MarkerBackgroundProvider$Companion;", "", "()V", "getScale", "", "scale", "Lcom/webmd/webmdrx/util/MarkerBackgroundProvider$LOGO_SCALE;", "provideBackground", "Landroid/graphics/Bitmap;", "front", "logoSize", "resources", "Landroid/content/res/Resources;", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: MarkerBackgroundProvider.kt */
    public static final class Companion {

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[LOGO_SCALE.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[LOGO_SCALE.EIGHTY_PERCENT_ORIGINAL_SIZE.ordinal()] = 1;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Bitmap provideBackground(Bitmap bitmap, LOGO_SCALE logo_scale, Resources resources) {
            Intrinsics.checkNotNullParameter(bitmap, "front");
            Intrinsics.checkNotNullParameter(logo_scale, "logoSize");
            Intrinsics.checkNotNullParameter(resources, "resources");
            float scale = getScale(logo_scale);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (((float) bitmap.getWidth()) * scale), (int) (((float) bitmap.getHeight()) * scale), false);
            Bitmap decodeResource = BitmapFactory.decodeResource(resources, R.drawable.map_marker_logo_background);
            Intrinsics.checkNotNullExpressionValue(decodeResource, "back");
            Bitmap createBitmap = Bitmap.createBitmap(decodeResource.getWidth(), decodeResource.getHeight(), decodeResource.getConfig());
            Canvas canvas = new Canvas(createBitmap);
            float width = (float) ((decodeResource.getWidth() - createScaledBitmap.getWidth()) / 2);
            canvas.drawBitmap(decodeResource, 0.0f, 0.0f, (Paint) null);
            canvas.drawBitmap(createScaledBitmap, width, width, (Paint) null);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "combined");
            return createBitmap;
        }

        private final float getScale(LOGO_SCALE logo_scale) {
            return WhenMappings.$EnumSwitchMapping$0[logo_scale.ordinal()] != 1 ? 1.0f : 0.8f;
        }
    }
}
