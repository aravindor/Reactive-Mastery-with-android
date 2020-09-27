package org.lifeautomation.reactivemastery.ccvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.jakewharton.rxbinding4.widget.RxTextView;

import org.lifeautomation.reactivemastery.AppConstants;
import org.lifeautomation.reactivemastery.R;
import org.lifeautomation.reactivemastery.ccvalidation.utils.CardType;
import org.lifeautomation.reactivemastery.ccvalidation.utils.ValidationUtils;
import org.lifeautomation.reactivemastery.databinding.ActivityCCValidationBinding;

import io.reactivex.rxjava3.core.Observable;

public class CCValidationActivity extends AppCompatActivity {

    ActivityCCValidationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCCValidationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Observable<String> expirationDateObservable = RxTextView.textChanges(binding.expiry).map(CharSequence::toString);
        Observable<Boolean> isExpirationDateValid = expirationDateObservable.map(ValidationUtils::checkExpirationDate);

        Observable<String> creditCardNumberObservable = RxTextView.textChanges(binding.cardNo).map(CharSequence::toString);
        Observable<CardType> cardTypeObservable = creditCardNumberObservable.map(CardType::fromString);
        Observable<Boolean> isCardTypeValid = cardTypeObservable.map(cardType -> cardType!=CardType.UNKNOWN);

        creditCardNumberObservable.subscribe(s -> Log.d(AppConstants.TAG, s));
        cardTypeObservable.subscribe(cardType -> Log.d(AppConstants.TAG, cardType.toString()));
        isCardTypeValid.subscribe(aBoolean -> Log.d(AppConstants.TAG, "is card no valid:"+aBoolean));

        expirationDateObservable.subscribe(s -> Log.d(AppConstants.TAG, s));
        isExpirationDateValid.subscribe(aBoolean -> Log.d(AppConstants.TAG, "is expiry valid: "+aBoolean));

    }
}