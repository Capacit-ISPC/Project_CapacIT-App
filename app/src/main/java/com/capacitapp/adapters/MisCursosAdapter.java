package com.capacitapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capacitapp.R;
import com.capacitapp.models.ItemData;

import java.util.List;

public class MisCursosAdapter extends RecyclerView.Adapter<MisCursosAdapter.ViewHolder> {

    private List<ItemData> dataList;

    public MisCursosAdapter(List<ItemData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemData item = dataList.get(position);
        holder.textView.setText(item.getText());

        // Clear the LinearLayout to avoid duplicating CheckBoxes on scroll
        holder.expandableLayout.removeAllViews();

        // Add CheckBoxes dynamically
        for (String checkBoxText : item.getCheckBoxItems()) {
            CheckBox checkBox = new CheckBox(holder.itemView.getContext());
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            checkBox.setText(checkBoxText);
            holder.expandableLayout.addView(checkBox);
        }

        if (item.isExpanded()) {
            holder.expandableLayout.setVisibility(View.VISIBLE);
            holder.imageView.setRotation(180); // Rotates arrow icon
        } else {
            holder.expandableLayout.setVisibility(View.GONE);
            holder.imageView.setRotation(0); // Resets arrow icon rotation
        }

        holder.itemView.setOnClickListener(v -> {
            item.setExpanded(!item.isExpanded());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout expandableLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);
        }
    }
}
