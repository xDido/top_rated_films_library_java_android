package com.example.recyclerdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class AdvancedRecycleAdapter extends RecyclerView.Adapter<AdvancedRecycleAdapter.ViewHolder> {
    ArrayList<Movie> models = new ArrayList<>();
    public static final String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w185";

    public AdvancedRecycleAdapter(ArrayList<Movie> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = models.get(position);
        holder.headerTxt.setText(movie.getTitle());
        holder.descTxt.setText(movie.getRelease_date());
        Picasso.with(holder.img.getContext()).load(IMAGE_BASE_URL + movie.getPoster_path()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return models == null ? 0 : models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView headerTxt;
        TextView descTxt;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            intUI(itemView);
        }

        private void intUI(View itemView) {
            headerTxt = itemView.findViewById(R.id.header);
            descTxt = itemView.findViewById(R.id.sub);
            img = itemView.findViewById(R.id.imageView);
        }
    }

    public void setData(ArrayList<Movie> data) {
        if (models != null) {
            models.clear();
            models.addAll(data);
            notifyDataSetChanged();
        }
    }
}
