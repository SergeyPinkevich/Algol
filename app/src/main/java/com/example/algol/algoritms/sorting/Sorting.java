package com.example.algol.algoritms.sorting;

import com.example.algol.algoritms.DataHandler;

/**
 * Created by Сергей Пинкевич on 16.11.2016.
 */

public class Sorting implements DataHandler {

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
     * Move entries more than one position at a time by h-sorting the array
     * It is modifying insertion sort
     * Worst case O(n) = n^2
     * Best case O(n) = n * log^2(n)
     * Average case - depends from step
     * @param array
     */
    public static int[] shellSort(int[] array) {
        int h = 1;

        while (h < array.length / 3)
            h = 3 * h + 1;

        while (h >= 1) {
            for (int i = 0; i < array.length; i++)
                for (int j = i; j >= h && array[j - 1] > array[j]; j -= h)
                    swap(j, j - 1, array);
            h /= 3;
        }
        return array;
    }

    /**
     * We create additional array for interim work
     * O(n) = n * log(n)
     * @param array
     */
    public static void mergeSort(int[] array) {
        int aux[] = new int[array.length];
        sort(array, aux, 0, array.length - 1);
    }

    public static void sort(int[] array, int[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(array, aux, lo, mid);
        sort(array, aux, mid + 1, hi);
        merge(array, aux, lo, mid, hi);
    }

    public static void merge(int array[], int[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++)
            aux[k] = array[k];

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                array[k] = aux[j++];
            else if (j > hi)
                array[k] = aux[i++];
            else if (aux[j] < aux[i])
                array[k] = aux[j++];
            else
                array[k] = aux[i++];
        }
    }

    /**
     * In each iteration we compare pair of elements.
     * And on each pass we start from the beginning - it is not efficient
     * O(n) = n^2
     * @param array
     */
    public static int[] bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++)
            for (int j = 0; j < array.length - i - 1; j++)
                if (array[j] > array[j + 1])
                    swap(j, j + 1, array);
        return array;
    }

    /**
     * In iteration i, swap a[i] with each larger entry to its left
     * O(n) = n^2
     * @param array
     */
    public static int[] insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++)
            for (int j = i; j > 0; j--)
                if (array[j - 1] > array[j])
                    swap(j, j - 1, array);
                else
                    break;
        return array;
    }

    /**
     * In iteration i, find index 'min' of smallest remaining entry
     * Swap a[i] and a[min]
     * O(n) = n^2
     *
     * @param array
     */
    public static int[] selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[min])
                    min = j;
            swap(min, i, array);
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
