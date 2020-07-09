package com.example.androidbase;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.settings);
        Spinner spinner = findViewById(R.id.settings_spinner_location);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this, R.array.locations_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        restoreSettings();
    }

    @Override
    public void onBackPressed() {
      saveSettings();
        super.onBackPressed();
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
        Settings.getInstance().setLocation(getIndexByString(spinner,spinner.getSelectedItem().toString()),spinner.getSelectedItem().toString());
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

    private void restoreSettings() {
        Log.d("restoreSettings()before" , Settings.getInstance().toString());
        ((Switch) findViewById(R.id.settings_switch_wind_speed)).setChecked(Settings.getInstance().isWindspeedInMph());
        ((Switch) findViewById(R.id.settings_switch_pressure)).setChecked(Settings.getInstance().isPressureInPascal());
        ((Switch) findViewById(R.id.settings_switch_temperature)).setChecked(Settings.getInstance().isTemperatureInF());
        ((Spinner) (findViewById(R.id.settings_spinner_location))).setSelection(Settings.getInstance().getLocation());
        Log.d("restoreSettings()after" , Settings.getInstance().toString());
    }
}
