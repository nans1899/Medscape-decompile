package net.media.android.bidder.base.models.internal;

import com.dd.plist.ASCIIPropertyListParser;
import com.mnet.gson.n;
import mnetinternal.c;

public final class Config {
    @c(a = "data")
    private n mConfigs;

    public n getAllConfigs() {
        return this.mConfigs;
    }

    public String toString() {
        return "Config{mConfigs=" + this.mConfigs + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
