package androidx.navigation.dynamicfeatures;

import androidx.navigation.NavDestinationBuilder;
import androidx.navigation.NavDestinationDsl;
import androidx.navigation.dynamicfeatures.DynamicIncludeGraphNavigator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\b\u0010\u0010\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\t\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/navigation/dynamicfeatures/DynamicIncludeNavGraphBuilder;", "Landroidx/navigation/NavDestinationBuilder;", "Landroidx/navigation/dynamicfeatures/DynamicIncludeGraphNavigator$DynamicIncludeNavGraph;", "dynamicIncludeGraphNavigator", "Landroidx/navigation/dynamicfeatures/DynamicIncludeGraphNavigator;", "id", "", "moduleName", "", "graphResourceName", "(Landroidx/navigation/dynamicfeatures/DynamicIncludeGraphNavigator;ILjava/lang/String;Ljava/lang/String;)V", "graphPackage", "getGraphPackage", "()Ljava/lang/String;", "setGraphPackage", "(Ljava/lang/String;)V", "build", "navigation-dynamic-features-runtime_release"}, k = 1, mv = {1, 1, 16})
@NavDestinationDsl
/* compiled from: DynamicIncludeNavGraphBuilder.kt */
public final class DynamicIncludeNavGraphBuilder extends NavDestinationBuilder<DynamicIncludeGraphNavigator.DynamicIncludeNavGraph> {
    private final DynamicIncludeGraphNavigator dynamicIncludeGraphNavigator;
    private String graphPackage;
    private final String graphResourceName;
    private final String moduleName;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DynamicIncludeNavGraphBuilder(DynamicIncludeGraphNavigator dynamicIncludeGraphNavigator2, int i, String str, String str2) {
        super(dynamicIncludeGraphNavigator2, i);
        Intrinsics.checkParameterIsNotNull(dynamicIncludeGraphNavigator2, "dynamicIncludeGraphNavigator");
        Intrinsics.checkParameterIsNotNull(str, "moduleName");
        Intrinsics.checkParameterIsNotNull(str2, "graphResourceName");
        this.dynamicIncludeGraphNavigator = dynamicIncludeGraphNavigator2;
        this.moduleName = str;
        this.graphResourceName = str2;
    }

    public final String getGraphPackage() {
        return this.graphPackage;
    }

    public final void setGraphPackage(String str) {
        this.graphPackage = str;
    }

    public DynamicIncludeGraphNavigator.DynamicIncludeNavGraph build() {
        DynamicIncludeGraphNavigator.DynamicIncludeNavGraph dynamicIncludeNavGraph = (DynamicIncludeGraphNavigator.DynamicIncludeNavGraph) super.build();
        boolean z = false;
        if (this.moduleName.length() > 0) {
            dynamicIncludeNavGraph.setModuleName(this.moduleName);
            String str = this.graphPackage;
            if (str == null) {
                dynamicIncludeNavGraph.setGraphPackage(this.dynamicIncludeGraphNavigator.getPackageName() + '.' + this.moduleName);
            } else {
                CharSequence charSequence = str;
                if (!(charSequence == null || charSequence.length() == 0)) {
                    dynamicIncludeNavGraph.setGraphPackage(this.graphPackage);
                } else {
                    throw new IllegalStateException("Graph package name cannot be empty".toString());
                }
            }
            if (this.graphResourceName.length() > 0) {
                z = true;
            }
            if (z) {
                dynamicIncludeNavGraph.setGraphResourceName(this.graphResourceName);
                return dynamicIncludeNavGraph;
            }
            throw new IllegalStateException("Graph resource name cannot be empty".toString());
        }
        throw new IllegalStateException("Module name cannot be empty".toString());
    }
}
