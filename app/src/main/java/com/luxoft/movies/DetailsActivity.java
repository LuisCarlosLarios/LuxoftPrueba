package com.luxoft.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luxoft.movies.Listeners.OnDetailsApiListener;
import com.luxoft.movies.models.DetailApiResponse;
import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {
    TextView textView_movie_name, textView_movie_release, textView_movie_runtime,
            textView_movie_rating, textView_movie_plot;
    ImageView imageView_movie_poster;

    RequestManager manager;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textView_movie_name = findViewById(R.id.textView_movie_name);
        textView_movie_release = findViewById(R.id.textView_movie_release);
        textView_movie_runtime = findViewById(R.id.textView_movie_runtime);
        textView_movie_rating = findViewById(R.id.textView_movie_rating);
        textView_movie_plot = findViewById(R.id.textView_movie_plot);
        imageView_movie_poster = findViewById(R.id.imageView_movie_poster);

        manager = new RequestManager(this);
        String movie_id = getIntent().getStringExtra("data");
        dialog = new ProgressDialog(this);
        dialog.setTitle("Cargando...");
        dialog.show();
        manager.searchMovieDetails(listener, movie_id);


        }
        private OnDetailsApiListener listener = new OnDetailsApiListener(){
        @Override
        public void onResponse(DetailApiResponse response){
            if (response.equals(null)){
                Toast.makeText(DetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                return;
            }
            showResults(response);
        }
        @Override
        public void onError(String message){
            dialog.dismiss();
            Toast.makeText(DetailsActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    };

    private void showResults(DetailApiResponse response){
        textView_movie_name.setText(response.getTitle());
        textView_movie_release.setText("Estreno: "+ response.getReleased());
        textView_movie_runtime.setText("Duración "+ response.getRuntime());
        textView_movie_rating.setText("Calificación "+ response.getRuntime());
        textView_movie_plot.setText("Sinopsis "+ response.getRuntime());


        try {


            Picasso.get().load(response.getPoster()).into(imageView_movie_poster);
        }
        catch (Exception e) {
            e.printStackTrace();

        }


    }
}