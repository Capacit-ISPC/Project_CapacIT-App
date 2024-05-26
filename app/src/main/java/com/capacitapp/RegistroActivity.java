package com.capacitapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroActivity extends AppCompatActivity {

    BaseDeDatos mibd;
    EditText nombreEditText, apellidoEditText ,emailEditText, passwordEditText;
    Button registerButton;
    ImageView imgBackArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        mibd = new BaseDeDatos(this);

        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        registerButton = findViewById(R.id.button_registrarse);

        imgBackArrow = findViewById(R.id.btn_back);

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String nombre = nombreEditText.getText().toString();
                String apellido = apellidoEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(apellido) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(RegistroActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                mibd.insertarUsuario(nombre, apellido, email, password);

                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(RegistroActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistroActivity.this, InicioActivity.class));
                finish();
            }
        });
    }
}