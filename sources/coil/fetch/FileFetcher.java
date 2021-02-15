package coil.fetch;

import android.webkit.MimeTypeMap;
import coil.bitmappool.BitmapPool;
import coil.decode.DataSource;
import coil.decode.Options;
import coil.fetch.Fetcher;
import coil.size.Size;
import com.dd.plist.ASCIIPropertyListParser;
import java.io.File;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Okio;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J1\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\u0002H\u0016\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lcoil/fetch/FileFetcher;", "Lcoil/fetch/Fetcher;", "Ljava/io/File;", "()V", "fetch", "Lcoil/fetch/FetchResult;", "pool", "Lcoil/bitmappool/BitmapPool;", "data", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "(Lcoil/bitmappool/BitmapPool;Ljava/io/File;Lcoil/size/Size;Lcoil/decode/Options;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "key", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: FileFetcher.kt */
public final class FileFetcher implements Fetcher<File> {
    public boolean handles(File file) {
        Intrinsics.checkParameterIsNotNull(file, "data");
        return Fetcher.DefaultImpls.handles(this, file);
    }

    public String key(File file) {
        Intrinsics.checkParameterIsNotNull(file, "data");
        return file.getPath() + ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER + file.lastModified();
    }

    public Object fetch(BitmapPool bitmapPool, File file, Size size, Options options, Continuation<? super FetchResult> continuation) {
        return new SourceResult(Okio.buffer(Okio.source(file)), MimeTypeMap.getSingleton().getMimeTypeFromExtension(FilesKt.getExtension(file)), DataSource.DISK);
    }
}
