package coil;

import coil.decode.Decoder;
import coil.fetch.Fetcher;
import coil.map.Mapper;
import coil.map.MeasuredMapper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001%B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0002\b\u0002\u0012P\u0010\u0003\u001aL\u0012$\u0012\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\u00070\u00050\u0004j\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\u0007`\b\u0012P\u0010\t\u001aL\u0012$\u0012\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\n0\u00050\u0004j\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\n`\b\u0012H\u0010\u000b\u001aD\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f0\u00050\u0004j\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f`\b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0004¢\u0006\u0002\u0010\u000fJ)\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0016\u0012\u0002\b\u0003\u0018\u00010\u0007\"\b\b\u0000\u0010\u0016*\u00020\u00012\u0006\u0010\u0017\u001a\u0002H\u0016¢\u0006\u0002\u0010\u0018J)\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0016\u0012\u0002\b\u0003\u0018\u00010\n\"\b\b\u0000\u0010\u0016*\u00020\u00012\u0006\u0010\u0017\u001a\u0002H\u0016¢\u0006\u0002\u0010\u001aJ\u0006\u0010\u001b\u001a\u00020\u001cJ/\u0010\u001d\u001a\u00020\u000e\"\b\b\u0000\u0010\u0016*\u00020\u00012\u0006\u0010\u0017\u001a\u0002H\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!¢\u0006\u0002\u0010\"J#\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00160\f\"\b\b\u0000\u0010\u0016*\u00020\u00012\u0006\u0010\u0017\u001a\u0002H\u0016¢\u0006\u0002\u0010$R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011RV\u0010\u000b\u001aD\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f0\u00050\u0004j\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f`\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R^\u0010\u0003\u001aL\u0012$\u0012\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\u00070\u00050\u0004j\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\u0007`\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R^\u0010\t\u001aL\u0012$\u0012\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\n0\u00050\u0004j\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0006\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\n`\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011¨\u0006&"}, d2 = {"Lcoil/ComponentRegistry;", "", "()V", "mappers", "", "Lkotlin/Pair;", "Ljava/lang/Class;", "Lcoil/map/Mapper;", "Lcoil/util/MultiList;", "measuredMappers", "Lcoil/map/MeasuredMapper;", "fetchers", "Lcoil/fetch/Fetcher;", "decoders", "Lcoil/decode/Decoder;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getDecoders$coil_base_release", "()Ljava/util/List;", "getFetchers$coil_base_release", "getMappers$coil_base_release", "getMeasuredMappers$coil_base_release", "getMapper", "T", "data", "(Ljava/lang/Object;)Lcoil/map/Mapper;", "getMeasuredMapper", "(Ljava/lang/Object;)Lcoil/map/MeasuredMapper;", "newBuilder", "Lcoil/ComponentRegistry$Builder;", "requireDecoder", "source", "Lokio/BufferedSource;", "mimeType", "", "(Ljava/lang/Object;Lokio/BufferedSource;Ljava/lang/String;)Lcoil/decode/Decoder;", "requireFetcher", "(Ljava/lang/Object;)Lcoil/fetch/Fetcher;", "Builder", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ComponentRegistry.kt */
public final class ComponentRegistry {
    private final List<Decoder> decoders;
    private final List<Pair<Class<? extends Object>, Fetcher<? extends Object>>> fetchers;
    private final List<Pair<Class<? extends Object>, Mapper<? extends Object, ?>>> mappers;
    private final List<Pair<Class<? extends Object>, MeasuredMapper<? extends Object, ?>>> measuredMappers;

    private ComponentRegistry(List<? extends Pair<? extends Class<? extends Object>, ? extends Mapper<? extends Object, ?>>> list, List<? extends Pair<? extends Class<? extends Object>, ? extends MeasuredMapper<? extends Object, ?>>> list2, List<? extends Pair<? extends Class<? extends Object>, ? extends Fetcher<? extends Object>>> list3, List<? extends Decoder> list4) {
        this.mappers = list;
        this.measuredMappers = list2;
        this.fetchers = list3;
        this.decoders = list4;
    }

