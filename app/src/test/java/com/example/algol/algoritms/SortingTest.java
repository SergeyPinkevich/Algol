package com.example.algol.algoritms;

import com.example.algol.algoritms.sorting.Sorting;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Сергей Пинкевич on 20.11.2016.
 */
public class SortingTest {

    private int[] array;
    private int[] answer = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    @Before
    public void setUp() throws Exception {
        array = Sorting.shuffling(answer);
    }

    @Test
    public void shuffle() throws Exception {
        array = Sorting.shuffling(answer);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(array[i] + " " + answer[i] + '\n');
        }
    }

    @Test
    public void shellSort() throws Exception {
        assertArrayEquals(answer, Sorting.shellSort(array));
    }

    @Test
    public void mergeSort() throws Exception {

    }

    @Test
    public void bubbleSort() throws Exception {
        assertArrayEquals(answer, Sorting.bubbleSort(array));
    }

    @Test
    public void insertionSort() throws Exception {
        assertArrayEquals(answer, Sorting.insertionSort(array));
    }

    @Test
    public void selectionSort() throws Exception {
        assertArrayEquals(answer, Sorting.selectionSort(array));
    }
}