package com.capacitapp;

import android.util.Patterns;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.capacitapp.utils.UserPreferences;


public class loginActivity extends AppCompatActivity {
    private ImageView imgBackArrow;
    private EditText tvEmail, tvPass;
    private Button btn;
    private com.capacitapp.DBHelper.DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        imgBackArrow = findViewById(R.id.btn_back);
        tvEmail = findViewById(R.id.editTextTextEmailAddress);
        tvPass = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.button2);
        dbHelper = new com.capacitapp.DBHelper.DBHelper(this);

        TextView registrarseTextView = findViewById(R.id.textV_registrarse_login);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        registrarseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, RegistroActivity.class);
                startActivity(intent);
            }
        });

        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String useremail = tvEmail.getText().toString().trim();
        String password = tvPass.getText().toString().trim();

        if (useremail.isEmpty()) {
            tvEmail.setError("El correo es requerido");
            tvEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
            tvEmail.setError("Por favor, ingrese un correo válido");
            tvEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            tvPass.setError("La contraseña es requerida");
            tvPass.requestFocus();
            return;
        }
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor cursor = db.query(
                    "Usuario",
                    new String[]{"email", "password"},
                    "email = ? AND password = ?",
                    new String[]{useremail, password},
                    null,
                    null,
                    null
            );

            if (cursor != null && cursor.moveToFirst()) {
                int userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                UserPreferences.saveUserIdToPreferences(this, userId); // Guardar el ID del usuario para luego usarlo en cualquier parte usando sharedpreferences
                Intent i = new Intent(loginActivity.this, MainActivity.class);
                startActivity(i);
                finish(); // Finalizar la actividad de login para que el usuario no pueda regresar con el botón de retroceso
            } else {
                // Credenciales incorrectas
                Toast.makeText(loginActivity.this, "Email o contraseña incorrectos", Toast.LENGTH_LONG).show();
            }

            if (cursor != null) {
                cursor.close();
            }
        }
    }


