package com.example.algol.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей Пинкевич on 16.11.2016.
 */

public class AlgolLab {
    private static AlgolLab sAlgolLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private AlgolLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = SimpleDatabaseHelper.getInstance(context).getWritableDatabase();
    }
}
