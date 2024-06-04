package com.capacitapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.capacitapp.DBHelper.DBHelper;
import com.capacitapp.models.Usuario;
import com.capacitapp.utils.UserPreferences;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class PerfilActivity extends AppCompatActivity {

    private ImageView imgBackArrow;
    private TextView textConfiguracion;
    private TextView textEliminarCuenta;

    private TextView nombreText;
    private DBHelper dbHelper;
    private Context context;
    private int currentUserId;
    private Button cerrarSesion;

    private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        profileImageView = findViewById(R.id.imageViewProfile);

        context = this;
        dbHelper = new DBHelper(context);

        // Obtener referencias a los TextInputEditText
        nombreText = findViewById(R.id.textViewName);

        imgBackArrow = findViewById(R.id.btn_back);
        textConfiguracion=findViewById(R.id.textConfig);

        textEliminarCuenta=findViewById(R.id.textCloseCount);

        cerrarSesion = findViewById(R.id.cerrarSesión);



        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });

        textEliminarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PerfilActivity.this, EliminarCuentaActivity.class);
                startActivity(intent);

            }
        });

        textConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, ConfiguracionActivity.class);
                startActivity(intent);

            }
        });
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, loginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loadUserData();
        loadProfileImage();

        }

    private void loadProfileImage() {
        String imageUri = UserPreferences.getUserProfileImage(this);
        if (imageUri != null) {
            profileImageView.setImageURI(Uri.parse(imageUri));
        }
    }

    private void loadUserData() {        currentUserId = UserPreferences.getLoggedUserId(context); // Obtén el ID del usuario logueado
        Usuario userPerfil = dbHelper.getUserById(currentUserId);
        if (userPerfil != null) {
            nombreText.setText(userPerfil.getName());

        }
    }

}