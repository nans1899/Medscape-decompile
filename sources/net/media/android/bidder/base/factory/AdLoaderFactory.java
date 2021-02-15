package net.media.android.bidder.base.factory;

import java.util.ArrayList;
import java.util.List;
import mnetinternal.cm;
import mnetinternal.cn;
import mnetinternal.co;
import mnetinternal.cp;
import mnetinternal.cq;
import mnetinternal.cr;
import mnetinternal.cs;
import net.media.android.bidder.base.adloader.AdLoader;
import net.media.android.bidder.base.adloader.c;

public final class AdLoaderFactory {
    private static AdLoaderFactory sInstance;
    private AdLoader mAdLoader = new c(this.mTransformers);
    private List<cs> mTransformers;

    private AdLoaderFactory() {
        initTransformers();
    }

    private void initTransformers() {
        ArrayList arrayList = new ArrayList();
        this.mTransformers = arrayList;
        arrayList.add(new cq());
        this.mTransformers.add(new co());
        this.mTransformers.add(new cm());
        this.mTransformers.add(new cp());
        this.mTransformers.add(new cr());
        this.mTransformers.add(new cn());
    }

    public static AdLoader getAdLoader() {
        if (sInstance == null) {
            sInstance = new AdLoaderFactory();
        }
        return sInstance.mAdLoader;
    }
}
