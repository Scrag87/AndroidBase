package com.example.androidbase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass. Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment {

  private TextView current_temperature_unit;

  private TextView currentTime;
  private TextView date;
  private TextView city;
  private TextView current_temperature;
  private ImageView current_weather_icon;
  private ImageView menu_button;
  private ImageView settings_button;
  private final Settings instance = Settings.getInstance();

  // TODO: Rename parameter arguments, choose names that match
  // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";

  // TODO: Rename and change types of parameters
  private String mParam1;
  private String mParam2;

  public WeatherFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of this fragment using the provided
   * parameters.
   *
   * @param param1 Parameter 1.
   * @param param2 Parameter 2.
   * @return A new instance of fragment WeatherFragment.
   */
  // TODO: Rename and change types and number of parameters
  public static WeatherFragment newInstance(String param1, String param2) {
    WeatherFragment fragment = new WeatherFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Settings.getInstance();
    fillMap();
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_weather, container, false);
    mainActivity(view);
    setParams(view);
    changeTheme(view);
    onClickCityWeatherListener(view);
//   restoreSettings(view);
    return view;
  }

  private void onClickCityWeatherListener(View view) {
    TextView textView = view.findViewById(R.id.city);
    textView.setOnClickListener(view1 -> getTheWeather(textView));
  }

  public void getTheWeather(View view) {
    String url = "https://yandex.com/weather/";
    TextView city = view.findViewById(R.id.city);
    Intent browser =
        new Intent(Intent.ACTION_VIEW, Uri.parse(url + city.getText().toString().toLowerCase()));
    startActivity(browser);
  }

  private void setParams(View view) {
    Log.d("*", "setParams: in");
    current_temperature_unit = view.findViewById(R.id.temperature_unit);
    if (Settings.getInstance().isTemperatureInF()) {
      current_temperature_unit.setText("\u2109");
    } else {
      current_temperature_unit.setText("\u2103");
    }

  }

  @Override
  public void onResume() {
    super.onResume();
    city.setText(instance.getLocationMap().get(instance.getLocation()));
    setParams(Objects.requireNonNull(getView()));
  }

  private void mainActivity(View view) {
    currentTime = view.findViewById(R.id.time);
    date = view.findViewById(R.id.date);
    city = view.findViewById(R.id.city);
    current_temperature = view.findViewById(R.id.current_temperature);
    current_temperature_unit = view.findViewById(R.id.temperature_unit);
    current_weather_icon = view.findViewById(R.id.current_weather_icon);
    menu_button = view.findViewById(R.id.menu_button);
    settings_button = view.findViewById(R.id.settings_button);
    city.setText(instance.getLocationMap().get(instance.getLocation()));
  }

  public void changeTheme(View view) {
    if (Settings.getInstance().getThemeColor().equals(ThemeColor.BRIGHT)) {
      setTheme(ThemeColor.BRIGHT);
    } else {
      setTheme(ThemeColor.DARK);
    }
  }

  private void setTheme(ThemeColor color) {
    Log.d(TAG, "setTheme: ");
    switch (color) {
      case DARK:
        // code block
        currentTime.setTextColor(R.string.brightTheme);
        date.setTextColor(R.string.brightTheme);
        break;
      case BRIGHT:
        Log.d(TAG, "setTheme: bright");
        //Почемуто не срабатывает смена цвета шрифта
        // code block
        currentTime.setTextColor(R.string.darkTheme);
        date.setTextColor(R.string.darkTheme);
        break;
      default:
        // code block
    }
  }

  private void fillMap() {
    String[] locations = getResources().getStringArray(R.array.locations_array);
    for (int i = 0; i < locations.length; i++) {
      Settings.getInstance().setLocation(i, locations[i]);
    }
  }
}
