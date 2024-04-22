package com.victorashino.appgaseta.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.victorashino.appgaseta.model.Fuel;

import java.util.ArrayList;
import java.util.List;

public class GasEtaDB extends SQLiteOpenHelper {

    private static final String DB_NAME = "gaseta.db";
    private static final int DB_VERSION = 1;

    Cursor cursor;

    SQLiteDatabase db;

    public GasEtaDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlFuelTable =
                "CREATE TABLE Fuel (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "price REAL, " +
                        "recommendation TEXT" +
                        ")";

        db.execSQL(sqlFuelTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void saveObject(String table, ContentValues data) {
        db.insert(table, null, data);
    }

    public List<Fuel> listData() {
        List<Fuel> list = new ArrayList<>();

        Fuel record;

        String querySQL = "SELECT * FROM Fuel";

        cursor = db.rawQuery(querySQL, null);

        if (cursor.moveToFirst()) {
            do {
                record = new Fuel();
                record.setId(cursor.getInt(0));
                record.setFuel(cursor.getString(1));
                record.setFuelPrice(cursor.getDouble(2));
                record.setRecommendation(cursor.getString(3));
                list.add(record);
            } while (cursor.moveToNext());
        }
        return list;
    }

    public void modifyData(String table, ContentValues data) {

        int id = data.getAsInteger("id");
        db.update(table, data, "id=?", new String[]{Integer.toString(id)});
    }

    public void delete(String table, int id) {
        db.delete(table, "id=?", new String[]{Integer.toString(id)});
    }

}
