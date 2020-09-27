package org.lifeautomation.reactivemastery.combinedobservable.api;

import org.lifeautomation.reactivemastery.combinedobservable.models.RSSEntry;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RssClient {
    @GET("api.json")
    Observable<RSSEntry> getFeed(@Query("rss_url") String provider);
}
