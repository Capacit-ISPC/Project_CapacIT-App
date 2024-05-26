package com.capacitapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;

public class BaseDeDatos extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "usuarios.db";
    private static final String TABLE_NAME = "usuarios";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NOMBRE";
    private static final String COL_3 = "APELLIDO";
    private static final String COL_4 = "EMAIL";
    private static final String COL_5 = "PASSWORD";

    public BaseDeDatos(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, EMAIL TEXT UNIQUE, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertarUsuario(String nombre, String apellido, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellido", apellido);
        values.put("email", email);
        values.put("password", password);

        db.insert("usuarios", null, values);
        db.close();
    }

    public boolean verificarUsuario(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ? AND password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}

