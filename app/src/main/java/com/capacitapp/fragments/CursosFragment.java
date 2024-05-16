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
import com.capacitapp.adapters.CursosAdapter;

import java.util.ArrayList;
import java.util.List;

public class CursosFragment extends Fragment {

    private RecyclerView recyclerViewCursos;
    private List<Curso> cursosList;
    private CursosAdapter cursosAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cursos, container, false);

        cursosList = new ArrayList<Curso>();

        recyclerViewCursos = view.findViewById(R.id.recyclerViewCursos);
        recyclerViewCursos.setLayoutManager(new LinearLayoutManager(getContext()));
        cursosList = generateCursos();
        cursosAdapter = new CursosAdapter(cursosList, null);
        recyclerViewCursos.setAdapter(cursosAdapter);

        return view;
    }

    // Método para generar lista de cursos (simulado)
    private List<Curso> generateCursos() {
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso(1, "imgcurso", "Curso 1", "Descripción del Curso 1", 9.99));
        cursos.add(new Curso(2, "imgcurso2", "Curso 2", "Descripción del Curso 2", 14.99));
        cursos.add(new Curso(3, "imgcurso", "Curso 3", "Descripción del Curso 3", 12.99));
        cursos.add(new Curso(4, "imgcurso1", "Curso 4", "Descripción del Curso 4", 19.99));
        // Agregar más cursos según sea necesario
        return cursos;
    }
}
