package com.capacitapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.R;
import com.capacitapp.VideoActivity;
import com.capacitapp.models.Curso;

import java.util.List;

public class MisCursosAdapter extends RecyclerView.Adapter<MisCursosAdapter.MisCursosViewHolder> {
    private Context context;
    private List<Curso> cursos;

    public MisCursosAdapter(Context context, List<Curso> cursos) {
        this.context = context;
        this.cursos = cursos;
    }

    @NonNull
    @Override
    public MisCursosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new MisCursosViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MisCursosViewHolder holder, int position) {
        Curso curso = cursos.get(position);
        holder.textViewCursoName.setText(curso.getName());
        holder.checkBoxCursoLink.setText("Ver curso");

        holder.checkBoxCursoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoActivity.class);
                intent.putExtra("videoUrl", curso.getLink());
                System.out.println("Url: "+curso.getLink());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    static class MisCursosViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCursoName;
        CheckBox checkBoxCursoLink;

        public MisCursosViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCursoName = itemView.findViewById(R.id.textViewCursoName);
            checkBoxCursoLink = itemView.findViewById(R.id.checkbox);
        }
    }
}
