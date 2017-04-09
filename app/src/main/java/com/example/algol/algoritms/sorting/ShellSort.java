package com.example.algol.algoritms.sorting;

/**
 * Created by Сергей Пинкевич on 02.04.2017.
 */

public class ShellSort extends SortingAlgorithm {

    /**
     * Move entries more than one position at a time by h-sorting the array
     * It is modifying insertion sort
     * Worst case O(n) = n^2
     * Best case O(n) = n * log^2(n)
     * Average case - depends from step
     * @param array
     */
    public static int[] sort(int[] array) {
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
}
