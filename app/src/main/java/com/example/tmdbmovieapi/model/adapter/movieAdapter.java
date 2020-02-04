package com.example.tmdbmovieapi.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.tmdbmovieapi.R;
import com.example.tmdbmovieapi.model.pojo.movieslist;
import com.example.tmdbmovieapi.view.movie;

import java.util.List;

public class movieAdapter extends RecyclerView.Adapter<movieAdapter.ViewHolder>  {
    @NonNull

    private Context context;
    private List <movieslist> movieslists;

    public movieAdapter(List<movieslist> movieslists,Context context){
        this.movieslists = movieslists;
        this.context = context;
    }

    public void addmovies(List<movieslist> flower) {
        movieslists = flower;
        notifyDataSetChanged();
    }

    @Override
    public movieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_details,parent,false);

        return new movieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull movieAdapter.ViewHolder holder, final int position) {
        movieslist c = movieslists.get(position);
        holder.date.setText(String.valueOf(c.getRelease_date()));
        holder.rating.setText(String.valueOf(c.getVote_average()));
        holder.name.setText(String.valueOf(c.getOriginal_title()));

        if(c.getPoster_path() != null){


            Glide.with(context).load("https://image.tmdb.org/t/p/w500//" + c.getPoster_path()).into(holder.imageView);

        }else {
            holder.imageView.setImageResource(R.drawable.noimage);


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context.getApplicationContext(), movie.class);
                intent.putExtra("MyClass", movieslists.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//not recommend
                context.getApplicationContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movieslists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,date,rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            name = itemView.findViewById(R.id.movie_name);
            date = itemView.findViewById(R.id.releasedate);
            rating = itemView.findViewById(R.id.rating);

        }
    }
}
