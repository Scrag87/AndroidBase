package com.example.androidbase;

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
  ImageView current_weather_icon;
  ImageView menu_button;
  ImageView settings_button;
  Switch switch1;
  String instanceState;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // mainActivity();
    // menu();
    settings();
    if (savedInstanceState == null) {
      instanceState = "Первый запуск!";
    } else {
      instanceState = "Повторный запуск!";
    }
    Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT)
        .show();
    Log.d(this.getClass().getName() , "onCreate");
  }

  @Override
  protected void onStart() {
    super.onStart();
    Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName() , "onStart");
  }

  @Override
  protected void onRestoreInstanceState(Bundle saveInstanceState) {
    super.onRestoreInstanceState(saveInstanceState);
    restoreSettings();
    Toast.makeText(getApplicationContext(), " onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName() , "onRestoreInstanceState");
  }

  private void restoreSettings() {
   Log.d("restoreSettings()before" , Settings.getInstance().toString());
    ((Switch) findViewById(R.id.settings_switch_wind_speed)).setChecked(Settings.getInstance().isWindspeedInMph());
    ((Switch) findViewById(R.id.settings_switch_pressure)).setChecked(Settings.getInstance().isPressureInPascal());
    ((Switch) findViewById(R.id.settings_switch_temperature)).setChecked(Settings.getInstance().isTemperatureInF());
    ((Spinner) (findViewById(R.id.settings_spinner_location))).setSelection(Settings.getInstance().getLocation());
    Log.d("restoreSettings()after" , Settings.getInstance().toString());
  }

  @Override
  protected void onResume() {
    super.onResume();
    Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName() , "onResume");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName() , "onPause");
  }

  @Override
  protected void onSaveInstanceState(Bundle saveInstanceState) {
    super.onSaveInstanceState(saveInstanceState);
    saveSettings();
    Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName() , "onSaveInstanceState");
  }

  private void saveSettings() {

    /*
      saveSettings()-before: Settings{temperatureInF=true, windspeedInMph=false, pressureInPascal=false, location=0}
      saveSettings()-after: Settings{temperatureInF=true, windspeedInMph=false, pressureInPascal=false, location=2}
      restoreSettings()before: Settings{temperatureInF=true, windspeedInMph=false, pressureInPascal=false, location=2}
      restoreSettings()after: Settings{temperatureInF=true, windspeedInMph=false, pressureInPascal=false, location=2}
    */

    Log.d("saveSettings()-before " , Settings.getInstance().toString());
    Settings.getInstance()
        .setWindspeedInMph(((Switch) findViewById(R.id.settings_switch_wind_speed)).isChecked());
    Settings.getInstance()
        .setTemperatureInF(((Switch) findViewById(R.id.settings_switch_temperature)).isChecked());
    Settings.getInstance()
        .setPressureInPascal(((Switch) findViewById(R.id.settings_switch_pressure)).isChecked());
    Spinner spinner = findViewById(R.id.settings_spinner_location);
    Settings.getInstance().setLocation(getIndexByString(spinner,spinner.getSelectedItem().toString()));
    Log.d("saveSettings()-after " , Settings.getInstance().toString());
  }

  private int getIndexByString(Spinner spinner, String string) {
    int index = 0;
    for (int i = 0; i < spinner.getCount(); i++) {
      if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(string)) {
        index = i;
        break;
      }
    }
    return index;
  }

  @Override
  protected void onStop() {
    super.onStop();
    Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName() , "onStop");
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName() , "onRestart");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName() , "onDestroy");
  }

  private void menu() {
    setContentView(R.layout.menu);
  }

  private void settings() {
    setContentView(R.layout.settings);

    Spinner spinner = findViewById(R.id.settings_spinner_location);
    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(
            this, R.array.planets_array, android.R.layout.simple_spinner_item);
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
