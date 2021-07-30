package com.malekAubert.android.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

  private Button mButtonSearch;
  public static final String finalKey = "movieName";
  private Intent intent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mButtonSearch = findViewById(R.id.searchButton);
    mButtonSearch.setOnClickListener(v -> Log.d("msg", "Click on Search Button"));

    intent = new Intent(this, MovieActivity.class);
    Log.d("Create", "MainActivity : onCreate()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d("Destroy", "MainActivity : onDestroy()");
  }

  public void onClickButton(View view) {

    String movieName = "";
    switch (view.getId()) {
      case R.id.button1:
        movieName = "Legends of the Fall";
        break;
      case R.id.button2:
        movieName = "If Only";
        break;
    }

    intent.putExtra(finalKey, movieName);
    startActivity(intent);
  }
}