    public /* synthetic */ ComponentRegistry(List list, List list2, List list3, List list4, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, list3, list4);
    }

    public final List<Pair<Class<? extends Object>, Mapper<? extends Object, ?>>> getMappers$coil_base_release() {
        return this.mappers;
    }

    public final List<Pair<Class<? extends Object>, MeasuredMapper<? extends Object, ?>>> getMeasuredMappers$coil_base_release() {
        return this.measuredMappers;
    }

    public final List<Pair<Class<? extends Object>, Fetcher<? extends Object>>> getFetchers$coil_base_release() {
        return this.fetchers;
    }

    public final List<Decoder> getDecoders$coil_base_release() {
        return this.decoders;
    }

    public ComponentRegistry() {
        this(CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList());
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0046 A[LOOP:0: B:1:0x0010->B:13:0x0046, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004a A[EDGE_INSN: B:20:0x004a->B:15:0x004a ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> coil.map.Mapper<T, ?> getMapper(T r10) {
        /*
            r9 = this;
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            java.util.List<kotlin.Pair<java.lang.Class<? extends java.lang.Object>, coil.map.Mapper<? extends java.lang.Object, ?>>> r0 = r9.mappers
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            int r1 = r1.size()
            r2 = 0
            r3 = 0
        L_0x0010:
            r4 = 0
            if (r3 >= r1) goto L_0x0049
            java.lang.Object r5 = r0.get(r3)
            r6 = r5
            kotlin.Pair r6 = (kotlin.Pair) r6
            java.lang.Object r7 = r6.component1()
            java.lang.Class r7 = (java.lang.Class) r7
            java.lang.Object r6 = r6.component2()
            coil.map.Mapper r6 = (coil.map.Mapper) r6
            java.lang.Class r8 = r10.getClass()
            boolean r7 = r7.isAssignableFrom(r8)
            if (r7 == 0) goto L_0x0042
            if (r6 == 0) goto L_0x003a
            boolean r6 = r6.handles(r10)
            if (r6 == 0) goto L_0x0042
            r6 = 1
            goto L_0x0043
        L_0x003a:
            kotlin.TypeCastException r10 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type coil.map.Mapper<kotlin.Any, *>"
            r10.<init>(r0)
            throw r10
        L_0x0042:
            r6 = 0
        L_0x0043:
            if (r6 == 0) goto L_0x0046
            goto L_0x004a
        L_0x0046:
            int r3 = r3 + 1
            goto L_0x0010
        L_0x0049:
            r5 = r4
        L_0x004a:
            kotlin.Pair r5 = (kotlin.Pair) r5
            if (r5 == 0) goto L_0x0055
            java.lang.Object r10 = r5.getSecond()
            r4 = r10
            coil.map.Mapper r4 = (coil.map.Mapper) r4
        L_0x0055:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.ComponentRegistry.getMapper(java.lang.Object):coil.map.Mapper");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0046 A[LOOP:0: B:1:0x0010->B:13:0x0046, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004a A[EDGE_INSN: B:20:0x004a->B:15:0x004a ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> coil.map.MeasuredMapper<T, ?> getMeasuredMapper(T r10) {
        /*
            r9 = this;
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r0)
            java.util.List<kotlin.Pair<java.lang.Class<? extends java.lang.Object>, coil.map.MeasuredMapper<? extends java.lang.Object, ?>>> r0 = r9.measuredMappers
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            int r1 = r1.size()
            r2 = 0
            r3 = 0
        L_0x0010:
            r4 = 0
            if (r3 >= r1) goto L_0x0049
            java.lang.Object r5 = r0.get(r3)
            r6 = r5
            kotlin.Pair r6 = (kotlin.Pair) r6
            java.lang.Object r7 = r6.component1()
            java.lang.Class r7 = (java.lang.Class) r7
            java.lang.Object r6 = r6.component2()
            coil.map.MeasuredMapper r6 = (coil.map.MeasuredMapper) r6
            java.lang.Class r8 = r10.getClass()
            boolean r7 = r7.isAssignableFrom(r8)
            if (r7 == 0) goto L_0x0042
            if (r6 == 0) goto L_0x003a
            boolean r6 = r6.handles(r10)
            if (r6 == 0) goto L_0x0042
            r6 = 1
            goto L_0x0043
        L_0x003a:
            kotlin.TypeCastException r10 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type coil.map.MeasuredMapper<kotlin.Any, *>"
            r10.<init>(r0)
            throw r10
        L_0x0042:
            r6 = 0
        L_0x0043:
            if (r6 == 0) goto L_0x0046
            goto L_0x004a
        L_0x0046:
            int r3 = r3 + 1
            goto L_0x0010
        L_0x0049:
            r5 = r4
        L_0x004a:
            kotlin.Pair r5 = (kotlin.Pair) r5
            if (r5 == 0) goto L_0x0055
            java.lang.Object r10 = r5.getSecond()
            r4 = r10
            coil.map.MeasuredMapper r4 = (coil.map.MeasuredMapper) r4
        L_0x0055:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.ComponentRegistry.getMeasuredMapper(java.lang.Object):coil.map.MeasuredMapper");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045 A[LOOP:0: B:1:0x0010->B:12:0x0045, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0049 A[EDGE_INSN: B:25:0x0049->B:14:0x0049 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> coil.fetch.Fetcher<T> requireFetcher(T r9) {
        /*
            r8 = this;
            java.lang.String r0 = "data"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            java.util.List<kotlin.Pair<java.lang.Class<? extends java.lang.Object>, coil.fetch.Fetcher<? extends java.lang.Object>>> r0 = r8.fetchers
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            int r1 = r1.size()
            r2 = 0
            r3 = 0
        L_0x0010:
            if (r3 >= r1) goto L_0x0048
            java.lang.Object r4 = r0.get(r3)
            r5 = r4
            kotlin.Pair r5 = (kotlin.Pair) r5
            java.lang.Object r6 = r5.component1()
            java.lang.Class r6 = (java.lang.Class) r6
            java.lang.Object r5 = r5.component2()
            coil.fetch.Fetcher r5 = (coil.fetch.Fetcher) r5
            java.lang.Class r7 = r9.getClass()
            boolean r6 = r6.isAssignableFrom(r7)
            if (r6 == 0) goto L_0x0041
            if (r5 == 0) goto L_0x0039
            boolean r5 = r5.handles(r9)
            if (r5 == 0) goto L_0x0041
            r5 = 1
            goto L_0x0042
        L_0x0039:
            kotlin.TypeCastException r9 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type coil.fetch.Fetcher<kotlin.Any>"
            r9.<init>(r0)
            throw r9
        L_0x0041:
            r5 = 0
        L_0x0042:
            if (r5 == 0) goto L_0x0045
            goto L_0x0049
        L_0x0045:
            int r3 = r3 + 1
            goto L_0x0010
        L_0x0048:
            r4 = 0
        L_0x0049:
            kotlin.Pair r4 = (kotlin.Pair) r4
            if (r4 == 0) goto L_0x005e
            java.lang.Object r9 = r4.getSecond()
            if (r9 == 0) goto L_0x0056
            coil.fetch.Fetcher r9 = (coil.fetch.Fetcher) r9
            return r9
        L_0x0056:
            kotlin.TypeCastException r9 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type coil.fetch.Fetcher<T>"
            r9.<init>(r0)
            throw r9
        L_0x005e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Unable to fetch data. No fetcher supports: "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r9 = r9.toString()
            r0.<init>(r9)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.ComponentRegistry.requireFetcher(java.lang.Object):coil.fetch.Fetcher");
    }

    public final <T> Decoder requireDecoder(T t, BufferedSource bufferedSource, String str) {
        Decoder decoder;
        Intrinsics.checkParameterIsNotNull(t, "data");
        Intrinsics.checkParameterIsNotNull(bufferedSource, "source");
        List<Decoder> list = this.decoders;
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                decoder = null;
                break;
            }
            decoder = list.get(i);
            if (decoder.handles(bufferedSource, str)) {
                break;
            }
            i++;
        }
        Decoder decoder2 = decoder;
        if (decoder2 != null) {
            return decoder2;
        }
        throw new IllegalStateException(("Unable to decode data. No decoder supports: " + t).toString());
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\bJ#\u0010\u0012\u001a\u00020\u0000\"\n\b\u0000\u0010\u0014\u0018\u0001*\u00020\u00012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\fH\bJ'\u0010\u0012\u001a\u00020\u0000\"\n\b\u0000\u0010\u0014\u0018\u0001*\u00020\u00012\u0010\u0010\u0016\u001a\f\u0012\u0004\u0012\u0002H\u0014\u0012\u0002\b\u00030\u000fH\bJ'\u0010\u0012\u001a\u00020\u0000\"\n\b\u0000\u0010\u0014\u0018\u0001*\u00020\u00012\u0010\u0010\u0017\u001a\f\u0012\u0004\u0012\u0002H\u0014\u0012\u0002\b\u00030\u0011H\bJ.\u0010\u0012\u001a\u00020\u0000\"\b\b\u0000\u0010\u0014*\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\fH\u0001J2\u0010\u0012\u001a\u00020\u0000\"\b\b\u0000\u0010\u0014*\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u000b2\u0010\u0010\u0016\u001a\f\u0012\u0004\u0012\u0002H\u0014\u0012\u0002\b\u00030\u000fH\u0001J2\u0010\u0012\u001a\u00020\u0000\"\b\b\u0000\u0010\u0014*\u00020\u00012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00140\u000b2\u0010\u0010\u0017\u001a\f\u0012\u0004\u0012\u0002H\u0014\u0012\u0002\b\u00030\u0011H\u0001J\u0006\u0010\u0019\u001a\u00020\u0004R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000RP\u0010\t\u001aD\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f0\n0\u0007j\u001e\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\f`\rX\u0004¢\u0006\u0002\n\u0000RX\u0010\u000e\u001aL\u0012$\u0012\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\u000f0\n0\u0007j\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\u000f`\rX\u0004¢\u0006\u0002\n\u0000RX\u0010\u0010\u001aL\u0012$\u0012\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\u00110\n0\u0007j\"\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b\u0012\u0010\u0012\u000e\u0012\u0006\b\u0001\u0012\u00020\u0001\u0012\u0002\b\u00030\u0011`\rX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcoil/ComponentRegistry$Builder;", "", "()V", "registry", "Lcoil/ComponentRegistry;", "(Lcoil/ComponentRegistry;)V", "decoders", "", "Lcoil/decode/Decoder;", "fetchers", "Lkotlin/Pair;", "Ljava/lang/Class;", "Lcoil/fetch/Fetcher;", "Lcoil/util/MultiMutableList;", "mappers", "Lcoil/map/Mapper;", "measuredMappers", "Lcoil/map/MeasuredMapper;", "add", "decoder", "T", "fetcher", "mapper", "measuredMapper", "type", "build", "coil-base_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: ComponentRegistry.kt */
    public static final class Builder {
        private final List<Decoder> decoders;
        private final List<Pair<Class<? extends Object>, Fetcher<? extends Object>>> fetchers;
        private final List<Pair<Class<? extends Object>, Mapper<? extends Object, ?>>> mappers;
        private final List<Pair<Class<? extends Object>, MeasuredMapper<? extends Object, ?>>> measuredMappers;

        public Builder() {
            this.mappers = new ArrayList();
            this.measuredMappers = new ArrayList();
            this.fetchers = new ArrayList();
            this.decoders = new ArrayList();
        }

        public Builder(ComponentRegistry componentRegistry) {
            Intrinsics.checkParameterIsNotNull(componentRegistry, "registry");
            this.mappers = CollectionsKt.toMutableList(componentRegistry.getMappers$coil_base_release());
            this.measuredMappers = CollectionsKt.toMutableList(componentRegistry.getMeasuredMappers$coil_base_release());
            this.fetchers = CollectionsKt.toMutableList(componentRegistry.getFetchers$coil_base_release());
            this.decoders = CollectionsKt.toMutableList(componentRegistry.getDecoders$coil_base_release());
        }

        public final /* synthetic */ <T> Builder add(Mapper<T, ?> mapper) {
            Intrinsics.checkParameterIsNotNull(mapper, "mapper");
            Intrinsics.reifiedOperationMarker(4, "T");
            return add(Object.class, mapper);
        }

        public final <T> Builder add(Class<T> cls, Mapper<T, ?> mapper) {
            Intrinsics.checkParameterIsNotNull(cls, "type");
            Intrinsics.checkParameterIsNotNull(mapper, "mapper");
            Builder builder = this;
            builder.mappers.add(TuplesKt.to(cls, mapper));
            return builder;
        }

        public final /* synthetic */ <T> Builder add(MeasuredMapper<T, ?> measuredMapper) {
            Intrinsics.checkParameterIsNotNull(measuredMapper, "measuredMapper");
            Intrinsics.reifiedOperationMarker(4, "T");
            return add(Object.class, measuredMapper);
        }

        public final <T> Builder add(Class<T> cls, MeasuredMapper<T, ?> measuredMapper) {
            Intrinsics.checkParameterIsNotNull(cls, "type");
            Intrinsics.checkParameterIsNotNull(measuredMapper, "measuredMapper");
            Builder builder = this;
            builder.measuredMappers.add(TuplesKt.to(cls, measuredMapper));
            return builder;
        }

        public final /* synthetic */ <T> Builder add(Fetcher<T> fetcher) {
            Intrinsics.checkParameterIsNotNull(fetcher, "fetcher");
            Intrinsics.reifiedOperationMarker(4, "T");
            return add(Object.class, fetcher);
        }

        public final <T> Builder add(Class<T> cls, Fetcher<T> fetcher) {
            Intrinsics.checkParameterIsNotNull(cls, "type");
            Intrinsics.checkParameterIsNotNull(fetcher, "fetcher");
            Builder builder = this;
            builder.fetchers.add(TuplesKt.to(cls, fetcher));
            return builder;
        }

        public final Builder add(Decoder decoder) {
            Intrinsics.checkParameterIsNotNull(decoder, "decoder");
            Builder builder = this;
            builder.decoders.add(decoder);
            return builder;
        }

        public final ComponentRegistry build() {
            return new ComponentRegistry(CollectionsKt.toList(this.mappers), CollectionsKt.toList(this.measuredMappers), CollectionsKt.toList(this.fetchers), CollectionsKt.toList(this.decoders), (DefaultConstructorMarker) null);
        }
    }
}
