package com.example.algol.algoritms.sorting;

/**
 * Created by Сергей Пинкевич on 02.04.2017.
 */

public class InsertionSort extends SortingAlgorithm {

    /**
     * In iteration i, swap a[i] with each larger entry to its left
     * O(n) = n^2
     * @param array
     */
    public static int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++)
            for (int j = i; j > 0; j--)
                if (array[j - 1] > array[j])
                    swap(j, j - 1, array);
                else
                    break;
        return array;
    }
}
