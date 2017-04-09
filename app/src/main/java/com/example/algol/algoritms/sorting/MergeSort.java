package com.example.algol.algoritms.sorting;

/**
 * Created by Сергей Пинкевич on 02.04.2017.
 */

public class MergeSort extends SortingAlgorithm {

    /**
     * We create additional array for interim work
     * O(n) = n * log(n)
     * @param array
     */
    public static void sort(int[] array) {
        int aux[] = new int[array.length];
        mergeSort(array, aux, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(array, aux, lo, mid);
        mergeSort(array, aux, mid + 1, hi);
        merge(array, aux, lo, mid, hi);
    }

    private static void merge(int array[], int[] aux, int lo, int mid, int hi) {
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
}
