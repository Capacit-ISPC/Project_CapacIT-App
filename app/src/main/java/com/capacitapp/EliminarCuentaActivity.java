package com.capacitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class EliminarCuentaActivity extends AppCompatActivity {

    private ImageView imgBackArrow;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eliminar_cuenta);

        imgBackArrow = findViewById(R.id.btn_back);
        btn = findViewById(R.id.btn_cancelar_eliminar);

        btn.setOnClickListener(new View.OnClickListener() {
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
    }
}