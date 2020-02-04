package com.example.tmdbmovieapi.controller;
import com.example.tmdbmovieapi.model.api.tdmovieapi;
import com.example.tmdbmovieapi.model.pojo.genremain;
import com.example.tmdbmovieapi.model.pojo.similarmovielist;
import com.example.tmdbmovieapi.model.pojo.similarmovielistmain;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class movie_controller {

    private MovieCallbackListener mListener;

    public static Retrofit retrofit = null;

    private List <similarmovielist> data = new ArrayList <>();

    //genres
    StringBuilder genre = new StringBuilder();


    public movie_controller(MovieCallbackListener listener) {
        mListener = listener;
    }

    public interface MovieCallbackListener {

        void onFetchStart();
        void onFetchCompletegenre(StringBuilder genre, genremain genremain);
        void onFetchFailedGenre(String failedgenremessage);
        void onFetchCompletesimilarmovielist(List <similarmovielist> data);
        void onFetchFailedSimilarMovielist(String failedsimilarmessage);
    }

    public void startFetchinggenre(String id) {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tdmovieapi tdmovieapi1 = retrofit.create(tdmovieapi.class);
        Call <genremain> call = tdmovieapi1.getgenre(id);
        call.enqueue(new Callback <genremain>() {
            @Override
            public void onResponse(Call  <genremain> call, retrofit2.Response <genremain> response) {
                genremain genremain = response.body();
                //System.out.println("genr,jb");
                //System.out.println(genremain.getGenres());

                for (int i =0;i<genremain.getGenres().size();i++){
                    genre.append(genremain.getGenres().get(i).getName() + " ");
                }

                mListener.onFetchCompletegenre(genre, genremain);

            }

            @Override
            public void onFailure(Call <genremain> call, Throwable t) {
                System.out.println(t.getMessage());
                mListener.onFetchFailedGenre(t.getMessage());
            }
        });
    }

    public void startFetchingSimilarMovielist(String id){

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tdmovieapi tdmovieapi1 = retrofit.create(tdmovieapi.class);
        Call <similarmovielistmain> call = tdmovieapi1.getsimilarlist(id);
        call.enqueue(new Callback <similarmovielistmain>() {
            @Override
            public void onResponse(Call  <similarmovielistmain> call, retrofit2.Response <similarmovielistmain> response) {
                //System.out.println("jnnkn");
                //System.out.println(response.body());

                similarmovielistmain similarmovielistmain = response.body();
                data = similarmovielistmain.getResults();

                mListener.onFetchCompletesimilarmovielist(data);


            }

            @Override
            public void onFailure(Call <similarmovielistmain> call, Throwable t) {
                System.out.println(t.getMessage());
                mListener.onFetchFailedSimilarMovielist(t.getMessage());


            }
        });

    }

    }
