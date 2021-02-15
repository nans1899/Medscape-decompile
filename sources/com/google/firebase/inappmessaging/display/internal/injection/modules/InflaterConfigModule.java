package com.google.firebase.inappmessaging.display.internal.injection.modules;

import android.app.Application;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.firebase.inappmessaging.display.internal.InAppMessageLayoutConfig;
import com.google.firebase.inappmessaging.display.internal.injection.keys.LayoutConfigKey;
import com.google.firebase.inappmessaging.model.MessageType;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
/* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
public class InflaterConfigModule {
    public static int DISABLED_BG_FLAG = 327938;
    public static int DISMISSIBLE_DIALOG_FLAG = 327970;
    private int ENABLED_BG_FLAG = 65824;

    /* renamed from: com.google.firebase.inappmessaging.display.internal.injection.modules.InflaterConfigModule$1  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-inappmessaging-display@@19.0.5 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$inappmessaging$model$MessageType;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.firebase.inappmessaging.model.MessageType[] r0 = com.google.firebase.inappmessaging.model.MessageType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$inappmessaging$model$MessageType = r0
                com.google.firebase.inappmessaging.model.MessageType r1 = com.google.firebase.inappmessaging.model.MessageType.MODAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$model$MessageType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.inappmessaging.model.MessageType r1 = com.google.firebase.inappmessaging.model.MessageType.CARD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$model$MessageType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.inappmessaging.model.MessageType r1 = com.google.firebase.inappmessaging.model.MessageType.IMAGE_ONLY     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$model$MessageType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.firebase.inappmessaging.model.MessageType r1 = com.google.firebase.inappmessaging.model.MessageType.BANNER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.display.internal.injection.modules.InflaterConfigModule.AnonymousClass1.<clinit>():void");
        }
    }

    public static String configFor(MessageType messageType, int i) {
        if (i == 1) {
            int i2 = AnonymousClass1.$SwitchMap$com$google$firebase$inappmessaging$model$MessageType[messageType.ordinal()];
            if (i2 == 1) {
                return LayoutConfigKey.MODAL_PORTRAIT;
            }
            if (i2 == 2) {
                return LayoutConfigKey.CARD_PORTRAIT;
            }
            if (i2 == 3) {
                return LayoutConfigKey.IMAGE_ONLY_PORTRAIT;
            }
            if (i2 != 4) {
                return null;
            }
            return LayoutConfigKey.BANNER_PORTRAIT;
        }
        int i3 = AnonymousClass1.$SwitchMap$com$google$firebase$inappmessaging$model$MessageType[messageType.ordinal()];
        if (i3 == 1) {
            return LayoutConfigKey.MODAL_LANDSCAPE;
        }
        if (i3 == 2) {
            return LayoutConfigKey.CARD_LANDSCAPE;
        }
        if (i3 == 3) {
            return LayoutConfigKey.IMAGE_ONLY_LANDSCAPE;
        }
        if (i3 != 4) {
            return null;
        }
        return LayoutConfigKey.BANNER_LANDSCAPE;
    }

    /* access modifiers changed from: package-private */
    @Provides
    public DisplayMetrics providesDisplayMetrics(Application application) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) application.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    @IntoMap
    @StringKey("IMAGE_ONLY_PORTRAIT")
    @Provides
    public InAppMessageLayoutConfig providesPortraitImageLayoutConfig(DisplayMetrics displayMetrics) {
        InAppMessageLayoutConfig.Builder maxDialogWidthPx = InAppMessageLayoutConfig.builder().setMaxDialogHeightPx(Integer.valueOf((int) (((float) displayMetrics.heightPixels) * 0.9f))).setMaxDialogWidthPx(Integer.valueOf((int) (((float) displayMetrics.widthPixels) * 0.9f)));
        Float valueOf = Float.valueOf(0.8f);
        return maxDialogWidthPx.setMaxImageWidthWeight(valueOf).setMaxImageHeightWeight(valueOf).setViewWindowGravity(17).setWindowFlag(Integer.valueOf(DISABLED_BG_FLAG)).setWindowWidth(-2).setWindowHeight(-2).setBackgroundEnabled(false).setAnimate(false).setAutoDismiss(false).build();
    }

    @IntoMap
    @StringKey("IMAGE_ONLY_LANDSCAPE")
    @Provides
    public InAppMessageLayoutConfig providesLandscapeImageLayoutConfig(DisplayMetrics displayMetrics) {
        InAppMessageLayoutConfig.Builder maxDialogWidthPx = InAppMessageLayoutConfig.builder().setMaxDialogHeightPx(Integer.valueOf((int) (((float) displayMetrics.heightPixels) * 0.9f))).setMaxDialogWidthPx(Integer.valueOf((int) (((float) displayMetrics.widthPixels) * 0.9f)));
        Float valueOf = Float.valueOf(0.8f);
        return maxDialogWidthPx.setMaxImageWidthWeight(valueOf).setMaxImageHeightWeight(valueOf).setViewWindowGravity(17).setWindowFlag(Integer.valueOf(DISABLED_BG_FLAG)).setWindowWidth(-2).setWindowHeight(-2).setBackgroundEnabled(false).setAnimate(false).setAutoDismiss(false).build();
    }

    @IntoMap
    @StringKey("MODAL_LANDSCAPE")
    @Provides
    public InAppMessageLayoutConfig providesModalLandscapeConfig(DisplayMetrics displayMetrics) {
        InAppMessageLayoutConfig.Builder maxImageHeightWeight = InAppMessageLayoutConfig.builder().setMaxDialogHeightPx(Integer.valueOf((int) (((double) displayMetrics.heightPixels) * 0.8d))).setMaxDialogWidthPx(Integer.valueOf(displayMetrics.widthPixels)).setMaxImageHeightWeight(Float.valueOf(1.0f));
        Float valueOf = Float.valueOf(0.4f);
        return maxImageHeightWeight.setMaxImageWidthWeight(valueOf).setMaxBodyHeightWeight(Float.valueOf(0.6f)).setMaxBodyWidthWeight(valueOf).setViewWindowGravity(17).setWindowFlag(Integer.valueOf(DISABLED_BG_FLAG)).setWindowWidth(-1).setWindowHeight(-1).setBackgroundEnabled(false).setAnimate(false).setAutoDismiss(false).build();
    }

    @IntoMap
    @StringKey("MODAL_PORTRAIT")
    @Provides
    public InAppMessageLayoutConfig providesModalPortraitConfig(DisplayMetrics displayMetrics) {
        InAppMessageLayoutConfig.Builder maxBodyHeightWeight = InAppMessageLayoutConfig.builder().setMaxDialogHeightPx(Integer.valueOf((int) (((double) displayMetrics.heightPixels) * 0.8d))).setMaxDialogWidthPx(Integer.valueOf((int) (((float) displayMetrics.widthPixels) * 0.7f))).setMaxImageHeightWeight(Float.valueOf(0.6f)).setMaxBodyHeightWeight(Float.valueOf(0.1f));
        Float valueOf = Float.valueOf(0.9f);
        return maxBodyHeightWeight.setMaxImageWidthWeight(valueOf).setMaxBodyWidthWeight(valueOf).setViewWindowGravity(17).setWindowFlag(Integer.valueOf(DISABLED_BG_FLAG)).setWindowWidth(-1).setWindowHeight(-2).setBackgroundEnabled(false).setAnimate(false).setAutoDismiss(false).build();
    }

    @IntoMap
    @StringKey("CARD_LANDSCAPE")
    @Provides
    public InAppMessageLayoutConfig providesCardLandscapeConfig(DisplayMetrics displayMetrics) {
        return InAppMessageLayoutConfig.builder().setMaxDialogHeightPx(Integer.valueOf((int) (((double) displayMetrics.heightPixels) * 0.8d))).setMaxDialogWidthPx(Integer.valueOf(displayMetrics.widthPixels)).setMaxImageHeightWeight(Float.valueOf(1.0f)).setMaxImageWidthWeight(Float.valueOf(0.5f)).setViewWindowGravity(17).setWindowFlag(Integer.valueOf(DISMISSIBLE_DIALOG_FLAG)).setWindowWidth(-2).setWindowHeight(-2).setBackgroundEnabled(false).setAnimate(false).setAutoDismiss(false).build();
    }

    @IntoMap
    @StringKey("CARD_PORTRAIT")
    @Provides
    public InAppMessageLayoutConfig providesCardPortraitConfig(DisplayMetrics displayMetrics) {
        return InAppMessageLayoutConfig.builder().setMaxDialogHeightPx(Integer.valueOf((int) (((double) displayMetrics.heightPixels) * 0.8d))).setMaxDialogWidthPx(Integer.valueOf((int) (((float) displayMetrics.widthPixels) * 0.7f))).setMaxImageHeightWeight(Float.valueOf(0.6f)).setMaxImageWidthWeight(Float.valueOf(1.0f)).setMaxBodyHeightWeight(Float.valueOf(0.1f)).setMaxBodyWidthWeight(Float.valueOf(0.9f)).setViewWindowGravity(17).setWindowFlag(Integer.valueOf(DISMISSIBLE_DIALOG_FLAG)).setWindowWidth(-2).setWindowHeight(-2).setBackgroundEnabled(false).setAnimate(false).setAutoDismiss(false).build();
    }

    @IntoMap
    @StringKey("BANNER_PORTRAIT")
    @Provides
    public InAppMessageLayoutConfig providesBannerPortraitLayoutConfig(DisplayMetrics displayMetrics) {
        InAppMessageLayoutConfig.Builder builder = InAppMessageLayoutConfig.builder();
        Float valueOf = Float.valueOf(0.3f);
        return builder.setMaxImageHeightWeight(valueOf).setMaxImageWidthWeight(valueOf).setMaxDialogHeightPx(Integer.valueOf((int) (((float) displayMetrics.heightPixels) * 0.5f))).setMaxDialogWidthPx(Integer.valueOf((int) (((float) displayMetrics.widthPixels) * 0.9f))).setViewWindowGravity(48).setWindowFlag(Integer.valueOf(this.ENABLED_BG_FLAG)).setWindowWidth(-1).setWindowHeight(-2).setBackgroundEnabled(true).setAnimate(true).setAutoDismiss(true).build();
    }

    @IntoMap
    @StringKey("BANNER_LANDSCAPE")
    @Provides
    public InAppMessageLayoutConfig providesBannerLandscapeLayoutConfig(DisplayMetrics displayMetrics) {
        InAppMessageLayoutConfig.Builder builder = InAppMessageLayoutConfig.builder();
        Float valueOf = Float.valueOf(0.3f);
        return builder.setMaxImageHeightWeight(valueOf).setMaxImageWidthWeight(valueOf).setMaxDialogHeightPx(Integer.valueOf((int) (((float) displayMetrics.heightPixels) * 0.5f))).setMaxDialogWidthPx(Integer.valueOf((int) (((float) displayMetrics.widthPixels) * 0.9f))).setViewWindowGravity(48).setWindowFlag(Integer.valueOf(this.ENABLED_BG_FLAG)).setWindowWidth(-1).setWindowHeight(-2).setBackgroundEnabled(true).setAnimate(true).setAutoDismiss(true).build();
    }
}
