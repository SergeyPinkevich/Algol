package com.example.algol.algoritms;

/**
 * Created by Сергей Пинкевич on 15.02.2017.
 */

public interface ExplanationHandler {
    void onDataReceived(Object data);
    void onCommandReceived(String text);
}
