package coil.decode;

import coil.bitmappool.BitmapPool;
import coil.size.Size;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import okio.BufferedSource;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J1\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\fJ\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H&\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lcoil/decode/Decoder;", "", "decode", "Lcoil/decode/DecodeResult;", "pool", "Lcoil/bitmappool/BitmapPool;", "source", "Lokio/BufferedSource;", "size", "Lcoil/size/Size;", "options", "Lcoil/decode/Options;", "(Lcoil/bitmappool/BitmapPool;Lokio/BufferedSource;Lcoil/size/Size;Lcoil/decode/Options;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handles", "", "mimeType", "", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Decoder.kt */
public interface Decoder {
    Object decode(BitmapPool bitmapPool, BufferedSource bufferedSource, Size size, Options options, Continuation<? super DecodeResult> continuation);

    boolean handles(BufferedSource bufferedSource, String str);
}
