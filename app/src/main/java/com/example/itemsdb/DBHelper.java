package com.example.itemsdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_ITEM_TYPE = "ITEM_TYPE";
    public static final String COLUMN_ITEM_COST = "ITEM_COST";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "items.db", null, 1);
    }

    // this method is called when db is first time accessed.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " +
                ITEM_TABLE +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEM_NAME + " TEXT, " +
                COLUMN_ITEM_TYPE + " TEXT, " +
                COLUMN_ITEM_COST + " FLOAT)";

        sqLiteDatabase.execSQL(createTableStatement);

    }

    // this method is called when db version number changes. It prevents app from crash when db changes
    // its design (e.g. new column added)
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
