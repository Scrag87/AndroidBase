package com.example.androidbase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  TextView currentTime;
  TextView date;
  TextView city;
  TextView current_temperature;
  TextView current_temperature_unit;
  ImageView current_weather_icon;
  ImageView menu_button;
  ImageView settings_button;
  Switch switch1;
  String instanceState;
  Settings instance = Settings.getInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mainActivity();
    fillMap();
    setParams();
    if (savedInstanceState == null) {
      instanceState = "Первый запуск!";
    } else {
      instanceState = "Повторный запуск!";
    }
    Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT)
        .show();
    Log.d(this.getClass().getName(), "onCreate");
  }

  private void setParams() {
    if (Settings.getInstance().isTemperatureInF()) {
      current_temperature_unit.setText("\u2109");
    } else {
      current_temperature_unit.setText("\u2103");
    }
  }

  private void fillMap() {
    String[] locations = getResources().getStringArray(R.array.locations_array);
    for (int i = 0; i < locations.length; i++) {
      Settings.getInstance().setLocation(i, locations[i]);
    }
  }

  @Override
  protected void onStart() {
    super.onStart();
    Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName(), "onStart");
  }

  @Override
  protected void onRestoreInstanceState(Bundle saveInstanceState) {
    super.onRestoreInstanceState(saveInstanceState);
    restoreSettings();

    Toast.makeText(getApplicationContext(), " onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName(), "onRestoreInstanceState");
  }

  private void restoreSettings() {
    Log.d("restoreSettings()before", Settings.getInstance().toString());
    ((Switch) findViewById(R.id.settings_switch_wind_speed))
        .setChecked(Settings.getInstance().isWindspeedInMph());
    ((Switch) findViewById(R.id.settings_switch_pressure))
        .setChecked(Settings.getInstance().isPressureInPascal());
    ((Switch) findViewById(R.id.settings_switch_temperature))
        .setChecked(Settings.getInstance().isTemperatureInF());
    ((Spinner) (findViewById(R.id.settings_spinner_location)))
        .setSelection(Settings.getInstance().getLocation());
    Log.d("restoreSettings()after", Settings.getInstance().toString());
  }

  @Override
  protected void onResume() {
    super.onResume();
    setParams();
    city.setText(instance.getLocationMap().get(instance.getLocation()));
    Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName(), "onResume");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName(), "onPause");
  }

  @Override
  protected void onSaveInstanceState(Bundle saveInstanceState) {
    super.onSaveInstanceState(saveInstanceState);
    //    saveSettings();
    Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName(), "onSaveInstanceState");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName(), "onStop");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName(), "onRestart");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName(), "onDestroy");
  }

  private void menu() {
    setContentView(R.layout.menu);
  }

  private void mainActivity() {
    setContentView(R.layout.activity_main);

    currentTime = findViewById(R.id.time);
    date = findViewById(R.id.date);
    city = findViewById(R.id.city);
    current_temperature = findViewById(R.id.current_temperature);
    current_temperature_unit = findViewById(R.id.temperature_unit);
    current_weather_icon = findViewById(R.id.current_weather_icon);
    menu_button = findViewById(R.id.menu_button);
    settings_button = findViewById(R.id.settings_button);
    switch1 = findViewById(R.id.switch1);

    city.setText(instance.getLocationMap().get(instance.getLocation()));
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

  public void openSettingsActivity(View view) {
    Intent intent = new Intent(this, SettingsActivity.class);
    startActivity(intent);
  }

  public void getTheWeather(View view) {
    // https://yandex.com/weather/almaty
    TextView city = findViewById(R.id.city);
    Intent browser =
        new Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://yandex.com/weather/" + city.getText().toString().toLowerCase()));
    startActivity(browser);
  }
}
