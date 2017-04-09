package com.example.algol.algoritms;

import com.example.algol.algoritms.sorting.SortingAlgorithm;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by Сергей Пинкевич on 20.11.2016.
 */
public class SortingAlgorithmTest {

    private int[] array;
    private int[] answer = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    @Before
    public void setUp() throws Exception {
        array = SortingAlgorithm.shuffling(answer);
    }

    @Test
    public void shuffle() throws Exception {
        array = SortingAlgorithm.shuffling(answer);
        for (int i = 0; i < answer.length; i++) {
            System.out.print(array[i] + " " + answer[i] + '\n');
        }
    }
}