package com.example.recyclerdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    RecyclerView recyclerView;
    AdvancedRecycleAdapter advancedRecycleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        recyclerView = findViewById(R.id.recycler);

        ArrayList<Movie> movies = new ArrayList<>();
        advancedRecycleAdapter = new AdvancedRecycleAdapter(movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(advancedRecycleAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService service = retrofit.create(MovieService.class);
        Call<MoviesModel> call = service.getMovieModel();
        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                if (response.isSuccessful()) {
                    updateData(response.body().getMovies());
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {
            }
        });
    }
    public void updateData(ArrayList<Movie> models) {
        advancedRecycleAdapter.setData(models);
    }
}