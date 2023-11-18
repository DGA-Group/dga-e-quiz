package com.dga.game.GameMode;

public class Timer extends Thread {
    long timeMillisecond;

    public Timer(long timeMillisecond) {
        this.timeMillisecond = timeMillisecond;
    }

    public void abort() {
        this.interrupt();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeMillisecond);
        } catch (InterruptedException e) {
        }
    }
}
