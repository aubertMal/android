package com.malekAubert.android.movieapp.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.malekAubert.android.movieapp.R;
import com.malekAubert.android.movieapp.activities.MainActivity;
import com.malekAubert.android.movieapp.models.Movie;
import com.malekAubert.android.movieapp.models.Parser;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieActivity extends AppCompatActivity {
  private OkHttpClient mOkHttpClient;
  private ProgressBar progressBar;
  private LinearLayout linearLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_movie);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);
    toolBarLayout.setTitle(getTitle());

    progressBar = findViewById(R.id.progressBar);
    linearLayout = findViewById(R.id.pageprincipale);

    Intent intent = new Intent(this,SearchActivity.class);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Snackbar.make(view, "start Search", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show();
          }
        });

    //Démarrer la page sur une progressBar
    linearLayout.setVisibility(View.INVISIBLE);
    progressBar.setVisibility(View.VISIBLE);

    mOkHttpClient = new OkHttpClient();
    Bundle extras = getIntent().getExtras();
    getMovieFromAPI(extras.getString(MainActivity.finalKey));
  }

  public void updateUi(Movie movie) {
    TextView mTextViewTitle = findViewById(R.id.movieTitle);
    mTextViewTitle.setText(movie.getTitle());

    TextView mTextViewReleaseDate = findViewById(R.id.release);
    mTextViewReleaseDate.setText(movie.getReleaseDate());

    TextView mTextViewDescription = findViewById(R.id.details);
    mTextViewDescription.setText(movie.getDescription());

    ImageView mImageViewPoster = findViewById(R.id.poster);
    Log.d("poster", movie.getUrlPoster());
    Picasso.get().setLoggingEnabled(true);
    Picasso.get().load(movie.getUrlPoster()).into(mImageViewPoster);
  }

  public void getMovieFromAPI(String movieTitle) {

    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo = cm.getActiveNetworkInfo();

    if (networkInfo != null && networkInfo.isConnected()) {
      Toast.makeText(
              this, "Vous êtes connecté et vous recherchez: " + movieTitle, Toast.LENGTH_LONG)
          .show();
      Request request =
          new Request.Builder()
              .url("http://www.omdbapi.com/?t=" + movieTitle + "&apikey=bf4e1adb")
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
                    runOnUiThread(
                        new Runnable() {
                          @Override
                          public void run() {
                            //j'ajoute un sleep juste pour tester la progressBar
                            try{
                              Thread.sleep(2000);
                            }catch(InterruptedException e){
                              e.printStackTrace();
                            }
                            Parser parser = new Parser();
                            progressBar.setVisibility(View.GONE);
                            linearLayout.setVisibility(View.VISIBLE);
                            // Code exécuté dans le Thread principale
                            updateUi(parser.setMovieFromJSON(stringJson));
                          }
                        });
                    Log.d("TAG", stringJson);
                  }
                }
              });
    } else {
      Toast.makeText(this, "Vous n'êtes pas connecté", Toast.LENGTH_LONG).show();
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d("Destroy", "MovieActivity : onDestroy()");
  }
}
