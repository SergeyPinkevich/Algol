package com.example.algol.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Сергей Пинкевич on 07.03.2017.
 */

public interface AlgolRestApi {
    @GET("/algol/api/sorting/bubble")
    Call<Double> bubbleSort(@Query("number_elements") int numberElements, @Query("maximum_element") int maximumElement);

    @GET("/algol/api/sorting/insertion")
    Call<Double> insertionSort(@Query("number_elements") int numberElements, @Query("maximum_element") int maximumElement);

    @GET("/algol/api/sorting/selection")
    Call<Double> selectionSort(@Query("number_elements") int numberElements, @Query("maximum_element") int maximumElement);
}
