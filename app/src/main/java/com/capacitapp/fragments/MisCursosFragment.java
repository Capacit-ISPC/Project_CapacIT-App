package com.capacitapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.DBHelper.DBHelper;
import com.capacitapp.R;
import com.capacitapp.adapters.MisCursosAdapter;
import com.capacitapp.models.Curso;
import com.capacitapp.utils.UserPreferences;

import java.util.List;

public class MisCursosFragment extends Fragment {
    private RecyclerView recyclerView;
    private MisCursosAdapter adapter;
    private DBHelper dbHelper;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        dbHelper = new DBHelper(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_cursos, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewMisCursos);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        int userId = UserPreferences.getLoggedUserId(context); // Obtén el ID del usuario logueado
        List<Curso> cursos = dbHelper.getCursosByUserId(userId); // Obtén la lista de cursos

        adapter = new MisCursosAdapter(context, cursos);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
