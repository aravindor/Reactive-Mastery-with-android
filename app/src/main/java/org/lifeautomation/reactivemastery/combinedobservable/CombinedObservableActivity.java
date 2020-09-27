package org.lifeautomation.reactivemastery.combinedobservable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.lifeautomation.reactivemastery.AppConstants;
import org.lifeautomation.reactivemastery.R;
import org.lifeautomation.reactivemastery.combinedobservable.api.RssClient;
import org.lifeautomation.reactivemastery.combinedobservable.models.FeedItem;
import org.lifeautomation.reactivemastery.combinedobservable.models.RSSEntry;
import org.lifeautomation.reactivemastery.utils.RetrofitSingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CombinedObservableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combined_observable);


        Observable<RSSEntry> googleRssObservable = RetrofitSingleton.getInstance().createService(RssClient.class).getFeed("https://news.google.com/?output=atom989xcd");
        Observable<RSSEntry> registerRssObservable = RetrofitSingleton.getInstance().createService(RssClient.class).getFeed("http://www.theregister.co.uk/software/headlines.atom");
        Observable<List<FeedItem>> combinedRss = Observable.combineLatest(googleRssObservable,registerRssObservable,(googleRssEntry, registerRssEntry) -> {
            List<FeedItem> feedItems = new ArrayList<>();
            feedItems.addAll(googleRssEntry.getItems());
            feedItems.addAll(registerRssEntry.getItems());
            return feedItems;
        });

        combinedRss.observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::sortList)
                .retry(3)
                .subscribe(this::handleResponse,this::handleError);

    }

    private List<FeedItem> sortList(List<FeedItem> feedItems) {
        Collections.sort(feedItems);
        return  feedItems;
    }

    private void handleError(Throwable throwable) {
    }

    private void handleResponse(List<FeedItem> feedItems) {
        Log.d(AppConstants.TAG, "handleResponse: "+feedItems);
    }
}