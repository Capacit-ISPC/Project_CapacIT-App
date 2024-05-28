package com.capacitapp.DBHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "capacitapp.db";
    private static final int DATABASE_VERSION = 2;

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

    // Nueva Tabla UsuarioCurso (intermedia)
    private static final String TABLE_USER_COURSE = "UsuarioCurso";
    private static final String USER_COURSE_USER_ID = "usuario_id";
    private static final String USER_COURSE_COURSE_ID = "curso_id";

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

        // Agregada Crear tabla intermedia UsuarioCurso
        String CREATE_USER_COURSE_TABLE = "CREATE TABLE " + TABLE_USER_COURSE + "("
                + USER_COURSE_USER_ID + " INTEGER,"
                + USER_COURSE_COURSE_ID + " INTEGER,"
                + "FOREIGN KEY(" + USER_COURSE_USER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_ID + "),"
                + "FOREIGN KEY(" + USER_COURSE_COURSE_ID + ") REFERENCES " + TABLE_COURSE + "(" + COURSE_ID + "),"
                + "PRIMARY KEY(" + USER_COURSE_USER_ID + ", " + USER_COURSE_COURSE_ID + "))";
        db.execSQL(CREATE_USER_COURSE_TABLE);

        // Insertar un usuario por defecto
        String INSERT_DEFAULT_USER = "INSERT INTO Usuario (email, name, lastname, password, is_active, is_staff) " +
                "VALUES ('admin@gmail.com', 'User', 'Admin', '12345', 1, 0)";
        db.execSQL(INSERT_DEFAULT_USER);
        //Insertar cursos disponibles por defecto
        String INSERT_DEFAULT_COURSES = "INSERT INTO Curso (name, description, language, technology, level, price, link, teacher_name) VALUES "
                + "('Curso de Java Básico', 'Aprende los fundamentos de Java.', 'Español', 'Java', 'Básico', 49.99, 'http://example.com/java-basico', 'Juan Pérez'),"
                + "('Curso de Python Avanzado', 'Domina técnicas avanzadas de Python.', 'Español', 'Python', 'Avanzado', 79.99, 'http://example.com/python-avanzado', 'Ana García'),"
                + "('Curso de Desarrollo Web', 'Desarrolla sitios web profesionales.', 'Español', 'HTML, CSS, JavaScript', 'Intermedio', 59.99, 'http://example.com/desarrollo-web', 'Carlos Martínez'),"
                + "('Curso de Machine Learning', 'Machine Learning con Python.', 'Español', 'Python', 'Intermedio', 89.99, 'http://example.com/machine-learning', 'Lucía Fernández'),"
                + "('Curso de Bases de Datos SQL', 'Aprende SQL y gestiona BBDD.', 'Español', 'SQL', 'Básico', 39.99, 'http://example.com/bases-de-datos-sql', 'David Rodríguez');";
        db.execSQL(INSERT_DEFAULT_COURSES);

        //Insertar datos por defecto tabla cursos usuario
        String INSERT_DEFAULT_USUARIO_CURSO="INSERT INTO UsuarioCurso (usuario_id, curso_id) VALUES (1, 1)";
        db.execSQL(INSERT_DEFAULT_USUARIO_CURSO);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_COURSE); //agregado tabla nueva version
        onCreate(db);
    }

    public Cursor getCursos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Curso", null);
    }
}
