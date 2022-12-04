package com.example.recyclerdemo;

import retrofit2.Call;
import retrofit2.http.GET;
public interface  MovieService {
    @GET("top_rated?api_key=3bb4c761f77505d13427b549232349fa")
    Call<MoviesModel> getMovieModel();
    @GET("top_?api_key=3bb4c761f77505d13427b549232349fa")
    Call<String> get();
}
