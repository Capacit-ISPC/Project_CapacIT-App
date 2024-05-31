package com.capacitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerPerfilActivity extends AppCompatActivity {

    private ImageView imgBackArrow;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_perfil);

        imgBackArrow = findViewById(R.id.btn_back);
        btn = findViewById(R.id.btn_ireditarperfil);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(VerPerfilActivity.this, ConfiguracionActivity.class);
            startActivity(intent);
            }
        });

        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerPerfilActivity.this, PerfilActivity.class);
                startActivity(intent);

            }
        });

    }
}