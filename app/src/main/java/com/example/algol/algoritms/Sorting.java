package com.example.algol.algoritms;

/**
 * Created by Сергей Пинкевич on 16.11.2016.
 */

public class Sorting {

    /**
     * In each iteration we compare pair of elements.
     * And on each pass we start from the beginning - it is not efficient
     * O(n) = n^2
     * @param array
     */
    public static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++)
            for (int j = 0; j < array.length - i - 1; j++)
                if (array[j] > array[j + 1])
                    swap(j, j + 1, array);
    }

    /**
     * In iteration i, swap a[i] with each larger entry to its left
     * O(n) = n^2
     * @param array
     */
    public static void insertionSort(int[] array) {
        for (int i = 0; i < array.length; i++)
            for (int j = i; j > 0; j--)
                if (array[j - 1] > array[j])
                    swap(j, j - 1, array);
                else
                    break;
    }

    /**
     * In iteration i, find index 'min' of smallest remaining entry
     * Swap a[i] and a[min]
     * O(n) = n^2
     *
     * @param array
     */
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[min])
                    min = j;
            swap(min, i, array);
        }
    }

    public static void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
