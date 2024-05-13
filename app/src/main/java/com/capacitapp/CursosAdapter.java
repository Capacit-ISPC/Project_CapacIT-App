package com.capacitapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.Curso;
import com.capacitapp.OnItemClickListener;
import com.capacitapp.R;

import java.util.List;

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.CursoViewHolder> {

    private List<Curso> cursosList;
    private Context context;
    private OnItemClickListener listener;

    public CursosAdapter(List<Curso> cursosList, OnItemClickListener listener) {
        this.cursosList = cursosList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.cardview_curso, parent, false);
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = cursosList.get(position);


        int resourceId = context.getResources().getIdentifier(curso.getImg(), "drawable", context.getPackageName());
        if (resourceId != 0) {
            holder.imgCurso.setImageResource(resourceId);
        } else {
            holder.imgCurso.setImageResource(R.drawable.imgcurso1);
        }

        holder.textViewTituloCurso.setText(curso.getTitulo());
        holder.textViewDescripcion.setText(curso.getDescripcion());
        holder.textViewPrecio.setText(String.valueOf(curso.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return cursosList.size();
    }

    public class CursoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imgCurso;
        TextView textViewTituloCurso;
        TextView textViewDescripcion;
        TextView textViewPrecio;

        public CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCurso = itemView.findViewById(R.id.imgFoto);
            textViewTituloCurso = itemView.findViewById(R.id.tvNombreCV);
            textViewDescripcion = itemView.findViewById(R.id.textView);
            textViewPrecio = itemView.findViewById(R.id.tvPrecioCurso);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Manejar el clic en el elemento del RecyclerView
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(cursosList.get(position));
                }
            }
        }
    }
}

