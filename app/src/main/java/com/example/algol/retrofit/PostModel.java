package com.example.algol.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Сергей Пинкевич on 10.03.2017.
 */

public class PostModel {

    @SerializedName("time")
    @Expose
    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
