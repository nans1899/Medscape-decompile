package net.media.android.bidder.base.models.internal;

import com.mnet.gson.e;
import com.mnet.gson.v;
import com.mnet.gson.w;
import mnetinternal.g;
import net.media.android.bidder.base.models.internal.AdDetails;
import net.media.android.bidder.base.models.internal.AdImpression;
import net.media.android.bidder.base.models.internal.AdResponse;
import net.media.android.bidder.base.models.internal.AdSource;
import net.media.android.bidder.base.models.internal.BidDetails;
import net.media.android.bidder.base.models.internal.BidRequest;
import net.media.android.bidder.base.models.internal.BidResponse;
import net.media.android.bidder.base.models.internal.Bidder;
import net.media.android.bidder.base.models.internal.DeviceInfo;
import net.media.android.bidder.base.models.internal.FireLogsInfo;
import net.media.android.bidder.base.models.internal.GeoLocation;
import net.media.android.bidder.base.models.internal.HostAppContext;
import net.media.android.bidder.base.models.internal.HostAppInfo;
import net.media.android.bidder.base.models.internal.Publisher;
import net.media.android.bidder.base.models.internal.VideoImpression;

public final class StagFactory implements w {
    public <T> v<T> create(e eVar, g<T> gVar) {
        Class<? super T> a = gVar.a();
        if (a == Publisher.class) {
            return new Publisher.TypeAdapter(eVar);
        }
        if (a == HostAppInfo.class) {
            return new HostAppInfo.TypeAdapter(eVar);
        }
        if (a == BidDetails.class) {
            return new BidDetails.TypeAdapter(eVar);
        }
        if (a == a.class) {
            return new AdFormat$TypeAdapter(eVar);
        }
        if (a == BidRequest.class) {
            return new BidRequest.TypeAdapter(eVar);
        }
        if (a == HostAppContext.class) {
            return new HostAppContext.TypeAdapter(eVar);
        }
        if (a == Bidder.class) {
            return new Bidder.TypeAdapter(eVar);
        }
        if (a == GeoLocation.class) {
            return new GeoLocation.TypeAdapter(eVar);
        }
        if (a == AdResponse.class) {
            return new AdResponse.TypeAdapter(eVar);
        }
        if (a == b.class) {
            return new BannerImpression$TypeAdapter(eVar);
        }
        if (a == AdDetails.class) {
            return new AdDetails.TypeAdapter(eVar);
        }
        if (a == VideoImpression.class) {
            return new VideoImpression.TypeAdapter(eVar);
        }
        if (a == c.class) {
            return new Regulations$TypeAdapter(eVar);
        }
        if (a == HostAppContext.CrawlerLink.class) {
            return new HostAppContext.CrawlerLink.TypeAdapter(eVar);
        }
        if (a == DeviceInfo.class) {
            return new DeviceInfo.TypeAdapter(eVar);
        }
        if (a == BidResponse.class) {
            return new BidResponse.TypeAdapter(eVar);
        }
        if (a == FireLogsInfo.class) {
            return new FireLogsInfo.TypeAdapter(eVar);
        }
        if (a == AdSource.class) {
            return new AdSource.TypeAdapter(eVar);
        }
        if (a == AdImpression.class) {
            return new AdImpression.TypeAdapter(eVar);
        }
        return null;
    }
}
