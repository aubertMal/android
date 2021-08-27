package com.malekAubert.android.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.malekAubert.android.movieapp.R;
import com.malekAubert.android.movieapp.adapters.SearchAdapter;
import com.malekAubert.android.movieapp.models.Movie;
import com.malekAubert.android.movieapp.models.Parser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {

  private ArrayList<Movie> mMovies;
  private Movie mMovie;
  private MovieActivity mMovieActivity = new MovieActivity();
  private SearchAdapter mAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    Bundle extras = getIntent().getExtras();
    Log.d("MovieName", extras.getString(MainActivity.finalKey));
    findMoviesByTitle(extras.getString(MainActivity.finalKey));

    RecyclerView mRecyclerView = findViewById(R.id.my_recycler_view);
    mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

    mAdapter = new SearchAdapter(this, mMovies);
    mRecyclerView.setAdapter(mAdapter);
  }

  private void initMovieList() {
    Log.d("LISTE", "Start init List");
    mMovies = new ArrayList<>();
    mMovies.add(
        new Movie(
            "If Only",
            "15 Jul 2004",
            "Gil Junger",
            "blablabla",
            "https://m.media-amazon.com/images/M/MV5BZjg1ZDg4ZTQtMmFjNS00N2VkLWEzNDUtYzE3YTUxZjU5ZjE2XkEyXkFqcGdeQXVyNTM0NTU5Mg@@._V1_SX300.jpg"));
    Log.d("LISTE", ":" + mMovies);
  }

  public Movie findMoviesByTitle(String movieTitle) {
    OkHttpClient mOkHttpClient = new OkHttpClient();
    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = cm.getActiveNetworkInfo();

    if (networkInfo != null && networkInfo.isConnected()) {
      Request request =
          new Request.Builder()
              .url("http://www.omdbapi.com/?s=" + movieTitle + "&apikey=bf4e1adb")
              .build();

      mOkHttpClient
          .newCall(request)
          .enqueue(
              new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                  Log.d("FAILURE", "Echec de connexion à l'API");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                  if (response.isSuccessful()) {
                    final String stringJson = response.body().string();
                    Parser parser = new Parser();
                    mMovies = parser.setMoviesFromJSON(stringJson);
                    Log.d("TAG", stringJson);
                  }
                }
              });
    } else {
      Toast.makeText(this, "Vous n'êtes pas connecté", Toast.LENGTH_LONG).show();
    }
    return mMovie;
  }
}
