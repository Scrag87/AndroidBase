package com.example.androidbase;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  TextView currentTime;
  TextView date;
  TextView city;
  TextView current_temperature;
  ImageView current_weather_icon;
  ImageView menu_button;
  ImageView settings_button;
  Switch switch1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mainActivity();
   // menu();
   // settings();

  }

  private void menu() {
    setContentView(R.layout.menu);
  }

  private void settings() {
    setContentView(R.layout.settings);

    Spinner spinner = findViewById(R.id.settings_spinner_location);
    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    spinner.setAdapter(adapter);
  }

  private void mainActivity() {
    setContentView(R.layout.activity_main);
    currentTime = findViewById(R.id.time);
    date = findViewById(R.id.date);
    city = findViewById(R.id.city);
    current_temperature = findViewById(R.id.current_temperature);
    current_weather_icon = findViewById(R.id.current_weather_icon);
    menu_button = findViewById(R.id.menu_button);
    settings_button = findViewById(R.id.settings_button);
    switch1 = findViewById(R.id.switch1);
  }

  public void switchClickToChangeTheme(View view) {
    if (switch1.isChecked()) {
      setTheme(ThemeColor.BRIGHT);
    } else {
      setTheme(ThemeColor.DARK);
    }
  }

  private void setTheme(ThemeColor color) {
    switch (color) {
      case DARK:
        // code block
        Toast.makeText(this, "Dark " + R.string.brightTheme, 2).show();
        currentTime.setTextColor(R.string.brightTheme);
        date.setTextColor(R.string.brightTheme);
        break;
      case BRIGHT:
        // code block
        Toast.makeText(this, "Bright " + R.string.darkTheme, 2).show();
        currentTime.setTextColor(R.string.darkTheme);
        date.setTextColor(R.string.darkTheme);
        break;
      default:
        // code block
    }
  }
}
