package com.example.algol.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.algol.database.AlgolDbSchema.MainMenuTable;

/**
 * Created by Сергей Пинкевич on 10.11.2016.
 */

public class SimpleDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "algol.db";
    private static final int DB_VERSION = 1;

    public SimpleDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        updateDatabase(sqLiteDatabase, i, i1);
    }

    public void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE " + MainMenuTable.NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + MainMenuTable.Cols.NAME + ", "
                    + MainMenuTable.Cols.CATEGORY + ");");
            insertItemToMenu(db, "Bubble Sort", "Sorting");
            insertItemToMenu(db, "Insertion Sort", "Sorting");
            insertItemToMenu(db, "Quick Sort", "Sorting");
            insertItemToMenu(db, "Depth First Search", "Graph");
            insertItemToMenu(db, "Breadth First Search", "Graph");
        }
    }

    public void insertItemToMenu(SQLiteDatabase db, String category, String name) {
        ContentValues itemValues = new ContentValues();
        itemValues.put("NAME", name);
        itemValues.put("CATEGORY", category);
        db.insert(MainMenuTable.NAME, null, itemValues);
    }
}