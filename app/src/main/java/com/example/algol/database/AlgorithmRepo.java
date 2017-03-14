package com.example.algol.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.algol.database.AlgolDbSchema.MainMenuTable.Cols;

import com.example.algol.Algorithm;

import java.util.ArrayList;

/**
 * Created by Сергей Пинкевич on 13.01.2017.
 */

public class AlgorithmRepo {

    private SimpleDatabaseHelper mHelper;

    public AlgorithmRepo(Context context) {
        mHelper = SimpleDatabaseHelper.getInstance(context);
    }

    public void addAlgorithm(SQLiteDatabase db, Algorithm algorithm) {
        ContentValues contentValues = getContentValues(algorithm);
        db.insert(AlgolDbSchema.MainMenuTable.NAME, null, contentValues);
    }

    public Algorithm getAlgorithmById(int id) {
        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(AlgolDbSchema.MainMenuTable.NAME, null,
                AlgolDbSchema.MainMenuTable.Cols.ID + " = ?", new String[] {String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Algorithm algorithm = createAlgorithmFromCursor(cursor);
        cursor.close();
        database.close();
        return algorithm;
    }

    public Algorithm createAlgorithmFromCursor(Cursor cursor) {
        Algorithm algorithm = new Algorithm(cursor.getInt(cursor.getColumnIndex(AlgolDbSchema.MainMenuTable.Cols.ID)),
                cursor.getString(cursor.getColumnIndex(Cols.NAME)),
                cursor.getString(cursor.getColumnIndex(Cols.CATEGORY)),
                cursor.getString(cursor.getColumnIndex(Cols.DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(Cols.COMPLEXITY)));
        return algorithm;
    }

    public ArrayList<Algorithm> getAlgorithmsList() {
        ArrayList<Algorithm> algorithmArrayList = new ArrayList<>();

        SQLiteDatabase database = mHelper.getReadableDatabase();
        Cursor cursor = database.query(AlgolDbSchema.MainMenuTable.NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast() == false) {
                Algorithm algorithm = createAlgorithmFromCursor(cursor);
                algorithmArrayList.add(algorithm);
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return algorithmArrayList;
    }

    public ContentValues getContentValues(Algorithm algorithm) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Cols.ID, algorithm.getId());
        contentValues.put(Cols.NAME, algorithm.getName());
        contentValues.put(Cols.CATEGORY, algorithm.getCategory());
        contentValues.put(Cols.DESCRIPTION, algorithm.getDescription());
        contentValues.put(Cols.COMPLEXITY, algorithm.getDescription());
        return contentValues;
    }
}