package com.krishana.androidhackathontemplates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView lottieAnimationView1,lottieAnimationView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //bg = findViewById(R.id.imageView5);
        lottieAnimationView1 = findViewById(R.id.lottieAnimationView1);
        lottieAnimationView2 = findViewById(R.id.lottieAnimationView2);
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                Intent i = new Intent(SplashScreen.this, LogInActivity.class);
                startActivity(i);
                finish();
            }
        }, 4000);
    }
}