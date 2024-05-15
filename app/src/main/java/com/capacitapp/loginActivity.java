package com.capacitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginActivity extends AppCompatActivity {

    EditText tvEmail, tvPass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        tvEmail = findViewById(R.id.editTextTextEmailAddress);
        tvPass = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login() {
        String useremail = tvEmail.getText().toString();
        String password = tvPass.getText().toString();

        if (useremail.equals("") || password.equals("")) {
            Toast.makeText(loginActivity.this, "Email o contraseña no ingresado", Toast.LENGTH_LONG).show();
        } else if (useremail.equals("admin") && password.equals("1234")) {
            Intent i = new Intent(loginActivity.this, MainActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(loginActivity.this, "Email o Password erroneos", Toast.LENGTH_LONG).show();
        }
    }
}