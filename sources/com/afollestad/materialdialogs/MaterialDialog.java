package com.afollestad.materialdialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import androidx.recyclerview.widget.RecyclerView;
import com.afollestad.materialdialogs.actions.DialogActionExtKt;
import com.afollestad.materialdialogs.callbacks.DialogCallbackExtKt;
import com.afollestad.materialdialogs.internal.button.DialogActionButton;
import com.afollestad.materialdialogs.internal.list.DialogAdapter;
import com.afollestad.materialdialogs.internal.main.DialogLayout;
import com.afollestad.materialdialogs.list.DialogListExtKt;
import com.afollestad.materialdialogs.message.DialogMessageSettings;
import com.afollestad.materialdialogs.utils.ColorsKt;
import com.afollestad.materialdialogs.utils.DialogsKt;
import com.afollestad.materialdialogs.utils.FontsKt;
import com.afollestad.materialdialogs.utils.MDUtil;
import com.afollestad.materialdialogs.utils.ViewsKt;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 q2\u00020\u0001:\u0001qB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\bJ\u000e\u0010!\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\bJ\u0006\u0010H\u001a\u00020\u0000J\b\u0010I\u001a\u00020\u0000H\u0007J\u0006\u0010J\u001a\u00020\u0000J\u0019\u0010$\u001a\u0002HK\"\u0004\b\u0000\u0010K2\u0006\u0010L\u001a\u00020&¢\u0006\u0002\u0010MJ#\u0010+\u001a\u00020\u00002\n\b\u0002\u0010N\u001a\u0004\u0018\u00010*2\n\b\u0003\u0010O\u001a\u0004\u0018\u000106¢\u0006\u0002\u0010PJ\u0012\u0010Q\u001a\u00020\u00002\b\b\u0002\u0010Q\u001a\u00020\bH\u0007J\b\u0010R\u001a\u00020\u001aH\u0016J#\u0010S\u001a\u00020\u00002\n\b\u0003\u0010O\u001a\u0004\u0018\u0001062\n\b\u0002\u0010T\u001a\u0004\u0018\u00010U¢\u0006\u0002\u0010VJ\b\u0010W\u001a\u00020\u001aH\u0002J#\u00105\u001a\u00020\u00002\n\b\u0003\u0010O\u001a\u0004\u0018\u0001062\n\b\u0003\u0010X\u001a\u0004\u0018\u000106¢\u0006\u0002\u0010YJ@\u0010Z\u001a\u00020\u00002\n\b\u0003\u0010O\u001a\u0004\u0018\u0001062\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\\2\u001b\b\u0002\u0010]\u001a\u0015\u0012\u0004\u0012\u00020^\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019¢\u0006\u0002\b_¢\u0006\u0002\u0010`JA\u0010a\u001a\u00020\u00002\n\b\u0003\u0010O\u001a\u0004\u0018\u0001062\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\\2\u001c\b\u0002\u0010b\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019j\u0004\u0018\u0001`\u001b¢\u0006\u0002\u0010`JC\u0010c\u001a\u00020\u00002\n\b\u0003\u0010O\u001a\u0004\u0018\u0001062\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\\2\u001c\b\u0002\u0010b\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019j\u0004\u0018\u0001`\u001bH\u0007¢\u0006\u0002\u0010`J\b\u0010d\u001a\u00020\u0000H\u0007J\u0015\u0010e\u001a\u00020\u001a2\u0006\u0010f\u001a\u00020gH\u0000¢\u0006\u0002\bhJA\u0010i\u001a\u00020\u00002\n\b\u0003\u0010O\u001a\u0004\u0018\u0001062\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\\2\u001c\b\u0002\u0010b\u001a\u0016\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019j\u0004\u0018\u0001`\u001b¢\u0006\u0002\u0010`J\u0010\u0010j\u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\bH\u0017J\u0010\u0010k\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\bH\u0017J\b\u0010l\u001a\u00020\u001aH\u0002J\b\u0010m\u001a\u00020\u001aH\u0016J\"\u0010m\u001a\u00020\u00002\u0017\u0010n\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\u0002\b_H\bJ#\u0010o\u001a\u00020\u00002\n\b\u0003\u0010O\u001a\u0004\u0018\u0001062\n\b\u0002\u0010[\u001a\u0004\u0018\u00010&¢\u0006\u0002\u0010pR$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000e@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R(\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000e@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R*\u0010\u0017\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a0\u0019j\u0002`\u001b0\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000b\"\u0004\b \u0010\rR$\u0010!\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000b\"\u0004\b#\u0010\rR\u001d\u0010$\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R*\u0010+\u001a\u0004\u0018\u00010*2\b\u0010\u0007\u001a\u0004\u0018\u00010*@@X\u000e¢\u0006\u0010\n\u0002\u00100\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R*\u00103\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a0\u0019j\u0002`\u001b0\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001dR\u0016\u00105\u001a\u0004\u0018\u0001068\u0002@\u0002X\u000e¢\u0006\u0004\n\u0002\u00107R$\u00108\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a0\u0019j\u0002`\u001b0\u0018X\u0004¢\u0006\u0002\n\u0000R$\u00109\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a0\u0019j\u0002`\u001b0\u0018X\u0004¢\u0006\u0002\n\u0000R$\u0010:\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a0\u0019j\u0002`\u001b0\u0018X\u0004¢\u0006\u0002\n\u0000R*\u0010;\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a0\u0019j\u0002`\u001b0\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u001dR*\u0010=\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u001a0\u0019j\u0002`\u001b0\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u001dR(\u0010?\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0007\u001a\u0004\u0018\u00010\u000e@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0011\"\u0004\bA\u0010\u0013R\u0011\u0010B\u001a\u00020C¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bF\u0010G¨\u0006r"}, d2 = {"Lcom/afollestad/materialdialogs/MaterialDialog;", "Landroid/app/Dialog;", "windowContext", "Landroid/content/Context;", "dialogBehavior", "Lcom/afollestad/materialdialogs/DialogBehavior;", "(Landroid/content/Context;Lcom/afollestad/materialdialogs/DialogBehavior;)V", "<set-?>", "", "autoDismissEnabled", "getAutoDismissEnabled", "()Z", "setAutoDismissEnabled$core", "(Z)V", "Landroid/graphics/Typeface;", "bodyFont", "getBodyFont", "()Landroid/graphics/Typeface;", "setBodyFont$core", "(Landroid/graphics/Typeface;)V", "buttonFont", "getButtonFont", "setButtonFont$core", "cancelListeners", "", "Lkotlin/Function1;", "", "Lcom/afollestad/materialdialogs/DialogCallback;", "getCancelListeners$core", "()Ljava/util/List;", "cancelOnTouchOutside", "getCancelOnTouchOutside", "setCancelOnTouchOutside$core", "cancelable", "getCancelable", "setCancelable$core", "config", "", "", "", "getConfig", "()Ljava/util/Map;", "", "cornerRadius", "getCornerRadius", "()Ljava/lang/Float;", "setCornerRadius$core", "(Ljava/lang/Float;)V", "Ljava/lang/Float;", "getDialogBehavior", "()Lcom/afollestad/materialdialogs/DialogBehavior;", "dismissListeners", "getDismissListeners$core", "maxWidth", "", "Ljava/lang/Integer;", "negativeListeners", "neutralListeners", "positiveListeners", "preShowListeners", "getPreShowListeners$core", "showListeners", "getShowListeners$core", "titleFont", "getTitleFont", "setTitleFont$core", "view", "Lcom/afollestad/materialdialogs/internal/main/DialogLayout;", "getView", "()Lcom/afollestad/materialdialogs/internal/main/DialogLayout;", "getWindowContext", "()Landroid/content/Context;", "clearNegativeListeners", "clearNeutralListeners", "clearPositiveListeners", "T", "key", "(Ljava/lang/String;)Ljava/lang/Object;", "literalDp", "res", "(Ljava/lang/Float;Ljava/lang/Integer;)Lcom/afollestad/materialdialogs/MaterialDialog;", "debugMode", "dismiss", "icon", "drawable", "Landroid/graphics/drawable/Drawable;", "(Ljava/lang/Integer;Landroid/graphics/drawable/Drawable;)Lcom/afollestad/materialdialogs/MaterialDialog;", "invalidateBackgroundColorAndRadius", "literal", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lcom/afollestad/materialdialogs/MaterialDialog;", "message", "text", "", "applySettings", "Lcom/afollestad/materialdialogs/message/DialogMessageSettings;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Integer;Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Lcom/afollestad/materialdialogs/MaterialDialog;", "negativeButton", "click", "neutralButton", "noAutoDismiss", "onActionButtonClicked", "which", "Lcom/afollestad/materialdialogs/WhichButton;", "onActionButtonClicked$core", "positiveButton", "setCancelable", "setCanceledOnTouchOutside", "setWindowConstraints", "show", "func", "title", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/afollestad/materialdialogs/MaterialDialog;", "Companion", "core"}, k = 1, mv = {1, 1, 16})
/* compiled from: MaterialDialog.kt */
public final class MaterialDialog extends Dialog {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static DialogBehavior DEFAULT_BEHAVIOR = ModalDialog.INSTANCE;
    private boolean autoDismissEnabled;
    private Typeface bodyFont;
    private Typeface buttonFont;
    private final List<Function1<MaterialDialog, Unit>> cancelListeners;
    private boolean cancelOnTouchOutside;
    private boolean cancelable;
    private final Map<String, Object> config;
    private Float cornerRadius;
    private final DialogBehavior dialogBehavior;
    private final List<Function1<MaterialDialog, Unit>> dismissListeners;
    private Integer maxWidth;
    private final List<Function1<MaterialDialog, Unit>> negativeListeners;
    private final List<Function1<MaterialDialog, Unit>> neutralListeners;
    private final List<Function1<MaterialDialog, Unit>> positiveListeners;
    private final List<Function1<MaterialDialog, Unit>> preShowListeners;
    private final List<Function1<MaterialDialog, Unit>> showListeners;
    private Typeface titleFont;
    private final DialogLayout view;
    private final Context windowContext;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WhichButton.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[WhichButton.POSITIVE.ordinal()] = 1;
            $EnumSwitchMapping$0[WhichButton.NEGATIVE.ordinal()] = 2;
            $EnumSwitchMapping$0[WhichButton.NEUTRAL.ordinal()] = 3;
        }
    }

    public static final DialogBehavior getDEFAULT_BEHAVIOR() {
        return DEFAULT_BEHAVIOR;
    }

    public static final void setDEFAULT_BEHAVIOR(DialogBehavior dialogBehavior2) {
        DEFAULT_BEHAVIOR = dialogBehavior2;
    }

    public final Context getWindowContext() {
        return this.windowContext;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MaterialDialog(Context context, DialogBehavior dialogBehavior2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? DEFAULT_BEHAVIOR : dialogBehavior2);
    }

    public final DialogBehavior getDialogBehavior() {
        return this.dialogBehavior;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MaterialDialog(Context context, DialogBehavior dialogBehavior2) {
        super(context, ThemeKt.inferTheme(context, dialogBehavior2));
        Intrinsics.checkParameterIsNotNull(context, "windowContext");
        Intrinsics.checkParameterIsNotNull(dialogBehavior2, "dialogBehavior");
        this.windowContext = context;
        this.dialogBehavior = dialogBehavior2;
        this.config = new LinkedHashMap();
        this.autoDismissEnabled = true;
        this.cancelOnTouchOutside = true;
        this.cancelable = true;
        this.preShowListeners = new ArrayList();
        this.showListeners = new ArrayList();
        this.dismissListeners = new ArrayList();
        this.cancelListeners = new ArrayList();
        this.positiveListeners = new ArrayList();
        this.negativeListeners = new ArrayList();
        this.neutralListeners = new ArrayList();
        LayoutInflater from = LayoutInflater.from(this.windowContext);
        DialogBehavior dialogBehavior3 = this.dialogBehavior;
        Context context2 = this.windowContext;
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window, "window!!");
        Intrinsics.checkExpressionValueIsNotNull(from, "layoutInflater");
        ViewGroup createView = dialogBehavior3.createView(context2, window, from, this);
        setContentView(createView);
        DialogLayout dialogLayout = this.dialogBehavior.getDialogLayout(createView);
        dialogLayout.attachDialog(this);
        this.view = dialogLayout;
        this.titleFont = FontsKt.font$default(this, (Integer) null, Integer.valueOf(R.attr.md_font_title), 1, (Object) null);
        this.bodyFont = FontsKt.font$default(this, (Integer) null, Integer.valueOf(R.attr.md_font_body), 1, (Object) null);
        this.buttonFont = FontsKt.font$default(this, (Integer) null, Integer.valueOf(R.attr.md_font_button), 1, (Object) null);
        invalidateBackgroundColorAndRadius();
    }

    public final Map<String, Object> getConfig() {
        return this.config;
    }

    public final <T> T config(String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        return this.config.get(str);
    }

    public final boolean getAutoDismissEnabled() {
        return this.autoDismissEnabled;
    }

    public final void setAutoDismissEnabled$core(boolean z) {
        this.autoDismissEnabled = z;
    }

    public final Typeface getTitleFont() {
        return this.titleFont;
    }

    public final void setTitleFont$core(Typeface typeface) {
        this.titleFont = typeface;
    }

    public final Typeface getBodyFont() {
        return this.bodyFont;
    }

    public final void setBodyFont$core(Typeface typeface) {
        this.bodyFont = typeface;
    }

    public final Typeface getButtonFont() {
        return this.buttonFont;
    }

    public final void setButtonFont$core(Typeface typeface) {
        this.buttonFont = typeface;
    }

    public final boolean getCancelOnTouchOutside() {
        return this.cancelOnTouchOutside;
    }

    public final void setCancelOnTouchOutside$core(boolean z) {
        this.cancelOnTouchOutside = z;
    }

    public final boolean getCancelable() {
        return this.cancelable;
    }

    public final void setCancelable$core(boolean z) {
        this.cancelable = z;
    }

    public final Float getCornerRadius() {
        return this.cornerRadius;
    }

    public final void setCornerRadius$core(Float f) {
        this.cornerRadius = f;
    }

    public final DialogLayout getView() {
        return this.view;
    }

    public final List<Function1<MaterialDialog, Unit>> getPreShowListeners$core() {
        return this.preShowListeners;
    }

    public final List<Function1<MaterialDialog, Unit>> getShowListeners$core() {
        return this.showListeners;
    }

    public final List<Function1<MaterialDialog, Unit>> getDismissListeners$core() {
        return this.dismissListeners;
    }

    public final List<Function1<MaterialDialog, Unit>> getCancelListeners$core() {
        return this.cancelListeners;
    }

    public static /* synthetic */ MaterialDialog icon$default(MaterialDialog materialDialog, Integer num, Drawable drawable, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            drawable = null;
        }
        return materialDialog.icon(num, drawable);
    }

    public final MaterialDialog icon(Integer num, Drawable drawable) {
        MaterialDialog materialDialog = this;
        MDUtil.INSTANCE.assertOneSet("icon", drawable, num);
        DialogsKt.populateIcon(materialDialog, materialDialog.view.getTitleLayout().getIconView$core(), num, drawable);
        return materialDialog;
    }

    public static /* synthetic */ MaterialDialog title$default(MaterialDialog materialDialog, Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return materialDialog.title(num, str);
    }

    public final MaterialDialog title(Integer num, String str) {
        MaterialDialog materialDialog = this;
        MDUtil.INSTANCE.assertOneSet("title", str, num);
        DialogsKt.populateText$default(materialDialog, materialDialog.view.getTitleLayout().getTitleView$core(), num, str, 0, materialDialog.titleFont, Integer.valueOf(R.attr.md_color_title), 8, (Object) null);
        return materialDialog;
    }

    public static /* synthetic */ MaterialDialog message$default(MaterialDialog materialDialog, Integer num, CharSequence charSequence, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            charSequence = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        return materialDialog.message(num, charSequence, function1);
    }

    public final MaterialDialog message(Integer num, CharSequence charSequence, Function1<? super DialogMessageSettings, Unit> function1) {
        MaterialDialog materialDialog = this;
        MDUtil.INSTANCE.assertOneSet(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, charSequence, num);
        materialDialog.view.getContentLayout().setMessage(materialDialog, num, charSequence, materialDialog.bodyFont, function1);
        return materialDialog;
    }

    public static /* synthetic */ MaterialDialog positiveButton$default(MaterialDialog materialDialog, Integer num, CharSequence charSequence, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            charSequence = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        return materialDialog.positiveButton(num, charSequence, function1);
    }

    public final MaterialDialog positiveButton(Integer num, CharSequence charSequence, Function1<? super MaterialDialog, Unit> function1) {
        MaterialDialog materialDialog = this;
        if (function1 != null) {
            materialDialog.positiveListeners.add(function1);
        }
        DialogActionButton actionButton = DialogActionExtKt.getActionButton(materialDialog, WhichButton.POSITIVE);
        if (num == null && charSequence == null && ViewsKt.isVisible(actionButton)) {
            return materialDialog;
        }
        DialogsKt.populateText$default(materialDialog, actionButton, num, charSequence, 17039370, materialDialog.buttonFont, (Integer) null, 32, (Object) null);
        return materialDialog;
    }

    public final MaterialDialog clearPositiveListeners() {
        MaterialDialog materialDialog = this;
        materialDialog.positiveListeners.clear();
        return materialDialog;
    }

    public static /* synthetic */ MaterialDialog negativeButton$default(MaterialDialog materialDialog, Integer num, CharSequence charSequence, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            charSequence = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        return materialDialog.negativeButton(num, charSequence, function1);
    }

    public final MaterialDialog negativeButton(Integer num, CharSequence charSequence, Function1<? super MaterialDialog, Unit> function1) {
        MaterialDialog materialDialog = this;
        if (function1 != null) {
            materialDialog.negativeListeners.add(function1);
        }
        DialogActionButton actionButton = DialogActionExtKt.getActionButton(materialDialog, WhichButton.NEGATIVE);
        if (!(num == null && charSequence == null && ViewsKt.isVisible(actionButton))) {
            DialogsKt.populateText$default(materialDialog, actionButton, num, charSequence, 17039360, materialDialog.buttonFont, (Integer) null, 32, (Object) null);
        }
        return materialDialog;
    }

    public final MaterialDialog clearNegativeListeners() {
        MaterialDialog materialDialog = this;
        materialDialog.negativeListeners.clear();
        return materialDialog;
    }

    public static /* synthetic */ MaterialDialog neutralButton$default(MaterialDialog materialDialog, Integer num, CharSequence charSequence, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            charSequence = null;
        }
        if ((i & 4) != 0) {
            function1 = null;
        }
        return materialDialog.neutralButton(num, charSequence, function1);
    }

    @Deprecated(message = "Use of neutral buttons is discouraged, see https://material.io/design/components/dialogs.html#actions.")
    public final MaterialDialog neutralButton(Integer num, CharSequence charSequence, Function1<? super MaterialDialog, Unit> function1) {
        MaterialDialog materialDialog = this;
        if (function1 != null) {
            materialDialog.neutralListeners.add(function1);
        }
        DialogActionButton actionButton = DialogActionExtKt.getActionButton(materialDialog, WhichButton.NEUTRAL);
        if (!(num == null && charSequence == null && ViewsKt.isVisible(actionButton))) {
            DialogsKt.populateText$default(materialDialog, actionButton, num, charSequence, 0, materialDialog.buttonFont, (Integer) null, 40, (Object) null);
        }
        return materialDialog;
    }

    @Deprecated(message = "Use of neutral buttons is discouraged, see https://material.io/design/components/dialogs.html#actions.")
    public final MaterialDialog clearNeutralListeners() {
        MaterialDialog materialDialog = this;
        materialDialog.neutralListeners.clear();
        return materialDialog;
    }

    public final MaterialDialog noAutoDismiss() {
        MaterialDialog materialDialog = this;
        materialDialog.autoDismissEnabled = false;
        return materialDialog;
    }

    public static /* synthetic */ MaterialDialog maxWidth$default(MaterialDialog materialDialog, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        if ((i & 2) != 0) {
            num2 = null;
        }
        return materialDialog.maxWidth(num, num2);
    }

    public final MaterialDialog maxWidth(Integer num, Integer num2) {
        MaterialDialog materialDialog = this;
        MDUtil.INSTANCE.assertOneSet("maxWidth", num, num2);
        Integer num3 = materialDialog.maxWidth;
        boolean z = (num3 == null || num3 == null || num3.intValue() != 0) ? false : true;
        if (num != null) {
            num2 = Integer.valueOf(materialDialog.windowContext.getResources().getDimensionPixelSize(num.intValue()));
        } else if (num2 == null) {
            Intrinsics.throwNpe();
        }
        materialDialog.maxWidth = num2;
        if (z) {
            materialDialog.setWindowConstraints();
        }
        return materialDialog;
    }

    public static /* synthetic */ MaterialDialog cornerRadius$default(MaterialDialog materialDialog, Float f, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            f = null;
        }
        if ((i & 2) != 0) {
            num = null;
        }
        return materialDialog.cornerRadius(f, num);
    }

    public final MaterialDialog cornerRadius(Float f, Integer num) {
        Float f2;
        MaterialDialog materialDialog = this;
        MDUtil.INSTANCE.assertOneSet("cornerRadius", f, num);
        if (num != null) {
            f2 = Float.valueOf(materialDialog.windowContext.getResources().getDimension(num.intValue()));
        } else {
            Resources resources = materialDialog.windowContext.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "windowContext.resources");
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            if (f == null) {
                Intrinsics.throwNpe();
            }
            f2 = Float.valueOf(TypedValue.applyDimension(1, f.floatValue(), displayMetrics));
        }
        materialDialog.cornerRadius = f2;
        materialDialog.invalidateBackgroundColorAndRadius();
        return materialDialog;
    }

    public static /* synthetic */ MaterialDialog debugMode$default(MaterialDialog materialDialog, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return materialDialog.debugMode(z);
    }

    public final MaterialDialog debugMode(boolean z) {
        MaterialDialog materialDialog = this;
        materialDialog.view.setDebugMode(z);
        return materialDialog;
    }

    public void show() {
        setWindowConstraints();
        DialogsKt.preShow(this);
        this.dialogBehavior.onPreShow(this);
        super.show();
        this.dialogBehavior.onPostShow(this);
    }

    public final MaterialDialog show(Function1<? super MaterialDialog, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "func");
        MaterialDialog materialDialog = this;
        function1.invoke(materialDialog);
        materialDialog.show();
        return materialDialog;
    }

    public final MaterialDialog cancelable(boolean z) {
        MaterialDialog materialDialog = this;
        materialDialog.setCancelable(z);
        return materialDialog;
    }

    @Deprecated(message = "Use fluent cancelable(Boolean) instead.", replaceWith = @ReplaceWith(expression = "cancelable(cancelable)", imports = {}))
    public void setCancelable(boolean z) {
        this.cancelable = z;
        super.setCancelable(z);
    }

    public final MaterialDialog cancelOnTouchOutside(boolean z) {
        MaterialDialog materialDialog = this;
        materialDialog.setCanceledOnTouchOutside(z);
        return materialDialog;
    }

    @Deprecated(message = "Use fluent cancelOnTouchOutside(Boolean) instead.", replaceWith = @ReplaceWith(expression = "cancelOnTouchOutside(cancelOnTouchOutside)", imports = {}))
    public void setCanceledOnTouchOutside(boolean z) {
        this.cancelOnTouchOutside = z;
        super.setCanceledOnTouchOutside(z);
    }

    public void dismiss() {
        if (!this.dialogBehavior.onDismiss()) {
            DialogsKt.hideKeyboard(this);
            super.dismiss();
        }
    }

    public final void onActionButtonClicked$core(WhichButton whichButton) {
        Intrinsics.checkParameterIsNotNull(whichButton, "which");
        int i = WhenMappings.$EnumSwitchMapping$0[whichButton.ordinal()];
        if (i == 1) {
            DialogCallbackExtKt.invokeAll(this.positiveListeners, this);
            RecyclerView.Adapter<?> listAdapter = DialogListExtKt.getListAdapter(this);
            if (!(listAdapter instanceof DialogAdapter)) {
                listAdapter = null;
            }
            DialogAdapter dialogAdapter = (DialogAdapter) listAdapter;
            if (dialogAdapter != null) {
                dialogAdapter.positiveButtonClicked();
            }
        } else if (i == 2) {
            DialogCallbackExtKt.invokeAll(this.negativeListeners, this);
        } else if (i == 3) {
            DialogCallbackExtKt.invokeAll(this.neutralListeners, this);
        }
        if (this.autoDismissEnabled) {
            dismiss();
        }
    }

    private final void setWindowConstraints() {
        DialogBehavior dialogBehavior2 = this.dialogBehavior;
        Context context = this.windowContext;
        Integer num = this.maxWidth;
        Window window = getWindow();
        if (window == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(window, "window!!");
        dialogBehavior2.setWindowConstraints(context, window, this.view, num);
    }

    private final void invalidateBackgroundColorAndRadius() {
        int resolveColor$default = ColorsKt.resolveColor$default(this, (Integer) null, Integer.valueOf(R.attr.md_background_color), new MaterialDialog$invalidateBackgroundColorAndRadius$backgroundColor$1(this), 1, (Object) null);
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
        }
        DialogBehavior dialogBehavior2 = this.dialogBehavior;
        DialogLayout dialogLayout = this.view;
        Float f = this.cornerRadius;
        dialogBehavior2.setBackgroundColor(dialogLayout, resolveColor$default, f != null ? f.floatValue() : MDUtil.INSTANCE.resolveDimen(this.windowContext, R.attr.md_corner_radius, new MaterialDialog$invalidateBackgroundColorAndRadius$1(this)));
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/afollestad/materialdialogs/MaterialDialog$Companion;", "", "()V", "DEFAULT_BEHAVIOR", "Lcom/afollestad/materialdialogs/DialogBehavior;", "DEFAULT_BEHAVIOR$annotations", "getDEFAULT_BEHAVIOR", "()Lcom/afollestad/materialdialogs/DialogBehavior;", "setDEFAULT_BEHAVIOR", "(Lcom/afollestad/materialdialogs/DialogBehavior;)V", "core"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MaterialDialog.kt */
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void DEFAULT_BEHAVIOR$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DialogBehavior getDEFAULT_BEHAVIOR() {
            return MaterialDialog.DEFAULT_BEHAVIOR;
        }

        public final void setDEFAULT_BEHAVIOR(DialogBehavior dialogBehavior) {
            Intrinsics.checkParameterIsNotNull(dialogBehavior, "<set-?>");
            MaterialDialog.DEFAULT_BEHAVIOR = dialogBehavior;
        }
    }
}
