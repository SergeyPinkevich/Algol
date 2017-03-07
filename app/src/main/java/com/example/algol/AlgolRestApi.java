package com.example.algol;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Сергей Пинкевич on 07.03.2017.
 */

public interface AlgolRestApi {
    @POST("/algol/api/sorting/bubble")
    Call<Double> getTime(@Query("array") int[] array);
}
