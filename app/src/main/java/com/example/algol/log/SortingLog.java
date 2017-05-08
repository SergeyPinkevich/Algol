package com.example.algol.log;

/**
 * Created by Сергей Пинкевич on 08.05.2017.
 */

public class SortingLog {

    public static String explanationMessage(int a, int b) {
        StringBuilder builder = new StringBuilder();
        builder.append("The " + a);
        if (a < b) {
            builder.append(" is smaller than " + b);
            builder.append(", we don't need to swap");
        }
        else if (a > b) {
            builder.append(" is greater than " + b);
            builder.append(", so we need to swap");
        }
        else {
            builder.append(" equals " + b);
            builder.append(", we don't need to swap");
        }
        return builder.toString();
    }
}
