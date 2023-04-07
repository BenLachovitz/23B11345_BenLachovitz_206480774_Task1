package com.example.a23b11345benlachovitz206480774;

import android.os.VibrationEffect;

import com.example.a23b11345benlachovitz206480774.Utillities.SignalGenerator;

import java.util.Random;

public class GameManager
{
    private int crashes;

    private int currentPosition;

    private SignalGenerator signalGenerator;

    private boolean makeNew;

    public GameManager() {
        this.crashes = 0;
        this.currentPosition = 1;
        makeNew = true;
        signalGenerator = SignalGenerator.getInstance();
    }

    public int getCrashes() {
        return crashes;
    }

    public void setCrashes(int crashes) {
        this.crashes = crashes;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public boolean isMakeNew() {
        return makeNew;
    }

    public void setMakeNew(boolean makeNew) {
        this.makeNew = makeNew;
    }

    public boolean getMakeNew()
    {
        return makeNew;
    }

    public int getRandom() {
        return new Random().nextInt(3);
    }


    public void vibrate()
    {
        signalGenerator.vibrate(500);
    }

    public void toast()
    {
        signalGenerator.toast("Crashed 🥺",0);
    }


}
