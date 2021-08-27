package com.malekAubert.android.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Placeholder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.malekAubert.android.movieapp.R;

public class MainActivity extends AppCompatActivity {

  public static final String finalKey = "movieName";
  private EditText mEditText;
  private Intent intentSearch;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mEditText = findViewById(R.id.movieTitle);

    Intent intentMovie = new Intent(this, MovieActivity.class);
    intentSearch = new Intent(this,SearchActivity.class);

    Log.d("Create", "MainActivity : onCreate()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d("Destroy", "MainActivity : onDestroy()");
  }

  public void onClickSearch(View view){
    Toast.makeText(this,"searchButton clicked",Toast.LENGTH_LONG);
    intentSearch.putExtra(finalKey,mEditText.getText().toString());
    startActivity(intentSearch);
  }
}
