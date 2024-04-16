package com.victorashino.appgaseta.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GasEtaDB extends SQLiteOpenHelper {

    public static final String DB_NAME = "gaseta.db";
    public static final int DB_VERSION = 1;

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

    // Para criar um database
    // 1 - nome do db
    // 2 - versão do db

    // TODO: Criar métodos para implementar um CRUD
    // Create
    // Read
    // Update
    // Delete

    //Create database nome_do_banco_de_dados.db
    // Create table

    // Select * from table

    // Update from table

    // Delete from

}
