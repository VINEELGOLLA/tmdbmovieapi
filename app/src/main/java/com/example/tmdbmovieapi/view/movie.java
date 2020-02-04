package com.example.tmdbmovieapi.view;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.tmdbmovieapi.R;
import com.example.tmdbmovieapi.controller.movie_controller;
import com.example.tmdbmovieapi.model.pojo.movieslist;
import com.example.tmdbmovieapi.model.pojo.genremain;
import com.example.tmdbmovieapi.model.pojo.similarmovielist;
import com.example.tmdbmovieapi.model.adapter.similarAdapter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;
import java.util.ArrayList;
import java.util.List;

public class movie extends AppCompatActivity implements movie_controller.MovieCallbackListener, InternetConnectivityListener {

    ImageView imageView;
    TextView name;
    TextView overview,mgenre,releasedate,runtime,tagline,status;

    private movie_controller movie_controller;

    private String message = "NO INTERNET";

    private boolean status1;



    RecyclerView recyclerView;

    private List <similarmovielist> data = new ArrayList <>();

    private similarAdapter adapter;

    private  movieslist movieslist;

    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    private Snackbar snackbar;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);




        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("");


        snackbar = Snackbar
                .make(findViewById(R.id.backimage), message, Snackbar.LENGTH_LONG);

        //getSupportActionBar().hide();

        movie_controller = new movie_controller(movie.this);


        configViews();

        Intent intent = getIntent();
        movieslist= (com.example.tmdbmovieapi.model.pojo.movieslist) intent.getSerializableExtra("MyClass");
        System.out.println(movieslist);

        Glide.with(this).load("https://image.tmdb.org/t/p/w500//" + movieslist.getBackdrop_path()).into(imageView);

        System.out.println("lolpop");

        name.setText(movieslist.getOriginal_title());

        overview.setText(movieslist.getOverview());

        /*movie_controller.startFetchinggenre(movieslist.getId());

        movie_controller.startFetchingSimilarMovielist(movieslist.getId());*/

        InternetAvailabilityChecker.init(this);

        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        mInternetAvailabilityChecker.addInternetConnectivityListener(this);




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void fetch(){

        movie_controller.startFetchinggenre(movieslist.getId());

        movie_controller.startFetchingSimilarMovielist(movieslist.getId());

    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);


    }

    private void configViews() {

        imageView = findViewById(R.id.backimage);
        name = findViewById(R.id.movie_name);
        overview = findViewById(R.id.overview);
        mgenre = findViewById(R.id.genre);
        releasedate = findViewById(R.id.releasedate);
        runtime = findViewById(R.id.runtime);
        tagline = findViewById(R.id.tagline);
        status = findViewById(R.id.status);




        recyclerView = findViewById(R.id.recyclerview2);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(movie.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);
        adapter = new similarAdapter(data, getApplicationContext());
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onFetchStart() {

    }


    @Override
    public void onFetchCompletesimilarmovielist(List <similarmovielist> data) {
        adapter.addmovies(data);
    }

    @Override
    public void onFetchFailedSimilarMovielist(String failedsimilarmessage) {
        Toast.makeText(getApplicationContext(),failedsimilarmessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFetchCompletegenre(StringBuilder genre,genremain genremains) {
        mgenre.setText(genre);
        releasedate.setText("Release Date: "+ genremains.getRelease_date());
        runtime.setText("Runtime: "+genremains.getRuntime());
        tagline.setText("Tagline: " + genremains.getTagline());
        status.setText("Status: " + genremains.getStatus());


    }

    @Override
    public void onFetchFailedGenre(String failedgenremessage) {
        Toast.makeText(getApplicationContext(),failedgenremessage,Toast.LENGTH_LONG).show();

    }


    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {

        if(isConnected == false){
            status1 = false;
            showsnack();

        }
        else{
            fetch();
            status1 = true;
            snackbar.dismiss();
        }

    }



    private void showsnack() {


        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        snackbar.setDuration(BaseTransientBottomBar.LENGTH_INDEFINITE);
        snackbar.show();
    }
}
