package com.example.algol.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.algol.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей Пинкевич on 16.11.2016.
 */

public class AlgolLab {
    private static AlgolLab sAlgolLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;
    private List<MenuItem> mMenuItems;

    private AlgolLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new SimpleDatabaseHelper(context).getWritableDatabase();
        mMenuItems = new ArrayList<>();
    }
}
