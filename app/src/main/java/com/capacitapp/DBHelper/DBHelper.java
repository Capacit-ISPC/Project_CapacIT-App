package com.capacitapp.DBHelper;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "capacitapp.db";
    private static final int DATABASE_VERSION = 1;

    // Tabla Usuario
    private static final String TABLE_USER = "Usuario";
    private static final String USER_ID = "id";
    private static final String USER_EMAIL = "email";
    private static final String USER_NAME = "name";
    private static final String USER_LASTNAME = "lastname";
    private static final String USER_PASSWORD = "password";
    private static final String USER_IS_ACTIVE = "is_active";
    private static final String USER_IS_STAFF = "is_staff";

    // Tabla Curso
    private static final String TABLE_COURSE = "Curso";
    private static final String COURSE_ID = "id";
    private static final String COURSE_NAME = "name";
    private static final String COURSE_DESCRIPTION = "description";
    private static final String COURSE_LANGUAGE = "language";
    private static final String COURSE_TECHNOLOGY = "technology";
    private static final String COURSE_LEVEL = "level";
    private static final String COURSE_PRICE = "price";
    private static final String COURSE_LINK = "link";
    private static final String COURSE_TEACHER_NAME = "teacher_name";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla Usuario
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_EMAIL + " TEXT,"
                + USER_NAME + " TEXT,"
                + USER_LASTNAME + " TEXT,"
                + USER_PASSWORD + " TEXT,"
                + USER_IS_ACTIVE + " INTEGER,"
                + USER_IS_STAFF + " INTEGER" + ")";
        db.execSQL(CREATE_USER_TABLE);

        // Crear tabla Curso
        String CREATE_COURSE_TABLE = "CREATE TABLE " + TABLE_COURSE + "("
                + COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COURSE_NAME + " TEXT,"
                + COURSE_DESCRIPTION + " TEXT,"
                + COURSE_LANGUAGE + " TEXT,"
                + COURSE_TECHNOLOGY + " TEXT,"
                + COURSE_LEVEL + " TEXT,"
                + COURSE_PRICE + " REAL,"
                + COURSE_LINK + " TEXT,"
                + COURSE_TEACHER_NAME + " TEXT" + ")";
        db.execSQL(CREATE_COURSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        onCreate(db);
    }
}
