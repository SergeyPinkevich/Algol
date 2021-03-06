package com.example.algol.algoritms.sorting;

import android.os.Handler;
import android.os.Looper;

import com.example.algol.algoritms.AlgorithmExplanation;
import com.example.algol.algoritms.ExplanationHandler;
import com.example.algol.visualizer.SortingVisualizer;

/**
 * Created by Сергей Пинкевич on 16.11.2016.
 */

public class SortingAlgorithm extends AlgorithmExplanation implements ExplanationHandler {

    public SortingVisualizer mSortingVisualizer;

    public void setData(final int[] array) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSortingVisualizer.setArrayForSorting(array);
            }
        });
        start();
        prepareHandler(this);
        sendData(array);
    }

    public void highlightSwap(final int first, final int second) {
//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mSortingVisualizer.highlightSwap(first, second);
//            }
//        }, 6000);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSortingVisualizer.highlightSwap(first, second);
            }
        });
    }

    public void highlightTrace(final int position) {
//        Handler handler = new Handler(Looper.getMainLooper());
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mSortingVisualizer.highlightTrace(position);
//            }
//        }, 6000);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSortingVisualizer.highlightTrace(position);
            }
        });
    }

    public void completed() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSortingVisualizer.onCompleted();
            }
        });
    }

    /**
     * Good way to randomize input array
     * @param array
     */
    public static int[] shuffling(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int r = (int) (Math.random() * array.length);
            swap(r, i,array);
        }
        return array;
    }

    /**
     * Swap elements array[i] and array[j]
     * @param i
     * @param j
     * @param array
     */
    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public void onDataReceived(Object data) {

    }

    @Override
    public void onCommandReceived(String text) {

    }
}
