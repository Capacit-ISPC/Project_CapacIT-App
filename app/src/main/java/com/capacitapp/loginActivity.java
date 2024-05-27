package com.capacitapp;

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
        String useremail = tvEmail.getText().toString();
        String password = tvPass.getText().toString();

        if (useremail.isEmpty() || password.isEmpty()) {
            Toast.makeText(loginActivity.this, "Email o contraseña no ingresado", Toast.LENGTH_LONG).show();
        } else {
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
                Intent i = new Intent(loginActivity.this, MainActivity.class);
                startActivity(i);
            } else {
                // Credenciales incorrectas
                Toast.makeText(loginActivity.this, "Email o contraseña incorrectos", Toast.LENGTH_LONG).show();
            }

            if (cursor != null) {
                cursor.close();
            }
        }
    }


}