package com.capacitapp.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.models.Curso;
import com.capacitapp.R;
import com.capacitapp.adapters.MisCursosAdapter;

import java.util.ArrayList;
import java.util.List;

public class MisCursosFragment extends Fragment {

    private RecyclerView recyclerViewMisCursos;
    private List<Curso> misCursosList;
    private MisCursosAdapter misCursosAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_cursos, container, false);

        recyclerViewMisCursos = view.findViewById(R.id.recyclerViewMisCursos);
        recyclerViewMisCursos.setLayoutManager(new LinearLayoutManager(getContext()));
        misCursosList = generateMisCursos(); // Generar lista de mis cursos (simulado)
        misCursosAdapter = new MisCursosAdapter(misCursosList, null); // El listener se establecerá más tarde
        recyclerViewMisCursos.setAdapter(misCursosAdapter);

        return view;
    }

    // Método para generar lista de mis cursos (simulado)
    private List<Curso> generateMisCursos() {
        List<Curso> misCursos = new ArrayList<>();
        misCursos.add(new Curso(1, "imgmicurso", "Mi Curso 1", "Descripción de Mi Curso 1", 19.99));
        misCursos.add(new Curso(2, "imgmicurso", "Mi Curso 2", "Descripción de Mi Curso 2", 24.99));
        // Agregar más cursos según sea necesario
        return misCursos;
    }
}

