package com.example.algol.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.algol.Algorithm;
import com.example.algol.R;
import com.example.algol.database.AlgolDbSchema.MainMenuTable;

/**
 * Created by Сергей Пинкевич on 10.11.2016.
 */

public class SimpleDatabaseHelper extends SQLiteOpenHelper {
    private static SimpleDatabaseHelper sInstance;

    private static final String DB_NAME = "algol.db";
    private static final int DB_VERSION = 1;
    private Context mContext;

    private SimpleDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
    }

    public static synchronized SimpleDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SimpleDatabaseHelper(context);
        }
        return sInstance;
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
                    + MainMenuTable.Cols.ID + " INTEGER, "
                    + MainMenuTable.Cols.NAME + " TEXT, "
                    + MainMenuTable.Cols.CATEGORY + " TEXT, "
                    + MainMenuTable.Cols.DESCRIPTION + " TEXT, "
                    + MainMenuTable.Cols.COMPLEXITY + " TEXT);");

            addAlgorithms(db);
        }
    }

    public void addAlgorithms(SQLiteDatabase db) {
        AlgorithmRepo algorithmRepo = new AlgorithmRepo(mContext);
        algorithmRepo.addAlgorithm(db, new Algorithm(0, "Bubble Sort", "Sorting", mContext.getString(R.string.bubble_sort_description), "n_2"));
        algorithmRepo.addAlgorithm(db, new Algorithm(1, "Selection Sort", "Sorting", mContext.getString(R.string.selection_sort_description), "n_2"));
        algorithmRepo.addAlgorithm(db, new Algorithm(2, "Insertion Sort", "Sorting", mContext.getString(R.string.insertion_sort_description), "n_2"));
    }
}