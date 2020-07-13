package com.example.androidbase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass. Use the {@link SettingsFragment#newInstance} factory method
 * to create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
  private Spinner location;
  private Switch temperatureInF;
  private Switch windspeedInMph;
  private Switch pressureInPascal;
  private Switch themeColor;

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public SettingsFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment SettingsFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static SettingsFragment newInstance(String param1, String param2) {
    SettingsFragment fragment = new SettingsFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_settings, container, false);
    init(view);
    spinnerConfigure();
    restoreSettings(view);
    Log.d("*-", "onCreateView: " + Settings.getInstance().getLocationMap());
    return view;
  }

  private void spinnerConfigure() {
    ArrayAdapter<CharSequence> adapter =
        ArrayAdapter.createFromResource(
            getContext(), R.array.locations_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    location.setAdapter(adapter);
  }

  public void init(View view) {
    temperatureInF = view.findViewById(R.id.settings_switch_temperature);
    windspeedInMph = view.findViewById(R.id.settings_switch_wind_speed);
    pressureInPascal = view.findViewById(R.id.settings_switch_pressure);
    themeColor = view.findViewById(R.id.settings_switch_theme);
    location = view.findViewById(R.id.settings_spinner_location);
  }

  public void restoreSettings(View view) {
    Log.d("restoreSettings()before", Settings.getInstance().toString());
    windspeedInMph.setChecked(Settings.getInstance().isWindspeedInMph());
    pressureInPascal.setChecked(Settings.getInstance().isPressureInPascal());
    temperatureInF.setChecked(Settings.getInstance().isTemperatureInF());
    location.setSelection(Settings.getInstance().getLocation());
    themeColor.setChecked(Settings.getInstance().getThemeColor().equals(ThemeColor.BRIGHT));
    Log.d("restoreSettings()after", Settings.getInstance().toString());
  }

  public void saveSettings(View view) {
    Log.d("saveSettings()-before ", Settings.getInstance().toString());
    Settings.getInstance().setWindspeedInMph(windspeedInMph.isChecked());
    Settings.getInstance().setTemperatureInF(temperatureInF.isChecked());
    Settings.getInstance().setPressureInPascal(pressureInPascal.isChecked());
    Settings.getInstance()
        .setLocation(
            getIndexByString(location, location.getSelectedItem().toString()),
            location.getSelectedItem().toString());
    Settings.getInstance().setThemeColor(themeColor.isChecked() ? ThemeColor.BRIGHT : ThemeColor.DARK);
    Log.d("saveSettings()-after ", Settings.getInstance().toString());
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
  public void onPause() {
    saveSettings(getView());
    super.onPause();
  }
}
