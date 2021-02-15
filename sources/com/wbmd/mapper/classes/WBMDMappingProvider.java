package com.wbmd.mapper.classes;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/wbmd/mapper/classes/WBMDMappingProvider;", "", "fileName", "", "type", "Lcom/wbmd/mapper/classes/WBMDMappingType;", "(Ljava/lang/String;Lcom/wbmd/mapper/classes/WBMDMappingType;)V", "getFileName", "()Ljava/lang/String;", "getType", "()Lcom/wbmd/mapper/classes/WBMDMappingType;", "wbmdmapper_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: WBMDMappingProvider.kt */
public final class WBMDMappingProvider {
    private final String fileName;
    private final WBMDMappingType type;

    public WBMDMappingProvider(String str, WBMDMappingType wBMDMappingType) {
        Intrinsics.checkNotNullParameter(str, "fileName");
        Intrinsics.checkNotNullParameter(wBMDMappingType, "type");
        this.fileName = str;
        this.type = wBMDMappingType;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final WBMDMappingType getType() {
        return this.type;
    }
}
