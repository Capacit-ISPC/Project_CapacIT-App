package com.capacitapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.capacitapp.DBHelper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PerfilActivity extends AppCompatActivity {

    private ImageView imgBackArrow;
    private TextView textConfiguracion;
    private TextView textEliminarCuenta;

//    private TextView textNombre;

//    DBHelper conn;
//    List<String> item =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil);

        imgBackArrow = findViewById(R.id.btn_back);
        textConfiguracion=findViewById(R.id.textConfig);

        textEliminarCuenta=findViewById(R.id.textCloseCount);

    //    textNombre = findViewById(R.id.textViewName);

    //   showUser();
    //    conn = new DBHelper(getApplicationContext());


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
    }

    //private void showUser() {
    //conn = new DBHelper(this);
    //   Cursor c = conn.getUsuario();
    //    item = new ArrayList<String>();
    //    String Name = "";

    //    if(c.moveToFirst()){
    //        do {
    //            Name = c.getString(0);
    //            item.add(Name);

    //        }while (c.moveToNext());
    //    }

    //    ArrayAdapter<String> adaptor =
    //            new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, item);
    //    textNombre..setAdapter(adaptor);

    //}
}