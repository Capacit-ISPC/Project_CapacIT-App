package com.capacitapp;


import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;


import com.capacitapp.AccountManagerHelper.AccountManagerHelper;


public class loginActivity extends AppCompatActivity {
    private EditText tvEmail, tvPass;
    private com.capacitapp.DBHelper.DBHelper dbHelper;
    private AccountManagerHelper accountManagerHelper;
    private static final int PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        requestPermissionsIfNeeded();

        ImageView imgBackArrow = findViewById(R.id.btn_back);
        tvEmail = findViewById(R.id.editTextTextEmailAddress);
        tvPass = findViewById(R.id.editTextTextPassword);
        Button btn = findViewById(R.id.button2);
        dbHelper = new com.capacitapp.DBHelper.DBHelper(this);

        accountManagerHelper = new AccountManagerHelper(this);

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

    private void requestPermissionsIfNeeded() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.GET_ACCOUNTS,
                    //Manifest.permission.MANAGE_ACCOUNTS,
                    //Manifest.permission.AUTHENTICATE_ACCOUNTS,
                    //Manifest.permission.USE_CREDENTIALS,
            }, PERMISSION_REQUEST_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso concedido, puedes añadir cuentas ahora
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show();
            } else {
                // Permiso denegado, muestra un mensaje al usuario o realiza alguna otra acción
                Toast.makeText(this, "Permisos necesarios no concedidos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void login() {
        String useremail = tvEmail.getText().toString();
        String password = tvPass.getText().toString();

        if (useremail.isEmpty() || password.isEmpty()) {
            Toast.makeText(loginActivity.this, "Email o contraseña no ingresado", Toast.LENGTH_LONG).show();
            Log.d(TAG, "Email o contraseña no ingresado");
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
            Log.d(TAG, "Credenciales correctas");
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "Permiso GET_ACCOUNTS concedido, iniciando sesión con AccountManagerHelper");
                accountManagerHelper.login(this, useremail, password);
            } else {
                // Credenciales incorrectas
                Log.d(TAG, "Permiso GET_ACCOUNTS no concedido, solicitando permisos");
                requestPermissionsIfNeeded();
            }
        } else {
            // Credenciales incorrectas
            Toast.makeText(loginActivity.this, "Email o contraseña incorrectos", Toast.LENGTH_LONG).show();
            Log.d(TAG, "Email o contraseña incorrectos");
        }

        if (cursor != null) {
            cursor.close();
        }


    }

}