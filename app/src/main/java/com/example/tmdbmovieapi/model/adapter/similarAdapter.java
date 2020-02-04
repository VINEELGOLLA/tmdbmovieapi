package com.example.tmdbmovieapi.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tmdbmovieapi.R;
import com.example.tmdbmovieapi.controller.movie_controller;
import com.example.tmdbmovieapi.model.pojo.similarmovielist;

import java.util.List;

public class similarAdapter extends RecyclerView.Adapter<similarAdapter.ViewHolder> {
    @NonNull

    private Context context;
    private List <similarmovielist> movieslists;

    public similarAdapter(List<similarmovielist> movieslists,Context context){
        this.movieslists = movieslists;
        this.context = context;
    }

    public void addmovies(List<similarmovielist> flower) {
        movieslists = flower;
        notifyDataSetChanged();
    }
    @Override
    public similarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.similar,parent,false);

        return new similarAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull similarAdapter.ViewHolder holder, int position) {
        similarmovielist c = movieslists.get(position);
        if(c.getPoster_path() == "null"){

            holder.imageView.setImageResource(R.drawable.noimage);
        }else {

            Glide.with(context).load("https://image.tmdb.org/t/p/w500//" + c.getPoster_path()).into(holder.imageView);
        }

        holder.name.setText(c.getOriginal_title());




    }

    @Override
    public int getItemCount() {
        return movieslists.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.mname);

        }
    }
}
