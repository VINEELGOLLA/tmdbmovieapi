package com.example.tmdbmovieapi.controller;

import com.example.tmdbmovieapi.model.api.tdmovieapi;
import com.example.tmdbmovieapi.model.pojo.movieslist;
import com.example.tmdbmovieapi.model.pojo.tdmovieresults;
import com.example.tmdbmovieapi.model.pojo.searchname;
import com.example.tmdbmovieapi.model.pojo.searchnamemain;



import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_controller {

    private FlowerCallbackListener mListener;

    //retrofit
    public static Retrofit retrofit = null;

    private List<movieslist> data = new ArrayList <>();

    private List<searchname> data1 = new ArrayList <>();




    public MainActivity_controller(FlowerCallbackListener listener) {
        mListener = listener;
    }

    public void fetchnames(String name){

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //System.out.println("lol1   " +name);

        tdmovieapi tdmovieapi1 = retrofit.create(tdmovieapi.class);
        Call <searchnamemain> call = tdmovieapi1.getnames(name);
        call.enqueue(new Callback <searchnamemain>() {
            @Override
            public void onResponse(Call  <searchnamemain> call, Response <searchnamemain> response) {
                //System.out.println("jnnkn");

                searchnamemain searchnamemain = response.body();

                if(searchnamemain != null) {
                    data1 = searchnamemain.getResults();
                    mListener.onFetchsearchresultComplete(data1);
                }
            }

            @Override
            public void onFailure(Call <searchnamemain> call, Throwable t) {
                System.out.println(t.getMessage());
                //mListener.onFetchFailed(t.getMessage());

            }
        });


    }

    public void startFetching(String name) {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //System.out.println(name);

        tdmovieapi tdmovieapi1 = retrofit.create(tdmovieapi.class);
        Call <tdmovieresults> call = tdmovieapi1.getlist(name);
        call.enqueue(new Callback <tdmovieresults>() {
            @Override
            public void onResponse(Call  <tdmovieresults> call, Response <tdmovieresults> response) {
                //System.out.println("jnnkn");
                //System.out.println(response.body().getResults());

                tdmovieresults tdmovieresults = response.body();
                data = tdmovieresults.getResults();

                mListener.onFetchComplete(data);
            }

            @Override
            public void onFailure(Call <tdmovieresults> call, Throwable t) {
                System.out.println(t.getMessage());
                mListener.onFetchFailed(t.getMessage());

            }
        });

    }


        public interface FlowerCallbackListener {

        void onFetchStart();
        void onFetchComplete(List<movieslist> data);
        void onFetchFailed(String messagefailed);
        void onFetchsearchresultComplete(List<searchname> name);
    }

}
