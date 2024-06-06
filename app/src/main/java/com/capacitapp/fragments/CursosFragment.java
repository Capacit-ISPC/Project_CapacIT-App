package com.capacitapp.fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.DBHelper.DBHelper;
import com.capacitapp.R;
import com.capacitapp.adapters.CursosAdapter;
import com.capacitapp.models.Curso;

import java.util.ArrayList;
import java.util.List;

public class CursosFragment extends Fragment {


    private RecyclerView recyclerViewCursos;
    private RecyclerView recyclerViewSearchResults;
    private List<Curso> cursosList;
    private List<Curso> searchResultsList;
    private CursosAdapter cursosAdapter;
    private CursosAdapter searchResultsAdapter;
    private DBHelper dbHelper;
    private SearchView searchView;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cursos, container, false);

        cursosList = new ArrayList<>();
        searchResultsList = new ArrayList<>();
        recyclerViewCursos = view.findViewById(R.id.recyclerViewCursos);
        recyclerViewSearchResults = view.findViewById(R.id.recyclerViewSearchResults);
        searchView = view.findViewById(R.id.search);

        recyclerViewCursos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearchResults.setLayoutManager(new LinearLayoutManager(getContext()));

        dbHelper = new DBHelper(getContext());
        cargarCursosDesdeDB();

        cursosAdapter = new CursosAdapter(cursosList);
        searchResultsAdapter = new CursosAdapter(searchResultsList);

        recyclerViewCursos.setAdapter(cursosAdapter);
        recyclerViewSearchResults.setAdapter(searchResultsAdapter);

        return view;
    }

    private void cargarCursosDesdeDB() {
        Cursor cursor = dbHelper.getCursos();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                System.out.println(cursor);
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String language = cursor.getString(cursor.getColumnIndexOrThrow("language"));
                String technology = cursor.getString(cursor.getColumnIndexOrThrow("technology"));
                String level = cursor.getString(cursor.getColumnIndexOrThrow("level"));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));
                String link = cursor.getString(cursor.getColumnIndexOrThrow("link"));
                String teacher_name = cursor.getString(cursor.getColumnIndexOrThrow("teacher_name"));

                Curso curso = new Curso(id, name, description, language, technology, level, price, link, teacher_name);
                cursosList.add(curso);
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Toast.makeText(getContext(), "No se encontraron cursos en la base de datos", Toast.LENGTH_SHORT).show();
        }
    }
    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                handleSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handleSearch(newText);
                return true;
            }
        });
    }

    private void handleSearch(String query) {
        searchResultsList.clear();
        for (Curso curso : cursosList) {
            if (curso.getName().toLowerCase().contains(query.toLowerCase())) {
                searchResultsList.add(curso);
            }
        }
        if (searchResultsList.isEmpty()) {
            recyclerViewSearchResults.setVisibility(View.GONE);
        } else {
            searchResultsAdapter.updateData (searchResultsList);
            recyclerViewSearchResults.setVisibility(View.VISIBLE);
        }
    }
}