package com.example.algol.algoritms.sorting;

/**
 * Created by Сергей Пинкевич on 02.04.2017.
 */

public class BubbleSort extends Sorting {

    /**
     * In each iteration we compare pair of elements.
     * And on each pass we start from the beginning - it is not efficient
     * O(n) = n^2
     * @param array
     */
    public static int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++)
            for (int j = 0; j < array.length - i - 1; j++)
                if (array[j] > array[j + 1])
                    swap(j, j + 1, array);
        return array;
    }
}
