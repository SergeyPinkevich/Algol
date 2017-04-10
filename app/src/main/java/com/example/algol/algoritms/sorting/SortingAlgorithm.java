package com.example.algol.algoritms.sorting;

import com.example.algol.algoritms.AlgorithmExplanation;
import com.example.algol.algoritms.ExplanationHandler;
import com.example.algol.visualizer.SortingVisualizer;

/**
 * Created by Сергей Пинкевич on 16.11.2016.
 */

public class SortingAlgorithm extends AlgorithmExplanation implements ExplanationHandler {

    public SortingVisualizer mSortingVisualizer;
    protected int[] mArray;

    public void setData(final int[] array) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSortingVisualizer.setData(array);
            }
        });
        mArray = array;
        start();
        prepareHandler(this);
    }

    public void highlightSwap(final int first, final int second) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSortingVisualizer.highlightSwap(first, second);
            }
        });
    }

    public void highlightTrace(final int position) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSortingVisualizer.highlightTrace(position);
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
