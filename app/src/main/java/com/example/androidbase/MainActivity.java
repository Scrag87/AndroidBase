package com.example.androidbase;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    String time = "20:00";
    setContentView(R.layout.activity_main);
    TextView currentTime = findViewById(R.id.time);
    currentTime.setText(time);
  }
}
