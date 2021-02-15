package coil.map;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u0013\u0012\t\u0012\u00070\u0002¢\u0006\u0002\b\u0003\u0012\u0004\u0012\u00020\u00040\u0001B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0012\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u0002H\u0016J\u0012\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcoil/map/ResourceIntMapper;", "Lcoil/map/Mapper;", "", "Landroidx/annotation/DrawableRes;", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "handles", "", "data", "map", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ResourceIntMapper.kt */
public final class ResourceIntMapper implements Mapper<Integer, Uri> {
    private final Context context;

    public ResourceIntMapper(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    public /* bridge */ /* synthetic */ boolean handles(Object obj) {
        return handles(((Number) obj).intValue());
    }

    public /* bridge */ /* synthetic */ Object map(Object obj) {
        return map(((Number) obj).intValue());
    }

    public boolean handles(int i) {
        try {
            return this.context.getResources().getResourceEntryName(i) != null;
        } catch (Resources.NotFoundException unused) {
            return false;
        }
    }

    public Uri map(int i) {
        Uri parse = Uri.parse("android.resource://" + this.context.getPackageName() + '/' + i);
        Intrinsics.checkExpressionValueIsNotNull(parse, "Uri.parse(this)");
        return parse;
    }
}
