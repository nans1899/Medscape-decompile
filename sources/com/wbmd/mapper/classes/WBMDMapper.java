package com.wbmd.mapper.classes;

import android.content.Context;
import android.content.res.Resources;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.wbmd.mapper.R;
import com.wbmd.mapper.model.AssociatedItemPair;
import com.wbmd.mapper.model.GroupItem;
import com.wbmd.mapper.model.MappedItem;
import com.wbmd.mapper.model.MappedItemPair;
import com.wbmd.mapper.model.MappedResponse;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.EnumMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u001c\u0010\u0011\u001a\u00020\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u000f\u001a\u00020\u0010J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0005J\u0018\u0010\u0017\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J \u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010 \u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b0\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/wbmd/mapper/classes/WBMDMapper;", "", "()V", "associatedGroups", "Ljava/util/EnumMap;", "Lcom/wbmd/mapper/classes/WBMDMappingType;", "Lcom/wbmd/mapper/model/AssociatedItemPair;", "mappings", "", "Lcom/wbmd/mapper/model/MappedItemPair;", "Lcom/wbmd/mapper/model/MappedItem;", "addMappingProvider", "", "mappingProvider", "Lcom/wbmd/mapper/classes/WBMDMappingProvider;", "context", "Landroid/content/Context;", "addMappingProviders", "mappingProviders", "getAffiliateDirectMapping", "webMDId", "", "type", "getAffiliateMapping", "getWebMDMapping", "affiliateId", "isValidMappingType", "", "wbmdMappingType", "parseData", "Lcom/wbmd/mapper/model/MappedResponse;", "fileName", "parseMapping", "Companion", "wbmdmapper_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: WBMDMapper.kt */
public final class WBMDMapper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static WBMDMapper instance;
    private final EnumMap<WBMDMappingType, AssociatedItemPair> associatedGroups = new EnumMap<>(WBMDMappingType.class);
    private final EnumMap<WBMDMappingType, List<MappedItemPair<MappedItem>>> mappings = new EnumMap<>(WBMDMappingType.class);

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 0})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WBMDMappingType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[WBMDMappingType.PROFESSION.ordinal()] = 1;
            $EnumSwitchMapping$0[WBMDMappingType.SPECIALTY.ordinal()] = 2;
            $EnumSwitchMapping$0[WBMDMappingType.LOCATION.ordinal()] = 3;
        }
    }

    public static final synchronized WBMDMapper getInstance() {
        WBMDMapper instance2;
        synchronized (WBMDMapper.class) {
            instance2 = Companion.getInstance();
        }
        return instance2;
    }

    private static final void setInstance(WBMDMapper wBMDMapper) {
        instance = wBMDMapper;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u000b\u001a\u0002H\f\"\u0006\b\u0000\u0010\f\u0018\u00012\u0006\u0010\r\u001a\u00020\u000eH\b¢\u0006\u0002\u0010\u000fR0\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00048F@BX\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0010"}, d2 = {"Lcom/wbmd/mapper/classes/WBMDMapper$Companion;", "", "()V", "<set-?>", "Lcom/wbmd/mapper/classes/WBMDMapper;", "instance", "getInstance$annotations", "getInstance", "()Lcom/wbmd/mapper/classes/WBMDMapper;", "setInstance", "(Lcom/wbmd/mapper/classes/WBMDMapper;)V", "fromJson", "T", "json", "", "(Ljava/lang/String;)Ljava/lang/Object;", "wbmdmapper_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: WBMDMapper.kt */
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* access modifiers changed from: private */
        public final void setInstance(WBMDMapper wBMDMapper) {
            WBMDMapper.instance = wBMDMapper;
        }

        public final synchronized WBMDMapper getInstance() {
            if (WBMDMapper.instance == null) {
                WBMDMapper.instance = new WBMDMapper();
            }
            return WBMDMapper.instance;
        }

        public final /* synthetic */ <T> T fromJson(String str) {
            Intrinsics.checkNotNullParameter(str, "json");
            Gson gson = new Gson();
            Intrinsics.needClassReification();
            return gson.fromJson(str, new WBMDMapper$Companion$fromJson$1().getType());
        }
    }

    public final void addMappingProviders(List<WBMDMappingProvider> list, Context context) {
        Intrinsics.checkNotNullParameter(list, "mappingProviders");
        Intrinsics.checkNotNullParameter(context, "context");
        for (WBMDMappingProvider addMappingProvider : list) {
            try {
                addMappingProvider(addMappingProvider, context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public final void addMappingProvider(WBMDMappingProvider wBMDMappingProvider, Context context) {
        Intrinsics.checkNotNullParameter(wBMDMappingProvider, "mappingProvider");
        Intrinsics.checkNotNullParameter(context, "context");
        String fileName = wBMDMappingProvider.getFileName();
        if (!StringsKt.endsWith$default(fileName, ".json", false, 2, (Object) null)) {
            throw new Exception(context.getString(R.string.resource_not_found, new Object[]{wBMDMappingProvider.getFileName()}));
        } else if (isValidMappingType(wBMDMappingProvider.getType())) {
            parseMapping(wBMDMappingProvider.getType(), fileName, context);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0056, code lost:
        r6 = r6.getData();
     */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void parseMapping(com.wbmd.mapper.classes.WBMDMappingType r5, java.lang.String r6, android.content.Context r7) {
        /*
            r4 = this;
            com.wbmd.mapper.model.MappedResponse r6 = r4.parseData(r6, r7)
            if (r6 == 0) goto L_0x0050
            com.wbmd.mapper.model.MappedDataObject r0 = r6.getData()
            if (r0 == 0) goto L_0x0050
            java.util.List r0 = r0.getDirectMappings()
            if (r0 == 0) goto L_0x0050
            java.util.Iterator r1 = r0.iterator()
        L_0x0016:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0049
            java.lang.Object r2 = r1.next()
            com.wbmd.mapper.model.MappedItemPair r2 = (com.wbmd.mapper.model.MappedItemPair) r2
            java.lang.Object r3 = r2.getAffiliateItem()
            com.wbmd.mapper.model.MappedItem r3 = (com.wbmd.mapper.model.MappedItem) r3
            java.lang.String r3 = r3.getId()
            if (r3 == 0) goto L_0x003b
            java.lang.Object r2 = r2.getWebmdItem()
            com.wbmd.mapper.model.MappedItem r2 = (com.wbmd.mapper.model.MappedItem) r2
            java.lang.String r2 = r2.getId()
            if (r2 == 0) goto L_0x003b
            goto L_0x0016
        L_0x003b:
            java.lang.Exception r5 = new java.lang.Exception
            int r6 = com.wbmd.mapper.R.string.parsing_error
            java.lang.String r6 = r7.getString(r6)
            r5.<init>(r6)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            throw r5
        L_0x0049:
            java.util.EnumMap<com.wbmd.mapper.classes.WBMDMappingType, java.util.List<com.wbmd.mapper.model.MappedItemPair<com.wbmd.mapper.model.MappedItem>>> r7 = r4.mappings
            java.util.Map r7 = (java.util.Map) r7
            r7.put(r5, r0)
        L_0x0050:
            java.util.EnumMap<com.wbmd.mapper.classes.WBMDMappingType, com.wbmd.mapper.model.AssociatedItemPair> r7 = r4.associatedGroups
            java.util.Map r7 = (java.util.Map) r7
            if (r6 == 0) goto L_0x0061
            com.wbmd.mapper.model.MappedDataObject r6 = r6.getData()
            if (r6 == 0) goto L_0x0061
            com.wbmd.mapper.model.AssociatedItemPair r6 = r6.getAssociatedItemPair()
            goto L_0x0062
        L_0x0061:
            r6 = 0
        L_0x0062:
            r7.put(r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.mapper.classes.WBMDMapper.parseMapping(com.wbmd.mapper.classes.WBMDMappingType, java.lang.String, android.content.Context):void");
    }

    private final MappedResponse<MappedItem> parseData(String str, Context context) {
        Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
        InputStream open = resources.getAssets().open(str);
        Intrinsics.checkNotNullExpressionValue(open, "context.resources.assets.open(fileName)");
        JsonElement parse = new JsonParser().parse((Reader) new InputStreamReader(open));
        Intrinsics.checkNotNullExpressionValue(parse, "JsonParser().parse(InputStreamReader(inputStream))");
        String jSONObject = new JSONObject(parse.getAsJsonObject().toString()).toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "jsonObject.toString()");
        return (MappedResponse) new Gson().fromJson(jSONObject, new WBMDMapper$parseData$$inlined$fromJson$1().getType());
    }

    private final boolean isValidMappingType(WBMDMappingType wBMDMappingType) {
        int i = WhenMappings.$EnumSwitchMapping$0[wBMDMappingType.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return true;
        }
        throw new NoWhenBranchMatchedException();
    }

    public final MappedItem getWebMDMapping(String str, WBMDMappingType wBMDMappingType) {
        Intrinsics.checkNotNullParameter(str, "affiliateId");
        Intrinsics.checkNotNullParameter(wBMDMappingType, "type");
        List<MappedItemPair> list = this.mappings.get(wBMDMappingType);
        if (list != null) {
            for (MappedItemPair mappedItemPair : list) {
                MappedItem mappedItem = (MappedItem) mappedItemPair.getAffiliateItem();
                if (Intrinsics.areEqual((Object) mappedItem != null ? mappedItem.getId() : null, (Object) str)) {
                    return (MappedItem) mappedItemPair.getWebmdItem();
                }
            }
        }
        return null;
    }

    public final MappedItem getAffiliateMapping(String str, WBMDMappingType wBMDMappingType) {
        List<String> associatedIds;
        Intrinsics.checkNotNullParameter(str, "webMDId");
        Intrinsics.checkNotNullParameter(wBMDMappingType, "type");
        MappedItem affiliateDirectMapping = getAffiliateDirectMapping(str, wBMDMappingType);
        if (affiliateDirectMapping == null) {
            AssociatedItemPair associatedItemPair = this.associatedGroups.get(wBMDMappingType);
            List<GroupItem> webmdGroups = associatedItemPair != null ? associatedItemPair.getWebmdGroups() : null;
            if (webmdGroups != null) {
                loop0:
                for (GroupItem next : webmdGroups) {
                    List<String> allIds = next.getAllIds();
                    if (!(allIds == null || !allIds.contains(str) || (associatedIds = next.getAssociatedIds()) == null)) {
                        for (String affiliateDirectMapping2 : associatedIds) {
                            affiliateDirectMapping = getAffiliateDirectMapping(affiliateDirectMapping2, wBMDMappingType);
                            if (affiliateDirectMapping != null) {
                                break loop0;
                            }
                        }
                        continue;
                    }
                }
            }
        }
        return affiliateDirectMapping;
    }

    public final MappedItem getAffiliateDirectMapping(String str, WBMDMappingType wBMDMappingType) {
        Intrinsics.checkNotNullParameter(wBMDMappingType, "type");
        List<MappedItemPair> list = this.mappings.get(wBMDMappingType);
        MappedItem mappedItem = null;
        if (list != null) {
            for (MappedItemPair mappedItemPair : list) {
                if (Intrinsics.areEqual((Object) ((MappedItem) mappedItemPair.getWebmdItem()).getId(), (Object) str)) {
                    return (MappedItem) mappedItemPair.getAffiliateItem();
                }
            }
        }
        return null;
    }
}
