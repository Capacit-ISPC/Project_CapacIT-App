package com.capacitapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.R;
import com.capacitapp.adapters.MisCursosAdapter;
import com.capacitapp.models.ItemData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MisCursosFragment extends Fragment {
    private RecyclerView recyclerView;
    private MisCursosAdapter adapter;
    private List<ItemData> itemList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_cursos, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewMisCursos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Aquí deberías crear y poblar itemList con tus datos
        itemList = new ArrayList<>();
        List<String> checkBoxItems1 = Arrays.asList("Clase 1", "Clase 2", "Clase 3");
        List<String> checkBoxItems2 = Arrays.asList("Clase 1", "Clase 2");
        itemList.add(new ItemData("Angular", checkBoxItems1));
        itemList.add(new ItemData("Django", checkBoxItems2));
        // Agrega más elementos según sea necesario

        adapter = new MisCursosAdapter(itemList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
