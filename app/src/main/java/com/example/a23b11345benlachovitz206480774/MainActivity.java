package com.example.a23b11345benlachovitz206480774;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private ShapeableImageView[][] main_IMG_rocks;
    private ShapeableImageView[] main_IMG_cars;
    private ShapeableImageView[] main_IMG_hearts;
    private ExtendedFloatingActionButton main_FAB_left;
    private ExtendedFloatingActionButton main_FAB_right;
    private GameManager gameManager;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameManager = new GameManager();
        findViews();
        initViews();
        moveObs();
        startTime();
    }

    private void startTime()
    {
        long startTime= TimeUnit.MILLISECONDS.toMillis(3000);
        timer = new CountDownTimer(startTime,600) {
            @Override
            public void onTick(long millisUntilFinished) {
                moveObs();
            }

            @Override
            public void onFinish() {
                restartTimer();
            }
        }.start();
    }
    private void restartTimer()
    {
        timer.cancel();
        startTime();
    }

    private void initViews()
    {
        main_FAB_left.setOnClickListener(v -> goLeft());
        main_FAB_right.setOnClickListener(v -> goRight());
    }

    private void goLeft()
    {
        int tempPos = gameManager.getCurrentPosition();
        if(tempPos!=0) {
            main_IMG_cars[tempPos-1].setVisibility(View.VISIBLE);
            main_IMG_cars[tempPos--].setVisibility(View.INVISIBLE);
            gameManager.setCurrentPosition(tempPos);
            crashCheck();
        }
    }

    private void goRight()
    {
        int tempPos = gameManager.getCurrentPosition();
        if(tempPos!=2) {
            main_IMG_cars[tempPos+1].setVisibility(View.VISIBLE);
            main_IMG_cars[tempPos++].setVisibility(View.INVISIBLE);
            gameManager.setCurrentPosition(tempPos);
            crashCheck();
        }
    }

    private void crashCheck()
    {
        if(main_IMG_rocks[5][gameManager.getCurrentPosition()].getVisibility()==View.VISIBLE)
            lifeLoss();
    }

    private void moveObs()
    {
        for(int i=5;i>0;i--)
        {
            for(int j=2;j>=0;j--)
            {
                main_IMG_rocks[i][j].setVisibility(View.INVISIBLE);
                if(main_IMG_rocks[i-1][j].getVisibility()==View.VISIBLE)
                {
                    main_IMG_rocks[i-1][j].setVisibility(View.INVISIBLE);
                    main_IMG_rocks[i][j].setVisibility(View.VISIBLE);
                }
            }
        }
        if(gameManager.getMakeNew())
        {
            gameManager.setMakeNew(false);
            main_IMG_rocks[0][gameManager.getRandom()].setVisibility(View.VISIBLE);
        }
        else
            gameManager.setMakeNew(true);
        crashCheck();
    }

    private void lifeLoss()
    {
        int tempCrashes=gameManager.getCrashes();
        gameManager.toast();
        gameManager.vibrate();
        main_IMG_hearts[tempCrashes++].setVisibility(View.INVISIBLE);
        gameManager.setCrashes(tempCrashes);
        if(tempCrashes==3)
        {
            gameManager.setCrashes(0);
            for(int i=0;i<main_IMG_hearts.length;i++)
                main_IMG_hearts[i].setVisibility(View.VISIBLE);
        }
    }
    private void findViews()
    {
        String temp;
        int resId;
        main_IMG_rocks = new ShapeableImageView[6][3];
        for (int i = 1; i <= 3; i++)
        {
            for (int j = 1; j <= 6; j++)
            {
                temp = "main_LINE"+i+"_rock"+j;
                resId = getResources().getIdentifier(temp,"id",getPackageName());
                Log.d("TAG", ""+resId);
                main_IMG_rocks[j-1][i-1] = (ShapeableImageView) findViewById(resId);
            }
        }

        main_IMG_cars = new ShapeableImageView[] {findViewById(R.id.main_CAR_left),findViewById(R.id.main_CAR_center),findViewById(R.id.main_CAR_right)};
        main_IMG_hearts = new ShapeableImageView[] {findViewById(R.id.main_IMG_heart1),findViewById(R.id.main_IMG_heart2),findViewById(R.id.main_IMG_heart3)};
        main_FAB_left = findViewById(R.id.main_BUTTON_left);
        main_FAB_right = findViewById(R.id.main_BUTTON_right);
    }
}