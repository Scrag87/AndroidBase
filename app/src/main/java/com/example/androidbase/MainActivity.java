package com.example.androidbase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
  static {
    Settings.getInstance();
  }

  SettingsFragment settingsFragment;
  WeatherFragment weatherFragment;
  WeatherForecastFragment weatherForecastFragment;
  MenuFragment menuFragment;
  String instanceState;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    fillMap();
    setContentView(R.layout.activity_main);
    initFragments();
    if (findViewById(R.id.fragment_container) != null) {
      if (savedInstanceState != null) {
        return;
      }
      getSupportFragmentManager()
          .beginTransaction()
          // сделал 2 фрагмента на одном экране а как отобразить weatherForecastFragment внизу пока
          // не разобрался
          //          .add(R.id.fragment_container, menuFragment) //test
          .add(R.id.fragment_container, weatherFragment)
          //          .add(R.id.fragment_container, weatherForecastFragment)
          .commit();
    }

    if (savedInstanceState == null) {
      instanceState = "Первый запуск!";
    } else {
      instanceState = "Повторный запуск!";
    }
    Toast.makeText(getApplicationContext(), instanceState + " - onCreate()", Toast.LENGTH_SHORT)
        .show();
    Log.d(this.getClass().getName(), "onCreate");
  }

  private void initFragments() {
    weatherFragment = new WeatherFragment();
    weatherFragment.setArguments(getIntent().getExtras());

    weatherForecastFragment = new WeatherForecastFragment();
    weatherForecastFragment.setArguments(getIntent().getExtras());

    settingsFragment = new SettingsFragment();
    settingsFragment.setArguments(getIntent().getExtras());

    menuFragment = new MenuFragment();
    menuFragment.setArguments(getIntent().getExtras());
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
    Toast.makeText(getApplicationContext(), " onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
    Log.d(this.getClass().getName(), "onRestoreInstanceState");
  }

  @Override
  public void onBackPressed() {
    toMainFragments(new View(this));
    //    weatherFragment.changeTheme(new View(this));
    settingsFragment.saveSettings(new View(this));
    if (weatherFragment.isInLayout()) {
      super.onBackPressed();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

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

  public void openSettingsFragment(View view) {
    // switch to setting fragment
    getSupportFragmentManager()
        .beginTransaction()
        .add(settingsFragment, "")
        .replace(R.id.fragment_container, settingsFragment)
        .commit();
  }

  public void toMainFragments(View view) {
    getSupportFragmentManager()
        .beginTransaction()
        .remove(settingsFragment)
//        .remove(menuFragment)
        .add(R.id.fragment_container, weatherFragment)
        //            .add(R.id.fragment_container, weatherForecastFragment)
        .commit();
  }

  public void toMainScreen(View view) {
    Log.d("TAG", "toMainScreen: +");
    menuFragment.okButtonHandler(view);
    getSupportFragmentManager()
        .beginTransaction()
        .remove(menuFragment)
        .add(R.id.fragment_container, weatherFragment)
        .commit();
  }

  public void citySelector(View view) {
    getSupportFragmentManager()
        .beginTransaction()
//        .add(menuFragment, "")
        .replace(R.id.fragment_container, menuFragment)
        .commit();
  }
}
