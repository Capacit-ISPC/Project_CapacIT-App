package com.capacitapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.models.Curso;
import com.capacitapp.OnItemClickListener;
import com.capacitapp.R;

import java.util.List;

public class MisCursosAdapter extends RecyclerView.Adapter<MisCursosAdapter.MisCursoViewHolder> {

    private List<Curso> misCursosList;
    private Context context;
    private OnItemClickListener listener;

    public MisCursosAdapter(List<Curso> misCursosList, OnItemClickListener listener) {
        this.misCursosList = misCursosList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MisCursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mi_curso, parent, false);
        this.context = parent.getContext();
        return new MisCursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MisCursoViewHolder holder, int position) {
        Curso curso = misCursosList.get(position);

        // Manejar el caso en que getImg() devuelve un nombre de recurso de imagen como String
        int resourceId = context.getResources().getIdentifier(curso.getImg(), "drawable", context.getPackageName());
        if (resourceId != 0) {
            holder.imgViewMyCourse.setImageResource(resourceId);
        } else {
            holder.imgViewMyCourse.setImageResource(R.drawable.imgmicurso_1);
        }
        holder.bind(curso, listener);
    }

    @Override
    public int getItemCount() {
        return misCursosList.size();
    }

    public static class MisCursoViewHolder extends RecyclerView.ViewHolder {

        ImageView imgViewMyCourse;
        TextView tvTitle;
        //ImageView imageView3;
        RelativeLayout containerMain;

        public MisCursoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewMyCourse = itemView.findViewById(R.id.imgViewMyCourse);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            containerMain = itemView.findViewById(R.id.containerMain);
        }

        public void bind(final Curso curso, final OnItemClickListener listener) {
            imgViewMyCourse.setImageResource(R.drawable.imgmicurso_1); // Imagen por defecto

            tvTitle.setText(curso.getTitulo());

            containerMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(curso);
                    }
                }
            });


        }
    }
}
