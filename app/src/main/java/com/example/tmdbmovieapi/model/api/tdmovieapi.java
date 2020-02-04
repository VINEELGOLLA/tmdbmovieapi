package com.example.tmdbmovieapi.model.api;

import com.example.tmdbmovieapi.model.pojo.genremain;
import com.example.tmdbmovieapi.model.pojo.similarmovielistmain;
import com.example.tmdbmovieapi.model.pojo.tdmovieresults;
import com.example.tmdbmovieapi.model.pojo.searchnamemain;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface tdmovieapi {

    @GET("movie?api_key=f1693850078bf7989f7c084671b57a5b&}")
    Call <tdmovieresults> getlist(@Query("query") String query);

    @GET("{id}/similar?api_key=f1693850078bf7989f7c084671b57a5b&language=en-US&page=1")
    Call<similarmovielistmain> getsimilarlist(@Path("id") String id);

    @GET("{id}?api_key=f1693850078bf7989f7c084671b57a5b&language=en-US")
    Call<genremain> getgenre(@Path("id") String id);

    @GET("keyword?api_key=f1693850078bf7989f7c084671b57a5b&")
    Call<searchnamemain> getnames(@Query("query") String query);

}
