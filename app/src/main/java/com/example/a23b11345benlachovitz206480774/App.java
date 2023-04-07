package com.example.a23b11345benlachovitz206480774;

import android.app.Application;

import com.example.a23b11345benlachovitz206480774.Utillities.SignalGenerator;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SignalGenerator.init(this);
    }
}
