package com.sugiartha.juniorandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    ImageView gifImageView;
    int value;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progressbar);
        textView = findViewById(R.id.progresstext);
        gifImageView = findViewById(R.id.gif);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                startProgress();
            }
        });
        thread.start();
    }

    public void startProgress() {
        for (value = 0; value < 100; value++) {
            try {
                Thread.sleep(25);
                progressBar.setProgress(value);
                moveGif();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(String.valueOf(value + "%"));
                }
            });
        }
        run();
    }

    public void moveGif() {
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) gifImageView.getLayoutParams();
        float progressBarWidth = progressBar.getWidth();
        float progress = progressBar.getProgress();
        float maxProgress = progressBar.getMax();

        float distance = progressBarWidth * (progress / maxProgress);
        float targetX = distance - (gifImageView.getWidth() / 2);

        Animation animation = new TranslateAnimation(targetX, targetX, 0, 0);
        animation.setFillAfter(true);
        animation.setDuration(25);
        gifImageView.startAnimation(animation);
    }

    public void run() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
