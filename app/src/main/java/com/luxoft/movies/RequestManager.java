package com.luxoft.movies;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.luxoft.movies.Listeners.OnDetailsApiListener;
import com.luxoft.movies.Listeners.OnSearchApiListener;
import com.luxoft.movies.models.DetailApiResponse;
import com.luxoft.movies.models.SearchApiResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit ;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public class RequestManager {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void searchMovies(OnSearchApiListener listener, String movie_name){
        getMovies getMovies = retrofit.create(RequestManager.getMovies.class);
        Call<SearchApiResponse> call = getMovies.callMovies(movie_name);

        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context,"No se encontraron Datos", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }
    public void searchMovieDetails(OnDetailsApiListener listener, String movie_id){
        getMovieDetails getMovieDetails = retrofit.create(RequestManager.getMovieDetails.class);
        Call <DetailApiResponse> call = getMovieDetails.callMovieDetails(movie_id);

        call.enqueue(new Callback<DetailApiResponse>() {
            @Override
            public void onResponse(Call<DetailApiResponse> call, Response<DetailApiResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context,"No se encontraron Datos", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<DetailApiResponse> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });

    }




    public interface getMovies {
        @Headers({
                "Accept: application/json",
        })
        @GET("/?apikey=ce1cae7a&s=")
        Call<SearchApiResponse> callMovies(

                @Query("movie_name") String movie_name
        );
    }

    public interface getMovieDetails {
        @Headers({
                "Accept: application/json",
        })
        @GET("/?apikey=ce1cae7a&t=")
        Call<DetailApiResponse> callMovieDetails(

                @Query("movie_id") String movie_id
        );
    }


}

