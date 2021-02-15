package coil.map;

import android.net.Uri;
import androidx.core.net.UriKt;
import coil.fetch.AssetUriFetcher;
import coil.util.Extensions;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcoil/map/FileUriMapper;", "Lcoil/map/Mapper;", "Landroid/net/Uri;", "Ljava/io/File;", "()V", "handles", "", "data", "map", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: FileUriMapper.kt */
public final class FileUriMapper implements Mapper<Uri, File> {
    public boolean handles(Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "data");
        if (!Intrinsics.areEqual((Object) uri.getScheme(), (Object) "file")) {
            return false;
        }
        String firstPathSegment = Extensions.getFirstPathSegment(uri);
        return firstPathSegment != null && (Intrinsics.areEqual((Object) firstPathSegment, (Object) AssetUriFetcher.ASSET_FILE_PATH_ROOT) ^ true);
    }

    public File map(Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "data");
        return UriKt.toFile(uri);
    }
}
