package com.wbmd.wbmdcommons.caching;

import com.wbmd.wbmdcommons.caching.CacheProvider;
import java.io.File;

public interface ICacheProvider {
    void clear();

    CacheProvider.Entry get(String str, boolean z);

    void initialize(File file, int i);

    void put(CacheProvider.Entry entry);

    void removeKey(String str);
}
