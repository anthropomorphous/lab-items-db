package com.example.itemsdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String ITEM_TABLE = "ITEM_TABLE";
    public static final String COLUMN_ITEM_NAME = "ITEM_NAME";
    public static final String COLUMN_ITEM_TYPE = "ITEM_TYPE";
    public static final String COLUMN_ITEM_COST = "ITEM_COST";

    public DBHelper(@Nullable Context context) {
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

    public boolean addItem(ItemModel itemModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ITEM_NAME, itemModel.getItemName());
        cv.put(COLUMN_ITEM_TYPE, itemModel.getItemType());
        cv.put(COLUMN_ITEM_COST, itemModel.getCost());

        // there is no need to add an ID in the DB, because we already have autoincrement in it
        // (it will be calculated and written automatically)

        long insert = db.insert(ITEM_TABLE, null, cv);

        // insert returns -1, if it fails

        if(insert == -1) {
            return false;
        } else {
            return true;
        }

    }

    public List<ItemModel> getEveryone() {
        List<ItemModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + ITEM_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        // cursor = data, that returns our query (result)
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int itemID = cursor.getInt(0);
                String itemName = cursor.getString(1);
                String itemType = cursor.getString(2);
                Float itemCost = cursor.getFloat(3);

                ItemModel newItem = new ItemModel(itemID, itemName, itemType, itemCost);
                returnList.add(newItem);

            } while (cursor.moveToNext());
        } else {
            // do nothing
        }

        // closing connection to db
        cursor.close();
        db.close();

        return returnList;
    }
}
