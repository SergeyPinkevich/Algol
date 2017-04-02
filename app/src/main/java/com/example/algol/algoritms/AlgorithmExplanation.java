package com.example.algol.algoritms;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Сергей Пинкевич on 02.04.2017.
 */

public class AlgorithmExplanation extends HandlerThread {

    private boolean isStarted;
    private boolean isPaused;
    private Handler mHandler;
    private Activity mActivity;

    private final AtomicBoolean paused = new AtomicBoolean(false);
    private final Object pauseLock = new Object();

    public AlgorithmExplanation() {
        super("");
    }

    public void sleep() {
        sleepFor(500);
    }

    public void sleepFor(long time) {
        try {
            sleep(time);
            if (isPaused())
                pauseExecution();
            else resumeExecution();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void startExecution() {
        isStarted = true;
    }

    private void pauseExecution() {
        if (paused.get())
            synchronized (getPauseLock()) {
                if (paused.get())
                    try {
                        getPauseLock().wait();
                    } catch (InterruptedException ignored) {
                        ignored.printStackTrace();
                    }
            }
    }

    private void resumeExecution() {
        synchronized (pauseLock) {
            pauseLock.notifyAll();
        }
    }

    public void setPaused(boolean b) {
        paused.set(b);
        if (!b) {
            synchronized (getPauseLock()) {
                getPauseLock().notify();
            }
        }
    }

    public boolean isPaused() {
        return paused.get();
    }

    Object getPauseLock() {
        return pauseLock;
    }

    public void setStarted(boolean started) {
        this.isStarted = started;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void prepareHandler(final DataHandler dataHandler) {
        mHandler = new Handler(getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.obj instanceof String) {
                    dataHandler.onTextReceived((String) msg.obj);
                } else {
                    dataHandler.onDataReceived(msg.obj);
                }
                return true;
            }
        });
    }
}
