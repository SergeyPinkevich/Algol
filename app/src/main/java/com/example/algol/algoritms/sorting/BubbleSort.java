package com.example.algol.algoritms.sorting;

import android.app.Activity;

import com.example.algol.algoritms.AlgorithmExplanation;
import com.example.algol.algoritms.ExplanationHandler;
import com.example.algol.visualizer.SortingVisualizer;

/**
 * Created by Сергей Пинкевич on 02.04.2017.
 */

public class BubbleSort extends SortingAlgorithm implements ExplanationHandler {

    private int[] mArray;

    public BubbleSort(SortingVisualizer visualizer, Activity activity) {
        this.mSortingVisualizer = visualizer;
        this.mActivity = activity;
    }

    @Override
    public void run() {
        super.run();
    }

    /**
     * In each iteration we compare pair of elements.
     * And on each pass we start from the beginning - it is not efficient
     * O(n) = n^2
     */
    public int[] sort() {
        for (int i = 0; i < mArray.length - 1; i++) {
            highlightTrace(i);
            sleep();
            for (int j = 0; j < mArray.length - i - 1; j++) {
                highlightTrace(j);
                if (mArray[j] > mArray[j + 1]) {
                    int temp = mArray[j];
                    mArray[j] = mArray[j + 1];
                    mArray[j + 1] = temp;
                    highlightSwap(j, j + 1);
                }
                sleep();
            }
        }
        completed();
        return mArray;
    }


    @Override
    public void onDataReceived(Object data) {
        super.onDataReceived(data);
        mArray = (int[]) data;
    }

    @Override
    public void onCommandReceived(String command) {
        super.onCommandReceived(command);
        if (command.equals(AlgorithmExplanation.COMMAND_START)) {
            startExecution();
            sort();
        }
    }
}
