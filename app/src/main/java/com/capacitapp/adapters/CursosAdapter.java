package com.capacitapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.models.Curso;
import com.capacitapp.R;

import java.util.List;

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.CursoViewHolder> {

    private List<Curso> cursosList;

    public CursosAdapter(List<Curso> cursosList) {
        this.cursosList = cursosList;
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_curso, parent, false);
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = cursosList.get(position);
        holder.bind(curso);
    }

    @Override
    public int getItemCount() {
        return cursosList.size();
    }


    public static class CursoViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreCurso;
        TextView tvDescripcionCurso;
        TextView tvPrecioCurso;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombreCurso = itemView.findViewById(R.id.tvNombreCV);
            tvDescripcionCurso = itemView.findViewById(R.id.textView_nombreconfig);
            tvPrecioCurso = itemView.findViewById(R.id.tvPrecioCurso);
        }

        public void bind(Curso curso) {
            tvNombreCurso.setText(curso.getName());
            tvDescripcionCurso.setText(curso.getDescription());
            tvPrecioCurso.setText(String.valueOf(curso.getPrice()));
        }
    }
}
