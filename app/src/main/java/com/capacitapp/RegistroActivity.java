package com.capacitapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegistroActivity extends AppCompatActivity {

    private ImageView imgBackArrow;
    private TextInputEditText nombreEditText, apellidoEditText, emailEditText, passwordEditText;
    private Button registrarseButton;
    private com.capacitapp.DBHelper.DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        imgBackArrow = findViewById(R.id.btn_back);
        nombreEditText = findViewById(R.id.textInputNombre);
        apellidoEditText = findViewById(R.id.textInputApellido);
        emailEditText = findViewById(R.id.textInputCorreo);
        passwordEditText = findViewById(R.id.textInputPassword);
        registrarseButton = findViewById(R.id.button_registrarse);
        dbHelper = new com.capacitapp.DBHelper.DBHelper(this);

        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroActivity.this, InicioActivity.class);
                startActivity(intent);
            }
        });

        registrarseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        String nombre = nombreEditText.getText().toString();
        String apellido = apellidoEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("name", nombre);
            values.put("lastname", apellido);
            values.put("email", email);
            values.put("password", password);
            values.put("is_active", 1);
            values.put("is_staff", 0);

            long newRowId = db.insert("Usuario", null, values);
            if (newRowId != -1) {
                Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistroActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
