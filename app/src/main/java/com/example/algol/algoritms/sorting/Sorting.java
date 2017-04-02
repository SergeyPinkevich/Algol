package com.example.algol.algoritms.sorting;

import com.example.algol.algoritms.DataHandler;
import com.example.algol.visualizer.SortingVisualizer;

/**
 * Created by Сергей Пинкевич on 16.11.2016.
 */

public class Sorting implements DataHandler {

    public SortingVisualizer mSortingVisualizer;

    public void setData(final int[] array) {

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
    public void onTextReceived(String text) {

    }
}
