package com.capacitapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.R;
import com.capacitapp.models.Curso;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {

    private List<Curso> cursos;

    public CursoAdapter(List<Curso> cursos) {
        this.cursos = cursos;
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_curso, parent, false);
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = cursos.get(position);
        holder.nameTextView.setText(curso.getName());
        // Set other fields as needed
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    public void updateCursos(List<Curso> newCursos) {
        cursos.clear();
        cursos.addAll(newCursos);
        notifyDataSetChanged();
    }

    static class CursoViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        // Other fields

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.curso_name);
            // Initialize other fields
        }
    }
}

