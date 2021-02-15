package coil.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Xml;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u00042\f\u0010\u0006\u001a\b\u0018\u00010\u0007R\u00020\u0005H\u0000\u001a\u000e\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u0002H\u0000\u001a\u001e\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0001\u0010\u0003\u001a\u00020\u0004H\u0001\u001a\u0015\u0010\f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\b\u001a\u001e\u0010\u0010\u001a\u0002H\u0011\"\n\b\u0000\u0010\u0011\u0018\u0001*\u00020\u0012*\u00020\u0002H\b¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"getDrawableCompat", "Landroid/graphics/drawable/Drawable;", "Landroid/content/Context;", "resId", "", "Landroid/content/res/Resources;", "theme", "Landroid/content/res/Resources$Theme;", "getLifecycle", "Landroidx/lifecycle/Lifecycle;", "getXmlDrawableCompat", "resources", "isPermissionGranted", "", "permission", "", "requireSystemService", "T", "", "(Landroid/content/Context;)Ljava/lang/Object;", "coil-base_release"}, k = 2, mv = {1, 1, 16})
/* renamed from: coil.util.-Contexts  reason: invalid class name */
/* compiled from: Contexts.kt */
public final class Contexts {
    public static final Drawable getDrawableCompat(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "$this$getDrawableCompat");
        Drawable drawable = AppCompatResources.getDrawable(context, i);
        if (drawable != null) {
            return drawable;
        }
        throw new IllegalStateException(("Invalid resource ID: " + i).toString());
    }

    public static final Drawable getDrawableCompat(Resources resources, int i, Resources.Theme theme) {
        Intrinsics.checkParameterIsNotNull(resources, "$this$getDrawableCompat");
        Drawable drawable = ResourcesCompat.getDrawable(resources, i, theme);
        if (drawable != null) {
            return drawable;
        }
        throw new IllegalStateException(("Invalid resource ID: " + i).toString());
    }

    public static final Drawable getXmlDrawableCompat(Context context, Resources resources, int i) {
        String name;
        Intrinsics.checkParameterIsNotNull(context, "$this$getXmlDrawableCompat");
        Intrinsics.checkParameterIsNotNull(resources, "resources");
        XmlResourceParser xml = resources.getXml(i);
        Intrinsics.checkExpressionValueIsNotNull(xml, "resources.getXml(resId)");
        int next = xml.next();
        while (next != 2 && next != 1) {
            next = xml.next();
        }
        if (next == 2) {
            if (Build.VERSION.SDK_INT < 24 && (name = xml.getName()) != null) {
                int hashCode = name.hashCode();
                if (hashCode != -820387517) {
                    if (hashCode == 2118620333 && name.equals("animated-vector")) {
                        XmlPullParser xmlPullParser = xml;
                        AnimatedVectorDrawableCompat createFromXmlInner = AnimatedVectorDrawableCompat.createFromXmlInner(context, resources, xmlPullParser, Xml.asAttributeSet(xmlPullParser), context.getTheme());
                        Intrinsics.checkExpressionValueIsNotNull(createFromXmlInner, "AnimatedVectorDrawableCo…es, parser, attrs, theme)");
                        return createFromXmlInner;
                    }
                } else if (name.equals("vector")) {
                    XmlPullParser xmlPullParser2 = xml;
                    VectorDrawableCompat createFromXmlInner2 = VectorDrawableCompat.createFromXmlInner(resources, xmlPullParser2, Xml.asAttributeSet(xmlPullParser2), context.getTheme());
                    Intrinsics.checkExpressionValueIsNotNull(createFromXmlInner2, "VectorDrawableCompat.cre…es, parser, attrs, theme)");
                    return createFromXmlInner2;
                }
            }
            return getDrawableCompat(resources, i, context.getTheme());
        }
        throw new XmlPullParserException("No start tag found.");
    }

    public static final Lifecycle getLifecycle(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "$this$getLifecycle");
        while (!(context instanceof LifecycleOwner)) {
            if (!(context instanceof ContextWrapper)) {
                return null;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return ((LifecycleOwner) context).getLifecycle();
    }

    public static final boolean isPermissionGranted(Context context, String str) {
        Intrinsics.checkParameterIsNotNull(context, "$this$isPermissionGranted");
        Intrinsics.checkParameterIsNotNull(str, "permission");
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static final /* synthetic */ <T> T requireSystemService(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "$this$requireSystemService");
        Intrinsics.reifiedOperationMarker(4, "T");
        T systemService = ContextCompat.getSystemService(context, Object.class);
        if (systemService != null) {
            return systemService;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("System service of type ");
        Intrinsics.reifiedOperationMarker(4, "T");
        sb.append(Object.class);
        sb.append(" was not found.");
        throw new IllegalStateException(sb.toString().toString());
    }
}
