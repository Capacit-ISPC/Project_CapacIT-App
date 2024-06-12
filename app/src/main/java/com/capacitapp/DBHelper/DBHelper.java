package com.capacitapp.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.capacitapp.models.Curso;
import com.capacitapp.models.Usuario;

import java.util.ArrayList;
import java.util.List;

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
                "VALUES ('admin@gmail.com', 'User', 'Admin', 'abc456', 1, 1),"
                + "('leomesi@gmail.com', 'Leonel', 'Messi', 'abc456', 1, 1),"
                + "('luchaymar@gmail.c       om', 'Luciana', 'Aymar', 'abc456', 1, 1),"
                + "('manuginobili@gmail.com', 'Manu', 'Ginobili', 'abc456', 1, 1)";
        db.execSQL(INSERT_DEFAULT_USER);
        //Insertar cursos disponibles por defecto
        String INSERT_DEFAULT_COURSES = "INSERT INTO Curso (name, description, language, technology, level, price, link, teacher_name) VALUES "
                + "('Java Básico', 'Aprende los fundamentos de Java.', 'Español', 'Java', 'Básico', 49.99, 'NbhlGfQLaKI', 'Juan Pérez'),"
                + "('Python Avanzado', 'Domina técnicas avanzadas de Python.', 'Español', 'Python', 'Avanzado', 79.99, 'eS0Q511qNgg', 'Sara García'),"
                + "('Desarrollo Web Java', 'Desarrolla sitios web profesionales.', 'Español', 'HTML, CSS, JavaScript', 'Intermedio', 59.99, 'NbhlGfQLaKI', 'Carlos Martínez'),"
                + "('Machine Learning con Python', 'Machine Learning con Python.', 'Español', 'Python', 'Intermedio', 89.99, 'eS0Q511qNgg', 'Lucía Fernández'),"
                + "('Analisis de Datos con Python', 'Conviertete en analista de datos con Python.', 'Español', 'Python', 'Intermedio', 89.99, 'eS0Q511qNgg', 'Lucía Fernández'),"
                + "('Bases de Datos SQL', 'Aprende SQL y gestiona BBDD.', 'Español', 'SQL', 'Básico', 39.99, 'NbhlGfQLaKI', 'David Rodríguez')";
        db.execSQL(INSERT_DEFAULT_COURSES);

        //Insertar datos por defecto tabla cursos usuario
        String INSERT_DEFAULT_USUARIO_CURSO="INSERT INTO UsuarioCurso (usuario_id, curso_id) VALUES (1, 1), (1,2),(1,3),(2,1),(2,2),(2,4),(3,2),(3,3),(3,4),(3,5),(4,2),(4,4),(4,5) ";

        db.execSQL(INSERT_DEFAULT_USUARIO_CURSO);


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_COURSE); //agregado tabla usuariocurso nueva version
        db.execSQL("DROP TABLE IF EXISTS Clase");//agregada tabla clase nueva version
        onCreate(db);
    }

    public Cursor getCursos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Curso", null);
    }



    // Métodos actualizados para obtener la lista de cursos de un usuario
    public List<Curso> getCursosByUserId(int userId) {
        List<Curso> cursos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT c.id, c.name, c.description, c.language, c.technology, c.level, c.price, c.link, c.teacher_name " +
                "FROM UsuarioCurso uc " +
                "INNER JOIN Curso c ON uc.curso_id = c.id " +
                "WHERE uc.usuario_id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Curso curso = new Curso(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getString(cursor.getColumnIndexOrThrow("description")),
                        cursor.getString(cursor.getColumnIndexOrThrow("language")),
                        cursor.getString(cursor.getColumnIndexOrThrow("technology")),
                        cursor.getString(cursor.getColumnIndexOrThrow("level")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("price")),
                        cursor.getString(cursor.getColumnIndexOrThrow("link")),
                        cursor.getString(cursor.getColumnIndexOrThrow("teacher_name"))
                );
                cursos.add(curso);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return cursos;
    }
    // Método que retorna los datos de un usuario dado su ID
    public Usuario getUserById(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});

        Usuario usuario = null;
        if (cursor != null && cursor.moveToFirst()) {
            usuario = new Usuario(
                    cursor.getInt(cursor.getColumnIndexOrThrow(USER_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(USER_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(USER_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(USER_LASTNAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(USER_PASSWORD)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(USER_IS_ACTIVE))>0,
                    cursor.getInt(cursor.getColumnIndexOrThrow(USER_IS_STAFF))>0
            );
            cursor.close();
        }
        return usuario;
    }
    //Metdodo para actualizar los datos del usuario
    public void updateUser(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_EMAIL, usuario.getEmail());
        values.put(USER_NAME, usuario.getName());
        values.put(USER_LASTNAME, usuario.getLastname());
        values.put(USER_PASSWORD, usuario.getPassword());
        values.put(USER_IS_ACTIVE, usuario.getIs_active() ? 1 : 0);
        values.put(USER_IS_STAFF, usuario.getIs_staff() ? 1 : 0);

        db.update(TABLE_USER, values, USER_ID + " = ?", new String[]{String.valueOf(usuario.getId())});
        db.close();
    }

    public void deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_USER_COURSE, USER_COURSE_USER_ID + " = ?", new String[]{String.valueOf(userId)});
            db.delete(TABLE_USER, USER_ID + " = ?", new String[]{String.valueOf(userId)});
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

    }
    public List<Curso> searchCursos(String query) {
        List<Curso> cursos = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String searchQuery = "SELECT * FROM " + TABLE_COURSE + " WHERE " + COURSE_NAME + " LIKE ?";
        Cursor cursor = db.rawQuery(searchQuery, new String[]{"%" + query + "%"});

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Curso curso = new Curso(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COURSE_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COURSE_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COURSE_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COURSE_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COURSE_TECHNOLOGY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COURSE_LEVEL)),
                        cursor.getDouble(cursor.getColumnIndexOrThrow(COURSE_PRICE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COURSE_LINK)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COURSE_TEACHER_NAME))
                );
                cursos.add(curso);
            } while (cursor.moveToNext());
            cursor.close();
        }
        Log.d("DBHelper", "Cursos encontrados: " + cursos.size());
        return cursos;
    }

}
