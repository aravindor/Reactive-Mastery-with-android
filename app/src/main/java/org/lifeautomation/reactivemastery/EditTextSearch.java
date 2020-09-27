package org.lifeautomation.reactivemastery;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding4.widget.RxTextView;

import org.lifeautomation.reactivemastery.databinding.ActivityEditTextSearchBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class EditTextSearch extends AppCompatActivity {

    Observable<String> textObservable;
    ActivityEditTextSearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditTextSearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textObservable = RxTextView.textChanges(binding.searchBox)
                .map(CharSequence::toString)
                .filter(text -> text.length() >= 3)
                .debounce(250, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());

        textObservable.subscribe(this::updateSearchResult);
    }

    private void updateSearchResult(String s) {
        Log.d(AppConstants.TAG, "updateSearchResult: " + s);
    }
}