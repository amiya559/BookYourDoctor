package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class MyAppIntro extends AppIntro {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_my_app_intro);


        // First Slider
        addSlide(AppIntroFragment.newInstance("", "Doctor, add your details to give services through online now."
                , R.drawable.appintro_adddetails, ContextCompat.getColor(getApplicationContext(), R.color.appintrocolor)));

        // Second Slider
        addSlide(AppIntroFragment.newInstance("", "View doctor's profile and choose wisely."
                , R.drawable.appintro_view_profile, ContextCompat.getColor(getApplicationContext(),R.color.appintrocolor)));

        // Third Slider
        addSlide(AppIntroFragment.newInstance("", "Search for right doctors from your nearby clinics."
                , R.drawable.appintro_search_doctor, ContextCompat.getColor(getApplicationContext(), R.color.appintrocolor)));

        // Delete Slider
        addSlide(AppIntroFragment.newInstance("", "Book an appointment with the right doctor."
              , R.drawable.appintro_appointment_book, ContextCompat.getColor(getApplicationContext(),R.color.appintrocolor)));

        setFadeAnimation();


        sharedPreferences = getApplicationContext().getSharedPreferences("MyPreference", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences != null){
            boolean checkShared = sharedPreferences.getBoolean("checkStated",false);

            if (checkShared == true){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        }

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        editor.putBoolean("checkStated",false).commit();
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        //editor.putBoolean("checkStated",true).commit();
        finish();
    }
}
