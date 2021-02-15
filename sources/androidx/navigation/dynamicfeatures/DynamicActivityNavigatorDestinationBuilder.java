package androidx.navigation.dynamicfeatures;

import android.content.ComponentName;
import android.net.Uri;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavDestination;
import androidx.navigation.NavDestinationBuilder;
import androidx.navigation.NavDestinationDsl;
import androidx.navigation.dynamicfeatures.DynamicActivityNavigator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010 \u001a\u00020!H\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000b\"\u0004\b\u0019\u0010\rR\u001c\u0010\u001a\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000b\"\u0004\b\u001c\u0010\rR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u000b\"\u0004\b\u001f\u0010\r¨\u0006\""}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicActivityNavigatorDestinationBuilder;", "Landroidx/navigation/NavDestinationBuilder;", "Landroidx/navigation/ActivityNavigator$Destination;", "activityNavigator", "Landroidx/navigation/dynamicfeatures/DynamicActivityNavigator;", "id", "", "(Landroidx/navigation/dynamicfeatures/DynamicActivityNavigator;I)V", "action", "", "getAction", "()Ljava/lang/String;", "setAction", "(Ljava/lang/String;)V", "activityClassName", "getActivityClassName", "setActivityClassName", "data", "Landroid/net/Uri;", "getData", "()Landroid/net/Uri;", "setData", "(Landroid/net/Uri;)V", "dataPattern", "getDataPattern", "setDataPattern", "moduleName", "getModuleName", "setModuleName", "targetPackage", "getTargetPackage", "setTargetPackage", "build", "Landroidx/navigation/dynamicfeatures/DynamicActivityNavigator$Destination;", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
@NavDestinationDsl
/* compiled from: DynamicActivityNavigatorDestinationBuilder.kt */
public final class DynamicActivityNavigatorDestinationBuilder extends NavDestinationBuilder<ActivityNavigator.Destination> {
    private String action;
    private String activityClassName;
    private final DynamicActivityNavigator activityNavigator;
    private Uri data;
    private String dataPattern;
    private String moduleName;
    private String targetPackage;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DynamicActivityNavigatorDestinationBuilder(DynamicActivityNavigator dynamicActivityNavigator, int i) {
        super(dynamicActivityNavigator, i);
        Intrinsics.checkParameterIsNotNull(dynamicActivityNavigator, "activityNavigator");
        this.activityNavigator = dynamicActivityNavigator;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final void setModuleName(String str) {
        this.moduleName = str;
    }

    public final String getTargetPackage() {
        return this.targetPackage;
    }

    public final void setTargetPackage(String str) {
        this.targetPackage = str;
    }

    public final String getActivityClassName() {
        return this.activityClassName;
    }

    public final void setActivityClassName(String str) {
        this.activityClassName = str;
    }

    public final String getAction() {
        return this.action;
    }

    public final void setAction(String str) {
        this.action = str;
    }

    public final Uri getData() {
        return this.data;
    }

    public final void setData(Uri uri) {
        this.data = uri;
    }

    public final String getDataPattern() {
        return this.dataPattern;
    }

    public final void setDataPattern(String str) {
        this.dataPattern = str;
    }

    public DynamicActivityNavigator.Destination build() {
        NavDestination build = super.build();
        if (build != null) {
            DynamicActivityNavigator.Destination destination = (DynamicActivityNavigator.Destination) build;
            String str = this.activityClassName;
            if (str != null) {
                String str2 = this.targetPackage;
                if (str2 == null) {
                    str2 = this.activityNavigator.getPackageName();
                } else if (str2 == null) {
                    Intrinsics.throwNpe();
                }
                destination.setComponentName(new ComponentName(str2, str));
            }
            destination.setTargetPackage(this.targetPackage);
            destination.setModuleName(this.moduleName);
            destination.setAction(this.action);
            destination.setData(this.data);
            destination.setDataPattern(this.dataPattern);
            return destination;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.navigation.dynamicfeatures.DynamicActivityNavigator.Destination");
    }
}
