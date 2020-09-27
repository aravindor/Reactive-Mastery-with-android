package org.lifeautomation.reactivemastery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.lifeautomation.reactivemastery.ccvalidation.CCValidationActivity;
import org.lifeautomation.reactivemastery.combinedobservable.CombinedObservableActivity;
import org.lifeautomation.reactivemastery.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.problem1.setOnClickListener(this);
        binding.problem2.setOnClickListener(this);
        binding.problem3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.problem1:
                startActivity(new Intent(this,EditTextSearch.class));
                break;
            case R.id.problem2:
                startActivity(new Intent(this, CombinedObservableActivity.class));
                break;
            case R.id.problem3:
                startActivity(new Intent(this, CCValidationActivity.class));
                break;
        }
    }
}