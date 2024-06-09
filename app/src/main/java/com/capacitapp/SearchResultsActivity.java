package com.capacitapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.DBHelper.DBHelper;
import com.capacitapp.adapters.CursosAdapter;
import com.capacitapp.models.Curso;

import android.widget.SearchView;
import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        String query = getIntent().getStringExtra("query");

        TextView resultTextView = findViewById(R.id.resultTextView);
        TextView noResultsTextView = findViewById(R.id.noResultsTextView);
        ImageView imgBackArrow = findViewById(R.id.btn_back);

        String searchResults = getString(R.string.search_results, query);

        resultTextView.setText(searchResults);

        DBHelper dbHelper = new DBHelper(this);


        imgBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchResultsActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });

        //Cargar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerSearchCoursesView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Realizando la búsqueda y actualizando el RecyclerView
        List<Curso> cursos = dbHelper.searchCursos(query);
        if (cursos.isEmpty()) {
            // Mostrar mensaje de sin resultados
            noResultsTextView.setVisibility(TextView.VISIBLE);
            recyclerView.setVisibility(RecyclerView.GONE);
        } else {
            // Mostrar resultados encontrados
            noResultsTextView.setVisibility(TextView.GONE);
            recyclerView.setVisibility(RecyclerView.VISIBLE);
            CursosAdapter cursosAdapter = new CursosAdapter(cursos);
            recyclerView.setAdapter(cursosAdapter);
        }

        //funcion de busqueda
        SearchView searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String newQuery) {
                Intent newIntent = new Intent(SearchResultsActivity.this, SearchResultsActivity.class);
                newIntent.putExtra("query", newQuery);
                startActivity(newIntent);
                finish(); // Cerrar activity actual
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
