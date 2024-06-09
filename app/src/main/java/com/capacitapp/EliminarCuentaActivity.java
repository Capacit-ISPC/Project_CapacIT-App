package com.capacitapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.capacitapp.DBHelper.DBHelper;
import com.capacitapp.utils.UserPreferences;

public class EliminarCuentaActivity extends AppCompatActivity {

    private ImageView imgBackArrow;

    private Button btnCancelar, btnEliminarCuenta;
    private RadioButton radioButtonEliminar;
    private DBHelper dbHelper;
    private int userId;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eliminar_cuenta);

        imgBackArrow = findViewById(R.id.btn_back);
        btnCancelar = findViewById(R.id.btn_cancelar_eliminar);
        btnEliminarCuenta = findViewById(R.id.btn_eliminar_cuenta);
        radioButtonEliminar = findViewById(R.id.radioButton_eliminar);

        dbHelper = new DBHelper(this);
        //SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        //userId = prefs.getInt("userId", -1);
        //userId = getIntent().getIntExtra("userId", 0);
        userId = UserPreferences.getLoggedUserId(this);

        Log.d("EliminarCuentaActivity", "ID del usuario: " + userId);
        btnEliminarCuenta.setEnabled(false); // Desactivar botón eliminar al inicio


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EliminarCuentaActivity.this, PerfilActivity.class);
                startActivity(intent);

            }
        });

        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarCuentaActivity.this, PerfilActivity.class);
                startActivity(intent);

            }
        });
        btnEliminarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                //int userId = prefs.getInt("userId", -1);

                if (userId != -1) {

                    dbHelper.deleteUser(userId);

                    //SharedPreferences.Editor editor = prefs.edit();
                    //editor.remove("userId");
                    //editor.apply();

                    UserPreferences.clearUserPreferences(EliminarCuentaActivity.this);

                    Toast.makeText(EliminarCuentaActivity.this, "Cuenta eliminada con éxito", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EliminarCuentaActivity.this, loginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(EliminarCuentaActivity.this, "Error al obtener el ID de usuario", Toast.LENGTH_SHORT).show();
                    Log.d("EliminarCuentaActivity", "Error al obtener el ID de usuario");
                }
                dbHelper.close();
            }
        });
    }
}