package com.example.tmdbmovieapi.view;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tmdbmovieapi.R;
import com.example.tmdbmovieapi.controller.MainActivity_controller;
import com.example.tmdbmovieapi.model.pojo.movieslist;
import com.example.tmdbmovieapi.model.adapter.movieAdapter;
import com.example.tmdbmovieapi.model.adapter.listadapter;
import com.example.tmdbmovieapi.model.pojo.searchname;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;
import com.treebo.internetavailabilitychecker.InternetConnectivityListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivity_controller.FlowerCallbackListener, InternetConnectivityListener {

    SearchView searchView;
    CharSequence name;
    RecyclerView recyclerView;
    private List <movieslist> data = new ArrayList <>();
    private MainActivity_controller mController;
    private movieAdapter adapter;
    private ListView listView;

    private String message = "NO INTERNET";

    private boolean status;

    listadapter listadapter;

    private String sname;

    private InternetAvailabilityChecker mInternetAvailabilityChecker;

    private Snackbar snackbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        System.out.println("main");

        snackbar = Snackbar
                .make(findViewById(R.id.recyclerview), message, Snackbar.LENGTH_LONG);








        mController = new MainActivity_controller(MainActivity.this);

        configViews();

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                System.out.println("close listener");
                listView.setVisibility(View.INVISIBLE);
                listView.setEnabled(false);
                listView.setClickable(false);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                if(status == true) {

                    System.out.println("onQueryTextSubmit called");
                    name = searchView.getQuery();
                    mController.startFetching(name.toString());
                    listView.setVisibility(View.INVISIBLE);
                    listView.setEnabled(false);
                    listView.setClickable(false);
                    searchView.clearFocus();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(status == true) {
                    System.out.println("onQueryTextChange called");
                    listView.setVisibility(View.VISIBLE);
                    listView.setEnabled(true);
                    listView.setClickable(true);
                    mController.fetchnames(s);
                }

                return false;
            }


        });


        InternetAvailabilityChecker.init(this);

        mInternetAvailabilityChecker = InternetAvailabilityChecker.getInstance();
        mInternetAvailabilityChecker.addInternetConnectivityListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        if(status == false){
            snackbar.setDuration(BaseTransientBottomBar.LENGTH_INDEFINITE);
            snackbar.show();
        }




    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {

        super.onSaveInstanceState(savedInstanceState);

        if(searchView.getQuery().toString()!=null) {

            savedInstanceState.putString("searchname", searchView.getQuery().toString());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        System.out.println("onRestoreInstanceState called");

        sname = savedInstanceState.getString("searchname");
        if(!sname.isEmpty()){
            System.out.println("serchoing agaim");
            searchView.setQuery(sname,true);
            mController.startFetching(sname);
            searchView.clearFocus();
        }



    }



    private void configViews() {


        listView = findViewById(R.id.listview);


        searchView = findViewById(R.id.search);


        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        adapter = new movieAdapter(data,getApplicationContext());

        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onFetchStart() {

    }


    @Override
    public void onFetchComplete(List<movieslist> data) {
        adapter.addmovies(data);

    }



    @Override
    public void onFetchFailed(String messagefailed) {
        Toast.makeText(getApplicationContext(),messagefailed,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFetchsearchresultComplete(List<searchname> name) {
        //System.out.println(name);
        listadapter = new listadapter(this, R.layout.listviewitem, name);
        listView.setAdapter(listadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> adapterView, View view, int i, long l) {
                searchView.setQuery(name.get(i).getName(),true);
                mController.startFetching(name.get(i).getName());
                listView.setEnabled(false);
                listView.setClickable(false);
                listView.setVisibility(View.INVISIBLE);

            }
        });


    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {
        System.out.println("lol called");
        if(isConnected == false){
            status = false;
            System.out.println("false");
            searchView.clearFocus();
            showsnack();
            searchView.setEnabled(false);
            searchView.setSubmitButtonEnabled(false);
        }
        else{
            status = true;
            System.out.println("true");
            snackbar.dismiss();
            searchView.setEnabled(true);
            searchView.setSubmitButtonEnabled(true);
        }
    }

    private void showsnack() {

        System.out.println("pol");
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.RED);
        snackbar.setDuration(BaseTransientBottomBar.LENGTH_INDEFINITE).show();
    }
}
