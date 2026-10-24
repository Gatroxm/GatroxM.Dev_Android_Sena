package com.example.proyectogatroxmdev;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder> {

    private final Context context;
    private final List<CatalogItem> itemList;

    public CatalogAdapter(Context context, List<CatalogItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_catalog_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CatalogItem item = itemList.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvCategory.setText(item.getCategory());
        holder.tvDescription.setText(item.getDescription());
        holder.ivImage.setImageResource(item.getImageResId());

        holder.btnExplore.setOnClickListener(v -> 
            Toast.makeText(context, "Explorando: " + item.getTitle(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public int getItemCount() {
        return itemList != null ? itemList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvCategory, tvTitle, tvDescription;
        MaterialButton btnExplore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivCardImage);
            tvCategory = itemView.findViewById(R.id.tvCardCategory);
            tvTitle = itemView.findViewById(R.id.tvCardTitle);
            tvDescription = itemView.findViewById(R.id.tvCardDescription);
            btnExplore = itemView.findViewById(R.id.btnCardExplore);
        }
    }
}
