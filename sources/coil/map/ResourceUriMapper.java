package coil.map;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0016J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcoil/map/ResourceUriMapper;", "Lcoil/map/Mapper;", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "handles", "", "data", "map", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ResourceUriMapper.kt */
public final class ResourceUriMapper implements Mapper<Uri, Uri> {
    private final Context context;

    public ResourceUriMapper(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    public boolean handles(Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "data");
        if (Intrinsics.areEqual((Object) uri.getScheme(), (Object) "android.resource")) {
            CharSequence authority = uri.getAuthority();
            if (!(authority == null || StringsKt.isBlank(authority))) {
                List<String> pathSegments = uri.getPathSegments();
                Intrinsics.checkExpressionValueIsNotNull(pathSegments, "data.pathSegments");
                if (pathSegments.size() == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public Uri map(Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "data");
        String authority = uri.getAuthority();
        if (authority == null) {
            authority = "";
        }
        Resources resourcesForApplication = this.context.getPackageManager().getResourcesForApplication(authority);
        List<String> pathSegments = uri.getPathSegments();
        boolean z = false;
        int identifier = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
        if (identifier != 0) {
            z = true;
        }
        if (z) {
            Uri parse = Uri.parse("android.resource://" + authority + '/' + identifier);
            Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(this)");
            return parse;
        }
        throw new IllegalStateException(("Invalid android.resource URI: " + uri).toString());
    }
}
