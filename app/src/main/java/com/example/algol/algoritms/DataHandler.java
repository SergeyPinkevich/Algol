package com.example.algol.algoritms;

/**
 * Created by Сергей Пинкевич on 15.02.2017.
 */

public interface DataHandler {
    void onDataReceived(Object data);
    void onTextReceived(String text);
}
