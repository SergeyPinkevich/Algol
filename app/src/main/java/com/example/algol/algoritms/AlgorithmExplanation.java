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

    protected Handler mHandler;
    protected Activity mActivity;
    protected AlgorithmCompletedListener mCompletedListener;

    public static final String COMMAND_START = "start";

    protected boolean isStarted;
    private final AtomicBoolean isPaused = new AtomicBoolean(false);
    private final Object pauseLock = new Object();

    public AlgorithmExplanation() {
        super("");
    }

    public void sleep() {
        sleepFor(400);
    }

    public void sleepFor(long time) {
        try {
            sleep(time);
            if (getIsPaused())
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

    public void setStarted(boolean started) {
        this.isStarted = started;
    }

    public boolean isStarted() {
        return isStarted;
    }

    private void pauseExecution() {
        if (isPaused.get())
            synchronized (getPauseLock()) {
                if (isPaused.get())
                    try {
                        getPauseLock().wait();
                    } catch (InterruptedException ignored) {
                        ignored.printStackTrace();
                    }
            }
    }

    public void setIsPaused(boolean b) {
        isPaused.set(b);
        if (!b) {
            synchronized (getPauseLock()) {
                getPauseLock().notify();
            }
        }
    }

    public boolean getIsPaused() {
        return isPaused.get();
    }

    Object getPauseLock() {
        return pauseLock;
    }

    private void resumeExecution() {
        synchronized (pauseLock) {
            pauseLock.notifyAll();
        }
    }

    public void sendData(Object data) { mHandler.obtainMessage(1, data).sendToTarget(); }

    public void sendLog(String log) {
        mHandler.obtainMessage(1, log).sendToTarget();
    }

    public void prepareHandler(final ExplanationHandler explanationHandler) {
        mHandler = new Handler(getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.obj instanceof String) {
                    explanationHandler.onCommandReceived((String) msg.obj);
                } else {
                    explanationHandler.onDataReceived(msg.obj);
                }
                return true;
            }
        });
    }

    public void completed() {
        isStarted = false;
        if (mCompletedListener != null) {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mCompletedListener.onAlgorithmCompleted();
                }
            });
        }
    }
}
