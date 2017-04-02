package com.example.algol.algoritms.sorting;

/**
 * Created by Сергей Пинкевич on 02.04.2017.
 */

public class SelectionSort extends Sorting {

    /**
     * In iteration i, find index 'min' of smallest remaining entry
     * Swap a[i] and a[min]
     * O(n) = n^2
     *
     * @param array
     */
    public static int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++)
                if (array[j] < array[min])
                    min = j;
            swap(min, i, array);
        }
        return array;
    }
}
